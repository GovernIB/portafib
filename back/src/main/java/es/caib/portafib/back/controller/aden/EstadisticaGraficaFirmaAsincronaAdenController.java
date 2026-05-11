package es.caib.portafib.back.controller.aden;

import java.util.List;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author anadal (u80067)
 * 8 may 2026 10:16:13
 */
@MenuOption(
        labelCode = "=Gràfiques de consultes a Firmes Web Asíncrones",
        order = 245,
        group = Tab.MENU_ADEN,
        baseLink = "/aden/estadisticagraficafirmesasincrones/",
        relativeLink = "/")
@Controller
@RequestMapping(value = "/aden/estadisticagraficafirmesasincrones")
public class EstadisticaGraficaFirmaAsincronaAdenController extends AbstractEstadisticaGraficaAdenController {

    @Override
    public int getCategoria() {
        return CATEGORIA_FIRMA_ASINCRONA;
    }

    @Override
    public String getCategoriaTitol() {
        return "Firmes Web Asíncrones";
    }

    @Override
    public boolean isSupportedCancelStatus() {
        return true;
    }

    @Override
    public String getCancelLabel() {
        return "Peticions Rebutjades";
    }

    @Override
    public String getErrorLabel() {
        return "Peticions amb Error";
    }

    @Override
    public String getCreateLabel() {
        return "Peticions Creades";
    }

    @Override
    public String getOkLabel() {
        return "Peticions Finalitzades";
    }

    @Override
    public List<Integer> getCreateEstadistiques() {
        return List.of(ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_CREADA, ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_CREADA);
    }

    @Override
    public List<Integer> getFirmesEstadistiques() {
        return List.of(ESTADISTICA_TIPUS_APIFIRMASIMPLE_ASINCRONA_FIRMA, ESTADISTICA_TIPUS_APISWAGGER_ASYNCV1_FIRMA);
    }

}
