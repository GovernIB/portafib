package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.UsuariAplicacioController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioForm;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.UsuariAplicacioWebLogicValidator;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.model.entity.RoleUsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created 25/06/13 14:26
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = "/aden/usuariAplicacio")
@SessionAttributes(types = { UsuariAplicacioForm.class, UsuariAplicacioFilterForm.class })
public class GestioUsuariAplicacioAdenController extends UsuariAplicacioController {

  protected static final int PERMISOS = 1;
  
  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariAplicacioLocal roleUsuariAplicacioEjb;

  @Autowired
  private UsuariAplicacioWebLogicValidator usuariAplicacioWebLogicValidator;
  
  /**
   * Indica si es admin o aden
   * @return
   */
  protected boolean isAdmin() {
    return false;
  }


  @Override
  public String getTileForm() {
    return "gestioUsuariAplicacioFormAden";
  }

  @Override
  public String getTileList() {
    return isAdmin()?"gestioUsuariAplicacioListAdmin" : "gestioUsuariAplicacioListAden";
  }
  
  @Override
  public String getSessionAttributeFilterForm() {
    return isAdmin()? "UsuariAplicacio_Admin_FilterForm" : "UsuariAplicacio_Aden_FilterForm";
  }

  @Override
  public boolean isActiveFormNew() {
    return !isAdmin();
  }

  @Override
  public boolean isActiveFormEdit() {
    return !isAdmin();
  }

  
  @PostConstruct
  public void init() {
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
    setWebValidator(usuariAplicacioWebLogicValidator);
  }

  @Override
  public UsuariAplicacioForm getUsuariAplicacioForm(UsuariAplicacioJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariAplicacioForm usuariAplicacioForm= super.getUsuariAplicacioForm(_jpa, __isView,request,mav);

    // Establim l'entitat de login
    usuariAplicacioForm.getUsuariAplicacio().setEntitatID(LoginInfo.getInstance().getEntitatID());

    if (usuariAplicacioForm.isNou()) {
      // Fixam l'email de l'administrador si el formulari es nou      
      usuariAplicacioForm.getUsuariAplicacio().setEmailAdmin(Utils.getLoggedUserEmail());

      UsuariAplicacioJPA aplicacio = usuariAplicacioForm.getUsuariAplicacio();
      if (!Configuracio.isCAIB()) {
        String prefix = LoginInfo.getInstance().getEntitatID() + "_";
        aplicacio.setUsuariAplicacioID(prefix);
      }
      aplicacio.setCallbackVersio(1);
      aplicacio.setIdiomaID(Configuracio.getDefaultLanguage());
      aplicacio.setActiu(true);
      aplicacio.setCallbackURL("http://HOST:8080/portafib/cb/v1/PortaFIBCallBack");
      aplicacio.setPotCustodiar(false);
    } else {
      usuariAplicacioForm.addReadOnlyField(USUARIAPLICACIOID);
      usuariAplicacioForm.addReadOnlyField(ACTIU);
    }
    
    usuariAplicacioForm.setAttachedAdditionalJspCode(true);

    // Ocultam camps
    usuariAplicacioForm.addHiddenField(ENTITATID);
    
   
    if (Configuracio.isCAIB()) {
      usuariAplicacioForm.addHiddenField(CONTRASENYA);
      /*
      HtmlUtils.saveMessageInfo(request, missatge)
      
      usuariAplicacioForm.addHelpToField(CONTRASENYA, I18NUtils.tradueix("usuariaplicacio.gestioseycon"));
      //usuariAplicacioForm.getUsuariAplicacio().
      // setContrasenya(I18NUtils.tradueix("usuariaplicacio.gestioseycon"));
      */
    }

    if (usuariAplicacioForm.getUsuariAplicacio().isActiu()) {
      usuariAplicacioForm.addAdditionalButton(new AdditionalButton(
        "icon-ban-circle", "desactivar", getContextWeb() + "/desactivar/{0}", "btn-warning"));
    } else {
      usuariAplicacioForm.addAdditionalButton(new AdditionalButton(
        "icon-play", "activar", getContextWeb() + "/activar/{0}", "btn-success"));
    }

    return usuariAplicacioForm;
   }

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
      
      if (isAdmin()) {
        return super.getReferenceListForEntitatID(request, mav, where);
      } else {
        List<StringKeyValue> lskvEntitat = new ArrayList<StringKeyValue>();
        LoginInfo loginInfo = LoginInfo.getInstance();
        EntitatJPA entitatJPA = loginInfo.getEntitat();
        StringKeyValue skvEntitat = new StringKeyValue(entitatJPA.getEntitatID(),
                entitatJPA.getNom());
        lskvEntitat.add(skvEntitat);
        return lskvEntitat;
      }
      
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForCallbackVersio(HttpServletRequest request,
        ModelAndView mav, Where where)  throws I18NException {
     List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
          
     __tmp.add(new StringKeyValue("-1" , "No Callback"));
     __tmp.add(new StringKeyValue("0" , "Portafirmas CAIB"));
     __tmp.add(new StringKeyValue("1" , "PortaFIB Callback v1.0"));
     return __tmp;
   }
    
    

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      if (isAdmin()) {
        return null;
      } else {
        return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
      }
    }
    
    @Override
    public UsuariAplicacioFilterForm getUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
        HttpServletRequest request) throws I18NException {
        UsuariAplicacioFilterForm usuariAplicacioFilterForm;
        usuariAplicacioFilterForm = super.getUsuariAplicacioFilterForm(pagina, mav, request);

        if (usuariAplicacioFilterForm.isNou()) {
          
          usuariAplicacioFilterForm.addHiddenField(CONTRASENYA);
          usuariAplicacioFilterForm.addHiddenField(DESCRIPCIO);
          usuariAplicacioFilterForm.addHiddenField(IDIOMAID);
          usuariAplicacioFilterForm.addHiddenField(EMAILADMIN);
          usuariAplicacioFilterForm.addHiddenField(LOGOSEGELLID);
          usuariAplicacioFilterForm.addHiddenField(POTCUSTODIAR);
          

          usuariAplicacioFilterForm.setTitleCode("usuariaplicacio.llistat");

          usuariAplicacioFilterForm.setDeleteSelectedButtonVisible(false);
          
          usuariAplicacioFilterForm.setVisibleMultipleSelection(false);
          /*
          Map<String,String> map = new HashMap<String, String>();
          map.put("anadal", "ROL 1");
          map.put("anadalapp", "ROL 2");
          map.put("caibapp", "ROL 3");
          map.put("fundaciobit_hola", "ROL 4");
          map.put("sonespases_app", "ROL 5");
          
          
          AdditionalField<String,String> adfield = new AdditionalField<String,String>(); 
          adfield.setCodeName("signant");
          adfield.setPosition(-2);
          adfield.setValueField(USUARIAPLICACIOID);
          adfield.setValueMap(map);

          AdditionalField<String,String> adfield2 = new AdditionalField<String,String>(); 
          adfield2.setCodeName("descripcio");
          adfield2.setPosition(-3);
          
          StringField sf = new UsuariAplicacioQueryPath().IDIOMA().NOM();
          log.info(" DDDDDDDDDDDDDDDD = " + sf.fullName);
          adfield2.setValueField(sf);
          
          
          AdditionalField<String,String> adfield3 = new AdditionalField<String,String>(); 
          adfield3.setCodeName("estatdefirma.motiu.rebuig");
          adfield3.setPosition(-1);
          adfield3.setValueMap(map);
          
          usuariAplicacioFilterForm.addAdditionalField(adfield);
          usuariAplicacioFilterForm.addAdditionalField(adfield2);
          usuariAplicacioFilterForm.addAdditionalField(adfield3);
          
          */
          if (!Configuracio.isCAIB()) {
            AdditionalField<String,String> adfield4 = new AdditionalField<String,String>(); 
            adfield4.setCodeName("permisos");
            adfield4.setPosition(PERMISOS);
            // Els valors s'ompliran al mètode postList()
            adfield4.setValueMap(new HashMap<String, String>());
  
            usuariAplicacioFilterForm.addAdditionalField(adfield4);
          }

          if (isAdmin()) {
            usuariAplicacioFilterForm.setDeleteButtonVisible(false);
            usuariAplicacioFilterForm.setEditButtonVisible(false);
            usuariAplicacioFilterForm.setAddButtonVisible(false);

            List<Field<?>> filterBy = new ArrayList<Field<?>>();
            filterBy.add(USUARIAPLICACIOID);
            usuariAplicacioFilterForm.setFilterByFields(filterBy);

            usuariAplicacioFilterForm.addGroupByField(ENTITATID);
            usuariAplicacioFilterForm.addGroupByField(ACTIU);

            usuariAplicacioFilterForm.addHiddenField(CALLBACKURL);
            usuariAplicacioFilterForm.addHiddenField(CALLBACKVERSIO);
          } else {
            usuariAplicacioFilterForm.addHiddenField(ENTITATID);
          }

        }
        
        usuariAplicacioFilterForm.setVisibleExportList(true);

        return usuariAplicacioFilterForm;
    }
    
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariAplicacioFilterForm filterForm,  List<UsuariAplicacio> list) throws I18NException {
      
       if (Configuracio.isCAIB()) {
         return;
       }
      

       List<String> usuariAplicacioIds = new ArrayList<String>(list.size());
       for (UsuariAplicacio usuariAplicacio : list) {
         usuariAplicacioIds.add(usuariAplicacio.getUsuariAplicacioID());
       }
       
       
       List<RoleUsuariAplicacio> rols;
       rols = roleUsuariAplicacioEjb.select(USUARIAPLICACIOID.in(usuariAplicacioIds));
       
       Map<String, String> map;
       map = (Map<String, String>)filterForm.getAdditionalField(PERMISOS).getValueMap(); 
       map.clear();
       String key, value;
       for (RoleUsuariAplicacio roleUsuariAplicacio : rols) {
        key = roleUsuariAplicacio.getUsuariAplicacioID();
        value = map.get(key);
        if (value == null) {
          map.put(key, roleUsuariAplicacio.getRoleID());
        } else {
          map.put(key, value + "," + roleUsuariAplicacio.getRoleID());
        }
       }
       
       filterForm.getAdditionalButtonsByPK().clear();
       
       final String suffix = isAdmin()? "admin" : "user";
       final String rol = isAdmin()? Constants.PFI_ADMIN : Constants.PFI_USER;
       
       
       for (String id : usuariAplicacioIds) {
         String roles = map.get(id);
         if (roles == null || roles.trim().isEmpty() || roles.indexOf(rol) == -1) {
           filterForm.addAdditionalButtonByPK(id, new AdditionalButton(
               "icon-plus-sign", "usuariaplicacio.addrol" + suffix,  getContextWeb() + "/addrol" + suffix + "/{0}",
               "btn-success"));
         } else {
           filterForm.addAdditionalButtonByPK(id, new AdditionalButton(
               "icon-minus-sign", "usuariaplicacio.removerol" + suffix,  getContextWeb() + "/removerol" + suffix+ "/{0}",
               "btn-warning"));
         }
       }

    }
    
    
    
    
    @RequestMapping(value = "/addroluser/{usuariAplicacioID}", method = RequestMethod.GET)
    public String afegirRolUsuari(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {

      gestionaRolUsuari(usuariAplicacioID, request, true);
      
      return "redirect:" + getContextWeb() + "/list";
    }
    
    
    @RequestMapping(value = "/removeroluser/{usuariAplicacioID}", method = RequestMethod.GET)
    public String eliminarRolUsuari(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {
      gestionaRolUsuari(usuariAplicacioID, request, false);

      return "redirect:" + getContextWeb() + "/list";
    }

    private void gestionaRolUsuari(String usuariAplicacioID, HttpServletRequest request,
        boolean afegir) {
      try {
        boolean cacheBorrada;
        if (afegir) {
          cacheBorrada = usuariAplicacioLogicaEjb.afegirRolUser(usuariAplicacioID);
        } else {
          cacheBorrada = usuariAplicacioLogicaEjb.eliminarRolUser(usuariAplicacioID);
        }
        if (!cacheBorrada) {
          String msg = I18NUtils.tradueix("usuariaplicacio.noborradacache");
          HtmlUtils.saveMessageWarning(request, msg);
        }
      } catch(I18NException i18ne) {
        String msg = I18NUtils.getMessage(i18ne);
        log.error(msg, i18ne);
        HtmlUtils.saveMessageError(request, msg);
      } catch(Exception e) {
        String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
        log.error(msg, e);
        HtmlUtils.saveMessageError(request, msg);
      }
    }
    
    
    
    @RequestMapping(value = "/addroladmin/{usuariAplicacioID}", method = RequestMethod.GET)
    public String afegirRolAdmin(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {

      gestionaRolAdmin(usuariAplicacioID, request, true);
      
      return "redirect:" + getContextWeb() + "/list";
    }
    
    
    @RequestMapping(value = "/removeroladmin/{usuariAplicacioID}", method = RequestMethod.GET)
    public String eliminarRolAdmin(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {

      gestionaRolAdmin(usuariAplicacioID, request, false);

      return "redirect:" + getContextWeb() + "/list";
    }

    private void gestionaRolAdmin(String usuariAplicacioID, HttpServletRequest request,
        boolean afegir) {
      try {
        boolean cacheBorrada;
        if (afegir) {
          cacheBorrada = usuariAplicacioLogicaEjb.afegirRolAdmin(usuariAplicacioID);
        } else {
          cacheBorrada = usuariAplicacioLogicaEjb.eliminarRolAdmin(usuariAplicacioID);
        }
        if (!cacheBorrada) {
          String msg = I18NUtils.tradueix("usuariaplicacio.noborradacache");
          HtmlUtils.saveMessageWarning(request, msg);
        }
      } catch(I18NException i18ne) {
        String msg = I18NUtils.getMessage(i18ne);
        log.error(msg, i18ne);
        HtmlUtils.saveMessageError(request, msg);
      } catch(Exception e) {
        String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
        log.error(msg, e);
        HtmlUtils.saveMessageError(request, msg);
      }
    }
    

    @Override
    public void delete(HttpServletRequest request, UsuariAplicacio usuariAplicacio) throws Exception,I18NException {
      Set<Long> fitxers = usuariAplicacioLogicaEjb.deleteFull(usuariAplicacio.getUsuariAplicacioID());
      this.borrarFitxers(fitxers);
    }
    

    
    @RequestMapping(value = "/activar/{usuariAplicacioID}", method = RequestMethod.GET)
    public ModelAndView activarUsuariEntitat(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {

      activarDesactivarUsauriApp(usuariAplicacioID, request, true);

      ModelAndView mav = new ModelAndView(new RedirectView(
          getContextWeb() + "/" + usuariAplicacioID + "/edit", true));
      return mav;
    }
    
    @RequestMapping(value = "/desactivar/{usuariAplicacioID}", method = RequestMethod.GET)
    public ModelAndView desactivarUsuariEntitat(
        @PathVariable("usuariAplicacioID") String usuariAplicacioID, 
        HttpServletRequest request,  HttpServletResponse response) throws Exception {

      activarDesactivarUsauriApp(usuariAplicacioID,
          request,false);

      ModelAndView mav = new ModelAndView(new RedirectView(
          getContextWeb() + "/" + usuariAplicacioID + "/edit", true));
      return mav;
    }

    private void activarDesactivarUsauriApp(String usuariAplicacioID,
      HttpServletRequest request, boolean activar) {
      try {        
        if (activar) {
          usuariAplicacioLogicaEjb.activar(usuariAplicacioID);
        } else {
          usuariAplicacioLogicaEjb.desactivar(usuariAplicacioID);
        }
      } catch(I18NException i18ne) {
        String msg = I18NUtils.getMessage(i18ne);
        log.error(msg, i18ne);
        HtmlUtils.saveMessageError(request, msg);
      } catch(Exception e) {
        String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
        log.error(msg, e);
        HtmlUtils.saveMessageError(request, msg);
      }
    }
    
    
    @Override
    public UsuariAplicacioJPA create(HttpServletRequest request, UsuariAplicacioJPA usuariAplicacio)
      throws Exception,I18NException, I18NValidationException {
      return usuariAplicacioLogicaEjb.createFull(usuariAplicacio, 
         LoginInfo.getInstance().getEntitatID());
    }
    

}
