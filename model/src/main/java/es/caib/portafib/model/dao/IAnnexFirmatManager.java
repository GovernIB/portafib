package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAnnexFirmatManager extends org.fundaciobit.genapp.common.query.ITableManager<AnnexFirmat, Long> {


	public AnnexFirmat create( long _fitxerID_, long _annexID_, long _firmaID_) throws I18NException;

	public AnnexFirmat findByPrimaryKey(long _annexfirmatID_);

	public void delete(long _annexfirmatID_);

}
