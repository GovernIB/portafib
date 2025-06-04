package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 20 may 2025 14:48:19
 */
@Schema(description = "Classe que representa un firmant. Només s'ha d'omplir un camp dels que conté.")
public class Signer extends Person {

    /**
     * Dades d'un usuari extern
     */
    @Schema(description = "Dades a omplir en cas de que la persona sigui un usuari extern", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected ExternalSigner externalSigner;

    public Signer() {
        super();
    }

    public ExternalSigner getExternalSigner() {
        return externalSigner;
    }

    public void setExternalSigner(ExternalSigner externalSigner) {
        this.externalSigner = externalSigner;
    }
    

    /**
     * 
     * @param signer
     * @return
     */
    public static String toString(Signer signer) {
      ExternalSigner externalSigner = signer.getExternalSigner();
      if (externalSigner == null) {
        return Person.toString(signer);
      } else {
        return "UsuariExtern => " + ExternalSigner.toString(externalSigner);
      }
    }

}
