package java2_Homework_3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, ArrayList<String>> phoneBookList = new HashMap<>();
    private ArrayList<String> phoneNumberList;

    public void add(String surname, String phoneNumber){

        phoneNumberList = phoneBookList.getOrDefault(surname, new ArrayList<>());
        phoneNumberList.add(phoneNumber);
        phoneBookList.put(surname, phoneNumberList);

        /*
        if (!phoneBookList.containsKey(surname)) {
            phoneNumberList = new ArrayList<>();
            phoneNumberList.add(phoneNumber);
            phoneBookList.put(surname, phoneNumberList);
        } else {
            phoneNumberList = phoneBookList.get(surname);
            phoneNumberList.add(phoneNumber);
            phoneBookList.put(surname, phoneNumberList);
        }*/

    }

    public ArrayList<String> get(String surname) {
        return phoneBookList.getOrDefault(surname, new ArrayList<>());
    }
}
