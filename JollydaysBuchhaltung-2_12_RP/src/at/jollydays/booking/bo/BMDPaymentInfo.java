/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rpadmin
 */
@Entity
public class BMDPaymentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    private String kontoStr;

    public String getKontoStr() {
        return kontoStr;
    }

    public void setKontoStr(String kontoStr) {
        this.kontoStr = kontoStr;
    }

    public String getBelegStr() {
        return belegStr;
    }

    public void setBelegStr(String belegStr) {
        this.belegStr = belegStr;
    }

    public String getOpBetragStr() {
        return opBetragStr;
    }

    public void setOpBetragStr(String opBetragStr) {
        this.opBetragStr = opBetragStr;
    }

    public double getBetragNr() {
        return betragNr;
    }

    public void setBetragNr(double betragNr) {
        this.betragNr = betragNr;
    }

    public int getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(int kontoNr) {
        this.kontoNr = kontoNr;
    }

    public int getBelegNr() {
        return belegNr;
    }

    public void setBelegNr(int belegNr) {
        this.belegNr = belegNr;
    }
    private String belegStr;
    private String opBetragStr;
    private double betragNr;
    private int kontoNr;
    private int belegNr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(object instanceof BMDPaymentInfo)) {
            return false;
        }
        BMDPaymentInfo other = (BMDPaymentInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.BMDPaymentInfo[ id=" + id + " ]";
    }
    
}
