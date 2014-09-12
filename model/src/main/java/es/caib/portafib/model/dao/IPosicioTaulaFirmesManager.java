package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPosicioTaulaFirmesManager extends org.fundaciobit.genapp.common.query.ITableManager<PosicioTaulaFirmes, Integer> {


	public PosicioTaulaFirmes create( int _posicioTaulaFirmesID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _suportada_) throws I18NException;

	public PosicioTaulaFirmes findByPrimaryKey(int _posicioTaulaFirmesID_);

	public void delete(int _posicioTaulaFirmesID_);

}
