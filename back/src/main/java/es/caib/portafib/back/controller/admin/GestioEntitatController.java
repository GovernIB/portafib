package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaController;
import es.caib.portafib.back.controller.webdb.EntitatController;
import es.caib.portafib.back.form.webdb.CustodiaInfoRefList;
import es.caib.portafib.back.form.webdb.EntitatFilterForm;
import es.caib.portafib.back.form.webdb.EntitatForm;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.validator.EntitatLogicValidator;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.ConstantsPortaFIB;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

/**
 * Created 24/05/13 9:13
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value = "/admin/entitat")
public class GestioEntitatController extends EntitatController implements ConstantsV2, ConstantsPortaFIB {

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/EntitatLogicaEJB/local")
  protected EntitatLogicaLocal entitatLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PropietatGlobalLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PropietatGlobalLocal propietatGlobalEjb;

  @Override
  public String getTileForm() {
    return "gestioEntitatForm";
  }

  @Override
  public String getTileList() {
    return "gestioEntitatList";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "EntitatWebDB_FilterForm_Admin";
  }
 
  
  @PostConstruct
  public void init() {

    CustodiaInfoRefList custRefList = new CustodiaInfoRefList(this.custodiaInfoRefList);

    custRefList.setSelects(new Select<?>[] { CustodiaInfoFields.NOMPLANTILLA.select });
    custRefList.setOrderBy(new OrderBy[] { new OrderBy(CustodiaInfoFields.NOMPLANTILLA)} );

    this.custodiaInfoRefList = custRefList;    
  }
  

  @Override
  public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {

       EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);

       if(entitatForm.isNou()) {
         entitatForm.addHiddenField(USUARIAPLICACIOID);
         entitatForm.addHiddenField(CUSTODIAINFOID); // Plantilla de custòdia
         entitatForm.addHiddenField(PLUGINID); // Segell de temps
         entitatForm.addHiddenField(POLITICASEGELLATDETEMPS);
         entitatForm.getEntitat().setPoliticaSegellatDeTemps(POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR);
         
         entitatForm.getEntitat().setPoliticaTaulaFirmes(ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF);
         entitatForm.getEntitat().setPosicioTaulaFirmes(ConstantsV2.TAULADEFIRMES_PRIMERAPAGINA);
         
       } else {
         // TODO S'ha d'ocultar només si te usuarisEntitat o usuarisAplicacio associats (veure mètode delete)         
         entitatForm.addHiddenField(ENTITATID);           
       }

       // HELP
       entitatForm.addHelpToField(FILTRECERTIFICATS, I18NUtils.tradueix("manualfiltrescertificats.ajuda"));
       entitatForm.addHelpToField(MOTIUDELEGACIOID, I18NUtils.tradueix("motiudelegacio.help"));
       entitatForm.addHelpToField(FIRMATPERFORMATID, I18NUtils.tradueix("firmatperformat.help"));
       
       // #165 Pentent de que s'implementi XYZ ZZZ
       entitatForm.getEntitat().setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA);
       entitatForm.addReadOnlyField(POLITICACUSTODIA);

       // #172 Pentent de que s'implementi XYZ ZZZ
       entitatForm.addReadOnlyField(PLUGINRUBRICAID);

       // #166 Pentent de que s'implementi XYZ ZZZ
       entitatForm.addReadOnlyField(POLITICATAULAFIRMES);
       entitatForm.addReadOnlyField(POSICIOTAULAFIRMES);

       // Pentent de que s'implementi XYZ ZZZ
       // #176 Configuració etiquetes de la Taula de Firmes 
       entitatForm.addReadOnlyField(PROPIETATSTAULAFIRMES);

       // #171 Pentent de que s'implementi XYZ ZZZ
       entitatForm.addReadOnlyField(PLUGINVALIDACERTIFICATID);
       //entitatForm.addReadOnlyField(PLUGINVALIDAFIRMESID);
       entitatForm.addReadOnlyField(CHECKCANVIATDOCFIRMAT);
       if(entitatForm.isNou()) {
         entitatForm.getEntitat().setCheckCanviatDocFirmat(true);
       }
       
       // #148 Pendent fins que s'implementi
       entitatForm.addReadOnlyField(USPOLITICADEFIRMA);
       
       entitatForm.setAttachedAdditionalJspCode(true);
       
       /*
       entitatForm.addAdditionalButton(new AdditionalButton(
           "icon-info-sign icon-white", "manualfiltrescertificats", 
            // getContextWeb() + "/docfirmat/" + peticioDeFirmaID,
           "javascript:var win = window.open('" + request.getContextPath() + "/doc/MCFv1.3_manual-integrador_ES_Filtros.pdf', '_blank'); win.focus();",
           "btn-info") );
       */
       
       return entitatForm;
   }

  @Override
  public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav,
		    HttpServletRequest request) throws I18NException {
	    EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);
	    
		/**
		 * Cuando un FilterForm es nuevo?
		 * Cuando se crea la sesión.
		 * Un FilterForm representa la composición básica (títulos, botones columnas, etc...) de un listado.
		 * Durante la sesión el usuario puede des/ordenar o des/agrupar como el quiera pero, a pesar de estas
		 * acciones la estructura base se debe mantener.
		 * Por ello, mediante la siguiente condición inicializamos la composición del listado base
		 * solo una vez: isNou()
		 */
		if (entitatFilterForm.isNou()) {
			// Ocultam totes les columnes
		  Set<Field<?>> hiddenFields = entitatFilterForm.getHiddenFields(); 
		  hiddenFields.addAll(java.util.Arrays.asList(EntitatFields.ALL_ENTITAT_FIELDS));
		  
		  // Mostram només les següents ...
		  hiddenFields.remove(ENTITATID);
		  hiddenFields.remove(NOM);
		  hiddenFields.remove(DESCRIPCIO);
		  hiddenFields.remove(ACTIVA);
			
			entitatFilterForm.addGroupByField(ACTIVA);
			
			entitatFilterForm.addFilterByField(ENTITATID);
			entitatFilterForm.addFilterByField(NOM);
			entitatFilterForm.addFilterByField(DESCRIPCIO);
			
			AdditionalButton additionalButton = new AdditionalButton("icon-user", 
			    "administradorentitat.alta",
			    "/admin/adminentitat/selecciousuari?entitatID={0}",
          null);
			entitatFilterForm.addAdditionalButtonForEachItem(additionalButton);
			

		}
	    return entitatFilterForm;
  }

  @Override
  public void delete(HttpServletRequest request, Entitat entitat) throws Exception, I18NException {

    entitatLogicaEjb.deleteFull(entitat.getEntitatID());

  }
  
  @Override
  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(
      HttpServletRequest request, ModelAndView mav,
      EntitatForm entitatForm, Where _w)  throws I18NException {
    if (entitatForm.isNou()) {
      return new ArrayList<StringKeyValue>();
    } else {
      
      Where w = UsuariAplicacioFields.ENTITATID.equal(entitatForm.getEntitat().getEntitatID());
      List<String> ids = usuariAplicacioEjb.executeQuery(UsuariAplicacioFields.USUARIAPLICACIOID,w);
      
      List<StringKeyValue> list = new ArrayList<StringKeyValue>(ids.size());
      for (String id : ids) {
        StringKeyValue skv = new StringKeyValue(id, id);
        list.add(skv);
      }
      return list;
    }
  }
  
  
  @Override
  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(
      HttpServletRequest request, ModelAndView mav, 
      EntitatFilterForm entitatFilterForm, List<Entitat> list,
      Map<Field<?>, GroupByItem> _groupByItemsMap, Where w) throws I18NException {

    List<StringKeyValue> list2 = new ArrayList<StringKeyValue>(list.size());
    for (Entitat entitat : list) {
      StringKeyValue skv = new StringKeyValue(entitat.getUsuariAplicacioID(), entitat.getUsuariAplicacioID());
      list2.add(skv);
    }

    return list2;

 }
 
  
  @Override
  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    I18NException e = new I18NException("Ningú hauria de cridar aquest mètode."); 
    throw e;
  }
  
  public EntitatLogicValidator<Object> entitatLogicValidator = new EntitatLogicValidator<Object>();
  
  
  @Override
  @InitBinder("entitatForm")
  public void initBinderForm(WebDataBinder binder) {
    getWebValidator().setValidator(entitatLogicValidator);
    super.initBinder(binder);
  }
  
  
  
  @Override
  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
      ModelAndView mav, EntitatForm entitatForm, Where where) throws I18NException {

    Where where2;
    if (entitatForm.isNou()) {
      where2 = where;
    } else {
      where2 = Where.AND(where,
          CustodiaInfoFields.ENTITATID.equal(entitatForm.getEntitat().getEntitatID()),
          CustodiaInfoFields.NOMPLANTILLA.isNotNull()
          );
    }

    return super.getReferenceListForCustodiaInfoID(request, mav, entitatForm, where2);
  }

  /**
   * Plugin de Segellat de Temps
   * @param request
   * @param mav
   * @param entitatFilterForm
   * @param list
   * @param _groupByItemsMap
   * @param where
   * @return
   * @throws I18NException
   */
  @Override
  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
      ModelAndView mav, EntitatForm entitatForm, Where where) throws I18NException {

    Where where2 = Where.AND(
        where,
        PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_SEGELLDETEMPS),
        PluginFields.ENTITATID.equal(entitatForm.getEntitat().getEntitatID()));

    return super.getReferenceListForPluginID(request, mav, entitatForm, where2);
  }
  
  
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    final boolean isEntitat = true;
   return staticGetReferenceListForPoliticaSegellatDeTemps(isEntitat);
 }

  public static List<StringKeyValue> staticGetReferenceListForPoliticaSegellatDeTemps(boolean isEntitat) {
    
    final int[] myArray;
    if (isEntitat) {
      myArray = ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_EN_ENTITAT;
    } else {
      myArray = ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_EN_USR_APP_CONFIG;
    }
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    for (int i = 0; i < myArray.length; i++) {
      String val = String.valueOf(myArray[i]);
      __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("politicadesegellatdetemps." + val)));
    }
    return __tmp;
    
    /* XYZ ZZZ 
    
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
     __tmp.add(new StringKeyValue("" + POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR , I18NUtils.tradueix("segelldetempsviaweb_" + ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR)));
     __tmp.add(new StringKeyValue("" + POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI , I18NUtils.tradueix("segelldetempsviaweb_" + POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI)));
     __tmp.add(new StringKeyValue("" + POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI , I18NUtils.tradueix("segelldetempsviaweb_" + POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI)));
     __tmp.add(new StringKeyValue("" + POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO , I18NUtils.tradueix("segelldetempsviaweb_" + POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO)));
     return __tmp;
     */
  }
  
  
  
  
  
  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    
    final boolean isEntitat = true;
    List<StringKeyValue> kvList = staticGetReferenceListForPoliticaCustodia(isEntitat);
    /* XYZ ZZZ
    for (StringKeyValue skv : kvList) {
      if (skv.getKey().equals(String.valueOf(Constants.POLITICA_CUSTODIA_EL_DEFINIT_EN_ENTITAT))) {
        kvList.remove(skv);
        break;
      }
    }
    */
    return kvList;    
  }
  
  public static List<StringKeyValue> staticGetReferenceListForPoliticaCustodia(boolean isEntitat) {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    
    final int[] myArray;
    if (isEntitat) {
      myArray = ConstantsPortaFIB.POLITICA_CUSTODIA_EN_ENTITAT;
    } else {
      myArray = ConstantsPortaFIB.POLITICA_CUSTODIA_EN_USR_APP_CONFIG;
    }
    
    for (int i = 0; i < myArray.length; i++) {
      String val = String.valueOf(myArray[i]);
      __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("usuarientitat.politicacustodia." + val)));
    }
    return __tmp;
  }
  
  
  
  
  /**
   * #166
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    
    boolean isEntitat = true;
    
    return staticGetReferenceListForPoliticaTaulaFirmes(isEntitat);
    
   
  }

  public static List<StringKeyValue> staticGetReferenceListForPoliticaTaulaFirmes(boolean isEntitat) {
    final int[] myArray;
    if (isEntitat) {
      myArray = ConstantsPortaFIB.POLITICA_TAULA_FIRMES_EN_ENTITAT;
    } else {
      myArray = ConstantsPortaFIB.POLITICA_TAULA_FIRMES_EN_USR_APP_CONFIG;
    }
    
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    for (int i = 0; i < myArray.length; i++) {
      String val = String.valueOf(myArray[i]);
      __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("politicataulafirmes." + val)));
    }
    return __tmp;
  }

  /**
   * #166
   */
  @Override
  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmes(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    
    return staticGetReferenceListForPosicioTaulaFirmes();
  }

  public static List<StringKeyValue> staticGetReferenceListForPosicioTaulaFirmes() {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    
    for (int i = 0; i < ConstantsV2.TAULADEFIRMES.length; i++) {
      int val = ConstantsV2.TAULADEFIRMES[i];
      __tmp.add(new StringKeyValue(String.valueOf(val), I18NUtils.tradueix("tauladefirmes." + val) ));
    }
    return __tmp;
  }
  

  @Override
  public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    final boolean isEntitat = true;
    return staticGetReferenceListForUsPoliticaDeFirma(isEntitat);
  }

  
  
  
  public static List<StringKeyValue> staticGetReferenceListForUsPoliticaDeFirma(boolean isEntitat) {
    final int[] myArray;
    if (isEntitat) {
      myArray = ConstantsPortaFIB.US_POLITICA_DE_FIRMA_EN_ENTITAT;
    } else {
      myArray = ConstantsPortaFIB.US_POLITICA_DE_FIRMA_EN_USR_APP_CONFIG;
    }
    
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    for (int i = 0; i < myArray.length; i++) {
      String val = String.valueOf(myArray[i]);
      __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("usuariaplicacioconfig.uspoliticafirma." + val)));
    }
    return __tmp;
  }
  
  
  @Override
  public List<StringKeyValue> getReferenceListForPluginValidaFirmesID(HttpServletRequest request,
      ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    
   
    Where where2;
    where2 = Where.AND(where, 
        PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_VALIDACIOFIRMES),
        PluginFields.ENTITATID.equal(entitatForm.getEntitat().getEntitatID()));
     

    return super.getReferenceListForPluginValidaFirmesID(request,
        mav, entitatForm,  where2 );
    
    
    
   
  }
  
  
  
  @Override
  public EntitatJPA update(HttpServletRequest request, EntitatJPA entitat)
      throws Exception,I18NException, I18NValidationException {
    EntitatJPA e = (EntitatJPA) super.update(request, entitat);
    // Es requereix que tanqui el navegador per a que els canvis tinguin efecte
    HtmlUtils.saveMessageError(request, I18NUtils.tradueix("entitat.postmodificacio"));
      return e;
    }
  
/*


INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 
'', '2', ''  FROM pfi_entitat;

INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid,
 '', NULL, ''  FROM pfi_entitat;

*/

  @Override
  public EntitatJPA create(HttpServletRequest request, EntitatJPA entitat)
      throws Exception,I18NException, I18NValidationException {
      EntitatJPA e = (EntitatJPA) entitatEjb.create(entitat);
      
      try {
        String _entitatID_ = e.getEntitatID();
        propietatGlobalEjb.create("es.caib.portafib.maxitemstoshowinautocomplete",
            "10", _entitatID_, "Opcional. Valor per defecte 10. En els formularis de cerques"
                + " dinàmiques d'usuari, indica el màxim de resultats permesos per mostrar"
                + " resultats de l'usuari.");
        
        propietatGlobalEjb.create("es.caib.portafib.mincharstostartautocomplete",
            "2",  _entitatID_, "Opcional. Valor per defecte 2. En formularis de cerques"
                + " dinàmiques d'usuari, indica el mínim de caràcters que s'han d'escriure"
                + " per a que  apareguin resultats. En entitats amb molts d''usuaris es"
                + " recomana incrementar aquest valor a 3 o 4.");
        
        propietatGlobalEjb.create("es.caib.portafib.maxtimelockedsigninms", null,
            _entitatID_, "Opcional. Indica Temps de validesa del Token de Firma només"
                + " quan hi ha multiples firmes en un bloc o hi ha delegats definits. "
                + "Es a dir, el temps màxim que un firmant pot tenir bloquejat un"
                + " document durant la firma. Per defecte 3 minuts.");

        propietatGlobalEjb.create("es.caib.portafib.notificationwhencreatedelegaciocolaboracio",
            null,  _entitatID_, "Opcional.Valors posibles true o false.Indica si s’han"
            + " d’enviar avisos via correu electrònic als delegats o col·laboradors quan"
            + "són assignats per un destinatari.Si no es defineix s´usa el valor de la" 
            + " mateixa propietat definida en Propietats Globals");

        propietatGlobalEjb.create("es.caib.portafib.avisosfirmespendents.diesabans", null,
            _entitatID_, "Fa que s'enviïn correus als que tenen peticions de firma pendents."
            + " Indica el numero de dies abans de la caducitat de la petició en que s'han"
            + " de començar a enviar correus. Relacionat amb la PropietatGlobal"
            + " es.caib.portafib.avisosfirmespendents.cron");

        propietatGlobalEjb.create("es.caib.portafib.autofirmaallowed", "true",
            _entitatID_, "Opcional. Serveix per forçar la visibilitat de l´opció Gestió "
                + "d´AutoFirmes del Menú d´Inici. Valors: * true: sempre mostra l´opció"
                + " de menú. * false: mai mostra l´opció de menú. * null: consulta"
                + " el role PFI_AUTOFIRMA");

        propietatGlobalEjb.create("es.caib.portafib.ignorecheckpostsign", "false", 
            _entitatID_, "Opcional. Serveix per indicar a PortaFIB que "
                + "revisi(false o no definit) o no revisi(true) la manipulació"
                + " del PDF firmat");

        
      } catch(I18NException ie) {
        String msg = I18NUtils.getMessage(ie);
        // TODO Traduir
        String missatge = "S'ha produït un error creant les propietats per l'entitat" 
            +  e.getNom() + ". Haurà de crear-les manualment. Error: " + msg;
        
        log.error(missatge, ie);
        HtmlUtils.saveMessageError(request, missatge);
      }
      
      return e;
      
  }
  
  
  // #199
  @Override
  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   return AbstractPeticioDeFirmaController.staticGetReferenceListForAlgorismeDeFirmaID();
 }

}
