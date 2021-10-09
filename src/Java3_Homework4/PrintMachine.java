package Java3_Homework4;

public class PrintMachine {


    private volatile char currentLetter = 'A';


    public static void main(String[] args) {
        PrintMachine printMachine = new PrintMachine();

        Thread thread1 = new Thread(()->{
            printMachine.printA();
        });
        Thread thread2 = new Thread(()->{
            printMachine.printB();
        });
        Thread thread3 = new Thread(()->{
            printMachine.printC();
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }

    public void printA(){
        synchronized (this){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A'){
                        wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    notifyAll();
                }

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (this){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B'){
                        wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    notifyAll();
                }

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void printC(){
        synchronized (this){
            try{
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C'){
                        wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    notifyAll();
                }

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
