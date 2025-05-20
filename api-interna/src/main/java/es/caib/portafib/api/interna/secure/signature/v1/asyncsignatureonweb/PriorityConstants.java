package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
@Schema(
        name = "PriorityConstants",
        description = "Valors:\n"
                        + "PRIORITY_PAUSED_PAUSADA = 0; // Prioritat Pausada\r\n"
                        + "PRIORITY_INSIGNIFICANT_INSIGNIFICANT = 1; // =Prioritat Insignificant\r\n"
                        + "PRIORITY_VERYLOW_MOLTBAIXA = 2; // =Prioritat Molt Baixa\r\n"
                        + "PRIORITY_LOW_BAIXA = 3; // =Prioritat Baixa\r\n"
                        + "PRIORITY_NORMALLOW_NORMALBAIXA = 4; // =Prioritat Normal-Baixa\r\n"
                        + "PRIORITY_NORMAL_NORMAL = 5; // =Prioritat Normal\r\n"
                        + "PRIORITY_NORMALHIGH_NORMALALTA = 6; // =Prioritat Normal-Alta\r\n"
                        + "PRIORITY_HIGH_ALTA = 7; // =Prioritat Alta\r\n"
                        + "PRIORITY_VERYHIGH_MOLTALTA = 8; // =Prioritat Molt Alta\r\n"
                        + "PRIORITY_IMMEDIATE_INMEDIATA = 9; // =Prioritat Immediata",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "PRIORITY_PAUSED_PAUSADA(Prioritat Pausada)"
                + "|PRIORITY_INSIGNIFICANT_INSIGNIFICANT(Prioritat Insignificant)"
                + "|PRIORITY_VERYLOW_MOLTBAIXA(Prioritat Molt Baixa)"
                + "|PRIORITY_LOW_BAIXA(Prioritat Baixa)"
                + "|PRIORITY_NORMALLOW_NORMALBAIXA(Prioritat Normal-Baixa)"
                + "|PRIORITY_NORMAL_NORMAL(Prioritat Normal)"
                + "|PRIORITY_NORMALHIGH_NORMALALTA(Prioritat Normal-Alta)"
                + "|PRIORITY_HIGH_ALTA(Prioritat Alta)"
                + "|PRIORITY_VERYHIGH_MOLTALTA(Prioritat Molt Alta)"
                + "|PRIORITY_IMMEDIATE_INMEDIATA(Prioritat Immediata)")
public enum PriorityConstants {
/*
    public static final int PRIORITY_PAUSED_PAUSADA = 0; // Prioritat Pausada
    public static final int PRIORITY_INSIGNIFICANT_INSIGNIFICANT = 1; // =Prioritat Insignificant
    public static final int PRIORITY_VERYLOW_MOLTBAIXA = 2; // =Prioritat Molt Baixa
    public static final int PRIORITY_LOW_BAIXA = 3; // =Prioritat Baixa
    public static final int PRIORITY_NORMALLOW_NORMALBAIXA = 4; // =Prioritat Normal-Baixa
    public static final int PRIORITY_NORMAL_NORMAL = 5; // =Prioritat Normal
    public static final int PRIORITY_NORMALHIGH_NORMALALTA = 6; // =Prioritat Normal-Alta
    public static final int PRIORITY_HIGH_ALTA = 7; // =Prioritat Alta
    public static final int PRIORITY_VERYHIGH_MOLTALTA = 8; // =Prioritat Molt Alta
    public static final int PRIORITY_IMMEDIATE_INMEDIATA = 9; // =Prioritat Immediata
*/
    PRIORITY_PAUSED_PAUSADA(0), PRIORITY_INSIGNIFICANT_INSIGNIFICANT(1),  
    PRIORITY_VERYLOW_MOLTBAIXA(2), PRIORITY_LOW_BAIXA(3),
    PRIORITY_NORMALLOW_NORMALBAIXA(4), PRIORITY_NORMAL_NORMAL(5), PRIORITY_NORMALHIGH_NORMALALTA(6),
    PRIORITY_HIGH_ALTA(7), PRIORITY_VERYHIGH_MOLTALTA(8),PRIORITY_IMMEDIATE_IMMEDIATA(9);
    
    public final Integer value;

    PriorityConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PriorityConstants fromValue(Integer value) {
        for (PriorityConstants b : PriorityConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for PriorityConstants.");
    }
}
