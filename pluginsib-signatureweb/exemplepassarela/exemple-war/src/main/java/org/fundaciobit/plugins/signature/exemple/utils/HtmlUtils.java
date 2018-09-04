package org.fundaciobit.plugins.signature.exemple.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal
 * 
 */
public class HtmlUtils {
  
  public static final String MISSATGES = "missatges";
  
  public static final String ERROR = "error";
  
  public static final String WARN = "warn";
  
  public static final String SUCCESS = "success";
  
  public static final String INFO = "info";

  public static void saveMessageInfo(HttpServletRequest request, String missatge) {
    addMessage(request, INFO , missatge);
  }
  

  public static void saveMessageWarning(HttpServletRequest request, String missatge) {
    addMessage(request, WARN , missatge);

  }

  public static void saveMessageSuccess(HttpServletRequest request, String missatge) {
    addMessage(request, SUCCESS , missatge);
  }

  public static void saveMessageError(HttpServletRequest request, String missatge) {    
    addMessage(request, ERROR , missatge);
  }
  

  private static void addMessage(HttpServletRequest request, String type, String missatge) {
    HttpSession session = request.getSession();
    
    Map<String, List<String>> missatges = (Map<String, List<String>>)session.getAttribute(MISSATGES);
    
    if (missatges == null) {
      missatges = new HashMap<String, List<String>>();
      session.setAttribute(MISSATGES, missatges);
    }
    
    List<String> missatgesTipus = missatges.get(type);
    
    if (missatgesTipus == null) {
      missatgesTipus = new ArrayList<String>();
      missatges.put(type, missatgesTipus);
    }

    missatgesTipus.add(missatge);

  }

}
