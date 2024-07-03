package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.GrupEntitatController;
import es.caib.portafib.back.form.webdb.GrupEntitatFilterForm;
import es.caib.portafib.back.form.webdb.GrupEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.GrupEntitatJPA;
import es.caib.portafib.logic.GrupEntitatLogicaLocal;
import es.caib.portafib.model.entity.GrupEntitat;

/**
 * 
 * @author anadal
 * 
 */

@Controller
@RequestMapping(value = "/aden/grup")
@SessionAttributes(types = { GrupEntitatForm.class, GrupEntitatFilterForm.class })
public class GestioGrupsController extends GrupEntitatController {

    @EJB(mappedName = GrupEntitatLogicaLocal.JNDI_NAME)
    protected GrupEntitatLogicaLocal grupEntitatLogicaEjb;

    @Override
    public GrupEntitatFilterForm getGrupEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        GrupEntitatFilterForm grupEntitatFilterForm;
        grupEntitatFilterForm = super.getGrupEntitatFilterForm(pagina, mav, request);

        if (grupEntitatFilterForm.isNou()) {
            grupEntitatFilterForm.addHiddenField(GRUPENTITATID);
            grupEntitatFilterForm.addHiddenField(ENTITATID);
        }

        grupEntitatFilterForm.setGroupByFields(new ArrayList<Field<?>>());

        return grupEntitatFilterForm;

    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public GrupEntitatForm getGrupEntitatForm(GrupEntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        GrupEntitatForm grupEntitatForm;
        grupEntitatForm = super.getGrupEntitatForm(_jpa, __isView, request, mav);

        grupEntitatForm.addHiddenField(GRUPENTITATID);
        grupEntitatForm.addHiddenField(ENTITATID);

        grupEntitatForm.getGrupEntitat().setEntitatID(LoginInfo.getInstance().getEntitatID());

        return grupEntitatForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, GrupEntitatFilterForm filterForm,
            List<GrupEntitat> list) throws I18NException {

        // Mostrar boto per editar usuaris que poden veure les meves plantilles

        filterForm.getAdditionalButtonsByPK().clear();

        for (GrupEntitat grup : list) {
            filterForm.addAdditionalButtonByPK(grup.getGrupEntitatID(),
                    new AdditionalButton("fas fa-users", "grups.gestionarpersones2",
                            "/aden/usuarisgrup/listusuaris/" + grup.getGrupEntitatID(), AdditionalButtonStyle.SUCCESS));
        }

    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Grup_Aden";
    }

    @Override
    public String getTileForm() {
        return "grupEntitatForm_aden";
    }

    @Override
    public String getTileList() {
        return "grupEntitatList_aden";
    }

    @Override
    public void delete(HttpServletRequest request, GrupEntitat grupEntitat) throws I18NException {
        grupEntitatLogicaEjb.deleteFull(grupEntitat.getGrupEntitatID());
    }

}
