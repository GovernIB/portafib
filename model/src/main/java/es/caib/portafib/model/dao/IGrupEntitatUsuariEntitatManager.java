package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGrupEntitatUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<GrupEntitatUsuariEntitat, Long> {


	public GrupEntitatUsuariEntitat create( java.lang.String _usuariEntitatID_, java.lang.Long _grupEntitatID_) throws I18NException;

	public GrupEntitatUsuariEntitat findByPrimaryKey(long _grupEntitatUsuariEntitatID_);

	public void delete(long _grupEntitatUsuariEntitatID_);

}
