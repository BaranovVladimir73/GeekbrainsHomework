package java2_Homework_1;

public class Treadmill implements Obstacle {

    private double distance;

    public Treadmill(double distance) {
        this.distance = distance;
    }


    public boolean checkPassing(double result){
        if(result >= distance){
            System.out.println("Дистанция " + distance + " м. Дистанция пройдена!");
            return true;

        } else {
            System.out.println("Дистанция " + distance + " м. Дистанция не пройдена!");
            return false;

        }
    }

}
