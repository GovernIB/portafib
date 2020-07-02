package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.model.entity.PerfilDeFirma;

import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ConfiguracioUsuariAplicacioLogicaLocal extends
    UsuariAplicacioConfiguracioLocal {

  public static final String JNDI_NAME = "portafib/ConfiguracioUsuariAplicacioLogicaEJB/local";

  public UsuariAplicacioConfiguracioJPA findByPrimaryKeyUnauthorized(Long _ID_);
  
  public PerfilDeFirma getPerfilDeFirma(final String usuariAplicacioID, String codiPerfil) throws I18NException;

  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerUpgrade(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) throws I18NException;
  
  public PerfilConfiguracionsDeFirma getConfiguracioFirmaPerApiFirmaSimpleEnServidor(
      String usuariAplicacioID, String codiPerfil,
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaSimpleWeb(
      String usuariAplicacioID,  PerfilDeFirma codiPerfil, 
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaAsyncSimple(
      String usuariAplicacioID,  String codiPerfil,
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) throws I18NException;
  
  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaSimple(final String usuariAplicacioID,
      final boolean esFirmaEnServidor) throws I18NException;

  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaAsyncRest(final String usuariAplicacioID) throws I18NException;

  public PerfilConfiguracionsDeFirma getConfiguracioUsuariAplicacioPerPassarela(String usuariAplicacioID,
      PassarelaSignaturesSet signaturesSet, boolean esFirmaEnServidor) throws I18NException;

  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerApiPortafibWS1(
      final String usuariAplicacioID) throws I18NException;

  public void deleteFull(Long _ID_) throws I18NException;

}
