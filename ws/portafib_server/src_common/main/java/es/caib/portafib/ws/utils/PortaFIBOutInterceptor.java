package es.caib.portafib.ws.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * 
 * @author anadal
 */
public class PortaFIBOutInterceptor extends AbstractPhaseInterceptor<Message> {

  protected final Log log = LogFactory.getLog(getClass());

  public PortaFIBOutInterceptor() {
    // Veure https://cxf.apache.org/docs/interceptors.html
    super(Phase.SEND);
  }

  @SuppressWarnings("unchecked")
  public void handleMessage(Message message) throws Fault {
    UsuariAplicacioCache.remove();
  }

/*
  @Override
  public void handleFault(Message message) {
    log.error("PortaFIBOutInterceptor::handleFault(Message arg0)");
    super.handleFault(message);
  }
*/
}