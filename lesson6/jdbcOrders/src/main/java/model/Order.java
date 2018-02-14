package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private Customer customer;
    private List<Product> products = new ArrayList<Product>();
    private LocalDateTime date;

    public Order(Customer customer, List<Product> products, LocalDateTime date) {
        this.customer = customer;
        this.products = products;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
