package org.fundaciobit.apisib.apifirmasimple.v1.beans;

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
public class FirmaSimpleFile extends ApisIBFile {

  public FirmaSimpleFile() {
    super();
  }

  public FirmaSimpleFile(ApisIBFile apisibfile) {
    super(apisibfile);
  }

  public FirmaSimpleFile(String nom, String mime, byte[] data) {
    super(nom, mime, data);
  }

}
