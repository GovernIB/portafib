package es.caib.portafib.model.dao;

import es.caib.portafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFitxerManager extends org.fundaciobit.genapp.common.filesystem.IFileManager<Fitxer> {


	public Fitxer create( java.lang.String _nom_, java.lang.String _descripcio_, long _tamany_, java.lang.String _mime_) throws I18NException;

	public Fitxer findByPrimaryKey(long _fitxerID_);

	public void delete(long _fitxerID_);

}
