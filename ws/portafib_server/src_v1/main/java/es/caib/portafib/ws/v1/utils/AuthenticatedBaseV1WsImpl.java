package es.caib.portafib.ws.v1.utils;

import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.ws.utils.AuthenticatedBaseWsImpl;
import es.caib.portafib.ws.utils.VersionsWs;
import es.caib.portafib.ws.v1.impl.FitxerBean;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.ws.WsI18NException;

import javax.activation.DataHandler;
import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.mail.util.ByteArrayDataSource;

/**
 * 
 * @author anadal
 * 
 */
public class AuthenticatedBaseV1WsImpl extends AuthenticatedBaseWsImpl {

//-------------------------------------------------------------------
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

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public FitxerBean downloadFileUsingEncryptedFileID(String encryptedFileID)
      throws WsI18NException, Throwable {

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      long fitxerID = HibernateFileUtil.decryptFileID(encryptedFileID);

      // Checks
      Fitxer fitxer = fitxerLogicaEjb.checkBasic(fitxerID);

      FitxerBean fitxerBean = new FitxerBean(fitxer);
      
      byte[] data = FileUtils.readFileToByteArray(FileSystemManager.getFile(fitxerID));

      DataHandler dh = new DataHandler(new ByteArrayDataSource(data, fitxerBean.getMime()));

      fitxerBean.setData(dh);
      
      //System.gc();

      return fitxerBean;

    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }

  }

}
