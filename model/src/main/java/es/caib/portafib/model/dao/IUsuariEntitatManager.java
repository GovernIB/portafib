package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitat, String> {


	public UsuariEntitat create( java.lang.String _usuariEntitatID_, java.lang.String _carrec_, java.lang.String _usuariPersonaID_, java.lang.String _entitatID_, boolean _actiu_, java.lang.String _email_, java.lang.Long _logoSegellID_, boolean _predeterminat_, boolean _rebreTotsElsAvisos_, java.lang.Boolean _potCustodiar_, java.lang.Integer _politicaCustodia_, int _politicaDePluginFirmaWeb_) throws I18NException;

	public UsuariEntitat findByPrimaryKey(java.lang.String _usuariEntitatID_);

	public void delete(java.lang.String _usuariEntitatID_);

}
