package org.fundaciobit.plugins.signatureserver.afirmalibs.integra;

import org.apache.log4j.Logger;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.util.Store;

import com.aowagie.text.pdf.PdfName;

public class UtilsSignature {

  public static Logger LOGGER = Logger.getLogger(UtilsSignature.class);

  public static CMSSignedData getCMSSignature(PDFSignatureDictionary signatureDictionary) throws Exception {
      //LOGGER.info((Object)Language.getResIntegra("US048"));
      try {
          if (signatureDictionary == null) {
              String errorMsg = "US007: signatureDictionary vale null";
              LOGGER.error((Object)errorMsg);
              throw new IllegalArgumentException(errorMsg);
          }
          byte[] contents = signatureDictionary.getDictionary().getAsString(PdfName.CONTENTS).getOriginalBytes();
          try {
              CMSSignedData signedData = new CMSSignedData(contents);
              if (signedData.getSignerInfos().getSigners().size() == 0) {
                  String errorMsg = "US015: No hay información de los firmantes";
                  LOGGER.error((Object)errorMsg);
                  throw new Exception(errorMsg);
              }
              CMSSignedData errorMsg = signedData;
              return errorMsg;
          }
          catch (CMSException e) {
              String errorMsg = "US009: " + e.getMessage();
              LOGGER.error((Object)errorMsg, (Throwable)e);
              throw new Exception(errorMsg, (Throwable)e);
          }
      }
      finally {
          LOGGER.info("Final getCMSSignature()");
      }
  }

  
  public static X509CertificateHolder getX509CertificateHolderBySignerId(Store certificatesStore,
      SignerId signerId) {
    if (certificatesStore != null && certificatesStore.getMatches(null) != null && signerId != null) {
        for (Object certObj : certificatesStore.getMatches(null)) {
          X509CertificateHolder cert = (X509CertificateHolder) certObj;
            if (!signerId.match((Object)cert)) continue;
            return cert;
        }
    }
    return null;
}
  
}
