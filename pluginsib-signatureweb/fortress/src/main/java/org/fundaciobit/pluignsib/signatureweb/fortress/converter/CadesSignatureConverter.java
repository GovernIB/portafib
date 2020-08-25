package org.fundaciobit.pluignsib.signatureweb.fortress.converter;

import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

public class CadesSignatureConverter extends AbstractSignatureConverter {

    @Override
    protected void applyConfiguration(SignatureConfiguration config, FileInfoSignature fis) {
        SignatureConfiguration.SignatureType type = fis.getTimeStampGenerator() == null
                ? SignatureConfiguration.SignatureType.CADES_B
                : SignatureConfiguration.SignatureType.CADES_T;
        config.setSignatureType(type);

        config.setPackaging(fis.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT
                ? SignatureConfiguration.Packaging.ENVELOPING
                : SignatureConfiguration.Packaging.DETACHED);
    }
}
