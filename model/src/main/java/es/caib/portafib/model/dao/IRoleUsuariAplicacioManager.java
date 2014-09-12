package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRoleUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<RoleUsuariAplicacio, Long> {


	public RoleUsuariAplicacio create( java.lang.String _roleID_, java.lang.String _usuariAplicacioID_) throws I18NException;

	public RoleUsuariAplicacio findByPrimaryKey(long _id_);

	public void delete(long _id_);

}
