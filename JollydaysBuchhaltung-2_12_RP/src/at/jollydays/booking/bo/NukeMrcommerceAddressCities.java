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
@Table(name = "nuke_mrcommerce_address_cities")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceAddressCities.findAll", query = "SELECT n FROM NukeMrcommerceAddressCities n"),
    @NamedQuery(name = "NukeMrcommerceAddressCities.findById", query = "SELECT n FROM NukeMrcommerceAddressCities n WHERE n.id = :id"),
    @NamedQuery(name = "NukeMrcommerceAddressCities.findByCountry", query = "SELECT n FROM NukeMrcommerceAddressCities n WHERE n.country = :country"),
    @NamedQuery(name = "NukeMrcommerceAddressCities.findByName", query = "SELECT n FROM NukeMrcommerceAddressCities n WHERE n.name = :name")})
public class NukeMrcommerceAddressCities implements Serializable {
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

    public NukeMrcommerceAddressCities() {
    }

    public NukeMrcommerceAddressCities(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceAddressCities)) {
            return false;
        }
        NukeMrcommerceAddressCities other = (NukeMrcommerceAddressCities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceAddressCities[id=" + id + "]";
    }

}
