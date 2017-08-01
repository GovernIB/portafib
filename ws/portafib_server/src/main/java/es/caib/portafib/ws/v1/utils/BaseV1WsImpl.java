package es.caib.portafib.ws.v1.utils;

import javax.jws.WebMethod;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.BaseWsImpl;
import es.caib.portafib.ws.utils.VersionsWs;
/**
 * 
 * @author anadal
 *
 */
public class BaseV1WsImpl extends BaseWsImpl implements Constants {
  
  protected final Log log = LogFactory.getLog(getClass());
  
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  
  @WebMethod
  @Override
  public String getVersion() {
    //log.info("PortaFIBUsuariAplicacioWsImpl::getVersio()");
    return LogicUtils.getVersio();
  }


  @WebMethod
  @Override
  public int getVersionWs() {
    return VersionsWs.VERSIO_WS_1;
  }
}
