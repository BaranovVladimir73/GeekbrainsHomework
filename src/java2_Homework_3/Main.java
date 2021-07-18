package java2_Homework_3;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        doExercise1();


        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "+7123134322");
        phoneBook.add("Козлова", "+71231343182");
        phoneBook.add("Афанасьев", "+7123134322");
        phoneBook.add("Сидорова", "+75462539765");
        phoneBook.add("Соколов", "+78394253241");
        phoneBook.add("Артемьева", "+71234567890");
        phoneBook.add("Афанасьев", "+7123134321");

        System.out.println(phoneBook.get("Афанасьев"));
    }

    static void doExercise1(){
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        arrayList.add("Сообщение");
        arrayList.add("Java");
        arrayList.add("Имя");
        arrayList.add("Фамилия");
        arrayList.add("Сообщение");
        arrayList.add("Фамилия");
        arrayList.add("Чертёж");
        arrayList.add("Сообщение");
        arrayList.add("Паспорт");
        arrayList.add("Java");

        for (String str:arrayList) {
            int i = 0;
            for (String str1:arrayList) {
                if(str.equals(str1)){
                    i++;
                }
            }
            String result = str + " встречается " + i + " раз";
            set.add(result);

        }
        System.out.println(set);
    }

}