package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.aden.GestioUsuariEntitatAdenController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.webdb.EntitatRefList;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
/**
 * @author dboerner
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/adminentitat")
@SessionAttributes(types = {RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class,
    SeleccioNifForm.class })
public class GestioRoleAdminEntitatController extends AbstractGestioRoleUsuariEntitatController {

  
  public static final StringField ENTITAT_NOM;
  
  
  static {
    ENTITAT_NOM = new RoleUsuariEntitatQueryPath().USUARIENTITAT().ENTITAT().NOM();
  }
  
  @Autowired
  protected EntitatRefList entitatRefList;
  
  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @Override
  public String getRoleGestionat() {
    return Constants.ROLE_ADEN;
  }
  
  @Override
  public String getTileForm() {
    return "gestioRoleAdminEntitatForm";
  }

  @Override
  public String getTileList() {
    return "gestioRoleAdminEntitatList";
  }
  
  @Override
  public String getTileNif() {
    return "selectUsuariPersonaForGestioRoleAdminEntitatForm";
  }
  
  @Override
  protected void initGetNif(SeleccioNifForm seleccionNifForm) {
    seleccionNifForm.setTitol("administradorentitat.alta");
    seleccionNifForm.setSubtitol("administradorentitat.alta.introduirnif");
    seleccionNifForm.setCancelUrl(getContextWeb() + "/list/1");
  }
  
  @Override
  public boolean checkIfHasUsuariEntitat() {
    return false;
  }
  
  
  @Override
  protected String checksPostNif(HttpServletRequest request,
      String usuariPersonaID, SeleccioNifForm seleccioNifForm,
      List<UsuariEntitat> usuariEntitatList, BindingResult result)
      throws I18NException {
    
    
    String nif = seleccioNifForm.getNif();
    
    // Veure quins usuaris entitat poden fer administrador-entitat
    List<String> candidatosAdminEntitatList = new ArrayList<String>();
    
    for (UsuariEntitat usuariEntitat : usuariEntitatList) {
      Where w1 = RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitat
          .getUsuariEntitatID());
      final Where w2 = RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_ADEN);
      //List<RoleUsuariEntitat> listCandidatos = roleUsuariEntitatLogicaEjb.selectWithEntitat(Where.AND(w1, w2));
      Long count = roleUsuariEntitatLogicaEjb.count(Where.AND(w1, w2));

      if (count == 0) {
        // Posible candidat
        candidatosAdminEntitatList.add(usuariEntitat.getUsuariEntitatID());
      } else {
        // Ja esta donat d'alta (informam a l'usuari)
        // TODO Falta nom de l'entitat
        String missatge = I18NUtils.tradueix("administradorentitat.jaexisteix", nif, usuariEntitat.getEntitatID());
        HtmlUtils.saveMessageInfo(request, missatge);
        /*
        List<String> usuariEntitatIDList = new ArrayList<String>();
        usuariEntitatIDList.add(usuariEntitat.getUsuariEntitatID());
        List<UsuariEntitatJPA> usuariEntitatJPAList = usuariEntitatLogicaEjb
            .findByPrimaryKeyFullWithEntitat(usuariEntitatIDList);
        
        result.rejectValue("nif", "validaciojaesadminent", new Object[] { nif }, null);
        return mav;
        */
        
      }
    }

    if (candidatosAdminEntitatList.size() == 0) {
      // Miram si s'ha de forçar la creació de l'usuari entitat
      String entitatID = seleccioNifForm.getParam1();
      if (entitatID != null && entitatID.trim().length() != 0) {
        
        // Existeix l'entitat
        EntitatJPA entitat = entitatEjb.findByPrimaryKey(entitatID);
        
        if (entitat == null) {
          log.error("L'entitat [" + entitatID + "] no existeix");
          entitatID = null;
        } else {
          // Mirar si era AdEn
          if (usuariEntitatList.contains(entitat)) {
            entitatID = null;
            log.error("Ja és ADEN de l'entitat [" + entitatID + "]");
          } else {
            UsuariEntitat ue = new UsuariEntitatJPA();
            GestioUsuariEntitatAdenController.initUsuariEntitat(ue, usuariPersonaID, entitatID);
            
            ue = usuariEntitatLogicaEjb.create(ue);
            
            candidatosAdminEntitatList.add(ue.getUsuariEntitatID());
          }
        }        
      }
      
      if (entitatID == null) {
        if (usuariEntitatList.size() == 0) {
          result.rejectValue("nif", "administradorentitat.senseentitat", new Object[] { nif }, null);
          
          return null;
        } else {
          result.rejectValue("nif", "administradorentitat.jaesADENdetotes", new Object[] { nif }, null);
          return null;
        }
      }
    } 
    
    request.getSession().setAttribute("candidatosADEN", candidatosAdminEntitatList);
    
    return getContextWeb() + "/new";
  }
  
  
  
  @Override
  public void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException {

    List<String> listUsuariEntidadID = (List<String>) request.getSession().getAttribute(
    "candidatosADEN");
    
    List<UsuariEntitatJPA> listUsuariEntitatJPA = usuariEntitatLogicaEjb
        .findByPrimaryKeyFullWithEntitat(listUsuariEntidadID);
    
    List<StringKeyValue> nueva = new ArrayList<StringKeyValue>();

    for (UsuariEntitatJPA usuariEntitatJPA : listUsuariEntitatJPA) {
      nueva.add(new StringKeyValue(usuariEntitatJPA.getUsuariEntitatID(), Utils
          .getNom(usuariEntitatJPA.getUsuariPersona())
          + " - "
          + usuariEntitatJPA.getEntitat().getNom()));
    }
    
    roleUsuariEntitatForm.setListOfUsuariEntitatForUsuariEntitatID(nueva);

  }
  
  @Override
  public void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav, String nif) throws I18NException {
    throw new I18NException("error.unknown", "Els roles dels usuaris entitat no són editables !!!!");
  }
  
  
  @Override
  public boolean isActiveFormEdit() {
    return false;
  }
  
  @Override
  public List<StringKeyValue> getReferenceListForUsuariEntitatID(
      HttpServletRequest request, ModelAndView mav,
    RoleUsuariEntitatForm roleUsuariEntitatForm, Where _w) throws I18NException {
    
    List<String> listUsuariEntidadID = (List<String>) request.getSession().getAttribute("candidatosADEN");
    request.getSession().removeAttribute("candidatosADEN");
    List<StringKeyValue> nueva = new ArrayList<StringKeyValue>();
    if (listUsuariEntidadID == null) {
      mav.setView(new RedirectView(getContextWeb() + "/nif", true));
      return nueva;
    }
    List<UsuariEntitatJPA> listUsuariEntitatJPA = usuariEntitatLogicaEjb
        .findByPrimaryKeyFullWithEntitat(listUsuariEntidadID);

    for (UsuariEntitatJPA usuariEntitatJPA : listUsuariEntitatJPA) {
      nueva.add(new StringKeyValue(usuariEntitatJPA.getUsuariEntitatID(),
          Utils.getNom(usuariEntitatJPA.getUsuariPersona()) + " "
              + usuariEntitatJPA.getEntitat().getNom()));
    }
    return nueva;
  }
  
  
  @Override
  public RoleUsuariEntitatFilterForm getRoleUsuariEntitatFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    RoleUsuariEntitatFilterForm roleUsuariEntitatFilterForm;
    roleUsuariEntitatFilterForm = super.getRoleUsuariEntitatFilterForm(pagina, mav, request);

    /**
     * Cuando un FilterForm es nuevo? Cuando se crea la sesión. Un FilterForm
     * representa la composición básica (títulos, botones columnas, etc...) de
     * un listado. Durante la sesión el usuario puede des/ordenar o des/agrupar
     * como el quiera pero, a pesar de estas acciones la estructura base se debe
     * mantener. Por ello, mediante la siguiente condición inicializamos la
     * composición del listado base solo una vez: isNou()
     */
    if (roleUsuariEntitatFilterForm.isNou()) {
      // Ocultam columnes
      roleUsuariEntitatFilterForm.addHiddenField(ROLEID);

      // Ocultam botons d'accions
      // roleUsuariEntitatFilterForm.setAddButtonVisible(false);
      roleUsuariEntitatFilterForm.setEditButtonVisible(false);

      if (!Configuracio.isDesenvolupament()) {
        roleUsuariEntitatFilterForm.addHiddenField(ID);
      }
      
      roleUsuariEntitatFilterForm.addLabel(USUARIENTITATID, Constants.ROLE_ADEN);
      
      roleUsuariEntitatFilterForm.setTitleCode("administradorentitat.llistat");
      
      roleUsuariEntitatFilterForm.addGroupByField(ENTITAT_NOM);
      
      roleUsuariEntitatFilterForm.setVisibleMultipleSelection(true);
      
      if (entitatEjb.count(EntitatFields.ACTIVA.equal(true)) == 0) {
        roleUsuariEntitatFilterForm.setAddButtonVisible(false);
      }
    }
    
    
    return roleUsuariEntitatFilterForm;
  }
  
  

  @Override
  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
      ModelAndView mav, RoleUsuariEntitatFilterForm roleUsuariEntitatFilterForm,
      List<RoleUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where _w)
      throws I18NException {

    // Key == usuariEntitatID | value = RoleUsuariEntitat
    Map<String, RoleUsuariEntitatJPA> map = new HashMap<String, RoleUsuariEntitatJPA>();

    List<String> listUsuariEntidadID = new ArrayList<String>();
    for (RoleUsuariEntitat roleUsuariEntitat : list) {
      listUsuariEntidadID.add(roleUsuariEntitat.getUsuariEntitatID());
      map.put(roleUsuariEntitat.getUsuariEntitatID(), (RoleUsuariEntitatJPA) roleUsuariEntitat);
    }
    List<UsuariEntitatJPA> listUsuariEntitatJPA = usuariEntitatLogicaEjb
        .findByPrimaryKeyFullWithEntitat(listUsuariEntidadID);

    List<StringKeyValue> nueva = new ArrayList<StringKeyValue>();
    for (UsuariEntitatJPA usuariEntitatJPA : listUsuariEntitatJPA) {
      nueva.add(new StringKeyValue(usuariEntitatJPA.getUsuariEntitatID(), Utils
          .getNom(usuariEntitatJPA.getUsuariPersona())));
      map.get(usuariEntitatJPA.getUsuariEntitatID()).setUsuariEntitat(usuariEntitatJPA);
    }

    return nueva;
  }
  

  @Override
  public Map<Field<?>, GroupByItem> fillReferencesForList(RoleUsuariEntitatFilterForm filterForm,
      HttpServletRequest request, ModelAndView mav,
        List<RoleUsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    
      Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm, request, mav, list, groupItems);

      Map<String, String> _tmp;
      List<StringKeyValue> _listSKV;

      // Field ENTITAT_NOM
      _listSKV = this.entitatRefList.getReferenceList(EntitatFields.NOM, null);
      _tmp = org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
      groupByItemsMap.get(ENTITAT_NOM).setCodeLabel(EntitatFields._TABLE_MODEL + "." + EntitatFields._TABLE_MODEL);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITAT_NOM, false);

      return groupByItemsMap;
  }
  
  
}
