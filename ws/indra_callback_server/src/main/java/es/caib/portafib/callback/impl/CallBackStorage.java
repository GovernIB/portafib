package es.caib.portafib.callback.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest;

/**
 * Guarda en una variable estatica totes les peticions de callback rebudes,
 *  per després poder ser mostrades a traves de index.jsp
 * @author anadal
 *
 */
public class CallBackStorage {

  public static class CallBackInfo {
    final CallbackRequest callBackRequest;
    
    final Date data;

    /**
     * @param callbbackrequest
     */
    public CallBackInfo(CallbackRequest callBackRequest) {
      super();
      this.callBackRequest = callBackRequest;
      this.data = new Date();
    }

    

    public Date getData() {
      return data;
    }



    public CallbackRequest getCallBackRequest() {
      return callBackRequest;
    }
    
    
    
  }
  
  public static Set<CallBackInfo> llistat = new TreeSet<CallBackInfo>(new Comparator<CallBackInfo>() {

    public int compare(CallBackInfo o1, CallBackInfo o2) {
      
      return -o1.getData().compareTo(o2.getData());
    }
  });
  
  
  public static void addCallback(CallBackInfo info) {
    llistat.add(info);
  }
  
  
  
  public static Set<CallBackInfo> getAllCallbacks() {
    return llistat;    
  }
  
  
  
  
}
