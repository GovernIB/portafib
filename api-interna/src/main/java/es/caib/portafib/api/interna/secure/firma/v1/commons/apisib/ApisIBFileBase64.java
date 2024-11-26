package es.caib.portafib.api.interna.secure.firma.v1.commons.apisib;

import es.caib.portafib.model.entity.Fitxer;
import io.swagger.v3.oas.annotations.media.Schema;

public class ApisIBFileBase64 {


    public ApisIBFileBase64() {
        super();
    }

    public ApisIBFileBase64(ApisIBFile evi) {
        super();
        this.nom = evi.getNom();
        this.mime = evi.getMime();
    }

    @Schema(description = "Nom del fitxer")
    private String nom = null;

    @Schema(description = "Tipus MIME del Fitxer")
    private String mime = null;

    @Schema(description = "Contingut del fitxer en Base64. En llistats aquest camp vendrà buit.", required = false)
    protected String dataBase64;

    public String getName() {
        return nom;
    }

    public void setName(String name) {
        this.nom = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getDataBase64() {
        return dataBase64;
    }

    public void setDataBase64(String documentBase64) {
        this.dataBase64 = documentBase64;
    }


    public static ApisIBFileBase64 toBean(Fitxer __bean) {
        if (__bean == null) {
            return null;
        }
        ApisIBFileBase64 __tmp = new ApisIBFileBase64();
        __tmp.setName(__bean.getNom());
        __tmp.setMime(__bean.getMime());
        return __tmp;
    }
}
