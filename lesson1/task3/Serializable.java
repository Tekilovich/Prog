package lesson1.task3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Serializable {

    private Object obj;

    public Serializable(MyClass myClass) {
        this.obj = myClass;
    }

    public void writeToFile(String path) {


        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        ArrayList<String> listFields = new ArrayList<>();

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)){
                if (!field.isAccessible()) field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = null;
                try {
                    fieldValue = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                String str = String.format("%s=%s\r\n", fieldName, fieldValue);
                listFields.add(str);

            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {

            for (String text : listFields) {
                fileWriter.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
