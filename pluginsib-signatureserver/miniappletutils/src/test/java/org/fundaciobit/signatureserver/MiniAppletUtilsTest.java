package org.fundaciobit.signatureserver;

import org.fundaciobit.plugins.signatureserver.miniappletutils.MiniAppletUtils;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.security.cert.X509Certificate;

/**
 * @author anadal
 */
public class MiniAppletUtilsTest {


    @Test
    public void testDNIe() throws Exception {
        InputStream certstream = MiniAppletUtilsTest.class.getResourceAsStream("/Ciudadano_firma_activo.cer");
        assert certstream != null;
        X509Certificate certificate1 = CertificateUtils.decodeCertificate(certstream);

        String filter =
                "filters=nonexpired:\n" +
                "filters.1=issuer.rfc2254:|(cn=AC DNIE 001)(cn=AC DNIE 002)(cn=AC DNIE 003)(cn=AC DNIE 004)";

        Assert.assertTrue(MiniAppletUtils.matchFilter(certificate1, filter));
    }

    @Test
    public void testDNIeNofilters() throws Exception {
        InputStream certstream = MiniAppletUtilsTest.class.getResourceAsStream("/Ciudadano_firma_activo.cer");
        assert certstream != null;
        X509Certificate certificate1 = CertificateUtils.decodeCertificate(certstream);

        String filter = "";

        // Quan no tenim cap filtre, hauria de passar? La implementació que hi ha fins ara diu que no.
        Assert.assertFalse(MiniAppletUtils.matchFilter(certificate1, filter));
    }

}
