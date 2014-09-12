package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRoleUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<RoleUsuariEntitat, Long> {


	public RoleUsuariEntitat create( java.lang.String _roleID_, java.lang.String _usuariEntitatID_) throws I18NException;

	public RoleUsuariEntitat findByPrimaryKey(long _id_);

	public void delete(long _id_);

}
