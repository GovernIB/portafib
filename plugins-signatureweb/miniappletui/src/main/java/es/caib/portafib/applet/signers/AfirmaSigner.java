package es.caib.portafib.applet.signers;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.security.AccessController;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.UnrecoverableEntryException;
import java.util.List;

import es.gob.afirma.core.AOCancelledOperationException;
import es.gob.afirma.core.AOException;
import es.gob.afirma.core.AOFormatFileException;
import es.gob.afirma.keystores.AOCertificatesNotFoundException;
import es.gob.afirma.keystores.AOKeyStore;
import es.gob.afirma.keystores.AOKeyStoreDialog;
import es.gob.afirma.keystores.AOKeyStoreManager;
import es.gob.afirma.keystores.AOKeyStoreManagerFactory;
import es.gob.afirma.keystores.filters.CertificateFilter;
import es.caib.portafib.applet.MiniAppletConstants;
import es.caib.portafib.applet.BasePanel;
import es.caib.portafib.applet.ISigner;
import es.caib.portafib.applet.ParentPanel;
import es.caib.portafib.applet.SignerContext;
import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.signers.AOSignConstants;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.signers.AOSignerFactory;
import es.gob.afirma.keystores.filters.CertFilterManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.logging.Logger;


/**
 *
 * @author anadal
 *
 */
public class AfirmaSigner implements ISigner {

  boolean finalDeSelectCert = false;

  PrivateKeyEntry privateKeyEntry;

  PropertyResourceBundle bundleUI;

  boolean nohihacertificats = false;

  @Override
  public void init(PropertyResourceBundle bundleUI, SignerContext signerContext)
      throws Exception {    
    this.bundleUI = bundleUI;
  }

  @Override
  public byte[] sign(PropertyResourceBundle bundleSign, 
      InputStream input, 
      String signType, String signAlgorithm, Properties properties)
        throws IOException, Exception {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    {   
      int nRead;
      byte[] data = new byte[4*1024];
      while ((nRead = input.read(data, 0, data.length)) != -1) {
        baos.write(data, 0, nRead);
      }
      baos.flush();
    }

    String signatureRubricImage = properties.getProperty(MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE);
    
    System.out.println("signatureRubricImage ORIGINAL = " + signatureRubricImage);
    

      if (signatureRubricImage != null 
          && (signatureRubricImage.startsWith("http:") || signatureRubricImage.startsWith("https:"))) {
        final String url = signatureRubricImage;
        
        System.out.println("signatureRubricImage DESCARREGANT DE " + signatureRubricImage);
        
        try {
          byte[] image;
          image = downloadImage(url, this.privateKeyEntry.getCertificate().getEncoded());
          
          properties.setProperty(MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE,
            es.gob.afirma.core.misc.Base64.encode(image));
          
          
        } catch (final Exception e) {
           // TODO Traduir
           String msg = "Se ha producido un error durante la descarga de la imagen de rubrica"
              + " desde la URL " + url + ": " + e.getMessage();
           System.err.println(msg); //$NON-NLS-1$
           
           e.printStackTrace(System.err);
          // TODO Aqui hay que lanzar una excepción !!!!1
          // No podemos firmar con una rubrica incorrecta !!!!!
          throw new Exception(msg, e);
        }
        
      }
      


    String propertiesStr;
    {
      System.out.println("---------------- All Properties: ---------------- ");
      StringWriter writer = new StringWriter();
      properties.store(writer, "");

      propertiesStr = writer.getBuffer().toString();
      System.out.println(propertiesStr);
      System.out.println("------------------------------------------------- ");
    }
    
    
    Thread.sleep(4000);
    
    byte[] result =  sign(baos.toByteArray(),
        this.privateKeyEntry,
        signAlgorithm, 
        signType, //$NON-NLS-1$
        propertiesStr
    );
    
    // ===============================
   
    return result;
    
  }

 
   
  private byte[] downloadImage(String url, byte[] certificate) throws Exception  {
    
    
    String charset = "UTF-8";

    String CRLF = "\r\n"; // Line separator required by multipart/form-data.
    String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
    URLConnection connection = new URL(url).openConnection();
  
    connection.setDoOutput(true);
    connection.setDoInput(true);
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
  
  
    OutputStream output = connection.getOutputStream();
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
  
  
    // Send binary file.
    
    writer.append("--" + boundary).append(CRLF);
    writer.append("Content-Disposition: form-data; name=\"certificateFile\"; filename=\"certificate.cer\"").append(CRLF);
    writer.append("Content-Type: application/x-509-user-cert").append(CRLF);
    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    writer.append(CRLF).flush();
    output.write(certificate);
    output.flush(); // Important before continuing with writer!
    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
  
    // End of multipart/form-data.
    writer.append("--" + boundary + "--").append(CRLF).flush();
  
  
    // Request is lazily fired whenever you need to obtain information about response.
      
    int responseCode = ((HttpURLConnection) connection).getResponseCode();
    
    if (responseCode != 200) {
      throw new Exception("Retornado codigo de error " + responseCode 
          + " durante la descarga de la imagen.");
    }
    
    InputStream is = connection.getInputStream();
    
    return AOUtil.getDataFromInputStream(is);
    
    
  }
  
  
  

  public BasePanel getPanelSelectCertificate(ParentPanel parentPanel) {
    return new AfirmaSelectCertificate(parentPanel);
  }

  public boolean isSelectedCert() {
    return this.finalDeSelectCert;
  }
  
  
  public static byte[] toByteArray(InputStream input) throws IOException {

    ByteArrayOutputStream output = new ByteArrayOutputStream();

    copy(input, output);

    return output.toByteArray();

  }
  
  
  public static void copy(InputStream input, OutputStream output)
      throws IOException {
    byte[] buffer = new byte[4096];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
  }
  
  /*
  public static class OrFilter extends CertificateFilter {

    final List<CertificateFilter> filters;
    
    public OrFilter(List<CertificateFilter> filters) {
      this.filters = filters;
    }
    
    @Override
    public String[] matches(final String[] aliases, final KeyStoreManager ksm) {
      String[] filteredAliases = aliases.clone();
      for (final CertificateFilter filter : this.filters) {
        filteredAliases = filter.matches(filteredAliases, ksm);
      }
      return filteredAliases;
    }
    
    
    @Override
    public boolean matches(X509Certificate cert) {
      for (CertificateFilter certificateFilter : filters) {
        if (certificateFilter.matches(cert)) {
          return true;
        }
      }
      return false;
    }
    
  }
  
 
  public static class AndFilter extends CertificateFilter {

    final List<CertificateFilter> filters;
    
    public AndFilter(List<CertificateFilter> filters) {
      this.filters = filters;
    }
    
    
    @Override
    public String[] matches(final String[] aliases, final KeyStoreManager ksm) {
      String[] filteredAliases = aliases.clone();
      for (final CertificateFilter filter : this.filters) {
        filteredAliases = filter.matches(filteredAliases, ksm);
      }
      return filteredAliases;
    }
    
    
    @Override
    public boolean matches(X509Certificate cert) {
      
      for (CertificateFilter certificateFilter : filters) {
        if (!certificateFilter.matches(cert)) {
          return false;
        }
      }
      return true;
      
    }
    

  }
  */
  

  
  public class AfirmaSelectCertificate extends BasePanel implements Runnable {

    Thread thread;
    
    /**
     * 
     */
    public AfirmaSelectCertificate(ParentPanel parentPanel) {
      super(parentPanel);
      init();
    }

    public void init() {

      setLayout(new BorderLayout(4, 4));

      // Titol
      JLabel jLabel3 = new JLabel();
      jLabel3.setText(tradueix("selectCert"));
      add(jLabel3, BorderLayout.CENTER);

    }

    public void preShow() throws Exception {

      thread = new Thread(this);
      
      thread.start();
      
      repaint();
      setVisible(true);
    }

    public boolean next() {

      if (!finalDeSelectCert) {
        System.out.println("No s'ha seleccionat certificat");
        return false;
      }

      if (nohihacertificats) {
        // TODO traduir No hi ha certificats o no hi ha cap certificat que s`ajusti al filtre
        String msg = "No hi ha certificats o no hi ha cap certificat que s`ajusti al filtre";
        JOptionPane.showMessageDialog(null, msg, tradueix("error"), JOptionPane.WARNING_MESSAGE);
        return false;
      }

      if (privateKeyEntry == null) {
        // NO s'ha triat cap certificat
        JOptionPane.showMessageDialog(null, tradueix(("selectCert")), tradueix("error"),
            JOptionPane.WARNING_MESSAGE);
        return false;
      }
      
      System.out.println("Retorna NEXT true");
      
      return true;
    }
    
   

    public void run() {

      Properties params = new Properties();

      String filterURLEncoded = parentPanel.signerContext.getContextParameter(MiniAppletConstants.APPLET_CERTIFICATE_FILTER);
      StringBuffer filterLog = new StringBuffer("\n========== Z FILTER Z==========\n");
      if (filterURLEncoded != null) {
        String filter;
        try {
          filter = URLDecoder.decode(filterURLEncoded, "UTF-8");
        } catch (Exception e) {
          e.printStackTrace();
          filter = filterURLEncoded;
        }
        
        
        try {
          params.load(new StringReader(filter));
        } catch (IOException e) {
          e.printStackTrace();
        }

        filterLog.append(filter).append('\n');
      }
      filterLog.append("============================\n");
      System.out.println(filterLog.toString());
      
      StringBuffer filterLog2 = new StringBuffer("\n==========P FILTER P==========\n");
      for(Object key : params.keySet()) {
        filterLog2.append(key + "=" + params.getProperty((String)key)).append('\n');
      }
      
      filterLog2.append("============================\n");
      System.out.println(filterLog2.toString());

      for (int i = 0; i < 3; i++) {
        try {
         
            System.out.println("Crida a selectPrivateKey (intent = " + (i + 1) + ")");
            privateKeyEntry = selectPrivateKey(params, this);
            
            System.out.println("privateKeyEntry = " + privateKeyEntry);
            break;

        } catch (PrivilegedActionException e) {
          System.out.println("ERROR DE TIPUS PrivilegedActionException");
          e.printStackTrace();
          Throwable cause = e.getCause();
          if (cause != null && cause instanceof AOCertificatesNotFoundException) {
            System.out.println("   - No hi ha certificats !!!!");
            nohihacertificats = true;
            break;
          }
          
        } catch (Exception e) {
          System.out.println("ERROR DE TIPUS Exception");
          e.printStackTrace();
          // TODO Auto-generated catch block
          
          if (e instanceof AOCancelledOperationException) {
            System.out.println("   - Cancelat per l`usuari !!!!");
            JOptionPane.showMessageDialog(null, tradueix(("selectCert")), tradueix("error"),
                JOptionPane.WARNING_MESSAGE);
          } 
        }
      } // Final de for
      finalDeSelectCert = true;
      parentPanel.nextAction(); // Saltar automaticament a la pantalla de firmes
    }

  }
  
  

  public byte[] sign(final byte[] dataBinary, final PrivateKeyEntry privateKeyEntry,
      final String algorithm, final String format, final String extraParams)
      throws PrivilegedActionException, IOException, AOException {

    final Properties params = ExtraParamsProcessor.convertToProperties(extraParams);
    
    try {
      String signatureFormat = format;
      final es.gob.afirma.core.signers.AOSigner signer = selectSigner(
          cleanParam(signatureFormat), null);
      if (SIGNATURE_FORMAT_AUTO.equalsIgnoreCase(signatureFormat)) {
        signatureFormat = ExtraParamsProcessor.updateFormat(signer);
      }
      return AccessController.doPrivileged(new SignAction(signer, dataBinary, 
          cleanParam(algorithm), privateKeyEntry, // this.selectPrivateKey(params),
          ExtraParamsProcessor.expandProperties(params, dataBinary, signatureFormat)));
    } catch (final AOFormatFileException e) {
      //setError(e);
      throw e;
    } catch (final PrivilegedActionException e) {
      //setError(e);
      throw e;
    } catch (final RuntimeException e) {
      //setError(e);
      throw e;
    } catch (final OutOfMemoryError e) {
      //setError(e, "Error de falta de memoria durante la operaci\u00F3n de firma"); //$NON-NLS-1$
      throw e;
    } catch (final Error e) {
      //setError(e);
      throw new AOException("Ocurrio un error grave durante la operaci\u00F3n de firma", e); //$NON-NLS-1$
    }
  }

  /**
   * IMPORTANT !!!!!!!!!
   * 
   * Aquest mètode és una còpia del mètode selectPrivateKey de la 
   * classe es.gob.afirma.miniapplet.MiniAfirmaApplet.
   * A partir del moment en que aquesta classe sigui publica llavors s'utilitzarà la 
   * del MiniApplet i no aquesta còpia. 
   */
  
  /** Permite que el usuario seleccione un certificado del almac&eacute;n configurado, o
   * el por defecto si no se indic&oacute;, y devuelve su clave privada.
   * @param params Configuraci&oacute;n general establecida para la operaci&oacute;n.
   * @return Clave privada asociada al certificado seleccionado.
   * @throws PrivilegedActionException Cuando ocurre un error de seguridad.
   * @throws AOCancelledOperationException Cuando se cancela la operaci&oacute;n. */
  public PrivateKeyEntry selectPrivateKey(final Properties params, Component parent) throws PrivilegedActionException {

    System.out.println("ZZZZZZZZZZZZZZ  Entra dins selectPrivateKey");

    final SelectPrivateKeyAction selectPrivateKeyAction;
    //if (this.keystoreType == null) 
    {
      selectPrivateKeyAction = new SelectPrivateKeyAction(
          Platform.getOS(),
          Platform.getBrowser(null), //this.userAgent),
          new CertFilterManager(params),
          parent);
    }
    /*
    else {
      selectPrivateKeyAction = new SelectPrivateKeyAction(
          this.keystoreType,
          this.keystoreLib,
          new CertFilterManager(params),
          this);
    }
    */
    final PrivateKeyEntry keyEntry = AccessController.doPrivileged(selectPrivateKeyAction);
    
    return keyEntry;
  }

  /**
   * IMPORTANT !!!!!!!!!
   * 
   * Aquesta classe és una còpia de la classe es.gob.afirma.miniapplet.SelectPrivateKeyAction.
   * A partir del moment en que aquesta classe sigui publica llavors s'utilitzarà la 
   * del MiniApplet i no aquesta còpia. 
   */
  final class SelectPrivateKeyAction implements PrivilegedExceptionAction<PrivateKeyEntry> {

    private final AOKeyStore keyStore;
    private final Component parent;
    private final CertFilterManager filterManager;

    private String library = null;

    /** Crea la acci&oacute;n para la selecci&oacute;n de la clave privada de un certificado.
       * @param type Tipo de almac&eacute;n de certificados y claves privadas a usar.
       * @param lib Fichero asociado al almac&aacute;n (biblioteca din&aacute;mica en el caso de PKCS#11,
       *            fichero PFX en el caso de PKCS#12, archivo de llavero en el caso de llavero de
       *            Mac OS X, etc.
       * @param filterManager Manejador de filtros de certificados.
       * @param parent Componente padre para los di&aacute;logos que se
       * visualizan como parte de la acci&oacute;n. */
    SelectPrivateKeyAction(final AOKeyStore type,
                           final String lib,
                           final CertFilterManager filterManager,
                             final Component parent) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de almacen no puede ser nulo"); //$NON-NLS-1$
        }
        this.keyStore = type;
        this.filterManager = filterManager;
        this.parent = parent;
        this.library = lib;
    }

    /** Crea la acci&oacute;n para la selecci&oacute;n de la clave privada de un certificado.
     * @param os Sistema operativo actual.
     * @param browser Navegador web actual.
     * @param filterManager Manejador de filtros de certificados.
     * @param parent Componente padre para los di&aacute;logos que se
     * visualizan como parte de la acci&oacute;n. */
    SelectPrivateKeyAction(final Platform.OS os,
                           final Platform.BROWSER browser,
                           final CertFilterManager filterManager,
                           final Component parent) {
        if (browser == Platform.BROWSER.FIREFOX) {
            this.keyStore = AOKeyStore.MOZ_UNI;
        }
        else if (os == Platform.OS.WINDOWS) {
      this.keyStore = AOKeyStore.WINDOWS;
      }
      else if (os == Platform.OS.LINUX || os == Platform.OS.SOLARIS) {
        this.keyStore = AOKeyStore.MOZ_UNI;
      }
      else if (os == Platform.OS.MACOSX) {
        this.keyStore = AOKeyStore.APPLE;
      }
      else {
        this.keyStore = AOKeyStore.PKCS12;
      }
      this.filterManager = filterManager;
      this.parent = parent;
    }

    /** {@inheritDoc} */
    //@Override
    public PrivateKeyEntry run() throws KeyException,
                                        es.gob.afirma.keystores.AOKeystoreAlternativeException,
                                        AOException,
                                        UnrecoverableEntryException,
                                        NoSuchAlgorithmException,
                                        KeyStoreException,
                                        IOException {
      final AOKeyStoreManager ksm = AOKeyStoreManagerFactory.getAOKeyStoreManager(
        this.keyStore,
        this.library,
        null,
        this.keyStore.getStorePasswordCallback(this.parent),
        this.parent
      );

      boolean mandatoryCertificate = false;
      List<CertificateFilter> filters = null;
      if (this.filterManager != null) {
        filters = this.filterManager.getFilters();
        mandatoryCertificate = this.filterManager.isMandatoryCertificate();
      }
      

      final AOKeyStoreDialog dialog = new AOKeyStoreDialog(
          ksm,
          this,
          true,
          true,
          true,
          filters,
          mandatoryCertificate
          );
      
      dialog.show();

      ksm.setParentComponent(this.parent);
      return ksm.getKeyEntry(dialog.getSelectedAlias());

    }


  }


 
  
  private static final String SIGNATURE_FORMAT_AUTO = "AUTO"; //$NON-NLS-1$
  
  private static es.gob.afirma.core.signers.AOSigner selectSigner(final String format, final byte[] sign)
      throws es.gob.afirma.core.AOFormatFileException, PrivilegedActionException, IOException {
    final AOSigner signer;
    if (format != null && !SIGNATURE_FORMAT_AUTO.equalsIgnoreCase(format)) {
      signer = getSigner(format);
      if (signer == null) {
        throw new AOFormatFileException("El formato de firma indicado no esta soportado"); //$NON-NLS-1$
      }
    } else if (sign != null) {
      signer = getSigner(sign);
      if (signer == null) {
        throw new IllegalArgumentException(
            "Los datos introducidos no se corresponden con una firma soportada" //$NON-NLS-1$
        );
      }
    } else {
      // Si llegamos aqui es por no haberse indicado el formato de firma y que
      // no disponemos de
      // una firma del que tomarlo (por lo que no puede ser una multifirma)
      throw new IllegalArgumentException(
          "No se ha indicado el formato para la operacion de firma" //$NON-NLS-1$
      );
    }
    return signer;
  }

  /**
   * Recupera un manejador de firma compatible con el formato indicado. Si no se
   * encuentra uno compatible, se devuelve {@code null}.
   * 
   * @param format
   *          Nombre de un formato de firma.
   * @return Manejador de firma.
   * @throws PrivilegedActionException
   *           Cuando ocurre un problema de seguridad.
   */
  private static AOSigner getSigner(final String format) throws PrivilegedActionException {
    return AccessController.doPrivileged(new SelectSignerAction(format));
  }

  /**
   * Recupera un manejador de firma compatible con la firma indicada. Si no se
   * encuentra uno compatible, se devuelve {@code null}.
   * 
   * @param signature
   *          Firma electr&oacute;nica.
   * @return Manejador de firma.
   * @throws PrivilegedActionException
   *           Cuando ocurre un problema de seguridad.
   */
  private static AOSigner getSigner(final byte[] signature) throws PrivilegedActionException {
    return AccessController.doPrivileged(new SelectSignerAction(signature));
  }
  
  
  // Important !!! Demanar que sigui publica no ofuscada
  static final class SelectSignerAction implements PrivilegedExceptionAction<AOSigner> {

    private final String format;
    private final byte[] data;

    /**
     * Crea la acci&oacute;n para la obtenci&oacute;n de un manejador de firma compatible
     * con el formato de firma indicado.
     * @param format Formato de firma.
     */
    SelectSignerAction(final String format) {
        this.format = format;
        this.data = null;
    }

    /**
     * Crea la acci&oacute;n para la obtenci&oacute;n de un manejador de firma compatible
     * con la firma indicada.
     * @param data Firma electr&oacute;nica para la que se desea el manejador.
     */
    SelectSignerAction(final byte[] data) {
        this.data = (data != null ? data.clone() : null);
        this.format = null;
    }

    /**
     * Selecciona el manejador de firma adecuado para el formato o los datos indicados.
     * Si no se encuentra un manejador compatible, se devuelve {@code null}.
     * @return Manejador de firma.
     * @throws IOException Cuando se produce un error durante la lectura de los datos.
     */
  //@Override
  public AOSigner run() throws IOException {
    if (this.format != null) {
      return AOSignerFactory.getSigner(this.format);
    }
    else if (this.data != null) {
      return AOSignerFactory.getSigner(this.data);
    }
        return null;
  }
}

  /*
   * IMPORTANT !!!!! Això és una còpia de la classe es.gob.afirma.miniapplet.ExtraParamsProcessor
   * 
   * PROBLEMA: NO ES PUBLICA !!!!
   */
  
  static final class ExtraParamsProcessor {

    /** Tama&ntilde;o equivalente a 1 MegaBytes en bytes. */
    private static final int SIZE_1MB = 1024 * 1024;

    /** Clave expansible para pol&iacute;ticas de firma. */
    private static final String EXPANDIBLE_POLICY_KEY = "expPolicy"; //$NON-NLS-1$

    private ExtraParamsProcessor() {
      /* Constructor no publico */
    }

    /**
     * Transforma la entrada introducida en un properties.
     * Las entradas deben estar separadas por salto de l&iacute;nea y tener la forma
     * {@code CLAVE=VALOR} en donde CLAVE es el identificador del par&aacute;metro y
     * VALOR el valor asignado a este.
     * La CLAVE no puede contener ning&uacute;n signo igual ('=') ni empezar por
     * almohadilla ('#') y se ignorar&aacute;n aquellas entradas que no contengan
     * el signo igual en una posici&oacute;n la cadena distinta a la primera.
     * Si se introduce null se devuelve un Properties vac&iacute;o.
     * @param entries Listado de pares CLAVE - VALOR.
     * @return Properties con las claves indicadas cargadas como par&aacute;metro.
     */
    static Properties convertToProperties(final String entries) {

      final Properties params = new Properties();
      if (entries == null) {
        return params;
      }

      try {
        params.load(new ByteArrayInputStream(entries.getBytes()));
      } catch (final Exception e) {
        System.err.println( //$NON-NLS-1$
            "Se han encontrado entradas no validas en la configuracion de la operacion: " //$NON-NLS-1$
            + e);
        return params;
      }

      return params;
    }

    /**
     * Devuelve la colecci&oacute;n de propiedades de entrada con las entradas que correspondan
     * expandidos. Se expandiran una serie de claves con valores predefinidos y se les
     * asignar&aacute; el valor correspondiente.
     * Una vez expandidos, se eliminaran estos par&aacute;metros de la lista. Si el expandir
     * los par&aacute;metros implica establer otras propiedades y estas ya est&aacute;n
     * definidas en el Properties, prevalecer&aacute;n los valores expandidos.<br/>
     * Entre los par&aacute;metros clave se encuentran:
     * <ul>
       *  <li><b>expPolicy</b>: Configuracion de la pol&iacute;tica de firma. Posibles valores:
       *   <ul><li><b>FirmaAGE</b>:
       *    Establece los diversions par&aacute;metros para la configuraci&oacute;n de la
       *    pol&iacute;tica de firma de la AGE.
       *   </li>
       *   </ul>
       *  </li>
       * </ul>
     * @param params Par&aacute;metros definidos para la operaci&oacute;n.
     * @return Propiedades expandidas.
     */
    static Properties expandProperties(final Properties params) {
      return expandProperties(params, null, null);
    }

    /**
     * Devuelve la colecci&oacute;n de propiedades de entrada con las entradas que correspondan
     * expandidos. Se expandiran una serie de claves con valores predefinidos y se les
     * asignar&aacute; el valor correspondiente.
     * Una vez expandidos, se eliminaran estos par&aacute;metros de la lista. Si el expandir
     * los par&aacute;metros implica establer otras propiedades y estas ya est&aacute;n
     * definidas en el Properties, prevalecer&aacute;n los valores expandidos.<br/>
     * Entre los par&aacute;metros clave se encuentran:
     * <ul>
       *  <li><b>expPolicy</b>: Configuracion de la pol&iacute;tica de firma. Posibles valores:
       *   <ul><li><b>FirmaAGE</b>:
       *    Establece los diversions par&aacute;metros para la configuraci&oacute;n de la
       *    pol&iacute;tica de firma de la AGE.
       *   </li>
       *   </ul>
       *  </li>
       * </ul>
     * @param params Par&aacute;metros definidos para la operaci&oacute;n.
     * @param signedData Datos firmados.
     * @param format Formato de firma.
     * @return Propiedades expandidas.
     */
    static Properties expandProperties(final Properties params, final byte[] signedData, final String format) {

      final Properties p = new Properties();
      for (final String key : params.keySet().toArray(new String[0])) {
        p.setProperty(key, params.getProperty(key));
      }

      expandPolicyKeys(p, signedData, format);

      return p;
    }

    /**
     * Funci&oacute;n para obtener el nombre del formato de firma en base al manejador de firma.
     * @param signer Manejador de firma.
     * @return Nombre del formato de firma preferente del que se encarga el manejador o {@code null} si no
     * se reconoce.
     */
    static String updateFormat(final AOSigner signer) {

      //XXX: Utilizamos los nombres de las clases para evitar cargarlas pero habria que buscar un modo mejor
      final String signerClassname = signer.getClass().getName();
      if (signerClassname.equals("es.gob.afirma.signers.xades.AOXAdESSigner")) { //$NON-NLS-1$
        return AOSignConstants.SIGN_FORMAT_XADES;
      } else if (signerClassname.equals("es.gob.afirma.signers.cades.AOCAdESSigner")) { //$NON-NLS-1$
        return AOSignConstants.SIGN_FORMAT_CADES;
      } else if (signerClassname.equals("es.gob.afirma.signers.pades.AOPDFSigner")) { //$NON-NLS-1$
        return AOSignConstants.SIGN_FORMAT_PADES;
      } else if (signerClassname.equals("es.gob.afirma.signers.xades.AOFacturaESigner")) { //$NON-NLS-1$
        return AOSignConstants.SIGN_FORMAT_FACTURAE;
      }
      return null;
    }

    /**
     * Expande las propiedades de pol&iacute;tica de firma modificando el conjunto de propiedades.
     * @param p Propiedades configuradas.
     * @param signedData Datos firmados.
     * @param format Formato de firma.
     */
    static void expandPolicyKeys(final Properties p, final byte[] signedData, final String format) {
      if (p.containsKey(EXPANDIBLE_POLICY_KEY)) {

        if (PolicyPropertiesManager.POLICY_ID_AGE.equals(p.getProperty(EXPANDIBLE_POLICY_KEY))) {

          String normalizedFormat = null;
          if (format != null) {
            if (format.startsWith(AOSignConstants.SIGN_FORMAT_XADES)) {
              normalizedFormat = PolicyPropertiesManager.FORMAT_XADES;

              // La firma XAdES conforme a la politica de firma de la AGE debe ser Detached o Enveloped
              if (!AOSignConstants.SIGN_FORMAT_XADES_DETACHED.equals(p.getProperty("format")) && //$NON-NLS-1$
                  !AOSignConstants.SIGN_FORMAT_XADES_ENVELOPED.equals(p.getProperty("format"))) { //$NON-NLS-1$
                p.setProperty("format", AOSignConstants.SIGN_FORMAT_XADES_DETACHED); //$NON-NLS-1$
              }
            } else if (format.equals(AOSignConstants.SIGN_FORMAT_CADES)) {
              normalizedFormat = PolicyPropertiesManager.FORMAT_CADES;

              // La politica indica que la firma debe ser implicita siempre que el tamano
              // del documento sea razonable. Como no se especifica que tamano es razonable
              // respetaremos el modo indicado por el integrador. En caso de no haberlo
              // indicado, establecemos el limite en 1Mb. Esto solo aplicaria a CAdES ya que
              // PAdES siempre es implicita e ignora este parametro.
              if (!p.containsKey("mode") && signedData != null) { //$NON-NLS-1$
                p.setProperty("mode", signedData.length < SIZE_1MB ? //$NON-NLS-1$
                    AOSignConstants.SIGN_MODE_IMPLICIT : AOSignConstants.SIGN_MODE_EXPLICIT);
              }
            } else if (format.equals(AOSignConstants.SIGN_FORMAT_PDF) ||
                format.equals(AOSignConstants.SIGN_FORMAT_PADES) ||
                format.equals(AOSignConstants.SIGN_FORMAT_PDF_TRI)) {
              normalizedFormat = PolicyPropertiesManager.FORMAT_PADES;
            }
          }
          try {
            PolicyPropertiesManager.setProperties(p, PolicyPropertiesManager.POLICY_ID_AGE, normalizedFormat);
          } catch (final IOException e) {
            System.out.println("No se han encontrado podido cargar el fichero de propiedades: " + e); //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
        p.remove(EXPANDIBLE_POLICY_KEY);
      }
    }
  }
  
  
  
  
  
  
  
  private static String cleanParam(final String appletParam) {
    return appletParam == null || appletParam.trim().length() < 1 ?
        null : appletParam.trim();
  }
  
  
  /* Copia de MINIAPPLET: * es.gob.afirma.miniapplet.PolicyPropertiesManager: 
   * 
   * IMPORTANT: Si fan publica ExtraParamsProcessor i no la ofusquen llavors 
   * aquesta classe no es neceesaria
   */
static final class PolicyPropertiesManager {

  /** Identificador de la pol&iacute;tica de firma de la AGE. */
  static final String POLICY_ID_AGE = "FirmaAGE"; //$NON-NLS-1$

  static final String PROPERTY_POLICY_IDENTIFIER = "policyIdentifier"; //$NON-NLS-1$

  static final String PROPERTY_POLICY_HASH_ALGORITHM = "policyIdentifierHashAlgorithm"; //$NON-NLS-1$

  static final String PROPERTY_POLICY_HASH = "policyIdentifierHash"; //$NON-NLS-1$

  static final String PROPERTY_POLICY_QUALIFIER = "policyQualifier"; //$NON-NLS-1$

  static final String PROPERTY_POLICY_DESCRIPTION = "policyDescription"; //$NON-NLS-1$

  static final String FORMAT_CADES = "CAdES"; //$NON-NLS-1$

  static final String FORMAT_XADES = "XAdES"; //$NON-NLS-1$

  static final String FORMAT_PADES = "PAdES"; //$NON-NLS-1$

  private static final String BUNDLE_NAME = "policy"; //$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
      .getBundle(BUNDLE_NAME, Locale.getDefault());

    private PolicyPropertiesManager() {
        // No permitimos la instanciacion
    }


  /**
   * Establece las propiedades asociadas a una pol&iacute;tica de firma determinada por un identificador
   * y con los valores adecuados a un formato de firma particular.
   * @param prop Documento de propiedades al que se agregar&aacute;n las correspondientes a la
   *      pol&iacute;tica de firma.
   * @param policyId Identificador de la pol&iacute;tica. Si no se indica, se usar&aacute;n los valores
   *      gen&eacute;ricos configurados.
   * @param format Formato de firma particular al que deben corresponder los valores. Si no se indica se
   *      usar&aacute;n los gen&eacute;ricos de la pol&iacute;tica.
   * @throws IOException Cuando no se encuentra o no puede leerse el fichero de propiedades.
   */
  static void setProperties(final Properties prop, final String policyId, final String format) throws IOException {

    String value = getProperty(policyId, PROPERTY_POLICY_IDENTIFIER, format);
    if (value != null) {
      prop.setProperty(PROPERTY_POLICY_IDENTIFIER, value);
    }
    value = getProperty(policyId, PROPERTY_POLICY_HASH_ALGORITHM, format);
    if (value != null) {
      prop.setProperty(PROPERTY_POLICY_HASH_ALGORITHM, value);
    }
    value = getProperty(policyId, PROPERTY_POLICY_HASH, format);
    if (value != null) {
      prop.setProperty(PROPERTY_POLICY_HASH, value);
    }
    value = getProperty(policyId, PROPERTY_POLICY_QUALIFIER, format);
    if (value != null) {
      prop.setProperty(PROPERTY_POLICY_QUALIFIER, value);
    }
    value = getProperty(policyId, PROPERTY_POLICY_DESCRIPTION, format);
    if (value != null) {
      prop.setProperty(PROPERTY_POLICY_DESCRIPTION, value);
    }
  }

  /**
   * Recupera una propiedad de la configuraci&oacute;n establecida de pol&iacute;ticas de firma.
   * @param id Identificador de la pol&iacute;tica.
   * @param property Propiedad que se desea recuperar.
   * @param format Modificador relativo al formato de firma.
   * @return Valor de la propiedad de la pol&iacute;tica.
   */
  private static String getProperty(final String id, final String property, final String format) {

    if (property == null) {
      throw new IllegalArgumentException("No se ha indicado la propiedad de la politica de firma"); //$NON-NLS-1$
    }

    String key;
    if (id != null) {

      if (format != null) {
        key = id + "." + property + "." + format; //$NON-NLS-1$ //$NON-NLS-2$
        if (RESOURCE_BUNDLE.containsKey(key)) {
          return RESOURCE_BUNDLE.getString(key);
        }
      }
      key = id + "." + property; //$NON-NLS-1$
      if (RESOURCE_BUNDLE.containsKey(key)) {
        return RESOURCE_BUNDLE.getString(key);
      }
    }
    if (format != null) {
      key = property + "." + format; //$NON-NLS-1$
      if (RESOURCE_BUNDLE.containsKey(key)) {
        return RESOURCE_BUNDLE.getString(key);
      }
    }
    key = property;
    if (RESOURCE_BUNDLE.containsKey(key)) {
      return RESOURCE_BUNDLE.getString(key);
    }
    return null;
  }
}
  
  

final class SignAction implements PrivilegedExceptionAction<byte[]> {

  private final AOSigner signer;
  private final byte[] data;
  private final String algorithm;
  private final PrivateKeyEntry keyEntry;
  private final Properties extraParams;

  /**
   * Crea la acci&oacute;n para la firma de datos.
   * @param signer Manejador de firma.
   * @param data Datos que se desean firmar.
   * @param algorithm Algoritmo de firma.
   * @param keyEntry Clave privada de firma.
   * @param extraParams Datos adicionales para la configuraci&oacute;n de la firma.
   */
  SignAction(final AOSigner signer,
                    final byte[] data,
                    final String algorithm,
                    final PrivateKeyEntry keyEntry,
                    final Properties extraParams) {
    this.signer = signer;
    this.data = (data != null ? data.clone() : null);
    this.algorithm = algorithm;
    this.keyEntry = keyEntry;
    this.extraParams = extraParams;
  }

  /** {@inheritDoc} */
  //@Override
  public byte[] run() throws AOException, IOException {
    return this.signer.sign(this.data, this.algorithm, this.keyEntry.getPrivateKey(),
        this.keyEntry.getCertificateChain(), this.extraParams);
  }

}
  



/** Clase envoltorio para las clases de Sun para acceso al registro de Windows.
 * Garantizan la carga est&aacute;tica de la biblioteca nativa */
public static final class WinRegistryWrapper {

    private static final Logger LOGGER = Logger.getLogger("es.gob.afirma"); //$NON-NLS-1$

    static {

        if (Platform.getOS().equals(Platform.OS.WINDOWS)) {

          try {
            final File deploy = new File(Platform.getJavaHome() + "\\lib\\deploy.jar"); //$NON-NLS-1$
            if (deploy.exists()) {
              WinRegistryWrapper.addURL(deploy.toURI().toURL());
            }
            else {
              LOGGER.warning("No se ha podido localizar la biblioteca java de despliegue '" + deploy.getAbsolutePath() + "'");  //$NON-NLS-1$ //$NON-NLS-2$
            }
            }
            catch (final Exception e) {
                LOGGER.warning("No se ha podido agregar al path la biblioteca java de despliegue 'deploy.jar': " + e);  //$NON-NLS-1$
            }

            // Cargamos la libreria nativa 'deploy.dll'
            try {
                AOUtil.loadNativeLibrary(Platform.getJavaHome() + "\\bin\\deploy.dll"); //$NON-NLS-1$
            }
            catch (final Exception e) {
                LOGGER.warning("No se ha podido cargar la libreria de despliegue 'deploy.dll': " + e);  //$NON-NLS-1$
            }
        }
    }

    private WinRegistryWrapper() {
        // No permitimos la instanciacion
    }

    /** Clave <i>HKEY_LOCAL_MACHINE</i> del registro de Windows. */
    public static final int HKEY_LOCAL_MACHINE = 0x80000002;

    /** Clave <i>HKEY_CURRENT_USER</i> del registro de Windows. */
    public static final int HKEY_CURRENT_USER = 0x80000001;

    /** Clave <i>HKEY_CLASSES_ROOT</i> del registro de Windows. */
    public static final int HKEY_CLASSES_ROOT = 0x80000000;

    /** Obtiene una clave del registro de Windows.
     * @param hKey
     *        Ra&iacute;z de la clave a obtener del registro
     * @param path
     *        Ruta de la clave a obtener
     * @param name
     *        Nombre de la clave a obtener
     * @return Valor de la clave obtenida, <i>null</i> si no se encontr&oacute;
     *         la clave o si se produjeron errores durante la obtenci&oacute;n */
    public static Object get(final int hKey, final String path, final String name) {

        try {
          final Method getMethod = classForName("com.sun.deploy.util.WinRegistry") //$NON-NLS-1$
          .getDeclaredMethod("get", new Class[] { Integer.TYPE, String.class, String.class }); //$NON-NLS-1$
            return getMethod.invoke(null, new Object[] { Integer.valueOf(hKey), path, name });
        }
        catch(final NoSuchMethodError e) {
            LOGGER.severe(
            "No se ha podido obtener la clave de registro por una discordancia de version con deploy.dll, se devolvera null: " + e //$NON-NLS-1$
            );
        }
        catch (final InvocationTargetException e) {
          LOGGER.severe(
                "Error al invocar mediante reflexion al metodo get(int, String, String) de WinRegistry, se devolvera null: " + e //$NON-NLS-1$
                );
    }
        catch (final Exception e) {
            LOGGER.severe(
            "No se ha podido obtener la clave de registro con ruta '" + path //$NON-NLS-1$
                 + "' y nombre '" //$NON-NLS-1$
                 + name
                 + "', se devolvera null: " //$NON-NLS-1$
                 + e
             );
        }
        return null;
    }

    /** Obtiene una clave de tipo cadena de texto del registro de Windows.
     * @param hKey
     *        Ra&iacute;z de la clave a obtener del registro
     * @param path
     *        Ruta de la clave a obtener
     * @param name
     *        Nombre de la clave a obtener
     * @return Texto correspondiente a la clave obtenida, <i>null</i> si no se
     *         encontr&oacute; la clave o si se produjeron errores durante la
     *         obtenci&oacute;n */
    public static String getString(final int hKey, final String path, final String name) {
        try {
          final Method getStringMethod = classForName("com.sun.deploy.util.WinRegistry") //$NON-NLS-1$
        .getDeclaredMethod("getString", new Class[] { Integer.TYPE, String.class, String.class }); //$NON-NLS-1$
          return (String) getStringMethod.invoke(null, new Object[] { Integer.valueOf(hKey), path, name });
        }
        catch (final InvocationTargetException e) {
          LOGGER.severe(
                "Error al invocar mediante reflexion al metodo getString(int, String, String) de WinRegistry, se devolvera null: " + e //$NON-NLS-1$
                );
    }
        catch (final Exception e) {
            LOGGER.severe("No se ha podido obtener la clave de registro con ruta '" + path  //$NON-NLS-1$
                                                     + "' y nombre '" //$NON-NLS-1$
                                                     + name
                                                     + "', se devolvera null: " //$NON-NLS-1$
                                                     + e);
        }
        return null;
    }

    /** Establece un valor textual a una clave del registro de Windows. Si la
     * clave no existe la crea
     * @param hKey
     *        Ra&iacute;z de la clave a establecer en el registro
     * @param path
     *        Ruta de la clave
     * @param name
     *        Nombre de la clave
     * @param value
     *        Valor textual que se desea dar a la clave
     * @return <code>true</code> si se estableci&oacute; el valor, <code>false</code> si no se pudo establecer */
    public static boolean setStringValue(final int hKey, final String path, final String name, final String value) {
        try {
          final Method setStringValueMethod = classForName("com.sun.deploy.util.WinRegistry") //$NON-NLS-1$
        .getDeclaredMethod("setStringValue", new Class[] { Integer.TYPE, String.class, String.class, String.class }); //$NON-NLS-1$
          return ((Boolean) setStringValueMethod.invoke(null, new Object[] { Integer.valueOf(hKey), path, name, value })).booleanValue();
        }
        catch (final InvocationTargetException e) {
          LOGGER.severe(
                "Error al invocar mediante reflexion al metodo setStringValue(int, String, String, String) de WinRegistry, se devolvera false: " + e //$NON-NLS-1$
                );
    }
        catch (final Exception e) {
            LOGGER.severe("No se ha podido establecer la clave de registro con ruta '" + path //$NON-NLS-1$
                                                     + "' y nombre '" //$NON-NLS-1$
                                                     + name
                                                     + "', se devolvera false: " //$NON-NLS-1$
                                                     + e);
        }
        return false;
    }

    /** Agrega una URL al PATH de bibliotecas de Java.
     * @param u URL que se desea agregar al PATH.
     * @throws IOException Cuando ocurre un error al agregar la ruta. */
  private static void addURL(final URL u) throws IOException {
    final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    final Class<?> sysclass = URLClassLoader.class;
    try {
      final Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class }); //$NON-NLS-1$
      method.setAccessible(true);
      method.invoke(sysloader, new Object[] { u });
    }
    catch (final Exception e) {
      throw new IOException(
        "No se ha podido anadir la URL al classloader( " + u + "): " + e, e //$NON-NLS-1$ //$NON-NLS-2$
      );
    }
  }
}


/** Carga una clase excluyendo de la ruta de b&uacute;squeda de clases las URL que no correspondan con JAR.
 * @param className Nombre de la clase a cargar
 * @return Clase cargada
 * @throws ClassNotFoundException cuando no se encuentra la clase a cargar
 */
public static Class<?> classForName(final String className) throws ClassNotFoundException {
    getCleanClassLoader().loadClass(className);
    return Class.forName(className);
}

/** Obtiene un ClassLoader que no incluye URL que no referencien directamente a ficheros JAR.
 * @return ClassLoader sin URL adicionales a directorios sueltos Web
 */
public static ClassLoader getCleanClassLoader() {
    return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
  @Override
  public ClassLoader run() {
        ClassLoader classLoader = AOUtil.class.getClassLoader();
        if (classLoader instanceof URLClassLoader && !classLoader.getClass().toString().contains("sun.plugin2.applet.JNLP2ClassLoader")) { //$NON-NLS-1$
          final List<URL> urls = new ArrayList<URL>();
          for (final URL url : ((URLClassLoader) classLoader).getURLs()) {
            if (url.toString().endsWith(".jar")) { //$NON-NLS-1$
              urls.add(url);
            }
          }
          classLoader = new URLClassLoader(urls.toArray(new URL[0]));
        }
        return classLoader;
  }
});
}




//Important !!!!! Això és una còpia de la classe es.gob.afirma.core.misc.Platform

/*  AQUESTA CLASSE ES PUBLICA PERO LES ENUMERACIONS OS I BROWSER estan ofuscades */


/** Clase para la identificaci&oacute;n de la plataforma Cliente y
* extracci&oacute;n de datos relativos a la misma. */
public static final class Platform {

 private static final Logger LOGGER = Logger.getLogger("es.gob.afirma"); //$NON-NLS-1$

 /** Sistema operativo. */
 public enum OS {
     /** Microsoft Windows. */
     WINDOWS,
     /** Linux. */
     LINUX,
     /** Sun Solaris / Open Solaris. */
     SOLARIS,
     /** Apple Mac OS X. */
     MACOSX,
     /** Google Android. */
     ANDROID,
     /** Sistema operativo no identificado. */
     OTHER
 }

 /** Navegador Web. */
 public enum BROWSER {
     /** Microsoft internet Explorer. */
     INTERNET_EXPLORER,
     /** Mozilla Firefox. */
     FIREFOX,
     /** Google Chrome. */
     CHROME,
     /** Apple Safari. */
     SAFARI,
     /** Opera. */
     OPERA,
     /** Navegador Web no identificado. */
     OTHER
 }

 /** Sistema operativo. */
 private static OS os = null;

 /** Arquitectura de la m&eacute;quina virtual java. */
 private static String javaArch = null;

 /** Directorio de Java. */
 private static String javaHome = null;

 /** Directorio de bibliotecas de Java. */
 private static String javaLibraryPath = null;

 /** Directorio del usuario. */
 private static String userHome = null;

 /** Constructor bloqueado. */
 private Platform() {
     // No permitimos la instanciacion
 }

 private static OS recoverOsName() {

     final String osName = System.getProperty("os.name"); //$NON-NLS-1$

     if (osName.contains("indows")) { //$NON-NLS-1$
         return OS.WINDOWS;
     }
     else if (osName.contains("inux")) { //$NON-NLS-1$
         if ("Dalvik".equals(System.getProperty("java.vm.name"))) { //$NON-NLS-1$ //$NON-NLS-2$
             return OS.ANDROID;
         }
         return OS.LINUX;
     }
     else if (osName.contains("SunOS") || osName.contains("olaris")) { //$NON-NLS-1$ //$NON-NLS-2$
         return OS.SOLARIS;
     }
     else if (osName.startsWith("Mac OS X")) { //$NON-NLS-1$
         return OS.MACOSX;
     }
     else {
         LOGGER.warning("No se ha podido determinar el sistema operativo"); //$NON-NLS-1$
         return OS.OTHER;
     }

 }

 /** Recupera el navegador web actual.
  * @param userAgent <i>UserAgent</i> del navegador Web
  * @return Navegador web correspondiente al UserAgent indicado. */
 public static BROWSER getBrowser(final String userAgent) {
     if (userAgent == null) {
         return BROWSER.OTHER;
     }
     else if (userAgent.toLowerCase(Locale.US).contains("msie")) { //$NON-NLS-1$
         return BROWSER.INTERNET_EXPLORER;
     }
     else if (userAgent.toLowerCase(Locale.US).contains("firefox")) { //$NON-NLS-1$
         return BROWSER.FIREFOX;
     }
     else if (userAgent.toLowerCase(Locale.US).contains("chrome")) { //$NON-NLS-1$
         return BROWSER.CHROME;
     }
     else if (userAgent.toLowerCase(Locale.US).contains("safari")) { //$NON-NLS-1$
         // CUIDADO: Chrome incluye la cadena "safari" como parte de su
         // UserAgent
         return BROWSER.SAFARI;
     }
     else if (userAgent.toLowerCase(Locale.US).contains("opera")) { //$NON-NLS-1$
         return BROWSER.OPERA;
     }
     else { // Cualquier otro navegador
         return BROWSER.OTHER;
     }
 }

 /** Recupera el sistema operativo de ejecuci&oacute;n.
  * @return Sistema operativo actual. */
 public static Platform.OS getOS() {
     if (os == null) {
         os = recoverOsName();
     }
     return os;
 }

 /** Recupera la arquitectura de la JVM en ejecuci&oacute;n seg&uacute;n las
  * propiedades de Java.
  * @return Arquitectura de la JVM. */
 public static String getJavaArch() {
     if (javaArch == null) {
       try {
           javaArch = System.getProperty("sun.arch.data.model"); //$NON-NLS-1$
           if (javaArch == null) {
               javaArch = System.getProperty("com.ibm.vm.bitmode"); //$NON-NLS-1$
           }
       }
       catch(final Exception e) {
         LOGGER.severe(
         "No se ha podido determinar si la JVM es de 32 o 64 bits, se asume que es 32: " + e //$NON-NLS-1$
     );
         return "32"; //$NON-NLS-1$
       }
     }
     return javaArch;
 }

 /** Recupera la ruta del directorio de instalaci&oacute;n de Java.
  * @return Ruta del directorio de instalaci&oacute;n de Java. */
 public static String getJavaHome() {
     if (javaHome == null) {
         javaHome = recoverJavaHome();
     }
     return javaHome;
 }

 /** Recupera la propiedad Path de Java.
  * @return Propiedad en el Path de Java. */
 public static String getJavaLibraryPath() {
     if (javaLibraryPath == null) {
         javaLibraryPath = System.getProperty("java.library.path"); //$NON-NLS-1$
     }
     return javaLibraryPath;
 }

 /** Recupera la ruta del directorio personal del usuario del sistema
  * operativo (no incluye nunca una barra final).
  * @return Ruta del directorio del usuario. */
 public static String getUserHome() {
     if (userHome == null) {
         userHome = System.getProperty("user.home"); //$NON-NLS-1$
     }
     return userHome;
 }

 /** Obtiene el directorio de instalaci&oacute;n del entorno de
  * ejecuci&oacute;n de Java actualmente en uso. Si no se puede obtener, se
  * devolver&aacute; {@code null}. Copiado de com.sun.deploy.config.Config.
  * @return Directorio del entorno de ejecuci&oacute;n de Java. */
 private static String recoverJavaHome() {
     String ret = null;
     try {
         ret = System.getProperty("jnlpx.home"); //$NON-NLS-1$
     }
     catch (final Exception e) {
         // Se ignora, puede que no haya permisos para leerla
     }
     if (ret != null) {
         return ret.substring(0, ret.lastIndexOf(File.separator));
     }

     try {
         return System.getProperty("java.home"); //$NON-NLS-1$
     }
     catch (final Exception e) {
         LOGGER.warning("No se ha podido identificar el directorio de java"); //$NON-NLS-1$
     }

     return null;
 }

 /** Obtiene el directorio principal del sistema operativo del sistema.
  * @return Directorio principal del sistema operativo */
 private static String getSystemRoot() {
     if (!Platform.getOS().equals(Platform.OS.WINDOWS)) {
         return File.separator;
     }
     String systemRoot = System.getProperty("SystemRoot"); //$NON-NLS-1$
     if (systemRoot == null) {
         final String defaultSystemRoot = "C:\\WINDOWS"; //$NON-NLS-1$
         final File winSys32 = new File(defaultSystemRoot + "\\SYSTEM32"); //$NON-NLS-1$
         if (winSys32.exists() && winSys32.isDirectory()) {
             return defaultSystemRoot;
         }
     }
     if (systemRoot == null) {
         LOGGER
         .warning("No se ha encontrado el directorio ra&iacute;z del sistema, se devolver&aacute;: " + File.separator); //$NON-NLS-1$
         systemRoot = File.separator;
     }
     return systemRoot;
 }

 /** Devuelve el directorio principal de bibliotecas del sistema.
  * @return Directorio principal de bibliotecas
  */
 public static String getSystemLibDir() {
     if (Platform.getOS().equals(Platform.OS.WINDOWS)) {
         String systemRoot = getSystemRoot();
         if (systemRoot == null) {
             LOGGER .warning("No se ha podido determinar el directorio de Windows accediendo al registro, se usara 'C:\\WINDOWS\\'"); //$NON-NLS-1$
             systemRoot = "c:\\windows\\"; //$NON-NLS-1$
         }
         if (!systemRoot.endsWith("\\")) { //$NON-NLS-1$
             systemRoot += "\\"; //$NON-NLS-1$
         }
         return systemRoot + "System32"; //$NON-NLS-1$
     }
     return "/usr/lib"; //$NON-NLS-1$
 }

}








}
