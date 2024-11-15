package es.caib.portafib.ws.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.ws.WsFieldValidationError;
import org.fundaciobit.genapp.common.ws.WsI18NArgument;
import org.fundaciobit.genapp.common.ws.WsI18NTranslation;
import org.fundaciobit.genapp.common.ws.WsValidationException;
import org.fundaciobit.genapp.common.i18n.I18NArgument;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.logic.utils.I18NLogicUtils;

/**
 * 
 * @author anadal
 * 
 */
public class WsUtils {

    protected static final Log log = LogFactory.getLog(WsUtils.class);

    public static WsValidationException convertToWsValidationException(I18NValidationException ve, Locale locale) {
        if (ve == null) {
            return null;
        }

        StringBuffer str = new StringBuffer();
        List<WsFieldValidationError> list = new ArrayList<WsFieldValidationError>();

        for (I18NFieldError fe : ve.getFieldErrorList()) {
            I18NTranslation trans = fe.getTranslation();
            String code = trans.getCode();
            String[] args = I18NLogicUtils.tradueixArguments(locale, trans.getArgs());
            String error = I18NLogicUtils.tradueix(locale, code, args);
            Field<?> field = fe.getField();
            String fieldLabel = I18NLogicUtils.tradueix(locale, field.fullName);

            list.add(new WsFieldValidationError(field.javaName, fieldLabel, error, convertToWsTranslation(trans)));

            if (str.length() != 0) {
                str.append("\n");
            }
            str.append(fieldLabel + "(" + field.javaName + "): " + error);

        }

        return new WsValidationException(str.toString(), list);

    }

    /**
     * 
     * @param translation
     * @return
     */
    public static WsI18NTranslation convertToWsTranslation(I18NTranslation translation) {
        if (translation == null) {
            return null;
        }
        List<WsI18NArgument> args = null;
        I18NArgument[] origArgs = translation.getArgs();
        if (origArgs != null && origArgs.length != 0) {
            args = new ArrayList<WsI18NArgument>(origArgs.length);
            for (I18NArgument i18nArgument : origArgs) {
                args.add(new WsI18NArgument(i18nArgument.getValue(), i18nArgument instanceof I18NArgumentCode));
            }
        }
        return new WsI18NTranslation(translation.getCode(), args);

    }

}
