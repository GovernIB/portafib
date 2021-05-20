package es.caib.portafib.logic.signature;

import es.caib.portafib.logic.signatures.CadesSignatureExtractor;
import es.caib.portafib.logic.signatures.PadesSignatureExtractor;
import es.caib.portafib.logic.signatures.SignType;
import es.caib.portafib.logic.signatures.Signature;
import es.caib.portafib.logic.signatures.SignatureExtractor;
import es.caib.portafib.logic.signatures.SignatureExtractorFactory;
import es.caib.portafib.logic.signatures.XadesSignatureExtractor;
import es.caib.portafib.logic.utils.ProviderRegistration;
import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class TestSignatureExtractor {

    private static final ProviderRegistration providerRegistration = new ProviderRegistration();

    @BeforeClass
    public static void setup() {
        providerRegistration.register();
    }

    @AfterClass
    public static void teardown() {
        providerRegistration.unregister();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalidExtractor() {
        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        extractorFactory.getExtractor(SignType.SMIME);
        Assert.fail();
    }

    @Test
    public void testGetPadesExtractor() {
        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);
        Assert.assertTrue(extractor instanceof PadesSignatureExtractor);
    }

    @Test
    public void testGetXadesExtractor() {
        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);
        Assert.assertTrue(extractor instanceof XadesSignatureExtractor);
    }

    @Test
    public void testGetCadesExtractor() {
        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.CADES);
        Assert.assertTrue(extractor instanceof CadesSignatureExtractor);
    }

    @Test
    public void testExtractPdfNotSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);

        URL resource = getClass().getResource("/pdf-unsigned.pdf");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(0, signatures.size());
    }

    @Test
    public void testExtractPdf1Signed() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);

        URL resource = getClass().getResource("/pdf-1signed.pdf");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(1, signatures.size());

        Signature signature = signatures.get(0);
        Assert.assertEquals("PRUEBAS EIDAS CERTIFICADO", signature.getSignerName());
        Assert.assertEquals("99999999R", signature.getSignerAdministrationId());
        Assert.assertNull(signature.getOrganizationName());
        Assert.assertNull(signature.getOrganizationAdministrationId());

    }

    @Test
    public void testExtractPdf2Signed() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);

        URL resource = getClass().getResource("/pdf-2signed.pdf");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(2, signatures.size());
    }

    @Test
    public void testExtractPdfRepSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);

        URL resource = getClass().getResource("/pdf_rep_signed.pdf");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(1, signatures.size());

        Signature signature = signatures.get(0);
        Assert.assertEquals("PRUEBASPF APELLIDOUNOPF APELLIDODOSPF", signature.getSignerName());
        Assert.assertEquals("00000000T", signature.getSignerAdministrationId());
        Assert.assertEquals("FNMT-RCM PRUEBAS", signature.getOrganizationName());
        Assert.assertEquals("Q0000000J", signature.getOrganizationAdministrationId());
    }

    @Test
    public void testExtractPdfRepSigned2() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.PADES);

        URL resource = getClass().getResource("/pdf_rep_signed2.pdf");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(1, signatures.size());

        Signature signature = signatures.get(0);
        Assert.assertEquals("JUAN ANTONIO CÁMARA ESPAÑOL", signature.getSignerName());
        Assert.assertEquals("00000000T", signature.getSignerAdministrationId());
        Assert.assertEquals("[SOLO PRUEBAS] ENTIDAD", signature.getOrganizationName());
        Assert.assertEquals("R0599999J", signature.getOrganizationAdministrationId());
    }

    @Test
    public void testExtractXadesCosigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        URL resource = getClass().getResource("/pdf-xades-signat-cosignat.xsig");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(2, signatures.size());
    }

    @Test
    public void testExtractXadesResigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        URL resource = getClass().getResource("/pdf-xades-signat-resignat.xsig");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(2, signatures.size());
    }

    @Test
    public void testExtractXadesNotSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        URL resource = getClass().getResource("/sample.xml");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(0, signatures.size());
    }

    @Test
    public void testExtractXadesInvalid() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        URL resource = getClass().getResource("/dibuix.png");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(0, signatures.size());
    }

    @Test
    public void testExtractAllXades1Sign() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        String[] resources = {
                "/xades_attached_binary_autofirma.xsig",
                "/xades_attached_sample_xml.xsig",
                "/xades_dettached_binary_europe.xml",
                "/xades_idettached_binary_autofirma.xsig",
                "/xades_idettached_xml_autofirma.xsig",
                "/xades_idettached_xml_europe.xml"
        };

        for (String resource : resources) {
            URL url = getClass().getResource(resource);
            IPortaFIBDataSource dataSource = new FileDataSource(new File(url.toURI()));
            List<Signature> signatures = extractor.extract(dataSource);
            Assert.assertEquals(1, signatures.size());
        }
    }

    @Test
    public void testExtractXadesRepSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.XADES);

        URL resource = getClass().getResource("/xades_attached_rep_sample_xml.xsig");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(1, signatures.size());

        Signature signature = signatures.get(0);
        Assert.assertEquals("JUAN ANTONIO CÁMARA ESPAÑOL", signature.getSignerName());
        Assert.assertEquals("00000000T", signature.getSignerAdministrationId());
        Assert.assertEquals("[SOLO PRUEBAS] ENTIDAD", signature.getOrganizationName());
        Assert.assertEquals("R0599999J", signature.getOrganizationAdministrationId());
    }

    @Test
    public void testExtractAllCades1Sign() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.CADES);

        String[] resources = {
                "/dibuix.png_asigned.csig",
                "/dibuix.png_dsigned.csig",
                "/Document.txt_asigned.csig",
                "/Document.txt_dsigned.csig",
        };

        for (String resource : resources) {
            URL url = getClass().getResource(resource);
            IPortaFIBDataSource dataSource = new FileDataSource(new File(url.toURI()));
            List<Signature> signatures = extractor.extract(dataSource);
            Assert.assertEquals(1, signatures.size());
        }
    }


    @Test
    public void testExtractCadesCosigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.CADES);

        URL resource = getClass().getResource("/dibuix.png_acosigned.csig");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(2, signatures.size());
    }

    @Test
    public void testExtractCadesNotSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.CADES);

        URL resource = getClass().getResource("/Document.txt");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(0, signatures.size());
    }

    @Test
    public void testExtractCadesRepSigned() throws URISyntaxException, I18NException {

        SignatureExtractorFactory extractorFactory = SignatureExtractorFactory.getInstance();
        SignatureExtractor extractor = extractorFactory.getExtractor(SignType.CADES);

        URL resource = getClass().getResource("/Document.txt_rep_asigned.csig");
        IPortaFIBDataSource dataSource = new FileDataSource(new File(resource.toURI()));

        List<Signature> signatures = extractor.extract(dataSource);
        Assert.assertEquals(1, signatures.size());

        Signature signature = signatures.get(0);
        Assert.assertEquals("JUAN ANTONIO CÁMARA ESPAÑOL", signature.getSignerName());
        Assert.assertEquals("00000000T", signature.getSignerAdministrationId());
        Assert.assertEquals("[SOLO PRUEBAS] ENTIDAD", signature.getOrganizationName());
        Assert.assertEquals("R0599999J", signature.getOrganizationAdministrationId());
    }

}
