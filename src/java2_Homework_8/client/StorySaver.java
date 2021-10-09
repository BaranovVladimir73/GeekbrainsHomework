package java2_Homework_8.client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorySaver {

    File story = new File("C:\\Java\\Java_2_lesson_8\\src\\java2_Homework_8\\story.txt");

    public void saveStory(String message){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(story, true))){
            bufferedWriter.newLine();
            bufferedWriter.write(message);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public List<String> sendStory(){

        ArrayList<String> temp = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(story))) {
            while (bufferedReader.ready()){
                temp.add(bufferedReader.readLine());
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        if (temp.size()>100) {
            return temp.subList(temp.size() - 100, temp.size());
        } else return temp;
    }
}
