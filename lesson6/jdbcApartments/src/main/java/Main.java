import dao.ApartmentDAO;
import model.Apartment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;

public class Main {
    private static ApartmentDAO apartmentDAO;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection;

    public static void main(String[] args) {

        connection = ConectingFactory.getConnection();
        apartmentDAO = new ApartmentDAO(connection);

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

    private static void addApartment()  throws SQLException, IOException{

        Apartment apartment;

        String area;
        String address;
        double sqft;
        int rooms;
        double price;

        while (true) {
            System.out.println("Input area");
            area = reader.readLine();
            System.out.println("Input adress");
            address = reader.readLine();
            while (true) {
                try {
                    System.out.println("square apartment");
                    sqft = Double.parseDouble(reader.readLine());
                    break;
                } catch (Exception ex) {
                    System.out.println("Format error");
                }
            }
            while (true) {
                try {
                    System.out.println("Input number of rooms");
                    rooms = Integer.parseInt(reader.readLine());
                    break;
                } catch (Exception ex) {
                    System.out.println("Format error");
                }
            }
            while (true) {
                try {
                    System.out.println("Input price");
                    price = Double.parseDouble(reader.readLine());
                    break;
                } catch (Exception ex) {
                    System.out.println("Format error");
                }
            }

            apartment = new Apartment(area, address, sqft, rooms, price);

            apartmentDAO.addApartment(apartment);

            System.out.println("Do you want input next apartment? y or n");
            String s = reader.readLine();
            if (!s.equals("y")) break;
        }

    }

    private static void findByParameters(String paramType) throws IOException, SQLException{

        List<Apartment> apartments;

        try {
            System.out.printf("Input %s:", paramType);
            String area = reader.readLine();

            apartments = apartmentDAO.findByParameters(paramType, area);

            for (Apartment apartment : apartments) {
                apartment.print();
            }

            System.out.println("Press any key for continue");
            reader.readLine();

        } finally {
        }
    }
}
