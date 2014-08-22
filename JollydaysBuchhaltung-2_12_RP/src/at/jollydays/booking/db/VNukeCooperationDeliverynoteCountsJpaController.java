/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.VNukeCooperationDeliverynoteCounts;
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
public class VNukeCooperationDeliverynoteCountsJpaController implements Serializable {

    public VNukeCooperationDeliverynoteCountsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VNukeCooperationDeliverynoteCountsJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VNukeCooperationDeliverynoteCounts VNukeCooperationDeliverynoteCounts) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(VNukeCooperationDeliverynoteCounts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVNukeCooperationDeliverynoteCounts(VNukeCooperationDeliverynoteCounts.getUuid()) != null) {
                throw new PreexistingEntityException("VNukeCooperationDeliverynoteCounts " + VNukeCooperationDeliverynoteCounts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VNukeCooperationDeliverynoteCounts VNukeCooperationDeliverynoteCounts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VNukeCooperationDeliverynoteCounts = em.merge(VNukeCooperationDeliverynoteCounts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = VNukeCooperationDeliverynoteCounts.getUuid();
                if (findVNukeCooperationDeliverynoteCounts(id) == null) {
                    throw new NonexistentEntityException("The vNukeCooperationDeliverynoteCounts with id " + id + " no longer exists.");
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
            VNukeCooperationDeliverynoteCounts VNukeCooperationDeliverynoteCounts;
            try {
                VNukeCooperationDeliverynoteCounts = em.getReference(VNukeCooperationDeliverynoteCounts.class, id);
                VNukeCooperationDeliverynoteCounts.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The VNukeCooperationDeliverynoteCounts with id " + id + " no longer exists.", enfe);
            }
            em.remove(VNukeCooperationDeliverynoteCounts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VNukeCooperationDeliverynoteCounts> findVNukeCooperationDeliverynoteCountsEntities() {
        return findVNukeCooperationDeliverynoteCountsEntities(true, -1, -1);
    }

    public List<VNukeCooperationDeliverynoteCounts> findVNukeCooperationDeliverynoteCountsEntities(int maxResults, int firstResult) {
        return findVNukeCooperationDeliverynoteCountsEntities(false, maxResults, firstResult);
    }

    private List<VNukeCooperationDeliverynoteCounts> findVNukeCooperationDeliverynoteCountsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VNukeCooperationDeliverynoteCounts.class));
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
    
    //    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByNukeCooperatiodeliverynoteaccountInvoice", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.nukeCooperatiodeliverynoteaccountInvoice = :nukeCooperatiodeliverynoteaccountInvoice")})
    public List<VNukeCooperationDeliverynoteCounts> findByNukeCooperatiodeliverynoteaccountInvoice(Integer i) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("VNukeCooperationDeliverynoteCounts.findByNukeCooperatiodeliverynoteaccountInvoice", VNukeCooperationDeliverynoteCounts.class);
            query2.setParameter("nukeCooperatiodeliverynoteaccountInvoice", i);
            return query2.getResultList();

        } finally {
            em.close();
        }
    }
    
    public List<VNukeCooperationDeliverynoteCounts> findByInvoiceAndMrcommerceItemID(Integer i, Integer m) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("VNukeCooperationDeliverynoteCounts.findByInvoiceAndMrcommerceItemID", VNukeCooperationDeliverynoteCounts.class);
            query2.setParameter("nukeCooperatiodeliverynoteaccountInvoice", i);
            query2.setParameter("mrcommerceItemID", m);
            return query2.getResultList();

        } finally {
            em.close();
        }
    }

    public VNukeCooperationDeliverynoteCounts findVNukeCooperationDeliverynoteCounts(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VNukeCooperationDeliverynoteCounts.class, id);
        } finally {
            em.close();
        }
    }

    public int getVNukeCooperationDeliverynoteCountsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VNukeCooperationDeliverynoteCounts> rt = cq.from(VNukeCooperationDeliverynoteCounts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
