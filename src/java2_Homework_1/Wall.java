package java2_Homework_1;

public class Wall implements Obstacle{

    private double height;

    public Wall(double height) {
        this.height = height;
    }


    public boolean checkPassing(double result){
        if(result >= height){
            System.out.println("Высота " + height + " м. Стена пройдена!");
            return true;

        } else {
            System.out.println("Высота " + height + " м. Стена не пройдена");
            return false;

        }
    }


}
