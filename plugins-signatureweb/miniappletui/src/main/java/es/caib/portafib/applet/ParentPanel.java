package es.caib.portafib.applet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import es.caib.portafib.applet.signers.AfirmaSigner;


/**
 * 
 * @author anadal
 * 
 */
public class ParentPanel extends JPanel {

  private PropertyResourceBundle bundleUI;

  private ISigner signer = null;

  public BasePanel[] basePanel2;

  public int index = 0;

  public final SignerContext signerContext;

  public JPanel nextButtonPanel;

  public JPanel centerPanel;
  
  public CardLayout centerCardLayout;

  private List<ProcessDeFirma> firmes = null;
  
  private PanelError panelError = new PanelError(true);

  public ParentPanel(SignerContext signerContext) {

    this.signerContext = signerContext;

    try {

      String langUI = signerContext.getContextParameter(MiniAppletConstants.APPLET_LANGUAGE_UI);
      if (langUI == null) {
        bundleUI = (PropertyResourceBundle) ResourceBundle.getBundle("signatura");
      } else {
        bundleUI = (PropertyResourceBundle) ResourceBundle.getBundle("signatura", new Locale(
            langUI));
      }

      this.firmes = llegirFirmes(signerContext);


      
      System.out.println(" USING DEFAULT SIGNER = ]" + AfirmaSigner.class.getName() + "[");
      signer = new AfirmaSigner();
      
      
      //signer = new es.caib.portafib.applet.signers.AfirmaSigner();
      signer.init(bundleUI, signerContext);

      
      BasePanel selectCert = signer.getPanelSelectCertificate(this);
      
      System.out.println(" Select cert = " + ((selectCert == null)? selectCert : selectCert.getClass()));
      
      basePanel2 = new BasePanel[] {
          new PanelInici(this), 
          selectCert,
          new PanelSign(this) };
      
      // Init panel
      init();

      showPanel();

      nextButtonPanel.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
      // TODO Traduir
      MiniAppletException pae = new MiniAppletException(
          "Error inicialitzant l'applet de firma", e.getMessage(), e); 
      showErrorPanel(pae);
    }
  }
  
  public void start() {
    // arrancar threads
    for (ProcessDeFirma proces : this.firmes) {
      proces.start();
    }
  }
  

  public List<ProcessDeFirma> llegirFirmes(SignerContext signerContext) throws Exception {
    final String sep = "_";
    List<ProcessDeFirma> firmes = new ArrayList<ProcessDeFirma>();
    int x = 0;
    do {
      String source = signerContext.getContextParameter(MiniAppletConstants.APPLET_SOURCE + sep + x);
      if (source == null || source.trim().length() == 0) {
        break;
      }
      source = signerContext.checkURL(source);
      
      String destination = signerContext
          .getContextParameter(MiniAppletConstants.APPLET_DESTINATION + sep + x);
      if (destination == null || destination.trim().length() == 0) {
        System.out.println(" Break destination ");
        break;
      }
      destination = signerContext.checkURL(destination);
      
      String errorPage = signerContext
          .getContextParameter(MiniAppletConstants.APPLET_ERRORPAGE + sep + x);

      String idname = signerContext.getContextParameter(MiniAppletConstants.APPLET_IDNAME + sep + x);
      if (idname == null || idname.trim().length() == 0) {
        System.out.println(" Break idname ");
        break;
      }

      
      String signType = signerContext.getContextParameter(MiniAppletConstants.APPLET_SIGN_TYPE
          + sep + x);
      if (signType == null || signType.trim().length() == 0) {
        System.out.println(" Break signType ");
        break;
      }
      
      
      
      String signAlgorithm = signerContext.getContextParameter(MiniAppletConstants.APPLET_SIGN_ALGORITHM
          + sep + x);
      if (signAlgorithm == null || signAlgorithm.trim().length() == 0) {
        System.out.println(" Break signAlgorithm ");
        break;
      }


      String propertiesStr64 = signerContext.getContextParameter(MiniAppletConstants.APPLET_MINIAPPLET_PROPERTIES + sep + x);
      if (propertiesStr64 == null || propertiesStr64.trim().length() == 0) {
        System.out.println(" Break properties ");
        break;
      }
      
      String propertiesStr = URLDecoder.decode(propertiesStr64,"UTF-8");
      
      Properties properties = new Properties();
      try {
        properties.load(new StringReader(propertiesStr));
      } catch (IOException e) {

        System.out.println("Error llegint properties amb index " + x);

        System.out.println(propertiesStr64);

        break;
      }

      firmes.add(new ProcessDeFirma(this, source, destination, errorPage, idname, 
          signType, signAlgorithm, properties));
      x++;
    } while (true);

    return firmes;
  }
  


  public void init() {

    this.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(2,2,2,2),
        BorderFactory.createLineBorder(Color.white, 3))
        );
    this.setLayout(new BorderLayout());
    centerCardLayout = new CardLayout();
    centerPanel = new JPanel(centerCardLayout);
    
    centerPanel.add(panelError, panelError.getClass().getName());
    for (int i = 0; i < basePanel2.length; i++) {
      centerPanel.add(basePanel2[i],basePanel2[i].getClass().getName());
    }
    
    

    add(centerPanel, BorderLayout.CENTER);

    JButton nextButton;

    nextButton = new JButton("   " + I18NUtils.traduiex(bundleUI, "seguent") + " >>> " + "   ");
    nextButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        nextAction();
      }
    });

    nextButtonPanel = new JPanel();
    nextButtonPanel.setAlignmentX(CENTER_ALIGNMENT);
    nextButtonPanel.add(nextButton);
    nextButtonPanel.setBackground(Color.white);
    nextButtonPanel.setVisible(false);

    add(nextButtonPanel, BorderLayout.SOUTH);
  };

  private boolean processingNextAction = false;

  public void nextAction() {
    if (processingNextAction) {
      return;
    }
    try {
      processingNextAction = true;
      BasePanel current = basePanel2[index];
      if (current.next()) {
        current.setVisible(false);
        // L'actual panell accepta passar al següent
        index++;
        showPanel();
      }
    } catch (Exception e) {
      e.printStackTrace();
      
      // TODO Traduir
      MiniAppletException pae = new MiniAppletException(
          "Error passant al següent panell", e.getMessage(), e); 
      showErrorPanel(pae);

    } finally {
      processingNextAction = false;
    }

  }

  public void hideNextButton() {
    this.nextButtonPanel.setVisible(false);
  }

  public void showPanel() throws Exception {
    //this.centerPanel.removeAll();
    /*
    if (index !=0) {
    BorderLayout layout = (BorderLayout)this.centerPanel.getLayout();
    centerPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
    }
    */

    BasePanel bp = basePanel2[index];
    bp.preShow();
    
    this.centerCardLayout.show(this.centerPanel, bp.getClass().getName());
    
    
    /*
    this.centerPanel.add(bp, BorderLayout.CENTER);
    this.centerPanel.setVisible(true);
    bp.setVisible(true);
    this.centerPanel.repaint();
    this.repaint();
    
    this.requestFocus();
    
    if (index !=0) {
      this.centerPanel.add(new JLabel(""), (index % 2 == 0) ? BorderLayout.EAST :BorderLayout.WEST);
    }
    
    this.setSize(this.getWidth() +1, this.getHeight() + 1);
    this.setSize(this.getWidth() -1, this.getHeight() - 1);
    */
    System.out.println("Show Panel " + index);
  }

  public void showErrorPanel(MiniAppletException e) {

    panelError.init(e, signerContext);

    if (centerPanel != null) {
      /**
      if (basePanel != null) {
        BasePanel bp = basePanel[index];
        bp.setVisible(false);
      }
      this.centerPanel.add(pe);
      this.centerPanel.setVisible(true);
      this.centerPanel.repaint();
      */
      this.centerCardLayout.show(this.centerPanel, panelError.getClass().getName());
    } else {
      this.add(panelError);
    }
    
    System.out.println("Passa per showErrorPanel");
    this.repaint();

  }

  public PropertyResourceBundle getBundleUI() {
    return bundleUI;
  }
  
 

  public ISigner getSigner() {
    return signer;
  }



  public List<ProcessDeFirma> getFirmes() {
    return firmes;
  }

  

  public final String tradueix(String code, String... params) {
    return I18NUtils.traduiex(bundleUI, code, params);
  }
}
