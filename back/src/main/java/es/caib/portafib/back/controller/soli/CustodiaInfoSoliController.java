package es.caib.portafib.back.controller.soli;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.CustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.reflist.PosicioPaginaTraduibleRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.validator.CustodiaInfoLogicValidator;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = CustodiaInfoSoliController.SOLI_CUSTODIA_CONTEXT)
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
public class CustodiaInfoSoliController extends CustodiaInfoController {

  public static final String SOLI_CUSTODIA_CONTEXT = "/soli/peticio/custodiainfo";
  
  protected CustodiaInfoLogicValidator<Object> validator = new CustodiaInfoLogicValidator<Object>();

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @Override
  public boolean isActiveList() {
    return true;
  }

  @Override
  public boolean isActiveFormNew() {
    return true;
  }

  @PostConstruct
  public void init() {
    // Traduccions especials per Tipus de Documents
    this.posicioPaginaRefList = new PosicioPaginaTraduibleRefList(this.posicioPaginaRefList);
    
    // Configura com es mostra l'usuari aplicació
    this.usuariAplicacioRefList = new UsuariAplicacioRefList(
        usuariAplicacioRefList);
    usuariAplicacioRefList
        .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    usuariAplicacioRefList.setSeparator("");
  }

  @Override
  public String getRedirectWhenModified(CustodiaInfoForm custodiaInfoForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getPeticioDeFirmaContext() + "/list";
    } else {
      return getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(java.lang.Long custodiaInfoID, Throwable __e) {
    return "redirect:" + getPeticioDeFirmaContext() + "/list";
  }

  @Override
  public String getRedirectWhenCancel(java.lang.Long custodiaInfoID) {
    return "redirect:" + getPeticioDeFirmaContext() + "/list";
  }

  protected String getPeticioDeFirmaContext() {
    if (isUsuariEntitat()) {
      return Constants.CONTEXT_SOLI_PETICIOFIRMA;
    } else {
      return Constants.CONTEXT_ADEN_PETICIOFIRMA;
    }

  }

  @Override
  public CustodiaInfoForm getCustodiaInfoForm(CustodiaInfoJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    // Sempre serà edit o view
    CustodiaInfoForm custodiaInfoForm = super
        .getCustodiaInfoForm(_jpa, __isView, request, mav);

    CustodiaInfoJPA custodia = custodiaInfoForm.getCustodiaInfo();

    if (custodia.getCustodiaPluginID() == null) {
      custodiaInfoForm.addHiddenField(CUSTODIAPLUGINID);
    }
    if (custodia.getUrlFitxerCustodiat() == null) {
      custodiaInfoForm.addHiddenField(URLFITXERCUSTODIAT);
    }
    if (custodia.isEditable()) {
      custodiaInfoForm.addHiddenField(EDITABLE);
    } else {
      custodiaInfoForm.addReadOnlyField(EDITABLE);
      custodiaInfoForm.setSaveButtonVisible(false);
    }

    custodiaInfoForm.addHelpToField(MISSATGE,
        I18NUtils.tradueix("custodiainfo.missatge.ajuda"));
    custodiaInfoForm.addHelpToField(PAGINES, I18NUtils.tradueix("custodiainfo.pagines.ajuda"));
    custodiaInfoForm.addHelpToField(CODIBARRESTEXT, I18NUtils.tradueix("custodiainfo.codibarrestext.ajuda"));

    custodiaInfoForm.addHiddenField(ENTITATID);
    custodiaInfoForm.addHiddenField(NOMPLANTILLA);
    custodiaInfoForm.addHiddenField(USUARIAPLICACIOID);
    custodiaInfoForm.addHiddenField(USUARIENTITATID);
    custodiaInfoForm.addHiddenField(CODIBARRESPOSICIOPAGINAID);

    if (custodiaInfoForm.getCustodiaInfo().isEditable()) {
      custodiaInfoForm.addReadOnlyField(CUSTODIAPLUGINCLASSID);
    } else {
      custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
    }

    if (__isView) {
      
      Long existeixPeticio = peticioDeFirmaLogicaEjb.count(Where.AND(
             PeticioDeFirmaFields.CUSTODIAINFOID.equal(custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID()),
             PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT)
          ));
      
      if (existeixPeticio == 0) {
        // Segur retornar a llista de custodia
        custodiaInfoForm.setCancelButtonVisible(false);
        custodiaInfoForm.addAdditionalButton(new AdditionalButton("",
            "tornar",getContextWeb() +"/list", ""));
      } else {
        // Retornar a la pàgina de peticions de firma    
      }
      
    } else {
      custodiaInfoForm.addHiddenField(TITOLPETICIO);
      custodiaInfoForm.addHiddenField(DATACUSTODIA);
    }

    return custodiaInfoForm;
  }

  /**
   * 
   * @return
   */
  public boolean isUsuariEntitat() {
    return true;
  }

  @Override
  public String getTileForm() {
    return "custodiaInfoForm_" + (isUsuariEntitat() ? "soli" : "aden");
  }

  @Override
  public void delete(HttpServletRequest request, CustodiaInfo custodiaInfo) throws Exception,
      I18NException {
    peticioDeFirmaLogicaEjb.deleteCustodiaInfoOfPeticioDeFirma(custodiaInfo);
  }

  @Override
  public String getTileList() {
    return "custodiaInfoList_" + (isUsuariEntitat() ? "soli" : "aden");
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "CustodiaInfo_FilterForm_" + (isUsuariEntitat() ? "soli" : "aden");
  }

  @Override
  public CustodiaInfoFilterForm getCustodiaInfoFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    CustodiaInfoFilterForm filterForm = super.getCustodiaInfoFilterForm(pagina, mav, request);

    if (filterForm.isNou()) {
      List<Field<?>> hiddenFields = filterForm.getHiddenFields();
      hiddenFields.addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));

      //hiddenFields.remove(CUSTODIAINFOID);
      hiddenFields.remove(CUSTODIAPLUGINID);

      if (!isUsuariEntitat()) {
        hiddenFields.remove(USUARIAPLICACIOID);
      }

      hiddenFields.remove(TITOLPETICIO);
      hiddenFields.remove(DATACUSTODIA);

      filterForm.addLabel(CUSTODIAPLUGINID, "custodiaInfo.custodiaInfo");
      
      filterForm.setAddButtonVisible(false);
      
      filterForm.setDeleteButtonVisible(false);
      
      filterForm.setEditButtonVisible(false);
      
      filterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-eye-open",
          "vistacompleta", getContextWeb() + "/view/{0}" ,"btn-info" ));

      // TODO AGRUPAR i LLISTAT
      filterForm.setDeleteSelectedButtonVisible(false);
      filterForm.setVisibleMultipleSelection(false);

    }

    return filterForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    
    // Només les finalitzades o les que ja no tenen petició
    Where wFinished = Where.OR(
      // Només les finalitzades
      CUSTODIAINFOID.in(
        peticioDeFirmaLogicaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID,
            PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT))),
      // Les que ja no tenen peticio de firma
      CUSTODIAINFOID.notIn(peticioDeFirmaLogicaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID, PeticioDeFirmaFields.CUSTODIAINFOID.isNotNull()))
    );
    
    // Per usuaris entitat o usuaris aplicacio
    Where wUser;
    if (isUsuariEntitat()) {
      wUser = Where.AND(
          USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID()),
          USUARIAPLICACIOID.isNull()        
          );
    } else {
      wUser = Where.AND(
          USUARIENTITATID.isNull(),
          USUARIAPLICACIOID.isNotNull()          
          );
    }
    
    // No volem plantilles
    Where wCommon = Where.AND(
        ENTITATID.isNull(),
        NOMPLANTILLA.isNull(),
        CUSTODIAPLUGINID.isNotNull(),
        CUSTODIAPLUGINCLASSID.isNotNull()
        );
    
    return Where.AND(wUser, wCommon, wFinished);
  }

  @Override
  @InitBinder("custodiaInfoForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinderForm(binder);
    getWebValidator().setValidator(validator);
  }

}
