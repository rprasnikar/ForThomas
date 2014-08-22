/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeCooperationInvoiceDetails;
import at.jollydays.booking.bo.NukeCooperationInvoiceDetailsPK;
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
public class NukeCooperationInvoiceDetailsJpaController implements Serializable {

    public NukeCooperationInvoiceDetailsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public NukeCooperationInvoiceDetailsJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeCooperationInvoiceDetails nukeCooperationInvoiceDetails) throws PreexistingEntityException, Exception {
        if (nukeCooperationInvoiceDetails.getNukeCooperationInvoiceDetailsPK() == null) {
            nukeCooperationInvoiceDetails.setNukeCooperationInvoiceDetailsPK(new NukeCooperationInvoiceDetailsPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeCooperationInvoiceDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNukeCooperationInvoiceDetails(nukeCooperationInvoiceDetails.getNukeCooperationInvoiceDetailsPK()) != null) {
                throw new PreexistingEntityException("NukeCooperationInvoiceDetails " + nukeCooperationInvoiceDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeCooperationInvoiceDetails nukeCooperationInvoiceDetails) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeCooperationInvoiceDetails = em.merge(nukeCooperationInvoiceDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NukeCooperationInvoiceDetailsPK id = nukeCooperationInvoiceDetails.getNukeCooperationInvoiceDetailsPK();
                if (findNukeCooperationInvoiceDetails(id) == null) {
                    throw new NonexistentEntityException("The nukeCooperationInvoiceDetails with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NukeCooperationInvoiceDetailsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NukeCooperationInvoiceDetails nukeCooperationInvoiceDetails;
            try {
                nukeCooperationInvoiceDetails = em.getReference(NukeCooperationInvoiceDetails.class, id);
                nukeCooperationInvoiceDetails.getNukeCooperationInvoiceDetailsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeCooperationInvoiceDetails with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeCooperationInvoiceDetails);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeCooperationInvoiceDetails> findNukeCooperationInvoiceDetailsEntities() {
        return findNukeCooperationInvoiceDetailsEntities(true, -1, -1);
    }

    public List<NukeCooperationInvoiceDetails> findNukeCooperationInvoiceDetailsEntities(int maxResults, int firstResult) {
        return findNukeCooperationInvoiceDetailsEntities(false, maxResults, firstResult);
    }

    private List<NukeCooperationInvoiceDetails> findNukeCooperationInvoiceDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeCooperationInvoiceDetails.class));
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

    public NukeCooperationInvoiceDetails findNukeCooperationInvoiceDetails(NukeCooperationInvoiceDetailsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeCooperationInvoiceDetails.class, id);
        } finally {
            em.close();
        }
    }

    
        public List<NukeCooperationInvoiceDetails> findByNukeCooperationinvoiceInvoice(Integer i) {
        EntityManager em = getEntityManager();
        try {
        Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("NukeCooperationInvoiceDetails.findByNukeCooperationinvoiceInvoice", NukeCooperationInvoiceDetails.class);
            query2.setParameter("nukeCooperationinvoiceInvoice", i);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }
        

    public int getNukeCooperationInvoiceDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeCooperationInvoiceDetails> rt = cq.from(NukeCooperationInvoiceDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
