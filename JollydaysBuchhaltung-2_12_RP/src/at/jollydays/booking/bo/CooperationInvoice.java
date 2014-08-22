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
@Table(name = "CooperationInvoice")
@NamedQueries({
    @NamedQuery(name = "CooperationInvoice.findAll", query = "SELECT c FROM CooperationInvoice c"),
    @NamedQuery(name = "CooperationInvoice.findNext", query = "SELECT c FROM CooperationInvoice c WHERE c.id >= :id order by c.id"),
    @NamedQuery(name = "CooperationInvoice.findById", query = "SELECT c FROM CooperationInvoice c WHERE c.id = :id"),
    @NamedQuery(name = "CooperationInvoice.findByCooperationID", query = "SELECT c FROM CooperationInvoice c WHERE c.cooperationID = :cooperationID"),
    @NamedQuery(name = "CooperationInvoice.findByInvoiceNumber", query = "SELECT c FROM CooperationInvoice c WHERE c.invoiceNumber = :invoiceNumber")
//    @NamedQuery(name = "CooperationInvoice.findByCurrencyISOCode", query = "SELECT c FROM CooperationInvoice c WHERE c.currencyISOCode = :currencyISOCode"),
//    @NamedQuery(name = "CooperationInvoice.findByInvoiceDateTime", query = "SELECT c FROM CooperationInvoice c WHERE c.invoiceDateTime = :invoiceDateTime"),
//    @NamedQuery(name = "CooperationInvoice.findByBillTo", query = "SELECT c FROM CooperationInvoice c WHERE c.billTo = :billTo"),
//    @NamedQuery(name = "CooperationInvoice.findByDeliveryTO", query = "SELECT c FROM CooperationInvoice c WHERE c.deliveryTO = :deliveryTO"),
//    @NamedQuery(name = "CooperationInvoice.findByUidNr", query = "SELECT c FROM CooperationInvoice c WHERE c.uidNr = :uidNr"),
//    @NamedQuery(name = "CooperationInvoice.findBySteuerNo", query = "SELECT c FROM CooperationInvoice c WHERE c.steuerNo = :steuerNo"),
//    @NamedQuery(name = "CooperationInvoice.findByBlz", query = "SELECT c FROM CooperationInvoice c WHERE c.blz = :blz"),
//    @NamedQuery(name = "CooperationInvoice.findByKontoNr", query = "SELECT c FROM CooperationInvoice c WHERE c.kontoNr = :kontoNr"),
//    @NamedQuery(name = "CooperationInvoice.findByStatus", query = "SELECT c FROM CooperationInvoice c WHERE c.status = :status"),
//    @NamedQuery(name = "CooperationInvoice.findByPdfSigned", query = "SELECT c FROM CooperationInvoice c WHERE c.pdfSigned = :pdfSigned"),
//    @NamedQuery(name = "CooperationInvoice.findByStatusChanged", query = "SELECT c FROM CooperationInvoice c WHERE c.statusChanged = :statusChanged"),
//    @NamedQuery(name = "CooperationInvoice.findByDeptconditionday1", query = "SELECT c FROM CooperationInvoice c WHERE c.deptconditionday1 = :deptconditionday1"),
//    @NamedQuery(name = "CooperationInvoice.findByDeptconditionpercent1", query = "SELECT c FROM CooperationInvoice c WHERE c.deptconditionpercent1 = :deptconditionpercent1"),
//    @NamedQuery(name = "CooperationInvoice.findByDeptconditionday2", query = "SELECT c FROM CooperationInvoice c WHERE c.deptconditionday2 = :deptconditionday2"),
//    @NamedQuery(name = "CooperationInvoice.findByNotes", query = "SELECT c FROM CooperationInvoice c WHERE c.notes = :notes")
})
public class CooperationInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CooperationID")
    private int cooperationID;
    @Basic(optional = false)
    @Column(name = "InvoiceNumber")
    private String invoiceNumber;
    @Basic(optional = false)
    @Column(name = "CurrencyISOCode")
    private String currencyISOCode;
    @Basic(optional = false)
    @Column(name = "InvoiceDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDateTime;
    @Basic(optional = false)
    @Column(name = "BillTo")
    private int billTo;
    @Basic(optional = false)
    @Column(name = "DeliveryTO")
    private int deliveryTO;
    @Column(name = "UidNr")
    private String uidNr;
    @Column(name = "SteuerNo")
    private String steuerNo;
    @Column(name = "BLZ")
    private String blz;
    @Column(name = "KontoNr")
    private String kontoNr;
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "PdfSigned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdfSigned;
    @Column(name = "StatusChanged")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusChanged;
    @Basic(optional = false)
    @Column(name = "Deptcondition_day1")
    private int deptconditionday1;
    @Basic(optional = false)
    @Column(name = "Deptcondition_percent1")
    private double deptconditionpercent1;
    @Basic(optional = false)
    @Column(name = "Deptcondition_day2")
    private int deptconditionday2;
    @Column(name = "Notes")
    private String notes;


    public CooperationInvoice() {
    }

    public CooperationInvoice(Integer id) {
        this.id = id;
    }

    public CooperationInvoice(Integer id, int cooperationID, String invoiceNumber, String currencyISOCode, Date invoiceDateTime, int billTo, int deliveryTO, Date pdfSigned, int deptconditionday1, double deptconditionpercent1, int deptconditionday2) {
        this.id = id;
        this.cooperationID = cooperationID;
        this.invoiceNumber = invoiceNumber;
        this.currencyISOCode = currencyISOCode;
        this.invoiceDateTime = invoiceDateTime;
        this.billTo = billTo;
        this.deliveryTO = deliveryTO;
        this.pdfSigned = pdfSigned;
        this.deptconditionday1 = deptconditionday1;
        this.deptconditionpercent1 = deptconditionpercent1;
        this.deptconditionday2 = deptconditionday2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCooperationID() {
        return cooperationID;
    }

    public void setCooperationID(int cooperationID) {
        this.cooperationID = cooperationID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCurrencyISOCode() {
        return currencyISOCode;
    }

    public void setCurrencyISOCode(String currencyISOCode) {
        this.currencyISOCode = currencyISOCode;
    }

    public Date getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(Date invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    public int getBillTo() {
        return billTo;
    }

    public void setBillTo(int billTo) {
        this.billTo = billTo;
    }

    public int getDeliveryTO() {
        return deliveryTO;
    }

    public void setDeliveryTO(int deliveryTO) {
        this.deliveryTO = deliveryTO;
    }

    public String getUidNr() {
        return uidNr;
    }

    public void setUidNr(String uidNr) {
        this.uidNr = uidNr;
    }

    public String getSteuerNo() {
        return steuerNo;
    }

    public void setSteuerNo(String steuerNo) {
        this.steuerNo = steuerNo;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public String getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(String kontoNr) {
        this.kontoNr = kontoNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPdfSigned() {
        return pdfSigned;
    }

    public void setPdfSigned(Date pdfSigned) {
        this.pdfSigned = pdfSigned;
    }

    public Date getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(Date statusChanged) {
        this.statusChanged = statusChanged;
    }

    public int getDeptconditionday1() {
        return deptconditionday1;
    }

    public void setDeptconditionday1(int deptconditionday1) {
        this.deptconditionday1 = deptconditionday1;
    }

    public double getDeptconditionpercent1() {
        return deptconditionpercent1;
    }

    public void setDeptconditionpercent1(double deptconditionpercent1) {
        this.deptconditionpercent1 = deptconditionpercent1;
    }

    public int getDeptconditionday2() {
        return deptconditionday2;
    }

    public void setDeptconditionday2(int deptconditionday2) {
        this.deptconditionday2 = deptconditionday2;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof CooperationInvoice)) {
            return false;
        }
        CooperationInvoice other = (CooperationInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.CooperationInvoice[id=" + id + "]";
    }

}
