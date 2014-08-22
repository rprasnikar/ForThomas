/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gunter Reinitzer
 */
@Embeddable
public class NukeCooperationInvoiceDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nuke_cooperationinvoice_cooperation")
    private int nukeCooperationinvoiceCooperation;
    @Basic(optional = false)
    @Column(name = "nuke_cooperationinvoice_invoice")
    private int nukeCooperationinvoiceInvoice;

    public NukeCooperationInvoiceDetailsPK() {
    }

    public NukeCooperationInvoiceDetailsPK(int nukeCooperationinvoiceCooperation, int nukeCooperationinvoiceInvoice) {
        this.nukeCooperationinvoiceCooperation = nukeCooperationinvoiceCooperation;
        this.nukeCooperationinvoiceInvoice = nukeCooperationinvoiceInvoice;
    }

    public int getNukeCooperationinvoiceCooperation() {
        return nukeCooperationinvoiceCooperation;
    }

    public void setNukeCooperationinvoiceCooperation(int nukeCooperationinvoiceCooperation) {
        this.nukeCooperationinvoiceCooperation = nukeCooperationinvoiceCooperation;
    }

    public int getNukeCooperationinvoiceInvoice() {
        return nukeCooperationinvoiceInvoice;
    }

    public void setNukeCooperationinvoiceInvoice(int nukeCooperationinvoiceInvoice) {
        this.nukeCooperationinvoiceInvoice = nukeCooperationinvoiceInvoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nukeCooperationinvoiceCooperation;
        hash += (int) nukeCooperationinvoiceInvoice;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeCooperationInvoiceDetailsPK)) {
            return false;
        }
        NukeCooperationInvoiceDetailsPK other = (NukeCooperationInvoiceDetailsPK) object;
        if (this.nukeCooperationinvoiceCooperation != other.nukeCooperationinvoiceCooperation) {
            return false;
        }
        if (this.nukeCooperationinvoiceInvoice != other.nukeCooperationinvoiceInvoice) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeCooperationInvoiceDetailsPK[nukeCooperationinvoiceCooperation=" + nukeCooperationinvoiceCooperation + ", nukeCooperationinvoiceInvoice=" + nukeCooperationinvoiceInvoice + "]";
    }

}
