import java.io.BufferedReader;

public class Parameters {

    public static double getDoubleFromConsole(BufferedReader reader, String question) {

        double param;

        while (true) {
            try {
                System.out.println(question);
                param = Double.parseDouble(reader.readLine());
                return param;
            } catch (Exception ex) {
                System.out.println("Format error");
            }
        }


    }

    public static Integer getIntFromConsole(BufferedReader reader, String question) {

        int param;

        while (true) {
            try {
                System.out.println(question);
                param = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception ex) {
                System.out.println("Format error");
            }
        }

        return param;

    }

    public static String getStringFromConsole (BufferedReader reader, String question) {
        String param;

        while (true) {
            try {
                System.out.println(question);
                param = reader.readLine();
                break;
            } catch (Exception ex) {
                System.out.println("Format error");
            }
        }

        return param;
    }
}
