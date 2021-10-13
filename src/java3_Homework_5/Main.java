package java3_Homework_5;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static final int CARS_COUNT = 4;
    public static CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args)  {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Thread[] thread = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), countDownLatch);
        }
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(cars[i]);
            thread[i].start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            cars[i].startRace();
        }
        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

    }
}
