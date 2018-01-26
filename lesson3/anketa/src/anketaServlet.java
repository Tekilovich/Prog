import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Anketa", urlPatterns = {"/"})
public class anketaServlet extends HttpServlet {

    private String firstName;
    private String lastName;
    private int age;

    private Map<String, Integer> drinksMap = new answerMap("BEER", "TEA", "COFFEE").getMap();
    private Map<String, Integer> dishesMap = new answerMap("PIZZA", "SUSHI", "BORSCHT").getMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        age = Integer.parseInt(req.getParameter("age"));

        String drink = (req.getParameter("drink")).toUpperCase();
        String[] dishes = req.getParameterValues("dish");

        int count;

        if (this.drinksMap.containsKey(drink)) {
            count = this.drinksMap.get(drink);
            this.drinksMap.put(drink, count+1);
        }


        for (int i = 0; i < dishes.length; i++) {
            String dish = dishes[i].toUpperCase();
            if (this.dishesMap.containsKey(dish)) {
                count = this.dishesMap.get(dish);
                this.dishesMap.put(dish, count+1);
            }
        }


        PrintWriter out = resp.getWriter();

        out.print("<html><body>");

        out.print("<h1> Rating of popularity of drinks:</h1>");
        out.print("<ul>");
        for (Map.Entry entry : this.drinksMap.entrySet()) {
            out.printf("<li>%s = %d </li>", entry.getKey(), entry.getValue());
        }
        out.print("</ul>");

        out.print("<h1> Rating of popularity of dishes:</h1>");
        out.print("<ul>");
        for (Map.Entry entry : this.dishesMap.entrySet()) {
            out.printf("<li>%s = %d </li>", entry.getKey(), entry.getValue());
        }
        out.print("</ul>");
    }
}
