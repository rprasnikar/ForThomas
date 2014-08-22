/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gunter Reinitzer
 */
@Entity
@Table(name = "nuke_mrcommerce_items")
@NamedQueries({
    @NamedQuery(name = "NukeMrcommerceItems.findAll", query = "SELECT n FROM NukeMrcommerceItems n"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemId", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemId = :nukeItemId")
    /*
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemLevel", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemLevel = :nukeItemLevel"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSegment", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSegment = :nukeItemSegment"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemName", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemName = :nukeItemName"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPriceAut", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPriceAut = :nukeItemPriceAut"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemIsAllowedToCard", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemIsAllowedToCard = :nukeItemIsAllowedToCard"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPrice42", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPrice42 = :nukeItemPrice42"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemTaxAut", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemTaxAut = :nukeItemTaxAut"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSeasonSpring", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSeasonSpring = :nukeItemSeasonSpring"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSeasonSummer", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSeasonSummer = :nukeItemSeasonSummer"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSeasonAutumn", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSeasonAutumn = :nukeItemSeasonAutumn"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSeasonWinter", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSeasonWinter = :nukeItemSeasonWinter"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSex", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSex = :nukeItemSex"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktor", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktor = :nukeItemEmotionalFaktor"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktor2", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktor2 = :nukeItemEmotionalFaktor2"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemCelebrity", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemCelebrity = :nukeItemCelebrity"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeitemJEG", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeitemJEG = :nukeitemJEG"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGroupMin", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGroupMin = :nukeItemGroupMin"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGroupMax", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGroupMax = :nukeItemGroupMax"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGroupDisplay", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGroupDisplay = :nukeItemGroupDisplay"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo1", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo1 = :nukeItemStornoTo1"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent1", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent1 = :nukeItemStornoPercent1"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoFrom2", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoFrom2 = :nukeItemStornoFrom2"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo2", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo2 = :nukeItemStornoTo2"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent2", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent2 = :nukeItemStornoPercent2"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoFrom3", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoFrom3 = :nukeItemStornoFrom3"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo3", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo3 = :nukeItemStornoTo3"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent3", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent3 = :nukeItemStornoPercent3"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoFrom4", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoFrom4 = :nukeItemStornoFrom4"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo4", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo4 = :nukeItemStornoTo4"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent4", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent4 = :nukeItemStornoPercent4"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStartpage", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStartpage = :nukeItemStartpage"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemImage", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemImage = :nukeItemImage"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStatus", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStatus = :nukeItemStatus"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAlternToPrice", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAlternToPrice = :nukeItemAlternToPrice"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAlternToPriceDe", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAlternToPriceDe = :nukeItemAlternToPriceDe"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemDisplayAlternText", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemDisplayAlternText = :nukeItemDisplayAlternText"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAlternText", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAlternText = :nukeItemAlternText"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemIsPromo", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemIsPromo = :nukeItemIsPromo"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemIsSpecialoffer", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemIsSpecialoffer = :nukeItemIsSpecialoffer"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPartnerprogramId", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPartnerprogramId = :nukeItemPartnerprogramId"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemCustomerprogramId", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemCustomerprogramId = :nukeItemCustomerprogramId"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAgefrom", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAgefrom = :nukeItemAgefrom"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAgeto", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAgeto = :nukeItemAgeto"),
//    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemUserquantity", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemUserquantity = :nukeItemUserquantity"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGrouptype", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGrouptype = :nukeItemGrouptype"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSortid", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSortid = :nukeItemSortid"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSign", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSign = :nukeItemSign"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemTaxDeu", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemTaxDeu = :nukeItemTaxDeu"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPriceDeu", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPriceDeu = :nukeItemPriceDeu"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemMetaDescAut", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemMetaDescAut = :nukeItemMetaDescAut"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemMetaDescDeu", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemMetaDescDeu = :nukeItemMetaDescDeu"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemMetaDescChe", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemMetaDescChe = :nukeItemMetaDescChe"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemMetaTags", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemMetaTags = :nukeItemMetaTags"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAdventureReport", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAdventureReport = :nukeItemAdventureReport"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeMrcommerceAccruals", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeMrcommerceAccruals = :nukeMrcommerceAccruals"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoFrom5", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoFrom5 = :nukeItemStornoFrom5"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo5", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo5 = :nukeItemStornoTo5"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent5", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent5 = :nukeItemStornoPercent5"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoFrom6", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoFrom6 = :nukeItemStornoFrom6"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoTo6", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoTo6 = :nukeItemStornoTo6"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoPercent6", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoPercent6 = :nukeItemStornoPercent6"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount1", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount1 = :nukeItemStornoAmount1"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount2", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount2 = :nukeItemStornoAmount2"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount3", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount3 = :nukeItemStornoAmount3"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount4", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount4 = :nukeItemStornoAmount4"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount5", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount5 = :nukeItemStornoAmount5"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoAmount6", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoAmount6 = :nukeItemStornoAmount6"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemStornoMinMax", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemStornoMinMax = :nukeItemStornoMinMax"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemUrlName", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemUrlName = :nukeItemUrlName"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemUrlPriceclusterName", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemUrlPriceclusterName = :nukeItemUrlPriceclusterName"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSexMan", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSexMan = :nukeItemSexMan"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSexWoman", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSexWoman = :nukeItemSexWoman"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorAction", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorAction = :nukeItemEmotionalFaktorAction"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorPleasure", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorPleasure = :nukeItemEmotionalFaktorPleasure"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorFun", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorFun = :nukeItemEmotionalFaktorFun"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorRelax", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorRelax = :nukeItemEmotionalFaktorRelax"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorRomantic", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorRomantic = :nukeItemEmotionalFaktorRomantic"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEmotionalFaktorCreativity", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEmotionalFaktorCreativity = :nukeItemEmotionalFaktorCreativity"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGrouptypeFamily", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGrouptypeFamily = :nukeItemGrouptypeFamily"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGrouptypePair", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGrouptypePair = :nukeItemGrouptypePair"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGrouptypeGroup", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGrouptypeGroup = :nukeItemGrouptypeGroup"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemGrouptypeChild", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemGrouptypeChild = :nukeItemGrouptypeChild"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEwType", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEwType = :nukeItemEwType"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemModificationdate", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemModificationdate = :nukeItemModificationdate"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemImagedisplayascategory", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemImagedisplayascategory = :nukeItemImagedisplayascategory"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPriceChe", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPriceChe = :nukeItemPriceChe"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemTaxChe", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemTaxChe = :nukeItemTaxChe"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemPriceCheEur", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemPriceCheEur = :nukeItemPriceCheEur"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemDefaultCurrency", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemDefaultCurrency = :nukeItemDefaultCurrency"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemMaxbruttoek", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemMaxbruttoek = :nukeItemMaxbruttoek"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemEancode", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemEancode = :nukeItemEancode"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxtitle", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxtitle = :nukeItemBoxtitle"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxtyp", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxtyp = :nukeItemBoxtyp"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSaleschannelIsdirect", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSaleschannelIsdirect = :nukeItemSaleschannelIsdirect"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemImageBox", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemImageBox = :nukeItemImageBox"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemSubtitleBox", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemSubtitleBox = :nukeItemSubtitleBox"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemImageHowitworksBox", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemImageHowitworksBox = :nukeItemImageHowitworksBox"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxIsaut", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxIsaut = :nukeItemBoxIsaut"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxIsdeu", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxIsdeu = :nukeItemBoxIsdeu"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxIsche", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxIsche = :nukeItemBoxIsche"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemIsfulfillmentbooking", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemIsfulfillmentbooking = :nukeItemIsfulfillmentbooking"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemCataloguefolder", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemCataloguefolder = :nukeItemCataloguefolder"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemAdminitemforcart", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemAdminitemforcart = :nukeItemAdminitemforcart"),
    @NamedQuery(name = "NukeMrcommerceItems.findByNukeItemBoxValidto", query = "SELECT n FROM NukeMrcommerceItems n WHERE n.nukeItemBoxValidto = :nukeItemBoxValidto")
     * 
     * */
     })
public class NukeMrcommerceItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_item_id")
    private Integer nukeItemId;
    @Lob
    @Column(name = "nuke_item_description")
    private String nukeItemDescription;
    @Column(name = "nuke_item_level")
    private Integer nukeItemLevel;
    @Column(name = "nuke_item_segment")
    private String nukeItemSegment;
    @Column(name = "nuke_item_name")
    private String nukeItemName;
//    @Lob
//    @Column(name = "nuke_item_first_text")
//    private String nukeItemFirstText;
//    @Lob
//    @Column(name = "nuke_item_katalog_text")
//    private String nukeItemKatalogText;
//    @Lob
//    @Column(name = "nuke_item_www_text_aut")
//    private String nukeItemWwwTextAut;
//    @Lob
//    @Column(name = "nuke_item_www_text_deu")
//    private String nukeItemWwwTextDeu;
//    @Lob
//    @Column(name = "nuke_item_www_text_che")
//    private String nukeItemWwwTextChe;
//    @Lob
//    @Column(name = "nuke_item_place")
//    private String nukeItemPlace;
//    @Column(name = "nuke_item_price_aut")
//    private BigDecimal nukeItemPriceAut;
//    @Column(name = "nuke_item_is_allowed_to_card")
//    private Boolean nukeItemIsAllowedToCard;
//    @Column(name = "nuke_item_price42")
//    private Boolean nukeItemPrice42;
//    @Column(name = "nuke_item_tax_aut")
//    private Integer nukeItemTaxAut;
//    @Column(name = "nuke_item_season_spring")
//    private Boolean nukeItemSeasonSpring;
//    @Column(name = "nuke_item_season_summer")
//    private Boolean nukeItemSeasonSummer;
//    @Column(name = "nuke_item_season_autumn")
//    private Boolean nukeItemSeasonAutumn;
//    @Column(name = "nuke_item_season_winter")
//    private Boolean nukeItemSeasonWinter;
//    @Lob
//    @Column(name = "nuke_item_availability")
//    private String nukeItemAvailability;
//    @Lob
//    @Column(name = "nuke_item_duration_summary")
//    private String nukeItemDurationSummary;
//    @Lob
//    @Column(name = "nuke_item_weather_dependance")
//    private String nukeItemWeatherDependance;
//    @Lob
//    @Column(name = "nuke_item_visitors")
//    private String nukeItemVisitors;
//    @Lob
//    @Column(name = "nuke_item_bring_in")
//    private String nukeItemBringIn;
//    @Lob
//    @Column(name = "nuke_item_important_info")
//    private String nukeItemImportantInfo;
//    @Column(name = "nuke_item_sex")
//    private String nukeItemSex;
//    @Column(name = "nuke_item_emotional_faktor")
//    private String nukeItemEmotionalFaktor;
//    @Column(name = "nuke_item_emotional_faktor2")
//    private String nukeItemEmotionalFaktor2;
//    @Column(name = "nuke_item_celebrity")
//    private Boolean nukeItemCelebrity;
//    @Column(name = "nuke_item_JEG")
//    private Boolean nukeitemJEG;
//    @Column(name = "nuke_item_group_min")
//    private Integer nukeItemGroupMin;
//    @Column(name = "nuke_item_group_max")
//    private Integer nukeItemGroupMax;
//    @Column(name = "nuke_item_group_display")
//    private String nukeItemGroupDisplay;
//    @Column(name = "nuke_item_storno_to1")
//    private Integer nukeItemStornoTo1;
//    @Column(name = "nuke_item_storno_percent1")
//    private Double nukeItemStornoPercent1;
//    @Column(name = "nuke_item_storno_from2")
//    private Integer nukeItemStornoFrom2;
//    @Column(name = "nuke_item_storno_to2")
//    private Integer nukeItemStornoTo2;
//    @Column(name = "nuke_item_storno_percent2")
//    private Double nukeItemStornoPercent2;
//    @Column(name = "nuke_item_storno_from3")
//    private Integer nukeItemStornoFrom3;
//    @Column(name = "nuke_item_storno_to3")
//    private Integer nukeItemStornoTo3;
//    @Column(name = "nuke_item_storno_percent3")
//    private Double nukeItemStornoPercent3;
//    @Column(name = "nuke_item_storno_from4")
//    private Integer nukeItemStornoFrom4;
//    @Column(name = "nuke_item_storno_to4")
//    private Integer nukeItemStornoTo4;
//    @Column(name = "nuke_item_storno_percent4")
//    private Double nukeItemStornoPercent4;
//    @Column(name = "nuke_item_startpage")
//    private Integer nukeItemStartpage;
//    @Column(name = "nuke_item_image")
//    private String nukeItemImage;
//    @Column(name = "nuke_item_status")
//    private Integer nukeItemStatus;
//    @Column(name = "nuke_item_altern_to_price")
//    private String nukeItemAlternToPrice;
//    @Column(name = "nuke_item_altern_to_price_de")
//    private String nukeItemAlternToPriceDe;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_display_altern_text")
//    private boolean nukeItemDisplayAlternText;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_altern_text")
//    private String nukeItemAlternText;
//    @Column(name = "nuke_item_is_promo")
//    private Boolean nukeItemIsPromo;
//    @Column(name = "nuke_item_is_specialoffer")
//    private Short nukeItemIsSpecialoffer;
//    @Lob
//    @Column(name = "nuke_item_promo_text")
//    private String nukeItemPromoText;
//    @Column(name = "nuke_item_partnerprogram_id")
//    private Integer nukeItemPartnerprogramId;
//    @Lob
//    @Column(name = "nuke_item_partnerprogram_text")
//    private String nukeItemPartnerprogramText;
//    @Column(name = "nuke_item_customerprogram_id")
//    private Integer nukeItemCustomerprogramId;
//    @Column(name = "nuke_item_agefrom")
//    private Integer nukeItemAgefrom;
//    @Column(name = "nuke_item_ageto")
//    private Integer nukeItemAgeto;
////    @Column(name = "nuke_item_userquantity")
////    private Boolean nukeItemUserquantity;
//    @Column(name = "nuke_item_grouptype")
//    private String nukeItemGrouptype;
//    @Column(name = "nuke_item_sortid")
//    private Integer nukeItemSortid;
//    @Lob
//    @Column(name = "nuke_item_cc_text")
//    private String nukeItemCcText;
    @Basic(optional = false)
    @Column(name = "nuke_item_sign")
    private short nukeItemSign;
    @Column(name = "nuke_item_tax_deu")
    private Integer nukeItemTaxDeu;
    @Column(name = "nuke_item_price_deu")
    private BigDecimal nukeItemPriceDeu;
//    @Column(name = "nuke_item_meta_desc_aut")
//    private String nukeItemMetaDescAut;
//    @Column(name = "nuke_item_meta_desc_deu")
//    private String nukeItemMetaDescDeu;
//    @Column(name = "nuke_item_meta_desc_che")
//    private String nukeItemMetaDescChe;
//    @Column(name = "nuke_item_meta_tags")
//    private String nukeItemMetaTags;
//    @Column(name = "nuke_item_adventure_report")
//    private String nukeItemAdventureReport;
//    @Column(name = "nuke_mrcommerce_accruals")
//    private BigDecimal nukeMrcommerceAccruals;
//    @Column(name = "nuke_item_storno_from5")
//    private Integer nukeItemStornoFrom5;
//    @Column(name = "nuke_item_storno_to5")
//    private Integer nukeItemStornoTo5;
//    @Column(name = "nuke_item_storno_percent5")
//    private Double nukeItemStornoPercent5;
//    @Column(name = "nuke_item_storno_from6")
//    private Integer nukeItemStornoFrom6;
//    @Column(name = "nuke_item_storno_to6")
//    private Integer nukeItemStornoTo6;
//    @Column(name = "nuke_item_storno_percent6")
//    private Double nukeItemStornoPercent6;
//    @Column(name = "nuke_item_storno_amount_1")
//    private Integer nukeItemStornoAmount1;
//    @Column(name = "nuke_item_storno_amount_2")
//    private Integer nukeItemStornoAmount2;
//    @Column(name = "nuke_item_storno_amount_3")
//    private Integer nukeItemStornoAmount3;
//    @Column(name = "nuke_item_storno_amount_4")
//    private Integer nukeItemStornoAmount4;
//    @Column(name = "nuke_item_storno_amount_5")
//    private Integer nukeItemStornoAmount5;
//    @Column(name = "nuke_item_storno_amount_6")
//    private Integer nukeItemStornoAmount6;
//    @Column(name = "nuke_item_storno_min_max")
//    private Short nukeItemStornoMinMax;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_url_name")
//    private String nukeItemUrlName;
//    @Column(name = "nuke_item_url_pricecluster_name")
//    private String nukeItemUrlPriceclusterName;
//    @Column(name = "nuke_item_sex_man")
//    private Integer nukeItemSexMan;
//    @Column(name = "nuke_item_sex_woman")
//    private Integer nukeItemSexWoman;
//    @Column(name = "nuke_item_emotional_faktor_action")
//    private BigDecimal nukeItemEmotionalFaktorAction;
//    @Column(name = "nuke_item_emotional_faktor_pleasure")
//    private BigDecimal nukeItemEmotionalFaktorPleasure;
//    @Column(name = "nuke_item_emotional_faktor_fun")
//    private BigDecimal nukeItemEmotionalFaktorFun;
//    @Column(name = "nuke_item_emotional_faktor_relax")
//    private BigDecimal nukeItemEmotionalFaktorRelax;
//    @Column(name = "nuke_item_emotional_faktor_romantic")
//    private BigDecimal nukeItemEmotionalFaktorRomantic;
//    @Column(name = "nuke_item_emotional_faktor_creativity")
//    private BigDecimal nukeItemEmotionalFaktorCreativity;
//    @Column(name = "nuke_item_grouptype_family")
//    private Integer nukeItemGrouptypeFamily;
//    @Column(name = "nuke_item_grouptype_pair")
//    private Integer nukeItemGrouptypePair;
//    @Column(name = "nuke_item_grouptype_group")
//    private Integer nukeItemGrouptypeGroup;
//    @Column(name = "nuke_item_grouptype_child")
//    private Integer nukeItemGrouptypeChild;
//    @Column(name = "nuke_item_ew_type")
//    private Short nukeItemEwType;
//    @Column(name = "nuke_item_modificationdate")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date nukeItemModificationdate;
//    @Column(name = "nuke_item_imagedisplayascategory")
//    private String nukeItemImagedisplayascategory;
    @Column(name = "nuke_item_price_che")
    private BigDecimal nukeItemPriceChe;
    @Column(name = "nuke_item_tax_che")
    private BigDecimal nukeItemTaxChe;
    @Basic(optional = false)
    @Column(name = "nuke_item_price_che_eur")
    private BigDecimal nukeItemPriceCheEur;
    @Column(name = "nuke_item_default_currency")
    private String nukeItemDefaultCurrency;
    @Basic(optional = false)
    @Column(name = "nuke_item_maxbruttoek")
    private BigDecimal nukeItemMaxbruttoek;
    @Column(name = "nuke_item_eancode")
    private String nukeItemEancode;
//    @Lob
//    @Column(name = "nuke_item_text_affiliate_online")
//    private String nukeItemTextAffiliateOnline;
//    @Lob
//    @Column(name = "nuke_item_text_box_offline")
//    private String nukeItemTextBoxOffline;
//    @Column(name = "nuke_item_boxtitle")
//    private String nukeItemBoxtitle;
    @Basic(optional = false)
    @Column(name = "nuke_item_boxtyp")
    private boolean nukeItemBoxtyp;
    @Column(name = "nuke_item_saleschannel_isdirect")
    private Boolean nukeItemSaleschannelIsdirect;
//    @Column(name = "nuke_item_image_box")
//    private String nukeItemImageBox;
//    @Column(name = "nuke_item_subtitle_box")
//    private String nukeItemSubtitleBox;
//    @Column(name = "nuke_item_image_howitworks_box")
//    private String nukeItemImageHowitworksBox;
//    @Lob
//    @Column(name = "nuke_item_text_howitworks_box")
//    private String nukeItemTextHowitworksBox;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_box_isaut")
//    private boolean nukeItemBoxIsaut;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_box_isdeu")
//    private boolean nukeItemBoxIsdeu;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_box_ische")
//    private boolean nukeItemBoxIsche;
//    @Lob
//    @Column(name = "nuke_item_teaser_country_aut")
//    private String nukeItemTeaserCountryAut;
//    @Lob
//    @Column(name = "nuke_item_teaser_country_deu")
//    private String nukeItemTeaserCountryDeu;
//    @Lob
//    @Column(name = "nuke_item_teaser_country_che")
//    private String nukeItemTeaserCountryChe;
//    @Basic(optional = false)
//    @Column(name = "nuke_item_isfulfillmentbooking")
//    private boolean nukeItemIsfulfillmentbooking;
//    @Column(name = "nuke_item_cataloguefolder")
//    private String nukeItemCataloguefolder;
    @Basic(optional = false)
    @Column(name = "nuke_item_adminitemforcart")
    private boolean nukeItemAdminitemforcart;
    @Column(name = "nuke_item_box_validto")
    @Temporal(TemporalType.DATE)
    private Date nukeItemBoxValidto;
    @JoinColumn(name = "nuke_item_cat_id", referencedColumnName = "nuke_cat_id")
    @ManyToOne
    private NukeMrcommerceCategory nukeMrcommerceCategory;
//    @OneToMany(mappedBy = "nukeMrcommerceItems")
//    private Collection<NukeMrcommerceArrangement> nukeMrcommerceArrangementCollection;

    public NukeMrcommerceItems() {
    }

    public NukeMrcommerceItems(Integer nukeItemId) {
        this.nukeItemId = nukeItemId;
    }

    public NukeMrcommerceItems(Integer nukeItemId, boolean nukeItemDisplayAlternText, String nukeItemAlternText, short nukeItemSign, String nukeItemUrlName, BigDecimal nukeItemPriceCheEur, BigDecimal nukeItemMaxbruttoek, boolean nukeItemBoxtyp, boolean nukeItemBoxIsaut, boolean nukeItemBoxIsdeu, boolean nukeItemBoxIsche, boolean nukeItemIsfulfillmentbooking, boolean nukeItemAdminitemforcart) {
        this.nukeItemId = nukeItemId;
//        this.nukeItemDisplayAlternText = nukeItemDisplayAlternText;
//        this.nukeItemAlternText = nukeItemAlternText;
        this.nukeItemSign = nukeItemSign;
//        this.nukeItemUrlName = nukeItemUrlName;
        this.nukeItemPriceCheEur = nukeItemPriceCheEur;
        this.nukeItemMaxbruttoek = nukeItemMaxbruttoek;
        this.nukeItemBoxtyp = nukeItemBoxtyp;
//        this.nukeItemBoxIsaut = nukeItemBoxIsaut;
//        this.nukeItemBoxIsdeu = nukeItemBoxIsdeu;
//        this.nukeItemBoxIsche = nukeItemBoxIsche;
//        this.nukeItemIsfulfillmentbooking = nukeItemIsfulfillmentbooking;
//        this.nukeItemAdminitemforcart = nukeItemAdminitemforcart;
    }

    public Integer getNukeItemId() {
        return nukeItemId;
    }

    public void setNukeItemId(Integer nukeItemId) {
        this.nukeItemId = nukeItemId;
    }

    public String getNukeItemDescription() {
        return nukeItemDescription;
    }

    public void setNukeItemDescription(String nukeItemDescription) {
        this.nukeItemDescription = nukeItemDescription;
    }

    public Integer getNukeItemLevel() {
        return nukeItemLevel;
    }

    public void setNukeItemLevel(Integer nukeItemLevel) {
        this.nukeItemLevel = nukeItemLevel;
    }

    public String getNukeItemSegment() {
        return nukeItemSegment;
    }

    public void setNukeItemSegment(String nukeItemSegment) {
        this.nukeItemSegment = nukeItemSegment;
    }

    public String getNukeItemName() {
        return nukeItemName;
    }

    public void setNukeItemName(String nukeItemName) {
        this.nukeItemName = nukeItemName;
    }

//    public String getNukeItemFirstText() {
//        return nukeItemFirstText;
//    }
//
//    public void setNukeItemFirstText(String nukeItemFirstText) {
//        this.nukeItemFirstText = nukeItemFirstText;
//    }
//
//    public String getNukeItemKatalogText() {
//        return nukeItemKatalogText;
//    }
//
//    public void setNukeItemKatalogText(String nukeItemKatalogText) {
//        this.nukeItemKatalogText = nukeItemKatalogText;
//    }
//
//    public String getNukeItemWwwTextAut() {
//        return nukeItemWwwTextAut;
//    }
//
//    public void setNukeItemWwwTextAut(String nukeItemWwwTextAut) {
//        this.nukeItemWwwTextAut = nukeItemWwwTextAut;
//    }
//
//    public String getNukeItemWwwTextDeu() {
//        return nukeItemWwwTextDeu;
//    }
//
//    public void setNukeItemWwwTextDeu(String nukeItemWwwTextDeu) {
//        this.nukeItemWwwTextDeu = nukeItemWwwTextDeu;
//    }
//
//    public String getNukeItemWwwTextChe() {
//        return nukeItemWwwTextChe;
//    }
//
//    public void setNukeItemWwwTextChe(String nukeItemWwwTextChe) {
//        this.nukeItemWwwTextChe = nukeItemWwwTextChe;
//    }
//
//    public String getNukeItemPlace() {
//        return nukeItemPlace;
//    }
//
//    public void setNukeItemPlace(String nukeItemPlace) {
//        this.nukeItemPlace = nukeItemPlace;
//    }
//
//    public BigDecimal getNukeItemPriceAut() {
//        return nukeItemPriceAut;
//    }
//
//    public void setNukeItemPriceAut(BigDecimal nukeItemPriceAut) {
//        this.nukeItemPriceAut = nukeItemPriceAut;
//    }
//
//    public Boolean getNukeItemIsAllowedToCard() {
//        return nukeItemIsAllowedToCard;
//    }
//
//    public void setNukeItemIsAllowedToCard(Boolean nukeItemIsAllowedToCard) {
//        this.nukeItemIsAllowedToCard = nukeItemIsAllowedToCard;
//    }
//
//    public Boolean getNukeItemPrice42() {
//        return nukeItemPrice42;
//    }
//
//    public void setNukeItemPrice42(Boolean nukeItemPrice42) {
//        this.nukeItemPrice42 = nukeItemPrice42;
//    }
//
//    public Integer getNukeItemTaxAut() {
//        return nukeItemTaxAut;
//    }
//
//    public void setNukeItemTaxAut(Integer nukeItemTaxAut) {
//        this.nukeItemTaxAut = nukeItemTaxAut;
//    }
//
//    public Boolean getNukeItemSeasonSpring() {
//        return nukeItemSeasonSpring;
//    }
//
//    public void setNukeItemSeasonSpring(Boolean nukeItemSeasonSpring) {
//        this.nukeItemSeasonSpring = nukeItemSeasonSpring;
//    }
//
//    public Boolean getNukeItemSeasonSummer() {
//        return nukeItemSeasonSummer;
//    }
//
//    public void setNukeItemSeasonSummer(Boolean nukeItemSeasonSummer) {
//        this.nukeItemSeasonSummer = nukeItemSeasonSummer;
//    }
//
//    public Boolean getNukeItemSeasonAutumn() {
//        return nukeItemSeasonAutumn;
//    }
//
//    public void setNukeItemSeasonAutumn(Boolean nukeItemSeasonAutumn) {
//        this.nukeItemSeasonAutumn = nukeItemSeasonAutumn;
//    }
//
//    public Boolean getNukeItemSeasonWinter() {
//        return nukeItemSeasonWinter;
//    }
//
//    public void setNukeItemSeasonWinter(Boolean nukeItemSeasonWinter) {
//        this.nukeItemSeasonWinter = nukeItemSeasonWinter;
//    }
//
//    public String getNukeItemAvailability() {
//        return nukeItemAvailability;
//    }
//
//    public void setNukeItemAvailability(String nukeItemAvailability) {
//        this.nukeItemAvailability = nukeItemAvailability;
//    }
//
//    public String getNukeItemDurationSummary() {
//        return nukeItemDurationSummary;
//    }
//
//    public void setNukeItemDurationSummary(String nukeItemDurationSummary) {
//        this.nukeItemDurationSummary = nukeItemDurationSummary;
//    }
//
//    public String getNukeItemWeatherDependance() {
//        return nukeItemWeatherDependance;
//    }
//
//    public void setNukeItemWeatherDependance(String nukeItemWeatherDependance) {
//        this.nukeItemWeatherDependance = nukeItemWeatherDependance;
//    }
//
//    public String getNukeItemVisitors() {
//        return nukeItemVisitors;
//    }
//
//    public void setNukeItemVisitors(String nukeItemVisitors) {
//        this.nukeItemVisitors = nukeItemVisitors;
//    }
//
//    public String getNukeItemBringIn() {
//        return nukeItemBringIn;
//    }
//
//    public void setNukeItemBringIn(String nukeItemBringIn) {
//        this.nukeItemBringIn = nukeItemBringIn;
//    }
//
//    public String getNukeItemImportantInfo() {
//        return nukeItemImportantInfo;
//    }
//
//    public void setNukeItemImportantInfo(String nukeItemImportantInfo) {
//        this.nukeItemImportantInfo = nukeItemImportantInfo;
//    }
//
//    public String getNukeItemSex() {
//        return nukeItemSex;
//    }
//
//    public void setNukeItemSex(String nukeItemSex) {
//        this.nukeItemSex = nukeItemSex;
//    }
//
//    public String getNukeItemEmotionalFaktor() {
//        return nukeItemEmotionalFaktor;
//    }
//
//    public void setNukeItemEmotionalFaktor(String nukeItemEmotionalFaktor) {
//        this.nukeItemEmotionalFaktor = nukeItemEmotionalFaktor;
//    }
//
//    public String getNukeItemEmotionalFaktor2() {
//        return nukeItemEmotionalFaktor2;
//    }
//
//    public void setNukeItemEmotionalFaktor2(String nukeItemEmotionalFaktor2) {
//        this.nukeItemEmotionalFaktor2 = nukeItemEmotionalFaktor2;
//    }
//
//    public Boolean getNukeItemCelebrity() {
//        return nukeItemCelebrity;
//    }
//
//    public void setNukeItemCelebrity(Boolean nukeItemCelebrity) {
//        this.nukeItemCelebrity = nukeItemCelebrity;
//    }
//
//    public Boolean getNukeitemJEG() {
//        return nukeitemJEG;
//    }
//
//    public void setNukeitemJEG(Boolean nukeitemJEG) {
//        this.nukeitemJEG = nukeitemJEG;
//    }
//
//    public Integer getNukeItemGroupMin() {
//        return nukeItemGroupMin;
//    }
//
//    public void setNukeItemGroupMin(Integer nukeItemGroupMin) {
//        this.nukeItemGroupMin = nukeItemGroupMin;
//    }
//
//    public Integer getNukeItemGroupMax() {
//        return nukeItemGroupMax;
//    }
//
//    public void setNukeItemGroupMax(Integer nukeItemGroupMax) {
//        this.nukeItemGroupMax = nukeItemGroupMax;
//    }
//
//    public String getNukeItemGroupDisplay() {
//        return nukeItemGroupDisplay;
//    }
//
//    public void setNukeItemGroupDisplay(String nukeItemGroupDisplay) {
//        this.nukeItemGroupDisplay = nukeItemGroupDisplay;
//    }
//
//    public Integer getNukeItemStornoTo1() {
//        return nukeItemStornoTo1;
//    }
//
//    public void setNukeItemStornoTo1(Integer nukeItemStornoTo1) {
//        this.nukeItemStornoTo1 = nukeItemStornoTo1;
//    }
//
//    public Double getNukeItemStornoPercent1() {
//        return nukeItemStornoPercent1;
//    }
//
//    public void setNukeItemStornoPercent1(Double nukeItemStornoPercent1) {
//        this.nukeItemStornoPercent1 = nukeItemStornoPercent1;
//    }
//
//    public Integer getNukeItemStornoFrom2() {
//        return nukeItemStornoFrom2;
//    }
//
//    public void setNukeItemStornoFrom2(Integer nukeItemStornoFrom2) {
//        this.nukeItemStornoFrom2 = nukeItemStornoFrom2;
//    }
//
//    public Integer getNukeItemStornoTo2() {
//        return nukeItemStornoTo2;
//    }
//
//    public void setNukeItemStornoTo2(Integer nukeItemStornoTo2) {
//        this.nukeItemStornoTo2 = nukeItemStornoTo2;
//    }
//
//    public Double getNukeItemStornoPercent2() {
//        return nukeItemStornoPercent2;
//    }
//
//    public void setNukeItemStornoPercent2(Double nukeItemStornoPercent2) {
//        this.nukeItemStornoPercent2 = nukeItemStornoPercent2;
//    }
//
//    public Integer getNukeItemStornoFrom3() {
//        return nukeItemStornoFrom3;
//    }
//
//    public void setNukeItemStornoFrom3(Integer nukeItemStornoFrom3) {
//        this.nukeItemStornoFrom3 = nukeItemStornoFrom3;
//    }
//
//    public Integer getNukeItemStornoTo3() {
//        return nukeItemStornoTo3;
//    }
//
//    public void setNukeItemStornoTo3(Integer nukeItemStornoTo3) {
//        this.nukeItemStornoTo3 = nukeItemStornoTo3;
//    }
//
//    public Double getNukeItemStornoPercent3() {
//        return nukeItemStornoPercent3;
//    }
//
//    public void setNukeItemStornoPercent3(Double nukeItemStornoPercent3) {
//        this.nukeItemStornoPercent3 = nukeItemStornoPercent3;
//    }
//
//    public Integer getNukeItemStornoFrom4() {
//        return nukeItemStornoFrom4;
//    }
//
//    public void setNukeItemStornoFrom4(Integer nukeItemStornoFrom4) {
//        this.nukeItemStornoFrom4 = nukeItemStornoFrom4;
//    }
//
//    public Integer getNukeItemStornoTo4() {
//        return nukeItemStornoTo4;
//    }
//
//    public void setNukeItemStornoTo4(Integer nukeItemStornoTo4) {
//        this.nukeItemStornoTo4 = nukeItemStornoTo4;
//    }
//
//    public Double getNukeItemStornoPercent4() {
//        return nukeItemStornoPercent4;
//    }
//
//    public void setNukeItemStornoPercent4(Double nukeItemStornoPercent4) {
//        this.nukeItemStornoPercent4 = nukeItemStornoPercent4;
//    }
//
//    public Integer getNukeItemStartpage() {
//        return nukeItemStartpage;
//    }
//
//    public void setNukeItemStartpage(Integer nukeItemStartpage) {
//        this.nukeItemStartpage = nukeItemStartpage;
//    }
//
//    public String getNukeItemImage() {
//        return nukeItemImage;
//    }
//
//    public void setNukeItemImage(String nukeItemImage) {
//        this.nukeItemImage = nukeItemImage;
//    }
//
//    public Integer getNukeItemStatus() {
//        return nukeItemStatus;
//    }
//
//    public void setNukeItemStatus(Integer nukeItemStatus) {
//        this.nukeItemStatus = nukeItemStatus;
//    }
//
//    public String getNukeItemAlternToPrice() {
//        return nukeItemAlternToPrice;
//    }
//
//    public void setNukeItemAlternToPrice(String nukeItemAlternToPrice) {
//        this.nukeItemAlternToPrice = nukeItemAlternToPrice;
//    }
//
//    public String getNukeItemAlternToPriceDe() {
//        return nukeItemAlternToPriceDe;
//    }
//
//    public void setNukeItemAlternToPriceDe(String nukeItemAlternToPriceDe) {
//        this.nukeItemAlternToPriceDe = nukeItemAlternToPriceDe;
//    }
//
//    public boolean getNukeItemDisplayAlternText() {
//        return nukeItemDisplayAlternText;
//    }
//
//    public void setNukeItemDisplayAlternText(boolean nukeItemDisplayAlternText) {
//        this.nukeItemDisplayAlternText = nukeItemDisplayAlternText;
//    }
//
//    public String getNukeItemAlternText() {
//        return nukeItemAlternText;
//    }
//
//    public void setNukeItemAlternText(String nukeItemAlternText) {
//        this.nukeItemAlternText = nukeItemAlternText;
//    }
//
//    public Boolean getNukeItemIsPromo() {
//        return nukeItemIsPromo;
//    }
//
//    public void setNukeItemIsPromo(Boolean nukeItemIsPromo) {
//        this.nukeItemIsPromo = nukeItemIsPromo;
//    }
//
//    public Short getNukeItemIsSpecialoffer() {
//        return nukeItemIsSpecialoffer;
//    }
//
//    public void setNukeItemIsSpecialoffer(Short nukeItemIsSpecialoffer) {
//        this.nukeItemIsSpecialoffer = nukeItemIsSpecialoffer;
//    }
//
//    public String getNukeItemPromoText() {
//        return nukeItemPromoText;
//    }
//
//    public void setNukeItemPromoText(String nukeItemPromoText) {
//        this.nukeItemPromoText = nukeItemPromoText;
//    }
//
//    public Integer getNukeItemPartnerprogramId() {
//        return nukeItemPartnerprogramId;
//    }
//
//    public void setNukeItemPartnerprogramId(Integer nukeItemPartnerprogramId) {
//        this.nukeItemPartnerprogramId = nukeItemPartnerprogramId;
//    }
//
//    public String getNukeItemPartnerprogramText() {
//        return nukeItemPartnerprogramText;
//    }
//
//    public void setNukeItemPartnerprogramText(String nukeItemPartnerprogramText) {
//        this.nukeItemPartnerprogramText = nukeItemPartnerprogramText;
//    }
//
//    public Integer getNukeItemCustomerprogramId() {
//        return nukeItemCustomerprogramId;
//    }
//
//    public void setNukeItemCustomerprogramId(Integer nukeItemCustomerprogramId) {
//        this.nukeItemCustomerprogramId = nukeItemCustomerprogramId;
//    }
//
//    public Integer getNukeItemAgefrom() {
//        return nukeItemAgefrom;
//    }
//
//    public void setNukeItemAgefrom(Integer nukeItemAgefrom) {
//        this.nukeItemAgefrom = nukeItemAgefrom;
//    }
//
//    public Integer getNukeItemAgeto() {
//        return nukeItemAgeto;
//    }
//
//    public void setNukeItemAgeto(Integer nukeItemAgeto) {
//        this.nukeItemAgeto = nukeItemAgeto;
//    }

/*    public Boolean getNukeItemUserquantity() {
        return nukeItemUserquantity;
    }

    public void setNukeItemUserquantity(Boolean nukeItemUserquantity) {
        this.nukeItemUserquantity = nukeItemUserquantity;
    }
* */


//    public String getNukeItemGrouptype() {
//        return nukeItemGrouptype;
//    }
//
//    public void setNukeItemGrouptype(String nukeItemGrouptype) {
//        this.nukeItemGrouptype = nukeItemGrouptype;
//    }
//
//    public Integer getNukeItemSortid() {
//        return nukeItemSortid;
//    }
//
//    public void setNukeItemSortid(Integer nukeItemSortid) {
//        this.nukeItemSortid = nukeItemSortid;
//    }
//
//    public String getNukeItemCcText() {
//        return nukeItemCcText;
//    }
//
//    public void setNukeItemCcText(String nukeItemCcText) {
//        this.nukeItemCcText = nukeItemCcText;
//    }

    public short getNukeItemSign() {
        return nukeItemSign;
    }

    public void setNukeItemSign(short nukeItemSign) {
        this.nukeItemSign = nukeItemSign;
    }

    public Integer getNukeItemTaxDeu() {
        return nukeItemTaxDeu;
    }

    public void setNukeItemTaxDeu(Integer nukeItemTaxDeu) {
        this.nukeItemTaxDeu = nukeItemTaxDeu;
    }

    public BigDecimal getNukeItemPriceDeu() {
        return nukeItemPriceDeu;
    }

    public void setNukeItemPriceDeu(BigDecimal nukeItemPriceDeu) {
        this.nukeItemPriceDeu = nukeItemPriceDeu;
    }

//    public String getNukeItemMetaDescAut() {
//        return nukeItemMetaDescAut;
//    }
//
//    public void setNukeItemMetaDescAut(String nukeItemMetaDescAut) {
//        this.nukeItemMetaDescAut = nukeItemMetaDescAut;
//    }
//
//    public String getNukeItemMetaDescDeu() {
//        return nukeItemMetaDescDeu;
//    }
//
//    public void setNukeItemMetaDescDeu(String nukeItemMetaDescDeu) {
//        this.nukeItemMetaDescDeu = nukeItemMetaDescDeu;
//    }
//
//    public String getNukeItemMetaDescChe() {
//        return nukeItemMetaDescChe;
//    }
//
//    public void setNukeItemMetaDescChe(String nukeItemMetaDescChe) {
//        this.nukeItemMetaDescChe = nukeItemMetaDescChe;
//    }
//
//    public String getNukeItemMetaTags() {
//        return nukeItemMetaTags;
//    }
//
//    public void setNukeItemMetaTags(String nukeItemMetaTags) {
//        this.nukeItemMetaTags = nukeItemMetaTags;
//    }
//
//    public String getNukeItemAdventureReport() {
//        return nukeItemAdventureReport;
//    }
//
//    public void setNukeItemAdventureReport(String nukeItemAdventureReport) {
//        this.nukeItemAdventureReport = nukeItemAdventureReport;
//    }
//
//    public BigDecimal getNukeMrcommerceAccruals() {
//        return nukeMrcommerceAccruals;
//    }
//
//    public void setNukeMrcommerceAccruals(BigDecimal nukeMrcommerceAccruals) {
//        this.nukeMrcommerceAccruals = nukeMrcommerceAccruals;
//    }
//
//    public Integer getNukeItemStornoFrom5() {
//        return nukeItemStornoFrom5;
//    }
//
//    public void setNukeItemStornoFrom5(Integer nukeItemStornoFrom5) {
//        this.nukeItemStornoFrom5 = nukeItemStornoFrom5;
//    }
//
//    public Integer getNukeItemStornoTo5() {
//        return nukeItemStornoTo5;
//    }
//
//    public void setNukeItemStornoTo5(Integer nukeItemStornoTo5) {
//        this.nukeItemStornoTo5 = nukeItemStornoTo5;
//    }
//
//    public Double getNukeItemStornoPercent5() {
//        return nukeItemStornoPercent5;
//    }
//
//    public void setNukeItemStornoPercent5(Double nukeItemStornoPercent5) {
//        this.nukeItemStornoPercent5 = nukeItemStornoPercent5;
//    }
//
//    public Integer getNukeItemStornoFrom6() {
//        return nukeItemStornoFrom6;
//    }
//
//    public void setNukeItemStornoFrom6(Integer nukeItemStornoFrom6) {
//        this.nukeItemStornoFrom6 = nukeItemStornoFrom6;
//    }
//
//    public Integer getNukeItemStornoTo6() {
//        return nukeItemStornoTo6;
//    }
//
//    public void setNukeItemStornoTo6(Integer nukeItemStornoTo6) {
//        this.nukeItemStornoTo6 = nukeItemStornoTo6;
//    }
//
//    public Double getNukeItemStornoPercent6() {
//        return nukeItemStornoPercent6;
//    }
//
//    public void setNukeItemStornoPercent6(Double nukeItemStornoPercent6) {
//        this.nukeItemStornoPercent6 = nukeItemStornoPercent6;
//    }
//
//    public Integer getNukeItemStornoAmount1() {
//        return nukeItemStornoAmount1;
//    }
//
//    public void setNukeItemStornoAmount1(Integer nukeItemStornoAmount1) {
//        this.nukeItemStornoAmount1 = nukeItemStornoAmount1;
//    }
//
//    public Integer getNukeItemStornoAmount2() {
//        return nukeItemStornoAmount2;
//    }
//
//    public void setNukeItemStornoAmount2(Integer nukeItemStornoAmount2) {
//        this.nukeItemStornoAmount2 = nukeItemStornoAmount2;
//    }
//
//    public Integer getNukeItemStornoAmount3() {
//        return nukeItemStornoAmount3;
//    }
//
//    public void setNukeItemStornoAmount3(Integer nukeItemStornoAmount3) {
//        this.nukeItemStornoAmount3 = nukeItemStornoAmount3;
//    }
//
//    public Integer getNukeItemStornoAmount4() {
//        return nukeItemStornoAmount4;
//    }
//
//    public void setNukeItemStornoAmount4(Integer nukeItemStornoAmount4) {
//        this.nukeItemStornoAmount4 = nukeItemStornoAmount4;
//    }
//
//    public Integer getNukeItemStornoAmount5() {
//        return nukeItemStornoAmount5;
//    }
//
//    public void setNukeItemStornoAmount5(Integer nukeItemStornoAmount5) {
//        this.nukeItemStornoAmount5 = nukeItemStornoAmount5;
//    }
//
//    public Integer getNukeItemStornoAmount6() {
//        return nukeItemStornoAmount6;
//    }
//
//    public void setNukeItemStornoAmount6(Integer nukeItemStornoAmount6) {
//        this.nukeItemStornoAmount6 = nukeItemStornoAmount6;
//    }
//
//    public Short getNukeItemStornoMinMax() {
//        return nukeItemStornoMinMax;
//    }
//
//    public void setNukeItemStornoMinMax(Short nukeItemStornoMinMax) {
//        this.nukeItemStornoMinMax = nukeItemStornoMinMax;
//    }
//
//    public String getNukeItemUrlName() {
//        return nukeItemUrlName;
//    }
//
//    public void setNukeItemUrlName(String nukeItemUrlName) {
//        this.nukeItemUrlName = nukeItemUrlName;
//    }
//
//    public String getNukeItemUrlPriceclusterName() {
//        return nukeItemUrlPriceclusterName;
//    }
//
//    public void setNukeItemUrlPriceclusterName(String nukeItemUrlPriceclusterName) {
//        this.nukeItemUrlPriceclusterName = nukeItemUrlPriceclusterName;
//    }
//
//    public Integer getNukeItemSexMan() {
//        return nukeItemSexMan;
//    }
//
//    public void setNukeItemSexMan(Integer nukeItemSexMan) {
//        this.nukeItemSexMan = nukeItemSexMan;
//    }
//
//    public Integer getNukeItemSexWoman() {
//        return nukeItemSexWoman;
//    }
//
//    public void setNukeItemSexWoman(Integer nukeItemSexWoman) {
//        this.nukeItemSexWoman = nukeItemSexWoman;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorAction() {
//        return nukeItemEmotionalFaktorAction;
//    }
//
//    public void setNukeItemEmotionalFaktorAction(BigDecimal nukeItemEmotionalFaktorAction) {
//        this.nukeItemEmotionalFaktorAction = nukeItemEmotionalFaktorAction;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorPleasure() {
//        return nukeItemEmotionalFaktorPleasure;
//    }
//
//    public void setNukeItemEmotionalFaktorPleasure(BigDecimal nukeItemEmotionalFaktorPleasure) {
//        this.nukeItemEmotionalFaktorPleasure = nukeItemEmotionalFaktorPleasure;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorFun() {
//        return nukeItemEmotionalFaktorFun;
//    }
//
//    public void setNukeItemEmotionalFaktorFun(BigDecimal nukeItemEmotionalFaktorFun) {
//        this.nukeItemEmotionalFaktorFun = nukeItemEmotionalFaktorFun;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorRelax() {
//        return nukeItemEmotionalFaktorRelax;
//    }
//
//    public void setNukeItemEmotionalFaktorRelax(BigDecimal nukeItemEmotionalFaktorRelax) {
//        this.nukeItemEmotionalFaktorRelax = nukeItemEmotionalFaktorRelax;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorRomantic() {
//        return nukeItemEmotionalFaktorRomantic;
//    }
//
//    public void setNukeItemEmotionalFaktorRomantic(BigDecimal nukeItemEmotionalFaktorRomantic) {
//        this.nukeItemEmotionalFaktorRomantic = nukeItemEmotionalFaktorRomantic;
//    }
//
//    public BigDecimal getNukeItemEmotionalFaktorCreativity() {
//        return nukeItemEmotionalFaktorCreativity;
//    }
//
//    public void setNukeItemEmotionalFaktorCreativity(BigDecimal nukeItemEmotionalFaktorCreativity) {
//        this.nukeItemEmotionalFaktorCreativity = nukeItemEmotionalFaktorCreativity;
//    }
//
//    public Integer getNukeItemGrouptypeFamily() {
//        return nukeItemGrouptypeFamily;
//    }
//
//    public void setNukeItemGrouptypeFamily(Integer nukeItemGrouptypeFamily) {
//        this.nukeItemGrouptypeFamily = nukeItemGrouptypeFamily;
//    }
//
//    public Integer getNukeItemGrouptypePair() {
//        return nukeItemGrouptypePair;
//    }
//
//    public void setNukeItemGrouptypePair(Integer nukeItemGrouptypePair) {
//        this.nukeItemGrouptypePair = nukeItemGrouptypePair;
//    }
//
//    public Integer getNukeItemGrouptypeGroup() {
//        return nukeItemGrouptypeGroup;
//    }
//
//    public void setNukeItemGrouptypeGroup(Integer nukeItemGrouptypeGroup) {
//        this.nukeItemGrouptypeGroup = nukeItemGrouptypeGroup;
//    }
//
//    public Integer getNukeItemGrouptypeChild() {
//        return nukeItemGrouptypeChild;
//    }
//
//    public void setNukeItemGrouptypeChild(Integer nukeItemGrouptypeChild) {
//        this.nukeItemGrouptypeChild = nukeItemGrouptypeChild;
//    }
//
//    public Short getNukeItemEwType() {
//        return nukeItemEwType;
//    }
//
//    public void setNukeItemEwType(Short nukeItemEwType) {
//        this.nukeItemEwType = nukeItemEwType;
//    }
//
//    public Date getNukeItemModificationdate() {
//        return nukeItemModificationdate;
//    }
//
//    public void setNukeItemModificationdate(Date nukeItemModificationdate) {
//        this.nukeItemModificationdate = nukeItemModificationdate;
//    }
//
//    public String getNukeItemImagedisplayascategory() {
//        return nukeItemImagedisplayascategory;
//    }
//
//    public void setNukeItemImagedisplayascategory(String nukeItemImagedisplayascategory) {
//        this.nukeItemImagedisplayascategory = nukeItemImagedisplayascategory;
//    }

    public BigDecimal getNukeItemPriceChe() {
        return nukeItemPriceChe;
    }

    public void setNukeItemPriceChe(BigDecimal nukeItemPriceChe) {
        this.nukeItemPriceChe = nukeItemPriceChe;
    }

    public BigDecimal getNukeItemTaxChe() {
        return nukeItemTaxChe;
    }

    public void setNukeItemTaxChe(BigDecimal nukeItemTaxChe) {
        this.nukeItemTaxChe = nukeItemTaxChe;
    }

    public BigDecimal getNukeItemPriceCheEur() {
        return nukeItemPriceCheEur;
    }

    public void setNukeItemPriceCheEur(BigDecimal nukeItemPriceCheEur) {
        this.nukeItemPriceCheEur = nukeItemPriceCheEur;
    }

    public String getNukeItemDefaultCurrency() {
        return nukeItemDefaultCurrency;
    }

    public void setNukeItemDefaultCurrency(String nukeItemDefaultCurrency) {
        this.nukeItemDefaultCurrency = nukeItemDefaultCurrency;
    }

    public BigDecimal getNukeItemMaxbruttoek() {
        return nukeItemMaxbruttoek;
    }

    public void setNukeItemMaxbruttoek(BigDecimal nukeItemMaxbruttoek) {
        this.nukeItemMaxbruttoek = nukeItemMaxbruttoek;
    }

    public String getNukeItemEancode() {
        return nukeItemEancode;
    }

    public void setNukeItemEancode(String nukeItemEancode) {
        this.nukeItemEancode = nukeItemEancode;
    }

//    public String getNukeItemTextAffiliateOnline() {
//        return nukeItemTextAffiliateOnline;
//    }
//
//    public void setNukeItemTextAffiliateOnline(String nukeItemTextAffiliateOnline) {
//        this.nukeItemTextAffiliateOnline = nukeItemTextAffiliateOnline;
//    }
//
//    public String getNukeItemTextBoxOffline() {
//        return nukeItemTextBoxOffline;
//    }
//
//    public void setNukeItemTextBoxOffline(String nukeItemTextBoxOffline) {
//        this.nukeItemTextBoxOffline = nukeItemTextBoxOffline;
//    }
//
//    public String getNukeItemBoxtitle() {
//        return nukeItemBoxtitle;
//    }
//
//    public void setNukeItemBoxtitle(String nukeItemBoxtitle) {
//        this.nukeItemBoxtitle = nukeItemBoxtitle;
//    }

    public boolean getNukeItemBoxtyp() {
        return nukeItemBoxtyp;
    }

    public void setNukeItemBoxtyp(boolean nukeItemBoxtyp) {
        this.nukeItemBoxtyp = nukeItemBoxtyp;
    }

    public Boolean getNukeItemSaleschannelIsdirect() {
        return nukeItemSaleschannelIsdirect;
    }

    public void setNukeItemSaleschannelIsdirect(Boolean nukeItemSaleschannelIsdirect) {
        this.nukeItemSaleschannelIsdirect = nukeItemSaleschannelIsdirect;
    }

//    public String getNukeItemImageBox() {
//        return nukeItemImageBox;
//    }
//
//    public void setNukeItemImageBox(String nukeItemImageBox) {
//        this.nukeItemImageBox = nukeItemImageBox;
//    }
//
//    public String getNukeItemSubtitleBox() {
//        return nukeItemSubtitleBox;
//    }
//
//    public void setNukeItemSubtitleBox(String nukeItemSubtitleBox) {
//        this.nukeItemSubtitleBox = nukeItemSubtitleBox;
//    }
//
//    public String getNukeItemImageHowitworksBox() {
//        return nukeItemImageHowitworksBox;
//    }
//
//    public void setNukeItemImageHowitworksBox(String nukeItemImageHowitworksBox) {
//        this.nukeItemImageHowitworksBox = nukeItemImageHowitworksBox;
//    }
//
//    public String getNukeItemTextHowitworksBox() {
//        return nukeItemTextHowitworksBox;
//    }
//
//    public void setNukeItemTextHowitworksBox(String nukeItemTextHowitworksBox) {
//        this.nukeItemTextHowitworksBox = nukeItemTextHowitworksBox;
//    }
//
//    public boolean getNukeItemBoxIsaut() {
//        return nukeItemBoxIsaut;
//    }
//
//    public void setNukeItemBoxIsaut(boolean nukeItemBoxIsaut) {
//        this.nukeItemBoxIsaut = nukeItemBoxIsaut;
//    }
//
//    public boolean getNukeItemBoxIsdeu() {
//        return nukeItemBoxIsdeu;
//    }
//
//    public void setNukeItemBoxIsdeu(boolean nukeItemBoxIsdeu) {
//        this.nukeItemBoxIsdeu = nukeItemBoxIsdeu;
//    }
//
//    public boolean getNukeItemBoxIsche() {
//        return nukeItemBoxIsche;
//    }
//
//    public void setNukeItemBoxIsche(boolean nukeItemBoxIsche) {
//        this.nukeItemBoxIsche = nukeItemBoxIsche;
//    }
//
//    public String getNukeItemTeaserCountryAut() {
//        return nukeItemTeaserCountryAut;
//    }
//
//    public void setNukeItemTeaserCountryAut(String nukeItemTeaserCountryAut) {
//        this.nukeItemTeaserCountryAut = nukeItemTeaserCountryAut;
//    }
//
//    public String getNukeItemTeaserCountryDeu() {
//        return nukeItemTeaserCountryDeu;
//    }
//
//    public void setNukeItemTeaserCountryDeu(String nukeItemTeaserCountryDeu) {
//        this.nukeItemTeaserCountryDeu = nukeItemTeaserCountryDeu;
//    }
//
//    public String getNukeItemTeaserCountryChe() {
//        return nukeItemTeaserCountryChe;
//    }
//
//    public void setNukeItemTeaserCountryChe(String nukeItemTeaserCountryChe) {
//        this.nukeItemTeaserCountryChe = nukeItemTeaserCountryChe;
//    }
//
//    public boolean getNukeItemIsfulfillmentbooking() {
//        return nukeItemIsfulfillmentbooking;
//    }
//
//    public void setNukeItemIsfulfillmentbooking(boolean nukeItemIsfulfillmentbooking) {
//        this.nukeItemIsfulfillmentbooking = nukeItemIsfulfillmentbooking;
//    }
//
//    public String getNukeItemCataloguefolder() {
//        return nukeItemCataloguefolder;
//    }
//
//    public void setNukeItemCataloguefolder(String nukeItemCataloguefolder) {
//        this.nukeItemCataloguefolder = nukeItemCataloguefolder;
//    }

    public boolean getNukeItemAdminitemforcart() {
        return nukeItemAdminitemforcart;
    }

    public void setNukeItemAdminitemforcart(boolean nukeItemAdminitemforcart) {
        this.nukeItemAdminitemforcart = nukeItemAdminitemforcart;
    }

    public Date getNukeItemBoxValidto() {
        return nukeItemBoxValidto;
    }

    public void setNukeItemBoxValidto(Date nukeItemBoxValidto) {
        this.nukeItemBoxValidto = nukeItemBoxValidto;
    }

    public NukeMrcommerceCategory getNukeMrcommerceCategory() {
        return nukeMrcommerceCategory;
    }

    public void setNukeMrcommerceCategory(NukeMrcommerceCategory nukeMrcommerceCategory) {
        this.nukeMrcommerceCategory = nukeMrcommerceCategory;
    }

//    public Collection<NukeMrcommerceArrangement> getNukeMrcommerceArrangementCollection() {
//        return nukeMrcommerceArrangementCollection;
//    }
//
//    public void setNukeMrcommerceArrangementCollection(Collection<NukeMrcommerceArrangement> nukeMrcommerceArrangementCollection) {
//        this.nukeMrcommerceArrangementCollection = nukeMrcommerceArrangementCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeItemId != null ? nukeItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeMrcommerceItems)) {
            return false;
        }
        NukeMrcommerceItems other = (NukeMrcommerceItems) object;
        if ((this.nukeItemId == null && other.nukeItemId != null) || (this.nukeItemId != null && !this.nukeItemId.equals(other.nukeItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeMrcommerceItems[nukeItemId=" + nukeItemId + "]";
    }

}
