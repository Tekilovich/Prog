import dao.MenuItemDAOImpl;
import model.Menu;
import model.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Restaurant {

    private EntityManager em;
    private EntityManagerFactory emf;
    private MenuItemDAOImpl menuItemDAO;

    public Restaurant() {
        init();
    }

    private void init() {
        em = ConnectionFactory.getEm();
        this.emf = ConnectionFactory.getEmf();
        menuItemDAO = new MenuItemDAOImpl(em);
    }

    public void mainMenu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            try {
                while (true) {
                    System.out.println("1: add dishes");
                    System.out.println("2: show dishes by price");
                    System.out.println("3: show dishes with discount");
                    System.out.println("4: select by weight");
                    System.out.println("5: view clients");
                    System.out.print("-> ");

                    String s = reader.readLine();
                    switch (s) {
                        case "1":
                            addDish(reader);
                            break;
                        case "2":
                            showByPrice(reader);
                            break;
                        case "3":
                            showWithDiscount(reader);
                            break;
                        case "4":
                            getByWeight(reader);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                em.close();
                emf.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addDish(BufferedReader reader) throws IOException{

            System.out.print("Enter dish name: ");
            String name = reader.readLine();
            System.out.print("Enter dish price: ");
            String sPrice = reader.readLine();
            double price = Double.parseDouble(sPrice);
            System.out.print("Enter dish weight: ");
            String sWeight = reader.readLine();
            double weight = Double.parseDouble(sWeight);
            System.out.print("Enter dish discount: ");
            String sDiscount = reader.readLine();
            double discount = Double.parseDouble(sDiscount);

            MenuItem item = new MenuItem(name, price, weight, discount);

            menuItemDAO.addDish(item);
    }

    private void showByPrice(BufferedReader reader) throws IOException{

        System.out.println("from");
        double from = Double.parseDouble(reader.readLine());
        System.out.println("to");
        double to = Double.parseDouble(reader.readLine());

        List<MenuItem> list = menuItemDAO.getByPrice(from, to);

        if (list == null) return;

        for (MenuItem item : list) System.out.println(item);

        reader.readLine();

    }

    private void showWithDiscount(BufferedReader reader) throws IOException{
        List<MenuItem> list = menuItemDAO.getWithDiscount();

        if (list == null) return;

        for (MenuItem item : list) System.out.println(item);
        reader.readLine();
    }

    private void getByWeight(BufferedReader reader) throws IOException{

        Map<MenuItem, Integer> order = new HashMap<>();
        //List<MenuItem> order = new ArrayList<MenuItem>();
        System.out.println("Input maximal weight");
        double max;
        max = Double.parseDouble(reader.readLine());
        MenuItem menuItem;

        while (true) {
            List<MenuItem> list = menuItemDAO.getByMaxWeight(max);
            if (list == null || list.isEmpty()) break;
            Menu menu = new Menu(list);
            menuItem = menu.chooseDish();
            if (order.containsKey(menuItem)) {
                order.put(menuItem, order.get(menuItem)+1);
            } else order.put(menuItem, 1);
            max = max - menuItem.getWeight();
        }

        System.out.println("Your order:");
        for (Map.Entry entry : order.entrySet()) {
            MenuItem item = (MenuItem)entry.getKey();
            System.out.printf("%s - %d шт.\n", item.getName(), entry.getValue());
        }
        reader.readLine();

    }
}
