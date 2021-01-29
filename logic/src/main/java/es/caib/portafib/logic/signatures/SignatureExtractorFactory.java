package es.caib.portafib.logic.signatures;

import java.util.EnumMap;
import java.util.Map;

public class SignatureExtractorFactory {

    private static final SignatureExtractorFactory INSTANCE = new SignatureExtractorFactory();

    private static final Map<SignType, SignatureExtractor> EXTRACTOR_MAP
            = new EnumMap<SignType, SignatureExtractor>(SignType.class);

    static {
        EXTRACTOR_MAP.put(SignType.PADES, new PadesSignatureExtractor());
        EXTRACTOR_MAP.put(SignType.XADES, new XadesSignatureExtractor());
        EXTRACTOR_MAP.put(SignType.CADES, new CadesSignatureExtractor());
    }

    private SignatureExtractorFactory() {}

    public static SignatureExtractorFactory getInstance() {
        return INSTANCE;
    }

    public SignatureExtractor getExtractor(SignType signType) {
        if (signType == null || !EXTRACTOR_MAP.containsKey(signType)) {
            throw new IllegalArgumentException("Not a valid extractor: " + signType);
        }
        return EXTRACTOR_MAP.get(signType);
    }

    public SignatureExtractor getExtractor(int signTypeId) {
        return getExtractor(SignType.fromId(signTypeId));
    }
}
