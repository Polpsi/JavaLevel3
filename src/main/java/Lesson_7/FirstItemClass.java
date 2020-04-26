package Lesson_7;

public class FirstItemClass {

    //Просто какой-то объект с методами.
    public FirstItemClass() {}

    @BeforeSuite
    public void beforeTests(){
        System.out.println("BeforeSuite complete.");
    }

    @AfterSuite
    public void afterTests(){
        System.out.println("AfterSuite complete.");
    }

    @Test(priority = 10)
    public void test10() {
        System.out.println("Test10 complete.");
    }

    @Test(priority = 3)
    public void test3_1() {
        System.out.println("Test3_1 complete.");
    }

    @Test(priority = 7)
    public void test7() {
        System.out.println("Test7 complete.");
    }

    @Test(priority = 5)
    public void test5() {
        System.out.println("Test5 complete.");
    }

    @Test(priority = 3)
    public void test3_2() {
        System.out.println("Test3_2 complete.");
    }


    @Test(priority = 8)
    public void test8() {
        System.out.println("Test8 complete.");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("Test1 complete.");
    }
}
