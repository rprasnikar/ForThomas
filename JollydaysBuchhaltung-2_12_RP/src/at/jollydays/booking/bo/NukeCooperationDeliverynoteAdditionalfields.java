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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpadmin
 */
@Entity
@Table(name = "nuke_cooperation_deliverynote_additionalfields")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findAll", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsInvoice", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsInvoice = :nukeCooperatiodeliverynoteadditionalfieldsInvoice"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference1", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference1 = :nukeCooperatiodeliverynoteadditionalfieldsReference1"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference2", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference2 = :nukeCooperatiodeliverynoteadditionalfieldsReference2"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference3", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference3 = :nukeCooperatiodeliverynoteadditionalfieldsReference3"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference4", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference4 = :nukeCooperatiodeliverynoteadditionalfieldsReference4"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference5", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference5 = :nukeCooperatiodeliverynoteadditionalfieldsReference5"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsReference6", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsReference6 = :nukeCooperatiodeliverynoteadditionalfieldsReference6"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod = :nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsPeriod", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsPeriod = :nukeCooperatiodeliverynoteadditionalfieldsPeriod"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsOrderdate", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsOrderdate = :nukeCooperatiodeliverynoteadditionalfieldsOrderdate"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsProject", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsProject = :nukeCooperatiodeliverynoteadditionalfieldsProject"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry = :nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry"),
    @NamedQuery(name = "NukeCooperationDeliverynoteAdditionalfields.findByNukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto", query = "SELECT n FROM NukeCooperationDeliverynoteAdditionalfields n WHERE n.nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto = :nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto")})
public class NukeCooperationDeliverynoteAdditionalfields implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_invoice")
    private Integer nukeCooperatiodeliverynoteadditionalfieldsInvoice;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference1")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference1;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference2")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference2;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference3")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference3;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference4")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference4;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference5")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference5;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_reference6")
    private String nukeCooperatiodeliverynoteadditionalfieldsReference6;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_invoicingperiod")
    private String nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_period")
    private String nukeCooperatiodeliverynoteadditionalfieldsPeriod;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_orderdate")
    private String nukeCooperatiodeliverynoteadditionalfieldsOrderdate;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_project")
    private String nukeCooperatiodeliverynoteadditionalfieldsProject;
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_invoicefromcountry")
    private String nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry;
    @Basic(optional = false)
    @Column(name = "nuke_cooperatiodeliverynoteadditionalfields_provision_is_brutto")
    private short nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto;

    public NukeCooperationDeliverynoteAdditionalfields() {
    }

    public NukeCooperationDeliverynoteAdditionalfields(Integer nukeCooperatiodeliverynoteadditionalfieldsInvoice) {
        this.nukeCooperatiodeliverynoteadditionalfieldsInvoice = nukeCooperatiodeliverynoteadditionalfieldsInvoice;
    }

    public NukeCooperationDeliverynoteAdditionalfields(Integer nukeCooperatiodeliverynoteadditionalfieldsInvoice, short nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto) {
        this.nukeCooperatiodeliverynoteadditionalfieldsInvoice = nukeCooperatiodeliverynoteadditionalfieldsInvoice;
        this.nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto = nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto;
    }

    public Integer getNukeCooperatiodeliverynoteadditionalfieldsInvoice() {
        return nukeCooperatiodeliverynoteadditionalfieldsInvoice;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsInvoice(Integer nukeCooperatiodeliverynoteadditionalfieldsInvoice) {
        this.nukeCooperatiodeliverynoteadditionalfieldsInvoice = nukeCooperatiodeliverynoteadditionalfieldsInvoice;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference1() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference1;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference1(String nukeCooperatiodeliverynoteadditionalfieldsReference1) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference1 = nukeCooperatiodeliverynoteadditionalfieldsReference1;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference2() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference2;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference2(String nukeCooperatiodeliverynoteadditionalfieldsReference2) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference2 = nukeCooperatiodeliverynoteadditionalfieldsReference2;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference3() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference3;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference3(String nukeCooperatiodeliverynoteadditionalfieldsReference3) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference3 = nukeCooperatiodeliverynoteadditionalfieldsReference3;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference4() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference4;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference4(String nukeCooperatiodeliverynoteadditionalfieldsReference4) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference4 = nukeCooperatiodeliverynoteadditionalfieldsReference4;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference5() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference5;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference5(String nukeCooperatiodeliverynoteadditionalfieldsReference5) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference5 = nukeCooperatiodeliverynoteadditionalfieldsReference5;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsReference6() {
        return nukeCooperatiodeliverynoteadditionalfieldsReference6;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsReference6(String nukeCooperatiodeliverynoteadditionalfieldsReference6) {
        this.nukeCooperatiodeliverynoteadditionalfieldsReference6 = nukeCooperatiodeliverynoteadditionalfieldsReference6;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod() {
        return nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod(String nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod) {
        this.nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod = nukeCooperatiodeliverynoteadditionalfieldsInvoicingperiod;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsPeriod() {
        return nukeCooperatiodeliverynoteadditionalfieldsPeriod;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsPeriod(String nukeCooperatiodeliverynoteadditionalfieldsPeriod) {
        this.nukeCooperatiodeliverynoteadditionalfieldsPeriod = nukeCooperatiodeliverynoteadditionalfieldsPeriod;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsOrderdate() {
        return nukeCooperatiodeliverynoteadditionalfieldsOrderdate;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsOrderdate(String nukeCooperatiodeliverynoteadditionalfieldsOrderdate) {
        this.nukeCooperatiodeliverynoteadditionalfieldsOrderdate = nukeCooperatiodeliverynoteadditionalfieldsOrderdate;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsProject() {
        return nukeCooperatiodeliverynoteadditionalfieldsProject;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsProject(String nukeCooperatiodeliverynoteadditionalfieldsProject) {
        this.nukeCooperatiodeliverynoteadditionalfieldsProject = nukeCooperatiodeliverynoteadditionalfieldsProject;
    }

    public String getNukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry() {
        return nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry(String nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry) {
        this.nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry = nukeCooperatiodeliverynoteadditionalfieldsInvoicefromcountry;
    }

    public short getNukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto() {
        return nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto;
    }

    public void setNukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto(short nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto) {
        this.nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto = nukeCooperatiodeliverynoteadditionalfieldsProvisionIsBrutto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeCooperatiodeliverynoteadditionalfieldsInvoice != null ? nukeCooperatiodeliverynoteadditionalfieldsInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeCooperationDeliverynoteAdditionalfields)) {
            return false;
        }
        NukeCooperationDeliverynoteAdditionalfields other = (NukeCooperationDeliverynoteAdditionalfields) object;
        if ((this.nukeCooperatiodeliverynoteadditionalfieldsInvoice == null && other.nukeCooperatiodeliverynoteadditionalfieldsInvoice != null) || (this.nukeCooperatiodeliverynoteadditionalfieldsInvoice != null && !this.nukeCooperatiodeliverynoteadditionalfieldsInvoice.equals(other.nukeCooperatiodeliverynoteadditionalfieldsInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeCooperationDeliverynoteAdditionalfields[ nukeCooperatiodeliverynoteadditionalfieldsInvoice=" + nukeCooperatiodeliverynoteadditionalfieldsInvoice + " ]";
    }
    
}
