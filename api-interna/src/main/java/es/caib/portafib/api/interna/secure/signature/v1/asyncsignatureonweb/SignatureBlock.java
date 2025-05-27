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
            description = "Numero mínim de signatures per passar al següent bloc de firmes.",
            requiredMode = RequiredMode.REQUIRED)
    protected int minimumNumberOfSignaturesRequired;

    @Schema(description = "Llistat de firmes associades a aquest bloc.", requiredMode = RequiredMode.REQUIRED)
    protected List<Signature> signers;

    public SignatureBlock() {
        super();
    }

    public SignatureBlock(int minimumNumberOfSignaturesRequired, List<Signature> signers) {
        super();
        this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
        this.signers = signers;
    }

    public int getMinimumNumberOfSignaturesRequired() {
        return minimumNumberOfSignaturesRequired;
    }

    public void setMinimumNumberOfSignaturesRequired(int minimumNumberOfSignaturesRequired) {
        this.minimumNumberOfSignaturesRequired = minimumNumberOfSignaturesRequired;
    }

    public List<Signature> getSigners() {
        return signers;
    }

    public void setSigners(List<Signature> signers) {
        this.signers = signers;
    }

}
