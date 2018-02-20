import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Restaurant");
    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEm() {
        return em;
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
