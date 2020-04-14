package Lesson_4;

public class Main {

    // Советник: -Архитектор! Император заказал башню из разноцветного кирпича!
    // Архитектор: -Мы это сделаем, во славу Императора!

    // Архитектор: -Итак, Советник, сколько раз повторить узор?
    // Советник: -По условиям задачи - всего 5, но для Императора маловато.
    private static final int numRepeat = 5;
    // Архитектор: -Скажите, Советник, с какого цвета Император желает начать? (можно задать чаром, можно кодом ASCII)
    // Советник: -Конечно же с 'A'! Так возжелал Император и условие задачи!
    private static final char startSymbol = 'A';
    // Архитектор: -Советник, сколько цветов мы используем в нашей башне? (например, 3 - "ABC", а 5 - "ABCDE").
    // Советник: -Архитектор, по одному рабу на цвет? Расточительно!
    // Архитектор: -Архитектор здесь Я!
    private static final int quantityWorkers = 3;
    //Всё final, т.к. Император своих решений не меняет!

    //Флаг раба, который укладывает нужный цвет.
    private static int flag = 0;

    public static void main(String[] args) {
        Object overseer = new Object(); //нам нужен суровый(но справедливый!) надсмотрщик.
        for (int i = 0; i < quantityWorkers; i++) { //Т.к. количество потребных в будущем рабов неизвестно -> цикл.
            int numThread = i;
            //Новый раб. Делает то же, что и остальные, но у него будет свой цвет-символ, в зависимости от номера.
            //У него будет номер, но не будет имени. Рабство в штате Миссисиппи официально отменено только 7 февраля 2013-го года.
            new Thread(() -> {
                try {
                    sendLetter(overseer, numThread); //Будем укладывать ряд кирпичиков нужного цвета.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start(); //Начинаем укладывать...
        }
    }

    private static void sendLetter(Object overseer, int numThread) throws InterruptedException {
        synchronized (overseer) { //Хорошего взяли, следит, чтобы кучей не лезли.
            for (int i = 0; i < numRepeat; i++) {
                while (flag != numThread) { //те, кто вне очереди - ждите. Как электронная очередь в налоговой.
                    overseer.wait();
                }
                System.out.print((char) (startSymbol + numThread)); //Т.к. последовательность, то (стартовый+номер укладчика цвета).
                flag = (flag + 1) % quantityWorkers; //Неизвестно заранее, сколько всего цветов возжелает Император, потому формула.
                if (numThread == (quantityWorkers-1) && i == numRepeat-1) { //Раб, положивший последний кирпич - обязан доложить!
                    System.out.println("\n Милорд Архитектор! Башня готова!");
                }
                overseer.notifyAll(); //Кто там следующий?
            }
        }
    }
}