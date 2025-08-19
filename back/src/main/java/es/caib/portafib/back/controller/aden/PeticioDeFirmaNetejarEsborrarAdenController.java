package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

/**
 *
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PeticioDeFirmaNetejarEsborrarAdenController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
@MenuOption(group = Tab.MENU_ADEN, labelCode = "peticiodefirma.netejaesborrat", order = 210)
public class PeticioDeFirmaNetejarEsborrarAdenController extends AbstractPeticioDeFirmaAdenController {
    
    public static final String CONTEXT_WEB = "/aden/peticio/netejaesborrat";

    /**
     * AnnexAdenController conté aquesta ruta, si es vol canviar, cal canviar-la també allà
     */
    public String getAnnexPath() {
        return CONTEXT_WEB + "/gestioannexes" + "/list";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_TOTS;
    }

    @Override
    public boolean addCreateButton() {
        return false;
    }

    @Override
    public String getTileList() {
        return "peticionsDeFirmaNetejarEsborrarList";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PeticioDeFirmaNetejarEsborrar_WEB_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        Where w1 = Where.OR(TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT),
                TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT));

        String entitatID = LoginInfo.getInstance().getEntitatID();

        Where w2 = new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID().equal(entitatID);

        return Where.AND(w1, w2);
    }

    @Override
    public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
        peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

        if (peticioDeFirmaFilterForm.isNou()) {

            peticioDeFirmaFilterForm.setTitleCode("peticiodefirma.netejaesborrat.llistat");

            peticioDeFirmaFilterForm.setSubTitleCode("peticiodefirma.netejaesborrat.subtitle");

            // Per defecte seleccionam les anteriors a un mes
            peticioDeFirmaFilterForm
                    .setDataFinalFins(new Timestamp(Calendar.getInstance().getTimeInMillis() - 31556952000L / 12));

            // ordenar mes antigues primer
            peticioDeFirmaFilterForm.setOrderBy(DATAFINAL.fullName);
            peticioDeFirmaFilterForm.setOrderAsc(true);
        }
        return peticioDeFirmaFilterForm;
    }

    @Override
    public boolean isNomesConsulta() {
        return false;
    }

}
