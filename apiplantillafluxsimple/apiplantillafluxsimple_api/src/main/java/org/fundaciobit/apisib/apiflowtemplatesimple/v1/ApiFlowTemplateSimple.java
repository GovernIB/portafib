package org.fundaciobit.apisib.apiflowtemplatesimple.v1;

import java.util.List;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public interface ApiFlowTemplateSimple {

  // Nom de les operacions en constants

  public static final String GETTRANSACTIONID = "getTransactionID";

  public static final String STARTTRANSACTION = "startTransaction";

  public static final String GETFLOWTEMPLATERESULT = "getFlowTemplateResult";

  public static final String CLOSETRANSACTION = "closeTransaction";

  public static final String AVAILABLELANGUAGES = "getAvailableLanguages";

  public static final String GETALLFLOWTEMPLATES = "getAllFlowTemplates";

  public static final String GETALLFLOWTEMPLATESBYFILTER = "getAllFlowTemplatesByFilter";

  public static final String GETURLTOVIEWFLOWTEMPLATE = "getUrlToViewFlowTemplate";
  
  public static final String GETURLTOEDITFLOWTEMPLATE = "getUrlToEditFlowTemplate";

  public static final String DELETEFLOWTEMPLATE = "deleteFlowTemplate";

  public static final String GETFLOWINFOBYFLOWTEMPLATEID = "getFlowInfoByFlowTemplateID";



  /**
   * 
   * @param languageUI
   * @return
   * @throws AbstractApisIBException
   */
  public FlowTemplateSimpleFlowTemplateList getAllFlowTemplates(String languageUI)
      throws AbstractApisIBException;

  /**
   * 
   * @param filterBy
   * @return
   * @throws AbstractApisIBException
   */
  public FlowTemplateSimpleFlowTemplateList getAllFlowTemplatesByFilter(
      FlowTemplateSimpleFilterGetAllByFilter filterBy) throws AbstractApisIBException;

  /**
   * 
   * @param flowTemplateID
   * @return
   * @throws AbstractApisIBException
   */
  public FlowTemplateSimpleFlowTemplate getFlowInfoByFlowTemplateID(
      FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest)
      throws AbstractApisIBException;

  /**
   * 
   * @param flowTemplateRequest
   * @return
   * @throws AbstractApisIBException
   */
  public boolean deleteFlowTemplate(
      FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest)
      throws AbstractApisIBException;
  
  

  /**
   * 
   * @param flowTemplateRequest
   * @return
   * @throws AbstractApisIBException
   */
  public String getUrlToEditFlowTemplate(
      FlowTemplateSimpleEditFlowTemplateRequest editFlowTemplate)
      throws AbstractApisIBException;
  
  /**
   * 
   * @param viewFlowRequest
   * @return
   * @throws AbstractApisIBException
   */
  public String getUrlToViewFlowTemplate(
      FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest)
      throws AbstractApisIBException;
  
  
  /**
   * 
   * @param languageUI
   * @return
   * @throws AbstractFirmaAsyncSimpleException
   */
  public List<FlowTemplateSimpleKeyValue> getAvailableLanguages(String languageUI)
      throws AbstractApisIBException;

  /**
   * 
   * 
   * @param signaturesSet
   * @return Retorna l'ID de la transacció
   * @throws Exception
   */
  public String getTransactionID(FlowTemplateSimpleGetTransactionIdRequest transactionRequest)
      throws AbstractApisIBException;

  /**
   *
   * @param startTransactionInfo
   * @return Retorna la URL on redireccionar per realitzar la firma
   * @throws Exception
   */
  public String startTransaction(
      FlowTemplateSimpleStartTransactionRequest startTransactionInfo)
      throws AbstractApisIBException;

  /**
   * Retorna el flux generat
   * 
   * @param transactionID
   * @return
   * @throws Exception
   */
  public FlowTemplateSimpleGetFlowResultResponse getFlowTemplateResult(String transactionID)
      throws AbstractApisIBException;

  /**
   * 
   * @param transactionID
   * @throws Exception
   */
  public void closeTransaction(String transactionID) throws AbstractApisIBException;

}
