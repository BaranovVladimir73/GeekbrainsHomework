package Homework_2;

public class homework2 {

    public static void main(String[] args) {

        // Вызов метода из первого задания

        int firstNumber = 9;
        int secondNumber = 7;

        System.out.println("Cумма положительна? - " + sumChecking(firstNumber, secondNumber));

        // Вызов метода из второго задания

        int number1 = 4;

        positiveNumberChecking(number1);

        // Вызов метода из третьего задания

        int number2 = -7;

        System.out.println("Число отрицательное? - " + negativeNumberChecking(number2));

        // Вызов метода из четвёртого задания

        String txtToPrint = "Это текст для четвёртого задания";
        int count = 3;

        printText(txtToPrint, count);

        // Вызов метода из пятого задания

        int year = 2100;

        System.out.println("Год високосный? - " + checkingYear(year));

    }

    public static boolean sumChecking(int a, int b) {
        if ((a + b) > 10 && (a + b) <= 20) return true;
        else return false;
    }

    public static void positiveNumberChecking(int a){
        if (a >= 0) System.out.println("Число положительное");
        else System.out.println("Число отрицательное");
    }

    public static boolean negativeNumberChecking(int a) {
        if (a < 0) return true;
        else return false;
    }

    public static void printText(String text, int a){
        for (int i = 1; i <= a; i++){
            System.out.println(text);
        }
    }

    public static boolean checkingYear(int a){
        if ((a % 4 == 0) && !(a % 100 == 0) || (a % 400 == 0)) return true;
        else return false;
    }
}
