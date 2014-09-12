package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICodiBarresManager extends org.fundaciobit.genapp.common.query.ITableManager<CodiBarres, String> {


	public CodiBarres create( java.lang.String _codiBarresID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public CodiBarres findByPrimaryKey(java.lang.String _codiBarresID_);

	public void delete(java.lang.String _codiBarresID_);

}
