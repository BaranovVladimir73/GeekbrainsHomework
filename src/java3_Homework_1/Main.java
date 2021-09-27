package java3_Homework_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] array = {"Слово1", "Слово2", "Слово3", "Слово4", "Слово5"};

        String[] newArray = swapElements(array, 2, 4);

        for (String element:newArray) {
            System.out.println(element);
        }

        ArrayList<String> list = toArrayList(array);

        for (String element:list) {
            System.out.println(element);
        }

        Box<Apple> box1 = new Box<>();
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());

        Box<Orange> box2 = new Box<>();
        box2.add(new Orange());
        box2.add(new Orange());
        box2.add(new Orange());
        box2.add(new Orange());

        System.out.println("Вес первой коробки = " + box1.getWeight());
        System.out.println("Вес второй коробки = " + box2.getWeight());

        System.out.println("Коробки с одинаковым весом? - " + box1.compare(box2));

        Box<Apple> box3 = new Box<>();
        box3.add(new Apple());

        box1.putToAnotherBox(box3);

        System.out.println("Вес первой коробки = " + box1.getWeight());
        System.out.println("Вес третьей коробки = " + box3.getWeight());


    }

    public static <T> T[] swapElements(T[] array, int a, int b){

        T temp1 = array[a];
        T temp2 = array[b];

        array[a] = temp2;
        array[b] = temp1;

        return array;
    }

    public static <T> ArrayList<T> toArrayList(T[] array){

        ArrayList<T> list = new ArrayList<>();

        list.addAll(Arrays.asList(array));
        return list;
    }
}
