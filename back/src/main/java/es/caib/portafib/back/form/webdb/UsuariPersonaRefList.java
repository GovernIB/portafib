
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.UsuariPersonaService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariPersonaRefList extends RefListBase
    implements UsuariPersonaFields {

  @EJB(mappedName = UsuariPersonaService.JNDI_NAME)
  private UsuariPersonaService usuariPersonaEjb;

  public UsuariPersonaRefList(UsuariPersonaRefList __clone) {
    super(__clone);
    this.usuariPersonaEjb = __clone.usuariPersonaEjb;
  }
  public UsuariPersonaRefList() {
    setSelects(new Select<?>[] { NOM.select, LLINATGES.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = usuariPersonaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
