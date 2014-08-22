/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceUser2event;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author Gunter Reinitzer
 */
public class NukeMrcommerceUser2eventJpaController {

    public NukeMrcommerceUser2eventJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<NukeMrcommerceUser2event> findNukeMrcommerceUser2eventEntities() {
        return findNukeMrcommerceUser2eventEntities(true, -1, -1);
    }

    public List<NukeMrcommerceUser2event> findNukeMrcommerceUser2eventEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceUser2eventEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceUser2event> findNukeMrcommerceUser2eventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceUser2event.class));
            Query q = em.createQuery(cq);
            //q.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NukeMrcommerceUser2event findNukeMrcommerceUser2event(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceUser2event.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceUser2eventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceUser2event> rt = cq.from(NukeMrcommerceUser2event.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
