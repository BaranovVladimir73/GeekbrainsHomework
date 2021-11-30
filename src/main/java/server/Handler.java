package server;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable{

    private boolean isRunning;
    private final byte[] buffer;
    private final DataInputStream inputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket;

    public Handler(Socket socket) throws IOException {
        isRunning = true;
        buffer = new byte[8192];
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

    }

    public void stop(){
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (isRunning){

                String fileName = inputStream.readUTF();

                try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\result\\" + fileName)) {

                    int read;
                    while ((read = inputStream.read(buffer)) != -1) {

                        fileOutputStream.write(buffer, 0, read);
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }

            }
            close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void close() throws IOException {

        inputStream.close();
        socket.close();
    }
}
