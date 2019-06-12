package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XYZ ZZZ ZZZ Revisar si ho utilitzam
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleDocumentTypeInformation {

  protected long documentType;

  protected String name;

  protected long documentTypeBase;

  /**
 * 
 */
  public FirmaAsyncSimpleDocumentTypeInformation() {
    super();
  }

  public FirmaAsyncSimpleDocumentTypeInformation(long documentType, String name,
      long documentTypeBase) {
    super();
    this.documentType = documentType;
    this.name = name;
    this.documentTypeBase = documentTypeBase;
  }

  public long getDocumentType() {
    return documentType;
  }

  public void setDocumentType(long documentType) {
    this.documentType = documentType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getDocumentTypeBase() {
    return documentTypeBase;
  }

  public void setDocumentTypeBase(long documentTypeBase) {
    this.documentTypeBase = documentTypeBase;
  }

}
