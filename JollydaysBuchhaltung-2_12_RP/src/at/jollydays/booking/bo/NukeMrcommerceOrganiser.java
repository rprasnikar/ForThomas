/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import at.jollydays.booking.Globals;
import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_organiser")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceOrganiser.findAll", query = "SELECT n FROM NukeMrcommerceOrganiser n"),
    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeorgID", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeorgID = :nukeorgID")
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgName", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgName = :nukeOrgName"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgName2", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgName2 = :nukeOrgName2"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgStreet", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgStreet = :nukeOrgStreet"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgZip", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgZip = :nukeOrgZip"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgCity", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgCity = :nukeOrgCity"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgState", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgState = :nukeOrgState"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgFon", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgFon = :nukeOrgFon"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgFax", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgFax = :nukeOrgFax"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgUrl", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgUrl = :nukeOrgUrl"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgEmail", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgEmail = :nukeOrgEmail"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgSuborganiser", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgSuborganiser = :nukeOrgSuborganiser"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgFirstContact", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgFirstContact = :nukeOrgFirstContact"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgTodo", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgTodo = :nukeOrgTodo"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgStatus", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgStatus = :nukeOrgStatus"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgContactOwner", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgContactOwner = :nukeOrgContactOwner"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgConcession", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgConcession = :nukeOrgConcession"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgAgb", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgAgb = :nukeOrgAgb"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgInsurance", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgInsurance = :nukeOrgInsurance"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgBankname", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgBankname = :nukeOrgBankname"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgBankaccount", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgBankaccount = :nukeOrgBankaccount"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgBlz", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgBlz = :nukeOrgBlz"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgCompanyFirmbook", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgCompanyFirmbook = :nukeOrgCompanyFirmbook"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgTaxNumber", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgTaxNumber = :nukeOrgTaxNumber"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgTaxUid", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgTaxUid = :nukeOrgTaxUid"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgLevel", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgLevel = :nukeOrgLevel"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgInfoAvailEvent", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgInfoAvailEvent = :nukeOrgInfoAvailEvent"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgInfoBooking", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgInfoBooking = :nukeOrgInfoBooking"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgInfoOtherInfo", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgInfoOtherInfo = :nukeOrgInfoOtherInfo"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgAllowedtouseTax", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgAllowedtouseTax = :nukeOrgAllowedtouseTax"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgInvoiceneedscheck", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgInvoiceneedscheck = :nukeOrgInvoiceneedscheck"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgIban", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgIban = :nukeOrgIban"),
//    @NamedQuery(name = "NukeMrcommerceOrganiser.findByNukeOrgBic", query = "SELECT n FROM NukeMrcommerceOrganiser n WHERE n.nukeOrgBic = :nukeOrgBic")
})
public class NukeMrcommerceOrganiser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Transient 
    private int termOfPayment = Globals.KREDITORTERMSOFPAYMENT;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_org_ID")
    private Integer nukeorgID;
    @Column(name = "nuke_org_name")
    private String nukeOrgName;
    @Column(name = "nuke_org_name2")
    private String nukeOrgName2;
    @Column(name = "nuke_org_street")
    private String nukeOrgStreet;
    @Column(name = "nuke_org_zip")
    private String nukeOrgZip;
    @Column(name = "nuke_org_city")
    private String nukeOrgCity;
    @Column(name = "nuke_org_state")
    private String nukeOrgState;
    @Column(name = "nuke_org_fon")
    private String nukeOrgFon;
    @Column(name = "nuke_org_fax")
    private String nukeOrgFax;
    @Column(name = "nuke_org_url")
    private String nukeOrgUrl;
    @Column(name = "nuke_org_email")
    private String nukeOrgEmail;
//    @Column(name = "nuke_org_suborganiser")
//    private Boolean nukeOrgSuborganiser;
//    @Column(name = "nuke_org_first_contact")
//    private String nukeOrgFirstContact;
    @Lob
    @Column(name = "nuke_org_contact_history")
    private String nukeOrgContactHistory;
    @Column(name = "nuke_org_todo")
    private String nukeOrgTodo;
    @Column(name = "nuke_org_status")
    private String nukeOrgStatus;
//    @Column(name = "nuke_org_contact_owner")
//    private String nukeOrgContactOwner;
    @Column(name = "nuke_org_concession")
    private Boolean nukeOrgConcession;
    @Lob
    @Column(name = "nuke_org_concession_info")
    private String nukeOrgConcessionInfo;
//    @Column(name = "nuke_org_agb")
//    private Boolean nukeOrgAgb;
    @Lob
    @Column(name = "nuke_org_agb_info")
    private String nukeOrgAgbInfo;
    @Column(name = "nuke_org_insurance")
    private Boolean nukeOrgInsurance;
    @Lob
    @Column(name = "nuke_org_insurance_info")
    private String nukeOrgInsuranceInfo;
//    @Lob
//    @Column(name = "nuke_org_way_description")
//    private String nukeOrgWayDescription;
    @Column(name = "nuke_org_bankname")
    private String nukeOrgBankname;
    @Column(name = "nuke_org_bankaccount")
    private String nukeOrgBankaccount;
    @Column(name = "nuke_org_blz")
    private String nukeOrgBlz;
    @Column(name = "nuke_org_company_firmbook")
    private String nukeOrgCompanyFirmbook;
    @Column(name = "nuke_org_tax_number")
    private String nukeOrgTaxNumber;
    @Column(name = "nuke_org_tax_uid")
    private String nukeOrgTaxUid;
    @Lob
    @Column(name = "nuke_org_insurance_history")
    private String nukeOrgInsuranceHistory;
    @Lob
    @Column(name = "nuke_org_other_info")
    private String nukeOrgOtherInfo;
//    @Column(name = "nuke_org_level")
//    private Integer nukeOrgLevel;
    @Column(name = "nuke_org_info_avail_event")
    private String nukeOrgInfoAvailEvent;
    @Column(name = "nuke_org_info_booking")
    private String nukeOrgInfoBooking;
    @Column(name = "nuke_org_info_other_info")
    private String nukeOrgInfoOtherInfo;
    @Column(name = "nuke_org_allowedtouse_tax")
    private Short nukeOrgAllowedtouseTax;
//    @Basic(optional = false)
//    @Column(name = "nuke_org_invoiceneedscheck")
//    private short nukeOrgInvoiceneedscheck;
    @Column(name = "nuke_org_iban")
    private String nukeOrgIban;
    @Column(name = "nuke_org_bic")
    private String nukeOrgBic;

    @Column(name = "deptcondition_day2")
    private Integer deptcondition_day2;
    @Column(name = "default_currency")
    private String default_currency;
    @Column(name = "nuke_org_plant_isostate")
    private String nuke_org_plant_isostate;
    
    public NukeMrcommerceOrganiser() {
    }

    public NukeMrcommerceOrganiser(Integer nukeorgID) {
        this.nukeorgID = nukeorgID;
    }

//    public NukeMrcommerceOrganiser(Integer nukeorgID, short nukeOrgInvoiceneedscheck) {
//        this.nukeorgID = nukeorgID;
//        this.nukeOrgInvoiceneedscheck = nukeOrgInvoiceneedscheck;
//    }

    public Integer getNukeorgID() {
        return nukeorgID;
    }

    public void setNukeorgID(Integer nukeorgID) {
        this.nukeorgID = nukeorgID;
    }

    public String getNukeOrgName() {
        return nukeOrgName;
    }

    public void setNukeOrgName(String nukeOrgName) {
        this.nukeOrgName = nukeOrgName;
    }

    public String getNukeOrgName2() {
        return nukeOrgName2;
    }

    public void setNukeOrgName2(String nukeOrgName2) {
        this.nukeOrgName2 = nukeOrgName2;
    }

    public String getNukeOrgStreet() {
        return nukeOrgStreet;
    }

    public void setNukeOrgStreet(String nukeOrgStreet) {
        this.nukeOrgStreet = nukeOrgStreet;
    }

    public String getNukeOrgZip() {
        return nukeOrgZip;
    }

    public void setNukeOrgZip(String nukeOrgZip) {
        this.nukeOrgZip = nukeOrgZip;
    }

    public String getNukeOrgCity() {
        return nukeOrgCity;
    }

    public void setNukeOrgCity(String nukeOrgCity) {
        this.nukeOrgCity = nukeOrgCity;
    }

    public String getNukeOrgState() {
        return nukeOrgState;
    }

    public void setNukeOrgState(String nukeOrgState) {
        this.nukeOrgState = nukeOrgState;
    }

    public String getNukeOrgFon() {
        return nukeOrgFon;
    }

    public void setNukeOrgFon(String nukeOrgFon) {
        this.nukeOrgFon = nukeOrgFon;
    }

    public String getNukeOrgFax() {
        return nukeOrgFax;
    }

    public void setNukeOrgFax(String nukeOrgFax) {
        this.nukeOrgFax = nukeOrgFax;
    }

    public String getNukeOrgUrl() {
        return nukeOrgUrl;
    }

    public void setNukeOrgUrl(String nukeOrgUrl) {
        this.nukeOrgUrl = nukeOrgUrl;
    }

    public String getNukeOrgEmail() {
        return nukeOrgEmail;
    }

    public void setNukeOrgEmail(String nukeOrgEmail) {
        this.nukeOrgEmail = nukeOrgEmail;
    }

//    public Boolean getNukeOrgSuborganiser() {
//        return nukeOrgSuborganiser;
//    }
//
//    public void setNukeOrgSuborganiser(Boolean nukeOrgSuborganiser) {
//        this.nukeOrgSuborganiser = nukeOrgSuborganiser;
//    }
//
//    public String getNukeOrgFirstContact() {
//        return nukeOrgFirstContact;
//    }
//
//    public void setNukeOrgFirstContact(String nukeOrgFirstContact) {
//        this.nukeOrgFirstContact = nukeOrgFirstContact;
//    }

    public String getNukeOrgContactHistory() {
        return nukeOrgContactHistory;
    }

    public void setNukeOrgContactHistory(String nukeOrgContactHistory) {
        this.nukeOrgContactHistory = nukeOrgContactHistory;
    }

    public String getNukeOrgTodo() {
        return nukeOrgTodo;
    }

    public void setNukeOrgTodo(String nukeOrgTodo) {
        this.nukeOrgTodo = nukeOrgTodo;
    }

    public String getNukeOrgStatus() {
        return nukeOrgStatus;
    }

    public void setNukeOrgStatus(String nukeOrgStatus) {
        this.nukeOrgStatus = nukeOrgStatus;
    }

//    public String getNukeOrgContactOwner() {
//        return nukeOrgContactOwner;
//    }
//
//    public void setNukeOrgContactOwner(String nukeOrgContactOwner) {
//        this.nukeOrgContactOwner = nukeOrgContactOwner;
//    }

    public Boolean getNukeOrgConcession() {
        return nukeOrgConcession;
    }

    public void setNukeOrgConcession(Boolean nukeOrgConcession) {
        this.nukeOrgConcession = nukeOrgConcession;
    }

    public String getNukeOrgConcessionInfo() {
        return nukeOrgConcessionInfo;
    }

    public void setNukeOrgConcessionInfo(String nukeOrgConcessionInfo) {
        this.nukeOrgConcessionInfo = nukeOrgConcessionInfo;
    }

//    public Boolean getNukeOrgAgb() {
//        return nukeOrgAgb;
//    }
//
//    public void setNukeOrgAgb(Boolean nukeOrgAgb) {
//        this.nukeOrgAgb = nukeOrgAgb;
//    }

    public String getNukeOrgAgbInfo() {
        return nukeOrgAgbInfo;
    }

    public void setNukeOrgAgbInfo(String nukeOrgAgbInfo) {
        this.nukeOrgAgbInfo = nukeOrgAgbInfo;
    }

    public Boolean getNukeOrgInsurance() {
        return nukeOrgInsurance;
    }

    public void setNukeOrgInsurance(Boolean nukeOrgInsurance) {
        this.nukeOrgInsurance = nukeOrgInsurance;
    }

    public String getNukeOrgInsuranceInfo() {
        return nukeOrgInsuranceInfo;
    }

    public void setNukeOrgInsuranceInfo(String nukeOrgInsuranceInfo) {
        this.nukeOrgInsuranceInfo = nukeOrgInsuranceInfo;
    }

//    public String getNukeOrgWayDescription() {
//        return nukeOrgWayDescription;
//    }
//
//    public void setNukeOrgWayDescription(String nukeOrgWayDescription) {
//        this.nukeOrgWayDescription = nukeOrgWayDescription;
//    }

    public String getNukeOrgBankname() {
        return nukeOrgBankname;
    }

    public void setNukeOrgBankname(String nukeOrgBankname) {
        this.nukeOrgBankname = nukeOrgBankname;
    }

    public String getNukeOrgBankaccount() {
        return nukeOrgBankaccount;
    }

    public void setNukeOrgBankaccount(String nukeOrgBankaccount) {
        this.nukeOrgBankaccount = nukeOrgBankaccount;
    }

    public String getNukeOrgBlz() {
        return nukeOrgBlz;
    }

    public void setNukeOrgBlz(String nukeOrgBlz) {
        this.nukeOrgBlz = nukeOrgBlz;
    }

    public String getNukeOrgCompanyFirmbook() {
        return nukeOrgCompanyFirmbook;
    }

    public void setNukeOrgCompanyFirmbook(String nukeOrgCompanyFirmbook) {
        this.nukeOrgCompanyFirmbook = nukeOrgCompanyFirmbook;
    }

    public String getNukeOrgTaxNumber() {
        return nukeOrgTaxNumber;
    }

    public void setNukeOrgTaxNumber(String nukeOrgTaxNumber) {
        this.nukeOrgTaxNumber = nukeOrgTaxNumber;
    }

    public String getNukeOrgTaxUid() {
        return nukeOrgTaxUid;
    }

    public void setNukeOrgTaxUid(String nukeOrgTaxUid) {
        this.nukeOrgTaxUid = nukeOrgTaxUid;
    }

    public String getNukeOrgInsuranceHistory() {
        return nukeOrgInsuranceHistory;
    }

    public void setNukeOrgInsuranceHistory(String nukeOrgInsuranceHistory) {
        this.nukeOrgInsuranceHistory = nukeOrgInsuranceHistory;
    }

    public String getNukeOrgOtherInfo() {
        return nukeOrgOtherInfo;
    }

    public void setNukeOrgOtherInfo(String nukeOrgOtherInfo) {
        this.nukeOrgOtherInfo = nukeOrgOtherInfo;
    }

//    public Integer getNukeOrgLevel() {
//        return nukeOrgLevel;
//    }
//
//    public void setNukeOrgLevel(Integer nukeOrgLevel) {
//        this.nukeOrgLevel = nukeOrgLevel;
//    }

    public String getNukeOrgInfoAvailEvent() {
        return nukeOrgInfoAvailEvent;
    }

    public void setNukeOrgInfoAvailEvent(String nukeOrgInfoAvailEvent) {
        this.nukeOrgInfoAvailEvent = nukeOrgInfoAvailEvent;
    }

    public String getNukeOrgInfoBooking() {
        return nukeOrgInfoBooking;
    }

    public void setNukeOrgInfoBooking(String nukeOrgInfoBooking) {
        this.nukeOrgInfoBooking = nukeOrgInfoBooking;
    }

    public String getNukeOrgInfoOtherInfo() {
        return nukeOrgInfoOtherInfo;
    }

    public void setNukeOrgInfoOtherInfo(String nukeOrgInfoOtherInfo) {
        this.nukeOrgInfoOtherInfo = nukeOrgInfoOtherInfo;
    }

    public Short getNukeOrgAllowedtouseTax() {
        return nukeOrgAllowedtouseTax;
    }

    public void setNukeOrgAllowedtouseTax(Short nukeOrgAllowedtouseTax) {
        this.nukeOrgAllowedtouseTax = nukeOrgAllowedtouseTax;
    }

//    public short getNukeOrgInvoiceneedscheck() {
//        return nukeOrgInvoiceneedscheck;
//    }
//
//    public void setNukeOrgInvoiceneedscheck(short nukeOrgInvoiceneedscheck) {
//        this.nukeOrgInvoiceneedscheck = nukeOrgInvoiceneedscheck;
//    }

    public String getNukeOrgIban() {
        return nukeOrgIban;
    }

    public void setNukeOrgIban(String nukeOrgIban) {
        this.nukeOrgIban = nukeOrgIban;
    }

    public String getNukeOrgBic() {
        return nukeOrgBic;
    }

    public void setNukeOrgBic(String nukeOrgBic) {
        this.nukeOrgBic = nukeOrgBic;
    }

    public int getTermOfPayment() {
        return termOfPayment;
    }

    public void setTermOfPayment(int termOfPayment) {
        this.termOfPayment = termOfPayment;
    }

    public Integer getDeptcondition_day2() {
        return deptcondition_day2;
    }

    public void setDeptcondition_day2(Integer deptcondition_day2) {
        this.deptcondition_day2 = deptcondition_day2;
    }
    public String getDefault_currency() {
        return default_currency;
    }

    public void setDefault_currency(String default_currency) {
        this.default_currency = default_currency;
    }
    
    public String getNukeOrgPlantIsostate() {
        return nuke_org_plant_isostate;
    }

    public void setNukeOrgPlantIsostate(String nuke_org_plant_isostate) {
        this.nuke_org_plant_isostate = nuke_org_plant_isostate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeorgID != null ? nukeorgID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceOrganiser)) {
            return false;
        }
        NukeMrcommerceOrganiser other = (NukeMrcommerceOrganiser) object;
        if ((this.nukeorgID == null && other.nukeorgID != null) || (this.nukeorgID != null && !this.nukeorgID.equals(other.nukeorgID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceOrganiser[nukeorgID=" + nukeorgID + "]";
    }

}
