package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IBitacolaManager extends org.fundaciobit.genapp.common.query.ITableManager<Bitacola, Long> {


	public Bitacola create( java.sql.Timestamp _data_, java.lang.String _descripcio_, long _peticioDeFirmaID_, java.lang.String _usuariEntitatID_) throws I18NException;

	public Bitacola findByPrimaryKey(long _bitacolaID_);

	public void delete(long _bitacolaID_);

}
