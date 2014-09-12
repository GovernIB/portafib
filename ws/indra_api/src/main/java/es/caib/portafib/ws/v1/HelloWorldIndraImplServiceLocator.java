/**
 * HelloWorldIndraImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.portafib.ws.v1;

@SuppressWarnings("rawtypes")
public class HelloWorldIndraImplServiceLocator extends org.apache.axis.client.Service implements es.caib.portafib.ws.v1.HelloWorldIndraImplService {

    public HelloWorldIndraImplServiceLocator() {
    }


    public HelloWorldIndraImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HelloWorldIndraImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HelloWorldIndraImplPort
    private java.lang.String HelloWorldIndraImplPort_address = "http://localhost:8080/portafibws/v2/HelloWorldIndra";

    public java.lang.String getHelloWorldIndraImplPortAddress() {
        return HelloWorldIndraImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HelloWorldIndraImplPortWSDDServiceName = "HelloWorldIndraImplPort";

    public java.lang.String getHelloWorldIndraImplPortWSDDServiceName() {
        return HelloWorldIndraImplPortWSDDServiceName;
    }

    public void setHelloWorldIndraImplPortWSDDServiceName(java.lang.String name) {
        HelloWorldIndraImplPortWSDDServiceName = name;
    }

    public es.caib.portafib.ws.v1.HelloWorldIndra getHelloWorldIndraImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HelloWorldIndraImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHelloWorldIndraImplPort(endpoint);
    }

    public es.caib.portafib.ws.v1.HelloWorldIndra getHelloWorldIndraImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.caib.portafib.ws.v1.HelloWorldIndraImplServiceSoapBindingStub _stub = new es.caib.portafib.ws.v1.HelloWorldIndraImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getHelloWorldIndraImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHelloWorldIndraImplPortEndpointAddress(java.lang.String address) {
        HelloWorldIndraImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.caib.portafib.ws.v1.HelloWorldIndra.class.isAssignableFrom(serviceEndpointInterface)) {
                es.caib.portafib.ws.v1.HelloWorldIndraImplServiceSoapBindingStub _stub = new es.caib.portafib.ws.v1.HelloWorldIndraImplServiceSoapBindingStub(new java.net.URL(HelloWorldIndraImplPort_address), this);
                _stub.setPortName(getHelloWorldIndraImplPortWSDDServiceName());
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
        if ("HelloWorldIndraImplPort".equals(inputPortName)) {
            return getHelloWorldIndraImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://v1.ws.portafib.caib.es/", "HelloWorldIndraImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://v1.ws.portafib.caib.es/", "HelloWorldIndraImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HelloWorldIndraImplPort".equals(portName)) {
            setHelloWorldIndraImplPortEndpointAddress(address);
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
