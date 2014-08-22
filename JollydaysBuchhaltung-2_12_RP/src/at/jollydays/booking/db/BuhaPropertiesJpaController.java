/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaProperties;
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
public class BuhaPropertiesJpaController {

    public BuhaPropertiesJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaProperties buhaProperties) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buhaProperties);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaProperties buhaProperties) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buhaProperties = em.merge(buhaProperties);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaProperties.getId();
                if (findBuhaProperties(id) == null) {
                    throw new NonexistentEntityException("The buhaProperties with id " + id + " no longer exists.");
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
            BuhaProperties buhaProperties;
            try {
                buhaProperties = em.getReference(BuhaProperties.class, id);
                buhaProperties.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaProperties with id " + id + " no longer exists.", enfe);
            }
            em.remove(buhaProperties);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaProperties> findBuhaPropertiesEntities() {
        return findBuhaPropertiesEntities(true, -1, -1);
    }

    public List<BuhaProperties> findBuhaPropertiesEntities(int maxResults, int firstResult) {
        return findBuhaPropertiesEntities(false, maxResults, firstResult);
    }

    private List<BuhaProperties> findBuhaPropertiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaProperties.class));
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
    
    

    public List<BuhaProperties> findBuhaPropertiesPre() {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("BuhaProperties.findByNamePRE", BuhaProperties.class);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }    

    public List<BuhaProperties> findBuhaPropertiesNotPre() {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("BuhaProperties.findByNameNotPRE", BuhaProperties.class);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }    
    
    public BuhaProperties findBuhaProperties(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaProperties.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaPropertiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaProperties> rt = cq.from(BuhaProperties.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
