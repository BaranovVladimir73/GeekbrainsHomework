package Homework_3;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class lesson_3 {

    public static void main(String[] args) {

        /*  Задание 1.
            Задать целочисленный массив, состоящий из элементов 0 и 1.
            Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
         */

        int[] array1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for(int i = 0; i < array1.length; i++){
            if (array1[i] == 1){
                array1[i] = 0;
            } else if(array1[i] == 0){
                array1[i] = 1;
            }
            System.out.println("array1[" + i + "] = " + array1[i]);
        }
        System.out.println();

        /* Задание 2.
            Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его
            значениями 1 2 3 4 5 6 7 8 … 100;
         */

        int[] array2 = new int[100];

        for(int i = 0; i < array2.length; i++){
            array2[i] = i + 1;
            System.out.println("array2[" + i + "] = " + array2[i]);
        }
        System.out.println();

        /* Задание 3.
           Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
           и числа меньшие 6 умножить на 2;
         */

        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for(int i = 0; i < array3.length; i++){
            if(array3[i] < 6) array3[i] = array3[i] * 2;
            System.out.println("array3[" + i + "] = " + array3[i]);
        }
        System.out.println();

        /* Задание 4.
            Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
            и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей,
            если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу: индексы таких
            элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
         */

        int[][] array4 = new int[5][5];

        for (int i = 0; i < array4.length; i++){
            for (int j = 0; j < array4[i].length; j++){
                if ((i == j) || ((i + j) == array4.length-1)) array4[i][j] = 1;
                else array4[i][j] = 0;
                if (j < array4[i].length - 1) {
                    System.out.print(array4[i][j] + " ");
                } else {
                    System.out.print(array4[i][j]);
                    System.out.println();
                }
            }
        }
        System.out.println();

        /* Задание 5.
             Написать метод, принимающий на вход два аргумента: len и initialValue, и
             возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
         */

        returnArr(5,13);

        System.out.println();

        /* Задание 6.
           Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
         */

        int[] array5 = {123, 85, 299, 194, 6, 19, 55};
        int minNumber = array5[1];
        int maxNumber = array5[1];
        for (int i = 0; i < array5.length; i++){
            if (array5[i] < minNumber) minNumber = array5[i];
            if (array5[i] > maxNumber) maxNumber = array5[i];
        }
        System.out.println("Минимальный элемент массива: " + minNumber);
        System.out.println("Максимальный элемент массива: " + maxNumber);

        System.out.println();

        /* Задание 7
            Написать метод, в который передается не пустой одномерный целочисленный массив,
            метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
         */


        int[] array6 = {3, 14, 1, 3, 1, 20};

        System.out.println("Есть ли место с равными частями в массиве? - " + checkBalance1(array6));

    }



    public static int[] returnArr(int len, int initialValue){

        int[] massive = new int[len];
        for (int i = 0; i < len; i++){
            massive[i] = initialValue;
            System.out.println(massive[i]);
        }
        return massive;
    }


    public static boolean checkBalance1(int[] arrayCheck){

        for (int j = 0; j < arrayCheck.length - 1; j++){
            int sum_1_part = 0;
            int sum_2_part = 0;
            for (int i = 0; i < arrayCheck.length; i++) {
                if(i <= j){
                    sum_1_part = sum_1_part + arrayCheck[i];
                } else sum_2_part = sum_2_part + arrayCheck[i];
                if (sum_1_part == sum_2_part) return true;
            }
        }
        return false;
    }
}
