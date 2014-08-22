/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.bo.BMDPaymentInfo;
import at.jollydays.booking.control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rpadmin
 */
public class BMDPaymentInfoJpaController implements Serializable {

    public BMDPaymentInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BMDPaymentInfo BMDPaymentInfo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(BMDPaymentInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BMDPaymentInfo BMDPaymentInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BMDPaymentInfo = em.merge(BMDPaymentInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = BMDPaymentInfo.getId();
                if (findBMDPaymentInfo(id) == null) {
                    throw new NonexistentEntityException("The bMDPaymentInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BMDPaymentInfo BMDPaymentInfo;
            try {
                BMDPaymentInfo = em.getReference(BMDPaymentInfo.class, id);
                BMDPaymentInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The BMDPaymentInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(BMDPaymentInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BMDPaymentInfo> findBMDPaymentInfoEntities() {
        return findBMDPaymentInfoEntities(true, -1, -1);
    }

    public List<BMDPaymentInfo> findBMDPaymentInfoEntities(int maxResults, int firstResult) {
        return findBMDPaymentInfoEntities(false, maxResults, firstResult);
    }

    private List<BMDPaymentInfo> findBMDPaymentInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BMDPaymentInfo.class));
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

    public BMDPaymentInfo findBMDPaymentInfo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BMDPaymentInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getBMDPaymentInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BMDPaymentInfo> rt = cq.from(BMDPaymentInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
