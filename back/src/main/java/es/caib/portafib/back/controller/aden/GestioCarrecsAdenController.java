package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.SeleccioCarrecForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.back.validator.SelectCarrecValidator;
import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.UsuariEntitatFavoritLocal;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created 28/05/13 11:51
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = "/aden/carrec")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class,
      SeleccioCarrecForm.class, SeleccioUsuariForm.class })
public class GestioCarrecsAdenController extends UsuariEntitatController {

  public static final String SELECTION_CARREC_USUARI_ENTITAT_ID
    = "SELECTION_CARREC_USUARI_ENTITAT_ID";

  public static final StringField USUARI_PERSONA_FIELD = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();

  @EJB(mappedName = UsuariEntitatFavoritLocal.JNDI_NAME)
  protected UsuariEntitatFavoritLocal usuariEntitatFavoritEjb;
  
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;

  @EJB(mappedName = BlocDeFirmesLocal.JNDI_NAME)
  protected BlocDeFirmesLocal blocDeFirmesEjb;

  @EJB(mappedName = FirmaLocal.JNDI_NAME)
  protected FirmaLocal firmaEjb;

  @Autowired
  protected SelectCarrecValidator selectCarrecValidator;
  
  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;

  @Override
  public String getTileForm() {
    return "carrecFormAden";
  }

  @Override
  public String getTileList() {
    return "carrecListAden";
  }

  public String getTileSeleccionaForm() {
    return "seleccionaCarrecForm";
  }

  @PostConstruct
  public void init() {

    usuariPersonaRefList = new UsuariPersonaRefList(usuariPersonaRefList);

    usuariPersonaRefList.setSelects(new Select<?>[] {
        UsuariPersonaFields.NOM.select,
        new SelectConstant(" "),
        UsuariPersonaFields.LLINATGES.select,
        new SelectConstant(" ("),
        UsuariPersonaFields.NIF.select,
        new SelectConstant(")")
    });
    usuariPersonaRefList.setSeparator("");
  }

  // ============================ ALTA CARREC ===========================

   @RequestMapping(value = "/selectcarrec", method = RequestMethod.GET)
   public ModelAndView seleccionarCarrecGet(HttpServletRequest request) throws Exception {

      ModelAndView mav = new ModelAndView(getTileSeleccionaForm());
      SeleccioCarrecForm seleccioCarrecForm = new SeleccioCarrecForm();

      seleccioCarrecForm.setTitol("carrec.alta");
      seleccioCarrecForm.setEntitatID(LoginInfo.getInstance().getEntitatID());

      seleccioCarrecForm.setCancelUrl("/canviarPipella/" + ConstantsV2.ROLE_ADEN);
      seleccioCarrecForm.setUrlData("/common/json/usuarientitatintern");
     
      try {
        seleccioCarrecForm.setUsuarisFavorits(
            SearchJSONController.favoritsToUsuariEntitat(
                usuariEntitatLogicaEjb.selectFavorits(
                  LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
      } catch (I18NException e) {
        log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
      }
      
      mav.addObject(seleccioCarrecForm);

      return mav;
   }



  @RequestMapping(value = "/selectcarrec", method = RequestMethod.POST)
  public ModelAndView seleccionarCarrecPost(SeleccioCarrecForm seleccioCarrecForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

    // Obtenim la entitat actual.
    ModelAndView mav = new ModelAndView(getTileSeleccionaForm());

    selectCarrecValidator.validate(seleccioCarrecForm, result);
    if (result.hasErrors()) {
       return mav;
    }

    HttpSession session = request.getSession();

    String usuariEntitatID = seleccioCarrecForm.getId();
   
    // Comprobar que existeix un usuari-entitat real actiu 
    // en la nostra entitat associat a  l'identificador de l'usuari-entitat 
    String entitatActualID = LoginInfo.getInstance().getEntitatID();
    Where checkUE = Where.AND(
        USUARIENTITATID.equal(usuariEntitatID),
        // ENTITATID.equal(entitatActualID),
        CARREC.equal((String)null),
        ACTIU.equal(true)
            );
    Long count = usuariEntitatEjb.count(checkUE);
    if (count == 0) {
      // La persona seleccionada no està activa dins l´entitat {0}
      result.rejectValue("id","usuarientitat.noactiva",
          new Object[]{entitatActualID},null);
      return mav;
    }

    // Comprovam que no existeix un ID de càrrec igual ( idCarrec == usuarientitatID )
    String idCarrec = entitatActualID + "_" + seleccioCarrecForm.getIdCarrec();
    Where w = USUARIENTITATID.equal(idCarrec);
    count = usuariEntitatEjb.count(w);
    if(count != 0) {
      result.rejectValue("idCarrec","usuarientitat.existeix",
          new Object[]{"idCarrec", idCarrec, entitatActualID},null);
      return mav;
    }

    // Comprovam que no existeix un nom de càrrec igual ( carrec == nomcarrec )
    String carrec = seleccioCarrecForm.getCarrec();
    if (!checkNomDeCarrec(result, entitatActualID, "carrec", carrec)) {
      return mav;
    }

    seleccioCarrecForm.setIdCarrec(idCarrec);

    session.setAttribute(SELECTION_CARREC_USUARI_ENTITAT_ID, seleccioCarrecForm);
    return new ModelAndView(new RedirectView(getContextWeb() + "/new", true));

  }

  private boolean checkNomDeCarrec(BindingResult result,
      String entitatActualID, String carrecField, String carrec) throws I18NException {
    Where wCheck = Where.AND( CARREC.like(carrec),
        ENTITATID.equal(entitatActualID));
    Long countC = usuariEntitatEjb.count(wCheck);
    if(countC != 0) {
      result.rejectValue(carrecField, "usuarientitat.existeix",
          new Object[]{I18NUtils.tradueix(CARREC.fullName), carrec, entitatActualID},null);
      return false;
    } else {
      return true;
    }
  }
  
  
  // ======================== MODIFICACIO PERSONA ASSOCIADA AL CARREC ==================

  
  @RequestMapping(value = "/modificarpersona/{usuariEntitatCarrecID}", method = RequestMethod.GET)
  public ModelAndView modificarPersonaCarrecGet(@PathVariable String usuariEntitatCarrecID) throws Exception {
    
   

      ModelAndView mav = new ModelAndView("seleccioUsuariForm_ADEN");

      SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

      UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatCarrecID);
      
      if (ue == null) {
        mav.setView(new RedirectView(getContextWeb() + "/list", true));
        // TODO Falta un missatges
        // HtmlUtils.saveMessageWarning(request, "no s'ha trobat !!!");
        return mav;
      }

      seleccioUsuariForm.setTitol("carrec.modificaciopersona.titol");
      String subtitol = "=" + 
          I18NUtils.tradueix("carrec.modificaciopersona.subtitol",
              ue.getCarrec(), Utils.getOnlyNom(ue.getUsuariPersona()) );

      seleccioUsuariForm.setSubtitol(subtitol);
      seleccioUsuariForm.setCancelUrl("/aden/carrec/list");
      seleccioUsuariForm.setUrlData("/common/json/usuaripersonaentitat");
      //seleccioUsuariForm.setParam1(usuariEntitatCarrecID);
      
      try {
        seleccioUsuariForm.setUsuarisFavorits(
            SearchJSONController.favoritsToUsuariPersona(
                usuariEntitatLogicaEjb.selectFavorits(
                  LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
      } catch (I18NException e) {
        log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
      }
      
      mav.addObject(seleccioUsuariForm);

      return mav;
  }
  
  


  @RequestMapping(value = "/modificarpersona/{usuariEntitatCarrecID}", method = RequestMethod.POST)
  public ModelAndView modificarPersonaCarrecPost(SeleccioUsuariForm seleccioUsuariForm,
      BindingResult result, HttpServletRequest request,@PathVariable String usuariEntitatCarrecID) throws I18NException {
    
    ModelAndView mav = new ModelAndView("seleccioUsuariForm_ADEN");

    seleccioUsuariValidator.validate(seleccioUsuariForm, result);
    if (result.hasErrors()) {
      return mav;
    }

    String usuariPersonaID = seleccioUsuariForm.getId();
    
    // Ha d'estar activa !!!!
    

    // Comprovam que no existesqui un usuarientitat ja per a aquest usuari persona.
    UsuariEntitatJPA ueCarrec = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatCarrecID);
    
    ueCarrec.setUsuariPersonaID(usuariPersonaID);
    
    try {
      usuariEntitatLogicaEjb.updateFull(ueCarrec);
      createMessageSuccess(request, "success.modification", ueCarrec.getUsuariEntitatID());
    } catch (I18NValidationException e) {
      String msg = I18NUtils.getMessage(e);
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }


      mav.setView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    
  }


  // =====================================


  @Override
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);

    // Cas d'alta
    if(usuariEntitatForm.isNou()) {
      
      HttpSession session = request.getSession();
      SeleccioCarrecForm transmissioDadesForm = (SeleccioCarrecForm)session.getAttribute(SELECTION_CARREC_USUARI_ENTITAT_ID);
      if(_jpa == null && transmissioDadesForm == null){
            mav.setView(new RedirectView("/aden/carrec/selectcarrec", true));
            return new UsuariEntitatForm();
      }

      String usuariEntitatID = transmissioDadesForm.getId();
      
      UsuariEntitatJPA ueSeleccionat = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);  


      // Si no han indicat usuaripersona o entitat  tornam a demanar-ho
      if(ueSeleccionat == null){
          mav.setView(new RedirectView("/aden/carrec/selectcarrec", true));
          return usuariEntitatForm;
      }


      UsuariEntitat usuariEntitatCarrec = usuariEntitatForm.getUsuariEntitat();

      usuariEntitatCarrec.setUsuariPersonaID(ueSeleccionat.getUsuariPersonaID());
      usuariEntitatCarrec.setEntitatID(ueSeleccionat.getEntitatID());
      usuariEntitatCarrec.setActiu(true);

      usuariEntitatCarrec.setUsuariEntitatID(transmissioDadesForm.getIdCarrec());
      usuariEntitatCarrec.setCarrec(transmissioDadesForm.getCarrec());
      if (log.isDebugEnabled()) {
        log.debug(" --------------- ");
        log.debug("NOU CARREC UsusariEntitatCarrecID(" + usuariEntitatCarrec.getUsuariEntitatID() + ");");
        log.debug("NOU CARREC UsuariPersonaID(" + ueSeleccionat.getUsuariPersonaID() + ");");
        log.debug("NOU CARREC Entitat(" + ueSeleccionat.getEntitatID() + ");");
        log.debug("NOU CARREC CarrecID(" + transmissioDadesForm.getIdCarrec() + ");");
        log.debug("NOU CARREC CarrecNom(" + transmissioDadesForm.getCarrec() + ");");
      }

      // LLevam de la sessio el formulari de selecció.
      session.removeAttribute(SELECTION_CARREC_USUARI_ENTITAT_ID);
      
      usuariEntitatForm.addReadOnlyField(USUARIPERSONAID);
      
      HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("verificardades"));
      
    } else {
      // Cas modificació
      usuariEntitatForm.addReadOnlyField(ACTIU);
      
      if (usuariEntitatForm.getUsuariEntitat().isActiu()) {
        usuariEntitatForm.addAdditionalButton(new AdditionalButton(
          "icon-ban-circle", "desactivar", getContextWeb() + "/desactivar/{0}", "btn-warning"));
      } else {
        usuariEntitatForm.addAdditionalButton(new AdditionalButton(
          "icon-play", "activar", getContextWeb() + "/activar/{0}", "btn-success"));
      }
    }

    // Ocultam camps del formulari
    usuariEntitatForm.addHiddenField(EMAIL);
    usuariEntitatForm.addHiddenField(LOGOSEGELLID);
    usuariEntitatForm.addHiddenField(PREDETERMINAT);
    usuariEntitatForm.addHiddenField(REBRETOTSELSAVISOS);
    usuariEntitatForm.addHiddenField(ENTITATID);
    // XYZ ZZZ  usuariEntitatForm.addHiddenField(POTCUSTODIAR);
    usuariEntitatForm.addHiddenField(POLITICACUSTODIA);
    usuariEntitatForm.addHiddenField(POLITICADEPLUGINFIRMAWEB);

    // Ocultar la plantilla de custòdia #324
    usuariEntitatForm.addHiddenField(CUSTODIAINFOID);

    // Camps de només lectura
    usuariEntitatForm.addReadOnlyField(USUARIENTITATID);
    usuariEntitatForm.addReadOnlyField(USUARIPERSONAID);
    return usuariEntitatForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      Where w = Where.AND(ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),CARREC.isNotNull() );
      return w;
  }

   @Override
   public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina,
       ModelAndView mav, HttpServletRequest request) throws I18NException {
       UsuariEntitatFilterForm filterForm;

       filterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);
       // Si es nou ocultam i marcam a readOnly camps del formulari
       if(filterForm.isNou()){
          filterForm.addGroupByField(ACTIU);
          filterForm.addGroupByField(USUARI_PERSONA_FIELD);

          filterForm.addHiddenField(REBRETOTSELSAVISOS);
          filterForm.addHiddenField(ENTITATID);
          filterForm.addHiddenField(EMAIL);
          filterForm.addHiddenField(LOGOSEGELLID);
          filterForm.addHiddenField(PREDETERMINAT);
          
          // XYZ ZZZ   filterForm.addHiddenField(POTCUSTODIAR);
          filterForm.addHiddenField(POLITICACUSTODIA);

          filterForm.addHiddenField(POLITICADEPLUGINFIRMAWEB);

          filterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(CARREC)  });

          filterForm.addAdditionalButtonForEachItem(new AdditionalButton(
              "icon-user", "carrec.modificaciopersona.titol", getContextWeb() + "/modificarpersona/{0}", "btn-success"));

       }

       return filterForm;
   }


  @InitBinder("seleccioCarrecForm")
  public void initBinderSeleccio(WebDataBinder binder) {
    super.initBinder(binder);
    binder.setValidator(this.selectCarrecValidator);
  }


  @Override
  public String getSessionAttributeFilterForm() {
    return "currentPageList_Carrec_aden";
  }

  @Override
  public void fillReferencesForForm(UsuariEntitatForm usuariEntitatForm,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    if (usuariEntitatForm.getUsuariEntitat() != null) {
      super.fillReferencesForForm(usuariEntitatForm, request, mav);
    }
  }
  
  @Override
  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariEntitatFilterForm filterForm,
      HttpServletRequest request, ModelAndView mav,
        List<UsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    
      Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm, request, mav, list, groupItems);

      Map<String, String> _tmp;
      List<StringKeyValue> _listSKV;

      // Field NIF
      final String model = UsuariPersonaFields._TABLE_MODEL;
      _listSKV = this.usuariPersonaRefList.getReferenceList(UsuariPersonaFields.NIF,
            null, new OrderBy(UsuariPersonaFields.LLINATGES));
      _tmp = org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
      groupByItemsMap.get(USUARI_PERSONA_FIELD).setCodeLabel(model + "." + model);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARI_PERSONA_FIELD, false);

      return groupByItemsMap;
  }

  @Override
  public void delete(HttpServletRequest request, UsuariEntitat usuariEntitat) throws I18NException,Exception {
    Set<Long> fitxers;
    fitxers = usuariEntitatLogicaEjb.deleteFull(usuariEntitat.getUsuariEntitatID());
    borrarFitxers(fitxers);
  }
  
  @Override
  public UsuariEntitatJPA create(HttpServletRequest request, UsuariEntitatJPA usuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatJPA) usuariEntitatLogicaEjb.createFull(usuariEntitat);
  }

  @Override
  public UsuariEntitatJPA update(HttpServletRequest request, UsuariEntitatJPA usuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatJPA) usuariEntitatLogicaEjb.updateFull(usuariEntitat);
  }
  
  
  @Override
  public String getEntityNameCode() {
    return "carrec";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "carrec.plural";
  }
  

  @RequestMapping(value = "/activar/{usuariEntitatID:.+}", method = RequestMethod.GET)
  public ModelAndView activarCarrec(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activaDesactivarCarrec(usuariEntitatID, request, true);
  }
  
  

  @RequestMapping(value = "/desactivar/{usuariEntitatID:.+}", method = RequestMethod.GET)
  public ModelAndView desactivarCarrec(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activaDesactivarCarrec(usuariEntitatID, request, false);
  }

  public ModelAndView activaDesactivarCarrec(String usuariEntitatID,
      HttpServletRequest request, boolean activar) {
    try {
      if (activar) {
        usuariEntitatLogicaEjb.activarCarrec(usuariEntitatID);
      } else {
        usuariEntitatLogicaEjb.desactivarCarrec(usuariEntitatID);
      }
    } catch(I18NException i18ne) {
      String msg = I18NUtils.getMessage(i18ne);
      log.error(msg, i18ne);
      HtmlUtils.saveMessageError(request, msg);
    } catch(Exception e) {
      String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }

    ModelAndView mav = new ModelAndView(new RedirectView(
        getContextWeb() + "/" + usuariEntitatID + "/edit", true));
    return mav;
  }


}
