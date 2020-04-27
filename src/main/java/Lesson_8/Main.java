package Lesson_8;

public class Main {

    //Матрица может быть любая, хоть квадратная, хоть 1*2,6*12,13*4... Вообще любая.
    //По условиям задачи от 5 до 7.
    private static final int SIZE_HOR = 5;
    private static final int SIZE_VER = 6;
    //Стартовое значение
    private static int value = 1;

    private static final int MAX_VALUE = value+(SIZE_HOR * SIZE_VER);
    private static int[][] matrix = new int[SIZE_VER][SIZE_HOR];

    public static void main(String[] args) {
        int iteration = (SIZE_HOR < SIZE_VER) ? SIZE_HOR / 2 : SIZE_VER / 2;
        int k = 0;
        do {
            try {
                setPerimetr(matrix, k, k, SIZE_HOR - (k * 2), SIZE_VER - (2 * k++));
            } catch (RuntimeException e) {
                e.printStackTrace();
                break;
            }
        } while (k <= iteration);

        //Чисто посмотреть, что получилось.
        for (int i = 0; i < SIZE_VER; i++) {
            for (int j = 0; j < SIZE_HOR; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    //Устанавливаем периметр. Ведь что есть спираль, как не последовательные периметры. В матрице, я имею в виду.
    private static void setPerimetr(int[][] matrix, int beginX, int beginY, int sizeHorizontal, int sizeVertical) throws RuntimeException {
        //Так лучше. C line() можно не только периметр рисовать. Но метод рисует периметр и точка!
        line(matrix, beginX, beginX + sizeHorizontal - 1, beginY, 'H');
        line(matrix, beginY + 1, beginY + sizeVertical - 1, beginX + sizeHorizontal - 1, 'V');
        line(matrix, beginX + sizeHorizontal - 2, beginX, beginY + sizeVertical - 1, 'H');
        line(matrix, beginY + sizeVertical - 2, beginY + 1, beginX, 'V');

        //Можно было сделать в лоб. Но много повторяющегося кода.
//        //go left-right
//        for (int i = 0; i < sizeHorizontal; i++) {
//            if (value > MAX_VALUE) break;
//            matrix[beginY][i + beginX] = value++;
//        }
//        //up-down
//        for (int i = 1; i < sizeVertical; i++) {
//            if (value > MAX_VALUE) break;
//            matrix[i + beginY][sizeHorizontal - 1 + beginX] = value++;
//        }
//        //right-left
//        for (int i = sizeHorizontal - 2; i >= 0; i--) {
//            if (value > MAX_VALUE) break;
//            matrix[sizeVertical - 1 + beginY][i + beginX] = value++;
//        }
//        //down-up
//        for (int i = sizeVertical - 2; i > 0; i--) {
//            if (value > MAX_VALUE) break;
//            matrix[i + beginY][beginX] = value++;
//        }

    }

    //Метод заполняет линию. Можно использовать в любой матрице из интов. TR or TH - это еще из HTML-тегов.
    //По хорошему ещё диагональ сделать и дженерик(чтобы не только инты), но для этого "марсохода" излишне.
    private static void line(int[][] matrix, int start, int end, int numTRorTH, char direction) throws RuntimeException {
        int i = start;
        while (true) {
            //Приехали! Выходим!
            if (value > MAX_VALUE-1) break;
            if (direction == 'H') {
                matrix[numTRorTH][i] = value++;
            } else if (direction == 'V') {
                matrix[i][numTRorTH] = value++;
            } else throw new RuntimeException("What did you mean when you send '" + direction + "' ?");
            if (start < end) {
                if (i > end - 1) break;
                i++;
            } else {
                if (i < end + 1) break;
                i--;
            }
        }
    }
}
