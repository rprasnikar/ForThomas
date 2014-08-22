/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "InvoiceStatus")
@NamedQueries({
    @NamedQuery(name = "InvoiceStatus.findAll", query = "SELECT i FROM InvoiceStatus i"),
    @NamedQuery(name = "InvoiceStatus.findByInvoiceId", query = "SELECT i FROM InvoiceStatus i WHERE i.invoiceId = :invoiceId"),
    @NamedQuery(name = "InvoiceStatus.findByStatus", query = "SELECT i FROM InvoiceStatus i WHERE i.status = :status")
//    @NamedQuery(name = "InvoiceStatus.findByInsertDate", query = "SELECT i FROM InvoiceStatus i WHERE i.insertDate = :insertDate"),
//    @NamedQuery(name = "InvoiceStatus.findByPartnerId", query = "SELECT i FROM InvoiceStatus i WHERE i.partnerId = :partnerId")
})
public class InvoiceStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InvoiceId")
    private Long invoiceId;
    @Column(name = "Status")
    private Short status;
    @Column(name = "InsertDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;
    @Lob
    @Column(name = "Notes")
    private String notes;
    @Column(name = "PartnerId")
    private Integer partnerId;

    public InvoiceStatus() {
    }

    public InvoiceStatus(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceStatus)) {
            return false;
        }
        InvoiceStatus other = (InvoiceStatus) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.InvoiceStatus[invoiceId=" + invoiceId + "]";
    }

}
