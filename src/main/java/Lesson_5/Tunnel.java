package Lesson_5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    // У каждого тоннеля должна быть своя пропускная способность!
    // И это нужно закрепить в Конституции Тоннелей!
    private Semaphore bandwidth;

    public Tunnel(int bandwidth) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        //Кто раньше запросил, тот раньше и поедет!
        this.bandwidth =new Semaphore(bandwidth,true);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(MainClass.getTimer() + c.getName() + " готовится к этапу(ждёт): " + description);
                bandwidth.acquire();
                System.out.println(MainClass.getTimer() + c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(MainClass.getTimer() + c.getName() + " закончил этап: " + description);
                bandwidth.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// По комментарию про Милорда Архитектора из предыдущего ДЗ: это все что осталось от изначальной версии.
// Запамятовал про него, когда чистил.
// Изначально было так: https://github.com/Polpsi/JavaLevel3/pull/5/files
// Иногда загоняюсь(не всегда торкает), если оставлять такие комменты, то дайте знать.