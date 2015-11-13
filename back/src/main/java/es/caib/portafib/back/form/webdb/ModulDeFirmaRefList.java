
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.ModulDeFirmaLocal;
import es.caib.portafib.ejb.TraduccioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.ModulDeFirmaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModulDeFirmaRefList extends RefListBase
    implements ModulDeFirmaFields {

  @EJB(mappedName = ModulDeFirmaLocal.JNDI_NAME)
  private ModulDeFirmaLocal modulDeFirmaEjb;

  @EJB(mappedName = TraduccioLocal.JNDI_NAME)
  private TraduccioLocal traduccioEjb;
  public ModulDeFirmaRefList(ModulDeFirmaRefList __clone) {
    super(__clone);
    this.modulDeFirmaEjb = __clone.modulDeFirmaEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public ModulDeFirmaRefList() {
    setSelects(new Select<?>[] { NOMID.select });
    addCampTraduible(NOMID.select);
    addCampTraduible(DESCRIPCIOCURTAID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = modulDeFirmaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => modulDeFirmaEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.portafib.model.fields.TraduccioFields.TRADUCCIOID.in(modulDeFirmaEjb.executeQuery(_transSelect, where));
    List<es.caib.portafib.model.entity.Traduccio> traduccions = traduccioEjb.select(_w1);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (es.caib.portafib.model.entity.Traduccio traduccio : traduccions) {
      es.caib.portafib.jpa.TraduccioJPA traduccioJPA = (es.caib.portafib.jpa.TraduccioJPA) traduccio;
      String key = keysMap.get(String.valueOf(traduccioJPA.getTraduccioID()));
      String value = traduccioJPA.getTraduccio(_lang).getValor();
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);
    }
    java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    return _list;

  }
}
