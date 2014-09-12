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
  
}