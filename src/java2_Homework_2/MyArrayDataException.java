package java2_Homework_2;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(String message, NumberFormatException e) {
        super(message);
    }

}
