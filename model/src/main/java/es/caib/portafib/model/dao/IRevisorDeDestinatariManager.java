package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRevisorDeDestinatariManager extends org.fundaciobit.genapp.common.query.ITableManager<RevisorDeDestinatari, Long> {


	public RevisorDeDestinatari create( java.lang.String _destinatariID_, java.lang.String _revisorID_) throws I18NException;

	public RevisorDeDestinatari findByPrimaryKey(long _revisorDeDestinatariID_);

	public void delete(long _revisorDeDestinatariID_);

}
