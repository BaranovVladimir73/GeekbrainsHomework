import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel serverChannel;
    private Selector selector;
    private ByteBuffer buf;
    private Path path;

    public NioServer(int port) throws IOException {

        buf = ByteBuffer.allocate(5);
        serverChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        path = Paths.get("C:\\task");

        while (serverChannel.isOpen()){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            try{
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()){
                        handleAccept();
                    }
                    if (key.isReadable()){
                        handleRead(key);
                    }
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        StringBuilder msg = new StringBuilder();
        while (true){
            int read = channel.read(buf);
            if (read == -1){
                channel.close();
                return;
            }
            if (read == 0){
                break;
            }
            buf.flip();
            while (buf.hasRemaining()){
                msg.append((char) buf.get());
            }
            buf.clear();
        }
        processMessage(channel, msg.toString().trim());
    }

    private void processMessage(SocketChannel channel, String msg) throws IOException {

        String[] message = msg.split(" +");
        CommandType type = null;

        try{
            type = CommandType.byCommand(message[0]);
            switch (type){
                case LS:
                    doLsCommand(channel);
                    break;
                case CAT:
                    doCatCommand(channel, message);
                    break;
                case CD:
                    doCdCommand(channel, message);
                    break;
                case TOUCH:
                    doTouchCommand(channel, message);
                    break;
                case MKDIR:
                    doMkdirCommand(channel, message);
            }
        } catch (RuntimeException e) {
            String response = "Command " + message[0] + " is not exists!\n\r";
            writeIntoConsole(channel,response);
        }

    }

    private void doLsCommand(SocketChannel channel) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path path : directoryStream) {
                String fileName = path.getFileName().toString() + "\n\r";
                writeIntoConsole(channel, fileName);
            }
        }
    }

    private void doCatCommand(SocketChannel channel, String[] msg) throws IOException {
        if ((msg == null) || msg.length != 2){
            writeIntoConsole(channel, "This command must have 2 args");
        } else {
            Path pathToFile = path.resolve(msg[1]);
            if (Files.exists(pathToFile)) {
                String fileText = new String(Files.readAllBytes(pathToFile)) + "\n\r";
                writeIntoConsole(channel, fileText);
            } else {
                writeIntoConsole(channel, "File doesn't exist \n\r");
            }
        }
    }

    private void doCdCommand(SocketChannel channel, String[] msg) throws IOException {
        if ((msg == null) || msg.length != 2){
            writeIntoConsole(channel, "This command must have 2 args \n\r");
        } else {
            String directoryPath = msg[1];
            if (Files.isDirectory(path.resolve(directoryPath))) {
                path = path.resolve(directoryPath);
            } else {
                writeIntoConsole(channel, "This directory doesn't exist \n\r");
            }
        }
    }

    private void doTouchCommand(SocketChannel channel, String[] msg) throws IOException {
        if ((msg == null) || msg.length != 2){
            writeIntoConsole(channel, "This command must have 2 args \n\r");
        } else {
            String fileName = msg[1];
            Files.createFile(path.resolve(fileName));
        }
    }

    private void doMkdirCommand(SocketChannel channel, String[] msg) throws IOException {
        if ((msg == null) || msg.length != 2){
            writeIntoConsole(channel, "This command must have 2 args \n\r");
        } else {
            String directoryName = msg[1];
            Files.createDirectory(path.resolve(directoryName));
        }
    }


    private void writeIntoConsole(SocketChannel channel, String msg) throws IOException {
        channel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
     }

    private void handleAccept() throws IOException {
        System.out.println("Client accepted...");
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, "Hello world!");
    }

    public static void main(String[] args) throws IOException {
        new NioServer(8189);
        
    }
}
