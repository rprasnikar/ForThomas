/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "OrganiserAddress")
@NamedQueries({
    @NamedQuery(name = "OrganiserAddress.findAll", query = "SELECT o FROM OrganiserAddress o"),
    @NamedQuery(name = "OrganiserAddress.findById", query = "SELECT o FROM OrganiserAddress o WHERE o.id = :id")
//    @NamedQuery(name = "OrganiserAddress.findByISOState", query = "SELECT o FROM OrganiserAddress o WHERE o.iSOState = :iSOState"),
//    @NamedQuery(name = "OrganiserAddress.findByPostalCode", query = "SELECT o FROM OrganiserAddress o WHERE o.postalCode = :postalCode"),
//    @NamedQuery(name = "OrganiserAddress.findByCity", query = "SELECT o FROM OrganiserAddress o WHERE o.city = :city"),
//    @NamedQuery(name = "OrganiserAddress.findByStreet", query = "SELECT o FROM OrganiserAddress o WHERE o.street = :street"),
//    @NamedQuery(name = "OrganiserAddress.findByFirstname", query = "SELECT o FROM OrganiserAddress o WHERE o.firstname = :firstname"),
//    @NamedQuery(name = "OrganiserAddress.findByLastname", query = "SELECT o FROM OrganiserAddress o WHERE o.lastname = :lastname"),
//    @NamedQuery(name = "OrganiserAddress.findByCompanyName", query = "SELECT o FROM OrganiserAddress o WHERE o.companyName = :companyName"),
//    @NamedQuery(name = "OrganiserAddress.findByCompanyName2", query = "SELECT o FROM OrganiserAddress o WHERE o.companyName2 = :companyName2"),
//    @NamedQuery(name = "OrganiserAddress.findByCompanyNameAdditional", query = "SELECT o FROM OrganiserAddress o WHERE o.companyNameAdditional = :companyNameAdditional"),
//    @NamedQuery(name = "OrganiserAddress.findByPosition", query = "SELECT o FROM OrganiserAddress o WHERE o.position = :position"),
//    @NamedQuery(name = "OrganiserAddress.findByFon", query = "SELECT o FROM OrganiserAddress o WHERE o.fon = :fon"),
//    @NamedQuery(name = "OrganiserAddress.findByMobile", query = "SELECT o FROM OrganiserAddress o WHERE o.mobile = :mobile"),
//    @NamedQuery(name = "OrganiserAddress.findByEmail", query = "SELECT o FROM OrganiserAddress o WHERE o.email = :email"),
//    @NamedQuery(name = "OrganiserAddress.findBySalutation", query = "SELECT o FROM OrganiserAddress o WHERE o.salutation = :salutation"),
//    @NamedQuery(name = "OrganiserAddress.findByBranch", query = "SELECT o FROM OrganiserAddress o WHERE o.branch = :branch")
})
public class OrganiserAddress implements Serializable {
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

    public OrganiserAddress() {
    }

    public OrganiserAddress(Integer id) {
        this.id = id;
    }

    public OrganiserAddress(Integer id, String iSOState, String postalCode, String city, String street) {
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

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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
        if (!(object instanceof OrganiserAddress)) {
            return false;
        }
        OrganiserAddress other = (OrganiserAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.OrganiserAddress[id=" + id + "]";
    }

}
