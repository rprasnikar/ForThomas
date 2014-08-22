/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import at.jollydays.booking.bo.BuhaBooking;

/**
 *
 * @author Gunter Reinitzer
 */
public class BuhaFilterJpaController {

    public BuhaFilterJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaFilter buhaFilter) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaBooking buhaBooking = buhaFilter.getBuhaBooking();
            if (buhaBooking != null) {
                buhaBooking = em.getReference(buhaBooking.getClass(), buhaBooking.getId());
                buhaFilter.setBuhaBooking(buhaBooking);
            }
            em.persist(buhaFilter);
            if (buhaBooking != null) {
                buhaBooking.getBuhaFilterCollection().add(buhaFilter);
                buhaBooking = em.merge(buhaBooking);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaFilter buhaFilter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaFilter persistentBuhaFilter = em.find(BuhaFilter.class, buhaFilter.getId());
            BuhaBooking buhaBookingOld = persistentBuhaFilter.getBuhaBooking();
            BuhaBooking buhaBookingNew = buhaFilter.getBuhaBooking();
            if (buhaBookingNew != null) {
                buhaBookingNew = em.getReference(buhaBookingNew.getClass(), buhaBookingNew.getId());
                buhaFilter.setBuhaBooking(buhaBookingNew);
            }
            buhaFilter = em.merge(buhaFilter);
            if (buhaBookingOld != null && !buhaBookingOld.equals(buhaBookingNew)) {
                buhaBookingOld.getBuhaFilterCollection().remove(buhaFilter);
                buhaBookingOld = em.merge(buhaBookingOld);
            }
            if (buhaBookingNew != null && !buhaBookingNew.equals(buhaBookingOld)) {
                buhaBookingNew.getBuhaFilterCollection().add(buhaFilter);
                buhaBookingNew = em.merge(buhaBookingNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaFilter.getId();
                if (findBuhaFilter(id) == null) {
                    throw new NonexistentEntityException("The buhaFilter with id " + id + " no longer exists.");
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
            BuhaFilter buhaFilter;
            try {
                buhaFilter = em.getReference(BuhaFilter.class, id);
                buhaFilter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaFilter with id " + id + " no longer exists.", enfe);
            }
            BuhaBooking buhaBooking = buhaFilter.getBuhaBooking();
            if (buhaBooking != null) {
                buhaBooking.getBuhaFilterCollection().remove(buhaFilter);
                buhaBooking = em.merge(buhaBooking);
            }
            em.remove(buhaFilter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaFilter> findBuhaFilterEntities() {
        return findBuhaFilterEntities(true, -1, -1);
    }

    public List<BuhaFilter> findBuhaFilterEntities(int maxResults, int firstResult) {
        return findBuhaFilterEntities(false, maxResults, firstResult);
    }

    private List<BuhaFilter> findBuhaFilterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaFilter.class));
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

    public BuhaFilter findBuhaFilter(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaFilter.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaFilterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaFilter> rt = cq.from(BuhaFilter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
