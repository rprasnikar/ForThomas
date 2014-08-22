/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceOrganiser;
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
public class NukeMrcommerceOrganiserJpaController {

    public NukeMrcommerceOrganiserJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeMrcommerceOrganiser nukeMrcommerceOrganiser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeMrcommerceOrganiser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeMrcommerceOrganiser nukeMrcommerceOrganiser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeMrcommerceOrganiser = em.merge(nukeMrcommerceOrganiser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nukeMrcommerceOrganiser.getNukeorgID();
                if (findNukeMrcommerceOrganiser(id) == null) {
                    throw new NonexistentEntityException("The nukeMrcommerceOrganiser with id " + id + " no longer exists.");
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
            NukeMrcommerceOrganiser nukeMrcommerceOrganiser;
            try {
                nukeMrcommerceOrganiser = em.getReference(NukeMrcommerceOrganiser.class, id);
                nukeMrcommerceOrganiser.getNukeorgID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeMrcommerceOrganiser with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeMrcommerceOrganiser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeMrcommerceOrganiser> findNukeMrcommerceOrganiserEntities() {
        return findNukeMrcommerceOrganiserEntities(true, -1, -1);
    }

    public List<NukeMrcommerceOrganiser> findNukeMrcommerceOrganiserEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceOrganiserEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceOrganiser> findNukeMrcommerceOrganiserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceOrganiser.class));
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

    public NukeMrcommerceOrganiser findNukeMrcommerceOrganiser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceOrganiser.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceOrganiserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceOrganiser> rt = cq.from(NukeMrcommerceOrganiser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
