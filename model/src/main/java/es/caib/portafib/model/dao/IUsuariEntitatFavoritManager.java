package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatFavoritManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitatFavorit, Long> {


	public UsuariEntitatFavorit create( java.lang.String _origenID_, java.lang.String _favoritID_) throws I18NException;

	public UsuariEntitatFavorit findByPrimaryKey(long _ID_);

	public void delete(long _ID_);

}
