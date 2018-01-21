package lesson2.task3gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        String request = "http://resources.finance.ua/ru/public/currency-cash.json";

        String result = performRequest(request);

        Gson gson = new GsonBuilder().create();
        Finance finance = (Finance) gson.fromJson(result, Finance.class);
        String currency = "usd";

        finance.printAsk(currency);

        System.out.println();

        finance.printMinimumAsk(currency);
        finance.printMaximumAsk(currency);

        //System.out.println("Finance: \n\t" + gson.toJson(finance));

    }

    private static String performRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }

        return sb.toString();
    }
}
