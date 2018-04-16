
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFirmaWebPerUsuariAplicacioRefList extends RefListBase
    implements PluginFirmaWebPerUsuariAplicacioFields {

  @EJB(mappedName = PluginFirmaWebPerUsuariAplicacioLocal.JNDI_NAME)
  private PluginFirmaWebPerUsuariAplicacioLocal pluginFirmaWebPerUsuariAplicacioEjb;

  public PluginFirmaWebPerUsuariAplicacioRefList(PluginFirmaWebPerUsuariAplicacioRefList __clone) {
    super(__clone);
    this.pluginFirmaWebPerUsuariAplicacioEjb = __clone.pluginFirmaWebPerUsuariAplicacioEjb;
  }
  public PluginFirmaWebPerUsuariAplicacioRefList() {
    setSelects(new Select<?>[] { USUARIAPLICACIOID.select, PLUGINFIRMAWEBID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = pluginFirmaWebPerUsuariAplicacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
