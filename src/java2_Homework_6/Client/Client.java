package java2_Homework_6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {


    private Socket socket;

    public Client() {
        start();
        communicate();

    }
    private void start(){
        try {
            Thread.sleep(3000);
            socket = new Socket("LocalHost", 8899);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicate(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            AtomicBoolean isAlive = new AtomicBoolean(true);

            new Thread(()->{
                try{
                    while (true) {
                        String inBoundMessage = in.readUTF();
                        if (inBoundMessage.equals("-end")) {
                            isAlive.set(false);
                            System.out.println("Please, press Enter to close client");
                            break;
                        } else {
                            if(inBoundMessage.equals("-exit")){
                                out.writeUTF("-end");
                                isAlive.set(false);
                                System.out.println("Server closed connection");
                                System.out.println("Please, press Enter to close client");
                                break;
                            }
                        }
                        System.out.println(inBoundMessage);

                    }
                } catch (IOException e){
                    e.printStackTrace();

                }
            })
                    .start();

            while (true){

                if (!isAlive.get()) {
                    System.out.println("Client closing...");
                    System.out.println("STATUS OK");
                    break;
                }
                String outBoundMessage = scanner.nextLine();
                try {
                    out.writeUTF(outBoundMessage);
                } catch (SocketException e){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
