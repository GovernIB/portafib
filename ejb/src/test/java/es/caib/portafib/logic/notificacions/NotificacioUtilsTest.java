package es.caib.portafib.logic.notificacions;

import org.junit.Assert;
import org.junit.Test;

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
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR;
import static es.caib.portafib.utils.ConstantsV2.NOTIFICACIOAVIS_VALIDAT;

public class NotificacioUtilsTest {

    @Test
    public void testGetOpericioBitacolaForEventType() {
        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_ENPROCES,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_PETICIO_EN_PROCES));

        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_PAUSADA,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_PETICIO_PAUSADA));

        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_REBUTJADA,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_PETICIO_REBUTJADA));

        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_FIRMA_PARCIAL,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_FIRMA_PARCIAL));

        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_FINALITZADA,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_PETICIO_FIRMADA));

        Assert.assertEquals(BITACOLA_OP_NOTIFICAR_INVALIDADA,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_INVALIDAT));

        // Codis invàlids
        Assert.assertEquals(-1,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR));

        Assert.assertEquals(-1,
                NotificacioUtils.getOpericioBitacolaForEventType(NOTIFICACIOAVIS_VALIDAT));

    }
}
