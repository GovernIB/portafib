package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioEJB;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.ConfiguracioApiFirmaAsyncUtils;
import es.caib.portafib.logic.utils.ConfiguracioApiFirmaSimpleUtils;
import es.caib.portafib.logic.utils.ConfiguracioCommonUtils;
import es.caib.portafib.logic.utils.ConfiguracioUsuariAplicacioPassarelaUtils;
import es.caib.portafib.logic.utils.ConfiguracioUsuariAplicacioUpgradeUtils;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioQueryPath;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ConfiguracioUsuariAplicacioLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class ConfiguracioUsuariAplicacioLogicaEJB extends UsuariAplicacioConfiguracioEJB
    implements ConfiguracioUsuariAplicacioLogicaLocal, UsuariAplicacioConfiguracioFields {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;

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
  public PerfilDeFirma getPerfilDeFirma(final String usuariAplicacioID, String codiPerfil) throws I18NException {

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
    }

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

    final int usFirma;
    if (esFirmaEnServidor) {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR;
    } else {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB;
    }

    Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String, UsuariAplicacioConfiguracioJPA>();

    final PassarelaCommonInfoSignature passarelaCommonInfoSignature = signaturesSet
            .getCommonInfoSignature();

    for (PassarelaFileInfoSignature passarelaFileInfoSignature : signaturesSet
            .getFileInfoSignatureArray()) {

      ConfiguracioUsuariAplicacioPassarelaUtils cfgUtils = new ConfiguracioUsuariAplicacioPassarelaUtils(
              passarelaCommonInfoSignature, passarelaFileInfoSignature, usFirma);

      UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma, cfgUtils);
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
    System.out.println(usuariAplicacioID + esFirmaEnServidor);

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

  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaAsyncRest(final String usuariAplicacioID) throws I18NException {

    final Field<Boolean> usFirmaApiAsync = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA()
              .CONFIGURACIODEFIRMA1().USENFIRMAASYNCREST2();

    Where where = usFirmaApiAsync.equal(true);
    final String nomus = "apifirmaasyncrest";

    return getPerfilDeFirmaUnike(usuariAplicacioID, where, nomus,
            "perfil.apifirmaasyncrest.perfilbuit", false);
  }

  protected PerfilDeFirma getPerfilDeFirmaUnike(final String usuariAplicacioID, Where where,
      String nomus, String codiError, boolean onlyOneCondition) throws I18NException {
    // Hauria de retornar 1
    List<String> codisPerfil = perfilsPerUsuariAplicacioEjb
        .executeQuery(new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA().CODI(), Where
            .AND(PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
                where));

    
    log.debug("codisPerfil == " + codisPerfil);
    if (codisPerfil != null) {
      log.debug("codisPerfil.size() == " + codisPerfil.size()); 
    }
    
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

    boolean isUpgrade = true;
    int usFirma = ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR;
    ConfiguracioUsuariAplicacioUpgradeUtils cfgUtils = new ConfiguracioUsuariAplicacioUpgradeUtils(
            isUpgrade, firmaSimpleUpgradeRequest, usFirma);
    
    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma, cfgUtils);
    
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

    PerfilDeFirma perfilDeFirma = getPerfilDeFirma(usuariAplicacioID, codiPerfil);

    boolean isUpgrade = false;
    int usFirma = ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR;
    ConfiguracioApiFirmaSimpleUtils cfgUtils = new ConfiguracioApiFirmaSimpleUtils(
            isUpgrade, firmaSimpleSignDocumentRequest, usFirma);
    
    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma, cfgUtils);
    
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

    PerfilDeFirma perfilDeFirma = getPerfilDeFirma(usuariAplicacioID, codiPerfil);

    ConfiguracioApiFirmaAsyncUtils cfgUtils = new ConfiguracioApiFirmaAsyncUtils(signatureRequest, usFirma);
    
    UsuariAplicacioConfiguracioJPA config = avaluarCondicio(usuariAplicacioID, perfilDeFirma, cfgUtils);  

    return config;
  }

  @Override
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaSimpleWeb(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException {

    boolean isUpgrade = false;
    int usFirma = ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB;
      
    ConfiguracioApiFirmaSimpleUtils cfgUtils = new ConfiguracioApiFirmaSimpleUtils(
            isUpgrade, firmaSimpleSignDocumentRequest, usFirma);

    return avaluarCondicio(usuariAplicacioID, perfilDeFirma, cfgUtils);
  }
   
    protected UsuariAplicacioConfiguracioJPA avaluarCondicio(String usuariAplicacioID,
            PerfilDeFirma perfilDeFirma, ConfiguracioCommonUtils configuracioFirmaUtils)
            throws I18NException {

        String condicio = perfilDeFirma.getCondicio();
        Long configID;

        if (condicio == null || condicio.trim().length() == 0) {
            configID = perfilDeFirma.getConfiguracioDeFirma1ID();
        } else {
            String errorOrConfig;
            try {
                errorOrConfig = TemplateEngine.processExpressionLanguage(condicio, configuracioFirmaUtils.getParameters());
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

        UsuariAplicacioConfiguracioJPA config = findByPrimaryKey(configID);
        checkTePermisPerUsDeFirma(usuariAplicacioID, perfilDeFirma.getCodi(), configuracioFirmaUtils.getUsFirma(), config);

        return config;
    }


  @Override
  public void deleteFull(Long _ID_) throws I18NException {

    // Es podran esborrar les traduccions quan només en quedi una referència
    UsuariAplicacioConfiguracio config = this.findByPrimaryKey(_ID_);

    if (config == null) {
      return;
    }

    Long motiuID = config.getMotiuDelegacioID();
    Long countM = null;
    if (motiuID != null) {
      countM = this.count(MOTIUDELEGACIOID.equal(motiuID));
    }
    
    Long countF = null;
    Long firmatPerID= config.getFirmatPerFormatID();
    if (firmatPerID != null) {
      countF = this.count(FIRMATPERFORMATID.equal(firmatPerID));
    }
    
    // El delete esborra automàticament les traduccions, si s'utilitzen en altres llocs llavors no s'han d'esborrar
    boolean update = false;
    if (countM != null && countM != 1) {
      config.setMotiuDelegacioID(null);
      update = true;
    }
    
    if (countF != null && countF != 1) {
      config.setFirmatPerFormatID(null);
      update = true;
    }
    
    if (update == true) {
      this.update(config);
    }
    

    this.delete(config);

  }
}
