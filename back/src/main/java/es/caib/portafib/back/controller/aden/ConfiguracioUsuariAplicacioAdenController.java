package es.caib.portafib.back.controller.aden;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaController;
import es.caib.portafib.back.controller.admin.GestioEntitatController;
import es.caib.portafib.back.controller.webdb.UsuariAplicacioConfiguracioController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * https://github.com/GovernIB/portafib/issues/148
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConfiguracioUsuariAplicacioAdenController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariAplicacioConfiguracioForm.class,
    UsuariAplicacioConfiguracioFilterForm.class })
public class ConfiguracioUsuariAplicacioAdenController extends
    UsuariAplicacioConfiguracioController {

  public static final String CONTEXT_WEB = "/aden/usrappconfig";

  public static final String SESSION_USUARIAPLICACIOID = "SESSION_USUARIAPLICACIOID_EN_CONFIG";

  @Override
  public String getTileForm() {
    return "usuariAplicacioConfiguracioFormAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "UsuariAplicacioConfiguracioADEN_FilterForm";
  }

  @PostConstruct
  public void init() {
    UsuariAplicacioRefList uaRefList = new UsuariAplicacioRefList(this.usuariAplicacioRefList);
    uaRefList.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    this.usuariAplicacioRefList = uaRefList;
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public UsuariAplicacioConfiguracioForm getUsuariAplicacioConfiguracioForm(
      UsuariAplicacioConfiguracioJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {

    UsuariAplicacioConfiguracioForm form = super.getUsuariAplicacioConfiguracioForm(_jpa,
        __isView, request, mav);

    if (form.isNou()) {
      // Creació
      String usuariAplicacioID = (String) request.getSession().getAttribute(
          SESSION_USUARIAPLICACIOID);
      if (usuariAplicacioID == null) {

        log.warn("S'ha cridat a crear configuració d'Aplicació però no s'ha guardat el USUARIAPLICACIOID dins la sessió.");

        mav.setView(new RedirectView(
            GestioUsuariAplicacioAdenController.GESTIO_USUARI_APLICACIO_CONTEXTWEB + "/list",
            true));
        return form;
      }

      UsuariAplicacioConfiguracio uac = form.getUsuariAplicacioConfiguracio();
      uac.setUsuariAplicacioID(usuariAplicacioID);

      uac.setTipusOperacioFirma(ConstantsV2.TIPUS_OPERACIO_FIRMA_FIRMAR);

      uac.setComprovarNifFirma(false);
      uac.setCheckCanviatDocFirmat(false);
      uac.setValidarCertificat(false);
      uac.setValidarFirma(false);

      // XYZ ZZZ Falta valors per politiques de custodia, taula i segell de temps !!!!
      // XYZ ZZZ ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET
      
      uac.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);
      uac.setPoliticaSegellatDeTemps(ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR);
      uac.setPosicioTaulaFirmesID(ConstantsV2.TAULADEFIRMES_SENSETAULA);
      
      
      uac.setPluginFirmaServidorID(null);
      uac.setMaxFirmesEnServidor(5);
      

    }

    // Codi comu
    form.addHelpToField(FILTRECERTIFICATS,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(MOTIUDELEGACIOID,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(FIRMATPERFORMATID,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(HTMLPERLLISTARPLUGINSFIRMAWEB,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));

    form.addReadOnlyField(TIPUSOPERACIOFIRMA);
    form.addReadOnlyField(USUARIAPLICACIOID);

    // XYZ ZZZ Es quedaran així fins que no s'implementi #165
    // form.addReadOnlyField(POLITICACUSTODIA);

    // XYZ ZZZ Pendent de Implementar
    form.addReadOnlyField(LOGINCERTIFICATEID);

    // XYZ ZZZ Pendent de Implementar
    form.addReadOnlyField(CHECKCANVIATDOCFIRMAT);
    form.addReadOnlyField(COMPROVARNIFFIRMA);
    form.addReadOnlyField(VALIDARCERTIFICAT);
    //form.addReadOnlyField(VALIDARFIRMA);
    
    // XYZ ZZZ Pendent de Implementar
    form.addReadOnlyField(HTMLPERLLISTARPLUGINSFIRMAWEB);

    // Pentent de que s'implementi XYZ ZZZ
    // #176 Configuració etiquetes de la Taula de Firmes 
    form.addReadOnlyField(PROPIETATSTAULAFIRMES);

    if (!__isView) {
      form.setAttachedAdditionalJspCode(true);
    }

    return form;

  }

  @Override
  public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    final boolean isEntitat = false;
    return GestioEntitatController.staticGetReferenceListForUsPoliticaDeFirma(isEntitat);
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {

    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_OPERACIO_FIRMA_FIRMAR, I18NUtils
        .tradueix("usuariaplicacioconfig.operaciofirma."
            + ConstantsV2.TIPUS_OPERACIO_FIRMA_FIRMAR)));
    __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_OPERACIO_FIRMA_COFIRMAR, I18NUtils
        .tradueix("usuariaplicacioconfig.operaciofirma."
            + +ConstantsV2.TIPUS_OPERACIO_FIRMA_COFIRMAR)));
    __tmp.add(new StringKeyValue("" + ConstantsV2.TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR, I18NUtils
        .tradueix("usuariaplicacioconfig.operaciofirma."
            + +ConstantsV2.TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR)));
    return __tmp;
  }

  @Override
  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    return GestioEntitatController.staticGetReferenceListForPosicioTaulaFirmes();
  }

  @Override
  public List<StringKeyValue> getReferenceListForPluginFirmaServidorID(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {

    final Where w = Where.AND(where,
        PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR),
        PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));

    List<StringKeyValue> list = pluginRefList.getReferenceList(PluginFields.PLUGINID, w);

    return list;
  }

  @Override
  public List<StringKeyValue> getReferenceListForPluginSegellatID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    Where where2 = Where.AND(where,
        PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitat().getEntitatID()),
        PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS));

    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where2);
  }

  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {

    final boolean isEntitat = false;
    return GestioEntitatController.staticGetReferenceListForPoliticaCustodia(isEntitat);
  }

  /**
   * #166
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {

    boolean isEntitat = false;
    return GestioEntitatController.staticGetReferenceListForPoliticaTaulaFirmes(isEntitat);
  }

  /**
   * #148
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    final boolean isEntitat = false;
    return GestioEntitatController.staticGetReferenceListForPoliticaSegellatDeTemps(isEntitat);
  }

  @Override
  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
      ModelAndView mav, UsuariAplicacioConfiguracioForm configuracioForm, Where where)
      throws I18NException {

    Where where2 = Where.AND(where,
        CustodiaInfoFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        CustodiaInfoFields.NOMPLANTILLA.isNotNull());

    return super.getReferenceListForCustodiaInfoID(request, mav, configuracioForm, where2);
  }
  
  @Override
  public void postValidate(HttpServletRequest request,
      UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, BindingResult result)
          throws I18NException {
    
    UsuariAplicacioConfiguracio uac = usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio();
    
    
    // Politica de Firma
    int usPoliticaDeFirma = uac.getUsPoliticaDeFirma();
    
    if (usPoliticaDeFirma == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT) { 
      
      Field<?>[] fields = { POLICYIDENTIFIER, POLICYIDENTIFIERHASH,
          POLICYIDENTIFIERHASHALGORITHM,  POLICYURLDOCUMENT
      };

      for (Field<?> field : fields) {
        String value = (String)result.getFieldValue(get(field));
        
        log.info(" VALOR CAMP[" + field.getFullName()+ "] => " + value);
        
        if (value == null || value.trim().length() == 0) {
          // El camp {0} és obligatori.
          result.rejectValue(get(field), "genapp.validation.required",
              new String[] { I18NUtils.tradueix(get(field)) }, null);
        }
      } 
      
    } else {
      uac.setPolicyIdentifier(null);
      uac.setPolicyIdentifierHash(null);
      uac.setPolicyIdentifierHashAlgorithm(null);
      uac.setPolicyUrlDocument(null);
    }


    
    // Custodia
    int politicaCustodia = uac.getPoliticaCustodia();
    if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA
       || politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_ACTIU
       || politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_NO_ACTIU) { 
      
      Long custInfoID = uac.getCustodiaInfoID();
      if (custInfoID == null) {
        // El camp {0} és obligatori.
        result.rejectValue(get(CUSTODIAINFOID), "genapp.validation.required",
            new String[] { I18NUtils.tradueix(get(CUSTODIAINFOID)) }, null);
      }
    } else {
      uac.setCustodiaInfoID(null);
    }
    
    // Segellat de temps
    int politicaSegellat = uac.getPoliticaSegellatDeTemps();
    
    if (politicaSegellat == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT
      || politicaSegellat == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR) { 
      uac.setPluginSegellatID(null);
    } else {
      if (uac.getPluginSegellatID() == null) {
        // El camp {0} és obligatori.
        result.rejectValue(get(PLUGINSEGELLATID), "genapp.validation.required",
            new String[] { I18NUtils.tradueix(get(PLUGINSEGELLATID)) }, null);
      }
    }



    
  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request,
      UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm) {
    return getRedirectWhenCancel(request, usuariAplicacioConfiguracioForm
        .getUsuariAplicacioConfiguracio().getUsuariAplicacioConfigID());
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request,
      UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Throwable __e) {
    if (__e == null) {
      return getRedirectWhenCancel(request, usuariAplicacioConfiguracioForm
          .getUsuariAplicacioConfiguracio().getUsuariAplicacioConfigID());
    } else {
      return getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(HttpServletRequest request,
      java.lang.Long usuariAplicacioConfigID, Throwable __e) {
    return getRedirectWhenCancel(request, usuariAplicacioConfigID);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request,
      java.lang.Long usuariAplicacioConfigID) {
    return "redirect:"
        + GestioUsuariAplicacioAdenController.GESTIO_USUARI_APLICACIO_CONTEXTWEB + "/list";
  }
  
  @Override
  public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
   
   for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
     __tmp.add(new StringKeyValue(String.valueOf(up.getId()) , up.getName()));
   }

   return __tmp;
 }
  
  @Override
  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = AbstractPeticioDeFirmaController.staticGetReferenceListForTipusFirmaID();
    return __tmp;
  }
  
  
  
  @Override
  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   return AbstractPeticioDeFirmaController.staticGetReferenceListForAlgorismeDeFirmaID();
 }
  

}
