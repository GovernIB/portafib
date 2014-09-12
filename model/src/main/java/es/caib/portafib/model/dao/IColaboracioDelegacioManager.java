package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IColaboracioDelegacioManager extends org.fundaciobit.genapp.common.query.ITableManager<ColaboracioDelegacio, Long> {


	public ColaboracioDelegacio create( java.lang.String _destinatariID_, java.lang.String _colaboradorDelegatID_, boolean _esDelegat_, java.lang.String _motiu_, java.lang.String _descripcio_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, boolean _activa_, boolean _revisor_, java.lang.String _motiuDeshabilitada_, java.lang.Long _fitxerAutoritzacioID_) throws I18NException;

	public ColaboracioDelegacio findByPrimaryKey(long _colaboracioDelegacioID_);

	public void delete(long _colaboracioDelegacioID_);

}
