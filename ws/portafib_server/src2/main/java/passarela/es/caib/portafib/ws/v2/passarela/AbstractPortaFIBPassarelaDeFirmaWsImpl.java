package es.caib.portafib.ws.v2.passarela;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.ws.WsI18NException;

import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.model.bean.CustodiaInfoBean;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.utils.UsuariAplicacioCache;
import es.caib.portafib.ws.v2.impl.utils.AuthenticatedBaseWsImpl;

/**
 * @author anadal
 *
 */
public abstract class AbstractPortaFIBPassarelaDeFirmaWsImpl extends AuthenticatedBaseWsImpl
   implements AbstractPortaFIBPassarelaDeFirmaWs {
  

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  
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
  // -------------------| Tipus de Firmes Suportades |------------------
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
  
  
}
