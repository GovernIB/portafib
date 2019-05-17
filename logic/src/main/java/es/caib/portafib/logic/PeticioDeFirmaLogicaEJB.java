package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.ColaboracioDelegacioLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.PeticioDeFirmaEJB;
import es.caib.portafib.ejb.PropietatGlobalLocal;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.ejb.UsuariPersonaLocal;
import es.caib.portafib.jpa.AnnexFirmatJPA;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.validator.PeticioDeFirmaBeanValidator;
import es.caib.portafib.logic.events.FirmaEventList;
import es.caib.portafib.logic.events.FirmaEventManagerLocal;
import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.EmailInfo;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.FitxerIdDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.logic.validator.PeticioDeFirmaLogicValidator;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.BitacolaFields;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioQueryPath;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.MetadadaFields;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.PropietatGlobalFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.plugins.documentcustody.api.CustodyException;
import org.fundaciobit.plugins.documentcustody.api.DocumentCustody;
import org.fundaciobit.plugins.documentcustody.api.IDocumentCustodyPlugin;
import org.fundaciobit.plugins.documentcustody.api.NotSupportedCustodyException;
import org.fundaciobit.plugins.documentcustody.api.SignatureCustody;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.barcode.IBarcodePlugin;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NCommonDateTimeFormat;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectCount;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.SelectSum;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.LongConstantField;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PeticioDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class PeticioDeFirmaLogicaEJB extends PeticioDeFirmaEJB implements
    PeticioDeFirmaLogicaLocal, ConstantsV2 {

  @EJB(mappedName = "portafib/AnnexFirmatEJB/local")
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;

  @EJB(mappedName = "portafib/BitacolaEJB/local")
  protected es.caib.portafib.ejb.BitacolaLocal bitacolaEjb;

  @EJB(mappedName = "portafib/FitxerLogicaEJB/local")
  private FitxerLogicaLocal fitxerLogicaEjb;

  @EJB(mappedName = "portafib/NotificacioWSEJB/local")
  protected es.caib.portafib.ejb.NotificacioWSLocal notificacioWsEjb;

  @EJB(mappedName = "portafib/FirmaEventManagerEJB/local")
  private FirmaEventManagerLocal firmaEventManagerEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  private EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  @EJB(mappedName = "portafib/BlocDeFirmesEJB/local")
  private BlocDeFirmesLocal blocDeFirmesEjb;

  @EJB(mappedName = "portafib/FirmaLogicaEJB/local")
  private FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = "portafib/ColaboracioDelegacioEJB/local")
  private ColaboracioDelegacioLocal colaboracioDelegacioEjb;

  @EJB(mappedName = "portafib/TipusDocumentColaboracioDelegacioEJB/local")
  private TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;

  @EJB(mappedName = "portafib/RoleUsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.RoleUsuariEntitatLocal roleUsuariEntitatEjb;

  @EJB(mappedName = "portafib/MetadadaLogicaEJB/local")
  protected MetadadaLogicaLocal metadadaLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  private UsuariEntitatLocal usuariEntitatEjb;

  @EJB(mappedName = UsuariPersonaLocal.JNDI_NAME)
  protected UsuariPersonaLocal usuariPersonaEjb;

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;

  @EJB(mappedName =  AnnexLogicaLocal.JNDI_NAME)
  private AnnexLogicaLocal annexLogicaEjb;

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  @EJB(mappedName = PluginDeCustodiaLogicaLocal.JNDI_NAME)
  private PluginDeCustodiaLogicaLocal pluginDeCustodiaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  private es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

  @EJB(mappedName = PropietatGlobalLocal.JNDI_NAME)
  protected PropietatGlobalLocal propietatGlobalEjb;

  @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeFirmaLocal revisorDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EstadisticaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EstadisticaLocal estadisticaEjb;

  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

  private PeticioDeFirmaLogicValidator<PeticioDeFirmaJPA> validator = new PeticioDeFirmaLogicValidator<PeticioDeFirmaJPA>();

  @Resource
  private SessionContext context;

  @Override
  public List<PeticioDeFirmaJPA> selectFull(Where where) throws I18NException {
    List<PeticioDeFirma> peticions = select(where);

    if (peticions == null) {
      return null;
    }

    List<PeticioDeFirmaJPA> peticionsJPA = new ArrayList<PeticioDeFirmaJPA>();
    for (PeticioDeFirma peticio : peticions) {
      PeticioDeFirmaJPA peticioDeFirma = (PeticioDeFirmaJPA) peticio;

      initializateFull(peticioDeFirma);

      peticionsJPA.add(peticioDeFirma);
    }

    return peticionsJPA;
  }

  @Override
  public PeticioDeFirmaJPA updateFull(PeticioDeFirmaJPA peticioDeFirma) throws I18NException,
      I18NValidationException {

    PeticioDeFirmaBeanValidator pfbv = new PeticioDeFirmaBeanValidator(validator,
        custodiaInfoLogicaEjb, fluxDeFirmesLogicaEjb, idiomaEjb, this, tipusDocumentEjb,
        usuariAplicacioEjb, usuariEntitatEjb);

    final boolean isNou = false;
    pfbv.throwValidationExceptionIfErrors(peticioDeFirma, isNou);

    return (PeticioDeFirmaJPA) this.update(peticioDeFirma);
  }

  /**
   * 
   * @param peticioDeFirma
   * @param peticioUuariEntitat
   *          Pot valer null. En aquest cas
   * @return
   */
  @Override
  public PeticioDeFirmaJPA createFull(PeticioDeFirmaJPA peticioDeFirma) throws I18NException,
      I18NValidationException {

    if (peticioDeFirma == null) {
      return null;
    }

    // Crear Flux si és necessari
    long fluxID = peticioDeFirma.getFluxDeFirmesID();

    if (fluxID == 0) {
      FluxDeFirmesJPA flux = peticioDeFirma.getFluxDeFirmes();
      if (flux != null) {
        // TODO Validate FLUX de Firmes
        flux = fluxDeFirmesLogicaEjb.createFull(flux);
        peticioDeFirma.setFluxDeFirmesID(flux.getFluxDeFirmesID());
      }
    }

    PeticioDeFirmaBeanValidator pfbv = new PeticioDeFirmaBeanValidator(validator,
        custodiaInfoLogicaEjb, fluxDeFirmesLogicaEjb, idiomaEjb, this, tipusDocumentEjb,
        usuariAplicacioEjb, usuariEntitatEjb);

    final boolean isNou = true;
    pfbv.throwValidationExceptionIfErrors(peticioDeFirma, isNou);

    // Validar Annexes: només són vàlids els valors true-true 
    // i false-false per adjuntar-firma
    {
      Set<AnnexJPA> annexes = peticioDeFirma.getAnnexs();
      if (annexes != null && annexes.size() != 0) {
        for (AnnexJPA annex : annexes) {
          if (annex.isAdjuntar() != annex.isFirmar()) {
            List<I18NFieldError> camps = new ArrayList<I18NFieldError>();
            camps.add(
                new I18NFieldError(AnnexFields.ADJUNTAR, new I18NTranslation("peticiodefirma.annexos.novalid"))
                );
            camps.add(
                new I18NFieldError(AnnexFields.FIRMAR, new I18NTranslation("peticiodefirma.annexos.novalid")
                ));
            throw new I18NValidationException(camps);
          }
        }
      }
    }

    // Crear Peticio
    Calendar cal = Calendar.getInstance();
    peticioDeFirma.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));

    // final String entitatID;
    final String usuariAplicacioID;
    final UsuariAplicacioJPA usuariAplicacio;
    {
      // Peticio de usuari Aplicacio

      usuariAplicacioID = peticioDeFirma.getSolicitantUsuariAplicacioID();
      if (peticioDeFirma.getRemitentNom() == null) {
        peticioDeFirma.setRemitentNom(usuariAplicacioID);
      }
      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
      // entitatID = usuariAplicacio.getEntitatID();
    }

    final String usuariEntitatID;
    final UsuariEntitatJPA usuariEntitat;
    if (peticioDeFirma.getSolicitantUsuariEntitat1ID() == null) {
      // Peticio via UsrApp
      // #186
      if (PropietatGlobalUtil.isDisabledSignaturesTable()) {
        if (peticioDeFirma != null) {
          peticioDeFirma.setPosicioTaulaFirmesID(ConstantsV2.TAULADEFIRMES_SENSETAULA); // = 0
        }
      }

      usuariEntitatID = null;
      usuariEntitat = null;
    } else {
      // Peticio de usuari web

      usuariEntitatID = peticioDeFirma.getSolicitantUsuariEntitat1ID();

      usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
      if (peticioDeFirma.getRemitentNom() == null) {
        UsuariPersona persona;
        persona = usuariPersonaEjb.findByPrimaryKey(usuariEntitat.getUsuariPersonaID());

        // UsuariPersona persona = usuariEntitat.getUsuariPersona();
        peticioDeFirma.setRemitentNom(persona.getNom() + " " + persona.getLlinatges());
        if (peticioDeFirma.getRemitentDescripcio() == null) {
          peticioDeFirma.setRemitentDescripcio(usuariEntitat.getEmail() == null ? persona
              .getEmail() : usuariEntitat.getEmail());
        }
      }
      // entitatID = usuariEntitat.getEntitatID();
    }

    // ======= Check de Custòdia ==========
    {
      CustodiaInfo ci = custodiaInfoLogicaEjb.getAllowedCustodyInfo(peticioDeFirma,
          usuariAplicacio, usuariEntitat);

      if (ci == null) {
        peticioDeFirma.setCustodiaInfoID(null);
      } else {

        CustodiaInfoJPA custodiaInfo = CustodiaInfoJPA.toJPA(ci);

        custodiaInfo = (CustodiaInfoJPA) custodiaInfoLogicaEjb.create(custodiaInfo);

        peticioDeFirma.setCustodiaInfoID(custodiaInfo.getCustodiaInfoID());
      }

    }

    // --- FINAL CHECK CUSTODIA

    PeticioDeFirmaJPA pf = null;
    pf = (PeticioDeFirmaJPA) create(peticioDeFirma);

    Long peticioDeFirmaID = pf.getPeticioDeFirmaID();

    if (log.isDebugEnabled()) {
      log.debug("PF[" + pf.getPeticioDeFirmaID() + "] = " + pf);
      log.debug("PeticioDeFirma[" + peticioDeFirma.getPeticioDeFirmaID() + "] = "
          + peticioDeFirma);
    }

    // Afegir annexos
    Set<AnnexJPA> annexos = peticioDeFirma.getAnnexs();
    if (annexos != null && annexos.size() != 0) {
      for (AnnexJPA annexJPA : annexos) {
        annexJPA.setPeticioDeFirmaID(peticioDeFirmaID);
        annexLogicaEjb.createFull(annexJPA);
      }
    }

    // Afegir Metadades
    Set<MetadadaJPA> metadades = peticioDeFirma.getMetadadas();
    if (metadades != null && metadades.size() != 0) {
      for (MetadadaJPA metadada : metadades) {
        metadada.setPeticioDeFirmaID(peticioDeFirmaID);
        metadadaLogicaEjb.createFull(metadada);
      }
    }

    return pf;
  }

  /*
   * protected Long checkCustodia(PeticioDeFirmaJPA peticioDeFirma, String entitatID,
   * UsuariAplicacioJPA usuariAplicacio, UsuariEntitatJPA usuariEntitat) throws I18NException,
   * I18NValidationException {
   * 
   * 
   * final String titol = peticioDeFirma.getTitol(); final CustodiaInfoJPA custodiaSentByUser =
   * peticioDeFirma.getCustodiaInfo();
   * 
   * final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
   * 
   * CustodiaInfo onlyDef; if (usuariEntitat == null) { onlyDef =
   * entitatLogicaEjb.getCustodiaUA(usuariAplicacio, custodiaSentByUser, titol); } else {
   * onlyDef = entitatLogicaEjb.getCustodiaUE(usuariEntitat, usuariAplicacioID,
   * custodiaSentByUser, titol); }
   * 
   * 
   * if (onlyDef == null) { // Per alguna rao no es permet Custodia per aquesta petició return
   * null; } else { return created.getCustodiaInfoID(); }
   */

  /*
   * int politicaCustodia;
   * 
   * 
   * Long defaultCustodiaInfoID = entitatEjb.executeQueryOne(EntitatFields.CUSTODIAINFOID,
   * EntitatFields.ENTITATID.equal(entitatID));
   * 
   * if (defaultCustodiaInfoID == null) { // L'entitat no deixa tenir custòdia
   * peticioDeFirma.setCustodiaInfoID(null); } else { CustodiaInfoJPA custodiaInfo =
   * peticioDeFirma.getCustodiaInfo();
   * 
   * CustodiaInfoJPA custodiaInfoEntitat =
   * custodiaInfoEjb.findByPrimaryKey(defaultCustodiaInfoID);
   * 
   * if (!custodiaInfoEntitat.isEditable()) { // S'obliga a l'usuari a emprar la plantilla
   * definida per l'entitat custodiaInfo = new
   * CustodiaInfoJPA(constructDefaultCustodiaInfo(peticioDeFirma.getTitol(),entitatID,
   * usuariEntitatID, usuariAplicacioID, peticioDeFirma.getIdiomaID() ));
   * 
   * }
   * 
   * if (custodiaInfo == null) { peticioDeFirma.setCustodiaInfoID(null); } else {
   * 
   * // Set common DATA custodiaInfo.setPluginID(custodiaInfoEntitat.getPluginID());
   * custodiaInfo.setUsuariEntitatID(usuariEntitatID);
   * custodiaInfo.setUsuariAplicacioID(usuariAplicacioID);
   * custodiaInfo.setTitolPeticio(peticioDeFirma.getTitol()); // Posam null per indicar que no
   * es de cap entitat custodiaInfo.setEntitatID(null); // Posam null per indicar que no es de
   * cap entitat i no es cap plantilla custodiaInfo.setNomPlantilla(null);
   * custodiaInfo.setCustodiaInfoID(0);
   * 
   * // Check custodia CustodiaInfoBeanValidator custodiaValidator = new
   * CustodiaInfoBeanValidator( codiBarresEjb, custodiaInfoEjb, entitatEjb,
   * pluginDeCustodiaLogicaEjb, usuariAplicacioEjb, usuariEntitatEjb); final boolean isNou =
   * true; custodiaValidator.throwValidationExceptionIfErrors(custodiaInfo, isNou);
   * 
   * custodiaInfo = (CustodiaInfoJPA)custodiaInfoEjb.create(custodiaInfo);
   * 
   * peticioDeFirma.setCustodiaInfoID(custodiaInfo.getCustodiaInfoID()); }
   * 
   * } peticioDeFirma.setCustodiaInfo(null);
   */
  /* } */

  @Override
  public PeticioDeFirmaJPA findByPrimaryKeyFull(Long peticioDeFirmaID) {
    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKey(peticioDeFirmaID);

    initialize_Flux_Annexos_Custody(peticioDeFirma);

    return peticioDeFirma;
  }

  @Override
  public PeticioDeFirmaJPA findByPrimaryKeyFullWithUserInfo(Long peticioDeFirmaID) {
    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKey(peticioDeFirmaID);

    initializateFull(peticioDeFirma);

    return peticioDeFirma;
  }

  @Override
  public PeticioDeFirmaJPA findByPrimaryKeyWithUserInfo(Long peticioDeFirmaID) {
    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKey(peticioDeFirmaID);

    initializeUsuaris(peticioDeFirma);

    return peticioDeFirma;
  }

  private void initializateFull(PeticioDeFirmaJPA peticioDeFirma) {

    if (peticioDeFirma == null)
      return;

    initialize_Flux_Annexos_Custody(peticioDeFirma);

    initializeUsuaris(peticioDeFirma);

    Hibernate.initialize(peticioDeFirma.getMetadadas());
  }

  private void initialize_Flux_Annexos_Custody(PeticioDeFirmaJPA peticioDeFirma) {

    if (peticioDeFirma == null)
      return;

    peticioDeFirma.setFluxDeFirmes(fluxDeFirmesLogicaEjb
        .findByPrimaryKeyFullForNextSign(peticioDeFirma.getFluxDeFirmesID()));

    Hibernate.initialize(peticioDeFirma.getAnnexs());

    Hibernate.initialize(peticioDeFirma.getCustodiaInfo());
  }

  private void initializeUsuaris(PeticioDeFirmaJPA peticioDeFirma) {
    if (peticioDeFirma == null) {
      return;
    }

    if (peticioDeFirma.getSolicitantUsuariEntitat1ID() != null) {
      Hibernate.initialize(peticioDeFirma.getSolicitantUsuariEntitat1ID());
      Hibernate.initialize(peticioDeFirma.getSolicitantUsuariEntitat1().getUsuariPersona());
    }

    if (peticioDeFirma.getSolicitantUsuariAplicacioID() != null) {
      Hibernate.initialize(peticioDeFirma.getUsuariAplicacio());

      if (peticioDeFirma.getUsuariAplicacio() == null) {

        peticioDeFirma.setUsuariAplicacio(usuariAplicacioEjb.findByPrimaryKey(peticioDeFirma
            .getSolicitantUsuariAplicacioID()));

        if (peticioDeFirma.getUsuariAplicacio() == null) {
          log.error(
              "No s'ha pogut inicialitzar l'usuari Aplicacio "
                  + peticioDeFirma.getSolicitantUsuariAplicacioID(), new Exception());
        }
      }

    }
  }

  /**
   * Inicia una peticio de firma
   */
  @Override
  public void start(Long peticioDeFirmaID, boolean wakeupTimer) throws I18NException {

    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

    if (peticioDeFirma == null) {
      // No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound", new I18NArgumentCode(_TABLE_MODEL + "."
          + _TABLE_MODEL), new I18NArgumentCode(PETICIODEFIRMAID.fullName),
          new I18NArgumentString(String.valueOf(peticioDeFirmaID)));
    }

    // TODO S'ha de diferenciar entre Iniciar si esta en
    // estat NO_INICIAT o si esta PAUSAT !!!!

    File dstPDF = null;
    String custodyID = null;
    IDocumentCustodyPlugin plugin = null;
    int currentState = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
    try {

      peticioDeFirma.setDataSolicitud(new Timestamp(new Date().getTime()));

      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES);

      FluxDeFirmesJPA flux = peticioDeFirma.getFluxDeFirmes();

      if (log.isDebugEnabled()) {
        log.debug("Current State = " + currentState);
        log.debug("State NO INICIAT = " + ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT);
      }

      boolean esInici;
      if (ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT == currentState) {

        // Reserva de ID de custodia
        CustodiaInfo custodiaInfo = null;
        if (peticioDeFirma.getCustodiaInfoID() != null) {
          custodiaInfo = custodiaInfoLogicaEjb.findByPrimaryKey(peticioDeFirma
              .getCustodiaInfoID());
          if (custodiaInfo.isCustodiar()) {
            plugin = pluginDeCustodiaLogicaEjb.getInstanceByPluginID(custodiaInfo
                .getPluginID());

            log.info(" BLOC FIRMES ORIGINAL = "
                + peticioDeFirma.getFluxDeFirmes().getBlocDeFirmess().size());

            // CustodyParameter conté la peticio de Firma en Format XML

            // la còpia es fa per evitar modificacions de la instància interna

            Map<String, Object> additionParameters = getAdditionalParametersForDocumentCustody(
                peticioDeFirma, custodiaInfo);

            custodyID = plugin.reserveCustodyID(additionParameters);
            // TODO Check custodyID != null
            custodiaInfo.setCustodiaDocumentID(custodyID);
            String url = plugin.getOriginalFileUrl(custodyID, additionParameters);
            custodiaInfo.setUrlFitxerCustodiat(url);
            custodiaInfo.setTitolPeticio(peticioDeFirma.getTitol());
            custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));

            custodiaInfo = custodiaInfoLogicaEjb.update(custodiaInfo);
          } else {
            custodiaInfo = null;
          }
        }

        // Numero de firmes total
        Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
        int numFirmes = 0;
        for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
          numFirmes = numFirmes + blocDeFirmesJPA.getMinimDeFirmes();
        }

        if (numFirmes > ConstantsV2.MAX_FIRMES_PER_TAULA) {
          // TODO TRADUIR per quan es passin els missatges a Logica
          throw new Exception("Una peticio de firma pot tenir com a màxim "
              + ConstantsV2.MAX_FIRMES_PER_TAULA + " firmes obligatories.");
        }

        // Posar ROLE DEST als firmants del flux de firmes
        checkUsersOfFlux(flux);

        Long fitxerFinalAFirmarID;
        int tipusFirma = peticioDeFirma.getTipusFirmaID();

        switch (tipusFirma) {

          case ConstantsV2.TIPUSFIRMA_PADES:
            fitxerFinalAFirmarID = thingsToDoInPADES(peticioDeFirma, numFirmes, custodiaInfo,
                plugin);
            dstPDF = FileSystemManager.getFile(fitxerFinalAFirmarID);
          break;

          // TODO
          case ConstantsV2.TIPUSFIRMA_XADES:
          case ConstantsV2.TIPUSFIRMA_CADES:
          default:
            throw new Exception("Tipus de Firma no suportada !!!!");
        }

        peticioDeFirma.setFitxerAdaptatID(fitxerFinalAFirmarID);

        esInici = true;

      } else {
        // Es restart
        esInici = false;
      }

      // Cercar següent firma
      FirmaEventList events = new FirmaEventList();
      startNextSign(peticioDeFirma, flux, null, events);

      peticioDeFirma = (PeticioDeFirmaJPA) update(peticioDeFirma);

      events.peticio_en_proces(peticioDeFirma);

      // Avisos
      firmaEventManagerEjb.processList(events, wakeupTimer);

      // Estadistiques
      if (esInici) {
        try {
          String entitatID = peticioDeFirma.getUsuariAplicacio().getEntitatID();

          Estadistica est = new EstadisticaJPA();

          est.setValor(1.0);
          est.setUsuariAplicacioID(peticioDeFirma.getSolicitantUsuariAplicacioID());
          est.setTipus(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_INICI);
          est.setUsuariEntitatID(peticioDeFirma.getSolicitantUsuariEntitat1ID());
          String usrent = peticioDeFirma.getSolicitantUsuariEntitat1ID();
          {
            Properties params = new Properties();
            params.setProperty("entitatID", entitatID);
            params.setProperty("peticioDeFirmaID",
                String.valueOf(peticioDeFirma.getPeticioDeFirmaID()));
            params.setProperty("tipusFirmaID",
                String.valueOf(peticioDeFirma.getTipusFirmaID()));
            params.setProperty("tipusDocumentID",
                String.valueOf(peticioDeFirma.getTipusDocumentID()));

            if (usrent != null) {
              params.setProperty("usuariEntitatID", usrent);
            }
            est.setParametres(getPropertiesAsString(params));
          }
          est.setEntitatID(entitatID);
          est.setData(new Timestamp(System.currentTimeMillis()));

          estadisticaEjb.create(est);

        } catch (Throwable th) {
          log.error("Error afegint estadistiques de Peticio Iniciada: " + th.getMessage(), th);
        }
      }

    } catch (Throwable error) {
      log.error("Error arrancant peticio de firma " + peticioDeFirmaID, error);
      if (dstPDF != null) {
        if (!dstPDF.delete()) {
          dstPDF.deleteOnExit();
        }
      }

      if (ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT != currentState && custodyID != null
          && plugin != null) {
        try {
          plugin.deleteCustody(custodyID);
        } catch (Throwable e) {
          log.error(
              "Error desconegut intentant borrar el document de custodia: " + e.getMessage(),
              e);
        }
      }

      context.setRollbackOnly();

      if (error instanceof I18NException) {
        throw (I18NException) error;
      } else {
        throw new I18NException(error, "error.unknown", new I18NArgumentString(
            error.getMessage()));
      }
    }

  }

  protected Map<String, Object> getAdditionalParametersForDocumentCustody(
      PeticioDeFirmaJPA peticioDeFirma, CustodiaInfo custodiaInfo) throws Exception {

    Map<String, Object> additionParameters = new HashMap<String, Object>();

    if (custodiaInfo != null) {
      String params = custodiaInfo.getCustodiaPluginParameters();
      if (params != null && params.trim().length() != 0) {
        // Afegir propietats a additionParameters
        Properties prop = new Properties();
        prop.load(new StringReader(params));
        for (Entry<Object, Object> e : prop.entrySet()) {
          additionParameters.put(e.getKey().toString(), e.getValue());
        }
      }
    }

    PeticioDeFirmaJPA clone = PeticioDeFirmaJPA.copyJPA(peticioDeFirma);
    additionParameters.put("peticio", clone);

    return additionParameters;
  }

  private long thingsToDoInPADES(PeticioDeFirmaJPA peticioDeFirma, int numFirmes,
      CustodiaInfo custodiaInfo, IDocumentCustodyPlugin plugin) throws Exception,
      I18NException {

    // Attachments

    Set<AnnexJPA> annexesAttached = new HashSet<AnnexJPA>();
    {
      Set<AnnexJPA> annexesOrig = peticioDeFirma.getAnnexs();
      // annexLogicaEjb.select(AnnexFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      for (AnnexJPA annexJPA : annexesOrig) {
        if (annexJPA.isAdjuntar()) {
          annexesAttached.add(annexJPA);
        }
      }
    }
    List<AttachedFile> attachments = null;

    if (annexesAttached.size() != 0) {
      attachments = new ArrayList<AttachedFile>(annexesAttached.size());
      for (AnnexJPA annex : annexesAttached) {
        File f = FileSystemManager.getFile(annex.getFitxerID());
        attachments.add(new AttachedFile(annex.getFitxer().getNom(), f));
      }
    }

    // DADES GENERALS
    String entitatID;
    if (peticioDeFirma.getSolicitantUsuariEntitat1() != null) {
      // usuari entitat
      log.debug(" Idioma Usuari Entitat: ");
      entitatID = peticioDeFirma.getSolicitantUsuariEntitat1().getEntitatID();
    } else {
      // usuari aplicacio
      log.debug(" Idioma Usuari Aplicació: ");
      entitatID = peticioDeFirma.getUsuariAplicacio().getEntitatID();
    }

    // obtenir tamany màxim del fitxer adaptat
    EntitatJPA entitat = entitatEjb.findByPrimaryKey(entitatID);
    Long maxSizeEntitat = null;
    if (entitat != null) {
      maxSizeEntitat = entitat.getMaxSizeFitxerAdaptat();
    }

    // Obtenir idioma de la petició
    String idioma = peticioDeFirma.getIdiomaID();

    if (idioma == null) {
      if (peticioDeFirma.getSolicitantUsuariEntitat1ID() != null) {
        // usuari entitat
        log.debug(" Idioma Usuari Entitat: ");
        idioma = peticioDeFirma.getSolicitantUsuariEntitat1().getUsuariPersona().getIdiomaID();
      } else {
        // usuari aplicacio
        log.debug(" Idioma Usuari Aplicació: ");
        idioma = peticioDeFirma.getUsuariAplicacio().getIdiomaID();
      }
      if (idioma == null) {
        idioma = Configuracio.getDefaultLanguage();
      }
    }
    log.debug(" Idioma Petició: " + idioma);

    Locale locale = new Locale(idioma);

    // ==== TAULA DE FIRMES
    final int posicio = (int) peticioDeFirma.getPosicioTaulaFirmesID();
    StampTaulaDeFirmes taulaDeFirmes = null;
    if (posicio != ConstantsV2.TAULADEFIRMES_SENSETAULA) {

      final String desc = peticioDeFirma.getDescripcio();
      final String titol = peticioDeFirma.getTitol();
      final File logo = getLogoOfPeticioDeFirma(peticioDeFirma);

      byte[] logoSegell;
      try {
        logoSegell = FileUtils.readFileToByteArray(logo);
      } catch (IOException e) {
        String msg = "Error desconegut llegint logo-segell de l'entitat " + entitatID + ": "
            + e.getMessage();
        log.error(msg, e);
        throw new I18NException("error.unknown", msg);
      }

      final String signantLabel = I18NLogicUtils.tradueix(locale, "signant");
      final String resumLabel = I18NLogicUtils.tradueix(locale, "resumdefirmes");
      final String titolLabel = I18NLogicUtils.tradueix(locale, "titol");
      final String descLabel = I18NLogicUtils.tradueix(locale, "descripcio");

      taulaDeFirmes = new StampTaulaDeFirmes(numFirmes, posicio, signantLabel, resumLabel,
          descLabel, desc, titolLabel, titol, logoSegell);
    }

    // Custodia
    es.caib.portafib.logic.utils.StampCustodiaInfo custodiaInfoStamp = null;
    if (custodiaInfo != null) {

      /**
       * Missatge de custòdia a mostrar en el document. {0} = URL {1} = custodiaID {2} =
       * custodiaPluginClassID {3} = data amb hora {4} = Special Value
       */

      String data = new I18NCommonDateTimeFormat(locale).format(new Date());

      Map<String, Object> additionParameters = getAdditionalParametersForDocumentCustody(
          peticioDeFirma, custodiaInfo);

      String custodyID = custodiaInfo.getCustodiaDocumentID();
      Object[] arguments = new Object[] { custodiaInfo.getUrlFitxerCustodiat(), custodyID,
          custodiaInfo.getPluginID(), // TODO Posar NOM del Plugin
          data, plugin.getSpecialValue(custodyID, additionParameters) };

      Properties prop = new Properties();
      prop.load(new StringReader(custodiaInfo.getMissatge()));

      String msg = prop.getProperty(locale.getLanguage());

      if (msg == null) {
        throw new I18NException("custodiainfo.missatge.error.format", locale.getLanguage());
      }

      String missatge = MessageFormat.format(msg, arguments);

      String barcodeText = MessageFormat.format(custodiaInfo.getCodiBarresText(), arguments);

      String javaName = custodiaInfo.getCodiBarresID();

      IBarcodePlugin barcode = (IBarcodePlugin) PluginsManager
          .instancePluginByClassName(javaName);
      if (barcode == null) {
        throw new I18NException("plugin.donotinstantiate", javaName);
      }

      custodiaInfoStamp = new StampCustodiaInfo(
          (int) custodiaInfo.getMissatgePosicioPaginaID(), missatge, barcode, barcodeText,
          custodiaInfo.getPagines());

    }

    // Crear nou fitxer amb taula de firmes i adjunts
    File srcPDF = FileSystemManager.getFile(peticioDeFirma.getFitxerAFirmarID());
    File dstPDF = FileSystemManager.getTmpFile(System.nanoTime());
    try {

      Long maxSize = PdfUtils.selectMin(maxSizeEntitat,
          PropietatGlobalUtil.getMaxFitxerAdaptatSizeInBytes());

      final boolean transformPdfA = PropietatGlobalUtil.isTransformPdfA(entitatID);

      final boolean forceCleanPdf = PropietatGlobalUtil.isForceCleanPdf(entitatID);

      PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(srcPDF, dstPDF, attachments, maxSize,
          taulaDeFirmes, custodiaInfoStamp, transformPdfA, forceCleanPdf);

      FitxerJPA f = new FitxerJPA();
      f.setDescripcio("");
      f.setMime(ConstantsV2.MIME_TYPE_PDF);
      f.setNom("TaulaDeFirmesDePeticio_" + peticioDeFirma.getPeticioDeFirmaID() + ".pdf");
      f.setTamany(dstPDF.length());

      f = fitxerLogicaEjb.createFull(f);

      long fitxerFinalAFirmarID = f.getFitxerID();

      dstPDF = FileSystemManager.sobreescriureFitxer(dstPDF, fitxerFinalAFirmarID);
      if (log.isDebugEnabled()) {
        log.info("Guardat fitxer amb taula i adjunts a " + dstPDF.getAbsolutePath());
      }

      return fitxerFinalAFirmarID;
    } catch (Exception e) {
      if (dstPDF != null && dstPDF.exists()) {
        if (!dstPDF.delete()) {
          dstPDF.deleteOnExit();
        }
      }
      log.error("Error processant fitxer PDF", e);
      throw e;
    }

  }

  // Ficar a l'api
  public File getLogoOfPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) throws I18NException {

    if (peticioDeFirma.getLogoSegellID() == null) {

      // Cercar en usuari entitat o usuari peticio
      String entitatID;
      if (peticioDeFirma.getSolicitantUsuariEntitat1() != null) {
        // usuari-entitat
        UsuariEntitat ue;
        ue = peticioDeFirma.getSolicitantUsuariEntitat1();
        if (ue.getLogoSegellID() != null) {
          return FileSystemManager.getFile(ue.getLogoSegellID());
        } else {
          entitatID = ue.getEntitatID();
        }
      } else {
        // usuari aplicacio
        Long logoID = peticioDeFirma.getUsuariAplicacio().getLogoSegellID();
        if (logoID != null) {
          return FileSystemManager.getFile(logoID);
        } else {
          entitatID = peticioDeFirma.getUsuariAplicacio().getEntitatID();
        }
      }
      Long logoID = entitatEjb.executeQueryOne(EntitatFields.LOGOSEGELLID,
          EntitatFields.ENTITATID.equal(entitatID));
      return FileSystemManager.getFile(logoID);
    } else {
      return FileSystemManager.getFile(peticioDeFirma.getLogoSegellID());
    }
  }

  /**
   * Check de: (1) Verificar que els usuaris estan actius (2) Assignam permis de ROLE_DEST als
   * firmants de la peticio que no el tenguin.
   * 
   * @param flux
   * @throws Exception
   */
  private void checkUsersOfFlux(FluxDeFirmesJPA flux) throws Exception, I18NException {

    Set<String> destinatarisUsuari = new HashSet<String>();
    Set<String> destinatarisCarrec = new HashSet<String>();

    Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
    if (blocs != null) {
      for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
        Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
        for (FirmaJPA firma : firmes) {
          UsuariEntitatJPA usuariEntitatJPA = firma.getUsuariEntitat();

          if (usuariEntitatJPA.getCarrec() == null) {
            destinatarisUsuari.add(firma.getDestinatariID());
          } else {
            String usuariEntitatID = usuariEntitatEjb.executeQueryOne(
                UsuariEntitatFields.USUARIENTITATID, Where.AND(
                    UsuariEntitatFields.USUARIPERSONAID.equal(usuariEntitatJPA
                        .getUsuariPersonaID()), UsuariEntitatFields.ENTITATID
                        .equal(usuariEntitatJPA.getEntitatID()), UsuariEntitatFields.CARREC
                        .isNull()));

            destinatarisCarrec.add(firma.getDestinatariID());
            destinatarisUsuari.add(usuariEntitatID);
          }
        }
      }
    }

    Set<String> allDestinataris = new HashSet<String>();
    allDestinataris.addAll(destinatarisUsuari);
    allDestinataris.addAll(destinatarisCarrec);

    // Verificar que els usuaris estan actius
    long count = usuariEntitatEjb.count(Where.AND(
        UsuariEntitatFields.USUARIENTITATID.in(allDestinataris),
        UsuariEntitatFields.ACTIU.equal(false)));
    if (count != 0) {
      throw new I18NException("peticiodefirma.usuarisnoactius", String.valueOf(count));
    }

    // Assignam permis de ROLE_DEST als firmants de la peticio que no el
    // tenguin.
    Where where = Where.AND(RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_DEST),
        RoleUsuariEntitatFields.USUARIENTITATID.in(destinatarisUsuari));
    List<String> destAmbPermis = roleUsuariEntitatEjb.executeQuery(
        RoleUsuariEntitatFields.USUARIENTITATID, where);
    // Borram tos els que tenen permis
    for (String ambPermis : destAmbPermis) {
      destinatarisUsuari.remove(ambPermis);
    }

    // Assignam el permis als que no en tenen
    boolean isDebug = log.isDebugEnabled();
    for (String sensePermis : destinatarisUsuari) {
      if (isDebug) {
        log.info("Afegint ROLE_DEST a l'usuari-entitat (persona) " + sensePermis);
      }
      roleUsuariEntitatEjb.create(ConstantsV2.ROLE_DEST, sensePermis);
    }

  }

  /**
   * Clau = peticioDeFirmaID. Valor = Hora en que es va fer la peticio" + "_" + usuariEntitat
   * que ha bloquejat
   */
  public static final Map<Long, Token> locks = new ConcurrentHashMap<Long, Token>();

  /**
   *
   * @author anadal
   *
   */
  public static class Token {

    /**
     * Temps de vida del token quan la firma no importa que es bloquegi. Posam un dia de temps
     * que n'hi ha suficient.
     */
    public static final long ONE_DAY_TIME_LOCKED_IN_MS = 24L * 60L * 60L * 1000L;

    /** Temps en ms en que el Token serà vàlid */
    private final long timeTokenAlive;

    private long timeInMs;

    private final String usuariEntitatID;

    private final String tokenString;

    /**
     * @param time
     * @param usuariEntitatID
     */
    public Token(String usuariEntitatID, long timeTokenAlive) {
      super();
      this.usuariEntitatID = usuariEntitatID;
      this.timeInMs = System.currentTimeMillis();
      this.timeTokenAlive = timeTokenAlive;
      tokenString = timeInMs + "_" + usuariEntitatID;
    }

    public void updateTime() {
      this.timeInMs = System.currentTimeMillis();
    }

    public String getTokenString() {
      return tokenString;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Token) {
        Token tok = (Token) obj;
        if (tok.timeInMs == this.timeInMs && this.usuariEntitatID.equals(tok.usuariEntitatID)) {
          return true;
        }
      }
      return false;
    }

    public boolean isInvalid() {
      long now = System.currentTimeMillis();

      if ((this.timeInMs + this.timeTokenAlive) < now) {
        return true;
      } else {
        return false;
      }

    }

    public String getUsuariEntitatID() {
      return usuariEntitatID;
    }

    public long getTimeInMs() {
      return timeInMs;
    }

  }

  private void cleanLocks() {

    Set<Long> ids = new HashSet<Long>(locks.keySet());

    for (Long id : ids) {
      Token token = locks.get(id);
      if (token != null && token.isInvalid()) {
        /*
         * final long now = System.nanoTime(); log.info("==================== ");
         * log.info("Netejant peticio amb id = " + id); log.info("    time + max < now => " +
         * token.getTimeInNano() + " + " + Token.MAX_TIME_LOCKED_IN_NANO + " < " + now);
         * log.info("    time + max < now => " + (token.getTimeInNano() +
         * Token.MAX_TIME_LOCKED_IN_NANO) + " < " + now); log.info("    time + max < now => " +
         * ((token.getTimeInNano() + Token.MAX_TIME_LOCKED_IN_NANO) < now) );
         * log.info("==================== ");
         */
        // S'ha de borrar
        locks.remove(id);
      }
    }

  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param token
   * @return null si esta bloquejat per un altra usuari, sino retorna un token (nou o
   *         actualitzat)
   */
  @Override
  public String lockPeticioDeFirma(long peticioDeFirmaID, String usuariEntitatID,
      long timeAliveToken) {

    // Si countFirmesPerBloc, o sigui el número de firmes del bloc, és 1 llavors el token
    // té temps il·limitat per firmar. En cas contrari poden passar tres coses:
    // CAS 1: No estigui bloquejada
    // CAS 2: estigui bloquejat per un usuari IGUAL a "usuariEntitatID"
    // CAS 3: estigui bloquejat per un usuari DIFERENT a "usuariEntitatID"

    cleanLocks();

    Token tokenStored = locks.get(peticioDeFirmaID);

    if (tokenStored == null) {
      // CAS 1: Crear un nou TOKEN
      Token token = new Token(usuariEntitatID, timeAliveToken);
      locks.put(peticioDeFirmaID, token);
      return token.getTokenString();
    } else {
      // Existeix bloqueig
      if (tokenStored.usuariEntitatID.equals(usuariEntitatID)) {
        // CAS 2: Si es el mateix usuari llavors no existeix bloqueig
        // Actualitzam la data (si és necessari) i retornam el token
        tokenStored.updateTime();
        return tokenStored.getTokenString();
      } else {
        // CAS 3: Esta bloquejat per un altre usuari.
        return null;
      }
    }

    /*
     * if (isLockedPeticioDeFirma(peticioDeFirmaID,usuariEntitatID)) { return null; } else {
     * Token tokenStored = locks.get(peticioDeFirmaID); if (tokenStored == null) { // Crear un
     * nou TOKEN Token token = new Token(System.currentTimeMillis(),usuariEntitatID);
     * locks.put(peticioDeFirmaID, token ); return token.getToken(); } else { // Reutilitzar el
     * token ja existent de l'usuari
     * 
     * return tokenStored.getToken(); } }
     */
  }

  // @Override
  public boolean unlockPeticioDeFirma(long peticioDeFirmaID, String token) {
    if (token == null) {
      return unlockPeticioDeFirma(peticioDeFirmaID);
    } else {
      cleanLocks();
      Token tokenStored = locks.get(peticioDeFirmaID);
      if (tokenStored != null) {
        if (tokenStored.getTokenString().equals(token)) {
          locks.remove(peticioDeFirmaID);
          return true;
        } else {
          return false;
        }
      }
      return true;
    }
  }

  public boolean unlockPeticioDeFirma(long peticioDeFirmaID) {
    cleanLocks();
    Token tokenStored = locks.remove(peticioDeFirmaID);
    if (tokenStored != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param token
   * @return null si el token no existeix (no hi ha bloqueig o s'ha tardat massa temps en
   *         firmar). true si tot està correcte i false si esta bloquejat per un altre usuari
   */
  @Override
  public Boolean checkPeticioDeFirmaByToken(long peticioDeFirmaID, String token) {
    cleanLocks();
    Token tokenStored = locks.get(peticioDeFirmaID);
    if (tokenStored == null) {
      if (log.isDebugEnabled()) {
        log.debug("S'ha cridat a checkPeticioDeFirmaByToken(" + peticioDeFirmaID + "," + token
            + ") però no existeix", new Exception());
      }
      return null;
    } else {
      if (tokenStored.getTokenString().equals(token)) {
        return true;
      } else {
        if (log.isDebugEnabled()) {
          log.debug("S'ha cridat a checkPeticioDeFirmaByToken(" + peticioDeFirmaID + ","
              + token + ") però els tokens són diferents (" + tokenStored + ")",
              new Exception());
        }
        return false;
      }
    }
  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param usuariEntitatID
   * @return true si la peticio no esta bloquejada o esta bloquejada per usuariEntitatID. false
   *         en altres cas
   */
  public boolean checkPeticioDeFirmaByUsuariEntitat(long peticioDeFirmaID,
      String usuariEntitatId) {
    cleanLocks();
    Token tokenStored = locks.get(peticioDeFirmaID);
    if (tokenStored == null) {
      if (log.isDebugEnabled()) {
        log.debug("S'ha cridat a checkPeticioDeFirmaByUsuariEntitat(" + peticioDeFirmaID + ","
            + usuariEntitatId + ") però no existeix", new Exception());
      }
      return true;
    } else {
      if (tokenStored.getUsuariEntitatID().equals(usuariEntitatId)) {
        return true;
      } else {
        if (log.isDebugEnabled()) {
          log.debug(
              "S'ha cridat a checkPeticioDeFirmaByUsuariEntitat(" + peticioDeFirmaID + ","
                  + usuariEntitatId + ") però els usuaris són diferents("
                  + tokenStored.getTokenString() + ")", new Exception());
        }
        return false;
      }
    }
  }

  @Override
  public boolean pause(Long peticioDeFirmaID) throws I18NException {
    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKey(peticioDeFirmaID);
    if (peticioDeFirma != null) {

      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT);
      update(peticioDeFirma);

      try {
        // Events
        FirmaEventList events = new FirmaEventList();
        events.peticio_pausada(peticioDeFirma);

        // Avisos
        firmaEventManagerEjb.processList(events, true);
      } catch (I18NException e) {
        log.error(I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage())),
            new Exception());
      }

      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   * @param peticioDeFirma
   * @param flux
   * @param estatDeFirma
   *          val null quan s'inicia la peticio de firma. En altres casos es la firma que
   *          provoca la cerca de la següent.
   * @return true si la peticio ha finalitzat, false si la peticio continua
   * @throws Exception
   */
  protected boolean startNextSign(PeticioDeFirmaJPA peticioDeFirma, FluxDeFirmesJPA flux,
      EstatDeFirmaJPA estatDeFirma, FirmaEventList events) throws I18NException {

    if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
      log.error("S'esta cercant un nou bloc de firmes disponible per la petició "
          + peticioDeFirma.getPeticioDeFirmaID() + " però aquesta no esta en progres. ");
      return false;
    }

    Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();

    TreeSet<BlocDeFirmesJPA> blocsOrdenats;
    blocsOrdenats = new TreeSet<BlocDeFirmesJPA>(new BlocDeFirmesComparator());
    blocsOrdenats.addAll(blocs);
    final boolean debug = log.isDebugEnabled();

    log.debug(" ========== startNextSign");
    Timestamp now = new Timestamp(System.currentTimeMillis());

    for (BlocDeFirmesJPA blocDeFirmesJPA : blocsOrdenats) {

      if (debug) {
        log.debug("----- Bloc " + blocDeFirmesJPA.getBlocDeFirmesID());
      }

      // Descartam els blocs tancats
      if (blocDeFirmesJPA.getDataFinalitzacio() != null) {
        log.debug("  Bloc finalitzat !!!");
        continue;
      }
      // Cercam el primer bloc amb firmes pendents o el primer bloc no iniciat

      Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();

      Long[] firmaIDs = new Long[firmes.size()];
      int i = 0;
      for (FirmaJPA firmaJPA : firmes) {
        firmaIDs[i] = firmaJPA.getFirmaID();
        i++;
      }

      // Cercam un bloc no tancat pero amb totes les firmes realitzades
      long firmesFinalitzades = firmaLogicaEjb.count(Where.AND(
          FirmaFields.FIRMAID.in(firmaIDs), FirmaFields.FITXERFIRMATID.isNotNull()));

      if (firmesFinalitzades >= blocDeFirmesJPA.getMinimDeFirmes()) {
        log.debug("  Tancam Bloc i passam al següent !!!");
        // Es tracta d'un bloc no tancat pero amb totes les firmes realitzades
        // o amb el mínim de firmes realitzades per la qual cosa tancam el bloc
        // i passam al següent bloc

        if (firmesFinalitzades != firmaIDs.length) {
          // Hem de tancar els EstatsDeFirma de les firmes obertes o
          // colaboracions
          // Estats de Firma associats a les firmes obertes no necessaries
          // TODO missatge
          final String msg = "Firma o validació no necessaries";
          descartarEstatsDeFirma(firmaIDs, msg, peticioDeFirma, events, now);

        }
        blocDeFirmesJPA.setDataFinalitzacio(now);
        // Revisam el següent bloc

      } else {

        log.debug("Bloc verge o amb firmes no realitzades ????");

        // Aqui hem d'esbrinar si es tracta d'un bloc verge
        // o d'un bloc amb estat de firmes pendents
        // NOTA les firmes d'un bloc verge no tenen cap EstatDeFirma associat

        // Cercam les firmes no realitzades

        long estatsDeFirma = estatDeFirmaLogicaEjb.count(EstatDeFirmaFields.FIRMAID
            .in(firmaIDs));

        if (estatsDeFirma != 0) {
          // És un bloc amb firmes pendents, per la qual cosa no feim res
          log.debug("  Bloc amb  firmes pendent: NO FER RES");
          return false;
        } else {
          if (debug) {
            log.debug(" Bloc Verge te " + firmes.size() + " firmes ");
          }
          // Es un bloc verge, per la qual cosa cream els estats de firma
          // associades a les firmes
          for (FirmaJPA firmaJPA : firmes) {

            if (debug) {
              log.debug("  +++ Firma " + firmaJPA.getFirmaID());
            }

            String destinatariReal;
            String carrec;
            // Miram si és un càrrec
            {
              UsuariEntitatJPA dest = firmaJPA.getUsuariEntitat();
              if (dest.getCarrec() == null) {
                // Usuari Persona
                destinatariReal = dest.getUsuariEntitatID();
                carrec = null;
              } else {
                carrec = dest.getUsuariEntitatID();
                // És un carrec, llavors:
                // (1) Hem de verificar que estigui actiu
                // (2) Hem de cercar l'usuari entitat (persona) associat a aquest càrrec
                if (!dest.isActiu()) {
                  // TODO TRADUIR !!!!!!!
                  String msg = "El carrec " + dest.getCarrec() + "(ID="
                      + dest.getUsuariEntitatID() + ", " + dest.getEntitatID()
                      + ") no esta actiu. "
                      + "Esperi a que s'activi o se li assigni una persona.";
                  I18NException e = new I18NException("error.unknown", msg);
                  log.error(msg, e);
                  throw e;
                }

                destinatariReal = usuariEntitatEjb.executeQueryOne(
                    UsuariEntitatFields.USUARIENTITATID, Where.AND(
                        UsuariEntitatFields.ENTITATID.equal(dest.getEntitatID()),
                        UsuariEntitatFields.USUARIPERSONAID.equal(dest.getUsuariPersonaID()),
                        UsuariEntitatFields.CARREC.isNull()));
                if (destinatariReal == null) {
                  // TODO TRADUIR !!!!!!!
                  String msg = "No s'ha trobat un usuari persona" + " associat al carrec "
                      + dest.getCarrec() + "(ID=" + dest.getUsuariEntitatID() + ", "
                      + dest.getEntitatID() + ")";
                  I18NException e = new I18NException("error.unknown", msg);
                  log.error(msg, e);
                  throw e;
                }
              }
            }

            // S'envia directament al DESTINATARI
            EstatDeFirmaJPA estatDeFirmaDest = new EstatDeFirmaJPA();
            estatDeFirmaDest.setDataInici(new Timestamp(System.currentTimeMillis()));
            estatDeFirmaDest.setDescripcio("");
            estatDeFirmaDest.setFirmaID(firmaJPA.getFirmaID());
            estatDeFirmaDest
                .setTipusEstatDeFirmaInicialID(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
            estatDeFirmaDest.setUsuariEntitatID(destinatariReal);
            estatDeFirmaDest = (EstatDeFirmaJPA) estatDeFirmaLogicaEjb
                .createFull(estatDeFirmaDest);
            events.requerit_per_firmar(peticioDeFirma, estatDeFirmaDest);

            if (debug) {
              log.debug("   == Nou estat per Destinatari " + firmaJPA.getDestinatariID());
            }

            // (a) Seleccionam els tipus de documents per aquesta delegacio,
            // (a.1) El que tenguin el tipus que s'ajusti al tipus de la peticio
            SubQuery<TipusDocumentColaboracioDelegacio, Long> subquery;
            subquery = tipusDocumentColaboracioDelegacioEjb.getSubQuery(
                TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID,
                TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID.equal(peticioDeFirma
                    .getTipusDocumentID()));

            // (a.2) Una llista buida significa que acceptarà qualsevol peticio
            SubQuery<TipusDocumentColaboracioDelegacio, Long> subquery2;
            subquery2 = tipusDocumentColaboracioDelegacioEjb.getSubQuery(new SelectCount(),
                TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID
                    .equal(ColaboracioDelegacioFields.COLABORACIODELEGACIOID));

            // (A) Cercar Col.laboradors i Delegats

            final Where commonWhere = Where.AND(ColaboracioDelegacioFields.ACTIVA.equal(true),
                Where.OR(ColaboracioDelegacioFields.DATAINICI.isNull(),
                    ColaboracioDelegacioFields.DATAINICI.lessThan(now)), Where.OR(
                    ColaboracioDelegacioFields.DATAFI.isNull(),
                    ColaboracioDelegacioFields.DATAFI.greaterThan(now)), Where.OR(
                    ColaboracioDelegacioFields.COLABORACIODELEGACIOID.in(subquery),
                    new LongConstantField(0L).in(subquery2)));

            final Where w1 = Where.AND(
                ColaboracioDelegacioFields.DESTINATARIID.equal(destinatariReal), commonWhere);

            // (B) Cercar col·laboradors de càrrec
            Where w2 = null;
            if (carrec != null) {
              w2 = Where.AND(ColaboracioDelegacioFields.DESTINATARIID.equal(carrec),
                  commonWhere);
            }

            Where w = Where.OR(w1, w2);

            if (debug) {
              log.debug("  Seleccio de COLABORADORS/DELEGATS: " + w.toSQL());
            }

            List<ColaboracioDelegacio> llistaColaDele = colaboracioDelegacioEjb.select(w);

            if (debug) {
              log.debug("   == # COLABORADORS/DELEGATS " + llistaColaDele.size());

              for (ColaboracioDelegacio cd : llistaColaDele) {
                log.debug("       + " + (cd.isEsDelegat() ? "DELE" : "COLA") + "["
                    + cd.getColaboracioDelegacioID() + "] : " + cd.getColaboradorDelegatID()
                    + "  =>  " + cd.getDestinatariID());
              }
            }

            for (ColaboracioDelegacio colaboracioDelegacio : llistaColaDele) {
              EstatDeFirmaJPA estatDeFirmaColaDele = new EstatDeFirmaJPA();
              estatDeFirmaColaDele.setDataInici(new Timestamp(System.currentTimeMillis()));
              estatDeFirmaColaDele.setDescripcio("");
              estatDeFirmaColaDele.setFirmaID(firmaJPA.getFirmaID());
              long tipusEstat;
              if (colaboracioDelegacio.isEsDelegat()) {
                tipusEstat = ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR;
              } else {
                tipusEstat = ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR;
              }
              estatDeFirmaColaDele.setTipusEstatDeFirmaInicialID(tipusEstat);
              estatDeFirmaColaDele.setUsuariEntitatID(colaboracioDelegacio
                  .getColaboradorDelegatID());
              estatDeFirmaColaDele.setColaboracioDelegacioID(colaboracioDelegacio
                  .getColaboracioDelegacioID());
              estatDeFirmaColaDele = estatDeFirmaLogicaEjb.createFull(estatDeFirmaColaDele);
              if (tipusEstat == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
                events.requerit_per_firmar(peticioDeFirma, estatDeFirmaColaDele);
              } else {
                events.requerit_per_validar(peticioDeFirma, estatDeFirmaColaDele);
              }
              if (debug) {
                log.debug("   == Nou estat per COLA/DELE "
                    + colaboracioDelegacio.getColaboradorDelegatID());
              }
            }

            // TODO Falten avisos
          }

          // ================================================
          // - - - - - - REQUERIT PER REVISAR - - - - - -
          // ================================================
          {

            List<RevisorDeFirma> revisors = revisorDeFirmaEjb
                .select(RevisorDeFirmaFields.FIRMAID.in(firmaIDs));

            for (RevisorDeFirma revisorDeFirma : revisors) {
              EstatDeFirmaJPA estatDeFirmaRevisor = new EstatDeFirmaJPA();
              estatDeFirmaRevisor.setDataInici(new Timestamp(System.currentTimeMillis()));
              estatDeFirmaRevisor.setDescripcio("");
              estatDeFirmaRevisor.setFirmaID(revisorDeFirma.getFirmaID());
              long tipusEstat = ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR;

              estatDeFirmaRevisor.setTipusEstatDeFirmaInicialID(tipusEstat);
              // XYZ ZZZ Falten càrrecs
              estatDeFirmaRevisor.setUsuariEntitatID(revisorDeFirma.getUsuariEntitatID());
              estatDeFirmaRevisor.setColaboracioDelegacioID(null);
              estatDeFirmaRevisor = estatDeFirmaLogicaEjb.createFull(estatDeFirmaRevisor);

              events.requerit_per_revisar(peticioDeFirma, estatDeFirmaRevisor);

              if (debug) {
                log.debug("   == Nou estat per REVISOR " + revisorDeFirma.getUsuariEntitatID()
                    + " (" + revisorDeFirma.getRevisorDeFirmaID() + ")");
              }
            }
          }

          log.debug("   == FINAL BLOC VERGE");
          return false;
        }

      }
    }

    // Si arribem aqui significa que ja no hi ha més blocs i el proces
    // de firma ha finalitzat, per la qual cosa marcam la peticio con
    // finalitzada

    log.debug("   == JA NO HI HA MÉS BLOCS: marcam la peticio con finalitzada");
    // Marcam la petició com Firmada
    peticioDeFirma.setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT);
    peticioDeFirma.setDataFinal(now);
    this.update(peticioDeFirma);

    // Avisam de la firma final
    events.peticio_firmada(peticioDeFirma);

    return true;
  }

  protected void descartarEstatsDeFirma(Long[] firmaIDs, final String msg,
      PeticioDeFirmaJPA peticioDeFirma, FirmaEventList events, Timestamp now)
      throws I18NException {
    List<EstatDeFirma> estatsDeFirma = estatDeFirmaLogicaEjb.select(Where.AND(
        EstatDeFirmaFields.DATAFI.isNull(), EstatDeFirmaFields.FIRMAID.in(firmaIDs)));

    for (EstatDeFirma estat : estatsDeFirma) {
      estat.setDataFi(now);
      estat.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      estat.setDescripcio(msg);
      estat = estatDeFirmaLogicaEjb.update(estat);
      if (estat.getTipusEstatDeFirmaInicialID() == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
        events.descartat_per_firmar(peticioDeFirma, estat);
      } else {
        events.descartat_per_validar(peticioDeFirma, estat);
      }
    }

    // Actualitzam Firmes a estat Descartat
    List<Firma> actualitzarFirmes = firmaLogicaEjb.select(FirmaFields.FIRMAID.in(firmaIDs));
    for (Firma firmaADescartar : actualitzarFirmes) {
      firmaADescartar
          .setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
    }

  }

  public static class BlocDeFirmesComparator implements Comparator<BlocDeFirmes> {

    public int compare(BlocDeFirmes o1, BlocDeFirmes o2) {

      int number1 = o1.getOrdre();
      int number2 = o2.getOrdre();

      return (number1 < number2 ? -1 : (number1 == number2 ? 0 : 1));
    }

  }

  @Override
  public Set<Long> deleteFullUsingUsuariEntitat(Long peticioDeFirmaID, String usuariEntitatID)
      throws I18NException {
    boolean isUsuariEntitat = true;
    return deleteFull(peticioDeFirmaID, usuariEntitatID, isUsuariEntitat);
  }

  @Override
  public Set<Long> deleteFullUsingUsuariAplicacio(Long peticioDeFirmaID,
      String usuariAplicacioID) throws I18NException {
    boolean isUsuariEntitat = false;
    return deleteFull(peticioDeFirmaID, usuariAplicacioID, isUsuariEntitat);
  }

  /**
   * 
   * @param peticioDeFirmaID
   * @return List of filesID deleted
   */

  private Set<Long> deleteFull(Long peticioDeFirmaID, String username, boolean isUsuariEntitat)
      throws I18NException {
    Set<Long> files = new HashSet<Long>();

    try {

      PeticioDeFirmaJPA pf = findByPrimaryKey(peticioDeFirmaID);
      if (pf == null) {
        return files;
      }

      // Check si l'usuari entitat o aplicació té permis per borrar
      // Si és PFI_ADMIN se li permet
      if (!context.isCallerInRole(PFI_ADMIN)) {
        if (isUsuariEntitat) {
          if (!username.equals(pf.getSolicitantUsuariEntitat1ID())) {
            // L'usuari {0} no té permisos per borrar la petició de firma titulada {1}
            throw new I18NException("peticiodefirma.error.nopermisdeborrar", username,
                pf.getTitol());
          }
        } else {
          if (!username.equals(pf.getSolicitantUsuariAplicacioID())) {
            throw new I18NException("peticiodefirma.error.nopermisdeborrar", username,
                pf.getTitol());
          }
        }
      }

      // Borrar Notificacions
      notificacioWsEjb.delete(NotificacioWSFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      // Borrar Bitacola
      bitacolaEjb.delete(BitacolaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      // Borrar metadades
      metadadaLogicaEjb.delete(MetadadaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      // Borrar anexes (i anexes firmats)
      List<Annex> adjunts = annexLogicaEjb.select(AnnexFields.PETICIODEFIRMAID
          .equal(peticioDeFirmaID));
      if (adjunts != null) {
        for (Annex annex : adjunts) {
          files.addAll(annexLogicaEjb.deleteFull(annex.getAnnexID()));
        }
        pf.setAnnexs(null);
      }

      // Borrar Peticio de Firma
      delete(pf); // peticioDeFirmaID);

      // Borrar flux de firmes
      Long fluxID = pf.getFluxDeFirmesID();
      files.addAll(fluxDeFirmesLogicaEjb.deleteFull(fluxID));

      // Borrar fitxer a firmar
      if (pf.getFitxerAFirmarID() != null) {
        files.add(pf.getFitxerAFirmarID());
        fitxerLogicaEjb.delete(pf.getFitxerAFirmarID());
      }

      // Borrar fitxer amb taula de firmes
      if (pf.getFitxerAdaptatID() != null
          && (pf.getFitxerAFirmarID() != pf.getFitxerAdaptatID())) {
        files.add(pf.getFitxerAdaptatID());
        fitxerLogicaEjb.delete(pf.getFitxerAdaptatID());
      }

      // Borram la reserva de Custòdia en cas de no haver finalitzat
      if (pf.getCustodiaInfoID() != null
          && pf.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {

        // Borram la reserva de custòdia
        CustodiaInfoJPA custInfo = custodiaInfoLogicaEjb.findByPrimaryKey(pf
            .getCustodiaInfoID());

        IDocumentCustodyPlugin plugin;
        plugin = pluginDeCustodiaLogicaEjb.getInstanceByPluginID(custInfo.getPluginID());
        /*
         * String pluginClass = custInfo.getCustodiaPluginClassID();
         * 
         * IDocumentCustodyPlugin plugin =
         * PortaFIBPluginsManager.getDocumentCustodyPluginByClassName(pluginClass);
         * 
         * if (plugin == null) { throw new I18NException("plugin.donotinstantiate",
         * pluginClass); }
         */
        try {
          plugin.deleteCustody(custInfo.getCustodiaDocumentID());
        } catch (NotSupportedCustodyException e) {
          // TODO Avisar Administrador
          e.printStackTrace();
        } catch (CustodyException e) {
          // TODO Avisar Administrador
          e.printStackTrace();
        }

        // Borram informacio de custòdia
        custodiaInfoLogicaEjb.delete(pf.getCustodiaInfoID());
      }

      // Retornam els fitxers, per que fora es borrin fisicament
      return files;

    } catch (I18NException e) {
      context.setRollbackOnly();
      throw e;
    }

  }

  /**
   * @return El darrer fitxer firmat si la peticio esta n marxa i algu ha firmat, els fitxer
   *         adaptat si la peticio esta en marxa i ningú ha firmat o el fitxer original si la
   *         peticio no s'ha iniciat.
   */
  @Override
  public FitxerJPA getLastSignedFileOfPeticioDeFirma(Long peticioDeFirmaID)
      throws I18NException {
    if (peticioDeFirmaID == null) {
      return null;
    }

    PeticioDeFirmaJPA peticio = findByPrimaryKey(peticioDeFirmaID);
    if (peticio == null) {
      return null;
    }

    long estat = peticio.getTipusEstatPeticioDeFirmaID();

    FitxerJPA f;
    if (estat != ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
      f = peticio.getFitxerAFirmar();
    } else {

      FirmaJPA firma = getLastSignOfPeticioDeFirma(peticioDeFirmaID);
      if (firma == null) {
        f = peticio.getFitxerAdaptat();
      } else {
        f = firma.getFitxerFirmat();
      }
    }

    return f;
  }

  /**
   * 
   * @return Pot retornar null si encara no hi ha firmes
   */
  @Override
  public FirmaJPA getLastSignOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException {

    if (peticioDeFirmaID == null) {
      return null;
    }

    /*
     * Long fluxID = peticioDeFirma.getFluxDeFirmesID();
     * 
     * // Cercam tots els blocs de firma del flux Where wBase =
     * BlocDeFirmesFields.FLUXDEFIRMESID.equal(fluxID);
     * 
     * SubQuery<BlocDeFirmes, Long> subQueryBlocs; subQueryBlocs =
     * blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.BLOCDEFIRMESID, wBase);
     * FirmaFields.BLOCDEFIRMAID.in(subQueryBlocs);
     * 
     * // Cercam la firma amb numfirmadocument not null i major. // Si ordenam aquestes firmes
     * de forma descendent per numfirmadocument // el primer element d'aquesta llista serà la
     * darrera firma
     * 
     * Where w = Where.AND(FirmaFields.NUMFIRMADOCUMENT.isNotNull(),
     * FirmaFields.BLOCDEFIRMAID.in(subQueryBlocs));
     * 
     * 
     * log.info("XXXX getLastSignOfPeticioDeFirma() : SQL = " + w.toSQL());
     * 
     * final Integer firstResult = null; final Integer maxResults = 1; List<Firma> firmes =
     * firmaEjb.select(w, firstResult, maxResults, new OrderBy( FirmaFields.NUMFIRMADOCUMENT,
     * OrderType.DESC));
     * 
     * if (firmes == null || firmes.size() == 0) { return null; } else { return (FirmaJPA)
     * firmes.get(0); }
     */

    LongField PETICIOID = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();

    // TODO falta selectOne !!!!
    final Integer firstResult = null;
    final Integer maxResults = 1;

    Where where = Where.AND(PETICIOID.equal(peticioDeFirmaID),
        FirmaFields.NUMFIRMADOCUMENT.isNotNull());

    // TODO Falta selectOne
    List<Firma> firmes = firmaLogicaEjb.select(where, firstResult, maxResults, new OrderBy(
        FirmaFields.NUMFIRMADOCUMENT, OrderType.DESC));

    if (firmes == null || firmes.size() == 0) {
      return null;
    } else {
      return (FirmaJPA) firmes.get(0);
    }

  }

  /**
   * Retorna els identificadors i index de les firmes realitzades de la petició
   * 
   * @return Pot retornar null si encara no hi ha firmes
   */
  protected Map<Integer, IPortaFIBDataSource> getFitxersFirmatsOfPeticioDeFirma(
      Long peticioDeFirmaID) throws I18NException {

    if (peticioDeFirmaID == null) {
      return null;
    }

    LongField PETICIOID = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();

    Where where = Where.AND(PETICIOID.equal(peticioDeFirmaID),
        FirmaFields.NUMFIRMADOCUMENT.isNotNull());

    Select<Long> keySelect = FirmaFields.FITXERFIRMATID.select;
    Select<Integer> valueSelects = FirmaFields.NUMFIRMADOCUMENT.select;

    SelectMultipleKeyValue<Long> select = new SelectMultipleKeyValue<Long>(keySelect,
        valueSelects);

    List<KeyValue<Long>> firmes = firmaLogicaEjb.executeQuery(select, where, new OrderBy(
        FirmaFields.NUMFIRMADOCUMENT, OrderType.DESC));

    if (firmes == null || firmes.size() == 0) {
      return null;
    } else {

      Map<Integer, IPortaFIBDataSource> fitxersByNumFirma = new HashMap<Integer, IPortaFIBDataSource>();
      for (KeyValue<Long> keyValue : firmes) {

        fitxersByNumFirma.put(Integer.parseInt(keyValue.getValue()), new FitxerIdDataSource(
            keyValue.getKey()));
      }

      return fitxersByNumFirma;
    }

  }

  @Override
  public void nouFitxerFirmat(File signatureFile, Long estatDeFirmaID, Long peticioDeFirmaID,
      String token, int numFirmaPortaFIB, int numFirmesOriginals) throws I18NException {

    Long fileID = null;
    try {
      FirmaEventList events = new FirmaEventList();

      // Check Bloqueig
      // Poden passar tres coses:
      // CAS 1: null si el token no existeix o s'ha tardat massa temps en
      // firmar).
      // CAS 2: true si tot està correcte (esta bloquejat per propi usuari)
      // CAS 3: false esta bloquejat per un altre usuari
      Boolean check = checkPeticioDeFirmaByToken(peticioDeFirmaID, token);
      if (check == null) {
        // TODO XYZ ZZZ traduir
        String msg = "Ha tardat massa temps en firmar la petició " + peticioDeFirmaID
            + " i ha expirat el temps. Tornau-ho a intentar (" + token + ")";
        throw new Exception(msg, new Exception());
      } else {
        if (check == false) {
          // TODO XYZ ZZZ traduir
          String msg = "Aquesta petició de firma (" + peticioDeFirmaID
              + ") esta bloquejada per un altre usuari."
              + " Tornau-ho a intentar en un parell de minuts (" + token + ")";
          throw new Exception(msg, new Exception());
        }
      }

      // Llegir Dades
      PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKeyFull(peticioDeFirmaID);
      EstatDeFirmaJPA estatDeFirma = estatDeFirmaLogicaEjb
          .findByPrimaryKeyUnauthorized(estatDeFirmaID);
      Long firmaID = (Long) estatDeFirma.getFirmaID();
      FirmaJPA firma = firmaLogicaEjb.findByPrimaryKey(firmaID);

      // Checks
      final String languageUI;
      final String entitatID;
      if (peticioDeFirma.getSolicitantUsuariEntitat1() != null) {
        entitatID = peticioDeFirma.getSolicitantUsuariEntitat1().getEntitatID();
        languageUI = peticioDeFirma.getSolicitantUsuariEntitat1().getUsuariPersona()
            .getIdiomaID();
      } else {
        entitatID = peticioDeFirma.getUsuariAplicacio().getEntitatID();
        languageUI = peticioDeFirma.getUsuariAplicacio().getIdiomaID();
      }

      // (a) Validar nova firma si es correcte
      int tipusFirma = peticioDeFirma.getTipusFirmaID();

      Map<Integer, IPortaFIBDataSource> fitxersByNumFirma = null;
      if (numFirmaPortaFIB != 1) {
        fitxersByNumFirma = getFitxersFirmatsOfPeticioDeFirma(peticioDeFirma
            .getPeticioDeFirmaID());
      }

      IPortaFIBDataSource documentDetached;
      {
        Long firmaOriginalDetached = peticioDeFirma.getFirmaOriginalDetachedID();
        if (firmaOriginalDetached != null) {
          documentDetached = new FitxerIdDataSource(firmaOriginalDetached);
        } else {
          documentDetached = null;
        }
      }

      IPortaFIBDataSource signature = null;
      try {
        signature = new FileDataSource(signatureFile);
      } catch (Exception e1) {
        // XYZ ZZZ Falta traduir missatge
        String msg = "No s'ha pogut llegir el fitxer de Firma per la validació: "
            + e1.getMessage();
        log.error(msg, e1);
        throw new I18NException("genapp.comodi", msg);
      }

      IPortaFIBDataSource fitxerOriginal = new FitxerIdDataSource(
          peticioDeFirma.getFitxerAdaptatID());

      // final Boolean checkCanviatDocFirmat = usuariEntitatEjb.executeQueryOne(
      // new UsuariEntitatQueryPath().ENTITAT().CHECKCANVIATDOCFIRMAT(),
      // UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirma.getUsuariEntitatID()));

      Entitat entitat = entitatEjb.findByPrimaryKey(entitatID);

      boolean validarFitxerFirma = entitat.getPluginValidaFirmesID() == null ? false : true;
      boolean checkCanviatDocFirmat = entitat.isCheckCanviatDocFirmat();
      boolean comprovarNifFirma = entitat.isComprovarNifFirma();

      String expectedNif;
      {
        final StringField NIF = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();
        final Where where = UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirma
            .getUsuariEntitatID());
        expectedNif = usuariEntitatEjb.executeQueryOne(NIF, where);
      }

      ValidacioCompletaRequest validacioRequest = new ValidacioCompletaRequest(entitatID,
          validarFitxerFirma, checkCanviatDocFirmat, comprovarNifFirma, fitxerOriginal,
          signature, documentDetached, peticioDeFirma.getTipusFirmaID(), 
          peticioDeFirma.getModeDeFirma(), languageUI,
          fitxersByNumFirma, numFirmaPortaFIB, numFirmesOriginals, expectedNif);

      // Aqui es fan totes les validacions completes !!!!!!
      ValidacioCompletaResponse validacioResponse;
      validacioResponse = validacioCompletaLogicaEjb.validateCompletaFirma(validacioRequest);

      firma.setNumeroSerieCertificat(validacioResponse.getNumeroSerieCertificat());
      firma.setEmissorCertificat(validacioResponse.getEmissorCertificat());
      firma.setNomCertificat(validacioResponse.getSubjectCertificat());

      // XYZ ZZZ
      // Verificar que com a minim aquests tres valors darrers SON NOT NULL !!!!

      // Guardar EN BBDD

      // 1.- Crear fitxer en BBDD
      FitxerJPA fitxer = new FitxerJPA();
      fitxer.setDescripcio("");
      fitxer.setMime(validacioResponse.getMime());
      // XYZ FALTA NOM DE FITXER ORIGINAL
      fitxer.setNom("PeticioFirma_" + peticioDeFirmaID + "."
          + validacioResponse.getExtension());
      fitxer.setTamany(signatureFile.length());

      fitxer = fitxerLogicaEjb.createFull(fitxer);

      // 2.- Tancar l'estat de firma
      final Timestamp now = new Timestamp(System.currentTimeMillis());

      estatDeFirma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT);
      estatDeFirma.setDataFi(now);
      estatDeFirma = (EstatDeFirmaJPA) estatDeFirmaLogicaEjb.update(estatDeFirma);
      events.firma_parcial(peticioDeFirma, estatDeFirma);

      // 3.- Associar Fitxer a la Firma i guardar
      firma.setNumFirmaDocument(numFirmaPortaFIB);
      firma.setFitxerFirmatID(fitxer.getFitxerID());
      firma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT);
      firmaLogicaEjb.update(firma);

      // 4.- Descartar tots els EstatDeFirma associats a la firma
      if (log.isDebugEnabled()) {
        log.debug(" FIRMAID = " + firmaID);
      }

      Where w = Where.AND(EstatDeFirmaFields.FIRMAID.equal(firmaID),
          EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull());
      List<EstatDeFirma> estatsDeFirma = estatDeFirmaLogicaEjb.select(w);
      if (log.isDebugEnabled()) {
        log.debug("Count Estats de Firma = " + estatsDeFirma.size());
      }
      for (EstatDeFirma estat : estatsDeFirma) {
        estat.setDataFi(now);
        estat.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
        estat.setDescripcio("Alguna altra persona ja ha firmat la peticio");
        estat = estatDeFirmaLogicaEjb.update(estat);
        if (estat.getTipusEstatDeFirmaInicialID() == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
          events.descartat_per_firmar(peticioDeFirma, estat);
        } else {
          events.descartat_per_validar(peticioDeFirma, estat);
        }

      }

      // 5.- Comprobar si s'han de descartar firmes no obligatories.
      // La formula és: #"firmes obligatories" +
      // #"firmes no obligatories ja firmades" <= "minim de firmes"
      {
        long blocID = firma.getBlocDeFirmaID();
        BlocDeFirmes bloc = blocDeFirmesEjb.findByPrimaryKey(blocID);
        final long minim_de_firmes = bloc.getMinimDeFirmes();

        final Where firmes_obligatories = Where.AND(FirmaFields.BLOCDEFIRMAID.equal(blocID),
            FirmaFields.OBLIGATORI.equal(true));
        final Where firmes_no_obligatories_ja_firmades = Where.AND(
            FirmaFields.BLOCDEFIRMAID.equal(blocID), FirmaFields.OBLIGATORI.equal(false),
            FirmaFields.FITXERFIRMATID.isNotNull());

        final long firmes = firmaLogicaEjb.count(Where.OR(firmes_obligatories,
            firmes_no_obligatories_ja_firmades));

        if (firmes >= minim_de_firmes) {
          // Descartar les firmes no obligatories que no estan firmades
          final Where firmes_no_obligatories_no_firmades = Where.AND(
              FirmaFields.BLOCDEFIRMAID.equal(blocID), FirmaFields.OBLIGATORI.equal(false),
              FirmaFields.FITXERFIRMATID.isNull());
          List<Long> firmesIDsList = firmaLogicaEjb.executeQuery(FirmaFields.FIRMAID,
              firmes_no_obligatories_no_firmades);

          if (firmesIDsList != null && firmesIDsList.size() > 0) {
            final Long[] firmaIDs = firmesIDsList.toArray(new Long[firmesIDsList.size()]);
            log.info("Descartant els estats de firma de les firmes "
                + Arrays.toString(firmaIDs) + " a causa de que la firma no és necessaria "
                + " al haver arribat al minim de firmes requerides.");
            // TODO traduir msg
            final String msg = "Firma no necessaria al haver arribat al mínim de firmes requerides";
            descartarEstatsDeFirma(firmaIDs, msg, peticioDeFirma, events, now);
          }
        }
      }

      // 6.- Cercar següent firma o finalitzar la petició
      FluxDeFirmesJPA flux;
      flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForNextSign(peticioDeFirma
          .getFluxDeFirmesID());

      boolean peticioFinalitzada;
      peticioFinalitzada = startNextSign(peticioDeFirma, flux, estatDeFirma, events);

      // 7.- Enviar Avisos
      firmaEventManagerEjb.processList(events, true);

      // IMPORTATNT: Això ha de ser lo darrer per si hi hagues algun error en
      // les passes anteriors
      // 8.- Guardar Fitxer en Sistema de Fitxers
      FileSystemManager.sobreescriureFitxer(signatureFile, fitxer.getFitxerID());
      fileID = fitxer.getFitxerID();

      // 9.- PeticióFinalitzada:
      // 9.1.- PeticióFinalitzada: Custodia
      if (peticioFinalitzada && peticioDeFirma.getCustodiaInfoID() != null) {

        CustodiaInfo custInfo = custodiaInfoLogicaEjb.findByPrimaryKey(peticioDeFirma
            .getCustodiaInfoID());
        if (custInfo != null && custInfo.isCustodiar() && signatureFile != null) {

          IDocumentCustodyPlugin plugin = pluginDeCustodiaLogicaEjb
              .getInstanceByPluginID(custInfo.getPluginID());

          custInfo.setDataCustodia(new Timestamp(new Date().getTime()));
          custodiaInfoLogicaEjb.update(custInfo);

          Map<String, Object> additionParameters = getAdditionalParametersForDocumentCustody(
              peticioDeFirma, custInfo);

          // Si la firma es DETACHED (o sigui EXPLICID) s'ha de definir DocumentCustody
          DocumentCustody dc = null;
          if (tipusFirma != ConstantsV2.TIPUSFIRMA_PADES
              && peticioDeFirma.getModeDeFirma() == ConstantsV2.SIGN_MODE_EXPLICIT) {

            FitxerJPA fitxerAFirmar = peticioDeFirma.getFitxerAFirmar();

            dc = new DocumentCustody();
            dc.setData(FileSystemManager.getFileContent(fitxerAFirmar.getFitxerID()));
            dc.setLength(dc.getData().length);
            dc.setMime(dc.getMime());
            dc.setName(dc.getName());

          }

          switch (tipusFirma) {
            case ConstantsV2.TIPUSFIRMA_PADES: {
              SignatureCustody sc = new SignatureCustody();
              sc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              sc.setData(FileSystemManager.getFileContent(fileID));
              sc.setSignatureType(SignatureCustody.PADES_SIGNATURE);
              plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, null, sc,
                  null);
            }
            break;
            case ConstantsV2.TIPUSFIRMA_XADES: {
              SignatureCustody sc = new SignatureCustody();
              sc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              sc.setData(FileSystemManager.getFileContent(fileID));
              sc.setSignatureType(SignatureCustody.XADES_SIGNATURE);
              plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, dc, sc,
                  null);
            }
            break;
            case ConstantsV2.TIPUSFIRMA_CADES: {
              SignatureCustody sc = new SignatureCustody();
              sc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              sc.setData(FileSystemManager.getFileContent(fileID));
              sc.setSignatureType(SignatureCustody.CADES_SIGNATURE);
              plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, dc, sc,
                  null);
            }
            break;
            default:
              throw new Exception("Tipus de Firma " + tipusFirma + " no suportada !!!!");
          }

        }

      }

      // 9.2.- PeticióFinalitzada: Esborrat de fitxers innecessaris
      if (peticioFinalitzada) {
        try {
          ferNetejaPeticioFinalitzadaRebutjada(peticioDeFirma, firma);
        } catch (Throwable error) {
          String msg;
          if (error instanceof I18NException) {
            msg = I18NLogicUtils.getMessage((I18NException) error, new Locale("ca"));
          } else {
            msg = error.getMessage();
          }

          // Si hi ha errors la cosa no és greu i es deixa passar
          log.error("Error greu netejant peticio de firma finalitzada o rebutjada "
              + peticioDeFirma.getPeticioDeFirmaID() + ": " + msg, error);
        }

        // Estadistiques
        try {

          Estadistica est = new EstadisticaJPA();

          est.setValor(1.0);
          est.setUsuariAplicacioID(peticioDeFirma.getSolicitantUsuariAplicacioID());
          est.setTipus(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FINAL);
          est.setUsuariEntitatID(null);
          {
            Properties params = new Properties();
            params.setProperty("entitatID", entitatID);
            params.setProperty("peticioDeFirmaID",
                String.valueOf(peticioDeFirma.getPeticioDeFirmaID()));
            params.setProperty("tipusFirmaID",
                String.valueOf(peticioDeFirma.getTipusFirmaID()));
            params.setProperty("tipusDocumentID",
                String.valueOf(peticioDeFirma.getTipusDocumentID()));
            String usrent = peticioDeFirma.getSolicitantUsuariEntitat1ID();
            if (usrent != null) {
              params.setProperty("usuariEntitatID", usrent);
            }
            est.setParametres(getPropertiesAsString(params));
          }
          est.setEntitatID(entitatID);
          est.setData(new Timestamp(System.currentTimeMillis()));

          estadisticaEjb.create(est);
        } catch (Throwable th) {
          log.error("Error afegint estadistiques de Peticio Finalitzada: " + th.getMessage(),
              th);
        }

      }

    } catch (Throwable error) {

      if (fileID != null) {
        try {
          FileSystemManager.eliminarArxiu(fileID);
        } catch (Throwable e) {
          log.error("Error intenant borrar ");
        }
      }

      log.error("ERROR GREU AL REBRE UN NOU FITXER FIRMAT: " + error.getMessage(), error);

      context.setRollbackOnly();

      if (error instanceof I18NException) {
        throw (I18NException) error;
      }

      throw new I18NException(error, "error.unknown", new I18NArgumentString(
          error.getMessage()));
    } finally {
      try {
        if (!unlockPeticioDeFirma(peticioDeFirmaID, token)) {
          log.error("No s'ha pogut desbloquejar la petició " + peticioDeFirmaID
              + " amb token " + token);
          for (Long peticioID : PeticioDeFirmaLogicaEJB.locks.keySet()) {
            Token tok = PeticioDeFirmaLogicaEJB.locks.get(peticioID);
            log.error("LOCKS: " + peticioID + " --> T(ms): " + tok.getTimeInMs() + " | U: "
                + tok.usuariEntitatID);
          }
          log.error("Forcing to unlock peticioDeFirmaID = " + peticioDeFirmaID);
          PeticioDeFirmaLogicaEJB.locks.remove(peticioDeFirmaID);
        }
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  @Override
  public void cleanOriginalFilesOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException {

    PeticioDeFirmaJPA peticioDeFirma = this.findByPrimaryKey(peticioDeFirmaID);

    // ----- 1.- FER CHECKS

    if (peticioDeFirma == null) {
      return;
    }

    // L'únic estat en que es pot fer neteja d'originals és l'estat Firmat
    if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
      // Les peticions no iniciades no es poden netejar
      throw new I18NException("peticiodefirma.error.neteja.nofirmat",
          peticioDeFirma.getTitol() + "(" + peticioDeFirma.getPeticioDeFirmaID() + ")");
    }

    // ----- 2.- FER NETEJA DEL FITXER ADAPTAT I FIRMES INTERMITGES
    FirmaJPA firmaJPA = getLastSignOfPeticioDeFirma(peticioDeFirmaID);
    ferNetejaPeticioFinalitzadaRebutjada(peticioDeFirma, firmaJPA);

    // ----- 3.- FER NETEJA D'ANNEXES ADJUNTS
    List<Annex> llista = annexLogicaEjb.select(Where.AND(
        AnnexFields.PETICIODEFIRMAID.equal(peticioDeFirmaID), AnnexFields.FIRMAR.equal(true),
        AnnexFields.ADJUNTAR.equal(true)));
    if (llista != null) {
      for (Annex annex : llista) {
        Set<Long> filesToDelete = annexLogicaEjb.deleteFull(annex);
        FileSystemManager.eliminarArxius(filesToDelete);
      }
    }

    // ----- 4.- FER NETEJA DEL FITXER ORIGINAL
    Long fitxerAfirmarID = peticioDeFirma.getFitxerAFirmarID();
    if (fitxerAfirmarID != null) {
      peticioDeFirma.setFitxerAFirmarID(null);
      update(peticioDeFirma);
      fitxerLogicaEjb.deleteFull(fitxerAfirmarID);
    }

  }

  @Override
  public void cleanAdaptatFileOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException {

    PeticioDeFirmaJPA peticioDeFirma = this.findByPrimaryKey(peticioDeFirmaID);

    // ----- 1.- FER CHECKS
    if (peticioDeFirma == null) {
      return;
    }

    // L'únic estat en que es pot fer neteja d'originals és l'estat Firmat
    int estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
    if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT
        || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT) {
      // ----- FER NETEJA DEL FITXER ADAPTAT I FIRMES INTERMITGES
      FirmaJPA firmaJPA = getLastSignOfPeticioDeFirma(peticioDeFirmaID);
      ferNetejaPeticioFinalitzadaRebutjada(peticioDeFirma, firmaJPA);

    } else {
      // Les peticions no iniciades no es poden netejar
      throw new I18NException("peticiodefirma.error.neteja.nofirmat",
          peticioDeFirma.getTitol() + "(" + peticioDeFirma.getPeticioDeFirmaID() + ")");
    }

  }

  /**
   * Fer neteja de tots els fitxers firmats (excepte el darrer si es una petició finalitzada) i
   * del fitxer adaptat.
   * 
   * @param peticioDeFirma
   * @param firma
   *          Si val null, llavors s'han d'esborrar totes les firmes ja que és un rebuig
   */
  protected void ferNetejaPeticioFinalitzadaRebutjada(PeticioDeFirmaJPA peticioDeFirma,
      FirmaJPA firma) throws I18NException {

    final Long peticioDeFirmaID = peticioDeFirma.getPeticioDeFirmaID();

    final boolean debug = log.isDebugEnabled();

    // (a) Esborrar document Adaptat
    Long fitxerAdaptatID = peticioDeFirma.getFitxerAdaptatID();
    if (fitxerAdaptatID != null) {
      peticioDeFirma.setFitxerAdaptatID(null);
      this.update(peticioDeFirma);
      if (debug) {
        log.debug("Fer neteja de Fitxer Adaptat: ID = " + fitxerAdaptatID);
      }

      if (!fitxerLogicaEjb.deleteFull(fitxerAdaptatID)) {
        log.warn("No s'ha pogut esborrar fitxer adaptat ( " + fitxerAdaptatID + ")");
      }
    }

    // (b) Esborrar Firmes Intermitges
    final LongField PETICIOID = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES()
        .PETICIODEFIRMA().PETICIODEFIRMAID();

    Where where = Where.AND(PETICIOID.equal(peticioDeFirmaID),
        FirmaFields.NUMFIRMADOCUMENT.isNotNull(), FirmaFields.FITXERFIRMATID.isNotNull());

    if (firma != null) {
      where = Where.AND(where, FirmaFields.FITXERFIRMATID.notEqual(firma.getFitxerFirmatID()));
    }

    List<Firma> firmes = firmaLogicaEjb.select(where);

    if (debug) {
      log.debug("Fer neteja de FITXERS INNECESSARIS DE firmes. LEN = " + firmes.size());
    }

    for (Firma f : firmes) {

      Long fitxerID = f.getFitxerFirmatID();
      if (debug) {
        log.debug("Fer neteja de FITXERS INNECESSARIS DE firmes. FITXER_ID = " + fitxerID);
      }
      f.setFitxerFirmatID(null);
      firmaLogicaEjb.update(f);

      fitxerLogicaEjb.deleteFull(fitxerID);
    }

  }

  @Override
  public void marcarRevisant(EstatDeFirma estatDeFirma, Firma firma,
      PeticioDeFirma peticioDeFirma) throws I18NException {

    // Check si hi ha colaboradors-revisors
    Where w1 = Where.AND(EstatDeFirmaFields.FIRMAID.equal(firma.getFirmaID()),
        EstatDeFirmaFields.DATAFI.isNull(), // No tancat
        EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
            .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR));
    Where w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull();
    ColaboracioDelegacioQueryPath cdqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO();
    Where w3 = Where.AND(cdqp.REVISOR().equal(true), cdqp.ACTIVA().equal(true), cdqp
        .ESDELEGAT().equal(false) // Es col·laborador
        );

    if (estatDeFirmaLogicaEjb.count(Where.AND(w1, w2, w3)) != 0) {
      // Existeixen col·laboradors-revisors per la qual cosa un col·laborador
      // no es pot apropir d'una col·laboració
      throw new I18NException("estatdefirma.marcarrevisant.colaboradorrevisor");
    }

    // Marcam revisant-per-validar
    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma
        .setTipusEstatDeFirmaInicialID(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
    estatDeFirma.setDescripcio("El document ha sigut marcat per revisar");
    estatDeFirmaLogicaEjb.update(estatDeFirma);

    List<EstatDeFirma> estatsDeFirma;
    estatsDeFirma = estatDeFirmaLogicaEjb.select(w1);

    for (EstatDeFirma estat : estatsDeFirma) {
      estat.setDataFi(now);
      estat.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      estat.setDescripcio("De la validació d'aquest document s´encarregarà"
          + " l´usuari-entitat " + estatDeFirma.getUsuariEntitatID());
      estatDeFirmaLogicaEjb.update(estat);
    }

  }

  @Override
  public void acceptar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma)
      throws I18NException {

    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_ACCEPTAT);
    estatDeFirma.setDescripcio("El document ha sigut acceptat pel revisor de Firmes");
    estatDeFirmaLogicaEjb.update(estatDeFirma);
  }

  @Override
  public void validar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma)
      throws I18NException {

    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_VALIDAT);
    estatDeFirma.setDescripcio("El document ha sigut validat");
    estatDeFirmaLogicaEjb.update(estatDeFirma);
  }

  @Override
  public void invalidar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma,
      String motiuInvalidacio) throws I18NException {

    if (motiuInvalidacio == null || motiuInvalidacio.trim().length() == 0) {
      throw new I18NException("estatdefirma.motiu.buit", new I18NArgumentCode(
          "estatdefirma.motiu.invalidacio"));
    }

    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
    estatDeFirma.setDescripcio(motiuInvalidacio);
    estatDeFirmaLogicaEjb.update(estatDeFirma);
  }

  protected void rebutjarEstadistica(String usuariEntitatIDQueRebutja, String entitatID,
      PeticioDeFirmaJPA peticioDeFirma) {
    // Estadistiques
    try {

      Estadistica est = new EstadisticaJPA();

      est.setValor(1.0);
      est.setUsuariAplicacioID(peticioDeFirma.getSolicitantUsuariAplicacioID());
      est.setTipus(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_REBUTJADA);
      est.setUsuariEntitatID(usuariEntitatIDQueRebutja);
      {
        Properties params = new Properties();
        params.setProperty("entitatID", entitatID);
        params.setProperty("peticioDeFirmaID",
            String.valueOf(peticioDeFirma.getPeticioDeFirmaID()));
        params.setProperty("tipusFirmaID", String.valueOf(peticioDeFirma.getTipusFirmaID()));
        params.setProperty("tipusDocumentID",
            String.valueOf(peticioDeFirma.getTipusDocumentID()));
        String usrent = peticioDeFirma.getSolicitantUsuariEntitat1ID();
        if (usrent != null) {
          params.setProperty("usuariEntitatID", usrent);
        }
        est.setParametres(getPropertiesAsString(params));
      }
      est.setEntitatID(entitatID);
      est.setData(new Timestamp(System.currentTimeMillis()));

      estadisticaEjb.create(est);
    } catch (Throwable th) {
      log.error("Error afegint estadistiques de Peticio Finalitzada: " + th.getMessage(), th);
    }
  }

  @Override
  public void rebutjarADEN(PeticioDeFirmaJPA peticioDeFirma, String usuariEntitatAden,
      String motiuDeRebuig) throws I18NException {

    EstatDeFirmaQueryPath efqp = new EstatDeFirmaQueryPath();

    LongField PETICIODEFIRMAID = efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();

    List<EstatDeFirma> estatsPendentsDeFirma = estatDeFirmaLogicaEjb.select(Where.AND(
        EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
            .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
        EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(), PETICIODEFIRMAID
            .equal(peticioDeFirma.getPeticioDeFirmaID())));

    if (estatsPendentsDeFirma.isEmpty()) {
      // Això significa que esta pausada i totes les firmes pendents s'han
      // firmat
      Timestamp now = new Timestamp(System.currentTimeMillis());
      FirmaEventList events = new FirmaEventList();

      // marcar la peticio de firma com rebutjat
      peticioDeFirma.setMotiuDeRebuig(motiuDeRebuig);
      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
      peticioDeFirma.setDataFinal(now);
      this.update(peticioDeFirma);

      events.peticio_rebutjada(peticioDeFirma, null);

      // Avisar del rebuig
      firmaEventManagerEjb.processList(events, true);

      rebutjarEstadistica(usuariEntitatAden, peticioDeFirma.getUsuariAplicacio()
          .getEntitatID(), peticioDeFirma);

    } else {

      EstatDeFirma estatDeFirma = estatsPendentsDeFirma.get(0);

      Firma firma = firmaLogicaEjb.findByPrimaryKey(estatDeFirma.getFirmaID());

      rebutjar(estatDeFirma, firma, peticioDeFirma, motiuDeRebuig);
    }

    // Fer neteja de tots els fitxers firmats i del fitxer adaptat
    // No és una acció crítica, d'aqui el try-catch: només es per alliberar espai
    try {
      ferNetejaPeticioFinalitzadaRebutjada(peticioDeFirma, null);
    } catch (Throwable error) {
      String msg;
      if (error instanceof I18NException) {
        msg = I18NLogicUtils.getMessage((I18NException) error, new Locale("ca"));
      } else {
        msg = error.getMessage();
      }

      // Si hi ha errors la cosa no és greu i es deixa passar
      log.error("Error greu netejant peticio de firma finalitzada o rebutjada "
          + peticioDeFirma.getPeticioDeFirmaID() + ": " + msg, error);
    }

  }

  @Override
  public void rebutjar(EstatDeFirma estatDeFirma, Firma firma,
      PeticioDeFirmaJPA peticioDeFirma, String motiuDeRebuig) throws I18NException {

    if (motiuDeRebuig == null || motiuDeRebuig.trim().length() == 0) {
      throw new I18NException("estatdefirma.motiu.buit", new I18NArgumentCode(
          "estatdefirma.motiu.rebuig"));
    }

    Long peticioDeFirmaID = peticioDeFirma.getPeticioDeFirmaID();
    Timestamp now = new Timestamp(System.currentTimeMillis());
    FirmaEventList events = new FirmaEventList();
    try {
      // Marcar l'Estat de Firma com a rebutjat
      estatDeFirma.setDataFi(now);
      estatDeFirma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT);
      estatDeFirma.setDescripcio(motiuDeRebuig);
      estatDeFirma = estatDeFirmaLogicaEjb.update(estatDeFirma);

      firma.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_REBUTJAT);

      // Descartar tots els estat de firma actius associats al flux
      Long fluxDeFirmesID = peticioDeFirma.getFluxDeFirmesID();
      Set<Long> firmes = new HashSet<Long>();
      List<EstatDeFirma> estatsDeFirma;
      estatsDeFirma = estatDeFirmaLogicaEjb.getAllEstatDeFirmaActiuOfFlux(fluxDeFirmesID);
      for (EstatDeFirma estat : estatsDeFirma) {
        // Actualitzam estat
        estat.setDataFi(now);
        estat.setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
        // TODO MISSATGE
        estat.setDescripcio("La petició ha sigut rebutjada per un altre usuari");
        estatDeFirmaLogicaEjb.update(estat);

        // Events
        if (estat.getTipusEstatDeFirmaInicialID() == ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
          events.descartat_per_firmar(peticioDeFirma, estatDeFirma);
        } else {
          events.descartat_per_validar(peticioDeFirma, estatDeFirma);
        }

        firmes.add(estat.getFirmaID());
      }

      // Actualitzam Firmes a estat Descartat
      List<Firma> actualitzarFirmes = firmaLogicaEjb.select(FirmaFields.FIRMAID.in(firmes));
      for (Firma firmaADescartar : actualitzarFirmes) {
        firmaADescartar
            .setTipusEstatDeFirmaFinalID(ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      }

      // Posar data de finalitzacio al Bloc
      long blocID = firma.getBlocDeFirmaID();
      BlocDeFirmes bloc = blocDeFirmesEjb.findByPrimaryKey(blocID);
      bloc.setDataFinalitzacio(now);
      blocDeFirmesEjb.update(bloc);

      // marcar la peticio de firma com rebutjat
      peticioDeFirma.setMotiuDeRebuig(motiuDeRebuig);
      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
      peticioDeFirma.setDataFinal(now);
      if (peticioDeFirma.getSolicitantUsuariEntitat1ID() != null) {
        peticioDeFirma.setAvisWeb(true);
      }
      this.update(peticioDeFirma);
      events.peticio_rebutjada(peticioDeFirma, estatDeFirma);

      // Avisar del rebuig
      firmaEventManagerEjb.processList(events, true);

      // Fer neteja de tots els fitxers firmats i del fitxer adaptat
      // No és una acció crítica, d'aqui el try-catch: només es per alliberar espai
      try {
        ferNetejaPeticioFinalitzadaRebutjada(peticioDeFirma, null);
      } catch (Throwable error) {
        String msg;
        if (error instanceof I18NException) {
          msg = I18NLogicUtils.getMessage((I18NException) error, new Locale("ca"));
        } else {
          msg = error.getMessage();
        }

        // Si hi ha errors la cosa no és greu i es deixa passar
        log.error("Error greu netejant peticio de firma finalitzada o rebutjada "
            + peticioDeFirma.getPeticioDeFirmaID() + ": " + msg, error);
      }

      rebutjarEstadistica(estatDeFirma.getUsuariEntitatID(), peticioDeFirma
          .getUsuariAplicacio().getEntitatID(), peticioDeFirma);

    } finally {
      try {
        unlockPeticioDeFirma(peticioDeFirmaID);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  @Override
  public PeticioDeFirmaJPA resetPeticioDeFirma(long peticioDeFirmaID) throws I18NException,
      Exception {

    PeticioDeFirmaJPA peticio = this.findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

    if (peticio == null) {
      return null;
    }

    // Check si fitxer original és diferent de null. Si val null, significa que s'ha
    // netejada la Petició de Firma i no és pot reinicialitzar
    if (peticio.getFitxerAFirmarID() == null) {
      // Aquesta petició no es pot reinicialitzar ja que s'ha fet neteja dels documents
      // originals que la composaven
      throw new I18NException("peticiodefirma.error.noreinicialitzar", peticio.getTitol()
          + "(" + peticio.getPeticioDeFirmaID() + ")");
    }

    // Fitxers a borrar
    Set<Fitxer> fitxers = new HashSet<Fitxer>();

    try {

      peticio.setDataFinal(null);
      Calendar cal = Calendar.getInstance();
      peticio.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));

      if (peticio.getFitxerAdaptat() != null) {
        fitxers.add(peticio.getFitxerAdaptat());
        peticio.setFitxerAdaptat(null);
        peticio.setFitxerAdaptatID(null);
      }

      peticio.setMotiuDeRebuig(null);

      // Borrar Notificacions
      notificacioWsEjb.delete(NotificacioWSFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      // TODO Borrar Bitacoles ????
      // bitacolaEjb.delete(BitacolaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      peticio.setAvisWeb(false);

      // Borrar Fitxers Firmats, AnnexosFirmats i Estats de Firma

      FluxDeFirmesJPA flux = peticio.getFluxDeFirmes();

      Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();

      for (BlocDeFirmesJPA blocDeFirmes : blocs) {

        blocDeFirmes.setDataFinalitzacio(null);

        List<Long> firmesIDs = new ArrayList<Long>();
        for (FirmaJPA firma : blocDeFirmes.getFirmas()) {

          firmesIDs.add(firma.getFirmaID());

          if (firma.getFitxerFirmatID() != null) {
            fitxers.add(firma.getFitxerFirmat());
            firma.setFitxerFirmat(null);
            firma.setFitxerFirmatID(null);
          }

          firma.setNumFirmaDocument(null);
          firma.setNumeroSerieCertificat(null);
          firma.setEmissorCertificat(null);
          firma.setNomCertificat(null);

          firma.setTipusEstatDeFirmaFinalID(null);

          firma.setAnnexFirmats(new HashSet<AnnexFirmatJPA>());
          firma.setEstatDeFirmas(new HashSet<EstatDeFirmaJPA>());
          // Actualitzam firma
          firmaLogicaEjb.update(firma);
        }

        // Borrar EstatsDeFirma
        estatDeFirmaLogicaEjb.delete(EstatDeFirmaFields.FIRMAID.in(firmesIDs));

        // Borrar AnnexosFirmats
        List<AnnexFirmat> annexosFirmats;
        annexosFirmats = annexFirmatEjb.select(AnnexFirmatFields.FIRMAID.in(firmesIDs));
        for (AnnexFirmat annexFirmat : annexosFirmats) {
          fitxers.add(((AnnexFirmatJPA) annexFirmat).getFitxer());
        }
        annexFirmatEjb.delete(AnnexFirmatFields.FIRMAID.in(firmesIDs));

        // Actualitzam bloc
        blocDeFirmesEjb.update(blocDeFirmes);
      }

      // Custodia: Veure com està el nous sistema (A lo millor s'ha activat o desactivat)
      CustodiaInfo deleteDocumentCustody = null;

      CustodiaInfo custodiaInfo_Peticio_Current = getCustodyInfoOfPeticioFirma(peticio);

      CustodiaInfo custodiaInfo_Entitat_Default = custodiaInfoLogicaEjb
          .getAllowedCustodyInfo(peticio);

      if (custodiaInfo_Entitat_Default == null) {
        // --------- Ara no hi ha custodia -----
        if (custodiaInfo_Peticio_Current == null) {
          // CAS 1: Ara no hi ha custodia i abans tampoc
          // OK No fer res
        } else {
          // CAS 2: Ara no hi ha custodia i abans si n'hi havia
          if (peticio.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
            // CAS 2.a Si la petició ha finalitzat, deslligam la custodiaactual de la peticio
            peticio.setCustodiaInfoID(null);
            peticio.setCustodiaInfo(null);
          } else {
            // CAS 2.b En altres casos esborram la reserva del document i el custody INFO
            // deleteCustodyInfoID = custodiaInfoID_Peticio_Current;
            deleteDocumentCustody = custodiaInfo_Peticio_Current;

            // deslligam la custodiaactual de la peticio
            peticio.setCustodiaInfoID(null);
            peticio.setCustodiaInfo(null);
          }

        }

      } else {
        // --------- Ara hi ha custodia per defecte -----
        if (custodiaInfo_Peticio_Current == null) {
          // CAS 3: Ara hi ha custodia i abans no n'hi havia: s'ha de crear una nova en cas de
          // no editable
          if (custodiaInfo_Entitat_Default.isEditable()) {
            // OK L'usuari pot fer el que vulgui. La deixam sense crear.
          } else {
            // La petició ha de tenir obligatoriament Custòdia (copia de la plantilla)
            cloneCustodiaInfo(peticio, custodiaInfo_Entitat_Default, true);
          }
        } else {

          // Cas 4: Ara hi ha custodia default i la petició també en tenia
          if (peticio.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {

            if (custodiaInfo_Entitat_Default.isEditable()) {
              // Feim una còpia de l'actual
              cloneCustodiaInfo(peticio, custodiaInfo_Peticio_Current, true);
              // Per si s'ha canviat el plugin de CustodyInfo de l'entitat
              peticio.getCustodiaInfo()
                  .setPluginID(custodiaInfo_Entitat_Default.getPluginID());
            } else {
              // Feim una copia de la plantilla de l'entitat
              cloneCustodiaInfo(peticio, custodiaInfo_Entitat_Default, true);
            }

          } else {
            // En qualsevol altra cas: borram custodia actual i la reserva,
            // i cream una de nova clonada de l'entitat

            // Borrar reserva
            // deleteCustodyInfoID = custodiaInfoID_Peticio_Current;
            deleteDocumentCustody = custodiaInfo_Peticio_Current;

            // Nova Custodia
            if (custodiaInfo_Entitat_Default.isEditable()) {
              // Feim una còpia de l'actual de la peticio
              cloneCustodiaInfo(peticio, custodiaInfo_Peticio_Current, true);
            } else {
              // Feim una copia de la plantilla de l'entitat
              cloneCustodiaInfo(peticio, custodiaInfo_Entitat_Default, true);
            }
          }
        }
      }

      // Canviam estat
      peticio.setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT);

      // Actualitzam petició
      peticio = (PeticioDeFirmaJPA) update(peticio);

      // Desbloquejam petició
      try {
        cleanLocks();
        locks.remove(peticio.getPeticioDeFirmaID());
      } catch (Exception e) {
      }

      // borrarFitxers
      for (Fitxer fitxer : fitxers) {
        try {
          fitxerLogicaEjb.delete(fitxer);
          FileSystemManager.eliminarArxiu(fitxer.getFitxerID());
        } catch (Throwable th) {
          log.error("Error borrant fitxers després de fer un reset de la peticio "
              + peticioDeFirmaID, th);
        }
      }

      // Desreservam identificador de custodia
      if (deleteDocumentCustody != null) {

        Long pluginID = deleteDocumentCustody.getPluginID();

        String deleteCustodyID = deleteDocumentCustody.getCustodiaDocumentID();

        IDocumentCustodyPlugin plugin = pluginDeCustodiaLogicaEjb
            .getInstanceByPluginID(pluginID);

        if (plugin == null) {
          log.error("Intentant desreservar identificador de custodia " + deleteCustodyID
              + " però CustodiaPlugin val null !!!!");
        } else {
          try {
            plugin.deleteCustody(deleteCustodyID);
          } catch (Throwable e) {
            log.error("Error desconegut intentant desreservar ID de custodia "
                + deleteCustodyID + ":" + e.getMessage(), e);
          }
        }
      }

    } catch (Throwable e) {
      log.error(e);

      if (e instanceof I18NException) {
        throw (I18NException) e;
      } else {
        throw new Exception(e.getMessage(), e);
      }
    }

    return peticio;

  }

  protected CustodiaInfo getCustodyInfoOfPeticioFirma(PeticioDeFirmaJPA peticio) {
    CustodiaInfo custodiaInfo_Peticio_Current = null;
    {
      Long custodiaInfoID_Peticio_Current = peticio.getCustodiaInfoID();

      if (custodiaInfoID_Peticio_Current != null) {
        custodiaInfo_Peticio_Current = custodiaInfoLogicaEjb
            .findByPrimaryKey(custodiaInfoID_Peticio_Current);
      }
    }
    return custodiaInfo_Peticio_Current;
  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param newMessageFormaPatternForName
   * @return
   * @throws I18NException
   */
  @Override
  public PeticioDeFirmaJPA clonePeticioDeFirma(long peticioDeFirmaID,
      String newMessageFormaPatternForName) throws I18NException {

    return clonePeticioDeFirma(peticioDeFirmaID, newMessageFormaPatternForName, null, null,
        null);

  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param newMessageFormaPatternForName
   * @param fitxerJPA
   * @return
   * @throws I18NException
   */
  @Override
  public PeticioDeFirmaJPA clonePeticioDeFirma(long peticioDeFirmaID,
      String newMessageFormaPatternForName, String descripcio, String motiu,
      FitxerJPA fitxerJPA) throws I18NException {

    PeticioDeFirmaJPA peticioOrig = this.findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

    if (peticioOrig == null) {
      return null;
    }

    // Check si fitxer original és diferent de null. Si val null, significa que s'ha
    // netejada la Petició de Firma i no és pot clonar
    if (peticioOrig.getFitxerAFirmarID() == null) {
      // Aquesta petició no es pot clonar ja que s'ha fet neteja dels document originals que la
      // composaven
      throw new I18NException("peticiodefirma.error.noclonar", peticioOrig.getTitol() + "("
          + peticioOrig.getPeticioDeFirmaID() + ")");
    }

    PeticioDeFirmaJPA peticio = PeticioDeFirmaJPA.toJPA(peticioOrig);
    String titol;
    if (newMessageFormaPatternForName == null
        || newMessageFormaPatternForName.trim().length() == 0) {
      titol = "Copy Of " + peticio.getTitol();
    } else {
      titol = MessageFormat.format(newMessageFormaPatternForName, peticio.getTitol());
    }
    peticio.setTitol(adjustSize(titol, 255));
    if (descripcio != null) {
      peticio.setDescripcio(adjustSize(descripcio, 255));
    }
    if (motiu != null) {
      peticio.setMotiu(adjustSize(motiu, 255));
    }

    // Borrarem els ID's i clonam els fitxers
    Set<Fitxer> fitxers = new HashSet<Fitxer>();

    File file = null;
    try {

      peticio.setPeticioDeFirmaID(0);
      peticio.setDataFinal(null);

      Calendar cal = Calendar.getInstance();
      peticio.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));
      if (fitxerJPA == null) {
        FitxerJPA fitxerClonat = cloneFitxer(fitxers, peticioOrig.getFitxerAFirmar());
        peticio.setFitxerAFirmar(fitxerClonat);
        peticio.setFitxerAFirmarID(fitxerClonat.getFitxerID());
      } else {
        fitxerJPA = (FitxerJPA) fitxerLogicaEjb.create(fitxerJPA);
        fitxers.add(fitxerJPA);
        file = FileSystemManager.crearFitxer(fitxerJPA.getData().getInputStream(),
            fitxerJPA.getFitxerID());

        peticio.setFitxerAFirmar(fitxerJPA);
        peticio.setFitxerAFirmarID(fitxerJPA.getFitxerID());

      }
      cal.add(Calendar.DATE, 15);
      peticio.setDataCaducitat(new Timestamp(cal.getTimeInMillis()));

      peticio.setFitxerAdaptat(null);
      peticio.setFitxerAdaptatID(null);

      peticio.setMotiuDeRebuig(null);

      peticio.setTipusEstatPeticioDeFirmaID(ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT);

      if (peticioOrig.getLogoSegellID() != null) {
        FitxerJPA fitxerClonat = cloneFitxer(fitxers, peticioOrig.getLogoSegell());
        peticio.setLogoSegellID(fitxerClonat.getFitxerID());
        peticio.setLogoSegell(fitxerClonat);
      }

      if (peticio.getNotificacioWSs() != null) {
        peticio.getNotificacioWSs().clear();
      }

      peticio.setAvisWeb(false);

      // FLUX
      peticio.setFluxDeFirmesID(0);
      FluxDeFirmesJPA fluxOrig = peticioOrig.getFluxDeFirmes();

      FluxDeFirmesJPA flux = FluxDeFirmesJPA.toJPA(fluxOrig);
      flux.setFluxDeFirmesID(0);

      flux.setNom(adjustSize(titol, 255));

      Set<BlocDeFirmesJPA> blocsOrig = fluxOrig.getBlocDeFirmess();

      for (BlocDeFirmesJPA blocDeFirmesOrig : blocsOrig) {

        BlocDeFirmesJPA blocDeFirmesJPA = BlocDeFirmesJPA.toJPA(blocDeFirmesOrig);

        blocDeFirmesJPA.setFluxDeFirmesID(0);
        blocDeFirmesJPA.setBlocDeFirmesID(0);
        blocDeFirmesJPA.setDataFinalitzacio(null);

        Set<FirmaJPA> firmes = blocDeFirmesOrig.getFirmas();
        for (FirmaJPA firmaOrig : firmes) {

          FirmaJPA firmaJPA = FirmaJPA.toJPA(firmaOrig);

          firmaJPA.setBlocDeFirmaID(0);
          firmaJPA.setFirmaID(0);
          firmaJPA.setUsuariEntitat(null);

          firmaJPA.setNumFirmaDocument(null);
          firmaJPA.setNumeroSerieCertificat(null);
          firmaJPA.setEmissorCertificat(null);
          firmaJPA.setNomCertificat(null);

          if (firmaJPA.getEstatDeFirmas() != null) {
            firmaJPA.getEstatDeFirmas().clear();
          }
          firmaJPA.setFitxerFirmat(null);
          firmaJPA.setFitxerFirmatID(null);

          firmaJPA.setTipusEstatDeFirmaFinalID(null);

          blocDeFirmesJPA.getFirmas().add(firmaJPA);
        }

        flux.getBlocDeFirmess().add(blocDeFirmesJPA);
      }
      peticio.setFluxDeFirmes(flux);

      // ====== Custodia =========
      // (NOTA: CustodiaInfo no està carregada)

      CustodiaInfo custodiaInfo_Peticio_Current = getCustodyInfoOfPeticioFirma(peticio);

      CustodiaInfo custodiaInfo_Entitat_Allowed = custodiaInfoLogicaEjb
          .getAllowedCustodyInfo(peticio);

      if (custodiaInfo_Entitat_Allowed == null) {
        // --------- Ara no hi ha custodia -----
        if (custodiaInfo_Peticio_Current == null) {
          // CAS 1: Ara no hi ha custodia i abans tampoc
          // OK No fer res
        } else {
          // CAS 2: Ara no hi ha custodia i abans si n'hi havia
          peticio.setCustodiaInfoID(null);
          peticio.setCustodiaInfo(null);
        }

      } else {
        // --------- Ara hi ha custodia per defecte -----
        if (custodiaInfo_Peticio_Current == null) {
          // CAS 3: Ara hi ha custodia i abans no n'hi havia: s'ha de crear una nova en cas de
          // no editable

          if (custodiaInfo_Entitat_Allowed.isEditable()) {
            // OK L'usuari pot fer el que vulgui. La deixam sense crear.
          } else {
            // La petició ha de tenir obligatoriament Custòdia (copia de la plantilla)
            cloneCustodiaInfo(peticio, custodiaInfo_Entitat_Allowed, false);
          }
        } else {

          // Cas 4: Ara hi ha custodia default i la petició també en tenia

          if (custodiaInfo_Entitat_Allowed.isEditable()) {
            // Feim una còpia de l'actual
            cloneCustodiaInfo(peticio, custodiaInfo_Peticio_Current, false);
            // Per si s'ha canviat el plugin de CustodyInfo de l'entitat
            peticio.getCustodiaInfo().setPluginID(custodiaInfo_Entitat_Allowed.getPluginID());
          } else {
            // Feim una copia de la plantilla de l'entitat
            cloneCustodiaInfo(peticio, custodiaInfo_Entitat_Allowed, false);
          }

        }
      }

      /*
       * IDocumentCustodyPlugin custodiaPluginClassID = null; try { custodiaPluginClassID =
       * PortaFIBPluginsManager.getDocumentCustodyPluginInstance(); } catch(Throwable e) { } if
       * (peticio.getCustodiaInfoID() != null && custodiaPluginClassID != null) {
       * 
       * cloneCustodiaInfo(peticio, custodiaPluginClassID);
       * 
       * } else { peticio.setCustodiaInfoID(null); peticio.setCustodiaInfo(null); }
       */

      peticio = createFull(peticio);

      // Necessitam l'identificador de la petició de Firma per annexes i metadades
      for (MetadadaJPA metaOrig : peticioOrig.getMetadadas()) {
        MetadadaJPA metadada = MetadadaJPA.toJPA(metaOrig);

        metadada.setMetadadaID(0);
        metadada.setPeticioDeFirmaID(peticio.getPeticioDeFirmaID());
        metadada.setPeticioDeFirma(peticio);

        metadada = metadadaLogicaEjb.createFull(metadada);

        peticio.getMetadadas().add(metadada);
      }

      for (AnnexJPA annexOrig : peticioOrig.getAnnexs()) {
        AnnexJPA annex = AnnexJPA.toJPA(annexOrig);

        annex.setAnnexID(0);
        annex.setPeticioDeFirmaID(peticio.getPeticioDeFirmaID());
        annex.setPeticioDeFirma(peticio);

        FitxerJPA fitxerClonat = cloneFitxer(fitxers, annexOrig.getFitxer());
        annex.setFitxer(fitxerClonat);
        annex.setFitxerID(fitxerClonat.getFitxerID());

        annex = annexLogicaEjb.createFull(annex);

        peticio.getAnnexs().add(annex);
      }

      return (PeticioDeFirmaJPA) update(peticio);

    } catch (Throwable e) {
      log.error(e.getMessage(), e);
      for (Fitxer fitxer : fitxers) {
        FileSystemManager.eliminarArxiu(fitxer.getFitxerID());
      }

      if (file != null && file.exists()) {
        if (!file.delete()) {
          // TODO WARN
          file.deleteOnExit();
        }
      }

      if (e instanceof I18NException) {
        throw (I18NException) e;
      }
      if (e instanceof I18NValidationException) {

        I18NValidationException valexc = (I18NValidationException) e;

        String msg = I18NLogicUtils.getMessage(valexc, new Locale("ca"));

        throw new I18NException(e, "error.unknown", new I18NArgumentString(msg));

      } else {
        throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
      }
    }
  }

  private String adjustSize(String titolflux, int maxSize) {
    if (titolflux == null) {
      return titolflux;
    }

    if (titolflux.length() >= maxSize) {
      String hash = String.valueOf(titolflux.hashCode());
      int pos = maxSize - hash.length() - 2;
      titolflux = titolflux.substring(0, pos) + "_" + hash;
    }

    return titolflux;
  }

  private void cloneCustodiaInfo(PeticioDeFirmaJPA peticio, CustodiaInfo custOrig,
      boolean create) throws I18NException {

    // TODO Check custOrig != null
    CustodiaInfoBean cloned = new CustodiaInfoBean(custOrig);

    CustodiaInfoJPA clonedCust = new CustodiaInfoJPA(cloned);

    clonedCust.setCustodiaInfoID(0);
    clonedCust.setCustodiaDocumentID(null);
    clonedCust.setUrlFitxerCustodiat(null);
    clonedCust.setNomPlantilla(null);
    clonedCust.setEntitatID(null);
    clonedCust.setTitolPeticio(peticio.getTitol());

    if (peticio.getSolicitantUsuariEntitat1ID() != null) {
      clonedCust.setUsuariEntitatID(peticio.getSolicitantUsuariEntitat1ID());
    } else {
      clonedCust.setUsuariAplicacioID(peticio.getSolicitantUsuariAplicacioID());
    }

    if (create) {
      clonedCust = (CustodiaInfoJPA) custodiaInfoLogicaEjb.create(clonedCust);
      peticio.setCustodiaInfoID(clonedCust.getCustodiaInfoID());
    } else {
      peticio.setCustodiaInfoID(null);
      peticio.setCustodiaInfo(clonedCust);
    }
  }

  private FitxerJPA cloneFitxer(Set<Fitxer> fitxers, FitxerJPA fitxerOrig)
      throws I18NException {

    if (fitxerOrig == null) {
      return null;
    }

    FitxerJPA fitxer = FitxerJPA.toJPA(fitxerOrig);
    fitxer.setFitxerID(0);
    FitxerJPA nouFitxer = (FitxerJPA) fitxerLogicaEjb.create(fitxer);
    fitxers.add(nouFitxer);

    long nouFitxerID = nouFitxer.getFitxerID();
    if (log.isDebugEnabled()) {
      log.info(" + Fitxer original = " + fitxerOrig.getFitxerID());
      log.info(" + Fitxer nou      = " + nouFitxerID);
    }

    FileSystemManager.crearFitxer(FileSystemManager.getFile(fitxerOrig.getFitxerID()),
        nouFitxerID);

    return nouFitxer;
  }

  /**
   * Per depurar
   */
  private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

  /**
   * Envia correus a la gent de les entitats que tenen definida una PropietatGlobal amb clau
   * 'es.caib.portafib.avisosfirmespendents.diesabans'. El valor d'aquesta clau indica el
   * numero de dies abans de la caducitat ed la petició en que s'han de començar a enviar
   * correus. La freqüencia d'enviaments ve determinada per una expressió cron definida en una
   * PropietatsGlobal de PortaFIB amb clau "es.caib.portafib.avisosfirmespendents.cron"
   * 
   * @return retorna els mail als que s'ha enviat un avis
   */
  public Collection<InfoUser> enviarMailPeticionsPendentsDeFirmar() throws Exception,
      I18NException {

    Map<String, InfoUser> allUsuariEntitat = new HashMap<String, InfoUser>();

    boolean isDebug = log.isDebugEnabled();

    // Cercar les entitats que tenguin activat l'enviament de correus
    Where wpg = Where.AND(PropietatGlobalFields.ENTITATID.isNotNull(),
        PropietatGlobalFields.CLAU
            .equal(PropietatGlobalUtil.PROPERTY_BYENTITY_AVISOS_FIRMES_PENDENTS_DIESABANS),
        PropietatGlobalFields.VALOR.isNotNull());

    List<PropietatGlobal> entitatsID = propietatGlobalEjb.select(wpg);

    if (entitatsID == null || entitatsID.size() == 0) {
      if (isDebug) {
        log.debug("No he trobat cap entitat");
      }
      return allUsuariEntitat.values();
    }

    if (isDebug) {
      StringBuffer str = new StringBuffer("Entitats a processar = ");
      for (PropietatGlobal pg : entitatsID) {
        str.append(pg.getEntitatID() + ", ");
      }
      log.debug(str);
    }
    ;

    // Per no sobrecarregar ho farem entitat per entitat
    for (PropietatGlobal pg : entitatsID) {

      String entitatID = pg.getEntitatID();

      if (isDebug) {
        log.debug("");
        log.debug("");
        log.debug("=========" + entitatID + "=========");
      }

      int margeDeDies;

      try {
        margeDeDies = Integer.parseInt(pg.getValor());

        if (margeDeDies <= 0) {
          throw new NumberFormatException("El marge de dies es 0 o negatiu");
        }

      } catch (NumberFormatException e) {
        log.error(
            "Error parsejant propietat de marge de dies de l'entitat " + pg.getEntitatID(), e);
        continue;
      }

      Where where = Where.AND(PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID
          .equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES), new PeticioDeFirmaQueryPath()
          .USUARIAPLICACIO().ENTITATID().equal(entitatID));
      List<PeticioDeFirma> peticions = this.select(where);

      // Cercar nom entitats per petició

      Set<String> usuarisAppIDs = new HashSet<String>();
      for (PeticioDeFirma peticioDeFirma : peticions) {
        usuarisAppIDs.add(peticioDeFirma.getSolicitantUsuariAplicacioID());
      }

      final long now = System.currentTimeMillis();

      List<PeticioDeFirma> avisarA = new ArrayList<PeticioDeFirma>();

      for (PeticioDeFirma peticioDeFirma : peticions) {

        long dif;

        Timestamp inici = peticioDeFirma.getDataSolicitud();
        Timestamp fi = peticioDeFirma.getDataCaducitat();

        boolean enviarCorreu;
        Long firmes;
        Long firmesRealitzades;
        Date dataSeguentAvis;

        if (fi.getTime() < now) {
          dif = -1;
          enviarCorreu = true;
          firmes = null;
          firmesRealitzades = null;
          dataSeguentAvis = null;
        } else {

          long diff = fi.getTime() - inici.getTime();

          // Firmes a realitzar
          dif = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
          Where w = BlocDeFirmesFields.FLUXDEFIRMESID
              .equal(peticioDeFirma.getFluxDeFirmesID());
          firmes = blocDeFirmesEjb.executeQueryOne(new SelectSum(
              BlocDeFirmesFields.MINIMDEFIRMES), w);

          // Firmes realitzades
          Where wf = Where.AND(
              new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMESID()
                  .equal(peticioDeFirma.getFluxDeFirmesID()),
              FirmaFields.TIPUSESTATDEFIRMAFINALID
                  .equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT));
          firmesRealitzades = firmaLogicaEjb.count(wf);
          if (isDebug) {
            log.debug(" ---------------------- ");
            log.debug("firmesRealitzades = " + firmesRealitzades);
          }

          double part = (1.0 * dif / firmes);
          double margeDeDiesAjustat = (1.0 * margeDeDies / firmes);
          if (isDebug) {
            log.debug("part = " + part);
            log.debug("margeDeDiesAjustat (" + margeDeDies + "/" + firmes + " = "
                + margeDeDiesAjustat);
          }

          double diesSeguentAvis = (((firmesRealitzades + 1) * part) - margeDeDiesAjustat);
          if (isDebug) {
            log.debug("diesSeguentAvis = " + diesSeguentAvis);
          }
          ;

          dataSeguentAvis = new Date(inici.getTime()
              + (int) (diesSeguentAvis * 24 * 60 * 60 * 1000));

          enviarCorreu = (dataSeguentAvis.getTime() < now);

        }

        if (enviarCorreu) {
          avisarA.add(peticioDeFirma);
        }

        if (isDebug) {
          log.debug(peticioDeFirma.getTitol() + "\t " + sdf.format(inici) + "\t "
              + sdf.format(fi) + "\t " + dif + "\t "
              + ((firmes == null) ? "-" : String.valueOf(firmes)) + "\t "
              + ((firmesRealitzades == null) ? "-" : String.valueOf(firmesRealitzades))
              + "\t " + ((dataSeguentAvis == null) ? "-" : sdf.format(dataSeguentAvis))
              + "\t " + enviarCorreu + "\t[" + entitatID + "]");
        }
      }

      // Cercar les firmes actives de les peticions a les que hem d'avisar
      Set<String> recuperarMailsDe = new HashSet<String>();

      Map<PeticioDeFirma, Set<String>> avisosUsuariPerPeticio = new HashMap<PeticioDeFirma, Set<String>>();

      for (PeticioDeFirma pf : avisarA) {

        Where wf = Where.AND(new EstatDeFirmaQueryPath().FIRMA().BLOCDEFIRMES()
            .FLUXDEFIRMESID().equal(pf.getFluxDeFirmesID()),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull());

        List<String> avisarusuaris = estatDeFirmaLogicaEjb.executeQuery(
            EstatDeFirmaFields.USUARIENTITATID, wf);

        avisosUsuariPerPeticio.put(pf, new HashSet<String>(avisarusuaris));

        recuperarMailsDe.addAll(avisarusuaris);

        if (isDebug) {
          log.debug("Per la peticio " + pf.getTitol() + " s'ha d'avisar a "
              + Arrays.toString(avisarusuaris.toArray()));
        }

      }

      if (isDebug) {
        log.debug("TOTS = " + Arrays.toString(recuperarMailsDe.toArray()));
      }

      // 1.- Cercar correu del usuaris persona amb id de
      // usuarientitat dins recuperarMailsDe
      // 1.1.- Mail UsuariEntitat
      Map<String, InfoUser> mailsByUsuariEntitat = new HashMap<String, InfoUser>();
      {
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
            UsuariEntitatFields.USUARIENTITATID.select, UsuariEntitatFields.EMAIL.select,
            new UsuariEntitatQueryPath().USUARIPERSONA().IDIOMAID().select);

        Where w1 = Where.AND(UsuariEntitatFields.USUARIENTITATID.in(recuperarMailsDe),
            UsuariEntitatFields.EMAIL.isNotNull());

        List<StringKeyValue> mailUE = usuariEntitatEjb.executeQuery(smskv, w1);

        mailsByUsuariEntitat.putAll(stringKeyValue2UserInfo(mailUE));

      }
      // 1.2.- Mail Usuari Persona
      {
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
            UsuariEntitatFields.USUARIENTITATID.select, new UsuariEntitatQueryPath()
                .USUARIPERSONA().EMAIL().select, new UsuariEntitatQueryPath().USUARIPERSONA()
                .IDIOMAID().select);

        Where w1 = Where.AND(UsuariEntitatFields.USUARIENTITATID.in(recuperarMailsDe),
            UsuariEntitatFields.EMAIL.isNull());

        List<StringKeyValue> mailUP = usuariEntitatEjb.executeQuery(smskv, w1);

        mailsByUsuariEntitat.putAll(stringKeyValue2UserInfo(mailUP));
      }

      if (isDebug) {
        log.debug("mailsByUsuariEntitat.size()::" + mailsByUsuariEntitat.size());
        log.debug("recuperarMailsDe::" + recuperarMailsDe.size());
        for (String ue : mailsByUsuariEntitat.keySet()) {
          log.debug("     " + ue + "\t=> " + mailsByUsuariEntitat.get(ue));
        }
      }

      allUsuariEntitat.putAll(mailsByUsuariEntitat);
      String nomEntitat = entitatEjb.executeQueryOne(EntitatFields.NOM,
          EntitatFields.ENTITATID.equal(entitatID));

      // Muntar mails
      String url = PropietatGlobalUtil.getAppUrl()
          + ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list";
      List<EmailInfo> emailsEntitat = new ArrayList<EmailInfo>();
      for (String ue : allUsuariEntitat.keySet()) {

        InfoUser iu = allUsuariEntitat.get(ue);
        final Locale loc = new Locale(iu.getLanguage());

        String subject = I18NLogicUtils.tradueix(loc, "notificacioavis.requerit_per_firmar");

        String html = I18NLogicUtils.tradueix(loc, "avisfirmapendent", url, nomEntitat);

        EmailInfo email = new EmailInfo();
        email.setEmail(iu.getEmail());
        email.setSubject(subject);
        email.setMessage(html.toString());
        email.setHtml(true);
        email.setUsuariEntitatID(iu.getUsuariEntitat());

        emailsEntitat.add(email);

      } // Final for

      // Enviar mails
      try {
        EmailUtil.enviarMails(emailsEntitat);
      } catch (I18NException e) {
        log.error(
            "Error enviant correus de l'entitat " + entitatID + ": "
                + I18NLogicUtils.getMessage(e, new Locale("ca")), e);
      }

    }
    ; // Final For Entitats

    return allUsuariEntitat.values();
  }

  public Map<String, InfoUser> stringKeyValue2UserInfo(List<StringKeyValue> info) {

    Map<String, InfoUser> tmp = new HashMap<String, InfoUser>();
    for (StringKeyValue skv : info) {
      // key = usuarientitatid
      // value = email + ' ' + language
      String usuarientitatid = skv.getKey();
      String value = skv.getValue();
      int pos = value.lastIndexOf(' ');

      String email = value.substring(0, pos);
      String lang = value.substring(pos + 1);

      tmp.put(usuarientitatid, new InfoUser(usuarientitatid, email, lang));

    }
    return tmp;

  };

  public static class InfoUser {
    final String usuariEntitat;
    final String email;
    final String language;

    /**
     * @param usuariEntitat
     * @param email
     * @param language
     */
    public InfoUser(String usuariEntitat, String email, String language) {
      super();
      this.usuariEntitat = usuariEntitat;
      this.email = email;
      this.language = language;
    }

    public String getUsuariEntitat() {
      return usuariEntitat;
    }

    public String getEmail() {
      return email;
    }

    public String getLanguage() {
      return language;
    }

    @Override
    public String toString() {
      return this.usuariEntitat + "(" + this.language + "): " + this.email;
    }

  }

  protected String getPropertiesAsString(Properties prop) {
    StringWriter writer = new StringWriter();
    try {
      prop.store(writer, "");
    } catch (Exception e) {
      log.error("Error passant properties a String", e);
    }
    return writer.getBuffer().toString();
  }

}
