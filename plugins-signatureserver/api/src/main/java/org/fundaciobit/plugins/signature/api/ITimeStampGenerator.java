package org.fundaciobit.plugins.signature.api;

import java.util.Calendar;

/**
 * Generador de Segell de Temps a partir d'una cadena binaria
 * 
 * @author anadal
 *
 */
public interface ITimeStampGenerator {

  public byte[] getTimeStamp(byte[] data, Calendar cal) throws Exception;

  public String getTimeStampPolicyOID();

  public String getTimeStampHashAlgorithm();

}
