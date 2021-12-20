package Client;

import common.AbstractMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class UploadFileHandler extends SimpleChannelInboundHandler<AbstractMessage> {

    private final OnMessageReceived callback;

    public UploadFileHandler(OnMessageReceived callback) {
        this.callback = callback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                AbstractMessage abstractMessage) throws Exception {
        callback.onReceive(abstractMessage);
    }

}
