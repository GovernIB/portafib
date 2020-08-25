package org.fundaciobit.pluignsib.signatureweb.fortress.converter;

import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import com.viafirma.fortress.sdk.model.signature.XadesConfiguration;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

public class XadesSignatureConverter extends AbstractSignatureConverter {

    @Override
    protected void applyConfiguration(SignatureConfiguration config, FileInfoSignature fis) {

        XadesConfiguration xadesConfiguration = new XadesConfiguration();
        config.setXadesConfiguration(xadesConfiguration);

        SignatureConfiguration.SignatureType type = fis.getTimeStampGenerator() == null
                ? SignatureConfiguration.SignatureType.XADES_B
                : SignatureConfiguration.SignatureType.XADES_T;
        config.setSignatureType(type);

        config.setPackaging(fis.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT
                ? SignatureConfiguration.Packaging.ENVELOPING
                : SignatureConfiguration.Packaging.DETACHED);
    }
}
