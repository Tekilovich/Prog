package lesson1.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "lesson1/task2/file.txt")
public class TextContainer {
    private String text = "Someone text";

    @Saver
    public void save(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
