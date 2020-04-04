package Lesson_1.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arrStrings = {"1q", "2w", "3e", "4r", "5t", "6y"};
        Integer[] arrInt = {1, 2, 3, 4, 5, 6, 7};

        //Пункт 1
        if (swapElements(arrInt, 3, 4)) {
            System.out.println("all ok\n" + Arrays.toString(arrInt) + "\n");
        } else {
            System.out.println("All bad\nCheck indexes\n");
        }

        //Пункт 2
        ArrayList<String> arrList = arrayToList(arrStrings);
        System.out.println(arrList.toString());
        ArrayList<Integer> arrList2 = arrayToList(arrInt);
        System.out.println(arrList2.toString() + "\n");

        //Пункт 3
        // Возьмем на некой овощебазе какие-то наборы фруктов. И несколько пустых коробок.
        ArrayList apple1 = getSomeFruits(Apple.class, 5);
        ArrayList apple2 = getSomeFruits(Apple.class, 9);
        ArrayList orange1 = getSomeFruits(Orange.class, 3);
        ArrayList orange2 = getSomeFruits(Orange.class, 7);
        BoxHW boxApple1 = new BoxHW();
        BoxHW boxApple2 = new BoxHW();
        BoxHW boxOrange1 = new BoxHW();
        BoxHW boxOrange2 = new BoxHW();

        //Закинем фрукты в пустые коробки, результат: true - 4 раза
        System.out.println("Добавление фруктов в коробки "
                + boxApple1.addInBox(apple1) + ","
                + boxApple2.addInBox(apple2) + ","
                + boxOrange1.addInBox(orange1) + ","
                + boxOrange2.addInBox(orange2));

        //Попробуем закинуть апельсины к яблокам, результат: false
        System.out.println(boxApple1.addInBox(orange1));
        //А вот забытое яблоко - можно, результат: true
        System.out.println(boxApple1.addInBox(new Apple()));

        // Сравним веса яблок во второй коробке и апельсинов в первой (9 и 4.5), результат: false
        System.out.println(boxApple2.compare(boxOrange1)); //false

        //Пересыпаем из коробок в коробки (во второй попытке случайно яблоки в апельсины чуть не сыпанули)
        //Результат: true,false,true
        System.out.println("Пересыпаем фрукты по коробкам "
                + boxApple1.transferToAnotherBox(boxApple2) + ","
                + boxApple2.transferToAnotherBox(boxOrange1) + ","
                + boxOrange2.transferToAnotherBox(boxOrange1));

        //Проверим вес коробок после пересыпаний, результат: 0.0, 15.0, 15.0, 0.0
        System.out.println(boxApple1.getWeight() + ", " + boxApple2.getWeight() + ", " + boxOrange1.getWeight() + ", " + boxOrange2.getWeight());

        // Сравним веса яблок во второй коробке и апельсинов в первой (15 и 15), теперь результат: true
        System.out.println(boxApple2.compare(boxOrange1));
    }


    //Пункт 1
    private static <T> boolean swapElements(T[] arr, int el1, int el2) {
        if (el1 > arr.length - 1 || el2 > arr.length - 1) return false;
        T tmp = arr[el1];
        arr[el1] = arr[el2];
        arr[el2] = tmp;
        return true;
    }

    //Пункт 2
    private static <T> ArrayList<T> arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    //Некая овощебаза, выдающая фрукты.
    private static <T> ArrayList getSomeFruits(Class fruit, int num) {
        ArrayList fruits = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            try {
                T fr = (T) fruit.newInstance();
                fruits.add(i, fr);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return fruits;
    }
}