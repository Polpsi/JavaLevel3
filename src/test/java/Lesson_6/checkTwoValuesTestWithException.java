package Lesson_6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class checkTwoValuesTestWithException {
    private int[] started;
    private int checkValue1;
    private int checkValue2;

    public checkTwoValuesTestWithException(int[] started, int checkValue1, int checkValue2) {
        this.started=started;
        this.checkValue1=checkValue1;
        this.checkValue2=checkValue2;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new int[]{1,4,4,2,1},1,4},
                {new int[]{4,1,1,3,1},1,4},
                {new int[]{1,2,1,1,1},1,4},
                {new int[]{4,4,9,4,4},1,4},
                {new int[]{4,4,5,4,1},1,4},
                {new int[]{3,3,5,2,3},2,3},
                {new int[]{2,2,9,2,2},2,4}

        });
    }

    @Test(expected = RuntimeException.class)
    public void checkTwoValues() {
        Main.checkTwoValues(started,checkValue1,checkValue2);
    }
}