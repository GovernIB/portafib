package es.caib.portafib.back.form.dest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;

/**
 * 
 * @author anadal
 *
 */
public class ColaboracioDelegacioDestForm extends ColaboracioDelegacioForm {
  
  
  public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
      public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    Map<K, V> result = new LinkedHashMap<K, V>();
    for (Map.Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }
  
  Map<Long, String> allTipusDocumentInfo = new HashMap<Long, String>();  
  
  List<Long> currentTipusDocument = new ArrayList<Long>();
  
  List<Long> availableTipusDocument = new ArrayList<Long>();

  /**
   * TIPUS = 1  => Accepta tots els tipus de document 
   * TIPUS = 2  => Accepta un conjunt de tipus de document 
   */
  int tipus = 2;
  
  boolean tipusReadOnly = false;

  String url_user = null;
  
  public ColaboracioDelegacioDestForm() {
  }
  
  
  public ColaboracioDelegacioDestForm(ColaboracioDelegacioForm __toClone) {
    super(__toClone);
  }

  public int getTipus() {
    return tipus;
  }


  public void setTipus(int tipus) {
    this.tipus = tipus;
  }

  public Map<Long, String> getAllTipusDocumentInfo() {
    return allTipusDocumentInfo;
  }


  public void setAllTipusDocumentInfo(Map<Long, String> allTipusDocumentInfo) {
    this.allTipusDocumentInfo = sortByValue(allTipusDocumentInfo);
  }


  public List<Long> getCurrentTipusDocument() {
    return currentTipusDocument;
  }


  public void setCurrentTipusDocument(List<Long> currentTipusDocument) {
    this.currentTipusDocument = currentTipusDocument;
  }


  public boolean isTipusReadOnly() {
    return tipusReadOnly;
  }


  public void setTipusReadOnly(boolean tipusReadOnly) {
    this.tipusReadOnly = tipusReadOnly;
  }


  public List<Long> getAvailableTipusDocument() {
    return availableTipusDocument;
  }


  public void setAvailableTipusDocument(List<Long> availableTipusDocument) {
    this.availableTipusDocument = availableTipusDocument;
  }

  public String getUrl_user() {
    return url_user;
  }

  public void setUrl_user(String url_user) {
    this.url_user = url_user;
  }
}
