/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "buha_filter")
@NamedQueries({
    @NamedQuery(name = "BuhaFilter.findAll", query = "SELECT b FROM BuhaFilter b"),
    @NamedQuery(name = "BuhaFilter.findById", query = "SELECT b FROM BuhaFilter b WHERE b.id = :id")
//    @NamedQuery(name = "BuhaFilter.findByItemFrom", query = "SELECT b FROM BuhaFilter b WHERE b.itemFrom = :itemFrom"),
//    @NamedQuery(name = "BuhaFilter.findByItemTo", query = "SELECT b FROM BuhaFilter b WHERE b.itemTo = :itemTo"),
//    @NamedQuery(name = "BuhaFilter.findByGutscheinfilter", query = "SELECT b FROM BuhaFilter b WHERE b.gutscheinfilter = :gutscheinfilter"),
//    @NamedQuery(name = "BuhaFilter.findByCountry", query = "SELECT b FROM BuhaFilter b WHERE b.country = :country"),
//    @NamedQuery(name = "BuhaFilter.findByDescription", query = "SELECT b FROM BuhaFilter b WHERE b.description = :description"),
//    @NamedQuery(name = "BuhaFilter.findByBesorger", query = "SELECT b FROM BuhaFilter b WHERE b.besorger = :besorger"),
//    @NamedQuery(name = "BuhaFilter.findByTaxrate", query = "SELECT b FROM BuhaFilter b WHERE b.taxrate = :taxrate"),
//    @NamedQuery(name = "BuhaFilter.findByInvoiceType", query = "SELECT b FROM BuhaFilter b WHERE b.invoiceType = :invoiceType"),
//    @NamedQuery(name = "BuhaFilter.findByPaymentType", query = "SELECT b FROM BuhaFilter b WHERE b.paymentType = :paymentType"),
//    @NamedQuery(name = "BuhaFilter.findByStatus", query = "SELECT b FROM BuhaFilter b WHERE b.status = :status"),
//    @NamedQuery(name = "BuhaFilter.findByCategory", query = "SELECT b FROM BuhaFilter b WHERE b.category = :category"),
//    @NamedQuery(name = "BuhaFilter.findByBillingType", query = "SELECT b FROM BuhaFilter b WHERE b.billingType = :billingType"),
//    @NamedQuery(name = "BuhaFilter.findByBHstatus", query = "SELECT b FROM BuhaFilter b WHERE b.bHstatus = :bHstatus"),
//    @NamedQuery(name = "BuhaFilter.findByFFstatus", query = "SELECT b FROM BuhaFilter b WHERE b.fFstatus = :fFstatus")
})
public class BuhaFilter implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "item_from")
    private int itemFrom;
    @Basic(optional = false)
    @Column(name = "item_to")
    private int itemTo;
    @Basic(optional = false)
    @Column(name = "gutscheinfilter")
    private String gutscheinfilter;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "besorger")
    private boolean besorger;
    @Basic(optional = false)
    @Column(name = "taxrate")
    private float taxrate;
    @Basic(optional = false)
    @Column(name = "checktaxrate")
    private boolean checktaxrate;
    @Basic(optional = false)
    @Column(name = "InvoiceType")
    private String invoiceType;
    @Basic(optional = false)
    @Column(name = "PaymentType")
    private String paymentType;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "Category")
    private String category;
    @Basic(optional = false)
    @Column(name = "BillingType")
    private String billingType;
    @Basic(optional = false)
    @Column(name = "BH_status")
    private short bHstatus;
    @Basic(optional = false)
    @Column(name = "FF_status")
    private short fFstatus;
    @Basic(optional = false)
    @Column(name = "donotaccount")
    private boolean donotaccount;
    @Basic(optional = false)
    @Column(name = "sign")
    private char sign;
    @Column(name = "saleschannel_id")
    private Integer saleschannel_id;
    @Column(name = "reverse_charge")
    private short reverse_charge;            
    @JoinColumn(name = "id_booking", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuhaBooking buhaBooking;

    public BuhaFilter() {
    }

    public BuhaFilter(Integer id) {
        this.id = id;
    }

    public BuhaFilter(Integer id, int itemFrom, int itemTo, String gutscheinfilter, String country, String description, boolean besorger, float taxrate, String invoiceType, String paymentType, String status, String category, String billingType, short bHstatus, short fFstatus) {
        this.id = id;
        this.itemFrom = itemFrom;
        this.itemTo = itemTo;
        this.gutscheinfilter = gutscheinfilter;
        this.country = country;
        this.description = description;
        this.besorger = besorger;
        this.taxrate = taxrate;
        this.invoiceType = invoiceType;
        this.paymentType = paymentType;
        this.status = status;
        this.category = category;
        this.billingType = billingType;
        this.bHstatus = bHstatus;
        this.fFstatus = fFstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getItemFrom() {
        return itemFrom;
    }

    public void setItemFrom(int itemFrom) {
        int oldItemFrom = this.itemFrom;
        this.itemFrom = itemFrom;
        changeSupport.firePropertyChange("itemFrom", oldItemFrom, itemFrom);
    }

    public int getItemTo() {
        return itemTo;
    }

    public void setItemTo(int itemTo) {
        int oldItemTo = this.itemTo;
        this.itemTo = itemTo;
        changeSupport.firePropertyChange("itemTo", oldItemTo, itemTo);
    }

    public String getGutscheinfilter() {
        return gutscheinfilter;
    }

    public void setGutscheinfilter(String gutscheinfilter) {
        String oldGutscheinfilter = this.gutscheinfilter;
        this.gutscheinfilter = gutscheinfilter;
        changeSupport.firePropertyChange("gutscheinfilter", oldGutscheinfilter, gutscheinfilter);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        changeSupport.firePropertyChange("country", oldCountry, country);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public boolean getBesorger() {
        return besorger;
    }

    public void setBesorger(boolean besorger) {
        boolean oldBesorger = this.besorger;
        this.besorger = besorger;
        changeSupport.firePropertyChange("besorger", oldBesorger, besorger);
    }

    public float getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(float taxrate) {
        float oldTaxrate = this.taxrate;
        this.taxrate = taxrate;
        changeSupport.firePropertyChange("taxrate", oldTaxrate, taxrate);
    }


    public boolean getCheckTaxrate() {
        return checktaxrate;
    }

    public void setCheckTaxrate(boolean checktaxrate) {
        boolean oldchecktaxrate = this.checktaxrate;
        this.checktaxrate = checktaxrate;
        changeSupport.firePropertyChange("checktaxrate", oldchecktaxrate, checktaxrate);
    }



    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        String oldInvoiceType = this.invoiceType;
        this.invoiceType = invoiceType;
        changeSupport.firePropertyChange("invoiceType", oldInvoiceType, invoiceType);
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        String oldPaymentType = this.paymentType;
        this.paymentType = paymentType;
        changeSupport.firePropertyChange("paymentType", oldPaymentType, paymentType);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        String oldCategory = this.category;
        this.category = category;
        changeSupport.firePropertyChange("category", oldCategory, category);
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        String oldBillingType = this.billingType;
        this.billingType = billingType;
        changeSupport.firePropertyChange("billingType", oldBillingType, billingType);
    }

    public short getBHstatus() {
        return bHstatus;
    }

    public void setBHstatus(short bHstatus) {
        short oldBHstatus = this.bHstatus;
        this.bHstatus = bHstatus;
        changeSupport.firePropertyChange("BHstatus", oldBHstatus, bHstatus);
    }

    public short getFFstatus() {
        return fFstatus;
    }

    public void setFFstatus(short fFstatus) {
        short oldFFstatus = this.fFstatus;
        this.fFstatus = fFstatus;
        changeSupport.firePropertyChange("FFstatus", oldFFstatus, fFstatus);
    }

    public BuhaBooking getBuhaBooking() {
        return buhaBooking;
    }

    public void setBuhaBooking(BuhaBooking buhaBooking) {
        BuhaBooking oldBuhaBooking = this.buhaBooking;
        this.buhaBooking = buhaBooking;
        changeSupport.firePropertyChange("buhaBooking", oldBuhaBooking, buhaBooking);
    }

    public boolean getDonotaccount() {
        return donotaccount;
    }

    public void setDonotaccount(boolean donotaccount) {
        boolean oldDonotaccount = this.donotaccount;
        this.donotaccount = donotaccount;
        changeSupport.firePropertyChange("donotaccount", oldDonotaccount, donotaccount);
    }
    
    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        char oldsign = this.sign;
        this.sign = sign;
        changeSupport.firePropertyChange("sign", oldsign, sign);
    }
    
    public Integer getSaleschannel_Id() {
        return saleschannel_id;
    }

    public void setSaleschannel_Id(Integer saleschannel_id) {
        Integer oldSaleschannel_Id = this.saleschannel_id;
        this.saleschannel_id = saleschannel_id;
        changeSupport.firePropertyChange("saleschannel_id", oldSaleschannel_Id, saleschannel_id);
    }
    
        public short getReverse_charge() {
        return reverse_charge;
    }

    public void setReverse_charge(short reverse_charge) {
        short oldReverse_charge = this.reverse_charge;
        this.reverse_charge = reverse_charge;
        changeSupport.firePropertyChange("reverse_charge", oldReverse_charge, reverse_charge);
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
        if (!(object instanceof BuhaFilter)) {
            return false;
        }
        BuhaFilter other = (BuhaFilter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaFilter[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
