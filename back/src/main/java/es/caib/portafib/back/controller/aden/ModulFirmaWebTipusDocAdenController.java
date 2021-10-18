package es.caib.portafib.back.controller.aden;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.ModulDeFirmaPerTipusDeDocumentController;
import es.caib.portafib.back.form.webdb.ModulDeFirmaPerTipusDeDocumentFilterForm;
import es.caib.portafib.back.form.webdb.ModulDeFirmaPerTipusDeDocumentForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/modulfirmatipusdoc")
@SessionAttributes(types = { ModulDeFirmaPerTipusDeDocumentForm.class,
    ModulDeFirmaPerTipusDeDocumentFilterForm.class })
public class ModulFirmaWebTipusDocAdenController extends
    ModulDeFirmaPerTipusDeDocumentController {

  @Override
  public String getTileForm() {
    return "modulDeFirmaPerTipusDeDocumentFormAden";
  }

  @Override
  public String getTileList() {
    return "modulDeFirmaPerTipusDeDocumentListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "ModulDeFirmaPerTipusDeDocumentAden_FilterForm";
  }

  @Override
  public ModulDeFirmaPerTipusDeDocumentFilterForm getModulDeFirmaPerTipusDeDocumentFilterForm(
      Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {
    ModulDeFirmaPerTipusDeDocumentFilterForm modulDeFirmaPerTipusDeDocumentFilterForm;
    modulDeFirmaPerTipusDeDocumentFilterForm = super
        .getModulDeFirmaPerTipusDeDocumentFilterForm(pagina, mav, request);
    if (modulDeFirmaPerTipusDeDocumentFilterForm.isNou()) {
      modulDeFirmaPerTipusDeDocumentFilterForm.addHiddenField(ID);
      
      modulDeFirmaPerTipusDeDocumentFilterForm.setAddButtonVisible(false);
      
      modulDeFirmaPerTipusDeDocumentFilterForm.addAdditionalButton(
          new AdditionalButton("icon-plus-sign", "modulfirmatipusdoc.novaentrada", getContextWeb() + "/new", ""));

    }

    return modulDeFirmaPerTipusDeDocumentFilterForm;
  }

  @Override
  public ModulDeFirmaPerTipusDeDocumentForm getModulDeFirmaPerTipusDeDocumentForm(
      ModulDeFirmaPerTipusDeDocumentJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {
    ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm;
    modulDeFirmaPerTipusDeDocumentForm = super.getModulDeFirmaPerTipusDeDocumentForm(_jpa,
        __isView, request, mav);
    if (modulDeFirmaPerTipusDeDocumentForm.isNou()) {
      
      
      
      
    }

    return modulDeFirmaPerTipusDeDocumentForm;

  }
  
  @Override
  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    
    
    
    Where w = Where.AND(where,
        PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB),
        PluginFields.ACTIU.equal(true));
   return super.getReferenceListForPluginID(request, mav, w );
 }
  
  @Override
  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    
    String usrAppID = LoginInfo.getInstance().getEntitat().getUsuariAplicacioID();
    
    Where w =  Where.AND(where,
        Where.OR(
          TipusDocumentFields.USUARIAPLICACIOID.equal(usrAppID),
          TipusDocumentFields.USUARIAPLICACIOID.isNull()
        )
       );
   return super.getReferenceListForTipusDocumentID(request, mav,w) ;
 }
  
  
  
}
