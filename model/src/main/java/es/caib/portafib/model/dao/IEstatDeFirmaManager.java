package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstatDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<EstatDeFirma, Long> {


	public EstatDeFirma create( long _firmaID_, java.lang.String _usuariEntitatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, long _tipusEstatDeFirmaInicialID_, java.lang.Long _tipusEstatDeFirmaFinalID_, java.lang.Long _colaboracioDelegacioID_, java.lang.String _descripcio_) throws I18NException;

	public EstatDeFirma findByPrimaryKey(long _estatDeFirmaID_);

	public void delete(long _estatDeFirmaID_);

}
