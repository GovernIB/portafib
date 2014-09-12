package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPermisUsuariPlantillaManager extends org.fundaciobit.genapp.common.query.ITableManager<PermisUsuariPlantilla, Long> {


	public PermisUsuariPlantilla create( java.lang.String _usuariEntitatID_, long _plantillaFluxDeFirmesID_) throws I18NException;

	public PermisUsuariPlantilla findByPrimaryKey(long _permisUsuariPlantillaID_);

	public void delete(long _permisUsuariPlantillaID_);

}
