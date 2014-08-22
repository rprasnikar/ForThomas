/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.db;

import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.db.exceptions.IllegalOrphanException;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import at.jollydays.booking.bo.PaymentStatusChange;
import at.jollydays.booking.bo.InvoiceItem;
import java.util.ArrayList;
import java.util.Collection;
import at.jollydays.booking.bo.CreditNode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.FlushModeType;

/**
 *
 * @author Gunter Reinitzer
 */
public class InvoiceJpaController {

    public InvoiceJpaController() {
        emf = Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Invoice invoice) {
        if (invoice.getInvoiceItemCollection() == null) {
            invoice.setInvoiceItemCollection(new ArrayList<InvoiceItem>());
        }
        if (invoice.getCreditNodeCollection() == null) {
            invoice.setCreditNodeCollection(new ArrayList<CreditNode>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaymentStatusChange paymentStatusChange = invoice.getPaymentStatusChange();
            if (paymentStatusChange != null) {
                paymentStatusChange = em.getReference(paymentStatusChange.getClass(), paymentStatusChange.getInvoiceid());
                invoice.setPaymentStatusChange(paymentStatusChange);
            }
            Collection<InvoiceItem> attachedInvoiceItemCollection = new ArrayList<InvoiceItem>();
            for (InvoiceItem invoiceItemCollectionInvoiceItemToAttach : invoice.getInvoiceItemCollection()) {
                invoiceItemCollectionInvoiceItemToAttach = em.getReference(invoiceItemCollectionInvoiceItemToAttach.getClass(), invoiceItemCollectionInvoiceItemToAttach.getId());
                attachedInvoiceItemCollection.add(invoiceItemCollectionInvoiceItemToAttach);
            }
            invoice.setInvoiceItemCollection(attachedInvoiceItemCollection);
            Collection<CreditNode> attachedCreditNodeCollection = new ArrayList<CreditNode>();
            for (CreditNode creditNodeCollectionCreditNodeToAttach : invoice.getCreditNodeCollection()) {
                creditNodeCollectionCreditNodeToAttach = em.getReference(creditNodeCollectionCreditNodeToAttach.getClass(), creditNodeCollectionCreditNodeToAttach.getId());
                attachedCreditNodeCollection.add(creditNodeCollectionCreditNodeToAttach);
            }
            invoice.setCreditNodeCollection(attachedCreditNodeCollection);
            em.persist(invoice);
            if (paymentStatusChange != null) {
                Invoice oldInvoiceOfPaymentStatusChange = paymentStatusChange.getInvoice();
                if (oldInvoiceOfPaymentStatusChange != null) {
                    oldInvoiceOfPaymentStatusChange.setPaymentStatusChange(null);
                    oldInvoiceOfPaymentStatusChange = em.merge(oldInvoiceOfPaymentStatusChange);
                }
                paymentStatusChange.setInvoice(invoice);
                paymentStatusChange = em.merge(paymentStatusChange);
            }
            for (InvoiceItem invoiceItemCollectionInvoiceItem : invoice.getInvoiceItemCollection()) {
                Invoice oldInvoiceIDOfInvoiceItemCollectionInvoiceItem = invoiceItemCollectionInvoiceItem.getInvoiceID();
                invoiceItemCollectionInvoiceItem.setInvoiceID(invoice);
                invoiceItemCollectionInvoiceItem = em.merge(invoiceItemCollectionInvoiceItem);
                if (oldInvoiceIDOfInvoiceItemCollectionInvoiceItem != null) {
                    oldInvoiceIDOfInvoiceItemCollectionInvoiceItem.getInvoiceItemCollection().remove(invoiceItemCollectionInvoiceItem);
                    oldInvoiceIDOfInvoiceItemCollectionInvoiceItem = em.merge(oldInvoiceIDOfInvoiceItemCollectionInvoiceItem);
                }
            }
            for (CreditNode creditNodeCollectionCreditNode : invoice.getCreditNodeCollection()) {
                Invoice oldInvoiceIDOfCreditNodeCollectionCreditNode = creditNodeCollectionCreditNode.getInvoiceID();
                creditNodeCollectionCreditNode.setInvoiceID(invoice);
                creditNodeCollectionCreditNode = em.merge(creditNodeCollectionCreditNode);
                if (oldInvoiceIDOfCreditNodeCollectionCreditNode != null) {
                    oldInvoiceIDOfCreditNodeCollectionCreditNode.getCreditNodeCollection().remove(creditNodeCollectionCreditNode);
                    oldInvoiceIDOfCreditNodeCollectionCreditNode = em.merge(oldInvoiceIDOfCreditNodeCollectionCreditNode);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Invoice invoice) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice persistentInvoice = em.find(Invoice.class, invoice.getId());
            PaymentStatusChange paymentStatusChangeOld = persistentInvoice.getPaymentStatusChange();
            PaymentStatusChange paymentStatusChangeNew = invoice.getPaymentStatusChange();
            Collection<InvoiceItem> invoiceItemCollectionOld = persistentInvoice.getInvoiceItemCollection();
            Collection<InvoiceItem> invoiceItemCollectionNew = invoice.getInvoiceItemCollection();
            Collection<CreditNode> creditNodeCollectionOld = persistentInvoice.getCreditNodeCollection();
            Collection<CreditNode> creditNodeCollectionNew = invoice.getCreditNodeCollection();
            List<String> illegalOrphanMessages = null;
            if (paymentStatusChangeOld != null && !paymentStatusChangeOld.equals(paymentStatusChangeNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PaymentStatusChange " + paymentStatusChangeOld + " since its invoice field is not nullable.");
            }
            for (InvoiceItem invoiceItemCollectionOldInvoiceItem : invoiceItemCollectionOld) {
                if (!invoiceItemCollectionNew.contains(invoiceItemCollectionOldInvoiceItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvoiceItem " + invoiceItemCollectionOldInvoiceItem + " since its invoiceID field is not nullable.");
                }
            }
            for (CreditNode creditNodeCollectionOldCreditNode : creditNodeCollectionOld) {
                if (!creditNodeCollectionNew.contains(creditNodeCollectionOldCreditNode)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CreditNode " + creditNodeCollectionOldCreditNode + " since its invoiceID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paymentStatusChangeNew != null) {
                paymentStatusChangeNew = em.getReference(paymentStatusChangeNew.getClass(), paymentStatusChangeNew.getInvoiceid());
                invoice.setPaymentStatusChange(paymentStatusChangeNew);
            }
            Collection<InvoiceItem> attachedInvoiceItemCollectionNew = new ArrayList<InvoiceItem>();
            for (InvoiceItem invoiceItemCollectionNewInvoiceItemToAttach : invoiceItemCollectionNew) {
                invoiceItemCollectionNewInvoiceItemToAttach = em.getReference(invoiceItemCollectionNewInvoiceItemToAttach.getClass(), invoiceItemCollectionNewInvoiceItemToAttach.getId());
                attachedInvoiceItemCollectionNew.add(invoiceItemCollectionNewInvoiceItemToAttach);
            }
            invoiceItemCollectionNew = attachedInvoiceItemCollectionNew;
            invoice.setInvoiceItemCollection(invoiceItemCollectionNew);
            Collection<CreditNode> attachedCreditNodeCollectionNew = new ArrayList<CreditNode>();
            for (CreditNode creditNodeCollectionNewCreditNodeToAttach : creditNodeCollectionNew) {
                creditNodeCollectionNewCreditNodeToAttach = em.getReference(creditNodeCollectionNewCreditNodeToAttach.getClass(), creditNodeCollectionNewCreditNodeToAttach.getId());
                attachedCreditNodeCollectionNew.add(creditNodeCollectionNewCreditNodeToAttach);
            }
            creditNodeCollectionNew = attachedCreditNodeCollectionNew;
            invoice.setCreditNodeCollection(creditNodeCollectionNew);
            invoice = em.merge(invoice);
            if (paymentStatusChangeNew != null && !paymentStatusChangeNew.equals(paymentStatusChangeOld)) {
                Invoice oldInvoiceOfPaymentStatusChange = paymentStatusChangeNew.getInvoice();
                if (oldInvoiceOfPaymentStatusChange != null) {
                    oldInvoiceOfPaymentStatusChange.setPaymentStatusChange(null);
                    oldInvoiceOfPaymentStatusChange = em.merge(oldInvoiceOfPaymentStatusChange);
                }
                paymentStatusChangeNew.setInvoice(invoice);
                paymentStatusChangeNew = em.merge(paymentStatusChangeNew);
            }
            for (InvoiceItem invoiceItemCollectionNewInvoiceItem : invoiceItemCollectionNew) {
                if (!invoiceItemCollectionOld.contains(invoiceItemCollectionNewInvoiceItem)) {
                    Invoice oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem = invoiceItemCollectionNewInvoiceItem.getInvoiceID();
                    invoiceItemCollectionNewInvoiceItem.setInvoiceID(invoice);
                    invoiceItemCollectionNewInvoiceItem = em.merge(invoiceItemCollectionNewInvoiceItem);
                    if (oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem != null && !oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem.equals(invoice)) {
                        oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem.getInvoiceItemCollection().remove(invoiceItemCollectionNewInvoiceItem);
                        oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem = em.merge(oldInvoiceIDOfInvoiceItemCollectionNewInvoiceItem);
                    }
                }
            }
            for (CreditNode creditNodeCollectionNewCreditNode : creditNodeCollectionNew) {
                if (!creditNodeCollectionOld.contains(creditNodeCollectionNewCreditNode)) {
                    Invoice oldInvoiceIDOfCreditNodeCollectionNewCreditNode = creditNodeCollectionNewCreditNode.getInvoiceID();
                    creditNodeCollectionNewCreditNode.setInvoiceID(invoice);
                    creditNodeCollectionNewCreditNode = em.merge(creditNodeCollectionNewCreditNode);
                    if (oldInvoiceIDOfCreditNodeCollectionNewCreditNode != null && !oldInvoiceIDOfCreditNodeCollectionNewCreditNode.equals(invoice)) {
                        oldInvoiceIDOfCreditNodeCollectionNewCreditNode.getCreditNodeCollection().remove(creditNodeCollectionNewCreditNode);
                        oldInvoiceIDOfCreditNodeCollectionNewCreditNode = em.merge(oldInvoiceIDOfCreditNodeCollectionNewCreditNode);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invoice.getId();
                if (findInvoice(id) == null) {
                    throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice invoice;
            try {
                invoice = em.getReference(Invoice.class, id);
                invoice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            PaymentStatusChange paymentStatusChangeOrphanCheck = invoice.getPaymentStatusChange();
            if (paymentStatusChangeOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the PaymentStatusChange " + paymentStatusChangeOrphanCheck + " in its paymentStatusChange field has a non-nullable invoice field.");
            }
            Collection<InvoiceItem> invoiceItemCollectionOrphanCheck = invoice.getInvoiceItemCollection();
            for (InvoiceItem invoiceItemCollectionOrphanCheckInvoiceItem : invoiceItemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the InvoiceItem " + invoiceItemCollectionOrphanCheckInvoiceItem + " in its invoiceItemCollection field has a non-nullable invoiceID field.");
            }
            Collection<CreditNode> creditNodeCollectionOrphanCheck = invoice.getCreditNodeCollection();
            for (CreditNode creditNodeCollectionOrphanCheckCreditNode : creditNodeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the CreditNode " + creditNodeCollectionOrphanCheckCreditNode + " in its creditNodeCollection field has a non-nullable invoiceID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(invoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Invoice> findInvoiceEntities() {
        return findInvoiceEntities(true, -1, -1);
    }

    public List<Invoice> findInvoiceEntities(int maxResults, int firstResult) {
        return findInvoiceEntities(false, maxResults, firstResult);
    }

    public List<Invoice> findInvoiceEntitiesNext(int maxResults, int firstResult) {
        //in Invoice: @NamedQuery(name = "Invoice.findNext", query = "SELECT i FROM Invoice i WHERE i.id >= :id order by i.id"),
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("Invoice.findNext", Invoice.class);
            query2.setParameter("id", firstResult);
            query2.setMaxResults(maxResults);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Invoice> findInvoiceEntitiesNextPartner(int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("Invoice.findNextPartner", Invoice.class);
            query2.setParameter("id", firstResult);
            query2.setMaxResults(maxResults);
            return query2.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Invoice> findInvoiceEntitiesOrdertypeDate(int ordertype, int paymenttype, Date dateFrom, Date dateTo, int maxResults) {
        EntityManager em = getEntityManager();

        try {
            Query query = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("Invoice.findOrdertypeDate", Invoice.class);
            query.setParameter("ordertype", Integer.toString(ordertype));
            query.setParameter("paymenttype", Integer.toString(paymenttype));
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
                        
            if (maxResults > 0)
                query.setMaxResults(maxResults);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    private List<Invoice> findInvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("Invoice.findNext", Invoice.class);
            query2.setParameter("id", firstResult);
            query2.setMaxResults(maxResults);
            return query2.getResultList();


            /*
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Invoice> rootInvoice = cq.from(Invoice.class);
            cq.select(rootInvoice);
            Order[] orders = new Order[1];
            cq.orderBy(cb.asc(rootInvoice.get("id")));


            Query q = em.createQuery(cq);
            if (!all) {
                Path<?> attributePath = rootInvoice.get("id");
                Predicate predicate = cb.greaterThanOrEqualTo(attributePath, cb.literal(new Long(2)));
                cq.where(predicate);

                q.setMaxResults(maxResults);
                //q.setFirstResult(firstResult - 1);
            }


            javax.persistence.EntityManager entityManager;
            java.util.List<Invoice> list;
            javax.persistence.Query query;
            org.jdesktop.beansbinding.BindingGroup bindingGroup;
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ConfigurationController.class).getContext().getResourceMap(ConfigurationController.class);

            entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory(resourceMap.getString("entityManager.persistenceUnit")).createEntityManager(); // NOI18N
            query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery(resourceMap.getString("query.query")); // NOI18N
            list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());





            return q.getResultList();
             *
             */
        } finally {
            em.close();
        }
    }

    public List<Invoice> findInvoiceByNumber(int invoiceNumber) {
        EntityManager em = getEntityManager();
        try {
            Query query2 = java.beans.Beans.isDesignTime() ? null : em.createNamedQuery("Invoice.findByInvoiceNumber", Invoice.class);
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


    public Invoice findInvoice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Invoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invoice> rt = cq.from(Invoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public void updateInvoiceBuha_ss_status(Invoice i) {
        EntityManager em = getEntityManager();

        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            i.setBuha_ss_status((short) 1);
            i = em.merge(i);
            em.getTransaction().commit();
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
                em.close();
            }
        }
        
        public void updateInvoiceBuha_ss_status(Invoice i, short status) {
        EntityManager em = getEntityManager();

        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.getTransaction().begin();
            i.setBuha_ss_status((short) status);
            i = em.merge(i);
            em.getTransaction().commit();
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            em.close();
        }
    } 
        


}
