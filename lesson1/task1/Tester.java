package lesson1.task1;

import java.lang.reflect.Method;

public class Tester {

    public void test(Class<?> cls) {

        Method[] methods = cls.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    int a = method.getAnnotation(Test.class).a();
                    int b = method.getAnnotation(Test.class).a();
                    method.invoke(cls.newInstance(), a, b);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
