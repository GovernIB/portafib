package es.caib.portafib.applet.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import es.caib.portafib.applet.SignApplet;
import es.caib.portafib.applet.SignerContext;
import es.caib.portafib.utils.Constants;


/**
 * 
 * @author anadal
 *
 */
public class TestApplet {
  
  public static Map<Integer, String> signBoxRectangle = new HashMap<Integer, String>();
  
  static {
    signBoxRectangle.put(1, "106,664,555,713");
    signBoxRectangle.put(2, "106,610,555,659");
    signBoxRectangle.put(3, "106,556,555,605");
    signBoxRectangle.put(4, "106,502,555,551");
    signBoxRectangle.put(5, "106,448,555,497");
    signBoxRectangle.put(6, "106,394,555,443");
    signBoxRectangle.put(7, "106,340,555,389");
    signBoxRectangle.put(8, "106,286,555,335");
    signBoxRectangle.put(9, "106,232,555,281");
    signBoxRectangle.put(10, "106,178,555,227");
    signBoxRectangle.put(11, "106,124,555,173");
    signBoxRectangle.put(12, "106,70,555,119");
  }

  
@SuppressWarnings("deprecation")
public static void main(String[] args) {
    
    File file = new File("./web/hola_amb_taula.pdf");
    
    boolean openSelectionFileDialog = false;
   
    try {
      JFileChooser fileChooser;
    
      if (args.length == 0) {      
        fileChooser = new JFileChooser(new File("./web"));
      } else {
        fileChooser = new JFileChooser(new File(args[0]));
      }

      Properties prop = new Properties();
      
      for (int x = 0; x < 1; x++) {

        if (openSelectionFileDialog || ( !openSelectionFileDialog && file == null)) {
          if (JFileChooser.CANCEL_OPTION == fileChooser.showOpenDialog(null)) {
            return;
          }        
          file = fileChooser.getSelectedFile();
        }
        String fileUrl = file.toURL().toExternalForm();
  
        String extension;
  
        String dest;
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
          extension = file.getName().substring(i + 1);
          dest = fileUrl.substring(0, fileUrl.length() - extension.length() - 1) + "_firmat."
              + extension;
        } else {
          dest = fileUrl + "_firmat";
          extension = "";
        }
  
        int signNumber = 12;
        int signType = Constants.TIPUSFIRMA_PADES;
        int locationSignTable = Constants.TAULADEFIRMES_PRIMERAPAGINA;
        
        
        if (signType == Constants.TIPUSFIRMA_PADES &&
            ( locationSignTable == Constants.TAULADEFIRMES_PRIMERAPAGINA 
                || locationSignTable == Constants.TAULADEFIRMES_DARRERAPAGINA) ) {
          prop.put(Constants.APPLET_SIGN_BOX_RECTANGLE + "_" + x , signBoxRectangle.get(signNumber));
        }
        
       
        System.out.println(" src =  " + fileUrl);
        prop.put(Constants.APPLET_SOURCE + "_" + x, fileUrl);
  
        System.out.println(" dst =  " + dest);
        prop.put(Constants.APPLET_DESTINATION + "_" + x, dest);
        
        prop.put(Constants.APPLET_IDNAME + "_" + x, file.getName());
        
        prop.put(Constants.APPLET_SIGN_NUMBER + "_" + x, String.valueOf(signNumber));

        prop.put(Constants.APPLET_REASON + "_" + x , "Firma Antoni Nadal Bennasar "
            + "(43096845C) per delegació de Antoni Nadal Bennasar (43096845C). Motiu: "
            + "123456789 123456789 123456789 123456789 123456789 123456789A"); // 60 chars

        prop.put(Constants.APPLET_LANGUAGE_SIGN + "_" + x, "ca");

        prop.put(Constants.APPLET_LOCATION_SIGN_TABLE + "_" + x, String.valueOf(locationSignTable));

        prop.put(Constants.APPLET_SIGN_TYPE + "_" + x, "" + signType);
        prop.put(Constants.APPLET_SIGN_ALGORITHM + "_" + x, "" + Constants.APPLET_SIGN_ALGORITHM_SHA1WITHRSA); 

        final String implicit = "false";
        prop.put(Constants.APPLET_SIGN_MODE + "_" + x, implicit);
      }

      prop.put(Constants.APPLET_LANGUAGE_UI , "ca");

      // Politica de Firma
      /*
      prop.put(Constants.APPLET_POLICYIDENTIFIER, "2.16.724.1.3.1.1.2.1.8");
      prop.put(Constants.APPLET_POLICYIDENTIFIERHASHALGORITHM,"SHA1");
      prop.put(Constants.APPLET_POLICYIDENTIFIERHASH, "7SxX3erFuH31TvAw9LZ70N7p1vA=");
      prop.put(Constants.APPLET_POLICYURLDOCUMENT, "http://administracionelectronica.gob.es/es/ctt/politicafirma/politica_firma_AGE_v1_8.pdf");
      */
      
      
      

      // Class<?> signerClass = es.caib.portafib.applet.signers.IBKeySigner.class;
      Class<?> signerClass = es.caib.portafib.applet.signers.AfirmaSigner.class; 


      prop.put(Constants.APPLET_SIGNERCLASS, signerClass.getName() );

      if (signerClass.getName().endsWith("IBKeySigner")) {
        prop.put(Constants.APPLET_CERTIFICATE_FILTER, 
          URLEncoder.encode(new File("./web/signatura_api.properties").toURL()
          .toExternalForm()));
      }

      if (signerClass.getName().endsWith("AfirmaSigner")) {


         StringBuffer filter = new StringBuffer();
         
         // Filtre per SerialNumber del Certificat
         //filter.append("ssl:02\n");
         
         // Filtre dnie
         //filter.append("filter=dnie:\n");

         
         //filter.append("checkexpiration:\n");
         
         //filter.append("rfc2254_issuer:")
         //   .append("|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA)(OU=FNMT Clase 2 CA))")
         //   .append("\r\n");         
         // (cn=FundacioBit-OTAE)

         filter.append("rfc2254_rec_issuer:")
            .append("|(CN= ANF Root CA)(OU= ANF Root CA)(CN=ANF Root 1 CA)(OU=ANF Root 1 CA)(CN=ANF Server CA)(OU=ANF Server CA)(CN=AC RAIZ DNIE)(OU=AC RAIZ DNIE)(CN=Chambers of Commerce Root)(OU=Chambers of Commerce Root)(CN=AC Camerfirma Certificados Camerales)(OU=AC Camerfirma Certificados Camerales)(CN=FNMT Clase 2 CA)(OU=FNMT Clase 2 CA)(CN=Autoridad de Certificacion Firmaprofesional CIF A62634068)(OU=Autoridad de Certificacion Firmaprofesional CIF A62634068)(CN=Autoridad de Certificacion de la Abogacia)(OU=Autoridad de Certificacion de la Abogacia)(CN=ANCERT Certificados CGN)(OU=ANCERT Certificados CGN)(CN=ANCERT Certificados Notariales)(OU=ANCERT Certificados Notariales)(CN=AC CAMERFIRMA AAPP)(OU=AC CAMERFIRMA AAPP)(CN=AC RAIZ FNMT-RCM)(OU=AC RAIZ FNMT-RCM)(CN=ANCERT Certificados CGN V2)(OU=ANCERT Certificados CGN V2)(CN=ANCERT Certificados Notariales V2)(OU=ANCERT Certificados Notariales V2)(CN=Chambers of Commerce Root - 2008)(OU=Chambers of Commerce Root - 2008)(CN=AC Administración Pública)(OU=AC Administración Pública)")
            .append("\r\n");

         //filter.append("rfc2254_subject:SERIALNUMBER=43096845C");
         // ou=OTAE
         // cn=Delegat deFundacioBit
         // (&(cn=Antoni Nadal Bennasar*))
         // SERIALNUMBER=43096845C
         
         
         //filter.append("sign_cert_usage:").append("\n");
         
         // # DNI-E (AUTENTICACION) 2.16.724.1.2.2.2.4
         // # DNI-E (FIRMA) 2.16.724.1.2.2.2.3
         //filter.append("policyid:").append("2.16.724.1.2.2.2.4,2.16.724.1.2.2.2.3").append("\r\n");

         // # CAMERFIRMA DSCF GENERADA POR TITULAR 1.3.6.1.4.1.17326.10.9.2.2.2
         // # Camerfirma certificado de sello de organo policy.advanced.21=1.3.6.1.4.1.17326.1.3.3.2
         //filter.append("policyid:").append("1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2").append("\r\n");

         //filter.append("policyid:1.3.6.1.4.1.17326.10.9.2.1.1,1.3.6.1.4.1.17326.10.9.2.2.2,2.16.724.1.2.2.2.3,1.3.6.1.4.1.13177.10.1.5.1,1.3.6.1.4.1.13177.10.1.2.1,1.3.6.1.4.1.13177.10.1.1.1,1.3.6.1.4.1.16533.10.5.1,1.3.6.1.4.1.16533.10.3.1,1.3.6.1.4.1.16533.10.2.1,1.3.6.1.4.1.18920.1.1.1.1,1.3.6.1.4.1.18920.1.1.2.1,1.3.6.1.4.1.18920.1.3.1.1,1.3.6.1.4.1.18920.1.3.2.1,1.3.6.1.4.1.18920.4.1.1.1,1.3.6.1.4.1.17326.1.3.3.2,1.3.6.1.4.1.5734.3.14,1.3.6.1.4.1.5734.3.3.4.4.1,1.3.6.1.4.1.18920.1.1.1.2.1,1.3.6.1.4.1.18920.1.1.2.2.1,1.3.6.1.4.1.18920.1.3.2.2.1,1.3.6.1.4.1.18920.4.1.1.2.1,1.3.6.1.4.1.17326.10.9.2.1.1,1.3.6.1.4.1.17326.10.9.2.1.2,1.3.6.1.4.1.17326.10.9.2.2.1,2.16.724.1.2.2.2.4,1.3.6.1.4.1.5734.3.5,,1.3.6.1.4.1.16533.10.5.1,1.3.6.1.4.1.16533.10.3.1,1.3.6.1.4.1.16533.10.2.1,1.3.6.1.4.1.13177.10.1.5.1,1.3.6.1.4.1.13177.10.1.2.1,1.3.6.1.4.1.13177.10.1.1.1,1.3.6.1.4.1.13177.10.1.2.2,1.3.6.1.4.1.13177.10.1.1.2,1.3.6.1.4.1.18920.1.1.1.1,1.3.6.1.4.1.18920.1.1.2.1,1.3.6.1.4.1.18920.1.3.1.1,1.3.6.1.4.1.18920.1.3.2.1,1.3.6.1.4.1.18920.4.1.1.1,1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2,1.3.6.1.4.1.22896.2.1.1,1.3.6.1.4.1.18332.3.4,1.3.6.1.4.1.18332.3.3,1.3.6.1.4.1.18332.2.4,1.3.6.1.4.1.5734.3.14,1.3.6.1.4.1.5734.3.3.4.4.1,1.3.6.1.4.1.5734.3.3.4.4.2,1.3.6.1.4.1.22896.2.1.3,1.3.6.1.4.1.18920.1.1.1.2.1,1.3.6.1.4.1.18920.1.1.1.2.2,1.3.6.1.4.1.18920.1.1.2.2.1,1.3.6.1.4.1.18920.1.1.2.2.2,1.3.6.1.4.1.18920.1.3.2.2.1,1.3.6.1.4.1.18920.1.3.2.2.2,1.3.6.1.4.1.18920.4.1.1.2.1,1.3.6.1.4.1.18920.4.1.1.2.2").append("\n");


        if (filter.length() != 0) {
          prop.put(Constants.APPLET_CERTIFICATE_FILTER, URLEncoder.encode(filter.toString()));
        }
         
      }

      /*
      
      String f = "#ssl:  Serial Number of Certificate" + "\n"
      + "checkexpiration:true" + "\n"
      + "#policyid:1.3.6.1.4.1.17326.10.9.2.2.2,1.3.6.1.4.1.17326.1.3.3.2" + "\n"
      + "#sign_cert_usage:  (sense valor) nom?s certificats per firmar" + "\n"
      + "#rfc2254_subject:(&(cn=Antoni Nadal Bennasar*))" + "\n"
      + "rfc2254_issuer:|(cn=AC RAIZ DNIE)(cn=AC Camerfirma Certificados Camerales)(cn=AC CAMERFIRMA AAPP)(cn=EC-ACC)(cn=FundacioBit-OTAE)(cn=IBIT_CA))";

      prop.put(Constants.APPLET_CERTIFICATE_FILTER, Base64.encode(f));

      String raros = "Ààí\nöçl·l";
      String encoded = URLEncoder.encode(raros);
      System.out.println("Encoded["+ raros + "] = " + encoded);
      
      System.out.println("Decoded["+ URLDecoder.decode(encoded) + "]");
      */
      
 

      SignApplet applet = new SignApplet(new StandaloneSignerContext(prop));
      applet.init();

      JFrame frame = new JFrame("Applet de Firma 1.0");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

      JRootPane panell = frame.getRootPane();

      panell.setLayout(new BorderLayout());
      panell.add(applet, BorderLayout.CENTER);
      
      applet.start();
      
      
      final int WIDTH = 475;
      final int HEIGHT = 300;
      applet.setSize(WIDTH, HEIGHT);
      applet.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      applet.setMinimumSize(new Dimension(WIDTH, HEIGHT));
      

      // Get the size of the screen
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      
      frame.pack();

      // Determine the new location of the window
      int w = frame.getSize().width;
      int h = frame.getSize().height;
      int x = (dim.width - w) / 2;
      int y = (dim.height - h) / 2;

      // Move the window
      frame.setLocation(x, y);

      frame.setVisible(true);
    } catch (Throwable e) {
      e.printStackTrace();      
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

  }

  public static class StandaloneSignerContext implements SignerContext {

    private final Properties standaloneParameters;

    /**
     * @param standaloneParameters
     */
    public StandaloneSignerContext(Properties standaloneParameters) {
      super();
      this.standaloneParameters = standaloneParameters;
    }

    public String getContextParameter(String key) {
      return standaloneParameters.getProperty(key);
    }

    public void showURL(URL url) {
      // TODO Auto-generated method stub
      try {
        obreNavegador(url.toString());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    public static void obreNavegador(String url) throws IOException {

      // Obrim el visor d'URLS per defecte (si no és un navegador,
      // no s'obrirà al navegador)

      if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) // si
        // es
        // windows
        Runtime.getRuntime().exec(new String[] { "cmd", "/C", "start", url });
      else if (System.getProperty("os.name").toLowerCase().indexOf("linux") != -1) // si
        // es
        // linux
        Runtime.getRuntime().exec(new String[] { "/usr/bin/firefox", url });
      else if (System.getProperty("os.name").toLowerCase().indexOf("mac os x") != -1) // si
        // es
        // mac
        Runtime.getRuntime().exec(new String[] { "open ", url });

    }

    public String checkURL(String url) {
      
      return url;
    }

  }
}
