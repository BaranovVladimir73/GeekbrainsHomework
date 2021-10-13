package java3_Homework_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


public class Car implements Runnable{

    private static CountDownLatch countDownLatch;
    private static int CARS_COUNT;
    private static AtomicBoolean winner = new AtomicBoolean(false);
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch countDownLatch) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatch.countDown();
            synchronized (countDownLatch) {
                countDownLatch.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (winner.compareAndSet(false, true)){
            System.out.println(this.name + " is Winner!");
        }

    }

    public void startRace(){

        synchronized (countDownLatch) {
            countDownLatch.notifyAll();
        }
    }
}
