package Lesson_1.HomeWork;

import java.util.ArrayList;


public class BoxHW<T extends Fruit> {
    ArrayList<T> elements = new ArrayList<>();

    //Ну, коробка и коробка, просто создаём
    BoxHW() {
    }

    //Добавление одного фрукта в коробку, с проверкой на соответствие.
    public boolean addInBox(T element) {
        if (elements.isEmpty() || elements.get(0).getClass() == element.getClass()) {
            elements.add(element);
            return true;
        } else {
            return false;
        }
    }

    //Добавление сразу кучки фруктов, но с проверкой каждого на соответствие содержимому коробки.
    //Если хоть один фрукт не тот (напутали на овощебазе), не добавляем ничего.
    public boolean addInBox(ArrayList<T> element) {
        for (T elem : element) {
            if (!elements.isEmpty() && elements.get(0).getClass() != elem.getClass()) {
                return false;
            }
        }
        elements.addAll(element);
        return true;
    }

    //Вес всех фруктов в коробке
    public float getWeight() {
        float sum = 0;
        for (T element : elements) {
            sum += element.getWeight();
        }
        return sum;
    }

    //Сравнение весов двух коробок
    public boolean compare(BoxHW anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    //Пересыпаем из текущей коробки в другую, если там другие фрукты, то не пересыпаем
    public boolean transferToAnotherBox(BoxHW anotherBox) {
        if (anotherBox.addInBox(elements)) {
            elements.clear();
            return true;
        } else return false;
    }
}
