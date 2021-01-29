package es.caib.portafib.logic.signatures;

import es.caib.portafib.utils.ConstantsV2;

import java.util.HashMap;
import java.util.Map;

public enum SignType {

    PADES(ConstantsV2.TIPUSFIRMA_PADES),
    XADES(ConstantsV2.TIPUSFIRMA_XADES),
    CADES(ConstantsV2.TIPUSFIRMA_CADES),
    SMIME(ConstantsV2.TIPUSFIRMA_SMIME);

    private static final Map<Integer, SignType> ID_TO_ENUM = new HashMap<Integer, SignType>();

    static {
        for (SignType signType : values()) {
            ID_TO_ENUM.put(signType.typeId, signType);
        }
    }

    private final int typeId;

    SignType(int typeId) {
        this.typeId = typeId;
    }

    public int typeId() {
        return typeId;
    }

    public static SignType fromId(int typeId) {
        if (!ID_TO_ENUM.containsKey(typeId)) {
            throw new IllegalArgumentException("Not a valid type: " + typeId);
        }
        return ID_TO_ENUM.get(typeId);
    }
}
