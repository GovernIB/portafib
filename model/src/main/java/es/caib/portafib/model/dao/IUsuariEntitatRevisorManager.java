package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatRevisorManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitatRevisor, Long> {


	public UsuariEntitatRevisor create( java.lang.String _usuariEntitatID_, boolean _actiu_) throws I18NException;

	public UsuariEntitatRevisor findByPrimaryKey(long _usuariEntitatRevisorId_);

	public void delete(long _usuariEntitatRevisorId_);

}
