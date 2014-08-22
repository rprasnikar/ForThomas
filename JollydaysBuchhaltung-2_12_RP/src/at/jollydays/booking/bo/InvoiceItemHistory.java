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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "InvoiceItemHistory")
@NamedQueries({
    @NamedQuery(name = "InvoiceItemHistory.findAll", query = "SELECT i FROM InvoiceItemHistory i"),
    @NamedQuery(name = "InvoiceItemHistory.findById", query = "SELECT i FROM InvoiceItemHistory i WHERE i.id = :id")
//    @NamedQuery(name = "InvoiceItemHistory.findByCrdate", query = "SELECT i FROM InvoiceItemHistory i WHERE i.crdate = :crdate"),
//    @NamedQuery(name = "InvoiceItemHistory.findByOldBHStatus", query = "SELECT i FROM InvoiceItemHistory i WHERE i.oldBHStatus = :oldBHStatus"),
//    @NamedQuery(name = "InvoiceItemHistory.findByOldFFStatus", query = "SELECT i FROM InvoiceItemHistory i WHERE i.oldFFStatus = :oldFFStatus"),
//    @NamedQuery(name = "InvoiceItemHistory.findByUniqueNumber", query = "SELECT i FROM InvoiceItemHistory i WHERE i.uniqueNumber = :uniqueNumber"),
//    @NamedQuery(name = "InvoiceItemHistory.findByUniqueValue", query = "SELECT i FROM InvoiceItemHistory i WHERE i.uniqueValue = :uniqueValue"),
//    @NamedQuery(name = "InvoiceItemHistory.findByProcess", query = "SELECT i FROM InvoiceItemHistory i WHERE i.process = :process")
})
public class InvoiceItemHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "crdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crdate;
    @Column(name = "oldBHStatus")
    private Integer oldBHStatus;
    @Column(name = "oldFFStatus")
    private Integer oldFFStatus;
    @Column(name = "uniqueNumber")
    private String uniqueNumber;
    @Column(name = "uniqueValue")
    private String uniqueValue;
    @Column(name = "process")
    private Integer process;
    @JoinColumn(name = "oldInvoiceItemId", referencedColumnName = "ID")
    @ManyToOne
    private InvoiceItem invoiceItemOld;
    @JoinColumn(name = "newInvoiceItemId", referencedColumnName = "ID")
    @ManyToOne
    private InvoiceItem invoiceItemNew;

    public InvoiceItemHistory() {
    }

    public InvoiceItemHistory(Integer id) {
        this.id = id;
    }

    public InvoiceItemHistory(Integer id, Date crdate) {
        this.id = id;
        this.crdate = crdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCrdate() {
        return crdate;
    }

    public void setCrdate(Date crdate) {
        this.crdate = crdate;
    }

    public Integer getOldBHStatus() {
        return oldBHStatus;
    }

    public void setOldBHStatus(Integer oldBHStatus) {
        this.oldBHStatus = oldBHStatus;
    }

    public Integer getOldFFStatus() {
        return oldFFStatus;
    }

    public void setOldFFStatus(Integer oldFFStatus) {
        this.oldFFStatus = oldFFStatus;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public InvoiceItem getInvoiceItemOld() {
        return invoiceItemOld;
    }

    public void setInvoiceItemOld(InvoiceItem invoiceItemOld) {
        this.invoiceItemOld = invoiceItemOld;
    }

    public InvoiceItem getInvoiceItemNew() {
        return invoiceItemNew;
    }

    public void setInvoiceItemNew(InvoiceItem invoiceItemNew) {
        this.invoiceItemNew = invoiceItemNew;
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
        if (!(object instanceof InvoiceItemHistory)) {
            return false;
        }
        InvoiceItemHistory other = (InvoiceItemHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.InvoiceItemHistory[id=" + id + "]";
    }

}
