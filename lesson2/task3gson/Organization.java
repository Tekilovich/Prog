package lesson2.task3gson;

import java.util.HashMap;
import java.util.Map;

public class Organization {
    private String id;
    private int oldId;
    private int orgType;
    private boolean branch;
    private String title;
    private String regionId;
    private String cityId;
    private String phone;
    private String adress;
    private String link;
    private Map<String, Currency> currencies = new HashMap<String, Currency>();

    public String getTitle() {
        return title;
    }

    public double getAsk(String currency) {
        if (currencies.containsKey(currency)) {
            return currencies.get(currency).getAsk();
        } else {
            return -1;
        }
    }

    public double getBid(String currency) {
        if (currencies.containsKey(currency)) {
            return currencies.get(currency).getBid();
        } else {
            return -1;
        }
    }
}
