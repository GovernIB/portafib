package es.caib.portafib.back.controller.admin;

import java.util.Set;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.webdb.PseudonimController;
import es.caib.portafib.back.form.webdb.PseudonimFilterForm;
import es.caib.portafib.back.form.webdb.PseudonimForm;
import es.caib.portafib.back.utils.Tab;

/**
 * 
 * @author anadal
 * 18 sept 2025 8:15:48
 */
@MenuOption(labelCode = "pseudonim.pseudonim.plural", order = 48, group = Tab.MENU_ADMIN)
@Controller
@RequestMapping(value = "/admin/pseudonim")
@SessionAttributes(types = { PseudonimForm.class, PseudonimFilterForm.class })
@Tile(
        name = "pseudonimFormAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/pseudonimForm.jsp",
        extendsTile = Tab.MENU_WEBDB,
        type = TileType.WEBDB_FORM,
        attributes = { @TileAttribute(name = "titol", value = "pseudonim.pseudonim") })
@Tile(
        name = "pseudonimListAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/pseudonimList.jsp",
        extendsTile = Tab.MENU_WEBDB,
        type = TileType.WEBDB_LIST,
        attributes = { @TileAttribute(name = "titol", value = "pseudonim.pseudonim") })
public class PseudonimAdminController extends PseudonimController {

    @Override
    public String getTileForm() {
        return "pseudonimFormAdmin";
    }

    @Override
    public String getTileList() {
        return "pseudonimListAdmin";
    }

}
