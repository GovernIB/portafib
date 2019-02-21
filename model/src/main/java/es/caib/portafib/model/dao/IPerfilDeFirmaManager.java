package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPerfilDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<PerfilDeFirma, Long> {


	public PerfilDeFirma create( java.lang.String _nom_, java.lang.String _codi_, java.lang.String _descripcio_, java.lang.String _condicio_, long _configuracioDeFirma1ID_, java.lang.Long _configuracioDeFirma2ID_, java.lang.Long _configuracioDeFirma3ID_) throws I18NException;

	public PerfilDeFirma findByPrimaryKey(long _usuariAplicacioPerfilID_);

	public void delete(long _usuariAplicacioPerfilID_);

}
