package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.webdb.UsuariEntitatFavoritController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritForm;
import es.caib.portafib.back.security.LoginInfo;

import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
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
 */
@Controller
@RequestMapping(value = "/common/usuariEntitatFavorit")
@SessionAttributes(types = { SeleccioNifForm.class, UsuariEntitatFavoritForm.class, UsuariEntitatFavoritFilterForm.class })
public class GestioUsuariEntitatFavoritController extends UsuariEntitatFavoritController {

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  //private CommonBaseForm usuariEntitatFavoritForm;

  @Override
  public String getTileForm() {
    return "usuariEntitatFavoritForm";
  }

  @Override
  public String getTileList() {
    return "usuariEntitatFavoritList";
  }

  public String getTileNif() {
    return "selectUsuariEntitatFavoritByNif";
  }

  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuariEntitatByNifGet() throws Exception {

    ModelAndView mav = new ModelAndView(getTileNif());
    SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
    seleccioNifForm.setTitol("favorit.crear");
    seleccioNifForm.setSubtitol("favorit.introduirnif");
    seleccioNifForm.setCancelUrl(getContextWeb() + "/list/1");
    mav.addObject(seleccioNifForm);
    return mav;
  }

  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuariEntitatByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

     ModelAndView mav = new ModelAndView(getTileNif());

     String nif = seleccioNifForm.getNif();

     if (result.hasErrors()) {
      return mav;
     }

      // Si no han introduit Nif
    if (nif == null || nif.trim().length() == 0) {
    result.rejectValue("nif", "genapp.validation.required");
    return mav;
    }

    // Obtenim l'usuari persona amb el nif indicat
    UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(nif);

    // Si no hi ha usuariPersona associat al NIF
    if (up == null) {
      result.rejectValue("nif", "usuaripersona.noexisteix",
          new Object[] { I18NUtils.tradueix("nif"), nif }, null);
      return mav;
    }

    // Obtenim la entitat actual
    LoginInfo loginInfo = LoginInfo.getInstance();
    String entitatActualID = loginInfo.getEntitatID();

    // Cercam els usuaris entitat que poden ser candidats a favorits
    // relacionats amb l'usuari persona del NIF indicat.
    // (No han de ser càrrecs)
    Where w = Where.AND(UsuariEntitatFields.ENTITATID.equal(entitatActualID),
               UsuariEntitatFields.USUARIPERSONAID.equal(up.getUsuariPersonaID()),
               UsuariEntitatFields.CARREC.isNull());

    List<UsuariEntitat> usuariEntitatList = usuariEntitatLogicaEjb.select(w);
    // Si no hi ha usuaris entitat
    if (usuariEntitatList.isEmpty()) {
    result.rejectValue("nif", "usuarientitat.noexisteix.full", new Object[] { "nif", nif,
        entitatActualID }, null);
    return mav;
    }
    // Identificador d'usuari entitat que volem fer favorit
    String usuariEntitaIDFavorit =  usuariEntitatList.get(0).getUsuariEntitatID();

    // Cercar si ja el tenim com favorit
    {
      Where w1 = FAVORITID.equal(usuariEntitaIDFavorit);
      Where w2 = ORIGENID.equal(LoginInfo.getInstance().getUsuariEntitatID());
      if(usuariEntitatFavoritEjb.count(Where.AND(w1,w2))!=0){
       result.rejectValue("nif", "favorit.error.existeix");
       return mav;
      }
    }

    StringKeyValue skv = new StringKeyValue(usuariEntitaIDFavorit,
      up.getNom() + " " + up.getLlinatges() + " (" + up.getNif() + ")");
    request.getSession().setAttribute("usuariEntitatFavorit", skv);
    mav = new ModelAndView(new RedirectView(getContextWeb() + "/new", true));

    HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("favorit.verificacio"));

    return mav;


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


    StringKeyValue skvFavorit;
    skvFavorit = (StringKeyValue) request.getSession().getAttribute("usuariEntitatFavorit");
    if (skvFavorit == null) {
        mav.setView(new RedirectView("/common/usuariEntitatFavorit/nif", true));
        return usuariEntitatFavoritForm;
    }
    request.getSession().removeAttribute("usuariEntitatFavorit");
    
    usuariEntitatFavoritForm.addLabel(FAVORITID, "favorit.persona");
    UsuariEntitatFavorit usuariEntitatFavorit = usuariEntitatFavoritForm.getUsuariEntitatFavorit();

    // Ocultam camps
    LoginInfo loginInfo = LoginInfo.getInstance();
    String origenId = loginInfo.getUsuariEntitatID();
    usuariEntitatFavoritForm.addHiddenField(ORIGENID);
    usuariEntitatFavorit.setOrigenID(origenId);

    usuariEntitatFavoritForm.addReadOnlyField(FAVORITID);
    usuariEntitatFavorit.setFavoritID(skvFavorit.getKey());

    // Fixam la llista amb l'usuari favorit
    List<StringKeyValue> lskvFavorit = new ArrayList<StringKeyValue>(1);
    lskvFavorit.add(skvFavorit);
    usuariEntitatFavoritForm.setListOfUsuariEntitatForFavoritID(lskvFavorit);

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
