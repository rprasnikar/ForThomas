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
@Table(name = "buha_test")
@NamedQueries({
    @NamedQuery(name = "BuhaTest.findAll", query = "SELECT b FROM BuhaTest b"),
    @NamedQuery(name = "BuhaTest.findById", query = "SELECT b FROM BuhaTest b WHERE b.id = :id"),
    @NamedQuery(name = "BuhaTest.findByBuhaTestcol", query = "SELECT b FROM BuhaTest b WHERE b.buhaTestcol = :buhaTestcol")})
public class BuhaTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "buha_testcol")
    private String buhaTestcol;

    public BuhaTest() {
    }

    public BuhaTest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuhaTestcol() {
        return buhaTestcol;
    }

    public void setBuhaTestcol(String buhaTestcol) {
        this.buhaTestcol = buhaTestcol;
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
        if (!(object instanceof BuhaTest)) {
            return false;
        }
        BuhaTest other = (BuhaTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BuhaTest[id=" + id + "]";
    }

}
