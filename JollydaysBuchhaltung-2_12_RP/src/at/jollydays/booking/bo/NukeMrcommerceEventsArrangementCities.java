/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_events_arrangement_cities")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceEventsArrangementCities.findAll", query = "SELECT n FROM NukeMrcommerceEventsArrangementCities n"),
    @NamedQuery(name = "NukeMrcommerceEventsArrangementCities.findByEventId", query = "SELECT n FROM NukeMrcommerceEventsArrangementCities n WHERE n.nukeMrcommerceEventsArrangementCitiesPK.eventId = :eventId"),
    @NamedQuery(name = "NukeMrcommerceEventsArrangementCities.findByArrangementToCityId", query = "SELECT n FROM NukeMrcommerceEventsArrangementCities n WHERE n.nukeMrcommerceEventsArrangementCitiesPK.arrangementToCityId = :arrangementToCityId")})
public class NukeMrcommerceEventsArrangementCities implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NukeMrcommerceEventsArrangementCitiesPK nukeMrcommerceEventsArrangementCitiesPK;
    //@JoinColumn(name = "arrangement_to_city_id", referencedColumnName = "ID", insertable = false, updatable = false)
    //@ManyToOne(optional = false)
    //private NukeMrcommerceArrangementCity nukeMrcommerceArrangementCity;

    public NukeMrcommerceEventsArrangementCities() {
    }

    public NukeMrcommerceEventsArrangementCities(NukeMrcommerceEventsArrangementCitiesPK nukeMrcommerceEventsArrangementCitiesPK) {
        this.nukeMrcommerceEventsArrangementCitiesPK = nukeMrcommerceEventsArrangementCitiesPK;
    }

    public NukeMrcommerceEventsArrangementCities(int eventId, int arrangementToCityId) {
        this.nukeMrcommerceEventsArrangementCitiesPK = new NukeMrcommerceEventsArrangementCitiesPK(eventId, arrangementToCityId);
    }

    public NukeMrcommerceEventsArrangementCitiesPK getNukeMrcommerceEventsArrangementCitiesPK() {
        return nukeMrcommerceEventsArrangementCitiesPK;
    }

    public void setNukeMrcommerceEventsArrangementCitiesPK(NukeMrcommerceEventsArrangementCitiesPK nukeMrcommerceEventsArrangementCitiesPK) {
        this.nukeMrcommerceEventsArrangementCitiesPK = nukeMrcommerceEventsArrangementCitiesPK;
    }

//    public NukeMrcommerceArrangementCity getNukeMrcommerceArrangementCity() {
//        return nukeMrcommerceArrangementCity;
//    }
//
//    public void setNukeMrcommerceArrangementCity(NukeMrcommerceArrangementCity nukeMrcommerceArrangementCity) {
//        this.nukeMrcommerceArrangementCity = nukeMrcommerceArrangementCity;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeMrcommerceEventsArrangementCitiesPK != null ? nukeMrcommerceEventsArrangementCitiesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceEventsArrangementCities)) {
            return false;
        }
        NukeMrcommerceEventsArrangementCities other = (NukeMrcommerceEventsArrangementCities) object;
        if ((this.nukeMrcommerceEventsArrangementCitiesPK == null && other.nukeMrcommerceEventsArrangementCitiesPK != null) || (this.nukeMrcommerceEventsArrangementCitiesPK != null && !this.nukeMrcommerceEventsArrangementCitiesPK.equals(other.nukeMrcommerceEventsArrangementCitiesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceEventsArrangementCities[nukeMrcommerceEventsArrangementCitiesPK=" + nukeMrcommerceEventsArrangementCitiesPK + "]";
    }

}
