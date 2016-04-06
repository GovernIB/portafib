package es.caib.portafib.logic.passarela;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaSecureVerificationCodeStampInfo {

  String pages;

  String message;
  int messagePosition;

  String barCodeType;
  int barCodePosition;
  String barCodeText;

  public String getPages() {
    return pages;
  }

  public void setPages(String pages) {
    this.pages = pages;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getMessagePosition() {
    return messagePosition;
  }

  public void setMessagePosition(int messagePosition) {
    this.messagePosition = messagePosition;
  }

  public String getBarCodeType() {
    return barCodeType;
  }

  public void setBarCodeType(String barCodeType) {
    this.barCodeType = barCodeType;
  }

  public int getBarCodePosition() {
    return barCodePosition;
  }

  public void setBarCodePosition(int barCodePosition) {
    this.barCodePosition = barCodePosition;
  }

  public String getBarCodeText() {
    return barCodeText;
  }

  public void setBarCodeText(String barCodeText) {
    this.barCodeText = barCodeText;
  }

}
