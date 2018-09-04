package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public class SecureVerificationCodeStampInfo {

  public static final String BARCODE_PDF417 = "Pdf417";

  public static final String BARCODE_QR = "QrCode";

  public static final String BARCODE_128 = "BarCode128";

  // ----------- POSICIO PAGINA

  /**
   * Indicates that the signature will be added in the upper part of the PDF
   * document.
   */
  public static final int POSITION_TOP = 1;

  /**
   * Indicates that the signature will be added in the bottom part of the PDF
   * document.
   */
  public static final int POSITION_BOTTOM = 2;

  /**
   * Indicates that the signature will be added in the left part of the PDF
   * document.
   */
  public static final int POSITION_LEFT = 3;

  /**
   * Indicates that the signature will be added int the right part of the PDF
   * document.
   */
  public static final int POSITION_RIGHT = 4;

  /**
   * Neither code bars nor signer information will be added to the PDF document.
   */
  public static final int POSITION_NONE = 0;

  String pages;

  String message;
  int messagePosition;

  String barCodeType;
  int barCodePosition;
  String barCodeText;

  ISecureVerificationCodeStamper secureVerificationCodeStamper;

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

  public ISecureVerificationCodeStamper getSecureVerificationCodeStamper() {
    return secureVerificationCodeStamper;
  }

  public void setSecureVerificationCodeStamper(
      ISecureVerificationCodeStamper secureVerificationCodeStamper) {
    this.secureVerificationCodeStamper = secureVerificationCodeStamper;
  }

}
