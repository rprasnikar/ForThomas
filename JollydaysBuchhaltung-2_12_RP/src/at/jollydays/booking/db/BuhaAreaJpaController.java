/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.BuhaArea;
import at.jollydays.booking.db.exceptions.IllegalOrphanException;
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
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gunter Reinitzer
 */
public class BuhaAreaJpaController {

    public BuhaAreaJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BuhaArea buhaArea) {
        if (buhaArea.getBuhaBookingCollection() == null) {
            buhaArea.setBuhaBookingCollection(new ArrayList<BuhaBooking>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<BuhaBooking> attachedBuhaBookingCollection = new ArrayList<BuhaBooking>();
            for (BuhaBooking buhaBookingCollectionBuhaBookingToAttach : buhaArea.getBuhaBookingCollection()) {
                buhaBookingCollectionBuhaBookingToAttach = em.getReference(buhaBookingCollectionBuhaBookingToAttach.getClass(), buhaBookingCollectionBuhaBookingToAttach.getId());
                attachedBuhaBookingCollection.add(buhaBookingCollectionBuhaBookingToAttach);
            }
            buhaArea.setBuhaBookingCollection(attachedBuhaBookingCollection);
            em.persist(buhaArea);
            for (BuhaBooking buhaBookingCollectionBuhaBooking : buhaArea.getBuhaBookingCollection()) {
                BuhaArea oldBuhaAreaOfBuhaBookingCollectionBuhaBooking = buhaBookingCollectionBuhaBooking.getBuhaArea();
                buhaBookingCollectionBuhaBooking.setBuhaArea(buhaArea);
                buhaBookingCollectionBuhaBooking = em.merge(buhaBookingCollectionBuhaBooking);
                if (oldBuhaAreaOfBuhaBookingCollectionBuhaBooking != null) {
                    oldBuhaAreaOfBuhaBookingCollectionBuhaBooking.getBuhaBookingCollection().remove(buhaBookingCollectionBuhaBooking);
                    oldBuhaAreaOfBuhaBookingCollectionBuhaBooking = em.merge(oldBuhaAreaOfBuhaBookingCollectionBuhaBooking);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BuhaArea buhaArea) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BuhaArea persistentBuhaArea = em.find(BuhaArea.class, buhaArea.getId());
            Collection<BuhaBooking> buhaBookingCollectionOld = persistentBuhaArea.getBuhaBookingCollection();
            Collection<BuhaBooking> buhaBookingCollectionNew = buhaArea.getBuhaBookingCollection();
            List<String> illegalOrphanMessages = null;
            for (BuhaBooking buhaBookingCollectionOldBuhaBooking : buhaBookingCollectionOld) {
                if (!buhaBookingCollectionNew.contains(buhaBookingCollectionOldBuhaBooking)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BuhaBooking " + buhaBookingCollectionOldBuhaBooking + " since its buhaArea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<BuhaBooking> attachedBuhaBookingCollectionNew = new ArrayList<BuhaBooking>();
            for (BuhaBooking buhaBookingCollectionNewBuhaBookingToAttach : buhaBookingCollectionNew) {
                buhaBookingCollectionNewBuhaBookingToAttach = em.getReference(buhaBookingCollectionNewBuhaBookingToAttach.getClass(), buhaBookingCollectionNewBuhaBookingToAttach.getId());
                attachedBuhaBookingCollectionNew.add(buhaBookingCollectionNewBuhaBookingToAttach);
            }
            buhaBookingCollectionNew = attachedBuhaBookingCollectionNew;
            buhaArea.setBuhaBookingCollection(buhaBookingCollectionNew);
            buhaArea = em.merge(buhaArea);
            for (BuhaBooking buhaBookingCollectionNewBuhaBooking : buhaBookingCollectionNew) {
                if (!buhaBookingCollectionOld.contains(buhaBookingCollectionNewBuhaBooking)) {
                    BuhaArea oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking = buhaBookingCollectionNewBuhaBooking.getBuhaArea();
                    buhaBookingCollectionNewBuhaBooking.setBuhaArea(buhaArea);
                    buhaBookingCollectionNewBuhaBooking = em.merge(buhaBookingCollectionNewBuhaBooking);
                    if (oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking != null && !oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking.equals(buhaArea)) {
                        oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking.getBuhaBookingCollection().remove(buhaBookingCollectionNewBuhaBooking);
                        oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking = em.merge(oldBuhaAreaOfBuhaBookingCollectionNewBuhaBooking);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buhaArea.getId();
                if (findBuhaArea(id) == null) {
                    throw new NonexistentEntityException("The buhaArea with id " + id + " no longer exists.");
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
            BuhaArea buhaArea;
            try {
                buhaArea = em.getReference(BuhaArea.class, id);
                buhaArea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buhaArea with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BuhaBooking> buhaBookingCollectionOrphanCheck = buhaArea.getBuhaBookingCollection();
            for (BuhaBooking buhaBookingCollectionOrphanCheckBuhaBooking : buhaBookingCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BuhaArea (" + buhaArea + ") cannot be destroyed since the BuhaBooking " + buhaBookingCollectionOrphanCheckBuhaBooking + " in its buhaBookingCollection field has a non-nullable buhaArea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(buhaArea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BuhaArea> findBuhaAreaEntities() {
//        return findBuhaAreaEntities(true, -1, -1);
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("BuhaArea.findAll", BuhaArea.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

//    public List<BuhaArea> findBuhaAreaEntitiesJoin() {
//        EntityManager em = getEntityManager();
//        try {
//            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("BuhaArea.findAllJoin", BuhaArea.class);
//            return query2.getResultList();
//        } finally {
//            em.close();
//        }
//    }
    
    
    public List<BuhaArea> findBuhaAreaEntities(int maxResults, int firstResult) {        
        return findBuhaAreaEntities(false, maxResults, firstResult);
    }

    private List<BuhaArea> findBuhaAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {           
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BuhaArea.class));
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

    public BuhaArea findBuhaArea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BuhaArea.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuhaAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BuhaArea> rt = cq.from(BuhaArea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
