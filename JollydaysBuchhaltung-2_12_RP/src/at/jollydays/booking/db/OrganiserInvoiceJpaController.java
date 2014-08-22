/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.OrganiserInvoice;
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
public class OrganiserInvoiceJpaController {

    public OrganiserInvoiceJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganiserInvoice organiserInvoice) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(organiserInvoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrganiserInvoice organiserInvoice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            organiserInvoice = em.merge(organiserInvoice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organiserInvoice.getId();
                if (findOrganiserInvoice(id) == null) {
                    throw new NonexistentEntityException("The organiserInvoice with id " + id + " no longer exists.");
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
            OrganiserInvoice organiserInvoice;
            try {
                organiserInvoice = em.getReference(OrganiserInvoice.class, id);
                organiserInvoice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organiserInvoice with id " + id + " no longer exists.", enfe);
            }
            em.remove(organiserInvoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrganiserInvoice> findOrganiserInvoiceEntities() {
        return findOrganiserInvoiceEntities(true, -1, -1);
    }

    public List<OrganiserInvoice> findOrganiserInvoiceEntities(int maxResults, int firstResult) {
        return findOrganiserInvoiceEntities(false, maxResults, firstResult);
    }

    public List<OrganiserInvoice> findOrganiserInvoiceByNumber(int invoiceNumber) {
            EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("OrganiserInvoice.findByInvoiceNumber", OrganiserInvoice.class);
            String invoice = Integer.toString(invoiceNumber);
            while (invoice.length() < 9) {
                invoice = "0".concat(invoice);
            }
            query2.setParameter("invoiceNumber", invoice);
            return query2.getResultList();
        } finally {
            em.close();
        }
   }
 

    public List<OrganiserInvoice> findOrganiserInvoiceEntitiesNext(int maxResults, int firstResult) {
        //in Invoice: @NamedQuery(name = "OrganiserInvoice.findNext", query = "SELECT o FROM OrganiserInvoice o WHERE o.id >= :id order by o.id"),
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("OrganiserInvoice.findNext", OrganiserInvoice.class);
            query2.setParameter("id", firstResult);
            query2.setMaxResults(maxResults);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }


    private List<OrganiserInvoice> findOrganiserInvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrganiserInvoice.class));
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

    public OrganiserInvoice findOrganiserInvoice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrganiserInvoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganiserInvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrganiserInvoice> rt = cq.from(OrganiserInvoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
