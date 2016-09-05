package es.caib.portafib.back.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PluginController;
import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.logic.PluginLogicaLocal;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPluginAdminController extends PluginController {

  @EJB(mappedName = PluginLogicaLocal.JNDI_NAME)
  protected PluginLogicaLocal pluginLogicaEjb;
  
  public abstract int getTipusDePlugin();
  
  public abstract String getCodeName();

  public boolean isAdmin() {
    return true;
  }
  

  @Override
  public String getSessionAttributeFilterForm() {
    return getClass().getName() + "_FilterForm";
  }
  
  @Override
  public final Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where w;
    if (isAdmin()) {
      w = null; // tots els plugins: amb i sense entitat   
    } else {
      w = ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }
    return Where.AND(w, TIPUS.equal(getTipusDePlugin()));
  }


  @Override
  public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      PluginFilterForm pluginFilterForm;
      pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);
      if(pluginFilterForm.isNou()) {
        
         Field<?>[] fields = ALL_PLUGIN_FIELDS;
         
         HashSet<Field<?>>  campsOcults = new HashSet<Field<?>>(Arrays.asList(fields));
        
         campsOcults.remove(NOMID);
         campsOcults.remove(ACTIU);
         
         if (isAdmin()) {
           campsOcults.remove(ENTITATID);
         }
         
         pluginFilterForm.getHiddenFields().addAll(campsOcults);
         
         
         pluginFilterForm.getDefaultGroupByFields().remove(ENTITATID);
         pluginFilterForm.getDefaultGroupByFields().remove(TIPUS);
         
         
         // TODO Ordenar per camp Traduit
         //pluginFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy( new PluginQueryPath().NOM(). )} );
      }
      return pluginFilterForm;
  }
  
  
  @Override
  public PluginForm getPluginForm(PluginJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
     if(pluginForm.isNou()) {
       pluginForm.getPlugin().setActiu(true);
       pluginForm.getPlugin().setTipus(getTipusDePlugin());
     }
     
     if (!isAdmin()) {
       pluginForm.addHiddenField(ENTITATID);
     }
     
     pluginForm.addHiddenField(TIPUS);
   
     return pluginForm;
   }


  @Override
  public String getEntityNameCode() {
    return getCodeName();
  }


  @Override
  public String getEntityNameCodePlural() {
    return getCodeName() + ".plural";
  }


  @Override
  public PluginJPA update(HttpServletRequest request, PluginJPA plugin)
    throws Exception,I18NException, I18NValidationException {
    return (PluginJPA)pluginLogicaEjb.update(plugin);
  }


  @Override
  public void delete(HttpServletRequest request, Plugin plugin) throws Exception,I18NException {
    pluginLogicaEjb.delete(plugin);
  }
  
  @Override
  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
   __tmp.add(new StringKeyValue("" + Constants.TIPUS_PLUGIN_MODULDEFIRMA_WEB , "PLUGIN MODUL DE FIRMA WEB"));
   __tmp.add(new StringKeyValue("" + Constants.TIPUS_PLUGIN_SEGELLDETEMPS , "TIPUS PLUGIN SEGELLDETEMPS"));
   __tmp.add(new StringKeyValue("" + Constants.TIPUS_PLUGIN_CUSTODIA , "TIPUS PLUGIN CUSTODIA"));
   __tmp.add(new StringKeyValue("" + Constants.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR , "PLUGIN MODUL DE FIRMA EN SERVIDOR"));
   return __tmp;
 }

  
}
