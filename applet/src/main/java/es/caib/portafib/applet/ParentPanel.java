package es.caib.portafib.applet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.SignBoxRectangle;


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

      String langUI = signerContext.getContextParameter(Constants.APPLET_LANGUAGE_UI);
      if (langUI == null) {
        bundleUI = (PropertyResourceBundle) ResourceBundle.getBundle("signatura");
      } else {
        bundleUI = (PropertyResourceBundle) ResourceBundle.getBundle("signatura", new Locale(
            langUI));
      }

      this.firmes = llegirFirmes(signerContext);


      // TODO EN PARALEL
      //System.out.println(" SIGNER IBKEY CLASS =      ]" + IBKeySigner.class.getName()+"[");
      
      String signerClassTxt = this.signerContext.getContextParameter(Constants.APPLET_SIGNERCLASS);
      System.out.println(" SIGNER CLASS FROM PARAM = ]" + signerClassTxt + "[");
      Class<?> signerClass = Class.forName(signerClassTxt);
      signer = (ISigner)signerClass.getConstructor().newInstance();
      
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
      PortaFIBAppletException pae = new PortaFIBAppletException(
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
  

  public List<ProcessDeFirma> llegirFirmes(SignerContext signerContext) {
    final String sep = "_";
    List<ProcessDeFirma> firmes = new ArrayList<ProcessDeFirma>();
    int x = 0;
    do {
      String source = signerContext.getContextParameter(Constants.APPLET_SOURCE + sep + x);
      if (source == null || source.trim().length() == 0) {
        break;
      }
      source = signerContext.checkURL(source);
      
      String destination = signerContext
          .getContextParameter(Constants.APPLET_DESTINATION + sep + x);
      if (destination == null || destination.trim().length() == 0) {
        System.out.println(" Break destination ");
        break;
      }
      destination = signerContext.checkURL(destination);

      String idname = signerContext.getContextParameter(Constants.APPLET_IDNAME + sep + x);
      if (idname == null || idname.trim().length() == 0) {
        System.out.println(" Break idname ");
        break;
      }

      String motiu = signerContext.getContextParameter(Constants.APPLET_REASON + sep + x);
      if (motiu == null || motiu.trim().length() == 0) {
        System.out.println(" Break reason ");
        break;
      }
      
      String firmatPerFormat = signerContext.getContextParameter(Constants.APPLET_FIRMATPERFORMAT + sep + x);
      if (firmatPerFormat == null || firmatPerFormat.trim().length() == 0) {
        System.out.println(" Break firmatPer ");
        break;
      }
      
      
      String numFirmesTxt = signerContext.getContextParameter(Constants.APPLET_SIGN_NUMBER
          + sep + x);
      if (numFirmesTxt == null || numFirmesTxt.trim().length() == 0) {
        System.out.println(" Break signnumber ");
        break;
      }
      int numFirmes;
      try {
        numFirmes = Integer.parseInt(numFirmesTxt);
      } catch (NumberFormatException e) {
        e.printStackTrace();
        System.out.println(" Break signnumber format");
        break;
      }
      

      String posicioTxt = signerContext.getContextParameter(Constants.APPLET_LOCATION_SIGN_TABLE
          + sep + x);
      if (posicioTxt == null || posicioTxt.trim().length() == 0) {
        System.out.println(" Break location");
        break;
      }
      int posicio;
      try {
        posicio = Integer.parseInt(posicioTxt);

        if ((posicio != Constants.TAULADEFIRMES_PRIMERAPAGINA)
            && (posicio != Constants.TAULADEFIRMES_DARRERAPAGINA)
            && (posicio != Constants.TAULADEFIRMES_SENSETAULA)) {
          throw new Exception("posicio." + x + " esta fora de rang (" + posicio + ")");
        }

      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
      
      String langSign = signerContext.getContextParameter(Constants.APPLET_LANGUAGE_SIGN + sep + x);
      if (langSign == null || langSign.trim().length() == 0) {
        System.out.println(" Break langSign");
        break;
      }
      
      int signType;
      String signTypeStr = signerContext.getContextParameter(Constants.APPLET_SIGN_TYPE
          + sep + x);
      try {
        signType = Integer.parseInt(signTypeStr);
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
      
      
      int signAlgorithm;
      String signAlgorithmStr = signerContext.getContextParameter(Constants.APPLET_SIGN_ALGORITHM
          + sep + x);
      try {
        signAlgorithm = Integer.parseInt(signAlgorithmStr);
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }

      boolean signMode;
      String signModeStr = signerContext.getContextParameter(Constants.APPLET_SIGN_MODE
          + sep + x);
      if (signModeStr != null && signModeStr.trim().equals("true")) {
        signMode = true; // EXPLICIT
      } else {
        signMode = false; // IMPLICIT
      }
      
      
      SignBoxRectangle signBoxRectangle = null;

      String signBoxRectangleStr = signerContext.getContextParameter(Constants.APPLET_SIGN_BOX_RECTANGLE
          + sep + x);
      
      System.out.println("SignBoxrectangle = " + signBoxRectangleStr );
      
      if (signBoxRectangleStr != null) {
        signBoxRectangle = new SignBoxRectangle(signBoxRectangleStr);
      }
      
      firmes.add(new ProcessDeFirma(this, source, destination, idname, motiu,
          posicio, numFirmes, langSign, signType, signAlgorithm, signMode,
          signBoxRectangle, firmatPerFormat));
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
      PortaFIBAppletException pae = new PortaFIBAppletException(
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

  public void showErrorPanel(PortaFIBAppletException e) {

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
