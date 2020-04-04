package Lesson_1;

public class BoxUltimate<T, V> {
    private T obj1;
    private V obj2;

    public BoxUltimate(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getObj1() {
        return obj1;
    }

    public void setObj1(T obj1) {
        this.obj1 = obj1;
    }

    public V getObj2() {
        return obj2;
    }

    public void setObj2(V obj2) {
        this.obj2 = obj2;
    }

    public void info() {
        System.out.println(obj1 + " " + obj2);
        System.out.println("type1 " + obj1.getClass());
        System.out.println("type2 " + obj2.getClass());
    }
}

class BoxUltimateMain {
    public static void main(String[] args) {
        BoxUltimate<Integer, String> box1 = new BoxUltimate<>(2, "str0");
        BoxUltimate<String, String> box2 = new BoxUltimate<>("str1", "str2");

        box1.info();
        box2.info();

        int x = 10;
        x = x + box1.getObj1();
    }
}
