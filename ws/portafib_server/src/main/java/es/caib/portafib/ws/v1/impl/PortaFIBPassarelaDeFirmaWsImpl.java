package es.caib.portafib.ws.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.AuthenticatedBaseWsImpl;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;

/**
 * 
 * @author anadal
 * 
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = PortaFIBPassarelaDeFirmaWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PFI_USER, Constants.PFI_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
@WebService(name = PortaFIBPassarelaDeFirmaWsImpl.NAME_WS, portName = PortaFIBPassarelaDeFirmaWsImpl.NAME_WS, serviceName = PortaFIBPassarelaDeFirmaWsImpl.NAME_WS
    + "Service", endpointInterface = "es.caib.portafib.ws.v1.impl."
    + PortaFIBPassarelaDeFirmaWsImpl.NAME_WS)
@WebContext(contextRoot = "/portafib/ws", urlPattern = "/v1/"
    + PortaFIBPassarelaDeFirmaWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class PortaFIBPassarelaDeFirmaWsImpl extends AuthenticatedBaseWsImpl implements
    PortaFIBPassarelaDeFirmaWs, Constants {

  public static final String NAME = "PortaFIBPassarelaDeFirma";

  public static final String NAME_WS = NAME + "Ws";


  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.PassarelaDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.logic.PassarelaDeFirmaLocal passarelaDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @Resource
  private WebServiceContext wsContext;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| Custodia |---------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public int getCustodiaPolicy() throws WsI18NException, Throwable {

    if (!LogicUtils.checkPotCustodiar(UsuariAplicacioCache.get())) {
      return CUSTODIA_NO_PERMETRE; // = 0;
    }

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    Long custInfoDefID = entitatEjb.executeQueryOne(EntitatFields.CUSTODIAINFOID,
        EntitatFields.ENTITATID.equal(userapp.getEntitatID()));

    if (custInfoDefID == null) {
      // CustodiaInfo per Defecte de l'entitat val NULL
      return Constants.CUSTODIA_NO_PERMETRE; // = 0;
    } else {

      boolean editable = custodiaInfoEjb.executeQueryOne(CustodiaInfoFields.EDITABLE,
          CustodiaInfoFields.CUSTODIAINFOID.equal(custInfoDefID));

      if (editable == false) {
        /**
         * CustodiaInfo per Defecte de l'entitat diferent de NULL i editable =
         * false
         */
        return CUSTODIA_NOMES_PLANTILLA_PER_DEFECTE; // = 1;
      } else {

        // TODO XYZ Cas no definit
        // public static final int
        // CUSTODIA_NOMES_PLANTILLES_DEFINIDES_EN_ENTITAT = 2;

        Long count = custodiaInfoEjb.count(Where.AND(
            CustodiaInfoFields.ENTITATID.equal(userapp.getEntitatID()),
            CustodiaInfoFields.NOMPLANTILLA.isNotNull() // == Plantilla
            ));

        if (count == 1) {
          /**
           * CustodiaInfo per Defecte de l'entitat diferent de NULL, editable =
           * true i només una plantilla de custòdia disponible per l'entitat
           */
          return CUSTODIA_EDITABLE_SENSE_CANVI_PLUGIN; // = 3;
        } else {
          /**
           * CustodiaInfo per Defecte de l'entitat diferent de NULL, editable =
           * true i multiples plantilles de custòdia disponible per l'entitat
           */
          return Constants.CUSTODIA_TOTALMENT_EDITABLE; // = 4;
        }
      }

    }

  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public CustodiaInfoBean getDefaultCustodiaInfo(@WebParam(name = "title") String title,
      @WebParam(name = "language") String language) throws WsI18NException, Throwable {

    if (!LogicUtils.checkPotCustodiar(UsuariAplicacioCache.get())) {
      return null;
    }

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    final String usuariEntitatID = null;
    if (language.trim().length() == 0) {
      language = userapp.getIdiomaID();
    }
    final String usuariAplicacioID = userapp.getUsuariAplicacioID();
    return peticioDeFirmaLogicaEjb.constructDefaultCustodiaInfo(title, userapp.getEntitatID(),
        usuariEntitatID, usuariAplicacioID, language);

  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public List<Long> getAllPluginIDCustodia() throws WsI18NException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    List<Long> pluginsID = pluginEjb.executeQuery(PluginFields.PLUGINID, Where.AND(
        Where.OR(PluginFields.ENTITATID.equal(userapp.getEntitatID()),
            PluginFields.ENTITATID.isNull() // Disponible per totes les entitats
            ), PluginFields.TIPUS.equal(Constants.TIPUS_PLUGIN_CUSTODIA)));
    return pluginsID;
  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  @Override
  public List<CustodiaInfoBean> getAllCustodiaInfoTemplates() throws WsI18NException,
      Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    List<CustodiaInfo> listJPA = custodiaInfoEjb.select(Where.AND(
        CustodiaInfoFields.ENTITATID.equal(userapp.getEntitatID()),
        CustodiaInfoFields.NOMPLANTILLA.isNotNull() // == Plantilla
        ));

    List<CustodiaInfoBean> list = new ArrayList<CustodiaInfoBean>(listJPA.size());

    for (CustodiaInfo custodiaInfo : listJPA) {
      list.add(new CustodiaInfoBean(custodiaInfo));
    }

    return list;

  }

  // --------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ----------------------| Segellat de Temps |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  /**
   *
   * @return
   * @throws WsI18NException
   * @throws Throwable
   */
  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public int getTimeStampPolicy() throws WsI18NException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    return passarelaDeFirmaEjb.getTimeStampPolicy(userapp.getEntitatID());

  }
  
  
  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  @Override
  public boolean providesTimeStampGenerator(String signType, 
      List<Long> filterByPluginID, List<String> filterByPluginCode)
          throws WsI18NException, Throwable {
    
    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    return passarelaDeFirmaEjb.providesTimeStampGenerator(signType,
        userapp.getEntitatID(), filterByPluginID, filterByPluginCode);
    
  }
  
  
  
  
  
  //--------------------------------------------------------------------
  //-------------------------------------------------------------------
  //----------------------| Estampació CSV |------------------------
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  
  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public List<String> getSupportedBarCodeTypes() throws WsI18NException, Throwable {
    return passarelaDeFirmaEjb.getSupportedBarCodeTypes();
  }
  
  

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ----------------------| Gestió de la Transacció |------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  
  
  
  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public String[] getSupportedSignatureTypes(List<Long> filterByPluginID,
      List<String> filterByPluginCode) throws WsI18NException, Throwable {
    
    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    return passarelaDeFirmaEjb.getSupportedSignatureTypes(userapp.getEntitatID(),
        filterByPluginID, filterByPluginCode);
  }

  @RolesAllowed({ Constants.PFI_ADMIN ,Constants.PFI_USER })
  @WebMethod
  public String[] getSupportedSignatureAlgorithms(String signType,
      List<Long> filterByPluginID,
      List<String> filterByPluginCode) throws WsI18NException, Throwable {
    
    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();
    
    return passarelaDeFirmaEjb.getSupportedSignatureAlgorithms(signType,
        userapp.getEntitatID(), filterByPluginID, filterByPluginCode);
  }
  

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public String startTransaction(
      @WebParam(name = "signaturesSet") PassarelaSignaturesSet signaturesSet)
      throws WsI18NException, WsValidationException, Throwable {

    UsuariAplicacioJPA userapp = UsuariAplicacioCache.get();

    return passarelaDeFirmaEjb.startTransaction(signaturesSet, userapp.getEntitatID());
  }

  @RolesAllowed({ Constants.PFI_ADMIN, Constants.PFI_USER })
  @WebMethod
  @Override
  public PassarelaSignatureStatus getStatusTransaction(@WebParam(name = "signaturesSetID") String signaturesSetID)
      throws WsI18NException, Throwable {

    return passarelaDeFirmaEjb.getStatusTransaction(signaturesSetID);

  }
  
  
  

  @Override
  public List<PassarelaSignatureResult> getSignatureResultsOfTransaction(
      @WebParam(name = "signaturesSetID") String signaturesSetID) throws WsI18NException,
      Throwable {

    return passarelaDeFirmaEjb.getSignatureResults(signaturesSetID);

  }

  @Override
  public void closeTransaction(String signaturesSetID) throws WsI18NException, Throwable {
    passarelaDeFirmaEjb.closeTransaction(signaturesSetID);
  }

}
