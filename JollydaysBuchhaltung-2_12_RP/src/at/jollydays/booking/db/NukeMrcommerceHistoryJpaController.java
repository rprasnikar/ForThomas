/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceHistory;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.io.Serializable;
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
 * @author rpadmin
 */
public class NukeMrcommerceHistoryJpaController implements Serializable {

    public NukeMrcommerceHistoryJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeMrcommerceHistory nukeMrcommerceHistory) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeMrcommerceHistory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeMrcommerceHistory nukeMrcommerceHistory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeMrcommerceHistory = em.merge(nukeMrcommerceHistory);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nukeMrcommerceHistory.getNukeHistoryId();
                if (findNukeMrcommerceHistory(id) == null) {
                    throw new NonexistentEntityException("The nukeMrcommerceHistory with id " + id + " no longer exists.");
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
            NukeMrcommerceHistory nukeMrcommerceHistory;
            try {
                nukeMrcommerceHistory = em.getReference(NukeMrcommerceHistory.class, id);
                nukeMrcommerceHistory.getNukeHistoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeMrcommerceHistory with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeMrcommerceHistory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeMrcommerceHistory> findNukeMrcommerceHistoryEntities() {
        return findNukeMrcommerceHistoryEntities(true, -1, -1);
    }

    public List<NukeMrcommerceHistory> findNukeMrcommerceHistoryEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceHistoryEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceHistory> findNukeMrcommerceHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceHistory.class));
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

    public NukeMrcommerceHistory findNukeMrcommerceHistory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceHistory.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceHistory> rt = cq.from(NukeMrcommerceHistory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
