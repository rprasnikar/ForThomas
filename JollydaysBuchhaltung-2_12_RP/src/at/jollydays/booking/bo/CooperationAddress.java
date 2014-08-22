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
@Table(name = "CooperationAddress")
@NamedQueries({
    @NamedQuery(name = "CooperationAddress.findAll", query = "SELECT c FROM CooperationAddress c"),
    @NamedQuery(name = "CooperationAddress.findById", query = "SELECT c FROM CooperationAddress c WHERE c.id = :id")
//    @NamedQuery(name = "CooperationAddress.findByISOState", query = "SELECT c FROM CooperationAddress c WHERE c.iSOState = :iSOState"),
//    @NamedQuery(name = "CooperationAddress.findByPostalCode", query = "SELECT c FROM CooperationAddress c WHERE c.postalCode = :postalCode"),
//    @NamedQuery(name = "CooperationAddress.findByCity", query = "SELECT c FROM CooperationAddress c WHERE c.city = :city"),
//    @NamedQuery(name = "CooperationAddress.findByStreet", query = "SELECT c FROM CooperationAddress c WHERE c.street = :street"),
//    @NamedQuery(name = "CooperationAddress.findByFirstname", query = "SELECT c FROM CooperationAddress c WHERE c.firstname = :firstname"),
//    @NamedQuery(name = "CooperationAddress.findByLastname", query = "SELECT c FROM CooperationAddress c WHERE c.lastname = :lastname"),
//    @NamedQuery(name = "CooperationAddress.findByCompanyName", query = "SELECT c FROM CooperationAddress c WHERE c.companyName = :companyName"),
//    @NamedQuery(name = "CooperationAddress.findByCompanyName2", query = "SELECT c FROM CooperationAddress c WHERE c.companyName2 = :companyName2"),
//    @NamedQuery(name = "CooperationAddress.findByCompanyNameAdditional", query = "SELECT c FROM CooperationAddress c WHERE c.companyNameAdditional = :companyNameAdditional"),
//    @NamedQuery(name = "CooperationAddress.findByPosition", query = "SELECT c FROM CooperationAddress c WHERE c.position = :position"),
//    @NamedQuery(name = "CooperationAddress.findByFon", query = "SELECT c FROM CooperationAddress c WHERE c.fon = :fon"),
//    @NamedQuery(name = "CooperationAddress.findByMobile", query = "SELECT c FROM CooperationAddress c WHERE c.mobile = :mobile"),
//    @NamedQuery(name = "CooperationAddress.findByEmail", query = "SELECT c FROM CooperationAddress c WHERE c.email = :email"),
//    @NamedQuery(name = "CooperationAddress.findBySalutation", query = "SELECT c FROM CooperationAddress c WHERE c.salutation = :salutation"),
//    @NamedQuery(name = "CooperationAddress.findByBranch", query = "SELECT c FROM CooperationAddress c WHERE c.branch = :branch")
})
public class CooperationAddress implements Serializable {
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

    public CooperationAddress() {
    }

    public CooperationAddress(Integer id) {
        this.id = id;
    }

    public CooperationAddress(Integer id, String iSOState, String postalCode, String city, String street) {
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
        if (!(object instanceof CooperationAddress)) {
            return false;
        }
        CooperationAddress other = (CooperationAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.CooperationAddress[id=" + id + "]";
    }

}
