package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/peticio/netejaesborrat")
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
public class PeticioDeFirmaNetejarEsborrarAdenController extends AbstractPeticioDeFirmaAdenController  {

  

  public static final int NETEJA_ADAPTAT = 1;

  public static final int NETEJA_ORIGINAL = 2;

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @Override
  public String getTileList() {
    return "peticionsDeFirmaNetejarEsborrarList";
  }


  @Override
  public String getSessionAttributeFilterForm() {
    return "PeticioDeFirmaNetejarEsborrar_WEB_FilterForm";
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    Where w1 = Where.OR(
        TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT),
        TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT));

    String entitatID = LoginInfo.getInstance().getEntitatID();

    Where w2 = new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID().equal(entitatID);

    return Where.AND(w1, w2);
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
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    HtmlUtils.saveMessageInfo(request,
        I18NUtils.tradueix("peticiodefirma.netejaesborrat.ajuda"));

    if (peticioDeFirmaFilterForm.isNou()) {

      peticioDeFirmaFilterForm.setTitleCode("peticiodefirma.netejaesborrat.llistat");

      peticioDeFirmaFilterForm.setSubTitleCode("peticiodefirma.netejaesborrat.subtitle");

      peticioDeFirmaFilterForm.setDeleteSelectedButtonVisible(false);
      peticioDeFirmaFilterForm.setEditButtonVisible(false);
      peticioDeFirmaFilterForm.setDeleteButtonVisible(false);
      peticioDeFirmaFilterForm.setAddButtonVisible(false);

      // Ocultam tots els camps
      Set<Field<?>> hiddenFields = peticioDeFirmaFilterForm.getHiddenFields();
      hiddenFields.addAll(Arrays.asList(ALL_PETICIODEFIRMA_FIELDS));

      // Mostram els següents camps...
      hiddenFields.remove(PeticioDeFirmaFields.PETICIODEFIRMAID);
      hiddenFields.remove(PeticioDeFirmaFields.TITOL);
      // hiddenFields.remove(PeticioDeFirmaFields.DATASOLICITUD);
      hiddenFields.remove(PeticioDeFirmaFields.DATAFINAL);
      // hiddenFields.remove(PeticioDeFirmaFields.USUARIENTITATID);
      // hiddenFields.remove(PeticioDeFirmaFields.USUARIAPLICACIOID);
      hiddenFields.remove(PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID);

      if (!Configuracio.isCAIB()) {
        // Cerca
        List<Field<?>> filterByFields = new ArrayList<Field<?>>();
  
        // filterByFields.add(PeticioDeFirmaFields.PETICIODEFIRMAID);
        filterByFields.add(PeticioDeFirmaFields.TITOL);
        // filterByFields.add(PeticioDeFirmaFields.DATASOLICITUD);
        filterByFields.add(PeticioDeFirmaFields.DATAFINAL);
        // filterByFields.add(PeticioDeFirmaFields.USUARIENTITATID);
        // filterByFields.add(PeticioDeFirmaFields.USUARIAPLICACIOID);
  
        peticioDeFirmaFilterForm.setFilterByFields(filterByFields);
  
        // Agrupacio
      
        peticioDeFirmaFilterForm.addGroupByField(DATAFINAL);
        peticioDeFirmaFilterForm.addGroupByField(DATASOLICITUD);
        peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIAPLICACIOID);
        peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIENTITAT1ID);
        peticioDeFirmaFilterForm.addGroupByField(TIPUSESTATPETICIODEFIRMAID);
      }
      
      AbstractPeticioDeFirmaAdenController.cleanFiltersAndGroups(peticioDeFirmaFilterForm);
      

      // Per defecte seleccionam les anteriors a un mes
      peticioDeFirmaFilterForm.setDataFinalFins(new Timestamp(Calendar.getInstance()
          .getTimeInMillis() - 31556952000L / 12));

      // ordenar mes antigues primer
      peticioDeFirmaFilterForm.setOrderBy(DATAFINAL.fullName);
      peticioDeFirmaFilterForm.setOrderAsc(true);

      /*
       * Si es torna a activar s'ha de donar d'alta en tiles (descomentar)
       * peticioDeFirmaFilterForm.addAdditionalButtonForEachItem( new
       * AdditionalButton("icon-list-alt", "veuredetalls", getContextWeb() +
       * "/view/{0}", "btn-info"));
       */


      AdditionalField<Long, String> adfield1 = new AdditionalField<Long, String>();
      adfield1.setCodeName("=<i class=\"icon-share\"></i>");
      adfield1.setPosition(NETEJA_ADAPTAT);
      adfield1.setEscapeXml(false);
      // Els valors s'ompliran al mètode postList()
      adfield1.setValueMap(new HashMap<Long, String>());

      peticioDeFirmaFilterForm.addAdditionalField(adfield1);

      AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
      adfield2.setCodeName("=<i class=\"icon-fire\"></i>");
      adfield2.setPosition(NETEJA_ORIGINAL);
      adfield2.setEscapeXml(false);
      // Els valors s'ompliran al mètode postList()
      adfield2.setValueMap(new HashMap<Long, String>());

      peticioDeFirmaFilterForm.addAdditionalField(adfield2);

    }
    return peticioDeFirmaFilterForm;
  }




  @RequestMapping(value = "/netejarOriginal", method = RequestMethod.POST)
  public String netejarOriginals(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats == null || seleccionats.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      for (int i = 0; i < seleccionats.length; i++) {
        try {
          Long peticioDeFirmaID = stringToPK(seleccionats[i]);

          peticioDeFirmaLogicaEjb.cleanOriginalFilesOfPeticioDeFirma(peticioDeFirmaID);

        } catch (I18NException i18ne) {
          String missatge = I18NUtils.getMessage(i18ne);
          HtmlUtils.saveMessageError(request, missatge);
          log.error(missatge, i18ne);
        }
      }
    }

    String redirect = getRedirectWhenDelete(request, null, null);
    return redirect;

  }

  @RequestMapping(value = "/netejarAdaptat", method = RequestMethod.POST)
  public String netejarAdaptats(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats == null || seleccionats.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      for (int i = 0; i < seleccionats.length; i++) {
        try {
          Long peticioDeFirmaID = stringToPK(seleccionats[i]);

          peticioDeFirmaLogicaEjb.cleanAdaptatFileOfPeticioDeFirma(peticioDeFirmaID);

        } catch (I18NException i18ne) {
          String missatge = I18NUtils.getMessage(i18ne);
          HtmlUtils.saveMessageError(request, missatge);
          log.error(missatge, i18ne);
        }
      }
    }

    String redirect = getRedirectWhenDelete(request, null, null);
    return redirect;

  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {
    
    super.postList(request, mav, filterForm, list);
    
    
    // En el pare es fa un esborrat de tots el botons addicionals,
    // per això cada vegada les hem de tornar a afegir 

    filterForm.addAdditionalButton(new AdditionalButton(
        "icon-fire icon-white", "peticiodefirma.netejaesborrat.netejaroriginal",
        "javascript:submitTo('peticioDeFirmaFilterForm'," + " '" + request.getContextPath()
            + getContextWeb() + "/netejarOriginal');", "btn-danger"));

    filterForm.addAdditionalButton(new AdditionalButton(
        "icon-share icon-white", "peticiodefirma.netejaesborrat.netejaradaptat",
        "javascript:submitTo('peticioDeFirmaFilterForm'," + " '" + request.getContextPath()
            + getContextWeb() + "/netejarAdaptat');", "btn-warning"));

    
    // Afegir contingut a columnes
    Map<Long, String> mapAdaptat;
    mapAdaptat = (Map<Long, String>) filterForm.getAdditionalField(NETEJA_ADAPTAT)
        .getValueMap();
    mapAdaptat.clear();

    Map<Long, String> mapOriginal;
    mapOriginal = (Map<Long, String>) filterForm.getAdditionalField(NETEJA_ORIGINAL)
        .getValueMap();
    mapOriginal.clear();

    
    Long key;
    for (PeticioDeFirma peticio : list) {
      key = peticio.getPeticioDeFirmaID();
      if (peticio.getFitxerAFirmarID() != null) {

        mapOriginal.put(key, "<i class=\"icon-fire\"></i>");

        if (peticio.getFitxerAdaptatID() != null) {
          mapAdaptat.put(key, "<i class=\"icon-share\"></i>");
        }
      }
    }
  }

  @Override
  protected boolean showUsuariEntitat() {
    return true;
  }

  @Override
  protected boolean showUsuariAplicacio() {
    return true;
  }
  
  @Override
  public boolean mostrarSolicitant() {
    return true;
  }

  @Override
  protected boolean mostrarBotoRebuig() {
    return false;
  }

  @Override
  protected boolean mostrarBotoBitacola() {
    return true;
  }

  @Override
  protected boolean mostrarBotoEsborrar() {
    return true;
  }


  @Override
  protected boolean ferRebuigQuanEsborra() {
    // Les peticions només són acabades bé o rebutjades
    return false;
  }
  

}
