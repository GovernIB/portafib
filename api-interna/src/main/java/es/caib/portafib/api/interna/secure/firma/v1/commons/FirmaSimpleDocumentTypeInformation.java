package es.caib.portafib.api.interna.secure.firma.v1.commons;


/**
 * 
 * @author anadal(u80067)
 *
 */

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
