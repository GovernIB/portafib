package es.caib.portafib.back.controller.aden;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author anadal (u80067)
 * 6 may 2026 12:26:35
 */
@MenuOption(
        labelCode = "=Gràfiques de consultes a Validacions de Firma",
        order = 243,
        group = Tab.MENU_ADEN,
        baseLink = "/aden/estadisticagraficavalidaciofirmes/",
        relativeLink = "/")
@Controller
@RequestMapping(value = "/aden/estadisticagraficavalidaciofirmes")
public class EstadisticaGraficaValidacioAdenController extends AbstractEstadisticaGraficaAdenController {

    @Override
    public int getCategoria() {
        return CATEGORIA_VALIDACIO_FIRMA;
    }

    @Override
    public String getCategoriaTitol() {
        return "Validació de Firmes";
    }

    @Override
    public boolean isSupportedCancelStatus() {
        return true;
    }

    @Override
    public final String getErrorLabel() {
        return "Errònies";
    }

    @Override
    public final String getCancelLabel() {
        return "Invàlides";
    }

    @Override
    public final String getOkLabel() {
        return "Vàlides";
    }

}
