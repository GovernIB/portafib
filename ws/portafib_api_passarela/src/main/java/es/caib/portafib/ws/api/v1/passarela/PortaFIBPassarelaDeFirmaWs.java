package es.caib.portafib.ws.api.v1.passarela;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2016-04-21T08:13:31.197+02:00
 * Generated source version: 2.6.4
 * 
 */
@WebService(targetNamespace = "http://impl.v1.ws.portafib.caib.es/", name = "PortaFIBPassarelaDeFirmaWs")
@XmlSeeAlso({ObjectFactory.class})
public interface PortaFIBPassarelaDeFirmaWs {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getEntitatID", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetEntitatID")
    @WebMethod
    @ResponseWrapper(localName = "getEntitatIDResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetEntitatIDResponse")
    public java.lang.String getEntitatID();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getTimeStampPolicy", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetTimeStampPolicy")
    @WebMethod
    @ResponseWrapper(localName = "getTimeStampPolicyResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetTimeStampPolicyResponse")
    public int getTimeStampPolicy() throws WsI18NException;

    @RequestWrapper(localName = "closeTransaction", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.CloseTransaction")
    @WebMethod
    @ResponseWrapper(localName = "closeTransactionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.CloseTransactionResponse")
    public void closeTransaction(
        @WebParam(name = "signaturesSetID", targetNamespace = "")
        java.lang.String signaturesSetID
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSupportedSignatureAlgorithms", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedSignatureAlgorithms")
    @WebMethod
    @ResponseWrapper(localName = "getSupportedSignatureAlgorithmsResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedSignatureAlgorithmsResponse")
    public java.util.List<java.lang.String> getSupportedSignatureAlgorithms(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSupportedBarCodeTypes", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedBarCodeTypes")
    @WebMethod
    @ResponseWrapper(localName = "getSupportedBarCodeTypesResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedBarCodeTypesResponse")
    public java.util.List<java.lang.String> getSupportedBarCodeTypes() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSignatureResultsOfTransaction", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSignatureResultsOfTransaction")
    @WebMethod
    @ResponseWrapper(localName = "getSignatureResultsOfTransactionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSignatureResultsOfTransactionResponse")
    public java.util.List<es.caib.portafib.ws.api.v1.passarela.PassarelaSignatureResult> getSignatureResultsOfTransaction(
        @WebParam(name = "signaturesSetID", targetNamespace = "")
        java.lang.String signaturesSetID
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersionWs", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetVersionWs")
    @WebMethod
    @ResponseWrapper(localName = "getVersionWsResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetVersionWsResponse")
    public int getVersionWs();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getStatusTransaction", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetStatusTransaction")
    @WebMethod
    @ResponseWrapper(localName = "getStatusTransactionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetStatusTransactionResponse")
    public es.caib.portafib.ws.api.v1.passarela.PassarelaSignatureStatus getStatusTransaction(
        @WebParam(name = "signaturesSetID", targetNamespace = "")
        java.lang.String signaturesSetID
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSupportedLanguages", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedLanguages")
    @WebMethod
    @ResponseWrapper(localName = "getSupportedLanguagesResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedLanguagesResponse")
    public java.util.List<java.lang.String> getSupportedLanguages() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "downloadFileUsingEncryptedFileID", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.DownloadFileUsingEncryptedFileID")
    @WebMethod
    @ResponseWrapper(localName = "downloadFileUsingEncryptedFileIDResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.DownloadFileUsingEncryptedFileIDResponse")
    public es.caib.portafib.ws.api.v1.passarela.FitxerBean downloadFileUsingEncryptedFileID(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getSupportedSignatureTypes", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedSignatureTypes")
    @WebMethod
    @ResponseWrapper(localName = "getSupportedSignatureTypesResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetSupportedSignatureTypesResponse")
    public java.util.List<java.lang.String> getSupportedSignatureTypes() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAllPluginIDCustodia", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetAllPluginIDCustodia")
    @WebMethod
    @ResponseWrapper(localName = "getAllPluginIDCustodiaResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetAllPluginIDCustodiaResponse")
    public java.util.List<java.lang.Long> getAllPluginIDCustodia() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getCustodiaPolicy", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetCustodiaPolicy")
    @WebMethod
    @ResponseWrapper(localName = "getCustodiaPolicyResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetCustodiaPolicyResponse")
    public int getCustodiaPolicy() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "startTransaction", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.StartTransaction")
    @WebMethod
    @ResponseWrapper(localName = "startTransactionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.StartTransactionResponse")
    public java.lang.String startTransaction(
        @WebParam(name = "signaturesSet", targetNamespace = "")
        es.caib.portafib.ws.api.v1.passarela.PassarelaSignaturesSet signaturesSet
    ) throws WsI18NException, WsValidationException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDefaultCustodiaInfo", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetDefaultCustodiaInfo")
    @WebMethod
    @ResponseWrapper(localName = "getDefaultCustodiaInfoResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetDefaultCustodiaInfoResponse")
    public es.caib.portafib.ws.api.v1.passarela.CustodiaInfoBean getDefaultCustodiaInfo(
        @WebParam(name = "title", targetNamespace = "")
        java.lang.String title,
        @WebParam(name = "language", targetNamespace = "")
        java.lang.String language
    ) throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAllCustodiaInfoTemplates", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetAllCustodiaInfoTemplates")
    @WebMethod
    @ResponseWrapper(localName = "getAllCustodiaInfoTemplatesResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetAllCustodiaInfoTemplatesResponse")
    public java.util.List<es.caib.portafib.ws.api.v1.passarela.CustodiaInfoBean> getAllCustodiaInfoTemplates() throws WsI18NException;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersion", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetVersion")
    @WebMethod
    @ResponseWrapper(localName = "getVersionResponse", targetNamespace = "http://impl.v1.ws.portafib.caib.es/", className = "es.caib.portafib.ws.api.v1.passarela.GetVersionResponse")
    public java.lang.String getVersion();
}
