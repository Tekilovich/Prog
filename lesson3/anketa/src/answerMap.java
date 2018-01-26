import java.util.HashMap;
import java.util.Map;

public class answerMap {

    private Map<String, Integer> map = new HashMap<>();
    private String[] arr;

    public answerMap(String... answers) {

        arr = answers;

        for (String answer : answers) {
            map.put(answer, 0);
        }

    }

    public Map<String, Integer> getMap() {
        return map;
    }
}
