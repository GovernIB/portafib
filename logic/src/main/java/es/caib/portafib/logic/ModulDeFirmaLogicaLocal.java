package es.caib.portafib.logic;


import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

import es.caib.portafib.ejb.ModulDeFirmaLocal;
import es.caib.portafib.model.entity.ModulDeFirma;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaLogicaLocal extends  ModulDeFirmaLocal {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaLogicaEJB/local";

  public ISignatureWebPlugin getSignatureWebPluginByModulDeFirmaID(long signatureWebPluginID) throws I18NException;
  
  public List<ModulDeFirma> getAllModulDeFirma(String entitatID) throws I18NException;
  
  public List<ISignatureWebPlugin> getSignatureWebPluginsByEntitatID(String entity) throws I18NException;

}
