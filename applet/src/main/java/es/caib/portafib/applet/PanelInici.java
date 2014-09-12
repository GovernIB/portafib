package es.caib.portafib.applet;

import java.awt.BorderLayout;
import javax.swing.JEditorPane;

/**
 * 
 * @author anadal
 * 
 */
public class PanelInici extends BasePanel {

  /**
   * @param signaturaProperties
   * @param signer
   */
  public PanelInici(ParentPanel parentPanel) {
    super(parentPanel);
    init();
  }

  public void init() {
    setLayout(new BorderLayout());
    JEditorPane msg;
    msg = new JEditorPane();
    msg.setContentType("text/html");
    msg.setText(tradueix("benvinguda"));
    msg.setEditable(false);

    add(msg, BorderLayout.CENTER);
  }

  @Override
  public boolean next() {
    return true;
  }

  @Override
  public void preShow() throws Exception {
  }

}
