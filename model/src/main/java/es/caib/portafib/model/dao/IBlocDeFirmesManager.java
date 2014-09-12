package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IBlocDeFirmesManager extends org.fundaciobit.genapp.common.query.ITableManager<BlocDeFirmes, Long> {


	public BlocDeFirmes create( int _ordre_, java.sql.Timestamp _dataFinalitzacio_, long _fluxDeFirmesID_, int _minimDeFirmes_) throws I18NException;

	public BlocDeFirmes findByPrimaryKey(long _blocDeFirmesID_);

	public void delete(long _blocDeFirmesID_);

}
