package es.caib.portafib.back.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.core.beans.ApisIBAvailableProfile;
import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestFirmaUtils<K extends ApisIBKeyValue> extends RestUtilsErrorManager {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;

  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;


  /**
   * 
   * @param w
   * @return
   * @throws I18NException
   */
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

  


  
  

  
  public ResponseEntity<?> internalGetAvailableProfiles(HttpServletRequest request,
      String locale) {
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {
      

      
     // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
      Where w = null;
      List<PerfilDeFirma> perfils = commonAvailableProfiles(w);
      
      List<ApisIBAvailableProfile<K>> list = new ArrayList<ApisIBAvailableProfile<K>>();

      for (PerfilDeFirma perfil : perfils) {

        String codiPerfil = perfil.getCodi();

        String descripcio = perfil.getDescripcio();

        // Falta llegir-ho de la BBDD
        ApisIBAvailableProfile<K> ap = new ApisIBAvailableProfile<K>(codiPerfil,
            perfil.getNom(), descripcio, null);

        list.add(ap);
      }
      
      

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<List<ApisIBAvailableProfile<K>>>(list, headers,
          HttpStatus.OK);

      return re;

    } catch (Throwable th) {

      // XYZ ZZZ Traduir
      String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }


}
