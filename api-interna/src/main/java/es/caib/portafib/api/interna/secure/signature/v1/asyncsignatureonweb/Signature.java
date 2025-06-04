package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 21 may 2025 8:19:25
 */
@Schema(description = "Definició d'una Firma")
public class Signature {

    @Schema(description = "Persona destinatària de la firma", requiredMode = RequiredMode.REQUIRED)
    protected Signer signer;

    @Schema(description = "És obligatori que aquesta persona firmi", requiredMode = RequiredMode.REQUIRED)
    protected boolean required;

    @Schema(
            description = "Raó de firma específica per aquesta firma. Sinó es defineix s'utilitzarà la raó definida en la Petició de Firma.")
    protected String reason;

    @Schema(description = "Número mínim de revisors. Per defecte 0.", requiredMode = RequiredMode.REQUIRED,nullable = false, defaultValue = "0")
    protected int minimumNumberOfRevisers; // Revisors;

    @Schema(
            description = "Llistat de revisors de la Firma. Abans de que aquest destinatari firma,"
                    + " els revisors hauran d'haver acceptat el document.")
    protected List<Reviser> revisers;

    public Signature() {
        super();
    }

    public Signature(Signer signer, boolean required, String reason, int minimumNumberOfRevisers,
            List<Reviser> revisers) {
        super();
        this.signer = signer;
        this.required = required;
        this.reason = reason;
        this.minimumNumberOfRevisers = minimumNumberOfRevisers;
        this.revisers = revisers;
    }

    public Signer getSigner() {
        return signer;
    }

    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getMinimumNumberOfRevisers() {
        return minimumNumberOfRevisers;
    }

    public void setMinimumNumberOfRevisers(int minimumNumberOfRevisers) {
        this.minimumNumberOfRevisers = minimumNumberOfRevisers;
    }

    public List<Reviser> getRevisers() {
        return revisers;
    }

    public void setRevisers(List<Reviser> revisers) {
        this.revisers = revisers;
    }
    
    
    public static String toString(Signature signature) {

        StringBuffer str = new StringBuffer();

        str.append("Reason: ").append(signature.getReason()).append("\n");
        str.append("Signer: ").append(Signer.toString(signature.getSigner()))
            .append("\n");

        // REvisors i altres
        List<Reviser> revisers = signature.getRevisers();

        if (revisers != null && revisers.size() != 0) {
          str.append("Minimum Number Of Revisers: ").append(signature.getMinimumNumberOfRevisers())
              .append("\n");
          int revCount = 1;
          for (Reviser reviser : revisers) {
            str.append("   ===  REVISER [" + revCount + "] ===");
            str.append(Reviser.toString(reviser));
          }
        }

        return str.toString();

      }

}
