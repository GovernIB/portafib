package es.caib.portafib.back.controller.soli;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaByTipusSolicitant;

/**
 * Controller per gestionar una PeticioDeFirma de Sol·licitant
 *
 * @author anadal(u80067)
 *
 */
public abstract class PeticioDeFirmaSoliController extends AbstractPeticioDeFirmaByTipusSolicitant {

    @Override
    public String getCustodiaContext() {
        return CustodiaInfoSoliController.SOLI_CUSTODIA_CONTEXT;
    }

    @Override
    public String getFluxPath() {
        return "/soli/fluxdefirmes";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_WEB;
    }

    @Override
    public boolean addCreateButton() {
        return true;
    }

    @Override
    public boolean isNomesConsulta() {
        return false;
    }

}
