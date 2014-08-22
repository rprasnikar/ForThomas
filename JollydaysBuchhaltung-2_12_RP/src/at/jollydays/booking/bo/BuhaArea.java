/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "buha_area")
//@SecondaryTable(name = "buha_booking", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_area"))
@NamedQueries({
    @NamedQuery(name = "BuhaArea.findAll", query = "SELECT b FROM BuhaArea b"),
//    @NamedQuery(name = "BuhaArea.findAllJoin", query = "SELECT b FROM BuhaArea b JOIN b.buhaBookingCollection c JOIN c.buhaFilterCollection d"),
    @NamedQuery(name = "BuhaArea.findById", query = "SELECT b FROM BuhaArea b WHERE b.id = :id")
//    @NamedQuery(name = "BuhaArea.findByDescription", query = "SELECT b FROM BuhaArea b WHERE b.description = :description")
})
public class BuhaArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buhaArea")
    private Collection<BuhaBooking> buhaBookingCollection;

    public BuhaArea() {
    }

    public BuhaArea(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<BuhaBooking> getBuhaBookingCollection() {
        Collections.sort((List) buhaBookingCollection);
        return buhaBookingCollection;
    }

//    public List<BuhaBooking> getBuhaBookingList() {
//        Collections.sort((List) buhaBookingCollection);
//        return (List<BuhaBooking>) buhaBookingCollection;
//    }

    public void setBuhaBookingCollection(Collection<BuhaBooking> buhaBookingCollection) {
        this.buhaBookingCollection = buhaBookingCollection;
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
        if (!(object instanceof BuhaArea)) {
            return false;
        }
        BuhaArea other = (BuhaArea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaArea[id=" + id + "]";
    }

}
