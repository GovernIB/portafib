package es.caib.portafib.back.controller.admin;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.comanda.model.management.Avis;
import es.caib.comanda.model.management.AvisTipus;
import es.caib.comanda.service.management.AppComandaClient;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * Gestió d'Avisos a Comanda 
 * @author anadal (u80067)
 * 12 nov 2026 8:52:09
 */

@MenuOption(
        relativeLink = "",
        baseLink = AvisComandaAdminController.CONTEXT_WEB,
        group = Tab.MENU_ADMIN,
        labelCode = "admin.aviscomanda.label",
        order = 55,
        addSeparatorBefore = true,
        condition = AvisComandaAdminController.MenuOptionConditionComandaUrl.class)
@Tile(
        name = AvisComandaAdminController.TILE_AVIS_COMANDA,
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/admin/aviscomanda.jsp",
        attributes = { @TileAttribute(name = "titol", value = "admin.aviscomanda.label") },
        type = TileType.ANOTHER)

@Controller
public class AvisComandaAdminController {

    /** Condició de MenuOption per només mostrar menú si la propietat URL de Comanda es diferent de null i no buida */
    public static class MenuOptionConditionComandaUrl
            implements org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition {

        protected Logger log = Logger.getLogger(MenuOptionConditionComandaUrl.class);

        @Override
        public boolean isVisible(HttpServletRequest request) {
            String comandaUrl = PropietatGlobalUtil.getComandaUrl();
            return (comandaUrl != null && !comandaUrl.isEmpty());
        }
    }

    public static final Logger log = Logger.getLogger(AvisComandaAdminController.class);

    public static final String CONTEXT_WEB = "/admin/aviscomanda";

    public static final String TILE_AVIS_COMANDA = "AvisComandaAdmin";

    // Format de data/hora dels inputs HTML de tipus datetime-local
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

    @RequestMapping(
            value = AvisComandaAdminController.CONTEXT_WEB,
            method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ModelAndView avisComandaGet() {

        ModelAndView mav = new ModelAndView(AvisComandaAdminController.TILE_AVIS_COMANDA);

        emplenaValorsPerDefecte(mav);

        return mav;
    }

    @RequestMapping(
            value = AvisComandaAdminController.CONTEXT_WEB,
            method = org.springframework.web.bind.annotation.RequestMethod.POST)
    public ModelAndView avisComandaPost(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView(AvisComandaAdminController.TILE_AVIS_COMANDA);

        // Camps del formulari (línies 80-86 originals)
        String tipusStr = request.getParameter("tipus");
        String titol = request.getParameter("titol");
        String descripcio = request.getParameter("descripcio");
        String dataIniciStr = request.getParameter("dataInici");
        String dataFiStr = request.getParameter("dataFi");
        String redireccioStr = request.getParameter("redireccio");

        // Deixam els valors introduïts per si s'ha de tornar a mostrar el formulari amb un error
        mav.addObject("tipusSeleccionat", tipusStr);
        mav.addObject("titol", titol);
        mav.addObject("descripcio", descripcio);
        mav.addObject("dataIniciStr", dataIniciStr);
        mav.addObject("dataFiStr", dataFiStr);
        mav.addObject("redireccioStr", redireccioStr);
        mav.addObject("tipusList", AvisTipus.values());

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

            AvisTipus tipus = AvisTipus.valueOf(tipusStr);
            OffsetDateTime dataInici = LocalDateTime.parse(dataIniciStr, formatter).atOffset(ZoneOffset.UTC);
            OffsetDateTime dataFi = LocalDateTime.parse(dataFiStr, formatter).atOffset(ZoneOffset.UTC);

            URL redireccio = (redireccioStr != null && !redireccioStr.isEmpty()) ? new URL(redireccioStr) : null;

            // Propietats de connexió amb Comanda llegides de Configuracio de portafib-commons
            final String url = getComandaRequiredProperty(PropietatGlobalUtil.getComandaUrl(), "comanda.url");
            final String username = getComandaRequiredProperty(PropietatGlobalUtil.getComandaUsername(),
                    "comanda.username");
            final String password = getComandaRequiredProperty(PropietatGlobalUtil.getComandaPassword(),
                    "comanda.password");
            final String codiAplicacio = Constants.PREFIX_APP;
            final String entornCodi = getComandaRequiredProperty(PropietatGlobalUtil.getComandaEntornCodi(),
                    "comanda.entorncodi");

            final String avisID = codiAplicacio + "_" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

            Avis avis = new Avis().appCodi(codiAplicacio).identificador(avisID).tipus(tipus).nom(titol)
                    .entornCodi(entornCodi).descripcio(descripcio).dataInici(dataInici).dataFi(dataFi)
                    .redireccio(redireccio);

            AppComandaClient client = new AppComandaClient(url, username, password);
            String resposta = client.crearAvis(avis);

            HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("admin.aviscomanda.creacio.ok", resposta));

            // Si ha anat bé, tornam a deixar el formulari amb els valors per defecte
            emplenaValorsPerDefecte(mav);

        } catch (Throwable e) {

            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("admin.aviscomanda.creacio.error", e.getMessage()));

            Throwable rootCause = e.getCause();
            String rootCauseMessage;
            if (rootCause == null) {
                rootCauseMessage = "";
            } else {
                rootCauseMessage = " ( " + rootCause.getMessage() + " )";
            }

            log.error("Error creant avís a Comanda: " + e.getMessage() + rootCauseMessage, e);

        }

        return mav;
    }

    /**
     * Obté una propietat global obligatòria de COMANDA. Si no està definida o
     * està buida llança una excepció.
     */
    private static String getComandaRequiredProperty(final String val, String partialPropertyName) throws Exception{

        if (val == null || val.trim().isEmpty()) {
            throw new Exception("La propietat global '" + Constants.PORTAFIB_PROPERTY_BASE + partialPropertyName
                    + "' és obligatòria si es volen enviar avisos a COMANDA. Vagi a 'Gestió de Propietats Globals'"
                    + " i seleccioni 'Propietats Globals Disponibles' per donar d'alta aquesta propietat");
        }
        return val.trim();
    }

    /**
     * Emplena el ModelAndView amb els valors per defecte del formulari.
     */
    private void emplenaValorsPerDefecte(ModelAndView mav) {

        //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        //
        //        LocalDateTime ara = LocalDateTime.now();

        mav.addObject("tipusList", AvisTipus.values());
        mav.addObject("tipusSeleccionat", AvisTipus.INFO.name());
        /*
        mav.addObject("titol", "Manteniment");
        mav.addObject("descripcio",
                "S'està realitzant un manteniment programat del sistema. Disculpeu les molèsties.");
        mav.addObject("dataIniciStr", ara.format(formatter));
        mav.addObject("dataFiStr", ara.plusHours(2).format(formatter));
        mav.addObject("redireccioStr", "");
        */
    }

}
