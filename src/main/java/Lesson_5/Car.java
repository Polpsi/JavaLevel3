package Lesson_5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(MainClass.getTimer() + this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(MainClass.getTimer() + this.name + " готов");
            //Ждем долгоготовящихся
            MainClass.beforeStart[0].countDown();
            MainClass.beforeStart[0].await();
            //Все готовы, ждем отмашки стартёра.
            MainClass.beforeStart[1].countDown();
            MainClass.beforeStart[1].await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

         if (MainClass.theCup == 0) {
             MainClass.finishLine.lock();
             MainClass.theCup = 1;
             System.out.println(MainClass.getTimer() + this.name + ": I'm WINNER! Ядрён батон!");
         } else {
             System.out.println(MainClass.getTimer() + this.name + ": We had a situation - some unforeseen difficulties.");
         }
        MainClass.afterFinish.countDown();
        try {
            MainClass.afterFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
