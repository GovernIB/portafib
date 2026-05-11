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
        labelCode = "=Gràfica de Consultes Firma en Servidor & Upgrade",
        order = 242,
        group = Tab.MENU_ADEN,
        baseLink = "/aden/estadisticagraficafirmaenservidor/",
        relativeLink = "/")
@Controller
@RequestMapping(value = "/aden/estadisticagraficafirmaenservidor")
public class EstadisticaGraficaFirmaEnServidorAdenController extends AbstractEstadisticaGraficaAdenController {

    @Override
    public int getCategoria() {

        return CATEGORIA_FIRMA_EN_SERVIDOR_UPGRADE;
    }

    @Override
    public String getCategoriaTitol() {
        return "Firmes en Servidor i Upgrades de Firma";
    }

    @Override
    public boolean isSupportedCancelStatus() {

        return false;
    }

}
