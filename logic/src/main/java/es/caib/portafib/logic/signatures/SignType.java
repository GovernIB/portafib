package es.caib.portafib.logic.signatures;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;

import java.util.HashMap;
import java.util.Map;

public enum SignType {

    PADES(ConstantsV2.TIPUSFIRMA_PADES, FileInfoSignature.SIGN_TYPE_PADES),
    XADES(ConstantsV2.TIPUSFIRMA_XADES, FileInfoSignature.SIGN_TYPE_XADES),
    CADES(ConstantsV2.TIPUSFIRMA_CADES, FileInfoSignature.SIGN_TYPE_CADES),
    SMIME(ConstantsV2.TIPUSFIRMA_SMIME, null);

    private static final Map<Integer, SignType> ID_TO_ENUM = new HashMap<Integer, SignType>();

    static {
        for (SignType signType : values()) {
            ID_TO_ENUM.put(signType.typeId, signType);
        }
    }

    private final int typeId;
    private final String typeName;

    SignType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int typeId() {
        return typeId;
    }

    public String typeName() {
        return typeName;
    }

    public static SignType fromId(int typeId) {
        if (!ID_TO_ENUM.containsKey(typeId)) {
            throw new IllegalArgumentException("Not a valid type: " + typeId);
        }
        return ID_TO_ENUM.get(typeId);
    }

    public static SignType fromFile(Fitxer fitxer) {
        if (fitxer.getNom().endsWith(".pdf")
                || fitxer.getMime().equals("application/pdf")) {
            return SignType.PADES;
        }

        if (fitxer.getNom().endsWith(".xsig")
                || fitxer.getNom().endsWith(".xml")
                || fitxer.getMime().equals("text/xml")
                || fitxer.getMime().equals("application/xml")) {
            return SignType.XADES;
        }

        return SignType.CADES;
    }
}
