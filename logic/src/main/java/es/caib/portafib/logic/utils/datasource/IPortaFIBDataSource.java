package es.caib.portafib.logic.utils.datasource;

import java.io.InputStream;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public interface IPortaFIBDataSource {

  public byte[] getByteArray() throws I18NException;
  
  public InputStream getInputStream() throws I18NException;
  
  public String checkSum() throws I18NException;
  
}
