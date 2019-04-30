package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Configuracio;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractPeticioDeFirmaAdenController extends
    AbstractPeticioDeFirmaController {

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    configureFilterFieldsPeticioDeFirma(peticioDeFirmaFilterForm);

    configureGroupByFieldsPeticioDeFirma(peticioDeFirmaFilterForm,
        showUsuariEntitat(), showUsuariAplicacio());

    return peticioDeFirmaFilterForm;
  }

  protected abstract boolean showUsuariEntitat();

  protected abstract boolean showUsuariAplicacio();

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
  
  
  public void configureFilterFieldsPeticioDeFirma(
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm) {
    
    configureFilterFieldsPeticioDeFirma(
        peticioDeFirmaFilterForm, showUsuariEntitat(), showUsuariAplicacio());
    
  }
  

  public static void configureFilterFieldsPeticioDeFirma(
      PeticioDeFirmaFilterForm peticioDeFirmaFilterForm, boolean showUsuariEntitat, boolean showUsuariAplicacio) {
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
      
      if(showUsuariEntitat) {
        campsFiltre.add(SOLICITANTUSUARIENTITAT1ID);
      }

      peticioDeFirmaFilterForm.setFilterByFields(campsFiltre);

    }

  }
}
