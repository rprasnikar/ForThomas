/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceEventsArrangementCities;
import at.jollydays.booking.bo.NukeMrcommerceEventsArrangementCitiesPK;
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
public class NukeMrcommerceEventsArrangementCitiesJpaController {

    public NukeMrcommerceEventsArrangementCitiesJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeMrcommerceEventsArrangementCities nukeMrcommerceEventsArrangementCities) throws PreexistingEntityException, Exception {
        if (nukeMrcommerceEventsArrangementCities.getNukeMrcommerceEventsArrangementCitiesPK() == null) {
            nukeMrcommerceEventsArrangementCities.setNukeMrcommerceEventsArrangementCitiesPK(new NukeMrcommerceEventsArrangementCitiesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeMrcommerceEventsArrangementCities);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNukeMrcommerceEventsArrangementCities(nukeMrcommerceEventsArrangementCities.getNukeMrcommerceEventsArrangementCitiesPK()) != null) {
                throw new PreexistingEntityException("NukeMrcommerceEventsArrangementCities " + nukeMrcommerceEventsArrangementCities + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeMrcommerceEventsArrangementCities nukeMrcommerceEventsArrangementCities) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeMrcommerceEventsArrangementCities = em.merge(nukeMrcommerceEventsArrangementCities);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NukeMrcommerceEventsArrangementCitiesPK id = nukeMrcommerceEventsArrangementCities.getNukeMrcommerceEventsArrangementCitiesPK();
                if (findNukeMrcommerceEventsArrangementCities(id) == null) {
                    throw new NonexistentEntityException("The nukeMrcommerceEventsArrangementCities with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NukeMrcommerceEventsArrangementCitiesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NukeMrcommerceEventsArrangementCities nukeMrcommerceEventsArrangementCities;
            try {
                nukeMrcommerceEventsArrangementCities = em.getReference(NukeMrcommerceEventsArrangementCities.class, id);
                nukeMrcommerceEventsArrangementCities.getNukeMrcommerceEventsArrangementCitiesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeMrcommerceEventsArrangementCities with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeMrcommerceEventsArrangementCities);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeMrcommerceEventsArrangementCities> findNukeMrcommerceEventsArrangementCitiesEntities() {
        return findNukeMrcommerceEventsArrangementCitiesEntities(true, -1, -1);
    }

    public List<NukeMrcommerceEventsArrangementCities> findNukeMrcommerceEventsArrangementCitiesEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceEventsArrangementCitiesEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceEventsArrangementCities> findNukeMrcommerceEventsArrangementCitiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceEventsArrangementCities.class));
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

    public NukeMrcommerceEventsArrangementCities findNukeMrcommerceEventsArrangementCities(NukeMrcommerceEventsArrangementCitiesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceEventsArrangementCities.class, id);
        } finally {
            em.close();
        }
    }

       public NukeMrcommerceEventsArrangementCities findNukeMrcommerceEventsArrangementCitiesByEvent(int event) {
        //in Invoice: @NamedQuery(name = "Invoice.findNext", query = "SELECT i FROM Invoice i WHERE i.id >= :id order by i.id"),
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("NukeMrcommerceEventsArrangementCities.findByEventId", NukeMrcommerceEventsArrangementCities.class);
            query2.setParameter("eventId", event);
            List<NukeMrcommerceEventsArrangementCities> list = query2.getResultList();
            if (!list.isEmpty())
                return list.iterator().next();
            return null;
        } finally {
            em.close();
        }
    }


    public int getNukeMrcommerceEventsArrangementCitiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceEventsArrangementCities> rt = cq.from(NukeMrcommerceEventsArrangementCities.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
