package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 14:07:26
 */
@Schema(description = "Informació de les validacions que s'han realitzat sobre el fitxer signat.")
public class ValidationInfo {

    @Schema(
            description = "S’ha verificat que l’identificador del firmant és la del que estava previst que firmàs."
                    + " Valor buit indica que no s'ha realitzat la validació.")
    protected Boolean checkAdministrationIDOfSigner;

    @Schema(
            description = "S’ha verificat que no s’hagi modificat el document original."
                    + " Valor buit indica que no s'ha realitzat la validació.")
    protected Boolean checkDocumentModifications;

    @Schema(
            description = "S’ha verificat que la firma és correcte."
                    + " Valor buit indica que no s'ha realitzat la validació.")
    protected Boolean checkValidationSignature;

    public ValidationInfo() {
        super();
    }

    public ValidationInfo(Boolean checkAdministrationIDOfSigner, Boolean checkDocumentModifications,
            Boolean checkValidationSignature) {
        super();
        this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
        this.checkDocumentModifications = checkDocumentModifications;
        this.checkValidationSignature = checkValidationSignature;
    }

    public Boolean getCheckAdministrationIDOfSigner() {
        return checkAdministrationIDOfSigner;
    }

    public void setCheckAdministrationIDOfSigner(Boolean checkAdministrationIDOfSigner) {
        this.checkAdministrationIDOfSigner = checkAdministrationIDOfSigner;
    }

    public Boolean getCheckDocumentModifications() {
        return checkDocumentModifications;
    }

    public void setCheckDocumentModifications(Boolean checkDocumentModifications) {
        this.checkDocumentModifications = checkDocumentModifications;
    }

    public Boolean getCheckValidationSignature() {
        return checkValidationSignature;
    }

    public void setCheckValidationSignature(Boolean checkValidationSignature) {
        this.checkValidationSignature = checkValidationSignature;
    }

}
