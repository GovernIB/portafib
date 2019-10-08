package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariAplicacio, String> {


	public UsuariAplicacio create( java.lang.String _usuariAplicacioID_, java.lang.String _entitatID_, java.lang.String _emailAdmin_, int _callbackVersio_, java.lang.String _callbackURL_, boolean _actiu_, java.lang.String _idiomaID_, java.lang.String _descripcio_, java.lang.Long _logoSegellID_, int _politicaDePluginFirmaWeb_, int _politicaCustodia_, java.lang.Long _custodiaInfoID_) throws I18NException;

	public UsuariAplicacio findByPrimaryKey(java.lang.String _usuariAplicacioID_);

	public void delete(java.lang.String _usuariAplicacioID_);

}
