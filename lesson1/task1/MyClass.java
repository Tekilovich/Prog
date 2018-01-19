package lesson1.task1;

public class MyClass {

    @Test(a=2, b=5)
    public void test(int a, int b) {
        System.out.printf("a = %d\nb = %d\n", a, b);
    }
}
