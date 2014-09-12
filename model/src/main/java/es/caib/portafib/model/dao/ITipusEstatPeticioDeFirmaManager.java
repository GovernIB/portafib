package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusEstatPeticioDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusEstatPeticioDeFirma, Integer> {


	public TipusEstatPeticioDeFirma create( int _tipusEstatPeticioDeFirmaID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public TipusEstatPeticioDeFirma findByPrimaryKey(int _tipusEstatPeticioDeFirmaID_);

	public void delete(int _tipusEstatPeticioDeFirmaID_);

}
