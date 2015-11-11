package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAlgorismeDeFirmaManager extends org.fundaciobit.genapp.common.query.ITableManager<AlgorismeDeFirma, Integer> {


	public AlgorismeDeFirma create( int _algorismeDeFirmaID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _suportat_) throws I18NException;

	public AlgorismeDeFirma findByPrimaryKey(int _algorismeDeFirmaID_);

	public void delete(int _algorismeDeFirmaID_);

}
