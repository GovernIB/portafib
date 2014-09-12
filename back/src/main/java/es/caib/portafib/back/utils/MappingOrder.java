package es.caib.portafib.back.utils;

import java.util.HashMap;
import java.util.Map;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;

// TODO Moure a GenApp
/**
 * 
 * @author anadal
 *
 */
public class MappingOrder {

  
  
  public Map<String, String> mappingIndirect = new HashMap<String, String>();
  
  public Map<String, String> mappingDirect = new HashMap<String, String>();
  
  public void addMapping(Field<?> from, Field<?> to) {
    mappingDirect.put(from.javaName, to.fullName);
    mappingIndirect.put(to.fullName,from.javaName);
  }
  
  public void processDirectOrderMapping(BaseFilterForm filterForm) {
    String orderBy = filterForm.getOrderBy();
    if (orderBy != null) {
      String to = mappingDirect.get(orderBy);
      if (to != null) {
        filterForm.setOrderBy(to);
      }
    }
  }
  
  public void processIndirectOrderMapping(BaseFilterForm filterForm) {
    String orderBy = filterForm.getOrderBy();    
    if (orderBy != null) {
      String from = mappingIndirect.get(orderBy);
      if (from != null) {
        filterForm.setOrderBy(from);
      }
    }
  }
}
