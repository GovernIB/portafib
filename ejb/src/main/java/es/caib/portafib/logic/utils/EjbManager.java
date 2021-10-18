package es.caib.portafib.logic.utils;

import javax.naming.InitialContext;

import es.caib.portafib.logic.misc.NotificacionsCallBackTimerLocal;
import es.caib.portafib.logic.notificacions.NotificacioQueueTimerLocal;
import es.caib.portafib.utils.Configuracio;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.ejb.IdiomaService;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;

/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

  protected static final Logger log = Logger.getLogger(EjbManager.class);

  protected static NotificacionsCallBackTimerLocal notificacioTimerEjb = null;

  protected static PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb = null;

  protected static FirmaLogicaLocal firmaLogicaEjb = null;

  protected static UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  protected static UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  protected static UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  protected static ColaboracioDelegacioLogicaLocal colaboracioDelegacioLogicaEjb;

  protected static IdiomaService idiomaEjb;
  
  protected static TipusDocumentLogicaLocal tipusDocumentLogicaEjb;

  protected static FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  protected static PropietatGlobalLogicaLocal propietatLogicaEjb;

  protected static BitacolaLogicaLocal bitacolaLogicaEjb;
  

  private static void throwNewI18NException(Throwable e, String name) throws I18NException {
    throw new I18NException(e, "error.unknown",
      new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
  }
  

  public static NotificacionsCallBackTimerLocal getNotificacioTimerEjb() throws I18NException {
    if (notificacioTimerEjb == null) {
      try {
        String jndiName = Configuracio.isNotificacionsQueue() ?
                NotificacioQueueTimerLocal.JNDI_NAME :
                NotificacionsCallBackTimerLocal.JNDI_NAME;

        notificacioTimerEjb = (NotificacionsCallBackTimerLocal) new InitialContext()
            .lookup(jndiName);
      } catch (Throwable e) {
        throwNewI18NException(e, "NotificacionsCallBackTimerLocal");
      }
    }
    return notificacioTimerEjb;
  }

  public static PeticioDeFirmaLogicaLocal getPeticioDeFirmaLogicaEJB() throws I18NException {

    if (peticioDeFirmaLogicaEjb == null) {
      try {
        peticioDeFirmaLogicaEjb = (PeticioDeFirmaLogicaLocal) new InitialContext()
            .lookup(PeticioDeFirmaLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "PeticioDeFirmaLogicaLocal");
      }
    }
    return peticioDeFirmaLogicaEjb;
  }

  public static FirmaLogicaLocal getFirmaLogicaEJB() throws I18NException {

    if (firmaLogicaEjb == null) {
      try {
        firmaLogicaEjb = (FirmaLogicaLocal) new InitialContext()
            .lookup(FirmaLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "FirmaLogicaLocal");
      }
    }
    return firmaLogicaEjb;
  }

  public static UsuariAplicacioLogicaLocal getUsuariAplicacioLogicaEJB() throws I18NException {

    if (usuariAplicacioLogicaEjb == null) {
      try {
        usuariAplicacioLogicaEjb = (UsuariAplicacioLogicaLocal) new InitialContext()
            .lookup(UsuariAplicacioLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "UsuariAplicacioLogicaLocal");
      }
    }
    return usuariAplicacioLogicaEjb;
  }

  public static UsuariEntitatLogicaLocal getUsuariEntitatLogicaEJB() throws I18NException {

    if (usuariEntitatLogicaEjb == null) {
      try {
        usuariEntitatLogicaEjb = (UsuariEntitatLogicaLocal) new InitialContext()
            .lookup(UsuariEntitatLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "UsuariEntitatLogicaLocal");
      }
    }
    return usuariEntitatLogicaEjb;
  }
  
  
  
  public static UsuariPersonaLogicaLocal getUsuariPersonaLogicaEJB() throws I18NException {

    if (usuariPersonaLogicaEjb == null) {
      try {
        usuariPersonaLogicaEjb = (UsuariPersonaLogicaLocal) new InitialContext()
            .lookup(UsuariPersonaLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "UsuariPersonaLogicaLocal");
      }
    }
    return usuariPersonaLogicaEjb;
  }

  

  public static ColaboracioDelegacioLogicaLocal getColaboracioDelegacioEJB() throws I18NException {
    if (colaboracioDelegacioLogicaEjb == null) {
      try {
        colaboracioDelegacioLogicaEjb = (ColaboracioDelegacioLogicaLocal) new InitialContext()
            .lookup(ColaboracioDelegacioLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "ColaboracioDelegacioLogicaEJB");
      }
    }
    return colaboracioDelegacioLogicaEjb;
  }

  public static IdiomaService getIdiomaEJB() throws I18NException {

    if (idiomaEjb == null) {
      try {
        idiomaEjb = (IdiomaService) new InitialContext()
            .lookup(IdiomaService.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "IdiomaEJB");
      }
    }
    return idiomaEjb;
  }
  

  public static TipusDocumentLogicaLocal getTipusDocumentLogicaEJB() throws I18NException {

    if (tipusDocumentLogicaEjb == null) {
      try {
        tipusDocumentLogicaEjb = (TipusDocumentLogicaLocal) new InitialContext()
            .lookup(TipusDocumentLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, "TipusDocumentLogicaEJB");
      }
    }
    return tipusDocumentLogicaEjb;
  }
  
  /**
   * S'ha tengut que fer així ja que injectat a traves del tag @EJB, el jboss 
   * llançava un error de dependències.
   */
  public static FluxDeFirmesLogicaLocal getFluxDeFirmesEjb() throws I18NException  {
    if (fluxDeFirmesLogicaEjb == null) {
      try {
        fluxDeFirmesLogicaEjb = (FluxDeFirmesLogicaLocal) new InitialContext()
            .lookup(FluxDeFirmesLogicaLocal.JNDI_NAME);
        } catch (Throwable e) {
        throwNewI18NException(e, "FluxDeFirmesLogicaEjb");
      }
    }
    return fluxDeFirmesLogicaEjb;
  }
  
  
  
  public static PropietatGlobalLogicaLocal getPropietatLogicaEJB() throws I18NException {

    if (propietatLogicaEjb == null) {
      try {
        propietatLogicaEjb = (PropietatGlobalLogicaLocal) new InitialContext()
            .lookup(PropietatGlobalLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, PropietatGlobalLogicaLocal.JNDI_NAME);
      }
    }
    return propietatLogicaEjb;
  }

  public static BitacolaLogicaLocal getBitacolaLogicaEJB() throws I18NException {

    if (bitacolaLogicaEjb == null) {
      try {
        bitacolaLogicaEjb = (BitacolaLogicaLocal) new InitialContext()
            .lookup(BitacolaLogicaLocal.JNDI_NAME);
      } catch (Throwable e) {
        throwNewI18NException(e, BitacolaLogicaLocal.JNDI_NAME);
      }
    }
    return bitacolaLogicaEjb;
  }

}
