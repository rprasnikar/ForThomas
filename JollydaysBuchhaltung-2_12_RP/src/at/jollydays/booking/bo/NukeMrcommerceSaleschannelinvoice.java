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
@Table(name = "nuke_mrcommerce_saleschannelinvoice")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findAll", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n"),
    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceInvoice", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceInvoice = :nukeSaleschannelinvoiceInvoice"),
    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceChannel", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceChannel = :nukeSaleschannelinvoiceChannel"),
    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceCooperationid", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceCooperationid = :nukeSaleschannelinvoiceCooperationid")
//    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceReferrer", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceReferrer = :nukeSaleschannelinvoiceReferrer"),
//    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceAffiliatenw", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceAffiliatenw = :nukeSaleschannelinvoiceAffiliatenw"),
//    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceAffiliatepartner", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceAffiliatepartner = :nukeSaleschannelinvoiceAffiliatepartner"),
//    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceSaleschannellogsession", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceSaleschannellogsession = :nukeSaleschannelinvoiceSaleschannellogsession"),
//    @NamedQuery(name = "NukeMrcommerceSaleschannelinvoice.findByNukeSaleschannelinvoiceIpaddress", query = "SELECT n FROM NukeMrcommerceSaleschannelinvoice n WHERE n.nukeSaleschannelinvoiceIpaddress = :nukeSaleschannelinvoiceIpaddress")
})
public class NukeMrcommerceSaleschannelinvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_saleschannelinvoice_invoice")
    private Integer nukeSaleschannelinvoiceInvoice;
    @Basic(optional = false)
    @Column(name = "nuke_saleschannelinvoice_channel")
    private int nukeSaleschannelinvoiceChannel;
    @Column(name = "nuke_saleschannelinvoice_cooperationid")
    private Integer nukeSaleschannelinvoiceCooperationid = new Integer(0);
    @Column(name = "nuke_saleschannelinvoice_referrer")
    private String nukeSaleschannelinvoiceReferrer = "";
    @Column(name = "nuke_saleschannelinvoice_affiliatenw")
    private String nukeSaleschannelinvoiceAffiliatenw = "";
    @Column(name = "nuke_saleschannelinvoice_affiliatepartner")
    private String nukeSaleschannelinvoiceAffiliatepartner = "";
    @Column(name = "nuke_saleschannelinvoice_saleschannellogsession")
    private String nukeSaleschannelinvoiceSaleschannellogsession = "";
    @Column(name = "nuke_saleschannelinvoice_ipaddress")
    private String nukeSaleschannelinvoiceIpaddress = "";

    public NukeMrcommerceSaleschannelinvoice() {
    }

    public NukeMrcommerceSaleschannelinvoice(Integer nukeSaleschannelinvoiceInvoice) {
        this.nukeSaleschannelinvoiceInvoice = nukeSaleschannelinvoiceInvoice;
    }

    public NukeMrcommerceSaleschannelinvoice(Integer nukeSaleschannelinvoiceInvoice, int nukeSaleschannelinvoiceChannel) {
        this.nukeSaleschannelinvoiceInvoice = nukeSaleschannelinvoiceInvoice;
        this.nukeSaleschannelinvoiceChannel = nukeSaleschannelinvoiceChannel;
    }

    public Integer getNukeSaleschannelinvoiceInvoice() {
        return nukeSaleschannelinvoiceInvoice;
    }

    public void setNukeSaleschannelinvoiceInvoice(Integer nukeSaleschannelinvoiceInvoice) {
        this.nukeSaleschannelinvoiceInvoice = nukeSaleschannelinvoiceInvoice;
    }

    public int getNukeSaleschannelinvoiceChannel() {
        return nukeSaleschannelinvoiceChannel;
    }

    public void setNukeSaleschannelinvoiceChannel(int nukeSaleschannelinvoiceChannel) {
        this.nukeSaleschannelinvoiceChannel = nukeSaleschannelinvoiceChannel;
    }

    public Integer getNukeSaleschannelinvoiceCooperationid() {
        return nukeSaleschannelinvoiceCooperationid;
    }

    public void setNukeSaleschannelinvoiceCooperationid(Integer nukeSaleschannelinvoiceCooperationid) {
        this.nukeSaleschannelinvoiceCooperationid = nukeSaleschannelinvoiceCooperationid;
    }

    public String getNukeSaleschannelinvoiceReferrer() {
        return nukeSaleschannelinvoiceReferrer;
    }

    public void setNukeSaleschannelinvoiceReferrer(String nukeSaleschannelinvoiceReferrer) {
        this.nukeSaleschannelinvoiceReferrer = nukeSaleschannelinvoiceReferrer;
    }

    public String getNukeSaleschannelinvoiceAffiliatenw() {
        return nukeSaleschannelinvoiceAffiliatenw;
    }

    public void setNukeSaleschannelinvoiceAffiliatenw(String nukeSaleschannelinvoiceAffiliatenw) {
        this.nukeSaleschannelinvoiceAffiliatenw = nukeSaleschannelinvoiceAffiliatenw;
    }

    public String getNukeSaleschannelinvoiceAffiliatepartner() {
        return nukeSaleschannelinvoiceAffiliatepartner;
    }

    public void setNukeSaleschannelinvoiceAffiliatepartner(String nukeSaleschannelinvoiceAffiliatepartner) {
        this.nukeSaleschannelinvoiceAffiliatepartner = nukeSaleschannelinvoiceAffiliatepartner;
    }

    public String getNukeSaleschannelinvoiceSaleschannellogsession() {
        return nukeSaleschannelinvoiceSaleschannellogsession;
    }

    public void setNukeSaleschannelinvoiceSaleschannellogsession(String nukeSaleschannelinvoiceSaleschannellogsession) {
        this.nukeSaleschannelinvoiceSaleschannellogsession = nukeSaleschannelinvoiceSaleschannellogsession;
    }

    public String getNukeSaleschannelinvoiceIpaddress() {
        return nukeSaleschannelinvoiceIpaddress;
    }

    public void setNukeSaleschannelinvoiceIpaddress(String nukeSaleschannelinvoiceIpaddress) {
        this.nukeSaleschannelinvoiceIpaddress = nukeSaleschannelinvoiceIpaddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeSaleschannelinvoiceInvoice != null ? nukeSaleschannelinvoiceInvoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceSaleschannelinvoice)) {
            return false;
        }
        NukeMrcommerceSaleschannelinvoice other = (NukeMrcommerceSaleschannelinvoice) object;
        if ((this.nukeSaleschannelinvoiceInvoice == null && other.nukeSaleschannelinvoiceInvoice != null) || (this.nukeSaleschannelinvoiceInvoice != null && !this.nukeSaleschannelinvoiceInvoice.equals(other.nukeSaleschannelinvoiceInvoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice[nukeSaleschannelinvoiceInvoice=" + nukeSaleschannelinvoiceInvoice + "]";
    }

}
