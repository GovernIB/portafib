package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRoleManager extends org.fundaciobit.genapp.common.query.ITableManager<Role, String> {


	public Role create( java.lang.String _roleID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public Role findByPrimaryKey(java.lang.String _roleID_);

	public void delete(java.lang.String _roleID_);

}
