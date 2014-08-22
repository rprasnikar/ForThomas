/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "CooperationInvoiceItem")
@NamedQueries({
    @NamedQuery(name = "CooperationInvoiceItem.findAll", query = "SELECT c FROM CooperationInvoiceItem c"),
    @NamedQuery(name = "CooperationInvoiceItem.findById", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.id = :id"),
    @NamedQuery(name = "CooperationInvoiceItem.findByInvoiceID", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.invoiceID = :invoiceID")
//    @NamedQuery(name = "CooperationInvoiceItem.findByServiceId", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.serviceId = :serviceId"),
//    @NamedQuery(name = "CooperationInvoiceItem.findByServiceName", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.serviceName = :serviceName"),
//    @NamedQuery(name = "CooperationInvoiceItem.findByAmount", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.amount = :amount"),
//    @NamedQuery(name = "CooperationInvoiceItem.findByTaxRate", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.taxRate = :taxRate"),
//    @NamedQuery(name = "CooperationInvoiceItem.findByCorrespondingInvoiceItemId", query = "SELECT c FROM CooperationInvoiceItem c WHERE c.correspondingInvoiceItemId = :correspondingInvoiceItemId")
})
public class CooperationInvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "InvoiceID")
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
    @Column(name = "CorrespondingInvoiceItemId")
    private Integer correspondingInvoiceItemId;

    public CooperationInvoiceItem() {
    }

    public CooperationInvoiceItem(Integer id) {
        this.id = id;
    }

    public CooperationInvoiceItem(Integer id, int invoiceID, int serviceId) {
        this.id = id;
        this.invoiceID = invoiceID;
        this.serviceId = serviceId;
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

    public int getServiceId() {
        return serviceId;
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

    public Integer getCorrespondingInvoiceItemId() {
        return correspondingInvoiceItemId;
    }

    public void setCorrespondingInvoiceItemId(Integer correspondingInvoiceItemId) {
        this.correspondingInvoiceItemId = correspondingInvoiceItemId;
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
        if (!(object instanceof CooperationInvoiceItem)) {
            return false;
        }
        CooperationInvoiceItem other = (CooperationInvoiceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.CooperationInvoiceItem[id=" + id + "]";
    }

}
