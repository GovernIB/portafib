package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusNotificacioManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusNotificacio, Long> {


	public TipusNotificacio create( long _tipusNotificacioID_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Boolean _esAvis_) throws I18NException;

	public TipusNotificacio findByPrimaryKey(long _tipusNotificacioID_);

	public void delete(long _tipusNotificacioID_);

}
