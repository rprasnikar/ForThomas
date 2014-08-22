/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "OrganiserInvoice")
@NamedQueries({
    @NamedQuery(name = "OrganiserInvoice.findAll", query = "SELECT o FROM OrganiserInvoice o"),
    @NamedQuery(name = "OrganiserInvoice.findNext", query = "SELECT o FROM OrganiserInvoice o WHERE o.id >= :id order by o.id"),
    @NamedQuery(name = "OrganiserInvoice.findById", query = "SELECT o FROM OrganiserInvoice o WHERE o.id = :id"),
    @NamedQuery(name = "OrganiserInvoice.findByOrganiserID", query = "SELECT o FROM OrganiserInvoice o WHERE o.organiserID = :organiserID"),
    @NamedQuery(name = "OrganiserInvoice.findByInvoiceNumber", query = "SELECT o FROM OrganiserInvoice o WHERE o.invoiceNumber = :invoiceNumber")
//    @NamedQuery(name = "OrganiserInvoice.findByCurrencyISOCode", query = "SELECT o FROM OrganiserInvoice o WHERE o.currencyISOCode = :currencyISOCode"),
//    @NamedQuery(name = "OrganiserInvoice.findByInvoiceDateTime", query = "SELECT o FROM OrganiserInvoice o WHERE o.invoiceDateTime = :invoiceDateTime"),
//    @NamedQuery(name = "OrganiserInvoice.findByBillTo", query = "SELECT o FROM OrganiserInvoice o WHERE o.billTo = :billTo"),
//    @NamedQuery(name = "OrganiserInvoice.findByDeliveryTO", query = "SELECT o FROM OrganiserInvoice o WHERE o.deliveryTO = :deliveryTO"),
//    @NamedQuery(name = "OrganiserInvoice.findByUidNr", query = "SELECT o FROM OrganiserInvoice o WHERE o.uidNr = :uidNr"),
//    @NamedQuery(name = "OrganiserInvoice.findBySteuerNo", query = "SELECT o FROM OrganiserInvoice o WHERE o.steuerNo = :steuerNo"),
//    @NamedQuery(name = "OrganiserInvoice.findByBlz", query = "SELECT o FROM OrganiserInvoice o WHERE o.blz = :blz"),
//    @NamedQuery(name = "OrganiserInvoice.findByKontoNr", query = "SELECT o FROM OrganiserInvoice o WHERE o.kontoNr = :kontoNr"),
//    @NamedQuery(name = "OrganiserInvoice.findByStatus", query = "SELECT o FROM OrganiserInvoice o WHERE o.status = :status"),
//    @NamedQuery(name = "OrganiserInvoice.findByPdfSigned", query = "SELECT o FROM OrganiserInvoice o WHERE o.pdfSigned = :pdfSigned"),
//    @NamedQuery(name = "OrganiserInvoice.findByStatusChanged", query = "SELECT o FROM OrganiserInvoice o WHERE o.statusChanged = :statusChanged"),
//    @NamedQuery(name = "OrganiserInvoice.findByDeptconditionday1", query = "SELECT o FROM OrganiserInvoice o WHERE o.deptconditionday1 = :deptconditionday1"),
//    @NamedQuery(name = "OrganiserInvoice.findByDeptconditionpercent1", query = "SELECT o FROM OrganiserInvoice o WHERE o.deptconditionpercent1 = :deptconditionpercent1"),
//    @NamedQuery(name = "OrganiserInvoice.findByDeptconditionday2", query = "SELECT o FROM OrganiserInvoice o WHERE o.deptconditionday2 = :deptconditionday2"),
//    @NamedQuery(name = "OrganiserInvoice.findByBesorger", query = "SELECT o FROM OrganiserInvoice o WHERE o.besorger = :besorger")
})
public class OrganiserInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "OrganiserID")
    private int organiserID;
    @Basic(optional = false)
    @Column(name = "InvoiceNumber")
    private String invoiceNumber;
    @Basic(optional = false)
    @Column(name = "CurrencyISOCode")
    private String currencyISOCode;
    @Basic(optional = false)
    @Column(name = "InvoiceDateTime")
    @Temporal(TemporalType.DATE)
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
    private Integer kontoNr;
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "PdfSigned")
    @Temporal(TemporalType.DATE)
    private Date pdfSigned;
    @Column(name = "StatusChanged")
    @Temporal(TemporalType.DATE)
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
    @Basic(optional = false)
    @Column(name = "Besorger")
    private boolean besorger;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private Collection<OrganiserInvoiceItem> organiserInvoiceItemCollection;
    @Basic(optional = false)
    @Column(name = "OrderType")
    private int orderType;    

    public OrganiserInvoice() {
    }

    public OrganiserInvoice(Integer id) {
        this.id = id;
    }

    public OrganiserInvoice(Integer id, int organiserID, String invoiceNumber, String currencyISOCode, Date invoiceDateTime, int billTo, int deliveryTO, Date pdfSigned, int deptconditionday1, double deptconditionpercent1, int deptconditionday2, boolean besorger) {
        this.id = id;
        this.organiserID = organiserID;
        this.invoiceNumber = invoiceNumber;
        this.currencyISOCode = currencyISOCode;
        this.invoiceDateTime = invoiceDateTime;
        this.billTo = billTo;
        this.deliveryTO = deliveryTO;
        this.pdfSigned = pdfSigned;
        this.deptconditionday1 = deptconditionday1;
        this.deptconditionpercent1 = deptconditionpercent1;
        this.deptconditionday2 = deptconditionday2;
        this.besorger = besorger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrganiserID() {
        return organiserID;
    }

    public void setOrganiserID(int organiserID) {
        this.organiserID = organiserID;
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

    public Integer getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(Integer kontoNr) {
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

    public boolean getBesorger() {
        return besorger;
    }

    public void setBesorger(boolean besorger) {
        this.besorger = besorger;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Collection<OrganiserInvoiceItem> getInvoiceItemCollection() {
        return organiserInvoiceItemCollection;
    }

    public void setInvoiceItemCollection(Collection<OrganiserInvoiceItem> invoiceItemCollection) {
        this.organiserInvoiceItemCollection = invoiceItemCollection;
    }
    
    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganiserInvoice)) {
            return false;
        }
        OrganiserInvoice other = (OrganiserInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.OrganiserInvoice[id=" + id + "]";
    }

}
