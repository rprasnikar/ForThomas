/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaKonten;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import at.jollydays.booking.db.exceptions.PreexistingEntityException;
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
public class BuhaKontenJpaController {

    public BuhaKontenJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaKonten buhaKonten) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buhaKonten);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBuhaKonten(buhaKonten.getKontonummer()) != null) {
                throw new PreexistingEntityException("BuhaKonten " + buhaKonten + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaKonten buhaKonten) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buhaKonten = em.merge(buhaKonten);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaKonten.getKontonummer();
                if (findBuhaKonten(id) == null) {
                    throw new NonexistentEntityException("The buhaKonten with id " + id + " no longer exists.");
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
            BuhaKonten buhaKonten;
            try {
                buhaKonten = em.getReference(BuhaKonten.class, id);
                buhaKonten.getKontonummer();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaKonten with id " + id + " no longer exists.", enfe);
            }
            em.remove(buhaKonten);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaKonten> findBuhaKontenEntities() {
        return findBuhaKontenEntities(true, -1, -1);
    }

    public List<BuhaKonten> findBuhaKontenEntities(int maxResults, int firstResult) {
        return findBuhaKontenEntities(false, maxResults, firstResult);
    }

    private List<BuhaKonten> findBuhaKontenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaKonten.class));
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

    public BuhaKonten findBuhaKonten(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaKonten.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaKontenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaKonten> rt = cq.from(BuhaKonten.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
