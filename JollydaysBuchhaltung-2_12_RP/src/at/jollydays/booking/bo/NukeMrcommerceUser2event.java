/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_user2event")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceUser2event.findAll", query = "SELECT n FROM NukeMrcommerceUser2event n"),
    @NamedQuery(name = "NukeMrcommerceUser2event.findById", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.id = :id"),
    @NamedQuery(name = "NukeMrcommerceUser2event.findByIncUserId", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.incUserId = :incUserId"),
    @NamedQuery(name = "NukeMrcommerceUser2event.findByNukeEventId", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.nukeEventId = :nukeEventId"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByNukeBookingDate", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.nukeBookingDate = :nukeBookingDate")
    @NamedQuery(name = "NukeMrcommerceUser2event.findByIncInvoiceitemId", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.incInvoiceitemId = :incInvoiceitemId")
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByDirectInscriptionBookingDelete", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.directInscriptionBookingDelete = :directInscriptionBookingDelete"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByFeedbackSent", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.feedbackSent = :feedbackSent"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByHasSpecialEkpriceFrom", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.hasSpecialEkpriceFrom = :hasSpecialEkpriceFrom"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findBySpecialEkprice", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.specialEkprice = :specialEkprice"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByCheckedForInvoice", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.checkedForInvoice = :checkedForInvoice"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByEventdateIfdirectInscription", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.eventdateIfdirectInscription = :eventdateIfdirectInscription"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByKeyAccepted", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.keyAccepted = :keyAccepted"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByOrganiserDonotaccount", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.organiserDonotaccount = :organiserDonotaccount"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByIsInvoiced", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.isInvoiced = :isInvoiced"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByNoShow", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.noShow = :noShow"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByCancelCustomer", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.cancelCustomer = :cancelCustomer"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByInvoiceComment", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.invoiceComment = :invoiceComment"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByCompoundBooking", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.compoundBooking = :compoundBooking"),
//    @NamedQuery(name = "NukeMrcommerceUser2event.findByIncBookingUserId", query = "SELECT n FROM NukeMrcommerceUser2event n WHERE n.incBookingUserId = :incBookingUserId")
})
public class NukeMrcommerceUser2event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "inc_user_id")
    private Integer incUserId;
    @Column(name = "nuke_event_id")
    private Integer nukeEventId;
    @Column(name = "nuke_booking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nukeBookingDate;
    @Column(name = "inc_invoiceitem_id")
    private Integer incInvoiceitemId;
    @Column(name = "direct_inscription_booking__delete")
    private Boolean directInscriptionBookingDelete;
    @Column(name = "feedback_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feedbackSent;
    @Lob
    @Column(name = "organiser_comment")
    private String organiserComment;
    @Column(name = "has_special_ekprice_from")
    private Integer hasSpecialEkpriceFrom;
    @Column(name = "special_ekprice")
    private BigDecimal specialEkprice;
    @Column(name = "checked_for_invoice")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkedForInvoice;
    @Column(name = "eventdate_ifdirect_inscription")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventdateIfdirectInscription;
    @Column(name = "key_accepted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date keyAccepted;
    @Basic(optional = false)
    @Column(name = "organiser_donotaccount")
    private short organiserDonotaccount;
    @Basic(optional = false)
    @Column(name = "is_invoiced")
    private short isInvoiced;
    @Basic(optional = false)
    @Column(name = "no_show")
    private short noShow;
    @Column(name = "cancel_customer")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelCustomer;
    @Column(name = "invoice_comment")
    private String invoiceComment;
    @Column(name = "compound_booking")
    private String compoundBooking;
    @Column(name = "inc_booking_user_id")
    private Integer incBookingUserId;
//    @JoinColumn(name = "nuke_event_id", referencedColumnName = "event_id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private NukeMrcommerceEventsArrangementCities eventsArrangementCities;


    public NukeMrcommerceUser2event() {
    }

    public NukeMrcommerceUser2event(Integer id) {
        this.id = id;
    }

    public NukeMrcommerceUser2event(Integer id, short organiserDonotaccount, short isInvoiced, short noShow) {
        this.id = id;
        this.organiserDonotaccount = organiserDonotaccount;
        this.isInvoiced = isInvoiced;
        this.noShow = noShow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncUserId() {
        return incUserId;
    }

    public void setIncUserId(Integer incUserId) {
        this.incUserId = incUserId;
    }

    public Integer getNukeEventId() {
        return nukeEventId;
    }

    public void setNukeEventId(Integer nukeEventId) {
        this.nukeEventId = nukeEventId;
    }

    public Date getNukeBookingDate() {
        return nukeBookingDate;
    }

    public void setNukeBookingDate(Date nukeBookingDate) {
        this.nukeBookingDate = nukeBookingDate;
    }

    public Integer getIncInvoiceitemId() {
        return incInvoiceitemId;
    }

    public void setIncInvoiceitemId(Integer incInvoiceitemId) {
        this.incInvoiceitemId = incInvoiceitemId;
    }

    public Boolean getDirectInscriptionBookingDelete() {
        return directInscriptionBookingDelete;
    }

    public void setDirectInscriptionBookingDelete(Boolean directInscriptionBookingDelete) {
        this.directInscriptionBookingDelete = directInscriptionBookingDelete;
    }

    public Date getFeedbackSent() {
        return feedbackSent;
    }

    public void setFeedbackSent(Date feedbackSent) {
        this.feedbackSent = feedbackSent;
    }

    public String getOrganiserComment() {
        return organiserComment;
    }

    public void setOrganiserComment(String organiserComment) {
        this.organiserComment = organiserComment;
    }

    public Integer getHasSpecialEkpriceFrom() {
        return hasSpecialEkpriceFrom;
    }

    public void setHasSpecialEkpriceFrom(Integer hasSpecialEkpriceFrom) {
        this.hasSpecialEkpriceFrom = hasSpecialEkpriceFrom;
    }

    public BigDecimal getSpecialEkprice() {
        return specialEkprice;
    }

    public void setSpecialEkprice(BigDecimal specialEkprice) {
        this.specialEkprice = specialEkprice;
    }

    public Date getCheckedForInvoice() {
        return checkedForInvoice;
    }

    public void setCheckedForInvoice(Date checkedForInvoice) {
        this.checkedForInvoice = checkedForInvoice;
    }

    public Date getEventdateIfdirectInscription() {
        return eventdateIfdirectInscription;
    }

    public void setEventdateIfdirectInscription(Date eventdateIfdirectInscription) {
        this.eventdateIfdirectInscription = eventdateIfdirectInscription;
    }

    public Date getKeyAccepted() {
        return keyAccepted;
    }

    public void setKeyAccepted(Date keyAccepted) {
        this.keyAccepted = keyAccepted;
    }

    public short getOrganiserDonotaccount() {
        return organiserDonotaccount;
    }

    public void setOrganiserDonotaccount(short organiserDonotaccount) {
        this.organiserDonotaccount = organiserDonotaccount;
    }

    public short getIsInvoiced() {
        return isInvoiced;
    }

    public void setIsInvoiced(short isInvoiced) {
        this.isInvoiced = isInvoiced;
    }

    public short getNoShow() {
        return noShow;
    }

    public void setNoShow(short noShow) {
        this.noShow = noShow;
    }

    public Date getCancelCustomer() {
        return cancelCustomer;
    }

    public void setCancelCustomer(Date cancelCustomer) {
        this.cancelCustomer = cancelCustomer;
    }

    public String getInvoiceComment() {
        return invoiceComment;
    }

    public void setInvoiceComment(String invoiceComment) {
        this.invoiceComment = invoiceComment;
    }

    public String getCompoundBooking() {
        return compoundBooking;
    }

    public void setCompoundBooking(String compoundBooking) {
        this.compoundBooking = compoundBooking;
    }

    public Integer getIncBookingUserId() {
        return incBookingUserId;
    }

    public void setIncBookingUserId(Integer incBookingUserId) {
        this.incBookingUserId = incBookingUserId;
    }

//    public NukeMrcommerceEventsArrangementCities getEventsArrangementCities() {
//        return eventsArrangementCities;
//    }
//
//    public void setEventsArrangementCities(NukeMrcommerceEventsArrangementCities eventsArrangementCities) {
//        this.eventsArrangementCities = eventsArrangementCities;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceUser2event)) {
            return false;
        }
        NukeMrcommerceUser2event other = (NukeMrcommerceUser2event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceUser2event[id=" + id + "]";
    }

}
