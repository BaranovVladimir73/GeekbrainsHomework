package java2_Homework_5;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] array = new float[size];

    public static void main(String[] args) {

        checkTimeExecution1();
        checkTimeExecution2();

    }

    static void checkTimeExecution1(){

        for (float arr:array) {
            arr = 1;
        }
        long time = System.currentTimeMillis();

        doCalculation(array);

        System.out.println(System.currentTimeMillis()-time);

    }

    static void checkTimeExecution2() {

        for (float arr:array) {
            arr = 1;
        }
        long time = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(array, 0, a1, 0, h);
        System.arraycopy(array, h, a2, 0, h);

        Thread thread1 = new Thread(()->{
            doCalculation(a1);
        });

        Thread thread2 = new Thread(()->{
            doCalculation(a2);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, array, 0, h);
        System.arraycopy(a2, 0, array, h, h);

        System.out.println(System.currentTimeMillis()-time);

    }

    static void doCalculation(float arr[]){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
