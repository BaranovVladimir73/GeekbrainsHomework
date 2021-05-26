package Homework_4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final char EMPTY_CELL_SYMBOL = '-';

    public static void start() {
        char[][] field = {
                {EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL},
                {EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL},
                {EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL},
                {EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL},
                {EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL, EMPTY_CELL_SYMBOL}
        };

        drawField(field);

        do {
            doPlayerMove(field);
            drawField(field);
            if (isWin(field, 'X')) {
                System.out.println("Congratulations!!! You are winner.");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw!!!");
                break;
            }

            doBotMove(field);
            drawField(field);
            if (isWin(field, 'O')) {
                System.out.println("Sorry!!! You are loser. :(");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw!!!");
                break;
            }
        } while (true);

    }

    static boolean isDraw(char[][] field) {
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field.length; h++) {
                if (isEmptyCell(field, v, h)) {
                    return false;
                }
            }
        }
        return true;
    }

    // boolean is = true
    // !is -> false
    // boolean isNot = false
    // !isNot -> true

    static boolean isEmptyCell(char[][] field, int v, int h) {
        return field[v][h] == EMPTY_CELL_SYMBOL;
    }

    static boolean isNotEmptyCell(char[][] field, int v, int h) {
        // if isEmptyCell -> false -> conversion !false ->  true
        return !isEmptyCell(field, v, h);
    }

    static boolean isWin(char[][] field, char sign) {
        // Horizontal

        for (int i = 0; i < field.length; i++) {
            int count = 0;
            for (int j = 0; j < field[i].length; j++) {

                if (field[i][j] == sign) {
                    count++;
                    if (count == field[i].length) {
                        return true;
                    }
                }

            }    //   0 1 2
            // 0 - - -     <- i = 0
            // 1 - - -     <- i = 1
            // 2 - - -     <- i = 2
        }

        // Vertical
        for (int i = 0; i < field.length; i++) {
            int count = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i] == sign) {
                    count++;
                    if (count == field[i].length) {
                        return true;
                    }
                }
            }
        }

        // Diagonals
        int count1 = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if ((i == j) && field[i][j] == sign) {
                    count1++;
                    if (count1 == field[i].length) {
                        return true;
                    }
                }
            }
        }
        int count2 = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if ((i + j) == field.length - 1 && field[i][j] == sign) {
                    count2++;
                    if (count2 == field[i].length) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    static void doBotMove(char[][] field) {
        Random random = new Random();

        int v, h;
        do {
            v = random.nextInt(3);
            h = random.nextInt(3);
        } while (isNotEmptyCell(field, v, h));

        field[v][h] = 'O';
    }

    static void doPlayerMove(char[][] field) {
        int v, h;
        do {
            v = getCoordinate(field, 'v');
            h = getCoordinate(field, 'h');
        } while (isNotEmptyCell(field, v, h));

        field[v][h] = 'X';
    }

    static int getCoordinate(char[][] field, char coordinateName) {
        Scanner scanner = new Scanner(System.in);

        int coordinate;
        do {
            System.out.printf("Please enter %s-coordinate [1..3] ...%n", coordinateName);
            coordinate = scanner.nextInt() - 1;
        } while (coordinate < 0 || coordinate >= field.length);

        return coordinate;
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

}
