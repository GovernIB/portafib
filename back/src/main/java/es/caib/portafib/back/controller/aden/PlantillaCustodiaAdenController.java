package es.caib.portafib.back.controller.aden;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.soli.CustodiaInfoSoliController;
import es.caib.portafib.back.controller.webdb.CustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/plantillacustodia")
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
public class PlantillaCustodiaAdenController extends CustodiaInfoController {
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @Override
  public String getTileForm() {
    return "plantillaCustodiaInfoFormAden";
  }

  @Override
  public String getTileList() {
    return "plantillaCustodiaInfoListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PlantillaCustodiaInfo_FilterForm";
  }
  
  
  @Override
  public CustodiaInfoFilterForm getCustodiaInfoFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      CustodiaInfoFilterForm custodiaInfoFilterForm = super.getCustodiaInfoFilterForm(pagina, mav, request);

      if(custodiaInfoFilterForm.isNou()) {
        
        List<Field<?>> groupby = new ArrayList<Field<?>>();
                
        groupby.add(MISSATGEPOSICIOPAGINAID);
        groupby.add(DATACUSTODIA);
        
        custodiaInfoFilterForm.setGroupByFields(groupby);
        
        
        List<Field<?>> filterby = new ArrayList<Field<?>>();
        filterby.add(NOMPLANTILLA);
        filterby.add(DATACUSTODIA);
        
        custodiaInfoFilterForm.setFilterByFields(filterby);


        HashSet<Field<?>> ocults = new HashSet<Field<?>>(Arrays.asList(ALL_CUSTODIAINFO_FIELDS));

        ocults.remove(NOMPLANTILLA);
        ocults.remove(DATACUSTODIA);

        custodiaInfoFilterForm.addLabel(DATACUSTODIA, "plantillacustodia.data");

        custodiaInfoFilterForm.setHiddenFields(ocults);

      }
      return custodiaInfoFilterForm;
  }
  
  
  
  

  @Override
  public CustodiaInfoForm getCustodiaInfoForm(CustodiaInfoJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    CustodiaInfoForm custodiaInfoForm = super
        .getCustodiaInfoForm(_jpa, __isView, request, mav);
    if (custodiaInfoForm.isNou()) {

      CustodiaInfoJPA custodiaInfo = custodiaInfoForm.getCustodiaInfo();
      // Valors per defecte

      custodiaInfo.setEntitatID(LoginInfo.getInstance().getEntitatID());
      custodiaInfo.setEditable(true);

      custodiaInfo.setPagines("*");
      custodiaInfo.setCodiBarresPosicioPaginaID(ConstantsV2.POSICIO_PAGINA_ESQUERRA);
      custodiaInfo.setCodiBarresText("{0}");
      custodiaInfo.setCodiBarresID(ConstantsV2.BARCODE_PDF417_PLUGIN);

      custodiaInfo.setMissatgePosicioPaginaID(ConstantsV2.POSICIO_PAGINA_ESQUERRA);

      Properties prop = new Properties();
      prop.put("ca", "Document custodiat amb el sistema {2}. Identificador {1}. Data:{3} URL de validació:{0}. Valor especial: {4}");
      prop.put("es", "Documento custodiado con el sistema {2}. Identificador = {1}. URL de validación = {0}. Fecha Custodia:{3}. Valor especial: {4}");
      
      StringWriter sw = new StringWriter();
      try {
        prop.store(sw, "");
      } catch (IOException e) {
         e.printStackTrace();
      }
      custodiaInfo.setMissatge(sw.toString());

      custodiaInfo.setCustodiar(true);

      custodiaInfo.setDataCustodia(new Timestamp(new Date().getTime()));

      //String custodiaPluginClass = custodiaPlugin.getClass().getCanonicalName();
      //custodiaInfo.setCustodiaPluginClassID(custodiaPluginClass);

    }
    
    addHelp(custodiaInfoForm);

    custodiaInfoForm.addHiddenField(USUARIAPLICACIOID);
    custodiaInfoForm.addHiddenField(USUARIENTITATID);
    custodiaInfoForm.addHiddenField(CUSTODIADOCUMENTID);
    custodiaInfoForm.addHiddenField(ENTITATID);
    custodiaInfoForm.addHiddenField(URLFITXERCUSTODIAT);
    custodiaInfoForm.addHiddenField(TITOLPETICIO);
    custodiaInfoForm.addHiddenField(CUSTODIAR);
    custodiaInfoForm.addHiddenField(CODIBARRESPOSICIOPAGINAID);
    
    custodiaInfoForm.addReadOnlyField(DATACUSTODIA);
    

    return custodiaInfoForm;

  }

  public static void addHelp(CustodiaInfoForm custodiaInfoForm) {
    custodiaInfoForm.addHelpToField(MISSATGE,
        I18NUtils.tradueix("custodiainfo.missatge.ajuda"));
    custodiaInfoForm.addHelpToField(PAGINES,
        I18NUtils.tradueix("custodiainfo.pagines.ajuda"));
    custodiaInfoForm.addHelpToField(CODIBARRESTEXT,
        I18NUtils.tradueix("custodiainfo.codibarrestext.ajuda"));
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return Where.AND(
        ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        NOMPLANTILLA.isNotNull()
        );
  }
  
  

  
  
  
  @Override
  public void postValidate(HttpServletRequest request,CustodiaInfoForm custodiaInfoForm, BindingResult result)  throws I18NException {
    
    // TODO Check plugin existeix 
    
    // Check NomPlantilla s'ha definit
    CustodiaInfo jpa = custodiaInfoForm.getCustodiaInfo();
    
    String nom = jpa.getNomPlantilla();
    if (nom == null || nom.trim().length() == 0) {
      result.rejectValue(get(NOMPLANTILLA),
          "genapp.validation.required", new String[] { I18NUtils
              .tradueix(get(NOMPLANTILLA)) }, null);
    }
    
    jpa.setCodiBarresPosicioPaginaID(jpa.getMissatgePosicioPaginaID());
    
    // Missatge ha de tenir traduccions 
    String str = jpa.getMissatge();
    if (str != null) {
      
      Properties prop = new Properties();
      
      try {
        prop.load(new StringReader(str));
      } catch (IOException e) {        
        e.printStackTrace();
      }
      
      List<String> idiomes = idiomaEjb.executeQuery(IdiomaFields.IDIOMAID, IdiomaFields.SUPORTAT.equal(true));
      for (String lang : idiomes) {
        if (prop.getProperty(lang) == null) {
          result.rejectValue(get(MISSATGE),
              "custodiainfo.missatge.traduccio", new String[] { lang }, null);
        }
      }
      
      
    }

    
//    String pagines = custodiaInfoForm.getCustodiaInfo().getPagines(); 
//    
//    if ("buit".equals(pagines)) {
//      custodiaInfoForm.getCustodiaInfo().setPagines("");
//    }
    

  }
  
  @Override
  public String getEntityNameCode() {
    return "plantillacustodia";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "plantillacustodia.plural";
  }
  
  @Override
  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
     return pluginRefList.getReferenceList(PluginFields.PLUGINID, 
         Where.AND(where,
             Where.OR(
               PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
               PluginFields.ENTITATID.isNull()
               ),
             PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_CUSTODIA)
             ));
  }

  
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    return CustodiaInfoSoliController.internalReferenceListForPosicioPagina();
  }
  
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   return CustodiaInfoSoliController.internalReferenceListForPosicioPagina();
  }
  
  
  
  
}
