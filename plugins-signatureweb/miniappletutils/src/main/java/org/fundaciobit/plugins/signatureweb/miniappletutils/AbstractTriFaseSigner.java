package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.Properties;

import es.gob.afirma.signers.pades.PAdESTriPhaseSigner;
import es.gob.afirma.signers.pades.PdfSignResult;
import es.gob.afirma.signers.pades.PdfTimestamper;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractTriFaseSigner {

  public byte[] sign(final byte[] inPDF, final String algorithm,
      final java.security.cert.Certificate[] certChain, final Properties xParams)
      throws Exception {

    final Properties extraParams = xParams != null ? xParams : new Properties();

    // TODO checkIText();

    final java.security.cert.Certificate[] certificateChain = Boolean.parseBoolean(extraParams
        .getProperty("includeOnlySignningCertificate", Boolean.FALSE.toString())) ? //$NON-NLS-1$
    new X509Certificate[] { (X509Certificate) certChain[0] }
        : certChain;

    final GregorianCalendar signTime = new GregorianCalendar();

    // Sello de stiempo
    byte[] data;

    data = PdfTimestamper.timestampPdf(inPDF, extraParams, signTime);

    // Prefirma
    final PdfSignResult pre;

    pre = PAdESTriPhaseSigner
        .preSign(algorithm, data, certificateChain, signTime, extraParams);

    final byte[] interSign = signHash(algorithm, pre.getSign());

    // Postfirma
    return PAdESTriPhaseSigner.postSign(algorithm, data, certificateChain, interSign, pre,
        null, null);

  }

  public abstract byte[] signHash(final String algorithm, final byte[] hash) throws Exception;

}
