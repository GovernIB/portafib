package es.caib.portafib.back.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
    } catch (Exception e) {
    }
    
    if (loginInfo != null) {
      try {
        String idioma = loginInfo.getUsuariPersona().getIdiomaID();    
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
  
  
}
