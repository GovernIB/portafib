package es.caib.portafib.applet;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.swing.DefaultBoundedRangeModel;

import org.fundaciobit.plugins.utils.XTrustProvider;


/**
 * 
 * @author anadal
 *
 */
public class ProcessDeFirma extends Thread {

  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  private EstatProcesDeFirma estat = EstatProcesDeFirma.NO_INICIAT;

  private MiniAppletException error = null;

  private InputStream inputPDF = null;
  
  private File tmpFile = null;

  private final String source;

  private final String destination;
  
  private final String errorPage;
  
  private final String idName;

  private final ParentPanel parentPanel;
  
  private final String signType;
  private final String signAlgorithm;
  
  private final Properties properties;
  
  
  private boolean processat = false;


  private final DefaultBoundedRangeModel lecturaProgress = new DefaultBoundedRangeModel(0, 0,
      0, 100);

  private final DefaultBoundedRangeModel escripturaProgress = new DefaultBoundedRangeModel(0,
      0, 0, 100);
  
  private PropertyResourceBundle bundleSign;

  private static String SYNC ="SYNC";

  /**
   * @param parentpanel
   * @param source
   * @param destination
   */
  public ProcessDeFirma(ParentPanel parentpanel, String source, String destination,
      String errorPage, String idName, String signType, String signAlgorithm,  Properties properties) {
    super();
    this.parentPanel = parentpanel;
    this.source = source;
    this.destination = destination;
    this.errorPage = errorPage;
    this.properties = properties;
    this.idName = idName;
    
    synchronized (SYNC) {
       System.out.println(" --------------- " + idName + " --------------");
       StringWriter writer = new StringWriter();
       properties.list(new PrintWriter(writer));

       System.out.println("this.source = " + source);
       System.out.println("this.destination = " + destination);
       System.out.println("this.errorPage = " + errorPage);
       System.out.println("MiniApplet Properties:");
       System.out.println(writer.getBuffer().toString());
    }
    

    this.signType = signType;
    this.signAlgorithm = signAlgorithm;

    bundleSign = this.parentPanel.getBundleUI();

  }
  

  public PropertyResourceBundle getBundleSign() {
    return bundleSign;
  }

  public InputStream llegirPDF(String source, DefaultBoundedRangeModel lecturaProgress)
      throws Exception {
    System.out.println("llegirPDF::Inicialitzant SSL.");
    initSSL();
    URLConnection sourceConnection;
    URL sourceURL;
    System.out.println("llegirPDF::Crear URL.");
    try {
      sourceURL = new URL(source);
    } catch (MalformedURLException e) {
      throw new Exception(tradueix("url_incorrecta", source),e);
    }
    System.out.println("llegirPDF::OpenConnection.");
    try {
      sourceConnection = sourceURL.openConnection();
    } catch (IOException e1) {
      throw new Exception(tradueix("url_inaccesible", source),e1);
    }
    System.out.println("llegirPDF::connect().");
    try {
      sourceConnection.connect();
    } catch (Exception e2) {
      throw new Exception(tradueix("url_connect_error",source), e2);
    }

    System.out.println("llegirPDF::checkErrors().");
    // Revisar si hi ha error i extreure el msg d'error
    if (sourceConnection instanceof  HttpURLConnection) {
      
      HttpURLConnection c = (HttpURLConnection) sourceConnection;      
      String msg = c.getHeaderField("MSGPORTAFIB");
      if (msg == null) {
         msg = c.getResponseMessage();
      }
      System.out.println("Connection Code = " + c.getResponseCode());
      System.out.println("Connection Msg. = " + msg);
      if (c.getResponseCode() != 200) {
        // TODO traduir
        throw new Exception(msg);
      }
    }

    // CHECK contentType == PDF
    String contentType = sourceConnection.getContentType();
    // TODO Només fer aquest check si la firma es PADES
    if (contentType == null || !contentType.startsWith(MiniAppletConstants.PDF_MIME_TYPE)) {
      // Si és text/html llavors imprimir el contingut per veure l'error
      if (contentType != null && contentType.startsWith("text/html")) {
        try {
          java.io.InputStream is = sourceConnection.getInputStream();
          int c;
          while((c = is.read()) != -1) {
            System.err.print((char)c);
          }
          System.err.println();
        } catch(Throwable e) {
        }
      }
      throw new Exception(tradueix("error_content_type",source, contentType));
    }

    List<String> values = sourceConnection.getHeaderFields().get("content-Length");
    int size = -1;
    if (values != null && !values.isEmpty()) {

      String sLength = (String) values.get(0);

      System.out.println("content-Length == " + sLength);

      if (sLength != null) {
        size = Integer.parseInt(sLength);
      }
    } else {
      System.out.println("content-Length NO EXISTTEIX");
    }
    

    

    // Llegir dades
    try {

      java.io.InputStream is = sourceConnection.getInputStream();
      if (size == -1) {
        int len = is.available();
        if (len != 0) {
          size = len;
        }
      }

      int nTotalBytesInStream = ((size == -1) ? DEFAULT_BUFFER_SIZE * 10 : size);
      if (nTotalBytesInStream < 1) {
        nTotalBytesInStream = 1;
      }
      lecturaProgress.setMaximum(nTotalBytesInStream);

      byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
      int bytesRead;
      int nBytesReceived = 0;

      OutputStream baos;
      try {
        tmpFile = File.createTempFile("portafib_applet_", ".tmp");
        System.out.println("Creat fitxer temporal a " + tmpFile.getAbsolutePath());
        tmpFile.deleteOnExit();
        baos = new FileOutputStream(tmpFile);
      } catch (IOException e) {
        System.err.println("No he pogut creat un fitxer temporal");
        baos = new ByteArrayOutputStream();
      }
      

      while ((bytesRead = is.read(buffer)) != -1) {
        baos.write(buffer, 0, bytesRead);
        nBytesReceived += bytesRead;
        lecturaProgress.setValue(nBytesReceived);
                
        // TODO
        if (size == -1) {
          // Mirar si nTotalBytesInStream < nBytesReceived si es així
          // incrementar
          // el maxim lecturaProgress.setMaximum(XX)
        }
      }

      if (tmpFile == null) {
        return new ByteArrayInputStream(((ByteArrayOutputStream)baos).toByteArray());
      } else {
        FileOutputStream fos = (FileOutputStream)baos;
        fos.flush();
        fos.close();
        return new FileInputStream(tmpFile);
      }

      // TODO falta listener
      // return IOUtils.toByteArray(sourceConnection.getInputStream());
    } catch (Exception e) {
      throw new Exception(tradueix("error_llegint", e.getMessage()), e);
    }

  }

  long firmaTime = 0;

  @Override
  public void run() {
    System.out.println("Arranca thread de ProcessDeFirma");
    try {
      this.estat = EstatProcesDeFirma.LLEGINT_PDF;
      System.out.println("Llegir PDF");
      this.inputPDF = llegirPDF(this.source, this.lecturaProgress);
      this.estat = EstatProcesDeFirma.ESPERA_SELECCIO_CERTIFICAT;
      System.out.println("BUCLE SELECTCERT - INICI");
      ISigner signer = this.parentPanel.getSigner(); 
      while (!signer.isSelectedCert()) {
        Thread.sleep(500);
      }
      System.out.println("BUCLE SELECTCERT - FINAL");
      this.estat = EstatProcesDeFirma.FIRMANT;
      System.out.println("FIRMANT ");
      firmaTime = System.currentTimeMillis();

      this.firmar();

      this.estat = EstatProcesDeFirma.FINAL;
      System.out.println("FINAL");
      
      try {
        if (tmpFile != null) {
          tmpFile.delete();
        }
      } catch(Throwable t) {
          t.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
      this.error = new MiniAppletException(tradueix("error_firmant", ""), e.getMessage() , e);
      this.estat = EstatProcesDeFirma.ERROR;
    }

  }

 

  public void firmar() throws Exception {
    // Generar la firma
    URL url;
    System.out.println("Pujar fitxer resultant a: " + destination);
    try {
      url = new URL(destination);
    } catch (Exception e) {
      throw new Exception(tradueix("error_url_desti", destination),e);
    }

    OutputStream os;
    URLConnection uc;
    String boundary = null;
    try {
      uc = url.openConnection();

      if (uc instanceof HttpURLConnection) {
        HttpURLConnection c = (HttpURLConnection) uc;
        c.setRequestMethod("GET");   
        c.setDoInput(true);
        c.setDoOutput(true);

        //c.setRequestMethod("POST");

        // XYZ Veure si es mes eficient
        //c.connect();
        
        boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        
        c.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        os = c.getOutputStream();

      } else {
        File f = new File(new URL(destination).getFile());
        @SuppressWarnings("resource")
        FileOutputStream fos = new FileOutputStream(f);
        os = fos;
      }
    } catch (Exception e2) {
      throw new Exception(tradueix("error_desti", destination),e2);
    }

    FilterOutput fio = new FilterOutput(os, this.inputPDF.available());

    try {
      //firmarPDF(this.bundleSign,parentPanel.getSigner(), this.inputPDF, fio, this.idName,
      //    signType, signAlgorithm, properties);
      byte[] signed;

      signed = parentPanel.getSigner().sign(bundleSign, this.inputPDF, signType, signAlgorithm, properties);

      if (uc instanceof HttpURLConnection) {
        final String formName = "destination";
        final String fileName = "destination.file";
        final String mime = "application/octet-stream";
        // Send binary file.
        final  String CRLF = "\r\n";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"" + formName + "\"; filename=\"" + fileName+ "\"").append(CRLF);
        writer.append("Content-Type: ").append(mime).append(CRLF);
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        os.write(signed);
        os.flush(); // Important before continuing with writer!
        writer.append(CRLF); // CRLF is important! It indicates end of boundary.
        // End of multipart/form-data.
        writer.append("--" + boundary + "--").append(CRLF).flush();
        writer.flush();
        writer.close();
        
      } else {
        
        os.write(signed);
        
      }
      
      
      
      
    } catch (Throwable e) {
      throw new Exception(tradueix("error_firma", e.getMessage()),e);
    } finally {
      try {
        fio.flush();
        fio.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    escripturaProgress.setMaximum(100);
    escripturaProgress.setValue(100);

    if (uc != null && uc instanceof HttpURLConnection) {
      
      System.out.println("Esperant a final de comunicacio: " + System.currentTimeMillis());
      
      int code = ((HttpURLConnection)uc).getResponseCode();
      
      System.out.println("Codi HTTP: "  + code);

      try {
        InputStream is = uc.getInputStream();
        int cc;
        while( (cc = is.read()) != -1 ) {
          System.out.print((char)cc);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        System.out.println();
        System.out.println("Final de comunicacio: " + System.currentTimeMillis());
      }

      HttpURLConnection c = (HttpURLConnection) uc;      
      String msg = c.getHeaderField("MSGPORTAFIB");
      if (msg == null) {
         msg = c.getResponseMessage();
      }
      System.out.println("Connection Code = " + c.getResponseCode());
      System.out.println("Connection Msg. = " + msg);
      if (c.getResponseCode() != 200) {
        // TODO traduir
        throw new IOException(msg);
      }
    }
    
    try {
        os.close();  
    } catch (Exception e) {
    }
    
  }


  public DefaultBoundedRangeModel getLecturaProgress() {
    return lecturaProgress;
  }

  public DefaultBoundedRangeModel getEscripturaProgress() {
    return escripturaProgress;
  }

  public String getSource() {
    return source;
  }

  public MiniAppletException getError() {
    return error;
  }

  public EstatProcesDeFirma getEstat() {
    return estat;
  }


  public boolean isProcessat() {
    return processat;
  }


  public void setProcessat(boolean jaProcessat) {
    this.processat = jaProcessat;
  }


  public String getIdName() {
    return idName;
  }




  public String getErrorPage() {
    return errorPage;
  }




  public class FilterOutput extends FilterOutputStream {

    int readBytes;

    int totalBytes;

    int expectedOutputBytes;

    /**
     * @param out
     * @param out2
     */
    public FilterOutput(OutputStream out, int readBytes) {
      super(out);
      this.readBytes = readBytes;
      expectedOutputBytes = readBytes;
      escripturaProgress.setMaximum(expectedOutputBytes);
    }

    private void process(int numbytes) {
      if (totalBytes == 0) {
        System.out.println("Sign Time: "
            + (System.currentTimeMillis() - firmaTime) + " ms");

        estat = EstatProcesDeFirma.GUARDANT_PDF;
      }
      totalBytes = totalBytes + numbytes;
      if ((expectedOutputBytes * 0.95) > ((double) totalBytes)) {
        expectedOutputBytes = expectedOutputBytes + readBytes;
        escripturaProgress.setMaximum(expectedOutputBytes);
      }
      escripturaProgress.setValue(totalBytes);     
    }

    public void write(int b) throws IOException {
      out.write(b);
      process(1);
    }

    public void write(byte[] b) throws IOException {
      out.write(b, 0, b.length);
      process(b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
      out.write(b, off, len);
      process(len);
    }
  }
  
  /*
  public synchronized static void firmarPDF(PropertyResourceBundle bundleSign,
      ISigner signer,InputStream input, OutputStream outStream, String idname,
      String signType, String signAlgorithm, Properties properties) throws 
      IOException, Exception {

    //PdfReader reader = new PdfReader(new ByteArrayInputStream(input));
    //AcroFields acroFields = reader.getAcroFields();
    //ArrayList<?> signatureNames = acroFields.getSignatureNames();
    //int num_firmes = signatureNames.size();


    System.out.println(" FIRMA: " + idname);

    // A4 210 x 297
    // AMPLADA 595 - ALT 842

//    final float width = 482 - 30;
//    final int marge = (int)(0.51f * 72f);
//    int alt = marge + Constants.APPLET_STARTSIGNTABLE + Constants.APPLET_HEIGHTSIGNBOX * (num_firma -1);
//    //System.out.println("Inches: " + inches);
//    final float top = -1.0f * alt;
//    //System.out.println("PDF: " + t);
//    final float left = 76 + 30;
//
//    final float height = Constants.APPLET_HEIGHTSIGNBOX;
    
    
    signer.sign(bundleSign, input, outStream, signType, signAlgorithm, properties);
        //top, left, height, width);
    
    
  }
  */
  
  public static boolean sslInitialized = false;

  public static synchronized void initSSL() {
    if (!sslInitialized) {
      XTrustProvider.install();
      sslInitialized = true;
    }
  }
  
  
  public final String tradueix(String code, String... params) {
    return I18NUtils.traduiex(parentPanel.getBundleUI(), code, params);
  }
  
  
  @Override
  protected void finalize() throws Throwable {
      try {
          if (tmpFile != null) {
            tmpFile.delete();
          }
      } catch(Throwable t) {
          t.printStackTrace();
      } finally {
          System.out.println("Calling finalize of Super Class");
          super.finalize();
      }
    
  }



}
