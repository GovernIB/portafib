
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal;
import es.caib.portafib.ejb.TraduccioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariAplicacioConfiguracioRefList extends RefListBase
    implements UsuariAplicacioConfiguracioFields {

  @EJB(mappedName = UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  private UsuariAplicacioConfiguracioLocal usuariAplicacioConfiguracioEjb;

  @EJB(mappedName = TraduccioLocal.JNDI_NAME)
  private TraduccioLocal traduccioEjb;
  public UsuariAplicacioConfiguracioRefList(UsuariAplicacioConfiguracioRefList __clone) {
    super(__clone);
    this.usuariAplicacioConfiguracioEjb = __clone.usuariAplicacioConfiguracioEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public UsuariAplicacioConfiguracioRefList() {
    setSelects(new Select<?>[] { NOM.select });
    addCampTraduible(MOTIUDELEGACIOID.select);
    addCampTraduible(FIRMATPERFORMATID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = usuariAplicacioConfiguracioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => usuariAplicacioConfiguracioEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.portafib.model.fields.TraduccioFields.TRADUCCIOID.in(usuariAplicacioConfiguracioEjb.executeQuery(_transSelect, where));
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
