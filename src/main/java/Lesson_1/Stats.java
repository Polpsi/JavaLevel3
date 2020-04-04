package Lesson_1;

import java.lang.reflect.Field;

public class Stats<T extends Number> {
    private T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }

    public boolean sameAvg(Stats<? super Float> another) {
        return Math.abs(this.avg() - another.avg()) < 0.0001;
    }
}

class MainStats {
    public static void main(String[] args) {
        Integer[] inums = {1,2,3,4,5};
        Double[] dnums = {1.0,2.0,3.0,4.0,15.0};

        Stats<Integer> iob = new Stats<>(inums);
        Stats<Double> dob = new Stats<>(dnums);

//        if (iob.sameAvg(dob)) {
//            System.out.println("Средние значения равны");
//        } else {
//            System.out.println("Средние значения не равны");
//        }

//        double res = iob.avg();
//        System.out.println(res);
//
//        Double[] dnums = {1.0,2.0,3.0,4.0,5.0};
//        Stats<Double> iob1 = new Stats<>(dnums);
    }
}
