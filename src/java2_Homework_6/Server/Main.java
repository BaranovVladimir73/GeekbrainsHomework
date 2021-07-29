package java2_Homework_6.Server;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> new Server()).start();
    }
}
