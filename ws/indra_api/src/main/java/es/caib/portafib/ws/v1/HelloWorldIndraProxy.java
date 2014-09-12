package es.caib.portafib.ws.v1;

public class HelloWorldIndraProxy implements es.caib.portafib.ws.v1.HelloWorldIndra {
  private String _endpoint = null;
  private es.caib.portafib.ws.v1.HelloWorldIndra helloWorldIndra = null;
  
  public HelloWorldIndraProxy() {
    _initHelloWorldIndraProxy();
  }
  
  public HelloWorldIndraProxy(String endpoint) {
    _endpoint = endpoint;
    _initHelloWorldIndraProxy();
  }
  
  private void _initHelloWorldIndraProxy() {
    try {
      helloWorldIndra = (new es.caib.portafib.ws.v1.HelloWorldIndraImplServiceLocator()).getHelloWorldIndraImplPort();
      if (helloWorldIndra != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)helloWorldIndra)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)helloWorldIndra)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (helloWorldIndra != null)
      ((javax.xml.rpc.Stub)helloWorldIndra)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public es.caib.portafib.ws.v1.HelloWorldIndra getHelloWorldIndra() {
    if (helloWorldIndra == null)
      _initHelloWorldIndraProxy();
    return helloWorldIndra;
  }
  
  public java.lang.String echo(java.lang.String arg0) throws java.rmi.RemoteException{
    if (helloWorldIndra == null)
      _initHelloWorldIndraProxy();
    return helloWorldIndra.echo(arg0);
  }
  
  
}