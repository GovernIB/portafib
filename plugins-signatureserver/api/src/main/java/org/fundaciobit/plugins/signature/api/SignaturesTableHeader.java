package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public class SignaturesTableHeader {

  private byte[] logoJpeg;

  private String title;

  private String titleFieldLabel;

  private String titleFieldValue;

  private String descriptionFieldLabel;

  private String descriptionFieldValue;
  
  private String signatureLabel;

  /**
   * 
   */
  public SignaturesTableHeader() {
    super();
  }



  /**
   * @param logoJpeg
   * @param title
   * @param titleFieldLabel
   * @param titleFieldValue
   * @param descriptionFieldLabel
   * @param descriptionFieldValue
   * @param signatureLabel
   */
  public SignaturesTableHeader(byte[] logoJpeg, String title, String titleFieldLabel,
      String titleFieldValue, String descriptionFieldLabel, String descriptionFieldValue,
      String signatureLabel) {
    super();
    this.logoJpeg = logoJpeg;
    this.title = title;
    this.titleFieldLabel = titleFieldLabel;
    this.titleFieldValue = titleFieldValue;
    this.descriptionFieldLabel = descriptionFieldLabel;
    this.descriptionFieldValue = descriptionFieldValue;
    this.signatureLabel = signatureLabel;
  }



  public byte[] getLogoJpeg() {
    return logoJpeg;
  }

  public void setLogoJpeg(byte[] logoJpeg) {
    this.logoJpeg = logoJpeg;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleFieldLabel() {
    return titleFieldLabel;
  }

  public void setTitleFieldLabel(String titleFieldLabel) {
    this.titleFieldLabel = titleFieldLabel;
  }

  public String getTitleFieldValue() {
    return titleFieldValue;
  }

  public void setTitleFieldValue(String titleFieldValue) {
    this.titleFieldValue = titleFieldValue;
  }

  public String getDescriptionFieldLabel() {
    return descriptionFieldLabel;
  }

  public void setDescriptionFieldLabel(String descriptionFieldLabel) {
    this.descriptionFieldLabel = descriptionFieldLabel;
  }

  public String getDescriptionFieldValue() {
    return descriptionFieldValue;
  }

  public void setDescriptionFieldValue(String descriptionFieldValue) {
    this.descriptionFieldValue = descriptionFieldValue;
  }

  public String getSignatureLabel() {
    return signatureLabel;
  }

  public void setSignatureLabel(String signatureLabel) {
    this.signatureLabel = signatureLabel;
  }

}
