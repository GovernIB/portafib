package es.caib.portafib.back.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.aden.PlantillaCustodiaAdenController;
import es.caib.portafib.back.controller.webdb.CustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.validator.CustodiaInfoLogicValidator;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariAplicacioQueryPath;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
public abstract class AbstractCustodiaInfoController extends CustodiaInfoController implements ConstantsV2 {

    protected CustodiaInfoLogicValidator<CustodiaInfo> validator = new CustodiaInfoLogicValidator<>();

    @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
    protected PeticioDeFirmaService peticioDeFirmaEjb;

    @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
    protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @PostConstruct
    public void init() {

        // Configura com es mostra l'usuari aplicació
        this.usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);
        usuariAplicacioRefList.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
        usuariAplicacioRefList.setSeparator("");
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm,
            Throwable __e) {
        if (__e == null) {
            return getRedirectWhenCancel(request, custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID());
        } else {
            return getTileForm();
        }
    }

    @Override
    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long custodiaInfoID, Throwable __e) {
        return getRedirectWhenCancel(request, custodiaInfoID);
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long custodiaInfoID) {

        String redirectOnCustody = (String) request.getSession().getAttribute("redirectOnCustody");

        if (redirectOnCustody == null) {
            return super.getRedirectWhenCancel(request, custodiaInfoID);
        } else {
            request.getSession().removeAttribute("redirectOnCustody");
            return "redirect:" + redirectOnCustody;
        }
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm) {
        return getRedirectWhenCancel(request, custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID());
    }

    @Override
    public CustodiaInfoForm getCustodiaInfoForm(CustodiaInfoJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        // Sempre serà edit o view
        CustodiaInfoForm custodiaInfoForm = super.getCustodiaInfoForm(_jpa, __isView, request, mav);

        CustodiaInfoJPA custodia = custodiaInfoForm.getCustodiaInfo();

        if (custodia.getCustodiaDocumentID() == null) {
            custodiaInfoForm.addHiddenField(CUSTODIADOCUMENTID);
        }
        if (custodia.getUrlFitxerCustodiat() == null) {
            custodiaInfoForm.addHiddenField(URLFITXERCUSTODIAT);
        }
        if (custodia.isEditable()) {
            custodiaInfoForm.addHiddenField(EDITABLE);
        } else {
            custodiaInfoForm.addReadOnlyField(EDITABLE);
            custodiaInfoForm.setSaveButtonVisible(false);
        }

        custodiaInfoForm.addHiddenField(ENTITATID);
        custodiaInfoForm.addHiddenField(NOMPLANTILLA);
        custodiaInfoForm.addHiddenField(USUARIAPLICACIOID);
        custodiaInfoForm.addHiddenField(USUARIENTITATID);
        custodiaInfoForm.addHiddenField(CODIBARRESPOSICIOPAGINAID);

        custodiaInfoForm.addHiddenField(EXPEDIENTARXIUID);
        custodiaInfoForm.addHiddenField(DOCUMENTARXIUID);

        if (__isView) {
            return custodiaInfoForm;
        }

        PlantillaCustodiaAdenController.addHelp(custodiaInfoForm);

        List<PeticioDeFirma> list = peticioDeFirmaEjb
                .select(PeticioDeFirmaFields.CUSTODIAINFOID.equal(custodia.getCustodiaInfoID()));
        if (list.isEmpty()) {
            throw new I18NException("genapp.comodi",
                    " No hi ha petició de firma per la custodia " + custodia.getCustodiaInfoID());
        }
        PeticioDeFirma peticioDeFirma = list.get(0);

        if (custodia.getNomPlantilla() != null) {
            // És una Plantilla
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.CSV);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.CSVGENERATIONDEFINITION);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.CSVVALIDATIONWEB);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.ENIFILEDIRECTURL);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.ORIGINALFILEDIRECTURL);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.PRINTABLEFILEDIRECTURL);
            custodiaInfoForm.addHiddenField(CustodiaInfoFields.URLFITXERCUSTODIAT);

            custodiaInfoForm.addHiddenField(USUARIAPLICACIOID);
            custodiaInfoForm.addHiddenField(USUARIENTITATID);

        } else {

            if (!custodia.isEditable()) {
                custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
                Utils.hiddenEmptyFields(custodiaInfoForm, custodia, ALL_CUSTODIAINFO_FIELDS);
            }

            EntitatJPA entitatJPA = LoginInfo.getInstance().getEntitat();
            Integer politicaDeCustodia;

            // Nous camps de Peticio de Firma #281
            switch (peticioDeFirma.getOrigenPeticioDeFirma()) {

                case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
                    // Usuari Entitat
                    politicaDeCustodia = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUE(
                            peticioDeFirma.getSolicitantUsuariEntitat1ID(), entitatJPA);
                break;

                case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
                case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
                    // Usuari Aplicació
                    politicaDeCustodia = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUA(
                            peticioDeFirma.getSolicitantUsuariAplicacioID(), entitatJPA);
                break;

                default:
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            " No hi ha codi per obtenir la Politica de Custodia de les Peticions de Firma amb Origen "
                                    + I18NUtils.tradueix(
                                            "origenpeticiodefirma." + peticioDeFirma.getOrigenPeticioDeFirma()));
            }

            switch (politicaDeCustodia) {

                // Obligatori Plantilla definida en Entitat, Usuari-Entitat o Usuari-Aplicació.
                case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: // = 2
                    custodiaInfoForm.setDeleteButtonVisible(false);
                    custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
                break;

                // Només Plantilles de l´Entitat (No editables)
                case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT: // = 1;
                    HtmlUtils.saveMessageWarning(request,
                            "Aquesta politica només permet alguna de les plantilles de l'entitat. "
                                    + "Encara no s'ha desenvolupat codi per canviar Politica de Custòdia per una altre ");
                    custodiaInfoForm.setDeleteButtonVisible(false);
                break;

                // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
                case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU: // = 3
                    // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
                case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU: // = 4
                    custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
                    custodiaInfoForm.setDeleteButtonVisible(true);
                break;

                // Llibertat Total (selecció, edició i us)
                case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL: // = 5;
                    custodiaInfoForm.setDeleteButtonVisible(true);
                break;

                case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
                    HtmlUtils.saveMessageWarning(request,
                            "La petició es va crear amb custòdia, però actualment ja no és permet."
                                    + "L'únic canvi que es pot realitzar és esborrar la custòdia.");
                    custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
                    custodiaInfoForm.setSaveButtonVisible(false);
                    custodiaInfoForm.setDeleteButtonVisible(true);
                break;

                default:
                    throw new I18NException("genapp.comodi", "Política de custòdia desconeguda: " + politicaDeCustodia);
            }

            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.CSV);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.CSVGENERATIONDEFINITION);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.CSVVALIDATIONWEB);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.ENIFILEDIRECTURL);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.ORIGINALFILEDIRECTURL);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.PRINTABLEFILEDIRECTURL);
            custodiaInfoForm.addReadOnlyField(CustodiaInfoFields.URLFITXERCUSTODIAT);
        }

        custodiaInfoForm.addHiddenField(TITOLPETICIO);
        custodiaInfoForm.addHiddenField(DATACUSTODIA);

        return custodiaInfoForm;
    }

    /**
     * 
     * @return
     */
    public abstract boolean isSolicitantWeb();

    @Override
    public String getTileForm() {
        return "custodiaInfoForm_" + (isSolicitantWeb() ? "soli" : "aden");
    }

    @Override
    public void delete(HttpServletRequest request, CustodiaInfo custodiaInfo) throws I18NException {
        custodiaInfoLogicaEjb.deleteCustodiaInfoOfPeticioDeFirma(custodiaInfo);
    }

    @Override
    public String getTileList() {
        return "custodiaInfoList_" + (isSolicitantWeb() ? "soli" : "aden");
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "CustodiaInfo_FilterForm_" + (isSolicitantWeb() ? "soli" : "aden");
    }

    @Override
    public CustodiaInfoFilterForm getCustodiaInfoFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        CustodiaInfoFilterForm filterForm = super.getCustodiaInfoFilterForm(pagina, mav, request);

        if (filterForm.isNou()) {
            Set<Field<?>> hiddenFields = filterForm.getHiddenFields();
            hiddenFields.addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));

            // hiddenFields.remove(CUSTODIAINFOID);
            hiddenFields.remove(CUSTODIADOCUMENTID);

            if (!isSolicitantWeb()) {
                hiddenFields.remove(USUARIAPLICACIOID);
            }

            filterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(DATACUSTODIA, OrderType.DESC) });

            hiddenFields.remove(TITOLPETICIO);
            hiddenFields.remove(DATACUSTODIA);

            filterForm.addLabel(CUSTODIADOCUMENTID, "custodiaInfo.custodiaInfo");

            filterForm.setAddButtonVisible(false);

            filterForm.setDeleteButtonVisible(false);

            filterForm.setEditButtonVisible(false);

            filterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-eye icon-white", "vistacompleta",
                    getContextWeb() + "/view/{0}", "btn-info"));

            // TODO AGRUPAR i LLISTAT
            filterForm.setDeleteSelectedButtonVisible(false);
            filterForm.setVisibleMultipleSelection(false);

        }

        return filterForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        // Només les finalitzades o les que ja no tenen petició

        Where wFinished = Where.OR(
                // Només les finalitzades
                CUSTODIAINFOID.in(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID,
                        Where.AND(
                                PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID
                                        .equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT),
                                PeticioDeFirmaFields.CUSTODIAINFOID.isNotNull()))),
                // Les que ja no tenen peticio de firma
                CUSTODIAINFOID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID,
                        PeticioDeFirmaFields.CUSTODIAINFOID.isNotNull())));

        // Per usuaris entitat o usuaris aplicacio
        Where wUser;
        if (isSolicitantWeb()) {
            wUser = USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID());
        } else {
            UsuariAplicacioQueryPath uaqp = new UsuariAplicacioQueryPath();
            wUser = Where.AND(USUARIENTITATID.isNull(), USUARIAPLICACIOID.isNotNull(),
                    uaqp.ENTITATID().equal(LoginInfo.getInstance().getEntitat().getEntitatID()));
        }

        // No volem plantilles
        Where wCommon = Where.AND(ENTITATID.isNull(), NOMPLANTILLA.isNull(), CUSTODIADOCUMENTID.isNotNull(),
                PLUGINID.isNotNull());

        return Where.AND(wUser, wCommon, wFinished);
    }

    @Override
    @InitBinder("custodiaInfoForm")
    public void initBinderForm(WebDataBinder binder) {
        super.initBinderForm(binder);
        getWebValidator().setValidator(validator);
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, CustodiaInfoFilterForm filterForm,
            List<CustodiaInfo> list) throws I18NException {

        // Mostrar boto per editar usuaris que poden veure les meves plantilles

        filterForm.getAdditionalButtonsByPK().clear();

        for (CustodiaInfo cust : list) {
            String url = cust.getUrlFitxerCustodiat();
            if (url != null && url.trim().length() != 0)
                filterForm.addAdditionalButtonByPK(cust.getCustodiaInfoID(),
                        new AdditionalButton("fas fa-download  icon-white", "descarregar", url, "btn-success"));
        }

    }

    // #199
    @Override
    public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return internalReferenceListForPosicioPagina();
    }

    // #199
    @Override
    public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        return internalReferenceListForPosicioPagina();
    }

    @Override
    public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        Where w = Where.AND(where, PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_CUSTODIA));
        return pluginRefList.getReferenceList(PluginFields.PLUGINID, w);
    }

    public static List<StringKeyValue> internalReferenceListForPosicioPagina() {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < 5; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(i), I18NUtils.tradueix("posiciopagina." + i)));
        }
        return __tmp;
    }

}
