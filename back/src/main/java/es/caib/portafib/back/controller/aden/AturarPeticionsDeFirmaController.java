package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;




import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.AturarPeticionsDeFirmaFilterForm;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/aturarpeticions")
@SessionAttributes(types = { AturarPeticionsDeFirmaFilterForm.class, SeleccioNifForm.class })
public class AturarPeticionsDeFirmaController extends PeticioDeFirmaController {
  
  public static final String NIF_OR_USERNAME_OF_USUARI_ENTITAT = "NIF_OR_USERNAME_OF_USUARI_ENTITAT";
  
  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
  protected UsuariEntitatLocal usuariEntitatEjb;
  
  @EJB(mappedName = FirmaLocal.JNDI_NAME)
  protected FirmaLocal firmaEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  public String getTileNif() {
    return "selectNifDestinatariPerAturarPeticio";
  }
  
  
  @Override
  public String getTileList() {
    return "aturarPeticionsDeFirma";
  }
  

  @Override
  public boolean isActiveFormNew() {
    return false;
  }
  
  @Override
  public boolean isActiveFormEdit() {
    return false;
  }
  
  @Override
  public boolean isActiveFormView() {
    return false;
  }
  

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "AturarPeticionsDeFirma_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    
    AturarPeticionsDeFirmaFilterForm filterForm;
    filterForm = (AturarPeticionsDeFirmaFilterForm) request.getSession()
          .getAttribute(getSessionAttributeFilterForm());
    
    String nifOrUsername = filterForm.getNifOrUsername();


    FirmaQueryPath firmaQueryPath = new FirmaQueryPath();
    
    PeticioDeFirmaQueryPath peticio = firmaQueryPath.BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA(); 
    
    
    // Seleccionam les peticions de firma actives que contenen alguna 
    // firma activa d'un usuari-entitat associat al nif NIF
    // NOTA: Una firma que conté un càrrec, en el moment que s'activa,
    // es converteix l'usuari-carrec en usuari-entitat 
    
    final UsuariPersonaQueryPath upqp;
    upqp = firmaQueryPath.USUARIENTITAT().USUARIPERSONA();
    
    Where w = Where.AND(
        // Peticions actives o pausades
        peticio.PETICIODEFIRMAID().isNotNull(),
        peticio.TIPUSESTATPETICIODEFIRMAID().in(new Integer[]{
            Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
            Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT
           }),
        // Nif associat al requerit
        Where.OR(
            upqp.NIF().equal(nifOrUsername.toUpperCase()),
            upqp.USUARIPERSONAID().equal(nifOrUsername)
            ),
        // D'un bloc no finalitzat
        firmaQueryPath.BLOCDEFIRMES().DATAFINALITZACIO().isNull(),
        // D'una firma no finalitzada
        FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull()
    );
    
    
    
    return PETICIODEFIRMAID.in(firmaEjb.getSubQuery(peticio.PETICIODEFIRMAID(), w));
        
  }
  
  
  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
      peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

      
      // Check: Sempre ha d'haver passat per la pantalla de nif/username
      String  nifOrUsername;
      nifOrUsername = (String) request.getSession().getAttribute(NIF_OR_USERNAME_OF_USUARI_ENTITAT);

      if (nifOrUsername == null || nifOrUsername.trim().length() == 0) {
        mav.setView(new RedirectView(getContextWeb()+"/nif", true));
        return peticioDeFirmaFilterForm;
      }

      peticioDeFirmaFilterForm = new AturarPeticionsDeFirmaFilterForm(peticioDeFirmaFilterForm);
      
      ((AturarPeticionsDeFirmaFilterForm)peticioDeFirmaFilterForm).setNifOrUsername(nifOrUsername);
      
      peticioDeFirmaFilterForm.setSubTitleCode("=" +
          I18NUtils.tradueix("aturarpeticionsdefirma.subtitol", nifOrUsername));
      
      
      if(peticioDeFirmaFilterForm.isNou()) {
       
        
        peticioDeFirmaFilterForm.setTitleCode("aturarpeticionsdefirma");
        // "=" Significa que no es traduirà, s'imprimirà directament a la pàgina web

        
        peticioDeFirmaFilterForm.setDeleteButtonVisible(false);
        peticioDeFirmaFilterForm.setEditButtonVisible(false);
        peticioDeFirmaFilterForm.setAddButtonVisible(false);
        peticioDeFirmaFilterForm.setVisibleMultipleSelection(false);
        
        // Ocultam tots els camps
        for(Field<?> f: PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS) {
          peticioDeFirmaFilterForm.addHiddenField(f);
        }
        // Mostram els següents ...
        List<Field<?>> list = peticioDeFirmaFilterForm.getHiddenFields();
        list.remove(PeticioDeFirmaFields.PETICIODEFIRMAID);
        list.remove(PeticioDeFirmaFields.TITOL);
        list.remove(PeticioDeFirmaFields.DESCRIPCIO);
        list.remove(PeticioDeFirmaFields.DATACADUCITAT);
        list.remove(PeticioDeFirmaFields.DATASOLICITUD);
        
        
        // Agrupacio
        peticioDeFirmaFilterForm.setGroupByFields(new ArrayList<Field<?>>());
        
        
        
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "icon-remove", "rebutjar",  getContextWeb() + "/rebutjar/{0}",
            "btn-danger"));

        
      }
      return peticioDeFirmaFilterForm;
  }
  
  /*
  @PostConstruct
  public void init() {
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }
  }
  */
  
  
  @RequestMapping(value = "/rebutjar/{peticioDeFirmaID}")
  public ModelAndView rebutjar(HttpServletRequest request,
      HttpServletResponse response, @PathVariable Long peticioDeFirmaID) throws I18NException {
    
    
    try {
      // TODO ha d'anar a la part de lògica
      PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFull(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        // Error
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      } else {
      
        String motiuDeRebuig = I18NUtils.tradueix(
            "aturarpeticionsdefirma.motiurebuig",
            Utils.getNom(LoginInfo.getInstance().getUsuariPersona()));
      
        peticioDeFirmaLogicaEjb.rebutjar(peticioDeFirma, motiuDeRebuig);

      }

    } catch(I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
    }

    return llistatPaginat(request, response, null);
  }
  
  
  
  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuaripersonaByNifGet()
      throws Exception {


     ModelAndView mav = new ModelAndView(getTileNif());
     SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
     seleccioNifForm.setTitol("aturarpeticionsdefirma");
     seleccioNifForm.setSubtitol("aturarpeticionsdefirma.nif.subtitol");
     seleccioNifForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
     mav.addObject(seleccioNifForm);
     return mav;
  }
  
  
  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuaripersonaByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request)
      throws I18NException {

    ModelAndView mav = new ModelAndView(getTileNif());

    String nifousername = seleccioNifForm.getNif();

    if(result.hasErrors()) {
         log.error("Errors en el formulari de NIF");
         return mav;
    }

    // Si no han introduit Nif
    if (nifousername == null || nifousername.trim().length() == 0) {
      // TODO En altre sidiomes que no siguin ca o es aquest missatge pot fallar
      result.rejectValue("nif", "genapp.validation.required", new Object[]{""}, null );
      return mav;
    }

    // Verificar que hi ha com a mínim un usuari-entitat amb aquest nif
    List<UsuariEntitat> ups = usuariEntitatEjb.select(getSelectUsuarisEntitat(nifousername));
    if (ups.isEmpty()) { // No existeix
      
      // No té usuaris-entitat associats
      result.rejectValue("nif", "aturarpeticionsdefirma.nif.error.nifsenseusuarisentitat",
          new Object[] { nifousername }, null);
      return mav;

    } else {
      request.getSession().setAttribute(NIF_OR_USERNAME_OF_USUARI_ENTITAT, nifousername);
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
      return mav;
    }

  }
  
  
  private Where getSelectUsuarisEntitat(String nifusername) {
    // TODO Ha d'anar a variable statica
    UsuariEntitatQueryPath usuariEntitatQueryPath = new UsuariEntitatQueryPath();
    return Where.AND(
          // NIF o USERNAME
          Where.OR(
            usuariEntitatQueryPath.USUARIPERSONA().NIF().equal(nifusername.toUpperCase()),
            usuariEntitatQueryPath.USUARIPERSONA().USUARIPERSONAID().equal(nifusername)
          ),
          // ENTITAT
          UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID())
        );
  }
  
}
