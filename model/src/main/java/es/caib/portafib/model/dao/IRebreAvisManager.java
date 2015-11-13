package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRebreAvisManager extends org.fundaciobit.genapp.common.query.ITableManager<RebreAvis, Long> {


	public RebreAvis create( java.lang.String _usuariEntitatID_, long _tipusNotificacioID_, boolean _rebreAgrupat_) throws I18NException;

	public RebreAvis findByPrimaryKey(long _id_);

	public void delete(long _id_);

}
