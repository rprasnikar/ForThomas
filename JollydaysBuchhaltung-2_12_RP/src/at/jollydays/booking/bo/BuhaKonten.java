/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "buha_konten")
@NamedQueries({
    @NamedQuery(name = "BuhaKonten.findAll", query = "SELECT b FROM BuhaKonten b"),
    @NamedQuery(name = "BuhaKonten.findByKontonummer", query = "SELECT b FROM BuhaKonten b WHERE b.kontonummer = :kontonummer")
//    @NamedQuery(name = "BuhaKonten.findByDescription", query = "SELECT b FROM BuhaKonten b WHERE b.description = :description")
})
public class BuhaKonten implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kontonummer")
    private Integer kontonummer;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public BuhaKonten() {
    }

    public BuhaKonten(Integer kontonummer) {
        this.kontonummer = kontonummer;
    }

    public BuhaKonten(Integer kontonummer, String description) {
        this.kontonummer = kontonummer;
        this.description = description;
    }

    public Integer getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(Integer kontonummer) {
        this.kontonummer = kontonummer;
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
        hash += (kontonummer != null ? kontonummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuhaKonten)) {
            return false;
        }
        BuhaKonten other = (BuhaKonten) object;
        if ((this.kontonummer == null && other.kontonummer != null) || (this.kontonummer != null && !this.kontonummer.equals(other.kontonummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaKonten[kontonummer=" + kontonummer + "]";
    }

}
