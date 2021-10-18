package es.caib.portafib.utils;

/**
 * 
 * @author anadal
 * 
 */
public class SignBoxRectangle {
  public final int llx; // llx - the lower left x corner
  public final int lly; // lly - the lower left y corner

  public final int urx; // urx - the upper right x corner
  public final int ury; // ury - the upper right y corner

  
  
  public SignBoxRectangle(String toString) {
    String[] parts = toString.split(",");
    llx = Integer.parseInt(parts[0]);
    lly = Integer.parseInt(parts[1]);
    urx = Integer.parseInt(parts[2]);
    ury = Integer.parseInt(parts[3]);
    
  }
  
  
  /**
   * @param llx
   * @param lly
   * @param urx
   * @param ury
   */
  public SignBoxRectangle(int llx, int lly, int urx, int ury) {
    super();
    this.llx = llx;
    this.lly = lly;
    this.urx = urx;
    this.ury = ury;
  }

  @Override
  public String toString() {
    return this.llx + "," + this.lly + "," +this.urx + "," +this.ury;
  }
  
  
  public static SignBoxRectangle getPositionOfVisibleSignature(int num_firma) {
    final float width = 482 - 30;
    final int marge = (int)(0.51f * 72f);
    int alt = marge + ConstantsV2.SIGNBOX_START + ConstantsV2.SIGNBOX_HEIGHT * (num_firma -1);
    final float top = -1.0f * alt;
    final float left = 76 + 30;

    final float height = ConstantsV2.SIGNBOX_HEIGHT;
    
    float llx = left;  // llx - the lower left x corner
    float lly = /*rect.getHeight()*/ ConstantsV2.A4_ALT + top  - ConstantsV2.SIGNBOX_HEIGHT;  // lly - the lower left y corner

    float urx = llx + width - 3;  // urx - the upper right x corner
    float ury = lly + height - 3;  // ury - the upper right y corner
    
    lly = lly + 2.5f;
    
    
    return new SignBoxRectangle((int)llx, (int)lly, (int)urx, (int)ury);
    
    
  }
  
  
  
}