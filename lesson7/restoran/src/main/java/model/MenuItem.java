package model;

import com.sun.javafx.binding.StringFormatter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "weight")
    private double weight;
    @Column(name = "discount")
    private double discount;

    public MenuItem() {
    }

    public MenuItem(String name, double price, double weight, double discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public static String getHead() {
        String str = String.format("%-9s%-35s%-10s%-10s%-10s", "id", "name", "price", "weight", "discount");
        return str;
    }

    @Override
    public String toString() {

        String str = String.format("%-9d%-35s%-10.2f%-10.2f%-10.2f", id, name, price, weight, discount);
        return str;
    }
}
