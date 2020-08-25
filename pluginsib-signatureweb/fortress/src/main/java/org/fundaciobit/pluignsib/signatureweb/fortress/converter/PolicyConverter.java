package org.fundaciobit.pluignsib.signatureweb.fortress.converter;

import com.viafirma.fortress.sdk.model.signature.Policy;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;

import java.util.HashMap;
import java.util.Map;

public class PolicyConverter {

    private static final Map<String, Policy.DigestAlgorithm> DIGEST_ALGORITHM_MAP =
            new HashMap<String, Policy.DigestAlgorithm>(4);

    static {
        DIGEST_ALGORITHM_MAP.put("SHA1", Policy.DigestAlgorithm.SHA1);
        DIGEST_ALGORITHM_MAP.put("SHA-256", Policy.DigestAlgorithm.SHA256);
        DIGEST_ALGORITHM_MAP.put("SHA-384", Policy.DigestAlgorithm.SHA384);
        DIGEST_ALGORITHM_MAP.put("SHA-512", Policy.DigestAlgorithm.SHA512);
    }

    public Policy convert(PolicyInfoSignature policyInfoSignature) {
        if (policyInfoSignature == null) {
            return null;
        }

        Policy policy = new Policy();
        policy.setId(policyInfoSignature.getPolicyIdentifier());
        policy.setDigestValueB64(policyInfoSignature.getPolicyIdentifierHash());
        policy.setDigestAlgorithm(DIGEST_ALGORITHM_MAP.get(policyInfoSignature.getPolicyIdentifierHashAlgorithm()));
        return policy;
    }
}
