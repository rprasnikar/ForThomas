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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "nuke_mrcommerce_arrangement_city")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceArrangementCity.findAll", query = "SELECT n FROM NukeMrcommerceArrangementCity n"),
    @NamedQuery(name = "NukeMrcommerceArrangementCity.findById", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.id = :id")
    //@NamedQuery(name = "NukeMrcommerceArrangementCity.findByAddressID", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.addressID = :addressID"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByAdditionalTimeInfo", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.additionalTimeInfo = :additionalTimeInfo"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByItemIDTMPDELTETE", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.itemIDTMPDELTETE = :itemIDTMPDELTETE"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityIsgutschein", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityIsgutschein = :nukeArrangementcityIsgutschein"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityIsticket", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityIsticket = :nukeArrangementcityIsticket"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityIstakt", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityIstakt = :nukeArrangementcityIstakt"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityIswaitinglist", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityIswaitinglist = :nukeArrangementcityIswaitinglist"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityNotesfororganiser", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityNotesfororganiser = :nukeArrangementcityNotesfororganiser"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityOfflinebookingdate", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityOfflinebookingdate = :nukeArrangementcityOfflinebookingdate"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityTitleDetail", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityTitleDetail = :nukeArrangementcityTitleDetail"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityTaktmin", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityTaktmin = :nukeArrangementcityTaktmin"),
//    @NamedQuery(name = "NukeMrcommerceArrangementCity.findByNukeArrangementcityTaktmax", query = "SELECT n FROM NukeMrcommerceArrangementCity n WHERE n.nukeArrangementcityTaktmax = :nukeArrangementcityTaktmax")
})
public class NukeMrcommerceArrangementCity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    //@Column(name = "address_ID")
    //private Integer addressID;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "additional_time_info")
    private String additionalTimeInfo;
    @Lob
    @Column(name = "nuke_arrangementcity_local_description")
    private String nukeArrangementcityLocalDescription;
    @Lob
    @Column(name = "nuke_arrangementcity_important_info")
    private String nukeArrangementcityImportantInfo;
    @Lob
    @Column(name = "nuke_arrangementcity_userrequirements")
    private String nukeArrangementcityUserrequirements;
    @Lob
    @Column(name = "nuke_arrangementcity_duration_summary")
    private String nukeArrangementcityDurationSummary;
    @Lob
    @Column(name = "nuke_arrangementcity_availability")
    private String nukeArrangementcityAvailability;
    @Lob
    @Column(name = "nuke_arrangementcity_visitors")
    private String nukeArrangementcityVisitors;
    @Lob
    @Column(name = "nuke_arrangementcity_bring_in")
    private String nukeArrangementcityBringIn;
    @Lob
    @Column(name = "nuke_arrangementcity_group_display")
    private String nukeArrangementcityGroupDisplay;
    @Lob
    @Column(name = "nuke_arrangementcity_weather_dependance")
    private String nukeArrangementcityWeatherDependance;
    @Lob
    @Column(name = "nuke_arrangementcity_incl_equipment")
    private String nukeArrangementcityInclEquipment;
    @Lob
    @Column(name = "nuke_arrangementcity_hard_facts")
    private String nukeArrangementcityHardFacts;
    @Column(name = "item_ID_TMP_DELTETE")
    private Integer itemIDTMPDELTETE;
    @Lob
    @Column(name = "nuke_arrangementcity_video")
    private String nukeArrangementcityVideo;
    @Lob
    @Column(name = "nuke_arrangementcity_audio")
    private String nukeArrangementcityAudio;
    @Basic(optional = false)
    @Column(name = "nuke_arrangementcity_isgutschein")
    private int nukeArrangementcityIsgutschein;
    @Basic(optional = false)
    @Column(name = "nuke_arrangementcity_isticket")
    private int nukeArrangementcityIsticket;
    @Basic(optional = false)
    @Column(name = "nuke_arrangementcity_istakt")
    private short nukeArrangementcityIstakt;
    @Basic(optional = false)
    @Column(name = "nuke_arrangementcity_iswaitinglist")
    private short nukeArrangementcityIswaitinglist;
    @Column(name = "nuke_arrangementcity_notesfororganiser")
    private String nukeArrangementcityNotesfororganiser;
    @Column(name = "nuke_arrangementcity_offlinebookingdate")
    @Temporal(TemporalType.DATE)
    private Date nukeArrangementcityOfflinebookingdate;
    @Column(name = "nuke_arrangementcity_title_detail")
    private String nukeArrangementcityTitleDetail;
    @Column(name = "nuke_arrangementcity_taktmin")
    private Integer nukeArrangementcityTaktmin;
    @Column(name = "nuke_arrangementcity_taktmax")
    private Integer nukeArrangementcityTaktmax;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "nukeMrcommerceArrangementCity")
    //private Collection<NukeMrcommerceEventsArrangementCities> nukeMrcommerceEventsArrangementCitiesCollection;
//    @JoinColumn(name = "arrangement_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private NukeMrcommerceArrangement nukeMrcommerceArrangement;
    @JoinColumn(name = "item_ID", referencedColumnName = "nuke_item_id")
    @ManyToOne
    private NukeMrcommerceItems nukeMrcommerceItems;
    @JoinColumn(name = "address_ID", referencedColumnName = "ID")
    @ManyToOne
    private NukeMrcommerceAddress nukeMrcommerceAddress;

    public NukeMrcommerceArrangementCity() {
    }

    public NukeMrcommerceArrangementCity(Integer id) {
        this.id = id;
    }

    public NukeMrcommerceArrangementCity(Integer id, int nukeArrangementcityIsgutschein, int nukeArrangementcityIsticket, short nukeArrangementcityIstakt, short nukeArrangementcityIswaitinglist) {
        this.id = id;
        this.nukeArrangementcityIsgutschein = nukeArrangementcityIsgutschein;
        this.nukeArrangementcityIsticket = nukeArrangementcityIsticket;
        this.nukeArrangementcityIstakt = nukeArrangementcityIstakt;
        this.nukeArrangementcityIswaitinglist = nukeArrangementcityIswaitinglist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*
    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }
     * 
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalTimeInfo() {
        return additionalTimeInfo;
    }

    public void setAdditionalTimeInfo(String additionalTimeInfo) {
        this.additionalTimeInfo = additionalTimeInfo;
    }

    public String getNukeArrangementcityLocalDescription() {
        return nukeArrangementcityLocalDescription;
    }

    public void setNukeArrangementcityLocalDescription(String nukeArrangementcityLocalDescription) {
        this.nukeArrangementcityLocalDescription = nukeArrangementcityLocalDescription;
    }

    public String getNukeArrangementcityImportantInfo() {
        return nukeArrangementcityImportantInfo;
    }

    public void setNukeArrangementcityImportantInfo(String nukeArrangementcityImportantInfo) {
        this.nukeArrangementcityImportantInfo = nukeArrangementcityImportantInfo;
    }

    public String getNukeArrangementcityUserrequirements() {
        return nukeArrangementcityUserrequirements;
    }

    public void setNukeArrangementcityUserrequirements(String nukeArrangementcityUserrequirements) {
        this.nukeArrangementcityUserrequirements = nukeArrangementcityUserrequirements;
    }

    public String getNukeArrangementcityDurationSummary() {
        return nukeArrangementcityDurationSummary;
    }

    public void setNukeArrangementcityDurationSummary(String nukeArrangementcityDurationSummary) {
        this.nukeArrangementcityDurationSummary = nukeArrangementcityDurationSummary;
    }

    public String getNukeArrangementcityAvailability() {
        return nukeArrangementcityAvailability;
    }

    public void setNukeArrangementcityAvailability(String nukeArrangementcityAvailability) {
        this.nukeArrangementcityAvailability = nukeArrangementcityAvailability;
    }

    public String getNukeArrangementcityVisitors() {
        return nukeArrangementcityVisitors;
    }

    public void setNukeArrangementcityVisitors(String nukeArrangementcityVisitors) {
        this.nukeArrangementcityVisitors = nukeArrangementcityVisitors;
    }

    public String getNukeArrangementcityBringIn() {
        return nukeArrangementcityBringIn;
    }

    public void setNukeArrangementcityBringIn(String nukeArrangementcityBringIn) {
        this.nukeArrangementcityBringIn = nukeArrangementcityBringIn;
    }

    public String getNukeArrangementcityGroupDisplay() {
        return nukeArrangementcityGroupDisplay;
    }

    public void setNukeArrangementcityGroupDisplay(String nukeArrangementcityGroupDisplay) {
        this.nukeArrangementcityGroupDisplay = nukeArrangementcityGroupDisplay;
    }

    public String getNukeArrangementcityWeatherDependance() {
        return nukeArrangementcityWeatherDependance;
    }

    public void setNukeArrangementcityWeatherDependance(String nukeArrangementcityWeatherDependance) {
        this.nukeArrangementcityWeatherDependance = nukeArrangementcityWeatherDependance;
    }

    public String getNukeArrangementcityInclEquipment() {
        return nukeArrangementcityInclEquipment;
    }

    public void setNukeArrangementcityInclEquipment(String nukeArrangementcityInclEquipment) {
        this.nukeArrangementcityInclEquipment = nukeArrangementcityInclEquipment;
    }

    public String getNukeArrangementcityHardFacts() {
        return nukeArrangementcityHardFacts;
    }

    public void setNukeArrangementcityHardFacts(String nukeArrangementcityHardFacts) {
        this.nukeArrangementcityHardFacts = nukeArrangementcityHardFacts;
    }

    public Integer getItemIDTMPDELTETE() {
        return itemIDTMPDELTETE;
    }

    public void setItemIDTMPDELTETE(Integer itemIDTMPDELTETE) {
        this.itemIDTMPDELTETE = itemIDTMPDELTETE;
    }

    public String getNukeArrangementcityVideo() {
        return nukeArrangementcityVideo;
    }

    public void setNukeArrangementcityVideo(String nukeArrangementcityVideo) {
        this.nukeArrangementcityVideo = nukeArrangementcityVideo;
    }

    public String getNukeArrangementcityAudio() {
        return nukeArrangementcityAudio;
    }

    public void setNukeArrangementcityAudio(String nukeArrangementcityAudio) {
        this.nukeArrangementcityAudio = nukeArrangementcityAudio;
    }

    public int getNukeArrangementcityIsgutschein() {
        return nukeArrangementcityIsgutschein;
    }

    public void setNukeArrangementcityIsgutschein(int nukeArrangementcityIsgutschein) {
        this.nukeArrangementcityIsgutschein = nukeArrangementcityIsgutschein;
    }

    public int getNukeArrangementcityIsticket() {
        return nukeArrangementcityIsticket;
    }

    public void setNukeArrangementcityIsticket(int nukeArrangementcityIsticket) {
        this.nukeArrangementcityIsticket = nukeArrangementcityIsticket;
    }

    public short getNukeArrangementcityIstakt() {
        return nukeArrangementcityIstakt;
    }

    public void setNukeArrangementcityIstakt(short nukeArrangementcityIstakt) {
        this.nukeArrangementcityIstakt = nukeArrangementcityIstakt;
    }

    public short getNukeArrangementcityIswaitinglist() {
        return nukeArrangementcityIswaitinglist;
    }

    public void setNukeArrangementcityIswaitinglist(short nukeArrangementcityIswaitinglist) {
        this.nukeArrangementcityIswaitinglist = nukeArrangementcityIswaitinglist;
    }

    public String getNukeArrangementcityNotesfororganiser() {
        return nukeArrangementcityNotesfororganiser;
    }

    public void setNukeArrangementcityNotesfororganiser(String nukeArrangementcityNotesfororganiser) {
        this.nukeArrangementcityNotesfororganiser = nukeArrangementcityNotesfororganiser;
    }

    public Date getNukeArrangementcityOfflinebookingdate() {
        return nukeArrangementcityOfflinebookingdate;
    }

    public void setNukeArrangementcityOfflinebookingdate(Date nukeArrangementcityOfflinebookingdate) {
        this.nukeArrangementcityOfflinebookingdate = nukeArrangementcityOfflinebookingdate;
    }

    public String getNukeArrangementcityTitleDetail() {
        return nukeArrangementcityTitleDetail;
    }

    public void setNukeArrangementcityTitleDetail(String nukeArrangementcityTitleDetail) {
        this.nukeArrangementcityTitleDetail = nukeArrangementcityTitleDetail;
    }

    public Integer getNukeArrangementcityTaktmin() {
        return nukeArrangementcityTaktmin;
    }

    public void setNukeArrangementcityTaktmin(Integer nukeArrangementcityTaktmin) {
        this.nukeArrangementcityTaktmin = nukeArrangementcityTaktmin;
    }

    public Integer getNukeArrangementcityTaktmax() {
        return nukeArrangementcityTaktmax;
    }

    public void setNukeArrangementcityTaktmax(Integer nukeArrangementcityTaktmax) {
        this.nukeArrangementcityTaktmax = nukeArrangementcityTaktmax;
    }

//    public Collection<NukeMrcommerceEventsArrangementCities> getNukeMrcommerceEventsArrangementCitiesCollection() {
//        return nukeMrcommerceEventsArrangementCitiesCollection;
//    }
//
//    public void setNukeMrcommerceEventsArrangementCitiesCollection(Collection<NukeMrcommerceEventsArrangementCities> nukeMrcommerceEventsArrangementCitiesCollection) {
//        this.nukeMrcommerceEventsArrangementCitiesCollection = nukeMrcommerceEventsArrangementCitiesCollection;
//    }


    public NukeMrcommerceItems getNukeMrcommerceItems() {
        return nukeMrcommerceItems;
    }

    public void setNukeMrcommerceItems(NukeMrcommerceItems nukeMrcommerceItems) {
        this.nukeMrcommerceItems = nukeMrcommerceItems;
    }
    
    public NukeMrcommerceAddress getNukeMrcommerceAddress() {
        return nukeMrcommerceAddress;
    }

    public void setNukeMrcommerceAddress(NukeMrcommerceAddress nukeMrcommerceAddress) {
        this.nukeMrcommerceAddress = nukeMrcommerceAddress;
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
        if (!(object instanceof NukeMrcommerceArrangementCity)) {
            return false;
        }
        NukeMrcommerceArrangementCity other = (NukeMrcommerceArrangementCity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceArrangementCity[id=" + id + "]";
    }

}
