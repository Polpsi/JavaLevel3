package Lesson_1.HomeWork;

public class Fruit {

    //Вес есть у всех фруктов (кроме тех, что в невесомости, масса есть, а веса нет)
    //Поэтому в этом классе
    private float weight;

    Fruit(float weight){
        this.weight=weight;
    }


    public float getWeight(){
        return weight;
    };
}
