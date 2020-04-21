package Lesson_6;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class afterLastValueTest {
    private int[] arrStarted;
    private int[] arrResult;
    private int searchValue;

    public afterLastValueTest(int[] arrStarted, int searchValue, int[] arrResult) {
        this.arrStarted=arrStarted;
        this.arrResult=arrResult;
        this.searchValue=searchValue;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {1,2,3,4,5},4,new int[] {5}},
                {new int[] {1,2,3,4,4},4,new int[] {}},
                {new int[] {7,3,1,5},7,new int[] {3,1,5}},
                {new int[] {1,2,9,3,5},9,new int[] {3,5}}
        } );
    }

    @Test
    public void afterLastValue() {
        Assert.assertArrayEquals(arrResult,Main.afterLastValue(arrStarted,searchValue));
    }
}