/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gunter Reinitzer
 */
public class BuhaKostlJpaController {

    public BuhaKostlJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaKostl buhaKostl) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buhaKostl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaKostl buhaKostl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buhaKostl = em.merge(buhaKostl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaKostl.getId();
                if (findBuhaKostl(id) == null) {
                    throw new NonexistentEntityException("The buhaKostl with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaKostl buhaKostl;
            try {
                buhaKostl = em.getReference(BuhaKostl.class, id);
                buhaKostl.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaKostl with id " + id + " no longer exists.", enfe);
            }
            em.remove(buhaKostl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaKostl> findBuhaKostlEntities() {
        return findBuhaKostlEntities(true, -1, -1);
    }

    public List<BuhaKostl> findBuhaKostlEntities(int maxResults, int firstResult) {
        return findBuhaKostlEntities(false, maxResults, firstResult);
    }

    private List<BuhaKostl> findBuhaKostlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaKostl.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BuhaKostl findBuhaKostl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaKostl.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaKostlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaKostl> rt = cq.from(BuhaKostl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
