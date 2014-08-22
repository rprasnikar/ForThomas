/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gunter Reinitzer
 */
public class NukeMrcommerceArrangementCityJpaController {

    public NukeMrcommerceArrangementCityJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public void create(NukeMrcommerceArrangementCity nukeMrcommerceArrangementCity) {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            NukeMrcommerceArrangement nukeMrcommerceArrangement = nukeMrcommerceArrangementCity.getNukeMrcommerceArrangement();
//            if (nukeMrcommerceArrangement != null) {
//                nukeMrcommerceArrangement = em.getReference(nukeMrcommerceArrangement.getClass(), nukeMrcommerceArrangement.getId());
//                nukeMrcommerceArrangementCity.setNukeMrcommerceArrangement(nukeMrcommerceArrangement);
//            }
//            em.persist(nukeMrcommerceArrangementCity);
//            if (nukeMrcommerceArrangement != null) {
//                nukeMrcommerceArrangement.getNukeMrcommerceArrangementCityCollection().add(nukeMrcommerceArrangementCity);
//                nukeMrcommerceArrangement = em.merge(nukeMrcommerceArrangement);
//            }
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

//    public void edit(NukeMrcommerceArrangementCity nukeMrcommerceArrangementCity) throws NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            NukeMrcommerceArrangementCity persistentNukeMrcommerceArrangementCity = em.find(NukeMrcommerceArrangementCity.class, nukeMrcommerceArrangementCity.getId());
//            NukeMrcommerceArrangement nukeMrcommerceArrangementOld = persistentNukeMrcommerceArrangementCity.getNukeMrcommerceArrangement();
//            NukeMrcommerceArrangement nukeMrcommerceArrangementNew = nukeMrcommerceArrangementCity.getNukeMrcommerceArrangement();
//            if (nukeMrcommerceArrangementNew != null) {
//                nukeMrcommerceArrangementNew = em.getReference(nukeMrcommerceArrangementNew.getClass(), nukeMrcommerceArrangementNew.getId());
//                nukeMrcommerceArrangementCity.setNukeMrcommerceArrangement(nukeMrcommerceArrangementNew);
//            }
//            nukeMrcommerceArrangementCity = em.merge(nukeMrcommerceArrangementCity);
//            if (nukeMrcommerceArrangementOld != null && !nukeMrcommerceArrangementOld.equals(nukeMrcommerceArrangementNew)) {
//                nukeMrcommerceArrangementOld.getNukeMrcommerceArrangementCityCollection().remove(nukeMrcommerceArrangementCity);
//                nukeMrcommerceArrangementOld = em.merge(nukeMrcommerceArrangementOld);
//            }
//            if (nukeMrcommerceArrangementNew != null && !nukeMrcommerceArrangementNew.equals(nukeMrcommerceArrangementOld)) {
//                nukeMrcommerceArrangementNew.getNukeMrcommerceArrangementCityCollection().add(nukeMrcommerceArrangementCity);
//                nukeMrcommerceArrangementNew = em.merge(nukeMrcommerceArrangementNew);
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = nukeMrcommerceArrangementCity.getId();
//                if (findNukeMrcommerceArrangementCity(id) == null) {
//                    throw new NonexistentEntityException("The nukeMrcommerceArrangementCity with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

//    public void destroy(Integer id) throws NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            NukeMrcommerceArrangementCity nukeMrcommerceArrangementCity;
//            try {
//                nukeMrcommerceArrangementCity = em.getReference(NukeMrcommerceArrangementCity.class, id);
//                nukeMrcommerceArrangementCity.getId();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The nukeMrcommerceArrangementCity with id " + id + " no longer exists.", enfe);
//            }
//            NukeMrcommerceArrangement nukeMrcommerceArrangement = nukeMrcommerceArrangementCity.getNukeMrcommerceArrangement();
//            if (nukeMrcommerceArrangement != null) {
//                nukeMrcommerceArrangement.getNukeMrcommerceArrangementCityCollection().remove(nukeMrcommerceArrangementCity);
//                nukeMrcommerceArrangement = em.merge(nukeMrcommerceArrangement);
//            }
//            em.remove(nukeMrcommerceArrangementCity);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }

    public List<NukeMrcommerceArrangementCity> findNukeMrcommerceArrangementCityEntities() {
        return findNukeMrcommerceArrangementCityEntities(true, -1, -1);
    }

    public List<NukeMrcommerceArrangementCity> findNukeMrcommerceArrangementCityEntities(int maxResults, int firstResult) {
        return findNukeMrcommerceArrangementCityEntities(false, maxResults, firstResult);
    }

    private List<NukeMrcommerceArrangementCity> findNukeMrcommerceArrangementCityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NukeMrcommerceArrangementCity.class));
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

    public NukeMrcommerceArrangementCity findNukeMrcommerceArrangementCity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NukeMrcommerceArrangementCity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNukeMrcommerceArrangementCityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NukeMrcommerceArrangementCity> rt = cq.from(NukeMrcommerceArrangementCity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
