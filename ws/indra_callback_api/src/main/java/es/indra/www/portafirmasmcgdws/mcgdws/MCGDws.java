package es.indra.www.portafirmasmcgdws.mcgdws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.12-patch-04
 * Fri Mar 06 09:59:38 CET 2015
 * Generated source version: 2.2.12-patch-04
 * 
 */
 
@WebService(targetNamespace = "http://www.indra.es/portafirmasmcgdws/mcgdws", name = "MCGDws")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MCGDws {

    @WebResult(name = "callback-response", targetNamespace = "http://www.indra.es/portafirmasmcgdws/mcgdws", partName = "callback-response")
    @WebMethod(operationName = "Callback", action = "Callback")
    public CallbackResponse callback(
        @WebParam(partName = "callback-request", name = "callback-request", targetNamespace = "http://www.indra.es/portafirmasmcgdws/mcgdws")
        CallbackRequest callbackRequest
    );
}