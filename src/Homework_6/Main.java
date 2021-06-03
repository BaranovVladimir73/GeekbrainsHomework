package Homework_6;

public class Main {

    public static void main(String[] args) {
        Cat barsik = new Cat("Барсик");
        barsik.run(200);
        barsik.swim(15);
        Dog bobik = new Dog("Бобик");
        bobik.run(250);
        bobik.swim(11);
        Cat vaska = new Cat("Васька");
        vaska.run(55);

        System.out.println("Количество животных: " + Animal.CountOfAnimals());
        System.out.println("Количество кошек: " + Cat.CountOfAnimals());
        System.out.println("Количество собак: " + Dog.CountOfAnimals());

    }
}
