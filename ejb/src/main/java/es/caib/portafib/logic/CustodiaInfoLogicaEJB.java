package es.caib.portafib.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.ejb.CustodiaInfoEJB;
import es.caib.portafib.ejb.CustodiaInfoService;
import es.caib.portafib.ejb.EntitatService;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.validator.CustodiaInfoBeanValidator;
import es.caib.portafib.logic.utils.CustodiaForStartPeticioDeFirma;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NCommonDateTimeFormat;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.documentcustody.api.CustodyException;
import org.fundaciobit.plugins.documentcustody.api.DocumentCustody;
import org.fundaciobit.plugins.documentcustody.api.IDocumentCustodyPlugin;
import org.fundaciobit.plugins.documentcustody.api.NotSupportedCustodyException;
import org.fundaciobit.plugins.documentcustody.api.SignatureCustody;
import org.fundaciobit.pluginsib.barcode.IBarcodePlugin;
import org.fundaciobit.pluginsib.core.utils.MetadataFormatException;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;


/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
@Stateless(name = "CustodiaInfoLogicaEJB")
public class CustodiaInfoLogicaEJB extends CustodiaInfoEJB
    implements CustodiaInfoLogicaLocal, ConstantsV2 {

  @EJB(mappedName = EntitatService.JNDI_NAME, beanName = "EntitatEJB")
  protected EntitatService entitatEjb;

  @EJB(mappedName = UsuariAplicacioService.JNDI_NAME, beanName = "UsuariAplicacioEJB")
  protected UsuariAplicacioService usuariAplicacioEjb;

  @EJB(mappedName = UsuariEntitatService.JNDI_NAME, beanName = "UsuariEntitatEJB")
  protected UsuariEntitatService usuariEntitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresService.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresService codiBarresEjb;

  @EJB(mappedName = PluginDeCustodiaLogicaLocal.JNDI_NAME)
  private PluginDeCustodiaLogicaLocal pluginDeCustodiaLogicaEjb;

  @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME, beanName = "PeticioDeFirmaEJB")
  protected PeticioDeFirmaService peticioDeFirmaEjb;

  @Override
  public CustodiaInfo getCustodiaUA(UsuariAplicacio usuariAplicacio,
      CustodiaInfoJPA custodiaSentByUser, String titol, EntitatJPA entitatJPA)
      throws I18NException, I18NValidationException {

    if (usuariAplicacio == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Aplicació val null");
    }

    final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
    final String usuariEntitatID = null;

    int politicaCustodia = usuariAplicacio.getPoliticaCustodia();

    final String name = "usuari-aplicació";

    boolean constructCustodiaInfo = true;

    CustodiaInformation ci = internalGetCustodiaInformation(entitatJPA, politicaCustodia, name,
        constructCustodiaInfo, custodiaSentByUser, titol, usuariAplicacioID, usuariEntitatID);

    return ci.custodiaInfo;
  }

  @Override
  public CustodiaInfo getCustodiaUE(EntitatJPA entitatJPA, UsuariEntitat usuariEntitat,
      String usuariAplicacioID, CustodiaInfoJPA custodiaSentByUser, String titol)
      throws I18NException, I18NValidationException {

    if (usuariEntitat == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    final String usuariEntitatID = usuariEntitat.getUsuariEntitatID();

    final int politicaCustodia = usuariEntitat.getPoliticaCustodia();

    final String name = "usuari-entitat";

    final boolean constructCustodiaInfo = true;

    CustodiaInformation ci = internalGetCustodiaInformation(entitatJPA, politicaCustodia, name,
        constructCustodiaInfo, custodiaSentByUser, titol, usuariAplicacioID, usuariEntitatID);

    if (ci == null) {
      return null;
    }

    return ci.custodiaInfo;
  }

  @Override
  public Integer getPoliticaDeCustodiaFinalPerUA(String usuariAplicacioID,
      EntitatJPA entitatJPA) throws I18NException {

    if (usuariAplicacioID == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari AplicacióID val null");
    }

    UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb
        .findByPrimaryKey(usuariAplicacioID);

    return getPoliticaDeCustodiaFinalPerUA(usuariAplicacio, entitatJPA);

  }

  @Override
  public Integer getPoliticaDeCustodiaFinalPerUA(UsuariAplicacio usuariAplicacio,
      EntitatJPA entitatJPA) throws I18NException {

    if (usuariAplicacio == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Aplicació val null");
    }

    int politicaCustodia = usuariAplicacio.getPoliticaCustodia();

    final String name = "usuari-aplicació";

    return internalGetPoliticaDeCustodiaIDFinal(entitatJPA, politicaCustodia, name);

  }

  protected Integer internalGetPoliticaDeCustodiaIDFinal(EntitatJPA entitatJPA,
      int politicaCustodia, String name) throws I18NException {

    final boolean constructCustodiaInfo = false;
    final CustodiaInfoJPA custodiaSentByUser = null;
    final String titol = null;
    String usuariAplicacioID = null;
    String usuariEntitatID = null;

    CustodiaInformation ci;
    try {
      ci = internalGetCustodiaInformation(entitatJPA, politicaCustodia, name,
          constructCustodiaInfo, custodiaSentByUser, titol, usuariAplicacioID,
          usuariEntitatID);
      return ci.politicaCustodia;

    } catch (I18NValidationException e) {
      // XYZ ZZZ TRA
      Locale locale = new Locale("ca");
      String msg = "No es posible que llanci aquest error ja que mai es produirà: "
          + I18NLogicUtils.getMessage(e, locale);
      log.error(msg, e);
      throw new I18NException("genapp.comodi", msg);
    }

  }

  protected CustodiaInformation internalGetCustodiaInformation(EntitatJPA entitatJPA,
      int politicaCustodia, String name,
      // requerid per include
      boolean constructCustodiaInfo, CustodiaInfoJPA custodiaSentByUser, String titol,
      String usuariAplicacioID, String usuariEntitatID)
      throws I18NException, I18NValidationException {

    // El que s´hagi definit dins l´Entitat
    if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT) {
      return checkPotCustodiarE(entitatJPA, constructCustodiaInfo, custodiaSentByUser, titol,
          usuariAplicacioID, usuariEntitatID);
    }

    switch (politicaCustodia) {

      // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU: {
        // Si entitat te alguna politica que no ofereix plantilla de Custòdia llavors hem de
        // retornar No es Permet
        CustodiaInformation ciEntitat = checkPotCustodiarE(entitatJPA, true, null, titol,
                usuariAplicacioID, usuariEntitatID);

        if (ciEntitat.custodiaInfo == null) {
          return new CustodiaInformation(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);
        } else {
          return new CustodiaInformation(politicaCustodia, ciEntitat.custodiaInfo);
        }
      }

      // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU: {
        CustodiaInformation ciEntitat = checkPotCustodiarE(entitatJPA, constructCustodiaInfo, custodiaSentByUser, titol,
                usuariAplicacioID, usuariEntitatID);
        if (ciEntitat.custodiaInfo == null) {
          return new CustodiaInformation(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);
        } else {
          return new CustodiaInformation(politicaCustodia, ciEntitat.custodiaInfo);
        }
      }

      // Obligatori Plantilla definida en Entitat, usuari-entitat o usuari-aplicació.
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: {
        CustodiaInfoJPA custodiaInfo;
        if (constructCustodiaInfo) {
          custodiaInfo = generateCustodiaInfoDefinidaAContinuacioForUsuariEntitatUsuariAplicacio(
              titol, usuariAplicacioID, usuariEntitatID);
        } else {
          custodiaInfo = null;
        }
        return new CustodiaInformation(politicaCustodia, custodiaInfo);
      }

      // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL: {
        CustodiaInfoJPA custodiaInfo;
        if (custodiaSentByUser != null) {
          custodiaInfo = custodiaSentByUser;
        } else {
          if (constructCustodiaInfo) {
            custodiaInfo = createCustodiaInfoLlibertatTotal(entitatJPA.getEntitatID(), titol,
                usuariAplicacioID, usuariEntitatID);
          } else {
            custodiaInfo = null;
          }
        }
        return new CustodiaInformation(politicaCustodia, custodiaInfo);
      }

      // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return new CustodiaInformation(politicaCustodia);

      // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "Politica de Custodia (id = "
            + politicaCustodia + ") no suportada per PortaFIB per " + name);

      default:
        throw new I18NException("genapp.comodi",
            "No es reconeix el tipus de politica de cutòdia amb id " + politicaCustodia);

    }
  }

  protected CustodiaInfoJPA generateCustodiaInfoDefinidaAContinuacioForUsuariEntitatUsuariAplicacio(
      String titol, String usuariAplicacioID, String usuariEntitatID)
      throws I18NException, I18NValidationException {

    Long custodiaObligatoriaID;
    // XYZ ZZZ ZZZ S'hauria de fer segons el tipus de Petició
    if (usuariEntitatID != null) {
      // Usuari Entitat
      custodiaObligatoriaID = usuariEntitatEjb.executeQueryOne(
          UsuariEntitatFields.CUSTODIAINFOID,
          UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID));
    } else {
      // Usuari Aplicacio
      custodiaObligatoriaID = usuariAplicacioEjb.executeQueryOne(
          UsuariAplicacioFields.CUSTODIAINFOID,
          UsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
    }

    if (custodiaObligatoriaID == null) {
      // UsrEnt o UsrApp no han definit custòdia
      return null;
    }

    final CustodiaInfoService custodiaInfoEjb = this;
    CustodiaInfoJPA custodiaInfoPersonaAplicacio = custodiaInfoEjb
        .findByPrimaryKey(custodiaObligatoriaID);

    // Feim una còpia de la custòdia definida per entitat
    CustodiaInfoJPA custodiaInfo;
    custodiaInfo = cloneCustodiaInfo(titol, usuariAplicacioID, usuariEntitatID,
        custodiaInfoPersonaAplicacio);
    {
      // Check custodia
      CustodiaInfoBeanValidator custodiaValidator = new CustodiaInfoBeanValidator(
          codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginDeCustodiaLogicaEjb,
          usuariAplicacioEjb, usuariEntitatEjb);
      final boolean isNou = true;

      custodiaValidator.throwValidationExceptionIfErrors(custodiaInfo, isNou);

    }

    return custodiaInfo;
  }

  @Override
  public Integer getPoliticaDeCustodiaFinalPerUE(String usuariEntitatID, EntitatJPA entitatJPA)
      throws I18NException {

    if (usuariEntitatID == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Entitat ID val null");
    }

    UsuariEntitatJPA usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);

    return getPoliticaDeCustodiaFinalPerUE(usuariEntitat, entitatJPA);
  }

  @Override
  public Integer getPoliticaDeCustodiaFinalPerUE(UsuariEntitat usuariEntitat,
      EntitatJPA entitatJPA) throws I18NException {
    if (usuariEntitat == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    int politicaCustodia;
    politicaCustodia = usuariEntitat.getPoliticaCustodia();

    final String name = "usuari-entitat";

    return internalGetPoliticaDeCustodiaIDFinal(entitatJPA, politicaCustodia, name);
  }

  protected CustodiaInformation checkPotCustodiarE(EntitatJPA entitat,
      // requerid per include
      boolean includeCustodiaInfo, CustodiaInfoJPA custodiaSentByUser, String titol,
      String usuariAplicacioID, String usuariEntitatID)
      throws I18NException, I18NValidationException {
    if (entitat == null) {
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    int politicaCustodia = entitat.getPoliticaCustodia();

    switch (politicaCustodia) {

      // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return new CustodiaInformation(politicaCustodia);

      // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:

        // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU: {
        CustodiaInfoJPA custodiaInfo;
        if (includeCustodiaInfo) {
          String entitatID = entitat.getEntitatID();
          custodiaInfo = getCustodiaInfoOfEntitat(titol, usuariAplicacioID, usuariEntitatID,
              entitatID);
        } else {
          custodiaInfo = null;
        }
        return new CustodiaInformation(politicaCustodia, custodiaInfo);
      }

      // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL: {
        CustodiaInfoJPA custodiaInfo;
        if (custodiaSentByUser != null) {
          custodiaInfo = custodiaSentByUser;
        } else {

          if (includeCustodiaInfo) {
            custodiaInfo = createCustodiaInfoLlibertatTotal(entitat.getEntitatID(), titol,
                usuariAplicacioID, usuariEntitatID);
          } else {
            custodiaInfo = null;
          }
        }
        return new CustodiaInformation(politicaCustodia, custodiaInfo);
      }

      // Obligatori Plantilla definida en Entitat
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: {
        CustodiaInfoJPA custodiaInfo;
        custodiaInfo = getCustodiaInfoOfEntitat(titol, usuariAplicacioID, usuariEntitatID,
            entitat.getEntitatID());
        return new CustodiaInformation(politicaCustodia, custodiaInfo);
      }

      // =========================
      // POLITIQUES NO SUPORTADES
      // =========================

      // El que s´hagi definit dins l´Entitat
      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT: {
        // XYZ ZZZ TRA
        throw new I18NException("La Politica de Custodia "
            + "´POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT´ no suportada per Entitat"
            + " usar ´OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO´ (id = " + politicaCustodia
            + " )");
      }

      // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:

        // XYZ ZZZ TRA
        throw new I18NException(
            "Politica de Custodia no suportada per Entitat (id = " + politicaCustodia + " )");

      default:
        throw new I18NException("genapp.comodi",
            "No es reconeix el tipus de politica amb id " + politicaCustodia);

    }
  }

  protected CustodiaInfoJPA getCustodiaInfoOfEntitat(String titol, String usuariAplicacioID,
      String usuariEntitatID, String entitatID) throws I18NException, I18NValidationException {
    CustodiaInfoJPA custodiaInfo;
    Long defaultCustodiaInfoID = this.executeQueryOne(EntitatFields.CUSTODIAINFOID,
        EntitatFields.ENTITATID.equal(entitatID));

    if (defaultCustodiaInfoID == null) {
      // L'entitat no ha definit custòdia
      custodiaInfo = null;
    } else {

      final CustodiaInfoService custodiaInfoEjb = this;
      CustodiaInfoJPA custodiaInfoEntitat = custodiaInfoEjb
          .findByPrimaryKey(defaultCustodiaInfoID);

      // Feim una còpia de la custòdia definida per entitat
      custodiaInfo = cloneCustodiaInfo(titol, usuariAplicacioID, usuariEntitatID,
          custodiaInfoEntitat);
      {
        // Check custodia
        CustodiaInfoBeanValidator custodiaValidator = new CustodiaInfoBeanValidator(
            codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginDeCustodiaLogicaEjb,
            usuariAplicacioEjb, usuariEntitatEjb);
        final boolean isNou = true;

        custodiaValidator.throwValidationExceptionIfErrors(custodiaInfo, isNou);

      }
    }
    return custodiaInfo;
  }

  protected CustodiaInfoJPA cloneCustodiaInfo(String titol, String usuariAplicacioID,
      String usuariEntitatID, CustodiaInfoJPA custodiaInfoEntitat) {
    CustodiaInfoJPA custodiaInfo;
    custodiaInfo = new CustodiaInfoJPA(custodiaInfoEntitat);

    // Modificam les dades especifiques per
    {
      // Set common DATA
      custodiaInfo.setPluginID(custodiaInfoEntitat.getPluginID());
      custodiaInfo.setUsuariEntitatID(usuariEntitatID);
      custodiaInfo.setUsuariAplicacioID(usuariAplicacioID);
      custodiaInfo.setTitolPeticio(titol);
      // Posam null per indicar que no es de cap entitat
      custodiaInfo.setEntitatID(null);
      // Posam null per indicar que no es de cap entitat i no es cap plantilla
      custodiaInfo.setNomPlantilla(null);
      custodiaInfo.setCustodiaInfoID(0);
    }
    return custodiaInfo;
  }

  @Override
  public void deleteCustodiaInfoOfPeticioDeFirma(CustodiaInfo custodiaInfo)
      throws I18NException {
    if (custodiaInfo == null || custodiaInfo.getCustodiaInfoID() == 0) {
      return;
    }
    long id = custodiaInfo.getCustodiaInfoID();

    if (this.findByPrimaryKey(id) == null) {
      return;
    }

    // TODO selectOne
    List<PeticioDeFirma> peticions = peticioDeFirmaEjb.select(CUSTODIAINFOID.equal(id));

    if (peticions.size() != 0) {
      // Eliminam la custodia de la peticio
      PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA) peticions.get(0);

      peticio.setCustodiaInfoID(null);
      peticio.setCustodiaInfo(null);

      peticioDeFirmaEjb.update(peticio);

      // Elimianm la info de custodia
      this.delete(custodiaInfo);
    }
  }

  public CustodiaInfo getCustodyInfoOnCreatePeticio(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA, UsuariEntitat usuariEntitat, UsuariAplicacio usuariAplicacio)
      throws I18NException, I18NValidationException {
    final boolean onCreatePeticio = true;
    return searchDefaultCustodyInfo(peticio, entitatJPA, onCreatePeticio, usuariEntitat,
        usuariAplicacio);
  }

  public CustodiaInfo getCustodyInfoOnAddCustodyToPeticio(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA) throws I18NException, I18NValidationException {
    final boolean onCreatePeticio = false;
    return searchDefaultCustodyInfo(peticio, entitatJPA, onCreatePeticio);
  }

  protected CustodiaInfo searchDefaultCustodyInfo(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA, boolean onCreatePeticio)
      throws I18NException, I18NValidationException {

    UsuariAplicacio usuariAplicacio;
    String usuariAplicacioID = peticio.getSolicitantUsuariAplicacioID();
    if (usuariAplicacioID == null) {
      usuariAplicacio = null;
    } else {
      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
    }

    UsuariEntitat usuariEntitat;
    String usuariEntitatID = peticio.getSolicitantUsuariEntitat1ID();
    if (usuariEntitatID == null) {
      usuariEntitat = null;
    } else {
      usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    }

    return searchDefaultCustodyInfo(peticio, entitatJPA, onCreatePeticio, usuariEntitat,
        usuariAplicacio);

  }

  protected CustodiaInfo searchDefaultCustodyInfo(PeticioDeFirmaJPA peticio,
      EntitatJPA entitatJPA, boolean onCreatePeticio, UsuariEntitat usuariEntitat,
      UsuariAplicacio usuariAplicacio) throws I18NException, I18NValidationException {


    final String titol = peticio.getTitol();

    CustodiaInfo onlyDef;

    // Nous camps de Peticio de Firma #281
    switch (peticio.getOrigenPeticioDeFirma()) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB: {
        
        int politicaCustodia = usuariEntitat.getPoliticaCustodia();

        onlyDef = this.searchDefaultCustodyInfo(peticio, entitatJPA, politicaCustodia, "soli web",
            titol, onCreatePeticio, usuariAplicacio, usuariEntitat);
      }
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2: {
        
        int politicaCustodia = usuariAplicacio.getPoliticaCustodia();
        onlyDef = this.searchDefaultCustodyInfo(peticio, entitatJPA, politicaCustodia, "soli aplicacio",
            titol, onCreatePeticio, usuariAplicacio, usuariEntitat);
      }
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "No hi ha codi per l´obtenció de Custodia per defecte"
                + " de les Peticions de Firma amb Origen "
                + I18NCommonUtils.tradueix(new Locale("ca"),
                    "origenpeticiodefirma." + peticio.getOrigenPeticioDeFirma()));
    }

    return onlyDef;

  }

  protected CustodiaInfo searchDefaultCustodyInfo(PeticioDeFirmaJPA peticio, 
      EntitatJPA entitatJPA, int politicaCustodia,
      String name, String titol, boolean onCreatePeticio, UsuariAplicacio usuariAplicacio,
      UsuariEntitat usuariEntitat) throws I18NException, I18NValidationException {

    String usuariAplicacioID = usuariAplicacio == null ? null
        : usuariAplicacio.getUsuariAplicacioID();
    String usuariEntitatID = usuariEntitat == null ? null : usuariEntitat.getUsuariEntitatID();

    switch (politicaCustodia) {

      // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        if (onCreatePeticio) {
          CustodiaInfo custodiaPeticio = peticio.getCustodiaInfo();
          if (custodiaPeticio == null) {
            return null;
          }
          // Miram primer quina custòdia té l'entitat
          CustodiaInfo custodiaEntitat = searchDefaultCustodyInfoE(peticio, entitatJPA, titol, usuariAplicacioID, usuariEntitatID,
                  onCreatePeticio);
          if (custodiaEntitat == null) {
            return null;
          }

          // Si l'entitat té una custòdia, i la petició ha demanat aquesta custòdia, s'ha d'emprar.
          // comprovarem al manco que el plugin sigui el mateix
          if (custodiaEntitat.getPluginID() == custodiaPeticio.getPluginID()) {
            // si es editable retornam el que ha enviat la petició de firma, sinó retornam la custòdia de l'entitat
            return custodiaEntitat.isEditable() ? custodiaPeticio : custodiaPeticio;
          }

        } // else ADDCUSTODY WEBUI
        // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
        // El que s´hagi definit dins l´Entitat
      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT:
        return searchDefaultCustodyInfoE(peticio,entitatJPA, titol, usuariAplicacioID, usuariEntitatID,
            onCreatePeticio);

      // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return null;

      // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
        if (onCreatePeticio) {
          return peticio.getCustodiaInfo();
        } else {
          return createCustodiaInfoLlibertatTotal(
              entitatJPA.getEntitatID(), titol, usuariAplicacioID, usuariEntitatID);
        }

        // Obligatori Plantilla definida en Entitat, usuari-entitat o usuari-aplicació.
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: {
        return generateCustodiaInfoDefinidaAContinuacioForUsuariEntitatUsuariAplicacio(
            titol, usuariAplicacioID, usuariEntitatID);
      }

      // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "Politica de Custodia (id = "
            + politicaCustodia + ") no suportada per PortaFIB per " + name);

      default:
     // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "No es reconeix el tipus de politica de cutòdia amb id " + politicaCustodia);

    }
  }

  protected CustodiaInfoJPA createCustodiaInfoLlibertatTotal(String entitatID, String titol,
      String usuariAplicacioID, String usuariEntitatID) throws I18NException {

    CustodiaInfoJPA custodiaInfo = new CustodiaInfoJPA();
    custodiaInfo.setCustodiaInfoID(0);

    defaultValuesForCustodiaInfo(entitatID, custodiaInfo);

    // Posam null per indicar que no es de cap entitat
    custodiaInfo.setEntitatID(null);
    // Posam null per indicar que no es cap plantilla
    custodiaInfo.setNomPlantilla(null);
    custodiaInfo.setUsuariEntitatID(usuariEntitatID);
    custodiaInfo.setUsuariAplicacioID(usuariAplicacioID);
    custodiaInfo.setTitolPeticio(titol);
    custodiaInfo.setEditable(true);

    return custodiaInfo;
  }

  protected CustodiaInfo searchDefaultCustodyInfoE(PeticioDeFirmaJPA peticio,EntitatJPA entitatJPA, String titol,
      String usuariAplicacioID, String usuariEntitatID, boolean onCreatePeticio)
      throws I18NException, I18NValidationException {

    int politicaCustodia = entitatJPA.getPoliticaCustodia();

    switch (politicaCustodia) {

      // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return null;

      // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        if (onCreatePeticio) {
           return null;
        }

        // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
        if (onCreatePeticio) {
            return null;
          }

        // Obligatori Plantilla definida en Entitat
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: {
        CustodiaInfoJPA custodiaInfo;
        custodiaInfo = getCustodiaInfoOfEntitat(titol, usuariAplicacioID, usuariEntitatID,
            entitatJPA.getEntitatID());
        return custodiaInfo;
      }

      // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
        if (onCreatePeticio) {
          return peticio.getCustodiaInfo();
        } else {
          return createCustodiaInfoLlibertatTotal(
              entitatJPA.getEntitatID(), titol, usuariAplicacioID, usuariEntitatID);
        }

        // =========================
        // POLITIQUES NO SUPORTADES
        // =========================

        // El que s´hagi definit dins l´Entitat
      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT: {
        // XYZ ZZZ TRA
        throw new I18NException("La Politica de Custodia "
            + "´POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT´ no suportada per Entitat"
            + " usar ´OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO´ (id = " + politicaCustodia
            + " )");
      }

      // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
        // XYZ ZZZ TRA
        throw new I18NException(
            "Politica de Custodia no suportada per Entitat (id = " + politicaCustodia + " )");

      default:
        throw new I18NException("genapp.comodi",
            "No es reconeix el tipus de politica amb id " + politicaCustodia);

    }

  }

  @Override
  public CustodiaInfoJPA defaultValuesForCustodiaInfo(String entitatID,
      CustodiaInfoJPA custodiaInfo) throws I18NException {
    custodiaInfo.setEntitatID(entitatID);
    custodiaInfo.setEditable(true);

    custodiaInfo.setPagines("*");
    custodiaInfo.setCodiBarresPosicioPaginaID(ConstantsV2.POSICIO_PAGINA_ESQUERRA);
    custodiaInfo.setCodiBarresText("{0}");
    custodiaInfo.setCodiBarresID(ConstantsV2.BARCODE_PDF417_PLUGIN);

    custodiaInfo.setMissatgePosicioPaginaID(ConstantsV2.POSICIO_PAGINA_ESQUERRA);

    Properties prop = new Properties();
    prop.put("ca",
        "Document custodiat amb el sistema {2}. Identificador {1}. Data:{3} URL de validació:{0}. Valor especial: {4}");
    prop.put("es",
        "Documento custodiado con el sistema {2}. Identificador = {1}. URL de validación = {0}. Fecha Custodia:{3}. Valor especial: {4}");

    StringWriter sw = new StringWriter();
    try {
      prop.store(sw, "");
    } catch (IOException e) {
      e.printStackTrace();
    }
    custodiaInfo.setMissatge(sw.toString());

    custodiaInfo.setCustodiar(true);

    custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));

    List<Plugin> plugins = pluginDeCustodiaLogicaEjb.getAllPluginsSenseEntitat();
    if (plugins.size() == 0) {
      log.warn("No hi ha plugins de custòdia!!!");
    } else {
      custodiaInfo.setPluginID(plugins.get(0).getPluginID());
    }

    return custodiaInfo;
  }

  @Override
  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA)
      throws I18NException, I18NValidationException {

    UsuariAplicacio usuariAplicacio;
    String usuariAplicacioID = peticio.getSolicitantUsuariAplicacioID();
    if (usuariAplicacioID == null) {
      usuariAplicacio = null;
    } else {
      usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
    }

    UsuariEntitat usuariEntitat;
    String usuariEntitatID = peticio.getSolicitantUsuariEntitat1ID();
    if (usuariEntitatID == null) {
      usuariEntitat = null;
    } else {
      usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    }

    return getAllowedCustodyInfo(peticio, entitatJPA, usuariAplicacio, usuariEntitat);

  }

  @Override
  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA,
      UsuariAplicacio usuariAplicacio, UsuariEntitat usuariEntitat)
      throws I18NException, I18NValidationException {

    // String usuariEntitatID = peticio.getSolicitantUsuariEntitat1ID();

    final CustodiaInfoJPA custodiaSentByUser = peticio.getCustodiaInfo();
    final String titol = peticio.getTitol();

    CustodiaInfo onlyDef;

    // Nous camps de Peticio de Firma #281
    switch (peticio.getOrigenPeticioDeFirma()) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
        log.debug("getAllowedCustodyInfo:: ES USER ENTITAT (cridant getCustodiaUE)");
        final String usuariAplicacioID = null;// usuariAplicacio.getUsuariAplicacioID();
        onlyDef = this.getCustodiaUE(entitatJPA, usuariEntitat, usuariAplicacioID,
            custodiaSentByUser, titol);
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
        log.debug("getAllowedCustodyInfo:: ES USER APP (cridant a getCustodiaUA)");
        onlyDef = this.getCustodiaUA(usuariAplicacio, custodiaSentByUser, titol, entitatJPA);
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "No hi ha codi per l´obtenció de Custodia per defecte"
                + " de les Peticions de Firma amb Origen "
                + I18NCommonUtils.tradueix(new Locale("ca"),
                    "origenpeticiodefirma." + peticio.getOrigenPeticioDeFirma()));
    }

    return onlyDef;

  }

  @Override
  public CustodiaForStartPeticioDeFirma custodiaCommonActionsOnStartPeticioDeFirma(
      PeticioDeFirmaJPA peticioDeFirma, CustodiaInfo custodiaInfo) throws I18NException {
    CustodiaForStartPeticioDeFirma custodiaForStartPeticioDeFirma = null;
    if (custodiaInfo != null) {

      // Mirar Com extreure aquest tros de custòdia i el de thingsToDo In PADES
      // per reutilitzar en Passarela !!!!

      if (custodiaInfo.isCustodiar()) {

        String custodyID;
        IDocumentCustodyPlugin plugin;

        plugin = pluginDeCustodiaLogicaEjb.getInstanceByPluginID(custodiaInfo.getPluginID());

        // CustodyParameter conté la peticio de Firma en Format XML
        // la còpia es fa per evitar modificacions de la instància interna
        Map<String, Object> parameters = getAdditionalParametersForDocumentCustody(
            peticioDeFirma, null, custodiaInfo);

        // PUNT IMPORTANT => RESERVA DE l'ID DE CUSTODIA
        try {
          custodyID = plugin.reserveCustodyID(parameters);

          // TODO Check custodyID != null
          custodiaInfo.setCustodiaDocumentID(custodyID);
          String url = plugin.getOriginalFileUrl(custodyID, parameters);
          custodiaInfo.setUrlFitxerCustodiat(url);

          custodiaInfo.setTitolPeticio(peticioDeFirma.getTitol());
          custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));

          // PORTAFIB2: Nous camps de CustodyInfo #280
          custodiaInfo.setCsv(plugin.getCsv(custodyID, parameters));
          custodiaInfo.setCsvGenerationDefinition(
              plugin.getCsvGenerationDefinition(custodyID, parameters));
          custodiaInfo.setCsvValidationWeb(plugin.getCsvValidationWeb(custodyID, parameters));
          custodiaInfo
              .setOriginalFileDirectUrl(plugin.getOriginalFileUrl(custodyID, parameters));
          custodiaInfo
              .setPrintableFileDirectUrl(plugin.getPrintableFileUrl(custodyID, parameters));
          custodiaInfo.setEniFileDirectUrl(plugin.getEniFileUrl(custodyID, parameters));

          if (log.isDebugEnabled()) {
            log.debug("custodiaInfo.CsvGenerationDefinition => "
                + custodiaInfo.getCsvGenerationDefinition());
            log.debug(
                "custodiaInfo.getCsvValidationWeb => " + custodiaInfo.getCsvValidationWeb());
            log.debug("custodiaInfo.setOriginalFileDirectUrl => "
                + custodiaInfo.getOriginalFileDirectUrl());
            log.debug("custodiaInfo.setPrintableFileDirectUrl => "
                + custodiaInfo.getPrintableFileDirectUrl());
            log.debug(
                "custodiaInfo.setEniFileDirectUrl => " + custodiaInfo.getEniFileDirectUrl());
          }

          custodiaInfo.setExpedientArxiuId(null);
          custodiaInfo.setDocumentArxiuId(null);

          this.update(custodiaInfo);

          custodiaForStartPeticioDeFirma = new CustodiaForStartPeticioDeFirma(custodyID,
              custodiaInfo, plugin);
        } catch (CustodyException e) {
          // XYZ ZZZ TRA
          final String msg = "Error reservant ID de Custòdia o recuperant URLs d'accés: "
              + e.getMessage();
          log.error(msg, e);
          throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
        }

      }
    }
    return custodiaForStartPeticioDeFirma;
  }

  @Override
  public es.caib.portafib.logic.utils.StampCustodiaInfo custodiaPAdESActionsOnStartPeticioDeFirma(
      PeticioDeFirmaJPA peticioDeFirma, CustodiaInfo custodiaInfo,
      CustodiaForStartPeticioDeFirma custodiaForStartPeticioDeFirma, Locale locale)
      throws I18NException {
    es.caib.portafib.logic.utils.StampCustodiaInfo custodiaInfoStamp = null;
    if (custodiaInfo != null) {

      /*
       * Missatge de custòdia a mostrar en el document. {0} = URL {1} = custodiaID {2} =
       * custodiaPluginClassID {3} = data amb hora {4} = Special Value
       */

      String data = new I18NCommonDateTimeFormat(locale).format(new Date());

      Map<String, Object> additionParameters = getAdditionalParametersForDocumentCustody(
          peticioDeFirma, null, custodiaInfo);

      final String custodyID = custodiaInfo.getCustodiaDocumentID();
      final String specialValue = null;
      try {
        custodiaForStartPeticioDeFirma.plugin.getSpecialValue(custodyID, additionParameters);
      } catch (CustodyException ce) {
        // XYZ ZZZ TRA
        throw new I18NException(ce, "genapp.comodi", new I18NArgumentString(
            "Error intentant obtenir SpecialValue de Custòdia: " + ce.getMessage()));
      }
      Object[] arguments = new Object[] { custodiaInfo.getUrlFitxerCustodiat(), custodyID,
          custodiaInfo.getPluginID(), // TODO Posar NOM del Plugin
          data, specialValue };

      Properties prop = new Properties();
      try {
        prop.load(new StringReader(custodiaInfo.getMissatge()));
      } catch (IOException e) {
        // No ha de llançar error mai
      }

      String msg = prop.getProperty(locale.getLanguage());

      if (msg == null) {
        throw new I18NException("custodiainfo.missatge.error.format", locale.getLanguage());
      }

      String missatge = MessageFormat.format(msg, arguments);

      String barcodeText = MessageFormat.format(custodiaInfo.getCodiBarresText(), arguments);

      String javaName = custodiaInfo.getCodiBarresID();

      IBarcodePlugin barcode = (IBarcodePlugin) PluginsManager.instancePluginByClassName(javaName, "",Configuracio.getSystemAndFileProperties());
      if (barcode == null) {
        throw new I18NException("plugin.donotinstantiate", javaName);
      }

      custodiaInfoStamp = new StampCustodiaInfo(
          (int) custodiaInfo.getMissatgePosicioPaginaID(), missatge, barcode, barcodeText,
          custodiaInfo.getPagines());

    }
    return custodiaInfoStamp;
  }

  @Override
  public Map<String, Object> getAdditionalParametersForDocumentCustody(
      PeticioDeFirmaJPA peticioDeFirma, FirmaJPA firma, CustodiaInfo custodiaInfo)
      throws I18NException {

    Map<String, Object> additionParameters = new HashMap<String, Object>();

    if (custodiaInfo != null) {
      String params = custodiaInfo.getCustodiaPluginParameters();
      if (params != null && params.trim().length() != 0) {
        // Afegir propietats a additionParameters
        Properties prop = new Properties();
        try {
          prop.load(new StringReader(params));
        } catch (IOException e1) {
          log.error(e1.getMessage(), e1);
        }
        for (Entry<Object, Object> e : prop.entrySet()) {
          additionParameters.put(e.getKey().toString(), e.getValue());
        }
      }
    }

    // PeticioDeFirmaJPA pclone = new
    // PeticioDeFirmaJPA(PeticioDeFirmaJPA.copyJPA(peticioDeFirma));
    additionParameters.put("peticio", peticioDeFirma);

    if (firma != null) {
      // FirmaJPA fclone = new FirmaJPA(FirmaJPA.copyJPA(firma));
      additionParameters.put("firma", firma);
    }

    return additionParameters;
  }

  @Override
  public void custodiaThingToDoOnFinishPeticioDeFirma(String fitxerAFirmarNom,
      IPortaFIBDataSource fitxerAFirmar, File signatureFile, PeticioDeFirmaJPA peticioDeFirma,
      FirmaJPA firma, CustodiaInfo custInfo) throws I18NException {
    if (custInfo != null && custInfo.isCustodiar() && signatureFile != null) {

      IDocumentCustodyPlugin plugin = pluginDeCustodiaLogicaEjb
          .getInstanceByPluginID(custInfo.getPluginID());

      custInfo.setDataCustodia(new Timestamp(new Date().getTime()));

      this.update(custInfo);

      Map<String, Object> additionParameters = this
          .getAdditionalParametersForDocumentCustody(peticioDeFirma, firma, custInfo);

      final int tipusFirma = peticioDeFirma.getTipusFirmaID();

      // Si la firma es DETACHED (o sigui EXPLICID) s'ha de definir DocumentCustody
      DocumentCustody dc = null;
      if (tipusFirma != ConstantsV2.TIPUSFIRMA_PADES
          && peticioDeFirma.getModeDeFirma() == ConstantsV2.SIGN_MODE_EXPLICIT) {

        dc = new DocumentCustody();
        try {
          InputStream inputStream = fitxerAFirmar.getInputStream();
          dc.setData(org.fundaciobit.pluginsib.core.utils.FileUtils.toByteArray(inputStream));
          inputStream.close();
        } catch (Exception e) {
          // XYZ ZZZ TRA
          String msg = "Error desconegut llegint el fitxer original per custodiar: "
              + e.getMessage();
          log.error(msg, e);
          throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
        }
        dc.setLength(dc.getData().length);
        dc.setMime(dc.getMime());
        dc.setName(dc.getName());

      }

      byte[] signatureContent;
      try {
        signatureContent = FileUtils.readFileToByteArray(signatureFile);
      } catch (IOException e1) {
        // XYZ ZZZ TRA
        String msg = "Error desconegut llegint el fitxer firmat per custodiar: "
            + e1.getMessage();
        log.error(msg, e1);
        throw new I18NException(e1, "genapp.comodi", new I18NArgumentString(msg));
      }

      try {

        switch (tipusFirma) {
          case ConstantsV2.TIPUSFIRMA_PADES: {
            SignatureCustody sc = new SignatureCustody();
            sc.setName(fitxerAFirmarNom);
            sc.setData(signatureContent);
            sc.setSignatureType(SignatureCustody.PADES_SIGNATURE);
            sc.setMime(PdfUtils.MIME_TYPE_PDF);

            plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, null, sc,
                null);

          }
          break;
          case ConstantsV2.TIPUSFIRMA_XADES: {
            SignatureCustody sc = new SignatureCustody();
            sc.setName(fitxerAFirmarNom);
            sc.setData(signatureContent);
            sc.setSignatureType(SignatureCustody.XADES_SIGNATURE);
            sc.setMime(MIME_TYPE_XML);
            plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, dc, sc, null);
          }
          break;
          case ConstantsV2.TIPUSFIRMA_CADES: {
            SignatureCustody sc = new SignatureCustody();
            sc.setName(fitxerAFirmarNom);
            sc.setData(signatureContent);
            sc.setSignatureType(SignatureCustody.CADES_SIGNATURE);
            sc.setMime(MIME_TYPE_BINARY);
            plugin.saveAll(custInfo.getCustodiaDocumentID(), additionParameters, dc, sc, null);
          }
          break;
          default:
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "Tipus de Firma " + tipusFirma
                + " no suportada en el procés de Custòdia !!!!");
        }

      } catch (CustodyException e) {
        // XYZ ZZZ TRA
        String msg = "Error desconegut a l'hora de Custodiar(custodyexception): "
            + e.getMessage();
        log.error(msg, e);
        throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
      } catch (NotSupportedCustodyException e) {
        // XYZ ZZZ TRA
        String msg = "Error desconegut a l'hora de Custodiar(notsupported): " + e.getMessage();
        log.error(msg, e);
        throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
      } catch (MetadataFormatException e) {
        // XYZ ZZZ TRA
        String msg = "Error desconegut a l'hora de Custodiar(metadata): " + e.getMessage();
        log.error(msg, e);
        throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
      }

    }
  }

  @Override
  public EntityManager getEntityManager() {
    return super.getEntityManager();
  }

  @Override
  public CustodiaInfoJPA findByPrimaryKeyUnathorized(Long _ID_) {
    return super.findByPrimaryKey(_ID_);
  }

  /**
   * 
   * @author anadal(u80067)
   * @author areus
   */
  protected static class CustodiaInformation {

    public final int politicaCustodia;
    public final CustodiaInfo custodiaInfo;

    public CustodiaInformation(int politicaCustodia) {
      this.politicaCustodia = politicaCustodia;
      this.custodiaInfo = null;
    }

    public CustodiaInformation(int politicaCustodia, CustodiaInfo custodiaInfo) {
      this.politicaCustodia = politicaCustodia;
      this.custodiaInfo = custodiaInfo;
    }
  }
}
