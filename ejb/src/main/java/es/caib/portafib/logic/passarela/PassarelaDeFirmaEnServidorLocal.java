package es.caib.portafib.logic.passarela;

import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

/**
 * 
 * @author anadal
 */
@Local
public interface PassarelaDeFirmaEnServidorLocal extends AbstractPassarelaDeFirmaLocal {

  String JNDI_NAME = "java:app/portafib-ejb/PassarelaDeFirmaEnServidorEJB";

  PassarelaSignatureInServerResults signDocuments(PassarelaSignaturesSet signaturesSet,
      EntitatJPA entitat, UsuariAplicacioJPA usrApp, 
      PerfilDeFirma perfilDeFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
         throws NoCompatibleSignaturePluginException;

  UpgradeResponse upgradeSignature(FirmaSimpleFile signature, FirmaSimpleFile detachedDocument,
      FirmaSimpleFile targetCertificate, SignatureTypeFormEnumForUpgrade signTypeForm,
      UsuariAplicacioJPA usrApp, PerfilDeFirma perfil, UsuariAplicacioConfiguracio config, 
      EntitatJPA entitat, String languageUI) throws NoCompatibleSignaturePluginException, I18NException;

}
