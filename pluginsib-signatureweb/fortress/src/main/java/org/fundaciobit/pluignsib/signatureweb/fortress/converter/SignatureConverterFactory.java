package org.fundaciobit.pluignsib.signatureweb.fortress.converter;

import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import java.util.HashMap;
import java.util.Map;

public class SignatureConverterFactory {

    private static final Map<String, SignatureConverter> INSTANCES = new HashMap<String, SignatureConverter>();

    static {
        INSTANCES.put(FileInfoSignature.SIGN_TYPE_PADES, new PadesSignatureConverter());
        INSTANCES.put(FileInfoSignature.SIGN_TYPE_CADES, new CadesSignatureConverter());
        INSTANCES.put(FileInfoSignature.SIGN_TYPE_XADES, new XadesSignatureConverter());
    }

    public static SignatureConverter getInstance(FileInfoSignature fileInfoSignature) {
        SignatureConverter signatureConverter = INSTANCES.get(fileInfoSignature.getSignType());
        if (signatureConverter == null) {
            throw new IllegalArgumentException("Tipus de firma no soportat: " + fileInfoSignature.getSignType());
        }
        return signatureConverter;
    }
}
