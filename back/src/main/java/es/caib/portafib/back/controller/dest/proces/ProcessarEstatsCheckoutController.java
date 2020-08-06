package es.caib.portafib.back.controller.dest.proces;

import es.caib.portafib.back.controller.webdb.EstatDeFirmaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Controller per gestionar els estats de firma de dins un carret.
 *
 * @author areus
 */
@Controller
@RequestMapping(value = ProcessarEstatsCheckoutController.CONTEXT_WEB)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class ProcessarEstatsCheckoutController extends EstatDeFirmaController {

    public static final String CONTEXT_WEB = "/dest/processar/checkout";

    private static final int COLUMN_TITOL_PETICIO = -3;
    private static final int COLUMN_TIPUS_DOCUMENT = -2;
    private static final int COLUMN_REMITENT = -1;
    private static final int COLUMN_ACCIO = 1;


    @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileList() {
        return "estatFirmaList_ROLE_DEST";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "checkout_FilterForm";
    }


    @Override
    public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
                                                            HttpServletRequest request) throws I18NException {

        EstatDeFirmaFilterForm filterForm = super.getEstatDeFirmaFilterForm(pagina, mav, request);

        if (filterForm.isNou()) {
            filterForm.setVisibleMultipleSelection(false);
            filterForm.setVisibleFilterBy(false);
            filterForm.setVisibleGroupBy(false);
            filterForm.setAddButtonVisible(false);
            filterForm.setDeleteSelectedButtonVisible(false);
            filterForm.setDeleteButtonVisible(false);
            filterForm.setEditButtonVisible(false);

            filterForm.setGroupByFields(Collections.<Field<?>>emptyList());
            filterForm.setFilterByFields(Collections.<Field<?>>emptyList());

            filterForm.setHiddenFields(
                    new HashSet<Field<?>>(
                            Arrays.asList(EstatDeFirmaFields.ALL_ESTATDEFIRMA_FIELDS)));
            filterForm.setItemsPerPage(-1);
            filterForm.setAttachedAdditionalJspCode(true);

            // NOUS CAMPS
            {
                AdditionalField<Long, String> addField = new AdditionalField<Long, String>();
                addField.setCodeName("document");
                addField.setPosition(COLUMN_TITOL_PETICIO);
                addField.setValueMap(new HashMap<Long, String>());
                filterForm.addAdditionalField(addField);
            }
            {
                AdditionalField<Long, String> addField = new AdditionalField<Long, String>();
                addField.setCodeName("tipus");
                addField.setPosition(COLUMN_TIPUS_DOCUMENT);
                addField.setValueMap(new HashMap<Long, String>());
                filterForm.addAdditionalField(addField);
            }
            {
                AdditionalField<Long, String> addField = new AdditionalField<Long, String>();
                addField.setCodeName("peticioDeFirma.remitentNom");
                addField.setPosition(COLUMN_REMITENT);
                addField.setEscapeXml(false);
                addField.setValueMap(new HashMap<Long, String>());
                filterForm.addAdditionalField(addField);
            }
            {
                AdditionalField<Long, String> addField = new AdditionalField<Long, String>();
                addField.setCodeName("carret.processar.accio");
                addField.setPosition(COLUMN_ACCIO);
                addField.setEscapeXml(false);
                addField.setValueMap(new HashMap<Long, String>());
                filterForm.addAdditionalField(addField);
            }

            // BOTONERA
            filterForm.setContexte(ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT);

            filterForm.addAdditionalButton(
                    new AdditionalButton("icon-off", "carret.processar.cancelar",
                            ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/processar/cancelar", "btn-danger"));

            filterForm.addAdditionalButton(
                    new AdditionalButton("icon-edit", "carret.processar.executar",
                            "javascript:processarExecutar()", "btn-success"));

        }

        filterForm.setTitleCode("carret.processar.checkout");
        request.setAttribute("checkoutList", true);

        return filterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav,
                         EstatDeFirmaFilterForm filterForm, List<EstatDeFirma> list) throws I18NException {
        super.postList(request, mav, filterForm, list);

        String language = LoginInfo.getInstance().getUsuariPersona().getIdiomaID();
        Carret carret = CarretHolder.getCarret(request);

        List<Long> estatDeFirmaIDList = new ArrayList<Long>(list.size());
        for (EstatDeFirma estat : list) {
            estatDeFirmaIDList.add(estat.getEstatDeFirmaID());
        }
        Map<Long, PeticioDeFirma> peticionsByEstat = estatDeFirmaLogicaEjb.getPeticioDeFirmaFromEstatDeFirmaID(estatDeFirmaIDList);

        Map<Long, String> mapTitol = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_TITOL_PETICIO).getValueMap();
        mapTitol.clear();

        Map<Long, String> mapTipus = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_TIPUS_DOCUMENT).getValueMap();
        mapTipus.clear();

        Map<Long, String> mapRemitent = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_REMITENT).getValueMap();
        mapRemitent.clear();

        Map<Long, String> mapAccio = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_ACCIO).getValueMap();
        mapRemitent.clear();

        for (Long estatDeFirmaId : peticionsByEstat.keySet()) {
            PeticioDeFirmaJPA peticio = (PeticioDeFirmaJPA) peticionsByEstat.get(estatDeFirmaId);
            mapTitol.put(estatDeFirmaId, peticio.getTitol());
            mapTipus.put(estatDeFirmaId, peticio.getTipusDocument().getNomTraduccions().get(language).getValor());

            StringBuilder remitentBuilder = new StringBuilder();
            remitentBuilder.append("<small><b>").append(peticio.getRemitentNom()).append("</b>");
            String remiDesc = peticio.getRemitentDescripcio();
            if (remiDesc != null) {
                remitentBuilder.append("<br/>").append(remiDesc);
            }
            remitentBuilder.append("</small>");
            mapRemitent.put(estatDeFirmaId, remitentBuilder.toString());

            String accio;
            if (carret.getEstatsFirmar().contains(estatDeFirmaId)) {
                accio = "<button type=\"button\" class=\"btn btn-small btn-success\" disabled=\"disabled\">" +
                        I18NUtils.tradueix("carret.processar.accio.firmar") +
                        "</button>";
            } else if (carret.getEstatsRebuig().containsKey(estatDeFirmaId)) {
                String motiu = carret.getEstatsRebuig().get(estatDeFirmaId);
                accio = "<button type=\"button\" class=\"btn btn-small btn-danger\" disabled=\"disabled\">" +
                        I18NUtils.tradueix("carret.processar.accio.rebutjar", motiu) +
                        "</button>";
            } else {
                accio = "<button type=\"button\" class=\"btn btn-small btn-warning\" disabled=\"disabled\">" +
                        I18NUtils.tradueix("carret.processar.accio.ignorar") +
                        "</button>";
            }
            mapAccio.put(estatDeFirmaId, accio);
        }
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Carret carret = CarretHolder.getCarret(request);
        return EstatDeFirmaFields.ESTATDEFIRMAID.in(carret.getEstatsProcessar());
    }


}
