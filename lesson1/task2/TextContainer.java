package homeWork.lesson1.task2;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "d:\\java\\file.txt")
public class TextContainer {
    String text = "Someone text";

    @Saver
    public void save(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
