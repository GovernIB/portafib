package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPermisGrupPlantillaManager extends org.fundaciobit.genapp.common.query.ITableManager<PermisGrupPlantilla, Long> {


	public PermisGrupPlantilla create( long _grupEntitatID_, long _plantillaFluxDeFirmesID_) throws I18NException;

	public PermisGrupPlantilla findByPrimaryKey(long _permisGrupPlantillaID_);

	public void delete(long _permisGrupPlantillaID_);

}
