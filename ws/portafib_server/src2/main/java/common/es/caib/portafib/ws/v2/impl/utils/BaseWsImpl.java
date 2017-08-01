package es.caib.portafib.ws.v2.impl.utils;

import javax.jws.WebMethod;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.VersionsWs;
/**
 * 
 * @author anadal
 *
 */
public class BaseWsImpl implements Constants {
  
  protected final Log log = LogFactory.getLog(getClass());
  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  
  @WebMethod
  public String getVersion() {
    //log.info("PortaFIBUsuariAplicacioWsImpl::getVersio()");
    return LogicUtils.getVersio();
  }


  @WebMethod
  public int getVersionWs() {
    return VersionsWs.VERSIO_WS_2;
  }
}
