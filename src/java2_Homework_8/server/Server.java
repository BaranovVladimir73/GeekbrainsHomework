package java2_Homework_8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUsers;
    private final AuthService authService;

    public Server() {
        authService = new AuthService();
        loggedUsers = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);

            while (true){
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void subscribe(ClientHandler user){
        loggedUsers.add(user);

    }

    public void unsubscribe(ClientHandler user){
        loggedUsers.remove(user);
    }

    public boolean isNotUserOccupied(String name){
        return !isUserOccupied(name);
    }

    public boolean isUserOccupied(String name){
        return loggedUsers.stream()
                .anyMatch(u -> u.getName().equals(name));
    }

    public void broadcastMessage(String outboundMessage){

        loggedUsers.forEach(clientHandler -> clientHandler.sendMessage(outboundMessage));

    }

}

