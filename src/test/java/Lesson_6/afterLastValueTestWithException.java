package Lesson_6;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class afterLastValueTestWithException {
    private int[] arrStarted;
    private int searchValue;


    public afterLastValueTestWithException(int[] arrStarted, int searchValue) {
        this.arrStarted = arrStarted;
        this.searchValue = searchValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {1, 2, 3, 1, 5}, 4},
                {new int[] {1, 2, 3, 3, 2}, 6},
                {new int[] {1, 3, 1, 5}, 2},
                {new int[] {1, 2, 3, 3, 5}, 7}
        });
    }

    @Test(expected = RuntimeException.class)
    public void afterLastValueWithException() {
        Main.afterLastValue(arrStarted,searchValue);
    }

}

