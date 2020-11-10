package es.caib.portafib.app.client;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * TrustManager que intenta validar els certificats amb el trustmanager per defecte i si no ho aconsegueix
 * empre un keystore definit per l'aplicació.
 */
public class CompositeTrustManager implements X509TrustManager {

    private final X509TrustManager defaultTrustManager;
    private final X509TrustManager localTrustManager;

    private final X509Certificate[] acceptedIssuers;

    public CompositeTrustManager(KeyStore localKeyStore) {
        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        defaultTrustManager = getTrustManager(algorithm, null);
        localTrustManager = getTrustManager(algorithm, localKeyStore);

        List<X509Certificate> acceptedIssuersList = new ArrayList<>();
        acceptedIssuersList.addAll(Arrays.asList(defaultTrustManager.getAcceptedIssuers()));
        acceptedIssuersList.addAll(Arrays.asList(localTrustManager.getAcceptedIssuers()));
        acceptedIssuers = acceptedIssuersList.toArray(new X509Certificate[0]);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            defaultTrustManager.checkClientTrusted(chain, authType);
        } catch (CertificateException ce) {
            localTrustManager.checkClientTrusted(chain, authType);
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        try {
            defaultTrustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException ce) {
            localTrustManager.checkServerTrusted(chain, authType);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return acceptedIssuers;
    }

    private static X509TrustManager getTrustManager(String algorithm, KeyStore keyStore) {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(algorithm);
            factory.init(keyStore);
            TrustManager[] trustManagers = factory.getTrustManagers();
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Cap X509TrustManager trobat");
    }
}