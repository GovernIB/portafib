package es.caib.portafib.back.controller.dele;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.dest.DelegacioDestController;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

@Controller
@RequestMapping(value = "/dele/delegatde")
@SessionAttributes(types = {  ColaboracioDelegacioDestForm.class,ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
public class SomDelegatDeDeleController extends DelegacioDestController 
//extends ColaboracioDelegacioController 
implements ConstantsV2 {
  
  
  @PostConstruct
  public void init() {
    final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
    this.usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);
  
    this.usuariEntitatRefList.setSelects(new Select<?>[] { personaQueryPath.NOM().select,
        personaQueryPath.LLINATGES().select });
  }


  @Override
  public String getTileForm() {
    return (esDelegat()?"delegacions": "colaboracions") + "_Form_" + (esDelegat()?ROLE_DELE:ROLE_COLA);
  }

  

  @Override
  public String getTileList() {
    return (esDelegat()?"delegacions": "colaboracions") + "_List_" + (esDelegat()?ROLE_DELE:ROLE_COLA);
  }



  @Override
  public String getSessionAttributeFilterForm() {
    return "ColaboracioDelegacio" + (esDelegat()?ROLE_DELE:ROLE_COLA) + "_FilterForm";
  }


  @Override
  public boolean esDelegat() {
    return true;
  }
  

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where w =  Where.AND(
        COLABORADORDELEGATID.equal(LoginInfo.getInstance().getUsuariEntitatID()),
        ESDELEGAT.equal(esDelegat())
        );
    
    if (esDelegat()) {
      // No volem delegacions on fitxer encara no està firmat
      return Where.AND(w,
          FITXERAUTORITZACIOID.isNotNull()
          );
    } else {
      return w;
    }
    
  }

  @Override
  public ColaboracioDelegacioFilterForm getColaboracioDelegacioFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      ColaboracioDelegacioFilterForm colaboracioDelegacioFilterForm;
      colaboracioDelegacioFilterForm = super.getColaboracioDelegacioFilterForm(pagina, mav, request);
      if(colaboracioDelegacioFilterForm.isNou()) {

        colaboracioDelegacioFilterForm.addHiddenField(COLABORACIODELEGACIOID);
        colaboracioDelegacioFilterForm.addHiddenField(COLABORADORDELEGATID);
        
        colaboracioDelegacioFilterForm.addHiddenField(ESDELEGAT);
        
        colaboracioDelegacioFilterForm.addHiddenField(MOTIUDESHABILITADA);
        colaboracioDelegacioFilterForm.addHiddenField(DESCRIPCIO);

        colaboracioDelegacioFilterForm.addHiddenField(FITXERAUTORITZACIOID);

        // TODO Tiquet #113 
        if (esDelegat()) {
          colaboracioDelegacioFilterForm.addHiddenField(REVISOR);
        }
        
        colaboracioDelegacioFilterForm.setVisibleMultipleSelection(false);

        // Ocultam botó creació i borrat
        colaboracioDelegacioFilterForm.setDeleteButtonVisible(false);
        colaboracioDelegacioFilterForm.setDeleteSelectedButtonVisible(false);
        colaboracioDelegacioFilterForm.setAddButtonVisible(false);
        colaboracioDelegacioFilterForm.setEditButtonVisible(false);
        
        colaboracioDelegacioFilterForm.addLabel(DESTINATARIID, esDelegat()?"delegatde.menu":"colaboradorde.menu");
        
        
        colaboracioDelegacioFilterForm.addAdditionalButtonForEachItem(
            new AdditionalButton("icon-eye-open","genapp.viewtitle", getContextWeb() + "/view/{0}","btn-info"));

      } 
      
      return colaboracioDelegacioFilterForm;
    }
  
  
  @Override
  public ColaboracioDelegacioForm getColaboracioDelegacioForm(ColaboracioDelegacioJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
   
    
    //ColaboracioDelegacioForm colaboracioDelegacioForm =super.getColaboracioDelegacioForm(_jpa, __isView, request, mav);
   
   ColaboracioDelegacioForm cdoriginal = super.getColaboracioDelegacioForm(_jpa, __isView, request, mav);
   ColaboracioDelegacioDestForm colaboracioDelegacioForm;
   if (cdoriginal instanceof ColaboracioDelegacioDestForm) {
     colaboracioDelegacioForm = (ColaboracioDelegacioDestForm)cdoriginal;
   } else {
     colaboracioDelegacioForm = new ColaboracioDelegacioDestForm(cdoriginal);
   }
   
   
   // fer enteja del pare
   request.getSession().removeAttribute(HtmlUtils.MISSATGES);
   colaboracioDelegacioForm.getAdditionalButtons().clear();
   colaboracioDelegacioForm.setTitleCode(null);

   colaboracioDelegacioForm.setAttachedAdditionalJspCode(true);
   
   if (esDelegat()) {
     colaboracioDelegacioForm.addHiddenField(REVISOR);
   } else {
     colaboracioDelegacioForm.addHiddenField(FITXERAUTORITZACIOID);
   
   }

   if (_jpa.isActiva()) {
     colaboracioDelegacioForm.addHiddenField(MOTIUDESHABILITADA);
   }
   
   colaboracioDelegacioForm.addLabel(DESTINATARIID, esDelegat()?"delegatde.menu":"colaboradorde.menu");

   colaboracioDelegacioForm.addHiddenField(COLABORACIODELEGACIOID);
   colaboracioDelegacioForm.addHiddenField(COLABORADORDELEGATID);
   
   colaboracioDelegacioForm.addHiddenField(ESDELEGAT);
   
   final boolean isDebug = log.isDebugEnabled();
   
   Map<Long, String> allTipusDocumentInfo;
   allTipusDocumentInfo = getAllTipusDocumentInfo(isDebug);
   
   List<Long> currentTipusDocument = getCurrentTipusDocument(
       colaboracioDelegacioForm.getColaboracioDelegacio(), allTipusDocumentInfo, isDebug);
   

   colaboracioDelegacioForm.setAllTipusDocumentInfo(allTipusDocumentInfo);
   colaboracioDelegacioForm.setCurrentTipusDocument(currentTipusDocument);
   colaboracioDelegacioForm.setTipus(currentTipusDocument.isEmpty() ? 1 : 2);
   
   
   
   colaboracioDelegacioForm.setCancelButtonVisible(false);
   
   
   colaboracioDelegacioForm.addAdditionalButton(
       new AdditionalButton("", "tornar", getContextWeb() + "/list", "btn-primary")
       );
   
   
   return colaboracioDelegacioForm;
 }
  
  
  
  @Override
  public String getEntityNameCode() {
    return esDelegat()?"delegatde":"colaboradorde";
  }

  @Override
  public String getEntityNameCodePlural() {
    return getEntityNameCode() + ".plural";
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
    return true;
  }

  

}
