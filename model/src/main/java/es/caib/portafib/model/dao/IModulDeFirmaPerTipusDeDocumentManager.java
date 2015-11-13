package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IModulDeFirmaPerTipusDeDocumentManager extends org.fundaciobit.genapp.common.query.ITableManager<ModulDeFirmaPerTipusDeDocument, Long> {


	public ModulDeFirmaPerTipusDeDocument create( long _tipusDocumentID_, long _modulDeFirmaID_, java.lang.String _nom_) throws I18NException;

	public ModulDeFirmaPerTipusDeDocument findByPrimaryKey(long _ID_);

	public void delete(long _ID_);

}