package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariAplicacioConfiguracioManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariAplicacioConfiguracio, Long> {


	public UsuariAplicacioConfiguracio create( java.lang.String _usuariAplicacioID_, int _usPoliticaDeFirma_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_, java.lang.String _filtreCertificats_, int _tipusOperacioFirma_, int _tipusFirmaID_, java.lang.Integer _algorismeDeFirmaID_, boolean _modeDeFirma_, java.lang.Long _motiuDelegacioID_, java.lang.Long _firmatPerFormatID_, int _politicaCustodia_, java.lang.Long _custodiaInfoID_, int _politicaTaulaFirmes_, int _posicioTaulaFirmesID_, int _politicaSegellatDeTemps_, java.lang.Long _pluginSegellatID_, java.lang.String _htmlPerLlistarPluginsFirmaWeb_, java.lang.Long _pluginFirmaServidorID_, java.lang.Long _loginCertificateID_, java.lang.Boolean _comprovarNifFirma_, java.lang.Boolean _checkCanviatDocFirmat_, java.lang.Boolean _validarFirma_, java.lang.Boolean _validarCertificat_) throws I18NException;

	public UsuariAplicacioConfiguracio findByPrimaryKey(long _usuariAplicacioConfigID_);

	public void delete(long _usuariAplicacioConfigID_);

}
