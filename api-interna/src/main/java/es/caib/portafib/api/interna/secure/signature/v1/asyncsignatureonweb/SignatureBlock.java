package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 21 may 2025 8:23:24
 */
@Schema(description = "Conjunt de firmes que es poden realitzar en paral·lel, o millor dit sense ordre.")
public class SignatureBlock {
    
    @Schema(
            description = "Ordre d'execució dels Blocs",
            requiredMode = RequiredMode.REQUIRED)
    protected int order;

    @Schema(
            description = "Numero mínim de signatures per passar al següent bloc de firmes.",
            requiredMode = RequiredMode.REQUIRED)
    protected int minimumNumberOfSignaturesRequired;

    @Schema(description = "Llistat de firmes associades a aquest bloc.", requiredMode = RequiredMode.REQUIRED)
    protected List<Signature> signatures;

    public SignatureBlock() {
        super();
    }

    public SignatureBlock(int order, int minimumNumberOfSignaturesRequired, List<Signature> signatures) {
        super();
        this.order = order;
        this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
        this.signatures = signatures;
    }


    public int getMinimumNumberOfSignaturesRequired() {
        return minimumNumberOfSignaturesRequired;
    }

    public void setMinimumNumberOfSignaturesRequired(int minimumNumberOfSignaturesRequired) {
        this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }

    public static String toString(SignatureBlock block) {

        StringBuffer str = new StringBuffer();

        str.append("SignatureMinimum: " + block.getMinimumNumberOfSignaturesRequired()).append("\n");
        str.append("Order: " + block.getOrder()).append("\n");

        int count = 0;
        for (Signature signature : block.getSignatures()) {
            str.append("    ---------  SIGNATURE[" + count + "]  ------------------").append("\n");

            str.append(Signature.toString(signature));

            count++;
        }

        return str.toString();

    }

}
