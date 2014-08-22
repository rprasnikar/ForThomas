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
@Table(name = "buha_properties")
@NamedQueries({
    @NamedQuery(name = "BuhaProperties.findAll", query = "SELECT b FROM BuhaProperties b"),
    @NamedQuery(name = "BuhaProperties.findById", query = "SELECT b FROM BuhaProperties b WHERE b.id = :id"),
    @NamedQuery(name = "BuhaProperties.findByName", query = "SELECT b FROM BuhaProperties b WHERE b.name = :name"),
    @NamedQuery(name = "BuhaProperties.findByNamePRE", query = "SELECT b FROM BuhaProperties b WHERE b.name like 'PRE%'"),
    @NamedQuery(name = "BuhaProperties.findByNameNotPRE", query = "SELECT b FROM BuhaProperties b WHERE b.name not like 'PRE%'"),
    @NamedQuery(name = "BuhaProperties.findByValue", query = "SELECT b FROM BuhaProperties b WHERE b.value = :value")})
public class BuhaProperties implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;

    public BuhaProperties() {
    }

    public BuhaProperties(Integer id) {
        this.id = id;
    }

    public BuhaProperties(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(object instanceof BuhaProperties)) {
            return false;
        }
        BuhaProperties other = (BuhaProperties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaProperties[id=" + id + "]";
    }

}
