package es.caib.portafib.back.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.commons.utils.Configuracio;
import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import es.caib.portafib.back.security.LoginInfo;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBSessionLocaleResolver extends SessionLocaleResolver {

  protected final Logger log = Logger.getLogger(getClass());
  
  @Override
  protected Locale determineDefaultLocale(HttpServletRequest request) {
    LoginInfo loginInfo = null;
    try {
      loginInfo = LoginInfo.getInstance();  
    } catch (Exception ignore) {}
    
    if (loginInfo != null) {
      try {
        String idioma = Configuracio.getDefaultLanguage();
        if (loginInfo.getUsuariPersona() != null) {
          idioma = loginInfo.getUsuariPersona().getIdiomaID();
        } else if (loginInfo.getUsuariAplicacio() != null) {
          idioma = loginInfo.getUsuariAplicacio().getIdiomaID();
        }
        Locale loc = new Locale(idioma);
        LocaleContextHolder.setLocale(loc);
        try {
          this.setLocale(request, null, loc);
        } catch(Exception e) {
           WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, loc);
        }
        return loc;
      } catch(Exception e) {
        log.error(e.getMessage(), e);
      }
    }
    
    return super.determineDefaultLocale(request);  
  }

  public static void setLocaleManually(HttpServletRequest request, String idioma) {
    Locale loc = new Locale(idioma);
    LocaleContextHolder.setLocale(loc);
    WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, loc);
  }
  
}
