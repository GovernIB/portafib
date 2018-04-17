package es.caib.portafib.back.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.form.webdb.PluginFilterForm;
import es.caib.portafib.back.form.webdb.PluginForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.model.entity.Plugin;


/**
 * 
 * @author anadal
 *
 */

public abstract class AbstractPluginAdenController<I> extends AbstractPluginAdminController {
  
  public abstract AbstractPluginLogicaLocal<I> getPluginEjb();
  
  public abstract String getCrearTranslationCode();
  
  public abstract String getTitolModalCode();
  
  @Override
  public final boolean isAdmin() {
    return false;
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
         //campsOcults.remove(CLASSE);
         campsOcults.remove(ACTIU);

         modulDeFirmaFilterForm.getHiddenFields().addAll(campsOcults);

         modulDeFirmaFilterForm.setAttachedAdditionalJspCode(true);

         // Ocultar boto de crear
         modulDeFirmaFilterForm.setAddButtonVisible(false);


         // Afegir boto addiconal per se
         modulDeFirmaFilterForm.addAdditionalButton(new AdditionalButton(
             "icon-plus-sign", getCrearTranslationCode() ,
             "javascript:openSelectModulDeFirmaDialog();", ""));

      }
      

      
      Where where = Where.AND(
          TIPUS.equal(getTipusDePlugin()),
          ACTIU.equal(true),
          ENTITATID.isNull()
      );
      

      List<Plugin> plantilles =  getPluginEjb().select(where);

      // TODO Controlar que no hi hagi cap modul de firma.
      request.getSession().setAttribute("llistatDePlantillaDeModuls",plantilles);
      
      mav.addObject("titolmodal", getTitolModalCode());
      
      return modulDeFirmaFilterForm;
  }
  
  
  @Override
  public PluginForm getPluginForm(PluginJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
     if(pluginForm.isNou()) {
       
       Long plantillamoduldefirmaid = (Long)request.getSession().getAttribute(PLANTILLAID);
       if (plantillamoduldefirmaid == null) {

         HtmlUtils.saveMessageError(request, "plugin.error.requereixplantilla");
                  
         mav.setView(new RedirectView(getContextWeb() + "/list/1" , true));
         return pluginForm;
       }
       request.getSession().removeAttribute(PLANTILLAID);
       
       // TODO check null
       PluginJPA moduldefirma = getPluginEjb().findByPrimaryKey(plantillamoduldefirmaid);
       
       
       PluginJPA clone = PluginJPA.toJPA(moduldefirma);
       clone.setPluginID(0);
       
       clone.setNomID(0);
       clone.setNom(copyTranslations(moduldefirma.getNom()));

       clone.setDescripcioCurtaID(0);
       clone.setDescripcioCurta(copyTranslations(moduldefirma.getDescripcioCurta()));
       
       clone.setEntitatID(LoginInfo.getInstance().getEntitatID());

       pluginForm.setPlugin(clone);
     }

     pluginForm.addReadOnlyField(PROPERTIESADMIN);
     pluginForm.addReadOnlyField(CLASSE);

     return pluginForm;
   }

  private TraduccioJPA copyTranslations(TraduccioJPA orig) {

    TraduccioJPA desti = new TraduccioJPA();

    Map<String, TraduccioMapJPA> tradOrig = orig.getTraduccions();
    Map<String, TraduccioMapJPA> tradNew = desti.getTraduccions();

    for (String lang : tradOrig.keySet()) {
      TraduccioMapJPA newTMJ = new TraduccioMapJPA(tradOrig.get(lang).getValor());
      tradNew.put(lang, newTMJ);
    }

    return desti;
  }

  
  public static final String PLANTILLAID = "plantilla_de_plugin_id";
  
  
  @RequestMapping(value = "/selectplantilla/{plantillamoduldefirmaid}", method = RequestMethod.GET)
  public String seleccionarPlantillaPost(HttpServletRequest request, 
      @PathVariable Long plantillamoduldefirmaid) throws I18NException {
    
     
    try {
      request.getSession().setAttribute(PLANTILLAID, plantillamoduldefirmaid);
      return "redirect:" + getContextWeb() + "/new";
    } catch(Exception e) {
      return "redirect:" + getContextWeb() + "/list/1";
    }
    
  }
   
  
}
