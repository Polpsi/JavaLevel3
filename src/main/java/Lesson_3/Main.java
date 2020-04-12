package Lesson_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args) throws IOException {
        String filePathName = "src/main/java/Lesson_3/files/";
        //Пункт 1
        read50bytes(filePathName+"50bytes.txt");

        //Пункт 2
        String[] files = {
                filePathName+"file1.txt",
                filePathName+"file2.txt",
                filePathName+"file3.txt",
                filePathName+"file4.txt",
                filePathName+"file5.txt"};
        gluingFiles(files, filePathName+"out.txt",false);
    }

    private static void read50bytes(String filePathName) throws IOException {
        File file=new File(filePathName);
        FileInputStream readFile = new FileInputStream(file);
        //В глубине души ArrayList - массив.
        ArrayList<Byte> arrChar = new ArrayList<>();
        int x;
        while ((x = readFile.read()) != -1) {
            arrChar.add((byte)x);
        }
        System.out.println(arrChar.toString());
    }

    private static void gluingFiles(String[] arrFiles, String outputFile, boolean append) throws IOException {
        ArrayList<InputStream> files = new ArrayList<>();
        for (int i = 0; i < arrFiles.length ; i++) {
            files.add(new FileInputStream(arrFiles[i]));
        }
        SequenceInputStream input = new SequenceInputStream(Collections.enumeration(files));
        FileOutputStream outStream = new FileOutputStream(outputFile,append);
        int x;
        while ((x=input.read())!=-1) {
            outStream.write(x);
        }
    }

}
