/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gunter Reinitzer
 */
@Embeddable
public class NukeMrcommerceEventsArrangementCitiesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "event_id")
    private int eventId;
    @Basic(optional = false)
    @Column(name = "arrangement_to_city_id")
    private int arrangementToCityId;

    public NukeMrcommerceEventsArrangementCitiesPK() {
    }

    public NukeMrcommerceEventsArrangementCitiesPK(int eventId, int arrangementToCityId) {
        this.eventId = eventId;
        this.arrangementToCityId = arrangementToCityId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getArrangementToCityId() {
        return arrangementToCityId;
    }

    public void setArrangementToCityId(int arrangementToCityId) {
        this.arrangementToCityId = arrangementToCityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) eventId;
        hash += (int) arrangementToCityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceEventsArrangementCitiesPK)) {
            return false;
        }
        NukeMrcommerceEventsArrangementCitiesPK other = (NukeMrcommerceEventsArrangementCitiesPK) object;
        if (this.eventId != other.eventId) {
            return false;
        }
        if (this.arrangementToCityId != other.arrangementToCityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceEventsArrangementCitiesPK[eventId=" + eventId + ", arrangementToCityId=" + arrangementToCityId + "]";
    }

}
