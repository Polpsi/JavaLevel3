// Отдельный модуль для компиляции
// Пункт 3.
import java.io.*;
import java.util.Scanner;

public class MainRead10Mb {

    public static void main(String[] args) {
        Scanner getFilePath = new Scanner(System.in);
        File file;
        while (true) {
            System.out.println("Input file with path:");
            String input = getFilePath.nextLine();
            if (input.equals("quit")) return;
            file = new File(input);
            if (!file.exists()) {
                System.out.println("File not found. Try again!");
                continue;
            } else {
                break;
            }
        }
        long t = System.currentTimeMillis();
        try {
            readFile (file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Результат 2140-2180мс, размер файла 10,2 МБ (10 702 962 байт)
        //Загрузка - меньше секунды, по ощущениям в консоли cmd. Команда: java -jar read10mb.jar
        System.out.println(System.currentTimeMillis()-t);
        System.out.println("finish");
    }

    private static void readFile(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] arr = new byte[1800];
        int x;
        while ((x = in.read(arr))!= -1) {
            System.out.print(new String(arr,0,x));
        }
    }
}
