/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceItems;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gunter Reinitzer
 */
public class NukeMrcommerceItemsJpaController {

    public NukeMrcommerceItemsJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<NukeMrcommerceItems> findNukeMrcommerceItemsEntities() {
        return findNukeMrcommerceItemsEntities(true, -1, -1);
    }

    public List<NukeMrcommerceItems> findNukeMrcommerceItemsEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceItemsEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceItems> findNukeMrcommerceItemsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceItems.class));
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

    public NukeMrcommerceItems findNukeMrcommerceItems(Integer id) {
        EntityManager em = getEntityManager(); 
        try {
            return em.find(NukeMrcommerceItems.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceItemsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceItems> rt = cq.from(NukeMrcommerceItems.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
