package es.caib.portafib.logic.passarela;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
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
   * @param signaturesSet
   * @param entitatID
   * @param usrApp
   * @return resultats o errors segons com hagi finalitzat
   */
  public PassarelaFullResults signDocuments(
      PassarelaSignaturesSet signaturesSet, EntitatJPA entitat, 
      UsuariAplicacioJPA usrApp, UsuariAplicacioConfiguracio config) throws NoCompatibleSignaturePluginException;
  
  
  /**
   * 
   * @param signature
   * @param signTypeForm
   * @return
   */
  public byte[] upgradeSignature(byte[] signature, SignatureTypeFormEnumForUpgrade signTypeForm,     
      UsuariAplicacioJPA usrApp, UsuariAplicacioConfiguracio config)
          throws NoCompatibleSignaturePluginException, I18NException, Exception;

}
