package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPosicioPaginaManager extends org.fundaciobit.genapp.common.query.ITableManager<PosicioPagina, Long> {


	public PosicioPagina create( long _posicioPaginaID_, java.lang.String _nom_) throws I18NException;

	public PosicioPagina findByPrimaryKey(long _posicioPaginaID_);

	public void delete(long _posicioPaginaID_);

}
