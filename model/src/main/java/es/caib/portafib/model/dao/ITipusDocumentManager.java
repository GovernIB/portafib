package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusDocumentManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusDocument, Long> {


	public TipusDocument create( long _tipusDocumentID_, long _nomID_, long _tipusDocumentBaseID_, java.lang.String _descripcio_, java.lang.String _usuariAplicacioID_) throws I18NException;

	public TipusDocument findByPrimaryKey(long _tipusDocumentID_);

	public void delete(long _tipusDocumentID_);

}
