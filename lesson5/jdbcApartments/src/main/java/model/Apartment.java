package model;

public class Apartment {

    private int id;
    private String area;
    private String address;
    private double sqft;
    private int rooms;
    private double price;

    public Apartment(String area, String address, double sqft, int rooms, double price) {
        this.area = area;
        this.address = address;
        this.sqft = sqft;
        this.rooms = rooms;
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public double getSqft() {
        return sqft;
    }

    public int getRooms() {
        return rooms;
    }

    public double getPrice() {
        return price;
    }

    public void print() {
        System.out.printf("%s\t\t%s\t\t%f\t\t%d\t\t%f\n",
                area, address, sqft, rooms, price);
    }
}
