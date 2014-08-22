/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "EP_ES_conversion")
@NamedQueries({
    @NamedQuery(name = "EPESconversion.findAll", query = "SELECT e FROM EPESconversion e"),
    @NamedQuery(name = "EPESconversion.findById", query = "SELECT e FROM EPESconversion e WHERE e.id = :id")
//    @NamedQuery(name = "EPESconversion.findByNumber", query = "SELECT e FROM EPESconversion e WHERE e.number = :number"),
//    @NamedQuery(name = "EPESconversion.findByCorespond", query = "SELECT e FROM EPESconversion e WHERE e.corespond = :corespond"),
//    @NamedQuery(name = "EPESconversion.findByUsed", query = "SELECT e FROM EPESconversion e WHERE e.used = :used"),
//    @NamedQuery(name = "EPESconversion.findByCounter", query = "SELECT e FROM EPESconversion e WHERE e.counter = :counter"),
//    @NamedQuery(name = "EPESconversion.findByType", query = "SELECT e FROM EPESconversion e WHERE e.type = :type"),
//    @NamedQuery(name = "EPESconversion.findByTime", query = "SELECT e FROM EPESconversion e WHERE e.time = :time"),
//    @NamedQuery(name = "EPESconversion.findByNoOfTries", query = "SELECT e FROM EPESconversion e WHERE e.noOfTries = :noOfTries")
})
public class EPESconversion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    private String number;
    @Column(name = "corespond")
    private String corespond;
    @Column(name = "used")
    private Boolean used;
    @Column(name = "counter")
    private Integer counter;
    @Column(name = "type")
    private Boolean type;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "no_of_tries")
    private Integer noOfTries;

    public EPESconversion() {
    }

    public EPESconversion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCorespond() {
        return corespond;
    }

    public void setCorespond(String corespond) {
        this.corespond = corespond;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getNoOfTries() {
        return noOfTries;
    }

    public void setNoOfTries(Integer noOfTries) {
        this.noOfTries = noOfTries;
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
        if (!(object instanceof EPESconversion)) {
            return false;
        }
        EPESconversion other = (EPESconversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.EPESconversion[id=" + id + "]";
    }

}
