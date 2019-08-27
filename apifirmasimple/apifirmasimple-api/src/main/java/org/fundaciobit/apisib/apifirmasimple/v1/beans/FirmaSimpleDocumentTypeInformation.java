package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleDocumentTypeInformation {

  protected long documentType;

  protected String name;

  protected long documentTypeBase;

  /**
   *
   */
  public FirmaSimpleDocumentTypeInformation() {
    super();
  }

  public FirmaSimpleDocumentTypeInformation(long documentType, String name,
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
