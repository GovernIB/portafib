package es.caib.portafib.logic.signatures;

import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

import java.security.cert.X509Certificate;
import java.util.Date;

public class SignatureFactory {

    private static final SignatureFactory INSTANCE = new SignatureFactory();

    private SignatureFactory() {}

    public static SignatureFactory getInstance() {
        return INSTANCE;
    }

    public Signature getSignature(X509Certificate certificate, Date date) {
        if (certificate == null) {
            throw new IllegalArgumentException("certificate is null");
        }
        String signerName = CertificateUtils.getSubjectCorrectName(certificate);
        String administrationId = CertificateUtils.getDNI(certificate);
        return new Signature(certificate, signerName, administrationId, date);
    }
}
