package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAnnexManager extends org.fundaciobit.genapp.common.query.ITableManager<Annex, Long> {


	public Annex create( long _peticioDeFirmaID_, long _fitxerID_, boolean _adjuntar_, boolean _firmar_) throws I18NException;

	public Annex findByPrimaryKey(long _annexID_);

	public void delete(long _annexID_);

}
