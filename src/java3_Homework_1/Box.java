package java3_Homework_1;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> list = new ArrayList<>();
    private float boxWeight;


    public void add(T fruit){
        list.add(fruit);
    }

    public float getWeight(){

        if (!list.isEmpty()) {

            for (T element : list) {
                boxWeight += element.getWeight();
            }
        } else boxWeight = 0.0f;
        return boxWeight;
    }

    public boolean compare(Box box){

        return boxWeight == box.boxWeight;
    }

    public void putToAnotherBox(Box<T> box){

        for (T element:list) {
            box.add(element);

        }

        list.clear();

    }
}
