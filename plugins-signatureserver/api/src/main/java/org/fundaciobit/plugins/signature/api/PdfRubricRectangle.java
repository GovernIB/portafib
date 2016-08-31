package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public class PdfRubricRectangle {

  protected float lowerLeftX; // llx - the lower left x corner
  protected float lowerLeftY; // lly - the lower left y corner

  protected float upperRightX; // urx - the upper right x corner
  protected float upperRightY; // ury - the upper right y corner

  public PdfRubricRectangle() {
  }
  
  public PdfRubricRectangle(String toString) {
    String[] parts = toString.split(",");
    lowerLeftX = Float.parseFloat(parts[0]);
    lowerLeftY = Float.parseFloat(parts[1]);
    upperRightX = Float.parseFloat(parts[2]);
    upperRightY = Float.parseFloat(parts[3]);
  }
  
  
  /**
   * @param llx
   * @param lly
   * @param urx
   * @param ury
   */
  public PdfRubricRectangle(float llx, float lly, float urx, float ury) {
    super();
    this.lowerLeftX = llx;
    this.lowerLeftY = lly;
    this.upperRightX = urx;
    this.upperRightY = ury;
  }

  @Override
  public String toString() {
    return this.lowerLeftX + "," + this.lowerLeftY + "," +this.upperRightX + "," +this.upperRightY;
  }

  public float getLowerLeftX() {
    return lowerLeftX;
  }

  public void setLowerLeftX(float lowerLeftX) {
    this.lowerLeftX = lowerLeftX;
  }

  public float getLowerLeftY() {
    return lowerLeftY;
  }

  public void setLowerLeftY(float lowerLeftY) {
    this.lowerLeftY = lowerLeftY;
  }

  public float getUpperRightX() {
    return upperRightX;
  }

  public void setUpperRightX(float upperRightX) {
    this.upperRightX = upperRightX;
  }

  public float getUpperRightY() {
    return upperRightY;
  }

  public void setUpperRightY(float upperRightY) {
    this.upperRightY = upperRightY;
  }

  
  
}
