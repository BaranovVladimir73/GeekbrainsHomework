package Homework_6;

public class Animal {

    private String name;
    private static int count;

    public String getName() {
        return name;
    }

    public static int getCount() {
        return count;
    }

    public Animal(String name){
        this.name = name;
        count++;
    }

    public void run(int runDistance){
        System.out.println(name + " пробежал " + runDistance +  " м");
    }

    public void swim(int swimDistance){

        System.out.println(name + " проплыл " + swimDistance + " м");
    }

    public static int CountOfAnimals(){
        return count;
    }

}
