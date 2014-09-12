package es.caib.portafib.back.preparer;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.NotificacioWSQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;


/**
 * @author anadal
 *
 */
@RunAs("PFI_USER")
@Component
public class BasePreparer extends ViewPreparerSupport implements Constants {
  
  
  public static ThreadLocal<I18NTranslation> loginErrorMessage = new ThreadLocal<I18NTranslation>();

  protected final Logger log = Logger.getLogger(getClass());
  
  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;
  
  @EJB(mappedName = "portafib/FirmaEJB/local")
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.NotificacioWSLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.NotificacioWSLocal notificacioWSEjb;
  
	@Override
	public void execute(TilesRequestContext tilesContext, 
	    AttributeContext attributeContext) throws PreparerException {

	  Map<String, Object> request = tilesContext.getRequestScope();
	  
	  

	  
	  
   	// URL 
	  // TODO ficarho dins cache (veure Capperpare.java)
	  Object[] requestObjects = tilesContext.getRequestObjects();
	  if (requestObjects[0] instanceof HttpServletRequest) {
	    HttpServletRequest httpRequest = (HttpServletRequest) requestObjects[0];

	    // Error de Login
	    I18NTranslation trans = loginErrorMessage.get();
	    if (trans == null) {
	      String msg = (String)httpRequest.getSession().getAttribute("loginerror");
	      if (msg != null) {
	        HtmlUtils.saveMessageError(httpRequest, msg);
	      }
	    } else {
	      String msg = I18NUtils.tradueix(trans);
	      HtmlUtils.saveMessageError(httpRequest, msg);
	      httpRequest.getSession().setAttribute("loginerror", msg);
	    }
	    
	    request.put("urlActual", httpRequest.getServletPath());

      // Compatibilitat IE8
	    String userAgent = httpRequest.getHeader("User-Agent");
	    if (userAgent != null) {
	      int index = userAgent.toUpperCase().indexOf("MSIE");
	      if (index != -1) {
	        try {
	           String ieversion = userAgent.substring(index + 4,userAgent.indexOf(";", index + 4));
	           if (Float.parseFloat(ieversion) < 9.0f) {
	             request.put("IE8", true);
	           }
	        } catch(Throwable e) {
	          log.debug(e);
	        }
	      }
	    }
	  }

    // Language
    Locale loc = LocaleContextHolder.getLocale();
    request.put("lang", loc.toString()); // LANG i si es necessari el country
    request.put("onlylang", loc.getLanguage()); // només el LANG

	  // Informació de Login
    LoginInfo loginInfo = LoginInfo.getInstance();

    // Posa dins la sessió la informació de Login    
    request.put("loginInfo", loginInfo);

    // Pipella
    request.put("pipella", attributeContext.getAttribute("pipella"));
    
    
    
    boolean containsRoleUser = false;
    Set<GrantedAuthority> rolesSeycon = loginInfo.getRolesPerEntitat().get(null);
    //log.info("BasePreparer:: rolesSeycon (" + rolesSeycon.size() + ")" );
    for (GrantedAuthority ga : rolesSeycon) {
      String rol = ga.getAuthority();
      //log.info("     Seycon = " + rol);
      if (Constants.ROLE_USER.equals(rol)) {
        containsRoleUser = true;
      }
    }
    
    
    
    if (containsRoleUser && loginInfo.getEntitatID() != null) {

      // Avisos
      Map<String,Long> avisos = new HashMap<String, Long>(); 
      try {      
        Set<GrantedAuthority> roles = loginInfo.getRoles(); 
        
        //log.info("BasePreparer::ROLES = " + roles);
        //log.info("BasePreparer::ROLES = " + roles.size());
        String usu_ent_actual = loginInfo.getUsuariEntitatID();      
        for (GrantedAuthority grantedAuthority : roles) {
          String rol = grantedAuthority.getAuthority();
          // ROL SOLICITANT
          if (ROLE_SOLI.equals(rol)) {
            Where w = Where.AND(
              PeticioDeFirmaFields.USUARIENTITATID.equal(usu_ent_actual),
              PeticioDeFirmaFields.AVISWEB.equal(true)
            );
            Long count = peticioDeFirmaEjb.count(w);
            if (count != 0) {
              avisos.put(rol, count);
            }
            continue;
          }
          // ROLS DESTINATARI, DELEGAT i COLABORADOR
          if (ROLE_DEST.equals(rol) 
              || ROLE_DELE.equals(rol)
              || ROLE_COLA.equals(rol)) {
            Long[] estatsDeFirma;
            if (ROLE_COLA.equals(rol)) {
              estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
                  TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR};
            } else {
              estatsDeFirma = new Long[] { TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR };
            }
            List<EstatDeFirma> estatsDeFirmaList;
            estatsDeFirmaList = estatDeFirmaLogicaEjb.getEstatDeFirmaByUsuariEntitat(
                usu_ent_actual, rol, estatsDeFirma);
            
            if (estatsDeFirmaList != null && estatsDeFirmaList.size() != 0) {
              if (log.isDebugEnabled()) {
                log.debug("Afegint avisos pel rol " + rol + " (" + estatsDeFirmaList.size()  + ")");
              }
              avisos.put(rol, new Long(estatsDeFirmaList.size()));
            }
          }
          // ROLS ADEN
          if (ROLE_ADEN.equals(rol)) {
            // Revisar si hi ha notificacion que donen errors
            
            Where w1 = NotificacioWSFields.DATAENVIAMENT.isNull();
            Where w2 = NotificacioWSFields.REINTENTS.greaterThan(5);
            Where w3 = NotificacioWSFields.BLOQUEJADA.equal(false);

            PeticioDeFirmaQueryPath pfQP = new NotificacioWSQueryPath().PETICIODEFIRMA();
            
            Where w4 = pfQP.USUARIAPLICACIOID().isNotNull();
            
            Where w5 = pfQP.USUARIAPLICACIO().ENTITATID().equal(loginInfo.getEntitatID());
            
            Long count = notificacioWSEjb.count(Where.AND(w1,w2,w3,w4,w5));
            
            if (count != null && count != 0) {
              avisos.put(rol, count);
            }
          }
        }
  
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
    /*
    Iterator<String> attribs = attributeContext.getAttributeNames();
    while ( attribs.hasNext()) {
      String at = attribs.next();
      log.info(at  + "   -->   " + );
    }
    */

    
	}

}
