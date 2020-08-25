package org.fundaciobit.pluignsib.signatureweb.fortress;

import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import com.viafirma.fortress.sdk.model.signature.SignatureResponse;
import com.viafirma.fortress.sdk.model.signature.Tsa;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;
import org.fundaciobit.pluignsib.signatureweb.fortress.converter.SignatureConverter;
import org.fundaciobit.pluignsib.signatureweb.fortress.converter.SignatureConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignatureAdapter {

    private final SignaturesSet signaturesSet;

    public SignatureAdapter(SignaturesSet signatureSet) {
        this.signaturesSet = signatureSet;
    }

    public List<SignatureConfiguration> getSignatureConfigurationList(String baseTimeStampUrl) throws IOException {
        FileInfoSignature[] fileInfoSignatures = signaturesSet.getFileInfoSignatureArray();
        List<SignatureConfiguration> configurationList = new ArrayList<SignatureConfiguration>(fileInfoSignatures.length);

        for (int i = 0, fileInfoSignaturesLength = fileInfoSignatures.length; i < fileInfoSignaturesLength; i++) {
            FileInfoSignature fis = fileInfoSignatures[i];
            SignatureConverter converter = SignatureConverterFactory.getInstance(fis);
            SignatureConfiguration convert = converter.convert(fis);
            convert.setRef(Integer.toString(i));
            if (fis.getTimeStampGenerator() != null) {
                convert.setTsa(getTsa(baseTimeStampUrl, i));
            }
            configurationList.add(convert);
        }
        return configurationList;
    }

    public void updateSignatureStatus(List<SignatureResponse> signatureResponseList) throws IOException {
        FileInfoSignature[] fileInfoSignatureArray = signaturesSet.getFileInfoSignatureArray();
        for (SignatureResponse signatureResponse : signatureResponseList) {
            int ref = Integer.parseInt(signatureResponse.getRef());
            FileInfoSignature fileInfoSignature = fileInfoSignatureArray[ref];

            File signedData = File.createTempFile("fortress_", "signedfile");
            byte[] data = Base64.decodeBase64(signatureResponse.getBytesB64());
            FileUtils.writeByteArrayToFile(signedData, data);
            fileInfoSignature.getStatusSignature().setSignedData(signedData);
            fileInfoSignature.getStatusSignature().setStatus(StatusSignature.STATUS_FINAL_OK);
        }
        signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
    }

    private Tsa getTsa(String baseTimeStampUrl, int index) {
        String url = baseTimeStampUrl + "/" + index + "/" + AbstractMiniAppletSignaturePlugin.TIMESTAMP_PAGE;
        return new Tsa(url);
    }
}
