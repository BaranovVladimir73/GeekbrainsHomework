package java2_Homework_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private ServerSocket socket;
    private Socket client;

    public Server() {


        init();
        communicate();

        System.out.println("Closing the connection...");
        System.out.println("Shutting down...");
        System.out.println("STATUS OK");
    }

    private void init(){

        try {
            socket = new ServerSocket(8899);
            System.out.println("Socket created");
            System.out.println("Waiting for connection");
            client = socket.accept();
            System.out.println("Client connected...");
            System.out.println(client);
            System.out.println("Status OK");


        } catch (IOException e) {
            System.out.println("Status NOK");
            e.printStackTrace();
        }
    }

    private void communicate(){
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            AtomicBoolean isAlive = new AtomicBoolean(true);

            new Thread(()-> {
                try {
                    while (true) {
                        String inBoundMessage = in.readUTF();

                        if (inBoundMessage.equals("-exit")) {
                            out.writeUTF("Good bye");
                            out.writeUTF("-end");
                            System.out.println("Client closed connection");
                            isAlive.set(false);
                            break;

                        } else {
                            if (inBoundMessage.equals("-end")) {
                                isAlive.set(false);
                                System.out.println("Please, press Enter to close client");
                                break;
                            }
                        }
                        System.out.println("Client: " + inBoundMessage);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }

            })
                .start();

            while (true) {

                if (!isAlive.get()) {
                    System.out.println("Server closing...");
                    System.out.println("STATUS OK");
                    break;
                }
                String outBoundMessage = scanner.nextLine();
                try {
                    out.writeUTF(outBoundMessage);
                } catch (SocketException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
