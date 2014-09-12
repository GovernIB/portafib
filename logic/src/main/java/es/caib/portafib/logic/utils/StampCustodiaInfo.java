package es.caib.portafib.logic.utils;

import org.fundaciobit.plugins.barcode.IBarcodePlugin;

/**
 * 
 * @author anadal
 *
 */
public class StampCustodiaInfo {

  protected int posicioCustodiaInfo;
  String missatgeCustodia;

  String barcodeText;

  IBarcodePlugin barcodePlugin;

  String pagines;

  /**
   * 
   */
  public StampCustodiaInfo() {
    super();
  }

  /**
   * @param posicioCustodiaInfo
   * @param missatgeCustodia
   * @param urlValidacioCustodia
   */
  public StampCustodiaInfo(int posicioCustodiaInfo, String missatgeCustodia,
      IBarcodePlugin barcodePlugin, String barcodeText, String pagines) {
    super();
    this.posicioCustodiaInfo = posicioCustodiaInfo;
    this.missatgeCustodia = missatgeCustodia;
    this.barcodeText = barcodeText;
    this.barcodePlugin = barcodePlugin;
    this.pagines = pagines;
  }

  public IBarcodePlugin getBarcodePlugin() {
    return barcodePlugin;
  }

  public void setBarcodePlugin(IBarcodePlugin barcodePlugin) {
    this.barcodePlugin = barcodePlugin;
  }

  public int getPosicioCustodiaInfo() {
    return posicioCustodiaInfo;
  }

  public void setPosicioCustodiaInfo(int posicioCustodiaInfo) {
    this.posicioCustodiaInfo = posicioCustodiaInfo;
  }

  public String getMissatgeCustodia() {
    return missatgeCustodia;
  }

  public void setMissatgeCustodia(String missatgeCustodia) {
    this.missatgeCustodia = missatgeCustodia;
  }

  public String getBarcodeText() {
    return barcodeText;
  }

  public void setBarcodeText(String barcodeText) {
    this.barcodeText = barcodeText;
  }

  public String getPagines() {
    return pagines;
  }

  public void setPagines(String pagines) {
    this.pagines = pagines;
  }

}
