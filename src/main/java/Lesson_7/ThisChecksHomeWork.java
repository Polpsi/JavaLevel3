package Lesson_7;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ThisChecksHomeWork {
    private Class classHW;
    private String result = "";
    private final File fileWithResult = new File("D:\\HWPath\\result.txt");
    private Object objHW;

    public ThisChecksHomeWork(Class classHW) {
        this.classHW = classHW;
    }

    @BeforeSuite
    public void beforeSuit() throws Exception {
        this.objHW = classHW.newInstance();
        FileWriter output = new FileWriter(fileWithResult);
        output.write("Surname\tIntCalc\tFloatCalc\tTwoNumber\tNegative\tLeapYear\n");
        output.close();
        result = classHW.getName() + "\t";
    }

    @AfterSuite
    public void writeResult() throws Exception {
        FileWriter output = new FileWriter(fileWithResult, true);
        System.out.println(result);
        output.write(result);
        output.close();
    }

    @Test(priority = 1)
    private void checkIntCalculate() throws Exception {
        Class[] paramType = {int.class, int.class, int.class, int.class};
        Method method = classHW.getDeclaredMethod("calculate", paramType);
        checkAccess(method);
        int resultMust = 20;
        int resultGet = (int) method.invoke(objHW, 5, 4, 3, 2);
        if (resultGet != resultMust) {
            result += "0\t";
        } else {
            result += "1\t";
        }
    }

    @Test(priority = 2)
    private void checkFloatCalculate() throws Exception {
        Class[] paramType = {float.class, float.class, float.class, float.class};
        Method method = classHW.getDeclaredMethod("calculate", paramType);
        checkAccess(method);
        float resultMust = 22f;
        float resultGet = (float) method.invoke(objHW, 5, 4, 3, 2);
        if (resultGet != resultMust) {
            result += "0\t";
        } else {
            result += "1\t";
        }
    }

    @Test(priority = 3)
    private void twoNumber() throws Exception {
        Class[] paramType = {int.class, int.class};
        Method method = classHW.getDeclaredMethod("checkTwoNumbers", paramType);
        checkAccess(method);
        if (!((boolean) (method.invoke(objHW, 10, 20)) && (boolean) method.invoke(objHW, 5, 6))) {
            result += "1\t";
        } else {
            result += "0\t";
        }
    }


    @Test(priority = 4)
    private void checkIsNegative() throws Exception {
        Class[] paramType = {int.class};
        Method method = classHW.getDeclaredMethod("isNegative",paramType);
        checkAccess(method);
        if ((boolean) method.invoke(objHW, -5) && !(boolean) method.invoke(objHW, 5)) {
            result += "1\t";
        } else {
            result += "0\t";
        }
    }

    @Test(priority = 5)
    private void checkIsLeapYear() throws Exception {
        Class[] paramType = {int.class};
        Method method = classHW.getDeclaredMethod("isLeapYear",paramType);
        checkAccess(method);
        if ((boolean) method.invoke(objHW, 2004) && !(boolean) method.invoke(objHW, 1900) && (boolean) method.invoke(objHW, 2000)) {
            result += "1\t";
        } else {
            result += "0\t";
        }
    }

    private void checkAccess(Method method) {
        if (Modifier.isPrivate(method.getModifiers())) {
            method.setAccessible(true);
        }
    }
}