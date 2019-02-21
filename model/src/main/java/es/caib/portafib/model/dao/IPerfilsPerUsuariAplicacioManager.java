package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPerfilsPerUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<PerfilsPerUsuariAplicacio, Long> {


	public PerfilsPerUsuariAplicacio create( long _perfilDeFirmaID_, java.lang.String _usuariAplicacioID_) throws I18NException;

	public PerfilsPerUsuariAplicacio findByPrimaryKey(long _perfilsPerUsrAppID_);

	public void delete(long _perfilsPerUsrAppID_);

}
