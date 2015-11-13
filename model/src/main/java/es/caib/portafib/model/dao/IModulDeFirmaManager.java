package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IModulDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<ModulDeFirma, Long> {


	public ModulDeFirma create( long _nomID_, long _descripcioCurtaID_, java.lang.String _classe_, java.lang.String _propertiesAdmin_, java.lang.String _propertiesEntitat_, java.lang.String _entitatID_, boolean _actiu_) throws I18NException;

	public ModulDeFirma findByPrimaryKey(long _modulDeFirmaID_);

	public void delete(long _modulDeFirmaID_);

}
