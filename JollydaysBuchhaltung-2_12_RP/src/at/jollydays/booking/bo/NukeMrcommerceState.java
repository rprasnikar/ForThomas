/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_state")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceState.findAll", query = "SELECT n FROM NukeMrcommerceState n"),
    @NamedQuery(name = "NukeMrcommerceState.findById", query = "SELECT n FROM NukeMrcommerceState n WHERE n.id = :id"),
    @NamedQuery(name = "NukeMrcommerceState.findByCountry", query = "SELECT n FROM NukeMrcommerceState n WHERE n.country = :country"),
    @NamedQuery(name = "NukeMrcommerceState.findByName", query = "SELECT n FROM NukeMrcommerceState n WHERE n.name = :name")})
public class NukeMrcommerceState implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "country")
    private String country;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "nukeMrcommerceState")
    private Collection<NukeMrcommerceAddress> nukeMrcommerceAddressCollection;

    public NukeMrcommerceState() {
    }

    public NukeMrcommerceState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<NukeMrcommerceAddress> getNukeMrcommerceAddressCollection() {
        return nukeMrcommerceAddressCollection;
    }

    public void setNukeMrcommerceAddressCollection(Collection<NukeMrcommerceAddress> nukeMrcommerceAddressCollection) {
        this.nukeMrcommerceAddressCollection = nukeMrcommerceAddressCollection;
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
        if (!(object instanceof NukeMrcommerceState)) {
            return false;
        }
        NukeMrcommerceState other = (NukeMrcommerceState) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceState[id=" + id + "]";
    }

}
