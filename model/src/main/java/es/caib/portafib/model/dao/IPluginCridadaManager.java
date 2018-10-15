package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginCridadaManager extends org.fundaciobit.genapp.common.query.ITableManager<PluginCridada, Long> {


	public PluginCridada create( java.lang.String _entitatID_, java.sql.Timestamp _data_, long _pluginID_, java.lang.String _metodePlugin_, java.lang.String _parametresText_, java.lang.Long _parametresFitxerID_, java.lang.String _retornText_, java.lang.Long _retornFitxerID_, int _tipusTesultat_, long _tempsExecucio_) throws I18NException;

	public PluginCridada findByPrimaryKey(long _pluginCridadaID_);

	public void delete(long _pluginCridadaID_);

}
