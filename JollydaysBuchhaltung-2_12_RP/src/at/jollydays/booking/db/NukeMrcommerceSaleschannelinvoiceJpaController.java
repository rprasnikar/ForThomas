/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
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
public class NukeMrcommerceSaleschannelinvoiceJpaController {

    public NukeMrcommerceSaleschannelinvoiceJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeMrcommerceSaleschannelinvoice nukeMrcommerceSaleschannelinvoice) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeMrcommerceSaleschannelinvoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeMrcommerceSaleschannelinvoice nukeMrcommerceSaleschannelinvoice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeMrcommerceSaleschannelinvoice = em.merge(nukeMrcommerceSaleschannelinvoice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nukeMrcommerceSaleschannelinvoice.getNukeSaleschannelinvoiceInvoice();
                if (findNukeMrcommerceSaleschannelinvoice(id) == null) {
                    throw new NonexistentEntityException("The nukeMrcommerceSaleschannelinvoice with id " + id + " no longer exists.");
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
            NukeMrcommerceSaleschannelinvoice nukeMrcommerceSaleschannelinvoice;
            try {
                nukeMrcommerceSaleschannelinvoice = em.getReference(NukeMrcommerceSaleschannelinvoice.class, id);
                nukeMrcommerceSaleschannelinvoice.getNukeSaleschannelinvoiceInvoice();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeMrcommerceSaleschannelinvoice with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeMrcommerceSaleschannelinvoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeMrcommerceSaleschannelinvoice> findNukeMrcommerceSaleschannelinvoiceEntities() {
        return findNukeMrcommerceSaleschannelinvoiceEntities(true, -1, -1);
    }

    public List<NukeMrcommerceSaleschannelinvoice> findNukeMrcommerceSaleschannelinvoiceEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceSaleschannelinvoiceEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceSaleschannelinvoice> findNukeMrcommerceSaleschannelinvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceSaleschannelinvoice.class));
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

    public NukeMrcommerceSaleschannelinvoice findNukeMrcommerceSaleschannelinvoice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceSaleschannelinvoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceSaleschannelinvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceSaleschannelinvoice> rt = cq.from(NukeMrcommerceSaleschannelinvoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
