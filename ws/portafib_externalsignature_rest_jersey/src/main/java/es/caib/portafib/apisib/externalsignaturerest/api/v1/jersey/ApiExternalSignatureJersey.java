package es.caib.portafib.apisib.externalsignaturerest.api.v1.jersey;

import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.jerseycore.AbstractApisIBConnectionManagerJersey;

import com.sun.jersey.api.client.ClientResponse;

import es.caib.portafib.apisib.externalsignaturerest.api.v1.ApiExternalSignature;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioRequest;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureAvisosPeticioResponse;
import es.caib.portafib.apisib.externalsignaturerest.api.v1.ExternalSignatureError;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiExternalSignatureJersey extends
    AbstractApisIBConnectionManagerJersey<ExternalSignatureError> implements
    ApiExternalSignature {

  /**
   * @param endPointBase
   */
  public ApiExternalSignatureJersey(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiExternalSignatureJersey(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  @Override
  public ExternalSignatureAvisosPeticioResponse getAvisosPeticioPerRol(
      ExternalSignatureAvisosPeticioRequest infoRequest) throws AbstractApisIBException {

    ClientResponse response = commonCall(infoRequest, GETAVISOSPETICIOPERROL);

    ExternalSignatureAvisosPeticioResponse result = response
        .getEntity(ExternalSignatureAvisosPeticioResponse.class);

    return result;
  }

  @Override
  protected Class<ExternalSignatureError> getErrorClass() {
    return ExternalSignatureError.class;
  }

}
