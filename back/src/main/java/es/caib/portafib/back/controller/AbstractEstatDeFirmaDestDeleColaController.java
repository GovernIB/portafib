package es.caib.portafib.back.controller;

import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.controller.webdb.EstatDeFirmaController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentRefList;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.AbstractParallelSignedFilesProcessing;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaEJB.Token;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
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
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.RevisorDeFirmaQueryPath;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.DoubleField;
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
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

  /**
   * Controller per gestionar un EstatDeFirma
   * 
   * @author anadal
   */
  @Controller  
  @SessionAttributes(types = { EstatDeFirmaFilterForm.class })
  public abstract class AbstractEstatDeFirmaDestDeleColaController extends EstatDeFirmaController implements
      EstatDeFirmaFields, ConstantsV2 {

    @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = "portafib/ColaboracioDelegacioLogicaEJB/local")
    protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioEjb;
    
    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioDeFirmaLogicaEjb;

    @EJB(mappedName = "portafib/FirmaEJB/local")
    protected FirmaLocal firmaEjb;

    @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;
    
    @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
    protected SegellDeTempsLogicaLocal segellDeTempsEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.AnnexLocal.JNDI_NAME)
    protected es.caib.portafib.ejb.AnnexLocal annexEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal.JNDI_NAME)
    protected es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal modulDeFirmaPerTipusDeDocumentEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaLocal.JNDI_NAME)
    protected es.caib.portafib.ejb.RevisorDeFirmaLocal revisorDeFirmaEjb;


    final Long[] ESTATS_INICIALS_COLA = new Long[] {
        ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
        ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR };

    final Long[] ESTATS_INICIALS_DELE = new Long[] { ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };
    
    final Long[] ESTATS_INICIALS_REVI = new Long[] { ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR };

    // References

    @Autowired
    protected UsuariPersonaRefList usuariPersonaRefList;

    @Autowired
    protected TipusDocumentRefList tipusDocumentRefList;

    private static final int COLUMN_PETICIODEFIRMA_TITOL = -6;
    private static final StringField COLUMN_PETICIODEFIRMA_TITOL_FIELD;
    
    
    private static final int COLUMN_PETICIODEFIRMA_DESCRIPCIO = -5;
    private static final StringField COLUMN_PETICIODEFIRMA_DESCRIPCIO_FIELD;
    
    private static final int COLUMN_PETICIODEFIRMA_MOTIU = -4;
    private static final StringField COLUMN_PETICIODEFIRMA_MOTIU_FIELD;

    private static final int COLUMN_PETICIODEFIRMA_TIPUSDOC = -3;
    
    private static final LongField COLUMN_PETICIODEFIRMA_TIPUSDOC_FIELD;
    
    private static final int COLUMN_ESTATDEFIRMA_DATAINICI_SMALL = -2;

    private static final int COLUMN_ESTATDEFIRMA_DATAFI_SMALL = -1;

    private static final int COLUMN_PETICIODEFIRMA_REMITENT = 1;
    
    private static final StringField COLUMN_PETICIODEFIRMA_REMITENT_FIELD;
    
    private static final int COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO = 2;
    
    private static final StringField COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO_FIELD;
    
    private static final int COLUMN_DELEGAT_DE_COLABORADOR = 3;
    
    private static final int COLUMN_DELEGATS_DE_DESTINATARI = 4;
    
    private static final int COLUMN_COLABORADORS = 5;
    
    private static final int COLUMN_REVISORS = 6;    

    private static final int COLUMN_PETICIODEFIRMA_PRIORITAT = 7;
    
    private static final IntegerField COLUMN_PETICIODEFIRMA_PRIORITAT_FIELD;
    
    private static final int COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE = 8;
    
    private static final DoubleField COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD;
    
    
    // Propietat de Col.laboracio-Delegacio
    private final static StringField DESTINATARIID;
    
    static {
      
      PeticioDeFirmaQueryPath pfqp;
      pfqp = new EstatDeFirmaQueryPath().FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();
      
      COLUMN_PETICIODEFIRMA_TITOL_FIELD = pfqp.TITOL();

      COLUMN_PETICIODEFIRMA_TIPUSDOC_FIELD = pfqp.TIPUSDOCUMENTID();
      
      COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD = pfqp.INFORMACIOADDICIONALAVALUABLE();

      COLUMN_PETICIODEFIRMA_REMITENT_FIELD = pfqp.REMITENTNOM();
          
      COLUMN_PETICIODEFIRMA_PRIORITAT_FIELD = pfqp.PRIORITATID();
      
      // NOMES SEARCH
      COLUMN_PETICIODEFIRMA_DESCRIPCIO_FIELD = pfqp.DESCRIPCIO();
      
      COLUMN_PETICIODEFIRMA_MOTIU_FIELD = pfqp.MOTIU();
      
      COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO_FIELD = pfqp.REMITENTDESCRIPCIO();
      
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
        ff.addHiddenField(DATAFI);

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
            new OrderBy(COLUMN_PETICIODEFIRMA_PRIORITAT_FIELD, OrderType.DESC), 
            new OrderBy(DATAINICI, OrderType.DESC),
        });

        ff.addGroupByField(COLUMN_PETICIODEFIRMA_TIPUSDOC_FIELD); // Propietat de Peticio De Firma
        ff.addGroupByField(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);
        
        
        if (getRole().equals(ConstantsV2.ROLE_COLA)) {
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
            
            // Eliminam la data de Fi, ja que sempre val null en cas PENDENT
            {
              ArrayList<Field<?>> filterBy = new ArrayList<Field<?>>(ff.getDefaultFilterByFields()); 
              filterBy.remove(DATAFI);
              ff.setFilterByFields(filterBy);
            }
            
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

        //  NOVES COLUMNES PETICIO

        // ===================  Nom de petició de firma
        {
          AdditionalField<String,String> addfieldPF = new AdditionalField<String,String>(); 
          addfieldPF.setCodeName("document");
          addfieldPF.setPosition(COLUMN_PETICIODEFIRMA_TITOL);
          // Els valors s'ompliran al mètode postList()
          addfieldPF.setValueMap(new HashMap<String, String>());
          addfieldPF.setOrderBy(COLUMN_PETICIODEFIRMA_TITOL_FIELD);
          
          addfieldPF.setSearchBy(COLUMN_PETICIODEFIRMA_TITOL_FIELD);
  
          ff.addAdditionalField(addfieldPF);
        }

        
        // ===================  tipus de document 
        {
          AdditionalField<String,String> adfieldTD = new AdditionalField<String,String>(); 
          adfieldTD.setCodeName("tipus");
          adfieldTD.setPosition(COLUMN_PETICIODEFIRMA_TIPUSDOC);
          // Els valors s'ompliran al mètode postList()
          adfieldTD.setValueMap(new HashMap<String, String>());
          adfieldTD.setOrderBy(COLUMN_PETICIODEFIRMA_TIPUSDOC_FIELD);
          
          ff.addAdditionalField(adfieldTD);
        }
        
        // =================== 
        final String role = getRole();
        if (role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE)) {

          // NOVA COLUMNA PETICIO DE FIRMA
          AdditionalField<String,String> adfieldRN = new AdditionalField<String,String>(); 
          adfieldRN.setCodeName("peticioDeFirma.remitentNom");
          adfieldRN.setPosition(COLUMN_PETICIODEFIRMA_REMITENT);
          adfieldRN.setEscapeXml(false);
          // Els valors s'ompliran al mètode postList()
          adfieldRN.setValueMap(new HashMap<String, String>());
          adfieldRN.setOrderBy(COLUMN_PETICIODEFIRMA_REMITENT_FIELD);
          adfieldRN.setSearchBy(COLUMN_PETICIODEFIRMA_REMITENT_FIELD);

          ff.addAdditionalField(adfieldRN);

          // NOVA AGRUPACIO
          ff.addGroupByField(COLUMN_PETICIODEFIRMA_REMITENT_FIELD);
        }
        
        // NOVES COLUMNES NOMES PER CERQUES
        
        // ===================  Cerca per Descripcio de petició de Firma 
        {
          AdditionalField<String,String> addfieldDESC = new AdditionalField<String,String>(); 
          addfieldDESC.setCodeName("peticioDeFirma.descripcio");
          addfieldDESC.setPosition(COLUMN_PETICIODEFIRMA_DESCRIPCIO);
          // No omplirem els valors
          addfieldDESC.setValueMap(new HashMap<String, String>());

          addfieldDESC.setSearchBy(COLUMN_PETICIODEFIRMA_DESCRIPCIO_FIELD);

          ff.addAdditionalField(addfieldDESC);
          
          ff.addHiddenField(COLUMN_PETICIODEFIRMA_DESCRIPCIO_FIELD);
        }


        // ===================  Cerca per Motiu de petició de Firma 
        {
          AdditionalField<String,String> addfieldMOTIU = new AdditionalField<String,String>(); 
          addfieldMOTIU.setCodeName("peticioDeFirma.motiu");
          addfieldMOTIU.setPosition(COLUMN_PETICIODEFIRMA_MOTIU);
          // No omplirem els valors
          addfieldMOTIU.setValueMap(new HashMap<String, String>());

          addfieldMOTIU.setSearchBy(COLUMN_PETICIODEFIRMA_MOTIU_FIELD);

          ff.addAdditionalField(addfieldMOTIU);
          
          ff.addHiddenField(COLUMN_PETICIODEFIRMA_MOTIU_FIELD);
        }
        
        // ===================  Cerca per remitent descripcio
        if (role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE)) {
          AdditionalField<String,String> addfieldREMIDESC = new AdditionalField<String,String>(); 
          addfieldREMIDESC.setCodeName("peticioDeFirma.remitentDescripcio");
          addfieldREMIDESC.setPosition(COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO);
          // No omplirem els valors
          addfieldREMIDESC.setValueMap(new HashMap<String, String>());

          addfieldREMIDESC.setSearchBy(COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO_FIELD);

          ff.addAdditionalField(addfieldREMIDESC);

          ff.addHiddenField(COLUMN_PETICIODEFIRMA_REMITENTDESCRIPCIO_FIELD);
        }
        
        // ===================  Cerca per informacio addicional avaluable
        if (role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE) || role.equals(ConstantsV2.ROLE_REVI)) {
          AdditionalField<String,String> addfieldInfoAddicAval = new AdditionalField<String,String>();

          addfieldInfoAddicAval.setCodeName(PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE.fullName);
          addfieldInfoAddicAval.setPosition(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE);
          addfieldInfoAddicAval.setCodeName("informacioaddicionalavaluable.short");
              /*
              "=<i class=\"icon-info-sign\" title=\"" 
              + I18NUtils.tradueix(PeticioDeFirmaFields.INFORMACIOADDICIONALAVALUABLE.fullName) + "\"></i>"*/
          
          // No omplirem els valors
          addfieldInfoAddicAval.setValueMap(new HashMap<String, String>());

          addfieldInfoAddicAval.setSearchBy(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);
          
          addfieldInfoAddicAval.setOrderBy(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);

          ff.addAdditionalField(addfieldInfoAddicAval);

          ff.addHiddenField(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);
        }


        // NOVES COLUMNES ESTAT DE FIRMA
        // ===================  data inici (small)
        {
          AdditionalField<String,String> adfieldDI = new AdditionalField<String,String>(); 
          adfieldDI.setCodeName("datainici.short");
          adfieldDI.setPosition(COLUMN_ESTATDEFIRMA_DATAINICI_SMALL);
          // Els valors s'ompliran al mètode postList()
          adfieldDI.setValueMap(new HashMap<String, String>());
          adfieldDI.setEscapeXml(false);
          adfieldDI.setOrderBy(DATAINICI);
          ff.addAdditionalField(adfieldDI);
        }
        
        // ===================  data fi (small)
        if (getFilterType() != FILTRAR_PER_PENDENT) {
          AdditionalField<String,String> adfieldDF = new AdditionalField<String,String>(); 
          adfieldDF.setCodeName("datafi.short");
          adfieldDF.setPosition(COLUMN_ESTATDEFIRMA_DATAFI_SMALL);
          // Els valors s'ompliran al mètode postList()
          adfieldDF.setValueMap(new HashMap<String, String>());
          adfieldDF.setEscapeXml(false);

          ff.addAdditionalField(adfieldDF);
        }


        // ======================

        if (role.equals(ConstantsV2.ROLE_COLA)) {

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
        adfieldPR.setPosition(COLUMN_PETICIODEFIRMA_PRIORITAT);
        adfieldPR.setEscapeXml(false);
        // Els valors s'ompliran al mètode postList()
        adfieldPR.setValueMap(new HashMap<String, String>());
        adfieldPR.setOrderBy(COLUMN_PETICIODEFIRMA_PRIORITAT_FIELD);
        
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
      if (getRole().equals(ConstantsV2.ROLE_COLA)) {

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
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLUMN_PETICIODEFIRMA_TIPUSDOC_FIELD, false);
      }
      
      {
//        List<Long> values = new ArrayList<Long>();
//        for (EstatDeFirma ef : list) {
//             values.add(ef.getEstatDeFirmaID());          
//        }
//          
//        EstatDeFirmaFields.ESTATDEFIRMAID.in(values);
        
        _listSKV = estatDeFirmaEjb.executeQuery(
             new SelectMultipleStringKeyValue(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD.select,
                 COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD.select), getAdditionalCondition(request) );
        
        //_listSKV = this.tipusDocumentRefList.getReferenceList(TipusDocumentFields.TIPUSDOCUMENTID, null);
        _tmp = Utils.listToMap(_listSKV);
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD, false);
      }

      return groupByItemsMap;
    }


    @RequestMapping(value = "/final", method = RequestMethod.GET)
    public String finalRequest(HttpServletRequest request, HttpServletResponse response)
        throws I18NException {
      
      log.info("\n\n XYZ ZZZ   ñññññññññ   PASSA PER finalRequest\n\n");
      
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
    
    
    
    

    @RequestMapping(value = "/firmarseleccionats", method = RequestMethod.POST)
    public ModelAndView firmarSeleccionats(HttpServletRequest request, HttpServletResponse response,
        @ModelAttribute EstatDeFirmaFilterForm filterForm,
        @RequestParam("url_user") String baseUrlFull) throws I18NException {

      log.info("XYZ ZZZ baseUrlFull = " + baseUrlFull);
      
      String baseUrl = es.caib.portafib.back.utils.Utils.getUrlBaseFromFullUrl(request, baseUrlFull);
      log.info("XYZ ZZZ  baseUrl OK = " + baseUrl);

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
        

        List<FileInfoFull> fileInfoFullArray = new ArrayList<FileInfoFull>();
        
        String langUI = loginInfo.getUsuariPersona().getIdiomaID();

        Set<Long> peticionsDeFirmaID = new HashSet<Long>();
        Map<String, List<Long>> pluginsFirmaBySignatureID = new HashMap<String, List<Long>>();

        final boolean debug = log.isDebugEnabled();
        final int numberTotalOfSignatures = seleccionats.size();

        EntitatJPA entitat = loginInfo.getEntitat();
        String firstFiltreCertificats = null;
        Properties firstFiltreProperties = null;

        for (int i = 0; i < listIds.size(); i++) {
        StringKeyValue skv = listIds.get(i);


          if (debug) {
            log.info("firmarSeleccionats::SELECCIONAT = " + skv.getKey() + " / "
              + skv.getValue());
          }

          Long estatDeFirmaID = new Long( skv.getKey());
          Long peticioDeFirmaID = new Long(skv.getValue());

          UsuariAplicacioConfiguracioJPA config = configuracio(peticioDeFirmaID);
          String filtreCertificats = (config != null ? config.getFiltreCertificats() : entitat.getFiltreCertificats());
          Properties filtreProperties = new Properties();
          if (filtreCertificats != null) {
            try {
              filtreProperties.load(new StringReader(filtreCertificats));
            } catch (IOException e) {
              log.warn("Error llegit filtre de certficats " + filtreCertificats);
            }
          }

          if (i == 0) {
            firstFiltreCertificats = filtreCertificats;
            firstFiltreProperties = filtreProperties;
          } else {
            // Si no té el mateix filtre de certificats que el primer no el podem signar. Avisam a l'usuari.
            if (!firstFiltreProperties.equals(filtreProperties)) {
              HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("firmarseleccionats.nomateixfiltre", String.valueOf(peticioDeFirmaID)) );
              continue;
            }
          }

          // Només permetre una firma per petició (evitar bloc de firmes amb una firma
          // usuari-entitat i una altra amb usuari càrrec)
          if (!peticionsDeFirmaID.contains(peticioDeFirmaID)) {

            try {
              checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
            } catch(I18NException e) {
              HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
              continue;
            }

            FileInfoFull fileInfoFull;
            fileInfoFull = prepareFirmaItem(request, estatDeFirmaID, peticioDeFirmaID,
                langUI, pluginsFirmaBySignatureID, loginInfo.getEntitatID(),
                numberTotalOfSignatures);

            if (fileInfoFull != null) {
              fileInfoFullArray.add(fileInfoFull);
            }

            peticionsDeFirmaID.add(peticioDeFirmaID);

          }
        }


        if (fileInfoFullArray.isEmpty()) {
          // TODO avis
          return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        }
        
        final String signaturesSetID = String.valueOf(SignatureModuleController.generateUniqueSignaturesSetID());

        CommonInfoSignature commonInfoSignature;
        {
          final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
          final String administrationID = loginInfo.getUsuariPersona().getNif();

          commonInfoSignature = new CommonInfoSignature(langUI, firstFiltreCertificats, username, administrationID);


        }
        
        // Vuls suposar que abans de "9 minuts més un minut per cada firma" haurà
        // finalitzat el proces de firma
        Calendar caducitat = Calendar.getInstance();
        caducitat.add(Calendar.MINUTE, 9 + fileInfoFullArray.size());

        // {0} ==> es substituirà per l'ID del plugin de firma seleccionat per firmar
        String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(request, getContextWeb());

        final String urlFinal = response.encodeURL(relativeControllerBase + "/finalFirma/" + signaturesSetID);

        FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[fileInfoFullArray.size()];
        int[] originalNumberOfSignsArray = new int[fileInfoFullArray.size()];
        {
          int count = 0;
          for(FileInfoFull fif : fileInfoFullArray) {
            fileInfoSignatureArray[count] = fif.fileInfoSignature;
            originalNumberOfSignsArray[count] = fif.originalNumberOfSigns;
            count++;
          }
        }

        PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID,
            caducitat.getTime(), commonInfoSignature,
            fileInfoSignatureArray, originalNumberOfSignsArray,
            loginInfo.getEntitat(), urlFinal, true, baseUrl);

        signaturesSet.setPluginsFirmaBySignatureID(pluginsFirmaBySignatureID);

        
        final String view = "PluginDeFirmaContenidor_" + getRole();
        ModelAndView mav = SignatureModuleController.startPrivateSignatureProcess(request, response, view, signaturesSet);
        
        
        // Només quan #peticions > 3 activar thread
        // Posar en marxa un thread que vagi mirant les entrades i les processi 
        // En el mapping finalOK o finalError descartar les entrades ja processades
        if (seleccionats.size() > 3) {
           ParallelSignedFilesProcessing pThread = new ParallelSignedFilesProcessing(
               request, signaturesSetID, peticioDeFirmaLogicaEjb, modulDeFirmaEjb);
           pThread.start();
        }
        return mav;
        
    }


    

    public abstract String getRole();
    
    public abstract String getBaseEntityNameCode();
    
    
    //              ID    DEST/DELE      COLA           REVI
    // -----------------------------------------------------
    // ALL          -1    tots_estats    tots_estats    tots_estats
    // PENDENT       1    pendent        pendent        pendent       
    // ACCEPTAT      2    firmat         validat        acceptat  
    // NOACCEPTAT    4    rebutjat       invalidat      rebutjar
    // NODEFINIT     8    descartat(*)   descartat(**)  -
    // TODO (*) En un futur el valor descartat per dest i dele no tindran sentit i les 
    //          delegacions firmades per un dest o les destinacions firmades per un delegat 
    //          serna tractades com l'estat final del la sol3licitud: firmada o rebutjada
    //  (**) En el cas de col3laborador-revisor no  pot ser l'estat DESCARTAT.
    
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
        log.debug(" ---------- checkCanSignPeticioDeFirma:: peticioID =>" + peticioDeFirmaID + " -----------");
      }

      // Colaboradors-revisors o Revisors de Firmes
      List<StringKeyValue> fullusuaris = new ArrayList<StringKeyValue>();
      
      // ================  
      // CHECK 1  REVISORS de FIRMA
      {
        // Revisors de Firmes
        List<StringKeyValue> usuaris;
        
        final Where specificRole = Where.AND(
            FIRMAID.equal(firmaId),
            TIPUSESTATDEFIRMAFINALID.isNull(),        
            TIPUSESTATDEFIRMAINICIALID.equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR)
            );
        
        if (isDebug) {
          log.debug("RV1 = " + estatDeFirmaEjb.count(specificRole));
        }

        
        Long count = estatDeFirmaEjb.count(specificRole);
        
        if (count != null && count != 0) {
          
          // CONSULTA
          RevisorDeFirmaQueryPath rfqp = new RevisorDeFirmaQueryPath();
          final Select<?>[] nomcomplet = new Select<?>[] {
              rfqp.USUARIENTITAT().USUARIPERSONA().NOM().select,
              rfqp.USUARIENTITAT().USUARIPERSONA().LLINATGES().select,
              rfqp.USUARIENTITAT().USUARIPERSONA().NIF().select
          };
          
          SelectMultipleStringKeyValue smskv;
          smskv = new SelectMultipleStringKeyValue(RevisorDeFirmaFields.REVISORDEFIRMAID.select, nomcomplet);
          
          usuaris = revisorDeFirmaEjb.executeQuery(smskv, RevisorDeFirmaFields.FIRMAID.equal(firmaId));
          
          if (usuaris != null) {
            fullusuaris.addAll(usuaris);
          }
        }

      } 

      // =========== COLABORADOR-REVISOR
      // Cercar colaboradors-revisors que no han donat el vist i plau a la firma
      {

        
        Where wColaRevi0 = Where.OR(
            TIPUSESTATDEFIRMAFINALID.isNull(),
            TIPUSESTATDEFIRMAFINALID.notEqual(ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT)
            );
        if (isDebug) {
          log.debug("CR0 = " + estatDeFirmaEjb.count(wColaRevi0));
        }
        
            
        Where wColaRevi1 = Where.OR(
            TIPUSESTATDEFIRMAINICIALID.equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR),
            TIPUSESTATDEFIRMAINICIALID.equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR)
          );
        if (isDebug) {
          log.debug("CR1 = " + estatDeFirmaEjb.count(Where.AND(wColaRevi0,wColaRevi1)));
        }
  
        
        // 
        Where wColaRevi2 = COLABORACIODELEGACIOID.isNotNull();
        if (isDebug) {
          log.debug("CR2 = " + estatDeFirmaEjb.count(Where.AND(wColaRevi0, wColaRevi1, wColaRevi2)));
        }
        
        // 
        ColaboracioDelegacioQueryPath cdqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO();
        
        Where wColaRevi3 = Where.AND(
            cdqp.REVISOR().equal(true),
            cdqp.ACTIVA().equal(true),
            cdqp.ESDELEGAT().equal(false) // Es col·laborador
          );
        
        if (isDebug) {
          log.debug("CR3 = " + estatDeFirmaEjb.count(Where.AND(wColaRevi0, wColaRevi1, wColaRevi2, wColaRevi3)));
        }
        
        // 
        Where wColaRevi4 = FIRMAID.equal(firmaId);
        final Where specificRole = Where.AND(wColaRevi0, wColaRevi1,wColaRevi2,wColaRevi3, wColaRevi4);
        if (isDebug) {
          log.debug("CR4 = " + estatDeFirmaEjb.count(specificRole));
        }

        
        // CONSULTA
        final Select<?>[] nomcomplet = new Select<?>[] {
            cdqp.COLABORADORDELEGAT().USUARIPERSONA().NOM().select,
            cdqp.COLABORADORDELEGAT().USUARIPERSONA().LLINATGES().select,
            cdqp.COLABORADORDELEGAT().USUARIPERSONA().NIF().select
        };
        
        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(COLABORACIODELEGACIOID.select, nomcomplet);
        
        // Colaboradors-revisors 
        List<StringKeyValue> usuaris;
        usuaris = estatDeFirmaEjb.executeQuery(smskv,specificRole);
        
        if (usuaris != null) {
          fullusuaris.addAll(usuaris);
        }
        
      }
      

      
      if (!fullusuaris.isEmpty()) {
        StringBuffer str = new StringBuffer();
        for (StringKeyValue colaID : fullusuaris) {

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

    
    @RequestMapping(value = "/acceptar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView acceptar(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR);
      if (check == null) {
        // S'ha produit un error i retornam el control al llistat
        return llistatPaginat(request, response, null);
      }

      EstatDeFirmaJPA estatDeFirma = check.estatDeFirma;
      FirmaJPA firma = check.firma;
      PeticioDeFirmaJPA peticioDeFirma = check.peticioDeFirma;

      peticioDeFirmaLogicaEjb.acceptar(estatDeFirma, firma, peticioDeFirma);

      // TODO falta missatge de tot OK

      return llistatPaginat(request, response, null);
    }
    

    @RequestMapping(value = "/firmar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView firmar(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID,
        @RequestParam("url_user") String baseUrlFull) throws I18NException {

      log.info("XYZ ZZZ baseUrlFull = " + baseUrlFull);
      
      String baseUrl = es.caib.portafib.back.utils.Utils.getUrlBaseFromFullUrl(request, baseUrlFull);
      log.info("XYZ ZZZ  baseUrl OK = " + baseUrl);
      
      
      ModelAndView mav;
      final int numberTotalOfSignatures = 1;
      mav = commonFirma(request, response, estatDeFirmaID,
                 peticioDeFirmaID, baseUrl, numberTotalOfSignatures);
      return mav;
    }

    private ModelAndView commonFirma(HttpServletRequest request, HttpServletResponse response,
        Long estatDeFirmaID, Long peticioDeFirmaID, String baseUrl, 
        int numberTotalOfSignatures) throws I18NException {
      
      log.info("Entra a firmar Peticio = " + peticioDeFirmaID + " | EstatDeFirma = " + estatDeFirmaID);
      
      try {
        checkCanSignPeticioDeFirma(request, peticioDeFirmaID,estatDeFirmaID);
      } catch(I18NException e) {
        HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
        return llistatPaginat(request, response, null);
      }
      
      LoginInfo loginInfo = LoginInfo.getInstance();
      
      final String langUI = loginInfo.getUsuariPersona().getIdiomaID();
      
      Map<String, List<Long>> pluginsFirmaBySignatureID = new HashMap<String, List<Long>>();
      FileInfoFull fif = prepareFirmaItem(request, estatDeFirmaID, 
          peticioDeFirmaID, langUI, pluginsFirmaBySignatureID,
          loginInfo.getEntitatID(), numberTotalOfSignatures);



      EntitatJPA entitat = loginInfo.getEntitat();

      final String signaturesSetID= String.valueOf(peticioDeFirmaID + "_" + estatDeFirmaID);
      
      CommonInfoSignature commonInfoSignature;
      {
        final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();
        final String administrationID = loginInfo.getUsuariPersona().getNif();
        final UsuariAplicacioConfiguracioJPA config = configuracio(peticioDeFirmaID);
        commonInfoSignature = SignatureUtils.getCommonInfoSignature(entitat, config,
            langUI, username, administrationID);
      }

      // Vuls suposar que abans de 10 minuts haurà firmat
      Calendar caducitat = Calendar.getInstance();
      caducitat.add(Calendar.MINUTE, 10);

      //String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(request, getContextWeb());
      
      String relativeControllerBase = baseUrl +  getContextWeb();
      
      log.info(" XYZ ZZZ relativeControllerBase = " + relativeControllerBase);
      
      final String urlFinal = response.encodeURL(relativeControllerBase + "/finalFirma/" + signaturesSetID);

      PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(
          signaturesSetID, caducitat.getTime(), commonInfoSignature,
          new FileInfoSignature[] { fif.fileInfoSignature }, new int[] { fif.originalNumberOfSigns},
          entitat, urlFinal, true, baseUrl);

      signaturesSet.setPluginsFirmaBySignatureID(pluginsFirmaBySignatureID);

      final String view = "PluginDeFirmaContenidor_AutoFirma";
      ModelAndView mav = SignatureModuleController.startPrivateSignatureProcess(request, response, view, signaturesSet);

      return mav;
    }
    
    
    
    
    /** 
     * Quan acaba el mòdul de firma mostram espera de validacions de firma
     * @param request
     * @param response
     * @param signaturesSetID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/finalFirma/{signaturesSetID}")
    public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
        @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {
    
      log.info("\n\n XYZ ZZZ   ñññññññññ   PASSA PER finalProcesDeFirma\n\n");
      
      ModelAndView mav = new ModelAndView("public_wait");
      
      mav.addObject("finalURL", getContextWeb() + "/finalFirmaReal/" + signaturesSetID);
      
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
    @RequestMapping(value = "/finalFirmaReal/{signaturesSetID}")
    public ModelAndView finalProcesDeFirmaReal(HttpServletRequest request, HttpServletResponse response,
        @PathVariable("signaturesSetID") String signaturesSetID) throws Exception {
    
      log.info("\n\n XYZ ZZZ   ñññññññññ   PASSA PER finalProcesDeFirmaReal\n\n");
    
      SignaturesSetWeb ss;
      ss = SignatureModuleController.getSignaturesSetByID(request, signaturesSetID, modulDeFirmaEjb);

      StatusSignaturesSet sss = ss.getStatusSignaturesSet();
      
      StatusSignaturesSet statusError = null;
      
      switch(sss.getStatus()) {
      
        case StatusSignaturesSet.STATUS_FINAL_OK:
            signPostProcessOfSignaturesSet(request, ss);
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

      SignatureModuleController.closeSignaturesSet(request, signaturesSetID, modulDeFirmaEjb);
     
      ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

    
  /**
   * 
   * @param request
   * @param ss
   */
  public void signPostProcessOfSignaturesSet(HttpServletRequest request, SignaturesSetWeb ss) {

    FileInfoSignature[] signedFiles = ss.getFileInfoSignatureArray();
    
    final boolean isDebug = log.isDebugEnabled();
    
    int[] originalNumberOfSignsArray = ((PortaFIBSignaturesSet)ss).getOriginalNumberOfSignsArray();
    

    for (int i = 0; i < signedFiles.length; i++) {

      final FileInfoSignature signedFile = signedFiles[i];

      StatusSignature status = signedFile.getStatusSignature();

      // Per a que no es trepitji Thread ParallelSignedFilesProcessing i finalProcesDeFirma()
      synchronized (status) {

        // Potser ja s'han processat en el Thread   ParallelSignedFilesProcessing
        if (status.isProcessed()) {
          continue;
        }

        SignatureID signID = decodeSignatureID(signedFile.getSignID());

        final long estatDeFirmaID = signID.getEstatDeFirmaID();
        final long peticioDeFirmaID = signID.getPeticioDeFirmaID();
        final String token = signID.getToken();

        switch (status.getStatus()) {

        case StatusSignature.STATUS_FINAL_OK:

          File firmat = null;
          try {

            firmat = status.getSignedData();

            if (isDebug) {
              log.debug("firmat.getAbsolutePath(): " + firmat.getAbsolutePath());
            }

            peticioDeFirmaLogicaEjb.nouFitxerFirmat(firmat, estatDeFirmaID, peticioDeFirmaID,
                token, signedFile.getSignNumber(), originalNumberOfSignsArray[i]);

            status.setProcessed(true);

            if (isDebug) {
              log.debug("(FINAL)Processada Signature " + signedFile.getSignID() + " ...");
            }

          } catch (Throwable e) {

            if (firmat != null && firmat.exists()) {
              if (!firmat.delete()) {
                // TODO missatge d'error
                firmat.deleteOnExit();
              }
            }

            log.error(" CLASS = " + e.getClass());
            String msg;
            if (e instanceof I18NException) {
              I18NException i18ne = (I18NException) e;
              msg = I18NUtils.getMessage(i18ne);
              log.error("Error processant fitxer firmat (I18NException): " + msg, e);
            } else {
              msg = e.getMessage();
              log.error("Error processant fitxer firmat (Throwable): " + msg, e);
            }

            // TODO Traduir
            String fullMsg = "S´ha produit un error processant el fitxer firmat ´"
                + signedFile.getName() + "´: " + msg;

            HtmlUtils.saveMessageError(request, fullMsg);

            status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            status.setProcessed(true);

          }

          break; // FINAL DE CAS FIRMAT

        case StatusSignature.STATUS_FINAL_ERROR: {
          // Mostrar excepció per log
          // TODO traduir
          String msg = "S´ha produit un error durant la firma del fitxer  ´"
              + signedFile.getName() + "´: " + status.getErrorMsg();

          if (status.getErrorException() == null) {
            log.error(msg);
          } else {
            log.error(msg, status.getErrorException());
          }

          HtmlUtils.saveMessageError(request, msg);

          status.setProcessed(true);
        }
          break;

        default: {
          // TODO traduir
          String msg = "Ha finalitzat el process de firma amb ID " + ss.getSignaturesSetID()
              + " però el document firmat del fitxer ´" + signedFile.getName()
              + "´ no existeix i tampoc s´han descrit errors.";

          log.error(msg, new Exception());

          HtmlUtils.saveMessageWarning(request, msg);
          status.setProcessed(true);
        }

        }
      }
    }
  }
    
  /**
   * Serveix per anar processant les firmes finalitzades en cas de fer un firma
   * multiple.
   * 
   * @author anadal
   *
   */
  public class ParallelSignedFilesProcessing extends AbstractParallelSignedFilesProcessing {

    protected final PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    /**
     * @param signaturesSetID
     * @param peticioDeFirmaLogicaEjb
     * @param modulDeFirmaEjb
     */
    public ParallelSignedFilesProcessing(HttpServletRequest request, String signaturesSetID,
        PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb,
        ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
      super(request, signaturesSetID, modulDeFirmaEjb);
      
      this.peticioDeFirmaLogicaEjb = peticioDeFirmaLogicaEjb;
    }

    @Override
    public void process(FileInfoSignature signedFileInfo, File firmat, int originalNumberOfSigns) throws I18NException {
      SignatureID signID = decodeSignatureID(signedFileInfo.getSignID());

      final long estatDeFirmaID = signID.getEstatDeFirmaID();
      final long peticioDeFirmaID = signID.getPeticioDeFirmaID();
      final String token = signID.getToken();
      
     
      peticioDeFirmaLogicaEjb.nouFitxerFirmat(firmat, estatDeFirmaID, peticioDeFirmaID,
          token, signedFileInfo.getSignNumber(), originalNumberOfSigns);
      
    }

  }

    
    protected String encodeSignatureID(Long peticioDeFirmaID, Long estatDeFirmaID, String token) {
      return peticioDeFirmaID + "|" + estatDeFirmaID + "|" + token;
      
    }

    /**
     * [0] conte peticioDeFirmaID, [1] conte estatDeFirmaID i [2] conté token
     * @param encoded
     * @return
     */
    protected static SignatureID decodeSignatureID(String encoded) {
      
      String[] parts = encoded.split("\\|");

      return new SignatureID(Long.parseLong(parts[0]),Long.parseLong(parts[1]),parts[2]);
    }
    

   

    protected FileInfoFull prepareFirmaItem(HttpServletRequest request, Long estatDeFirmaID,
        Long peticioDeFirmaID, String langUI, Map<String, List<Long>> pluginsFirmaBySignatureID,
        String entitatID, int numberTotalOfSignatures) throws I18NException {

        CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
            ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
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
                    ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR)
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
            timeAliveToken = PropietatGlobalUtil.getMaxTimeLockedSignInMs(entitatID);
          }
          // Afegim 5 segons per cada firma de les peticions seleccionades
          timeAliveToken = timeAliveToken + numberTotalOfSignatures * 5000;
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
        final String mimeType;
        if (lastFirma == null) {
          log.debug("No hi ha firmes. La font del document és el document original");
          source = FileSystemManager.getFile(peticioDeFirma.getFitxerAdaptatID());
          mimeType = peticioDeFirma.getFitxerAdaptat().getMime();
        } else {
          if (log.isDebugEnabled()) {
            log.debug("La darrera firma és " + firma.getFirmaID() + " (#"
                + firma.getNumFirmaDocument() + ")");
          }
          source =  FileSystemManager.getFile(lastFirma.getFitxerFirmatID());
          mimeType = lastFirma.getFitxerFirmat().getMime();
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
        Locale localeSign = new Locale(langSign);
        Long colaDeleID = estatDeFirma.getColaboracioDelegacioID();
        UsuariPersona up = loginInfo.getUsuariPersona();
        if (colaDeleID == null) {
          // Destinatari
          if (firma.getMotiu() != null) {
            reason = firma.getMotiu();
          } else {
            reason = I18NUtils.tradueix(localeSign, "motiupeticiodirecta");
          }
        } else {
          // Delegat
          // Firma {0} (NIF {1}) per substitució de {2} (NIF {3}).Motiu: {4}
          // (ID={5})
          ColaboracioDelegacio colaDele = colaboracioDelegacioEjb.findByPrimaryKey(colaDeleID);

          UsuariEntitatJPA dest = usuariEntitatLogicaEjb.findByPrimaryKeyFull(colaDele
              .getDestinatariID());

          String motiu;
          if (firma.getMotiu() == null) {
            // El de la peticio
            motiu = peticioDeFirma.getMotiu();
          } else {
            // El de la firma
            motiu = firma.getMotiu();
          }
          
          UsuariPersona destUP = dest.getUsuariPersona();
          String[] args = {
              // Delegat
              up.getNom() + " " + up.getLlinatges(), // {0} Nom del delegat
              up.getNif(),            // {1} NIF del delegat
              // Destinatari Original
              destUP.getNom() + " " + destUP.getLlinatges(),  // {2} Nom del destinatari
              destUP.getNif(),              // {3} NIF del destinatari
              colaDele.getMotiu(),          // {4} Motiu de la delegació
              motiu,    // {5} Motiu de la peticio de firma
              };
          String basemsg = SignatureUtils.getMotiuDeFirmaFormat(entitat, langSign);
          MessageFormat mf = new MessageFormat(basemsg);
          reason = mf.format(args);
        }
        
        // XYZ TODO FALTA
        final String location = null;

        final String signerEmail = up.getEmail(); 

        // Construir Objecte
        final String idname = peticioDeFirma.getFitxerAFirmar().getNom();

        final String firmatPerFormat = SignatureUtils.getFirmatPerFormat(entitat, null, langSign);

       final String signatureID = encodeSignatureID(peticioDeFirmaID, estatDeFirmaID, token);
       
       // S'ha d'obtenir de la PeticioDeFirma
       
       // Nous camps de Peticio de Firma #281
       ITimeStampGenerator timeStampGenerator;
       // Política de firma #283 -> #287
       PolicyInfoSignature policyInfoSignature;
       switch (peticioDeFirma.getOrigenPeticioDeFirma()) {
          
          case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:          
          {
            boolean userRequiresTimeStamp = peticioDeFirma.isSegellatDeTemps();
            timeStampGenerator = segellDeTempsEjb.getTimeStampGeneratorForWeb(entitat, userRequiresTimeStamp);
            policyInfoSignature = SignatureUtils.getPolicyInfoSignature(entitat, null);
          }
          break;

          case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
          {
            boolean userRequiresTimeStamp = peticioDeFirma.isSegellatDeTemps();
            UsuariAplicacioConfiguracio configuracioDefirma = null;
            if (peticioDeFirma.getConfiguracioDeFirmaID() != null) {
              configuracioDefirma = configuracioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());
            }
            policyInfoSignature = SignatureUtils.getPolicyInfoSignature(entitat, configuracioDefirma);

            if (userRequiresTimeStamp) {
              if (configuracioDefirma == null) {
                // Per peticions Antigues
                timeStampGenerator = segellDeTempsEjb.getTimeStampGeneratorForWeb(entitat, userRequiresTimeStamp);
              } else {
                timeStampGenerator = segellDeTempsEjb.getTimeStampGeneratorForUsrApp(
                    peticioDeFirma.getSolicitantUsuariAplicacioID(), entitat, configuracioDefirma);
              }
            } else {
              timeStampGenerator = null;
            }
          }
          break;
          
          case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
          {
            UsuariAplicacioConfiguracio configuracioDefirma;
            configuracioDefirma = configuracioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());

            timeStampGenerator = segellDeTempsEjb.getTimeStampGeneratorForUsrApp(
                  peticioDeFirma.getSolicitantUsuariAplicacioID(), entitat, configuracioDefirma);

            policyInfoSignature = SignatureUtils.getPolicyInfoSignature(entitat, configuracioDefirma);
          }
          break;
            
          default:
           // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi"," No hi ha codi per el Segellat de Temps de les Peticions de Firma amb Origen " + 
              I18NUtils.tradueix("origenpeticiodefirma." + peticioDeFirma.getOrigenPeticioDeFirma()));
        }

       // Seleccionar si existeixen restriccionas de PLugins de Firma segons els 
       // Tipus de Document de la Petició de Firma
       {
         Long tipusDoc = peticioDeFirma.getTipusDocumentID(); 
         Where where = Where.AND(
             new ModulDeFirmaPerTipusDeDocumentQueryPath().PLUGIN().
               ENTITATID().equal(LoginInfo.getInstance().getEntitatID()),
               ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID.equal(tipusDoc)
               );
  
         List<Long> pluginsID = modulDeFirmaPerTipusDeDocumentEjb.executeQuery(
             ModulDeFirmaPerTipusDeDocumentFields.PLUGINID.select, where);
         if (pluginsID != null && pluginsID.size() != 0) {
           log.info("Pel tipus de document " + tipusDoc + " i l'entitat " 
             + LoginInfo.getInstance().getEntitatID() + " hi ha assignats els plugins "
             +   Arrays.toString(pluginsID.toArray()));
           pluginsFirmaBySignatureID.put(signatureID, pluginsID);
         }
       }
       
       
       // Cercar el numero de firmes del document original
       // XYZ ZZZ Només per PDF !!!!!
       File originalDoc = FileSystemManager.getFile(peticioDeFirma.getFitxerAdaptatID());
       final int originalNumberOfSigns = PdfUtils.getNumberOfSignaturesInPDF(originalDoc);
      
       
       //  #174
       final String expedientCode = peticioDeFirma.getExpedientCodi();
       final String expedientName = peticioDeFirma.getExpedientNom();
       final String expedientUrl = peticioDeFirma.getExpedientUrl();
       final String procedureCode = peticioDeFirma.getProcedimentCodi();
       final String procedureName = peticioDeFirma.getProcedimentNom();

       return new FileInfoFull(SignatureUtils.getFileInfoSignature(signatureID, source,mimeType,
            idname, location_sign_table, reason, location, signerEmail,  sign_number, 
            langSign, peticioDeFirma.getTipusFirmaID(), peticioDeFirma.getAlgorismeDeFirmaID(),
            peticioDeFirma.getModeDeFirma(), firmatPerFormat, timeStampGenerator, policyInfoSignature,
            expedientCode, expedientName, expedientUrl, procedureCode, procedureName),
            originalNumberOfSigns);
    }


    @RequestMapping(value = "/rebutjar/{estatDeFirmaID}/{peticioDeFirmaID}")
    public ModelAndView rebutjar(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

      rebutjarInternal(request, response, estatDeFirmaID, peticioDeFirmaID);
      return llistatPaginat(request, response, null);
    }

    private void rebutjarInternal(HttpServletRequest request, HttpServletResponse response,
                                  Long estatDeFirmaID, Long peticioDeFirmaID) throws I18NException {
      final long estatFirmaInicial;
      if (ConstantsV2.ROLE_REVI.equals(getRole())) {
        estatFirmaInicial = ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR;
      } else {
        estatFirmaInicial = ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR;
      }

      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,estatFirmaInicial);
      if (check == null) {
        // S'ha produit un error. Retornam.
        return;
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
        return;
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
          
          rebutjarInternal(request, response, estatDeFirmaID, peticioDeFirmaID);
        }
      }
      
      
      return llistatPaginat(request, response, null);
    }
    
    
    
    
    
    

    @RequestMapping(value = "/validar/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView validar(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, true,
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
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
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
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
            ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
            ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
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

    /**
     * Retorna la configuració de firma d'una petició.
     * @param peticioDeFirmaID
     * @return
     */
    private UsuariAplicacioConfiguracioJPA configuracio(Long peticioDeFirmaID) {

      UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio = null;

      PeticioDeFirma peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirmaID);

      switch (peticioDeFirma.getOrigenPeticioDeFirma()) {

        case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:

          if (peticioDeFirma.getConfiguracioDeFirmaID() != null) {
            usuariAplicacioConfiguracio = configuracioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());
          }
          break;

        case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:

          usuariAplicacioConfiguracio = configuracioDeFirmaLogicaEjb.findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());
          break;
      }

      return usuariAplicacioConfiguracio;
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
          .findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
        return null;
      }
      if (checkEstat) {
        int estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
        if ((estat != ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES)
            && (estat != ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT)) {
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
          log.error("L'estat de firma no te el tipus inicial correcte: "
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
        mapPF = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_TITOL).getValueMap();
        mapPF.clear();
        
        Map<Long, String> mapTD;
        mapTD = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_TIPUSDOC).getValueMap();
        mapTD.clear();
        
        final boolean remitent = role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE);
        Map<Long, String> mapCR = null;
        if (remitent) {          
          mapCR = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_REMITENT).getValueMap();
          mapCR.clear();
        }

        for(Long estatDeFirmaId : peticionsByEstat.keySet()) {
           PeticioDeFirmaJPA pf = (PeticioDeFirmaJPA)peticionsByEstat.get(estatDeFirmaId);
           
           mapPF.put(estatDeFirmaId, pf.getTitol());
           mapTD.put(estatDeFirmaId, pf.getTipusDocument().getNomTraduccions().get("ca").getValor());
           
           if (remitent) {
             StringBuffer str = new StringBuffer();
             str.append("<small><b>");
             str.append(pf.getRemitentNom()).append("</b>");
             String remiDesc = pf.getRemitentDescripcio();
             if (remiDesc != null) {
               str.append("<br/>").append(remiDesc);
             }
             str.append("</small>");
             mapCR.put(estatDeFirmaId,  str.toString());
           }
           
           annexesByPeticio.put(pf.getPeticioDeFirmaID(),
             annexEjb.count(AnnexFields.PETICIODEFIRMAID.equal(pf.getPeticioDeFirmaID())));
           
        }
        
      }
      
      
      org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat dateTimeFormat;
      dateTimeFormat = new org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat();
      
      

      
      List<Long> estatFirmaIDs = new ArrayList<Long>();
      
      // DATA INICI 
      {
        Map<Long, String> mapDI;
        mapDI = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_ESTATDEFIRMA_DATAINICI_SMALL).getValueMap();
        mapDI.clear();

        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
          mapDI.put(estatDeFirma.getEstatDeFirmaID(), 
              "<small>" + dateTimeFormat.format(estatDeFirma.getDataInici()).replace(" ", "<br/>") + "</small>");
          estatFirmaIDs.add(estatDeFirma.getEstatDeFirmaID());
        }
      }


      // Informacio Addicional Avaluable
      {

        DoubleField df = new EstatDeFirmaQueryPath().FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().INFORMACIOADDICIONALAVALUABLE();
   
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(EstatDeFirmaFields.ESTATDEFIRMAID.select,
            df.select);
        
        //Long count = estatDeFirmaEjb.count(Where.AND(EstatDeFirmaFields.ESTATDEFIRMAID.in(estatFirmaIDs),df.isNotNull()));
        
        Where w = Where.AND(EstatDeFirmaFields.ESTATDEFIRMAID.in(estatFirmaIDs),df.isNotNull());
        
        List<StringKeyValue> list = estatDeFirmaEjb.executeQuery(smskv, w);
        
        
        if (list != null && list.size() > 0) {
          // Si hi elements llevam la columna del items a OCULTAR
          filterForm.getHiddenFields().remove(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);

          Map<Long, String> mapIAA;
          mapIAA = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE).getValueMap();
          mapIAA.clear();
          
          for (StringKeyValue skv : list) {
            mapIAA.put(new Long(skv.getKey()), skv.getValue());
          }
          
          
        } else {
          // Si no hiha elments, l'afegim als items a ocultar
          filterForm.addHiddenField(COLUMN_PETICIODEFIRMA_INFO_ADDICIONAL_AVALUABLE_FIELD);
        }
        
      }
      
      
      // DATA FI
      if (getFilterType() != FILTRAR_PER_PENDENT) {

        Map<Long, String> mapDF;
        mapDF = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_ESTATDEFIRMA_DATAFI_SMALL).getValueMap();
        mapDF.clear();
        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
          mapDF.put(estatDeFirma.getEstatDeFirmaID(), 
              "<small>" + dateTimeFormat.format(estatDeFirma.getDataFi()).replace(" ", "<br/>") + "</small>");
        }
      }  


      List<Long> estatsID = new ArrayList<Long>();
      if (role.equals(ConstantsV2.ROLE_COLA) || role.equals(ConstantsV2.ROLE_DELE)) {
        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {
          // Crec que aquest if es innecesari !!!
          if (estatDeFirma.getColaboracioDelegacioID() != null) {
            estatsID.add(estatDeFirma.getEstatDeFirmaID());
          }
        }
      }
      
      
      // Delegat de Colaborador
      if (role.equals(ConstantsV2.ROLE_COLA)) { 
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
      if (role.equals(ConstantsV2.ROLE_DEST)) {
        
        Map<Long, String> mapDD = new HashMap<Long, String>();
        Map<Long, int[]> infoDelegatsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
            ESTATS_INICIALS_DELE);
        
        boolean existeixenDelegacions = false;

        for(Long estatDeFirmaId :  infoDelegatsByEstat.keySet()) {
      
          int[] valors = infoDelegatsByEstat.get(estatDeFirmaId);
          StringBuffer str = new StringBuffer();
          if (valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.2")
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          
          if (valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_ACCEPTAT + 2] != 0) {
            str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.5")
                + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_ACCEPTAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          
          if (valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2] != 0) {
            str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.3")
                + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT + 2] + "/" + valors[0] + "</small><br/>\n");
          }
          if (valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0) {             
            str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.4")
                + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] + "/" + valors[0] + "</small><br/>\n");
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

      if ((role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE)) 
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
           if(valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.VALIDAT") 
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2]  + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.INVALIDAT")
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT + 2] != 0) {           
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.DESCARTAT")
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT+ 2] + "/" + valors[0] + "</small><br/>\n");
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
      
      
      
      
      // Revisors
      if (role.equals(ConstantsV2.ROLE_REVI)) {
        
        Map<Long, String> mapCC = new HashMap<Long, String>();

        boolean existeixenRevisors = false;
        
        
        Map<Long, int[]> infoColaboradorsByEstat = infoColaboradorsDelegats(estatDeFirmaList,
            ESTATS_INICIALS_REVI);
        //mav.addObject("infoColaboradorsByEstat", infoColaboradorsByEstat);
        
        for(Long estatDeFirmaId :  infoColaboradorsByEstat.keySet()) {
        
           int[] valors=infoColaboradorsByEstat.get(estatDeFirmaId);
           StringBuffer str = new StringBuffer();
          
           
           if(valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_ACCEPTAT + 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.VALIDAT") 
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT + 2]  + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT+ 2] != 0) {
             str.append("<small>" + I18NUtils.tradueix("tipusestatdefirmafinal.INVALIDAT")
                 + ": " + valors[(int)ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT+ 2] + "/" + valors[0] + "</small><br/>\n");
           }
           if(valors[1] != 0) {
             str.append("<small>" + I18NUtils.tradueix("pendent") 
                 + ": " + valors[1] + "/" + valors[0]  + "</small><br/>\n");
           }
           
           if (str.length() != 0) {
             existeixenRevisors = true;
           }
           
           mapCC.put(estatDeFirmaId, str.toString());
        }
        

        // Ocultar columna si esta buida
        if (!existeixenRevisors) {
          filterForm.getAdditionalFields().remove(COLUMN_REVISORS);
        } else {

          AdditionalField<Long,String> adfieldDD;
          adfieldDD = (AdditionalField<Long,String>)filterForm.getAdditionalFields().get(COLUMN_REVISORS);
          
          if (adfieldDD == null) {
            // NOVA COLUMNA si no esta creada

            adfieldDD = new AdditionalField<Long,String>(); 
            adfieldDD.setCodeName("revisor.short");
            adfieldDD.setPosition(COLUMN_REVISORS);
            // Els valors s'ompliran al mètode postList()
            adfieldDD.setEscapeXml(false);
            
            filterForm.addAdditionalField(adfieldDD);
          }
          
          adfieldDD.setValueMap(mapCC);

        }

      } else {
        if (ocultarColumnaColaboradors) {
          filterForm.getAdditionalFields().remove(COLUMN_REVISORS);
        }
      }
      
      
      
      
      {
        
        Map<Long, String> mapPR;
        mapPR = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_PRIORITAT).getValueMap();
        mapPR.clear();
        
        String color_priority, title_priority;
        for(Long estatDeFirmaId : peticionsByEstat.keySet()) {
          int prioritatID = peticionsByEstat.get(estatDeFirmaId).getPrioritatID();
          title_priority="prioritat." + prioritatID;
          
          if (prioritatID <= ConstantsV2.PRIORITAT_BAIXA) {
             color_priority="btn-success";             
          } else if (prioritatID >= ConstantsV2.PRIORITAT_ALTA) {
              color_priority="btn-danger";
          } else {
              color_priority="btn-warning";
          }

          mapPR.put(estatDeFirmaId, "<button title=\""+ I18NUtils.tradueix(title_priority) + "\" "
              + " class=\"btn btn-mini " + color_priority + "\" type=\"button\">&nbsp;</button>");         
         }

      }
      
      // TODO Només mostrar en les pantalles Pendents
      if (role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE)) {


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
      
        /* VEURE DOC */
        filterForm.addAdditionalButtonByPK(estatDeFirmaId, new AdditionalButton(
          "icon-file", "veuredoc",
          "javascript:var win = window.open('" + request.getContextPath() + getContextWeb() + "/docfirmat/" + peticioID + "', '_blank'); win.focus();"
           , "btn-info"));
        
        /* DESCARREGAR DOC */
        filterForm.addAdditionalButtonByPK(estatDeFirmaId, new AdditionalButton(
            "icon-download-alt", "descarregardoc", 
             // getContextWeb() + "/docfirmat/" + peticioDeFirmaID,
            "javascript:var win = window.open('" + request.getContextPath() + getContextWeb() + "/docfirmat/descarregar/" + peticioID + "', '_blank'); win.focus();"
            , "btn-info") );
        
        
        // Comprovar si hi ha anexes
        if (annexesByPeticio.get(peticioID) != 0) {
          filterForm.addAdditionalButtonByPK(estatDeFirmaId,
              new AdditionalButton("icon-folder-open", "annex.annex.plural",
              getContextWeb() + "/viewDocuments/" + estatDeFirmaId + "/" + peticioID , "btn-info"));

        }
        
      }
      
       // Full view
       for (EstatDeFirma ef : estatDeFirmaList) {
            final long estatInicial = ef.getTipusEstatDeFirmaInicialID(); 
            if (estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR
                || estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR) {
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
           
           
           if (estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR) {
             
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-check", "revisor.acceptar",
                  "javascript:acceptar('" + request.getContextPath() + getContextWeb() + "/acceptar/" + estatId + "/" + peticioID + "', {0})" ,
                  "btn-success"));
             
          
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-remove", "rebutjar",
                 "javascript:rebutjar('" + request.getContextPath() +  getContextWeb() + "/rebutjar/" + estatId + "/" + peticioID + "'," + estatId + ")" ,
                  "btn-danger"));

           }
           
           
           
           
           
           if (estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
             
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-edit", "firmar",
                  "javascript:firmar('" + request.getContextPath() + getContextWeb() + "/firmar/" + estatId + "/" + peticioID + "', {0})" ,
                  "btn-success"));
             
          
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-remove", "rebutjar",
                 "javascript:rebutjar('" + request.getContextPath() +  getContextWeb() + "/rebutjar/" + estatId + "/" + peticioID + "'," + estatId + ")" ,
                  "btn-danger"));

           }
        
           
           if (estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR) {
             // TODO Indicar la descripció d'aquest botó: traducció marcarrevisant.desc
             filterForm.addAdditionalButtonByPK(estatId,
                 new AdditionalButton("icon-flag", "marcarrevisant",
                 getContextWeb() + "/marcarrevisant/" + estatId + "/" + peticioID ,
                  "btn-warning"));
           }
        
           if (estatInicial ==  ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR 
               || estatInicial == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR) {
               
             
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
  
         if (role.equals(ConstantsV2.ROLE_DEST) || role.equals(ConstantsV2.ROLE_DELE)) {
           if (this.getFilterType() == FILTRAR_PER_PENDENT) {
             filterForm.setVisibleMultipleSelection(true);
           } else if (getFilterType() == FILTRAR_PER_RES) {
             for (EstatDeFirma ef : estatDeFirmaList) {
               if (ef.getTipusEstatDeFirmaInicialID() == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR
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
      if (role.equals(ConstantsV2.ROLE_DEST)) {
        // Els estats de firma de destinatari són aquells que:
        // (1) estat inicial es ASSIGNAT PER FIRMAR
        // (2) COLABORACIODELEGACIOID es null
        roleWhere = Where.AND(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
            .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
            EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull());

      } else  if (role.equals(ConstantsV2.ROLE_DELE)) {
          // Els estats de firma de delegat són aquells que:
          // (1) estat inicial es ASSIGNAT PER FIRMAR
          // (2) COLABORACIODELEGACIOID es not null
          roleWhere = Where.AND(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
              .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
              EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull());
        } else if (role.equals(ConstantsV2.ROLE_COLA)) {
            // Els estats de firma de colaborador són aquells que:
            // (1) Els estats inicials poden ser ASSIGNAT_PER_VALIDAR o
            // REVISANT_PER_VALIDAR
            // (2) COLABORACIODELEGACIOID es not null
            roleWhere = Where.AND(Where.OR(EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR),
                EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                    .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR)),
                EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull());
        } else if (role.equals(ConstantsV2.ROLE_REVI)) {
          // Els estats de firma de REVISOR són aquells que:
          // (1) L'estat inicial pot ser ASSIGNAT_PER_REVISAR
          // (2) COLABORACIODELEGACIOID es null
          roleWhere = Where.AND(
              EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
              .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR),
              EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull());
          
        } else {
            // TODO Traduir
            throw new I18NException("error.unknown","No hi ha gestió de EstatDeFirma pel role " + role);
        }
        

      
      
      Where estatWhere;
      switch(getFilterType()) {
        case FILTRAR_PER_PENDENT: // Pendent de firma o de de validacio
          estatWhere = TIPUSESTATDEFIRMAFINALID.isNull();
        break;
        
        case FILTRAR_PER_ACCEPTAT: // Firmat o validat
          if (role.equals(ROLE_COLA)) {
            estatWhere = TIPUSESTATDEFIRMAFINALID.equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT);
          } else if (role.equals(ROLE_REVI)) {
              // Revisor Acceptat
              estatWhere = TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_ACCEPTAT);
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
            estatWhere = TIPUSESTATDEFIRMAFINALID.equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
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

      Where full = Where.AND(roleWhere, estatWhere, super.getAdditionalCondition(request),
          EstatDeFirmaFields.USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID())); 

      return full;
    }

    
    @RequestMapping(value = "/viewDocuments/{estatDeFirmaID}/{peticioDeFirmaID}", method = RequestMethod.GET)
    public ModelAndView viewDocumentsFullView(HttpServletRequest request, HttpServletResponse response,
        @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {
      
      // p.e. viewDocuments_ROLE_DEST
      String view = "viewDocumentsFullView_" + getRole();
      
      CheckInfo check = checkAll(estatDeFirmaID, peticioDeFirmaID, request, false,
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
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
          ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
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
        List<EstatDeFirmaJPA> estatsRevisors = new ArrayList<EstatDeFirmaJPA>();

        for (EstatDeFirmaJPA estat : fullList) {
          
          final long estatInicial = estat.getTipusEstatDeFirmaInicialID();

          if (estatInicial == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR) {
            estatsRevisors.add(estat);
          } else if (estat.getColaboracioDelegacioID() == null) {
            mav.addObject("destinatari", estat);
          } else {
            if (estatInicial == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
              estatsDeDelegats.add(estat);
            } else {
              estatsColaboradors.add(estat);
            }
          }

        }

        mav.addObject("colaboradors", estatsColaboradors);
        mav.addObject("delegats", estatsDeDelegats);
        mav.addObject("revisors", estatsRevisors);
      }
      

      // 1.- Fitxers a visualitzar
      procesFitxersAVeure(mav, peticioDeFirmaID, peticioDeFirma);


      // Traduccions
      // Estats Finals d'un EstatDeFirma
      Map<Long, String> traduccions = new HashMap<Long, String>();

      traduccions.put(TIPUSESTATDEFIRMAFINAL_VALIDAT, "tipusestatdefirmafinal.0");
      traduccions.put(TIPUSESTATDEFIRMAFINAL_INVALIDAT, "tipusestatdefirmafinal.1");
      traduccions.put(TIPUSESTATDEFIRMAFINAL_FIRMAT, "tipusestatdefirmafinal.2");
      traduccions.put(TIPUSESTATDEFIRMAFINAL_REBUTJAT, "tipusestatdefirmafinal.3");
      traduccions.put(TIPUSESTATDEFIRMAFINAL_DESCARTAT, "tipusestatdefirmafinal.4");
      traduccions.put(TIPUSESTATDEFIRMAFINAL_ACCEPTAT, "tipusestatdefirmafinal.5");
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
        if (tipusFirmaID == ConstantsV2.TIPUSFIRMA_PADES) {
          FitxerJPA f;
          f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

          f.setNom(peticioDeFirma.getFitxerAFirmar().getNom());
          
          fitxers.add(new KeyValue<FitxerJPA>(f, String.valueOf(ConstantsV2.DOC_PDF)));
        } else {
          FitxerJPA f;
          if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
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

      if (mime.equals(ConstantsV2.MIME_TYPE_PDF)) {
        return String.valueOf(ConstantsV2.DOC_PDF);
      }

      if (mime.startsWith("image/")) {
        return String.valueOf(ConstantsV2.DOC_IMG);
      }

      if (mime.startsWith("text/") || mime.equals("application/xml")) {
        return String.valueOf(ConstantsV2.DOC_TXT);
      }

      return String.valueOf(ConstantsV2.DOC_BIN);

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

      final boolean attachment = false;
      FileDownloadController.fullDownload(f.getFitxerID(), f.getNom(), f.getMime(), response, attachment);
    }
    
    
    @RequestMapping(value = "/docfirmat/descarregar/{peticioDeFirmaID}", method = RequestMethod.GET)
    public void docfirmatDescarregar(HttpServletResponse response, @PathVariable Long peticioDeFirmaID)
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

      final boolean attachment = true;
      FileDownloadController.fullDownload(f.getFitxerID(), f.getNom(), f.getMime(), response, attachment);
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

        case FILTRAR_PER_NODEFINIT: // Col·laboracions ignorades
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
    

    public static class FileInfoFull {
      public final FileInfoSignature fileInfoSignature;
      public final int originalNumberOfSigns;
      /**
       * @param fileInfoSignature
       * @param originalNumberOfSigns
       */
      public FileInfoFull(FileInfoSignature fileInfoSignature, int originalNumberOfSigns) {
        super();
        this.fileInfoSignature = fileInfoSignature;
        this.originalNumberOfSigns = originalNumberOfSigns;
      }
      
      
      
    }
    
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    for (long tipus : ConstantsV2.TIPUSESTATDEFIRMAINICIAL) {
      __tmp.add(new StringKeyValue(String.valueOf(tipus), I18NUtils
          .tradueix("tipusestatdefirmafinal." + tipus)));
    }
    return __tmp;
  }

  // #199
  @Override
  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaInicialID(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    for (long tipus : ConstantsV2.TIPUSESTATDEFIRMAINICIAL) {
      __tmp.add(new StringKeyValue(String.valueOf(tipus), I18NUtils
          .tradueix("tipusestatdefirmainicial." + tipus)));
    }
    return __tmp;
  }

} // Final de Classe



