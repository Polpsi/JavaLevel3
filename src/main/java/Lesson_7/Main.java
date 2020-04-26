package Lesson_7;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {

        //Item 1
        Class classFirst = FirstItemClass.class;
        try {
            Object obj = classFirst.newInstance();
            start(classFirst,obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Item 2
        File pathWithHW = new File("HWPath");
        if (pathWithHW.isFile()) {
            try {
                checkFile(pathWithHW);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (pathWithHW.isDirectory()) {
            //Нам нужны только файлы с расширением ".class"
            String[] files = pathWithHW.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".class");
                }
            });
            for (String filePath : files) {
                File file = new File(pathWithHW.getName() + "\\" + filePath);
                try {
                    checkFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Сюда передаем еще и объект для того, чтобы можно было разные ДЗ проверять
    private static void start(Class classTest, Object obj) throws Exception {
        Method[] methods = classTest.getDeclaredMethods();
        Method beforeSuit = null;
        Method afterSuit = null;
        ArrayList<Method> tests = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuit = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterSuit = method;
            } else if (method.getAnnotation(Test.class) != null) {
                tests.add(method);
            }
        }
        tests.sort(Comparator.comparing(method -> method.getAnnotation(Test.class).priority()));
        if (beforeSuit != null) tests.add(0, beforeSuit);
        if (afterSuit != null) tests.add(afterSuit);
        if (tests.size() > 0) runMethods(obj, tests);
    }

    //Метод для выполненния методов.
    private static void runMethods(Object obj, ArrayList<Method> methods) throws Exception {
        for (Method method : methods) {
            if (Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
            }
            method.invoke(obj);
        }
    }

    //Метод для загрузки класса из файла и отправки его на проверку
    //Но что-то пошло не так...
    private static void checkFile(File path) throws Exception {
        String file = path.getName();
        String name = file.substring(0, file.lastIndexOf("."));
        System.out.println(path.getAbsolutePath());

        // Не удалось победить ClassNotFoundException, не могу понять, почему не грузит из файла.
        // Если создавать объекты не из файла, а из класса в проекте - все тесты проходят, результаты пишутся.
        // Например:
        // Class testClass = HomeWork.class;
        Class testClass = URLClassLoader.newInstance(new URL[]{path.toURL()}).loadClass(name);

        //Создаем экземпляр для проверки методов.
        Object obj = new ThisChecksHomeWork(testClass);
        start(ThisChecksHomeWork.class, obj);
    }
}