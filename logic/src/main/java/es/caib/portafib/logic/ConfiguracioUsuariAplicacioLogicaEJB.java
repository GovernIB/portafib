package es.caib.portafib.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioEJB;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioQueryPath;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ConfiguracioUsuariAplicacioLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class ConfiguracioUsuariAplicacioLogicaEJB extends UsuariAplicacioConfiguracioEJB
    implements ConfiguracioUsuariAplicacioLogicaLocal {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;

  /*
   * @Override
   * 
   * @RolesAllowed({ "PFI_ADMIN", "PFI_USER" }) public UsuariAplicacioConfiguracio
   * getConfiguracioUsuariAplicacio( final String usuariAplicacioID, String codiPerfil, final
   * int usFirma) throws I18NException {
   * 
   * PerfilDeFirma usuariApicacioPerfil = getPerfilDeFirma(usuariAplicacioID, codiPerfil,
   * usFirma);
   * 
   * // XYZ ZZZ Falta Fer !!!! // Aqui s'ha de processar la condició del Perfil i veure quin
   * valor retorna // A partir del valor sencer obtingut retornar UsrAppConfiguracio1ID // o
   * UsrAppConfiguracio2ID o UsrAppConfiguracio3ID
   * 
   * final Long idConf = usuariApicacioPerfil.getConfiguracioDeFirma1ID();
   * 
   * final UsuariAplicacioConfiguracio config = findByPrimaryKey(idConf);
   * 
   * checkTePermisPerUsDeFirma(usuariAplicacioID, codiPerfil, usFirma, config);
   * 
   * return config; }
   */

  protected void checkTePermisPerUsDeFirma(final String usuariAplicacioID, String codiPerfil,
      final int usFirma, final UsuariAplicacioConfiguracio config) throws I18NException {
    boolean tePermis = false;
    String nom;
    switch (usFirma) {
      case ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR:
        tePermis = config.isUsEnFirmaApiSimpleServidor();
        nom = "US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB:
        tePermis = config.isUsEnFirmaApiSimpleWeb();
        nom = "US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_FIRMAWEB:
        tePermis = config.isUsEnFirmaWeb();
        nom = "US_FIRMA_CONF_APP_FIRMAWEB";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_FIRMAWS1:
        tePermis = config.isUsEnFirmaWS1();
        nom = "US_FIRMA_CONF_APP_FIRMAWS1";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_FIRMAASYNCSIMPLEREST2:
        tePermis = config.isUsEnFirmaAsyncRest2();
        nom = "US_FIRMA_CONF_APP_FIRMAASYNCSIMPLEREST2";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR:
        tePermis = config.isUsEnFirmaPassarelaServidor();
        nom = "US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR";
      break;
      case ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB:
        tePermis = config.isUsEnFirmaPassarelaWeb();
        nom = "US_FIRMA_CONF_APP_PASSARELAFIRMAWEB";
      break;
      default:
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "Ús de Firma amb codi  " + usFirma
            + " desconegut." + "Consulti amb l'Administrador.");

    }

    if (!tePermis) {

      // XYZ ZZZ TRA Traduir
      throw new I18NException("genapp.comodi", "La configuració de firma " + config.getNom()
          + " enllaçada amb el perfil de firma amb codi " + codiPerfil
          + " i associat a l'usuari aplicació " + usuariAplicacioID
          + " no té permis per ser usat en firmes de tipus " + nom
          + ". Consulti amb l'Administrador.");
    }

    // Check si es firma en servidor que tengui definit el Plugin
    if ((usFirma == ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR)
        || (usFirma == ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR)) {

      Long pluginId = config.getPluginFirmaServidorID();

      if (pluginId == null) {
        // XYZ ZZZ Traduir
        throw new I18NException("genapp.comodi", "La configuració de firma " + config.getNom()
            + " enllaçada amb el perfil de firma amb codi " + codiPerfil
            + " i associat a l'usuari aplicació " + usuariAplicacioID
            + " no té definit el plugin de firma en servidor. "
            + "Consulti amb l'Administrador.");
      }
    }

  }

  @Override
  @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
  public PerfilDeFirma getPerfilDeFirma(final String usuariAplicacioID, String codiPerfil,
      final int usFirma) throws I18NException {

    if (codiPerfil == null || codiPerfil.trim().length() == 0) {
      // XYZ ZZZ TRA Traduir
      throw new I18NException("genapp.comodi",
          "S'ha fet una cridada REST amb l´usuari aplicació " + usuariAplicacioID
              + " però s'ha indicat un perfil de firma null o buit."
              + ". Consulti amb l'Administrador.");
    }

    // Check si codiPerfil existeix
    PerfilDeFirma perfilDeFirma = getPerfilDeFirmaByCodi(codiPerfil);

    // Check si usuariAplicacio té assignat aquest codi perfil
    // PerfilsPerUsuariAplicacio perfil;
    {

      Where w = Where.AND(PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID.equal(perfilDeFirma
          .getUsuariAplicacioPerfilID()), PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID
          .equal(usuariAplicacioID));

      List<PerfilsPerUsuariAplicacio> perfils = perfilsPerUsuariAplicacioEjb.select(w);

      if (perfils == null || perfils.size() == 0) {
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "El codi de perfil " + codiPerfil
            + " no està associat a l'usuari aplicació " + usuariAplicacioID
            + ". Consulti amb l'Administrador.");
      }

      // perfil = perfils.get(0);
    }

    // Cercar si tenim permis per aquell ús o si alguna de les configuracions
    // és firma en servidor que s'hagi definit plugin de firma en servidor

    Long[] configuracions = { perfilDeFirma.getConfiguracioDeFirma1ID(),
        perfilDeFirma.getConfiguracioDeFirma2ID(), perfilDeFirma.getConfiguracioDeFirma3ID(),
        perfilDeFirma.getConfiguracioDeFirma4ID(), perfilDeFirma.getConfiguracioDeFirma5ID() };

    List<Long> configuracionsList = new ArrayList<Long>();
    for (Long cfg : configuracions) {
      if (cfg != null) {
        configuracionsList.add(cfg);
      }
    }
    /*
     * List<UsuariAplicacioConfiguracio> configs; configs =
     * select(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID
     * .in(configuracionsList));
     * 
     * for (UsuariAplicacioConfiguracio config : configs) {
     * 
     * // Check si es té permis checkTePermisPerUsDeFirma(usuariAplicacioID, codiPerfil,
     * usFirma, config);
     * 
     * // Check si es firma en servidor que tengui definit el Plugin if ((usFirma ==
     * ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR) || (usFirma ==
     * ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR)) {
     * 
     * Long pluginId = config.getPluginFirmaServidorID();
     * 
     * if (pluginId == null) { // XYZ ZZZ Traduir throw new I18NException("genapp.comodi",
     * "La configuració de firma " + config.getNom() +
     * " enllaçada amb el perfil de firma amb codi " + codiPerfil +
     * " i associat a l'usuari aplicació " + usuariAplicacioID +
     * " no té definit el plugin de firma en servidor. " + "Consulti amb l'Administrador."); }
     * } }
     */
    return perfilDeFirma;
  }
  
  @Override
  public UsuariAplicacioConfiguracioJPA findByPrimaryKeyUnauthorized(Long _ID_) {
    return super.findByPrimaryKey(_ID_);
  }
  

  protected PerfilDeFirma getPerfilDeFirmaByCodi(String codiPerfil) throws I18NException {
    PerfilDeFirma perfilDeFirma;
    {
      List<PerfilDeFirma> list = perfilDeFirmaEjb.select(PerfilDeFirmaFields.CODI
          .equal(codiPerfil));

      if (list == null || list.size() == 0) {
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "El codi de perfil " + codiPerfil
            + " no existeix. Consulti amb l'Administrador.");
      }

      perfilDeFirma = list.get(0);
    }
    return perfilDeFirma;
  }

  /**
   * Els usuaris aplicacio de passarela NOMES poden tenir UN SOL PERFIL
   * 
   * @param usuariAplicacioID
   * @return
   * @throws I18NException
   */

  @Override
  @PermitAll
  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerApiPortafibWS1(
      final String usuariAplicacioID) throws I18NException {

    Where where = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
        .CONFIGURACIODEFIRMA1().USENFIRMAWS1().equal(true);
    final String us = "api portafib ws 1.0";

    PerfilDeFirma perfilDeFirma = getPerfilDeFirmaUnike(usuariAplicacioID, where, us,
        "perfil.passarela.nomesunperfil", true);

    UsuariAplicacioConfiguracioJPA config = (UsuariAplicacioConfiguracioJPA) findByPrimaryKey(perfilDeFirma
        .getConfiguracioDeFirma1ID());

    return config;

  }

  @Override
  // @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
  @PermitAll
  public PerfilConfiguracionsDeFirma getConfiguracioUsuariAplicacioPerPassarela(
      String usuariAplicacioID, PassarelaSignaturesSet signaturesSet, boolean esFirmaEnServidor)
      throws I18NException {

    PerfilDeFirma perfilDeFirma = getPerfilDeFirmaPerPassarela(usuariAplicacioID,
        esFirmaEnServidor);

    // UsuariAplicacioConfiguracioJPA config =
    // (UsuariAplicacioConfiguracioJPA)findByPrimaryKey(perfilDeFirma
    // .getConfiguracioDeFirma1ID());

    final int usFirma;
    if (esFirmaEnServidor) {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR;
    } else {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB;
    }

    Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String, UsuariAplicacioConfiguracioJPA>();

    final PassarelaCommonInfoSignature passarelaCommonInfoSignature = signaturesSet
        .getCommonInfoSignature();

    final String langUI = signaturesSet.getCommonInfoSignature().getLanguageUI();

    for (PassarelaFileInfoSignature passarelaFileInfoSignature : signaturesSet
        .getFileInfoSignatureArray()) {

      Map<String, Object> parameters = new HashMap<String, Object>();
      parameters.put("passarelaFileInfoSignature", passarelaFileInfoSignature);
      parameters.put("passarelaCommonInfoSignature", passarelaCommonInfoSignature);

      UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID,
          perfilDeFirma, usFirma, langUI, parameters);

      checkTePermisPerUsDeFirma(usuariAplicacioID, perfilDeFirma.getCodi(), usFirma, config);

      configBySignID.put(passarelaFileInfoSignature.getSignID(), config);

    }

    return new PerfilConfiguracionsDeFirma(perfilDeFirma, configBySignID);

  }

  /**
   * 
   * @param usuariAplicacioID
   * @param esFirmaEnServidor
   * @return
   * @throws I18NException
   */
  protected PerfilDeFirma getPerfilDeFirmaPerPassarela(final String usuariAplicacioID,
      final boolean esFirmaEnServidor) throws I18NException {

    final Field<Boolean> usFirmaPassarela;
    if (esFirmaEnServidor) {
      usFirmaPassarela = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
          .CONFIGURACIODEFIRMA1().USENFIRMAPASSARELASERVIDOR();
    } else {
      usFirmaPassarela = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
          .CONFIGURACIODEFIRMA1().USENFIRMAPASSARELAWEB();
    }
    Where where = usFirmaPassarela.equal(true);
    final String nomus = "passarela";

    return getPerfilDeFirmaUnike(usuariAplicacioID, where, nomus,
        "perfil.passarela.nomesunperfil", true);
  }

  /**
   * 
   * @param usuariAplicacioID
   * @param esFirmaEnServidor
   * @return
   * @throws I18NException
   */
  @Override
  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaSimple(final String usuariAplicacioID,
      final boolean esFirmaEnServidor) throws I18NException {

    final Field<Boolean> usFirmaApiFirmaSimple;
    if (esFirmaEnServidor) {
      usFirmaApiFirmaSimple = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
          .CONFIGURACIODEFIRMA1().USENFIRMAAPISIMPLESERVIDOR();
    } else {
      usFirmaApiFirmaSimple = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
          .CONFIGURACIODEFIRMA1().USENFIRMAAPISIMPLEWEB();
    }
    Where where = usFirmaApiFirmaSimple.equal(true);
    final String nomus = "apifirmasimple";

    return getPerfilDeFirmaUnike(usuariAplicacioID, where, nomus,
        "perfil.apifirmasimple.perfilbuit", false);
  }

  /**
   * 
   * @param usuariAplicacioID
   * @param where
   * @param nomus
   * @return
   * @throws I18NException
   */
  protected PerfilDeFirma getPerfilDeFirmaUnike(final String usuariAplicacioID, Where where,
      String nomus, String codiError, boolean onlyOneCondition) throws I18NException {
    // Hauria de retornar 1
    List<String> codisPerfil = perfilsPerUsuariAplicacioEjb
        .executeQuery(new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA().CODI(), Where
            .AND(PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                where));

    if (codisPerfil == null || codisPerfil.size() != 1) {
      throw new I18NException(codiError, usuariAplicacioID, nomus);
    }

    String codiPerfil = codisPerfil.get(0);

    PerfilDeFirma perfilDeFirma = getPerfilDeFirmaByCodi(codiPerfil);

    if (onlyOneCondition) {
      if (perfilDeFirma.getCondicio() != null
          || perfilDeFirma.getConfiguracioDeFirma2ID() != null
          || perfilDeFirma.getConfiguracioDeFirma3ID() != null
          || perfilDeFirma.getConfiguracioDeFirma4ID() != null
          || perfilDeFirma.getConfiguracioDeFirma5ID() != null) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "El perfil amb codi " + codiPerfil
            + " que esta assignat a l´usuari aplicació " + usuariAplicacioID
            + ", que es fa servir per " + nomus
            + ", no pot tenir condició, ni configuració de firma 2,3,4 o 5");
      }
    }

    return perfilDeFirma;
  }

  @Override
  @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerUpgrade(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) throws I18NException {

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("firmaSimpleUpgradeRequest", firmaSimpleUpgradeRequest);
    parameters.put("isUpgrade", true);

    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma,
        ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR,
        firmaSimpleUpgradeRequest.getLanguageUI(), parameters);

    Integer upgradeID = config.getUpgradeSignFormat();

    if (upgradeID == null) {
      // XYZ ZZZ Traduir
      throw new I18NException("genapp.codi", "L´usuari aplicació " + usuariAplicacioID
          + " no té definida configuració d´Extensió de Firma (Perfil de Firma: "
          + perfilDeFirma.getCodi() + ", Configuració de Firma: " + config.getNom() + ")");
    }

    return config;

  }

  /**
   * 
   */
  @Override
  @PermitAll
  public PerfilConfiguracionsDeFirma getConfiguracioFirmaPerApiFirmaSimpleEnServidor(
      String usuariAplicacioID, String codiPerfil,
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException {

    final int usFirma = ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR;

    PerfilDeFirma perfilDeFirma = getPerfilDeFirma(usuariAplicacioID, codiPerfil, usFirma);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("firmaSimpleSignDocumentRequest", firmaSimpleSignDocumentRequest);
    parameters.put("isUpgrade", false);

    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma,
        ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR, firmaSimpleSignDocumentRequest
            .getCommonInfo().getLanguageUI(), parameters);

    Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String, UsuariAplicacioConfiguracioJPA>();

    configBySignID.put(firmaSimpleSignDocumentRequest.getFileInfoSignature().getSignID(),
        config);

    return new PerfilConfiguracionsDeFirma(perfilDeFirma, configBySignID);

  }

  @Override
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaAsyncSimple(
      String usuariAplicacioID, String codiPerfil,
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) throws I18NException {

    final int usFirma = ConstantsV2.US_FIRMA_CONF_APP_FIRMAASYNCSIMPLEREST2;

    PerfilDeFirma perfilDeFirma = getPerfilDeFirma(usuariAplicacioID, codiPerfil, usFirma);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("signatureRequest", signatureRequest);

    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma,
        usFirma, signatureRequest.getLanguageUI(), parameters);

    return config;

  }

  @Override
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaSimpleWeb(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException {

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("firmaSimpleSignDocumentRequest", firmaSimpleSignDocumentRequest);
    parameters.put("isUpgrade", false);

    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma,
        ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB, firmaSimpleSignDocumentRequest
            .getCommonInfo().getLanguageUI(), parameters);

    return config;

  }

  /**
   * 
   * @param perfilDeFirma
   * @param parameters
   * @return
   * @throws I18NException
   */
  protected UsuariAplicacioConfiguracioJPA avaluarCondicio(String usuariAplicacioID,
      PerfilDeFirma perfilDeFirma, int usFirma, String lang, Map<String, Object> parameters)
      throws I18NException {

    parameters.put("usFirma", usFirma);
    parameters.put("lang", lang);

    String condicio = perfilDeFirma.getCondicio();
    Long configID;

    if (condicio == null || condicio.trim().length() == 0) {
      configID = perfilDeFirma.getConfiguracioDeFirma1ID();
    } else {
      String errorOrConfig;
      try {
        errorOrConfig = TemplateEngine.processExpressionLanguage(condicio, parameters);
      } catch (IOException e) {
        // XYZ ZZZ TRAD
        throw new I18NException(e, "genapp.comodi", new I18NArgumentString(
            "Error processant condició del perfil " + perfilDeFirma.getCodi() + ": "
                + e.getMessage()));
      }

      long configPos;
      try {
        configPos = Long.parseLong(errorOrConfig.trim());
      } catch (NumberFormatException e) {
        // Signnifica que la condicio ha retornat un error
        // XYZ ZZZ TRAD
        throw new I18NException("genapp.comodi", new I18NArgumentString(
            "El processat de la condició del perfil " + perfilDeFirma.getCodi()
                + " ha retornat un missatge d´error: " + errorOrConfig));
      }

      if (configPos < 1 || configPos > 5) {
        // XYZ ZZZ TRAD
        throw new I18NException("genapp.comodi", new I18NArgumentString(
            "El processat de la condició del perfil " + perfilDeFirma.getCodi()
                + " ha retornat id de Configuració major que 3 o menor que 1: " + configPos));
      }

      switch ((int) configPos) {

        case 1:
          configID = perfilDeFirma.getConfiguracioDeFirma1ID();
        break;

        case 2:
          configID = perfilDeFirma.getConfiguracioDeFirma2ID();
        break;

        case 3:
          configID = perfilDeFirma.getConfiguracioDeFirma3ID();
        break;

        case 4:
          configID = perfilDeFirma.getConfiguracioDeFirma4ID();
        break;

        case 5:
          configID = perfilDeFirma.getConfiguracioDeFirma5ID();
        break;

        default:
          // XYZ ZZZ TRAD
          throw new I18NException("genapp.comodi", "El processat de la condició del perfil "
              + perfilDeFirma.getCodi()
              + " ha retornat id de Configuració major que 5 o menor que 1: " + configPos);
      }

      if (configID == null) {
        // XYZ ZZZ TRAD
        throw new I18NException("genapp.comodi", "La configuració " + configPos
            + " del perfil " + perfilDeFirma.getCodi() + " no està definida.");
      }
    }

    UsuariAplicacioConfiguracioJPA config = (UsuariAplicacioConfiguracioJPA) findByPrimaryKey(configID);

    checkTePermisPerUsDeFirma(usuariAplicacioID, perfilDeFirma.getCodi(), usFirma, config);

    return config;
  }

  /*
   * @Override //@RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
   * 
   * @PermitAll public PerfilConfiguracionsDeFirma getConfiguracioFirmaPerApiPortaFIBWS1(
   * String usuariAplicacioID) throws I18NException {
   * 
   * final int usFirma = ConstantsV2.US_FIRMA_CONF_APP_FIRMAWS1;
   * 
   * PerfilDeFirma perfilDeFirma = getPerfilDeFirma(usuariAplicacioID, codiPerfil, usFirma);
   * 
   * Map<String, Object> parameters = new HashMap<String, Object>();
   * parameters.put("firmaSimpleSignDocumentRequest", firmaSimpleSignDocumentRequest);
   * 
   * UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma,
   * ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR, firmaSimpleSignDocumentRequest
   * .getCommonInfo().getLanguageUI(), parameters);
   * 
   * Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String,
   * UsuariAplicacioConfiguracioJPA>();
   * 
   * configBySignID.put(firmaSimpleSignDocumentRequest.getFileInfoSignature().getSignID(),
   * config);
   * 
   * return new PerfilConfiguracionsDeFirma(perfilDeFirma, configBySignID);
   * 
   * }
   */

}
