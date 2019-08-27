package es.caib.portafib.logic.passarela;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.model.entity.PerfilDeFirma;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PassarelaSignaturesSetWebInternalUse extends PassarelaSignatureStatus {

  protected final boolean fullView;

  protected final String entitatID;

  protected final PassarelaSignaturesSet signaturesSet;

  protected final int[] originalNumberOfSignsArray;

  protected final String applicationID;

  protected final String baseUrl;

  protected final PerfilDeFirma perfilDeFirma;

  protected final Map<String, UsuariAplicacioConfiguracioJPA> configBySignID;

  protected final Map<String, CustodiaInfoJPA> custodiaBySignID;
  
  protected final Map<String, PeticioDeFirmaJPA> peticioFirmaBySignID;

  protected final Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignatureID = new HashMap<String, PassarelaSignatureStatusWebInternalUse>();

  /**
   * @param signaturesSet
   */
  public PassarelaSignaturesSetWebInternalUse(String entitatID,
      int[] originalNumberOfSignsArray, boolean fullView,
      PassarelaSignaturesSet signaturesSet, String applicationID, String baseUrl,
      PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID,
      Map<String, CustodiaInfoJPA> custodiaBySignID,
      Map<String, PeticioDeFirmaJPA> peticioFirmaBySignID) {
    super();
    this.originalNumberOfSignsArray = originalNumberOfSignsArray;
    this.signaturesSet = signaturesSet;
    this.entitatID = entitatID;
    this.fullView = fullView;
    this.applicationID = applicationID;
    this.baseUrl = baseUrl;
    this.perfilDeFirma = perfilDeFirma;
    this.configBySignID = configBySignID;
    this.custodiaBySignID = custodiaBySignID;
    this.peticioFirmaBySignID = peticioFirmaBySignID;

    PassarelaFileInfoSignature[] files = this.signaturesSet.getFileInfoSignatureArray();

    for (PassarelaFileInfoSignature fileInfo : files) {
      statusBySignatureID.put(fileInfo.getSignID(),
          new PassarelaSignatureStatusWebInternalUse());
    }
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public PassarelaSignaturesSet getSignaturesSet() {
    return signaturesSet;
  }

  public Map<String, PassarelaSignatureStatusWebInternalUse> getStatusBySignatureID() {
    return statusBySignatureID;
  }

  public String getEntitatID() {
    return entitatID;
  }

  public int[] getOriginalNumberOfSignsArray() {
    return originalNumberOfSignsArray;
  }

  public boolean isFullView() {
    return fullView;
  }

  public String getApplicationID() {
    return applicationID;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public PerfilDeFirma getPerfilDeFirma() {
    return perfilDeFirma;
  }

  public Map<String, UsuariAplicacioConfiguracioJPA> getConfigBySignID() {
    return configBySignID;
  }

  public Map<String, CustodiaInfoJPA> getCustodiaBySignID() {
    return custodiaBySignID;
  }

  public Map<String, PeticioDeFirmaJPA> getPeticioFirmaBySignID() {
    return peticioFirmaBySignID;
  }

}