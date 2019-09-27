package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface INotificacioWSManager extends org.fundaciobit.genapp.common.query.ITableManager<NotificacioWS, Long> {


	public NotificacioWS create( long _peticioDeFirmaID_, long _tipusNotificacioID_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataEnviament_, java.lang.String _descripcio_, boolean _bloquejada_, java.lang.String _error_, java.sql.Timestamp _dataError_, int _reintents_, java.lang.String _usuariAplicacioID_) throws I18NException;

	public NotificacioWS findByPrimaryKey(long _notificacioID_);

	public void delete(long _notificacioID_);

}
