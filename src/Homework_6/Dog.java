package Homework_6;

public class Dog extends Animal {
    private static int count;
    public Dog(String name) {

        super(name);
        count++;
    }
    @Override
    public void run(int runDistance){
        if(runDistance < 500) {
            System.out.println(getName() + " пробежал " + runDistance +  " м");
        } else{
            System.out.println("Собаки не могут пробежать более 500 метров");
        }

    }
    @Override
    public void swim(int swimDistance){
        if(swimDistance < 10) {
            System.out.println(getName() + " проплыл " + swimDistance + " м");
        } else {
            System.out.println("Собаки не могут проплыть более 10 метров");
        }
    }
    public static int CountOfAnimals(){
        return count;
    }
}
