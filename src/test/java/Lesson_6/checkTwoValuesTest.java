package Lesson_6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class checkTwoValuesTest {
    private boolean result;
    private int[] started;
    private int checkValue1;
    private int checkValue2;

    public checkTwoValuesTest(int[] started, int checkValue1, int checkValue2, boolean result) {
        this.started=started;
        this.checkValue1=checkValue1;
        this.checkValue2=checkValue2;
        this.result=result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new int[]{1,4,4,1,1},1,4,true},
                {new int[]{4,1,1,1,1},1,4,true},
                {new int[]{1,1,1,1,1},1,4,false},
                {new int[]{4,4,4,4,4},1,4,false},
                {new int[]{4,4,4,4,1},1,4,true},
                {new int[]{3,3,2,2,3},2,3,true},
                {new int[]{2,2,2,2,2},2,4,false}
        });
    }

    @Test
    public void checkTwoValues() {
        Assert.assertEquals(result,Main.checkTwoValues(started,checkValue1,checkValue2));
    }
}