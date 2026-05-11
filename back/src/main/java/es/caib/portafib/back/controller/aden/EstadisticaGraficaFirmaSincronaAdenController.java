package es.caib.portafib.back.controller.aden;

import java.util.List;

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
        labelCode = "=Gràfiques de consultes a Firmes Web Síncrones",
        order = 244,
        group = Tab.MENU_ADEN,
        baseLink = "/aden/estadisticagraficafirmessincrones/",
        relativeLink = "/")
@Controller
@RequestMapping(value = "/aden/estadisticagraficafirmessincrones")
public class EstadisticaGraficaFirmaSincronaAdenController extends AbstractEstadisticaGraficaAdenController {

    @Override
    public int getCategoria() {
        return CATEGORIA_FIRMA_SINCRONA;
    }

    @Override
    public String getCategoriaTitol() {
        return "Firmes Web Síncrones";
    }

    @Override
    public boolean isSupportedCancelStatus() {
        return true;
    }

    @Override
    public List<Integer> getCreateEstadistiques() {
        return List.of(
                ESTADISTICA_TIPUS_APIFIRMASIMPLE_SINCRONA_CREADA,
                ESTADISTICA_TIPUS_APISWAGGER_SYNCV1_CREADA
        );
    }
    
    
    


}
