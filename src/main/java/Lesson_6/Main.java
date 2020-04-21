package Lesson_6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arrInt = new int[15];
        int searchValue = 4;
        int[] arrFourOne = new int[5];
        int checkValue1 = 1;
        int checkValue2 = 4;

        // Пункт 1.
        //Не хотелось самому придумывать массив...
        //А так и каждый раз разный, и придумывать не надо.
        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = (int) (Math.random() * 20);
        }

        System.out.println(Arrays.toString(arrInt));
        int[] res = afterLastValue(arrInt, searchValue);
        System.out.println(Arrays.toString(res));

        // Пункт 2.
        //Аналогично
        for (int i = 0; i < arrFourOne.length; i++) {
            arrFourOne[i] = (Math.random() > 0.5 ? checkValue1 : checkValue2);
        }

        System.out.println(Arrays.toString(arrFourOne));
        System.out.println(checkTwoValues(arrFourOne, checkValue1, checkValue2));
    }

    //Придадим немного универсальности методу. Пусть будет не только после последней четвёрки.
    //Два теста, на результат и на исключение.
    static int[] afterLastValue(int[] arrInt, int searchValue) {
        for (int i = arrInt.length - 1; i >= 0; i--) {
            if (arrInt[i] == searchValue) {
                return Arrays.copyOfRange(arrInt, i + 1, arrInt.length);
            }
        }
        throw new RuntimeException("Где хоть одно нужное значение?");
    }

    //Придадим немного универсальности методу. Пусть будут не только единица и четыре.
    //И если массив содержит хоть одно "левое" значение, выдадим исключение.
    //Два теста, на результат и на исключение.
    static boolean checkTwoValues(int[] arrFourOne, int value1, int value2) {
        boolean check = false;
        for (int i = 0; i < arrFourOne.length - 1; i++) {
            if (arrFourOne[i] != value1 && arrFourOne[i] != value2) {
                throw new RuntimeException("В массиве есть посторонние включения.");
            }
            if (arrFourOne[i] != arrFourOne[i + 1]) {
                check = true;
            }
        }
        return check;
    }
    //Tests passed: 22 of 22 tests - 21ms
}