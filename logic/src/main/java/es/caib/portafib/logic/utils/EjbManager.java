package es.caib.portafib.logic.utils;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.ejb.IdiomaLocal;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
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

  // EJB Notificacions
  protected static NotificacioWSLogicaLocal notificacioLogicaEjb = null;

  protected static PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb = null;

  protected static FirmaLogicaLocal firmaLogicaEjb = null;

  protected static UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  protected static UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  protected static  UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  protected static ColaboracioDelegacioLogicaLocal colaboracioDelegacioLogicaEjb;

  protected static IdiomaLocal idiomaEjb;
  
  protected static TipusDocumentLogicaLocal tipusDocumentLogicaEjb;
  
  // Veure mètode getFluxDeFirmesEjb() per saber com es que no s'injecta
  //@EJB(mappedName = "portafib/FluxDeFirmesLogicaEJB/local")  
  private static FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  

  public static NotificacioWSLogicaLocal getNotificacioLogicaEJB() throws I18NException {

    if (notificacioLogicaEjb == null) {
      try {
        notificacioLogicaEjb = (NotificacioWSLogicaLocal) new InitialContext()
            .lookup("portafib/NotificacioLogicaEJB/local");
      } catch (Throwable e) {
        throwNewI18NException(e, "NotificacioLogicaLocal");
      }
    }

    return notificacioLogicaEjb;
  }
  
  private static void throwNewI18NException(Throwable e, String name) throws I18NException {
    throw new I18NException(e, "error.unknown",
      new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
  }
  

  public static PeticioDeFirmaLogicaLocal getPeticioDeFirmaLogicaEJB() throws I18NException {

    if (peticioDeFirmaLogicaEjb == null) {
      try {
        peticioDeFirmaLogicaEjb = (PeticioDeFirmaLogicaLocal) new InitialContext()
            .lookup("portafib/PeticioDeFirmaLogicaEJB/local");
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
            .lookup("portafib/FirmaLogicaEJB/local");
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
            .lookup("portafib/UsuariAplicacioLogicaEJB/local");
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
            .lookup("portafib/UsuariEntitatLogicaEJB/local");
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
            .lookup("portafib/ColaboracioDelegacioLogicaEJB/local");
      } catch (Throwable e) {
        throwNewI18NException(e, "ColaboracioDelegacioLogicaEJB");
      }
    }
    return colaboracioDelegacioLogicaEjb;
  }
  
  
   
  
  public static IdiomaLocal getIdiomaEJB() throws I18NException {

    if (idiomaEjb == null) {
      try {
        idiomaEjb = (IdiomaLocal) new InitialContext()
            .lookup("portafib/IdiomaEJB/local");
      } catch (Throwable e) {
        throwNewI18NException(e, "IdiomaEJB");
      }
    }
    return idiomaEjb;
  }
  
  
  
 
  
  public static TipusDocumentLogicaLocal getTipusDocumentLogicaEJB() throws I18NException {

    final String name = "TipusDocumentLogica" + "EJB" ;
    if (tipusDocumentLogicaEjb == null) {
      try {
        tipusDocumentLogicaEjb = (TipusDocumentLogicaLocal) new InitialContext()
            .lookup("portafib/" + name + "/local");
      } catch (Throwable e) {
        throwNewI18NException(e, name);
      }
    }
    return tipusDocumentLogicaEjb;
  }
  
  /**
   * S'ha tengut que fer així ja que injectat a traves del tag @EJB, el jboss 
   * llançava un error de dependències. 
   * @return
   * @throws Exception
   */
  public static FluxDeFirmesLogicaLocal getFluxDeFirmesEjb() throws I18NException  {
    if (fluxDeFirmesLogicaEjb == null) {
      try {
        fluxDeFirmesLogicaEjb = (FluxDeFirmesLogicaLocal) new InitialContext()
            .lookup("portafib/FluxDeFirmesLogicaEJB/local");
        } catch (Throwable e) {
        throwNewI18NException(e, "FluxDeFirmesLogicaEjb");
      }
    }
    return fluxDeFirmesLogicaEjb;
  }

}
