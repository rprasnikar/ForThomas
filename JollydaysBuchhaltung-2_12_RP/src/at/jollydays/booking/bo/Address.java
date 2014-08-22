/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import at.jollydays.booking.Globals;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id")
//    @NamedQuery(name = "Address.findByISOState", query = "SELECT a FROM Address a WHERE a.iSOState = :iSOState"),
//    @NamedQuery(name = "Address.findByPostalCode", query = "SELECT a FROM Address a WHERE a.postalCode = :postalCode"),
//    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
//    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
//    @NamedQuery(name = "Address.findByFirstname", query = "SELECT a FROM Address a WHERE a.firstname = :firstname"),
//    @NamedQuery(name = "Address.findByLastname", query = "SELECT a FROM Address a WHERE a.lastname = :lastname"),
//    @NamedQuery(name = "Address.findByCompanyName", query = "SELECT a FROM Address a WHERE a.companyName = :companyName"),
//    @NamedQuery(name = "Address.findByCompanyName2", query = "SELECT a FROM Address a WHERE a.companyName2 = :companyName2"),
//    @NamedQuery(name = "Address.findByCompanyNameAdditional", query = "SELECT a FROM Address a WHERE a.companyNameAdditional = :companyNameAdditional"),
//    @NamedQuery(name = "Address.findByPosition", query = "SELECT a FROM Address a WHERE a.position = :position"),
//    @NamedQuery(name = "Address.findByFon", query = "SELECT a FROM Address a WHERE a.fon = :fon"),
//    @NamedQuery(name = "Address.findByMobile", query = "SELECT a FROM Address a WHERE a.mobile = :mobile"),
//    @NamedQuery(name = "Address.findByEmail", query = "SELECT a FROM Address a WHERE a.email = :email"),
//    @NamedQuery(name = "Address.findBySalutation", query = "SELECT a FROM Address a WHERE a.salutation = :salutation"),
//    @NamedQuery(name = "Address.findByBranch", query = "SELECT a FROM Address a WHERE a.branch = :branch"),
//    @NamedQuery(name = "Address.findByTransferDate", query = "SELECT a FROM Address a WHERE a.transferDate = :transferDate"),
//    @NamedQuery(name = "Address.findByTitle", query = "SELECT a FROM Address a WHERE a.title = :title")
})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ISOState")
    private String iSOState;
    @Basic(optional = false)
    @Column(name = "PostalCode")
    private String postalCode;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @Column(name = "Street")
    private String street;
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Lastname")
    private String lastname;
    @Column(name = "CompanyName")
    private String companyName;
    @Column(name = "CompanyName2")
    private String companyName2;
    @Column(name = "CompanyNameAdditional")
    private String companyNameAdditional;
    @Column(name = "Position")
    private String position;
    @Column(name = "Fon")
    private String fon;
    @Column(name = "Mobile")
    private String mobile;
    @Column(name = "Email")
    private String email;
    @Column(name = "Salutation")
    private String salutation;
    @Column(name = "Branch")
    private String branch;
    @Column(name = "TransferDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;
    @Column(name = "Title")
    private String title;
    //@JoinColumn(name = "ISOState", referencedColumnName = "iso_code")
    //@ManyToOne
    //private NukeMrcommerceCountry country;


    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Integer id, String iSOState, String postalCode, String city, String street) {
        this.id = id;
        this.iSOState = iSOState;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISOState() {
        return iSOState;
    }

    public void setISOState(String iSOState) {
        this.iSOState = iSOState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName2() {
        return companyName2;
    }

    public void setCompanyName2(String companyName2) {
        this.companyName2 = companyName2;
    }

    public String getCompanyNameAdditional() {
        return companyNameAdditional;
    }

    public void setCompanyNameAdditional(String companyNameAdditional) {
        this.companyNameAdditional = companyNameAdditional;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFon() {
        return fon;
    }

    public void setFon(String fon) {
        this.fon = fon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getSalutationCode() {
        if (salutation.equalsIgnoreCase("Herr"))
            return "1";
        if (salutation.equalsIgnoreCase("Frau"))
            return "2";
        if (companyName.trim().length() > 0)
            return "3";
        return Globals.EMPTYSTRING;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleText() {
        if (title == null)
            return Globals.EMPTYSTRING;
        
        String title = this.title.trim();
        if (title.equals("1"))
            return "Dipl. Ing.";
        if (title.equals("2"))
            return "Dr.";
        if (title.equals("3"))
            return "Prof.";
        if (title.equals("4"))
            return "Prof. Dr.";
        if (title.equals("5"))
            return "Mag.";
        return Globals.EMPTYSTRING;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*
    public NukeMrcommerceCountry getCountry() {
        return country;
    }

    public void setCountry (NukeMrcommerceCountry country) {
        this.country = country;
    }
     * *
     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.Address[id=" + id + "]";
    }

}
