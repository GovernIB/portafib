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

        Signature.Builder builder = new Signature.Builder(certificate, date)
                .signerName(CertificateUtils.getSubjectCorrectName(certificate))
                .signerAdministrationId(CertificateUtils.getDNI(certificate));

        try {
            String[] empresaNif = CertificateUtils.getEmpresaNIFNom(certificate);
            if (empresaNif != null) {
                builder.organizationAdministrationId(empresaNif[0])
                        .organizationName(empresaNif[1]);
            }
        } catch (Exception ignore) {}

        return builder.build();
    }
}
