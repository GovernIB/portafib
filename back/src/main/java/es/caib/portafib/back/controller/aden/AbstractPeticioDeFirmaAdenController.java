package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaByTipusSolicitant;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Configuracio;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
public abstract class AbstractPeticioDeFirmaAdenController extends
    AbstractPeticioDeFirmaByTipusSolicitant {

  private int COLUMN_REMITENT = 1;

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

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    if (peticioDeFirmaFilterForm.isNou()) {

      {
        // Amagam el remitent per defecte i cream una nova columna que juntarà remitentNom i remitentDescripció
        peticioDeFirmaFilterForm.addHiddenField(REMITENTNOM);

        AdditionalField<Long, String> additionalField = new AdditionalField<Long, String>();
        additionalField.setCodeName(REMITENTNOM.codeLabel);
        additionalField.setPosition(COLUMN_REMITENT);
        additionalField.setEscapeXml(false);
        // Els valors s'ompliran al mètode postList()
        additionalField.setValueMap(new HashMap<Long, String>());
        // Per ordenar feim servir el mateix camp de nom del remitent
        additionalField.setOrderBy(REMITENTNOM);

        peticioDeFirmaFilterForm.addAdditionalField(additionalField);
      }

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
      campsFiltre.add(REMITENTNOM);
      campsFiltre.add(REMITENTDESCRIPCIO);
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

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {

    log.info("postList()");
    log.info("HiddenFilds: " + filterForm.getHiddenFields());

    log.info("super.postList()");
    super.postList(request, mav, filterForm, list);

    Map<Long, String> mapRemitent = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_REMITENT).getValueMap();
    mapRemitent.clear();

    log.info("mapRemitent: " + mapRemitent);

    for (PeticioDeFirma pf : list) {
      StringBuffer str = new StringBuffer();
      str.append("<small><b>");
      str.append(pf.getRemitentNom()).append("</b>");
      String remiDesc = pf.getRemitentDescripcio();
      if (remiDesc != null) {
        str.append("<br/>").append(remiDesc);
      }
      str.append("</small>");
      mapRemitent.put(pf.getPeticioDeFirmaID(),  str.toString());
    }

    log.info("mapRemitent: " + mapRemitent);

    log.info("HiddenFilds: " + filterForm.getHiddenFields());
  }
}
