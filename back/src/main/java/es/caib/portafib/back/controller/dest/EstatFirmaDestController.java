package es.caib.portafib.back.controller.dest;

import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectGroupByAndCountForField;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.AppletConfig;
import es.caib.portafib.back.utils.AppletSignFile;

import es.caib.portafib.back.controller.FileDownloadController;
import es.caib.portafib.back.controller.webdb.EstatDeFirmaController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.ejb.FirmaLocal;

import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.utils.Configuracio;

/**
 * Controller per gestionar un EstatDeFirma
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaDestController extends EstatDeFirmaController implements
    EstatDeFirmaFields, Constants {

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  private UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/ColaboracioDelegacioLogicaEJB/local")
  protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioEjb;

  @EJB(mappedName = "portafib/FirmaEJB/local")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  final Long[] ESTATS_INICIALS_COLA = new Long[] {
      Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
      Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR };

  final Long[] ESTATS_INICIALS_DELE = new Long[] { Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };

  // References

  @Autowired
  protected UsuariPersonaRefList usuariPersonaRefList;

  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  // Propietat de Peticio de firma
  protected final static IntegerField PRIORITATID;

  // Propietat de Peticio de firma
  protected final static LongField TIPUSDOCUMENTID;

  // Propietat de Peticio de firma
  protected final static StringField REMITENTNOM;

  // Propietat de Col.laboracio-Delegacio
  protected final static StringField DESTINATARIID;

  static {
    PeticioDeFirmaQueryPath peticioQueryPath = new EstatDeFirmaQueryPath().FIRMA()
        .BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();
    PRIORITATID = peticioQueryPath.PRIORITATID();
    TIPUSDOCUMENTID = peticioQueryPath.TIPUSDOCUMENTID();
    REMITENTNOM = peticioQueryPath.REMITENTNOM();

    UsuariPersonaQueryPath personaQueryPath = new EstatDeFirmaQueryPath()
        .COLABORACIODELEGACIO().DESTINATARI().USUARIPERSONA();
    DESTINATARIID = personaQueryPath.USUARIPERSONAID();
  }

  @PostConstruct
  public void init() {
    usuariPersonaRefList = new UsuariPersonaRefList(usuariPersonaRefList);
    usuariPersonaRefList.setSelects(new Select<?>[] { UsuariPersonaFields.NOM.select,
        UsuariPersonaFields.LLINATGES.select });
  }

  @Override
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

    if (ff.isNou()) {

      if (!Configuracio.isDesenvolupament()) {
        ff.addHiddenField(ESTATDEFIRMAID);
        ff.addHiddenField(FIRMAID);
      }

      ff.addHiddenField(COLABORACIODELEGACIOID);
      ff.addHiddenField(DESCRIPCIO);
      ff.addHiddenField(USUARIENTITATID);
      // Ocultar estatinicial
      ff.addHiddenField(TIPUSESTATDEFIRMAINICIALID);

      // Ocultar botons
      ff.setDeleteButtonVisible(false);
      ff.setEditButtonVisible(false);
      ff.setAddButtonVisible(false);
      ff.setDeleteSelectedButtonVisible(false);

      ff.setDefaultOrderBy(new OrderBy[] {
          // DataFi és null  si no han fet res
          new OrderBy(DATAFI, OrderType.DESC), 
          // Propietat de Peticio de firma
          new OrderBy(PRIORITATID, OrderType.DESC), 
          new OrderBy(DATAINICI, OrderType.DESC),

      });

      ff.addGroupByField(TIPUSDOCUMENTID); // Propietat de Peticio De Firma
      if (getRole().equals(Constants.ROLE_COLA)) {
        // Propietat de Col.laboracio-Delegacio
        ff.addGroupByField(DESTINATARIID);
      } else {
        // Propietat de Peticio De Firma
        ff.addGroupByField(REMITENTNOM);
      }
      ff.addGroupByField(DATAINICI);
      ff.addGroupByField(DATAFI);
      // ff.addGroupByField(TIPUSESTATDEFIRMAINICIALID);
      ff.addGroupByField(TIPUSESTATDEFIRMAFINALID);
    }
    return ff;

  }

  @Override
  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatDeFirmaFilterForm filterForm,
      HttpServletRequest request, ModelAndView mav, List<EstatDeFirma> list,
      List<GroupByItem> groupItems) throws I18NException {

    Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm,
        request, mav, list, groupItems);

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipusDocumentID
    if (getRole().equals(Constants.ROLE_COLA)) {

      _listSKV = this.usuariPersonaRefList.getReferenceList(
          UsuariPersonaFields.USUARIPERSONAID, null);
      _tmp = Utils.listToMap(_listSKV);
      groupByItemsMap.get(DESTINATARIID).setCodeLabel(
          ColaboracioDelegacioFields.DESTINATARIID.fullName);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, DESTINATARIID, false);

    }

    {
      _listSKV = this.tipusDocumentRefList.getReferenceList(
          TipusDocumentFields.TIPUSDOCUMENTID, null);
      _tmp = Utils.listToMap(_listSKV);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSDOCUMENTID, false);
    }

    return groupByItemsMap;
  }

  @RequestMapping(value = "/final", method = RequestMethod.GET)
  public ModelAndView finalRequest(HttpServletRequest request, HttpServletResponse response)
      throws I18NException {
    // TODO traduir
    // checkRole(request, "final");
    return llistatPaginat(request, response, null);

  }

  @RequestMapping(value = "/firmarseleccionats", method = RequestMethod.POST)
  public ModelAndView firmarSeleccionats(HttpServletRequest request,
      @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {

    String[] seleccionats = filterForm.getSelectedItems();
    // String role = filterForm.getRole();

    if (seleccionats == null || seleccionats.length == 0) {
      log.debug("firmarSeleccionats::NO HI HA SELECCIONATS");
      // TODO avis
      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    } else {

      List<AppletSignFile> fitxers = new ArrayList<AppletSignFile>();
      LoginInfo loginInfo = LoginInfo.getInstance();
      String langUI = loginInfo.getUsuariPersona().getIdiomaID();

      // Només permetre una firma per petició
      // (evitar bloc de firmes amb una firma usuari-entitat i una altra amb
      // usuari càrrec)
      Set<Long> peticionsDeFirmaID = new HashSet<Long>();

      for (int i = 0; i < seleccionats.length; i++) {
        log.debug("firmarSeleccionats::SELECCIONAT = " + seleccionats[i]);

        int index = seleccionats[0].indexOf('/');
        Long estatDeFirmaID = new Long(seleccionats[i].substring(0, index));
        Long peticioDeFirmaID = new Long(seleccionats[i].substring(index + 1));

        if (!peticionsDeFirmaID.contains(peticioDeFirmaID)) {
          
          try {
            checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
          } catch(I18NException e) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
            continue;
          }

          AppletSignFile appletSignFile;
          appletSignFile = prepareAppletFirma(request, estatDeFirmaID, peticioDeFirmaID,
              langUI);
          if (appletSignFile != null) {
            fitxers.add(appletSignFile);
          }

          peticionsDeFirmaID.add(peticioDeFirmaID);

        }
      }

      if (fitxers.isEmpty()) {
        // TODO avis
        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      }

      EntitatJPA entitat = loginInfo.getEntitat(); 
      AppletConfig config = es.caib.portafib.back.utils.Utils.getAppletConfig(
          entitat, langUI, getContextWeb() + "/final");
        /*
         new AppletConfig(langUI, getContextWeb() + "/final", loginInfo
          .getEntitat().getFiltreCertificats(), Configuracio.getAppletSignerClass());
          */

      ModelAndView mav = new ModelAndView("firmaApplet_" + getRole());
      mav.addObject("fitxers", fitxers);
      mav.addObject("config", config);
      return mav;

    }

  }

  public String getRole() {
    return Constants.ROLE_DEST;
  }
  
  
  // TODO moure a EJB
  private void checkCanSignPeticioDeFirma(HttpServletRequest request, Long peticioDeFirmaID, Long estatDeFirmaID) throws I18NException {

    EstatDeFirma ef = checkEstatDeFirma(estatDeFirmaID, request, false);
   
    long firmaId = ef.getFirmaID();
    
    log.info(" ---------- " + peticioDeFirmaID + " -----------");
    
    // Cercar colaboradors-revisors que no han donat el vist i plau a la firma
    Where w1 = FIRMAID.equal(firmaId);
    log.info("C1 = " + estatDeFirmaEjb.count(w1));
    
    Where w2 = Where.OR(
        TIPUSESTATDEFIRMAINICIALID.equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR),
        TIPUSESTATDEFIRMAINICIALID.equal(Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR)
      );
    log.info("C2 = " + estatDeFirmaEjb.count(Where.AND(w1,w2)));
    
    Where w3 = Where.OR(
        TIPUSESTATDEFIRMAFINALID.isNull(),
        TIPUSESTATDEFIRMAFINALID.notEqual(Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT)
        );
    log.info("C3 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3)));
    
    Where w4 = COLABORACIODELEGACIOID.isNotNull();
    log.info("C4 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3,w4)));
    
    ColaboracioDelegacioQueryPath cdqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO();
    Where w5 = Where.AND(
        cdqp.REVISOR().equal(true),
        cdqp.ACTIVA().equal(true),
        cdqp.ESDELEGAT().equal(false) // Es col·laborador
      );
    
    log.info("C5 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3,w4,w5)));
    

    final Select<?>[] nomcomplet = new Select<?>[] {
        cdqp.COLABORADORDELEGAT().USUARIPERSONA().NOM().select,
        cdqp.COLABORADORDELEGAT().USUARIPERSONA().LLINATGES().select,
        cdqp.COLABORADORDELEGAT().USUARIPERSONA().NIF().select
    };
    SelectMultipleStringKeyValue smskv;
    smskv = new SelectMultipleStringKeyValue(COLABORACIODELEGACIOID.select, nomcomplet);
    
    List<StringKeyValue> colaboracions;
    colaboracions = estatDeFirmaEjb.executeQuery(smskv, Where.AND(w1,w2,w3, w4, w5));
    
    if (!colaboracions.isEmpty()) {
      StringBuffer str = new StringBuffer();
      for (StringKeyValue colaID : colaboracions) {

        String nom_i_dni = colaID.getValue() + ")";
        int index = nom_i_dni.lastIndexOf(' ');
        nom_i_dni = nom_i_dni.substring(0, index + 1) + " (" + nom_i_dni.substring(index + 1); 
        str.append(nom_i_dni);
      }
      
      PeticioDeFirmaJPA peticio = checkPeticioDeFirma(peticioDeFirmaID, request, false);

      throw new I18NException("solicituddefirma.requereixvistiplauderevisor",
          peticio.getTitol(), str.toString());
    }
  }
  

  @RequestMapping(value = "/firmar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView firmar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    
    log.info("Entra a firmar Peticio = " + peticioDeFirmaID + " | EstatDeFirma = " + estatDeFirmaID);
    
    try {
      checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
    } catch(I18NException e) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
      return llistatPaginat(request, response, null);
    }

    String role = getRole();

    AppletSignFile appletSignFile;
    appletSignFile = prepareAppletFirma(request, estatDeFirmaID, peticioDeFirmaID, LoginInfo
        .getInstance().getUsuariPersona().getIdiomaID());

    if (appletSignFile == null) {
      return llistatPaginat(request, response, null);
    }

    List<AppletSignFile> fitxers = new ArrayList<AppletSignFile>();
    fitxers.add(appletSignFile);

    LoginInfo loginInfo = LoginInfo.getInstance();

    
    
    EntitatJPA entitat = loginInfo.getEntitat();
    
    AppletConfig config = es.caib.portafib.back.utils.Utils.getAppletConfig(
        entitat, loginInfo.getUsuariPersona().getIdiomaID(),
        getContextWeb() + "/final");
    
    /*
    if (entitat.getPolicyIdentifier() != null) {
      config = new AppletConfig(loginInfo.getUsuariPersona().getIdiomaID(),
          getContextWeb() + "/final", entitat.getFiltreCertificats(),
          Configuracio.getAppletSignerClass(),
        entitat.getPolicyIdentifier(), entitat.getPolicyIdentifierHash(),
        entitat.getPolicyIdentifierHashAlgorithm(), entitat.getPolicyUrlDocument());
    } else {
      config = new AppletConfig(loginInfo.getUsuariPersona().getIdiomaID(),
          getContextWeb() + "/final", loginInfo.getEntitat().getFiltreCertificats(),
          Configuracio.getAppletSignerClass());
    }
    */
    

    ModelAndView mav = new ModelAndView("firmaApplet_" + role);
    mav.addObject("fitxers", fitxers);
    mav.addObject("config", config);
    return mav;

  }

  protected AppletSignFile prepareAppletFirma(HttpServletRequest request, Long estatDeFirmaID,
      Long peticioDeFirmaID, String langUI) throws I18NException {
    AppletSignFile appletSignFile;
    {

      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
          Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
      if (check == null) {
        // S'ha produit un error i retornam el control al llistat
        return null;
      }

      EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
      FirmaJPA firma = check.firma;
      PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

      LoginInfo loginInfo = LoginInfo.getInstance();

      // Crear un token i revisar si aquesta peticio esta bloquejada !!!
      String token = peticioDeFirmaLogicaEjb.lockPeticioDeFirma(peticioDeFirmaID,
          loginInfo.getUsuariEntitatID());
      if (token == null) {
        new PeticioDeFirmaController().createMessageError(request, "error.peticiobloquejada",
            peticioDeFirmaID);
        return null;
      }

      // Preparar pàgina Applet
      FirmaJPA lastFirma = peticioDeFirmaLogicaEjb
          .getLastSignOfPeticioDeFirma(peticioDeFirmaID);
      final String source;
      if (lastFirma == null) {
        log.debug("No hi ha firmes. La font del document és el document original");
        source = FileDownloadController.fileUrl(peticioDeFirma.getFitxerAdaptat());
      } else {
        if (log.isDebugEnabled()) {
          log.debug("La darrera firma és " + firma.getFirmaID() + " (#"
              + firma.getNumFirmaDocument() + ")");
        }
        source = FileDownloadController.fileUrl(lastFirma.getFitxerFirmat());
      }

      final int sign_number = lastFirma == null ? 1 : (lastFirma.getNumFirmaDocument() + 1);

      final String destination = "/estatDeFirma/destination/" + estatDeFirmaID + "/"
          + peticioDeFirmaID + "/" + token + "/" + sign_number;

      final int location_sign_table = (int) peticioDeFirma.getPosicioTaulaFirmesID();

      // Cercar el motiu segons si es DELEGACIO o DESTINATARI
      final String reason;
      Locale loc = new Locale(peticioDeFirma.getIdiomaID());
      Long colaDeleID = estatDeFirma.getColaboracioDelegacioID();
      if (colaDeleID == null) {
        // Destinatari
        reason = I18NUtils.tradueix(loc, "motiupeticiodirecta");
      } else {
        // Delegat
        // Firma {0} (NIF {1}) per delegació de {2} (NIF {3}).Motiu: {4}
        // (ID={5})
        ColaboracioDelegacio colaDele = colaboracioDelegacioEjb.findByPrimaryKey(colaDeleID);

        UsuariEntitatJPA dest = usuariEntitatLogicaEjb.findByPrimaryKeyFull(colaDele
            .getDestinatariID());

        UsuariPersona up = loginInfo.getUsuariPersona();
        UsuariPersona destUP = dest.getUsuariPersona();
        String[] args = {
            // Delegat
            up.getNom() + " " + up.getLlinatges(), up.getNif(),
            // Destinatari Original
            destUP.getNom() + " " + destUP.getLlinatges(), destUP.getNif(),
            colaDele.getMotiu() };
        reason = I18NUtils.tradueix(loc, "motiudelegacio", args);
      }

      final String idname = peticioDeFirma.getFitxerAFirmar().getNom();

      String langSign = peticioDeFirma.getIdiomaID();
      if (langSign == null) {
        langSign = langUI;
      }

      appletSignFile = new AppletSignFile(source, destination, idname, location_sign_table,
          reason, sign_number, langSign, peticioDeFirma.getTipusFirmaID(),
          peticioDeFirma.getAlgorismeDeFirmaID(), peticioDeFirma.getModeDeFirma());

    }
    return appletSignFile;
  }


  @RequestMapping(value = "/rebutjar/{estatDeFirmaID}/{peticioDeFirmaID}")
  public ModelAndView rebutjar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    // TODO traduir
    // checkRole(request, "rebutjar");

    CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
        Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
    if (check == null) {
      // S'ha produit un error i retornam el control al llistat
      return llistatPaginat(request, response, null);
    }

    EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
    FirmaJPA firma = check.firma;
    PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

    /*
     * EstatDeFirmaJPA estatDeFirma; estatDeFirma =
     * checkEstatDeFirma(estatDeFirmaID, request, loginInfo,
     * LogicUtils.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR); if
     * (estatDeFirma == null) { // S'ha produit un error i retornam el control
     * al llistat return llistatPaginat(request, null); }
     * 
     * FirmaJPA firma = checkFirma(estatDeFirma, request ); if (firma == null) {
     * // S'ha produit un error i retornam el control al llistat return
     * llistatPaginat(request, null); }
     * 
     * PeticioDeFirmaJPA peticioDeFirma = checkPeticioDeFirma(peticioDeFirmaID,
     * request); if (peticioDeFirma == null) { // S'ha produit un error i
     * retornam el control al llistat return llistatPaginat(request, null); }
     */

    // Mirar si aquesta peticio esta bloquejada. Sino la bloquejam per rebutjar
    // !!!
    // TODO AIXO HA d'ANAR a LOGICA
    boolean checkOK = peticioDeFirmaLogicaEjb.checkPeticioDeFirmaByUsuariEntitat(
        peticioDeFirmaID, LoginInfo.getInstance().getUsuariEntitatID());
    if (!checkOK) {
      new PeticioDeFirmaController().createMessageError(request, "error.peticiobloquejada",
          peticioDeFirmaID);
      return llistatPaginat(request, response, null);
    }

    String motiuDeRebuig = request.getParameter("motiu");

    if (log.isDebugEnabled()) {
      log.debug("Rebutjat motiuDeRebuig: " + motiuDeRebuig);
    }

    try {
      peticioDeFirmaLogicaEjb.rebutjar(estatDeFirma, firma, peticioDeFirma, motiuDeRebuig);

      // TODO falta missatge de tot OK

    } catch (I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
    }

    return llistatPaginat(request, response, null);
  }

  @RequestMapping(value = "/validar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView validar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
        Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
        Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
    if (check == null) {
      // S'ha produit un error i retornam el control al llistat
      return llistatPaginat(request, response, null);
    }

    EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
    FirmaJPA firma = check.firma;
    PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

    peticioDeFirmaLogicaEjb.validar(estatDeFirma, firma, peticioDeFirma);

    // TODO falta missatge de tot OK

    return llistatPaginat(request, response, null);
  }

  @RequestMapping(value = "/invalidar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.POST)
  public ModelAndView invalidar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    // TODO traduir
    // checkRole(request, "invalidar");

    CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
        Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
        Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
    if (check == null) {
      // S'ha produit un error i retornam el control al llistat
      return llistatPaginat(request, response, null);
    }

    EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
    FirmaJPA firma = check.firma;
    PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

    String motiuDeInvalidacio = request.getParameter("motiu");

    if (log.isDebugEnabled()) {
      log.debug("MotiuDeInvalidacio : " + motiuDeInvalidacio);
    }

    try {
      peticioDeFirmaLogicaEjb.invalidar(estatDeFirma, firma, peticioDeFirma,
          motiuDeInvalidacio);

      // TODO falta missatge de tot OK ?
    } catch (I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
    }

    return llistatPaginat(request, response, null);
  }

  @RequestMapping(value = "/marcarrevisant/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView marcarrevisant(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    // TODO traduir
    // checkRole(request, "revisant per validar");
    try {
      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
          Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
          Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
      if (check != null) {
        EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
        FirmaJPA firma = check.firma;
        PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;
        peticioDeFirmaLogicaEjb.marcarRevisant(estatDeFirma, firma, peticioDeFirma);
      }
    } catch(I18NException e) {
      
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    }
  
    // TODO falta missatge de tot OK

    return llistatPaginat(request, response, null);
  }

  public static class CheckInfo {
    final PeticioDeFirmaJPA peticioDeFirma;
    final FirmaJPA firma;
    final EstatDeFirmaJPA estatDeFirma;

    /**
     * @param estatDeFirma
     * @param firma
     * @param peticioDeFirma
     */
    public CheckInfo(EstatDeFirmaJPA estatDeFirma, FirmaJPA firma,
        PeticioDeFirmaJPA peticioDeFirma) {
      super();
      this.estatDeFirma = estatDeFirma;
      this.firma = firma;
      this.peticioDeFirma = peticioDeFirma;
    }

  }

  // TODO Moure a capa de logica (així com els altres metodes de check)
  private CheckInfo checkAll(Long estatDeFirmaID, Long peticioDeFirmaID,
      HttpServletRequest request, boolean checkEstat, long... estatsInicialsRequerits)
      throws I18NException {

    EstatDeFirmaJPA estatDeFirma;
    estatDeFirma = checkEstatDeFirma(estatDeFirmaID, request, checkEstat,
        estatsInicialsRequerits);
    if (estatDeFirma == null) {
      return null;
    }

    FirmaJPA firma = checkFirma(estatDeFirma, request, checkEstat);
    if (firma == null) {
      return null;
    }

    PeticioDeFirmaJPA peticioDeFirma;
    peticioDeFirma = checkPeticioDeFirma(peticioDeFirmaID, request, checkEstat);
    if (peticioDeFirma == null) {
      return null;
    }

    return new CheckInfo(estatDeFirma, firma, peticioDeFirma);

  }

  private PeticioDeFirmaJPA checkPeticioDeFirma(Long peticioDeFirmaID,
      HttpServletRequest request, boolean checkEstat) throws I18NException {

    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
        .findByPrimaryKeyFull(peticioDeFirmaID);
    if (peticioDeFirma == null) {
      new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      return null;
    }
    if (checkEstat) {
      int estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
      if ((estat != Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES)
          && (estat != Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT)) {
        new PeticioDeFirmaController().createMessageError(request, "error.peticionoenprogres",
            peticioDeFirmaID);
        return null;
      }
    }
    return peticioDeFirma;
  }

  /**
   * 
   * @param estatDeFirma
   * @param request
   * @return
   */
  private FirmaJPA checkFirma(EstatDeFirmaJPA estatDeFirma, HttpServletRequest request,
      boolean checkEstat) {
    FirmaJPA firma = firmaEjb.findByPrimaryKey(new Long(estatDeFirma.getFirmaID()));
    if (firma == null) {
      log.error("La firma de l'EstatDeFirma no existeix.");
      super.createMessageError(request, "error.nofirmar", estatDeFirma.getEstatDeFirmaID());
      return null;
    }

    if (checkEstat && firma.getFitxerFirmat() != null) {
      log.error("La firma de l'EstatDeFirma ja esta firmada");
      super.createMessageError(request, "error.nofirmar", estatDeFirma.getEstatDeFirmaID());
      return null;
    } else {
      return firma;
    }
  }

  /**
   * 
   * @param estatDeFirmaID
   * @param request
   * @param loginInfo
   * @return
   */
  private EstatDeFirmaJPA checkEstatDeFirma(Long estatDeFirmaID, HttpServletRequest request,
      boolean checkEstat, long... estatsInicialsRequerits) {

    LoginInfo loginInfo = LoginInfo.getInstance();

    EstatDeFirmaJPA estatDeFirma = estatDeFirmaLogicaEjb.findByPrimaryKey(estatDeFirmaID);
    if (estatDeFirma == null) {
      super.createMessageError(request, "error.notfound", estatDeFirmaID);
      return null;
    }
    if (checkEstat) {
      boolean conteEstat = false;
      long estat = estatDeFirma.getTipusEstatDeFirmaInicialID();
      for (int i = 0; i < estatsInicialsRequerits.length; i++) {
        if (estat == estatsInicialsRequerits[i]) {
          conteEstat = true;
          break;
        }
      }

      if (!conteEstat) {
        log.error("L'estat de firma no te el tipus correcte: "
            + estatDeFirma.getTipusEstatDeFirmaInicialID());
        super.createMessageError(request, "error.nofirmar", estatDeFirmaID);
        return null;
      }

      if (!estatDeFirma.getUsuariEntitatID().equals(loginInfo.getUsuariEntitatID())) {
        log.error("La persona encarregada de la firma no es qui esta intentant firmar");
        super.createMessageError(request, "error.nofirmar", estatDeFirmaID);
        return null;
      }
    }

    return estatDeFirma;

  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      EstatDeFirmaFilterForm filterForm, List<EstatDeFirma> estatDeFirmaList) throws I18NException {

    boolean mutipleSelection = false;
    String role = getRole();
    if (role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) {
      for (EstatDeFirma ef : estatDeFirmaList) {
        if (ef.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR
            && ef.getTipusEstatDeFirmaFinalID() == null) {
          mutipleSelection = true;
          break;
        }
      }
    }
    filterForm.setVisibleMultipleSelection(mutipleSelection);

    Map<Long, PeticioDeFirma> peticionsByEstat;
    peticionsByEstat = estatDeFirmaLogicaEjb
        .getPeticioDeFirmaFromEstatDeFirmaID(estatDeFirmaList);
    mav.addObject("peticionsByEstat", peticionsByEstat);

    if (role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) {
      Map<Long, int[]> infoColaboradorsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
          ESTATS_INICIALS_COLA);
      mav.addObject("infoColaboradorsByEstat", infoColaboradorsByEstat);

      Map<Long, String> rebuigDescriptionByEstat = getRebuigDescriptionByEstat(estatDeFirmaList);

      mav.addObject("rebuigDescriptionByEstat", rebuigDescriptionByEstat);

    }

    if (role.equals(Constants.ROLE_DEST)) {

      Map<Long, int[]> infoDelegatsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
          ESTATS_INICIALS_DELE);

      mav.addObject("infoDelegatsByEstat", infoDelegatsByEstat);
    }

    if (role.equals(Constants.ROLE_COLA) || role.equals(Constants.ROLE_DELE)) {

      // new FieldConstant
      List<Long> estatsID = new ArrayList<Long>();

      for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
        // Crec que aquest if es innecesari !!!
        if (estatDeFirma.getColaboracioDelegacioID() != null) {
          estatsID.add(estatDeFirma.getEstatDeFirmaID());
        }
      }

      UsuariPersonaQueryPath upqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO()
          .DESTINATARI().USUARIPERSONA();

      SelectMultipleKeyValue<Long> smsky;
      smsky = new SelectMultipleKeyValue<Long>(ESTATDEFIRMAID.select, upqp.NOM().select,
          upqp.LLINATGES().select);

      List<KeyValue<Long>> nomsDest = estatDeFirmaEjb.executeQuery(smsky,
          ESTATDEFIRMAID.in(estatsID));

      Map<Long, String> infoDestByEstat = new HashMap<Long, String>();
      for (KeyValue<Long> keyValue : nomsDest) {
        infoDestByEstat.put(keyValue.getKey(), keyValue.getValue());
      }

      mav.addObject("infoDestByEstat", infoDestByEstat);
    }

  }

  public HashMap<Long, String> getRebuigDescriptionByEstat(List<EstatDeFirma> estatDeFirmaList)
      throws I18NException {

    HashMap<Long, String> rebuigDescriptionByEstat = new HashMap<Long, String>();

    for (EstatDeFirma estatDeFirma : estatDeFirmaList) {

      if (estatDeFirma.getTipusEstatDeFirmaFinalID() == null) {
        // Cercarem tots els estats de firma associats a la mateixa firma
        // i que estiguin invalidats
        // (1) Invalidats
        Where w1 = TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_INVALIDAT);
        // (2) Amb la mateix firma
        Where w2 = FIRMAID.equal(estatDeFirma.getFirmaID());
        List<String> motiuList = estatDeFirmaEjb.executeQuery(DESCRIPCIO, Where.AND(w1, w2));

        StringBuffer motius = new StringBuffer();
        for (String m : motiuList) {
          if (motius.length() != 0) {
            motius.append(' ');
          }
          motius.append(m);
        }
        rebuigDescriptionByEstat.put(estatDeFirma.getEstatDeFirmaID(), motius.toString());
      }
    }

    return rebuigDescriptionByEstat;

  }

  /*
   * 
   * public void fillMapReferences(ModelAndView mav) throws Exception {
   * List<StringKeyValue> listInicial =
   * tipusEstatDeFirmaInicialRefList.getReferenceList(
   * TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID, null);
   * mav.addObject("estatsInicials", Utils.listToMap(listInicial));
   * 
   * List<StringKeyValue> listFinal =
   * tipusEstatDeFirmaFinalRefList.getReferenceList(
   * TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID, null);
   * mav.addObject("estatsFinals", Utils.listToMap(listFinal)); }
   */

  // TODO Estats inicials eliminar list

  protected Map<Long, int[]> infoColaboradorsDelegats(List<EstatDeFirma> estatDeFirma,
      Long[] estatsInicials) throws I18NException {
    Map<Long, int[]> infoValidacionsByEstat = new HashMap<Long, int[]>();
    final String usuariEntitatID = LoginInfo.getInstance().getUsuariEntitatID();
    for (EstatDeFirma estat : estatDeFirma) {
      Where w = Where.AND(EstatDeFirmaFields.USUARIENTITATID.notEqual(usuariEntitatID),
          EstatDeFirmaFields.FIRMAID.equal(estat.getFirmaID()),
          EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(estatsInicials));

      List<GroupByValueItem> list = estatDeFirmaLogicaEjb.executeQuery(
          new SelectGroupByAndCountForField(EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID), w,
          (OrderBy[]) null);

      int[] valors = new int[TIPUSESTATDEFIRMAFINAL.length + 2]; // +2 = pendent
                                                                 // i total

      int total = 0;
      for (GroupByValueItem item : list) {
        int count = item.getCount().intValue();
        String valStr = item.getValue();
        // Pendent
        if (valStr == null) {
          valors[1] = count;
          total = total + count;
          continue;
        }

        int index = Long.valueOf(valStr).intValue();
        // log.info("MMMMMM " + item.getCodeLabel() + "  " + val + "  " +
        // item.getCount());

        try {
          valors[index + 2] = count;
          total = total + count;
          continue;
        } catch (IndexOutOfBoundsException iobe) {
          log.error("Valor desconegut " + valStr + " o index fora de rang " + index, iobe);
        }
        /*
         * if ("1".equals(val)) { valors[2] = count; total = total + count;
         * continue; }
         * 
         * if ("4".equals(val)) { valors[3] = count; total = total + count;
         * continue; }
         */

      }

      valors[0] = total;

      infoValidacionsByEstat.put(estat.getEstatDeFirmaID(), valors);
    }
    return infoValidacionsByEstat;
  }

  @Override
  public String getTileList() {
    // No modificar: aquests noms estan definits dins els tiles.xml
    return "estatFirmaList_" + getRole();
  }

  @Override
  public final String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_" + getContextWeb();
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    String role = getRole();

    Where roleWhere;
    if (role.equals(Constants.ROLE_DEST)) {
      // Els estats de firma de destinatari són aquells que:
      // (1) estat inicial es ASSIGNAT PER FIRMAR
      // (2) COLABORACIODELEGACIOID es null
      roleWhere = Where.AND(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
          .equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
          EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull());

    } else {
      if (role.equals(Constants.ROLE_DELE)) {
        // Els estats de firma de delegat són aquells que:
        // (1) estat inicial es ASSIGNAT PER FIRMAR
        // (2) COLABORACIODELEGACIOID es not null
        roleWhere = Where.AND(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
            .equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
            EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull());
      } else {
        if (role.equals(Constants.ROLE_COLA)) {
          // Els estats de firma de colaborador són aquells que:
          // (1) Els estats inicials poden ser ASSIGNAT_PER_VALIDAR o
          // REVISANT_PER_VALIDAR
          // (2) COLABORACIODELEGACIOID es not null
          roleWhere = Where.AND(Where.OR(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
              .equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR),
              EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                  .equal(Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR)),
              EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull());
        } else {
          // TODO Traduir
          throw new I18NException("error.unknown","No hi ha gestió de EstatDeFirma pel role " + role);
        }
      }

    }

    return Where
        .AND(roleWhere, EstatDeFirmaFields.USUARIENTITATID.equal(LoginInfo.getInstance()
            .getUsuariEntitatID()));
  }

  @RequestMapping(value = "/fullView/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView fullView(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, false,
        Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
    if (check == null) {
      // S'ha produit un error i retornam el control al llistat
      return llistatPaginat(request, response, null);
    }

    EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
    FirmaJPA firma = check.firma;
    PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

    ModelAndView mav = new ModelAndView(getFullViewTile());

    mav.addObject("peticioDeFirma", peticioDeFirma);

    mav.addObject("estatDeFirma", estatDeFirma);

    mav.addObject("firma", firma);

    String contexte = getContextWeb();

    mav.addObject("contexte", contexte);

    mav.addObject("rolecontext", contexte.substring(1, contexte.indexOf('/', 2)));

    {
      List<EstatDeFirma> estats;
      estats = estatDeFirmaEjb.select(EstatDeFirmaFields.FIRMAID.equal(estatDeFirma
          .getFirmaID()));

      List<EstatDeFirmaJPA> fullList = usuariEntitatLogicaEjb.fillUsuariEntitatFull(estats);

      List<EstatDeFirmaJPA> estatsDeDelegats = new ArrayList<EstatDeFirmaJPA>();
      List<EstatDeFirmaJPA> estatsColaboradors = new ArrayList<EstatDeFirmaJPA>();

      for (EstatDeFirmaJPA estat : fullList) {

        if (estat.getColaboracioDelegacioID() == null) {
          mav.addObject("destinatari", estat);
        } else {
          if (estat.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
            estatsDeDelegats.add(estat);
          } else {
            estatsColaboradors.add(estat);
          }
        }

      }

      mav.addObject("colaboradors", estatsColaboradors);
      mav.addObject("delegats", estatsDeDelegats);
    }

    {
      // 1.- Fitxers a visualitzar

      List<KeyValue<FitxerJPA>> fitxers = new ArrayList<KeyValue<FitxerJPA>>();
      // 1.1.- Fitxer principal
      int tipusFirmaID = peticioDeFirma.getTipusFirmaID();
      if (tipusFirmaID == Constants.TIPUSFIRMA_PADES) {
        FitxerJPA f;
        f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

        fitxers.add(new KeyValue<FitxerJPA>(f, String.valueOf(Constants.DOC_PDF)));
      } else {
        FitxerJPA f;
        if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          f = peticioDeFirma.getFitxerAFirmar();
        } else {
          f = peticioDeFirma.getFitxerAdaptat();
        }
        fitxers.add(new KeyValue<FitxerJPA>(f, processFileType(f)));
      }

      // 1.2.- Annexes
      Set<AnnexJPA> annexes = peticioDeFirma.getAnnexs();
      for (AnnexJPA annex : annexes) {
        // TODO si es un PDF, podriem cercar el darrer firmat i passar-ho
        FitxerJPA f = annex.getFitxer();
        fitxers.add(new KeyValue<FitxerJPA>(f, processFileType(f)));
      }

      mav.addObject("fitxers", fitxers);
    }

    /*
     * 
     * final String usuariEntitatID =
     * LoginInfo.getInstance().getUsuariEntitatID();
     * 
     * // Llegir destinatari si som delegat Long colaboracioDelegacioID =
     * estatDeFirma.getColaboracioDelegacioID() ; {
     * 
     * UsuariEntitatJPA dest; if (colaboracioDelegacioID == null) { // (a)
     * Llegir el destinatari de col.laboracio-delegacio ColaboracioDelegacioJPA
     * cd; cd =
     * colaboracioDelegacioEjb.findByPrimaryKey(colaboracioDelegacioID); dest
     * =usuariEntitatLogicaEjb.findByPrimaryKeyFull(cd.getDestinatariID()); }
     * else { dest = LoginInfo.getInstance().getUsuariEntitat(); }
     * mav.addObject("destinatari", dest); }
     * 
     * if (colaboracioDelegacioID == null) { // SOM DESTINATARI // Obtenir la
     * llista de Delegats
     * 
     * Where w = Where.AND(
     * EstatDeFirmaFields.USUARIENTITATID.notEqual(usuariEntitatID),
     * EstatDeFirmaFields.FIRMAID.equal(estatDeFirma.getFirmaID()),
     * EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(ESTATS_INICIALS_DELE) );
     * 
     * List<EstatDeFirma> estatsDeDelegats; estatsDeDelegats =
     * estatDeFirmaEjb.select(w);
     * 
     * List<EstatDeFirmaJPA> list =
     * usuariEntitatLogicaEjb.fillUsuariEntitatFull(estatsDeDelegats);
     * 
     * mav.addObject("delegats", list);
     * 
     * }
     * 
     * 
     * // Obtenir la llista de Col.laboradors { Where w = Where.AND(
     * EstatDeFirmaFields.USUARIENTITATID.notEqual(usuariEntitatID),
     * EstatDeFirmaFields.FIRMAID.equal(estatDeFirma.getFirmaID()),
     * EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(ESTATS_INICIALS_COLA) );
     * 
     * List<EstatDeFirma> estatsColaboradors; estatsColaboradors =
     * estatDeFirmaEjb.select(w);
     * 
     * List<EstatDeFirmaJPA> list =
     * usuariEntitatLogicaEjb.fillUsuariEntitatFull(estatsColaboradors);
     * 
     * mav.addObject("colaboradors", list); }
     */

    // Traduccions
    // Estats Finals d'un EstatDeFirma
    Map<Long, String> traduccions = new HashMap<Long, String>();

    traduccions.put(TIPUSESTATDEFIRMAFINAL_VALIDAT, "tipusestatdefirmafinal.VALIDAT");
    traduccions.put(TIPUSESTATDEFIRMAFINAL_INVALIDAT, "tipusestatdefirmafinal.INVALIDAT");
    traduccions.put(TIPUSESTATDEFIRMAFINAL_FIRMAT, "tipusestatdefirmafinal.FIRMAT");
    traduccions.put(TIPUSESTATDEFIRMAFINAL_REBUTJAT, "tipusestatdefirmafinal.REBUTJAT");
    traduccions.put(TIPUSESTATDEFIRMAFINAL_DESCARTAT, "tipusestatdefirmafinal.DESCARTAT");
    traduccions.put(null, "pendent");

    mav.addObject("traduccions", traduccions);

    return mav;
  }

  public String processFileType(FitxerJPA f) {

    String mime = f.getMime();

    if (mime.equals(Constants.PDF_MIME_TYPE)) {
      return String.valueOf(Constants.DOC_PDF);
    }

    if (mime.startsWith("image/")) {
      return String.valueOf(Constants.DOC_IMG);
    }

    if (mime.startsWith("text/") || mime.equals("application/xml")) {
      return String.valueOf(Constants.DOC_TXT);
    }

    return String.valueOf(Constants.DOC_BIN);

  }

  public String getFullViewTile() {
    return "estatFirmaFullView_" + getRole();
  }

  @RequestMapping(value = "/docfirmat/{peticioDeFirmaID}", method = RequestMethod.GET)
  public void docfirmat(HttpServletResponse response, @PathVariable Long peticioDeFirmaID)
      throws I18NException {
    /*
     * PeticioDeFirmaJPA peticio =
     * this.peticioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirmaID); long
     * estat = peticio.getTipusEstatPeticioDeFirmaID();
     * 
     * Fitxer f; if (estat == TIPUSESTATPETICIODEFIRMA_NOINICIAT) { f =
     * peticio.getFitxerAFirmar(); } else {
     * 
     * FirmaJPA firma =
     * this.peticioDeFirmaLogicaEjb.getLastSignOfPeticioDeFirma(
     * peticioDeFirmaID); if (firma == null) { f =
     * peticio.getFitxerTaulaFirmesFitxersAdjunts(); } else { f =
     * firma.getFitxerFirmat(); } }
     */

    Fitxer f;
    f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

    FileDownloadController.fullDownload(f.getFitxerID(), f.getNom(), f.getMime(), response);
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public final String getEntityNameCode() {
    return getBaseEntityNameCode();
  }

  @Override
  public final String getEntityNameCodePlural() {
    return getBaseEntityNameCode() + ".plural";
  }

  public String getBaseEntityNameCode() {
    return "solicituddefirma";
  }

  /*
   * @Override public String[] getArgumentsMissatge(Object __estatDeFirmaID,
   * Exception e) { java.lang.Long estatDeFirmaID = (java.lang.Long)
   * __estatDeFirmaID; if (estatDeFirmaID == null) { return new String[] {
   * I18NUtils.tradueix("estatDeFirma.estatDeFirma"),
   * I18NUtils.tradueix("estatDeFirma.estatDeFirmaID"), null, e == null ? "" :
   * e.getMessage() }; } else { return new String[] {
   * I18NUtils.tradueix("estatDeFirma.estatDeFirma"),
   * I18NUtils.tradueix("estatDeFirma.estatDeFirmaID"),
   * String.valueOf(estatDeFirmaID), e == null ? "" : e.getMessage() }; } }
   */
} // Final de Classe

