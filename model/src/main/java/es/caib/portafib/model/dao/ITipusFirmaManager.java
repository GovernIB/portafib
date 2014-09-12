package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusFirma, Integer> {


	public TipusFirma create( int _tipusFirmaID_, java.lang.String _nom_, boolean _suportada_, java.lang.String _descripcio_) throws I18NException;

	public TipusFirma findByPrimaryKey(int _tipusFirmaID_);

	public void delete(int _tipusFirmaID_);

}
