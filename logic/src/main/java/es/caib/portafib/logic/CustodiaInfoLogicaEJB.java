package es.caib.portafib.logic;

import java.util.List;
import java.util.Locale;

import es.caib.portafib.ejb.CustodiaInfoEJB;
import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.ejb.UsuariAplicacioLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.validator.CustodiaInfoBeanValidator;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "CustodiaInfoLogicaEJB")
@SecurityDomain("seycon")
public class CustodiaInfoLogicaEJB extends CustodiaInfoEJB implements CustodiaInfoLogicaLocal {


  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;
  
  
  @EJB(mappedName = UsuariAplicacioLocal.JNDI_NAME)
  protected UsuariAplicacioLocal usuariAplicacioEjb;
  
  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
  protected UsuariEntitatLocal usuariEntitatEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = PluginDeCustodiaLogicaLocal.JNDI_NAME)
  private PluginDeCustodiaLogicaLocal pluginDeCustodiaLogicaEjb;
  
  @EJB(mappedName = PeticioDeFirmaLocal.JNDI_NAME)
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;

  @Override
  public CustodiaInfo getCustodiaUA(UsuariAplicacio usuariAplicacio,
      CustodiaInfoJPA custodiaSentByUser, String titol, EntitatJPA entitatJPA) throws I18NException,
      I18NValidationException {

    if (usuariAplicacio == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Aplicació val null");
    }

    final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
    final String usuariEntitatID = null;

    int politicaCustodia = usuariAplicacio.getPoliticaCustodia();

    final String name = "usuari-aplicació";

    boolean includeCustodiaInfo = true;

    CustodiaInformation ci = internalCheckPotCustodia(entitatJPA,
        politicaCustodia, name, includeCustodiaInfo, custodiaSentByUser, titol,
        usuariAplicacioID, usuariEntitatID);

    if (ci == null) {
      return null;
    }

    return ci.custodiaInfo;
  }
  
   

  /**
   * 
   * @param usuariEntitat
   * @param usuariAplicacioID
   * @param custodiaSentByUser
   * @param titol
   * @return
   * @throws I18NException
   * @throws I18NValidationException
   */
  @Override
  public CustodiaInfo getCustodiaUE(EntitatJPA entitatJPA, UsuariEntitat usuariEntitat, String usuariAplicacioID,
      CustodiaInfoJPA custodiaSentByUser, String titol) throws I18NException,
      I18NValidationException {

    if (usuariEntitat == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    final String usuariEntitatID = usuariEntitat.getUsuariEntitatID();

    int politicaCustodia = usuariEntitat.getPoliticaCustodia();

    final String name = "usuari-entitat";

    final boolean includeCustodiaInfo = true;

    CustodiaInformation ci = internalCheckPotCustodia(entitatJPA,
        politicaCustodia, name, includeCustodiaInfo, custodiaSentByUser, titol,
        usuariAplicacioID, usuariEntitatID);

    if (ci == null) {
      return null;
    }

    return ci.custodiaInfo;
  }

  
  /**
   * 
   * @param usuariAplicacio
   * @param entitatEjb
   * @return
   * @throws I18NException
   * @throws I18NValidationException
   */
  @Override
  public Integer getPoliticaDeCustodiaFinalPerUA(String usuariAplicacioID, EntitatJPA entitatJPA)
      throws I18NException {

    if (usuariAplicacioID == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari AplicacióID val null");
    }

    UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);

    return getPoliticaDeCustodiaFinalPerUA(usuariAplicacio, entitatJPA);
    

  }
  
  
  
  
  /**
   * 
   * @param usuariAplicacio
   * @param entitatEjb
   * @return
   * @throws I18NException
   * @throws I18NValidationException
   */
  @Override
  public Integer getPoliticaDeCustodiaFinalPerUA(UsuariAplicacio usuariAplicacio, EntitatJPA entitatJPA)  throws I18NException {

    if (usuariAplicacio == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Aplicació val null");
    }

    int politicaCustodia = usuariAplicacio.getPoliticaCustodia();

    final String name = "usuari-aplicació";

    
    return internalCheckPotCustodia(entitatJPA, politicaCustodia, name);
    

  }

  /**
   * 
   * @param entitatID
   * @param politicaCustodia
   * @param name
   * @return
   * @throws I18NException
   * @throws I18NValidationException
   */
  protected Integer internalCheckPotCustodia(EntitatJPA entitatJPA, int politicaCustodia,
      String name) throws I18NException {

    final boolean includeCustodiaInfo = false;
    final CustodiaInfoJPA custodiaSentByUser = null;
    final String titol = null;
    String usuariAplicacioID = null;
    String usuariEntitatID = null;

    CustodiaInformation ci;
    try {
      ci = internalCheckPotCustodia(entitatJPA, politicaCustodia, name, includeCustodiaInfo,
          custodiaSentByUser, titol, usuariAplicacioID, usuariEntitatID);
      if (ci == null) {
        return null;
      }

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

  /**
   * 
   * @param entitatID
   * @param politicaCustodia
   * @param name
   * @param includeCustodiaInfo
   * @param custodiaSentByUser
   * @param titol
   * @param usuariAplicacioID
   * @param usuariEntitatID
   * @return
   * @throws I18NException
   * @throws I18NValidationException
   */
  protected CustodiaInformation internalCheckPotCustodia(EntitatJPA entitatJPA,
      int politicaCustodia, String name,
      // requerid per include
      boolean includeCustodiaInfo, CustodiaInfoJPA custodiaSentByUser, String titol,
      String usuariAplicacioID, String usuariEntitatID) throws I18NException,
      I18NValidationException {

    // El que s´hagi definit dins l´Entitat
    if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT) {
      return checkPotCustodiarE(entitatJPA, includeCustodiaInfo, custodiaSentByUser, titol,
          usuariAplicacioID, usuariEntitatID);
    }

    switch (politicaCustodia) {

    // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
        // El que s´hagi definit dins l´Entitat
      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT:        
        return checkPotCustodiarE(entitatJPA, includeCustodiaInfo, custodiaSentByUser, titol,
            usuariAplicacioID, usuariEntitatID);

        // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return null;

        // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:

        // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:

        // Obligatori Plantilla definida en Entitat, usuari-entitat o usuari-aplicació.
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:

        // XYZ ZZZ TRA
        throw new I18NException("Politica de Custodia (id = " + politicaCustodia
            + ") no suportada per PortaFIB per " + name);

      default:
        throw new I18NException("genapp.comodi",
            "No es reconeix el tipus de politica de cutòdia amb id " + politicaCustodia);

    }
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
  
  
  
  /**
   * 
   * @param usuariEntitat
   * @return
   * @throws I18NException
   */
  @Override
  public Integer getPoliticaDeCustodiaFinalPerUE(UsuariEntitat usuariEntitat, EntitatJPA entitatJPA) throws I18NException {
    if (usuariEntitat == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    int politicaCustodia = usuariEntitat.getPoliticaCustodia();

    final String name = "usuari-entitat";

    return internalCheckPotCustodia(entitatJPA, politicaCustodia, name);

  }


  
/**
 * 
 * @param entitat
 * @param includeCustodiaInfo
 * @param custodiaSentByUser2
 * @param titol
 * @param usuariAplicacioID
 * @param usuariEntitatID
 * @return
 * @throws I18NException
 * @throws I18NValidationException
 */
  protected CustodiaInformation checkPotCustodiarE(EntitatJPA entitat,
      // requerid per include
      boolean includeCustodiaInfo, CustodiaInfoJPA custodiaSentByUser, String titol,
      String usuariAplicacioID, String usuariEntitatID) throws I18NException,
      I18NValidationException {
    if (entitat == null) {
      throw new I18NException("genapp.comodi", "Usuari Entitat val null");
    }

    int politicaCustodia = entitat.getPoliticaCustodia();

    switch (politicaCustodia) {

    // No permetre
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
        return null;

        // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        if (includeCustodiaInfo) {

//          if (custodiaSentByUser2 == null) {
//            return null;
//          }

          Long defaultCustodiaInfoID = this.executeQueryOne(EntitatFields.CUSTODIAINFOID,
              EntitatFields.ENTITATID.equal(entitat.getEntitatID()));

          if (defaultCustodiaInfoID == null) {
            // L'entitat no ha definit custòdia
            return null;
          }

          final CustodiaInfoLocal custodiaInfoEjb = this;
          CustodiaInfoJPA custodiaInfoEntitat = custodiaInfoEjb
              .findByPrimaryKey(defaultCustodiaInfoID);

          CustodiaInfoJPA custodiaInfo;
          // Feim una còpia de la custòdia definida per entitat
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

            // Check custodia
           
            CustodiaInfoBeanValidator custodiaValidator = new CustodiaInfoBeanValidator(
                codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginDeCustodiaLogicaEjb,
                usuariAplicacioEjb, usuariEntitatEjb);
            final boolean isNou = true;
            custodiaValidator.throwValidationExceptionIfErrors(custodiaInfo, isNou);

          }

          return new CustodiaInformation(
              ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU,
              custodiaInfo);

        } else {
          return new CustodiaInformation(
              ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU);
        }

        // =========================
        // POLITIQUES NO SUPORTADES
        // =========================

        // Llibertat Total (selecció, edició i us)
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:

        // Només Plantilles de l´Entitat (No editables)
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:

        // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:

        // Obligatori Plantilla definida en Entitat
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:

        // El que s´hagi definit dins l´Entitat
      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT:
        // XYZ ZZZ TRA
        throw new I18NException("Politica de Custodia no suportada per Entitat (id = "
            + politicaCustodia + " )");

      default:
        throw new I18NException("genapp.comodi", "No es reconeix el tipus de politica amb id "
            + politicaCustodia);

    }
  }
  
  
  
  @Override
  public void deleteCustodiaInfoOfPeticioDeFirma(CustodiaInfo custodiaInfo) throws I18NException {
    if (custodiaInfo == null || custodiaInfo.getCustodiaInfoID() == 0) {
      return;
    }
    long id =  custodiaInfo.getCustodiaInfoID();
    
    if (this.findByPrimaryKey(id) == null) {
      return;
    }
    
    // TODO selectOne
    List<PeticioDeFirma> peticions = peticioDeFirmaEjb.select(CUSTODIAINFOID.equal(id));
    
    if (peticions.size() != 0) {
      // Eliminam la custodia de la peticio
      PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA)peticions.get(0);
      
      peticio.setCustodiaInfoID(null);
      peticio.setCustodiaInfo(null);
      
      peticioDeFirmaEjb.update(peticio);

      // Elimianm la info de custodia
      this.delete(custodiaInfo);
    }

  }
  
  

  
  @Override
  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio, EntitatJPA entitatJPA) 
          throws I18NException, I18NValidationException {

    
    UsuariAplicacio usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(peticio.getSolicitantUsuariAplicacioID());
    
    UsuariEntitat usuariEntitat;
    String usuariEntitatID = peticio.getSolicitantUsuariEntitat1ID();
    if (usuariEntitatID == null) {
      usuariEntitat = null;
    } else {
      usuariEntitat = usuariEntitatEjb.findByPrimaryKey(usuariEntitatID);
    }

    return  getAllowedCustodyInfo(peticio, entitatJPA, usuariAplicacio,  usuariEntitat);

  }
  
  

  @Override
  public CustodiaInfo getAllowedCustodyInfo(PeticioDeFirmaJPA peticio,
     EntitatJPA entitatJPA, UsuariAplicacio usuariAplicacio, UsuariEntitat usuariEntitat) throws I18NException, I18NValidationException {

    
    String usuariEntitatID = peticio.getSolicitantUsuariEntitat1ID();
    
    final CustodiaInfoJPA custodiaSentByUser = peticio.getCustodiaInfo();
    final String titol = peticio.getTitol();
    
    CustodiaInfo onlyDef;
    if (usuariEntitatID == null) {
      log.info("XYZ ZZZ  getAllowedCustodyInfo:: ES USER APP (cridant a getCustodiaUA)");
      onlyDef = this.getCustodiaUA(usuariAplicacio, custodiaSentByUser, titol, entitatJPA);      
    } else {
      log.info("XYZ ZZZ  getAllowedCustodyInfo:: ES USER ENTITAT (cridant getCustodiaUE)");
      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      onlyDef = this.getCustodiaUE(entitatJPA, usuariEntitat, usuariAplicacioID, custodiaSentByUser, titol);
    }
   

    return onlyDef;
    
    
    /*
    String entitatID;
    if (peticio.getSolicitantUsuariEntitat1ID() == null) {
      entitatID = usuariAplicacioEjb.executeQueryOne(UsuariAplicacioFields.ENTITATID,
          UsuariAplicacioFields.USUARIAPLICACIOID.equal(peticio.getSolicitantUsuariAplicacioID()));
    } else {
      entitatID = usuariEntitatEjb.executeQueryOne(UsuariEntitatFields.ENTITATID,
          UsuariEntitatFields.USUARIENTITATID.equal(peticio.getSolicitantUsuariEntitat1ID()));
    }

    CustodiaInfo custodiaInfo_Entitat_Default = getDefaultCustodyInfoByEntity(entitatID);

    return custodiaInfo_Entitat_Default;
    */
  }
/*
  protected CustodiaInfo getDefaultCustodyInfoByEntity(String entitatID) {
    CustodiaInfo custodiaInfo_Entitat_Default = null;
    EntitatJPA entitat = entitatLogicaEjb.findByPrimaryKey(entitatID);
    Long custodiaInfoID_Entitat_Default = entitat.getCustodiaInfoID();
    if (custodiaInfoID_Entitat_Default != null) {
      custodiaInfo_Entitat_Default = custodiaInfoEjb
          .findByPrimaryKey(custodiaInfoID_Entitat_Default);
    }
    return custodiaInfo_Entitat_Default;
  }
*/
  

  
  
  

  /*
  @Override
  public CustodiaInfoBean constructDefaultCustodiaInfo(String titol, String entitatID,
      String usuariEntitatID, String usuariAplicacioID, String idioma) {

    CustodiaInfo custodiaInfo_Entitat_Default = getDefaultCustodyInfoByEntity(entitatID);
    
    if (custodiaInfo_Entitat_Default == null) {
      return null;
    }
    
    CustodiaInfoBean custodiaInfo = new CustodiaInfoBean(custodiaInfo_Entitat_Default);

    custodiaInfo.setCustodiaInfoID(0);
    custodiaInfo.setNomPlantilla(null);
    custodiaInfo.setEntitatID(null);
    custodiaInfo.setTitolPeticio(titol);
    
    if (usuariEntitatID != null) {
      custodiaInfo.setUsuariEntitatID(usuariEntitatID);
    } else {
      custodiaInfo.setUsuariAplicacioID(usuariAplicacioID);
    }
   
    custodiaInfo.setTitolPeticio(titol);
    custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));

    return custodiaInfo;
  }
  */
  
  

  

  /**
   * 
   * @author anadal(u80067)
   *
   */
  protected class CustodiaInformation {

    public final int politicaCustodia;

    public final CustodiaInfo custodiaInfo;

    public CustodiaInformation(int politicaCustodia) {
      super();
      this.politicaCustodia = politicaCustodia;
      this.custodiaInfo = null;
    }

    public CustodiaInformation(int politicaCustodia, CustodiaInfo custodiaInfo) {
      super();
      this.politicaCustodia = politicaCustodia;
      this.custodiaInfo = custodiaInfo;
    }

  }


}
