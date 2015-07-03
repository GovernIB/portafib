package es.caib.portafib.applet.signers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import es.caib.portafib.applet.ActualitzarLlibreriaException;
import es.caib.portafib.applet.BasePanel;
import es.caib.portafib.applet.I18NUtils;
import es.caib.portafib.applet.ISigner;
import es.caib.portafib.applet.ParentPanel;
import es.caib.portafib.applet.SignerContext;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.SignBoxRectangle;
import es.caib.signatura.api.SignatureCertNotFoundException;
import es.caib.signatura.api.SignaturePrivKeyException;
import es.caib.signatura.api.Signer;
import es.caib.signatura.api.SignerFactory;
import es.caib.signatura.api.UpgradeNeededException;


/**
 * 
 * @author anadal
 *
 */
public class IBKeySigner implements ISigner {

  
  private es.caib.signatura.api.Signer signerInstance = null;
  
  
  private PanelSelectCertificate panelSelectCertificate = null;
  
  public String selectedCert;

  public String password;
  
  public IBKeySigner() {    
  }
  
  @Override
  public void init(PropertyResourceBundle bundle,
      SignerContext signerContext) throws Exception, ActualitzarLlibreriaException {

    SignerFactory sf = null;
    @SuppressWarnings("deprecation")
    String signaturaPropertiesServletURL = URLDecoder.decode(signerContext
        .getContextParameter(Constants.APPLET_CERTIFICATE_FILTER));
    
    signaturaPropertiesServletURL = signerContext.checkURL(signaturaPropertiesServletURL);
    
    es.caib.signatura.api.Signer signer;
    try {
      if (signaturaPropertiesServletURL != null) {
        Properties p = new Properties();
        p.load(new URL(signaturaPropertiesServletURL).openStream());
        sf = new SignerFactory(p);

      } else {
        // En la CAIB nos conviene mejor esto
        sf = new SignerFactory();

        // En proyectos que no sean de CAIB conviene mejor esto
        // showMessageError("Error en la configuración de la aplicación: configuración de las propiedades de firma");
      }

      signer = sf.getSigner();
    } catch (UpgradeNeededException e) {
      e.printStackTrace();
      String msg = bundle.getString("upgradeneeded");

      System.out.println(" SSSSSSSSS " + msg);

      throw new ActualitzarLlibreriaException(msg, e);

    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new Exception(I18NUtils.traduiex(bundle,"error_config", e.getMessage()), e);
    } catch (IOException e) {
      throw new Exception(I18NUtils.traduiex(bundle,"error_config", e.getMessage()), e);
    }

    if (signer == null) {
      throw new Exception(I18NUtils.traduiex(bundle,"NoProvider"));
    }

    this.signerInstance = signer;
  }
  
  @Override
  public void sign(PropertyResourceBundle bundleSign, 
      InputStream input, OutputStream outStream,  String reason,
      int signType, int signAlgorithm, boolean signMode,
      int location_page, SignBoxRectangle signBoxRectangle, String firmatPerFormat)
          throws IOException, Exception {

    
      final float rot = 0;
      float top = 0;
      float left = 0;
      float height = 0;
      float width = 0;


      int position;
      if (location_page == Constants.TAULADEFIRMES_SENSETAULA) {
        position = Signer.PDF_SIGN_POSITION_NONE;
      } else {
        if (location_page == Constants.TAULADEFIRMES_PRIMERAPAGINA) {
          position = Signer.PDF_SIGN_DEFAULT_SIGNATURE_APPERANCE;
        } else {          
          position = Signer.PDF_SIGN_DEFAULT_SIGNATURE_APPERANCE
            | Signer.PDF_SIGN_PAGE_LAST;     
        }
        top = signBoxRectangle.ury;
        left = signBoxRectangle.urx;
        height = Math.abs(signBoxRectangle.ury - signBoxRectangle.lly);
        width = Math.abs(signBoxRectangle.urx - signBoxRectangle.llx);
        left = left - 30;
        width = width + 30;
      }

      System.out.println("Inici de Firma");
      signerInstance.signPDF(input, outStream, this.selectedCert, this.password,
          Constants.PDF_MIME_TYPE, reason, position, top, left,
          height, width, rot, true);

      System.out.println("Final de Firma");
  
  }
  
  
  public BasePanel getPanelSelectCertificate(ParentPanel parentPanel) {
    if (panelSelectCertificate == null) {
      this.panelSelectCertificate = new PanelSelectCertificate(parentPanel);
    }
    return this.panelSelectCertificate;
  }
  
  
  
  public class PanelSelectCertificate extends BasePanel {

    private JTextField passwordText = new JPasswordField(11);

    private JList list = new JList();

    /**
     * 
     */
    public PanelSelectCertificate(ParentPanel parentPanel) {
      super(parentPanel);
      init();
    }

    public void init() {

      setLayout(new BorderLayout(4, 4));

      // Titol
      JLabel jLabel3 = new JLabel();
      jLabel3.setText(tradueix("selectCert"));
      add(jLabel3, BorderLayout.NORTH);

      // Llista
      JScrollPane scrollPanel = new JScrollPane(list);    
      add(scrollPanel, BorderLayout.CENTER);
      scrollPanel.getViewport().add(list, null);

      // Contrasenya
      JPanel sud = new JPanel();
      sud.setBackground(Color.white);
      JLabel passwordLabel = new JLabel();
      passwordLabel.setText(tradueix("insertPassword"));
      sud.add(passwordLabel, BorderLayout.EAST);
      sud.add(passwordText, BorderLayout.CENTER);

      add(sud, BorderLayout.SOUTH);

    }

    public void preShow() throws Exception {

      String[] certList = { tradueix("certListNotFound") };

      try {
        certList = signerInstance.getCertList(Constants.PDF_MIME_TYPE);
      } catch (SignaturePrivKeyException ex) {
        ex.printStackTrace();
        throw new Exception(tradueix("certListNotFound"),ex);
      } catch (SignatureCertNotFoundException cex) {
        cex.printStackTrace();
        throw new Exception(tradueix("certListNotFound"), cex);
      }

      if (certList != null && certList.length != 0) {
        list.setListData(certList);
        if (certList.length == 1) {
          list.setSelectedIndex(0);
          passwordText.requestFocus();
        } else {
          list.requestFocus();
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        hasNextPanel = true;
      }

      repaint();
      setVisible(true);
    }

    public boolean next() {

      String selectCert = (String) list.getSelectedValue();

      if (selectCert == null) {
        JOptionPane.showMessageDialog(null, tradueix(("selectCert")), tradueix("error"),
            JOptionPane.WARNING_MESSAGE);
        return false;
      }

      setSelectedCert(selectCert);

      setPassword(passwordText.getText());

      return true;
    }

  }
  
  
  public String getSelectedCert() {
    return selectedCert;
  }

  public void setSelectedCert(String selectedCert) {
    this.selectedCert = selectedCert;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isSelectedCert() {
    return this.selectedCert != null;
  }



  
}
