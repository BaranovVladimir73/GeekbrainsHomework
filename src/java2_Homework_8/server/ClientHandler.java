package java2_Homework_8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private Timer timer;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()-> {
                timer = new Timer();

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        sendMessage("Connection timer is off");
                        closeConnection(socket);
                    }
                };
                timer.schedule(timerTask, 120000);

            }).start();

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

        sendMessage("Greeting you in the Outstanding chat!");
        sendMessage("Please, do authentication. Template is: -auth [login] [password]");

        while (true) {
            String maybeCredentials = in.readUTF();
            /** sample: -auth login1 password1 */
            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    Entry user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        timer.cancel();
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
