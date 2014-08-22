/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "OrganiserInvoiceItem")
@NamedQueries({
    @NamedQuery(name = "OrganiserInvoiceItem.findAll", query = "SELECT o FROM OrganiserInvoiceItem o"),
    @NamedQuery(name = "OrganiserInvoiceItem.findById", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.id = :id"),
    @NamedQuery(name = "OrganiserInvoiceItem.findByInvoiceID", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.invoiceID = :invoiceID")
//    @NamedQuery(name = "OrganiserInvoiceItem.findByServiceId", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.serviceId = :serviceId"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByServiceName", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.serviceName = :serviceName"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByAmount", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.amount = :amount"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByTaxRate", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.taxRate = :taxRate"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByCorrespondingBookingId", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.correspondingBookingId = :correspondingBookingId"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByBruttoek", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.bruttoek = :bruttoek"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByCorrespondingItemRabattID", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.correspondingItemRabattID = :correspondingItemRabattID"),
//    @NamedQuery(name = "OrganiserInvoiceItem.findByCorrespondingItemRabattAmount", query = "SELECT o FROM OrganiserInvoiceItem o WHERE o.correspondingItemRabattAmount = :correspondingItemRabattAmount")
})

    public class OrganiserInvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @PrimaryKeyJoinColumn(name = "InvoiceID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrganiserInvoice invoice;
    @Basic(optional = false)
    private int invoiceID;
    @Basic(optional = false)
    @Column(name = "ServiceId")
    private int serviceId;
    @Column(name = "ServiceName")
    private String serviceName;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "TaxRate")
    private BigDecimal taxRate;
    @Column(name = "CorrespondingBookingId")
    private Integer correspondingBookingId;
    @Basic(optional = false)
    @Column(name = "Brutto_ek")
    private BigDecimal bruttoek;
    @Column(name = "CorrespondingItemRabattID")
    private Integer correspondingItemRabattID;
    @Basic(optional = false)
    @Column(name = "CorrespondingItemRabattAmount")
    private BigDecimal correspondingItemRabattAmount;
    @Basic(optional = false)
    @Column(name = "Brutto_vk_currency_converted")
    private BigDecimal brutto_vk_currency_converted;
    @Basic(optional = false)
    @Column(name = "CorrespondingItemRabattAmount_currency_converted")
    private BigDecimal correspondingItemRabattAmount_currency_converted;
    @Column(name = "CorrespondingOrganiserInvoiceItemID")
    private Integer correspondingOrganiserInvoiceItemID; 
    
    

    public OrganiserInvoiceItem() {
    }

    public OrganiserInvoiceItem(Integer id) {
        this.id = id;
    }

    public OrganiserInvoiceItem(Integer id, int invoiceID, int service, BigDecimal bruttoek, BigDecimal correspondingItemRabattAmount) {
        this.id = id;
        this.invoiceID = invoiceID;
        this.serviceId = serviceId;
        this.bruttoek = bruttoek;
        this.correspondingItemRabattAmount = correspondingItemRabattAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public OrganiserInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(OrganiserInvoice invoice) {
        this.invoice = invoice;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getCorrespondingBookingId() {
        return correspondingBookingId;
    }

    public void setCorrespondingBookingId(Integer correspondingBookingId) {
        this.correspondingBookingId = correspondingBookingId;
    }

    public BigDecimal getBruttoek() {
        return bruttoek;
    }

    public void setBruttoek(BigDecimal bruttoek) {
        this.bruttoek = bruttoek;
    }

    public Integer getCorrespondingItemRabattID() {
        return correspondingItemRabattID;
    }

    public void setCorrespondingItemRabattID(Integer correspondingItemRabattID) {
        this.correspondingItemRabattID = correspondingItemRabattID;
    }

    public BigDecimal getCorrespondingItemRabattAmount() {
        return correspondingItemRabattAmount;
    }

    public void setCorrespondingItemRabattAmount(BigDecimal correspondingItemRabattAmount) {
        this.correspondingItemRabattAmount = correspondingItemRabattAmount;
    }
   
    public BigDecimal getBrutto_vk_currency_converted() {
        return brutto_vk_currency_converted;
    }

    public void setBrutto_vk_currency_converted(BigDecimal brutto_vk_currency_converted) {
        this.brutto_vk_currency_converted = brutto_vk_currency_converted;
    }
 
    public BigDecimal getCorrespondingItemRabattAmount_currency_converted() {
        return correspondingItemRabattAmount_currency_converted;
    }

    public void setCorrespondingItemRabattAmount_currency_converted(BigDecimal correspondingItemRabattAmount_currency_converted) {
        this.correspondingItemRabattAmount_currency_converted = correspondingItemRabattAmount_currency_converted;
    }
 
    public Integer getCorrespondingOrganiserInvoiceItemID() {
        return correspondingOrganiserInvoiceItemID;
    }

    public void setCorrespondingOrganiserInvoiceItemID(Integer correspondingOrganiserInvoiceItemID) {
        this.correspondingOrganiserInvoiceItemID = correspondingOrganiserInvoiceItemID;
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
        if (!(object instanceof OrganiserInvoiceItem)) {
            return false;
        }
        OrganiserInvoiceItem other = (OrganiserInvoiceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.OrganiserInvoiceItem[id=" + id + "]";
    }

}
