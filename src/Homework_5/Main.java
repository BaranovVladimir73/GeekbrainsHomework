package Homework_5;

public class Main {

    public static void main(String[] args) {

        employee[] persArray = new employee[5];

        persArray[0] = new employee("Иван", "Иванов", "инженер-конструктор", "ivanov@mail.ru","+79370012300", 30000, 30);
        persArray[1] = new employee("Артём", "Сергеев", "инженер-испытатель", "sergeev@mail.ru","+79370012121", 59000, 43);
        persArray[2] = new employee("Сергей", "Родимов", "главный конструктор", "rodimov@mail.ru","+79370012470", 90000, 51);
        persArray[3] = new employee("Светлана", "Сорокина", "бухгалтер", "sorokina@mail.ru","+79370012299", 30000, 25);
        persArray[4] = new employee("Ирина", "Андреева", "юрист", "andreeva@mail.ru","+79370012371", 35000, 31);

        for (int i = 0; i < persArray.length; i++) {
            if (persArray[i].getAge() >= 40) {
                persArray[i].printInConsole();

            }
        }

    }
}
