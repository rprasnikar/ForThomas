/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.VNukeCooperationDeliverynoteDetails;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import at.jollydays.booking.db.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rpadmin
 */
public class VNukeCooperationDeliverynoteDetailsJpaController implements Serializable {

    public VNukeCooperationDeliverynoteDetailsJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }

    public VNukeCooperationDeliverynoteDetailsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VNukeCooperationDeliverynoteDetails VNukeCooperationDeliverynoteDetails) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(VNukeCooperationDeliverynoteDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVNukeCooperationDeliverynoteDetails(VNukeCooperationDeliverynoteDetails.getNukeCooperationId()) != null) {
                throw new PreexistingEntityException("VNukeCooperationDeliverynoteDetails " + VNukeCooperationDeliverynoteDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VNukeCooperationDeliverynoteDetails VNukeCooperationDeliverynoteDetails) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VNukeCooperationDeliverynoteDetails = em.merge(VNukeCooperationDeliverynoteDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = VNukeCooperationDeliverynoteDetails.getNukeCooperationId();
                if (findVNukeCooperationDeliverynoteDetails(id) == null) {
                    throw new NonexistentEntityException("The vNukeCooperationDeliverynoteDetails with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VNukeCooperationDeliverynoteDetails VNukeCooperationDeliverynoteDetails;
            try {
                VNukeCooperationDeliverynoteDetails = em.getReference(VNukeCooperationDeliverynoteDetails.class, id);
                VNukeCooperationDeliverynoteDetails.getNukeCooperationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The VNukeCooperationDeliverynoteDetails with id " + id + " no longer exists.", enfe);
            }
            em.remove(VNukeCooperationDeliverynoteDetails);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VNukeCooperationDeliverynoteDetails> findVNukeCooperationDeliverynoteDetailsEntities() {
        return findVNukeCooperationDeliverynoteDetailsEntities(true, -1, -1);
    }

    public List<VNukeCooperationDeliverynoteDetails> findVNukeCooperationDeliverynoteDetailsEntities(int maxResults, int firstResult) {
        return findVNukeCooperationDeliverynoteDetailsEntities(false, maxResults, firstResult);
    }

    private List<VNukeCooperationDeliverynoteDetails> findVNukeCooperationDeliverynoteDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VNukeCooperationDeliverynoteDetails.class));
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

    public VNukeCooperationDeliverynoteDetails findVNukeCooperationDeliverynoteDetails(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VNukeCooperationDeliverynoteDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getVNukeCooperationDeliverynoteDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VNukeCooperationDeliverynoteDetails> rt = cq.from(VNukeCooperationDeliverynoteDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
