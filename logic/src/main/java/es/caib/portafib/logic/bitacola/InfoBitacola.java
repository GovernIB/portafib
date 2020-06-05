package es.caib.portafib.logic.bitacola;

import java.security.Principal;

/**
 * Classe per encapçular la informació per crear un registre a la bitàcola.
 * Implementa el builder pattern.
 * Exemple per crear una instància:
 * <pre>
 *     InfoBitacola.builder(ENTITATID)
 *             .objecteid(IDOBJECTE)
 *             .tipusObjecte(TIPUS_OBJECTE)
 *             .tipusOperacio(TIPUS_OPERACIO)
 *             .descripcio(DESCRIPCIO) // opcional
 *             .objecte(OBJECTE_A_SERIALITZAR) // opcional
 *             .principal(PRINCIPAL) // opcional
 *             .build();
 * </pre>
 *
 * L'objecte passat amb el mètode {@link Builder#objecte} ha de ser un dels objectes que
 * es poden serialitzar amb {@link es.caib.portafib.logic.jaxb.JAXBUtil}
 */
public class InfoBitacola {

    private final String entitatid;
    private final String objecteid;
    private final int tipusObjecte;
    private final int tipusOperacio;
    private final Principal principal;
    private final String descripcio;
    private final Object  objecte;

    public String getEntitatid() {
        return entitatid;
    }

    public String getObjecteid() {
        return objecteid;
    }

    public int getTipusObjecte() {
        return tipusObjecte;
    }

    public int getTipusOperacio() {
        return tipusOperacio;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public Object getObjecte() {
        return objecte;
    }

    public static class Builder {

        private String entitatid;
        private String objecteid;
        private int tipusObjecte;
        private int tipusOperacio;
        private Principal principal;
        private String descripcio;
        private Object objecte;

        private Builder(String entitatid) {
            this.entitatid = entitatid;
        }

        public Builder objecteid(String objecteid) {
            this.objecteid = objecteid;
            return this;
        }

        public Builder objecteid(long objecteid) {
            this.objecteid = String.valueOf(objecteid);
            return this;
        }

        public Builder tipusObjecte(int tipusObjecte) {
            this.tipusObjecte = tipusObjecte;
            return this;
        }

        public Builder tipusOperacio(int tipusOperacio) {
            this.tipusOperacio = tipusOperacio;
            return this;
        }

        public Builder principal(Principal principal) {
            this.principal = principal;
            return this;
        }

        public Builder descripcio(String descripcio) {
            this.descripcio = descripcio;
            return this;
        }

        public Builder objecte(Object objecte) {
            this.objecte = objecte;
            return this;
        }

        public InfoBitacola build() {
            return new InfoBitacola(this);
        }
    }

    public static Builder builder(String entitatid) {
        return new Builder(entitatid);
    }

    private InfoBitacola(Builder builder) {
        this.entitatid = builder.entitatid;
        this.objecteid = builder.objecteid;
        this.tipusObjecte = builder.tipusObjecte;
        this.tipusOperacio = builder.tipusOperacio;
        this.principal = builder.principal;
        this.descripcio = builder.descripcio;
        this.objecte = builder.objecte;

        if (entitatid == null) {
            throw new IllegalArgumentException("entitatid no pot ser nul");
        }

        if (objecteid == null) {
            throw new IllegalArgumentException("objecteid no pot ser nul");
        }
    }
}
