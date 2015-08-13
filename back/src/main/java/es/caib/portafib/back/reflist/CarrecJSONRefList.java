package es.caib.portafib.back.reflist;

import java.util.List;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.controller.RefListBase;
import org.springframework.stereotype.Component;

import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;

/**
 * 
 * @author anadal
 *
 */
@Component
public class CarrecJSONRefList extends RefListBase {

  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
  private UsuariEntitatLocal usuariEntitatEjb;

  
  /**
   * 
   */
  public CarrecJSONRefList() {
    super();
    init();
  }


  
   protected void init() {

     final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
     this.setSelects(new Select<?>[] {
         new SelectConstant("(*) "),
         UsuariEntitatFields.CARREC.select,  new SelectConstant(" ("),
         personaQueryPath.NOM().select, new SelectConstant(" "),
         personaQueryPath.LLINATGES().select , new SelectConstant(" - "), 
         personaQueryPath.NIF().select, new SelectConstant(" - "),
         personaQueryPath.USUARIPERSONAID().select,new SelectConstant(")") });
     
     this.setSeparator("");
   }
  
  
   public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
     Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
     List<StringKeyValue> list = usuariEntitatEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
     return list;
   }
  
  
}
