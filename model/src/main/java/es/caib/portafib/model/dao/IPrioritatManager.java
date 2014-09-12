package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPrioritatManager extends org.fundaciobit.genapp.common.query.ITableManager<Prioritat, Integer> {


	public Prioritat create( int _prioritatID_, java.lang.String _nom_) throws I18NException;

	public Prioritat findByPrimaryKey(int _prioritatID_);

	public void delete(int _prioritatID_);

}
