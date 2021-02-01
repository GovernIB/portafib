package org.fundaciobit.plugins.signatureserver.miniappletutils;

import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractTriFaseSigner extends MiniAppletClassLoader  {
  
  // final PdfSignResult pre
  protected Object pre = null;
  
  // Original file with TimeStamp
  protected byte[] data;
  
  

  public byte[] fullSign(final byte[] inPDF, final String algorithm,
      final java.security.cert.Certificate[] certChain, final Properties xParams)
      throws Exception {

    // *************** PreFirma
    byte[] presign = step1_PreSign(inPDF, algorithm, certChain, xParams); 
    
    // *************** Firma
    final byte[] interSign = step2_signHash(algorithm, presign);


    // *************** PostFirma
    return step3_PostSign(algorithm, certChain, xParams, interSign);

  }



  public byte[] step3_PostSign(final String algorithm,
      final java.security.cert.Certificate[] certChain, final Properties xParams,
      final byte[] interSign) throws Exception {
    final Properties extraParams = xParams != null ? xParams : new Properties();
    final java.security.cert.Certificate[] certificateChain = Boolean.parseBoolean(extraParams
        .getProperty("includeOnlySignningCertificate", Boolean.FALSE.toString())) ? //$NON-NLS-1$
    new X509Certificate[] { (X509Certificate) certChain[0] }
        : certChain;
    
    // byte[] result = PAdESTriPhaseSigner.postSign(algorithm, data, certificateChain, interSign, pre, null, null);
    byte[] result = invoke_PAdESTriPhaseSigner_postSign(algorithm, data, certificateChain, interSign, pre, null, null);
    return result;
  }
  
  
  
  public byte[] step1_PreSign(final byte[] inPDF,  final String algorithm,
      final java.security.cert.Certificate[] certChain, final Properties xParams) throws Exception {
    final Properties extraParams = xParams != null ? xParams : new Properties();

    // TODO checkIText();

    final java.security.cert.Certificate[] certificateChain = Boolean.parseBoolean(extraParams
        .getProperty("includeOnlySignningCertificate", Boolean.FALSE.toString())) ? //$NON-NLS-1$
    new X509Certificate[] { (X509Certificate) certChain[0] }
        : certChain;

    final GregorianCalendar signTime = new GregorianCalendar();

    
    //  --------------- ORIGINAL
    // Sello de stiempo
    /*
    byte[] data;
    data = es.gob.afirma.signers.pades.PdfTimestamper.timestampPdf(inPDF, extraParams, signTime);

    // Prefirma
    final PdfSignResult pre;

    pre = PAdESTriPhaseSigner
        .preSign(algorithm, data, certificateChain, signTime, extraParams);

    final byte[] interSign = signHash(algorithm, pre.getSign());

    // Postfirma
    return PAdESTriPhaseSigner.postSign(algorithm, data, certificateChain, interSign, pre,
        null, null);
    */


    
    
    // *************** Sello de tiempo
    

    // data = es.gob.afirma.signers.pades.PdfTimestamper.timestampPdf(inPDF, extraParams, signTime);
    this.data = invoke_PdfTimestamper_timestampPdf(inPDF, extraParams, signTime);
    
    
    // *************** Prefirma
    // final PdfSignResult pre = PAdESTriPhaseSigner.preSign(algorithm, data, certificateChain, signTime, extraParams);
    // final byte[] presign = pre.getSign();
    
    this.pre = invoke_PAdESTriPhaseSigner_preSign(algorithm, data, certificateChain, signTime, extraParams);
    byte[] presign = invoke_PdfSignResult_getSign(pre);
    
    return presign;
  }
  
  
  

  public abstract byte[] step2_signHash(final String algorithm, final byte[] hash) throws Exception;
  
  
  // ---------------------------------------------
  
  
  
  
  public byte[] invoke_PdfTimestamper_timestampPdf(final byte[] inPDF,
      final Properties extraParams,
      final Calendar signTime) throws Exception { 
    
    Class<?> PdfTimestamper = loadClass("es.gob.afirma.signers.pades.PdfTimestamper");
    
    Method method;
    method = PdfTimestamper.getDeclaredMethod("timestampPdf",
        byte[].class, Properties.class, Calendar.class);
    
    return (byte[])method.invoke(null, inPDF, extraParams, signTime);
     //data = PdfTimestamper.timestampPdf(inPDF, extraParams, signTime);
  }
  
  /**
   * 
   // final PdfSignResult pre = PAdESTriPhaseSigner.preSign(algorithm, data, certificateChain, signTime, extraParams);
   // final byte[] presign = pre.getSign();
   * 
   * 
   * @param algorithm
   * @param inPDF
   * @param signerCertificateChain
   * @param signTime
   * @param xParams
   * @return
   * @throws Exception
   */
  public Object invoke_PAdESTriPhaseSigner_preSign(final String algorithm,
      final byte[] inPDF,
      final Certificate[] signerCertificateChain,
      final GregorianCalendar signTime,
      final Properties xParams) throws Exception {
    
    /* No BORRAR indica el codi de la cridada a invoke
    PdfSignResult preSign(final String digestAlgorithmName,
        final byte[] inPDF,
        final Certificate[] signerCertificateChain,
        final GregorianCalendar signTime,
        final Properties xParams) throws IOException,
                                         AOException,
                                         DocumentException {
    */
      Class<?> PAdESTriPhaseSigner = loadClass("es.gob.afirma.signers.pades.PAdESTriPhaseSigner");

      //System.out.println(" PAdESTriPhaseSigner class = " + PAdESTriPhaseSigner);
      
      Method method = getMethod(PAdESTriPhaseSigner, "preSign");
      
      //System.out.println(" PAdESTriPhaseSigner.preSigg  method = " + method);

      Object pre = method.invoke(null, algorithm, inPDF, signerCertificateChain, signTime, xParams);
      
      return pre;
      
  }
  
  
  public byte[] invoke_PdfSignResult_getSign(Object pre) throws Exception {
      
      // ---- PART 2
      
      Class<?> PdfSignResult = loadClass("es.gob.afirma.signers.pades.PdfSignResult");
      
      Method method2 = getMethod(PdfSignResult, "getSign");
      
      return (byte[])method2.invoke(pre, new Object[]{});
      
  }


  
  
  
  protected byte[] invoke_PAdESTriPhaseSigner_postSign(final String digestAlgorithmName,
      final byte[] inPdf,
      final Certificate[] signerCertificateChain,
      final byte[] pkcs1Signature,
      final Object preSign, // PdfSignResult
      final Object enhancer, // SignEnhancer
      final Properties enhancerConfig) throws Exception {
    
    // byte[] result = PAdESTriPhaseSigner.postSign(algorithm, data, certificateChain, interSign, pre, null, null);
  
    Class<?> PAdESTriPhaseSigner = loadClass("es.gob.afirma.signers.pades.PAdESTriPhaseSigner");

    
    Method method = getMethod(PAdESTriPhaseSigner, "postSign");
    
    byte[] result;
    result =  (byte[])method.invoke(null, digestAlgorithmName, inPdf, signerCertificateChain, pkcs1Signature, preSign, enhancer, enhancerConfig);
    return result;
  }
  
  /*
  
   public static byte[] postSign(final String digestAlgorithmName,
                                  final byte[] inPdf,
                                  final Certificate[] signerCertificateChain,
                                  final byte[] pkcs1Signature,
                                  final PdfSignResult preSign,
                                  final SignEnhancer enhancer,
                                  final Properties enhancerConfig) throws AOException,
  
  */
  
  /*
  public Object invokePreSign() {
    
    
  }
  */

}
