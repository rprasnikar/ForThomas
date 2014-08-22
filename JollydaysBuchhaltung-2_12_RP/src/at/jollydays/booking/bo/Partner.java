/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import at.jollydays.booking.Globals;
import at.jollydays.booking.control.BookingController;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Partner")
@NamedQueries({
    @NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p"),
    @NamedQuery(name = "Partner.findNext", query = "SELECT p FROM Partner p WHERE p.id >= :id order by p.id"),
    @NamedQuery(name = "Partner.findById", query = "SELECT p FROM Partner p WHERE p.id = :id")
//    @NamedQuery(name = "Partner.findByPartnerValidFrom", query = "SELECT p FROM Partner p WHERE p.partnerValidFrom = :partnerValidFrom"),
//    @NamedQuery(name = "Partner.findByPartnersegment", query = "SELECT p FROM Partner p WHERE p.partnersegment = :partnersegment"),
//    @NamedQuery(name = "Partner.findByUIDNumber", query = "SELECT p FROM Partner p WHERE p.uIDNumber = :uIDNumber"),
//    @NamedQuery(name = "Partner.findByFNNumber", query = "SELECT p FROM Partner p WHERE p.fNNumber = :fNNumber"),
//    @NamedQuery(name = "Partner.findBySex", query = "SELECT p FROM Partner p WHERE p.sex = :sex"),
//    @NamedQuery(name = "Partner.findByEMail", query = "SELECT p FROM Partner p WHERE p.eMail = :eMail"),
//    @NamedQuery(name = "Partner.findByPaymentType", query = "SELECT p FROM Partner p WHERE p.paymentType = :paymentType"),
//    @NamedQuery(name = "Partner.findByBICCode", query = "SELECT p FROM Partner p WHERE p.bICCode = :bICCode"),
//    @NamedQuery(name = "Partner.findByBankName", query = "SELECT p FROM Partner p WHERE p.bankName = :bankName"),
//    @NamedQuery(name = "Partner.findByBankCode", query = "SELECT p FROM Partner p WHERE p.bankCode = :bankCode"),
//    @NamedQuery(name = "Partner.findByIBANNumber", query = "SELECT p FROM Partner p WHERE p.iBANNumber = :iBANNumber"),
//    @NamedQuery(name = "Partner.findByAccountNumber", query = "SELECT p FROM Partner p WHERE p.accountNumber = :accountNumber"),
//    @NamedQuery(name = "Partner.findByAccountOwner", query = "SELECT p FROM Partner p WHERE p.accountOwner = :accountOwner"),
//    @NamedQuery(name = "Partner.findByIdentifier", query = "SELECT p FROM Partner p WHERE p.identifier = :identifier"),
//    @NamedQuery(name = "Partner.findByPassword", query = "SELECT p FROM Partner p WHERE p.password = :password"),
//    @NamedQuery(name = "Partner.findByAddressId", query = "SELECT p FROM Partner p WHERE p.addressId = :addressId"),
//    @NamedQuery(name = "Partner.findByReceiveNewsletter", query = "SELECT p FROM Partner p WHERE p.receiveNewsletter = :receiveNewsletter"),
//    @NamedQuery(name = "Partner.findByHowToHearFromUs", query = "SELECT p FROM Partner p WHERE p.howToHearFromUs = :howToHearFromUs"),
//    @NamedQuery(name = "Partner.findByLastLogin", query = "SELECT p FROM Partner p WHERE p.lastLogin = :lastLogin"),
//    @NamedQuery(name = "Partner.findByIsActive", query = "SELECT p FROM Partner p WHERE p.isActive = :isActive"),
//    @NamedQuery(name = "Partner.findByHasLoggedOutCorrectly", query = "SELECT p FROM Partner p WHERE p.hasLoggedOutCorrectly = :hasLoggedOutCorrectly")
})
public class Partner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PartnerValidFrom")
    @Temporal(TemporalType.DATE)
    private Date partnerValidFrom;
    @Column(name = "Partnersegment")
    private String partnersegment;
    @Column(name = "UIDNumber")
    private String uIDNumber;
    @Column(name = "FNNumber")
    private String fNNumber;
    @Column(name = "Sex")
    private Character sex;
    @Column(name = "EMail")
    private String eMail;
    @Column(name = "PaymentType")
    private String paymentType;
    @Column(name = "BICCode")
    private String bICCode;
    @Column(name = "BankName")
    private String bankName;
    @Column(name = "BankCode")
    private String bankCode;
    @Column(name = "IBANNumber")
    private String iBANNumber;
    @Column(name = "AccountNumber")
    private String accountNumber;
    @Column(name = "AccountOwner")
    private String accountOwner;
    @Basic(optional = false)
    @Column(name = "Identifier")
    private String identifier;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Column(name = "AddressId")
    private Integer addressId;
    @Column(name = "ReceiveNewsletter")
    private Boolean receiveNewsletter;
    @Column(name = "HowToHearFromUs")
    private Short howToHearFromUs;
    @Column(name = "LastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Basic(optional = false)
    @Column(name = "IsActive")
    private short isActive;
    @Column(name = "HasLoggedOutCorrectly")
    private Boolean hasLoggedOutCorrectly;
    @JoinColumn(name = "AddressId", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Address address;


    public Partner() {
    }

    public Partner(Integer id) {
        this.id = id;
    }

    public Partner(Integer id, Date partnerValidFrom, String identifier, String password, short isActive) {
        this.id = id;
        this.partnerValidFrom = partnerValidFrom;
        this.identifier = identifier;
        this.password = password;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPartnerValidFrom() {
        return partnerValidFrom;
    }

    public void setPartnerValidFrom(Date partnerValidFrom) {
        this.partnerValidFrom = partnerValidFrom;
    }

    public String getPartnersegment() {
        return partnersegment;
    }

    public void setPartnersegment(String partnersegment) {
        this.partnersegment = partnersegment;
    }

    public String getUIDNumber() {
        if (uIDNumber != null && uIDNumber.length() >= 2) {
            if (Character.isLetter(uIDNumber.charAt(0)) && Character.isLetter(uIDNumber.charAt(1))) {
                return uIDNumber;
            }
        }

        return Globals.EMPTYSTRING;
    }

    public void setUIDNumber(String uIDNumber) {
        this.uIDNumber = uIDNumber;
    }

    public String getFNNumber() {
        return fNNumber;
    }

    public void setFNNumber(String fNNumber) {
        this.fNNumber = fNNumber;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getBICCode() {
        return bICCode;
    }

    public void setBICCode(String bICCode) {
        this.bICCode = bICCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getIBANNumber() {
        return iBANNumber;
    }

    public void setIBANNumber(String iBANNumber) {
        this.iBANNumber = iBANNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Boolean getReceiveNewsletter() {
        return receiveNewsletter;
    }

    public void setReceiveNewsletter(Boolean receiveNewsletter) {
        this.receiveNewsletter = receiveNewsletter;
    }

    public Short getHowToHearFromUs() {
        return howToHearFromUs;
    }

    public void setHowToHearFromUs(Short howToHearFromUs) {
        this.howToHearFromUs = howToHearFromUs;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public Boolean getHasLoggedOutCorrectly() {
        return hasLoggedOutCorrectly;
    }

    public void setHasLoggedOutCorrectly(Boolean hasLoggedOutCorrectly) {
        this.hasLoggedOutCorrectly = hasLoggedOutCorrectly;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (!(object instanceof Partner)) {
            return false;
        }
        Partner other = (Partner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.Partner[id=" + id + "]";
    }

}
