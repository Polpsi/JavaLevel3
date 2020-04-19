package Lesson_5;

import java.util.concurrent.Semaphore;
//Переименовал класс, чтобы доп был после ДЗ.
public class zMFU {

    //Для скана у нас условно RAM есть.
    //Object printLock = new Object();
    //Object scanLock = new Object();
    //Классная штука Семафор, однако!
    Semaphore waitScan = new Semaphore(1, true);
    Semaphore waitPrint = new Semaphore(1, true);

    public void print(String doc, int n) {
        String operation = " печати ";
        waitOperation(waitPrint, n, operation, doc);
    }

    public void scan(String doc, int n) {
        String operation = " сканирования ";
        waitOperation(waitScan, n, operation, doc);
    }

    public void copy(String doc, int n) {
        scan(doc,n);
        print(doc,n);
    }

    //Скан-принт.... Одинаково же!
    private void waitOperation(Semaphore waitOp, int n, String ops, String doc) {
        try {
            //В очередь, "ай да братья-сёстры Пушкина".
            waitOp.acquire();
            System.out.println("Начало "+ops + " " + doc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < n; i++) {
            // "Многа цифрофф" в консоли, закомментил.
            // System.out.print(i+",");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Конец "+ops + " " + doc);
        waitOp.release();
    }

    public static void main(String[] args) {
        final zMFU mfu = new zMFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 1", 10);
            }
        }).start();

        //Здесь копируем
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.copy("Doc4", 15);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 2", 5);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 3", 5);
            }
        }).start();

    }

}
//Работает! Работодатели, в очередь!