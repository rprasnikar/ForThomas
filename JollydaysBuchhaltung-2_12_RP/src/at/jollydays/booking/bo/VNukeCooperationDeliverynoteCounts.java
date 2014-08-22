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
@Table(name = "v_nuke_cooperation_deliverynote_counts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findAll", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByUuid", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.uuid = :uuid"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByAnzahl", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.anzahl = :anzahl"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByMrcommerceItemID", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.mrcommerceItemID = :mrcommerceItemID"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByNukeCooperatiodeliverynoteaccountInvoice", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.nukeCooperatiodeliverynoteaccountInvoice = :nukeCooperatiodeliverynoteaccountInvoice"),
    @NamedQuery(name = "VNukeCooperationDeliverynoteCounts.findByInvoiceAndMrcommerceItemID", query = "SELECT v FROM VNukeCooperationDeliverynoteCounts v WHERE v.nukeCooperatiodeliverynoteaccountInvoice = :nukeCooperatiodeliverynoteaccountInvoice and v.mrcommerceItemID = :mrcommerceItemID")})
public class VNukeCooperationDeliverynoteCounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "UUID()")
    @Id
    private String uuid;
    @Basic(optional = false)
    @Column(name = "anzahl")
    private long anzahl;
    @Column(name = "Mrcommerce_Item_ID")
    private Integer mrcommerceItemID;
    @Column(name = "nuke_cooperatiodeliverynoteaccount_invoice")
    private Integer nukeCooperatiodeliverynoteaccountInvoice;

    public VNukeCooperationDeliverynoteCounts() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(long anzahl) {
        this.anzahl = anzahl;
    }

    public Integer getMrcommerceItemID() {
        return mrcommerceItemID;
    }

    public void setMrcommerceItemID(Integer mrcommerceItemID) {
        this.mrcommerceItemID = mrcommerceItemID;
    }

    public Integer getNukeCooperatiodeliverynoteaccountInvoice() {
        return nukeCooperatiodeliverynoteaccountInvoice;
    }

    public void setNukeCooperatiodeliverynoteaccountInvoice(Integer nukeCooperatiodeliverynoteaccountInvoice) {
        this.nukeCooperatiodeliverynoteaccountInvoice = nukeCooperatiodeliverynoteaccountInvoice;
    }
    
}
