package es.caib.portafib.back.controller.soli;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.aden.PlantillaCustodiaAdenController;
import es.caib.portafib.back.controller.webdb.CustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.validator.CustodiaInfoLogicValidator;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;

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

  @EJB(mappedName = PeticioDeFirmaLocal.JNDI_NAME)
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;
  
  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;
  

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

    // Configura com es mostra l'usuari aplicació
    this.usuariAplicacioRefList = new UsuariAplicacioRefList(
        usuariAplicacioRefList);
    usuariAplicacioRefList
        .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    usuariAplicacioRefList.setSeparator("");
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm, Throwable __e) {
    if (__e == null) {
      return getRedirectWhenCancel(request, custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID());
    } else {
      return getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(HttpServletRequest request,java.lang.Long custodiaInfoID, Throwable __e) {
    return getRedirectWhenCancel(request, custodiaInfoID);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long custodiaInfoID) {
    String redirectOnCustody = (String)request.getSession().getAttribute("redirectOnCustody");
    if (redirectOnCustody == null) {
      log.warn("redirectOnCustody == NULL");
      return "redirect:" + getPeticioDeFirmaContext() + "/list";
    } else {
      request.getSession().removeAttribute("redirectOnCustody");
      return "redirect:" + redirectOnCustody;
    }
    
  }
  
  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm) {
    return getRedirectWhenCancel(request, custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID());
  }


  protected String getPeticioDeFirmaContext() {
    if (isUsuariEntitat()) {
      return ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA;
    } else {
      return ConstantsV2.CONTEXT_ADEN_PETICIOFIRMA;
    }
  }

  @Override
  public CustodiaInfoForm getCustodiaInfoForm(CustodiaInfoJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    
    String redirectOnModify = request.getParameter("redirectOnCustody");
    if (redirectOnModify != null) {
      request.getSession().setAttribute("redirectOnCustody", redirectOnModify);
    }

    // Sempre serà edit o view
    CustodiaInfoForm custodiaInfoForm = super
        .getCustodiaInfoForm(_jpa, __isView, request, mav);

    CustodiaInfoJPA custodia = custodiaInfoForm.getCustodiaInfo();

    if (custodia.getCustodiaDocumentID() == null) {
      custodiaInfoForm.addHiddenField(CUSTODIADOCUMENTID);
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

    PlantillaCustodiaAdenController.addHelp(custodiaInfoForm);

    custodiaInfoForm.addHiddenField(ENTITATID);
    custodiaInfoForm.addHiddenField(NOMPLANTILLA);
    custodiaInfoForm.addHiddenField(USUARIAPLICACIOID);
    custodiaInfoForm.addHiddenField(USUARIENTITATID);
    custodiaInfoForm.addHiddenField(CODIBARRESPOSICIOPAGINAID);

    
    PeticioDeFirma peticioDeFirma;
    {
       List<PeticioDeFirma> list;
       list = peticioDeFirmaEjb.select(PeticioDeFirmaFields.CUSTODIAINFOID.equal(custodia.getCustodiaInfoID()));
       
       if (list == null || list.size() == 0) {
         peticioDeFirma = null;
       } else {
         peticioDeFirma = list.get(0);
       }
    }
    
    if (peticioDeFirma == null) {
      // És una Plantilla
    } else {
      
      if (custodiaInfoForm.getCustodiaInfo().isEditable()) {
        custodiaInfoForm.addReadOnlyField(PLUGINID);
      } else {
        custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
      }
      
      EntitatJPA entitatJPA = LoginInfo.getInstance().getEntitat();
      
      Integer politicaDeCustodia;      
      if (peticioDeFirma.getSolicitantUsuariEntitat1ID() == null) {
        // Usuari Aplicació
        politicaDeCustodia = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUA(peticioDeFirma.getSolicitantUsuariAplicacioID(), entitatJPA);
      } else {
        // Usuari Entitat
        politicaDeCustodia = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUE(peticioDeFirma.getSolicitantUsuariEntitat1ID(), entitatJPA);
      }
      
      log.info("XYZ ZZZ getCustodiaInfoForm():: politicaDeFirma => " + politicaDeCustodia);
      
      if (politicaDeCustodia == null) {
        // No Permetre
        custodiaInfoForm.setDeleteButtonVisible(true);
      } else {
        
        switch((int)politicaDeCustodia) {
          
          // Obligatori Plantilla definida en Entitat, Usuari-Entitat  o Usuari-Aplicació.
          case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: // = 2;
            custodiaInfoForm.setDeleteButtonVisible(false);
            custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
          break;

          // Només Plantilles de l´Entitat (No editables)
          case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT: // = 1;
              // XYZ ZZZ S'hauria de poder permetre canviar de
            // XYZ ZZZ TRA
            HtmlUtils.saveMessageWarning(request, "Aquesta politica només permet alguna de les plantilles de l'entitat. Encara no s'ha desenvolupat codi per canviar Politica de Custòdia per una altre ");
            custodiaInfoForm.setDeleteButtonVisible(false);
          break;

          // [ENTITAT] Opcional plantilla Entitat (Per defecte Actiu)
          case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU: //= 3;
          
          // [ENTITAT] Opcional plantilla Entitat (Per defecte NO Actiu)
          case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU: // = 4;
            
            log.info("XYZ ZZZ posa tot a ReadOnly ...");
            custodiaInfoForm.getReadOnlyFields().addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));
          
          // Llibertat Total (selecció, edició i us)
          case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL: // = 5;
            custodiaInfoForm.setDeleteButtonVisible(true);
          break;
          
          default:

        }

      }

    }




    if (__isView) {

//      Long existeixPeticio = peticioDeFirmaLogicaEjb.count(Where.AND(
//             PeticioDeFirmaFields.CUSTODIAINFOID.equal(custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID()),
//             PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT)
//          ));
      
      if (peticioDeFirma == null) {
        // Segur retornar a llista de custodia
        custodiaInfoForm.setCancelButtonVisible(false);
        String r;
        r = getRedirectWhenCancel(request, custodiaInfoForm.getCustodiaInfo().getCustodiaInfoID());
        custodiaInfoForm.addAdditionalButton(new AdditionalButton("", "tornar", 
            r.replace("redirect:","") , ""));
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
    custodiaInfoLogicaEjb.deleteCustodiaInfoOfPeticioDeFirma(custodiaInfo);
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
      Set<Field<?>> hiddenFields = filterForm.getHiddenFields();
      hiddenFields.addAll(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));

      //hiddenFields.remove(CUSTODIAINFOID);
      hiddenFields.remove(CUSTODIADOCUMENTID);

      if (!isUsuariEntitat()) {
        hiddenFields.remove(USUARIAPLICACIOID);
      }
      
      filterForm.setDefaultOrderBy(
          new OrderBy[] {new OrderBy(DATACUSTODIA, OrderType.DESC)});

      hiddenFields.remove(TITOLPETICIO);
      hiddenFields.remove(DATACUSTODIA);

      filterForm.addLabel(CUSTODIADOCUMENTID, "custodiaInfo.custodiaInfo");
      
      filterForm.setAddButtonVisible(false);
      
      filterForm.setDeleteButtonVisible(false);
      
      filterForm.setEditButtonVisible(false);
      
      filterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-eye-open icon-white",
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
        peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID,
            PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT))),
      // Les que ja no tenen peticio de firma
      CUSTODIAINFOID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.CUSTODIAINFOID, PeticioDeFirmaFields.CUSTODIAINFOID.isNotNull()))
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
        CUSTODIADOCUMENTID.isNotNull(),
        PLUGINID.isNotNull() 
        );
    
    return Where.AND(wUser, wCommon, wFinished);
  }

  @Override
  @InitBinder("custodiaInfoForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinderForm(binder);
    getWebValidator().setValidator(validator);
  }
  
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, 
    CustodiaInfoFilterForm filterForm,  List<CustodiaInfo> list)
    throws I18NException {

    
    // Mostrar boto per editar usuaris que poden veure les meves plantilles
   
    filterForm.getAdditionalButtonsByPK().clear();

    for (CustodiaInfo cust : list) {
      String url = cust.getUrlFitxerCustodiat();
      if (url != null && url.trim().length() != 0 )
       filterForm.addAdditionalButtonByPK(cust.getCustodiaInfoID(),
           new AdditionalButton("icon-download-alt  icon-white",
           "descarregar", url, "btn-success"));
    }

  }
  
  
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    return internalReferenceListForPosicioPagina();
  }
  
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   return internalReferenceListForPosicioPagina();
  }

  public static List<StringKeyValue> internalReferenceListForPosicioPagina() {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
     for (int i = 0; i < 5; i++) {
       __tmp.add(new StringKeyValue(String.valueOf(i) , I18NUtils.tradueix("posiciopagina." + i)));
     }
     return __tmp;
  }

}
