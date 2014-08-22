/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "Invoice")
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findNext", query = "SELECT i FROM Invoice i WHERE i.id >= :id order by i.id"),
    @NamedQuery(name = "Invoice.findNextOpt", query = "SELECT i, t, p FROM Invoice i LEFT JOIN i.invoiceItem t LEFT JOIN i.partner p WHERE i.id >= :id order by i.id"),
//    @NamedQuery(name = "Invoice.findNext", query = "SELECT i, t, p, s, c FROM Invoice i LEFT JOIN i.invoiceItem t LEFT JOIN i.partner p LEFT JOIN i.paymentStatusChange s LEFT JOIN i.creditNodeCollection c WHERE i.id >= :id order by i.id"),
    @NamedQuery(name = "Invoice.findNextPartner", query = "SELECT i.id, p FROM Invoice i JOIN i.partner p WHERE i.id >= :id order by i.id"),
    @NamedQuery(name = "Invoice.findOrdertypeDate", query = "SELECT i FROM Invoice i WHERE i.category = :ordertype and i.paymentType = :paymenttype and i.invoiceDate >= :dateFrom and i.invoiceDate <= :dateTo order by i.id"),
//    @NamedQuery(name = "Invoice.findOrdertypeDate", query = "SELECT i FROM Invoice i JOIN i.invoiceItem t WHERE i.category = :ordertype and i.paymentType = :paymenttype and i.invoiceDate >= :dateFrom and i.invoiceDate <= :dateTo order by i.id"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findByPartnerID", query = "SELECT i FROM Invoice i WHERE i.partnerID = :partnerID"),
    @NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber")
//    @NamedQuery(name = "Invoice.findByInvoiceType", query = "SELECT i FROM Invoice i WHERE i.invoiceType = :invoiceType"),
//    @NamedQuery(name = "Invoice.findByPaymentType", query = "SELECT i FROM Invoice i WHERE i.paymentType = :paymentType"),
//    @NamedQuery(name = "Invoice.findByCurrencyISOCode", query = "SELECT i FROM Invoice i WHERE i.currencyISOCode = :currencyISOCode"),
//    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate"),
//    @NamedQuery(name = "Invoice.findByDueDate", query = "SELECT i FROM Invoice i WHERE i.dueDate = :dueDate"),
//    @NamedQuery(name = "Invoice.findByLanguage", query = "SELECT i FROM Invoice i WHERE i.language = :language"),
//    @NamedQuery(name = "Invoice.findByLocked", query = "SELECT i FROM Invoice i WHERE i.locked = :locked"),
//    @NamedQuery(name = "Invoice.findByLockedReason", query = "SELECT i FROM Invoice i WHERE i.lockedReason = :lockedReason"),
//    @NamedQuery(name = "Invoice.findByStatus", query = "SELECT i FROM Invoice i WHERE i.status = :status"),
//    @NamedQuery(name = "Invoice.findByBillTo", query = "SELECT i FROM Invoice i WHERE i.billTo = :billTo"),
//    @NamedQuery(name = "Invoice.findByDeliveryTO", query = "SELECT i FROM Invoice i WHERE i.deliveryTO = :deliveryTO"),
//    @NamedQuery(name = "Invoice.findByCreationDate", query = "SELECT i FROM Invoice i WHERE i.creationDate = :creationDate"),
//    @NamedQuery(name = "Invoice.findByCategory", query = "SELECT i FROM Invoice i WHERE i.category = :category"),
//    @NamedQuery(name = "Invoice.findByReference", query = "SELECT i FROM Invoice i WHERE i.reference = :reference"),
//    @NamedQuery(name = "Invoice.findByCorrespondingInvoice", query = "SELECT i FROM Invoice i WHERE i.correspondingInvoice = :correspondingInvoice"),
//    @NamedQuery(name = "Invoice.findByIsTicket", query = "SELECT i FROM Invoice i WHERE i.isTicket = :isTicket"),
//    @NamedQuery(name = "Invoice.findByARNo", query = "SELECT i FROM Invoice i WHERE i.aRNo = :aRNo"),
//    @NamedQuery(name = "Invoice.findByARDate", query = "SELECT i FROM Invoice i WHERE i.aRDate = :aRDate"),
//    @NamedQuery(name = "Invoice.findByARSent", query = "SELECT i FROM Invoice i WHERE i.aRSent = :aRSent"),
//    @NamedQuery(name = "Invoice.findByTransferDate", query = "SELECT i FROM Invoice i WHERE i.transferDate = :transferDate"),
//    @NamedQuery(name = "Invoice.findByPaybackno", query = "SELECT i FROM Invoice i WHERE i.paybackno = :paybackno"),
//    @NamedQuery(name = "Invoice.findByPaybacktype", query = "SELECT i FROM Invoice i WHERE i.paybacktype = :paybacktype"),
//    @NamedQuery(name = "Invoice.findByPaybackSent", query = "SELECT i FROM Invoice i WHERE i.paybackSent = :paybackSent"),
//    @NamedQuery(name = "Invoice.findByPDFCreated", query = "SELECT i FROM Invoice i WHERE i.pDFCreated = :pDFCreated"),
//    @NamedQuery(name = "Invoice.findByBillingType", query = "SELECT i FROM Invoice i WHERE i.billingType = :billingType"),
//    @NamedQuery(name = "Invoice.findByBesorger", query = "SELECT i FROM Invoice i WHERE i.besorger = :besorger")
})
public class Invoice implements Serializable {
    @Basic(optional = false)
    @Column(name = "PDFEmail")
    private boolean pDFEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private Collection<InvoiceItem> invoiceItemCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PartnerID")
    private int partnerID;
    @Basic(optional = false)
    @Column(name = "InvoiceNumber")
    private String invoiceNumber;
    @Basic(optional = false)
    @Column(name = "InvoiceType")
    private String invoiceType;
//    @Column(name = "PaymentTypes")
//    private String paymentType;
    @Basic(optional = false)
    @Column(name = "CurrencyISOCode")
    private String currencyISOCode;
    @Basic(optional = false)
    @Column(name = "InvoiceDate")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "Language")
    private String language;
    @Column(name = "Locked")
    private Character locked;
    @Column(name = "LockedReason")
    private String lockedReason;
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "BillTo")
    private int billTo;
    @Basic(optional = false)
    @Column(name = "DeliveryTO")
    private int deliveryTO;
    @Basic(optional = false)
    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "Category")
    private String category;
    @Column(name = "Reference")
    private String reference;
    @Column(name = "CorrespondingInvoice")
    private Integer correspondingInvoice;
    @Basic(optional = false)
    @Column(name = "IsTicket")
    private short isTicket;
    @Column(name = "AR_No")
    private String aRNo;
    @Column(name = "AR_Date")
    @Temporal(TemporalType.DATE)
    private Date aRDate;
    @Basic(optional = false)
    @Column(name = "AR_Sent")
    private short aRSent;
    @Column(name = "TransferDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;
    @Column(name = "Paybackno")
    private String paybackno;
    @Basic(optional = false)
    @Column(name = "Paybacktype")
    private short paybacktype;
    @Column(name = "PaybackSent")
    private String paybackSent;
    @Column(name = "PDFCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pDFCreated;
    @Column(name = "BillingType")
    private String billingType;
    @Basic(optional = false)
    @Column(name = "Besorger")
    private boolean besorger;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "invoice")
    private PaymentStatusChange paymentStatusChange;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceID")
    private Collection<InvoiceItem> invoiceItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceID")
    private Collection<CreditNode> creditNodeCollection;
    @JoinColumn(name = "PaymentType", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PaymentTypes paymentType;
    @JoinColumn(name = "PartnerID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Partner partner;
    @Column(name = "Buha_ss_status")
    private short Buha_ss_status;

    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Invoice(Integer id, int partnerID, String invoiceNumber, String invoiceType, String currencyISOCode, Date invoiceDate, int billTo, int deliveryTO, Date creationDate, short isTicket, short aRSent, short paybacktype, boolean besorger) {
        this.id = id;
        this.partnerID = partnerID;
        this.invoiceNumber = invoiceNumber;
        this.invoiceType = invoiceType;
        this.currencyISOCode = currencyISOCode;
        this.invoiceDate = invoiceDate;
        this.billTo = billTo;
        this.deliveryTO = deliveryTO;
        this.creationDate = creationDate;
        this.isTicket = isTicket;
        this.aRSent = aRSent;
        this.paybacktype = paybacktype;
        this.besorger = besorger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(int partnerID) {
        this.partnerID = partnerID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    public String getCurrencyISOCode() {
        return currencyISOCode;
    }

    public void setCurrencyISOCode(String currencyISOCode) {
        this.currencyISOCode = currencyISOCode;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    public String getLockedReason() {
        return lockedReason;
    }

    public void setLockedReason(String lockedReason) {
        this.lockedReason = lockedReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBillTo() {
        return billTo;
    }

    public void setBillTo(int billTo) {
        this.billTo = billTo;
    }

    public int getDeliveryTO() {
        return deliveryTO;
    }

    public void setDeliveryTO(int deliveryTO) {
        this.deliveryTO = deliveryTO;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getCorrespondingInvoice() {
        return correspondingInvoice;
    }

    public void setCorrespondingInvoice(Integer correspondingInvoice) {
        this.correspondingInvoice = correspondingInvoice;
    }

    public short getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(short isTicket) {
        this.isTicket = isTicket;
    }

    public String getARNo() {
        return aRNo;
    }

    public void setARNo(String aRNo) {
        this.aRNo = aRNo;
    }

    public Date getARDate() {
        return aRDate;
    }

    public void setARDate(Date aRDate) {
        this.aRDate = aRDate;
    }

    public short getARSent() {
        return aRSent;
    }

    public void setARSent(short aRSent) {
        this.aRSent = aRSent;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getPaybackno() {
        return paybackno;
    }

    public void setPaybackno(String paybackno) {
        this.paybackno = paybackno;
    }

    public short getPaybacktype() {
        return paybacktype;
    }

    public void setPaybacktype(short paybacktype) {
        this.paybacktype = paybacktype;
    }

    public String getPaybackSent() {
        return paybackSent;
    }

    public void setPaybackSent(String paybackSent) {
        this.paybackSent = paybackSent;
    }

    public Date getPDFCreated() {
        return pDFCreated;
    }

    public void setPDFCreated(Date pDFCreated) {
        this.pDFCreated = pDFCreated;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public boolean getBesorger() {
        return besorger;
    }

    public void setBesorger(boolean besorger) {
        this.besorger = besorger;
    }

    public PaymentStatusChange getPaymentStatusChange() {
        return paymentStatusChange;
    }

    public void setPaymentStatusChange(PaymentStatusChange paymentStatusChange) {
        this.paymentStatusChange = paymentStatusChange;
    }

    public Collection<InvoiceItem> getInvoiceItemCollection() {
        return invoiceItem;
    }

    public void setInvoiceItemCollection(Collection<InvoiceItem> invoiceItemCollection) {
        this.invoiceItem = invoiceItemCollection;
    }



    public Collection<CreditNode> getCreditNodeCollection() {
        return creditNodeCollection;
    }

    public void setCreditNodeCollection(Collection<CreditNode> creditNodeCollection) {
        this.creditNodeCollection = creditNodeCollection;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.Invoice[id=" + id + "]";
    }

    public boolean getPDFEmail() {
        return pDFEmail;
    }

    public void setPDFEmail(boolean pDFEmail) {
        this.pDFEmail = pDFEmail;
    }

    //Buha_ss_status
    public short getBuha_ss_status() {
        return Buha_ss_status;
    }

    public void setBuha_ss_status(short Buha_ss_status) {
        this.Buha_ss_status = Buha_ss_status;
    }

}
