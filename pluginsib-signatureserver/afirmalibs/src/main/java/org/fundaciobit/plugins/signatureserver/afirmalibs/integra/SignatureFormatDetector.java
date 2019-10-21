package org.fundaciobit.plugins.signatureserver.afirmalibs.integra;

import com.aowagie.text.pdf.PdfName;

public class SignatureFormatDetector {

  
  public static boolean isPDF(PDFSignatureDictionary signatureDictionary) {
    PdfName subFilterValue = (PdfName)signatureDictionary.getDictionary().get(PdfName.SUBFILTER);
    if (!(subFilterValue.equals((Object)PdfName.ADBE_PKCS7_DETACHED) || subFilterValue.equals((Object)PdfName.ADBE_PKCS7_SHA1) || subFilterValue.equals((Object)new PdfName("ETSI.CAdES.detached")))) {
        return true;
    }
    return false;
}
  
}
