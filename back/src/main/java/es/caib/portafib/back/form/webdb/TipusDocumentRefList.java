
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.TipusDocumentLocal;
import es.caib.portafib.ejb.TraduccioLocal;
import es.caib.portafib.jpa.TraduccioMapJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.TipusDocumentFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;
import org.jfree.util.Log;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentRefList extends RefListBase
    implements TipusDocumentFields {
  
  protected final Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = TipusDocumentLocal.JNDI_NAME)
  private TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = TraduccioLocal.JNDI_NAME)
  private TraduccioLocal traduccioEjb;
  public TipusDocumentRefList(TipusDocumentRefList __clone) {
    super(__clone);
    this.tipusDocumentEjb = __clone.tipusDocumentEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public TipusDocumentRefList() {
    setSelects(new Select<?>[] { NOMID.select });
    addCampTraduible(NOMID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = tipusDocumentEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => tipusDocumentEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.portafib.model.fields.TraduccioFields.TRADUCCIOID.in(tipusDocumentEjb.executeQuery(_transSelect, where));
    List<es.caib.portafib.model.entity.Traduccio> traduccions = traduccioEjb.select(_w1);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (es.caib.portafib.model.entity.Traduccio traduccio : traduccions) {
      es.caib.portafib.jpa.TraduccioJPA traduccioJPA = (es.caib.portafib.jpa.TraduccioJPA) traduccio;
      String key = keysMap.get(String.valueOf(traduccioJPA.getTraduccioID()));

      // XYZ ZZZ ZZZ
      TraduccioMapJPA tra = traduccioJPA.getTraduccio(_lang);
      String value;      
      if (tra == null) {
        value = "No es troba la traducció a [" + _lang + "] pel codi de traducció {" + traduccioJPA.getTraduccioID() + "}";
        log.error(value);
        value ="UNKNOWN TRANSLATION[" +  traduccioJPA.getTraduccioID() + "][" + _lang+ "]";
      } else {
        value = tra.getValor();
      }
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);

    }
    java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    return _list;

  }
}
