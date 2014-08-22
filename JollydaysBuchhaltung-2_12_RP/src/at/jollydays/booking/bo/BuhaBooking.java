/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "buha_booking")
@NamedQueries({
    @NamedQuery(name = "BuhaBooking.findAll", query = "SELECT b FROM BuhaBooking b")
//    @NamedQuery(name = "BuhaBooking.findById", query = "SELECT b FROM BuhaBooking b WHERE b.id = :id"),
//    @NamedQuery(name = "BuhaBooking.findBySortId", query = "SELECT b FROM BuhaBooking b WHERE b.sortId = :sortId"),
//    @NamedQuery(name = "BuhaBooking.findByKonto", query = "SELECT b FROM BuhaBooking b WHERE b.konto = :konto"),
//    @NamedQuery(name = "BuhaBooking.findByZwischenkonto", query = "SELECT b FROM BuhaBooking b WHERE b.zwischenkonto = :zwischenkonto"),
//    @NamedQuery(name = "BuhaBooking.findByGegenkonto", query = "SELECT b FROM BuhaBooking b WHERE b.gegenkonto = :gegenkonto"),
//    @NamedQuery(name = "BuhaBooking.findByDebitor", query = "SELECT b FROM BuhaBooking b WHERE b.debitor = :debitor"),
//    @NamedQuery(name = "BuhaBooking.findByCreditor", query = "SELECT b FROM BuhaBooking b WHERE b.creditor = :creditor"),
//    @NamedQuery(name = "BuhaBooking.findByBuchungscode", query = "SELECT b FROM BuhaBooking b WHERE b.buchungscode = :buchungscode"),
//    @NamedQuery(name = "BuhaBooking.findBySteuercode", query = "SELECT b FROM BuhaBooking b WHERE b.steuercode = :steuercode"),
//    @NamedQuery(name = "BuhaBooking.findByBuhaCountry", query = "SELECT b FROM BuhaBooking b WHERE b.buhaCountry = :buhaCountry"),
//    @NamedQuery(name = "BuhaBooking.findByDescription", query = "SELECT b FROM BuhaBooking b WHERE b.description = :description")
})
public class BuhaBooking implements Serializable, Comparable<BuhaBooking> {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "sortid")
    private int sortId;
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
    @Column(name = "debitor")
    private boolean debitor;
    @Basic(optional = false)
    @Column(name = "creditor")
    private boolean creditor;
    @Basic(optional = false)
    @Column(name = "buchungscode")
    private short buchungscode;
    @Basic(optional = false)
    @Column(name = "steuercode")
    private int steuercode;
    @Basic(optional = false)
    @Column(name = "buha_country")
    private String buhaCountry;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BuhaArea buhaArea;
    @Basic(optional = false)
    @Column(name = "negativbuchen")
    private boolean negativbuchen;
    @Basic(optional = false)
    @Column(name = "steuerignorieren")
    private boolean steuerignorieren;
    @Basic(optional = false)
    @Column(name = "steuernegativ")
    private boolean steuernegativ;
    @Basic(optional = false)
    @Column(name = "betragnetto")
    private boolean betragnetto;
    @Basic(optional = false)
    @Column(name = "nettoumrechnung")
    private char nettoumrechnung = ' ';
    @Basic(optional = false)
    @Column(name = "buchungscode2")
    private short buchungscode2 = 0;
    @Basic(optional = false)
    @Column(name = "splitbuchung")
    private boolean splitbuchung;    
    @Basic(optional = false)
    @Column(name = "forcevermittler")
    private boolean forcevermittler;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buhaBooking")
    private Collection<BuhaFilter> buhaFilterCollection;

    public BuhaBooking() {
    }

    public BuhaBooking(Integer id) {
        this.id = id;
    }

    public BuhaBooking(Integer id, int sortId, int konto, int zwischenkonto, int gegenkonto, boolean debitor, boolean creditor, short buchungscode, int steuercode, String buhaCountry, String description) {
        this.id = id;
        this.sortId = sortId;
        this.konto = konto;
        this.zwischenkonto = zwischenkonto;
        this.gegenkonto = gegenkonto;
        this.debitor = debitor;
        this.creditor = creditor;
        this.buchungscode = buchungscode;
        this.steuercode = steuercode;
        this.buhaCountry = buhaCountry;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        int oldSortId = this.sortId;
        this.sortId = sortId;
        changeSupport.firePropertyChange("sortId", oldSortId, sortId);
    }

    public int getKonto() {
        return konto;
    }

    public void setKonto(int konto) {
        int oldKonto = this.konto;
        this.konto = konto;
        changeSupport.firePropertyChange("konto", oldKonto, konto);
    }

    public int getZwischenkonto() {
        return zwischenkonto;
    }

    public void setZwischenkonto(int zwischenkonto) {
        int oldZwischenkonto = this.zwischenkonto;
        this.zwischenkonto = zwischenkonto;
        changeSupport.firePropertyChange("zwischenkonto", oldZwischenkonto, zwischenkonto);
    }

    public int getGegenkonto() {
        return gegenkonto;
    }

    public void setGegenkonto(int gegenkonto) {
        int oldGegenkonto = this.gegenkonto;
        this.gegenkonto = gegenkonto;
        changeSupport.firePropertyChange("gegenkonto", oldGegenkonto, gegenkonto);
    }

    public boolean getDebitor() {
        return debitor;
    }

    public void setDebitor(boolean debitor) {
        boolean oldDebitor = this.debitor;
        this.debitor = debitor;
        changeSupport.firePropertyChange("debitor", oldDebitor, debitor);
    }

    public boolean getCreditor() {
        return creditor;
    }

    public void setCreditor(boolean creditor) {
        boolean oldCreditor = this.creditor;
        this.creditor = creditor;
        changeSupport.firePropertyChange("creditor", oldCreditor, creditor);
    }

    public short getBuchungscode() {
        return buchungscode;
    }

    public void setBuchungscode(short buchungscode) {
        short oldBuchungscode = this.buchungscode;
        this.buchungscode = buchungscode;
        changeSupport.firePropertyChange("buchungscode", oldBuchungscode, buchungscode);
    }

    public short getBuchungscode2() {
        return buchungscode2;
    }

    public void setBuchungscode2(short buchungscode2) {
        short oldBuchungscode2 = this.buchungscode2;
        this.buchungscode2 = buchungscode2;
        changeSupport.firePropertyChange("buchungscode2", oldBuchungscode2, buchungscode2);
    }

    public int getSteuercode() {
        return steuercode;
    }

    public void setSteuercode(int steuercode) {
        int oldSteuercode = this.steuercode;
        this.steuercode = steuercode;
        changeSupport.firePropertyChange("steuercode", oldSteuercode, steuercode);
    }

    public String getBuhaCountry() {
        return buhaCountry;
    }

    public void setBuhaCountry(String buhaCountry) {
        String oldBuhaCountry = this.buhaCountry;
        this.buhaCountry = buhaCountry;
        changeSupport.firePropertyChange("buhaCountry", oldBuhaCountry, buhaCountry);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public BuhaArea getBuhaArea() {
        return buhaArea;
    }

    public void setBuhaArea(BuhaArea buhaArea) {
        BuhaArea oldBuhaArea = this.buhaArea;
        this.buhaArea = buhaArea;
        changeSupport.firePropertyChange("buhaArea", oldBuhaArea, buhaArea);
    }

    public boolean getNegativBuchen() {
        return negativbuchen;
    }

    public void setNegativBuchen(boolean negativbuchen) {
        boolean oldNegativbuchen = this.negativbuchen;
        this.negativbuchen = negativbuchen;
        changeSupport.firePropertyChange("negativbuchen", oldNegativbuchen, negativbuchen);
    }

    public boolean getSteuerIgnorieren() {
        return steuerignorieren;
    }

    public void setSteuerIgnorieren(boolean steuerignorieren) {
        boolean oldsteuerignorieren = this.steuerignorieren;
        this.steuerignorieren = steuerignorieren;
        changeSupport.firePropertyChange("negativbuchen", oldsteuerignorieren, steuerignorieren);
    }

    public boolean getSteuerNegativ() {
        return steuernegativ;
    }

    public void setSteuerNegativ(boolean steuernegativ) {
        boolean oldsteuernegativ = this.steuernegativ;
        this.steuernegativ = steuernegativ;
        changeSupport.firePropertyChange("steuernegativ", oldsteuernegativ, steuernegativ);
    }
    
    public boolean getForcevermittler() {
        return forcevermittler;
    }

    public void setForcevermittler(boolean forcevermittler) {
        boolean oldforcevermittler = this.forcevermittler;
        this.forcevermittler = forcevermittler;
        changeSupport.firePropertyChange("forcevermittler", oldforcevermittler, forcevermittler);
    }
    
    public boolean getBetragNetto() {
        return betragnetto;
    }

    public void setBetragNetto(boolean betragnetto) {
        boolean oldbetragnetto = this.betragnetto;
        this.betragnetto = betragnetto;
        changeSupport.firePropertyChange("betragnetto", oldbetragnetto, betragnetto);
    }

    public char getNettoUmrechnung() {
        return nettoumrechnung;
    }

    public void setNettoUmrechnung(char nettoumrechnung) {
        char oldnettoumrechnung = this.nettoumrechnung;
        this.nettoumrechnung = nettoumrechnung;
        changeSupport.firePropertyChange("nettoumrechnung", oldnettoumrechnung, nettoumrechnung);
    }

    public boolean getSplitbuchung() {
        return splitbuchung;
    }

    public void setSplitbuchung(boolean splitbuchung) {
        boolean oldsplitbuchung = this.splitbuchung;
        this.splitbuchung = splitbuchung;
        changeSupport.firePropertyChange("splitbuchung", oldsplitbuchung, splitbuchung);
    }

    
    public Collection<BuhaFilter> getBuhaFilterCollection() {
        return buhaFilterCollection;
    }

//    public List<BuhaFilter> getBuhaFilterList() {
//        return (List<BuhaFilter>) buhaFilterCollection;
//    }

    public void setBuhaFilterCollection(Collection<BuhaFilter> buhaFilterCollection) {
        this.buhaFilterCollection = buhaFilterCollection;
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
        if (!(object instanceof BuhaBooking)) {
            return false;
        }
        BuhaBooking other = (BuhaBooking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaBooking[id=" + id + "]";
    }

    @Override
    public int compareTo(BuhaBooking other) {
        if (this.getSortId() < other.getSortId()) {
            return -1;
        } else if (this.getSortId() == other.getSortId()) {
            return 0;
        }
        return 1;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
