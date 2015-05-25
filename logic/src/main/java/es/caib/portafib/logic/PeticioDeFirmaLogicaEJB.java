package es.caib.portafib.logic;


import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.ColaboracioDelegacioLocal;
import es.caib.portafib.ejb.PeticioDeFirmaEJB;
import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.AnnexFirmatJPA;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.validator.CustodiaInfoBeanValidator;
import es.caib.portafib.jpa.validator.PeticioDeFirmaBeanValidator;
import es.caib.portafib.logic.events.FirmaEventList;
import es.caib.portafib.logic.events.FirmaEventManagerLocal;
import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.validator.PeticioDeFirmaLogicValidator;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.BitacolaFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioQueryPath;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.MetadadaFields;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

import java.io.File;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.fundaciobit.plugins.barcode.IBarcodePlugin;
import org.fundaciobit.plugins.certificate.InformacioCertificat;
import org.fundaciobit.plugins.documentcustody.DocumentCustody;
import org.fundaciobit.plugins.documentcustody.CustodyException;
import org.fundaciobit.plugins.documentcustody.IDocumentCustodyPlugin;
import org.fundaciobit.plugins.documentcustody.NotSupportedCustodyException;
import org.fundaciobit.plugins.documentcustody.SignatureCustody;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NCommonDateTimeFormat;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectCount;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "PeticioDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class PeticioDeFirmaLogicaEJB extends PeticioDeFirmaEJB implements
    PeticioDeFirmaLogicaLocal, Constants {

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

  @EJB(mappedName = "portafib/UsuariPersonaEJB/local")
  protected es.caib.portafib.ejb.UsuariPersonaLocal usuariPersonaEjb;

  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = "portafib/AnnexLogicaEJB/local")
  private AnnexLogicaLocal annexLogicaEjb;

  @EJB(mappedName = "portafib/FluxDeFirmesLogicaEJB/local")
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.TipusFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusFirmaLocal tipusFirmaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PrioritatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PrioritatLocal prioritatEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PosicioTaulaFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PosicioTaulaFirmesLocal posicioTaulaFirmesEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal tipusEstatPeticioDeFirmaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.AlgorismeDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.AlgorismeDeFirmaLocal algorismeDeFirmaEjb;

  private PeticioDeFirmaLogicValidator<PeticioDeFirmaJPA> validator =
     new PeticioDeFirmaLogicValidator<PeticioDeFirmaJPA>();
  
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
  public PeticioDeFirmaJPA updateFull(PeticioDeFirmaJPA peticioDeFirma)
    throws I18NException, I18NValidationException {
    
    
    PeticioDeFirmaBeanValidator pfbv = new PeticioDeFirmaBeanValidator(validator,
        algorismeDeFirmaEjb, custodiaInfoEjb,  fluxDeFirmesLogicaEjb, idiomaEjb,
        this, posicioTaulaFirmesEjb, prioritatEjb, 
        tipusDocumentEjb, tipusEstatPeticioDeFirmaEjb,  tipusFirmaEjb,
        usuariAplicacioEjb, usuariEntitatEjb);
    
    final boolean isNou = false;
    pfbv.throwValidationExceptionIfErrors(peticioDeFirma, isNou);

    return (PeticioDeFirmaJPA)this.update(peticioDeFirma);
  }
  
  
  
  /**
   * 
   * @param peticioDeFirma
   * @param peticioUuariEntitat
   *          Pot valer null. En aquest cas
   * @return
   */
  @Override
  public PeticioDeFirmaJPA createFull(PeticioDeFirmaJPA peticioDeFirma)
    throws I18NException, I18NValidationException {
    
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
        algorismeDeFirmaEjb, custodiaInfoEjb,  fluxDeFirmesLogicaEjb, idiomaEjb,
        this, posicioTaulaFirmesEjb, prioritatEjb, 
        tipusDocumentEjb, tipusEstatPeticioDeFirmaEjb,  tipusFirmaEjb,
        usuariAplicacioEjb, usuariEntitatEjb);
    
    final boolean isNou = true;
    pfbv.throwValidationExceptionIfErrors(peticioDeFirma, isNou);

    // Crear Peticio
    Calendar cal = Calendar.getInstance();
    peticioDeFirma.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));

    if (peticioDeFirma.getUsuariEntitatID() == null) {
      // Peticio de usuari Aplicacio
      if (peticioDeFirma.getRemitentNom() == null) {
        peticioDeFirma.setRemitentNom(peticioDeFirma.getUsuariAplicacioID());
      }
    } else {
      // Peticio de usuari web
      if (peticioDeFirma.getRemitentNom() == null) {
        UsuariEntitatJPA usuariEntitat;
        usuariEntitat = usuariEntitatEjb.findByPrimaryKey(peticioDeFirma.getUsuariEntitatID());

        UsuariPersona persona;
        persona = usuariPersonaEjb.findByPrimaryKey(usuariEntitat.getUsuariPersonaID());

        // UsuariPersona persona = usuariEntitat.getUsuariPersona();
        peticioDeFirma.setRemitentNom(persona.getNom() + " " + persona.getLlinatges());
        if (peticioDeFirma.getRemitentDescripcio() == null) {
          peticioDeFirma.setRemitentDescripcio(usuariEntitat.getEmail() == null ? persona
              .getEmail() : usuariEntitat.getEmail());
        }
      }
    }
    
    // Afegir Custòdia
    CustodiaInfoJPA custodiaInfo = peticioDeFirma.getCustodiaInfo();
    if (custodiaInfo != null) {
      CustodiaInfoBeanValidator custodiaValidator = new CustodiaInfoBeanValidator(
          codiBarresEjb, custodiaInfoEjb, entitatEjb, posicioPaginaEjb, usuariAplicacioEjb, usuariEntitatEjb);
      
      custodiaValidator.throwValidationExceptionIfErrors(custodiaInfo, isNou);

      custodiaInfo = (CustodiaInfoJPA)custodiaInfoEjb.create(custodiaInfo);

      peticioDeFirma.setCustodiaInfoID(custodiaInfo.getCustodiaInfoID());
    }
    

    PeticioDeFirmaJPA pf = (PeticioDeFirmaJPA) create(peticioDeFirma);

    Long peticioDeFirmaID = pf.getPeticioDeFirmaID();

    if (log.isDebugEnabled()) {
      log.info("PF[" + pf.getPeticioDeFirmaID() + "] = " + pf);
      log.info("PeticioDeFirma[" + peticioDeFirma.getPeticioDeFirmaID() + "] = "
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

  private void initializateFull(PeticioDeFirmaJPA peticioDeFirma)  {

    if (peticioDeFirma == null)
      return;

    initialize_Flux_Annexos_Custody(peticioDeFirma);

    initializeUsuaris(peticioDeFirma);

    Hibernate.initialize(peticioDeFirma.getMetadadas());
  }

  private void initialize_Flux_Annexos_Custody(PeticioDeFirmaJPA peticioDeFirma)  {

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
    
    if (peticioDeFirma.getUsuariEntitatID() != null) {
      Hibernate.initialize(peticioDeFirma.getUsuariEntitat());
      Hibernate.initialize(peticioDeFirma.getUsuariEntitat().getUsuariPersona());
    }

    if (peticioDeFirma.getUsuariAplicacioID() != null) {
      Hibernate.initialize(peticioDeFirma.getUsuariAplicacio());
      
      if (peticioDeFirma.getUsuariAplicacio() == null) {
        
        peticioDeFirma.setUsuariAplicacio(
            usuariAplicacioEjb.findByPrimaryKey(peticioDeFirma.getUsuariAplicacioID()));

        if (peticioDeFirma.getUsuariAplicacio() == null) {
          log.error("No s'ha pogut inicialitzar l'usuari Aplicacio "
            + peticioDeFirma.getUsuariAplicacioID(), new Exception());
        }
      }
      
    }
  }

  /**
   * Inicia una peticio de firma
   */
  @Override
  public void start(Long peticioDeFirmaID) throws I18NException {

    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

    // TODO S'ha de diferenciar entre Iniciar si esta en
    // estat NO_INICIAT o si esta PAUSAT !!!!

    File dstPDF = null;
    String custodyID = null;
    try {

      if (peticioDeFirma == null) {
        // No s´ha trobat cap {0} amb {1} igual a {2}
        throw new I18NException("error.notfound", new I18NArgumentCode(_TABLE_MODEL + "."
            + _TABLE_MODEL), new I18NArgumentCode(PETICIODEFIRMAID.fullName),
            new I18NArgumentString(String.valueOf(peticioDeFirmaID)));
      }

      peticioDeFirma.setDataSolicitud(new Timestamp(new Date().getTime()));
      int currentState = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES);

      FluxDeFirmesJPA flux = peticioDeFirma.getFluxDeFirmes();

      if (log.isDebugEnabled()) {
        log.info("Current State = " + currentState);
        log.info("State NO INICIAT = " + Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT);
      }

      if (Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT == currentState) {
        IDocumentCustodyPlugin plugin = null;
        // Reserva de ID de custodia
        CustodiaInfo custodiaInfo = null;
        if (peticioDeFirma.getCustodiaInfoID() != null) {
          custodiaInfo = custodiaInfoEjb.findByPrimaryKey(peticioDeFirma.getCustodiaInfoID());
          if (custodiaInfo.isCustodiar()) {
            plugin = PortaFIBPluginsManager.getDocumentCustodyPluginInstance();
            // Afegir Hora de Solicitud evita duplicats si reiniciam la petició
            custodyID = plugin.reserveCustodyID(custodiaInfo.getCustodiaPluginParameters());
            // TODO Check custodyID != null
            String url = plugin.getValidationUrl(custodyID);
            custodiaInfo.setCustodiaPluginID(custodyID);
            custodiaInfo.setUrlFitxerCustodiat(url);
            custodiaInfo.setTitolPeticio(peticioDeFirma.getTitol());
            custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));
            
            custodiaInfo = custodiaInfoEjb.update(custodiaInfo);
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

        if (numFirmes > Constants.APPLET_MAX_FIRMES_PER_TAULA) {
          // TODO TRADUIR per quan es passin els missatges a Logica
          throw new Exception("Una peticio de firma pot tenir com a màxim "
              + Constants.APPLET_MAX_FIRMES_PER_TAULA + " firmes obligatories.");
        }

        // Posar ROLE DEST als firmants del flux de firmes
        checkUsersOfFlux(flux);

        Long fitxerFinalAFirmarID;
        int tipusFirma = peticioDeFirma.getTipusFirmaID();

        switch (tipusFirma) {

        case Constants.TIPUSFIRMA_PADES:
          fitxerFinalAFirmarID = thingsToDoInPADES(peticioDeFirma,
            numFirmes, custodiaInfo, plugin);
          dstPDF = FileSystemManager.getFile(fitxerFinalAFirmarID);
          break;

        // TODO
        case Constants.TIPUSFIRMA_XADES:
        case Constants.TIPUSFIRMA_CADES:
        default:
          throw new Exception("Tipus de Firma no suportada !!!!");
        }

        peticioDeFirma.setFitxerAdaptatID(fitxerFinalAFirmarID);
        

      }

      // Cercar següent firma
      FirmaEventList events = new FirmaEventList();
      startNextSign(peticioDeFirma, flux, null, events);

      peticioDeFirma = (PeticioDeFirmaJPA) update(peticioDeFirma);

      events.peticio_en_proces(peticioDeFirma);

      // Avisos
      firmaEventManagerEjb.processList(events);

    } catch (Throwable error) {
      log.error("Error arrancant peticio de firma " + peticioDeFirmaID, error);
      if (dstPDF != null) {
        if (!dstPDF.delete()) {
          dstPDF.deleteOnExit();
        }
      }
      
      if (custodyID != null) {
        try {
          IDocumentCustodyPlugin plugin = PortaFIBPluginsManager.getDocumentCustodyPluginInstance(); 
          plugin.deleteCustody(custodyID);
        } catch (Throwable e) {
          log.error("Error desconegut intentant borrar el document de custodia: " + e.getMessage(), e);
        }
      }

      context.setRollbackOnly();

      if (error instanceof I18NException) {
        throw (I18NException) error;
      } else {
        throw new I18NException(error, "error.unknown",
          new I18NArgumentString(error.getMessage()));
      }
    }

  }

  private long thingsToDoInPADES(PeticioDeFirmaJPA peticioDeFirma, int numFirmes,
      CustodiaInfo custodiaInfo, IDocumentCustodyPlugin plugin)
     throws Exception, I18NException {

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
    if (peticioDeFirma.getUsuariEntitat() != null) {
      // usuari entitat
      log.debug(" Idioma Usuari Entitat: ");
      entitatID = peticioDeFirma.getUsuariEntitat().getEntitatID();
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
      if (peticioDeFirma.getUsuariEntitatID() != null) {
        // usuari entitat
        log.debug(" Idioma Usuari Entitat: ");
        idioma = peticioDeFirma.getUsuariEntitat().getUsuariPersona().getIdiomaID();
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
    if (posicio != Constants.TAULADEFIRMES_SENSETAULA) {
    
      final String desc = peticioDeFirma.getDescripcio();
      final String titol = peticioDeFirma.getTitol();
      final File logo = getLogoOfPeticioDeFirma(peticioDeFirma);
    
      final String signantLabel = I18NLogicUtils.tradueix(locale, "signant");
      final String resumLabel = I18NLogicUtils.tradueix(locale, "resumdefirmes");
      final String titolLabel = I18NLogicUtils.tradueix(locale, "titol");
      final String descLabel = I18NLogicUtils.tradueix(locale, "descripcio");
      
      
      taulaDeFirmes = new StampTaulaDeFirmes(numFirmes, posicio, signantLabel, 
          resumLabel, descLabel, desc, titolLabel, titol, logo);
    }
  
    // Custodia
    es.caib.portafib.logic.utils.StampCustodiaInfo custodiaInfoStamp = null;
    if (custodiaInfo != null) {

      /** Missatge de custòdia a mostrar en el document. 
       * {0} = URL
       * {1} = custodiaID 
       * {2} = custodiaPluginClassID
       * {3} = data  amb hora
       * {4} = Special Value
       */
      
      String data = new I18NCommonDateTimeFormat(locale).format(new Date());

      String custodyID = custodiaInfo.getCustodiaPluginID();
      Object[] arguments = new Object[] {
          custodiaInfo.getUrlFitxerCustodiat(), custodyID,
          custodiaInfo.getCustodiaPluginClassID(), data,
          plugin.getSpecialValue(custodyID)
          };
      
      String missatge = MessageFormat.format(custodiaInfo.getMissatge(), arguments);
     
      
      String barcodeText = MessageFormat.format(custodiaInfo.getCodiBarresText(), arguments);
      

      String javaName = custodiaInfo.getCodiBarresID();
      
      IBarcodePlugin barcode = (IBarcodePlugin)PluginsManager.instancePluginByClassName(javaName);
      if (barcode == null) {
        throw new I18NException("plugin.donotinstantiate", javaName);
      }

      custodiaInfoStamp = new StampCustodiaInfo((int)custodiaInfo.getMissatgePosicioPaginaID(),
          missatge, barcode, barcodeText, custodiaInfo.getPagines());

    }

    // Crear nou fitxer amb taula de firmes i adjunts
    File srcPDF = FileSystemManager.getFile(peticioDeFirma.getFitxerAFirmarID());
    File dstPDF = FileSystemManager.getTmpFile(System.nanoTime());
    try {

      Long maxSize = PdfUtils.selectMin(maxSizeEntitat,
          Configuracio.getMaxFitxerAdaptatSizeInBytes());

      PdfUtils.add_TableSign_Attachments_CustodyInfo(srcPDF, dstPDF, attachments, 
          maxSize, taulaDeFirmes, custodiaInfoStamp);
      /*
      TableSignAndAttachments(srcPDF, dstPDF, numFirmes, posicio, signantLabel,
          resumLabel, descLabel, desc, titolLabel, titol, logo, attachments, attachmentsNames,
          maxSize);
          */

      FitxerJPA f = new FitxerJPA();
      f.setDescripcio("");
      f.setMime(Constants.PDF_MIME_TYPE);
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
      if (peticioDeFirma.getUsuariEntitat() != null) {
        // usuari-entitat
        UsuariEntitat ue;
        ue = peticioDeFirma.getUsuariEntitat();
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
   * Check de: (1) Verificar que els usuaris estan actius (2) Assignam permis de
   * ROLE_DEST als firmants de la peticio que no el tenguin.
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
    Where where = Where.AND(RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_DEST),
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
      roleUsuariEntitatEjb.create(Constants.ROLE_DEST, sensePermis);
    }

  }

  /**
   * Clau = peticioDeFirmaID. Valor = Hora en que es va fer la peticio" + "_" +
   * usuariEntitat que ha bloquejat
   */
  public static final Map<Long, Token> locks = new ConcurrentHashMap<Long, Token>();

  /**
   * 
   * @author anadal
   * 
   */
  public static class Token {
    
    /**
     * Com a molt pot estar bloquejat 3 minuts 
     * TODO Cercar temps apropiat !!!!
     */
    public static final long MAX_TIME_LOCKED_IN_NANO = 3L * 60L * 1000L * 1000000L;

    private long timeInNano;

    private final String usuariEntitatID;

    /**
     * @param time
     * @param usuariEntitatID
     */
    public Token(String usuariEntitatID) {
      super();
      this.usuariEntitatID = usuariEntitatID;
      updateTime();
    }

    public void updateTime() {
      this.timeInNano = System.nanoTime();
    }

    /*
     * public static Token getFromTokenString(String token) { int index =
     * token.indexOf('_'); long time = new Long(token.substring(0, index));
     * String usuariEntitatID = token.substring(index + 1); return new
     * Token(time, usuariEntitatID); }
     */

    public String getTokenString() {
      return timeInNano + "_" + usuariEntitatID;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Token) {
        Token tok = (Token) obj;
        if (tok.timeInNano == this.timeInNano && this.usuariEntitatID.equals(tok.usuariEntitatID)) {
          return true;
        }
      }
      return false;
    }

    
    public boolean isInvalid() {
      long now = System.nanoTime();
      
      if ((this.timeInNano + MAX_TIME_LOCKED_IN_NANO) < now) {
        return true;
      } else {
        return false;
      }
      
    }

    public String getUsuariEntitatID() {
      return usuariEntitatID;
    }

    public long getTimeInNano() {
      return timeInNano;
    }

  }


  

  private void cleanLocks() {
    // new hashSet per evitar ConcurrentModificationException
    Set<Long> ids = new HashSet<Long>(locks.keySet());
    
    for (Long id : ids) {
      Token token = locks.get(id);
      if (token != null && token.isInvalid()) {
       /*
          final long now = System.nanoTime();
          log.info("==================== ");
          log.info("Netejant peticio amb id = " + id);
          log.info("    time + max < now => " + token.getTimeInNano() + " + " + Token.MAX_TIME_LOCKED_IN_NANO + " < " + now);
          log.info("    time + max < now => " + (token.getTimeInNano() + Token.MAX_TIME_LOCKED_IN_NANO) + " < " + now);
          log.info("    time + max < now => " + ((token.getTimeInNano() + Token.MAX_TIME_LOCKED_IN_NANO) < now) );
          log.info("==================== ");
        */
        //  S'ha de borrar
        locks.remove(id);
      }
    }
  }

  /**
   * 
   * @param peticioDeFirmaID
   * @param token
   * @return null si esta bloquejat per un altra usuari, sino retorna un token (nou o actualitzat)
   */
  @Override
  public String lockPeticioDeFirma(long peticioDeFirmaID, String usuariEntitatID) {

    // Poden passar tres coses:
    // CAS 1: No estigui bloquejada
    // CAS 2: estigui bloquejat per un usuari IGUAL a "usuariEntitatID"
    // CAS 3: estigui bloquejat per un usuari DIFERENT a "usuariEntitatID"

    cleanLocks();

    Token tokenStored = locks.get(peticioDeFirmaID);

    if (tokenStored == null) {
      // CAS 1: Crear un nou TOKEN
      Token token = new Token(usuariEntitatID);
      locks.put(peticioDeFirmaID, token);
      return token.getTokenString();
    } else {
      // Existeix bloqueig
      if (tokenStored.usuariEntitatID.equals(usuariEntitatID)) {
        // CAS 2: Si es el mateix usuari llavors no existeix bloqueig
        // Actualitzam la data i retornam el token
        tokenStored.updateTime();
        return tokenStored.getTokenString();
      } else {
        // CAS 3: Esta bloquejat per un altre usuari.
        return null;
      }
    }

    /*
     * if (isLockedPeticioDeFirma(peticioDeFirmaID,usuariEntitatID)) { return
     * null; } else { Token tokenStored = locks.get(peticioDeFirmaID); if
     * (tokenStored == null) { // Crear un nou TOKEN Token token = new
     * Token(System.currentTimeMillis(),usuariEntitatID);
     * locks.put(peticioDeFirmaID, token ); return token.getToken(); } else { //
     * Reutilitzar el token ja existent de l'usuari
     * 
     * return tokenStored.getToken(); } }
     */
  }

  //@Override
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
   * @return null si el token no existeix (no hi ha bloqueig o s'ha tardat massa temps en firmar). 
   *         true si tot està correcte i false si esta bloquejat per un altre usuari  
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
          log.debug("S'ha cridat a checkPeticioDeFirmaByToken(" + peticioDeFirmaID + "," + token
            + ") però els tokens són diferents (" + tokenStored + ")", new Exception());
        }
        return false;
      }
    }
  }
  
  /**
   * 
   * @param peticioDeFirmaID
   * @param usuariEntitatID
   * @return true si la peticio no esta bloquejada o esta bloquejada per usuariEntitatID.
   *         false en altres cas  
   */
  public boolean checkPeticioDeFirmaByUsuariEntitat(long peticioDeFirmaID, String usuariEntitatId) {
    cleanLocks();
    Token tokenStored = locks.get(peticioDeFirmaID);
    if (tokenStored == null) {
      if (log.isDebugEnabled()) {
        log.debug("S'ha cridat a checkPeticioDeFirmaByUsuariEntitat(" + peticioDeFirmaID 
          + "," + usuariEntitatId + ") però no existeix", new Exception());
      }
      return true;
    } else {
      if (tokenStored.getUsuariEntitatID().equals(usuariEntitatId)) {
        return true;
      } else {
        if (log.isDebugEnabled()) {
          log.debug("S'ha cridat a checkPeticioDeFirmaByUsuariEntitat(" + peticioDeFirmaID
            + "," +  usuariEntitatId + ") però els usuaris són diferents(" 
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

      peticioDeFirma.setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT);
      update(peticioDeFirma);

      // Events
      FirmaEventList events = new FirmaEventList();
      events.peticio_pausada(peticioDeFirma);

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
   *          val null quan s'inicia la peticio de firma. En altres casos es la
   *          firma que provoca la cerca de la següent.
   * @return true si la peticio ha finalitzat, false si la peticio continua
   * @throws Exception
   */
  protected boolean startNextSign(PeticioDeFirmaJPA peticioDeFirma, FluxDeFirmesJPA flux,
      EstatDeFirmaJPA estatDeFirma, FirmaEventList events) throws I18NException {
    
    System.out.println(" ---------------------------------------- ");
    System.out.println(" ---------------------------------------- ");
    System.out.println(" --------   startNextSign  -------------- ");
    System.out.println(" ---------------------------------------- ");
    System.out.println(" ---------------------------------------- ");
    

    if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() != Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
      log.error("S'esta cercant un nou bloc de firmes disponible per la petició "
          + peticioDeFirma.getPeticioDeFirmaID() + " però aquesta no esta en progres. ");
      return false;
    }

    Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();

    TreeSet<BlocDeFirmesJPA> blocsOrdenats;
    blocsOrdenats = new TreeSet<BlocDeFirmesJPA>(new BlocDeFirmesComparator());
    blocsOrdenats.addAll(blocs);

    log.debug(" ========== startNextSign");
    Timestamp now = new Timestamp(System.currentTimeMillis());

    for (BlocDeFirmesJPA blocDeFirmesJPA : blocsOrdenats) {

      
      log.debug("----- Bloc " + blocDeFirmesJPA.getBlocDeFirmesID());

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
          
          log.debug(" Bloc Verge te " + firmes.size() + " firmes ");
          // Es un bloc verge, per la qual cosa cream els estats de firma
          // associades a les firmes
          for (FirmaJPA firmaJPA : firmes) {

            log.debug("  +++ Firma " + firmaJPA.getFirmaID());

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
                  String msg = "El carrec "+ dest.getCarrec() + "(ID=" + dest.getUsuariEntitatID()
                    + ", " + dest.getEntitatID() + ") no esta actiu. "
                    + "Esperi a que s'activi o se li assigni una persona."; 
                  I18NException e = new I18NException("error.unknown",msg);
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
                  String msg = "No s'ha trobat un usuari persona"
                    + " associat al carrec "+ dest.getCarrec() + "(ID=" + dest.getUsuariEntitatID()
                    + ", " + dest.getEntitatID() + ")"; 
                  I18NException e = new I18NException("error.unknown",msg);
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
                .setTipusEstatDeFirmaInicialID(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR);
            estatDeFirmaDest.setUsuariEntitatID(destinatariReal);
            estatDeFirmaDest = (EstatDeFirmaJPA) estatDeFirmaLogicaEjb
                .createFull(estatDeFirmaDest);
            events.requerit_per_firmar(peticioDeFirma, estatDeFirmaDest);

            log.debug("   == Nou estat per Destinatari " + firmaJPA.getDestinatariID());

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
            
            final Where commonWhere =
                Where.AND(
                  ColaboracioDelegacioFields.ACTIVA.equal(true),
                  Where.OR(
                    ColaboracioDelegacioFields.DATAINICI.isNull(),
                    ColaboracioDelegacioFields.DATAINICI.lessThan(now)),
                  Where.OR(
                     ColaboracioDelegacioFields.DATAFI.isNull(),
                     ColaboracioDelegacioFields.DATAFI.greaterThan(now)),
                  Where.OR(
                     ColaboracioDelegacioFields.COLABORACIODELEGACIOID.in(subquery),
                     new LongConstantField(0L).in(subquery2))
                );

            final Where w1 = Where.AND(ColaboracioDelegacioFields.DESTINATARIID
                .equal(destinatariReal), commonWhere);
            
            // (B) Cercar col·laboradors de càrrec
            Where w2 = null;
            if (carrec != null) {
              w2 = Where.AND(ColaboracioDelegacioFields.DESTINATARIID
                .equal(carrec), commonWhere);
            }

            Where w = Where.OR(w1, w2);

            if (log.isDebugEnabled()) {
              log.info("  Seleccio de COLABORADORS/DELEGATS: " + w.toSQL());
            }

            List<ColaboracioDelegacio> llistaColaDele = colaboracioDelegacioEjb.select(w);

            if (log.isDebugEnabled()) {
              log.info("   == # COLABORADORS/DELEGATS " + llistaColaDele.size());

              for(ColaboracioDelegacio cd : llistaColaDele) {
                log.info("       + " + (cd.isEsDelegat()?"DELE": "COLA")
                   + "[" + cd.getColaboracioDelegacioID() + "] : "
                   + cd.getColaboradorDelegatID() + "  =>  " + cd.getDestinatariID());
              }
            }

            for (ColaboracioDelegacio colaboracioDelegacio : llistaColaDele) {
              EstatDeFirmaJPA estatDeFirmaColaDele = new EstatDeFirmaJPA();
              estatDeFirmaColaDele.setDataInici(new Timestamp(System.currentTimeMillis()));
              estatDeFirmaColaDele.setDescripcio("");
              estatDeFirmaColaDele.setFirmaID(firmaJPA.getFirmaID());
              long tipusEstat;
              if (colaboracioDelegacio.isEsDelegat()) {
                tipusEstat = Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR;
              } else {
                tipusEstat = Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR;
              }
              estatDeFirmaColaDele.setTipusEstatDeFirmaInicialID(tipusEstat);
              estatDeFirmaColaDele.setUsuariEntitatID(colaboracioDelegacio
                  .getColaboradorDelegatID());
              estatDeFirmaColaDele.setColaboracioDelegacioID(colaboracioDelegacio
                  .getColaboracioDelegacioID());
              estatDeFirmaColaDele = estatDeFirmaLogicaEjb.createFull(estatDeFirmaColaDele);
              if (tipusEstat == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
                events.requerit_per_firmar(peticioDeFirma, estatDeFirmaColaDele);
              } else {
                events.requerit_per_validar(peticioDeFirma, estatDeFirmaColaDele);
              }
              if (log.isDebugEnabled()) {
                log.info("   == Nou estat per COLA/DELE "
                    + colaboracioDelegacio.getColaboradorDelegatID());
              }
            }

            // TODO Falten avisos
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
    peticioDeFirma.setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT);
    peticioDeFirma.setDataFinal(now);
    this.update(peticioDeFirma);

    // Avisam de la firma final
    events.peticio_firmada(peticioDeFirma);
    
    return true;
  }

  protected void descartarEstatsDeFirma(Long[] firmaIDs, final String msg,
      PeticioDeFirmaJPA peticioDeFirma, FirmaEventList events, Timestamp now) throws I18NException {
    List<EstatDeFirma> estatsDeFirma = estatDeFirmaLogicaEjb.select(Where.AND(
        EstatDeFirmaFields.DATAFI.isNull(), EstatDeFirmaFields.FIRMAID.in(firmaIDs)));

    for (EstatDeFirma estat : estatsDeFirma) {
      estat.setDataFi(now);
      estat.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      estat.setDescripcio(msg);
      estat = estatDeFirmaLogicaEjb.update(estat);
      if (estat.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
        events.descartat_per_firmar(peticioDeFirma, estat);
      } else {
        events.descartat_per_validar(peticioDeFirma, estat);
      }
    }

    // Actualitzam Firmes a estat Descartat
    List<Firma> actualitzarFirmes = firmaLogicaEjb.select(FirmaFields.FIRMAID.in(firmaIDs));
    for (Firma firmaADescartar : actualitzarFirmes) {
      firmaADescartar.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
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
  public Set<Long> deleteFullUsingUsuariEntitat(Long peticioDeFirmaID, String usuariEntitatID) throws I18NException {
    boolean isUsuariEntitat = true;
    return deleteFull(peticioDeFirmaID,usuariEntitatID, isUsuariEntitat);
  }
  
  @Override
  public Set<Long> deleteFullUsingUsuariAplicacio(Long peticioDeFirmaID, String usuariAplicacioID) throws I18NException {
    boolean isUsuariEntitat = false;
    return deleteFull(peticioDeFirmaID,usuariAplicacioID, isUsuariEntitat);
  }

  
  
  
  /**
   * 
   * @param peticioDeFirmaID
   * @return List of filesID deleted
   */

  private Set<Long> deleteFull(Long peticioDeFirmaID, String username, boolean isUsuariEntitat) throws I18NException {
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
          if (!username.equals(pf.getUsuariEntitatID())) {
            // L'usuari {0} no té permisos per borrar la petició de firma titulada {1}
            throw new I18NException("peticiodefirma.error.nopermisdeborrar", username, pf.getTitol());
          }
        } else {
          if (!username.equals(pf.getUsuariAplicacioID())) {
            throw new I18NException("peticiodefirma.error.nopermisdeborrar", username, pf.getTitol());
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
      files.add(pf.getFitxerAFirmarID());
      fitxerLogicaEjb.delete(pf.getFitxerAFirmarID());

      // Borrar fitxer amb taula de firmes
      if (pf.getFitxerAdaptatID() != null
          && (pf.getFitxerAFirmarID() != pf.getFitxerAdaptatID())) {
        files.add(pf.getFitxerAdaptatID());
        fitxerLogicaEjb.delete(pf.getFitxerAdaptatID());
      }
      
      // Borram la reserva de Custòdia en cas de no haver finalitzat
      if (pf.getCustodiaInfoID() != null 
          && pf.getTipusEstatPeticioDeFirmaID() != Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
        
        // Borram la reserva de custòdia
        CustodiaInfoJPA custInfo = custodiaInfoEjb.findByPrimaryKey(pf.getCustodiaInfoID());
        
        String pluginClass = custInfo.getCustodiaPluginClassID();
        
        IDocumentCustodyPlugin plugin = PortaFIBPluginsManager.getDocumentCustodyPluginByClassName(pluginClass);
        if (plugin == null) {
          throw new I18NException("plugin.donotinstantiate", pluginClass);
        }
        try {
          plugin.deleteCustody(custInfo.getCustodiaPluginID());
        } catch (NotSupportedCustodyException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (CustodyException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        
        // Borram informacio de custòdia
        custodiaInfoEjb.delete(pf.getCustodiaInfoID() );
      }

      // Retornam els fitxers, per que fora es borrin fisicament
      return files;

    } catch (I18NException e) {
      context.setRollbackOnly();
      throw e;
    }

  }

  /**
   * @return El darrer fitxer firmat si la peticio esta n marxa i algu ha firmat,
   *         els fitxer adaptat si la peticio esta en marxa i ningú ha firmat
   *         o el fitxer original si la peticio no s'ha iniciat.
   */
  @Override
  public FitxerJPA getLastSignedFileOfPeticioDeFirma(Long peticioDeFirmaID) throws I18NException {
    if (peticioDeFirmaID == null) {
      return null;
    }

    PeticioDeFirmaJPA peticio = findByPrimaryKey(peticioDeFirmaID);
    if (peticio == null) {
      return null;
    }

    long estat = peticio.getTipusEstatPeticioDeFirmaID();

    FitxerJPA f;
    if (estat == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
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
     * // Cercam la firma amb numfirmadocument not null i major. // Si ordenam
     * aquestes firmes de forma descendent per numfirmadocument // el primer
     * element d'aquesta llista serà la darrera firma
     * 
     * Where w = Where.AND(FirmaFields.NUMFIRMADOCUMENT.isNotNull(),
     * FirmaFields.BLOCDEFIRMAID.in(subQueryBlocs));
     * 
     * 
     * log.info("XXXX getLastSignOfPeticioDeFirma() : SQL = " + w.toSQL());
     * 
     * final Integer firstResult = null; final Integer maxResults = 1;
     * List<Firma> firmes = firmaEjb.select(w, firstResult, maxResults, new
     * OrderBy( FirmaFields.NUMFIRMADOCUMENT, OrderType.DESC));
     * 
     * if (firmes == null || firmes.size() == 0) { return null; } else { return
     * (FirmaJPA) firmes.get(0); }
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
  protected Map<Integer, Long> getFitxersFirmatsOfPeticioDeFirma(Long peticioDeFirmaID)
      throws I18NException {

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

      Map<Integer, Long> fitxersByNumFirma = new HashMap<Integer, Long>();
      for (KeyValue<Long> keyValue : firmes) {

        fitxersByNumFirma.put(Integer.parseInt(keyValue.getValue()), keyValue.getKey());
      }

      return fitxersByNumFirma;
    }

  }

  @Override
  public void nouFitxerFirmat(File file, Long estatDeFirmaID, Long peticioDeFirmaID,
      String token, int numFirma) throws I18NException {

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
        // TODO traduir
        String msg = "Ha tardat massa temps en firmar la petició " + peticioDeFirmaID
            + " i ha expirat el temps. Tornau-ho a intentar (" + token + ")";
        throw new Exception(msg, new Exception());
      } else {
        if (check == false) {
          // TODO traduir
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

      // (a) Verificar que el certificat emprat en la firma es correcte (vàlid)
      int tipusFirma = peticioDeFirma.getTipusFirmaID();
      String mime;
      String extension;
      String nifFirmant;
      switch (tipusFirma) {
      case Constants.TIPUSFIRMA_PADES:
        extension = "pdf";
        mime = Constants.PDF_MIME_TYPE;

        Map<Integer, Long> fitxersByNumFirma = null;
        if (numFirma != 1) {
          fitxersByNumFirma = getFitxersFirmatsOfPeticioDeFirma(peticioDeFirma
              .getPeticioDeFirmaID());
        }

        Long fitxerOriginalID = peticioDeFirma.getFitxerAdaptatID();

        InformacioCertificat info;
        info = PdfUtils.checkCertificatePADES(fitxerOriginalID, fitxersByNumFirma, file,
            numFirma);

        // Obtenir informació del certificat
        firma.setNumeroSerieCertificat(info.getNumeroSerie());
        log.info("NumeroSerieCertificat = " + info.getNumeroSerie());

        if (info.getEmissorID() != null) {
          log.info("Emissor LEN = " + info.getEmissorID().length());
        }
        firma.setEmissorCertificat(info.getEmissorID());

        if (info.getSubject() != null) {
          log.info("Subject LEN = " + info.getSubject().length());
        }
        firma.setNomCertificat(info.getSubject());

        nifFirmant = info.getNifResponsable();
        if (info.getNifResponsable() != null) {
          log.info("NIF LEN = " + info.getNifResponsable().length());
        }

        break;

      default:
        throw new Exception("No esta implementat la gestió de fitxers"
            + " amb tipus de firma " + tipusFirma);
      }

      // (b) Verificar que el NIF del certificat correspon amb qui tenia que
      // firmar
      if (Configuracio.isCheckNifCertificate()) {
        StringField NIF = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();
        Where where = UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirma
            .getUsuariEntitatID());
        String expectedNif = usuariEntitatEjb.executeQueryOne(NIF, where);
        LogicUtils.checkExpectedNif(nifFirmant, expectedNif);
      }

      // Guardar EN BBDD

      // 1.- Crear fitxer en BBDD
      FitxerJPA fitxer = new FitxerJPA();
      fitxer.setDescripcio("");
      fitxer.setMime(mime);
      fitxer.setNom("PeticioFirma_" + peticioDeFirmaID + "." + extension);
      fitxer.setTamany(file.length());

      fitxer = fitxerLogicaEjb.createFull(fitxer);

      // 2.- Tancar l'estat de firma
      final Timestamp now = new Timestamp(System.currentTimeMillis());

      estatDeFirma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT);
      estatDeFirma.setDataFi(now);
      estatDeFirma = (EstatDeFirmaJPA) estatDeFirmaLogicaEjb.update(estatDeFirma);
      events.firma_parcial(peticioDeFirma, estatDeFirma);

      // 3.- Associar Fitxer a la Firma i guardar
      firma.setNumFirmaDocument(numFirma);
      firma.setFitxerFirmatID(fitxer.getFitxerID());
      firma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_FIRMAT);
      firmaLogicaEjb.update(firma);

      // 4.- Descartar tots els EstatDeFirma associats a la firma
      if (log.isDebugEnabled()) {
        log.debug(" FIRMAID = " + firmaID);
      }

      
      
      Where w = Where.AND(EstatDeFirmaFields.FIRMAID.equal(firmaID),
          EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull());
      List<EstatDeFirma> estatsDeFirma = estatDeFirmaLogicaEjb.select(w);
      if (log.isDebugEnabled()) {
        log.debug("Count Estats de Firma = " + estatsDeFirma.size() );
      }
      for (EstatDeFirma estat : estatsDeFirma) {
        estat.setDataFi(now);
        estat.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
        estat.setDescripcio("Alguna altra persona ja ha firmat la peticio");
        estat = estatDeFirmaLogicaEjb.update(estat);
        if (estat.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
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
      firmaEventManagerEjb.processList(events);

      // IMPORTATNT: Això ha de ser lo darrer per si hi hagues algun error en
      // les passes anteriors
      // 8.- Guardar Fitxer en Sistema de Fitxers
      FileSystemManager.sobreescriureFitxer(file, fitxer.getFitxerID());
      fileID = fitxer.getFitxerID();
      
      // 9.- Custodia
      if (peticioFinalitzada && peticioDeFirma.getCustodiaInfoID() != null) {

        CustodiaInfo custInfo = custodiaInfoEjb.findByPrimaryKey(peticioDeFirma.getCustodiaInfoID());
        if (custInfo != null && custInfo.isCustodiar() && file != null ) {

          IDocumentCustodyPlugin plugin = PortaFIBPluginsManager.getDocumentCustodyPluginInstance();
          
          custInfo.setDataCustodia(new Timestamp(new Date().getTime()));
          custodiaInfoEjb.update(custInfo);

          

          switch (tipusFirma) {
            case Constants.TIPUSFIRMA_PADES:
            {
              DocumentCustody dc = new DocumentCustody();
              dc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              dc.setData(FileSystemManager.getFileContent(fileID));
              dc.setDocumentType(DocumentCustody.PDF_WITH_SIGNATURE);
              plugin.saveDocument(custInfo.getCustodiaPluginID(), custInfo.getCustodiaPluginParameters(), dc);
            }
              break;
            case Constants.TIPUSFIRMA_XADES:
            {
              SignatureCustody sc = new SignatureCustody();
              sc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              sc.setData(FileSystemManager.getFileContent(fileID));
              sc.setSignatureType(SignatureCustody.XADES_SIGNATURE);
              plugin.saveSignature(custInfo.getCustodiaPluginID(), custInfo.getCustodiaPluginParameters(), sc);
            }
              break;
            case Constants.TIPUSFIRMA_CADES:
            {
              SignatureCustody sc = new SignatureCustody();
              sc.setName(peticioDeFirma.getFitxerAFirmar().getNom());
              sc.setData(FileSystemManager.getFileContent(fileID));
              sc.setSignatureType(SignatureCustody.CADES_SIGNATURE);
              plugin.saveSignature(custInfo.getCustodiaPluginID(), custInfo.getCustodiaPluginParameters(), sc);
            }
              break;
            default:
              throw new Exception("Tipus de Firma no suportada !!!!");
          }
          
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


      throw new I18NException(error, "error.unknown",  new I18NArgumentString(error.getMessage()));
    } finally {
      try {
        if (!unlockPeticioDeFirma(peticioDeFirmaID, token)) {
          log.error("No s'ha pogut desbloquejar la petició " + peticioDeFirmaID
              + " amb token " + token);
          for (Long peticioID : this.locks.keySet()) {
            Token tok = this.locks.get(peticioID);
            log.error("LOCKS: " + peticioID + " --> T: " + tok.getTimeInNano() + " | U: "
                + tok.usuariEntitatID);
          }
          log.error("Forcing to unlock peticioDeFirmaID = " + peticioDeFirmaID);
          this.locks.remove(peticioDeFirmaID);
        }
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  @Override
  public void marcarRevisant(EstatDeFirma estatDeFirma, Firma firma,
      PeticioDeFirma peticioDeFirma) throws I18NException {
    
    // Check si hi ha colaboradors-revisors
    Where w1 = Where.AND(
      EstatDeFirmaFields.FIRMAID.equal(firma.getFirmaID()),
      EstatDeFirmaFields.DATAFI.isNull(), // No tancat
      EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR)
    );
    Where w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull();
    ColaboracioDelegacioQueryPath cdqp = new EstatDeFirmaQueryPath().COLABORACIODELEGACIO();
    Where w3 = Where.AND(
        cdqp.REVISOR().equal(true),
        cdqp.ACTIVA().equal(true),
        cdqp.ESDELEGAT().equal(false) // Es col·laborador
      );
    
    if (estatDeFirmaLogicaEjb.count(Where.AND(w1, w2, w3)) != 0) {
      // Existeixen col·laboradors-revisors per la qual cosa un col·laborador
      // no es pot apropir d'uan col3laboració
      throw new I18NException("estatdefirma.marcarrevisant.colaboradorrevisor");
    }
    

    // Marcam revisant-per-validar
    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma
        .setTipusEstatDeFirmaInicialID(Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR);
    estatDeFirma.setDescripcio("El document ha sigut marcat per revisar");
    estatDeFirmaLogicaEjb.update(estatDeFirma);
    
    List<EstatDeFirma> estatsDeFirma;
    estatsDeFirma = estatDeFirmaLogicaEjb.select(w1);

    for (EstatDeFirma estat : estatsDeFirma) {
      estat.setDataFi(now);
      estat.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      estat.setDescripcio("De la validació d'aquest document s´encarregarà"
          + " l´usuari-entitat " + estatDeFirma.getUsuariEntitatID());
      estatDeFirmaLogicaEjb.update(estat);
    }

  }

  @Override
  public void validar(EstatDeFirma estatDeFirma, Firma firma, PeticioDeFirma peticioDeFirma)
      throws I18NException {

    Timestamp now = new Timestamp(System.currentTimeMillis());
    estatDeFirma.setDataFi(now);
    estatDeFirma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT);
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
    estatDeFirma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
    estatDeFirma.setDescripcio(motiuInvalidacio);
    estatDeFirmaLogicaEjb.update(estatDeFirma);
  }

  @Override
  public void rebutjar(PeticioDeFirmaJPA peticioDeFirma, String motiuDeRebuig)
    throws I18NException {

    EstatDeFirmaQueryPath efqp = new EstatDeFirmaQueryPath();

    LongField PETICIODEFIRMAID = efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();

    List<EstatDeFirma> estatsPendentsDeFirma = estatDeFirmaLogicaEjb.select(Where.AND(
        EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
            .equal(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
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
          .setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
      peticioDeFirma.setDataFinal(now);
      this.update(peticioDeFirma);

      events.peticio_rebutjada(peticioDeFirma, null);

      // Avisar del rebuig
      firmaEventManagerEjb.processList(events);

    } else {

      EstatDeFirma estatDeFirma = estatsPendentsDeFirma.get(0);

      Firma firma = firmaLogicaEjb.findByPrimaryKey(estatDeFirma.getFirmaID());

      rebutjar(estatDeFirma, firma, peticioDeFirma, motiuDeRebuig);
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
      estatDeFirma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT);
      estatDeFirma.setDescripcio(motiuDeRebuig);
      estatDeFirma = estatDeFirmaLogicaEjb.update(estatDeFirma);

      firma.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_REBUTJAT);

      // Descartar tots els estat de firma actius associats al flux
      Long fluxDeFirmesID = peticioDeFirma.getFluxDeFirmesID();
      Set<Long> firmes = new HashSet<Long>();
      List<EstatDeFirma> estatsDeFirma;
      estatsDeFirma = estatDeFirmaLogicaEjb.getAllEstatDeFirmaActiuOfFlux(fluxDeFirmesID);
      for (EstatDeFirma estat : estatsDeFirma) {
        // Actualitzam estat
        estat.setDataFi(now);
        estat.setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
        // TODO MISSATGE
        estat.setDescripcio("La petició ha sigut rebutjada per un altre usuari");
        estatDeFirmaLogicaEjb.update(estat);

        // Events
        if (estat.getTipusEstatDeFirmaInicialID() == Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR) {
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
            .setTipusEstatDeFirmaFinalID(Constants.TIPUSESTATDEFIRMAFINAL_DESCARTAT);
      }

      // Posar data de finalitzacio al Bloc
      long blocID = firma.getBlocDeFirmaID();
      BlocDeFirmes bloc = blocDeFirmesEjb.findByPrimaryKey(blocID);
      bloc.setDataFinalitzacio(now);
      blocDeFirmesEjb.update(bloc);

      // marcar la peticio de firma com rebutjat
      peticioDeFirma.setMotiuDeRebuig(motiuDeRebuig);
      peticioDeFirma
          .setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT);
      peticioDeFirma.setDataFinal(now);
      this.update(peticioDeFirma);
      events.peticio_rebutjada(peticioDeFirma, estatDeFirma);

      // Avisar del rebuig
      firmaEventManagerEjb.processList(events);

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

    // Fitxers a borrar
    Set<Fitxer> fitxers = new HashSet<Fitxer>();
    IDocumentCustodyPlugin custodiaPlugin = null;
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
          firma.setTipusEstatDeFirmaFinal(null);

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
          fitxers.add(((AnnexFirmatJPA)annexFirmat).getFitxer());
        }
        annexFirmatEjb.delete(AnnexFirmatFields.FIRMAID.in(firmesIDs));

        // Actualitzam bloc
        blocDeFirmesEjb.update(blocDeFirmes);
      }
      

      // Custodia (NOTA: CustodiaInfo no està carregada)
      String deleteCustodyID = null;
      
      try {
        custodiaPlugin = PortaFIBPluginsManager.getDocumentCustodyPluginInstance();
      } catch(Throwable e) {
      }
      if (peticio.getCustodiaInfoID() != null) {
        if (custodiaPlugin == null) {
          // Ja no hi ha plugin de custodia
          
          // Borrarem la reserva de ID en tots els casos excepte si la peticio ha sigut firmada
          if (peticio.getTipusEstatPeticioDeFirmaID() != Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
            // Obtenim el sistema de custodia antic
            CustodiaInfoJPA custOrig = custodiaInfoEjb.findByPrimaryKey(peticio.getCustodiaInfoID());
            custodiaPlugin = PortaFIBPluginsManager.getDocumentCustodyPluginByClassName(custOrig.getCustodiaPluginClassID());
            deleteCustodyID = custOrig.getCustodiaPluginID();
          }
          // Eliminam info de custodia ja que no hi ha definit plugin de custodia
          peticio.setCustodiaInfoID(null);
          peticio.setCustodiaInfo(null);
          
        } else {
          
          if (peticio.getTipusEstatPeticioDeFirmaID() == Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
            // Si la peticio esta en estat FIRMAT llavors s'ha de crear 
            // un nou CustodiaInfo clonat de l'actual
            cloneCustodiaInfo(peticio, custodiaPlugin);
            
          } else {
            // En qualsevol altra cas s'ha de borrar les dades variables de custodia
            // i s'ha de borrar la reserva 
            CustodiaInfoJPA custOrig = custodiaInfoEjb.findByPrimaryKey(peticio.getCustodiaInfoID());
            // TODO Check custOrig != null
            //CustodiaInfoJPA clonedCust = new CustodiaInfoJPA(custOrig);
            
            // Obtenim sistema de custodia antic
            IDocumentCustodyPlugin oldCustodiaPluginClassID = PortaFIBPluginsManager.getDocumentCustodyPluginByClassName(custOrig.getCustodiaPluginClassID());
            if (oldCustodiaPluginClassID == null) {
              throw new I18NException("plugin.donotinstantiate", custOrig.getCustodiaPluginClassID());
            }
            IDocumentCustodyPlugin newCustodiaPluginClassID = custodiaPlugin;
            
            // Borrar reserva
            custodiaPlugin = oldCustodiaPluginClassID;
            deleteCustodyID = custOrig.getCustodiaPluginID();
    
            custOrig.setCustodiaPluginID(null);
            custOrig.setCustodiaPluginClassID(newCustodiaPluginClassID.getClass().getName());
            custOrig.setUrlFitxerCustodiat(null);
            custOrig.setNomPlantilla(null);
            
            custOrig = (CustodiaInfoJPA)custodiaInfoEjb.update(custOrig);
     
            peticio.setCustodiaInfo(custOrig);
            
            custodiaPlugin = oldCustodiaPluginClassID;
          }
        }
      }

      // Canviam estat
      peticio.setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT);

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
      if (deleteCustodyID != null) {
        if (custodiaPlugin == null) {
          log.error("Intentant desreservar identificador de custodia " + deleteCustodyID + " però CustodiaPlugin val null !!!!");
        } else {
          try {
            custodiaPlugin.deleteCustody(deleteCustodyID);
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

  /**
   * 
   */
  @Override
  public PeticioDeFirmaJPA clonePeticioDeFirma(long peticioDeFirmaID,
      String newMessageFormaPatternForName) throws I18NException {

    PeticioDeFirmaJPA peticioOrig = this.findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

    if (peticioOrig == null) {
      return null;
    }

    PeticioDeFirmaJPA peticio = PeticioDeFirmaJPA.toJPA(peticioOrig);
    String titol;
    if (newMessageFormaPatternForName == null
        || newMessageFormaPatternForName.trim().length() == 0) {
      titol = "Copy Of " + peticio.getTitol();
    } else {
      titol = MessageFormat.format(newMessageFormaPatternForName, peticio.getTitol());
    }
    peticio.setTitol(titol);

    // Borrarem els ID's i clonam els fitxers
    Set<Fitxer> fitxers = new HashSet<Fitxer>();
    try {

      peticio.setPeticioDeFirmaID(0);
      peticio.setDataFinal(null);
      Calendar cal = Calendar.getInstance();
      peticio.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));
      {
        FitxerJPA fitxerClonat = cloneFitxer(fitxers, peticioOrig.getFitxerAFirmar());
        peticio.setFitxerAFirmar(fitxerClonat);
        peticio.setFitxerAFirmarID(fitxerClonat.getFitxerID());
      }

      peticio.setFitxerAdaptat(null);
      peticio.setFitxerAdaptatID(null);

      peticio.setMotiuDeRebuig(null);

      peticio.setTipusEstatPeticioDeFirmaID(Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT);

      if (peticioOrig.getLogoSegellID() != null) {
        FitxerJPA fitxerClonat = cloneFitxer(fitxers, peticioOrig.getLogoSegell());
        peticio.setLogoSegellID(fitxerClonat.getFitxerID());
        peticio.setLogoSegell(fitxerClonat);
      }

      

      if (peticio.getNotificacioWSs() != null) {
        peticio.getNotificacioWSs().clear();
      }

      if (peticio.getBitacolas() != null) {
        peticio.getBitacolas().clear();
      }

      peticio.setAvisWeb(false);

      // FLUX
      peticio.setFluxDeFirmesID(0);
      FluxDeFirmesJPA fluxOrig = peticioOrig.getFluxDeFirmes();

      FluxDeFirmesJPA flux = FluxDeFirmesJPA.toJPA(fluxOrig);
      flux.setFluxDeFirmesID(0);
      flux.setNom(titol);

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
          firmaJPA.setTipusEstatDeFirmaFinal(null);

          blocDeFirmesJPA.getFirmas().add(firmaJPA);
        }

        flux.getBlocDeFirmess().add(blocDeFirmesJPA);
      }
      peticio.setFluxDeFirmes(flux);
      
      // Custodia (NOTA: CustodiaInfo no està carregada)
      IDocumentCustodyPlugin custodiaPluginClassID = null;
      try {
        custodiaPluginClassID = PortaFIBPluginsManager.getDocumentCustodyPluginInstance();
      } catch(Throwable e) {
      }
      if (peticio.getCustodiaInfoID() != null && custodiaPluginClassID != null) {
        
        cloneCustodiaInfo(peticio, custodiaPluginClassID);

      } else {
        peticio.setCustodiaInfoID(null);
        peticio.setCustodiaInfo(null);
      }

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
      
      return (PeticioDeFirmaJPA)update(peticio);

    } catch (Throwable e) {
      log.error(e.getMessage(), e);
      for (Fitxer fitxer : fitxers) {
        FileSystemManager.eliminarArxiu(fitxer.getFitxerID());
      }
      if (e instanceof I18NException) {
        throw (I18NException) e;
      } else {
        throw new I18NException(e, "error.unknown", new I18NArgumentString(e.getMessage()));
      }
    }
  }

  private void cloneCustodiaInfo(PeticioDeFirmaJPA peticio,
      IDocumentCustodyPlugin custodiaPluginClassID) throws I18NException {
    CustodiaInfoJPA custOrig = custodiaInfoEjb.findByPrimaryKey(peticio.getCustodiaInfoID());
    // TODO Check custOrig != null
    CustodiaInfoJPA clonedCust = new CustodiaInfoJPA(custOrig);
    
    clonedCust.setCustodiaInfoID(0);
    clonedCust.setCustodiaPluginID(null);
    clonedCust.setCustodiaPluginClassID(custodiaPluginClassID.getClass().getName());
    clonedCust.setUrlFitxerCustodiat(null);
    clonedCust.setNomPlantilla(null);
    
    clonedCust = (CustodiaInfoJPA)custodiaInfoEjb.create(clonedCust);
    
    peticio.setCustodiaInfoID(clonedCust.getCustodiaInfoID());  
    peticio.setCustodiaInfo(clonedCust);
  }

  private FitxerJPA cloneFitxer(Set<Fitxer> fitxers, FitxerJPA fitxerOrig) throws I18NException {

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
   * TODO Moure a GenApp i fer per altres tipus 
   * 
   * @author anadal
   * 
   */
  public static class LongConstantField extends Field<Long> {

    protected LongConstantField(Long value) {
      super(null, String.valueOf(value), String.valueOf(value), null);
    }

  }
  
  
  @EJB(mappedName = es.caib.portafib.ejb.PosicioPaginaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PosicioPaginaLocal posicioPaginaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;
  
  
  @Override
  public CustodiaInfo addCustodiaInfoToPeticioDeFirma(long peticioDeFirmaID) throws I18NException {

    // Check peticio de firma
    PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA)this.findByPrimaryKey(peticioDeFirmaID);
    if (peticio == null) {
      return null;
    }


    // TODO Check si usuari app o usuari entitat pot custodiar
    
    /*
    CustodiaInfoBeanValidator cibv = new CustodiaInfoBeanValidator(validatorCustodiaInfo,usuariEntitatEjb,
        entitatEjb, posicioPaginaEjb, codiBarresEjb, usuariAplicacioEjb);
    
    cibv.validate(target, isNou)

    cibv.throwValidationExceptionIfErrors(custodiaInfo, true)
    */

    String usuariEntitatID = peticio.getUsuariEntitatID();
    
    CustodiaInfoJPA custodiaInfo = CustodiaInfoJPA.toJPA(constructDefaultCustodiaInfo(peticio.getTitol(),
        usuariEntitatID, peticio.getUsuariAplicacioID(), peticio.getIdiomaID()));

    custodiaInfo = (CustodiaInfoJPA)custodiaInfoEjb.create(custodiaInfo);
    
    peticio.setCustodiaInfoID(custodiaInfo.getCustodiaInfoID());
    peticio.setCustodiaInfo(custodiaInfo);
    
    this.update(peticio);
    
    return custodiaInfo;
  }

  @Override
  public CustodiaInfoBean constructDefaultCustodiaInfo(String titol,
      String usuariEntitatID, String usuariAplicacioID, String idioma) {
    
    
    IDocumentCustodyPlugin custodiaPlugin = null;
    try {
      custodiaPlugin = PortaFIBPluginsManager.getDocumentCustodyPluginInstance();
    } catch (Exception e) {
      log.error("Error intentant obtenir plugin de custòdia:" + e.getMessage(), e);
    }
    if (custodiaPlugin == null) {
      return null;
    }
    
    
    String custodiaPluginClass = custodiaPlugin.getClass().getCanonicalName() ;
    
    CustodiaInfoBean custodiaInfo = new CustodiaInfoBean();

    custodiaInfo.setEditable(true);

    //custodiaInfo.setCodiBarresID();
    custodiaInfo.setPagines("*");
    custodiaInfo.setCodiBarresPosicioPaginaID(Constants.POSICIO_PAGINA_ESQUERRA);
    custodiaInfo.setCodiBarresText("{0}");
    custodiaInfo.setCodiBarresID(Constants.BARCODE_PDF417_PLUGIN);
    
    custodiaInfo.setMissatgePosicioPaginaID(Constants.POSICIO_PAGINA_ESQUERRA);
    custodiaInfo.setMissatge(
        I18NLogicUtils.tradueix(
            new Locale(idioma),"custodiainfo.missatgeperdefecte"));
    
    if (usuariEntitatID != null) {
      custodiaInfo.setUsuariEntitatID(usuariEntitatID);
    } else {
      custodiaInfo.setUsuariAplicacioID(usuariAplicacioID);
    }
    custodiaInfo.setCustodiar(true);
    
    custodiaInfo.setTitolPeticio(titol);
    custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));
    
    custodiaInfo.setCustodiaPluginClassID(custodiaPluginClass);
    return custodiaInfo;
  }
  
  
  
  @Override
  public void deleteCustodiaInfoOfPeticioDeFirma(CustodiaInfo custodiaInfo) throws I18NException {
    if (custodiaInfo == null || custodiaInfo.getCustodiaInfoID() == 0) {
      return;
    }
    long id =  custodiaInfo.getCustodiaInfoID();
    
    if (custodiaInfoEjb.findByPrimaryKey(id) == null) {
      return;
    }
    
    // TODO selectOne
    List<PeticioDeFirma> peticions = this.select(CUSTODIAINFOID.equal(id));
    
    if (peticions.size() != 0) {
      // Eliminam la custodia de la peticio
      PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA)peticions.get(0);
      
      peticio.setCustodiaInfoID(null);
      peticio.setCustodiaInfo(null);
      
      update(peticio);

      // Elimianm la info de custodia
      custodiaInfoEjb.delete(custodiaInfo);
    }

  }

}
