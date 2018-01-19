package lesson1.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Deseriazable {

    private File file;
    private Object obj;

    public Deseriazable(String path) {

        this.file = new File(path);
    }

    public Object get(Object obj) {
        this.obj = obj;
        getFromFile();
        return obj;
    }

    private void getFromFile() {

        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        String str;
        String[] field;
        String value;

        Map<String,String> mapFields = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while ((str = reader.readLine()) != null) {
                field = str.split("=");
                mapFields.put(field[0],field[1]);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }

        for (Field f : fields) {
            if (mapFields.containsKey(f.getName())) {
                if (!f.isAccessible()) f.setAccessible(true);

                value = mapFields.get(f.getName());

                switch (f.getType().getName()) {
                    case "int":
                        try {
                            f.setInt(obj, Integer.parseInt(value));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "java.lang.String":
                        try {
                            f.set(obj, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "boolean":
                        try {
                            f.set(obj, Boolean.parseBoolean(value));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }

        }

    }
}
