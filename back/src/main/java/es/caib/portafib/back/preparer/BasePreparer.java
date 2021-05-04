package es.caib.portafib.back.preparer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.security.LoginException;
import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.utils.ConstantsV2;

/**
 * @author anadal
 * @author areus
 */
@RunAs("PFI_USER")
@Component
public class BasePreparer extends ViewPreparerSupport implements ConstantsV2 {

    public static Map<String, I18NTranslation> loginErrorMessage = new HashMap<String, I18NTranslation>();

    protected final Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @Override
    public void execute(TilesRequestContext tilesContext,
                        AttributeContext attributeContext) throws PreparerException {

        Map<String, Object> request = tilesContext.getRequestScope();

        // Informació de Login
        LoginInfo loginInfo;
        try {
            loginInfo = LoginInfo.getInstance();
        } catch (LoginException e) {
            log.warn("Informació de login incorrecte a BasePreparer: " + e.getMessage());
            return;
        }

        // URL
        // TODO ficarho dins cache (veure Capperpare.java)
        Object[] requestObjects = tilesContext.getRequestObjects();
        if (requestObjects[0] instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) requestObjects[0];

            Device currentDevice = DeviceUtils.getRequiredCurrentDevice(httpRequest);
            if (currentDevice.isMobile()) {
                log.info("XYZ ZZZ IS MOBILE = true");
                httpRequest.getSession().setAttribute("isMobile", true);
                request.put("isMobile", true);
            }

            // Error de Login
            if (loginInfo.getUsuariPersona() != null) {
                final String username = loginInfo.getUsuariPersona().getUsuariPersonaID();

                I18NTranslation trans = loginErrorMessage.get(username);
                if (trans == null) {
                    String msg = (String) httpRequest.getSession().getAttribute("loginerror");
                    if (msg != null) {
                        HtmlUtils.saveMessageError(httpRequest, msg);
                    }
                } else {
                    loginErrorMessage.remove(username);
                    String msg = I18NUtils.tradueix(trans);
                    HtmlUtils.saveMessageError(httpRequest, msg);
                    httpRequest.getSession().setAttribute("loginerror", msg);
                }
            }

            request.put("urlActual", httpRequest.getServletPath());

            // Compatibilitat IE8
            String userAgent = httpRequest.getHeader("User-Agent");
            if (userAgent != null) {
                int index = userAgent.toUpperCase().indexOf("MSIE");
                if (index != -1) {
                    try {
                        String ieversion = userAgent.substring(index + 4, userAgent.indexOf(";", index + 4));
                        if (Float.parseFloat(ieversion) < 9.0f) {
                            request.put("IE8", true);
                        }
                    } catch (Throwable e) {
                        log.debug(e);
                    }
                }
            }
        }

        // Language
        Locale loc = LocaleContextHolder.getLocale();
        request.put("lang", loc.toString()); // LANG i si es necessari el country
        request.put("onlylang", loc.getLanguage()); // només el LANG

        // Posa dins la sessió la informació de Login
        request.put("loginInfo", loginInfo);

        // Pipella
        request.put("pipella", attributeContext.getAttribute("pipella"));

        boolean isUserOrAny = false;
        Set<GrantedAuthority> rolesSeycon = loginInfo.getRolesPerEntitat().get(null);
        //log.info("BasePreparer:: rolesSeycon (" + rolesSeycon.size() + ")" );
        for (GrantedAuthority ga : rolesSeycon) {
            String rol = ga.getAuthority();
            //log.info("     Seycon = " + rol);
            if (ConstantsV2.ROLE_USER.equals(rol) || ConstantsV2.ROLE_ANY.equals(rol)) {
                isUserOrAny = true;
            }
        }

        if (isUserOrAny && loginInfo.getEntitatID() != null) {

            // Avisos
            Map<String, Long> avisos = new HashMap<String, Long>();
            try {
                Set<GrantedAuthority> rolesInterns = loginInfo.getRoles();
                Set<String> roles = new HashSet<String>();
                for (GrantedAuthority grantedAuthority : rolesInterns) {
                    roles.add(grantedAuthority.getAuthority());
                }

                String usu_ent_actual = loginInfo.getUsuariEntitatID();

                avisos = estatDeFirmaLogicaEjb.getNombreAvisosUsuariEntitat(usu_ent_actual, loginInfo.getEntitatID(), roles);

            } catch (I18NException e) {
                log.error("Error intentant obtenir els avisos dels rols "
                        + I18NUtils.getMessage(e), e);
            }
            request.put("avisos", avisos);
        }


        if (attributeContext.getAttribute("menu") != null) {
            request.put("menu_tile", attributeContext.getAttribute("menu").toString());
        }
        request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());

	}
}