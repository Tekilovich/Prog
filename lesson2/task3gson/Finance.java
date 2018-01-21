package lesson2.task3gson;

import java.util.HashMap;
import java.util.Map;

public class Finance {
    private String sourceId;
    private String date;
    private Organization[] organizations;

    public void printAsk(String currency) {
        currency = currency.toUpperCase();
        for (Organization organization : organizations) {
            double ask = organization.getAsk(currency);
            if (ask != -1) {
                System.out.println(organization.getTitle() + " = " + ask);
            }
        }
    }

    public void printMinimumAsk(String currency) {
        currency = currency.toUpperCase();
        Map<String,Double> listMinimum = new HashMap<>();
        double minimumAsk = organizations[0].getAsk(currency);
        listMinimum.put(organizations[0].getTitle(),minimumAsk);

        for (int i = 1; i < organizations.length; i++) {
            double ask = organizations[i].getAsk(currency);
            if ((ask != -1) && (ask < minimumAsk)) {
                minimumAsk = ask;
                listMinimum.clear();
                listMinimum.put(organizations[i].getTitle(), ask);
            }
            if ((ask != -1) && (ask == minimumAsk)) {
                listMinimum.put(organizations[i].getTitle(), ask);
            }
        }

        System.out.println("Minimum:");
        for (Map.Entry entry : listMinimum.entrySet()) {
            System.out.printf("%s = %f\n", entry.getKey(),entry.getValue());
        }
    }

    public void printMaximumAsk(String currency) {
        currency = currency.toUpperCase();
        Map<String,Double> listMaximum = new HashMap<>();
        double maximumAsk = organizations[0].getAsk(currency);
        listMaximum.put(organizations[0].getTitle(),maximumAsk);

        for (int i = 1; i < organizations.length; i++) {
            double ask = organizations[i].getAsk(currency);
            if ((ask != -1) && (ask > maximumAsk)) {
                maximumAsk = ask;
                listMaximum.clear();
                listMaximum.put(organizations[i].getTitle(), ask);
            }
            if ((ask != -1) && (ask == maximumAsk)) {
                listMaximum.put(organizations[i].getTitle(), ask);
            }
        }

        System.out.println("Maximum:");
        for (Map.Entry entry : listMaximum.entrySet()) {
            System.out.printf("%s = %f\n", entry.getKey(),entry.getValue());
        }
    }
}
