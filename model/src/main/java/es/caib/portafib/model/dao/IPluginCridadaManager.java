package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginCridadaManager extends org.fundaciobit.genapp.common.query.ITableManager<PluginCridada, Long> {


	public PluginCridada create( java.lang.String _entitatID_, java.sql.Timestamp _data_, int _tipusPlugin_, java.lang.String _dadesPlugin_, java.lang.String _metodePlugin_, java.lang.String _dadesCridada_, int _tipusTesultat_, java.lang.String _resultat_, long _tempsExecucio_) throws I18NException;

	public PluginCridada findByPrimaryKey(long _pluginCridadaID_);

	public void delete(long _pluginCridadaID_);

}
