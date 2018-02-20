package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<MenuItem>();
    }

    public Menu(List<MenuItem> menu) {
        this.items = menu;
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public void print() {
        System.out.println(MenuItem.getHead());
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    private MenuItem getById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) return item;
        }

        return null;
    }

    public MenuItem chooseDish() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu(items);
        MenuItem item;
        print();

        while (true) {
            System.out.println("Enter the code of the item you want add to the order");

            try {
                int id = Integer.parseInt(reader.readLine());
                item = getById(id);
                if (item == null) {
                    System.out.println("Code error!");
                    continue;
                }
                break;
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Format error!");
                continue;
            }
        }

        return item;
    }
}
