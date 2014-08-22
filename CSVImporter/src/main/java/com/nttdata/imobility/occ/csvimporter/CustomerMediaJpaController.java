/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nttdata.imobility.occ.csvimporter;

import com.nttdata.imobility.occ.csvimporter.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Robert
 */
public class CustomerMediaJpaController implements Serializable {

    public CustomerMediaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    CustomerMediaJpaController() {
        emf = Persistence.createEntityManagerFactory("com.nttdata.i-mobility.occ_CSVImporter_jar_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerMedia customerMedia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerMedia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerMedia customerMedia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerMedia = em.merge(customerMedia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = customerMedia.getId();
                if (findCustomerMedia(id) == null) {
                    throw new NonexistentEntityException("The customerMedia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerMedia customerMedia;
            try {
                customerMedia = em.getReference(CustomerMedia.class, id);
                customerMedia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerMedia with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerMedia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerMedia> findCustomerMediaEntities() {
        return findCustomerMediaEntities(true, -1, -1);
    }

    public List<CustomerMedia> findCustomerMediaEntities(int maxResults, int firstResult) {
        return findCustomerMediaEntities(false, maxResults, firstResult);
    }

    private List<CustomerMedia> findCustomerMediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerMedia.class));
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

    public CustomerMedia findCustomerMedia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerMedia.class, id);
        } finally {
            em.close();
        }
    }
    
        public List<CustomerMedia> findCustomerMediaByTag(String tag) {
        EntityManager em = getEntityManager();
        try {
            Query q = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("CustomerMedia.findByTag", CustomerMedia.class);
            q.setParameter("tag", tag);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getCustomerMediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerMedia> rt = cq.from(CustomerMedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
