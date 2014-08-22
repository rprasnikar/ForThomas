/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.db.exceptions.IllegalOrphanException;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import at.jollydays.booking.bo.BuhaArea;
import at.jollydays.booking.bo.BuhaFilter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gunter Reinitzer
 */
public class BuhaBookingJpaController1 implements Serializable {

    public BuhaBookingJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaBooking buhaBooking) {
        if (buhaBooking.getBuhaFilterCollection() == null) {
            buhaBooking.setBuhaFilterCollection(new ArrayList<BuhaFilter>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaArea buhaArea = buhaBooking.getBuhaArea();
            if (buhaArea != null) {
                buhaArea = em.getReference(buhaArea.getClass(), buhaArea.getId());
                buhaBooking.setBuhaArea(buhaArea);
            }
            Collection<BuhaFilter> attachedBuhaFilterCollection = new ArrayList<BuhaFilter>();
            for (BuhaFilter buhaFilterCollectionBuhaFilterToAttach : buhaBooking.getBuhaFilterCollection()) {
                buhaFilterCollectionBuhaFilterToAttach = em.getReference(buhaFilterCollectionBuhaFilterToAttach.getClass(), buhaFilterCollectionBuhaFilterToAttach.getId());
                attachedBuhaFilterCollection.add(buhaFilterCollectionBuhaFilterToAttach);
            }
            buhaBooking.setBuhaFilterCollection(attachedBuhaFilterCollection);
            em.persist(buhaBooking);
            if (buhaArea != null) {
                buhaArea.getBuhaBookingCollection().add(buhaBooking);
                buhaArea = em.merge(buhaArea);
            }
            for (BuhaFilter buhaFilterCollectionBuhaFilter : buhaBooking.getBuhaFilterCollection()) {
                BuhaBooking oldBuhaBookingOfBuhaFilterCollectionBuhaFilter = buhaFilterCollectionBuhaFilter.getBuhaBooking();
                buhaFilterCollectionBuhaFilter.setBuhaBooking(buhaBooking);
                buhaFilterCollectionBuhaFilter = em.merge(buhaFilterCollectionBuhaFilter);
                if (oldBuhaBookingOfBuhaFilterCollectionBuhaFilter != null) {
                    oldBuhaBookingOfBuhaFilterCollectionBuhaFilter.getBuhaFilterCollection().remove(buhaFilterCollectionBuhaFilter);
                    oldBuhaBookingOfBuhaFilterCollectionBuhaFilter = em.merge(oldBuhaBookingOfBuhaFilterCollectionBuhaFilter);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaBooking buhaBooking) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaBooking persistentBuhaBooking = em.find(BuhaBooking.class, buhaBooking.getId());
            BuhaArea buhaAreaOld = persistentBuhaBooking.getBuhaArea();
            BuhaArea buhaAreaNew = buhaBooking.getBuhaArea();
            Collection<BuhaFilter> buhaFilterCollectionOld = persistentBuhaBooking.getBuhaFilterCollection();
            Collection<BuhaFilter> buhaFilterCollectionNew = buhaBooking.getBuhaFilterCollection();
            List<String> illegalOrphanMessages = null;
            for (BuhaFilter buhaFilterCollectionOldBuhaFilter : buhaFilterCollectionOld) {
                if (!buhaFilterCollectionNew.contains(buhaFilterCollectionOldBuhaFilter)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BuhaFilter " + buhaFilterCollectionOldBuhaFilter + " since its buhaBooking field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (buhaAreaNew != null) {
                buhaAreaNew = em.getReference(buhaAreaNew.getClass(), buhaAreaNew.getId());
                buhaBooking.setBuhaArea(buhaAreaNew);
            }
            Collection<BuhaFilter> attachedBuhaFilterCollectionNew = new ArrayList<BuhaFilter>();
            for (BuhaFilter buhaFilterCollectionNewBuhaFilterToAttach : buhaFilterCollectionNew) {
                buhaFilterCollectionNewBuhaFilterToAttach = em.getReference(buhaFilterCollectionNewBuhaFilterToAttach.getClass(), buhaFilterCollectionNewBuhaFilterToAttach.getId());
                attachedBuhaFilterCollectionNew.add(buhaFilterCollectionNewBuhaFilterToAttach);
            }
            buhaFilterCollectionNew = attachedBuhaFilterCollectionNew;
            buhaBooking.setBuhaFilterCollection(buhaFilterCollectionNew);
            buhaBooking = em.merge(buhaBooking);
            if (buhaAreaOld != null && !buhaAreaOld.equals(buhaAreaNew)) {
                buhaAreaOld.getBuhaBookingCollection().remove(buhaBooking);
                buhaAreaOld = em.merge(buhaAreaOld);
            }
            if (buhaAreaNew != null && !buhaAreaNew.equals(buhaAreaOld)) {
                buhaAreaNew.getBuhaBookingCollection().add(buhaBooking);
                buhaAreaNew = em.merge(buhaAreaNew);
            }
            for (BuhaFilter buhaFilterCollectionNewBuhaFilter : buhaFilterCollectionNew) {
                if (!buhaFilterCollectionOld.contains(buhaFilterCollectionNewBuhaFilter)) {
                    BuhaBooking oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter = buhaFilterCollectionNewBuhaFilter.getBuhaBooking();
                    buhaFilterCollectionNewBuhaFilter.setBuhaBooking(buhaBooking);
                    buhaFilterCollectionNewBuhaFilter = em.merge(buhaFilterCollectionNewBuhaFilter);
                    if (oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter != null && !oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter.equals(buhaBooking)) {
                        oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter.getBuhaFilterCollection().remove(buhaFilterCollectionNewBuhaFilter);
                        oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter = em.merge(oldBuhaBookingOfBuhaFilterCollectionNewBuhaFilter);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaBooking.getId();
                if (findBuhaBooking(id) == null) {
                    throw new NonexistentEntityException("The buhaBooking with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaBooking buhaBooking;
            try {
                buhaBooking = em.getReference(BuhaBooking.class, id);
                buhaBooking.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaBooking with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BuhaFilter> buhaFilterCollectionOrphanCheck = buhaBooking.getBuhaFilterCollection();
            for (BuhaFilter buhaFilterCollectionOrphanCheckBuhaFilter : buhaFilterCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BuhaBooking (" + buhaBooking + ") cannot be destroyed since the BuhaFilter " + buhaFilterCollectionOrphanCheckBuhaFilter + " in its buhaFilterCollection field has a non-nullable buhaBooking field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BuhaArea buhaArea = buhaBooking.getBuhaArea();
            if (buhaArea != null) {
                buhaArea.getBuhaBookingCollection().remove(buhaBooking);
                buhaArea = em.merge(buhaArea);
            }
            em.remove(buhaBooking);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaBooking> findBuhaBookingEntities() {
        return findBuhaBookingEntities(true, -1, -1);
    }

    public List<BuhaBooking> findBuhaBookingEntities(int maxResults, int firstResult) {
        return findBuhaBookingEntities(false, maxResults, firstResult);
    }

    private List<BuhaBooking> findBuhaBookingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaBooking.class));
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

    public BuhaBooking findBuhaBooking(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaBooking.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaBookingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaBooking> rt = cq.from(BuhaBooking.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
