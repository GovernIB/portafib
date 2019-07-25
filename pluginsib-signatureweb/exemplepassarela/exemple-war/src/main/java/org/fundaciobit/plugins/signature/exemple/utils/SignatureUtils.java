package org.fundaciobit.plugins.signature.exemple.utils;

import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class SignatureUtils {

  

  /**
   * 
   * @param signatureID
   * @param fileToSign
   * @param idname
   * @param locationSignTableID
   * @param reason
   * @param signNumber
   * @param languageDoc
   * @param signTypeID
   * @param signAlgorithmID
   * @param signModeBool
   * @param firmatPerFormat
   * @param timeStampGenerator
   * @return
   * @throws Exception
   */
  @Deprecated
  public static FileInfoSignature getFileInfoSignature(String signatureID, File fileToSign,
      String mimeType, String idname, int locationSignTableID,
      SignaturesTableHeader signaturesTableHeader, String reason, String location,
      String signerEmail, int signNumber, String languageDoc, String signType,
      String signAlgorithm, int signModeUncheck, boolean userRequiresTimeStamp,
      ITimeStampGenerator timeStampGenerator, SecureVerificationCodeStampInfo csvStampInfo)
      throws Exception {

    int signMode = ((signModeUncheck == FileInfoSignature.SIGN_MODE_IMPLICIT) ? FileInfoSignature.SIGN_MODE_IMPLICIT
        : FileInfoSignature.SIGN_MODE_EXPLICIT);

    PdfVisibleSignature pdfInfoSignature = null;

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {

      // PDF Visible

      pdfInfoSignature = new PdfVisibleSignature();

      if (locationSignTableID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        // No tenim generadors en aquest APP
        pdfInfoSignature.setRubricGenerator(null);
        pdfInfoSignature.setPdfRubricRectangle(null);
      }
    
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
    } else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
    } else {
      // TODO Traduir
      throw new Exception("Tipus de firma no suportada: " + signType);
    }

    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)
        || FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
      // OK
    } else {
      // TODO Traduir
      throw new Exception("Tipus d'algorisme no suportat " + signAlgorithm);
    }

    FileInfoSignature fis = new FileInfoSignature(signatureID, fileToSign, mimeType, idname,
        reason, location, signerEmail, signNumber, languageDoc, signType, signAlgorithm,
        signMode, locationSignTableID, signaturesTableHeader, pdfInfoSignature, csvStampInfo,
        userRequiresTimeStamp, timeStampGenerator);

    return fis;
  };
  
  
  private static final String SYNC = "sync";
  
  
  /**
   * 
   * @return
   */
  public static long generateUniqueSignaturesSetID() {
    long id;
    synchronized (SYNC) {
      id = (System.currentTimeMillis() * 1000000L) + System.nanoTime() % 1000000L;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
    return id;
  }

  
}
