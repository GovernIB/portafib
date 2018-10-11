package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPeticioDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<PeticioDeFirma, Long> {


	public PeticioDeFirma create( java.lang.String _titol_, java.lang.String _descripcio_, java.lang.String _motiu_, java.lang.Long _fitxerAFirmarID_, java.lang.Long _firmaOriginalDetachedID_, java.lang.Long _fitxerAdaptatID_, long _tipusDocumentID_, java.lang.String _descripcioTipusDocument_, int _posicioTaulaFirmesID_, java.sql.Timestamp _dataSolicitud_, java.sql.Timestamp _dataFinal_, java.sql.Timestamp _dataCaducitat_, int _tipusOperacioFirma_, int _tipusFirmaID_, int _algorismeDeFirmaID_, java.lang.Boolean _modeDeFirma_, int _tipusEstatPeticioDeFirmaID_, java.lang.String _motiuDeRebuig_, java.lang.String _idiomaID_, int _prioritatID_, long _fluxDeFirmesID_, java.lang.String _usuariAplicacioID_, java.lang.String _remitentNom_, java.lang.String _remitentDescripcio_, java.lang.String _expedientCodi_, java.lang.String _expedientNom_, java.lang.String _expedientUrl_, java.lang.String _procedimentCodi_, java.lang.String _procedimentNom_, java.lang.String _informacioAddicional_, java.lang.Double _informacioAddicionalAvaluable_, java.lang.Long _logoSegellID_, java.lang.Long _custodiaInfoID_, java.lang.String _usuariEntitatID_, boolean _avisWeb_, boolean _segellatDeTemps_) throws I18NException;

	public PeticioDeFirma findByPrimaryKey(long _peticioDeFirmaID_);

	public void delete(long _peticioDeFirmaID_);

}
