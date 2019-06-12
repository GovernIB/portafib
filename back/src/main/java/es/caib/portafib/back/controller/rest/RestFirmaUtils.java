package es.caib.portafib.back.controller.rest;

import java.util.List;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestFirmaUtils extends RestUtils {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;
  
  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

  /**
   *
   * @param esFirmaEnServidor
   * @return
   * @throws I18NException
   */
  protected LoginInfo commonChecks() throws I18NException {

    LoginInfo loginInfo = LoginInfo.getInstance();

    log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

    // Checks Globals
    if (loginInfo.getUsuariEntitat() != null) {
      // TODO XYZ ZZZ Traduir
      throw new I18NException("genapp.comodi",
          "Aquest servei només el poden fer servir els Usuaris Aplicació");
    }

    return loginInfo;
  }

  /** XYZ ZZZ ZZZ MOure a codi Comu **/
  protected List<PerfilDeFirma> commonAvailableProfiles(Where w) throws I18NException {

    LoginInfo loginInfo = commonChecks();

    String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();

    log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

    List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
        PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
        PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

    List<PerfilDeFirma> perfils = perfilDeFirmaEjb
        .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

    return perfils;
  }

  /**
   * 
   * @param usuariAplicacioID
   * @param config
   * @param entitatJPA
   * @return
   * @throws I18NException
   */
  protected boolean getUseTimestampOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws I18NException {
    final boolean useTimeStamp;
    {
      int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

      if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
        politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
      }

      switch (politicaSegellatDeTemps) {
        case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
          useTimeStamp = false;
        break;

        case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
          useTimeStamp = true;
        break;

        case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
          useTimeStamp = true;
        break;
        case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
          useTimeStamp = false;
        break;

        default:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi",
              "Politica de segellat de temps desconeguda (" + politicaSegellatDeTemps
                  + ") en usuari aplicació " + usuariAplicacioID);
      }
    }
    return useTimeStamp;
  }
  
  
  protected String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA) throws I18NException {
    int signAlgorithmID = getAlgorismeDeFirmaIDOfConfig(config, entitatJPA);

    // ALGORISME DE FIRMA
    String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
    log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);
    return signAlgorithm;
  }

  protected int getAlgorismeDeFirmaIDOfConfig(final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA) {
    Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
    if (signAlgorithmID == null) {
      // Si val null cercar-ho a les DADES DE l'ENTITAT
      signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
    }

    log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
    return signAlgorithmID;
  }
  
  
  public static int getSignaturesTableLocationOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws I18NException {
    final int signaturesTableLocation; // =
                                       // FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
      int politicaTaulaDeFirmes = config.getPoliticaTaulaFirmes();
      boolean obtenirDeEntitat = false;
      if (politicaTaulaDeFirmes == ConstantsPortaFIB.POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT) {
        politicaTaulaDeFirmes = entitatJPA.getPoliticaTaulaFirmes();
        obtenirDeEntitat = true;
      }

      switch (politicaTaulaDeFirmes) {
      // 0 no es permet taules de firmes

        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET:
          signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;

        // 1 obligatori politica definida en la configuració d'usuari aplicació o entitat
        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT:
          if (obtenirDeEntitat) {
            signaturesTableLocation = entitatJPA.getPosicioTaulaFirmes();
          } else {
            signaturesTableLocation = config.getPosicioTaulaFirmesID();
          }
        break;

        // 2 opcional, per defecte el definit a l'entitat o conf. de usuari aplicacio
        case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF:
          // XYZ ZZZ Que faig: sense taula de firmes o llançar una excepció indicant
          // que aquest valor no es vàlid per API Firma Simple ??
          signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;

        default:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi","Politica de Taules de Firmes desconeguda ("
              + politicaTaulaDeFirmes + ") en usuari aplicació " + usuariAplicacioID);
      }

    } else {
      // XADES, CADES, ...
      signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    }
    return signaturesTableLocation;
  }

  
  protected PassarelaPolicyInfoSignature getPoliticaFirmaOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws I18NException {
    final PassarelaPolicyInfoSignature policyInfoSignature;
    {
      int usPoliticaDeFirma = config.getUsPoliticaDeFirma();
      boolean obtenirDeEntitat = false;
      if (usPoliticaDeFirma == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT) {
        usPoliticaDeFirma = entitatJPA.getUsPoliticaDeFirma();
        obtenirDeEntitat = true;
      }

      switch (usPoliticaDeFirma) {

      // 0 => no usar politica de firma,
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_NO_USAR:
          policyInfoSignature = null;
        break;

        // 1=> usar politica d'aquesta configuracio
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT:
          if (obtenirDeEntitat) {
            policyInfoSignature = new PassarelaPolicyInfoSignature(
                entitatJPA.getPolicyIdentifier(), entitatJPA.getPolicyIdentifierHash(),
                entitatJPA.getPolicyIdentifierHashAlgorithm(),
                entitatJPA.getPolicyUrlDocument());
          } else {
            policyInfoSignature = new PassarelaPolicyInfoSignature(
                config.getPolicyIdentifier(), config.getPolicyIdentifierHash(),
                config.getPolicyIdentifierHashAlgorithm(), config.getPolicyUrlDocument());
          }
        break;

        // 2 => L'usuari web o usuari-app elegeixen la politica de firma
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OPCIONAL:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi","Ús de Politica de Firma no suportada en API Firma Simple ("
              + usPoliticaDeFirma + ") en usuari aplicació " + usuariAplicacioID);

        default:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi","Ús de Politica de Firma desconeguda (" + usPoliticaDeFirma
              + ") en usuari aplicació " + usuariAplicacioID);

      }
    }
    if (policyInfoSignature == null) {
      log.info("No usam politica de firma");
    } else {
      log.info("Usam politica de firma: " + policyInfoSignature.getPolicyIdentifier() + "("
          + policyInfoSignature.getPolicyUrlDocument() + ")");
    }
    return policyInfoSignature;
  }


}
