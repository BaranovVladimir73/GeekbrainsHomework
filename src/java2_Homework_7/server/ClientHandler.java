package java2_Homework_7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()-> {
                try {
                    doAuthentication();
                    listenMessages();
                }catch (IOException e){
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }

            }).start();

        } catch (IOException e) {
            throw new RuntimeException("Something wrong during client establishing...", e);
        }

    }

    private void closeConnection(Socket socket) {

        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    private void doAuthentication() throws IOException {
        while(true){
            String maybeCredentials = in.readUTF();
            if (maybeCredentials.equals("-auth")){
                String[] credentials = maybeCredentials.split("\\s");
                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);
                if (maybeUser.isPresent()){
                    AuthService.Entry user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("AUTH OK");
                        sendMessage("Welcome!");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged in");

                    }

                } else {
                    sendMessage("Invalid credentials");

                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }
    }

    public void sendMessage(String outBoundMessage){
        try {
            out.writeUTF(outBoundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")){
                break;
            }
            server.broadcastMessage(inboundMessage);
        }

    }
}


