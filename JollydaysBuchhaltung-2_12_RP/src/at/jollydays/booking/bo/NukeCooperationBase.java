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
@Table(name = "nuke_cooperation_base")
@NamedQueries({
    @NamedQuery(name = "NukeCooperationBase.findAll", query = "SELECT n FROM NukeCooperationBase n"),
    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationId", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationId = :nukeCooperationId"),
    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationCustomId", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationCustomId = :nukeCooperationCustomId")
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationName", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationName = :nukeCooperationName"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationDescription", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationDescription = :nukeCooperationDescription"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationType", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationType = :nukeCooperationType"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAgencyType", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAgencyType = :nukeCooperationAgencyType"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationHasprovision", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationHasprovision = :nukeCooperationHasprovision"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionPercentage", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionPercentage = :nukeCooperationProvisionPercentage"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionIsBrutto", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionIsBrutto = :nukeCooperationProvisionIsBrutto"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationCustomThankyouPage", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationCustomThankyouPage = :nukeCooperationCustomThankyouPage"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationUser", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationUser = :nukeCooperationUser"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationDomain", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationDomain = :nukeCooperationDomain"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationBrandedshopDefaultcontent", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationBrandedshopDefaultcontent = :nukeCooperationBrandedshopDefaultcontent"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAllitemsdiscount", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAllitemsdiscount = :nukeCooperationAllitemsdiscount"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAllitemsdiscountPercentage", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAllitemsdiscountPercentage = :nukeCooperationAllitemsdiscountPercentage"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionOnInvoice", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionOnInvoice = :nukeCooperationProvisionOnInvoice"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationPunkteshopFfrequired", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationPunkteshopFfrequired = :nukeCooperationPunkteshopFfrequired"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationPunkteshopFfcomment", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationPunkteshopFfcomment = :nukeCooperationPunkteshopFfcomment"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationStarturl", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationStarturl = :nukeCooperationStarturl"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowLogo", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowLogo = :nukeCooperationShowLogo"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowBreadcrumb", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowBreadcrumb = :nukeCooperationShowBreadcrumb"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowMenu", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowMenu = :nukeCooperationShowMenu"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationHastheme", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationHastheme = :nukeCooperationHastheme"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationResponsableSales", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationResponsableSales = :nukeCooperationResponsableSales"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationResponsableService", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationResponsableService = :nukeCooperationResponsableService"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationUrlpartner", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationUrlpartner = :nukeCooperationUrlpartner"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationValidfrom", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationValidfrom = :nukeCooperationValidfrom"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationValidto", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationValidto = :nukeCooperationValidto"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationIshidden", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationIshidden = :nukeCooperationIshidden"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationResponsableUrlpartner", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationResponsableUrlpartner = :nukeCooperationResponsableUrlpartner"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionIsFixed", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionIsFixed = :nukeCooperationProvisionIsFixed"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionFixedValue", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionFixedValue = :nukeCooperationProvisionFixedValue"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionWhenToInvoice", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionWhenToInvoice = :nukeCooperationProvisionWhenToInvoice"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationInvoiceFromCountry", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationInvoiceFromCountry = :nukeCooperationInvoiceFromCountry"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationTrackinghascookie", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationTrackinghascookie = :nukeCooperationTrackinghascookie"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationIfcookieDuration", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationIfcookieDuration = :nukeCooperationIfcookieDuration"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationTrackinghasjoker", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationTrackinghasjoker = :nukeCooperationTrackinghasjoker"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationDefaultFfSalutation", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationDefaultFfSalutation = :nukeCooperationDefaultFfSalutation"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationDefaultFfMessage", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationDefaultFfMessage = :nukeCooperationDefaultFfMessage"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationProvisionPercentageForDiscountproducts", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationProvisionPercentageForDiscountproducts = :nukeCooperationProvisionPercentageForDiscountproducts"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAllitemsdiscountPriceperitem", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAllitemsdiscountPriceperitem = :nukeCooperationAllitemsdiscountPriceperitem"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationInvoicewithparent", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationInvoicewithparent = :nukeCooperationInvoicewithparent"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAllowedPackaging", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAllowedPackaging = :nukeCooperationAllowedPackaging"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationAllowedPayment", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationAllowedPayment = :nukeCooperationAllowedPayment"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationLoginrequired", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationLoginrequired = :nukeCooperationLoginrequired"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationSaleschannel", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationSaleschannel = :nukeCooperationSaleschannel"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationPackingforfree", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationPackingforfree = :nukeCooperationPackingforfree"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationPostageforfree", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationPostageforfree = :nukeCooperationPostageforfree"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationSpecialproductitemid", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationSpecialproductitemid = :nukeCooperationSpecialproductitemid"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationSpecialproductvalue", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationSpecialproductvalue = :nukeCooperationSpecialproductvalue"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationSpecialproductvalidfrom", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationSpecialproductvalidfrom = :nukeCooperationSpecialproductvalidfrom"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationItemvalidtotype", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationItemvalidtotype = :nukeCooperationItemvalidtotype"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationItemvalidtodays", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationItemvalidtodays = :nukeCooperationItemvalidtodays"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationItemvalidtodate", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationItemvalidtodate = :nukeCooperationItemvalidtodate"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationFastbookingpossible", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationFastbookingpossible = :nukeCooperationFastbookingpossible"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationHasspecialdedication", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationHasspecialdedication = :nukeCooperationHasspecialdedication"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationSpecialfulfillmentlabel", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationSpecialfulfillmentlabel = :nukeCooperationSpecialfulfillmentlabel"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationHasxmlinvoice", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationHasxmlinvoice = :nukeCooperationHasxmlinvoice"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationTextLogondescription", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationTextLogondescription = :nukeCooperationTextLogondescription"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationCollectiveinvoice", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationCollectiveinvoice = :nukeCooperationCollectiveinvoice"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationBesorger", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationBesorger = :nukeCooperationBesorger"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationStyleConfig", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationStyleConfig = :nukeCooperationStyleConfig"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowcheckbox", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowcheckbox = :nukeCooperationShowcheckbox"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowtextfield", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowtextfield = :nukeCooperationShowtextfield"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationShowtextfield2", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationShowtextfield2 = :nukeCooperationShowtextfield2"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationTextfieldtitle", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationTextfieldtitle = :nukeCooperationTextfieldtitle"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationTextfield2title", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationTextfield2title = :nukeCooperationTextfield2title"),
//    @NamedQuery(name = "NukeCooperationBase.findByNukeCooperationCheckboxtitle", query = "SELECT n FROM NukeCooperationBase n WHERE n.nukeCooperationCheckboxtitle = :nukeCooperationCheckboxtitle")
})
public class NukeCooperationBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_id")
    private Integer nukeCooperationId;
    @Column(name = "nuke_cooperation_custom_id")
    private String nukeCooperationCustomId;
    @Column(name = "nuke_cooperation_name")
    private String nukeCooperationName;
    @Column(name = "nuke_cooperation_description")
    private String nukeCooperationDescription;
    @Column(name = "nuke_cooperation_type")
    private Short nukeCooperationType;
    @Column(name = "nuke_cooperation_agency_type")
    private Short nukeCooperationAgencyType;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_hasprovision")
    private boolean nukeCooperationHasprovision;
    @Column(name = "nuke_cooperation_provision_percentage")
    private BigDecimal nukeCooperationProvisionPercentage;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_provision_is_brutto")
    private boolean nukeCooperationProvisionIsBrutto;
    @Column(name = "nuke_cooperation_custom_thankyou_page")
    private Short nukeCooperationCustomThankyouPage;
    @Lob
    @Column(name = "nuke_cooperation_custom_thankyou_page_text")
    private String nukeCooperationCustomThankyouPageText;
    @Column(name = "nuke_cooperation_user")
    private Integer nukeCooperationUser;
    @Column(name = "nuke_cooperation_domain")
    private String nukeCooperationDomain;
    @Lob
    @Column(name = "nuke_cooperation_header_text")
    private String nukeCooperationHeaderText;
    @Lob
    @Column(name = "nuke_cooperation_text_before")
    private String nukeCooperationTextBefore;
    @Lob
    @Column(name = "nuke_cooperation_text_after")
    private String nukeCooperationTextAfter;
    @Lob
    @Column(name = "nuke_cooperation_text_user_home")
    private String nukeCooperationTextUserHome;
    @Column(name = "nuke_cooperation_brandedshop_defaultcontent")
    private Boolean nukeCooperationBrandedshopDefaultcontent;
    @Column(name = "nuke_cooperation_allitemsdiscount")
    private Short nukeCooperationAllitemsdiscount;
    @Column(name = "nuke_cooperation_allitemsdiscount_percentage")
    private BigDecimal nukeCooperationAllitemsdiscountPercentage;
    @Column(name = "nuke_cooperation_provision_on_invoice")
    private Boolean nukeCooperationProvisionOnInvoice;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_punkteshop_ffrequired")
    private short nukeCooperationPunkteshopFfrequired;
    @Column(name = "nuke_cooperation_punkteshop_ffcomment")
    private String nukeCooperationPunkteshopFfcomment;
    @Column(name = "nuke_cooperation_starturl")
    private String nukeCooperationStarturl;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_show_logo")
    private short nukeCooperationShowLogo;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_show_breadcrumb")
    private short nukeCooperationShowBreadcrumb;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_show_menu")
    private short nukeCooperationShowMenu;
    @Lob
    @Column(name = "nuke_cooperation_show_styles")
    private String nukeCooperationShowStyles;
    @Column(name = "nuke_cooperation_hastheme")
    private String nukeCooperationHastheme;
    @Column(name = "nuke_cooperation_responsable_sales")
    private Integer nukeCooperationResponsableSales;
    @Column(name = "nuke_cooperation_responsable_service")
    private Integer nukeCooperationResponsableService;
    @Column(name = "nuke_cooperation_urlpartner")
    private String nukeCooperationUrlpartner;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_validfrom")
    @Temporal(TemporalType.DATE)
    private Date nukeCooperationValidfrom;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_validto")
    @Temporal(TemporalType.DATE)
    private Date nukeCooperationValidto;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_ishidden")
    private short nukeCooperationIshidden;
    @Column(name = "nuke_cooperation_responsable_urlpartner")
    private String nukeCooperationResponsableUrlpartner;
    @Column(name = "nuke_cooperation_provision_is_fixed")
    private Boolean nukeCooperationProvisionIsFixed;
    @Column(name = "nuke_cooperation_provision_fixed_value")
    private BigDecimal nukeCooperationProvisionFixedValue;
    @Column(name = "nuke_cooperation_provision_when_to_invoice")
    private Short nukeCooperationProvisionWhenToInvoice;
    @Column(name = "nuke_cooperation_invoice_from_country")
    private String nukeCooperationInvoiceFromCountry;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_trackinghascookie")
    private boolean nukeCooperationTrackinghascookie;
    @Column(name = "nuke_cooperation_ifcookie_duration")
    private Integer nukeCooperationIfcookieDuration;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_trackinghasjoker")
    private boolean nukeCooperationTrackinghasjoker;
    @Column(name = "nuke_cooperation_default_ff_salutation")
    private String nukeCooperationDefaultFfSalutation;
    @Column(name = "nuke_cooperation_default_ff_message")
    private String nukeCooperationDefaultFfMessage;
    @Column(name = "nuke_cooperation_provision_percentage_for_discountproducts")
    private Long nukeCooperationProvisionPercentageForDiscountproducts;
    @Column(name = "nuke_cooperation_allitemsdiscount_priceperitem")
    private BigDecimal nukeCooperationAllitemsdiscountPriceperitem;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_invoicewithparent")
    private boolean nukeCooperationInvoicewithparent;
    @Column(name = "nuke_cooperation_allowed_packaging")
    private String nukeCooperationAllowedPackaging;
    @Column(name = "nuke_cooperation_allowed_payment")
    private String nukeCooperationAllowedPayment;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_loginrequired")
    private boolean nukeCooperationLoginrequired;
    @Lob
    @Column(name = "nuke_cooperation_logincredential")
    private String nukeCooperationLogincredential;
    @Lob
    @Column(name = "nuke_cooperation_text_beforelogin")
    private String nukeCooperationTextBeforelogin;
    @Column(name = "nuke_cooperation_saleschannel")
    private Integer nukeCooperationSaleschannel;
    @Column(name = "nuke_cooperation_packingforfree")
    private Boolean nukeCooperationPackingforfree;
    @Column(name = "nuke_cooperation_postageforfree")
    private Boolean nukeCooperationPostageforfree;
    @Column(name = "nuke_cooperation_specialproductitemid")
    private Integer nukeCooperationSpecialproductitemid;
    @Column(name = "nuke_cooperation_specialproductvalue")
    private Double nukeCooperationSpecialproductvalue;
    @Column(name = "nuke_cooperation_specialproductvalidfrom")
    private BigDecimal nukeCooperationSpecialproductvalidfrom;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_itemvalidtotype")
    private boolean nukeCooperationItemvalidtotype;
    @Column(name = "nuke_cooperation_itemvalidtodays")
    private Integer nukeCooperationItemvalidtodays;
    @Column(name = "nuke_cooperation_itemvalidtodate")
    @Temporal(TemporalType.DATE)
    private Date nukeCooperationItemvalidtodate;
    @Column(name = "nuke_cooperation_fastbookingpossible")
    private Boolean nukeCooperationFastbookingpossible;
    @Column(name = "nuke_cooperation_hasspecialdedication")
    private Boolean nukeCooperationHasspecialdedication;
    @Column(name = "nuke_cooperation_specialfulfillmentlabel")
    private String nukeCooperationSpecialfulfillmentlabel;
    @Column(name = "nuke_cooperation_hasxmlinvoice")
    private Boolean nukeCooperationHasxmlinvoice;
    @Lob
    @Column(name = "nuke_cooperation_text_afterlogin")
    private String nukeCooperationTextAfterlogin;
    @Column(name = "nuke_cooperation_text_logondescription")
    private String nukeCooperationTextLogondescription;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_collectiveinvoice")
    private boolean nukeCooperationCollectiveinvoice;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_besorger")
    private boolean nukeCooperationBesorger;
    @Column(name = "nuke_cooperation_style_config")
    private String nukeCooperationStyleConfig;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_showcheckbox")
    private boolean nukeCooperationShowcheckbox;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_showtextfield")
    private boolean nukeCooperationShowtextfield;
    @Basic(optional = false)
    @Column(name = "nuke_cooperation_showtextfield2")
    private boolean nukeCooperationShowtextfield2;
    @Column(name = "nuke_cooperation_textfieldtitle")
    private String nukeCooperationTextfieldtitle;
    @Column(name = "nuke_cooperation_textfield2title")
    private String nukeCooperationTextfield2title;
    @Column(name = "nuke_cooperation_checkboxtitle")
    private String nukeCooperationCheckboxtitle;
    @Lob
    @Column(name = "nuke_cooperation_textfieldvalidation")
    private String nukeCooperationTextfieldvalidation;
    @Lob
    @Column(name = "nuke_cooperation_textfield2validation")
    private String nukeCooperationTextfield2validation;
    @OneToMany(mappedBy = "nukeCooperationBase")
    private Collection<NukeCooperationBase> nukeCooperationBaseCollection;
    @JoinColumn(name = "nuke_cooperation_hasparent", referencedColumnName = "nuke_cooperation_id")
    @ManyToOne
    private NukeCooperationBase nukeCooperationBase;

    public NukeCooperationBase() {
    }

    public NukeCooperationBase(Integer nukeCooperationId) {
        this.nukeCooperationId = nukeCooperationId;
    }

    public NukeCooperationBase(Integer nukeCooperationId, boolean nukeCooperationHasprovision, boolean nukeCooperationProvisionIsBrutto, short nukeCooperationPunkteshopFfrequired, short nukeCooperationShowLogo, short nukeCooperationShowBreadcrumb, short nukeCooperationShowMenu, Date nukeCooperationValidfrom, Date nukeCooperationValidto, short nukeCooperationIshidden, boolean nukeCooperationTrackinghascookie, boolean nukeCooperationTrackinghasjoker, boolean nukeCooperationInvoicewithparent, boolean nukeCooperationLoginrequired, boolean nukeCooperationItemvalidtotype, boolean nukeCooperationCollectiveinvoice, boolean nukeCooperationBesorger, boolean nukeCooperationShowcheckbox, boolean nukeCooperationShowtextfield, boolean nukeCooperationShowtextfield2) {
        this.nukeCooperationId = nukeCooperationId;
        this.nukeCooperationHasprovision = nukeCooperationHasprovision;
        this.nukeCooperationProvisionIsBrutto = nukeCooperationProvisionIsBrutto;
        this.nukeCooperationPunkteshopFfrequired = nukeCooperationPunkteshopFfrequired;
        this.nukeCooperationShowLogo = nukeCooperationShowLogo;
        this.nukeCooperationShowBreadcrumb = nukeCooperationShowBreadcrumb;
        this.nukeCooperationShowMenu = nukeCooperationShowMenu;
        this.nukeCooperationValidfrom = nukeCooperationValidfrom;
        this.nukeCooperationValidto = nukeCooperationValidto;
        this.nukeCooperationIshidden = nukeCooperationIshidden;
        this.nukeCooperationTrackinghascookie = nukeCooperationTrackinghascookie;
        this.nukeCooperationTrackinghasjoker = nukeCooperationTrackinghasjoker;
        this.nukeCooperationInvoicewithparent = nukeCooperationInvoicewithparent;
        this.nukeCooperationLoginrequired = nukeCooperationLoginrequired;
        this.nukeCooperationItemvalidtotype = nukeCooperationItemvalidtotype;
        this.nukeCooperationCollectiveinvoice = nukeCooperationCollectiveinvoice;
        this.nukeCooperationBesorger = nukeCooperationBesorger;
        this.nukeCooperationShowcheckbox = nukeCooperationShowcheckbox;
        this.nukeCooperationShowtextfield = nukeCooperationShowtextfield;
        this.nukeCooperationShowtextfield2 = nukeCooperationShowtextfield2;
    }

    public Integer getNukeCooperationId() {
        return nukeCooperationId;
    }

    public void setNukeCooperationId(Integer nukeCooperationId) {
        this.nukeCooperationId = nukeCooperationId;
    }

    public String getNukeCooperationCustomId() {
        return nukeCooperationCustomId;
    }

    public void setNukeCooperationCustomId(String nukeCooperationCustomId) {
        this.nukeCooperationCustomId = nukeCooperationCustomId;
    }

    public String getNukeCooperationName() {
        return nukeCooperationName;
    }

    public void setNukeCooperationName(String nukeCooperationName) {
        this.nukeCooperationName = nukeCooperationName;
    }

    public String getNukeCooperationDescription() {
        return nukeCooperationDescription;
    }

    public void setNukeCooperationDescription(String nukeCooperationDescription) {
        this.nukeCooperationDescription = nukeCooperationDescription;
    }

    public Short getNukeCooperationType() {
        return nukeCooperationType;
    }

    public void setNukeCooperationType(Short nukeCooperationType) {
        this.nukeCooperationType = nukeCooperationType;
    }

    public Short getNukeCooperationAgencyType() {
        return nukeCooperationAgencyType;
    }

    public void setNukeCooperationAgencyType(Short nukeCooperationAgencyType) {
        this.nukeCooperationAgencyType = nukeCooperationAgencyType;
    }

    public boolean getNukeCooperationHasprovision() {
        return nukeCooperationHasprovision;
    }

    public void setNukeCooperationHasprovision(boolean nukeCooperationHasprovision) {
        this.nukeCooperationHasprovision = nukeCooperationHasprovision;
    }

    public BigDecimal getNukeCooperationProvisionPercentage() {
        return nukeCooperationProvisionPercentage;
    }

    public void setNukeCooperationProvisionPercentage(BigDecimal nukeCooperationProvisionPercentage) {
        this.nukeCooperationProvisionPercentage = nukeCooperationProvisionPercentage;
    }

    public boolean getNukeCooperationProvisionIsBrutto() {
        return nukeCooperationProvisionIsBrutto;
    }

    public void setNukeCooperationProvisionIsBrutto(boolean nukeCooperationProvisionIsBrutto) {
        this.nukeCooperationProvisionIsBrutto = nukeCooperationProvisionIsBrutto;
    }

    public Short getNukeCooperationCustomThankyouPage() {
        return nukeCooperationCustomThankyouPage;
    }

    public void setNukeCooperationCustomThankyouPage(Short nukeCooperationCustomThankyouPage) {
        this.nukeCooperationCustomThankyouPage = nukeCooperationCustomThankyouPage;
    }

    public String getNukeCooperationCustomThankyouPageText() {
        return nukeCooperationCustomThankyouPageText;
    }

    public void setNukeCooperationCustomThankyouPageText(String nukeCooperationCustomThankyouPageText) {
        this.nukeCooperationCustomThankyouPageText = nukeCooperationCustomThankyouPageText;
    }

    public Integer getNukeCooperationUser() {
        return nukeCooperationUser;
    }

    public void setNukeCooperationUser(Integer nukeCooperationUser) {
        this.nukeCooperationUser = nukeCooperationUser;
    }

    public String getNukeCooperationDomain() {
        return nukeCooperationDomain;
    }

    public void setNukeCooperationDomain(String nukeCooperationDomain) {
        this.nukeCooperationDomain = nukeCooperationDomain;
    }

    public String getNukeCooperationHeaderText() {
        return nukeCooperationHeaderText;
    }

    public void setNukeCooperationHeaderText(String nukeCooperationHeaderText) {
        this.nukeCooperationHeaderText = nukeCooperationHeaderText;
    }

    public String getNukeCooperationTextBefore() {
        return nukeCooperationTextBefore;
    }

    public void setNukeCooperationTextBefore(String nukeCooperationTextBefore) {
        this.nukeCooperationTextBefore = nukeCooperationTextBefore;
    }

    public String getNukeCooperationTextAfter() {
        return nukeCooperationTextAfter;
    }

    public void setNukeCooperationTextAfter(String nukeCooperationTextAfter) {
        this.nukeCooperationTextAfter = nukeCooperationTextAfter;
    }

    public String getNukeCooperationTextUserHome() {
        return nukeCooperationTextUserHome;
    }

    public void setNukeCooperationTextUserHome(String nukeCooperationTextUserHome) {
        this.nukeCooperationTextUserHome = nukeCooperationTextUserHome;
    }

    public Boolean getNukeCooperationBrandedshopDefaultcontent() {
        return nukeCooperationBrandedshopDefaultcontent;
    }

    public void setNukeCooperationBrandedshopDefaultcontent(Boolean nukeCooperationBrandedshopDefaultcontent) {
        this.nukeCooperationBrandedshopDefaultcontent = nukeCooperationBrandedshopDefaultcontent;
    }

    public Short getNukeCooperationAllitemsdiscount() {
        return nukeCooperationAllitemsdiscount;
    }

    public void setNukeCooperationAllitemsdiscount(Short nukeCooperationAllitemsdiscount) {
        this.nukeCooperationAllitemsdiscount = nukeCooperationAllitemsdiscount;
    }

    public BigDecimal getNukeCooperationAllitemsdiscountPercentage() {
        return nukeCooperationAllitemsdiscountPercentage;
    }

    public void setNukeCooperationAllitemsdiscountPercentage(BigDecimal nukeCooperationAllitemsdiscountPercentage) {
        this.nukeCooperationAllitemsdiscountPercentage = nukeCooperationAllitemsdiscountPercentage;
    }

    public Boolean getNukeCooperationProvisionOnInvoice() {
        return nukeCooperationProvisionOnInvoice;
    }

    public void setNukeCooperationProvisionOnInvoice(Boolean nukeCooperationProvisionOnInvoice) {
        this.nukeCooperationProvisionOnInvoice = nukeCooperationProvisionOnInvoice;
    }

    public short getNukeCooperationPunkteshopFfrequired() {
        return nukeCooperationPunkteshopFfrequired;
    }

    public void setNukeCooperationPunkteshopFfrequired(short nukeCooperationPunkteshopFfrequired) {
        this.nukeCooperationPunkteshopFfrequired = nukeCooperationPunkteshopFfrequired;
    }

    public String getNukeCooperationPunkteshopFfcomment() {
        return nukeCooperationPunkteshopFfcomment;
    }

    public void setNukeCooperationPunkteshopFfcomment(String nukeCooperationPunkteshopFfcomment) {
        this.nukeCooperationPunkteshopFfcomment = nukeCooperationPunkteshopFfcomment;
    }

    public String getNukeCooperationStarturl() {
        return nukeCooperationStarturl;
    }

    public void setNukeCooperationStarturl(String nukeCooperationStarturl) {
        this.nukeCooperationStarturl = nukeCooperationStarturl;
    }

    public short getNukeCooperationShowLogo() {
        return nukeCooperationShowLogo;
    }

    public void setNukeCooperationShowLogo(short nukeCooperationShowLogo) {
        this.nukeCooperationShowLogo = nukeCooperationShowLogo;
    }

    public short getNukeCooperationShowBreadcrumb() {
        return nukeCooperationShowBreadcrumb;
    }

    public void setNukeCooperationShowBreadcrumb(short nukeCooperationShowBreadcrumb) {
        this.nukeCooperationShowBreadcrumb = nukeCooperationShowBreadcrumb;
    }

    public short getNukeCooperationShowMenu() {
        return nukeCooperationShowMenu;
    }

    public void setNukeCooperationShowMenu(short nukeCooperationShowMenu) {
        this.nukeCooperationShowMenu = nukeCooperationShowMenu;
    }

    public String getNukeCooperationShowStyles() {
        return nukeCooperationShowStyles;
    }

    public void setNukeCooperationShowStyles(String nukeCooperationShowStyles) {
        this.nukeCooperationShowStyles = nukeCooperationShowStyles;
    }

    public String getNukeCooperationHastheme() {
        return nukeCooperationHastheme;
    }

    public void setNukeCooperationHastheme(String nukeCooperationHastheme) {
        this.nukeCooperationHastheme = nukeCooperationHastheme;
    }

    public Integer getNukeCooperationResponsableSales() {
        return nukeCooperationResponsableSales;
    }

    public void setNukeCooperationResponsableSales(Integer nukeCooperationResponsableSales) {
        this.nukeCooperationResponsableSales = nukeCooperationResponsableSales;
    }

    public Integer getNukeCooperationResponsableService() {
        return nukeCooperationResponsableService;
    }

    public void setNukeCooperationResponsableService(Integer nukeCooperationResponsableService) {
        this.nukeCooperationResponsableService = nukeCooperationResponsableService;
    }

    public String getNukeCooperationUrlpartner() {
        return nukeCooperationUrlpartner;
    }

    public void setNukeCooperationUrlpartner(String nukeCooperationUrlpartner) {
        this.nukeCooperationUrlpartner = nukeCooperationUrlpartner;
    }

    public Date getNukeCooperationValidfrom() {
        return nukeCooperationValidfrom;
    }

    public void setNukeCooperationValidfrom(Date nukeCooperationValidfrom) {
        this.nukeCooperationValidfrom = nukeCooperationValidfrom;
    }

    public Date getNukeCooperationValidto() {
        return nukeCooperationValidto;
    }

    public void setNukeCooperationValidto(Date nukeCooperationValidto) {
        this.nukeCooperationValidto = nukeCooperationValidto;
    }

    public short getNukeCooperationIshidden() {
        return nukeCooperationIshidden;
    }

    public void setNukeCooperationIshidden(short nukeCooperationIshidden) {
        this.nukeCooperationIshidden = nukeCooperationIshidden;
    }

    public String getNukeCooperationResponsableUrlpartner() {
        return nukeCooperationResponsableUrlpartner;
    }

    public void setNukeCooperationResponsableUrlpartner(String nukeCooperationResponsableUrlpartner) {
        this.nukeCooperationResponsableUrlpartner = nukeCooperationResponsableUrlpartner;
    }

    public Boolean getNukeCooperationProvisionIsFixed() {
        return nukeCooperationProvisionIsFixed;
    }

    public void setNukeCooperationProvisionIsFixed(Boolean nukeCooperationProvisionIsFixed) {
        this.nukeCooperationProvisionIsFixed = nukeCooperationProvisionIsFixed;
    }

    public BigDecimal getNukeCooperationProvisionFixedValue() {
        return nukeCooperationProvisionFixedValue;
    }

    public void setNukeCooperationProvisionFixedValue(BigDecimal nukeCooperationProvisionFixedValue) {
        this.nukeCooperationProvisionFixedValue = nukeCooperationProvisionFixedValue;
    }

    public Short getNukeCooperationProvisionWhenToInvoice() {
        return nukeCooperationProvisionWhenToInvoice;
    }

    public void setNukeCooperationProvisionWhenToInvoice(Short nukeCooperationProvisionWhenToInvoice) {
        this.nukeCooperationProvisionWhenToInvoice = nukeCooperationProvisionWhenToInvoice;
    }

    public String getNukeCooperationInvoiceFromCountry() {
        return nukeCooperationInvoiceFromCountry;
    }

    public void setNukeCooperationInvoiceFromCountry(String nukeCooperationInvoiceFromCountry) {
        this.nukeCooperationInvoiceFromCountry = nukeCooperationInvoiceFromCountry;
    }

    public boolean getNukeCooperationTrackinghascookie() {
        return nukeCooperationTrackinghascookie;
    }

    public void setNukeCooperationTrackinghascookie(boolean nukeCooperationTrackinghascookie) {
        this.nukeCooperationTrackinghascookie = nukeCooperationTrackinghascookie;
    }

    public Integer getNukeCooperationIfcookieDuration() {
        return nukeCooperationIfcookieDuration;
    }

    public void setNukeCooperationIfcookieDuration(Integer nukeCooperationIfcookieDuration) {
        this.nukeCooperationIfcookieDuration = nukeCooperationIfcookieDuration;
    }

    public boolean getNukeCooperationTrackinghasjoker() {
        return nukeCooperationTrackinghasjoker;
    }

    public void setNukeCooperationTrackinghasjoker(boolean nukeCooperationTrackinghasjoker) {
        this.nukeCooperationTrackinghasjoker = nukeCooperationTrackinghasjoker;
    }

    public String getNukeCooperationDefaultFfSalutation() {
        return nukeCooperationDefaultFfSalutation;
    }

    public void setNukeCooperationDefaultFfSalutation(String nukeCooperationDefaultFfSalutation) {
        this.nukeCooperationDefaultFfSalutation = nukeCooperationDefaultFfSalutation;
    }

    public String getNukeCooperationDefaultFfMessage() {
        return nukeCooperationDefaultFfMessage;
    }

    public void setNukeCooperationDefaultFfMessage(String nukeCooperationDefaultFfMessage) {
        this.nukeCooperationDefaultFfMessage = nukeCooperationDefaultFfMessage;
    }

    public Long getNukeCooperationProvisionPercentageForDiscountproducts() {
        return nukeCooperationProvisionPercentageForDiscountproducts;
    }

    public void setNukeCooperationProvisionPercentageForDiscountproducts(Long nukeCooperationProvisionPercentageForDiscountproducts) {
        this.nukeCooperationProvisionPercentageForDiscountproducts = nukeCooperationProvisionPercentageForDiscountproducts;
    }

    public BigDecimal getNukeCooperationAllitemsdiscountPriceperitem() {
        return nukeCooperationAllitemsdiscountPriceperitem;
    }

    public void setNukeCooperationAllitemsdiscountPriceperitem(BigDecimal nukeCooperationAllitemsdiscountPriceperitem) {
        this.nukeCooperationAllitemsdiscountPriceperitem = nukeCooperationAllitemsdiscountPriceperitem;
    }

    public boolean getNukeCooperationInvoicewithparent() {
        return nukeCooperationInvoicewithparent;
    }

    public void setNukeCooperationInvoicewithparent(boolean nukeCooperationInvoicewithparent) {
        this.nukeCooperationInvoicewithparent = nukeCooperationInvoicewithparent;
    }

    public String getNukeCooperationAllowedPackaging() {
        return nukeCooperationAllowedPackaging;
    }

    public void setNukeCooperationAllowedPackaging(String nukeCooperationAllowedPackaging) {
        this.nukeCooperationAllowedPackaging = nukeCooperationAllowedPackaging;
    }

    public String getNukeCooperationAllowedPayment() {
        return nukeCooperationAllowedPayment;
    }

    public void setNukeCooperationAllowedPayment(String nukeCooperationAllowedPayment) {
        this.nukeCooperationAllowedPayment = nukeCooperationAllowedPayment;
    }

    public boolean getNukeCooperationLoginrequired() {
        return nukeCooperationLoginrequired;
    }

    public void setNukeCooperationLoginrequired(boolean nukeCooperationLoginrequired) {
        this.nukeCooperationLoginrequired = nukeCooperationLoginrequired;
    }

    public String getNukeCooperationLogincredential() {
        return nukeCooperationLogincredential;
    }

    public void setNukeCooperationLogincredential(String nukeCooperationLogincredential) {
        this.nukeCooperationLogincredential = nukeCooperationLogincredential;
    }

    public String getNukeCooperationTextBeforelogin() {
        return nukeCooperationTextBeforelogin;
    }

    public void setNukeCooperationTextBeforelogin(String nukeCooperationTextBeforelogin) {
        this.nukeCooperationTextBeforelogin = nukeCooperationTextBeforelogin;
    }

    public Integer getNukeCooperationSaleschannel() {
        return nukeCooperationSaleschannel;
    }

    public void setNukeCooperationSaleschannel(Integer nukeCooperationSaleschannel) {
        this.nukeCooperationSaleschannel = nukeCooperationSaleschannel;
    }

    public Boolean getNukeCooperationPackingforfree() {
        return nukeCooperationPackingforfree;
    }

    public void setNukeCooperationPackingforfree(Boolean nukeCooperationPackingforfree) {
        this.nukeCooperationPackingforfree = nukeCooperationPackingforfree;
    }

    public Boolean getNukeCooperationPostageforfree() {
        return nukeCooperationPostageforfree;
    }

    public void setNukeCooperationPostageforfree(Boolean nukeCooperationPostageforfree) {
        this.nukeCooperationPostageforfree = nukeCooperationPostageforfree;
    }

    public Integer getNukeCooperationSpecialproductitemid() {
        return nukeCooperationSpecialproductitemid;
    }

    public void setNukeCooperationSpecialproductitemid(Integer nukeCooperationSpecialproductitemid) {
        this.nukeCooperationSpecialproductitemid = nukeCooperationSpecialproductitemid;
    }

    public Double getNukeCooperationSpecialproductvalue() {
        return nukeCooperationSpecialproductvalue;
    }

    public void setNukeCooperationSpecialproductvalue(Double nukeCooperationSpecialproductvalue) {
        this.nukeCooperationSpecialproductvalue = nukeCooperationSpecialproductvalue;
    }

    public BigDecimal getNukeCooperationSpecialproductvalidfrom() {
        return nukeCooperationSpecialproductvalidfrom;
    }

    public void setNukeCooperationSpecialproductvalidfrom(BigDecimal nukeCooperationSpecialproductvalidfrom) {
        this.nukeCooperationSpecialproductvalidfrom = nukeCooperationSpecialproductvalidfrom;
    }

    public boolean getNukeCooperationItemvalidtotype() {
        return nukeCooperationItemvalidtotype;
    }

    public void setNukeCooperationItemvalidtotype(boolean nukeCooperationItemvalidtotype) {
        this.nukeCooperationItemvalidtotype = nukeCooperationItemvalidtotype;
    }

    public Integer getNukeCooperationItemvalidtodays() {
        return nukeCooperationItemvalidtodays;
    }

    public void setNukeCooperationItemvalidtodays(Integer nukeCooperationItemvalidtodays) {
        this.nukeCooperationItemvalidtodays = nukeCooperationItemvalidtodays;
    }

    public Date getNukeCooperationItemvalidtodate() {
        return nukeCooperationItemvalidtodate;
    }

    public void setNukeCooperationItemvalidtodate(Date nukeCooperationItemvalidtodate) {
        this.nukeCooperationItemvalidtodate = nukeCooperationItemvalidtodate;
    }

    public Boolean getNukeCooperationFastbookingpossible() {
        return nukeCooperationFastbookingpossible;
    }

    public void setNukeCooperationFastbookingpossible(Boolean nukeCooperationFastbookingpossible) {
        this.nukeCooperationFastbookingpossible = nukeCooperationFastbookingpossible;
    }

    public Boolean getNukeCooperationHasspecialdedication() {
        return nukeCooperationHasspecialdedication;
    }

    public void setNukeCooperationHasspecialdedication(Boolean nukeCooperationHasspecialdedication) {
        this.nukeCooperationHasspecialdedication = nukeCooperationHasspecialdedication;
    }

    public String getNukeCooperationSpecialfulfillmentlabel() {
        return nukeCooperationSpecialfulfillmentlabel;
    }

    public void setNukeCooperationSpecialfulfillmentlabel(String nukeCooperationSpecialfulfillmentlabel) {
        this.nukeCooperationSpecialfulfillmentlabel = nukeCooperationSpecialfulfillmentlabel;
    }

    public Boolean getNukeCooperationHasxmlinvoice() {
        return nukeCooperationHasxmlinvoice;
    }

    public void setNukeCooperationHasxmlinvoice(Boolean nukeCooperationHasxmlinvoice) {
        this.nukeCooperationHasxmlinvoice = nukeCooperationHasxmlinvoice;
    }

    public String getNukeCooperationTextAfterlogin() {
        return nukeCooperationTextAfterlogin;
    }

    public void setNukeCooperationTextAfterlogin(String nukeCooperationTextAfterlogin) {
        this.nukeCooperationTextAfterlogin = nukeCooperationTextAfterlogin;
    }

    public String getNukeCooperationTextLogondescription() {
        return nukeCooperationTextLogondescription;
    }

    public void setNukeCooperationTextLogondescription(String nukeCooperationTextLogondescription) {
        this.nukeCooperationTextLogondescription = nukeCooperationTextLogondescription;
    }

    public boolean getNukeCooperationCollectiveinvoice() {
        return nukeCooperationCollectiveinvoice;
    }

    public void setNukeCooperationCollectiveinvoice(boolean nukeCooperationCollectiveinvoice) {
        this.nukeCooperationCollectiveinvoice = nukeCooperationCollectiveinvoice;
    }

    public boolean getNukeCooperationBesorger() {
        return nukeCooperationBesorger;
    }

    public void setNukeCooperationBesorger(boolean nukeCooperationBesorger) {
        this.nukeCooperationBesorger = nukeCooperationBesorger;
    }

    public String getNukeCooperationStyleConfig() {
        return nukeCooperationStyleConfig;
    }

    public void setNukeCooperationStyleConfig(String nukeCooperationStyleConfig) {
        this.nukeCooperationStyleConfig = nukeCooperationStyleConfig;
    }

    public boolean getNukeCooperationShowcheckbox() {
        return nukeCooperationShowcheckbox;
    }

    public void setNukeCooperationShowcheckbox(boolean nukeCooperationShowcheckbox) {
        this.nukeCooperationShowcheckbox = nukeCooperationShowcheckbox;
    }

    public boolean getNukeCooperationShowtextfield() {
        return nukeCooperationShowtextfield;
    }

    public void setNukeCooperationShowtextfield(boolean nukeCooperationShowtextfield) {
        this.nukeCooperationShowtextfield = nukeCooperationShowtextfield;
    }

    public boolean getNukeCooperationShowtextfield2() {
        return nukeCooperationShowtextfield2;
    }

    public void setNukeCooperationShowtextfield2(boolean nukeCooperationShowtextfield2) {
        this.nukeCooperationShowtextfield2 = nukeCooperationShowtextfield2;
    }

    public String getNukeCooperationTextfieldtitle() {
        return nukeCooperationTextfieldtitle;
    }

    public void setNukeCooperationTextfieldtitle(String nukeCooperationTextfieldtitle) {
        this.nukeCooperationTextfieldtitle = nukeCooperationTextfieldtitle;
    }

    public String getNukeCooperationTextfield2title() {
        return nukeCooperationTextfield2title;
    }

    public void setNukeCooperationTextfield2title(String nukeCooperationTextfield2title) {
        this.nukeCooperationTextfield2title = nukeCooperationTextfield2title;
    }

    public String getNukeCooperationCheckboxtitle() {
        return nukeCooperationCheckboxtitle;
    }

    public void setNukeCooperationCheckboxtitle(String nukeCooperationCheckboxtitle) {
        this.nukeCooperationCheckboxtitle = nukeCooperationCheckboxtitle;
    }

    public String getNukeCooperationTextfieldvalidation() {
        return nukeCooperationTextfieldvalidation;
    }

    public void setNukeCooperationTextfieldvalidation(String nukeCooperationTextfieldvalidation) {
        this.nukeCooperationTextfieldvalidation = nukeCooperationTextfieldvalidation;
    }

    public String getNukeCooperationTextfield2validation() {
        return nukeCooperationTextfield2validation;
    }

    public void setNukeCooperationTextfield2validation(String nukeCooperationTextfield2validation) {
        this.nukeCooperationTextfield2validation = nukeCooperationTextfield2validation;
    }

    public Collection<NukeCooperationBase> getNukeCooperationBaseCollection() {
        return nukeCooperationBaseCollection;
    }

    public void setNukeCooperationBaseCollection(Collection<NukeCooperationBase> nukeCooperationBaseCollection) {
        this.nukeCooperationBaseCollection = nukeCooperationBaseCollection;
    }

    public NukeCooperationBase getNukeCooperationBase() {
        return nukeCooperationBase;
    }

    public void setNukeCooperationBase(NukeCooperationBase nukeCooperationBase) {
        this.nukeCooperationBase = nukeCooperationBase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nukeCooperationId != null ? nukeCooperationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NukeCooperationBase)) {
            return false;
        }
        NukeCooperationBase other = (NukeCooperationBase) object;
        if ((this.nukeCooperationId == null && other.nukeCooperationId != null) || (this.nukeCooperationId != null && !this.nukeCooperationId.equals(other.nukeCooperationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.jollydays.booking.bo.NukeCooperationBase[nukeCooperationId=" + nukeCooperationId + "]";
    }

}
