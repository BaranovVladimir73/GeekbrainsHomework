package Server;

import common.AbstractMessage;
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
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("Client disconnected...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractMessage msg) throws Exception {
        UploadFile in = (UploadFile) msg;
        byte[] bytes = in.getBytes();
        String fileName = in.getFileName();
        byteRead = in.getEndPosition();
        String pathFile = directory + File.separator + fileName;
        File file = new File(pathFile);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(start);
        randomAccessFile.write(bytes);
        readAllFiles(ctx);
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
