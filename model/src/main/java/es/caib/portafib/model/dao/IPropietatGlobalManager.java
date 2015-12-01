package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPropietatGlobalManager extends org.fundaciobit.genapp.common.query.ITableManager<PropietatGlobal, Long> {


	public PropietatGlobal create( java.lang.String _clau_, java.lang.String _valor_, java.lang.String _entitatID_, java.lang.String _descripcio_) throws I18NException;

	public PropietatGlobal findByPrimaryKey(long _propietatGlobalID_);

	public void delete(long _propietatGlobalID_);

}
