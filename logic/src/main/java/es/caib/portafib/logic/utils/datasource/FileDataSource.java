package es.caib.portafib.logic.utils.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class FileDataSource implements IPortaFIBDataSource {

  protected Logger log = Logger.getLogger(this.getClass());

  private final File file;

  public FileDataSource(File file) throws I18NException {
    super();
    this.file = file;
    if (!file.exists()) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Fitxer " + file.getAbsolutePath()
          + " no existeix");
    }

  }

  @Override
  public byte[] getByteArray() throws I18NException {

    try {
      return FileUtils.readFromFile(this.file);
    } catch (Exception e) {
      // XYZ ZZZ TRA
      String msg = "Error llegint fitxer  " + file.getAbsolutePath() + ": " + e.getMessage();

      log.error(msg, e);

      throw new I18NException("genapp.comodi", msg);
    }
  }

  @Override
  public String checkSum() throws I18NException {

    InputStream is = null;
    try {
      is = this.getInputStream();
      return Utils.getChecksum(is);
    } catch (Exception e) {
      // XYZ ZZZ TRA
      String msg = "Error calculant checkSum de fitxer   " + file.getAbsolutePath() + ": "
          + e.getMessage();

      log.error(msg, e);

      throw new I18NException("genapp.comodi", msg);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e2) {
        }
      }
    }
  }

  @Override
  public InputStream getInputStream() throws I18NException {
    try {
      return new FileInputStream(this.file);
    } catch (FileNotFoundException e) {
      // XYZ ZZZ TRA
      String msg = "Error llegint fitxer  " + file.getAbsolutePath() + ": " + e.getMessage();

      log.error(msg, e);

      throw new I18NException("genapp.comodi", msg);
    }
  }

  @Override
  public long size() throws I18NException {
    return this.file.length();
  }

}
