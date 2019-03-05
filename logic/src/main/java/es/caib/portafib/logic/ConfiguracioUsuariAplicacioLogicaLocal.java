package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;

import javax.ejb.Local;

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


  
  public PerfilDeFirma getPerfilDeFirma(final String usuariAplicacioID, String codiPerfil,
      final int usFirma) throws I18NException;


  public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacioPerUpgrade(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracio getConfiguracioFirmaPerApiFirmaSimpleEnServidor(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma, 
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracio getConfiguracioFirmaPerApiFirmaSimpleWeb(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma, 
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacioPerPassarela(
      final String usuariAplicacioID, final boolean esFirmaEnServidor) throws I18NException;


}
