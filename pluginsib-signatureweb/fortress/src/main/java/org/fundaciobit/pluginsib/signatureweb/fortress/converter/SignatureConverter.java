package org.fundaciobit.pluginsib.signatureweb.fortress.converter;

import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import java.io.IOException;

public interface SignatureConverter {

    SignatureConfiguration convert(FileInfoSignature fileInfoSignature) throws IOException;

}
