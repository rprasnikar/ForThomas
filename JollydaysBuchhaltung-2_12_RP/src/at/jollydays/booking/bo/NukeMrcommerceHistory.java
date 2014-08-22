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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpadmin
 */
@Entity
@Table(name = "nuke_mrcommerce_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceHistory.findAll", query = "SELECT n FROM NukeMrcommerceHistory n"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryId", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryId = :nukeHistoryId"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryDate", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryDate = :nukeHistoryDate"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryPass", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryPass = :nukeHistoryPass"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryPnUser", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryPnUser = :nukeHistoryPnUser"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryUser", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryUser = :nukeHistoryUser"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryMsg", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryMsg = :nukeHistoryMsg"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryDisplay", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryDisplay = :nukeHistoryDisplay"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryStatustype", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryStatustype = :nukeHistoryStatustype"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryStatus", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryStatus = :nukeHistoryStatus"),
    @NamedQuery(name = "NukeMrcommerceHistory.findByNukeHistoryIschecked", query = "SELECT n FROM NukeMrcommerceHistory n WHERE n.nukeHistoryIschecked = :nukeHistoryIschecked")})
public class NukeMrcommerceHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_history_id")
    private Integer nukeHistoryId;
    @Column(name = "nuke_history_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nukeHistoryDate;
    @Column(name = "nuke_history_pass")
    private String nukeHistoryPass;
    @Column(name = "nuke_history_pn_user")
    private Integer nukeHistoryPnUser;
    @Column(name = "nuke_history_user")
    private Integer nukeHistoryUser;
    @Column(name = "nuke_history_msg")
    private String nukeHistoryMsg;
    @Column(name = "nuke_history_display")
    private Boolean nukeHistoryDisplay;
    @Column(name = "nuke_history_statustype")
    private Short nukeHistoryStatustype;
    @Column(name = "nuke_history_status")
    private Integer nukeHistoryStatus;
    @Lob
    @Column(name = "nuke_history_trace")
    private String nukeHistoryTrace;
    @Basic(optional = false)
    @Column(name = "nuke_history_ischecked")
    private short nukeHistoryIschecked;

    public NukeMrcommerceHistory() {
    }

    public NukeMrcommerceHistory(Integer nukeHistoryId) {
        this.nukeHistoryId = nukeHistoryId;
    }

    public NukeMrcommerceHistory(Integer nukeHistoryId, short nukeHistoryIschecked) {
        this.nukeHistoryId = nukeHistoryId;
        this.nukeHistoryIschecked = nukeHistoryIschecked;
    }

    public Integer getNukeHistoryId() {
        return nukeHistoryId;
    }

    public void setNukeHistoryId(Integer nukeHistoryId) {
        this.nukeHistoryId = nukeHistoryId;
    }

    public Date getNukeHistoryDate() {
        return nukeHistoryDate;
    }

    public void setNukeHistoryDate(Date nukeHistoryDate) {
        this.nukeHistoryDate = nukeHistoryDate;
    }

    public String getNukeHistoryPass() {
        return nukeHistoryPass;
    }

    public void setNukeHistoryPass(String nukeHistoryPass) {
        this.nukeHistoryPass = nukeHistoryPass;
    }

    public Integer getNukeHistoryPnUser() {
        return nukeHistoryPnUser;
    }

    public void setNukeHistoryPnUser(Integer nukeHistoryPnUser) {
        this.nukeHistoryPnUser = nukeHistoryPnUser;
    }

    public Integer getNukeHistoryUser() {
        return nukeHistoryUser;
    }

    public void setNukeHistoryUser(Integer nukeHistoryUser) {
        this.nukeHistoryUser = nukeHistoryUser;
    }

    public String getNukeHistoryMsg() {
        return nukeHistoryMsg;
    }

    public void setNukeHistoryMsg(String nukeHistoryMsg) {
        this.nukeHistoryMsg = nukeHistoryMsg;
    }

    public Boolean getNukeHistoryDisplay() {
        return nukeHistoryDisplay;
    }

    public void setNukeHistoryDisplay(Boolean nukeHistoryDisplay) {
        this.nukeHistoryDisplay = nukeHistoryDisplay;
    }

    public Short getNukeHistoryStatustype() {
        return nukeHistoryStatustype;
    }

    public void setNukeHistoryStatustype(Short nukeHistoryStatustype) {
        this.nukeHistoryStatustype = nukeHistoryStatustype;
    }

    public Integer getNukeHistoryStatus() {
        return nukeHistoryStatus;
    }

    public void setNukeHistoryStatus(Integer nukeHistoryStatus) {
        this.nukeHistoryStatus = nukeHistoryStatus;
    }

    public String getNukeHistoryTrace() {
        return nukeHistoryTrace;
    }

    public void setNukeHistoryTrace(String nukeHistoryTrace) {
        this.nukeHistoryTrace = nukeHistoryTrace;
    }

    public short getNukeHistoryIschecked() {
        return nukeHistoryIschecked;
    }

    public void setNukeHistoryIschecked(short nukeHistoryIschecked) {
        this.nukeHistoryIschecked = nukeHistoryIschecked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeHistoryId != null ? nukeHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceHistory)) {
            return false;
        }
        NukeMrcommerceHistory other = (NukeMrcommerceHistory) object;
        if ((this.nukeHistoryId == null && other.nukeHistoryId != null) || (this.nukeHistoryId != null && !this.nukeHistoryId.equals(other.nukeHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceHistory[ nukeHistoryId=" + nukeHistoryId + " ]";
    }
    
}
