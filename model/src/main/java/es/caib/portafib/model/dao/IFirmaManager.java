package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<Firma, Long> {


	public Firma create( java.lang.String _destinatariID_, long _blocDeFirmaID_, boolean _obligatori_, java.lang.Long _fitxerFirmatID_, java.lang.Integer _numFirmaDocument_, int _caixaPagina_, java.lang.Integer _caixaX_, java.lang.Integer _caixaY_, java.lang.Integer _caixaAmple_, java.lang.Integer _caixaAlt_, java.math.BigInteger _numeroSerieCertificat_, java.lang.String _emissorCertificat_, java.lang.String _nomCertificat_, java.lang.Long _tipusEstatDeFirmaFinalID_, boolean _mostrarRubrica_, java.lang.String _motiu_, int _minimDeRevisors_) throws I18NException;

	public Firma findByPrimaryKey(long _firmaID_);

	public void delete(long _firmaID_);

}
