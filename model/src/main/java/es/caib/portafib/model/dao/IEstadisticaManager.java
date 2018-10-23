package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstadisticaManager extends org.fundaciobit.genapp.common.query.ITableManager<Estadistica, Long> {


	public Estadistica create( java.sql.Timestamp _data_, int _tipus_, java.lang.String _entitatID_, java.lang.Double _valor_, java.lang.String _usuariAplicacioID_, java.lang.String _usuariEntitatID_, java.lang.String _parametres_) throws I18NException;

	public Estadistica findByPrimaryKey(long _estadisticaID_);

	public void delete(long _estadisticaID_);

}
