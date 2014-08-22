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
@Table(name = "v_nuke_cooperation_deliverynote_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VNukeCooperationDeliverynoteDetails.findAll", query = "SELECT v FROM VNukeCooperationDeliverynoteDetails v"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteDetails.findByNukeCooperationId", query = "SELECT v FROM VNukeCooperationDeliverynoteDetails v WHERE v.nukeCooperationId = :nukeCooperationId"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteDetails.findByNukeCooperationProvisionIsBrutto", query = "SELECT v FROM VNukeCooperationDeliverynoteDetails v WHERE v.nukeCooperationProvisionIsBrutto = :nukeCooperationProvisionIsBrutto"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteDetails.findByNukeCooperationuserBmdId", query = "SELECT v FROM VNukeCooperationDeliverynoteDetails v WHERE v.nukeCooperationuserBmdId = :nukeCooperationuserBmdId"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteDetails.findByNukeCooperationuserAccountterms", query = "SELECT v FROM VNukeCooperationDeliverynoteDetails v WHERE v.nukeCooperationuserAccountterms = :nukeCooperationuserAccountterms")})
public class VNukeCooperationDeliverynoteDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_id")
    @Id
    private int nukeCooperationId;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_provision_is_brutto")
    private short nukeCooperationProvisionIsBrutto;
    @Column(name = "nuke_cooperationuser_bmd_id")
    private String nukeCooperationuserBmdId;
    @Column(name = "nuke_cooperationuser_accountterms")
    private String nukeCooperationuserAccountterms;
    @Column(name = "nuke_cooperationuser_isostate")
    private String nukeCooperationuserIsostate;

    public VNukeCooperationDeliverynoteDetails() {
    }

    public int getNukeCooperationId() {
        return nukeCooperationId;
    }

    public void setNukeCooperationId(int nukeCooperationId) {
        this.nukeCooperationId = nukeCooperationId;
    }

    public short getNukeCooperationProvisionIsBrutto() {
        return nukeCooperationProvisionIsBrutto;
    }

    public void setNukeCooperationProvisionIsBrutto(short nukeCooperationProvisionIsBrutto) {
        this.nukeCooperationProvisionIsBrutto = nukeCooperationProvisionIsBrutto;
    }

    public String getNukeCooperationuserBmdId() {
        return nukeCooperationuserBmdId;
    }

    public void setNukeCooperationuserBmdId(String nukeCooperationuserBmdId) {
        this.nukeCooperationuserBmdId = nukeCooperationuserBmdId;
    }

    public String getNukeCooperationuserAccountterms() {
        return nukeCooperationuserAccountterms;
    }

    public void setNukeCooperationuserAccountterms(String nukeCooperationuserAccountterms) {
        this.nukeCooperationuserAccountterms = nukeCooperationuserAccountterms;
    }
    
    public String getNukeCooperationuserIsostate() {
        return nukeCooperationuserIsostate;
    }

    public void setNukeCooperationuserIsostate(String nukeCooperationuserIsostate) {
        this.nukeCooperationuserIsostate = nukeCooperationuserIsostate;
    }
    

    
}
