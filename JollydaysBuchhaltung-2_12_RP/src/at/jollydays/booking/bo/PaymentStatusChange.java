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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "PaymentStatusChange")
@NamedQueries({
    @NamedQuery(name = "PaymentStatusChange.findAll", query = "SELECT p FROM PaymentStatusChange p"),
    @NamedQuery(name = "PaymentStatusChange.findByInvoiceid", query = "SELECT p FROM PaymentStatusChange p WHERE p.invoiceid = :invoiceid")
//    @NamedQuery(name = "PaymentStatusChange.findBySentdate", query = "SELECT p FROM PaymentStatusChange p WHERE p.sentdate = :sentdate")
})
public class PaymentStatusChange implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "invoiceid")
    private Integer invoiceid;
    @Basic(optional = false)
    @Column(name = "sentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentdate;
    @JoinColumn(name = "invoiceid", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Invoice invoice;

    public PaymentStatusChange() {
    }

    public PaymentStatusChange(Integer invoiceid) {
        this.invoiceid = invoiceid;
    }

    public PaymentStatusChange(Integer invoiceid, Date sentdate) {
        this.invoiceid = invoiceid;
        this.sentdate = sentdate;
    }

    public Integer getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(Integer invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Date getSentdate() {
        return sentdate;
    }

    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceid != null ? invoiceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentStatusChange)) {
            return false;
        }
        PaymentStatusChange other = (PaymentStatusChange) object;
        if ((this.invoiceid == null && other.invoiceid != null) || (this.invoiceid != null && !this.invoiceid.equals(other.invoiceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.PaymentStatusChange[invoiceid=" + invoiceid + "]";
    }

}
