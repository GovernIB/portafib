package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IMetadadaManager extends org.fundaciobit.genapp.common.query.ITableManager<Metadada, Long> {


	public Metadada create( java.lang.String _nom_, java.lang.String _valor_, java.lang.String _descripcio_, long _peticioDeFirmaID_, int _tipusMetadadaID_) throws I18NException;

	public Metadada findByPrimaryKey(long _metadadaID_);

	public void delete(long _metadadaID_);

}
