package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginFirmaWebPerUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<PluginFirmaWebPerUsuariAplicacio, Long> {


	public PluginFirmaWebPerUsuariAplicacio create( java.lang.String _usuariAplicacioID_, long _pluginFirmaWebID_, int _accio_) throws I18NException;

	public PluginFirmaWebPerUsuariAplicacio findByPrimaryKey(long _pluginfirmawebperusrappid_);

	public void delete(long _pluginfirmawebperusrappid_);

}
