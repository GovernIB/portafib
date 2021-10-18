package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;


/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/peticiosincrona")
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
public class PeticioSincronaAdenController extends PeticioDeFirmaController {

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal passarelaDeFirmaWebEjb;
  
  @PostConstruct
  public void init() {
    usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);
    usuariAplicacioRefList.setSelects(new Select<?>[] {
        UsuariAplicacioFields.USUARIAPLICACIOID.select
    });
  }
  
  

  @Override
  public String getEntityNameCode() {
    return "peticiosincrona";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "peticiosincrona.plural";
  }

  @Override
  public String getTileList() {
    return "peticioSincronaListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "peticiosincrona_FilterForm";
  }

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    PeticioDeFirmaFilterForm fitxerFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);
    if (fitxerFilterForm.isNou()) {
      
      
      fitxerFilterForm.setSubTitleCode("peticiosincrona.subtitol");

      fitxerFilterForm.setVisibleMultipleSelection(false);
      fitxerFilterForm.setVisibleFilterBy(false);
      fitxerFilterForm.setVisibleGroupBy(false);
      fitxerFilterForm.setAddButtonVisible(false);
      fitxerFilterForm.setDeleteSelectedButtonVisible(false);
      fitxerFilterForm.setDeleteButtonVisible(false);
      fitxerFilterForm.setEditButtonVisible(false);
      
      
      Set<Field<?>> hidden =  fitxerFilterForm.getHiddenFields();
      hidden.addAll(Arrays.asList(PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS));
      
      hidden.remove(TITOL);
      hidden.remove(DESCRIPCIO);
      hidden.remove(DATACADUCITAT);
      hidden.remove(SOLICITANTUSUARIAPLICACIOID);
     
      
      
      // ni agrupacio i filtre
      fitxerFilterForm.setGroupByFields(new ArrayList<Field<?>>());
      fitxerFilterForm.setFilterByFields(new ArrayList<Field<?>>());
      
      
      

      // All items
      fitxerFilterForm.setItemsPerPage(-1);

      //fitxerFilterForm.addLabel(MIME, "peticiosincrona.error");
      //fitxerFilterForm.addLabel(TAMANY, "peticiosincrona.items");

    }

    return fitxerFilterForm;
  }

  @Override
  public List<PeticioDeFirma> executeSelect(ITableManager<PeticioDeFirma, Long> ejb, Where where,
      final OrderBy[] orderBy, final Integer itemsPerPage, final int inici)
      throws I18NException {

    
    Map<String, PassarelaSignaturesSetWebInternalUse> map;
    map = passarelaDeFirmaWebEjb.getAllTransactionsByEntitatID(LoginInfo.getInstance()
        .getEntitatID());

    List<PeticioDeFirma> peticions = new ArrayList<PeticioDeFirma>();
   

    for (Map.Entry<String, PassarelaSignaturesSetWebInternalUse> entry : map.entrySet()) {

      PassarelaSignaturesSetWebInternalUse p = entry.getValue();

      
      java.lang.String nom = entry.getKey();

      java.lang.String descripcio = "Firmes: " + p.getSignaturesSet().getFileInfoSignatureArray().length;



      PeticioDeFirmaJPA f = new PeticioDeFirmaJPA();
      // hidden.remove(TITOL);
      f.setTitol(nom);
      //hidden.remove(DESCRIPCIO);
      f.setDescripcio(descripcio);
      //hidden.remove(DATACADUCITAT);
      f.setDataCaducitat(new Timestamp(p.getSignaturesSet().getExpiryDate().getTime()));
      //hidden.remove(SOLICITANTUSUARIAPLICACIOID);
      f.setSolicitantUsuariAplicacioID(p.getApplicationID());

      peticions.add(f);

    }

    return peticions;

  }

  @Override
  public boolean isActiveList() {
    return true;
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
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return false;
  }

}
