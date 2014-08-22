/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_cooperation_invoice_details")
@NamedQueries({
    @NamedQuery(name = "NukeCooperationInvoiceDetails.findAll", query = "SELECT n FROM NukeCooperationInvoiceDetails n"),
    @NamedQuery(name = "NukeCooperationInvoiceDetails.findByNukeCooperationinvoiceCooperation", query = "SELECT n FROM NukeCooperationInvoiceDetails n WHERE n.nukeCooperationInvoiceDetailsPK.nukeCooperationinvoiceCooperation = :nukeCooperationinvoiceCooperation"),
    @NamedQuery(name = "NukeCooperationInvoiceDetails.findByNukeCooperationinvoiceInvoice", query = "SELECT n FROM NukeCooperationInvoiceDetails n WHERE n.nukeCooperationInvoiceDetailsPK.nukeCooperationinvoiceInvoice = :nukeCooperationinvoiceInvoice")
//    @NamedQuery(name = "NukeCooperationInvoiceDetails.findByNukeCooperationinvoiceFirstname", query = "SELECT n FROM NukeCooperationInvoiceDetails n WHERE n.nukeCooperationinvoiceFirstname = :nukeCooperationinvoiceFirstname"),
//    @NamedQuery(name = "NukeCooperationInvoiceDetails.findByNukeCooperationinvoiceLastname", query = "SELECT n FROM NukeCooperationInvoiceDetails n WHERE n.nukeCooperationinvoiceLastname = :nukeCooperationinvoiceLastname")
})
public class NukeCooperationInvoiceDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NukeCooperationInvoiceDetailsPK nukeCooperationInvoiceDetailsPK;
    @Column(name = "nuke_cooperationinvoice_firstname")
    private String nukeCooperationinvoiceFirstname;
    @Column(name = "nuke_cooperationinvoice_lastname")
    private String nukeCooperationinvoiceLastname;
    @Lob
    @Column(name = "nuke_cooperationinvoice_note")
    private String nukeCooperationinvoiceNote;

    public NukeCooperationInvoiceDetails() {
    }

    public NukeCooperationInvoiceDetails(NukeCooperationInvoiceDetailsPK nukeCooperationInvoiceDetailsPK) {
        this.nukeCooperationInvoiceDetailsPK = nukeCooperationInvoiceDetailsPK;
    }

    public NukeCooperationInvoiceDetails(int nukeCooperationinvoiceCooperation, int nukeCooperationinvoiceInvoice) {
        this.nukeCooperationInvoiceDetailsPK = new NukeCooperationInvoiceDetailsPK(nukeCooperationinvoiceCooperation, nukeCooperationinvoiceInvoice);
    }

    public NukeCooperationInvoiceDetailsPK getNukeCooperationInvoiceDetailsPK() {
        return nukeCooperationInvoiceDetailsPK;
    }

    public void setNukeCooperationInvoiceDetailsPK(NukeCooperationInvoiceDetailsPK nukeCooperationInvoiceDetailsPK) {
        this.nukeCooperationInvoiceDetailsPK = nukeCooperationInvoiceDetailsPK;
    }

    public String getNukeCooperationinvoiceFirstname() {
        return nukeCooperationinvoiceFirstname;
    }

    public void setNukeCooperationinvoiceFirstname(String nukeCooperationinvoiceFirstname) {
        this.nukeCooperationinvoiceFirstname = nukeCooperationinvoiceFirstname;
    }

    public String getNukeCooperationinvoiceLastname() {
        return nukeCooperationinvoiceLastname;
    }

    public void setNukeCooperationinvoiceLastname(String nukeCooperationinvoiceLastname) {
        this.nukeCooperationinvoiceLastname = nukeCooperationinvoiceLastname;
    }

    public String getNukeCooperationinvoiceNote() {
        return nukeCooperationinvoiceNote;
    }

    public void setNukeCooperationinvoiceNote(String nukeCooperationinvoiceNote) {
        this.nukeCooperationinvoiceNote = nukeCooperationinvoiceNote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeCooperationInvoiceDetailsPK != null ? nukeCooperationInvoiceDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeCooperationInvoiceDetails)) {
            return false;
        }
        NukeCooperationInvoiceDetails other = (NukeCooperationInvoiceDetails) object;
        if ((this.nukeCooperationInvoiceDetailsPK == null && other.nukeCooperationInvoiceDetailsPK != null) || (this.nukeCooperationInvoiceDetailsPK != null && !this.nukeCooperationInvoiceDetailsPK.equals(other.nukeCooperationInvoiceDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeCooperationInvoiceDetails[nukeCooperationInvoiceDetailsPK=" + nukeCooperationInvoiceDetailsPK + "]";
    }

}
