package es.caib.portafib.ws.v1.utils;

import javax.jws.WebMethod;

import org.jboss.logging.Logger;

import es.caib.portafib.commons.utils.StaticVersion;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.BaseWsImpl;
import es.caib.portafib.ws.utils.VersionsWs;

/**
 * 
 * @author anadal
 *
 */
public class BaseV1WsImpl extends BaseWsImpl implements Constants {

    protected final Logger log = Logger.getLogger(getClass());

    // -------------------------------------------------------------------
    // -------------------------------------------------------------------
    // --------------------------| UTILITATS |----------------------------
    // -------------------------------------------------------------------
    // -------------------------------------------------------------------

    @WebMethod
    @Override
    public String getVersion() {
        //log.info("PortaFIBUsuariAplicacioWsImpl::getVersio()");
        return StaticVersion.getVersio();
    }

    @WebMethod
    @Override
    public int getVersionWs() {
        return VersionsWs.VERSIO_WS_1;
    }
}
