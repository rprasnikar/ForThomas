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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_category")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceCategory.findAll", query = "SELECT n FROM NukeMrcommerceCategory n"),
    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatId", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatId = :nukeCatId"),
    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatName", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatName = :nukeCatName"),
    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatParent", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatParent = :nukeCatParent")
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatInvisible", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatInvisible = :nukeCatInvisible"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatImage", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatImage = :nukeCatImage"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatMinId", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatMinId = :nukeCatMinId"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatMaxId", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatMaxId = :nukeCatMaxId"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatSorting", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatSorting = :nukeCatSorting"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatDisplayinmenu", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatDisplayinmenu = :nukeCatDisplayinmenu"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatUrlmapping", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatUrlmapping = :nukeCatUrlmapping"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatSeoKeywords", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatSeoKeywords = :nukeCatSeoKeywords"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatSeoDescriptionAut", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatSeoDescriptionAut = :nukeCatSeoDescriptionAut"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatSeoDescriptionDeu", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatSeoDescriptionDeu = :nukeCatSeoDescriptionDeu"),
//    @NamedQuery(name = "NukeMrcommerceCategory.findByNukeCatSeoDescriptionChe", query = "SELECT n FROM NukeMrcommerceCategory n WHERE n.nukeCatSeoDescriptionChe = :nukeCatSeoDescriptionChe")
})
public class NukeMrcommerceCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_cat_id")
    private Long nukeCatId;
    @Basic(optional = false)
    @Column(name = "nuke_cat_name")
    private String nukeCatName;
    @Basic(optional = false)
    @Column(name = "nuke_cat_parent")
    private long nukeCatParent;
    @Column(name = "nuke_cat_invisible")
    private Short nukeCatInvisible;
    @Column(name = "nuke_cat_image")
    private String nukeCatImage;
    @Lob
    @Column(name = "nuke_cat_description_aut")
    private String nukeCatDescriptionAut;
    @Lob
    @Column(name = "nuke_cat_description_deu")
    private String nukeCatDescriptionDeu;
    @Lob
    @Column(name = "nuke_cat_description_che")
    private String nukeCatDescriptionChe;
    @Column(name = "nuke_cat_min_id")
    private Integer nukeCatMinId;
    @Column(name = "nuke_cat_max_id")
    private Integer nukeCatMaxId;
    @Basic(optional = false)
    @Column(name = "nuke_cat_sorting")
    private int nukeCatSorting;
    @Basic(optional = false)
    @Column(name = "nuke_cat_displayinmenu")
    private short nukeCatDisplayinmenu;
    @Column(name = "nuke_cat_urlmapping")
    private String nukeCatUrlmapping;
    @Column(name = "nuke_cat_seo_keywords")
    private String nukeCatSeoKeywords;
    @Column(name = "nuke_cat_seo_description_aut")
    private String nukeCatSeoDescriptionAut;
    @Column(name = "nuke_cat_seo_description_deu")
    private String nukeCatSeoDescriptionDeu;
    @Column(name = "nuke_cat_seo_description_che")
    private String nukeCatSeoDescriptionChe;
    @OneToMany(mappedBy = "nukeMrcommerceCategory")
    private Collection<NukeMrcommerceItems> nukeMrcommerceItemsCollection;

    public NukeMrcommerceCategory() {
    }

    public NukeMrcommerceCategory(Long nukeCatId) {
        this.nukeCatId = nukeCatId;
    }

    public NukeMrcommerceCategory(Long nukeCatId, String nukeCatName, long nukeCatParent, int nukeCatSorting, short nukeCatDisplayinmenu) {
        this.nukeCatId = nukeCatId;
        this.nukeCatName = nukeCatName;
        this.nukeCatParent = nukeCatParent;
        this.nukeCatSorting = nukeCatSorting;
        this.nukeCatDisplayinmenu = nukeCatDisplayinmenu;
    }

    public Long getNukeCatId() {
        return nukeCatId;
    }

    public void setNukeCatId(Long nukeCatId) {
        this.nukeCatId = nukeCatId;
    }

    public String getNukeCatName() {
        return nukeCatName;
    }

    public void setNukeCatName(String nukeCatName) {
        this.nukeCatName = nukeCatName;
    }

    public long getNukeCatParent() {
        return nukeCatParent;
    }

    public void setNukeCatParent(long nukeCatParent) {
        this.nukeCatParent = nukeCatParent;
    }

    public Short getNukeCatInvisible() {
        return nukeCatInvisible;
    }

    public void setNukeCatInvisible(Short nukeCatInvisible) {
        this.nukeCatInvisible = nukeCatInvisible;
    }

    public String getNukeCatImage() {
        return nukeCatImage;
    }

    public void setNukeCatImage(String nukeCatImage) {
        this.nukeCatImage = nukeCatImage;
    }

    public String getNukeCatDescriptionAut() {
        return nukeCatDescriptionAut;
    }

    public void setNukeCatDescriptionAut(String nukeCatDescriptionAut) {
        this.nukeCatDescriptionAut = nukeCatDescriptionAut;
    }

    public String getNukeCatDescriptionDeu() {
        return nukeCatDescriptionDeu;
    }

    public void setNukeCatDescriptionDeu(String nukeCatDescriptionDeu) {
        this.nukeCatDescriptionDeu = nukeCatDescriptionDeu;
    }

    public String getNukeCatDescriptionChe() {
        return nukeCatDescriptionChe;
    }

    public void setNukeCatDescriptionChe(String nukeCatDescriptionChe) {
        this.nukeCatDescriptionChe = nukeCatDescriptionChe;
    }

    public Integer getNukeCatMinId() {
        return nukeCatMinId;
    }

    public void setNukeCatMinId(Integer nukeCatMinId) {
        this.nukeCatMinId = nukeCatMinId;
    }

    public Integer getNukeCatMaxId() {
        return nukeCatMaxId;
    }

    public void setNukeCatMaxId(Integer nukeCatMaxId) {
        this.nukeCatMaxId = nukeCatMaxId;
    }

    public int getNukeCatSorting() {
        return nukeCatSorting;
    }

    public void setNukeCatSorting(int nukeCatSorting) {
        this.nukeCatSorting = nukeCatSorting;
    }

    public short getNukeCatDisplayinmenu() {
        return nukeCatDisplayinmenu;
    }

    public void setNukeCatDisplayinmenu(short nukeCatDisplayinmenu) {
        this.nukeCatDisplayinmenu = nukeCatDisplayinmenu;
    }

    public String getNukeCatUrlmapping() {
        return nukeCatUrlmapping;
    }

    public void setNukeCatUrlmapping(String nukeCatUrlmapping) {
        this.nukeCatUrlmapping = nukeCatUrlmapping;
    }

    public String getNukeCatSeoKeywords() {
        return nukeCatSeoKeywords;
    }

    public void setNukeCatSeoKeywords(String nukeCatSeoKeywords) {
        this.nukeCatSeoKeywords = nukeCatSeoKeywords;
    }

    public String getNukeCatSeoDescriptionAut() {
        return nukeCatSeoDescriptionAut;
    }

    public void setNukeCatSeoDescriptionAut(String nukeCatSeoDescriptionAut) {
        this.nukeCatSeoDescriptionAut = nukeCatSeoDescriptionAut;
    }

    public String getNukeCatSeoDescriptionDeu() {
        return nukeCatSeoDescriptionDeu;
    }

    public void setNukeCatSeoDescriptionDeu(String nukeCatSeoDescriptionDeu) {
        this.nukeCatSeoDescriptionDeu = nukeCatSeoDescriptionDeu;
    }

    public String getNukeCatSeoDescriptionChe() {
        return nukeCatSeoDescriptionChe;
    }

    public void setNukeCatSeoDescriptionChe(String nukeCatSeoDescriptionChe) {
        this.nukeCatSeoDescriptionChe = nukeCatSeoDescriptionChe;
    }

    public Collection<NukeMrcommerceItems> getNukeMrcommerceItemsCollection() {
        return nukeMrcommerceItemsCollection;
    }

    public void setNukeMrcommerceItemsCollection(Collection<NukeMrcommerceItems> nukeMrcommerceItemsCollection) {
        this.nukeMrcommerceItemsCollection = nukeMrcommerceItemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeCatId != null ? nukeCatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceCategory)) {
            return false;
        }
        NukeMrcommerceCategory other = (NukeMrcommerceCategory) object;
        if ((this.nukeCatId == null && other.nukeCatId != null) || (this.nukeCatId != null && !this.nukeCatId.equals(other.nukeCatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceCategory[nukeCatId=" + nukeCatId + "]";
    }

}
