import dao.ApartmentDAO;
import model.Apartment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Realtor {

    private static ApartmentDAO apartmentDAO;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection;

    public Realtor() {
        init();
    }

    private void init() {
        connection = ConectingFactory.getConnection();
        apartmentDAO = new ApartmentDAO(connection);
    }

    public void start() {


        try {
            try {

                while (true) {
                    System.out.println("1: find apartments by area");
                    System.out.println("2: find apartments by adress");
                    System.out.println("3: find apartments by square");
                    System.out.println("4: find apartments by number of rooms");
                    System.out.println("5: find apartments by price");
                    System.out.println("0: add apartments");
                    System.out.println("->");

                    String value = reader.readLine();
                    switch (value) {
                        case "1":
                            findByParameters("area");
                            break;
                        case "2":
                            findByParameters("address");
                            break;
                        case "0":
                            addApartment();
                            break;
                        default:
                            return;
                    }
                }


            } finally {
                reader.close();
                if (connection != null) connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void addApartment()  throws SQLException, IOException{

        Apartment apartment;

        String area;
        String address;
        double sqft, price;
        int rooms;

        while (true) {
            area = Parameters.getStringFromConsole(reader, "Input area");
            address = Parameters.getStringFromConsole(reader, "Input address");
            sqft = Parameters.getDoubleFromConsole(reader, "square apartment");
            rooms = Parameters.getIntFromConsole(reader, "Input number of rooms");
            price = Parameters.getDoubleFromConsole(reader, "Input price");

            apartment = new Apartment(area, address, sqft, rooms, price);

            apartmentDAO.addApartment(apartment);

            String s = Parameters.getStringFromConsole(reader, "Do you want input next apartment? y or n");
            if (!s.equals("y")) break;
        }

    }

    private void findByParameters(String paramType) throws IOException, SQLException{

        List<Apartment> apartments;

        try {
            System.out.printf("Input %s:", paramType);
            String area = reader.readLine();

            apartments = apartmentDAO.findByParameters(paramType, area);

            if (apartments.isEmpty()) System.out.println("Not found!");

            for (Apartment apartment : apartments) {
                apartment.print();
            }

            System.out.println("Press any key for continue");
            reader.readLine();

        } finally {
        }
    }
}
