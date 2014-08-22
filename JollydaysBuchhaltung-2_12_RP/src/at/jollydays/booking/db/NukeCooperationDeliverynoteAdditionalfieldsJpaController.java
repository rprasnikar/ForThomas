/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeCooperationDeliverynoteAdditionalfields;
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
public class NukeCooperationDeliverynoteAdditionalfieldsJpaController implements Serializable {

    public NukeCooperationDeliverynoteAdditionalfieldsJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }

    public NukeCooperationDeliverynoteAdditionalfieldsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeCooperationDeliverynoteAdditionalfields nukeCooperationDeliverynoteAdditionalfields) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeCooperationDeliverynoteAdditionalfields);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNukeCooperationDeliverynoteAdditionalfields(nukeCooperationDeliverynoteAdditionalfields.getNukeCooperatiodeliverynoteadditionalfieldsInvoice()) != null) {
                throw new PreexistingEntityException("NukeCooperationDeliverynoteAdditionalfields " + nukeCooperationDeliverynoteAdditionalfields + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeCooperationDeliverynoteAdditionalfields nukeCooperationDeliverynoteAdditionalfields) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeCooperationDeliverynoteAdditionalfields = em.merge(nukeCooperationDeliverynoteAdditionalfields);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nukeCooperationDeliverynoteAdditionalfields.getNukeCooperatiodeliverynoteadditionalfieldsInvoice();
                if (findNukeCooperationDeliverynoteAdditionalfields(id) == null) {
                    throw new NonexistentEntityException("The nukeCooperationDeliverynoteAdditionalfields with id " + id + " no longer exists.");
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
            NukeCooperationDeliverynoteAdditionalfields nukeCooperationDeliverynoteAdditionalfields;
            try {
                nukeCooperationDeliverynoteAdditionalfields = em.getReference(NukeCooperationDeliverynoteAdditionalfields.class, id);
                nukeCooperationDeliverynoteAdditionalfields.getNukeCooperatiodeliverynoteadditionalfieldsInvoice();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeCooperationDeliverynoteAdditionalfields with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeCooperationDeliverynoteAdditionalfields);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeCooperationDeliverynoteAdditionalfields> findNukeCooperationDeliverynoteAdditionalfieldsEntities() {
        return findNukeCooperationDeliverynoteAdditionalfieldsEntities(true, -1, -1);
    }

    public List<NukeCooperationDeliverynoteAdditionalfields> findNukeCooperationDeliverynoteAdditionalfieldsEntities(int maxResults, int firstResult) {
        return findNukeCooperationDeliverynoteAdditionalfieldsEntities(false, maxResults, firstResult);
    }

    private List<NukeCooperationDeliverynoteAdditionalfields> findNukeCooperationDeliverynoteAdditionalfieldsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeCooperationDeliverynoteAdditionalfields.class));
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

    public NukeCooperationDeliverynoteAdditionalfields findNukeCooperationDeliverynoteAdditionalfields(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeCooperationDeliverynoteAdditionalfields.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeCooperationDeliverynoteAdditionalfieldsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeCooperationDeliverynoteAdditionalfields> rt = cq.from(NukeCooperationDeliverynoteAdditionalfields.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
