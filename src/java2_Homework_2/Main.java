package java2_Homework_2;

public class Main {

    public static void main(String[] args) {

        String[][] array = {{"10", "16", "-6", "1"},
                            {"1","11", "77", "12"},
                            {"21", "15", "33", "87"},
                            {"-5", "112", "34", "99"}};


            checkSizeArray(array);
            System.out.println("Сумма членов массива равна " + sumArrayNumbers(array));


    }
    public static void checkSizeArray(String[][] array){
        if (array.length != 4 ) {
            throw new MyArraySizeException("Массив должен быть 4х4");
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i].length != 4){
                throw new MyArraySizeException("Массив должен быть 4х4");
            }
        }
    }

    public static int sumArrayNumbers(String[][] array){
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){
                    String message = String.format("Неверный тип данных %d строке, %d колонке", i, j);
                    throw new MyArrayDataException(message, e);
                }
            }
        }
        return sum;
    }
}
