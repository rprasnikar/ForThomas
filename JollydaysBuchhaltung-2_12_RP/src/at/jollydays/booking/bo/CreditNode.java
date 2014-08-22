/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CreditNode")
@NamedQueries({
    @NamedQuery(name = "CreditNode.findAll", query = "SELECT c FROM CreditNode c"),
    @NamedQuery(name = "CreditNode.findById", query = "SELECT c FROM CreditNode c WHERE c.id = :id"),
    @NamedQuery(name = "CreditNode.findByPartnerID", query = "SELECT c FROM CreditNode c WHERE c.partnerID = :partnerID")
//    @NamedQuery(name = "CreditNode.findByCreationDate", query = "SELECT c FROM CreditNode c WHERE c.creationDate = :creationDate"),
//    @NamedQuery(name = "CreditNode.findByAmount", query = "SELECT c FROM CreditNode c WHERE c.amount = :amount"),
//    @NamedQuery(name = "CreditNode.findByStatus", query = "SELECT c FROM CreditNode c WHERE c.status = :status"),
//    @NamedQuery(name = "CreditNode.findByValidToDate", query = "SELECT c FROM CreditNode c WHERE c.validToDate = :validToDate"),
//    @NamedQuery(name = "CreditNode.findByDisplayText", query = "SELECT c FROM CreditNode c WHERE c.displayText = :displayText"),
//    @NamedQuery(name = "CreditNode.findByActive", query = "SELECT c FROM CreditNode c WHERE c.active = :active")
})
public class CreditNode implements Serializable {
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
    @Column(name = "CreationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "Status")
    private String status;
    @Column(name = "ValidToDate")
    @Temporal(TemporalType.DATE)
    private Date validToDate;
    @Column(name = "DisplayText")
    private String displayText;
    @Column(name = "Active")
    private Boolean active;
    @JoinColumn(name = "InvoiceID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Invoice invoiceID;

    public CreditNode() {
    }

    public CreditNode(Integer id) {
        this.id = id;
    }

    public CreditNode(Integer id, int partnerID, Date creationDate) {
        this.id = id;
        this.partnerID = partnerID;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Invoice getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Invoice invoiceID) {
        this.invoiceID = invoiceID;
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
        if (!(object instanceof CreditNode)) {
            return false;
        }
        CreditNode other = (CreditNode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.CreditNode[id=" + id + "]";
    }

}
