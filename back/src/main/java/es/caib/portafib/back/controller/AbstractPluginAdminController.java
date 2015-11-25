package es.caib.portafib.back.controller;

import java.util.Arrays;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

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
      PluginFilterForm modulDeFirmaFilterForm;
      modulDeFirmaFilterForm = super.getPluginFilterForm(pagina, mav, request);
      if(modulDeFirmaFilterForm.isNou()) {
        
         Field<?>[] fields = ALL_PLUGIN_FIELDS;
         
         HashSet<Field<?>>  campsOcults = new HashSet<Field<?>>(Arrays.asList(fields));
        
         campsOcults.remove(NOMID);         
         campsOcults.remove(ACTIU);
         
         if (isAdmin()) {
           campsOcults.remove(ENTITATID);
         }
         
         modulDeFirmaFilterForm.getHiddenFields().addAll(campsOcults);
      }
      return modulDeFirmaFilterForm;
  }
  
  
  @Override
  public PluginForm getPluginForm(PluginJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     PluginForm modulDeFirmaForm = super.getPluginForm(_jpa, __isView, request, mav);
     if(modulDeFirmaForm.isNou()) {
       modulDeFirmaForm.getPlugin().setActiu(true);
       modulDeFirmaForm.getPlugin().setTipus(getTipusDePlugin());
     }
     
     if (!isAdmin()) {
       modulDeFirmaForm.addHiddenField(ENTITATID);
     }
     
     modulDeFirmaForm.addHiddenField(TIPUS);
   
     return modulDeFirmaForm;
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

  
}
