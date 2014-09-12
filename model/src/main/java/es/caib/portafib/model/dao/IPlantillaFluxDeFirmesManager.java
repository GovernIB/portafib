package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPlantillaFluxDeFirmesManager extends org.fundaciobit.genapp.common.query.ITableManager<PlantillaFluxDeFirmes, Long> {


	public PlantillaFluxDeFirmes create( long _fluxDeFirmesID_, java.lang.String _descripcio_, java.lang.String _usuariEntitatID_, java.lang.String _usuariAplicacioID_, java.lang.Boolean _compartir_) throws I18NException;

	public PlantillaFluxDeFirmes findByPrimaryKey(long _fluxDeFirmesID_);

	public void delete(long _fluxDeFirmesID_);

}
