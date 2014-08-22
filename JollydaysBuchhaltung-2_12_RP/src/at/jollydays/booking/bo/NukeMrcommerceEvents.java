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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "nuke_mrcommerce_events")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceEvents.findAll", query = "SELECT n FROM NukeMrcommerceEvents n"),
    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveId", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveId = :nukeEveId")
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveCreationdate", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveCreationdate = :nukeEveCreationdate"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveOrgIdBackup", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveOrgIdBackup = :nukeEveOrgIdBackup"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveArrIdBackup", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveArrIdBackup = :nukeEveArrIdBackup"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveStart", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveStart = :nukeEveStart"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveEnd", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveEnd = :nukeEveEnd"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveBeginTime", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveBeginTime = :nukeEveBeginTime"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveParticipantMin", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveParticipantMin = :nukeEveParticipantMin"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveParticipantMax", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveParticipantMax = :nukeEveParticipantMax"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveParticipantInscribe", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveParticipantInscribe = :nukeEveParticipantInscribe"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveParticipantOption", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveParticipantOption = :nukeEveParticipantOption"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveSpecial", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveSpecial = :nukeEveSpecial"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveStatementOfPlace", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveStatementOfPlace = :nukeEveStatementOfPlace"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveAdrId", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveAdrId = :nukeEveAdrId"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEvePrice", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEvePrice = :nukeEvePrice"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEvePriceText", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEvePriceText = :nukeEvePriceText"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveIscancelled", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveIscancelled = :nukeEveIscancelled"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEvePriceDe", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEvePriceDe = :nukeEvePriceDe"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveShowfullevent", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveShowfullevent = :nukeEveShowfullevent"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveDirectinscription", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveDirectinscription = :nukeEveDirectinscription"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveCreatedfromtakt", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveCreatedfromtakt = :nukeEveCreatedfromtakt"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveCanceltype", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveCanceltype = :nukeEveCanceltype"),
//    @NamedQuery(name = "NukeMrcommerceEvents.findByNukeEveClosebeforedays", query = "SELECT n FROM NukeMrcommerceEvents n WHERE n.nukeEveClosebeforedays = :nukeEveClosebeforedays")
})
public class NukeMrcommerceEvents implements Serializable {
    private static final long serialVersionUID = 1L; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_eve_id")
    private Integer nukeEveId;
    @Column(name = "nuke_eve_creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nukeEveCreationdate;
    @Column(name = "nuke_eve_org_id_backup")
    private Integer nukeEveOrgIdBackup;
    @Column(name = "nuke_eve_arr_id_backup")
    private Integer nukeEveArrIdBackup;
    @Basic(optional = false)
    @Column(name = "nuke_eve_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nukeEveStart;
    @Basic(optional = false)
    @Column(name = "nuke_eve_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nukeEveEnd;
    @Column(name = "nuke_eve_begin_time")
    private String nukeEveBeginTime;
    @Column(name = "nuke_eve_participant_min")
    private Short nukeEveParticipantMin;
    @Column(name = "nuke_eve_participant_max")
    private Short nukeEveParticipantMax;
    @Column(name = "nuke_eve_participant_inscribe")
    private Short nukeEveParticipantInscribe;
    @Basic(optional = false)
    @Column(name = "nuke_eve_participant_option")
    private short nukeEveParticipantOption;
    @Column(name = "nuke_eve_special")
    private Boolean nukeEveSpecial;
    @Column(name = "nuke_eve_statement_of_place")
    private String nukeEveStatementOfPlace;
    @Lob
    @Column(name = "nuke_eve_additional_info")
    private String nukeEveAdditionalInfo;
    @Column(name = "nuke_eve_adr_id")
    private Integer nukeEveAdrId;
    @Column(name = "nuke_eve_price")
    private Long nukeEvePrice;
    @Column(name = "nuke_eve_price_text")
    private String nukeEvePriceText;
    @Column(name = "nuke_eve_iscancelled")
    private Short nukeEveIscancelled;
    @Column(name = "nuke_eve_price_de")
    private Long nukeEvePriceDe;
    @Basic(optional = false)
    @Column(name = "nuke_eve_showfullevent")
    private boolean nukeEveShowfullevent;
    @Basic(optional = false)
    @Column(name = "nuke_eve_directinscription")
    private short nukeEveDirectinscription;
    @Basic(optional = false)
    @Column(name = "nuke_eve_createdfromtakt")
    private boolean nukeEveCreatedfromtakt;
    @Basic(optional = false)
    @Column(name = "nuke_eve_canceltype")
    private short nukeEveCanceltype;
    @Column(name = "nuke_eve_closebeforedays")
    private Integer nukeEveClosebeforedays;
    @JoinColumn(name = "nuke_eve_item_id", referencedColumnName = "nuke_item_id")
    @ManyToOne
    private NukeMrcommerceItems nukeMrcommerceItems;

    public NukeMrcommerceEvents() {
    }

    public NukeMrcommerceEvents(Integer nukeEveId) {
        this.nukeEveId = nukeEveId;
    }

    public NukeMrcommerceEvents(Integer nukeEveId, Date nukeEveStart, Date nukeEveEnd, short nukeEveParticipantOption, boolean nukeEveShowfullevent, short nukeEveDirectinscription, boolean nukeEveCreatedfromtakt, short nukeEveCanceltype) {
        this.nukeEveId = nukeEveId;
        this.nukeEveStart = nukeEveStart;
        this.nukeEveEnd = nukeEveEnd;
        this.nukeEveParticipantOption = nukeEveParticipantOption;
        this.nukeEveShowfullevent = nukeEveShowfullevent;
        this.nukeEveDirectinscription = nukeEveDirectinscription;
        this.nukeEveCreatedfromtakt = nukeEveCreatedfromtakt;
        this.nukeEveCanceltype = nukeEveCanceltype;
    }

    public Integer getNukeEveId() {
        return nukeEveId;
    }

    public void setNukeEveId(Integer nukeEveId) {
        this.nukeEveId = nukeEveId;
    }

    public Date getNukeEveCreationdate() {
        return nukeEveCreationdate;
    }

    public void setNukeEveCreationdate(Date nukeEveCreationdate) {
        this.nukeEveCreationdate = nukeEveCreationdate;
    }

    public Integer getNukeEveOrgIdBackup() {
        return nukeEveOrgIdBackup;
    }

    public void setNukeEveOrgIdBackup(Integer nukeEveOrgIdBackup) {
        this.nukeEveOrgIdBackup = nukeEveOrgIdBackup;
    }

    public Integer getNukeEveArrIdBackup() {
        return nukeEveArrIdBackup;
    }

    public void setNukeEveArrIdBackup(Integer nukeEveArrIdBackup) {
        this.nukeEveArrIdBackup = nukeEveArrIdBackup;
    }

    public Date getNukeEveStart() {
        return nukeEveStart;
    }

    public void setNukeEveStart(Date nukeEveStart) {
        this.nukeEveStart = nukeEveStart;
    }

    public Date getNukeEveEnd() {
        return nukeEveEnd;
    }

    public void setNukeEveEnd(Date nukeEveEnd) {
        this.nukeEveEnd = nukeEveEnd;
    }

    public String getNukeEveBeginTime() {
        return nukeEveBeginTime;
    }

    public void setNukeEveBeginTime(String nukeEveBeginTime) {
        this.nukeEveBeginTime = nukeEveBeginTime;
    }

    public Short getNukeEveParticipantMin() {
        return nukeEveParticipantMin;
    }

    public void setNukeEveParticipantMin(Short nukeEveParticipantMin) {
        this.nukeEveParticipantMin = nukeEveParticipantMin;
    }

    public Short getNukeEveParticipantMax() {
        return nukeEveParticipantMax;
    }

    public void setNukeEveParticipantMax(Short nukeEveParticipantMax) {
        this.nukeEveParticipantMax = nukeEveParticipantMax;
    }

    public Short getNukeEveParticipantInscribe() {
        return nukeEveParticipantInscribe;
    }

    public void setNukeEveParticipantInscribe(Short nukeEveParticipantInscribe) {
        this.nukeEveParticipantInscribe = nukeEveParticipantInscribe;
    }

    public short getNukeEveParticipantOption() {
        return nukeEveParticipantOption;
    }

    public void setNukeEveParticipantOption(short nukeEveParticipantOption) {
        this.nukeEveParticipantOption = nukeEveParticipantOption;
    }

    public Boolean getNukeEveSpecial() {
        return nukeEveSpecial;
    }

    public void setNukeEveSpecial(Boolean nukeEveSpecial) {
        this.nukeEveSpecial = nukeEveSpecial;
    }

    public String getNukeEveStatementOfPlace() {
        return nukeEveStatementOfPlace;
    }

    public void setNukeEveStatementOfPlace(String nukeEveStatementOfPlace) {
        this.nukeEveStatementOfPlace = nukeEveStatementOfPlace;
    }

    public String getNukeEveAdditionalInfo() {
        return nukeEveAdditionalInfo;
    }

    public void setNukeEveAdditionalInfo(String nukeEveAdditionalInfo) {
        this.nukeEveAdditionalInfo = nukeEveAdditionalInfo;
    }

    public Integer getNukeEveAdrId() {
        return nukeEveAdrId;
    }

    public void setNukeEveAdrId(Integer nukeEveAdrId) {
        this.nukeEveAdrId = nukeEveAdrId;
    }

    public Long getNukeEvePrice() {
        return nukeEvePrice;
    }

    public void setNukeEvePrice(Long nukeEvePrice) {
        this.nukeEvePrice = nukeEvePrice;
    }

    public String getNukeEvePriceText() {
        return nukeEvePriceText;
    }

    public void setNukeEvePriceText(String nukeEvePriceText) {
        this.nukeEvePriceText = nukeEvePriceText;
    }

    public Short getNukeEveIscancelled() {
        return nukeEveIscancelled;
    }

    public void setNukeEveIscancelled(Short nukeEveIscancelled) {
        this.nukeEveIscancelled = nukeEveIscancelled;
    }

    public Long getNukeEvePriceDe() {
        return nukeEvePriceDe;
    }

    public void setNukeEvePriceDe(Long nukeEvePriceDe) {
        this.nukeEvePriceDe = nukeEvePriceDe;
    }

    public boolean getNukeEveShowfullevent() {
        return nukeEveShowfullevent;
    }

    public void setNukeEveShowfullevent(boolean nukeEveShowfullevent) {
        this.nukeEveShowfullevent = nukeEveShowfullevent;
    }

    public short getNukeEveDirectinscription() {
        return nukeEveDirectinscription;
    }

    public void setNukeEveDirectinscription(short nukeEveDirectinscription) {
        this.nukeEveDirectinscription = nukeEveDirectinscription;
    }

    public boolean getNukeEveCreatedfromtakt() {
        return nukeEveCreatedfromtakt;
    }

    public void setNukeEveCreatedfromtakt(boolean nukeEveCreatedfromtakt) {
        this.nukeEveCreatedfromtakt = nukeEveCreatedfromtakt;
    }

    public short getNukeEveCanceltype() {
        return nukeEveCanceltype;
    }

    public void setNukeEveCanceltype(short nukeEveCanceltype) {
        this.nukeEveCanceltype = nukeEveCanceltype;
    }

    public Integer getNukeEveClosebeforedays() {
        return nukeEveClosebeforedays;
    }

    public void setNukeEveClosebeforedays(Integer nukeEveClosebeforedays) {
        this.nukeEveClosebeforedays = nukeEveClosebeforedays;
    }

    public NukeMrcommerceItems getNukeMrcommerceItems() {
        return nukeMrcommerceItems;
    }

    public void setNukeMrcommerceItems(NukeMrcommerceItems nukeMrcommerceItems) {
        this.nukeMrcommerceItems = nukeMrcommerceItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeEveId != null ? nukeEveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceEvents)) {
            return false;
        }
        NukeMrcommerceEvents other = (NukeMrcommerceEvents) object;
        if ((this.nukeEveId == null && other.nukeEveId != null) || (this.nukeEveId != null && !this.nukeEveId.equals(other.nukeEveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceEvents[nukeEveId=" + nukeEveId + "]";
    }

}
