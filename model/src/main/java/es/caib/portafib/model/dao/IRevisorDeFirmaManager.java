package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRevisorDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<RevisorDeFirma, Long> {


	public RevisorDeFirma create( java.lang.String _usuariEntitatID_, long _firmaID_, boolean _obligatori_) throws I18NException;

	public RevisorDeFirma findByPrimaryKey(long _revisorDeFirmaID_);

	public void delete(long _revisorDeFirmaID_);

}
