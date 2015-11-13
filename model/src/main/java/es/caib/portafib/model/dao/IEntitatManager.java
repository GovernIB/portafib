package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, String> {


	public Entitat create( java.lang.String _entitatID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _activa_, java.lang.String _web_, java.lang.Long _faviconID_, java.lang.Long _logoWebID_, java.lang.Long _logoWebPeuID_, java.lang.Long _logoSegellID_, java.lang.String _adrezaHtml_, java.lang.String _filtreCertificats_, java.lang.Long _pdfAutoritzacioDelegacioID_, java.lang.String _suportTelefon_, java.lang.String _suportWeb_, java.lang.String _suportEmail_, java.lang.String _usuariAplicacioID_, java.lang.Long _maxUploadSize_, java.lang.Long _maxSizeFitxerAdaptat_, java.lang.Integer _maxFilesToSignAtSameTime_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_, java.lang.Long _motiuDelegacioID_, java.lang.Long _firmatPerFormatID_, int _algorismeDeFirmaID_, boolean _comprovarCertificatClientCert_, boolean _comprovarNifFirma_) throws I18NException;

	public Entitat findByPrimaryKey(java.lang.String _entitatID_);

	public void delete(java.lang.String _entitatID_);

}
