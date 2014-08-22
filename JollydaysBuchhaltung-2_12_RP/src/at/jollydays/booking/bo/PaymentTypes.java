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
@Table(name = "PaymentTypes")
@NamedQueries({
    @NamedQuery(name = "PaymentTypes.findAll", query = "SELECT p FROM PaymentTypes p"),
    @NamedQuery(name = "PaymentTypes.findById", query = "SELECT p FROM PaymentTypes p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentTypes.findByName", query = "SELECT p FROM PaymentTypes p WHERE p.name = :name")
//    @NamedQuery(name = "PaymentTypes.findByNametodisplay", query = "SELECT p FROM PaymentTypes p WHERE p.nametodisplay = :nametodisplay")
})
public class PaymentTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "nametodisplay")
    private String nametodisplay;

    public PaymentTypes() {
    }

    public PaymentTypes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNametodisplay() {
        return nametodisplay;
    }

    public void setNametodisplay(String nametodisplay) {
        this.nametodisplay = nametodisplay;
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
        if (!(object instanceof PaymentTypes)) {
            return false;
        }
        PaymentTypes other = (PaymentTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.PaymentTypes[id=" + id + "]";
    }

}
