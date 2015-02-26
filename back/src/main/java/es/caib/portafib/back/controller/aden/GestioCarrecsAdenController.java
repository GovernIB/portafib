package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.SeleccioCarrecForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SelectCarrecValidator;
import es.caib.portafib.back.validator.SelectUsuariEntitatValidator;
import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.UsuariEntitatFavoritLocal;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.UsuariEntitatFavoritQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaFields;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
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
import java.util.*;

/**
 * Created 28/05/13 11:51
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = "/aden/carrec")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class,
      SeleccioCarrecForm.class })
public class GestioCarrecsAdenController extends UsuariEntitatController {

  
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
  protected SelectUsuariEntitatValidator selectUsuariEntitatValidator;

  @Autowired
  protected SelectCarrecValidator selectCarrecValidator;

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


   @RequestMapping(value = "/selectcarrec", method = RequestMethod.GET)
   public ModelAndView seleccionarCarrecGet(HttpServletRequest request) throws Exception {

      ModelAndView mav = new ModelAndView(getTileSeleccionaForm());
      SeleccioCarrecForm seleccioCarrecForm = new SeleccioCarrecForm();

       // Obtenim la entitat actual.
      LoginInfo loginInfo = LoginInfo.getInstance();
      String entitatActualID = loginInfo.getEntitatID();

      seleccioCarrecForm.setEntitatID(entitatActualID);
      seleccioCarrecForm.setTitol("carrec.alta");
      seleccioCarrecForm.setContextWeb(getContextWeb());
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

    String nif = request.getParameter("nif");

    // Obtenim l'usuari persona amb el nif indicat
    UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(nif);

    // Si no hi ha usuariPersona associat al NIF
    if (up == null){
      result.rejectValue("nif","usuaripersona.noexisteix",
          new Object[] { I18NUtils.tradueix("nif"), nif }, null);
      return mav;
    }

    LoginInfo loginInfo = LoginInfo.getInstance();
    String entitatActualID = loginInfo.getEntitatID();
    
    // Comprobar que existeix un usuari-entitat real actiu 
    // en la nostra entitat associat al NIF
    Where checkUE = Where.AND(
        USUARIPERSONAID.equal(up.getUsuariPersonaID()),
        ENTITATID.equal(entitatActualID),
        CARREC.equal((String)null),
        ACTIU.equal(true)
            );
    Long count = usuariEntitatEjb.count(checkUE);
    if (count == 0) {
      //usuarientitat.noexisteix=La persona amb {0} {1} no existeix dins l´entitat {2}
      result.rejectValue("nif","usuarientitat.noexisteix.full",
          new Object[]{"nif", nif, entitatActualID},null);
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

    seleccioCarrecForm.setUp(up);
    seleccioCarrecForm.setIdCarrec(idCarrec);

    seleccioCarrecForm.setEntitatID(entitatActualID);
    session.setAttribute("transmissioDadesForm", seleccioCarrecForm);
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


  @Override
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {


    HttpSession session = request.getSession();
    SeleccioCarrecForm transmissioDadesForm = (SeleccioCarrecForm)session.getAttribute("transmissioDadesForm");
    if(_jpa == null && transmissioDadesForm == null){
          mav.setView(new RedirectView("/aden/carrec/selectcarrec", true));
          return new UsuariEntitatForm();
    }
    UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);

    // Cas d'alta
    if(usuariEntitatForm.isNou()) {

      // Agafam les dades del formulari de selecció
      UsuariPersona up;
      up = transmissioDadesForm.getUp();

      String usuariPersonaID = up.getUsuariPersonaID();
      String entitatID = transmissioDadesForm.getEntitatID();
      //Integer tipus = transmissioDadesForm.getTipus();

      // Si no han indicat usuaripersona o entitat  tornam a demanar-ho
      if(usuariPersonaID == null || entitatID == null /*|| tipus == null*/){
          mav.setView(new RedirectView("/aden/carrec/selectcarrec", true));
          return usuariEntitatForm;
      }

      UsuariEntitat usuariEntitat = usuariEntitatForm.getUsuariEntitat();

      

      usuariEntitat.setUsuariPersonaID(usuariPersonaID);
      usuariEntitat.setEntitatID(entitatID);
      usuariEntitat.setActiu(true);

      usuariEntitat.setUsuariEntitatID(transmissioDadesForm.getIdCarrec());
      usuariEntitat.setCarrec(transmissioDadesForm.getCarrec());
      if (log.isDebugEnabled()) {
        log.info(" --------------- ");
        log.info("usuariEntitat.setUsuariPersonaID(" + usuariPersonaID + ");");
        log.info("usuariEntitat.setUsuariEntitatID(" + transmissioDadesForm.getIdCarrec() + ");");
      }

      // LLevam de la sessio el formulari de selecció.
      session.removeAttribute("transmissioDadesForm");
      
      usuariEntitatForm.addReadOnlyField(USUARIPERSONAID);
      
      HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("verificardades"));
      
    } else {
      // Cas modificació
      //UsuariEntitatJPA ueJPA = usuariEntitatLogicaEjb.findByPrimaryKeyFull(_jpa.getUsuariEntitatID());
      //up = ueJPA.getUsuariPersona();
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
    usuariEntitatForm.addHiddenField(POTCUSTODIAR);

    // Camps de només lectura
    usuariEntitatForm.addReadOnlyField(USUARIENTITATID);

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
          filterForm.addGroupByField(CARREC);
          filterForm.addGroupByField(NIF);

          filterForm.addHiddenField(REBRETOTSELSAVISOS);
          filterForm.addHiddenField(ENTITATID);
          filterForm.addHiddenField(EMAIL);
          filterForm.addHiddenField(LOGOSEGELLID);
          filterForm.addHiddenField(PREDETERMINAT);
          filterForm.addHiddenField(POTCUSTODIAR);

          filterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(CARREC)  });
          
          // definim el titol de la pantalla del formulari llistat
          //filterForm.setTitleCode("carrec.llistat");
          // Ocultam el botó de crear
          //filterForm.setAddButtonVisible(false);
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

  
  
  /**
   * FORMULARI
   */
  @Override
  public List<StringKeyValue> getReferenceListForUsuariPersonaID(HttpServletRequest request,
      ModelAndView mav, UsuariEntitatForm usuariEntitatForm, Where where)  throws I18NException {
    
    final Where usuariActualCarrec = UsuariPersonaFields.USUARIPERSONAID.equal(
        usuariEntitatForm.getUsuariEntitat().getUsuariPersonaID());

    Where w;
    if (usuariEntitatForm.isNou()) {
      // Només requerim l'usuari definit en el NIF
      w = usuariActualCarrec;
    } else {
      // L'usuari actual del càrrec o els actius d'entre els favorits
      
      UsuariEntitatFavoritQueryPath favorit = new UsuariEntitatFavoritQueryPath();
      
      w = Where.OR(
          // L'usuari actual del càrrec
          usuariActualCarrec,
           // Els actius d'entre els favorits
          UsuariPersonaFields.USUARIPERSONAID.in(
              usuariEntitatFavoritEjb.executeQuery(favorit.FAVORIT().USUARIPERSONA().USUARIPERSONAID(),
                  Where.AND(
                      favorit.ORIGENID().equal(LoginInfo.getInstance().getUsuariEntitatID()),
                      favorit.ORIGEN().ACTIU().equal(true)
                  )
              )
          )
      );

    }
    return super.getReferenceListForUsuariPersonaID(request, mav, usuariEntitatForm, Where.AND(where, w));
  }
  
  @Override
  public void fillReferencesForForm(UsuariEntitatForm usuariEntitatForm,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    if (usuariEntitatForm.getUsuariEntitat() != null) {
      super.fillReferencesForForm(usuariEntitatForm, request, mav);
    }
  }
  
  
  public static final StringField NIF = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();
  
  
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
      groupByItemsMap.get(NIF).setCodeLabel(model + "." + model);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, NIF, false);

      return groupByItemsMap;
  }

  @Override
  public void delete(HttpServletRequest request, UsuariEntitat usuariEntitat) throws I18NException,Exception {
    Set<Long> fitxers;
    fitxers = usuariEntitatLogicaEjb.deleteFull(usuariEntitat.getUsuariEntitatID());
    borrarFitxers(fitxers);
  }
  
  @Override
  public UsuariEntitatJPA create(UsuariEntitatJPA usuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatJPA) usuariEntitatLogicaEjb.createFull(usuariEntitat);
  }

  @Override
  public UsuariEntitatJPA update(UsuariEntitatJPA usuariEntitat)
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
  
  

  @RequestMapping(value = "/activar/{usuariEntitatID}", method = RequestMethod.GET)
  public ModelAndView activarCarrec(
      @PathVariable("usuariEntitatID") String usuariEntitatID, 
      HttpServletRequest request,  HttpServletResponse response) throws Exception {

    return activaDesactivarCarrec(usuariEntitatID, request, true);
  }
  
  
  
  @RequestMapping(value = "/desactivar/{usuariEntitatID}", method = RequestMethod.GET)
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
