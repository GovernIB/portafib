package org.fundaciobit.pluignsib.signatureweb.fortress.converter;

import com.viafirma.fortress.sdk.model.signature.Document;
import com.viafirma.fortress.sdk.model.signature.Policy;
import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSignatureConverter implements SignatureConverter {

    private static final Map<String, SignatureConfiguration.SignatureAlgorithm> SIGN_ALGORITHM_MAP
            = new HashMap<String, SignatureConfiguration.SignatureAlgorithm>(4);

    static {
        SIGN_ALGORITHM_MAP.put(FileInfoSignature.SIGN_ALGORITHM_SHA1, SignatureConfiguration.SignatureAlgorithm.RSA_SHA1);
        SIGN_ALGORITHM_MAP.put(FileInfoSignature.SIGN_ALGORITHM_SHA256, SignatureConfiguration.SignatureAlgorithm.RSA_SHA256);
        SIGN_ALGORITHM_MAP.put(FileInfoSignature.SIGN_ALGORITHM_SHA384, SignatureConfiguration.SignatureAlgorithm.RSA_SHA384);
        SIGN_ALGORITHM_MAP.put(FileInfoSignature.SIGN_ALGORITHM_SHA512, SignatureConfiguration.SignatureAlgorithm.RSA_SHA512);
    }

    private final PolicyConverter policyConverter = new PolicyConverter();

    @Override
    public SignatureConfiguration convert(FileInfoSignature fis) throws IOException {
        SignatureConfiguration config = new SignatureConfiguration();
        config.setDocument(getDocument(fis));
        config.setPolicy(getPolicy(fis));
        config.setSignatureAlgorithm(getSignatureAlgorithm(fis));
        applyConfiguration(config, fis);
        return config;
    }

    protected Document getDocument(FileInfoSignature fis) throws IOException {
        Document document = new Document();
        String base64String = Base64.encodeBase64String(FileUtils.readFileToByteArray(fis.getFileToSign()));
        document.setBytesB64(base64String);
        document.setName(fis.getName());
        return document;
    }

    protected Policy getPolicy(FileInfoSignature fis) {
        return policyConverter.convert(fis.getPolicyInfoSignature());
    }

    protected SignatureConfiguration.SignatureAlgorithm getSignatureAlgorithm(FileInfoSignature fis) {
        return SIGN_ALGORITHM_MAP.get(fis.getSignAlgorithm());
    }

    protected abstract void applyConfiguration(SignatureConfiguration config, FileInfoSignature fis);
}
