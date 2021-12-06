package Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.RandomAccessFile;

@Slf4j
public class FileHandler extends ChannelInboundHandlerAdapter {

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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        UploadFile in = (UploadFile) msg;
        byte[] bytes = in.getBytes();
        String fileName = in.getFileName();
        byteRead = in.getEndPosition();
        String pathFile = directory + File.separator + fileName;
        File file = new File(pathFile);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(start);
        randomAccessFile.write(bytes);
    }


}
