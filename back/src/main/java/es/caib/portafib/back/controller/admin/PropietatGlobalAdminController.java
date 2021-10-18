package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PropietatGlobalController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.PropietatGlobalJPA;
import es.caib.portafib.logic.utils.PropietatsConstants;
import es.caib.portafib.logic.utils.PropietatsConstants.Propietat;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.model.fields.PropietatGlobalFields;
import es.caib.portafib.utils.ConstantsV2;
import javax.ejb.EJB;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdminController extends PropietatGlobalController {
    
  @EJB(mappedName = es.caib.portafib.logic.PropietatGlobalLogicaLocal.JNDI_NAME)
  protected es.caib.portafib.logic.PropietatGlobalLogicaLocal propietatGlobalLogicaEjb;

  public static final int COLUMN_ESTAT_PROPIETAT = 1;

  @Override
  public String getTileForm() {
    return "propietatGlobalFormAdmin";
  }

  @Override
  public String getTileList() {
    return "propietatGlobalListAdmin";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PropietatGlobalAdmin_FilterForm";
  }

  protected int getTipusPropietat() {
    return PropietatsConstants.TIPUS_PROPIETAT_GLOBAL;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    switch (getTipusPropietat()) {
      case PropietatsConstants.TIPUS_PROPIETAT_GLOBAL:
        return ENTITATID.isNull();

      case PropietatsConstants.TIPUS_PROPIETAT_ENTITAT:
        return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());

      case PropietatsConstants.TIPUS_PROPIETAT_SISTEMA:
      default:
        return null;
    }
  }

  @Override
  public List<PropietatGlobal> executeSelect(
      ITableManager<PropietatGlobal, java.lang.Long> ejb, Where where,
      final OrderBy[] orderBy, final Integer itemsPerPage, final int inici)
      throws I18NException {

    if (getTipusPropietat() == PropietatsConstants.TIPUS_PROPIETAT_SISTEMA) {

      Properties all = System.getProperties();

      List<PropietatGlobal> list = new ArrayList<PropietatGlobal>();
      int count = 0;

      for (Object keyObj : all.keySet()) {
        String key = (String) keyObj;
        if (key.startsWith(ConstantsV2.PORTAFIB_PROPERTY_BASE)) {
          list.add(new PropietatGlobalJPA(count++, key, (String) all.get(key), null, null));
          count++;
        }
      }

      Collections.sort(list, new Comparator<PropietatGlobal>() {

        @Override
        public int compare(PropietatGlobal o1, PropietatGlobal o2) {
          return o1.getClau().compareTo(o2.getClau());
        }
      });

      return list;

    } else {
      return super.executeSelect(ejb, where, orderBy, itemsPerPage, inici);
    }

  }

  @Override
  public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    PropietatGlobalFilterForm propietatGlobalFilterForm;
    propietatGlobalFilterForm = super.getPropietatGlobalFilterForm(pagina, mav, request);
    if (propietatGlobalFilterForm.isNou()) {
      propietatGlobalFilterForm.addHiddenField(ENTITATID);
      propietatGlobalFilterForm.addHiddenField(DESCRIPCIO);
      propietatGlobalFilterForm.addHiddenField(PROPIETATGLOBALID);
      propietatGlobalFilterForm.setGroupByFields(new ArrayList<Field<?>>());

      switch (getTipusPropietat()) {
        case PropietatsConstants.TIPUS_PROPIETAT_GLOBAL:
        break;

        case PropietatsConstants.TIPUS_PROPIETAT_ENTITAT:
        break;

        case PropietatsConstants.TIPUS_PROPIETAT_SISTEMA:
        default:
          propietatGlobalFilterForm.setDeleteButtonVisible(false);
          propietatGlobalFilterForm.setAddButtonVisible(false);
          propietatGlobalFilterForm.setVisibleMultipleSelection(false);
          propietatGlobalFilterForm.setEditButtonVisible(false);
          propietatGlobalFilterForm.setItemsPerPage(-1);

          propietatGlobalFilterForm.setFilterByFields(new ArrayList<Field<?>>());

      }

    }

    return propietatGlobalFilterForm;
  }

  @Override
  public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView,
        request, mav);
    if (propietatGlobalForm.isNou()) {
      propietatGlobalForm.getPropietatGlobal().setClau(ConstantsV2.PORTAFIB_PROPERTY_BASE);
      switch (getTipusPropietat()) {
        case PropietatsConstants.TIPUS_PROPIETAT_GLOBAL:
        break;

        case PropietatsConstants.TIPUS_PROPIETAT_ENTITAT:
          propietatGlobalForm.getPropietatGlobal().setEntitatID(
              LoginInfo.getInstance().getEntitatID());
        break;

        case PropietatsConstants.TIPUS_PROPIETAT_SISTEMA:
        default:
          throw new I18NException("genapp.comodi",
              "No podem crear Propietats de Tipus Sistema");

      }

    }

    propietatGlobalForm.addHiddenField(ENTITATID);

    return propietatGlobalForm;
  }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav,
            PropietatGlobalFilterForm filterForm, List<PropietatGlobal> list) throws I18NException {

        filterForm.getAdditionalFields().remove(COLUMN_ESTAT_PROPIETAT);

        HashMap<Long, String> map = new HashMap<Long, String>();

        Map<String, Propietat> propietats;
        boolean isSistema = false;
        switch (getTipusPropietat()) {
            case PropietatsConstants.TIPUS_PROPIETAT_GLOBAL:
                propietats = PropietatsConstants.propietatsGlobals;
                break;

            case PropietatsConstants.TIPUS_PROPIETAT_ENTITAT:
                propietats = PropietatsConstants.propietatsEntitat;
                break;

            case PropietatsConstants.TIPUS_PROPIETAT_SISTEMA:
            default:
                isSistema = true;
                propietats = PropietatsConstants.propietatsSistema;

        }

        if (log.isDebugEnabled()) {
            log.debug("propietats SIZE => " + propietats.size());
        }

        for (PropietatGlobal pg : list) {

            String clau = pg.getClau();

            Propietat prop = propietats.get(clau);
            if (prop == null) {

                if (isSistema
                        && (clau.startsWith("es.caib.portafib.hibernate.")
                        || clau.startsWith("es.caib.portafib.plugins.certificate")
                        || clau.startsWith("es.caib.portafib.plugins.userinformation")
                        || clau.startsWith("es.caib.portafib.hibernate"))) {
                    continue;
                }

                map.put(
                        pg.getPropietatGlobalID(),
                        "<b><a href=\"#\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Propietat Desconeguda\">????</a></b>");
            } else {

                if (!prop.activa) {

                    // &#9745; OK Millor &#10004;
                    // &#9746; ERR Millor &#9888;
                    // Unknown
                    map.put(
                            pg.getPropietatGlobalID(),
                            "<b><a href=\"#\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Propietat Obsoleta\">&#9888;</a></b>");

                }
            }

        }

        if (map.size() != 0) {
            AdditionalField<Long, String> addfieldMOTIU = new AdditionalField<Long, String>();
            addfieldMOTIU.setCodeName("=<b>&#9888;</b>");
            addfieldMOTIU.setPosition(COLUMN_ESTAT_PROPIETAT);
            // No omplirem els valors
            addfieldMOTIU.setValueMap(map);
            addfieldMOTIU.setEscapeXml(false);

            filterForm.addAdditionalField(addfieldMOTIU);

        }

    }

    @Override
    public void postValidate(HttpServletRequest request, PropietatGlobalForm propietatGlobalForm, BindingResult result) throws I18NException {

        //super.postValidate(request, propietatGlobalForm, result); //To change body of generated methods, choose Tools | Templates.
        if (result.hasErrors()) {

            FieldError fe = result.getFieldError(PropietatGlobalFields.PROPIETATGLOBALID.fullName);

            if (fe != null) {

                java.lang.reflect.Field f;
                try {
                    f = getField(result.getClass(), "errors", true);

                    f.setAccessible(true);
                    List<?> errors22 = (List<?>) f.get(result);
                    errors22.remove(fe);

                } catch (Throwable e) {
                    // TODO
                    e.printStackTrace();
                }

            }

        }
        
        Long idPropietat = propietatGlobalForm.getPropietatGlobal().getPropietatGlobalID();
        String clau = propietatGlobalForm.getPropietatGlobal().getClau();
        String entitat = propietatGlobalForm.getPropietatGlobal().getEntitatID();
        
        List<Long> ids = propietatGlobalLogicaEjb.getIdsProperty(entitat, clau);
        
        for (Long id:ids){
            if ((idPropietat!=null) && !idPropietat.equals(id)){
                 result.rejectValue(get(CLAU), "=Valor repetit",
                    new Object[] { I18NUtils.tradueix(CLAU.fullName) },
                    "Clau repetida amb codi " + id);
            }
        }
        

    }

  
   /**
   * Gets an accessible {@link Field} by name, breaking scope if requested.
   * Superclasses/interfaces will be considered.
   * 
   * @param cls
   *          the {@link Class} to reflect, must not be {@code null}
   * @param fieldName
   *          the field name to obtain
   * @param forceAccess
   *          whether to break scope restrictions using the
   *          {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method.
   *          {@code false} will only match {@code public} fields.
   * @return the Field object
   * @throws IllegalArgumentException
   *           if the class is {@code null}, or the field name is blank or empty or is matched
   *           at multiple places in the inheritance hierarchy
   */
  public static java.lang.reflect.Field getField(final Class<?> cls, final String fieldName,
      final boolean forceAccess) {

    // check up the superclass hierarchy
    for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
      try {
        final java.lang.reflect.Field field = acls.getDeclaredField(fieldName);
        // getDeclaredField checks for non-public scopes as well
        // and it returns accurate results
        if (!java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
          if (forceAccess) {
            field.setAccessible(true);
          } else {
            continue;
          }
        }
        return field;
      } catch (final NoSuchFieldException ex) { // NOPMD
        // ignore
      }
    }

    return null;

  }
  
  
  
  

}
