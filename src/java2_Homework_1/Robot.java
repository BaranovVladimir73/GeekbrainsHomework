package java2_Homework_1;

public class Robot implements Participants {


    private String name;
    private double maxDistance;
    private double maxHeight;

    public Robot(String name, double maxDistance, double maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    public double getDistance() {
        return maxDistance;
    }

    public double getHeight() {
        return maxHeight;
    }

    public void run(){
        System.out.println(name + " бежит");
    }

    public void jump(){
        System.out.println(name + " прыгает");
    }
}
