/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "InvoiceItem")
@NamedQueries({
    @NamedQuery(name = "InvoiceItem.findAll", query = "SELECT i FROM InvoiceItem i"),
    @NamedQuery(name = "InvoiceItem.findById", query = "SELECT i FROM InvoiceItem i WHERE i.id = :id"),
//    @NamedQuery(name = "InvoiceItem.findByItemType", query = "SELECT i FROM InvoiceItem i WHERE i.itemType = :itemType"),
//    @NamedQuery(name = "InvoiceItem.findByServiceName", query = "SELECT i FROM InvoiceItem i WHERE i.serviceName = :serviceName"),
//    @NamedQuery(name = "InvoiceItem.findByProductPrice", query = "SELECT i FROM InvoiceItem i WHERE i.productPrice = :productPrice"),
//    @NamedQuery(name = "InvoiceItem.findByAmount", query = "SELECT i FROM InvoiceItem i WHERE i.amount = :amount"),
//    @NamedQuery(name = "InvoiceItem.findByTaxRate", query = "SELECT i FROM InvoiceItem i WHERE i.taxRate = :taxRate"),
//    @NamedQuery(name = "InvoiceItem.findByValidFrom", query = "SELECT i FROM InvoiceItem i WHERE i.validFrom = :validFrom"),
//    @NamedQuery(name = "InvoiceItem.findByValidTo", query = "SELECT i FROM InvoiceItem i WHERE i.validTo = :validTo"),
//    @NamedQuery(name = "InvoiceItem.findByMrcommerceItemID", query = "SELECT i FROM InvoiceItem i WHERE i.mrcommerceItemID = :mrcommerceItemID"),
//    @NamedQuery(name = "InvoiceItem.findByMrcommerceOrganiserID", query = "SELECT i FROM InvoiceItem i WHERE i.mrcommerceOrganiserID = :mrcommerceOrganiserID"),
//    @NamedQuery(name = "InvoiceItem.findByUniqueValue", query = "SELECT i FROM InvoiceItem i WHERE i.uniqueValue = :uniqueValue"),
    @NamedQuery(name = "InvoiceItem.findByUniqueNumber", query = "SELECT i FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber"),
    @NamedQuery(name = "InvoiceItem.findFirstByUniqueNumber", query = "SELECT i FROM InvoiceItem i WHERE i.uniqueNumber = :uniqueNumber ORDER BY i.id ASC")
//    @NamedQuery(name = "InvoiceItem.findByPersonalSalutationText", query = "SELECT i FROM InvoiceItem i WHERE i.personalSalutationText = :personalSalutationText"),
//    @NamedQuery(name = "InvoiceItem.findByPersonalFromText", query = "SELECT i FROM InvoiceItem i WHERE i.personalFromText = :personalFromText"),
//    @NamedQuery(name = "InvoiceItem.findByItemStatus", query = "SELECT i FROM InvoiceItem i WHERE i.itemStatus = :itemStatus"),
//    @NamedQuery(name = "InvoiceItem.findByMigration", query = "SELECT i FROM InvoiceItem i WHERE i.migration = :migration"),
//    @NamedQuery(name = "InvoiceItem.findByChangeTicket", query = "SELECT i FROM InvoiceItem i WHERE i.changeTicket = :changeTicket"),
//    @NamedQuery(name = "InvoiceItem.findByManualStatus", query = "SELECT i FROM InvoiceItem i WHERE i.manualStatus = :manualStatus"),
//    @NamedQuery(name = "InvoiceItem.findByItemStatusoldtmp", query = "SELECT i FROM InvoiceItem i WHERE i.itemStatusoldtmp = :itemStatusoldtmp"),
//    @NamedQuery(name = "InvoiceItem.findByBHstatus", query = "SELECT i FROM InvoiceItem i WHERE i.bHstatus = :bHstatus"),
//    @NamedQuery(name = "InvoiceItem.findByFFstatus", query = "SELECT i FROM InvoiceItem i WHERE i.fFstatus = :fFstatus"),
//    @NamedQuery(name = "InvoiceItem.findByCorrespondingItemId", query = "SELECT i FROM InvoiceItem i WHERE i.correspondingItemId = :correspondingItemId"),
//    @NamedQuery(name = "InvoiceItem.findByItemNotes", query = "SELECT i FROM InvoiceItem i WHERE i.itemNotes = :itemNotes"),
//    @NamedQuery(name = "InvoiceItem.findByTransferDate", query = "SELECT i FROM InvoiceItem i WHERE i.transferDate = :transferDate"),
//    @NamedQuery(name = "InvoiceItem.findByOriginInvoiceItemId", query = "SELECT i FROM InvoiceItem i WHERE i.originInvoiceItemId = :originInvoiceItemId"),
//    @NamedQuery(name = "InvoiceItem.findByFfDedicationId", query = "SELECT i FROM InvoiceItem i WHERE i.ffDedicationId = :ffDedicationId"),
//    @NamedQuery(name = "InvoiceItem.findByFfSalutation", query = "SELECT i FROM InvoiceItem i WHERE i.ffSalutation = :ffSalutation"),
//    @NamedQuery(name = "InvoiceItem.findByAmounteur", query = "SELECT i FROM InvoiceItem i WHERE i.amounteur = :amounteur"),
//    @NamedQuery(name = "InvoiceItem.findByCorrespondingItemRabattAmount", query = "SELECT i FROM InvoiceItem i WHERE i.correspondingItemRabattAmount = :correspondingItemRabattAmount")
})

public class InvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Besorger")
    private boolean besorger;
    @OneToMany(mappedBy = "invoiceItem")
    private Collection<InvoiceItem> invoiceItemCollection;
    @JoinColumn(name = "CorrespondingItemRabattID", referencedColumnName = "ID")
    @ManyToOne
    private InvoiceItem invoiceItem;
    @JoinColumn(name = "InvoiceID", referencedColumnName = "ID", nullable=false, insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Invoice invoice;
    @OneToOne(mappedBy = "invoiceItemNew")
//    @ManyToOne( name = "ID", referencedColumnName = "invoiceItemOld")
    private InvoiceItemHistory invoiceItemHistory;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ItemType")
    private String itemType;
    @Column(name = "ServiceName")
    private String serviceName;
    @Column(name = "ProductPrice")
    private BigDecimal productPrice;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "TaxRate")
    private Double taxRate;
    @Column(name = "ValidFrom")
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    @Column(name = "ValidTo")
    @Temporal(TemporalType.DATE)
    private Date validTo;
    @Column(name = "Mrcommerce_Item_ID")
    private Integer mrcommerceItemID;
    @Column(name = "Mrcommerce_Organiser_ID")
    private Integer mrcommerceOrganiserID;
    @Column(name = "UniqueValue")
    private String uniqueValue;
    @Column(name = "UniqueNumber")
    private String uniqueNumber;
    @Column(name = "PersonalSalutationText")
    private String personalSalutationText;
    @Column(name = "PersonalFromText")
    private String personalFromText;
    @Basic(optional = false)
    @Column(name = "ItemStatus__")
    private int itemStatus;
    @Column(name = "Migration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date migration;
    @Column(name = "ChangeTicket")
    private Boolean changeTicket;
    @Column(name = "ManualStatus")
    private String manualStatus;
    @Column(name = "ItemStatus_old_tmp")
    private Integer itemStatusoldtmp;
    @Column(name = "BH_status")
    private Integer bHstatus;
    @Column(name = "FF_status")
    private Integer fFstatus;
    @Column(name = "CorrespondingItemId")
    private Integer correspondingItemId;
    @Column(name = "ItemNotes")
    private String itemNotes;
    @Column(name = "TransferDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;
    @Column(name = "OriginInvoiceItemId")
    private Integer originInvoiceItemId;
    @Column(name = "ff_dedication_id")
    private Integer ffDedicationId;
    @Column(name = "ff_salutation")
    private String ffSalutation;
    @Lob
    @Column(name = "ff_message")
    private String ffMessage;
    @Column(name = "Amount_eur")
    private BigDecimal amounteur;
    @Basic(optional = false)
    @Column(name = "CorrespondingItemRabattAmount")
    private BigDecimal correspondingItemRabattAmount;
    @JoinColumn(name = "InvoiceID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Invoice invoiceID;
    @JoinColumn(name = "CorrespondingItemRabattID", referencedColumnName = "ID", nullable=false, insertable=false, updatable=false )
    @ManyToOne
    private InvoiceItem correspondingItemRabattID;
    @Column(name = "arrangementcity_id")
    private Integer arrangementcity_id;
    



    //@JoinColumn(name = "Mrcommerce_Item_ID", referencedColumnName = "nuke_item_id")
    //@ManyToOne
    //private NukeMrcommerceItems item;


    public InvoiceItem() {
    }

    public InvoiceItem(Integer id) {
        this.id = id;
    }

    public InvoiceItem(Integer id, int itemStatus, BigDecimal correspondingItemRabattAmount) {
        this.id = id;
        this.itemStatus = itemStatus;
        this.correspondingItemRabattAmount = correspondingItemRabattAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Integer getMrcommerceItemID() {
        return mrcommerceItemID;
    }

    public void setMrcommerceItemID(Integer mrcommerceItemID) {
        this.mrcommerceItemID = mrcommerceItemID;
    }

    public Integer getMrcommerceOrganiserID() {
        return mrcommerceOrganiserID;
    }

    public void setMrcommerceOrganiserID(Integer mrcommerceOrganiserID) {
        this.mrcommerceOrganiserID = mrcommerceOrganiserID;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getPersonalSalutationText() {
        return personalSalutationText;
    }

    public void setPersonalSalutationText(String personalSalutationText) {
        this.personalSalutationText = personalSalutationText;
    }

    public String getPersonalFromText() {
        return personalFromText;
    }

    public void setPersonalFromText(String personalFromText) {
        this.personalFromText = personalFromText;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Date getMigration() {
        return migration;
    }

    public void setMigration(Date migration) {
        this.migration = migration;
    }

    public Boolean getChangeTicket() {
        return changeTicket;
    }

    public void setChangeTicket(Boolean changeTicket) {
        this.changeTicket = changeTicket;
    }

    public String getManualStatus() {
        return manualStatus;
    }

    public void setManualStatus(String manualStatus) {
        this.manualStatus = manualStatus;
    }

    public Integer getItemStatusoldtmp() {
        return itemStatusoldtmp;
    }

    public void setItemStatusoldtmp(Integer itemStatusoldtmp) {
        this.itemStatusoldtmp = itemStatusoldtmp;
    }

    public Integer getBHstatus() {
        return bHstatus;
    }

    public void setBHstatus(Integer bHstatus) {
        this.bHstatus = bHstatus;
    }

    public Integer getFFstatus() {
        return fFstatus;
    }

    public void setFFstatus(Integer fFstatus) {
        this.fFstatus = fFstatus;
    }

    public Integer getCorrespondingItemId() {
        return correspondingItemId;
    }

    public void setCorrespondingItemId(Integer correspondingItemId) {
        this.correspondingItemId = correspondingItemId;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Integer getOriginInvoiceItemId() {
        return originInvoiceItemId;
    }

    public void setOriginInvoiceItemId(Integer originInvoiceItemId) {
        this.originInvoiceItemId = originInvoiceItemId;
    }

    public Integer getArrangementcity_id() {
        return arrangementcity_id;
    }

    public void setArrangementcity_id(Integer arrangementcity_id) {
        this.arrangementcity_id = arrangementcity_id;
    }
    
    
    
    public Integer getFfDedicationId() {
        return ffDedicationId;
    }

    public void setFfDedicationId(Integer ffDedicationId) {
        this.ffDedicationId = ffDedicationId;
    }

    public String getFfSalutation() {
        return ffSalutation;
    }

    public void setFfSalutation(String ffSalutation) {
        this.ffSalutation = ffSalutation;
    }

    public String getFfMessage() {
        return ffMessage;
    }

    public void setFfMessage(String ffMessage) {
        this.ffMessage = ffMessage;
    }

    public BigDecimal getAmounteur() {
        if (amounteur == null && amount != null)
            amounteur = amount;
        
        return amounteur;
    }

    public void setAmounteur(BigDecimal amounteur) {
        this.amounteur = amounteur;
    }

    public BigDecimal getCorrespondingItemRabattAmount() {
        return correspondingItemRabattAmount;
    }

    public void setCorrespondingItemRabattAmount(BigDecimal correspondingItemRabattAmount) {
        this.correspondingItemRabattAmount = correspondingItemRabattAmount;
    }

    public Invoice getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Invoice invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Collection<InvoiceItem> getInvoiceItemCollection() {
        return invoiceItemCollection;
    }

    public void setInvoiceItemCollection(Collection<InvoiceItem> invoiceItemCollection) {
        this.invoiceItemCollection = invoiceItemCollection;
    }

    public InvoiceItem getCorrespondingItemRabattID() {
        return correspondingItemRabattID;
    }

    public void setCorrespondingItemRabattID(InvoiceItem correspondingItemRabattID) {
        this.correspondingItemRabattID = correspondingItemRabattID;
    }


    //public NukeMrcommerceItems getItem() {
    //    return item;
    //}
    //public void setItem(NukeMrcommerceItems item) {
    //    this.item = item;
    //}
     

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItem)) {
            return false;
        }
        InvoiceItem other = (InvoiceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.InvoiceItem[id=" + id + "]";
    }

    public boolean getBesorger() {
        return besorger;
    }

    public void setBesorger(boolean besorger) {
        this.besorger = besorger;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceItemHistory getInvoiceItemHistory() {
        return invoiceItemHistory;
    }

    public void setInvoiceItemHistory(InvoiceItemHistory invoiceItemHistory) {
        this.invoiceItemHistory = invoiceItemHistory;
    }


}
