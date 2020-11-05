package es.caib.portafib.app;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.utils.ConstantsV2;

/**
 * Mapeig dels rols de portafib, per obtenir el seu nom, url i recurs per mostrar l'etiqueta.
 */
public enum Rol {

    DESTINATARI (
            ConstantsV2.ROLE_DEST,
            ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT,
            R.string.role_destinatari),

    DELEGAT (
            ConstantsV2.ROLE_DELE,
            ConstantsV2.CONTEXT_DELE_ESTATFIRMA_PENDENT,
            R.string.role_delegat),

    COLABORADOR (
            ConstantsV2.ROLE_COLA,
            ConstantsV2.CONTEXT_COLA_ESTATFIRMA_PENDENT,
            R.string.role_colaborador),

    SOLICITANT (
            ConstantsV2.ROLE_SOLI,
            ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA,
            R.string.role_solicitant),

    REVISOR (
            ConstantsV2.ROLE_REVI,
            ConstantsV2.CONTEXT_REVI_ESTATFIRMA_PENDENT,
            R.string.role_revisor);

    private final String roleName;
    private final String url;
    private final int resource;

    Rol(String roleName, String url, int resource) {
        this.roleName = roleName;
        this.url = url;
        this.resource = resource;
    }

    public String url() {
        return url;
    }

    public int resource() {
        return resource;
    }

    @NonNull
    @Override
    public String toString() {
        return roleName;
    }

    private static final Map<String, Rol> stringToEnum = new HashMap<>();

    static {
        for (Rol rol: values()) {
            stringToEnum.put(rol.toString(), rol);
        }
    }

    public static Rol fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
