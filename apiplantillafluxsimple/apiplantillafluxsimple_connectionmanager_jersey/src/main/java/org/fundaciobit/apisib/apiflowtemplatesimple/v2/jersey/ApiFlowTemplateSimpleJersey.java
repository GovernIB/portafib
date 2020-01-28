package org.fundaciobit.apisib.apiflowtemplatesimple.v2.jersey;

import java.util.List;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleError;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.jerseycore.AbstractApisIBConnectionManagerJersey;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author anadal
 *
 */
public class ApiFlowTemplateSimpleJersey
    extends AbstractApisIBConnectionManagerJersey<FlowTemplateSimpleError>
    implements ApiFlowTemplateSimple {

  /**
   * @param endPointBase
   */
  public ApiFlowTemplateSimpleJersey(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFlowTemplateSimpleJersey(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  /**
   * 
   * @param endPointBase
   * @param username
   * @param password
   * @param ignoreServerCertificates
   */
  public ApiFlowTemplateSimpleJersey(String endPointBase, String username, String password,
      boolean ignoreServerCertificates) {
    super(endPointBase, username, password, ignoreServerCertificates);
  }

  @Override
  public List<FlowTemplateSimpleKeyValue> getAvailableLanguages(String languageUI)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(languageUI, AVAILABLELANGUAGES);

    List<FlowTemplateSimpleKeyValue> result = response
        .getEntity(new GenericType<List<FlowTemplateSimpleKeyValue>>() {
        });

    return result;
  }

  /**
   * 
   * 
   * @param signaturesSet
   * @return Retorna l'ID de la transacció
   * @throws Exception
   */
  @Override
  public String getTransactionID(FlowTemplateSimpleGetTransactionIdRequest transactionRequest)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(transactionRequest, GETTRANSACTIONID);

    String result = response.getEntity(String.class);

    result = cleanString(result);

    return result;
  }

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(
      FlowTemplateSimpleStartTransactionRequest startTransactionInfo)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(startTransactionInfo, STARTTRANSACTION);

    String result = response.getEntity(String.class);

    result = cleanString(result);

    return result;
  }

  /**
   * Retorna l'estat de la transacció.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */

  public FlowTemplateSimpleStatus getTransactionStatus(String transactionID)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(transactionID, TRANSACTIONSTATUS);

    FlowTemplateSimpleStatus result = response.getEntity(FlowTemplateSimpleStatus.class);

    return result;
  }

  /**
   * Retorna el resultat i les fitxers signats de les firmes enviades.
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FlowTemplateSimpleGetFlowResultResponse getFlowTemplateResult(String transactionID)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(transactionID, GETFLOWTEMPLATERESULT);

    FlowTemplateSimpleGetFlowResultResponse result = response
        .getEntity(FlowTemplateSimpleGetFlowResultResponse.class);

    return result;
  }

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  @Override
  public void closeTransaction(String transactionID) throws AbstractApisIBException {
    commonCall(transactionID, CLOSETRANSACTION);
  }

  @Override
  public FlowTemplateSimpleFlowTemplateList getAllFlowTemplates(String languageUI)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(languageUI, GETALLFLOWTEMPLATES);

    FlowTemplateSimpleFlowTemplateList result = response
        .getEntity(FlowTemplateSimpleFlowTemplateList.class);

    return result;
  }

  @Override
  public String getUrlToViewFlowTemplate(
      FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(viewFlowRequest, GETURLTOVIEWFLOWTEMPLATE);

    String result = response.getEntity(String.class);
    
    result = cleanString(result);

    return result;
  }

  @Override
  protected Class<FlowTemplateSimpleError> getErrorClass() {
    return FlowTemplateSimpleError.class;
  }

  @Override
  public FlowTemplateSimpleFlowTemplateList getAllFlowTemplatesByFilter(
      FlowTemplateSimpleFilterGetAllByFilter filterByDescription)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(filterByDescription, GETALLFLOWTEMPLATESBYFILTER);

    FlowTemplateSimpleFlowTemplateList result = response.getEntity(FlowTemplateSimpleFlowTemplateList.class);

    return result;
  }

  @Override
  public FlowTemplateSimpleFlowTemplate getFlowInfoByFlowTemplateID(String flowTemplateID)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(flowTemplateID, GETFLOWINFOBYFLOWTEMPLATEID);

    FlowTemplateSimpleFlowTemplate result = response.getEntity(FlowTemplateSimpleFlowTemplate.class);

    return result;
  }

}
