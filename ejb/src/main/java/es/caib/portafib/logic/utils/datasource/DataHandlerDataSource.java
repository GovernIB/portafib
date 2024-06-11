package es.caib.portafib.logic.utils.datasource;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class DataHandlerDataSource implements IPortaFIBDataSource {

  protected Logger log = Logger.getLogger(this.getClass());

  protected final DataHandler dataHandler;

  public DataHandlerDataSource(DataHandler dataHandler) {
    super();
    this.dataHandler = dataHandler;
  }

  @Override
  public byte[] getByteArray() throws I18NException {
    try {
      return FileUtils.toByteArray(this.getInputStream());
    } catch (IOException e) {
      // XYZ ZZZ TRA
      String msg = "Error obtenint byte[] de DataHandler: " + e.getMessage();
      log.error(msg, e);
      throw new I18NException("genapp.comodi", msg);
    }
  }

  @Override
  public InputStream getInputStream() throws I18NException {

    try {
      return dataHandler.getInputStream();
    } catch (IOException e) {
      // XYZ ZZZ TRA
      String msg = "Error obtenint inputstream de DataHandler: " + e.getMessage();
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
      String msg = "Error calculant checkSum de datahandler: " + e.getMessage();

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
  public long size() throws I18NException {
    try {
      return this.getInputStream().available();
    } catch (IOException e) {
      // XYZ ZZZ TRA
      String msg = "Error obtenint tamany de DataHandler: " + e.getMessage();
      log.error(msg, e);
      throw new I18NException("genapp.comodi", msg);
    }
  }

}
