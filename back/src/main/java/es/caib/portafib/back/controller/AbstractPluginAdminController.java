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
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.validation.BindingResult;
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
       PluginJPA p = pluginForm.getPlugin(); 
       p.setActiu(true);
       p.setTipus(getTipusDePlugin());
       // XYZ ZZZ #160
       p.setPoliticaMostrarPropietats(Constants.PLUGIN_POLITICA_MOSTRAR_PROPIETATS_EDIT_ENTITAT_MOSTRAR_ADMIN);
     }
     
     if (!isAdmin()) {
       pluginForm.addHiddenField(ENTITATID);
     }
     
     pluginForm.addHiddenField(TIPUS);
     
     // XYZ ZZZ pendent implementacio noves dades plugin #160
     pluginForm.addHiddenField(POLITICADEUS);
     pluginForm.addReadOnlyField(POLITICAMOSTRARPROPIETATS);
     pluginForm.addReadOnlyField(CODI);
     pluginForm.addReadOnlyField(ORDRE);
   
     return pluginForm;
   }

  @Override
  public void preValidate(HttpServletRequest request,PluginForm pluginForm , BindingResult result)  throws I18NException {
  
    // XYZ ZZZ #160
    if (pluginForm.getPlugin().getEntitatID() == null) {
      pluginForm.getPlugin().setPoliticaDeUs(Constants.PLUGIN_POLITICA_DE_US_PLANTILLA);  
    } else {
      pluginForm.getPlugin().setPoliticaDeUs(Constants.PLUGIN_POLITICA_DE_US_NOMES_ENTITAT);
    }
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
  
  
 
  
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaMostrarPropietats(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    for (int i = 0; i < Constants.PLUGIN_POLITICA_MOSTRAR_PROPIETATS.length; i++) {
      int val = Constants.PLUGIN_POLITICA_MOSTRAR_PROPIETATS[i];
      __tmp.add(new StringKeyValue(String.valueOf(val), I18NUtils
          .tradueix("plugin.politicamostrarpropietats." + val)));

    }

    return __tmp;
  }

  @Override
  public List<StringKeyValue> getReferenceListForPoliticaDeUs(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {

    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    for (int i = 0; i < Constants.PLUGIN_POLITICA_DE_US.length; i++) {
      int val = Constants.PLUGIN_POLITICA_DE_US[i];
      __tmp.add(new StringKeyValue(String.valueOf(val), I18NUtils
          .tradueix("plugin.politicadeus." + val)));
    }

    return __tmp;
  }

  
}
