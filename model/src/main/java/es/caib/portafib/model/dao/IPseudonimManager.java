package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPseudonimManager extends org.fundaciobit.genapp.common.query.ITableManager<Pseudonim, Long> {


	public Pseudonim create( java.lang.String _pseudonim_, java.lang.String _nif_) throws I18NException;

	public Pseudonim findByPrimaryKey(long _pseudonimid_);

	public void delete(long _pseudonimid_);

}
