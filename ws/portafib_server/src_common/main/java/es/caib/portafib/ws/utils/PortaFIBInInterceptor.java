package es.caib.portafib.ws.utils;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.frontend.MethodDispatcher;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

import org.apache.cxf.service.Service;

/**
 * 
 * @author anadal Veure https://cxf.apache.org/docs/interceptors.html
 */

public class PortaFIBInInterceptor extends AbstractPhaseInterceptor<Message> {

  protected static final javax.xml.namespace.QName QNAME = new javax.xml.namespace.QName("-1");

  protected final Log log = LogFactory.getLog(getClass());


  public PortaFIBInInterceptor() {
    // Veure https://cxf.apache.org/docs/interceptors.html
    super(Phase.PRE_INVOKE);
  }

  @SuppressWarnings("unchecked")
  public void handleMessage(Message message) throws Fault {
    
    boolean logEnable = log.isDebugEnabled(); 
   
    if (logEnable) {
      log.info(" ------------------ PortaFIBWSInInterceptor  --------------");
      
         
      
      try {
        
        Method method = getTargetMethod(message);
        
              
        log.info("  + Method NAME = " + method.getName());
        log.info("  + Method CLASS = " + method.getDeclaringClass());
        
  
        HttpServletRequest hsr = (HttpServletRequest)message.get("HTTP.REQUEST"); 
        log.info(" USR_1:  " +hsr.getRemoteUser());
    
        log.info(" ROLE: PFI_ADMIN  " +hsr.isUserInRole(Constants.PFI_ADMIN));
        log.info(" ROLE: PFI_USER  " +hsr.isUserInRole(Constants.PFI_USER));
        
      } catch (Exception e) {
       log.error(e.getMessage());
      }

    
      log.info("PortaFIBInInterceptor::handleMessage() =>  Thread = "
        + Thread.currentThread().getId());
  }
    

    SecurityContext context = message.get(SecurityContext.class);
    if (context == null || context.getUserPrincipal() == null) {
      log.error("S'ha cridat a l'API sense autenticar la petició.");
      return;
    }

    String userapp = context.getUserPrincipal().getName();
    // DEBUG
    if (logEnable) {
      log.info("PortaFIBInInterceptor::handleMessage() => user " + userapp);
      log.info("PortaFIBInInterceptor::handleMessage() => PFI_USER "
          + context.isUserInRole(Constants.PFI_USER));
      log.info("PortaFIBInInterceptor::handleMessage() => PFI_ADMIN "
          + context.isUserInRole(Constants.PFI_ADMIN));
    }

    UsuariAplicacioJPA usuariAplicacio = null;
    try {
      UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;
      usuariAplicacioLogicaEjb = EjbManager.getUsuariAplicacioLogicaEJB();
      usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKeyFull(userapp);
      //usuariAplicacio = getUsuariAplicacioLogicaEjb().findUsuariAplicacioJPAFull(userapp);
      if (usuariAplicacio != null) {
        log.info("PortaFIBInInterceptor::handleMessage() Usuari APP = "
            + usuariAplicacio.getUsuariAplicacioID());
      }
    } catch (Throwable e) {
      throw new SoapFault(e.getMessage(), e, QNAME);
    }

    // Si estam a CAIB llavors s'ha autenticat i autoritzat contra seycon,
    // però a més hauria de tenir usuari app donat d'alta al PortaFIB
    if (Configuracio.isCAIB()) {

      if (usuariAplicacio == null) {
        // TODO Traduccio
        // LogicI18NUtils.tradueix(loc, code, args)
        final String msg = "L´usuari aplicació " + userapp
          + " està autenticat en el domini de seguretat,"
          + " però no esta donat d'alta dins PortaFIB.";
        
        log.error("PortaFIBInInterceptor::handleMessage(CAIB) ", new Exception(msg));
        SoapFault sf;

        sf = new SoapFault(msg,QNAME);
        throw sf;
      }

    } else {
      if (usuariAplicacio == null) {
        log.error("PortaFIBInInterceptor::handleMessage(DEFAULT) Usuari APP = null");
        return;
      }
    }

    // Check si aquest usuari està actiu
    if (!usuariAplicacio.isActiu()) {
      // TODO traduir
      throw new SoapFault("L'usuari aplicació " + userapp + " no està actiu", QNAME);
    }
    
    int versio = usuariAplicacio.getCallbackVersio(); 
    if (versio == 0) {
      String msg = "Usuari Aplicació " + userapp + " funciona a traves de l'API " +
          "de Portafirmas de Indra amb versió 0. L'API a la que s'esta " +
          " cridant requereix una versió de PortaFIB (> 0) (Veure versió de CallBack)";
      log.error(msg);
      // TODO traduir
      throw new SoapFault(msg, QNAME);
    }

    UsuariAplicacioCache.put(usuariAplicacio);
  }
  
  
  
  private Method getTargetMethod(Message m) {
    BindingOperationInfo bop = m.getExchange().get(BindingOperationInfo.class);

    
    
    MethodDispatcher md = (MethodDispatcher) 
        m.getExchange().get(Service.class).get(MethodDispatcher.class.getName());
   
    return md.getMethod(bop);

}
  

  @Override
  public void handleFault(Message message) {

    Fault f = (Fault) message.getContent(Exception.class);

    log.error("PortaFIBInInterceptor::handleFault() - Code = " + f.getCode());
    log.error("PortaFIBInInterceptor::handleFault() - Msg = " + f.getMessage());

    Throwable cause = f.getCause();

    log.error("PortaFIBInInterceptor::handleFault() - Cause = " + cause);
    if (cause != null) {
      log.error("PortaFIBInInterceptor::handleFault() - Cause Class = " + cause.getClass());
      if (cause instanceof UndeclaredThrowableException) {
        log.error("PortaFIBInInterceptor::handleFault() - Cause.UndeclaredThrowable");
        cause = ((UndeclaredThrowableException) cause).getUndeclaredThrowable();
      }
      if (cause instanceof I18NException) {
        log.error("PortaFIBInInterceptor::handleFault() - CAUSE.I18NException");
        UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
        I18NException i18n = (I18NException) cause;
        String msg = I18NLogicUtils.getMessage(i18n, new Locale(userapp.getIdiomaID()));
        message.setContent(Exception.class,
        // new WsI18NException(i18n.getTraduccio(), msg, cause));
            new WsI18NException(WsUtils.convertToWsTranslation(i18n.getTraduccio()), msg, cause));
      } else if (cause instanceof I18NValidationException) {
        log.error("PortaFIBInInterceptor::handleFault() - CAUSE.ValidationException");
        UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
        I18NValidationException ve = (I18NValidationException) cause;
        message.setContent(
            Exception.class,
            WsUtils.convertToWsValidationException(ve,
                new Locale(userapp.getIdiomaID())));
      } else {
        log.error("PortaFIBInInterceptor::handleFault() - Cause.msg = " + cause.getMessage());
        log.error("PortaFIBInInterceptor::handleFault() - Cause.type = " + cause.getClass());
      }

    }
    super.handleFault(message);
  }



}