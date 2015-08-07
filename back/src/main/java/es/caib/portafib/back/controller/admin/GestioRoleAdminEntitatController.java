package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.aden.GestioUsuariEntitatAdenController;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.EntitatRefList;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
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
    SeleccioUsuariForm.class })
public class GestioRoleAdminEntitatController extends AbstractGestioRoleUsuariEntitatController {

  public static final StringField ENTITAT_NOM;
  
  public static final StringField USUARIPERSONA_NOM;
  

  static {
    ENTITAT_NOM = new RoleUsuariEntitatQueryPath().USUARIENTITAT().ENTITAT().NOM();
    USUARIPERSONA_NOM = new RoleUsuariEntitatQueryPath().USUARIENTITAT().USUARIPERSONAID();
  }
  
  @Autowired
  protected EntitatRefList entitatRefList;
  
  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
  
  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  @Autowired
  protected UsuariPersonaRefList personaRefList;
  

  @PostConstruct
  public void init() {
        
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }
    this.personaRefList = new UsuariPersonaRefList(personaRefList);

    this.personaRefList.setSelects(new Select<?>[] { 
        UsuariPersonaFields.LLINATGES.select , new SelectConstant(", "), 
        UsuariPersonaFields.NOM.select });
    
    this.personaRefList.setSeparator("");

  }

  

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
  
  
  
  
  /*
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
  */
  
  @Override
  protected String getTileSeleccioUsuari() {
    return "seleccioUsuariForm_ADMIN";
  }
  
  
  @Override
  protected SeleccioUsuariForm getSeleccioUsuariForm(HttpServletRequest request) {

     SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

     seleccioUsuariForm.setTitol("administradorentitat.alta");
     seleccioUsuariForm.setSubtitol("administradorentitat.alta.introduirnif");
     seleccioUsuariForm.setCancelUrl(getContextWeb() + "/list/1");
     seleccioUsuariForm.setUrlData("/common/json/usuaripersona");
     seleccioUsuariForm.setParam1(request.getParameter("entitatID"));

     // Només entrarem si l'administrador té el rol de PFI_USER. Si només
     // és PFI_ADMIN llavors no mostrarem Favorits
     if (LoginInfo.getInstance().getUsuariEntitatID() != null) {
       List<StringKeyValue> skvList;
       try { 
         skvList = SearchJSONController.favoritsToUsuariPersona(
             usuariEntitatLogicaEjb.selectFavorits(
           LoginInfo.getInstance().getUsuariEntitatID(), null, false));
       } catch (I18NException e) {
         log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
         skvList = null;
       }
       seleccioUsuariForm.setUsuarisFavorits(skvList);
     }

     return seleccioUsuariForm;
  }
  
  
  
  @Override
  protected String checksPostNif(HttpServletRequest request,
      UsuariPersona usuariPersona, String param1, String param2) throws I18NException {
    
    final String usuariPersonaID = usuariPersona.getUsuariPersonaID();
    List<String> candidatosAdminEntitatList = new ArrayList<String>();
    
    // Primer miram ens han especificat l'entitat a la que volem crear l'AdEn.
    String entitatID = param1;
    if (entitatID != null && entitatID.trim().length() != 0) {
      
      // Existeix l'entitat
      EntitatJPA entitat = entitatEjb.findByPrimaryKey(entitatID);
      
      if (entitat == null) {
        // No existeix l'entitat
        throw new I18NException("error.notfound",
            new I18NArgumentCode("entitat.entitat"),
            new I18NArgumentCode("entitat.entitatID"),
            new I18NArgumentString(String.valueOf(entitatID)));
      } else {
        // L'entitat existeix i ara mira si existeix l'usuari-entitat
        UsuariEntitatJPA usuariEntitat;
        usuariEntitat = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatID,
            usuariPersonaID);
        
        if (usuariEntitat == null) {
          // L'usuari entitat no existeix i el cream
          usuariEntitat = new UsuariEntitatJPA();
          GestioUsuariEntitatAdenController.initUsuariEntitat(usuariEntitat, usuariPersonaID, entitatID);
          usuariEntitat = (UsuariEntitatJPA)usuariEntitatLogicaEjb.create(usuariEntitat);
        } else {
          // Ara miram si ja és AdEn en aquesta entitat
          Where whereCount = Where.AND(
              RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitat.getUsuariEntitatID()),
              RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_ADEN)
             ); 
          if (roleUsuariEntitatLogicaEjb.count(whereCount) != 0) {
            // administradorentitat.jaexisteix=L´usuari {0} ja és Administrador-Entitat de l´entitat {1}
            throw new I18NException("administradorentitat.jaexisteix",
                Utils.getNom(usuariPersona), entitatID);
          }
        }
        
        // Ho proposam per crear
        candidatosAdminEntitatList.add(usuariEntitat.getUsuariEntitatID());
      }        
    } else {
      // No s'ha elegit una entitat en concret, llavors seleccionam tots els 
      // usuaris-entitat actius que no són càrrecs i que a més no tenen el rol ADEN 
      
      SubQuery<RoleUsuariEntitat, String> subquery;
      subquery = roleUsuariEntitatLogicaEjb.getSubQuery(RoleUsuariEntitatFields.USUARIENTITATID,
          Where.AND(
             RoleUsuariEntitatFields.USUARIENTITATID.equal(UsuariEntitatFields.USUARIENTITATID),
             RoleUsuariEntitatFields.ROLEID.equal(Constants.ROLE_ADEN)
            )
           );
      
      Where wTots = Where.AND(
          UsuariEntitatFields.USUARIPERSONAID.equal(usuariPersonaID),
          UsuariEntitatFields.CARREC.isNull(),
          UsuariEntitatFields.ACTIU.equal(true)
          );
      
      Where wTotsSenseAden = Where.AND(wTots,
          UsuariEntitatFields.USUARIENTITATID.notIn(subquery));
      
      List<UsuariEntitat> usuariEntitatTotsList = usuariEntitatLogicaEjb.select(wTots);
      List<UsuariEntitat> usuariEntitatSenseAdenList = usuariEntitatLogicaEjb.select(wTotsSenseAden);

      if (usuariEntitatTotsList.size() == 0) {
        // L´usuari {0} no té cap entitat associada
        throw new I18NException("administradorentitat.senseentitat",
            Utils.getNom(usuariPersona));
      } else {
      
        if (usuariEntitatSenseAdenList.size() == 0) {
          // Ja es Aden en totes les entitats en que es usuari-entitat
          throw new I18NException("administradorentitat.jaesADENdetotes",
              Utils.getNom(usuariPersona));
        } else {
          // Mostrar els usuaris entitat en que encara no es AdEn
          List<String> entitatsQueJaEsAden = new ArrayList<String>();
          
          for (UsuariEntitat usuariEntitat : usuariEntitatTotsList) {
            if (usuariEntitatSenseAdenList.contains(usuariEntitat)) {
              candidatosAdminEntitatList.add(usuariEntitat.getUsuariEntitatID());
            } else {
              entitatsQueJaEsAden.add(usuariEntitat.getEntitatID());
            }
          }

          // L´usuari {0} ja és Administrador-Entitat de les següents entitats {1}
          if (!entitatsQueJaEsAden.isEmpty()) {
            String missatge = I18NUtils.tradueix("administradorentitat.jaexisteixllistat", 
              Utils.getNom(usuariPersona), Arrays.toString(entitatsQueJaEsAden.toArray()) );
            HtmlUtils.saveMessageInfo(request, missatge);
          }
          
        }
      }
      
    }
    
    
    
    
    
    
    
    /*
    // Veure quins usuaris-entitat podem fer administrador-entitat
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

        
        String missatge = I18NUtils.tradueix("administradorentitat.jaexisteix", 
            Utils.getNom(usuariPersona), usuariEntitat.getEntitatID());
        HtmlUtils.saveMessageInfo(request, missatge);
        
      }
    }

    if (candidatosAdminEntitatList.size() == 0) {
      // Miram si s'ha de forçar la creació de l'usuari entitat
      String entitatID = param1;
      if (entitatID != null && entitatID.trim().length() != 0) {
        
        // Existeix l'entitat
        EntitatJPA entitat = entitatEjb.findByPrimaryKey(entitatID);
        
        if (entitat == null) {
          log.error("L'entitat [" + entitatID + "] no existeix");
          entitatID = null;
        } else {
          // Mirar si era AdEn
          if (usuariEntitatList.contains(entitat)) {
            log.error("Ja és ADEN de l'entitat [" + entitatID + "]");
            entitatID = null;
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
          throw new I18NException("administradorentitat.senseentitat", Utils.getNom(usuariPersona));
        } else {
          throw new I18NException("administradorentitat.jaesADENdetotes", Utils.getNom(usuariPersona));
        }
      }
    } 
    */
    request.getSession().setAttribute("candidatosADEN", candidatosAdminEntitatList);
    
    return getContextWeb() + "/new";
  }
  
  
  
  @Override
  public void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
      HttpServletRequest request, ModelAndView mav,UsuariPersona usuariPersona) throws I18NException {

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
      HttpServletRequest request, ModelAndView mav, UsuariPersona usuariPersona) throws I18NException {
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
      mav.setView(new RedirectView(getContextWeb() + "/selecciousuari", true));
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
      roleUsuariEntitatFilterForm.addGroupByField(USUARIPERSONA_NOM);
      
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
      
      
       // Field USUARI PERSONA_NOM
      _listSKV = this.personaRefList.getReferenceList(UsuariPersonaFields.USUARIPERSONAID, null, new OrderBy(UsuariPersonaFields.LLINATGES));
      _tmp = org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
      groupByItemsMap.get(USUARIPERSONA_NOM).setCodeLabel(UsuariPersonaFields._TABLE_MODEL + "." + UsuariPersonaFields._TABLE_MODEL);
      fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIPERSONA_NOM, false);


      return groupByItemsMap;
  }
  
  
}
