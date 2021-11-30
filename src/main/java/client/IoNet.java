package client;

import java.io.*;
import java.net.Socket;

public class IoNet implements Closeable {


    private final Socket socket;
    private final DataInputStream is;
    private final DataOutputStream os;
    private final byte[] buf;


    public IoNet(Socket socket) throws IOException {

        this.socket = socket;
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        buf = new byte[8192];

    }



    public void writeFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int read;
            while ((read = fileInputStream.read(buf)) != -1){
                os.write(buf, 0 , read);
            }
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileName(File file) throws IOException {

        String fileName = file.getName();

        os.writeUTF(fileName);
        os.flush();
    }



    @Override
    public void close() throws IOException {
        os.close();
        is.close();
        socket.close();
    }
}
