package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fundaciobit.apisib.core.beans.ApisIBFile;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleFile extends ApisIBFile {

  /** Constructor Buit */
  public FirmaAsyncSimpleFile() {
  }

  /** Constructor amb tots els camps */
  public FirmaAsyncSimpleFile(java.lang.String nom, java.lang.String mime, byte[] data) {
    super(nom, mime, data);
  }

  public FirmaAsyncSimpleFile(ApisIBFile apisibfile) {
    super(apisibfile);
  }

  
  
}
