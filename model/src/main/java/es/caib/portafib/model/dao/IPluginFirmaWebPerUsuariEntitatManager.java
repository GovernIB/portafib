package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginFirmaWebPerUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<PluginFirmaWebPerUsuariEntitat, Long> {


	public PluginFirmaWebPerUsuariEntitat create( java.lang.String _usuariEntitatID_, long _pluginFirmaWebID_, int _accio_) throws I18NException;

	public PluginFirmaWebPerUsuariEntitat findByPrimaryKey(long _pluginFirmaWebPerUsrEntID_);

	public void delete(long _pluginFirmaWebPerUsrEntID_);

}
