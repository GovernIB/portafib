package es.caib.portafib.apisib.externalsignaturerest.api.v1;

import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public interface ApiExternalSignature {

  // Nom de les operacions en constants
  public static final String GETAVISOSPETICIOPERROL = "getAvisosPeticioPerRol";

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // -----------------------| Petició de Firma |------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  public ExternalSignatureAvisosPeticioResponse getAvisosPeticioPerRol(
      ExternalSignatureAvisosPeticioRequest infoRequest) throws AbstractApisIBException;

}
