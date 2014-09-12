package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFluxDeFirmesManager extends org.fundaciobit.genapp.common.query.ITableManager<FluxDeFirmes, Long> {


	public FluxDeFirmes create( java.lang.String _nom_) throws I18NException;

	public FluxDeFirmes findByPrimaryKey(long _fluxDeFirmesID_);

	public void delete(long _fluxDeFirmesID_);

}
