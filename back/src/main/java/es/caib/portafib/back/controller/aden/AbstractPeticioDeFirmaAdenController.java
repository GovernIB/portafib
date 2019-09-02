package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractPeticioDeFirmaAdenController extends
    AbstractPeticioDeFirmaController implements ConstantsV2 {
  
  /**
   * Columna Solicitant
   */
  public static final int SOLICITANT = 0;
  
  
  @PostConstruct
  public void init() {
    if (log.isDebugEnabled()) {
      log.debug(" Entra dins init() de " + getClass().getName());
    }

    if (mostrarSolicitant()) {
      // Configura com es mostra l'usuari aplicació
      usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);
      usuariAplicacioRefList
          .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
      usuariAplicacioRefList.setSeparator("");
  
      // Configura com es mostra l'usuari entitat
      this.usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);
  
      UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
      usuariEntitatRefList.setSelects(new Select<?>[] { personaQueryPath.NOM().select,
          new SelectConstant(" "),personaQueryPath.LLINATGES().select,
          new SelectConstant(" ("), personaQueryPath.USUARIPERSONAID().select
          ,new SelectConstant(")") });
      usuariEntitatRefList.setSeparator("");
    }

  }
  
  
  

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    configureFilterFieldsPeticioDeFirma(peticioDeFirmaFilterForm);
    
    configureGroupByFieldsPeticioDeFirma(peticioDeFirmaFilterForm,
        showUsuariEntitat(), showUsuariAplicacio());
    
    if (peticioDeFirmaFilterForm.isNou()) {

      if (mostrarBotoBitacola()) {
        peticioDeFirmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "icon-cog", "peticiodefirma.bitacola",
            BitacolaPeticioAdenController.CONTEXT_WEB + "/peticio/{0}?returnPath=" + getContextWeb() + "/list",
            "btn-info"));
      }
      
      
      if(mostrarSolicitant()) {
        AdditionalField<Long, String> adfield0 = new AdditionalField<Long, String>();
        adfield0.setCodeName("ROLE_SOLI");
        adfield0.setPosition(SOLICITANT);
        adfield0.setEscapeXml(false);
        // Els valors s'ompliran al mètode postList()
        adfield0.setValueMap(new HashMap<Long, String>());

        peticioDeFirmaFilterForm.addAdditionalField(adfield0);
      }
      
      
    }
    

    return peticioDeFirmaFilterForm;
  }

  protected abstract boolean showUsuariEntitat();

  protected abstract boolean showUsuariAplicacio();

  protected abstract boolean mostrarBotoRebuig();

  protected abstract boolean mostrarBotoBitacola();
  
  protected abstract boolean mostrarSolicitant();
  
  protected abstract boolean mostrarBotoEsborrar();
  
  protected abstract boolean ferRebuigQuanEsborra();

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
  

  @RequestMapping(value = "/rebutjar/{peticioDeFirmaID}")
  public String rebutjar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {

    rebutjar(request, peticioDeFirmaID);

    return getRedirectWhenDelete(request, peticioDeFirmaID, null);
  }



  @RequestMapping(value = "/rebutjarSeleccionades", method = RequestMethod.POST)
  public String rebutjarSeleccionades(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute PeticioDeFirmaFilterForm filterForm) {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats != null && seleccionats.length != 0) {
      for (int i = 0; i < seleccionats.length; i++) {
        rebutjar(request, Long.parseLong(seleccionats[i]));
      }
    }

    return getRedirectWhenDelete(request, null, null);
  }


  protected PeticioDeFirmaJPA rebutjar(HttpServletRequest request, Long peticioDeFirmaID) {
    try {
      // TODO ha d'anar a la part de lògica
      PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
          .findByPrimaryKeyFull(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        // Error
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      } else {
        
        String motiuDeRebuig2 = request.getParameter("motiuRebuig");

        String motiuDeRebuig = I18NUtils.tradueix("aturarpeticionsdefirma.motiurebuig",
            Utils.getNom(LoginInfo.getInstance().getUsuariPersona()), motiuDeRebuig2);


        peticioDeFirmaLogicaEjb.rebutjarADEN(peticioDeFirma, LoginInfo.getInstance()
            .getUsuariEntitatID(), motiuDeRebuig);

      }
      
      return peticioDeFirma;

    } catch (I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
      return null;
    }
  }
  
  
  
  @RequestMapping(value = "/esborrar/{peticioDeFirmaID}")
  public String esborrar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {
    
    if (ferRebuigQuanEsborra()) {
      rebutjar(request, peticioDeFirmaID);
    }

    esborrar(request, peticioDeFirmaID);

    return  getRedirectWhenDelete(request, null, null);
  }
  
  
  

  @RequestMapping(value = "/esborrarSeleccionades", method = RequestMethod.POST)
  public String esborrarSeleccionades(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats == null || seleccionats.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      
      if (ferRebuigQuanEsborra()) {
        for (int i = 0; i < seleccionats.length; i++) {
          Long peticioDeFirmaID = stringToPK(seleccionats[i]);
          rebutjar(request, peticioDeFirmaID);
          Thread.sleep(100);
        }
        Thread.sleep(1000);
      }
      
      
      for (int i = 0; i < seleccionats.length; i++) {
        Long peticioDeFirmaID = stringToPK(seleccionats[i]);
        esborrar(request, peticioDeFirmaID);
      }
    }

    return getRedirectWhenDelete(request, null, null);
  }




  protected void esborrar(HttpServletRequest request, Long peticioDeFirmaID) {
    Set<Long> fitxersAEliminar;
    try {          
      {
        String usuariEntitatID = LoginInfo.getInstance().getUsuariEntitatID();
        fitxersAEliminar =  peticioDeFirmaLogicaEjb.
            deleteFullUsingUsuariEntitat(peticioDeFirmaID, usuariEntitatID);
        FileSystemManager.eliminarArxius(fitxersAEliminar);
      }
    } catch (I18NException i18ne) {
      String missatge = I18NUtils.getMessage(i18ne);
      HtmlUtils.saveMessageError(request, missatge);
      log.error(missatge, i18ne);
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
      campsFiltre.add(REMITENTNOM);
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
  
  
  
  
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {
    
    
    if (mostrarSolicitant()) {
    
      Map<Long, String> mapSoli;
      mapSoli = (Map<Long, String>) filterForm.getAdditionalField(SOLICITANT).getValueMap();
      mapSoli.clear();
  
      Long key;
  
      Map<String, String> mapWeb = filterForm.getMapOfUsuariEntitatForSolicitantUsuariEntitat1ID();
      Map<String, String> mapApp = filterForm.getMapOfUsuariAplicacioForSolicitantUsuariAplicacioID();
  
      for (PeticioDeFirma peticio : list) {
        key = peticio.getPeticioDeFirmaID();
        
        // Nous camps de Peticio de Firma #281
        switch (peticio.getOrigenPeticioDeFirma()) {
          case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
            mapSoli.put(key, "<small><b>WEB:</b> " + mapWeb.get(peticio.getSolicitantUsuariEntitat1ID())
                + "</small>");
          break;
  
          case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
            mapSoli.put(key, "<small><b>WS_V1:</b> " + mapApp.get(peticio.getSolicitantUsuariAplicacioID())
                + "</small>");
          break;
          
          case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
            mapSoli.put(key, "<small><b>ASYNC_SIMPLE_V2:</b> " + mapApp.get(peticio.getSolicitantUsuariAplicacioID())
                + "</small>");
          break;
            
          default:
           // XYZ ZZZ TRA
           HtmlUtils.saveMessageError(request, "No hi ha codi per el PostList de les Peticions de Firma amb Origen " + 
              I18NUtils.tradueix("origenpeticiodefirma." + peticio.getOrigenPeticioDeFirma()));
        }

      }
    }
    
    boolean visibleMultipleSelection = false;

    filterForm.getAdditionalButtonsByPK().clear();
    filterForm.getAdditionalButtons().clear();
    
    // Boto Global de rebuig
    if (mostrarBotoRebuig()) {


      boolean mostrarBotoGlobal = false;

      Long key;
      int estat;
      for (PeticioDeFirma peticio : list) {
        key = peticio.getPeticioDeFirmaID();
        estat = peticio.getTipusEstatPeticioDeFirmaID();
        if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
            || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
          mostrarBotoGlobal = true;
          filterForm.addAdditionalButtonByPK(key, new AdditionalButton("icon-remove",
              "rebutjar", "javascript:rebutjar(" + key + ");", "btn-warning"));
        }
      }

      if (mostrarBotoGlobal) {
        filterForm.addAdditionalButton(new AdditionalButton("icon-remove",
            "rebutjarseleccionats", "javascript:rebutjarseleccionades();", "btn-warning"));

        visibleMultipleSelection = true;
        filterForm.setAttachedAdditionalJspCode(true);

      } else {
        // Esborrar Boto Global
//        AdditionalButton abToDelete = null;
//        for (AdditionalButton ab : filterForm.getAdditionalButtons()) {
//          if ("rebutjarseleccionats".equals(ab.getCodeText())) {
//            abToDelete = ab;
//            break;
//          }
//        }
//
//        if (abToDelete != null) {
//          filterForm.getAdditionalButtons().remove(abToDelete);
//        }

        // Esborrar seleccio multiple

        filterForm.setAttachedAdditionalJspCode(false);
      }

    }

    // Boto Eliminar Petició
    filterForm.setDeleteSelectedButtonVisible(false);
    if (mostrarBotoEsborrar()) {

      boolean mostrarBotoGlobal = false;
      
      List<Integer> estatsEnQueNoEsPotEsborrar = new ArrayList<Integer>();
      if (ferRebuigQuanEsborra()) {
        // En tots els casos mostrar el Boto d'Esborrar
        } else {
        // En Proces i Pausat No mostrar boto d'esborrat
        estatsEnQueNoEsPotEsborrar.add(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES);
        estatsEnQueNoEsPotEsborrar.add(ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT);
      }
      
      Long key;
      int estat;
      for (PeticioDeFirma peticio : list) {
        key = peticio.getPeticioDeFirmaID();
        estat = peticio.getTipusEstatPeticioDeFirmaID();
        
        if (estatsEnQueNoEsPotEsborrar.contains(new Integer(estat))) {
          // NO BOTO ESBORRAR
        } else {
          mostrarBotoGlobal = true;
          
          String js1 = "rebutjarEsborrar(" + key + ");";
          String js2 = "openModal('" + getContextWeb() + "/esborrar/" + key + "','show');";
          
          filterForm.addAdditionalButtonByPK(key, new AdditionalButton(
              "icon-trash icon-white", "genapp.delete",              
              "javascript:" +  (ferRebuigQuanEsborra()?js1:js2),
              "btn-danger"));
        }
      }

      if (mostrarBotoGlobal) {
        
        String js1 = "rebutjarEsborrarSeleccionades();";
        String js2 = "openModalSubmit('" + request.getContextPath() + getContextWeb() + "/esborrarSeleccionades','show', 'peticioDeFirma');";
            
        filterForm.addAdditionalButton(new AdditionalButton("icon-trash icon-white",
            "genapp.delete.selected", 
            "javascript:" + (ferRebuigQuanEsborra()?js1:js2),
            // "javascript:submitTo('peticioDeFirmaFilterForm'," + " '" + request.getContextPath() + getContextWeb() + "/esborrarSeleccionades');",
            "btn-danger"));

        visibleMultipleSelection = true;

      } else {
//        // Esborrar Boto Global
//        AdditionalButton abToDelete = null;
//        for (AdditionalButton ab : filterForm.getAdditionalButtons()) {
//          if ("genapp.delete.selected".equals(ab.getCodeText())) {
//            abToDelete = ab;
//            break;
//          }
//        }
//
//        if (abToDelete != null) {
//          filterForm.getAdditionalButtons().remove(abToDelete);
//        }
      }

    }

    filterForm.setVisibleMultipleSelection(visibleMultipleSelection);

  }

}
