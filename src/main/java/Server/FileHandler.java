package Server;

import common.AbstractMessage;
import common.CommandToServer;
import common.ServerFileList;
import common.UploadFile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Slf4j
public class FileHandler extends SimpleChannelInboundHandler<AbstractMessage> {

    private int byteRead;
    private volatile int start = 0;
    private String directory = "C:\\result";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("Channel connected...");
        readAllFiles(ctx);
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
            String pathFile = directory + File.separator + fileName;
            File file = new File(pathFile);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            randomAccessFile.close();
            readAllFiles(ctx);
        } else if (msg instanceof CommandToServer){
            CommandToServer in = (CommandToServer) msg;
            log.debug(in.getCommand());
            if (in.getCommand().equals("#delete_file")){
                String fileNameFileToDelete = in.getFileName();
                String pathFile = directory + File.separator + fileNameFileToDelete;
                Files.delete(Paths.get(pathFile));
                readAllFiles(ctx);
            } else if (in.getCommand().equals("#rename_file")){
                String fileNameFileToRename = in.getFileName();
                String newFileNameToRename = in.getNewFileName();
                Path pathOldFile = Paths.get(directory + File.separator + fileNameFileToRename);
                Files.move(pathOldFile, pathOldFile.resolveSibling(newFileNameToRename));
                readAllFiles(ctx);
            } else if (in.getCommand().equals("#upload_file")){
                File file = new File(directory + File.separator + in.getFileName());
                try {
                    UploadFile uploadFile = new UploadFile();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                    randomAccessFile.seek(uploadFile.getStartPosition());
                    byte[] bytes = Files.readAllBytes(Paths.get(directory + File.separator + in.getFileName()));
                    int byteRead = (int) randomAccessFile.length();
                    log.debug(file.getName());
                    uploadFile.setFileName(file.getName());
                    uploadFile.setFile(file);
                    uploadFile.setBytes(bytes);
                    uploadFile.setEndPosition(byteRead);
                    log.debug(uploadFile.toString());
                    ctx.writeAndFlush(uploadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readAllFiles(ChannelHandlerContext ctx){

        ArrayList<String> listOfFiles = new ArrayList<>();

        try {
            Files.list(Paths.get(directory)).map(p -> p.getFileName().toString()).forEach(listOfFiles::add);
        } catch (IOException e) {
            e.printStackTrace();

        }

        ctx.writeAndFlush(new ServerFileList(listOfFiles));
    }


}
