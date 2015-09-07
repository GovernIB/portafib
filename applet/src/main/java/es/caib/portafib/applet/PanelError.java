package es.caib.portafib.applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * 
 * @author anadal
 * 
 */
public class PanelError extends JPanel {

  boolean showStackTrace;
  
  /**
   * @param e
   */
  public PanelError(boolean showStackTrace) {
    this.showStackTrace = showStackTrace;
  }

  public void init(PortaFIBAppletException e, SignerContext signerContext) {

    setLayout(new BorderLayout());

    /*
    if (e.getCause() != null && e.getCause() instanceof ActualitzarLlibreriaException) {

      ActualitzarLlibreriaException ale = (ActualitzarLlibreriaException) e.getCause();

      UpgradeNeededException une = ale.getUpgradeNeededException();

      String msg = ale.getMessage();

      add(new JLabel(msg.substring(0, msg.indexOf('|')), JLabel.CENTER), BorderLayout.NORTH);

      URL newUrl = une.getUrl();
      if (newUrl != null && newUrl.toString().trim().length() != 0) {
        JButton boto = new JButton(msg.substring(msg.indexOf('|') + 1));
        boto.addActionListener(new OnClick(une, signerContext));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        panel.add(boto);
        add(panel, BorderLayout.CENTER);
      } else {
        JTextArea ta = new JTextArea(getStack(ale));
        ta.setEditable(false);
        add(ta, BorderLayout.CENTER);
      }
    } else 
    */
    {

      JTextField title = new JTextField(e.getTitle());
      title.setEditable(false);
      title.setAlignmentX(JTextField.CENTER_ALIGNMENT);

      add(title, BorderLayout.NORTH);
      
      
      JLabel error = new JLabel(e.getError(), JLabel.CENTER);
      error.setBorder(BorderFactory.createEmptyBorder(15, 1, 15, 1));
      add(error, BorderLayout.CENTER);

      if (showStackTrace) {
        Dimension max = new Dimension(400, 200);
        JTextArea ta = new JTextArea(getStack(e));
        ta.setMaximumSize(max);
        ta.setEditable(false);
        JScrollPane scroll = new JScrollPane(ta);
        
        scroll.setMaximumSize(max);
        
        add(scroll, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(400, 250));
      } else {
        setPreferredSize(new Dimension(400, 150));
      }
    }
    
    
  }

  private String getStack(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stack = sw.toString();
    return stack;
  }

  /*
  public static class OnClick implements ActionListener {

    public UpgradeNeededException une;

    public SignerContext signerContext;


    public OnClick(UpgradeNeededException une, SignerContext signerContext) {
      super();
      this.une = une;
      this.signerContext = signerContext;
    }

    public void actionPerformed(ActionEvent e) {

      URL newUrl = une.getUrl();

      System.out.println("Url to update: " + newUrl);
      try {
        // newUrl = Install.getHtmlInstallUrl( newUrl );
        newUrl = new URL(newUrl.toExternalForm().replaceAll("install\\.jnlp", "install.jsp"));
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      // System.out.println( "Url canviada: " + newUrl );
      signerContext.showURL(newUrl);

    }
  }
*/
}
