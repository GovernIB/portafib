package es.caib.portafib.logic.utils.datasource;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class FitxerIdDataSource extends FileDataSource {
  
  private final long fitxerID;

  public FitxerIdDataSource(long fitxerID) throws I18NException {
    super(FileSystemManager.getFile(fitxerID));
    this.fitxerID = fitxerID;
  }

  public long getFitxerID() {
    return fitxerID;
  }


}
