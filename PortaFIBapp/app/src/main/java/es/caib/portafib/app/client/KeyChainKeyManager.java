package es.caib.portafib.app.client;

import android.content.Context;
import android.security.KeyChain;
import android.security.KeyChainException;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509ExtendedKeyManager;

public class KeyChainKeyManager extends X509ExtendedKeyManager {

    private final String clientAlias;
    private final X509Certificate[] certificateChain;
    private final PrivateKey privateKey;

    public static KeyChainKeyManager fromAlias(Context context, String alias)
            throws CertificateException {
        X509Certificate[] certificateChain;
        try {
            certificateChain = KeyChain.getCertificateChain(context, alias);
        } catch (KeyChainException | InterruptedException e) {
            throw new CertificateException(e);
        }

        PrivateKey privateKey;
        try {
            privateKey = KeyChain.getPrivateKey(context, alias);
        } catch (KeyChainException | InterruptedException e) {
            throw new CertificateException(e);
        }

        if (certificateChain == null || privateKey == null) {
            throw new CertificateException("Can't access certificate from keystore");
        }

        return new KeyChainKeyManager(alias, certificateChain, privateKey);
    }

    private KeyChainKeyManager(
            String clientAlias, X509Certificate[] certificateChain, PrivateKey privateKey) {
        this.clientAlias = clientAlias;
        this.certificateChain = certificateChain;
        this.privateKey = privateKey;
    }


    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        return clientAlias;
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return certificateChain;
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return privateKey;
    }

    @Override
    public final String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final String[] getClientAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final String[] getServerAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException();
    }
}