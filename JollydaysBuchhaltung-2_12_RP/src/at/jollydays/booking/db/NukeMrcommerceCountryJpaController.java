/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceCountry;
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
public class NukeMrcommerceCountryJpaController {

    public NukeMrcommerceCountryJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NukeMrcommerceCountry nukeMrcommerceCountry) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nukeMrcommerceCountry);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNukeMrcommerceCountry(nukeMrcommerceCountry.getIsoCode()) != null) {
                throw new PreexistingEntityException("NukeMrcommerceCountry " + nukeMrcommerceCountry + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NukeMrcommerceCountry nukeMrcommerceCountry) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nukeMrcommerceCountry = em.merge(nukeMrcommerceCountry);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = nukeMrcommerceCountry.getIsoCode();
                if (findNukeMrcommerceCountry(id) == null) {
                    throw new NonexistentEntityException("The nukeMrcommerceCountry with id " + id + " no longer exists.");
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
            NukeMrcommerceCountry nukeMrcommerceCountry;
            try {
                nukeMrcommerceCountry = em.getReference(NukeMrcommerceCountry.class, id);
                nukeMrcommerceCountry.getIsoCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nukeMrcommerceCountry with id " + id + " no longer exists.", enfe);
            }
            em.remove(nukeMrcommerceCountry);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NukeMrcommerceCountry> findNukeMrcommerceCountryEntities() {
        return findNukeMrcommerceCountryEntities(true, -1, -1);
    }

    public List<NukeMrcommerceCountry> findNukeMrcommerceCountryEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceCountryEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceCountry> findNukeMrcommerceCountryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceCountry.class));
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

    public NukeMrcommerceCountry findNukeMrcommerceCountry(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceCountry.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceCountryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceCountry> rt = cq.from(NukeMrcommerceCountry.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
