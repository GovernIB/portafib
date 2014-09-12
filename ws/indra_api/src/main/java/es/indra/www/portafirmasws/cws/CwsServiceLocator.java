/**
 * CwsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.indra.www.portafirmasws.cws;
@SuppressWarnings({"rawtypes"})
public class CwsServiceLocator extends org.apache.axis.client.Service implements es.indra.www.portafirmasws.cws.CwsService {

    public CwsServiceLocator() {
    }


    public CwsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CwsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CWS
    private java.lang.String CWS_address = "http://tapplin2.test.lab:48080/portafirmasws/web/services/CWS";

    public java.lang.String getCWSAddress() {
        return CWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CWSWSDDServiceName = "CWS";

    public java.lang.String getCWSWSDDServiceName() {
        return CWSWSDDServiceName;
    }

    public void setCWSWSDDServiceName(java.lang.String name) {
        CWSWSDDServiceName = name;
    }

    public es.indra.www.portafirmasws.cws.Cws getCWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCWS(endpoint);
    }

    public es.indra.www.portafirmasws.cws.Cws getCWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.indra.www.portafirmasws.cws.CWSSoapBindingStub _stub = new es.indra.www.portafirmasws.cws.CWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getCWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCWSEndpointAddress(java.lang.String address) {
        CWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.indra.www.portafirmasws.cws.Cws.class.isAssignableFrom(serviceEndpointInterface)) {
                es.indra.www.portafirmasws.cws.CWSSoapBindingStub _stub = new es.indra.www.portafirmasws.cws.CWSSoapBindingStub(new java.net.URL(CWS_address), this);
                _stub.setPortName(getCWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CWS".equals(inputPortName)) {
            return getCWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "CwsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.indra.es/portafirmasws/cws", "CWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CWS".equals(portName)) {
            setCWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
