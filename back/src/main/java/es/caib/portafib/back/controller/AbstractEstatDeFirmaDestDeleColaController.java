package es.caib.portafib.back.controller;

  import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
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
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.utils.PortaFIBTimeStampGenerator;
import es.caib.portafib.back.controller.FileDownloadController;
import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.controller.webdb.EstatDeFirmaController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentRefList;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
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
import es.caib.portafib.logic.ModulDeFirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaEJB.Token;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioQueryPath;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields;
import es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Configuracio;

  /**
   * Controller per gestionar un EstatDeFirma
   * 
   * @author anadal
   */
  @Controller  
  @SessionAttributes(types = { EstatDeFirmaFilterForm.class })
  public abstract class AbstractEstatDeFirmaDestDeleColaController extends EstatDeFirmaController implements
      EstatDeFirmaFields, Constants {

    @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = "portafib/ColaboracioDelegacioLogicaEJB/local")
    protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioEjb;

    @EJB(mappedName = "portafib/FirmaEJB/local")
    protected FirmaLocal firmaEjb;

    @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @EJB(mappedName = ModulDeFirmaLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaLogicaLocal modulDeFirmaEjb;
    
    @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
    protected SegellDeTempsLogicaLocal segellDeTempsEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.AnnexLocal.JNDI_NAME)
    protected es.caib.portafib.ejb.AnnexLocal annexEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal.JNDI_NAME)
    protected es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal modulDeFirmaPerTipusDeDocumentEjb;


    final Long[] ESTATS_INICIALS_COLA = new Long[] {
        Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
        Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR };

    final Long[] ESTATS_INICIALS_DELE = new Long[] { Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };

    // References

    @Autowired
    protected UsuariPersonaRefList usuariPersonaRefList;

    @Autowired
    protected TipusDocumentRefList tipusDocumentRefList;
    
    private static final int COLUMN_PETICIODEFIRMA = -3;
    
    private static final StringField COLUMN_PETICIODEFIRMA_FIELD;

    private static final int COLUMN_TIPUS_DOC = -2;
    
    private static final LongField COLUMN_TIPUS_DOC_FIELD;
    
    private static final int COLUMN_DATA_INICI_SHORT = -1;

    private static final int COLUMN_REMITENT = 1;
    
    private static final StringField COLUMN_REMITENT_FIELD;
    
    private static final int COLUMN_DELEGAT_DE_COLABORADOR = 2;
    
    private static final int COLUMN_DELEGATS_DE_DESTINATARI = 3;
    
    private static final int COLUMN_COLABORADORS = 4;
    
    private static final int COLUMN_PRIORITAT = 5;
    
    private static final IntegerField COLUMN_PRIORITAT_FIELD;
    
    
    // Propietat de Col.laboracio-Delegacio
    private final static StringField DESTINATARIID;
    
    static {
      
      PeticioDeFirmaQueryPath pfqp;
      pfqp = new EstatDeFirmaQueryPath().FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();
      
      COLUMN_PETICIODEFIRMA_FIELD = pfqp.TITOL();

      COLUMN_TIPUS_DOC_FIELD = pfqp.TIPUSDOCUMENTID();

      COLUMN_REMITENT_FIELD = pfqp.REMITENTNOM();
          
      COLUMN_PRIORITAT_FIELD = pfqp.PRIORITATID();
      
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
        ff.addHiddenField(DATAINICI);
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
            new OrderBy(COLUMN_PRIORITAT_FIELD, OrderType.DESC), 
            new OrderBy(DATAINICI, OrderType.DESC),

        });

        ff.addGroupByField(COLUMN_TIPUS_DOC_FIELD); // Propietat de Peticio De Firma
        if (getRole().equals(Constants.ROLE_COLA)) {
          // Propietat de Col.laboracio-Delegacio
          ff.addGroupByField(DESTINATARIID);
        }

        
        ff.addGroupByField(DATAINICI);
        ff.addGroupByField(DATAFI);
        ff.addGroupByField(TIPUSESTATDEFIRMAFINALID);
        
        switch(getFilterType()) {
        
          case FILTRAR_PER_PENDENT: // Pendent de firma o validacio
            ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
            ff.addHiddenField(DATAFI);

            ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
            ff.getGroupByFields().remove(DATAFI);
          break;
          
          case FILTRAR_PER_NOACCEPTAT: // Rebutjat o invalidat
            // Es la forma d'indicar que no volem la columna de Col·laborador
            ff.addHiddenField(ColaboracioDelegacioFields.DESTINATARIID);
            // Descripció conté el motiu de rebuig
            ff.getHiddenFields().remove(DESCRIPCIO);
            ff.addLabel(DESCRIPCIO, "motiurebutjar");

            ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
            ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
          break;
          
          case FILTRAR_PER_NODEFINIT: // Descartat o ni validat ni invalidat
          case FILTRAR_PER_ACCEPTAT:   // Firmat o validat
            ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
            ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);

          break;
          
          
          default:
        }

        //  NOVES COLUMNES

        // ===================  Nom de petició de firma
        AdditionalField<String,String> addfieldPF = new AdditionalField<String,String>(); 
        addfieldPF.setCodeName("document");
        addfieldPF.setPosition(COLUMN_PETICIODEFIRMA);
        // Els valors s'ompliran al mètode postList()
        addfieldPF.setValueMap(new HashMap<String, String>());
        addfieldPF.setOrderBy(COLUMN_PETICIODEFIRMA_FIELD);
        
        addfieldPF.setSearchBy(COLUMN_PETICIODEFIRMA_FIELD);

        ff.addAdditionalField(addfieldPF);

        
        // ===================  tipus de document 
        {
        AdditionalField<String,String> adfieldTD = new AdditionalField<String,String>(); 
        adfieldTD.setCodeName("tipus");
        adfieldTD.setPosition(COLUMN_TIPUS_DOC);
        // Els valors s'ompliran al mètode postList()
        adfieldTD.setValueMap(new HashMap<String, String>());
        adfieldTD.setOrderBy(COLUMN_TIPUS_DOC_FIELD);
        
        ff.addAdditionalField(adfieldTD);
        }
        
        // ===================  data inici (format curt)
        {
        AdditionalField<String,String> adfieldDI = new AdditionalField<String,String>(); 
        adfieldDI.setCodeName("datainici.short");
        adfieldDI.setPosition(COLUMN_DATA_INICI_SHORT);
        // Els valors s'ompliran al mètode postList()
        adfieldDI.setValueMap(new HashMap<String, String>());
        adfieldDI.setOrderBy(DATAINICI);

        
        ff.addAdditionalField(adfieldDI);
        }

        // =================== 
        final String role = getRole();
        if (role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) {

          // NOVA COLUMNA
          AdditionalField<String,String> adfieldRN = new AdditionalField<String,String>(); 
          adfieldRN.setCodeName("peticioDeFirma.remitentNom");
          adfieldRN.setPosition(COLUMN_REMITENT);
          adfieldRN.setEscapeXml(false);
          // Els valors s'ompliran al mètode postList()
          adfieldRN.setValueMap(new HashMap<String, String>());
          adfieldRN.setOrderBy(COLUMN_REMITENT_FIELD);
          adfieldRN.setSearchBy(COLUMN_REMITENT_FIELD);

          ff.addAdditionalField(adfieldRN);

          // NOVA AGRUPACIO
          ff.addGroupByField(COLUMN_REMITENT_FIELD);
        }

        
        // ======================

        if (role.equals(Constants.ROLE_COLA)) {

          // NOVA COLUMNA
          AdditionalField<String,String> adfieldDC = new AdditionalField<String,String>(); 
          adfieldDC.setCodeName("colaboracioDelegacio.destinatariID");
          adfieldDC.setPosition(COLUMN_DELEGAT_DE_COLABORADOR);
          adfieldDC.setEscapeXml(false);
          // Els valors s'ompliran al mètode postList()
          adfieldDC.setValueMap(new HashMap<String, String>());
          ff.addAdditionalField(adfieldDC);

        }
        
        // =========================

        // NOVA COLUMNA: Prioritat
        AdditionalField<String,String> adfieldPR = new AdditionalField<String,String>(); 
        adfieldPR.setCodeName("=<i class=\"icon-warning-sign\" title=\"" 
            + I18NUtils.tradueix(PeticioDeFirmaFields.PRIORITATID.fullName) + "\"></i>");
        adfieldPR.setPosition(COLUMN_PRIORITAT);
        adfieldPR.setEscapeXml(false);
        // Els valors s'ompliran al mètode postList()
        adfieldPR.setValueMap(new HashMap<String, String>());
        adfieldPR.setOrderBy(COLUMN_PRIORITAT_FIELD);
        
        ff.addAdditionalField(adfieldPR);
        
        // ===================== BOTONS =================
        
        if (getFilterType() == FILTRAR_PER_RES || getFilterType() == FILTRAR_PER_PENDENT) {
          ff.setActionsRenderer(EstatDeFirmaFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);
        } else {
          ff.setActionsRenderer(EstatDeFirmaFilterForm.ACTIONS_RENDERER_SIMPLE_ICON_LIST);
        }
        
        
        // altres comandes
        ff.setAttachedAdditionalJspCode(true);

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
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLUMN_TIPUS_DOC_FIELD, false);
      }

      return groupByItemsMap;
    }


    @RequestMapping(value = "/final", method = RequestMethod.GET)
    public String finalRequest(HttpServletRequest request, HttpServletResponse response)
        throws I18NException {
      return "redirect:" + getContextWeb() + "/list";
    }
    
    
    @RequestMapping(value = "/estatdelesfirmes/{firmesid}", method = RequestMethod.GET)
    public void estatdelesfirmes(HttpServletRequest request, HttpServletResponse response,
        @PathVariable String firmesid) throws I18NException {

      final boolean isDebug = log.isDebugEnabled();
      
      if (isDebug) {
        log.debug("========= Estat de les firmes = ]" +firmesid + "[");
      }
      
      String[] firmaIdArray = firmesid.split(",");
      
      
      Long count;
      for(int x = 0; x < firmaIdArray.length; x++) {
        if (isDebug) {
          log.debug("Check firma amb id =]" +firmaIdArray[x] + "[");
        }
        count = estatDeFirmaEjb.count(Where.AND(
             ESTATDEFIRMAID.equal(Long.parseLong(firmaIdArray[x])),
             TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_FIRMAT)
             ));
        
        if (count == 0) {
          response.setStatus(HttpServletResponse.SC_NO_CONTENT);
          return;
        }
      }
      
      // Si totes les firmes estan firmades OK
      try {
        response.getOutputStream().write("OK".getBytes());
      } catch (IOException e) {
      }
      response.setStatus(HttpServletResponse.SC_OK);
    }
    
    
    @RequestMapping(value = "/firmarseleccionats/Firmar.jnlp", method = RequestMethod.POST)
    public ModelAndView firmarSeleccionatsJNLP(HttpServletRequest request,
        @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {

      return firmarseleccionatscommon(request, filterForm, true);

    }
    
    

    @RequestMapping(value = "/firmarseleccionats", method = RequestMethod.POST)
    public ModelAndView firmarSeleccionats(HttpServletRequest request,
        @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {

      return firmarseleccionatscommon(request, filterForm,false);

    }
    
   

    private ModelAndView firmarseleccionatscommon(HttpServletRequest request,
        EstatDeFirmaFilterForm filterForm, boolean  isJnlp) throws I18NException {
      // seleccionats conté els estatIDs
      String[] seleccionatsStr = filterForm.getSelectedItems();
      // String role = filterForm.getRole();



      if (seleccionatsStr == null || seleccionatsStr.length == 0) {

        HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("firmarseleccionats.cap"));
        
        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      } 

      LoginInfo loginInfo = LoginInfo.getInstance();
      Integer maxFileToSignAtSameTime = loginInfo.getEntitat().getMaxFilesToSignAtSameTime();
      
      if (maxFileToSignAtSameTime == null || maxFileToSignAtSameTime < 1) {
        maxFileToSignAtSameTime = Integer.MAX_VALUE;
      } else {
        if (log.isDebugEnabled()) {
          log.debug("maxFileToSignAtSameTime = " + maxFileToSignAtSameTime);
        }
      }
        
        ArrayList<Long> seleccionats = new ArrayList<Long>();
        int min = Math.min(maxFileToSignAtSameTime, seleccionatsStr.length);
        for(int i = 0; i< min ; i++) {
           try {
            seleccionats.add(Long.parseLong(seleccionatsStr[i]));
          } catch(Throwable e) {
            log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
          }
        }
        
        
        EstatDeFirmaQueryPath efqp = new EstatDeFirmaQueryPath();
        
        
        
        
        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(ESTATDEFIRMAID.select, 
            efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID().select
            );
        
        List<StringKeyValue> listIds = estatDeFirmaEjb.executeQuery(smskv, ESTATDEFIRMAID.in(seleccionats));
        

        List<FileInfoSignature> fileInfoSignatureArray = new ArrayList<FileInfoSignature>();
        
        String langUI = loginInfo.getUsuariPersona().getIdiomaID();


        Set<Long> peticionsDeFirmaID = new HashSet<Long>();
        Map<String, Long> tipusDocBySignatureID = new HashMap<String, Long>();

        final boolean debug = log.isDebugEnabled();
        for (StringKeyValue skv: listIds ) {
          if (debug) {
            log.info("firmarSeleccionats::SELECCIONAT = " + skv.getKey() + " / "
              + skv.getValue());
          }

          Long estatDeFirmaID = new Long( skv.getKey());
          Long peticioDeFirmaID = new Long(skv.getValue());

          // Només permetre una firma per petició (evitar bloc de firmes amb una firma
          // usuari-entitat i una altra amb usuari càrrec)
          if (!peticionsDeFirmaID.contains(peticioDeFirmaID)) {

            try {
              checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
            } catch(I18NException e) {
              HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
              continue;
            }

            FileInfoSignature fileInfoSignature;
            fileInfoSignature = prepareFirmaItem(request, estatDeFirmaID, peticioDeFirmaID,
                langUI, tipusDocBySignatureID);

            if (fileInfoSignature != null) {
              fileInfoSignatureArray.add(fileInfoSignature);
            }

            peticionsDeFirmaID.add(peticioDeFirmaID);

          }
        }

        if (fileInfoSignatureArray.isEmpty()) {
          // TODO avis
          return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        }
        
        final String signaturesSetID = String.valueOf(SignatureModuleController.generateUniqueSignaturesSetID());

        CommonInfoSignature commonInfoSignature;
        {
          // {0} ==> es substituirà per l'ID del plugin de firma seleccionat per firmar
          String absoluteControllerBase = SignatureModuleController.getAbsoluteControllerBase(request, getContextWeb());

          
          final String urlFinal = absoluteControllerBase + "/finalFirma/" + signaturesSetID;

          final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
          commonInfoSignature = SignatureModuleController.getCommonInfoSignature(
              loginInfo.getEntitat(), langUI, username, urlFinal, !isJnlp);
        }
        
        // Vuls suposar que abans de "9 minuts més un minut per cada firma" haurà
        // finalitzat el proces de firma
        Calendar caducitat = Calendar.getInstance();
        caducitat.add(Calendar.MINUTE, 9 + fileInfoSignatureArray.size());
        


        PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
            caducitat.getTime(), commonInfoSignature,
            fileInfoSignatureArray.toArray(new FileInfoSignature[fileInfoSignatureArray.size()]));

        signaturesSet.setTipusDocBySignatureID(tipusDocBySignatureID);

        final String view = "PluginDeFirmaContenidor_" + getRole();
        ModelAndView mav = SignatureModuleController.startSignatureProcess(request, view, signaturesSet);
        
        
        // XYZ 
        // Posar en marxa un thread que vagi mirant les entrades i les processi 
        // En el mapping finalOK o finalError descartar les entrades ja processades

        return mav;
        
    }
    
    
    
    

    public abstract String getRole();
    
    public abstract String getBaseEntityNameCode();
    
    
    //              ID    DEST/DELE      COLA
    // -----------------------------------------------
    // ALL          -1    tots_estats    tots_estats 
    // PENDENT       1    pendent        pendent
    // ACCEPTAT      2    firmat         validat
    // NOACCEPTAT    4    rebutjat       invalidat
    // NODEFINIT     8    descartat(*)   descartat
    // TODO (*) En un futur el valor descartat per dest i dele no tindran sentit i les 
    //          delegacions firmades per un dest o les destinacions firmades per un delegat 
    //          serna tractades com l'estat final del la sol3licitud: firmada o rebutjada
    
    public static final int FILTRAR_PER_RES = -1;
    
    public static final int FILTRAR_PER_PENDENT = 1;
    
    public static final int FILTRAR_PER_ACCEPTAT = 2;
    
    public static final int FILTRAR_PER_NOACCEPTAT = 4;

    public static final int FILTRAR_PER_NODEFINIT = 8;
    
    
    public abstract int getFilterType();
    
    
    // TODO moure a EJB
    private void checkCanSignPeticioDeFirma(HttpServletRequest request, Long peticioDeFirmaID, Long estatDeFirmaID) throws I18NException {

      EstatDeFirma ef = checkEstatDeFirma(estatDeFirmaID, request, false);
     
      long firmaId = ef.getFirmaID();
      
      final boolean isDebug = log.isDebugEnabled();
      
      if (isDebug) {
        log.debug(" ---------- " + peticioDeFirmaID + " -----------");
      }
      
      // Cercar colaboradors-revisors que no han donat el vist i plau a la firma
      Where w1 = FIRMAID.equal(firmaId);
      if (isDebug) {
        log.debug("C1 = " + estatDeFirmaEjb.count(w1));
      }
      
      Where w2 = Where.OR(
          TIPUSESTATDEFIRMAINICIALID.equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR),
          TIPUSESTATDEFIRMAINICIALID.equal(Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR)
        );
      if (isDebug) {
        log.debug("C2 = " + estatDeFirmaEjb.count(Where.AND(w1,w2)));
      }
      
      Where w3 = Where.OR(
          TIPUSESTATDEFIRMAFINALID.isNull(),
          TIPUSESTATDEFIRMAFINALID.notEqual(Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT)
          );
      if (isDebug) {
        log.debug("C3 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3)));
      }
      
      Where w4 = COLABORACIODELEGACIOID.isNotNull();
      if (isDebug) {
        log.debug("C4 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3,w4)));
      }
      
      ColaboracioDelegacioQueryPath cdqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO();
      Where w5 = Where.AND(
          cdqp.REVISOR().equal(true),
          cdqp.ACTIVA().equal(true),
          cdqp.ESDELEGAT().equal(false) // Es col·laborador
        );
      if (isDebug) {
        log.debug("C5 = " + estatDeFirmaEjb.count(Where.AND(w1,w2,w3,w4,w5)));
      }
      

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

      ModelAndView mav;
      mav = commonFirma(request, response, estatDeFirmaID, peticioDeFirmaID, false);
      return mav;
    }
    
   
    
    @RequestMapping(value = "/firmar/{estatDeFirmaID}/{peticioDeFirmaID}/Firmar.jnlp",   method = RequestMethod.GET)
    public ModelAndView firmarJNLP(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID)
            throws I18NException {

      ModelAndView mav;
      mav = commonFirma(request, response, estatDeFirmaID, peticioDeFirmaID, true);
      return mav;
      
    }
    

    private ModelAndView commonFirma(HttpServletRequest request, HttpServletResponse response,
        Long estatDeFirmaID, Long peticioDeFirmaID,boolean isJnlp) throws I18NException {
      log.info("Entra a firmar Peticio = " + peticioDeFirmaID + " | EstatDeFirma = " + estatDeFirmaID);
      
      try {
        checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
      } catch(I18NException e) {
        HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
        return llistatPaginat(request, response, null);
      }
      
      LoginInfo loginInfo = LoginInfo.getInstance();
      
      final String langUI = loginInfo.getUsuariPersona().getIdiomaID();
      
      Map<String, Long> tipusDocBySignatureID = new HashMap<String, Long>();
      FileInfoSignature fis = prepareFirmaItem(request, estatDeFirmaID, 
          peticioDeFirmaID, langUI, tipusDocBySignatureID);

      FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[] { fis };

      EntitatJPA entitat = loginInfo.getEntitat();

      final String signaturesSetID= String.valueOf(peticioDeFirmaID + "_" + estatDeFirmaID);
      
      CommonInfoSignature commonInfoSignature;
      {
        
        String absoluteControllerBase = SignatureModuleController.getAbsoluteControllerBase(request, getContextWeb());
        
        
        final String urlFinal = absoluteControllerBase + "/finalFirma/" + signaturesSetID;
        
        final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
        commonInfoSignature = SignatureModuleController.getCommonInfoSignature(entitat, 
            langUI, username, urlFinal, !isJnlp);
      }
      
      // Vuls suposar que abans de 10 minuts haurà firmat
      Calendar caducitat = Calendar.getInstance();
      caducitat.add(Calendar.MINUTE, 10);
      

      PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID, caducitat.getTime(),
          commonInfoSignature, fileInfoSignatureArray);

      signaturesSet.setTipusDocBySignatureID(tipusDocBySignatureID);

      final String view = "PluginDeFirmaContenidor_AutoFirma";
      ModelAndView mav = SignatureModuleController.startSignatureProcess(request, view, signaturesSet);

      return mav;
    }
    
    
    
    /** 
     * Quan acaba el mòdul de firma
     * @param request
     * @param response
     * @param signaturesSetID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/finalFirma/{signaturesSetID}")
    public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
        @PathVariable("signaturesSetID") String signaturesSetID)throws Exception {
    
    
      SignaturesSet ss = SignatureModuleController.getSignaturesSetByID(signaturesSetID);

      StatusSignaturesSet sss = ss.getStatusSignaturesSet();
      
      StatusSignaturesSet statusError = null;
      
      switch(sss.getStatus()) {
      
        case StatusSignaturesSet.STATUS_FINAL_OK:
          {

            FileInfoSignature[] signedFiles = ss.getFileInfoSignatureArray();
            
            for (int i = 0; i < signedFiles.length; i++) {
            
              
              final FileInfoSignature signedFile = signedFiles[i];
              
              SignatureID signID = decodeSignatureID(signedFile.getSignID());
              
              final long estatDeFirmaID = signID.getEstatDeFirmaID();
              final long peticioDeFirmaID = signID.getPeticioDeFirmaID();
              final String token = signID.getToken();
              
              StatusSignature status = signedFile.getStatusSignature();
            
              switch(status.getStatus()) {
              
                  case StatusSignature.STATUS_FINAL_OK:
                    
                    File firmat = null;
                    try {
      
        
                      firmat = File.createTempFile(peticioDeFirmaID + "_" + estatDeFirmaID + "_Firma_Firmat_", ".pdf",
                          FileSystemManager.getFilesPath());
                      firmat.deleteOnExit();
        
                      FileOutputStream fos = new FileOutputStream(firmat);
                      fos.write(status.getSignedData());
                      fos.close();
        
                      if (log.isDebugEnabled()) {
                        log.debug("firmat.getAbsolutePath(): " + firmat.getAbsolutePath());
                      }
        
                      peticioDeFirmaLogicaEjb.nouFitxerFirmat(firmat, estatDeFirmaID, peticioDeFirmaID, token,
                          signedFile.getSignNumber());
        
                      log.debug("WWWWWWWWW FINAL ");
                      
                      status.setProcessed(true);
        
                    } catch (Throwable e) {
                      
                      if (firmat != null && firmat.exists()) {
                        if (!firmat.delete()) {
                          // TODO missatge d'error
                          firmat.deleteOnExit();
                        };
                      }
                      
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
                          signedFile.getName() + "´: " + msg;
                      
                      HtmlUtils.saveMessageError(request, fullMsg);
                      
                      status.setProcessed(true);
      
                    }
                    
                  break; // FINAL DE CAS FIRMAT
              
                  
                  case StatusSignature.STATUS_FINAL_ERROR:
                  {
                    // Mostrar excepció per log
                    // TODO traduir
                    String msg = "S´ha produit un error durant la firma del fitxer  ´" + 
                        signedFile.getName() + "´: " + status.getErrorMsg(); 
                    
                    if (status.getErrorException() == null) {
                      log.error(msg);
                    } else {
                      log.error(msg, status.getErrorException());
                    }
                    
                    
                    HtmlUtils.saveMessageError(request, msg);
                    
                    status.setProcessed(true);
                  }
                  break;
                  
                  default:
                  {
                    // TODO traduir
                    String msg = "Ha finalitzat el process de firma amb ID " + signaturesSetID 
                    + " però la firma del fitxer ´" +  signedFile.getName()
                    + "´ no està ni firmat ni té errors.";
                     
                    log.error(msg, new Exception());
                    
                    HtmlUtils.saveMessageWarning(request, msg);
                  }
              
              }
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
          // TODO Traduir
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

      SignatureModuleController.closeSignaturesSet(signaturesSetID, modulDeFirmaEjb);
     
      ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

    
    protected String encodeSignatureID(Long peticioDeFirmaID, Long estatDeFirmaID, String token) {
      return peticioDeFirmaID + "|" + estatDeFirmaID + "|" + token;
      
    }

    /**
     * [0] conte peticioDeFirmaID, [1] conte estatDeFirmaID i [2] conté token
     * @param encoded
     * @return
     */
    protected SignatureID decodeSignatureID(String encoded) {
      
      String[] parts = encoded.split("\\|");

      return new SignatureID(Long.parseLong(parts[0]),Long.parseLong(parts[1]),parts[2]);
    }
    

   

    protected FileInfoSignature prepareFirmaItem(HttpServletRequest request, Long estatDeFirmaID,
        Long peticioDeFirmaID, String langUI, Map<String, Long> tipusDocBySignatureID)
            throws I18NException {

        CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
            Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
        if (check == null) {
          // S'ha produit un error i retornam el control al llistat
          return null;
        }

        EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
        FirmaJPA firma = check.firma;
        PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;
        
        // Extreim número de firmes del bloc, si aquesta valor és 1 i no té delegats 
        // llavors el token té temps il·limitat per firmar.
        long timeAliveToken = -1;
        {
          Long countFirmesPerBloc = firmaEjb.count(
              FirmaFields.BLOCDEFIRMAID.equal(firma.getBlocDeFirmaID()));
          
          boolean isDebug=log.isDebugEnabled();
          if (isDebug) {
            log.debug("countFirmesPerBloc = " + countFirmesPerBloc);
          }
          if (countFirmesPerBloc == 1) {
            // Calcular delegats
            Long countFirmants = estatDeFirmaEjb.count(Where.AND(
                EstatDeFirmaFields.FIRMAID.equal(firma.getFirmaID()),
                EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(
                    Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR)
                ));
            if (isDebug) {
              log.debug(" countFirmants = " + countFirmants);
            }
            if (countFirmants == 1) { // No te delegats
              // Temps il·limitat (realment un dia sencer)
              timeAliveToken = Token.ONE_DAY_TIME_LOCKED_IN_MS;
              if (isDebug) {
                log.debug("TE TEMPS INFINIT ( UN DIA ) !!!!!");
              }
            }
          }
  
          if (timeAliveToken == -1) {
            timeAliveToken = Configuracio.getMaxTimeLockedSignInMs();
          }
        }

        LoginInfo loginInfo = LoginInfo.getInstance();

        // Crear un token i revisar si aquesta peticio esta bloquejada !!!
        String token = peticioDeFirmaLogicaEjb.lockPeticioDeFirma(peticioDeFirmaID,
            loginInfo.getUsuariEntitatID(), timeAliveToken);
        if (token == null) {
          new PeticioDeFirmaController().createMessageError(request, "error.peticiobloquejada",
              peticioDeFirmaID);
          return null;
        }

        // Preparar pàgina Applet
        FirmaJPA lastFirma = peticioDeFirmaLogicaEjb
            .getLastSignOfPeticioDeFirma(peticioDeFirmaID);
        final File source;
        if (lastFirma == null) {
          log.debug("No hi ha firmes. La font del document és el document original");
          source = FileSystemManager.getFile(peticioDeFirma.getFitxerAdaptatID());
        } else {
          if (log.isDebugEnabled()) {
            log.debug("La darrera firma és " + firma.getFirmaID() + " (#"
                + firma.getNumFirmaDocument() + ")");
          }
          source =  FileSystemManager.getFile(lastFirma.getFitxerFirmatID());
        }

        final int sign_number = lastFirma == null ? 1 : (lastFirma.getNumFirmaDocument() + 1);

        final long location_sign_table = peticioDeFirma.getPosicioTaulaFirmesID();
        
        String langSign = peticioDeFirma.getIdiomaID();
        if (langSign == null) {
          langSign = langUI;
        }
        
        EntitatJPA entitat = loginInfo.getEntitat();

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
              up.getNom() + " " + up.getLlinatges(), // {0} Nom del delegat
              up.getNif(),            // {1} NIF del delegat
              // Destinatari Original
              destUP.getNom() + " " + destUP.getLlinatges(),  // {2} Nom del destinatari
              destUP.getNif(),              // {3} NIF del destinatari
              colaDele.getMotiu(),          // {4} Motiu de la delegació
              peticioDeFirma.getMotiu(),    // {5} Motiu de la peticio de firma
              };
          String basemsg = es.caib.portafib.back.utils.Utils.getMotiuDeFirmaFormat(entitat, langSign);
          MessageFormat mf = new MessageFormat(basemsg);
          reason = mf.format(args);
        }
        
  
        
        
        // Construir Objecte

        final String idname = peticioDeFirma.getFitxerAFirmar().getNom();

        final String firmatPerFormat = es.caib.portafib.back.utils.Utils.getFirmatPerFormat(entitat, langSign);

       final String signatureID = encodeSignatureID(peticioDeFirmaID, estatDeFirmaID, token);
       
       // S'ha d'obtenir de la PeticioDeFirma
       boolean userRequiresTimeStamp = peticioDeFirma.isSegellatDeTemps();
       ITimeStampGenerator timeStampGenerator;
       timeStampGenerator = PortaFIBTimeStampGenerator.getInstance(segellDeTempsEjb, entitat, userRequiresTimeStamp);
       
       
       // Revisar TipusDoc per PlugindeFirma
       Long tipusDoc = peticioDeFirma.getTipusDocumentID(); 
       Where where = Where.AND(
           new ModulDeFirmaPerTipusDeDocumentQueryPath().PLUGIN().
             ENTITATID().equal(LoginInfo.getInstance().getEntitatID()),
             ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID.equal(tipusDoc)
             );
           
       
       // TODO Execute Query ONE
       Long pluginID = modulDeFirmaPerTipusDeDocumentEjb.executeQueryOne(
           ModulDeFirmaPerTipusDeDocumentFields.PLUGINID.select, where);
       if (pluginID != null) {
         log.info("Pel tipus de document " + tipusDoc + " i l'entitat " 
           + LoginInfo.getInstance().getEntitatID() + " hi ha assignat el plugin " + pluginID);
         tipusDocBySignatureID.put(signatureID, pluginID);
       }

       return SignatureModuleController.getFileInfoSignature(signatureID, source, idname,
            location_sign_table, reason, sign_number, 
            langUI, peticioDeFirma.getTipusFirmaID(), peticioDeFirma.getAlgorismeDeFirmaID(),
            peticioDeFirma.getModeDeFirma(), firmatPerFormat, timeStampGenerator);

    }
    



    @RequestMapping(value = "/rebutjar/{estatDeFirmaID}/{peticioDeFirmaID}")
    public ModelAndView rebutjar(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {


      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
          Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
      if (check == null) {
        // S'ha produit un error i retornam el control al llistat
        return llistatPaginat(request, response, null);
      }

      EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
      FirmaJPA firma = check.firma;
      PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

     
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
    
    
    
    @RequestMapping(value = "/rebutjarseleccionats", method = RequestMethod.POST)
    public ModelAndView rebutjarSeleccionats(HttpServletRequest request, HttpServletResponse response,
        @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {

      // seleccionats conté els estatIDs
      String[] seleccionatsStr = filterForm.getSelectedItems();


      if (seleccionatsStr == null || seleccionatsStr.length == 0) {

        HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("rebutjarseleccionats.cap"));
        
        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      } else {
        
       
        
        ArrayList<Long> seleccionats = new ArrayList<Long>();
        for(int i = 0; i< seleccionatsStr.length; i++) {
           try {
            seleccionats.add(Long.parseLong(seleccionatsStr[i]));
          } catch(Throwable e) {
            log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
          }
        }
        
        
        EstatDeFirmaQueryPath efqp = new EstatDeFirmaQueryPath();
        
        
        
        
        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(ESTATDEFIRMAID.select, 
            efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID().select
            );
        
        List<StringKeyValue> listIds = estatDeFirmaEjb.executeQuery(smskv, ESTATDEFIRMAID.in(seleccionats));
        

        final boolean debug = log.isDebugEnabled();
        for (StringKeyValue skv: listIds ) {
          if (debug) {
            log.info("rebutjarSeleccionats::SELECCIONAT = " + skv.getKey() + " / "
              + skv.getValue());
          }

          Long estatDeFirmaID = new Long( skv.getKey());
          Long peticioDeFirmaID = new Long(skv.getValue());
          
          rebutjar(request, response, estatDeFirmaID, peticioDeFirmaID);
          
        }
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
    
    
    /**
     * 
     * @author anadal
     *
     */
    public static class FirmaItem {
      final CheckInfo checkInfo;
      
      final FileInfoSignature fileInfosignature;

      /**
       * @param checkInfo
       * @param fileInfosignature
       */
      public FirmaItem(CheckInfo checkInfo, FileInfoSignature fileInfosignature) {
        super();
        this.checkInfo = checkInfo;
        this.fileInfosignature = fileInfosignature;
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
      
      this.preList(request, mav, filterForm);
      
      final String role = getRole();
      
      Map<Long, PeticioDeFirma> peticionsByEstat;
      peticionsByEstat = estatDeFirmaLogicaEjb
          .getPeticioDeFirmaFromEstatDeFirmaID(estatDeFirmaList);
      mav.addObject("peticionsByEstat", peticionsByEstat);
      
      Map<Long, Long> annexesByPeticio = new HashMap<Long,Long>();

      // Annexes, Peticio de Firma, Tipus Document , Remitent i DataInici
      {

        Map<Long, String> mapPF;
        mapPF = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA).getValueMap();
        mapPF.clear();
        
        Map<Long, String> mapTD;
        mapTD = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_TIPUS_DOC).getValueMap();
        mapTD.clear();
        
        final boolean remitent = role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE);
        Map<Long, String> mapCR = null;
        if (remitent) {          
          mapCR = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_REMITENT).getValueMap();
          mapCR.clear();
        }

        for(Long estatDeFirmaId : peticionsByEstat.keySet()) {
           PeticioDeFirmaJPA pf = (PeticioDeFirmaJPA)peticionsByEstat.get(estatDeFirmaId);
           
           
           mapPF.put(estatDeFirmaId, pf.getTitol());
           
           mapTD.put(estatDeFirmaId, pf.getTipusDocument().getNomTraduccions().get("ca").getValor());
           
           
           
           if (remitent) {
             mapCR.put(estatDeFirmaId, "<small title=\"" + pf.getRemitentDescripcio() + "\" >"
                 + pf.getRemitentNom() + "</small>");
           }
           
           
           annexesByPeticio.put(pf.getPeticioDeFirmaID(),
             annexEjb.count(AnnexFields.PETICIODEFIRMAID.equal(pf.getPeticioDeFirmaID())));
           
        }
        
      }
      
      
      
      
      {
        org.fundaciobit.genapp.common.web.i18n.I18NDateFormat dateFormat;
        dateFormat = new org.fundaciobit.genapp.common.web.i18n.I18NDateFormat();

        Map<Long, String> mapDI;
        mapDI = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_DATA_INICI_SHORT).getValueMap();
        mapDI.clear();
        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
          mapDI.put(estatDeFirma.getEstatDeFirmaID(), dateFormat.format(estatDeFirma.getDataInici()));
        }
        
      }
      
     
      
      List<Long> estatsID = new ArrayList<Long>();
      if (role.equals(Constants.ROLE_COLA) || role.equals(Constants.ROLE_DELE)) {
        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
          // Crec que aquest if es innecesari !!!
          if (estatDeFirma.getColaboracioDelegacioID() != null) {
            estatsID.add(estatDeFirma.getEstatDeFirmaID());
          }
        }
      }
      
      
      // Delegat de Colaborador
      if (role.equals(Constants.ROLE_COLA)) { 
        UsuariPersonaQueryPath upqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO()
            .DESTINATARI().USUARIPERSONA();
    
        SelectMultipleKeyValue<Long> smsky;
        smsky = new SelectMultipleKeyValue<Long>(ESTATDEFIRMAID.select, upqp.NOM().select,
            upqp.LLINATGES().select);
    
        List<KeyValue<Long>> nomsDest = estatDeFirmaEjb.executeQuery(smsky,
            ESTATDEFIRMAID.in(estatsID));
    
        Map<Long, String> infoDestByEstat = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_DELEGAT_DE_COLABORADOR).getValueMap();
        infoDestByEstat.clear();
        for (KeyValue<Long> keyValue : nomsDest) {
          
          infoDestByEstat.put(keyValue.getKey(), "<small>" + keyValue.getValue() + "</small>");
        }

      }
      
      //  Delegats
      if (role.equals(Constants.ROLE_DEST)) {
        
        Map<Long, String> mapDD = new HashMap<Long, String>();

        
        Map<Long, int[]> infoDelegatsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
            ESTATS_INICIALS_DELE);
        
        boolean existeixenDelegacions = false;

        for(Long estatDeFirmaId :  infoDelegatsByEstat.keySet()) {
      
          int[] valors = infoDelegatsByEstat.get(estatDeFirmaId);
          StringBuffer str = new StringBuffer();
          if (valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.FIRMAT")
                 + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          if (valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2] != 0) {
            str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.REBUTJAT")
                + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          if (valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0) {             
            str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.DESCARTAT")
                + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          if (valors[1] != 0) {
            str.append("<small>" + I18NUtils.tradueix("pendent") 
                + ": " + valors[1] + "/" + valors[0] + "</small><br/>\n");
          }
          
          if (str.length() != 0) {
            existeixenDelegacions = true;
          }

          mapDD.put(estatDeFirmaId, str.toString());
          
        }

        // Ocultar columna si esta buida
        if (!existeixenDelegacions) {
          filterForm.getAdditionalFields().remove(COLUMN_DELEGATS_DE_DESTINATARI);
        } else {
          
          AdditionalField<Long,String> adfieldDD;
          
          adfieldDD = (AdditionalField<Long,String>)filterForm.getAdditionalFields().get(COLUMN_DELEGATS_DE_DESTINATARI);
          
          if (adfieldDD == null) {
            // NOVA COLUMNA si no esta creada
            adfieldDD = new AdditionalField<Long,String>(); 
            adfieldDD.setCodeName("ROLE_DELE.plural");
            adfieldDD.setPosition(COLUMN_DELEGATS_DE_DESTINATARI);
            // Els valors s'ompliran al mètode postList()
            adfieldDD.setEscapeXml(false);
            
            filterForm.addAdditionalField(adfieldDD);
          }
          
          adfieldDD.setValueMap(mapDD);

        }

      }
      
      // Col·laboradors
      boolean ocultarColumnaColaboradors = filterForm.getHiddenFields().contains(ColaboracioDelegacioFields.DESTINATARIID); 

      if ((role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) 
          // Es la forma d'indicar que el doc s'ha rebutjar i que no importa veure els col·laboradors
          && !ocultarColumnaColaboradors) {
        
        Map<Long, String> mapCC = new HashMap<Long, String>();

        boolean existeixenColaboracions = false;
        
        
        Map<Long, int[]> infoColaboradorsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
            ESTATS_INICIALS_COLA);
        //mav.addObject("infoColaboradorsByEstat", infoColaboradorsByEstat);
        
        for(Long estatDeFirmaId :  infoColaboradorsByEstat.keySet()) {
        
           int[] valors=infoColaboradorsByEstat.get(estatDeFirmaId);
           StringBuffer str = new StringBuffer();
           if(valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.VALIDAT") 
                 + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2]  + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.INVALIDAT")
                 + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0) {           
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.DESCARTAT")
                 + ": " + valors[(int)Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT+ 2] + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[1] != 0) {
             str.append("<small>" + I18NUtils.tradueix("pendent") 
                 + ": " + valors[1] + "/" + valors[0]  + "</small><br/>\n");
           }
           
           if (str.length() != 0) {
             existeixenColaboracions = true;
           }
           
           mapCC.put(estatDeFirmaId, str.toString());
        }
        

        // Ocultar columna si esta buida
        if (!existeixenColaboracions) {
          filterForm.getAdditionalFields().remove(COLUMN_COLABORADORS);
        } else {

          AdditionalField<Long,String> adfieldDD;
          adfieldDD = (AdditionalField<Long,String>)filterForm.getAdditionalFields().get(COLUMN_COLABORADORS);
          
          if (adfieldDD == null) {
            // NOVA COLUMNA si no esta creada

            adfieldDD = new AdditionalField<Long,String>(); 
            adfieldDD.setCodeName("colaborador.short");
            adfieldDD.setPosition(COLUMN_COLABORADORS);
            // Els valors s'ompliran al mètode postList()
            adfieldDD.setEscapeXml(false);
            
            filterForm.addAdditionalField(adfieldDD);
          }
          
          adfieldDD.setValueMap(mapCC);

        }

      } else {
        if (ocultarColumnaColaboradors) {
          filterForm.getAdditionalFields().remove(COLUMN_COLABORADORS);
        }
      }
      
      
      {
        
        Map<Long, String> mapPR;
        mapPR = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PRIORITAT).getValueMap();
        mapPR.clear();
        
        String color_priority, title_priority;
        for(Long estatDeFirmaId : peticionsByEstat.keySet()) {
          int prioritatID = peticionsByEstat.get(estatDeFirmaId).getPrioritatID();
          
          
          if (prioritatID <= 2) {
             color_priority="btn-success";
             title_priority="prioritat.baixa";
          } else if (prioritatID >= 7) {
              color_priority="btn-danger";
              title_priority="prioritat.alta";
          } else {
              color_priority="btn-warning";
              title_priority="prioritat.normal";
          }

          mapPR.put(estatDeFirmaId, "<button title=\""+ I18NUtils.tradueix(title_priority) + "\" "
              + " class=\"btn btn-mini " + color_priority + "\" type=\"button\">&nbsp;</button>");         
         }

      }
      
      // TODO Només mostrar en les pantalles Pendents
      if (role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) {


        Map<Long, String> rebuigDescriptionByEstat = getRebuigDescriptionByEstat(estatDeFirmaList);

        mav.addObject("rebuigDescriptionByEstat", rebuigDescriptionByEstat);

      }

      // ============================
      // -----      BOTONS  --------- 
      // ============================
      
      filterForm.getAdditionalButtonsByPK().clear();
      
      // Document PDF
      for(Long estatDeFirmaId : peticionsByEstat.keySet()) {
        PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA)peticionsByEstat.get(estatDeFirmaId); 
        long peticioID = peticio.getPeticioDeFirmaID();
      
        // TODO falta obri-ho en una nova pipella
        filterForm.addAdditionalButtonByPK(estatDeFirmaId, new AdditionalButton("icon-file",
          "veuredoc",
          "javascript:var win = window.open('" + request.getContextPath() + getContextWeb() + "/docfirmat/" + peticioID + "', '_blank'); win.focus();"
           , "btn-info"));
        
        
        // Comprovar si hi ha anexes
        if (annexesByPeticio.get(peticioID) != 0) {
          filterForm.addAdditionalButtonByPK(estatDeFirmaId,
              new AdditionalButton("icon-folder-open", "annex.annex.plural",
              getContextWeb() + "/viewDocuments/" + estatDeFirmaId + "/" + peticioID , "btn-info"));

        }
        
      }
      
       // Full view
       for (EstatDeFirma ef : estatDeFirmaList) {
            if (ef.getTipusEstatDeFirmaInicialID() ==  Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
              long peticioID =  peticionsByEstat.get(ef.getEstatDeFirmaID()).getPeticioDeFirmaID();
              filterForm.addAdditionalButtonByPK(ef.getEstatDeFirmaID(),
                  new AdditionalButton("icon-eye-open",     "vistacompleta",
                  getContextWeb() + "/fullView/" + ef.getEstatDeFirmaID() + "/" + peticioID , "btn-info"));
            }
       }

       for (EstatDeFirma ef : estatDeFirmaList) {
       
         final long estatId = ef.getEstatDeFirmaID();
         final long peticioID =  peticionsByEstat.get(estatId).getPeticioDeFirmaID();

         if (ef.getTipusEstatDeFirmaFinalID() == null) {
   
           final long estatInicial = ef.getTipusEstatDeFirmaInicialID(); 
           if (estatInicial ==  Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
             
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-edit", "firmar",
                  "javascript:firmar('" + request.getContextPath() + getContextWeb() + "/firmar/" + estatId + "/" + peticioID + "', {0})" ,
                  "btn-success"));
             
          
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-remove", "rebutjar",
                 "javascript:rebutjar('" + request.getContextPath() +  getContextWeb() + "/rebutjar/" + estatId + "/" + peticioID + "'," + estatId + ")" ,
                  "btn-danger"));

           }
        
           
           if (estatInicial ==  Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR) {
             // TODO Indicar la descripció d'aquest botó: traducció marcarrevisant.desc
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-flag", "marcarrevisant",
                 getContextWeb() + "/marcarrevisant/" + estatId + "/" + peticioID ,
                  "btn-warning"));
           }
        
           if (estatInicial ==  Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR 
               || estatInicial == Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR) {
               
             
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-check", "validar",
                  getContextWeb() + "/validar/" + estatId + "/" + peticioID,
                  "btn-success"));
             
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-remove", "invalidar",
                 "javascript:invalidar('" + request.getContextPath() + "" + getContextWeb() + "/invalidar/" + estatId + "/" + peticioID + "')" ,
                  "btn-danger"));

          }
        
        
         } // Final if
       
       } // Final For


       // --------------------------------------------------------------------
       //
       //   MULTIPLE SELECCIO EN PANTALLES SENSE FILTRE
       //
       // --------------------------------------------------------------------
      

       if (estatDeFirmaList.size() == 0) {
         filterForm.setVisibleMultipleSelection(false);         
         //filterForm.setAdditionalButtons(new ArrayList<AdditionalButton>());
       } else {
         
         // Per defecte
         filterForm.setVisibleMultipleSelection(false);
  
         if (role.equals(Constants.ROLE_DEST) || role.equals(Constants.ROLE_DELE)) {
           if (this.getFilterType() == FILTRAR_PER_PENDENT) {
             filterForm.setVisibleMultipleSelection(true);
           } else if (getFilterType() == FILTRAR_PER_RES) {
             for (EstatDeFirma ef : estatDeFirmaList) {
               if (ef.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR
                   && ef.getTipusEstatDeFirmaFinalID() == null) {
                 filterForm.setVisibleMultipleSelection(true);
                 break;
               }
             }
           }
         }
         
         
          
         if (filterForm.isVisibleMultipleSelection() && filterForm.getAdditionalButtons().isEmpty()) {

            filterForm.addAdditionalButton(new  AdditionalButton("icon-remove",
                "rebutjarseleccionats", "javascript:rebutjarseleccionats()" , "btn-danger"));
            
            filterForm.addAdditionalButton(new  AdditionalButton("icon-edit",
                "firmarseleccionats", "javascript:firmarseleccionats()" , "btn-success"));
           
         }
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

          try {
            valors[index + 2] = count;
            total = total + count;
            continue;
          } catch (IndexOutOfBoundsException iobe) {
            log.error("Valor desconegut " + valStr + " o index fora de rang " + index, iobe);
          }


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
      
      Where estatWhere;
      switch(getFilterType()) {
        case FILTRAR_PER_PENDENT: // Pendent de firma o de de validacio
          estatWhere = TIPUSESTATDEFIRMAFINALID.isNull();
        break;
        
        case FILTRAR_PER_ACCEPTAT: // Firmat o validat
          if (role.equals(ROLE_COLA)) {
            estatWhere = TIPUSESTATDEFIRMAFINALID.equal(Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT);
          } else {
            estatWhere = Where.OR(
              // El propi usuari (destinatari o delegat) ha firmat el document
              TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_FIRMAT),
              // Alguna altra persona (delegat o destinatari) ha firmat el document
              Where.AND(
                  TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT),
                  new EstatDeFirmaQueryPath().FIRMA().FITXERFIRMATID().isNotNull() )
                  );
          }
        break;
        
        case FILTRAR_PER_NOACCEPTAT: // Rebutjat o invalidat
          if (role.equals(ROLE_COLA)) {
            estatWhere = TIPUSESTATDEFIRMAFINALID.equal(Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
          } else {
            estatWhere = Where.OR(
                // El propi usuari (destinatari o delegat) ha rebutjat el document
                TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_REBUTJAT),
                // Algun altre usuari (delegat o destinatari/delegat) ha rebutjat el document
                Where.AND(
                    TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT),
                    new EstatDeFirmaQueryPath().FIRMA().FITXERFIRMATID().isNull() )
                );
          }
        break;

        case FILTRAR_PER_NODEFINIT: // Rebutjat o invalidat
          // El propi usuari (destinatari o delegat) no ha firmat el document
          estatWhere = TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT);
        break;

        default:
          estatWhere = null;
      }

      return Where.AND(roleWhere, estatWhere, super.getAdditionalCondition(request),
              EstatDeFirmaFields.USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID()));
    }

    
    @RequestMapping(value = "/viewDocuments/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView viewDocumentsFullView(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {
      
      // p.e. viewDocuments_ROLE_DEST
      String view = "viewDocumentsFullView_" + getRole();
      
      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, false,
          Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
      if (check == null) {
        // S'ha produit un error i retornam el control al llistat
        return llistatPaginat(request, response, null);
      }

      EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
      FirmaJPA firma = check.firma;
      PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

      ModelAndView mav = new ModelAndView(view);
      

      mav.addObject("peticioDeFirma", peticioDeFirma);

      mav.addObject("estatDeFirma", estatDeFirma);

      mav.addObject("firma", firma);

      String contexte = getContextWeb();

      mav.addObject("contexte", contexte);

      mav.addObject("rolecontext", contexte.substring(1, contexte.indexOf('/', 2)));
      
      // 1.- Fitxers a visualitzar
      procesFitxersAVeure(mav, peticioDeFirmaID, peticioDeFirma);
      
      return mav;
      
    }



    @RequestMapping(value = "/fullView/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView fullView(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

      String view = getFullViewTile();
      
      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, false,
          Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
      if (check == null) {
        // S'ha produit un error i retornam el control al llistat
        return llistatPaginat(request, response, null);
      }

      EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
      FirmaJPA firma = check.firma;
      PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

      ModelAndView mav = new ModelAndView(view);

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

      // 1.- Fitxers a visualitzar
      procesFitxersAVeure(mav, peticioDeFirmaID, peticioDeFirma);


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

    private void procesFitxersAVeure(ModelAndView mav, Long peticioDeFirmaID,
        PeticioDeFirmaJPA peticioDeFirma) throws I18NException {

      {
        

        List<KeyValue<FitxerJPA>> fitxers = new ArrayList<KeyValue<FitxerJPA>>();
        // 1.1.- Fitxer principal
        int tipusFirmaID = peticioDeFirma.getTipusFirmaID();
        if (tipusFirmaID == Constants.TIPUSFIRMA_PADES) {
          FitxerJPA f;
          f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

          f.setNom(peticioDeFirma.getFitxerAFirmar().getNom());
          
          fitxers.add(new KeyValue<FitxerJPA>(f, String.valueOf(Constants.DOC_PDF)));
        } else {
          FitxerJPA f;
          if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
            f = peticioDeFirma.getFitxerAFirmar();
          } else {
            f = peticioDeFirma.getFitxerAdaptat();
            f.setNom(peticioDeFirma.getFitxerAFirmar().getNom());
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
      return getBaseEntityNameCode() + getEntityNameByFilterType();
    }

    @Override
    public final String getEntityNameCodePlural() {
      return getEntityNameCode() + ".plural";
    }

    
    public String getEntityNameByFilterType() {

      switch(getFilterType()) {
        case FILTRAR_PER_PENDENT: // Pendent de firma o de de validacio
          return ".pendent";

        case FILTRAR_PER_ACCEPTAT: // Firmat o validat
          return ".acceptada";
 
        case FILTRAR_PER_NOACCEPTAT: // Rebutjat o invalidat
           return ".noacceptada";

        case FILTRAR_PER_NODEFINIT: // Colllaboracions ignorades
           return ".ignorada";

        default:
          return ".totes";
      }
      
    }
   
    
    /**
     * 
     * @author anadal
     *
     */
    public static class SignatureID {
      
      
      final Long estatDeFirmaID;
      final Long peticioDeFirmaID;
      final String token;
      /**
       * @param peticioDeFirmaID
       * @param estatDeFirmaID
       * @param token
       */
      public SignatureID(Long peticioDeFirmaID, Long estatDeFirmaID, String token) {
        super();
        this.peticioDeFirmaID = peticioDeFirmaID;
        this.estatDeFirmaID = estatDeFirmaID;
        this.token = token;
      }
      public Long getEstatDeFirmaID() {
        return estatDeFirmaID;
      }
      public Long getPeticioDeFirmaID() {
        return peticioDeFirmaID;
      }
      public String getToken() {
        return token;
      }
      
      
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



