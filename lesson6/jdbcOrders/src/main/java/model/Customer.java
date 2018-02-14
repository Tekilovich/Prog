package model;

public class Customer {

    private int id;
    private String name;

    public Customer(int id, String name) {
        this.name = name;
    }

    public int getid() {
        return id;
    }

    public void setCustomerId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
