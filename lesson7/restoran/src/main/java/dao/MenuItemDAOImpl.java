package dao;

import model.Menu;
import model.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class MenuItemDAOImpl implements MenuItemDAO {
    private EntityManager em;

    public MenuItemDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addDish(MenuItem menu) {
            em.getTransaction().begin();
            try {
                em.persist(menu);
                em.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
    }

    @Override
    public List<MenuItem> getByPrice(double from, double to) {

        try {
            Query query = em.createQuery("SELECT m FROM MenuItem m WHERE m.price >= :from AND m.price <= :to", MenuItem.class);
            query.setParameter("from", from);
            query.setParameter("to", to);

            List<MenuItem> list = query.getResultList();

            return list;

        } catch (NoResultException ex) {
            System.out.println("Dishes not found!");
            return null;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result");
            return null;
        }

    }

    @Override
    public List<MenuItem> getWithDiscount() {

        try {
            Query query = em.createQuery("SELECT m FROM MenuItem m WHERE m.discount > 0", MenuItem.class);

            List<MenuItem> list = query.getResultList();

            return list;
        } catch (NoResultException ex) {
            System.out.println("Dishes not found!");
            return null;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result");
            return null;
        }
    }

    @Override
    public List<MenuItem> getByMaxWeight(double max) {
        try {
            Query query = em.createQuery("SELECT m FROM MenuItem m WHERE m.weight < :max", MenuItem.class);
            query.setParameter("max", max);

            List<MenuItem> list = query.getResultList();

            return list;
        } catch (NoResultException ex) {
            System.out.println("Dishes not found!");
            return null;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result");
            return null;
        }
    }
}
