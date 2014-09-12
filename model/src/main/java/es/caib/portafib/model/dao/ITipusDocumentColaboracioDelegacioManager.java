package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusDocumentColaboracioDelegacioManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusDocumentColaboracioDelegacio, Long> {


	public TipusDocumentColaboracioDelegacio create( long _colaboracioDelegacioID_, long _tipusDocumentID_) throws I18NException;

	public TipusDocumentColaboracioDelegacio findByPrimaryKey(long _id_);

	public void delete(long _id_);

}
