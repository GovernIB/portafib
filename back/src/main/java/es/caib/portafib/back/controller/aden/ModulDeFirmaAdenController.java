package es.caib.portafib.back.controller.aden;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.admin.ModulDeFirmaAdminController;
import es.caib.portafib.back.form.webdb.ModulDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.ModulDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.ModulDeFirmaJPA;
import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.logic.ModulDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.ModulDeFirma;
import es.caib.portafib.model.fields.ModulDeFirmaFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/modulDeFirma")
@SessionAttributes(types = { ModulDeFirmaForm.class, ModulDeFirmaFilterForm.class })
public class ModulDeFirmaAdenController extends ModulDeFirmaAdminController {
  
  @EJB(mappedName = ModulDeFirmaLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaLogicaLocal modulDeFirmaEjb;
  
  
  @Override
  public String getTileForm() {
    return "modulDeFirmaFormAden";
  }

  @Override
  public String getTileList() {
    return "modulDeFirmaListAden";
  }


  @Override
  public String getSessionAttributeFilterForm() {
    return "ModulDeFirmaAden_FilterForm";
  }
  
  @Override
  public String getEntityNameCode() {
    return "modulDeFirma.modulDeFirma";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "modulDeFirma.modulDeFirma.plural";
  }
  
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ModulDeFirmaFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
  }

  @Override
  public ModulDeFirmaFilterForm getModulDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      ModulDeFirmaFilterForm modulDeFirmaFilterForm;
      modulDeFirmaFilterForm = super.getModulDeFirmaFilterForm(pagina, mav, request);
      if(modulDeFirmaFilterForm.isNou()) {
        
         Field<?>[] fields = ALL_MODULDEFIRMA_FIELDS;
         
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
             "icon-plus-sign", "moduldefirma.crear" ,  "javascript:openSelectModulDeFirmaDialog();", ""));

      }
      

      
      Where where = Where.AND(
          ACTIU.equal(true),
          ENTITATID.isNull()
      );
      
//      ModulDeFirmaJPA modul;
//      modul.getModulDeFirmaID();
//      modul.getNom().getTraduccions()
      
      List<ModulDeFirma> plantilles =  modulDeFirmaEjb.select(where);
      
      log.info("XYZ XXXXXXXXXXXXXXXXXXXX  PLANTILLES SIZE = " + plantilles.size());
      
      
      // TODO Controlar que no hi hagi cap modul de firma.
      request.getSession().setAttribute("llistatDePlantillaDeModuls",plantilles);
      
      
      
      return modulDeFirmaFilterForm;
  }
  
  
  @Override
  public ModulDeFirmaForm getModulDeFirmaForm(ModulDeFirmaJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
     ModulDeFirmaForm modulDeFirmaForm = super.getModulDeFirmaForm(_jpa, __isView, request, mav);
     if(modulDeFirmaForm.isNou()) {
       
       Long plantillamoduldefirmaid = (Long)request.getSession().getAttribute(PLANTILLAID);
       if (plantillamoduldefirmaid == null) {
         
         // TODO XYZ tradueix
         HtmlUtils.saveMessageError(request, "Abans ha de seleccionar una Plantilla de Mòdul de Firma");
                  
         mav.setView(new RedirectView(getContextWeb() + "/list/1" , true));
         return modulDeFirmaForm;
       }
       request.getSession().removeAttribute(PLANTILLAID);
       
       // TODO chack null
       ModulDeFirmaJPA moduldefirma = modulDeFirmaEjb.findByPrimaryKey(plantillamoduldefirmaid);
       
       
       log.info(" XYZ  getNOM MMMMMMMMMMMMMMMMMM =  " + modulDeFirmaForm.getModulDeFirma().getNom());
       
       
       ModulDeFirmaJPA clone = moduldefirma.toJPA(moduldefirma);
       clone.setModulDeFirmaID(0);
       
       clone.setNomID(0);
       clone.setNom(copyTranslations(moduldefirma.getNom()));

       clone.setDescripcioCurtaID(0);
       clone.setDescripcioCurta(copyTranslations(moduldefirma.getDescripcioCurta()));
       
       clone.setEntitatID(LoginInfo.getInstance().getEntitatID());

       modulDeFirmaForm.setModulDeFirma(clone);
     }

     modulDeFirmaForm.addReadOnlyField(PROPERTIESADMIN);
     modulDeFirmaForm.addReadOnlyField(CLASSE);

     return modulDeFirmaForm;
   }

  private TraduccioJPA copyTranslations(TraduccioJPA orig) {
    
    TraduccioJPA desti = new TraduccioJPA();
    
    
    
    Map<String, TraduccioMapJPA> tradOrig = orig.getTraduccions();
    
    //Map<String, TraduccioMapJPA> tradNew = new HashMap<String, TraduccioMapJPA>();
    
    Map<String, TraduccioMapJPA> tradNew = desti.getTraduccions();
    
    System.out.println(" XYZ copyTranslations (" + tradOrig.keySet().size() + ")================= ");
            
     for (String lang : tradOrig.keySet()) {
       
       System.out.println(" KEY == " + lang + " ==> " + tradOrig.get(lang).getValor());
       
       TraduccioMapJPA newTMJ = new TraduccioMapJPA(tradOrig.get(lang).getValor());
       
       
       tradNew.put(lang, newTMJ);
     }
     
     return desti;
  }

  
  public static final String PLANTILLAID = "plantilla_modul_de_firma_id_aden";
  
  
  @RequestMapping(value = "/selectplantilla/{plantillamoduldefirmaid}", method = RequestMethod.GET)
  public String seleccionarPlantillaPost(HttpServletRequest request, 
      @PathVariable Long plantillamoduldefirmaid) throws I18NException {
    
    /*
    Long plantillamoduldefirmaidStr = request.getParameter("plantillamoduldefirmaid");
    
    if (plantillamoduldefirmaidStr == null || plantillamoduldefirmaidStr.trim().length() == 0) {
       return "redirect:" + getContextWeb() + "/list/1";
    }
    */

    
    try {
      
      
      request.getSession().setAttribute(PLANTILLAID, plantillamoduldefirmaid);
      
      // new ModelAndView(new RedirectView(getContextWeb() + "/list/1", true));
      return "redirect:" + getContextWeb() + "/new";

    } catch(Exception e) {
      return "redirect:" + getContextWeb() + "/list/1";
    }
  }
    
  
  
}
