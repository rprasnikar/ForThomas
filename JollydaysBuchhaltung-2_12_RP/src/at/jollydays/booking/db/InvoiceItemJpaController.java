/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.bo.InvoiceItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.FlushModeType;

/**
 *
 * @author Gunter Reinitzer
 */
public class InvoiceItemJpaController {

    public InvoiceItemJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvoiceItem invoiceItem) {
        if (invoiceItem.getInvoiceItemCollection() == null) {
            invoiceItem.setInvoiceItemCollection(new ArrayList<InvoiceItem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice invoiceID = invoiceItem.getInvoiceID();
            if (invoiceID != null) {
                invoiceID = em.getReference(invoiceID.getClass(), invoiceID.getId());
                invoiceItem.setInvoiceID(invoiceID);
            }
            InvoiceItem correspondingItemRabattID = invoiceItem.getCorrespondingItemRabattID();
            if (correspondingItemRabattID != null) {
                correspondingItemRabattID = em.getReference(correspondingItemRabattID.getClass(), correspondingItemRabattID.getId());
                invoiceItem.setCorrespondingItemRabattID(correspondingItemRabattID);
            }
            Collection<InvoiceItem> attachedInvoiceItemCollection = new ArrayList<InvoiceItem>();
            for (InvoiceItem invoiceItemCollectionInvoiceItemToAttach : invoiceItem.getInvoiceItemCollection()) {
                invoiceItemCollectionInvoiceItemToAttach = em.getReference(invoiceItemCollectionInvoiceItemToAttach.getClass(), invoiceItemCollectionInvoiceItemToAttach.getId());
                attachedInvoiceItemCollection.add(invoiceItemCollectionInvoiceItemToAttach);
            }
            invoiceItem.setInvoiceItemCollection(attachedInvoiceItemCollection);
            em.persist(invoiceItem);
            if (invoiceID != null) {
                invoiceID.getInvoiceItemCollection().add(invoiceItem);
                invoiceID = em.merge(invoiceID);
            }
            if (correspondingItemRabattID != null) {
                correspondingItemRabattID.getInvoiceItemCollection().add(invoiceItem);
                correspondingItemRabattID = em.merge(correspondingItemRabattID);
            }
            for (InvoiceItem invoiceItemCollectionInvoiceItem : invoiceItem.getInvoiceItemCollection()) {
                InvoiceItem oldCorrespondingItemRabattIDOfInvoiceItemCollectionInvoiceItem = invoiceItemCollectionInvoiceItem.getCorrespondingItemRabattID();
                invoiceItemCollectionInvoiceItem.setCorrespondingItemRabattID(invoiceItem);
                invoiceItemCollectionInvoiceItem = em.merge(invoiceItemCollectionInvoiceItem);
                if (oldCorrespondingItemRabattIDOfInvoiceItemCollectionInvoiceItem != null) {
                    oldCorrespondingItemRabattIDOfInvoiceItemCollectionInvoiceItem.getInvoiceItemCollection().remove(invoiceItemCollectionInvoiceItem);
                    oldCorrespondingItemRabattIDOfInvoiceItemCollectionInvoiceItem = em.merge(oldCorrespondingItemRabattIDOfInvoiceItemCollectionInvoiceItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvoiceItem invoiceItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvoiceItem persistentInvoiceItem = em.find(InvoiceItem.class, invoiceItem.getId());
            Invoice invoiceIDOld = persistentInvoiceItem.getInvoiceID();
            Invoice invoiceIDNew = invoiceItem.getInvoiceID();
            InvoiceItem correspondingItemRabattIDOld = persistentInvoiceItem.getCorrespondingItemRabattID();
            InvoiceItem correspondingItemRabattIDNew = invoiceItem.getCorrespondingItemRabattID();
            Collection<InvoiceItem> invoiceItemCollectionOld = persistentInvoiceItem.getInvoiceItemCollection();
            Collection<InvoiceItem> invoiceItemCollectionNew = invoiceItem.getInvoiceItemCollection();
            if (invoiceIDNew != null) {
                invoiceIDNew = em.getReference(invoiceIDNew.getClass(), invoiceIDNew.getId());
                invoiceItem.setInvoiceID(invoiceIDNew);
            }
            if (correspondingItemRabattIDNew != null) {
                correspondingItemRabattIDNew = em.getReference(correspondingItemRabattIDNew.getClass(), correspondingItemRabattIDNew.getId());
                invoiceItem.setCorrespondingItemRabattID(correspondingItemRabattIDNew);
            }
            Collection<InvoiceItem> attachedInvoiceItemCollectionNew = new ArrayList<InvoiceItem>();
            for (InvoiceItem invoiceItemCollectionNewInvoiceItemToAttach : invoiceItemCollectionNew) {
                invoiceItemCollectionNewInvoiceItemToAttach = em.getReference(invoiceItemCollectionNewInvoiceItemToAttach.getClass(), invoiceItemCollectionNewInvoiceItemToAttach.getId());
                attachedInvoiceItemCollectionNew.add(invoiceItemCollectionNewInvoiceItemToAttach);
            }
            invoiceItemCollectionNew = attachedInvoiceItemCollectionNew;
            invoiceItem.setInvoiceItemCollection(invoiceItemCollectionNew);
            invoiceItem = em.merge(invoiceItem);
            if (invoiceIDOld != null && !invoiceIDOld.equals(invoiceIDNew)) {
                invoiceIDOld.getInvoiceItemCollection().remove(invoiceItem);
                invoiceIDOld = em.merge(invoiceIDOld);
            }
            if (invoiceIDNew != null && !invoiceIDNew.equals(invoiceIDOld)) {
                invoiceIDNew.getInvoiceItemCollection().add(invoiceItem);
                invoiceIDNew = em.merge(invoiceIDNew);
            }
            if (correspondingItemRabattIDOld != null && !correspondingItemRabattIDOld.equals(correspondingItemRabattIDNew)) {
                correspondingItemRabattIDOld.getInvoiceItemCollection().remove(invoiceItem);
                correspondingItemRabattIDOld = em.merge(correspondingItemRabattIDOld);
            }
            if (correspondingItemRabattIDNew != null && !correspondingItemRabattIDNew.equals(correspondingItemRabattIDOld)) {
                correspondingItemRabattIDNew.getInvoiceItemCollection().add(invoiceItem);
                correspondingItemRabattIDNew = em.merge(correspondingItemRabattIDNew);
            }
            for (InvoiceItem invoiceItemCollectionOldInvoiceItem : invoiceItemCollectionOld) {
                if (!invoiceItemCollectionNew.contains(invoiceItemCollectionOldInvoiceItem)) {
                    invoiceItemCollectionOldInvoiceItem.setCorrespondingItemRabattID(null);
                    invoiceItemCollectionOldInvoiceItem = em.merge(invoiceItemCollectionOldInvoiceItem);
                }
            }
            for (InvoiceItem invoiceItemCollectionNewInvoiceItem : invoiceItemCollectionNew) {
                if (!invoiceItemCollectionOld.contains(invoiceItemCollectionNewInvoiceItem)) {
                    InvoiceItem oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem = invoiceItemCollectionNewInvoiceItem.getCorrespondingItemRabattID();
                    invoiceItemCollectionNewInvoiceItem.setCorrespondingItemRabattID(invoiceItem);
                    invoiceItemCollectionNewInvoiceItem = em.merge(invoiceItemCollectionNewInvoiceItem);
                    if (oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem != null && !oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem.equals(invoiceItem)) {
                        oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem.getInvoiceItemCollection().remove(invoiceItemCollectionNewInvoiceItem);
                        oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem = em.merge(oldCorrespondingItemRabattIDOfInvoiceItemCollectionNewInvoiceItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invoiceItem.getId();
                if (findInvoiceItem(id) == null) {
                    throw new NonexistentEntityException("The invoiceItem with id " + id + " no longer exists.");
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
            InvoiceItem invoiceItem;
            try {
                invoiceItem = em.getReference(InvoiceItem.class, id);
                invoiceItem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoiceItem with id " + id + " no longer exists.", enfe);
            }
            Invoice invoiceID = invoiceItem.getInvoiceID();
            if (invoiceID != null) {
                invoiceID.getInvoiceItemCollection().remove(invoiceItem);
                invoiceID = em.merge(invoiceID);
            }
            InvoiceItem correspondingItemRabattID = invoiceItem.getCorrespondingItemRabattID();
            if (correspondingItemRabattID != null) {
                correspondingItemRabattID.getInvoiceItemCollection().remove(invoiceItem);
                correspondingItemRabattID = em.merge(correspondingItemRabattID);
            }
            Collection<InvoiceItem> invoiceItemCollection = invoiceItem.getInvoiceItemCollection();
            for (InvoiceItem invoiceItemCollectionInvoiceItem : invoiceItemCollection) {
                invoiceItemCollectionInvoiceItem.setCorrespondingItemRabattID(null);
                invoiceItemCollectionInvoiceItem = em.merge(invoiceItemCollectionInvoiceItem);
            }
            em.remove(invoiceItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvoiceItem> findInvoiceItemEntities() {
        return findInvoiceItemEntities(true, -1, -1);
    }

    public List<InvoiceItem> findInvoiceItemEntities(int maxResults, int firstResult) {
        return findInvoiceItemEntities(false, maxResults, firstResult);
    }

    private List<InvoiceItem> findInvoiceItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvoiceItem.class));
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

    public InvoiceItem findFirstInvoiceItemByUniqueNumber(String uniqueNumber) {
        //in Invoice: @NamedQuery(name = "Invoice.findNext", query = "SELECT i FROM Invoice i WHERE i.id >= :id order by i.id"),
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("InvoiceItem.findFirstByUniqueNumber", Invoice.class);
            query2.setParameter("uniqueNumber", uniqueNumber);
            Iterator<InvoiceItem> iter = query2.getResultList().iterator();
            if (iter.hasNext())
                return iter.next();
            return null;
        } finally {
            em.close();
        }
    }

    public List<InvoiceItem> findInvoiceItemByUniqueNumber(String uniqueNumber) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("InvoiceItem.findByUniqueNumber", InvoiceItem.class);
            query2.setParameter("uniqueNumber", uniqueNumber);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }

    public InvoiceItem findInvoiceItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvoiceItem.class, id);
        } finally {
            em.close();
        }
    }

    public int updateInvoiceItemStatus(String uniqueNumber, int status) {
        EntityManager em = getEntityManager();

        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            List<InvoiceItem> items = findInvoiceItemByUniqueNumber(uniqueNumber);
            for (InvoiceItem item : items) {
                //System.out.println(" BH-Status: " + item.getBHstatus() + "   FF-Status: " + item.getFFstatus());
                item.setBHstatus(new Integer(status));
                em.merge(item);
            }
            em.getTransaction().commit();
            if (items.size() > 0)
                return items.size();
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -2;
        } finally {
            em.close();
        }
    }
    
    
    public int updateInvoiceItemStatusReact(String uniqueNumber, int status) {
        EntityManager em = getEntityManager();

        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            List<InvoiceItem> items = findInvoiceItemByUniqueNumber(uniqueNumber);
            for (InvoiceItem item : items) {
                //System.out.println(" BH-Status: " + item.getBHstatus() + "   FF-Status: " + item.getFFstatus());
                if (item.getMrcommerceItemID() < 95000) {
                    item.setBHstatus(new Integer(status));
                    em.merge(item);
                }

            }
            em.getTransaction().commit();
            if (items.size() > 0)
                return items.size();
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -2;
        } finally {
            em.close();
        }
    }
    
        public int updateInvoiceItemFFStatus(String uniqueNumber, int status) {
        EntityManager em = getEntityManager();

        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            List<InvoiceItem> items = findInvoiceItemByUniqueNumber(uniqueNumber);
            for (InvoiceItem item : items) {
                //System.out.println(" BH-Status: " + item.getBHstatus() + "   FF-Status: " + item.getFFstatus());
                item.setFFstatus(new Integer(status));
                em.merge(item);
            }
            em.getTransaction().commit();
            if (items.size() > 0)
                return items.size();
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -2;
        } finally {
            em.close();
        }
    }
        
                public int updateInvoiceItemFFStatusReact(String uniqueNumber, int status) {
        EntityManager em = getEntityManager();

        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        //    @NamedQuery(name = "InvoiceItem.updateBHstatus", query = "UPDATE i.bHstatus FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            List<InvoiceItem> items = findInvoiceItemByUniqueNumber(uniqueNumber);
            for (InvoiceItem item : items) {
                //System.out.println(" BH-Status: " + item.getBHstatus() + "   FF-Status: " + item.getFFstatus());
                if (item.getMrcommerceItemID() < 95000) {
                item.setFFstatus(new Integer(status));
                em.merge(item);
                }
            }
            em.getTransaction().commit();
            if (items.size() > 0)
                return items.size();
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -2;
        } finally {
            em.close();
        }
    }

    public int updateInvoiceItemStatus(InvoiceItem invoiceItem, int status) {
        invoiceItem.setBHstatus(new Integer(status));
        try {
            edit(invoiceItem);
        } catch (NonexistentEntityException ex) {
            return -1;
        } catch (Exception ex) {
            return -1;
        }
        return 0;
    }
    
    public int updateInvoiceItemFFStatus(InvoiceItem invoiceItem, int status) {
        invoiceItem.setFFstatus(new Integer(status));
        try {
            edit(invoiceItem);
        } catch (NonexistentEntityException ex) {
            return -1;
        } catch (Exception ex) {
            return -1;
        }
        return 0;
    }    
    
    public int getInvoiceItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvoiceItem> rt = cq.from(InvoiceItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
