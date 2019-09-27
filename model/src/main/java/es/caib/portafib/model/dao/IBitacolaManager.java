package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IBitacolaManager extends org.fundaciobit.genapp.common.query.ITableManager<Bitacola, Long> {


	public Bitacola create( java.lang.String _entitatid_, java.lang.String _usuariid_, java.sql.Timestamp _data_, int _tipusObjecte_, java.lang.String _objecteid_, int _tipusOperacio_, java.lang.String _descripcio_, java.lang.String _objecteSerialitzat_) throws I18NException;

	public Bitacola findByPrimaryKey(long _bitacolaID_);

	public void delete(long _bitacolaID_);

}
