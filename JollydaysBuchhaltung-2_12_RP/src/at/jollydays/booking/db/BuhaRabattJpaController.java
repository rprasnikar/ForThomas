/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaRabatt;
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
public class BuhaRabattJpaController {

    public BuhaRabattJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaRabatt buhaRabatt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buhaRabatt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaRabatt buhaRabatt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buhaRabatt = em.merge(buhaRabatt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaRabatt.getId();
                if (findBuhaRabatt(id) == null) {
                    throw new NonexistentEntityException("The buhaRabatt with id " + id + " no longer exists.");
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
            BuhaRabatt buhaRabatt;
            try {
                buhaRabatt = em.getReference(BuhaRabatt.class, id);
                buhaRabatt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaRabatt with id " + id + " no longer exists.", enfe);
            }
            em.remove(buhaRabatt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaRabatt> findBuhaRabattEntities() {
        return findBuhaRabattEntities(true, -1, -1);
    }

    public List<BuhaRabatt> findBuhaRabattEntities(int maxResults, int firstResult) {
        return findBuhaRabattEntities(false, maxResults, firstResult);
    }

    private List<BuhaRabatt> findBuhaRabattEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaRabatt.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public BuhaRabatt findBuhaRabatt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaRabatt.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaRabattCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaRabatt> rt = cq.from(BuhaRabatt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
