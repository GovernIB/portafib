package es.caib.portafib.logic.passarela;

import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

/**
 * 
 * @author anadal
 */
@Local
public interface PassarelaDeFirmaEnServidorLocal extends AbstractPassarelaDeFirmaLocal {

  public static final String JNDI_NAME = "portafib/PassarelaDeFirmaEnServidorEJB/local";

  /**
   * NOTA: Qualsevol error s'ha de retornar en ApiDeFirmaServidorResults
   * 
   * @param signaturesSet
   * @param entitatID
   * @param usrApp
   * @return resultats o errors segons com hagi finalitzat
   */
  public PassarelaFullResults signDocuments(PassarelaSignaturesSet signaturesSet,
      EntitatJPA entitat, UsuariAplicacioJPA usrApp, 
      PerfilDeFirma perfilDeFirma, UsuariAplicacioConfiguracioJPA config)
         throws NoCompatibleSignaturePluginException;

  /**
   * 
   * @param signature
   * @param signTypeForm
   * @return
   */
  public byte[] upgradeSignature(FirmaSimpleFile signature,FirmaSimpleFile targetCertificate,
      SignatureTypeFormEnumForUpgrade signTypeForm, UsuariAplicacioJPA usrApp,
      PerfilDeFirma perfil, UsuariAplicacioConfiguracio config, EntitatJPA entitat)
          throws NoCompatibleSignaturePluginException, I18NException, Exception;

}
