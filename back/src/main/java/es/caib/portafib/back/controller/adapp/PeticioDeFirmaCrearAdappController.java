package es.caib.portafib.back.controller.adapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaByTipusSolicitant.TipusSolicitant;
import es.caib.portafib.back.controller.soli.PeticioDeFirmaCrearSoliController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;


/**
 * 
 * @author anadal
 * 18 ago 2025 11:16:41
 */
/*
@MenuOption(
        group = Tab.MENU_ADAPP,
        labelCode = "peticiodefirma.crear",
        baseLink = PeticioDeFirmaCrearAdappController.CONTEXT_ADAPP_CREAR_PETICIOFIRMA,
        relativeLink = "/selectflux",
        order = 105,
        addSeparatorBefore = true)*/
@RequestMapping(value = PeticioDeFirmaCrearAdappController.CONTEXT_ADAPP_CREAR_PETICIOFIRMA)
@SessionAttributes(
        types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class,
                AnnexFilterForm.class, AnnexForm.class })
@Controller
public class PeticioDeFirmaCrearAdappController extends PeticioDeFirmaCrearSoliController {

    public static final String CONTEXT_ADAPP_CREAR_PETICIOFIRMA = "/adapp/crearpeticio";

    @Override
    public String getTileSeleccioFlux() {
        return "seleccionaFluxDeFirmaPerAplicacioForm2";
    }

    @Override
    public String getContextWebToNewRequestMapping() {
        return PeticioDeFirmaAplicacioAdappController.CONTEXT_ADAPP_PETICIOFIRMA_USRAPP + "/new";
    }
    
    public String getContextWebToListRequestMapping() {
        return PeticioDeFirmaAplicacioAdappController.CONTEXT_ADAPP_PETICIOFIRMA_USRAPP + "/list";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_APLICACIO;
    }

}
