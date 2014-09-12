package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusMetadadaManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusMetadada, Integer> {


	public TipusMetadada create( int _tipusMetadadaID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public TipusMetadada findByPrimaryKey(int _tipusMetadadaID_);

	public void delete(int _tipusMetadadaID_);

}
