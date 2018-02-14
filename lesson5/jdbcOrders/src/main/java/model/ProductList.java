package model;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    List<Product> products = new ArrayList<Product>();

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getByCode(int code) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == code) return p;
        }
        return product;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void show() {
        System.out.printf("%-10s\t\t%-30s\t\t%-15s\n",
                "Code", "Name", "Price");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
