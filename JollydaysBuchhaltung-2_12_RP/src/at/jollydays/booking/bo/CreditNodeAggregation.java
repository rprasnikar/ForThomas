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
@Table(name = "CreditNodeAggregation")
@NamedQueries({
    @NamedQuery(name = "CreditNodeAggregation.findAll", query = "SELECT c FROM CreditNodeAggregation c"),
    @NamedQuery(name = "CreditNodeAggregation.findById", query = "SELECT c FROM CreditNodeAggregation c WHERE c.id = :id"),
    @NamedQuery(name = "CreditNodeAggregation.findByPartnerID", query = "SELECT c FROM CreditNodeAggregation c WHERE c.partnerID = :partnerID")
//    @NamedQuery(name = "CreditNodeAggregation.findByCurrentDate", query = "SELECT c FROM CreditNodeAggregation c WHERE c.currentDate = :currentDate"),
//    @NamedQuery(name = "CreditNodeAggregation.findByAmount", query = "SELECT c FROM CreditNodeAggregation c WHERE c.amount = :amount"),
//    @NamedQuery(name = "CreditNodeAggregation.findByValidToDate", query = "SELECT c FROM CreditNodeAggregation c WHERE c.validToDate = :validToDate")
})
public class CreditNodeAggregation implements Serializable {
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
    @Column(name = "CurrentDate")
    @Temporal(TemporalType.DATE)
    private Date currentDate;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "ValidToDate")
    @Temporal(TemporalType.DATE)
    private Date validToDate;

    public CreditNodeAggregation() {
    }

    public CreditNodeAggregation(Integer id) {
        this.id = id;
    }

    public CreditNodeAggregation(Integer id, int partnerID, Date currentDate) {
        this.id = id;
        this.partnerID = partnerID;
        this.currentDate = currentDate;
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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
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
        if (!(object instanceof CreditNodeAggregation)) {
            return false;
        }
        CreditNodeAggregation other = (CreditNodeAggregation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.CreditNodeAggregation[id=" + id + "]";
    }

}
