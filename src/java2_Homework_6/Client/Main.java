package java2_Homework_6.Client;

public class Main {

    public static void main(String[] args){
        new Thread(()->new Client()).start();
    }
}
