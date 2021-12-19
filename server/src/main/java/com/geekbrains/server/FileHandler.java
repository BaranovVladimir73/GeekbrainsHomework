package com.geekbrains.server;

import com.geekbrains.common.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class FileHandler extends SimpleChannelInboundHandler<AbstractMessage> {

    private int byteRead;
    private volatile int start = 0;
    private Path directory;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("Channel connected...");
        directory = Paths.get("root");
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("Client disconnected...");

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractMessage msg) throws Exception {
        if (msg instanceof UploadFile) {
            UploadFile in = (UploadFile) msg;
            byte[] bytes = in.getBytes();
            String fileName = in.getFileName();
            byteRead = in.getEndPosition();
            Path path = Paths.get(directory.toString(), in.getUserName());
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path pathFile = Paths.get(directory.toString(), in.getUserName(), fileName);
            Files.write(pathFile, bytes);
            readAllFiles(ctx, in.getUserName());
        } else if (msg instanceof ReqToRenameFile) {
            ReqToRenameFile in = (ReqToRenameFile) msg;
            String fileNameFileToRename = in.getOldFileName();
            String newFileNameToRename = in.getNewFileName();
            Path pathOldFile = Paths.get(directory.toString(), in.getUserName(), fileNameFileToRename);
            Files.move(pathOldFile, pathOldFile.resolveSibling(newFileNameToRename));
            readAllFiles(ctx, in.getUserName());
        } else if (msg instanceof ReqToDeleteFileOnServer){
            ReqToDeleteFileOnServer in = (ReqToDeleteFileOnServer) msg;
            String fileNameFileToDelete = in.getFileToDelete();
            Files.delete(Paths.get(directory.toString(), in.getUserName(), fileNameFileToDelete));
            readAllFiles(ctx, in.getUserName());
        } else if (msg instanceof ReqDownloadFileFromServer){
            ReqDownloadFileFromServer in = (ReqDownloadFileFromServer) msg;
            File file = new File(Paths.get(directory.toString(), in.getUserName(), in.getFileName()).toString());
            try{
                UploadFile uploadFile = new UploadFile();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                randomAccessFile.seek(uploadFile.getStartPosition());
                byte[] bytes = Files.readAllBytes(Paths.get(directory.toString(), in.getUserName(), in.getFileName()));
                int byteRead = (int) randomAccessFile.length();
                log.debug(file.getName());
                uploadFile.setFileName(file.getName());
                uploadFile.setFile(file);
                uploadFile.setBytes(bytes);
                uploadFile.setEndPosition(byteRead);
                log.debug(uploadFile.toString());
                ctx.writeAndFlush(uploadFile);
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (msg instanceof AuthMessage) {
            AuthMessage authMessage = (AuthMessage) msg;
            String userName = authMessage.getUserName();
            String password = authMessage.getPassword();
            log.debug(authMessage.toString());
            String authorizedUserName = doAuthorizationOnServer(userName, password);
            log.debug(authorizedUserName);
            if (!(authorizedUserName == null)){
                AuthOk authOk = new AuthOk();
                authOk.setAuthorizedUserName(authorizedUserName);
                ctx.writeAndFlush(authOk);
            } else {
                AuthNotOk authNotOk = new AuthNotOk();
                ctx.writeAndFlush(authNotOk);
            }
        } else if(msg instanceof ReqToRefreshFilesOnServer){
            ReqToRefreshFilesOnServer reqToRefreshFilesOnServer = (ReqToRefreshFilesOnServer) msg;
            readAllFiles(ctx, reqToRefreshFilesOnServer.getUserName());
        }
    }

    private String doAuthorizationOnServer(String userName, String password){

        AuthService authService = new AuthService();
        Optional<Entry> maybeUser = authService.findUserByLoginAndPassword(userName, password);

        if (maybeUser.isPresent()) {
            Entry user = maybeUser.get();
            return user.login;
        } else return null;
    }


    private void readAllFiles(ChannelHandlerContext ctx, String userName){

        ArrayList<String> listOfFiles = new ArrayList<>();
        Path path = Paths.get(directory.toString(), userName).toAbsolutePath();
        log.debug(path.toString());

        try {
            Files.list(Paths.get(path.toString())).map(p -> p.getFileName().toString()).forEach(listOfFiles::add);
        } catch (IOException e) {
            e.printStackTrace();

        }

        ctx.writeAndFlush(new ServerFileList(listOfFiles));
    }


}
