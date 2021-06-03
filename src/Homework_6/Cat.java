package Homework_6;

public class Cat extends Animal {
    private static int count;
    public Cat(String name) {
        super(name);
        count++;

    }
    @Override
    public void run(int runDistance){
        if(runDistance < 200) {
            System.out.println(getName() + " пробежал " + runDistance +  " м");
        } else{
            System.out.println("Кошки не могут пробежать более 200 метров");
        }

    }
    @Override
    public void swim(int swimDistance){
        System.out.println("Кошки не умеют плавать");
    }

    public static int CountOfAnimals(){
        return count;
    }
}
