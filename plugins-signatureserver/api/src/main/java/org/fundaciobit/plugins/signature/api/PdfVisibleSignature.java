package org.fundaciobit.plugins.signature.api;


/**
 * 
 * @author anadal
 *
 */
public class PdfVisibleSignature {

  
  protected PdfRubricRectangle pdfRubricRectangle = null;
  
  protected IRubricGenerator rubricGenerator = null;
  

  /**
   * @param signaturesTableLocation
   * @param pdfRubricRectangle
   * @param rubricGenerator
   */
  public PdfVisibleSignature(PdfRubricRectangle pdfRubricRectangle,
      IRubricGenerator rubricGenerator) {
    super();    
    this.pdfRubricRectangle = pdfRubricRectangle;
    this.rubricGenerator = rubricGenerator;
  }

  /**
   * 
   */
  public PdfVisibleSignature() {
    super();
  }


  public PdfRubricRectangle getPdfRubricRectangle() {
    return pdfRubricRectangle;
  }

  public void setPdfRubricRectangle(PdfRubricRectangle pdfRubricRectangle) {
    this.pdfRubricRectangle = pdfRubricRectangle;
  }

  public IRubricGenerator getRubricGenerator() {
    return rubricGenerator;
  }

  public void setRubricGenerator(IRubricGenerator rubricGenerator) {
    this.rubricGenerator = rubricGenerator;
  }

}
