package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractAnnexController;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;


/**
 * Controlador d'annexos per l'aplicació ADAPP.
 * <p>
 * Aquest controlador gestiona les operacions relacionades amb els annexos dins el context
 * de l'aplicació ADAPP. Estén la funcionalitat de {@link AbstractAnnexController} i
 * defineix el tipus d'annex específic per ADAPP.
 * </p>
 *
 * <p>
 * Les rutes gestionades per aquest controlador estan prefixades amb el context
 * definit a {@link PeticioDeFirmaAplicacioAdappController#CONTEXT_ADAPP_PETICIOFIRMA_USRAPP}.
 * </p>
 *
 * @author anadal(u80067)
 */
@Controller
@RequestMapping(value = PeticioDeFirmaAplicacioAdappController.CONTEXT_ADAPP_PETICIOFIRMA_USRAPP + "/gestioannexes")
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexAdappController extends AbstractAnnexController {

    /**
     * Retorna el tipus d'annex associat a l'aplicació ADAPP.
     *
     * @return el codi identificador del tipus ADAPP
     */
    @Override
    public int getType() {
        return TYPE_ADAPP;
    }

}