/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_country")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceCountry.findAll", query = "SELECT n FROM NukeMrcommerceCountry n"),
    @NamedQuery(name = "NukeMrcommerceCountry.findByIsoCode", query = "SELECT n FROM NukeMrcommerceCountry n WHERE n.isoCode = :isoCode")
//    @NamedQuery(name = "NukeMrcommerceCountry.findByDescription", query = "SELECT n FROM NukeMrcommerceCountry n WHERE n.description = :description"),
//    @NamedQuery(name = "NukeMrcommerceCountry.findByPostCode", query = "SELECT n FROM NukeMrcommerceCountry n WHERE n.postCode = :postCode"),
//    @NamedQuery(name = "NukeMrcommerceCountry.findBySortId", query = "SELECT n FROM NukeMrcommerceCountry n WHERE n.sortId = :sortId")
})
public class NukeMrcommerceCountry implements Serializable {
    @Column(name = "iso2_code_bmd")
    private String iso2CodeBmd;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iso_code")
    private String isoCode;
    @Column(name = "description")
    private String description;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "sort_id")
    private Short sortId;
    @OneToMany(mappedBy = "nukeMrcommerceCountry")
    private Collection<NukeMrcommerceAddress> nukeMrcommerceAddressCollection;

    public NukeMrcommerceCountry() {
    }

    public NukeMrcommerceCountry(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Short getSortId() {
        return sortId;
    }

    public void setSortId(Short sortId) {
        this.sortId = sortId;
    }

    public Collection<NukeMrcommerceAddress> getNukeMrcommerceAddressCollection() {
        return nukeMrcommerceAddressCollection;
    }

    public void setNukeMrcommerceAddressCollection(Collection<NukeMrcommerceAddress> nukeMrcommerceAddressCollection) {
        this.nukeMrcommerceAddressCollection = nukeMrcommerceAddressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isoCode != null ? isoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceCountry)) {
            return false;
        }
        NukeMrcommerceCountry other = (NukeMrcommerceCountry) object;
        if ((this.isoCode == null && other.isoCode != null) || (this.isoCode != null && !this.isoCode.equals(other.isoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceCountry[isoCode=" + isoCode + "]";
    }

    public String getIso2CodeBmd() {
        return iso2CodeBmd;
    }

    public void setIso2CodeBmd(String iso2CodeBmd) {
        this.iso2CodeBmd = iso2CodeBmd;
    }

}
