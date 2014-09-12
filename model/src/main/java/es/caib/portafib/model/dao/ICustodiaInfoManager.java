package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICustodiaInfoManager extends org.fundaciobit.genapp.common.query.ITableManager<CustodiaInfo, Long> {


	public CustodiaInfo create( java.lang.String _custodiaPluginID_, java.lang.String _custodiaPluginClassID_, java.lang.String _custodiaPluginParameters_, java.lang.String _nomPlantilla_, boolean _custodiar_, java.lang.String _urlFitxerCustodiat_, java.lang.String _pagines_, java.lang.String _missatge_, long _missatgePosicioPaginaID_, java.lang.String _codiBarresID_, long _codiBarresPosicioPaginaID_, java.lang.String _codiBarresText_, java.lang.String _usuariEntitatID_, java.lang.String _usuariAplicacioID_, java.lang.String _entitatID_, java.lang.String _titolPeticio_, java.sql.Timestamp _dataCustodia_, boolean _editable_) throws I18NException;

	public CustodiaInfo findByPrimaryKey(long _custodiaInfoID_);

	public void delete(long _custodiaInfoID_);

}
