package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/peticionscaducades")
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
public class LlistatPeticionsCaducadesController extends AbstractPeticioDeFirmaAdenController {

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;
  
  @Override
  public String getTileList() {
    return "peticionsDeFirmaCaducadesList";
  }
  
  @Override
  public String getTileForm() {
    return "peticioDeFirmaCaducadaForm";
  }
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "PeticionsDeFirmaCaducades_FilterForm";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where w1 = DATACADUCITAT.lessThan(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    String entitatID = LoginInfo.getInstance().getEntitatID();
    
    Where w2 = new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID().equal(entitatID);
    /* Codi Antic
    Where w2 = USUARIAPLICACIOID.in(usuariAplicacioEjb.getSubQuery(
        UsuariAplicacioFields.USUARIAPLICACIOID, UsuariAplicacioFields.ENTITATID.equal(entitatID)));
    */
    Where w3 = TIPUSESTATPETICIODEFIRMAID.in(new Integer[]{        
        ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES,
        ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT,
    });
    return Where.AND(w1,w2, w3);
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
    return true;
  }
  

  @Override
  public boolean isActiveDelete() {
    return false;
  }
  
  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
      peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);
      if(peticioDeFirmaFilterForm.isNou()) {
        
        peticioDeFirmaFilterForm.setTitleCode("peticionscaducades.llistat");
        
        peticioDeFirmaFilterForm.setDeleteButtonVisible(false);
        peticioDeFirmaFilterForm.setEditButtonVisible(false);
        peticioDeFirmaFilterForm.setAddButtonVisible(false);
        peticioDeFirmaFilterForm.setVisibleMultipleSelection(false);
        
        // Ocultam tots els camps
        Set<Field<?>> hiddenFields =  peticioDeFirmaFilterForm.getHiddenFields();
        hiddenFields.addAll(Arrays.asList(ALL_PETICIODEFIRMA_FIELDS));

        // Mostram els següents camps...
        hiddenFields.remove(PeticioDeFirmaFields.PETICIODEFIRMAID);
        hiddenFields.remove(PeticioDeFirmaFields.TITOL);
        hiddenFields.remove(PeticioDeFirmaFields.DESCRIPCIO);
        hiddenFields.remove(PeticioDeFirmaFields.DATACADUCITAT);
        hiddenFields.remove(PeticioDeFirmaFields.DATASOLICITUD);

        // Agrupacio
        if (!Configuracio.isCAIB()) {
          peticioDeFirmaFilterForm.addGroupByField(TIPUSESTATPETICIODEFIRMAID);
          peticioDeFirmaFilterForm.addGroupByField(DATACADUCITAT);
          peticioDeFirmaFilterForm.addGroupByField(PRIORITATID);
          peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIAPLICACIOID);
        }
        
        
        // Afegim un boto
        /*
        log.info(" ++++ Scheme: " + request.getScheme());
        log.info(" ++++ PathInfo: " + request.getPathInfo());
        log.info(" ++++ PathTrans: " + request.getPathTranslated());
        log.info(" ++++ RealPath: " + request.getRealPath("/soli/plantilla/"));
        log.info(" ++++ ContextPath: " + request.getContextPath());
        log.info(" ++++ ServletPath: " + request.getServletPath());
        log.info(" ++++ getRequestURI: " + request.getRequestURI());
        log.info(" ++++ getQueryString: " + request.getQueryString());
        */

        final String message = FluxDeFirmesFields._TABLE_MODEL + "." +  FluxDeFirmesFields._TABLE_MODEL;
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-eye-open", message,
            "javascript:window.open('" + request.getContextPath() 
            + "/aden/plantilla/viewonlyfluxofpeticio/{0}?readOnly=true' ,'popup','toolbar=no,directories=no,menubar=no,location=no,scrollbars=yes,width=560,height=650')",
                "btn-info"));
        
        
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-list-alt", "veuredetalls",
            getContextWeb() + "/view/{0}",
                "btn-info"));

        String bitacolaLink = BitacolaPeticioAdenController.CONTEXT_WEB + "/peticio/{0}?returnPath=/aden/peticionscaducades/list";
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-cog", "peticiodefirma.bitacola",
                bitacolaLink, "btn-info"));

        AbstractPeticioDeFirmaAdenController.cleanFiltersAndGroups(peticioDeFirmaFilterForm);

        
      }
      return peticioDeFirmaFilterForm;
  }
  
  @PostConstruct
  public void init() {
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }

    // Configura com es mostra l'usuari aplicació
    usuariAplicacioRefList = new UsuariAplicacioRefList(
        usuariAplicacioRefList);
    usuariAplicacioRefList
        .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    usuariAplicacioRefList.setSeparator("");
  }

  @Override
  protected boolean showUsuariEntitat() {
    return true;
  }

  @Override
  protected boolean showUsuariAplicacio() {
    return true;
  }
  

  
}
