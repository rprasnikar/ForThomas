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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_address")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceAddress.findAll", query = "SELECT n FROM NukeMrcommerceAddress n"),
    @NamedQuery(name = "NukeMrcommerceAddress.findById", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.id = :id")
//    @NamedQuery(name = "NukeMrcommerceAddress.findByDescription", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.description = :description"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByStreet", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.street = :street"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByZip", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.zip = :zip"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByCity", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.city = :city"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByRoutingUrl", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.routingUrl = :routingUrl"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByFvvTel", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.fvvTel = :fvvTel"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByFvvEmail", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.fvvEmail = :fvvEmail"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByExternalDescription", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.externalDescription = :externalDescription"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByStatusDelete20080114", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.statusDelete20080114 = :statusDelete20080114"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByLevel", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.level = :level"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByExternalDescriptionLink", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.externalDescriptionLink = :externalDescriptionLink"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByLongitude", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.longitude = :longitude"),
//    @NamedQuery(name = "NukeMrcommerceAddress.findByLatitude", query = "SELECT n FROM NukeMrcommerceAddress n WHERE n.latitude = :latitude")
})
public class NukeMrcommerceAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "street")
    private String street;
    @Column(name = "zip")
    private String zip;
    @Column(name = "city")
    private String city;
    @Lob
    @Column(name = "tour")
    private String tour;
    @Column(name = "routing_url")
    private String routingUrl;
    @Column(name = "fvv_tel")
    private String fvvTel;
    @Column(name = "fvv_email")
    private String fvvEmail;
    @Lob
    @Column(name = "info_cc")
    private String infoCc;
    @Column(name = "external_description")
    private String externalDescription;
    @Column(name = "status_delete_20080114")
    private Integer statusDelete20080114;
//    @Column(name = "level")
//    private Integer level;
//    @Column(name = "external_description_link")
//    private String externalDescriptionLink;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "latitude")
    private Float latitude;
    @JoinColumn(name = "country", referencedColumnName = "iso_code")
    @ManyToOne
    private NukeMrcommerceCountry nukeMrcommerceCountry;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne
    private NukeMrcommerceState nukeMrcommerceState;

    public NukeMrcommerceAddress() {
    }

    public NukeMrcommerceAddress(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getRoutingUrl() {
        return routingUrl;
    }

    public void setRoutingUrl(String routingUrl) {
        this.routingUrl = routingUrl;
    }

    public String getFvvTel() {
        return fvvTel;
    }

    public void setFvvTel(String fvvTel) {
        this.fvvTel = fvvTel;
    }

    public String getFvvEmail() {
        return fvvEmail;
    }

    public void setFvvEmail(String fvvEmail) {
        this.fvvEmail = fvvEmail;
    }

    public String getInfoCc() {
        return infoCc;
    }

    public void setInfoCc(String infoCc) {
        this.infoCc = infoCc;
    }

    public String getExternalDescription() {
        return externalDescription;
    }

    public void setExternalDescription(String externalDescription) {
        this.externalDescription = externalDescription;
    }

    public Integer getStatusDelete20080114() {
        return statusDelete20080114;
    }

    public void setStatusDelete20080114(Integer statusDelete20080114) {
        this.statusDelete20080114 = statusDelete20080114;
    }

//    public Integer getLevel() {
//        return level;
//    }
//
//    public void setLevel(Integer level) {
//        this.level = level;
//    }

//    public String getExternalDescriptionLink() {
//        return externalDescriptionLink;
//    }
//
//    public void setExternalDescriptionLink(String externalDescriptionLink) {
//        this.externalDescriptionLink = externalDescriptionLink;
//    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public NukeMrcommerceCountry getNukeMrcommerceCountry() {
        return nukeMrcommerceCountry;
    }

    public void setNukeMrcommerceCountry(NukeMrcommerceCountry nukeMrcommerceCountry) {
        this.nukeMrcommerceCountry = nukeMrcommerceCountry;
    }

    public NukeMrcommerceState getNukeMrcommerceState() {
        return nukeMrcommerceState;
    }

    public void setNukeMrcommerceState(NukeMrcommerceState nukeMrcommerceState) {
        this.nukeMrcommerceState = nukeMrcommerceState;
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
        if (!(object instanceof NukeMrcommerceAddress)) {
            return false;
        }
        NukeMrcommerceAddress other = (NukeMrcommerceAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceAddress[id=" + id + "]";
    }

}
