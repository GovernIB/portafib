package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusEstatDeFirmaFinalManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusEstatDeFirmaFinal, Long> {


	public TipusEstatDeFirmaFinal create( long _tipusEstatDeFirmaFinalID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public TipusEstatDeFirmaFinal findByPrimaryKey(long _tipusEstatDeFirmaFinalID_);

	public void delete(long _tipusEstatDeFirmaFinalID_);

}
