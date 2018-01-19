package lesson1.task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SaveText {
    public void save() {

        TextContainer textContainer = new TextContainer();
        Class<?> cls = textContainer.getClass();

        if (cls.isAnnotationPresent(SaveTo.class)) {
            String path = cls.getAnnotation(SaveTo.class).path();
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    try {
                        method.invoke(textContainer, path);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
