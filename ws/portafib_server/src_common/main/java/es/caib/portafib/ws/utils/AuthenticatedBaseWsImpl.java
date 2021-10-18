package es.caib.portafib.ws.utils;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebMethod;

import org.fundaciobit.genapp.common.ws.WsI18NException;

import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.fields.IdiomaFields;

/**
 * 
 * @author anadal
 * 
 */
public abstract class AuthenticatedBaseWsImpl extends BaseWsImpl {

  @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
  protected FitxerLogicaLocal fitxerLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_USER, PFI_ADMIN })
  @WebMethod
  public List<String> getSupportedLanguages() throws WsI18NException, Throwable {
    List<String> idiomes;
    idiomes = idiomaEjb.executeQuery(IdiomaFields.IDIOMAID, IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }
  
  
  @RolesAllowed({ PFI_USER, PFI_ADMIN })
  @WebMethod
  public String getEntitatID() {
    return UsuariAplicacioCache.get().getEntitatID();
  }

}
