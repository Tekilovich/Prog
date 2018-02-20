package dao;

import model.Menu;
import model.MenuItem;

import java.util.List;

public interface MenuItemDAO {

    void addDish(MenuItem menu);
    List<MenuItem> getByPrice(double from, double to);
    List<MenuItem> getWithDiscount();
    List<MenuItem> getByMaxWeight(double max);
}
