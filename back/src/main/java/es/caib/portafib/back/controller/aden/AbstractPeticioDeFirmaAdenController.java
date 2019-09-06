package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaByTipusSolicitant;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Configuracio;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractPeticioDeFirmaAdenController extends
    AbstractPeticioDeFirmaByTipusSolicitant {

  @Override
  public String getAnnexPath() {
    return "/aden/gestioannexes/list";
  }

  @Override
  public String getFluxPath() {
    return "/aden/fluxdefirmes/";
  }

  @Override
  public String getCustodiaContext() {
    return CustodiaInfoAdenController.ADEN_CUSTODIA_CONTEXT;
  }

  @Override
  public String getTileForm() {
    return "peticioDeFirmaAplicacioForm";
  }

  @Override
  public String getTileList() {
    return "peticioDeFirmaAplicacioList";
  }

  @Override
  public String getTileSeleccioFlux() {
    return "seleccionaFluxDeFirmaPerAplicacioForm";
  }

  @RequestMapping(value = "/fitxerspeticio/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String fitxerspeticio(HttpServletRequest request, @PathVariable Long peticioDeFirmaID) {
    request.getSession().setAttribute(FitxersDePeticioAdenController.SESSION_BACK_URL,
        getContextWeb() + "/list/");
    return "redirect:" + FitxersDePeticioAdenController.CONTEXT_WEB + "/peticio/"
        + peticioDeFirmaID;
  }

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    if (peticioDeFirmaFilterForm.isNou()) {
      peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
          "icon-list-alt icon-white", "peticiodefirma.fitxerspeticio", getContextWeb()
              + "/fitxerspeticio/{0}",
          // FitxersDePeticioAdenController.CONTEXT_WEB + "/peticio/{0}",
          "btn-info"));

      String bitacolaLink = BitacolaPeticioAdenController.CONTEXT_WEB
          + "/peticio/{0}?returnPath=" + getContextWeb() + "/list";
      peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
          "icon-cog icon-white", "peticiodefirma.bitacola", bitacolaLink, "btn-info"));
    }

    final boolean showUsuariEntitat = false;
    final boolean showUsuariAplicacio = true;

    AbstractPeticioDeFirmaAdenController.configureFilterFieldsPeticioDeFirma(
        peticioDeFirmaFilterForm, showUsuariEntitat, showUsuariAplicacio);

    AbstractPeticioDeFirmaAdenController.configureGroupByFieldsPeticioDeFirma(
        peticioDeFirmaFilterForm, showUsuariEntitat, showUsuariAplicacio);

    AbstractPeticioDeFirmaAdenController.cleanFiltersAndGroups(peticioDeFirmaFilterForm);

    return peticioDeFirmaFilterForm;

  }

  protected static void cleanFiltersAndGroups(PeticioDeFirmaFilterForm peticioDeFirmaFilterForm) {

    List<Field<?>> list = peticioDeFirmaFilterForm.getFilterByFields();

    if (list != null) {
      peticioDeFirmaFilterForm.setFilterByFields(new ArrayList<Field<?>>(
          new HashSet<Field<?>>(list)));
    }

    list = peticioDeFirmaFilterForm.getGroupByFields();
    if (list != null) {
      peticioDeFirmaFilterForm.setGroupByFields(new ArrayList<Field<?>>(new HashSet<Field<?>>(
          list)));
    }

  }

  protected static void configureGroupByFieldsPeticioDeFirma(
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm, boolean showUsuariEntitat,
      boolean showUsuariAplicacio) {
    if (peticioDeFirmaFilterForm.isNou() && Configuracio.isCAIB()) {

      List<Field<?>> campsGroupBy = new ArrayList<Field<?>>();

      campsGroupBy.add(TIPUSDOCUMENTID);
      campsGroupBy.add(TIPUSESTATPETICIODEFIRMAID);
      campsGroupBy.add(PRIORITATID);

      if (showUsuariAplicacio) {
        campsGroupBy.add(PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID);
      }

      if (showUsuariEntitat) {
        campsGroupBy.add(PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID);
      }

      peticioDeFirmaFilterForm.setGroupByFields(campsGroupBy);
    }

  }

  public static void configureFilterFieldsPeticioDeFirma(
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm, boolean showUsuariEntitat,
      boolean showUsuariAplicacio) {
    if (peticioDeFirmaFilterForm.isNou() && Configuracio.isCAIB()) {

      List<Field<?>> campsFiltre = new ArrayList<Field<?>>();

      campsFiltre.add(PETICIODEFIRMAID);
      campsFiltre.add(TITOL);
      campsFiltre.add(DESCRIPCIO);
      campsFiltre.add(MOTIU);
      campsFiltre.add(DATASOLICITUD);
      campsFiltre.add(DATAFINAL);
      campsFiltre.add(DATACADUCITAT);

      if (showUsuariAplicacio) {
        campsFiltre.add(SOLICITANTUSUARIAPLICACIOID);
      }

      if (showUsuariEntitat) {
        campsFiltre.add(SOLICITANTUSUARIENTITAT1ID);
      }

      peticioDeFirmaFilterForm.setFilterByFields(campsFiltre);

    }

  }

}
