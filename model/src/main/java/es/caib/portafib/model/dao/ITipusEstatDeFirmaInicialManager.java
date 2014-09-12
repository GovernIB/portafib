package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusEstatDeFirmaInicialManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusEstatDeFirmaInicial, Long> {


	public TipusEstatDeFirmaInicial create( long _tipusEstatDeFirmaInicialID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public TipusEstatDeFirmaInicial findByPrimaryKey(long _tipusEstatDeFirmaInicialID_);

	public void delete(long _tipusEstatDeFirmaInicialID_);

}
