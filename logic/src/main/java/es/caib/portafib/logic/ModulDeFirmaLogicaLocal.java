package es.caib.portafib.logic;


import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;

import es.caib.portafib.logic.utils.ModulDeFirmaJPA;
/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModulDeFirmaLogicaLocal /* XYZ extends MetadadaLocal */ {

  public static final String JNDI_NAME = "portafib/ModulDeFirmaLogicaEJB/local";

  public ISignatureWebPlugin getSignatureWebPluginByID(long signatureWebPluginID) throws I18NException;
  
  public List<ModulDeFirmaJPA> getAllModulDeFirma(String entitat) throws I18NException;
  
  public List<ISignatureWebPlugin> getSignatureWebPluginsByEntity(String entity) throws I18NException;

}
