/**
 * MCGDwsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.indra.www.portafirmasmcgdws.mcgdws;

public interface MCGDwsService extends javax.xml.rpc.Service {
    public java.lang.String getMCGDWSAddress();

    public es.indra.www.portafirmasmcgdws.mcgdws.MCGDws getMCGDWS() throws javax.xml.rpc.ServiceException;

    public es.indra.www.portafirmasmcgdws.mcgdws.MCGDws getMCGDWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
