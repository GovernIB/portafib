package es.caib.portafib.logic.notificacions;

import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_ENPROCES;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_FINALITZADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_FIRMA_PARCIAL;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_INVALIDADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_PAUSADA;
import static es.caib.portafib.utils.ConstantsV2.BITACOLA_OP_NOTIFICAR_REBUTJADA;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_INVALIDAT;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA;

public class NotificacioUtils {

    public static int getOpericioBitacolaForEventType(long eventType) {
        switch ((int)eventType) {
            case (int) NOTIFICACIOAVIS_PETICIO_EN_PROCES:
                return BITACOLA_OP_NOTIFICAR_ENPROCES;

            case (int) NOTIFICACIOAVIS_PETICIO_PAUSADA:
                return BITACOLA_OP_NOTIFICAR_PAUSADA;

            case (int) NOTIFICACIOAVIS_PETICIO_REBUTJADA:
                return BITACOLA_OP_NOTIFICAR_REBUTJADA;

            case (int) NOTIFICACIOAVIS_FIRMA_PARCIAL:
                return BITACOLA_OP_NOTIFICAR_FIRMA_PARCIAL;

            case (int) NOTIFICACIOAVIS_PETICIO_FIRMADA:
                return BITACOLA_OP_NOTIFICAR_FINALITZADA;

            case (int) NOTIFICACIOAVIS_INVALIDAT:
                return BITACOLA_OP_NOTIFICAR_INVALIDADA;

            default:
                return -1;
        }
    }
}
