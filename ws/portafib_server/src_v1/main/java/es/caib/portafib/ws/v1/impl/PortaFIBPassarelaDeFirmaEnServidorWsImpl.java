package es.caib.portafib.ws.v1.impl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import es.caib.portafib.ws.utils.PortaFIBOutInterceptor;
import org.apache.cxf.interceptor.InFaultInterceptors;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import org.jboss.ws.api.annotation.TransportGuarantee;
import org.jboss.ws.api.annotation.WebContext;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.portafib.logic.passarela.PassarelaSignatureInServerResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v1.utils.PassarelaConversion;

/**
 * 
 * @author anadal
 */
@Stateless(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@InInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBInInterceptor"})
@InFaultInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBInInterceptor"})
@OutInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBOutInterceptor"})
@OutFaultInterceptors(interceptors = {"es.caib.portafib.ws.utils.PortaFIBOutInterceptor"})
@WebService(name = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v1.impl."
    + PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
    + PortaFIBPassarelaDeFirmaEnServidorWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "BASIC")
public class PortaFIBPassarelaDeFirmaEnServidorWsImpl extends
    AbstractPortaFIBPassarelaDeFirmaWsImpl implements PortaFIBPassarelaDeFirmaEnServidorWs,
    Constants {

  public static final String NAME = "PortaFIBPassarelaDeFirmaEnServidor";

  public static final String NAME_WS = NAME + "Ws";

  @EJB(mappedName = PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @Resource
  private WebServiceContext wsContext;

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public PassarelaFullResultsWs signDocuments(
      @WebParam(name = "signaturesSet") PassarelaSignaturesSetWs signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    // Recuperar Configuracio de Plugin associada a usuariAplicacio

    final boolean esFirmaEnServidor = true;


    es.caib.portafib.logic.passarela.api.PassarelaFullResults results;
    try {

      PassarelaSignaturesSet pss = PassarelaConversion.convert(signaturesSet);

      if (signaturesSet.getCommonInfoSignature().isUsePortafibCertificateFilter()) {
        pss.getCommonInfoSignature().setFiltreCertificats(
            userapp.getEntitat().getFiltreCertificats());
      }

      if (signaturesSet.getCommonInfoSignature().getUsername() == null) {
        pss.getCommonInfoSignature().setUsername(userapp.getUsuariAplicacioID());
      }
      
      
      PerfilConfiguracionsDeFirma pcf = configuracioUsuariAplicacioLogicaLocalEjb.
          getConfiguracioUsuariAplicacioPerPassarela(userapp.getUsuariAplicacioID(),
          pss, esFirmaEnServidor);

      PassarelaSignatureInServerResults psisr;

      psisr=passarelaDeFirmaEnServidorEjb.signDocuments(pss, userapp.getEntitat(),
          userapp, pcf.perfilDeFirma, pcf.configBySignID);
      
      
      results = psisr.getPassarelaFullResults();

    } catch (NoCompatibleSignaturePluginException nape) {
      throw new I18NException("signaturemodule.notfound");
    }

    return PassarelaConversion.convert(results);
  }

}
