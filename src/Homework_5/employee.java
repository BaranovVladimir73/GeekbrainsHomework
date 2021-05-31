package Homework_5;

public class employee {
    private String name;
    private String surname;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public int getAge() {
        return age;
    }

    public employee(String name, String surname, String position, String email, String phoneNumber, int salary, int age) {

        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printInConsole(){

        System.out.println("Имя: " + name);
        System.out.println("Фамилия: " + surname);
        System.out.println("Должность: " + position);
        System.out.println("email: " + email);
        System.out.println("Номер телефона: " + phoneNumber);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();

    }



}
