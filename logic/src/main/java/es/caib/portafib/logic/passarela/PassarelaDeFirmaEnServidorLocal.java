package es.caib.portafib.logic.passarela;

import javax.ejb.Local;

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
  

}
