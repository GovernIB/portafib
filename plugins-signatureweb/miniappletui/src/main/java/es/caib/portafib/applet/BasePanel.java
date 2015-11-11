package es.caib.portafib.applet;

import java.awt.Color;
import java.util.PropertyResourceBundle;

import javax.swing.JPanel;

/**
 * 
 * @author anadal
 * 
 */
public abstract class BasePanel extends JPanel {

  protected final PropertyResourceBundle bundle;

  protected final ISigner signer;

  protected final ParentPanel parentPanel;

  /**
   * @param signaturaProperties
   * @param signer
   */
  public BasePanel(ParentPanel parentPanel) {
    super();
    this.bundle = parentPanel.getBundleUI();
    this.signer = parentPanel.getSigner();
    this.parentPanel = parentPanel;
    this.setBackground(Color.white);
    this.setBorder(null);
  }

  protected boolean hasNextPanel = false;

  protected boolean isNextPanel() {
    return hasNextPanel;
  }

  public abstract boolean next();

  public abstract void preShow() throws Exception;

  public final String tradueix(String code, String... params) {
    return I18NUtils.traduiex(bundle, code, params);
  }
  
  

}
