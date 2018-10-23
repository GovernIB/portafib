package es.caib.portafib.back.controller.admin;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.FitxerForm;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/fitxersorfes")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
public class FitxersOrfesController extends FitxerController {
  
  @EJB(mappedName = "portafib/AnnexEJB/local")
  protected es.caib.portafib.ejb.AnnexLocal annexEjb;
  
  @EJB(mappedName = "portafib/ColaboracioDelegacioEJB/local")
  protected es.caib.portafib.ejb.ColaboracioDelegacioLocal colaboracioDelegacioEjb;
  
  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = "portafib/FirmaEJB/local")
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;
  
  @EJB(mappedName = "portafib/AnnexFirmatEJB/local")
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.UsuariPersonaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariPersonaLocal usuariPersonaEjb;

  @Override
  public String getTileList() {
    return "fitxersOrfesList";
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
    return true;
  }

  @Override
  public boolean isActiveFormView() {
    return true;
  }
  
  @Override
  public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      FitxerFilterForm fitxerFilterForm;
      fitxerFilterForm = (FitxerFilterForm)super.getFitxerFilterForm(pagina, mav, request);
      
      if (fitxerFilterForm.isNou()) {
        fitxerFilterForm.setTitleCode("fitxers.orfes");
        fitxerFilterForm.setAddButtonVisible(false);
      }
      
      
      
      return fitxerFilterForm;
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where parent = super.getAdditionalCondition(request);

    Where w1 = FITXERID.notIn(annexEjb.getSubQuery(AnnexFields.FITXERID, null));
    Where w2 = FITXERID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.FITXERAFIRMARID, null));
    Where w3 = FITXERID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.FITXERADAPTATID, null));
    Where w4 = FITXERID.notIn(colaboracioDelegacioEjb.getSubQuery(ColaboracioDelegacioFields.FITXERAUTORITZACIOID, null));
    Where w5 = FITXERID.notIn(firmaEjb.getSubQuery(FirmaFields.FITXERFIRMATID, null));
    Where w6 = FITXERID.notIn(annexFirmatEjb.getSubQuery(AnnexFirmatFields.FITXERID, null));

    Where w7 = FITXERID.notIn(usuariPersonaEjb.getSubQuery(UsuariPersonaFields.RUBRICAID, null));

    Where w8 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.PDFAUTORITZACIODELEGACIOID, null));
    Where w9 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.FAVICONID, null));
    Where w10 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOWEBID, null));
    Where w11 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOWEBPEUID, null));
    Where w12 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOSEGELLID, null));

    return Where.AND(parent, w1, w2 ,w3, w4, w5, w6, w7, w8, w9, w10, w11, w12);
  }
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "FitxersOrfes_FilterForm";
  }
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, FitxerFilterForm filterForm,
      List<Fitxer> list) throws I18NException {
    

    
    Map<Long, File> fitxersFisics = FileSystemManager.getAllFiles();
    
//    File path = FileSystemManager.getFilesPath();
//    List<String> fitxersFisics = new ArrayList<String>(Arrays.asList(path.list(new FilenameFilter() {
//      /**
//       * No volem directoris
//       */
//      @Override
//      public boolean accept(File dir, String name) {
//        return new File(dir, name).isFile();
//      }
//    })));
    
//    fitxersFisics.remove(AutoFirmaController.AUTOFIRMA);
    
    List<Long> fitxersBBDD = fitxerEjb.executeQuery(FITXERID, null, new OrderBy(FITXERID));
    
    // Fitxers que existeixen en BBDD però no fisicament
    for (Long fID : fitxersBBDD) {
      //File f = FileSystemManager.getFile(fID);
      //  fitxersFisics.contains(String.valueOf(fID))
      if (!fitxersFisics.containsKey(fID)) {
        // TODO XYZ ZZZ TRADUIR
        HtmlUtils.saveMessageError(request, "Fitxer amb ID="
            + fID + " existeix en BBDD però no existeix físicament !!!" );
      }
    }

    // Fitxers que existeixen fisicament però no en BBDD
    for (Long fBDID : fitxersBBDD) {
      
      if (fitxersFisics.containsKey(fBDID)) {
        fitxersFisics.remove(fBDID);
      }
    }
    for (Map.Entry<Long, File> fFisic : fitxersFisics.entrySet()) {
      HtmlUtils.saveMessageError(request, "Fitxer Fisic amb id ]"
          + fFisic.getKey() + "[ (" + fFisic.getValue().getAbsolutePath() +") no existeix en la BBDD !!!" );  
    }
    

    
  }
  
 
  
}
