package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.security.cert.X509Certificate;

public class DNIUtilsTest {

    @Before
    public void setup() {
        EjbManager.propietatLogicaEjb = Mockito.mock(PropietatGlobalLogicaLocal.class);
    }

    @Test
    public void testBeworNoPattern() throws Exception {
        X509Certificate cert = certificateFixture();
        Assert.assertEquals("IDCFJ-99999999R", DNIUtils.getDNI(cert));
    }

    @Test
    public void testBeworPattern() throws Exception, I18NException {

        Mockito.doReturn("^IDCFJ-([0-9]{8}[A-Z])$")
                .when(EjbManager.propietatLogicaEjb).getProperty(("es.caib.portafib.dniPattern"));

        X509Certificate cert = certificateFixture();
        Assert.assertEquals("99999999R", DNIUtils.getDNI(cert));
    }

    @Test
    public void testBeworPatternWithNewLines() throws Exception, I18NException {

        Mockito.doReturn("\r\n\r\n\r^IDCFJ-([0-9]{8}[A-Z])$\n\r\n\r")
                .when(EjbManager.propietatLogicaEjb).getProperty(("es.caib.portafib.dniPattern"));

        X509Certificate cert = certificateFixture();
        Assert.assertEquals("99999999R", DNIUtils.getDNI(cert));
    }

    private X509Certificate certificateFixture() throws Exception {
        InputStream is = getClass().getResourceAsStream("/bewor_pf.cer");
        X509Certificate cert = CertificateUtils.decodeCertificate(is);
        is.close();
        return cert;
    }
}
