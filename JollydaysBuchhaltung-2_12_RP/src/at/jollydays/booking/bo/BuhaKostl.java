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
@Table(name = "buha_kostl")
@NamedQueries({
    @NamedQuery(name = "BuhaKostl.findAll", query = "SELECT b FROM BuhaKostl b"),
    @NamedQuery(name = "BuhaKostl.findById", query = "SELECT b FROM BuhaKostl b WHERE b.id = :id")
//    @NamedQuery(name = "BuhaKostl.findByChannel", query = "SELECT b FROM BuhaKostl b WHERE b.channel = :channel"),
//    @NamedQuery(name = "BuhaKostl.findByCountry", query = "SELECT b FROM BuhaKostl b WHERE b.country = :country"),
//    @NamedQuery(name = "BuhaKostl.findByKostl", query = "SELECT b FROM BuhaKostl b WHERE b.kostl = :kostl"),
//    @NamedQuery(name = "BuhaKostl.findByDescription", query = "SELECT b FROM BuhaKostl b WHERE b.description = :description")
})
public class BuhaKostl implements Serializable, Comparable<BuhaKostl> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "channel")
    private int channel;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "kostl")
    private int kostl;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public BuhaKostl() {
    }

    public BuhaKostl(Integer id) {
        this.id = id;
    }

    public BuhaKostl(Integer id, int channel, String country, int kostl, String description) {
        this.id = id;
        this.channel = channel;
        this.country = country;
        this.kostl = kostl;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getKostl() {
        return kostl;
    }

    public void setKostl(int kostl) {
        this.kostl = kostl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof BuhaKostl)) {
            return false;
        }
        BuhaKostl other = (BuhaKostl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaKostl[id=" + id + "]";
    }


    @Override
    public int compareTo(BuhaKostl o) {
        return - this.getCountry().compareTo(o.getCountry());
    }

}
