package es.caib.portafib.back.controller.dest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.controller.webdb.ColaboracioDelegacioController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.back.form.webdb.TipusDocumentRefList;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.RoleUsuariEntitatLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.EmailInfo;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.PortaFIBTimeStampGenerator;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * 
 * @author anadal
 * @author dboerner
 * 
 */
@Controller
@RequestMapping(value = "/dest/delegat")
@SessionAttributes(types = { ColaboracioDelegacioDestForm.class,
    ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class, SeleccioUsuariForm.class })
public class DelegacioDestController extends ColaboracioDelegacioController implements
    ConstantsV2 {

  
  public static final String SELECTION_DELE_COLA_USUARI_ENTITAT = "SELECTION_DELE_COLA_USUARI_ENTITAT_ID";
  
  protected static final List<Field<?>> groupByFields = new ArrayList<Field<?>>();

  static {
    groupByFields.add(COLABORADORDELEGATID);
    groupByFields.add(DATAINICI);
    groupByFields.add(DATAFI);
    groupByFields.add(ACTIVA);
  }

  public static final int ESTAT_EDITABLE = 0;

  public static final int ESTAT_ACTIVADA = 1;

  public static final int ESTAT_DESACTIVADA = 2;
  
  public static final ValueComparator valueComparator = new ValueComparator();
  
  @EJB(mappedName = es.caib.portafib.ejb.UsuariPersonaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariPersonaLocal usuariPersonaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/TipusDocumentColaboracioDelegacioEJB/local")
  protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;

  @EJB(mappedName = "portafib/ColaboracioDelegacioLogicaEJB/local")
  protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioLogicaEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaEJB/local")
  protected es.caib.portafib.ejb.EstatDeFirmaLocal estatDeFirmaEjb;
  
  
  @EJB(mappedName = "portafib/RoleUsuariEntitatLogicaEJB/local")
  protected RoleUsuariEntitatLogicaLocal roleUsuariEntitatLogicaEjb;
  
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;
  
  @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
  protected SegellDeTempsLogicaLocal segellDeTempsEjb;

  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  @Autowired
  protected UsuariEntitatRefList personaRefList;
  
  @Autowired
  protected UsuariEntitatRefList carrecRefList;
  
  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;

  @PostConstruct
  public void init() {
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }

    {
      this.carrecRefList = new UsuariEntitatRefList(carrecRefList);
      this.carrecRefList.setSelects(new Select<?>[] { UsuariEntitatFields.CARREC.select});
    }
    
    
    final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
    {
      this.personaRefList = new UsuariEntitatRefList(personaRefList);
      this.personaRefList.setSelects(new Select<?>[] { 
          personaQueryPath.LLINATGES().select , new SelectConstant(", "), 
          personaQueryPath.NOM().select, new SelectConstant(" ("), 
          personaQueryPath.NIF().select, new SelectConstant(")") });
      this.personaRefList.setSeparator("");
    }
    
    
    {
      this.usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);
  
      this.usuariEntitatRefList.setSelects(new Select<?>[] { personaQueryPath.NOM().select,
        personaQueryPath.LLINATGES().select });
    }    

  }


  /**
   * @return true indica que gestiona delegacions, false gestiona col·laboracions
   */
  public boolean esDelegat() {
    return true;
  }
  
  
  /**
   * 
   * @return true indica que gestiona delegacions/colaboracions sobre persones fisiques, en canvi
   *         false indica que aquestes delegacions/col·laboracions es fan sobre càrrecs.
   */
  public boolean esDeCarrec() {
    return false;
  }
  
  // ---------------------------------------------------------------
  
  public String getTileSeleccioUsuari() {
    return "seleccioUsuariForm" + (esDeCarrec()? "_ADEN" : "_DEST");
  }
  
  
  
  @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
  public ModelAndView seleccioUsuariGet()
      throws Exception {

      ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

      SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();
      
      String type = esDelegat()?"delegat":"colaborador";
      
      String titol = esDeCarrec()? "colaboradordecarrec" : ( type + ".seleccio.titol");
      String subtitol = esDeCarrec()? "colaboradordecarrec.subtitol" : (type + ".seleccio.subtitol");
      seleccioUsuariForm.setTitol(titol);
      seleccioUsuariForm.setSubtitol(subtitol);
      seleccioUsuariForm.setCancelUrl("/canviarPipella/"+ConstantsV2.ROLE_DEST);
      seleccioUsuariForm.setUrlData("/common/json/usuarientitat");
      try {
        seleccioUsuariForm.setUsuarisFavorits(
            SearchJSONController.favoritsToUsuariEntitat(
                usuariEntitatLogicaEjb.selectFavorits(
                  LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
      } catch (I18NException e) {
        log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
      }
      
      mav.addObject(seleccioUsuariForm);

      return mav;
  }


  @RequestMapping(value = "/selecciousuari", method = RequestMethod.POST)
  public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm,
      BindingResult result, HttpServletRequest request) throws I18NException {
    
    ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

    seleccioUsuariValidator.validate(seleccioUsuariForm, result);
    if (result.hasErrors()) {
      return mav;
    }

    String usuariEntitatID = seleccioUsuariForm.getId();

    // Comprovam que no existesqui un usuarientitat ja per a aquest usuari persona.
    UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);

    if(ue != null) {
      request.getSession().setAttribute(SELECTION_DELE_COLA_USUARI_ENTITAT, usuariEntitatID);
      mav.setView(new RedirectView(getContextWeb() + "/new", true));
      return mav;
    } else {      
      return mav;
    }

  }
  
  
  // ----------------------------------------------------------------
  

  public final String getRole() {
    return esDelegat() ? ConstantsV2.ROLE_DELE : ConstantsV2.ROLE_COLA;
  }

  @Override
  public String getTileForm() {
    return "colaboracioDelegacioDestForm"; // _" + getRole();
  }

  @Override
  public String getTileList() {
    return "colaboracioDelegacioDestList";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return getEntityNameCode() + "_" + getRole() +"_" + esDeCarrec() + "_FilterForm";
  }

  @Override
  public String getEntityNameCode() {    
    if (esDeCarrec()) {
      return esDelegat() ? "delegatdecarrec" : "colaboradordecarrec";
    } else {
      return esDelegat() ? "delegacio" : "colaboracio";
    }
  }

  @Override
  public String getEntityNameCodePlural() {
    return getEntityNameCode() + ".plural";
  }

  @Override
  public ColaboracioDelegacioFilterForm getColaboracioDelegacioFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    ColaboracioDelegacioFilterForm colaboracioDelegacioFilterForm;
    colaboracioDelegacioFilterForm = super.getColaboracioDelegacioFilterForm(pagina, mav,
        request);

    if (colaboracioDelegacioFilterForm.isNou()) {
     
      // Ocultam columnes
      if (!Configuracio.isDesenvolupament()) {
        colaboracioDelegacioFilterForm.addHiddenField(COLABORACIODELEGACIOID);
      }
      if (!esDeCarrec()) {
        colaboracioDelegacioFilterForm.addHiddenField(DESTINATARIID);
      }
      colaboracioDelegacioFilterForm.addHiddenField(ESDELEGAT);
      colaboracioDelegacioFilterForm.addHiddenField(FITXERAUTORITZACIOID);
      colaboracioDelegacioFilterForm.addHiddenField(MOTIUDESHABILITADA);
      colaboracioDelegacioFilterForm.addHiddenField(DESCRIPCIO);

      // TODO Tiquet #113 
      if (esDelegat()) {
        colaboracioDelegacioFilterForm.addHiddenField(REVISOR);
      }

      colaboracioDelegacioFilterForm.setVisibleMultipleSelection(false);

      // Ocultam botó creació i borrat
      colaboracioDelegacioFilterForm.setDeleteButtonVisible(false);

      // Canvi d'etiqueta
      if (esDeCarrec()) {
        colaboracioDelegacioFilterForm.addLabel(DESTINATARIID, "carrec");
      }
      colaboracioDelegacioFilterForm.addLabel(COLABORADORDELEGATID, getRole());

      // Agrupació
      colaboracioDelegacioFilterForm.setGroupByFields(groupByFields);

      colaboracioDelegacioFilterForm.setDeleteSelectedButtonVisible(false);

    }

    return colaboracioDelegacioFilterForm;
  }

  @Override
  public ColaboracioDelegacioForm getColaboracioDelegacioForm(ColaboracioDelegacioJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    
    
    ColaboracioDelegacioForm cdoriginal = super.getColaboracioDelegacioForm(_jpa, __isView, request, mav);
    
    
    ColaboracioDelegacioDestForm colaboracioDelegacioForm;
    if (cdoriginal instanceof ColaboracioDelegacioDestForm) {
      colaboracioDelegacioForm = (ColaboracioDelegacioDestForm)cdoriginal;
    } else {
      colaboracioDelegacioForm = new ColaboracioDelegacioDestForm(cdoriginal);
    }

    // Valors per defecte
    ColaboracioDelegacioJPA colaboracioDelegacioJPA;
    colaboracioDelegacioJPA = colaboracioDelegacioForm.getColaboracioDelegacio();
    
    
    // /El Colaborador-Delegat és l'usuari seleccionat en un formulari extern emprant NIF
    if (colaboracioDelegacioForm.isNou()) {
      HttpSession session = request.getSession();
      String usuEntID = (String)session.getAttribute(SELECTION_DELE_COLA_USUARI_ENTITAT);
      if( usuEntID == null){
        mav.setView(new RedirectView(getContextWeb()+"/selecciousuari", true));
        return new ColaboracioDelegacioForm();
      }
      
      session.removeAttribute(SELECTION_DELE_COLA_USUARI_ENTITAT);
      colaboracioDelegacioJPA.setColaboradorDelegatID(usuEntID);
    }
    
    
    
    if (esDeCarrec()) {
      // (1.a) Destinatari s'elegirà entre una llista de Càrrecs de l'entitat
    } else {
      // (2.a) Destinatari es l'usuari loguejat
      colaboracioDelegacioJPA.setDestinatariID(LoginInfo.getInstance().getUsuariEntitatID());
      colaboracioDelegacioForm.addHiddenField(DESTINATARIID);
    }
    colaboracioDelegacioJPA.setEsDelegat(esDelegat());
    
    // camps read only
    colaboracioDelegacioForm.addReadOnlyField(ACTIVA);
    
    colaboracioDelegacioForm.addReadOnlyField(COLABORADORDELEGATID);
    
    // Ocultam camps
    colaboracioDelegacioForm.addHiddenField(ESDELEGAT);

    colaboracioDelegacioForm.addHiddenField(MOTIUDESHABILITADA);
    // Tiquet #113
    if (esDelegat()) {
      // Oculta revisor
      colaboracioDelegacioForm.addHiddenField(REVISOR);
    } else {
      // Mostra ajuda per revisor
      colaboracioDelegacioForm.addHelpToField(REVISOR, I18NUtils.tradueix("colaboracio.revisor.info"));

      // No mostra l'autorització per col·laboradors
      colaboracioDelegacioForm.addHiddenField(FITXERAUTORITZACIOID);
    }
    
    
    colaboracioDelegacioForm.setAttachedAdditionalJspCode(true);
    

    // Canvi d'etiqueta
    if (esDeCarrec()) {
      colaboracioDelegacioForm.addLabel(DESTINATARIID, "carrec");
    }
    colaboracioDelegacioForm.addLabel(COLABORADORDELEGATID, getRole());
    
    boolean isDebug = log.isDebugEnabled();

    // Carregam els tipus de documents

    // (a) Els nostres documents són aquells que apunten a usuariAplicacio null
    // o a un usuariAplicacio de la nostra entitat
    Map<Long, String> allTipusDocumentInfo;
    allTipusDocumentInfo = getAllTipusDocumentInfo(isDebug);
    // (b) Obtenim els tipus de document actual.
    // + Si es nou no té cap tipus
    // + Si estam editant, llegirem els seus tipus

    // List<StringKeyValue> currentTipusDocumentList = new
    // ArrayList<StringKeyValue>();

    List<Long> currentTipusDocument = new ArrayList<Long>();

    if (colaboracioDelegacioForm.isNou()) {
      // Assignam titol
      // colaboracioDelegacioForm.setTitleCode(getCodeMessage() + ".crear");

      if (esDelegat()) {
        colaboracioDelegacioJPA.setActiva(false);
        colaboracioDelegacioForm.addHiddenField(FITXERAUTORITZACIOID);
      }

      colaboracioDelegacioJPA.setDataInici(new Timestamp(
          System.currentTimeMillis() + 30 * 60 * 1000));
      
      if (!esDelegat()) {
        colaboracioDelegacioForm.getReadOnlyFields().remove(ACTIVA);
        colaboracioDelegacioForm.getColaboracioDelegacio().setActiva(true);
      }

    } else {

      
      currentTipusDocument = getCurrentTipusDocument(colaboracioDelegacioJPA,
          allTipusDocumentInfo, isDebug);
      
      
      

      int estat;

      boolean esEditable;
      if (colaboracioDelegacioJPA.isEsDelegat()) {
        esEditable = (colaboracioDelegacioJPA.getFitxerAutoritzacioID() == null);
      } else {
        esEditable = (colaboracioDelegacioJPA.getMotiuDeshabilitada() == null)
            && !colaboracioDelegacioJPA.isActiva();
      }

      if (esEditable) {
        estat = ESTAT_EDITABLE;
        log.debug("ESTAT_EDITABLE");
      } else {
        if (colaboracioDelegacioJPA.getMotiuDeshabilitada() == null) {
          // if (colaboracioDelegacioJPA.isActiva()) {
          estat = ESTAT_ACTIVADA;
          log.debug("ESTAT_ACTIVADA");
          /*
           * } else { estat = ESTAT_FITXER_FIRMAT;
           * log.debug("ESTAT_FITXER_FIRMAT"); }
           */
        } else {
          estat = ESTAT_DESACTIVADA;
          log.debug("ESTAT_DESACTIVADA");
        }
      }

      switch (estat) {
      case ESTAT_EDITABLE:
        if (colaboracioDelegacioJPA.isEsDelegat()) {
          // Encara no ha firmat l'autoritzacio, per la qual cosa no es pot
          // utilitzar
          colaboracioDelegacioJPA.setActiva(false);
          colaboracioDelegacioForm.addHiddenField(FITXERAUTORITZACIOID);
          // Afegim boto per firmar
          colaboracioDelegacioForm.addAdditionalButton(new AdditionalButton("icon-pencil",
              "firmar", 
              //"javascript:firmarAutoritzacio('" + request.getContextPath() + getContextWeb() + "/firmarautoritzacio/{0}')",
              //request.getContextPath() + 
              getContextWeb() + "/firmarautoritzacio/" +  colaboracioDelegacioForm.getColaboracioDelegacio().getColaboracioDelegacioID(),
              "btn-warning"));
          // Missatge informatiu
          HtmlUtils.saveMessageInfo(request,
              I18NUtils.tradueix("delegacio.avisnofirmadaautoritzacio"));
        } else {
          colaboracioDelegacioForm.addAdditionalButton(new AdditionalButton("icon-play",
              "activar", getContextWeb() + "/activar/{0}", "btn-success"));
        }
        break;
      /*
       * case ESTAT_FITXER_FIRMAT: // No es pot editar cap camp excepte ACTIU
       * for (Field<?> f :
       * ColaboracioDelegacioFields.ALL_COLABORACIODELEGACIO_FIELDS) {
       * colaboracioDelegacioForm.addReadOnlyField(f); }
       * 
       * // Tipus de Documents ja no es poden modificar
       * colaboracioDelegacioForm.setTipusReadOnly(true);
       * 
       * colaboracioDelegacioForm.addAdditionalButton(new
       * AdditionalButton("icon-play", "activar", getContextWeb() +
       * "/activar/{0}", "btn-success"));
       * 
       * break;
       */
      case ESTAT_ACTIVADA:
      case ESTAT_DESACTIVADA:
        if (esDeCarrec()) {
          colaboracioDelegacioForm.addReadOnlyField(DESTINATARIID);
        }
        
        // No es pot editar cap camp
        for (Field<?> f : ColaboracioDelegacioFields.ALL_COLABORACIODELEGACIO_FIELDS) {
          colaboracioDelegacioForm.addReadOnlyField(f);
        }
        // Tipus de Documents ja no es poden modificar
        colaboracioDelegacioForm.setTipusReadOnly(true);
        // Ja no és editable
        HtmlUtils.saveMessageWarning(request,
            I18NUtils.tradueix(getEntityNameCode() + ".noeditable"));

        // Es pot borrar? Per esbrinar-ho calcularem el nombre de estatsDeFirma
        // associats
        Long colaboracioDelegacioID = colaboracioDelegacioJPA.getColaboracioDelegacioID();
        Where w = EstatDeFirmaFields.COLABORACIODELEGACIOID.equal(colaboracioDelegacioID);
        boolean teEstatsDeFirma = (estatDeFirmaEjb.count(w) != 0);
        if (teEstatsDeFirma) {
          colaboracioDelegacioForm.setDeleteButtonVisible(false);
        }
        colaboracioDelegacioForm.setSaveButtonVisible(false);
        // Assignam titol
        colaboracioDelegacioForm.setTitleCode(getEntityNameCode() + ".consultar");

        if (estat == ESTAT_ACTIVADA) {
          // Revisar si aquests estats de firma estan en aquest mateix moment en
          // funcionament
          Where w2 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull();
          if (estatDeFirmaEjb.count(Where.AND(w, w2)) == 0) {
            // No hi ha cap col.laboracio en funcionament en aquest mateix
            // moment, afegim un boto per si la volen desactivar
            colaboracioDelegacioForm.addAdditionalButton(new AdditionalButton(
                "icon-ban-circle", "desactivar", "javascript:desactivar({0})", "btn-warning"));
          }
        } else {
          colaboracioDelegacioForm.getHiddenFields().remove(MOTIUDESHABILITADA);
        }

        break;

      }

    }

    // Calculam els tipus disponibles
    List<Long> availableTipusDocument = new ArrayList<Long>();
    for (Long id : allTipusDocumentInfo.keySet()) {
      if (!currentTipusDocument.contains(id)) {
        availableTipusDocument.add(id);
      }
    }

    colaboracioDelegacioForm.setAllTipusDocumentInfo(allTipusDocumentInfo);
    
    
    colaboracioDelegacioForm.setAvailableTipusDocument(availableTipusDocument);
    colaboracioDelegacioForm.setCurrentTipusDocument(currentTipusDocument);
    colaboracioDelegacioForm.setTipus(currentTipusDocument.isEmpty() ? 1 : 2);

    return colaboracioDelegacioForm;
  }


  public List<Long> getCurrentTipusDocument(ColaboracioDelegacioJPA colaboracioDelegacioJPA,
      Map<Long, String> allTipusDocumentInfo, boolean isDebug) {
    List<Long> currentTipusDocument;
    Set<TipusDocumentColaboracioDelegacioJPA> tipus;
    tipus = colaboracioDelegacioJPA.getTipusDocumentColaboracioDelegacios();

    if (isDebug) {
      log.info(" getColaboracioDelegacioForm: Updating: Tipus<BBDD>.size() == " + tipus.size());
    }

    Map<Long, String> currentTipusDocumentMap = new HashMap<Long, String>();
    
    for (TipusDocumentColaboracioDelegacioJPA t : tipus) {
      if (isDebug) {
        log.info("      + Conte Element[" + t.getTipusDocumentID() + "]");
      }
      
      long id = t.getTipusDocumentID();
      currentTipusDocumentMap.put(id, allTipusDocumentInfo.get(id));

    }
    
    currentTipusDocumentMap = sortByValue(currentTipusDocumentMap);
    
    currentTipusDocument = new ArrayList<Long>(currentTipusDocumentMap.keySet());
    return currentTipusDocument;
  }


  public Map<Long, String> getAllTipusDocumentInfo(boolean isDebug) throws I18NException {
    Map<Long, String> allTipusDocumentInfo;
    {
      List<StringKeyValue> allTipusDocumentList;
      String entitatID = LoginInfo.getInstance().getEntitatID();
      SubQuery<UsuariAplicacio, String> subQuery;
      subQuery = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
          UsuariAplicacioFields.ENTITATID.equal(entitatID));
      Where whereAll = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.isNull(),
          TipusDocumentFields.USUARIAPLICACIOID.in(subQuery));

      
      
      allTipusDocumentList = tipusDocumentRefList.getReferenceList(
          TipusDocumentFields.TIPUSDOCUMENTID, whereAll);
      // Ordenam pel nom
      // Collections.sort(allTipusDocumentList, STRINGKEYVALUE_COMPARATOR);

      allTipusDocumentInfo = new HashMap<Long, String>();

      for (StringKeyValue stringKeyValue : allTipusDocumentList) {
        allTipusDocumentInfo.put(new Long(stringKeyValue.getKey()), stringKeyValue.getValue());
      }
      
      // ordenam pel Valor
      allTipusDocumentInfo = sortByValue(allTipusDocumentInfo);
      

      if (isDebug) {
        log.debug(" COUNT allTipusDocument = " + allTipusDocumentList.size());
        for (StringKeyValue skv : allTipusDocumentList) {
          log.debug("      - Trobat allTipusDocument == " + skv.getKey() + " --> "
              + skv.getValue());
        }
      }
    }
    return allTipusDocumentInfo;
  }
  
  
  private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
    List<Entry<K, V>> list = new LinkedList<Map.Entry<K,V>>(map.entrySet());
    Collections.sort(list, new Comparator<Object>() {
        @SuppressWarnings("unchecked")
        public int compare(Object o1, Object o2) {
            return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
        }
    });

    Map<K, V> result = new LinkedHashMap<K, V>();
    for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
        Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
        result.put(entry.getKey(), entry.getValue());
    }

    return result;
}
  
  
  @Override
  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    
    List<StringKeyValue> ueList;
    if (esDeCarrec()) {
      ueList = carrecRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID,
        Where.AND(where, UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
           UsuariEntitatFields.CARREC.isNotNull()) );
    } else {
      return super.getReferenceListForDestinatariID(request, mav, where);
    }
    
    java.util.Collections.sort(ueList, valueComparator);
    
    return ueList;

   
  }
  
 

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    
    Where w;
    if (esDeCarrec()) {
      es.caib.portafib.model.fields.ColaboracioDelegacioQueryPath cdqp;
      cdqp = new es.caib.portafib.model.fields.ColaboracioDelegacioQueryPath();
      w = Where.AND(
           cdqp.DESTINATARI().CARREC().isNotNull(),
           cdqp.DESTINATARI().ENTITATID().equal(LoginInfo.getInstance().getEntitatID())
           );
    } else {
      w = DESTINATARIID.equal(LoginInfo.getInstance().getUsuariEntitatID());
    }
    
    
    return Where.AND(w, ESDELEGAT.equal(esDelegat()));

  }

  @Override
  public void postValidate(HttpServletRequest request,
      ColaboracioDelegacioForm colaboracioDelegacioForm,
      BindingResult result) throws I18NException {

    ColaboracioDelegacioDestForm form = (ColaboracioDelegacioDestForm) colaboracioDelegacioForm;
    ColaboracioDelegacioJPA colaboracioDelegacioJPA;
    colaboracioDelegacioJPA = colaboracioDelegacioForm.getColaboracioDelegacio();

    {
      // Validar Data-Inici
      if (!result.hasFieldErrors(get(DATAINICI))) {
        Timestamp tsInici = colaboracioDelegacioJPA.getDataInici();
        if (System.currentTimeMillis() > tsInici.getTime()) {
          result.rejectValue(get(DATAINICI), "typeMismatch.java.sql.Timestamp",
              new String[] { I18NUtils.tradueix(get(DATAINICI)) }, null);
        }

        // Validar Data-Fi si Data-Inici és correcte
        if (!result.hasFieldErrors(get(DATAFI))) {
          Timestamp tsFi = colaboracioDelegacioJPA.getDataFi();
          if (tsFi != null) {
            // Check 1
            if (tsFi.getTime() <= tsInici.getTime()) {
              result.rejectValue(get(DATAFI), "typeMismatch.java.sql.Timestamp",
                  new String[] { I18NUtils.tradueix(get(DATAFI)) }, null);
            }
          }
        }
      }
    }
    
 
    final boolean isDebug = log.isDebugEnabled();
    
    if (isDebug) {
      List<Long> noseleccionats = form.getAvailableTipusDocument();
    
      if (noseleccionats == null) {
        log.info(" +++++++++++  noseleccionats List= NULL");
      } else {
        log.info(" +++++++++++  noseleccionats Size() = " + noseleccionats.size());
      }
    }
    
    

    // Validar Tipus de Document
    final List<Long> tipusSeleccionats = form.getCurrentTipusDocument();
    final int type = form.getTipus();
    
    if (isDebug) {
      log.info(" ----------  CurrentTipusDocument List= " + tipusSeleccionats + "  -----------");
      log.info(" ----------  type= " + type  + "  -----------");
    }
    Set<TipusDocumentColaboracioDelegacioJPA> tipusPerColaDele;
    tipusPerColaDele = new HashSet<TipusDocumentColaboracioDelegacioJPA>();
    if (tipusSeleccionats == null || tipusSeleccionats.size() == 0 || type == 1) {
      // No fer res, no s'ha seleccionat cap tipus
    } else {
      if (isDebug) {
        log.info(" ----------  CurrentTipusDocument List Size= "
           + tipusSeleccionats.size() + "  -----------");
      }
      if (colaboracioDelegacioForm.isNou()) {
        // === NOU
        for (Long tip : tipusSeleccionats) {
          if (isDebug) {
            log.info(" Selected tipus document = " + tip);
          }
          TipusDocumentColaboracioDelegacioJPA tipusColaDele;
          tipusColaDele = new TipusDocumentColaboracioDelegacioJPA();

          tipusColaDele.setTipusDocumentID(tip);

          tipusPerColaDele.add(tipusColaDele);
        }

      } else {
        // === EDICIO

        // Llegim els tipus actuals en BBDD
        Set<TipusDocumentColaboracioDelegacioJPA> tipus;
        tipus = colaboracioDelegacioJPA.getTipusDocumentColaboracioDelegacios();

        Map<Long, TipusDocumentColaboracioDelegacioJPA> currentTipusDocument = new HashMap<Long, TipusDocumentColaboracioDelegacioJPA>();
        for (TipusDocumentColaboracioDelegacioJPA t : tipus) {
          currentTipusDocument.put(t.getTipusDocumentID(), t);
        }

        // Miram quins es mantenen, quins s'han eliminat i quins s'han creat
        Long id = colaboracioDelegacioForm.getColaboracioDelegacio()
            .getColaboracioDelegacioID();

        for (Long tip : tipusSeleccionats) {
          if (isDebug) {
            log.info(" Selected tipus document = " + tip);
          }

          if (currentTipusDocument.containsKey(tip)) {
            // Ja existeix
            tipusPerColaDele.add(currentTipusDocument.get(tip));
          } else {
            // Nou
            TipusDocumentColaboracioDelegacioJPA tipusColaDele;
            tipusColaDele = new TipusDocumentColaboracioDelegacioJPA();

            tipusColaDele.setColaboracioDelegacioID(id);
            tipusColaDele.setTipusDocumentID(tip);

            tipusPerColaDele.add(tipusColaDele);
          }
        }

      }

    }

    colaboracioDelegacioForm.getColaboracioDelegacio().setTipusDocumentColaboracioDelegacios(
        tipusPerColaDele);

  }

  @Override
  public ColaboracioDelegacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long colaboracioDelegacioID)
      throws I18NException {

    ColaboracioDelegacioJPA colaboracioDelegacio = colaboracioDelegacioLogicaEjb
        .findByPrimaryKeyFull(colaboracioDelegacioID);

    try {
      Set<TipusDocumentColaboracioDelegacioJPA> tipus;
      tipus = colaboracioDelegacio.getTipusDocumentColaboracioDelegacios();

      if (log.isDebugEnabled()) {
        if (tipus == null || tipus.size() == 0) {
          log.info(" DDC::findByPrimaryKey TIPUS esta BUIT: " + tipus);
        } else {
          log.info(" DDC::findByPrimaryKey TIPUS conté: " + +tipus.size());
        }
      }

    } catch (Exception e) {
      log.warn("Error checking tipus de document" + e.getMessage(), e);
    }

    return colaboracioDelegacio;

  }

  @Override
  public ColaboracioDelegacioJPA create(HttpServletRequest request, 
      ColaboracioDelegacioJPA colaboracioDelegacio)
      throws I18NException {
    return colaboracioDelegacioLogicaEjb.createFull(colaboracioDelegacio);
  }

  @Override
  public void delete(HttpServletRequest request, ColaboracioDelegacio colaboracioDelegacio)
      throws I18NException {

    Set<Long> fitxers = colaboracioDelegacioLogicaEjb
        .deleteFull((ColaboracioDelegacioJPA) colaboracioDelegacio);

    borrarFitxers(fitxers);
  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, 
      ColaboracioDelegacioForm colaboracioDelegacioForm) {
    
    if (esDelegat()) {
      // Anam a la pàgina de Firma
      return "redirect:" + getContextWeb() + "/firmarautoritzacio/" 
          + colaboracioDelegacioForm.getColaboracioDelegacio().getColaboracioDelegacioID();
    } else {
      enviarNotificacioMailColaDele(request, colaboracioDelegacioForm.getColaboracioDelegacio());
      
      
      return "redirect:" + getContextWeb() + "/"
          + colaboracioDelegacioForm.getColaboracioDelegacio().getColaboracioDelegacioID()
          + "/edit";
    }
  }


  public void enviarNotificacioMailColaDele(HttpServletRequest request,
      ColaboracioDelegacio coladele) {
    final String entitatID = LoginInfo.getInstance().getEntitatID();
    // El col·laborador s'activa directament
    if (PropietatGlobalUtil.isSendNotificationWhenCreateDelegacioColaboracio(entitatID) && !esDeCarrec()) {

      
      UsuariEntitatJPA coladeleUser = usuariEntitatLogicaEjb.findByPrimaryKeyFull(coladele.getColaboradorDelegatID());
      String email_coladele = (coladeleUser.getEmail() == null)? coladeleUser.getUsuariPersona().getEmail() : coladeleUser.getEmail();
      
      EmailInfo email = new EmailInfo();
      email.setHtml(true);
      // Això evita que ho torni a guardar per enviar més endavant 
      email.setUsuariEntitatID(coladele.getColaboradorDelegatID());
      email.setEventID(-1);
      // PortaFIB: Ara és {0} de {1}
      UsuariEntitatJPA usuDest = usuariEntitatLogicaEjb.findByPrimaryKeyFull(coladele.getDestinatariID());
      String destNom = usuDest.getUsuariPersona().getNom() + " " + usuDest.getUsuariPersona().getLlinatges();
      String tipus = I18NUtils.tradueix(getEntityNameCode());
      email.setSubject(I18NUtils.tradueix("email.delecola.titol", tipus, destNom));
      //Bones:<br/> Voliem informar-li que a partir del dia {0} serà {3} de {1}, per més informació pot accedir a la següent adreça <a href=\"{2}\">{2}</a>.
      
      
      SimpleDateFormat sdf;
      sdf = new I18NDateTimeFormat().getSimpleDateFormat(new Locale(coladeleUser.getUsuariPersona().getIdiomaID()));
      
      String dia = sdf.format(coladele.getDataInici());

      String urlList = PropietatGlobalUtil.getAppUrl() + "/" + (esDelegat()?"dele/delegatde":"cola/colaboradorde") + "/list";
      
      email.setMessage(I18NUtils.tradueix("email.delecola.msg", dia, destNom, urlList, tipus));
      email.setEmail(email_coladele);
      
      
      try {
        EmailUtil.enviarMails(Arrays.asList(email));
      } catch (I18NException e) {
        String missatge = I18NUtils.getMessage(e);
        HtmlUtils.saveMessageError(request, missatge);
      }
    }
  }

  @Override
  public ColaboracioDelegacioJPA update(HttpServletRequest request, ColaboracioDelegacioJPA colaboracioDelegacio)
      throws I18NException {
    if (log.isDebugEnabled()) {
      try {
        Set<TipusDocumentColaboracioDelegacioJPA> tipus;
        tipus = colaboracioDelegacio.getTipusDocumentColaboracioDelegacios();

        if (log.isDebugEnabled()) {
          if (tipus == null || tipus.size() == 0) {
            log.debug(" +++++ UPDATE TIPUS esta BUIT: " + tipus);
          } else {
            log.debug(" +++++ UPDATE TIPUS conté: " + +tipus.size());
          }
        }

      } catch (Exception e) {
        log.warn("Error checking tipus de document" + e.getMessage(), e);
      }
    }

    return (ColaboracioDelegacioJPA) colaboracioDelegacioLogicaEjb
        .updateFull(colaboracioDelegacio);
  }

  @RequestMapping(value = "/desactivar/{delegacioID}", method = RequestMethod.POST)
  public ModelAndView desactivar(@PathVariable("delegacioID") Long delegacioID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/" + delegacioID
        + "/edit", true));

    String motiu = request.getParameter("motiu");

    if (motiu == null || motiu.trim().length() < 3) {
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("notfoundmotiudeshabilitat"));
      return mav;
    }

    ColaboracioDelegacioJPA delegacio;
    delegacio = colaboracioDelegacioEjb.findByPrimaryKey(delegacioID);

    if (delegacio == null) {
      createMessageError(request, "error.notfound", delegacioID);
      return mav;
    }

    delegacio.setActiva(false);
    delegacio.setMotiuDeshabilitada(motiu);
    colaboracioDelegacioEjb.update(delegacio);

    return mav;

  }

  @RequestMapping(value = "/activar/{delegacioColaboracioID}", method = RequestMethod.GET)
  public ModelAndView activarColaboracioDelegacio(
      @PathVariable("delegacioColaboracioID") Long delegacioColaboracioID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    ColaboracioDelegacioJPA deleColaJPA;
    deleColaJPA = colaboracioDelegacioLogicaEjb.findByPrimaryKeyFull(delegacioColaboracioID);

    if (deleColaJPA == null || deleColaJPA.isActiva()) {
      createMessageError(request, "error.notfound", delegacioColaboracioID);
      ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

    if (deleColaJPA.isEsDelegat() && deleColaJPA.getFitxerAutoritzacioID() == null) {
      createMessageError(request, "error.notfound", delegacioColaboracioID);
      ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

    long now = System.currentTimeMillis();
    if (deleColaJPA.getDataInici().getTime() < now) {
      deleColaJPA.setDataInici(new Timestamp(now));
    }

    deleColaJPA.setActiva(true);

    colaboracioDelegacioEjb.update(deleColaJPA);

    ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/"
        + delegacioColaboracioID + "/edit", true));
    return mav;

  }
  
  
  
  
  
  @RequestMapping(value = "/existsautoritzacio/{delegacioID}", method = RequestMethod.GET)
  public void checkAutoritzacio(
      @PathVariable("delegacioID") Long delegacioID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {
    
    
    Long count = colaboracioDelegacioLogicaEjb.count(
        Where.AND(
            COLABORACIODELEGACIOID.equal(delegacioID),
            FITXERAUTORITZACIOID.isNotNull()
            ));
    
    if (count == 0) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } else {
      try {
        response.getOutputStream().write("OK".getBytes());
      } catch (IOException e) {
      }
      response.setStatus(HttpServletResponse.SC_OK);
    }
        
  
  }

  public static final String FITXER_AUTORITZACIO_PREFIX = "FitxerAutoritzacioDelegacio_";

  @RequestMapping(value = "/firmarautoritzacio/{delegacioID}", method = RequestMethod.GET)
  public ModelAndView firmarAutoritzacioDelegacio(
      @PathVariable("delegacioID") Long delegacioID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {
    

    ColaboracioDelegacioJPA delegacio;
    delegacio = colaboracioDelegacioLogicaEjb.findByPrimaryKeyFull(delegacioID);

    if (delegacio == null || !delegacio.isEsDelegat()
        || delegacio.getFitxerAutoritzacioID() != null) {
      createMessageError(request, "error.notfound", delegacioID);
      ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

    // Llistar documents
    Set<TipusDocumentColaboracioDelegacioJPA> tipusDocPerDelegacio = delegacio
        .getTipusDocumentColaboracioDelegacios();

    List<Long> llistaTipusDocID = new ArrayList<Long>(tipusDocPerDelegacio.size());
    for (TipusDocumentColaboracioDelegacioJPA t : tipusDocPerDelegacio) {
      llistaTipusDocID.add(t.getTipusDocumentID());
    }

    Where where = TipusDocumentFields.TIPUSDOCUMENTID.in(llistaTipusDocID);

    List<StringKeyValue> allTipusDocumentList;
    allTipusDocumentList = tipusDocumentRefList.getReferenceList(
        TipusDocumentFields.TIPUSDOCUMENTID, where);

    String documents;
    if (allTipusDocumentList.size() == 0) {
      documents = I18NUtils.tradueix("tots");
    } else {
      StringBuffer docs = new StringBuffer();
      for (StringKeyValue stringKeyValue : allTipusDocumentList) {
        if (docs.length() != 0) {
          docs.append(", ");
        }
        docs.append(stringKeyValue.value);
      }
      documents = docs.toString();
    }

    UsuariEntitatJPA destinatari;
    destinatari = usuariEntitatLogicaEjb.findByPrimaryKeyFull(delegacio.getDestinatariID());
    UsuariEntitatJPA delegat;
    delegat = usuariEntitatLogicaEjb.findByPrimaryKeyFull(delegacio.getColaboradorDelegatID());

    // TODO Check destinatari i delegat

    // Llegim plantilla i la guardam amb reemplaç
    FitxerJPA plantilla = LoginInfo.getInstance().getEntitat().getPdfAutoritzacioDelegacio();

    File plantillaPdf = FileSystemManager.getFile(plantilla.getFitxerID());

    // TODO Check Plantilla

    final File base = FileSystemManager.getFilesPath();
    File dstPDF = new File(base, FITXER_AUTORITZACIO_PREFIX + delegacioID + ".pdf");
    if (dstPDF.exists()) {
      dstPDF.delete();
    }
    dstPDF.deleteOnExit();

    try {
    PdfReader reader = new PdfReader(new FileInputStream(plantillaPdf));
    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dstPDF), '\0', false);
    AcroFields fields = stamper.getAcroFields();

    String nomDest = Utils.getOnlyNom(destinatari.getUsuariPersona());

    fields.setField("DESTINATARI.NOM", nomDest);

    fields.setField("DESTINATARI.NIF", destinatari.getUsuariPersona().getNif());

    fields.setField("DELEGAT.NOM", Utils.getOnlyNom(delegat.getUsuariPersona()));

    fields.setField("DELEGAT.NIF", delegat.getUsuariPersona().getNif());

    SimpleDateFormat sdf = new SimpleDateFormat(I18NUtils.getDateTimePattern());

    fields.setField("DATA_INICI", sdf.format(new Date(delegacio.getDataInici().getTime())));

    fields.setField("DATA_FI",
        delegacio.getDataFi() == null ? I18NUtils.tradueix("genapp.notdefined") : sdf
            .format(new Date(delegacio.getDataFi().getTime())));

    fields.setField("MOTIU", delegacio.getMotiu());

    fields.setField("DOCUMENTS", documents);

    fields.setField("DEST", nomDest);

    fields.setField("DATA", sdf.format(new Date()));

    stamper.setFormFlattening(true);

    stamper.close();
    
    } catch(Exception e) {
      log.error(e.getMessage(), e);
      // TODO traduir
      throw new I18NException("error.unknown", "Error omplint formulari d´autorització:" + e.getMessage());
    }
    
    
    LoginInfo loginInfo = LoginInfo.getInstance();
    
    final String idname = plantilla.getNom();

    final long location_sign_table = ConstantsV2.TAULADEFIRMES_SENSETAULA;
    final String reason = I18NUtils.tradueix("delegacio.autoritzar");
    // XYZ TODO FALTA
    final String location = null;
    
    final String signerEmail = loginInfo.getUsuariPersona().getEmail();
    
    final int sign_number = 1;
    final String langUI = loginInfo.getUsuariPersona().getIdiomaID();
    

    final String signaturesSetID = "" + SignatureModuleController.generateUniqueSignaturesSetID();

    // Posam l'ID de la delegacio
    final String signatureID = String.valueOf(delegacioID);
    
    
    
    // Per les delegacions si es posible empram TimeStamp
    EntitatJPA entitat = loginInfo.getEntitat();
    boolean userRequiresTimeStamp;
    if (entitat.getPoliticaSegellatDeTemps() == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR) {
      userRequiresTimeStamp = false;
    } else {
      userRequiresTimeStamp = true;
    }

    ITimeStampGenerator timeStampGenerator = PortaFIBTimeStampGenerator.getInstance(
        segellDeTempsEjb, entitat,userRequiresTimeStamp);

    FileInfoSignature fis = SignatureUtils.getFileInfoSignature(signatureID,
        dstPDF, FileInfoSignature.PDF_MIME_TYPE, idname,
        (int)location_sign_table,  reason, location, signerEmail,  sign_number, 
        langUI, ConstantsV2.TIPUSFIRMA_PADES, entitat.getAlgorismeDeFirmaID(),
        ConstantsV2.SIGN_MODE_IMPLICIT,
        SignatureUtils.getFirmatPerFormat(loginInfo.getEntitat(), langUI), timeStampGenerator);

    FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[] { fis };

    
    CommonInfoSignature commonInfoSignature;
    {
      final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
      final String administrationID = loginInfo.getUsuariPersona().getNif();
      commonInfoSignature = SignatureUtils.getCommonInfoSignature(entitat, 
          langUI, username, administrationID);
    }

    // Vuls suposar que abans de 10 minuts haurà firmat
    Calendar caducitat = Calendar.getInstance();
    caducitat.add(Calendar.MINUTE, 10);
    
    // {0} ==> es substituirà per l'ID del plugin de firma seleccionat per firmar
    String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(request, getContextWeb());
    final String urlFirmaFinal = relativeControllerBase + "/finalFirma/" + signaturesSetID;

    // Sabem que no té cap firma 
    final int[] originalNumberOfSignsArray = new int[] {0};
    
    PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
        caducitat.getTime(),  commonInfoSignature, fileInfoSignatureArray,
        originalNumberOfSignsArray, entitat, urlFirmaFinal, true);

    signaturesSet.setPluginsFirmaBySignatureID(null);
    
    HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("firmardelegacio.titol")); 
    //"<b>Firmar Autorització</b> Ara ha de firmar l´autorització de delegació. Sense aquest pas no es completarà aquesta delegació. Si vol, pot deixar aquest pas per més endavant però fins que no firmi aquesta delegació, aquesta no s´activarà.");


    final String view = "PluginDeFirmaContenidor_ROLE_DEST";
    ModelAndView mav = SignatureModuleController.startPrivateSignatureProcess(request, view, signaturesSet);

    return mav;
  }


  @RequestMapping(value = "/finalFirma/{signaturesSetID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("signaturesSetID") String signaturesSetID)throws Exception,I18NException {

  SignaturesSetWeb ss;
  ss = SignatureModuleController.getSignaturesSetByID(request, signaturesSetID, modulDeFirmaEjb);

  StatusSignaturesSet sss = ss.getStatusSignaturesSet();
  
  StatusSignaturesSet statusError = null;
  
  switch(sss.getStatus()) {
  
    case StatusSignaturesSet.STATUS_FINAL_OK:
      {
        // Revisam la primera i unica firma
        StatusSignature status = ss.getFileInfoSignatureArray()[0].getStatusSignature();
        // TODO check null
        
        if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {
          
          FileInfoSignature signFileInfo = ss.getFileInfoSignatureArray()[0];
          
          Long delegacioID = new Long(signFileInfo.getSignID());

          try {
            /*
            File firmat = null;

            firmat = File.createTempFile(DelegacioDestController.FITXER_AUTORITZACIO_PREFIX
                + "_Firmat_" + delegacioID, ".pdf", FileSystemManager.getFilesPath());
            firmat.deleteOnExit();

            
            FileOutputStream fos = new FileOutputStream(firmat);
            fos.write(status.getSignedData());
            fos.close();
            */
            File firmat = status.getSignedData();

            if(log.isDebugEnabled()) {
              log.debug("Ruta Fitxer Firmat: " + firmat.getAbsolutePath());
            }

            colaboracioDelegacioLogicaEjb.assignarAutoritzacioADelegacio(delegacioID, firmat,
                DelegacioDestController.FITXER_AUTORITZACIO_PREFIX + delegacioID + ".pdf");
            
            // Enviar mail a delegat o col·laborador
            enviarNotificacioMailColaDele(request, colaboracioDelegacioEjb.findByPrimaryKey(delegacioID));

          } catch (Throwable e) {
            log.error(" CLASS = " + e.getClass());
            String msg;
            if (e instanceof I18NException) {
              I18NException i18ne = (I18NException)e;
              msg = I18NUtils.getMessage(i18ne);
              log.error("Error processant fitxer firmat (I18NException): " + msg, e);
            } else {
              msg = e.getMessage();
              log.error("Error processant fitxer firmat (Throwable): " + msg , e);
            }

            //  TODO Traduir
            String fullMsg = "S´ha produit un error processant el fitxer firmat ´" + 
                signFileInfo.getName() + "´: " + msg;
            
            HtmlUtils.saveMessageError(request, fullMsg);

          }
          
          status.setProcessed(true);
        } else {
          statusError = status;
        }
      }
      
    
    break;
    
    case StatusSignaturesSet.STATUS_FINAL_ERROR:
      
      statusError = sss;
    break;
    
    
    case StatusSignaturesSet.STATUS_CANCELLED:
      if (sss.getErrorMsg() == null) {
        sss.setErrorMsg(I18NUtils.tradueix("plugindefirma.cancelat"));
      }
      statusError = sss;
    break;
      
    default:
      String inconsistentState = "El mòdul de firma ha finalitzat inesperadament "
          + "(no ha establit l'estat final del procés de firma)";
      sss.setErrorMsg(inconsistentState);
      statusError = sss;
      log.error(inconsistentState, new Exception());
  
  }
  
   
  
  if ( statusError != null) {      
    // TODO Mostrar excepció per log
    if (statusError.getErrorMsg() == null) {
      statusError.setErrorMsg("Error desconegut ja que no s'ha definit el missatge de l'error !!!!!");
    }
    HtmlUtils.saveMessageError(request, statusError.getErrorMsg());
  }
 
  
  SignatureModuleController.closeSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
 
  ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
  return mav;
  
}

  
 

  @RequestMapping(value = "/source/{id}", method = RequestMethod.GET)
  public void source(@PathVariable("id") Long id, HttpServletResponse response)
      throws Exception {

    final File base = FileSystemManager.getFilesPath();
    File dstPDF = new File(base, FITXER_AUTORITZACIO_PREFIX + id + ".pdf");
    dstPDF.deleteOnExit();

    response.setContentType(ConstantsV2.PDF_MIME_TYPE);
    response.setHeader("Content-Disposition", "inline; filename=\"" + dstPDF.getName() + "\"");
    response.setContentLength((int) dstPDF.length());

    java.io.OutputStream output = response.getOutputStream();
    InputStream input = new FileInputStream(dstPDF);

    FileSystemManager.copy(input, output);

    input.close();
  }

  

  
  /**
   * 
   * @author anadal
   *
   */
  public static class ValueComparator implements  Comparator<StringKeyValue> {

    @Override
    public int compare(StringKeyValue o1, StringKeyValue o2) {
      return o1.getValue().compareToIgnoreCase(o2.getValue());
    }
    
  }

}
