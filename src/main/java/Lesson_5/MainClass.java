package Lesson_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    private static final int CARS_COUNT = 4;
    static CountDownLatch[] beforeStart = {new CountDownLatch(CARS_COUNT+1),new CountDownLatch(CARS_COUNT+1)};
    static CountDownLatch afterFinish = new CountDownLatch(CARS_COUNT+1);
    static ReentrantLock finishLine = new ReentrantLock();
    static int theCup = 0;
    static long t;

    public static void main(String[] args) {
        t=System.currentTimeMillis();
        System.out.println(getTimer()+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            beforeStart[0].countDown();
            beforeStart[0].await();
            System.out.println(getTimer()+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            //второй счетчик, а то регулярно фальстарты бывали.
            beforeStart[1].countDown();
            beforeStart[1].await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        afterFinish.countDown();
        try {
            afterFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getTimer()+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static String getTimer() {
        return System.currentTimeMillis()-t + " ";
    }
}