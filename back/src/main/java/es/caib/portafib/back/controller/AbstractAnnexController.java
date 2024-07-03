package es.caib.portafib.back.controller;

import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.AnnexController;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.persistence.AnnexJPA;
import es.caib.portafib.logic.AnnexLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractAnnexController extends AnnexController {

    @EJB(mappedName = AnnexLogicaLocal.JNDI_NAME)
    protected AnnexLogicaLocal annexLogicaEjb;

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    protected static String[] stringByType = new String[] { "Soli", "Aden", "2Aden" };

    public static final int TYPE_SOLI = 0;

    public static final int TYPE_ADEN = 1;

    public static final int TYPE_ADEN2 = 2;

    @Override
    public String getTileForm() {
        return "annexForm" + stringByType[getType()];
    }

    @Override
    public String getTileList() {
        return "annexList" + stringByType[getType()];
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Annex_" + getType() + "_FilterForm";
    }

    public abstract int getType();

    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Long peticioDeFirmaID = getPeticioDeFirmaFromSession(request);

        return PETICIODEFIRMAID.equal(peticioDeFirmaID);
    }

    public static final String PETICIO_DE_FIRMA_FOR_ANNEX_MANAGEMENT = "PETICIO_DE_FIRMA_FOR_ANNEX_MANAGEMENT";

    public Long getPeticioDeFirmaFromSession(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(PETICIO_DE_FIRMA_FOR_ANNEX_MANAGEMENT);

    }

    public static final String BACKPAGE_FOR_ANNEX_MANAGEMENT = "BACKPAGE_FOR_ANNEX_MANAGEMENT";

    public String getBackPage(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(BACKPAGE_FOR_ANNEX_MANAGEMENT);
    }

    @Override
    public void postValidate(HttpServletRequest request, AnnexForm annexForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, annexForm, result);

        Long peticioDeFirmaID = getPeticioDeFirmaFromSession(request);

        log.debug(" ENTRA DINS postValidate => " + peticioDeFirmaID);

        int tipusFirma = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.TIPUSFIRMAID,
                PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

        Annex annex = annexForm.getAnnex();

        switch (tipusFirma) {

            case ConstantsV2.TIPUSFIRMA_PADES:
                if ((!annex.isAdjuntar() && annex.isFirmar()) || (annex.isAdjuntar() && !annex.isFirmar())) {
                    result.rejectValue(AnnexFields.ADJUNTAR.fullName, "peticiodefirma.annexos.novalid", null, null);
                    result.rejectValue(AnnexFields.FIRMAR.fullName, "peticiodefirma.annexos.novalid", null, null);
                }
            break;

            // TODO
            case ConstantsV2.TIPUSFIRMA_XADES:
            case ConstantsV2.TIPUSFIRMA_CADES:
            default:
                throw new I18NException("error.unknown", "Tipus de Firma no suportada !!!!");
        }

    }

    /**
     * 
     * @param request
     * @param annex
     * @throws Exception
     */
    @Override
    public void delete(HttpServletRequest request, Annex annex) throws I18NException {
        Set<Long> fitxers = annexLogicaEjb.deleteFull((AnnexJPA) annex);
        borrarFitxers(fitxers);
    }

    @Override
    public AnnexFilterForm getAnnexFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        AnnexFilterForm annexFilterForm = super.getAnnexFilterForm(pagina, mav, request);

        Long peticioDeFirmaID = getPeticioDeFirmaFromSession(request);

        int estat = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID,
                PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

        // Mai es pot editar, si no està bé, es s'esborra i es crea de nou
        annexFilterForm.setEditButtonVisible(false);

        if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
            annexFilterForm.setAddButtonVisible(true);
            annexFilterForm.setDeleteButtonVisible(true);
            annexFilterForm.setVisibleMultipleSelection(true);
            annexFilterForm.setDeleteSelectedButtonVisible(true);
        } else {
            annexFilterForm.setAddButtonVisible(false);
            annexFilterForm.setDeleteButtonVisible(false);
            annexFilterForm.setVisibleMultipleSelection(false);
            annexFilterForm.setDeleteSelectedButtonVisible(false);
        }

        if (annexFilterForm.isNou()) {
            annexFilterForm.addHiddenField(ANNEXID);
            annexFilterForm.addHiddenField(PETICIODEFIRMAID);
        }

        annexFilterForm.getAdditionalButtons().clear();
        annexFilterForm.addAdditionalButton(new AdditionalButton("fas fa-long-arrow-alt-left", "tornar",
                getBackPage(request), AdditionalButtonStyle.SUCCESS));
        annexFilterForm.setTitleCode("annexes.titol");

        String titleParam = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.TITOL,
                PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));
        annexFilterForm.setTitleParam(titleParam);

        annexFilterForm.setVisibleOrderBy(false);

        return annexFilterForm;

    }

    @Override
    public AnnexForm getAnnexForm(AnnexJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        AnnexForm annexForm = super.getAnnexForm(_jpa, __isView, request, mav);

        if (annexForm.isNou()) {
            Long peticioDeFirmaID = getPeticioDeFirmaFromSession(request);

            if (peticioDeFirmaID == null) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "peticioDeFirmaID val null en creació d'Annexe");
            }

            annexForm.addHiddenField(PETICIODEFIRMAID);

            Annex annex = annexForm.getAnnex();
            annex.setPeticioDeFirmaID(peticioDeFirmaID);
            annex.setFirmar(true);
            annex.setAdjuntar(true);
        }

        return annexForm;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

}
