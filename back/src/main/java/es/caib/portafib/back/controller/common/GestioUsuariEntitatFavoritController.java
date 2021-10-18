package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.webdb.UsuariEntitatFavoritController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.persistence.UsuariEntitatFavoritJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created 23/05/13 14:37
 * 
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = "/common/usuariEntitatFavorit")
@SessionAttributes(types = {  UsuariEntitatFavoritForm.class,
    UsuariEntitatFavoritFilterForm.class, SeleccioUsuariForm.class })
public class GestioUsuariEntitatFavoritController extends UsuariEntitatFavoritController {
  
  
  public static final String USUARI_ENTITAT_ID_HOLDER = 
      "GestioUsuariEntitatFavoritController_USUARI_ENTITAT_ID_HOLDER";

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @Autowired
  protected SeleccioUsuariValidator seleccioUsuariValidator;
  
//===== GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT


 
 public String getTileSeleccioUsuari() {
   return "seleccioUsuariForm_COMMON";
 }
 

 @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
 public ModelAndView seleccioUsuariGet()
     throws Exception {

     ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

     SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

     seleccioUsuariForm.setTitol("favorit.crear");
     seleccioUsuariForm.setSubtitol("favorit.subtitol");
     seleccioUsuariForm.setCancelUrl(getContextWeb() + "/list");
     seleccioUsuariForm.setUrlData("/common/json/usuarientitat");
     
     seleccioUsuariForm.setUsuarisFavorits(null);

     mav.addObject(seleccioUsuariForm);

     return mav;
 }


 @RequestMapping(value = "/selecciousuari", method = RequestMethod.POST)
 public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm,
     BindingResult result, HttpServletRequest request) throws I18NException {
   
   ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

   seleccioUsuariValidator.validate(seleccioUsuariForm, result);
   if (result.hasErrors()) {
     return mav;
   }

   String usuariEntitaIDFavorit = seleccioUsuariForm.getId();

  
   // Cercar si ja el tenim com favorit
   {
     Where w1 = FAVORITID.equal(usuariEntitaIDFavorit);
     Where w2 = ORIGENID.equal(LoginInfo.getInstance().getUsuariEntitatID());
     if(usuariEntitatFavoritEjb.count(Where.AND(w1,w2))!=0){
      result.rejectValue("id", "favorit.error.existeix");
      return mav;
     }
   }

   //UsuariEntitat ue;
   //ue = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitaIDFavorit)
   
   //StringKeyValue skv = new StringKeyValue(usuariEntitaIDFavorit,
   //  up.getNom() + " " + up.getLlinatges() + " (" + up.getNif() + ")");
   request.getSession().setAttribute(USUARI_ENTITAT_ID_HOLDER, usuariEntitaIDFavorit);   
      
   mav = new ModelAndView(new RedirectView(getContextWeb() + "/new", true));
   
   HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("favorit.verificacio"));

   return mav;

 }
 
 // --------------------------------------------------------
 

  
 
 @Override
 public String getTileForm() {
   return "usuariEntitatFavoritForm";
 }

 @Override
 public String getTileList() {
   return "usuariEntitatFavoritList";
 }

  
  

  @Override
  public UsuariEntitatFavoritForm getUsuariEntitatFavoritForm(UsuariEntitatFavoritJPA _jpa,
      boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariEntitatFavoritForm usuariEntitatFavoritForm;
    usuariEntitatFavoritForm = super.getUsuariEntitatFavoritForm(_jpa,__isView, request, mav);


    // Cas edició no permès
    if (!usuariEntitatFavoritForm.isNou()) {
        throw new I18NException("error.unknown","Un usuari favorit no es pot editar");
    }


    String usuariEntitatIDFavorit;
    usuariEntitatIDFavorit = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_HOLDER);
    if (usuariEntitatIDFavorit == null) {
        mav.setView(new RedirectView("/common/usuariEntitatFavorit/selecciousuari", true));
        return usuariEntitatFavoritForm;
    }
    request.getSession().removeAttribute(USUARI_ENTITAT_ID_HOLDER);
    
    usuariEntitatFavoritForm.addLabel(FAVORITID, "favorit.persona");
    UsuariEntitatFavorit usuariEntitatFavorit = usuariEntitatFavoritForm.getUsuariEntitatFavorit();

    // Ocultam camps
    LoginInfo loginInfo = LoginInfo.getInstance();
    String origenId = loginInfo.getUsuariEntitatID();
    usuariEntitatFavoritForm.addHiddenField(ORIGENID);
    usuariEntitatFavorit.setOrigenID(origenId);

    usuariEntitatFavoritForm.addReadOnlyField(FAVORITID);
    usuariEntitatFavorit.setFavoritID(usuariEntitatIDFavorit);

    /*
    // Fixam la llista amb l'usuari favorit
    UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatIDFavorit);

    
    List<StringKeyValue> lskvFavorit = new ArrayList<StringKeyValue>(1);
    lskvFavorit.add(skvFavorit);
    usuariEntitatFavoritForm.setListOfUsuariEntitatForFavoritID(lskvFavorit);
    */

    return usuariEntitatFavoritForm;
  }

  @Override
  public UsuariEntitatFavoritFilterForm getUsuariEntitatFavoritFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    UsuariEntitatFavoritFilterForm filterForm;
    filterForm = super.getUsuariEntitatFavoritFilterForm(pagina, mav, request);
    // Ocultam camps
    if (filterForm.isNou()) {
      filterForm.addHiddenField(ID);
      filterForm.addHiddenField(ORIGENID);

      // Llevam botó d'edició del llistat
      filterForm.setEditButtonVisible(false);
      
      filterForm.addLabel(FAVORITID, "favorit.persona");
      
      //filterForm.setTitleCode("favorit.llistat");

    }

    return filterForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return ORIGENID.equal(LoginInfo.getInstance().getUsuariEntitatID());
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      UsuariEntitatFavoritFilterForm filterForm, List<UsuariEntitatFavorit> list)
      throws I18NException {


    // Transformam de UsuariEntitatFavorit a una llista UsuariPersona
    List<String> entitatIDList = new ArrayList<String>(list.size());
    for (UsuariEntitatFavorit uef : list) {
       entitatIDList.add(uef.getFavoritID());
    }
    List< UsuariEntitatJPA> fullUsers =  usuariEntitatLogicaEjb.findByPrimaryKeyFullWithEntitat(entitatIDList);

    List<StringKeyValue> lskv = new ArrayList<StringKeyValue>(fullUsers.size());
    for(UsuariEntitatJPA ueJPA: fullUsers){
      UsuariPersonaJPA upJPA = ueJPA.getUsuariPersona();
      StringKeyValue skv = new StringKeyValue(ueJPA.getUsuariEntitatID(), upJPA.getNom() + " "
          + upJPA.getLlinatges() + " (" + upJPA.getNif() + ")");
      lskv.add(skv);
    }

    // Convertim a map la llista d'UsuariPersona
    Map<String, String> tmp = Utils.listToMap(lskv);
    filterForm.setMapOfUsuariEntitatForFavoritID(tmp);
  }

  @Override
  public String getEntityNameCode() {
    return "favorit.persona";
  }
  
  @Override
  public String getEntityNameCodePlural() {
    return "favorit.persona.plural";
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }
}
