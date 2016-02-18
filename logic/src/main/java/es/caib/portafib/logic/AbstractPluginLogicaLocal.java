package es.caib.portafib.logic;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.model.entity.Plugin;

/**
 * 
 * @author anadal
 *
 */
public interface AbstractPluginLogicaLocal<I> extends PluginLogicaLocal {

  public List<Plugin> getAllPlugins(String entitatID) throws I18NException;

  public I getInstanceByPluginID(long pluginID) throws I18NException;

  public List<I> getPluginInstancesByEntitatID(String entitatID) throws I18NException;

}