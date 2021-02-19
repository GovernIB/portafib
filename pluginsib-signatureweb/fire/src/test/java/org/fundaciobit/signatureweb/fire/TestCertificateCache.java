package org.fundaciobit.signatureweb.fire;

import org.fundaciobit.plugins.signatureweb.fire.CertificateCache;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class TestCertificateCache {

    private byte[] certificateBytes;

    @Before
    public void setup() throws IOException {
        certificateBytes = getCertificateBytes();
    }

    private byte[] getCertificateBytes() throws IOException {
        InputStream inputStream = FileUtils.readResource(getClass(), "99999999R.cer");
        byte[] certificateBytes = FileUtils.toByteArray(inputStream);
        inputStream.close();
        return certificateBytes;
    }

    @Test
    public void testMemory() throws Exception  {
        CertificateCache cache = new CertificateCache(100, 3600);
        for (int i = 0; i < 1000000; i++) {
            ByteArrayInputStream is = new ByteArrayInputStream(certificateBytes);
            X509Certificate x509Certificate = CertificateUtils.decodeCertificate(is);
            cache.setCachedCertificates(UUID.randomUUID().toString(), Collections.singletonList(x509Certificate));
        }
    }

    /**
     * Comprova que a una clau a la que s'accdeix regularment es manté dins el cache, mentre que les
     * que no, cada vegada que s'arriba a 100 insercions es descarten.
     */
    @Test
    public void testEntryPersist() throws Exception  {

        CertificateCache cache = new CertificateCache(100, 3600);

        String firstKey = UUID.randomUUID().toString();
        ByteArrayInputStream is = new ByteArrayInputStream(certificateBytes);
        X509Certificate x509Certificate = CertificateUtils.decodeCertificate(is);

        cache.setCachedCertificates(firstKey, Collections.singletonList(x509Certificate));

        String olderKey = null;
        for (int i = 0; i < 100000; i++) {
            is = new ByteArrayInputStream(certificateBytes);
            x509Certificate = CertificateUtils.decodeCertificate(is);
            String currentKey = UUID.randomUUID().toString();
            cache.setCachedCertificates(currentKey, Collections.singletonList(x509Certificate));

            if (i % 50 == 0) {
                Assert.assertNotNull("First key Iteració " + i, cache.getCachedCertificates(firstKey));
            }
            if (i % 100 == 0) {
                if (olderKey != null) {
                    Assert.assertNull("Old key Iteració " + i, cache.getCachedCertificates(olderKey));
                }
                olderKey = currentKey;
            }
        }
    }

    /**
     * Comprova que les instàncies despareixen quan passa el temps de vida.
     * Es fixa a 1s, i es van dues esperes de 600 milisegons.
     */
    @Test
    public void testEntryExpire() throws Exception  {

        CertificateCache cache = new CertificateCache(100, 1);

        String firstKey = UUID.randomUUID().toString();
        ByteArrayInputStream is = new ByteArrayInputStream(certificateBytes);
        X509Certificate x509Certificate = CertificateUtils.decodeCertificate(is);

        cache.setCachedCertificates(firstKey, Collections.singletonList(x509Certificate));

        Thread.sleep(600);

        Assert.assertNotNull(cache.getCachedCertificates(firstKey));

        Thread.sleep(600);

        Assert.assertNull(cache.getCachedCertificates(firstKey));
    }

}
