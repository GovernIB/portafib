package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.BitacolaController;
import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AbstractBitacolaAdenController extends BitacolaController {

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusOperacio(HttpServletRequest request,
                                                                 ModelAndView mav, Where where)  throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for(int op : ConstantsV2.BITACOLA_OP) {
            __tmp.add(new StringKeyValue(String.valueOf(op) , I18NUtils.tradueix("bitacola.operacio." + op)));
        }
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusObjecte(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for(int tipus : ConstantsV2.BITACOLA_TIPUS) {
            __tmp.add(new StringKeyValue(String.valueOf(tipus) , I18NUtils.tradueix("bitacola.tipus." + tipus)));
        }
        return __tmp;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        // ISSUE #321
        return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public BitacolaFilterForm getBitacolaFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {

        BitacolaFilterForm bitacolaFilterForm = super.getBitacolaFilterForm(pagina, mav, request);
        if (bitacolaFilterForm.isNou()) {

            bitacolaFilterForm.setAddButtonVisible(false);
            bitacolaFilterForm.setEditButtonVisible(false);

            bitacolaFilterForm.addHiddenField(BITACOLAID);
            bitacolaFilterForm.addHiddenField(ENTITATID);
            bitacolaFilterForm.addHiddenField(OBJECTESERIALITZAT);

            bitacolaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-eye",
                    "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));

        }

        return bitacolaFilterForm;
    }
}
