package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICorreuAgrupatManager extends org.fundaciobit.genapp.common.query.ITableManager<CorreuAgrupat, Long> {


	public CorreuAgrupat create( java.lang.String _email_, java.lang.String _subject_, java.lang.String _message_, boolean _html_, java.lang.String _usuariEntitatID_, java.sql.Timestamp _dataCreacio_, java.lang.String _error_) throws I18NException;

	public CorreuAgrupat findByPrimaryKey(long _correuAgrupatID_);

	public void delete(long _correuAgrupatID_);

}
