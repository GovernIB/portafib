package es.caib.portafib.logic.utils.datasource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ByteArrayDataSource implements IPortaFIBDataSource {

  protected Logger log = Logger.getLogger(this.getClass());

  private final byte[] byteArray;

  public ByteArrayDataSource(byte[] byteArray) throws I18NException {
    super();

    if (byteArray == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "El valor del byte[] es null");
    }

    this.byteArray = byteArray;
  }

  @Override
  public byte[] getByteArray() {

    return byteArray;
  }

  @Override
  public String checkSum() {

    try {
      return Utils.getChecksum(this.getInputStream());
    } catch (Exception e) {

      return null;
    }
  }

  @Override
  public InputStream getInputStream() {
    return new ByteArrayInputStream(this.byteArray);
  }

}
