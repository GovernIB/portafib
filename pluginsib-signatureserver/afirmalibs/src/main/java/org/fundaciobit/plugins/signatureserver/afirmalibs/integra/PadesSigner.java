package org.fundaciobit.plugins.signatureserver.afirmalibs.integra;

import com.aowagie.text.pdf.AcroFields;
import com.aowagie.text.pdf.PdfDictionary;
import com.aowagie.text.pdf.PdfName;
import com.aowagie.text.pdf.PdfNumber;
import com.aowagie.text.pdf.PdfObject;
import com.aowagie.text.pdf.PdfPKCS7;
import com.aowagie.text.pdf.PdfReader;
import com.aowagie.text.pdf.PdfSignature;
import com.aowagie.text.pdf.PdfSignatureAppearance;
import com.aowagie.text.pdf.PdfStamper;
import com.aowagie.text.pdf.PdfString;
import org.apache.log4j.Logger;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.tsp.TimeStampToken;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class PadesSigner {
  
  
  public static Logger LOGGER = Logger.getLogger(PadesSigner.class);
  

  public PadesSigner() {}

  
  public byte[] upgrade(byte[] pdfDocument,
      ITimeStampGenerator externalTimestamp
      //List<X509Certificate> listCertificates
      ) throws Exception {
      String errorMsg = null;
      
      /* XYZ ZZZ TODO S'ha de descomentar 
      String signatureFormat = SignatureFormatDetector.getSignatureFormat(pdfDocument);
      if (!(signatureFormat.equals("PAdES-Basic") || signatureFormat.equals("PAdES-BES") || signatureFormat.equals("PAdES-EPES") || signatureFormat.equals("PAdES-LTV"))) {
          errorMsg = Language.getFormatResIntegra("PS021", new Object[]{signatureFormat});
          LOGGER.error((Object)errorMsg);
          throw new SigningException(errorMsg);
      }
      */
      if (pdfDocument == null || pdfDocument.length == 0) {
          errorMsg = "PS001: Document val null";
          LOGGER.error((Object)errorMsg, new Exception());
          throw new IllegalArgumentException(errorMsg);
      }
      //LOGGER.debug((Object)Language.getResIntegra("PS007"));
      
      // XYZ ZZZ TODO S'ha de descomentar 
      PdfReader reader = this.validatePDFDocumentBeforeToUpgrade(pdfDocument);
      
      
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      PdfStamper stamper = null;
      try {
          int nRead;
          //errorMsg = Language.getResIntegra("PS008");
          //errorMsg = Language.getResIntegra("PS010");
          stamper = PdfStamper.createSignature((PdfReader)reader, (OutputStream)out, (char)'\u0000', (File)null, (boolean)true);
          PdfSignatureAppearance sap = stamper.getSignatureAppearance();
          PdfSignature timeStampSignature = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName("ETSI.RFC3161"));
          timeStampSignature.put(PdfName.TYPE, (PdfObject)new PdfName("DocTimeStamp"));
          timeStampSignature.put(PdfName.V, (PdfObject)new PdfNumber(0));
          sap.setCryptoDictionary((PdfDictionary)timeStampSignature);
          Integer reservedSpace = 25858;
          HashMap<PdfName, Integer> exc = new HashMap<PdfName, Integer>();
          exc.put(PdfName.CONTENTS, reservedSpace);
          //errorMsg = Language.getResIntegra("PS012");
          sap.preClose(exc);
          InputStream s = sap.getRangeStream();
          ByteArrayOutputStream buffer = new ByteArrayOutputStream();
          byte[] data = new byte[16384];
          while ((nRead = s.read(data, 0, data.length)) != -1) {
              buffer.write(data, 0, nRead);
          }
          buffer.flush();
          byte[] dataR = buffer.toByteArray();
          
          
          //TimeStampToken tst = new CMSBuilder().generateTimestamp(dataR, this.isCalledByFacade);
          
          // XYZ ZZZ TODO FAlata Implementar
          //UtilsTimestamp.validateASN1Timestamp(tst);
          Calendar cal = Calendar.getInstance();
          byte[] timestampToken = externalTimestamp.getTimeStamp(dataR, cal);
          
          //TimeStampToken tst =  getTimeStampTokenFromTimeStampResponse(timeStampBinary);
          
          
          //byte[] timestampToken = tst.getEncoded();
          byte[] outc = new byte[(reservedSpace - 2) / 2];
          PdfDictionary dic2 = new PdfDictionary();
          System.arraycopy(timestampToken, 0, outc, 0, timestampToken.length);
          dic2.put(PdfName.CONTENTS, (PdfObject)new PdfString(outc).setHexWriting(true));
          sap.close(dic2);
          byte[] arrby = out.toByteArray();
          return arrby;
      } catch (Exception e) {
          errorMsg = "PS011: " + e.getMessage();
          LOGGER.error(errorMsg, (Throwable)e);
          throw new Exception(errorMsg, e);
      }
      finally {
          UtilsResources.safeClosePDFStamper(stamper);
          UtilsResources.safeCloseOutputStream(out);
      }
  }

  
  

  @SuppressWarnings("unused")
  private PdfReader validatePDFDocumentBeforeToUpgrade(byte[] pdfDocument) throws Exception {
      PdfReader reader = null;
      //String errorMsg = null;
      try {
          HashMap<String, PDFSignatureDictionary> mapSigners = new HashMap<String, PDFSignatureDictionary>();
          //errorMsg = Language.getResIntegra("PS008");
          reader = new PdfReader(pdfDocument);
               
          AcroFields af = reader.getAcroFields();
          TimeStampToken mostRecentTimestamp = this.getMostRecentTimestamp(af, mapSigners);
          for (String signatureName : mapSigners.keySet()) {
              PDFSignatureDictionary pdfSignatureDictionary = mapSigners.get(signatureName);
              X509Certificate signingCertificate = null;
              if (SignatureFormatDetector.isPDF(pdfSignatureDictionary)) {
                  PdfPKCS7 pk = af.verifySignature(pdfSignatureDictionary.getName());
                  signingCertificate = pk.getSigningCertificate();
                  Date validationDate = Calendar.getInstance().getTime();
                  if (mostRecentTimestamp != null) {
                      validationDate = mostRecentTimestamp.getTimeStampInfo().getGenTime();
                  } else {
                      org.spongycastle.tsp.TimeStampToken tst = pk.getTimeStampToken();
                      if (tst != null) {
                          validationDate = tst.getTimeStampInfo().getGenTime();
                      }
                  }
                  
                  // XYZ ZZZ TODO FALTA VALIDAR CERTIFICAT ????
                  // UtilsSignature.validateCertificate(signingCertificate, validationDate, true, this.isCalledByFacade);
                  
                  continue;
              }
              CMSSignedData signedData = UtilsSignature.getCMSSignature(pdfSignatureDictionary);
              SignerInformation signerInformation = (SignerInformation)((List<?>)signedData.getSignerInfos().getSigners()).iterator().next();
              X509CertificateHolder x509CertificateHolder = UtilsSignature.getX509CertificateHolderBySignerId(signedData.getCertificates(), signerInformation.getSID());
              //errorMsg = Language.getFormatResIntegra("PS023", new Object[]{signatureName});
              signingCertificate = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(x509CertificateHolder);
              Date validationDate = Calendar.getInstance().getTime();
              if (mostRecentTimestamp != null) {
                  validationDate = mostRecentTimestamp.getTimeStampInfo().getGenTime();
              } else {
                  TimeStampToken tst = UtilsTimestamp.getTimeStampToken(signerInformation);
                  if (tst != null) {
                      validationDate = tst.getTimeStampInfo().getGenTime();
                  }
              }
              
              // XYZ ZZZ TODO FALTA VALIDAR CERTIFICAT ????
              // UtilsSignature.validateCertificate(signingCertificate, validationDate, true, this.isCalledByFacade);
              
              
          }
      
      }
      catch (IOException e) {
          LOGGER.error(e.getMessage(), (Throwable)e);
          throw new Exception(e.getMessage(), e);
      }
      catch (Exception e) {
          LOGGER.error(e.getMessage(), (Throwable)e);
          throw new Exception(e.getMessage(), e);
      }
      return reader;
  }
  
  
  
  
  private TimeStampToken getMostRecentTimestamp(AcroFields af, Map<String,
    PDFSignatureDictionary> mapSignatureDictionaries) throws Exception {
    TimeStampToken mostRecentTimestamp = null;
    List<String> names = af.getSignatureNames();
    for (String signatureName : names) {
        String subFilter;
        PdfDictionary signatureDictionary = af.getSignatureDictionary(signatureName);
        String pdfType = null;
        if (signatureDictionary.get(PdfName.TYPE) != null) {
            pdfType = signatureDictionary.get(PdfName.TYPE).toString();
        }
        if (this.isDocumentTimeStampDictionary(pdfType, subFilter = signatureDictionary.get(PdfName.SUBFILTER).toString())) {
            byte[] arrayTST = signatureDictionary.getAsString(PdfName.CONTENTS).getOriginalBytes();
            if (arrayTST == null) continue;
            try {
                TimeStampToken tst = new TimeStampToken(new CMSSignedData(arrayTST));
                if (mostRecentTimestamp != null && !mostRecentTimestamp.getTimeStampInfo().getGenTime().before(tst.getTimeStampInfo().getGenTime())) continue;
                mostRecentTimestamp = tst;
                continue;
            }
            catch (Exception e) {
                //String errorMsg = Language.getFormatResIntegra("PS022", new Object[]{signatureName});
                LOGGER.error("PS022: " + e.getMessage(), (Throwable)e);
                throw new Exception("PS022: " + e.getMessage(), e);
            }
        }
        if (!this.isSignatureDictionary(pdfType, subFilter)) continue;
        mapSignatureDictionaries.put(signatureName, new PDFSignatureDictionary(af.getRevision(signatureName), signatureDictionary, signatureName));
    }
    return mostRecentTimestamp;
}
  
  
  
  private boolean isDocumentTimeStampDictionary(String pdfType, String subFilter) {
    if (subFilter.equals(new PdfName("ETSI.RFC3161").toString()) && (pdfType == null || pdfType != null && pdfType.equals(new PdfName("DocTimeStamp").toString()))) {
        return true;
    }
    return false;
}

private boolean isSignatureDictionary(String pdfType, String subFilter) {
    if ((pdfType == null || pdfType != null && pdfType.equals(PdfName.SIG.toString())) && !subFilter.equals(new PdfName("ETSI.RFC3161").toString())) {
        return true;
    }
    return false;
}
  
  
/*
  public static  TimeStampToken getTimeStampTokenFromTimeStampResponse(byte[] res) throws TSPException {

    try {
      ASN1InputStream in = null;

      in = new ASN1InputStream(res);
      
      // OLD CODE
      BERSequence asn = (BERSequence) in.readObject();
      // NEW CODE
      //DLSequence asn = (DLSequence) in.readObject();
      
      // OLD CODE
      ASN1ObjectIdentifier info = (ASN1ObjectIdentifier) asn.getObjectAt(0);
      // NEW CODE
      //DLSequence info = (DLSequence) asn.getObjectAt(0);
      
      // OLD CODE      
      DERInteger status = (DERInteger) info.getObjectAt(0);
      
      //ASN1Integer status = (ASN1Integer) info.getObjectAt(0);
      
      if ((status.getValue().intValue() != 0) && (status.getValue().intValue() != 1)) {
        String tspExMsg = "Timestamp server error. ";
        if ((DERSequence) info.getObjectAt(1) != null) {
          tspExMsg = tspExMsg + new String(((DERSequence) info.getObjectAt(1)).getEncoded());
        }
        in.close();
        throw new TSPException(tspExMsg);
      }
      in.close();
      try {
        // OLD CODE
        //byte[] d = asn.getObjectAt(1).getDERObject().getDEREncoded()
        // NEW CODE
        byte[] d = asn.getObjectAt(1).toASN1Primitive().getEncoded(ASN1Encoding.DER);

        return new TimeStampToken(new CMSSignedData(d));
      } catch (TSPException e) {
        throw e;
      } catch (Exception e) {
        throw new TSPException(e.getMessage(), e);
      }

    } catch(IOException e) {
      throw new TSPException("Error parseando respuesta:" + e.getMessage(), e);
    }

  }
  */
  
  
}
