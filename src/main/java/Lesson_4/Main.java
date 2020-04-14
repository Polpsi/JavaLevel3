package Lesson_4;

public class Main {

    private static final int numRepeat = 5;
    private static final char startSymbol = 'A';
    private static final int quantityWorkers = 13;
    private static int flag = 0;

    public static void main(String[] args) {
        Object overseer = new Object();
        for (int i = 0; i < quantityWorkers; i++) {
            int numThread = i;
            new Thread(() -> {
                try {
                    sendLetter(overseer, numThread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void sendLetter(Object overseer, int numThread) throws InterruptedException {
        synchronized (overseer) {
            for (int i = 0; i < numRepeat; i++) {
                while (flag != numThread) {
                    overseer.wait();
                }
                System.out.print((char) (startSymbol + numThread));
                flag = (flag + 1) % quantityWorkers;
                if (numThread == (quantityWorkers-1) && i == numRepeat-1) {
                    System.out.println("\n Милорд Архитектор! Башня готова!");
                }
                overseer.notifyAll();
            }
        }
    }
}