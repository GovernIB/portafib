package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.ejb.UsuariAplicacioService;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.LongConstantField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.controller.webdb.TipusDocumentController;
import es.caib.portafib.back.form.webdb.TipusDocumentFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 * 
 * @author dboerner
 * @author anadal
 */
@Controller
@RequestMapping(value = "/admin/gestiotipusdoc")
public class GestioTipusDocumentAdminController extends TipusDocumentController {

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = TipusDocumentLogicaLocal.JNDI_NAME)
    protected TipusDocumentLogicaLocal tipusDocumentLogicaEjb;

    @PostConstruct
    public void init() {

        log.debug("Entra dins init()");

        // Configura com es mostra l'usuari aplicació
        usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);
        usuariAplicacioRefList.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
        usuariAplicacioRefList.setSeparator("");

    }

    /**
     * 
     * @param list
     * @author anadal
     */
    /*
    public static void tradueixTipusDocument(List<TipusDocument> list) {	  
      for (TipusDocument tipus : list) {
      long id = tipus.getTipusDocumentID();
      if (id  < 99) {        
        // Si no te traducció usar el nom
        tipus.setNom(I18NUtils.tradueix(true, tipus.getNom()));
      }
    }
    }
    */

    @Override
    public String getEntityNameCode() {
        switch (getTipusUsuari()) {
            case ADMIN:
                return "tipusdocument.admin";
            case ADEN:
                return "tipusdocument.aden";
            case ADAPP:
                return "tipusdocument.adapp";
            default:
                return "=Tipus d´Usuari desconegut: " + getTipusUsuari();
        }

    }

    @Override
    public String getEntityNameCodePlural() {
        return getEntityNameCode() + ".plural";
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, TipusDocumentFilterForm filterForm,
            List<TipusDocument> list) throws I18NException {
        //tradueixTipusDocument(list);
    }

    @Override
    public TipusDocumentFilterForm getTipusDocumentFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        TipusDocumentFilterForm tipusDocumentFilterForm;
        tipusDocumentFilterForm = super.getTipusDocumentFilterForm(pagina, mav, request);
        /**
         * Cuando un FilterForm es nuevo?
         * Cuando se crea la sesión.
         * Un FilterForm representa la composición básica (títulos, botones columnas, etc...) de un listado.
         * Durante la sesión el usuario puede des/ordenar o des/agrupar como el quiera pero, a pesar de estas
         * acciones la estructura base se debe mantener.
         * Por ello, mediante la siguiente condición inicializamos la composición del listado base
         * solo una vez: isNou()
         */
        if (tipusDocumentFilterForm.isNou()) {

            switch (getTipusUsuari()) {
                case ADMIN:
                    // Ocultam columnes
                    tipusDocumentFilterForm.addHiddenField(USUARIAPLICACIOID);
                    tipusDocumentFilterForm.addHiddenField(TIPUSDOCUMENTBASEID);
                    // No volem cap agrupacio
                    tipusDocumentFilterForm.setGroupByFields(new ArrayList<Field<?>>());
                break;
                case ADEN:
                    String idApp = LoginInfo.getInstance().getEntitat().getUsuariAplicacioID();
                    if (idApp != null) {
                        tipusDocumentFilterForm
                                .setSubTitleCode("=" + I18NUtils.tradueix("tipusdocument.aden.subtitle", idApp));
                    }
                break;
                case ADAPP:
                    tipusDocumentFilterForm.addFilterByField(USUARIAPLICACIOID);
                    tipusDocumentFilterForm.addGroupByField(USUARIAPLICACIOID);
                break;
            }

            tipusDocumentFilterForm.setVisibleExportList(true);

            // Sempre filtram per nom i descripcio
            tipusDocumentFilterForm.addFilterByField(TIPUSDOCUMENTID);
            // Poder filtrar per traduccions
            // tipusDocumentFilterForm.addFilterByField(NOM);
            tipusDocumentFilterForm.addFilterByField(DESCRIPCIO);

            tipusDocumentFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(TIPUSDOCUMENTID) });

            tipusDocumentFilterForm.setItemsPerPage(30);

        }
        return tipusDocumentFilterForm;
    }

    @Override
    public TipusDocumentForm getTipusDocumentForm(TipusDocumentJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TipusDocumentForm tipusDocumentForm = super.getTipusDocumentForm(_jpa, __isView, request, mav);

        switch (getTipusUsuari()) {
            case ADMIN:
                if (tipusDocumentForm.isNou()) {

                    HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("tipusdocument.nomesNTI"));

                    // TODO s'ha de cercar el primer lliure entre 1 i 100

                    long tipusDocID = this.tipusDocumentEjb.max(TIPUSDOCUMENTID, TIPUSDOCUMENTID.lessThan(99L));
                    if (tipusDocID == 0L) {
                        tipusDocID = 1L;
                    }
                    tipusDocumentForm.getTipusDocument().setTipusDocumentID(tipusDocID + 1);

                } else {
                    tipusDocumentForm.addReadOnlyField(TIPUSDOCUMENTID);
                }
                tipusDocumentForm.addHiddenField(TIPUSDOCUMENTBASEID);
                tipusDocumentForm.addHiddenField(USUARIAPLICACIOID);
            break;
            case ADEN:
                tipusDocumentForm.addHiddenField(TIPUSDOCUMENTID);
                tipusDocumentForm.addLabel(USUARIAPLICACIOID, "tipusdocument.usuariAplicacioID");
                tipusDocumentForm.getTipusDocument()
                        .setUsuariAplicacioID(LoginInfo.getInstance().getEntitat().getUsuariAplicacioID());
                tipusDocumentForm.addReadOnlyField(USUARIAPLICACIOID);
            break;
            case ADAPP:
                tipusDocumentForm.addHiddenField(TIPUSDOCUMENTID);
            break;
        }

        // Pendent de tiquet #161
        if (tipusDocumentForm.isNou()) {
            tipusDocumentForm.getTipusDocument().setTipusDocumentBaseID(99); // Altres: TD99
        }
        // Pendent de tiquet #161
        //tipusDocumentForm.addReadOnlyField(TIPUSDOCUMENTBASEID);

        return tipusDocumentForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where where;
        switch (getTipusUsuari()) {
            case ADMIN:
                // Tots els comuns 
                where = USUARIAPLICACIOID.isNull();
            break;
            case ADEN: {
                // Només els Tipus de usuari aplicació descrit a l'Entitat
                String idApp = LoginInfo.getInstance().getEntitat().getUsuariAplicacioID();
                if (idApp == null) {
                    where = new LongConstantField(3L).equal(4L); // FALSE
                    HtmlUtils.saveMessageError(request,
                            I18NUtils.tradueix("tipusdocument.entitatSenseUsuariAplicacio"));
                } else {
                    where = TipusDocumentFields.USUARIAPLICACIOID.equal(idApp);
                }
            }

            break;
            case ADAPP: {
                // Tos els usuaris Aplicació de la meva entitat excepte el que està assignat a l'entitat

                String entitatID = LoginInfo.getInstance().getEntitatID();
                SubQuery<UsuariAplicacio, String> subQuery;
                subQuery = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
                        UsuariAplicacioFields.ENTITATID.equal(entitatID));
                Where w1 = Where.AND(USUARIAPLICACIOID.isNotNull(), TipusDocumentFields.USUARIAPLICACIOID.in(subQuery));

                String idApp = LoginInfo.getInstance().getEntitat().getUsuariAplicacioID();
                if (idApp == null) {
                    where = w1;
                } else {
                    Where w2 = TipusDocumentFields.USUARIAPLICACIOID.notEqual(idApp);
                    where = Where.AND(w1, w2);
                }

            }
            break;

            default:
                throw new I18NException("genapp.comodi", "Tipus D'usuari Desconegut " + getTipusUsuari());
        }

        return where;
    }

    @Override
    public void postValidate(HttpServletRequest request, TipusDocumentForm tipusDocumentForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, tipusDocumentForm, result);

        if (getTipusUsuari().equals(TipusUsuari.ADMIN)) {
            //Long __tdid = (Long) result.getFieldValue(get(TIPUSDOCUMENTID));
            long __tdid = tipusDocumentForm.getTipusDocument().getTipusDocumentID();

            if (__tdid < 1L) {
                // El valor {0} del camp {1} ha de ser major o igual que {2}
                result.rejectValue(get(TIPUSDOCUMENTID), "genapp.validation.minimum", new String[] {
                        String.valueOf(__tdid), I18NUtils.tradueix(get(TIPUSDOCUMENTID)), String.valueOf(1L) }, null);
            }
            if (__tdid > 99L) {
                // El valor {0} del camp {1} ha de ser menor o igual que {2}
                result.rejectValue(get(TIPUSDOCUMENTID), "genapp.validation.maximum", new String[] {
                        String.valueOf(__tdid), I18NUtils.tradueix(get(TIPUSDOCUMENTID)), String.valueOf(99L) }, null);
            }
        } else {
            String __uaid = (String) result.getFieldValue(get(USUARIAPLICACIOID));
            if (__uaid == null || __uaid.trim().length() == 0) {
                // El camp {0} és obligatori.
                result.rejectValue(get(USUARIAPLICACIOID), "genapp.validation.required",
                        new String[] { I18NUtils.tradueix(get(USUARIAPLICACIOID)) }, null);
            }
        }
    }

    @Override
    public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request, ModelAndView mav,
            TipusDocumentForm tipusDocumentForm, Where where) throws I18NException {

        Where where2;
        if (tipusDocumentForm.isNou()) {
            where2 = UsuariAplicacioFields.ACTIU.equal(true);
        } else {
            where2 = Where.OR(UsuariAplicacioFields.ACTIU.equal(true), UsuariAplicacioFields.USUARIAPLICACIOID
                    .equal(tipusDocumentForm.getTipusDocument().getUsuariAplicacioID()));
        }
        return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, where2));
    }

    @Override
    public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where w = UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
        List<StringKeyValue> list = super.getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, w));
        
        if (getTipusUsuari().equals(TipusUsuari.ADAPP)) {
            String idApp = LoginInfo.getInstance().getEntitat().getUsuariAplicacioID();
            if (idApp != null) {
                for (StringKeyValue stringKeyValue : list) {
                    if (stringKeyValue.getKey().equals(idApp)) {
                        list.remove(stringKeyValue);
                        break;
                    }
                }
            }
        }
        
        return list;
    }

    @Override
    public String getTileList() {
        return "gestioTipusDocumentAdminList";
    }

    @Override
    public String getTileForm() {
        return "gestioTipusDocumentAdminForm";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "GestioTipusDocumentAdmin_FilterForm";
    }

    @Override
    public void delete(HttpServletRequest request, TipusDocument tipusDocument) throws I18NException {
        tipusDocumentLogicaEjb.deleteFull((TipusDocumentJPA) tipusDocument);
    }

    @Override
    public TipusDocumentJPA create(HttpServletRequest request, TipusDocumentJPA tipusDocument) throws I18NException {

        if (getTipusUsuari().equals(TipusUsuari.ADMIN)) {
            tipusDocument.setTipusDocumentBaseID(tipusDocument.getTipusDocumentID());
        }
        final boolean generateID = !getTipusUsuari().equals(TipusUsuari.ADMIN);
        return tipusDocumentLogicaEjb.create(tipusDocument, generateID);
    }

    public enum TipusUsuari {
        ADMIN, ADEN, ADAPP
    }

    public TipusUsuari getTipusUsuari() {
        return TipusUsuari.ADMIN;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusDocumentBaseID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        List<TipusDocument> tipusDocumentBase = tipusDocumentEjb.select(
                TipusDocumentFields.TIPUSDOCUMENTID.between(1L, 99L), new OrderBy(TipusDocumentFields.TIPUSDOCUMENTID));

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (TipusDocument tipusDocument : tipusDocumentBase) {

            long valNum = tipusDocument.getTipusDocumentID();
            String val = String.valueOf(tipusDocument.getTipusDocumentID());

            TipusDocumentJPA tdJPA = (TipusDocumentJPA) tipusDocument;

            __tmp.add(new StringKeyValue(val, "TD" + String.format("%02d", valNum) + " - "
                    + tdJPA.getNom().getTraduccio(I18NUtils.getLocale().getLanguage()).getValor()));
        }

        return __tmp;
    }

    /*
      @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
      public void exportList(@PathVariable("dataExporterID") String dataExporterID,
      HttpServletRequest request, HttpServletResponse response,
      TipusDocumentFilterForm filterForm) throws Exception, I18NException {
    
    
    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusDocument> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSDOCUMENT_FIELDS;
    
    
    exportData(request, response, dataExporterID, filterForm, list, allFields);
      }
    */

} // Final de Classe
