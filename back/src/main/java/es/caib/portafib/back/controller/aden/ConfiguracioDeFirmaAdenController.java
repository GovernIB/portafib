package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaController;
import es.caib.portafib.back.controller.admin.GestioEntitatAdminController;
import es.caib.portafib.back.controller.webdb.UsuariAplicacioConfiguracioController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.UsuariAplicacioConfiguracioLogicaLocal;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * - https://github.com/GovernIB/portafib/issues/148 - Perfils i Configuracions per l'API de
 * Firma Simple #235
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = ConfiguracioDeFirmaAdenController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariAplicacioConfiguracioForm.class,
    UsuariAplicacioConfiguracioFilterForm.class })
public class ConfiguracioDeFirmaAdenController extends UsuariAplicacioConfiguracioController {

  public static final String CONTEXT_WEB = "/aden/configdefirma";

  @EJB(mappedName = UsuariAplicacioConfiguracioLogicaLocal.JNDI_NAME)
  private UsuariAplicacioConfiguracioLogicaLocal usuariAplicacioConfiguracioLogicaEjb;

  @Override
  public String getTileForm() {
    return "usuariAplicacioConfiguracioFormAden";
  }

  @Override
  public String getTileList() {
    return "usuariAplicacioConfiguracioListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "UsuariAplicacioConfiguracioADEN_FilterForm";
  }

  @Override
  public UsuariAplicacioConfiguracioForm getUsuariAplicacioConfiguracioForm(
      UsuariAplicacioConfiguracioJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {

    UsuariAplicacioConfiguracioForm form = super.getUsuariAplicacioConfiguracioForm(_jpa,
        __isView, request, mav);

    if (form.isNou()) {
      // Creació
      UsuariAplicacioConfiguracio uac = form.getUsuariAplicacioConfiguracio();

      uac.setTipusOperacioFirma(ConstantsV2.TIPUS_OPERACIO_FIRMA_FIRMAR);

      uac.setComprovarNifFirma(false);
      uac.setCheckCanviatDocFirmat(false);
      uac.setValidarCertificat(false);
      uac.setValidarFirma(false);

      // XYZ ZZZ Falta valors per politiques de custodia, taula i segell de temps !!!!
      // XYZ ZZZ ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET

      uac.setPoliticaSegellatDeTemps(ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR);
      uac.setPosicioTaulaFirmesID(ConstantsV2.TAULADEFIRMES_SENSETAULA);
      uac.setPluginFirmaServidorID(null);
      uac.setEntitatID(LoginInfo.getInstance().getEntitatID());
      uac.setUsEnFirmaAsyncRest2(true);
      uac.setUsEnFirmaWeb(true);
      uac.setUsEnFirmaApiSimpleWeb(true);
    }

    if (__isView) {
      
      form.setCancelButtonVisible(false);
      
      form.addAdditionalButton(new AdditionalButton("icon-edit icon-white","genapp.edit",
          CONTEXT_WEB + "/" + form.getUsuariAplicacioConfiguracio().getUsuariAplicacioConfigID()
              + "/edit", "btn-warning"));
    }
    
    // Codi comu
    form.addHiddenField(ENTITATID);
    form.addHiddenField(ESDEPETICIO);
    

    form.addHelpToField(FILTRECERTIFICATS,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(MOTIUDELEGACIOID,
        I18NUtils.tradueix("motiudelegacio.help") + "\n" +
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(FIRMATPERFORMATID,
        I18NUtils.tradueix("firmatperformat.help") + "\n" +
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
    form.addHelpToField(HTMLPERLLISTARPLUGINSFIRMAWEB,
        I18NUtils.tradueix("usuariaplicacioconfig.definitenentitat.ajuda"));
  
    form.addReadOnlyField(TIPUSOPERACIOFIRMA);
    form.addReadOnlyField(VALIDARCERTIFICAT);

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
    return GestioEntitatAdminController.staticGetReferenceListForUsPoliticaDeFirma(isEntitat);
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
    return GestioEntitatAdminController.staticGetReferenceListForPosicioTaulaFirmes();
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
   * #166
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {

    boolean isEntitat = false;
    return GestioEntitatAdminController.staticGetReferenceListForPoliticaTaulaFirmes(isEntitat);
  }

  /**
   * #148
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    final boolean isEntitat = false;
    return GestioEntitatAdminController.staticGetReferenceListForPoliticaSegellatDeTemps(isEntitat);
  }

  @Override
  public void postValidate(HttpServletRequest request,
      UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, BindingResult result)
      throws I18NException {

    UsuariAplicacioConfiguracio uac = usuariAplicacioConfiguracioForm
        .getUsuariAplicacioConfiguracio();

    // Politica de Firma
    int usPoliticaDeFirma = uac.getUsPoliticaDeFirma();

    if (usPoliticaDeFirma == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT) {

      Field<?>[] fields = { POLICYIDENTIFIER, POLICYIDENTIFIERHASH,
          POLICYIDENTIFIERHASHALGORITHM, POLICYURLDOCUMENT };

      for (Field<?> field : fields) {
        String value = (String) result.getFieldValue(get(field));

        log.info(" VALOR CAMP[" + field.getFullName() + "] => " + value);

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

    // Comprovar que la configuracio de firma inclou el Plugin de Firma en Servidor
    {
      if (uac.isUsEnFirmaApiSimpleServidor() || uac.isUsEnFirmaPassarelaServidor()) {
        if (uac.getPluginFirmaServidorID() == null) {
          result.rejectValue(get(PLUGINFIRMASERVIDORID), "conf.nofirmaservidor",
              new String[] {}, null);
        }
      }
    }

  }

  @Override
  public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
      __tmp.add(new StringKeyValue(String.valueOf(up.getId()), up.getName()));
    }

    return __tmp;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = AbstractPeticioDeFirmaController
        .staticGetReferenceListForTipusFirmaID();
    return __tmp;
  }

  @Override
  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(
      HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    return AbstractPeticioDeFirmaController.staticGetReferenceListForAlgorismeDeFirmaID();
  }

  @Override
  public UsuariAplicacioConfiguracioFilterForm getUsuariAplicacioConfiguracioFilterForm(
      Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {
    UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm;
    usuariAplicacioConfiguracioFilterForm = super.getUsuariAplicacioConfiguracioFilterForm(
        pagina, mav, request);

    if (usuariAplicacioConfiguracioFilterForm.isNou()) {
      Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
          Arrays
              .asList(UsuariAplicacioConfiguracioFields.ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS));
      hiddenFields.remove(NOM);
      hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES);
      // hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICACUSTODIA);
      hiddenFields.remove(UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID);
      hiddenFields.remove(UsuariAplicacioConfiguracioFields.TIPUSFIRMAID);
      hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS);

      usuariAplicacioConfiguracioFilterForm.setHiddenFields(hiddenFields);

      usuariAplicacioConfiguracioFilterForm.setDeleteSelectedButtonVisible(false);

      usuariAplicacioConfiguracioFilterForm.setVisibleMultipleSelection(false);

      usuariAplicacioConfiguracioFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-info-sign icon-white", "ajuda.titol", "javascript:window.open('"
              + request.getContextPath()
              + "/img/perfil_i_configuracio_de_firma.png', '_blank');", "btn-info"));

    }

    return usuariAplicacioConfiguracioFilterForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return Where.AND(
        ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        ESDEPETICIO.equal(false)            
       );
  }

  /*
  Sobreescriure operacions de crear/actualitzar/esborrar per emprar UsuariAplicacioConfiguracioLogica que genera bitàcola
   */

  @Override
  public UsuariAplicacioConfiguracioJPA create(HttpServletRequest request, UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) throws Exception, I18NException, I18NValidationException {
    return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioLogicaEjb.create(usuariAplicacioConfiguracio);
  }

  @Override
  public UsuariAplicacioConfiguracioJPA update(HttpServletRequest request, UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) throws Exception, I18NException, I18NValidationException {
    return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioLogicaEjb.update(usuariAplicacioConfiguracio);
  }

  @Override
  public void delete(HttpServletRequest request, UsuariAplicacioConfiguracio usuariAplicacioConfiguracio) throws Exception, I18NException {
    usuariAplicacioConfiguracioLogicaEjb.delete(usuariAplicacioConfiguracio);
  }

}
