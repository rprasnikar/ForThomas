/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
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
@Table(name = "buha_rabatt")
@NamedQueries({
    @NamedQuery(name = "BuhaRabatt.findAll", query = "SELECT b FROM BuhaRabatt b"),
    @NamedQuery(name = "BuhaRabatt.findById", query = "SELECT b FROM BuhaRabatt b WHERE b.id = :id")
//    @NamedQuery(name = "BuhaRabatt.findByItem", query = "SELECT b FROM BuhaRabatt b WHERE b.item = :item"),
//    @NamedQuery(name = "BuhaRabatt.findByKonto", query = "SELECT b FROM BuhaRabatt b WHERE b.konto = :konto"),
//    @NamedQuery(name = "BuhaRabatt.findByZwischenkonto", query = "SELECT b FROM BuhaRabatt b WHERE b.zwischenkonto = :zwischenkonto"),
//    @NamedQuery(name = "BuhaRabatt.findByGegenkonto", query = "SELECT b FROM BuhaRabatt b WHERE b.gegenkonto = :gegenkonto"),
//    @NamedQuery(name = "BuhaRabatt.findByDescription", query = "SELECT b FROM BuhaRabatt b WHERE b.description = :description"),
//    @NamedQuery(name = "BuhaRabatt.findByBuhaLand", query = "SELECT b FROM BuhaRabatt b WHERE b.buhaLand = :buhaLand"),
//    @NamedQuery(name = "BuhaRabatt.findByWebLand", query = "SELECT b FROM BuhaRabatt b WHERE b.webLand = :webLand"),
//    @NamedQuery(name = "BuhaRabatt.findByGutscheinfilter", query = "SELECT b FROM BuhaRabatt b WHERE b.gutscheinfilter = :gutscheinfilter"),
//    @NamedQuery(name = "BuhaRabatt.findByBesorger", query = "SELECT b FROM BuhaRabatt b WHERE b.besorger = :besorger"),
//    @NamedQuery(name = "BuhaRabatt.findByTaxrate", query = "SELECT b FROM BuhaRabatt b WHERE b.taxrate = :taxrate"),
//    @NamedQuery(name = "BuhaRabatt.findByInvoicetype", query = "SELECT b FROM BuhaRabatt b WHERE b.invoicetype = :invoicetype"),
//    @NamedQuery(name = "BuhaRabatt.findByPaymenttype", query = "SELECT b FROM BuhaRabatt b WHERE b.paymenttype = :paymenttype"),
//    @NamedQuery(name = "BuhaRabatt.findByBillingtype", query = "SELECT b FROM BuhaRabatt b WHERE b.billingtype = :billingtype"),
//    @NamedQuery(name = "BuhaRabatt.findByCategory", query = "SELECT b FROM BuhaRabatt b WHERE b.category = :category")
})
public class BuhaRabatt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "item")
    private int item;
    @Basic(optional = false)
    @Column(name = "konto")
    private int konto;
    @Basic(optional = false)
    @Column(name = "zwischenkonto")
    private int zwischenkonto;
    @Basic(optional = false)
    @Column(name = "gegenkonto")
    private int gegenkonto;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "buha_land")
    private short buhaLand;
    @Basic(optional = false)
    @Column(name = "web_land")
    private String webLand;
    @Basic(optional = false)
    @Column(name = "gutscheinfilter")
    private String gutscheinfilter;
    @Basic(optional = false)
    @Column(name = "besorger")
    private boolean besorger;
    @Basic(optional = false)
    @Column(name = "taxrate")
    private float taxrate;
    @Basic(optional = false)
    @Column(name = "invoicetype")
    private String invoicetype;
    @Basic(optional = false)
    @Column(name = "paymenttype")
    private short paymenttype;
    @Basic(optional = false)
    @Column(name = "billingtype")
    private String billingtype;
    @Basic(optional = false)
    @Column(name = "category")
    private short category;

    public BuhaRabatt() {
    }

    public BuhaRabatt(Integer id) {
        this.id = id;
    }

    public BuhaRabatt(Integer id, int item, int konto, int zwischenkonto, int gegenkonto, String description, short buhaLand, String webLand, String gutscheinfilter, boolean besorger, float taxrate, String invoicetype, short paymenttype, String billingtype, short category) {
        this.id = id;
        this.item = item;
        this.konto = konto;
        this.zwischenkonto = zwischenkonto;
        this.gegenkonto = gegenkonto;
        this.description = description;
        this.buhaLand = buhaLand;
        this.webLand = webLand;
        this.gutscheinfilter = gutscheinfilter;
        this.besorger = besorger;
        this.taxrate = taxrate;
        this.invoicetype = invoicetype;
        this.paymenttype = paymenttype;
        this.billingtype = billingtype;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getKonto() {
        return konto;
    }

    public void setKonto(int konto) {
        this.konto = konto;
    }

    public int getZwischenkonto() {
        return zwischenkonto;
    }

    public void setZwischenkonto(int zwischenkonto) {
        this.zwischenkonto = zwischenkonto;
    }

    public int getGegenkonto() {
        return gegenkonto;
    }

    public void setGegenkonto(int gegenkonto) {
        this.gegenkonto = gegenkonto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getBuhaLand() {
        return buhaLand;
    }

    public void setBuhaLand(short buhaLand) {
        this.buhaLand = buhaLand;
    }

    public String getWebLand() {
        return webLand;
    }

    public void setWebLand(String webLand) {
        this.webLand = webLand;
    }

    public String getGutscheinfilter() {
        return gutscheinfilter;
    }

    public void setGutscheinfilter(String gutscheinfilter) {
        this.gutscheinfilter = gutscheinfilter;
    }

    public boolean getBesorger() {
        return besorger;
    }

    public void setBesorger(boolean besorger) {
        this.besorger = besorger;
    }

    public float getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(float taxrate) {
        this.taxrate = taxrate;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }

    public short getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(short paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(String billingtype) {
        this.billingtype = billingtype;
    }

    public short getCategory() {
        return category;
    }

    public void setCategory(short category) {
        this.category = category;
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
        if (!(object instanceof BuhaRabatt)) {
            return false;
        }
        BuhaRabatt other = (BuhaRabatt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaRabatt[id=" + id + "]";
    }

}
