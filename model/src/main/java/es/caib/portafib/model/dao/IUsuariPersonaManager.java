package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariPersonaManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariPersona, String> {


	public UsuariPersona create( java.lang.String _usuariPersonaID_, java.lang.String _nom_, java.lang.String _llinatges_, java.lang.String _email_, java.lang.String _nif_, java.lang.String _idiomaID_, java.lang.Long _rubricaID_) throws I18NException;

	public UsuariPersona findByPrimaryKey(java.lang.String _usuariPersonaID_);

	public void delete(java.lang.String _usuariPersonaID_);

}
