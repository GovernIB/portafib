package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.back.controller.common.ConfiguracioUsuariEntitatController;
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
import es.caib.portafib.utils.Constants;

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
public class GestioEntitatController extends EntitatController implements Constants {

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
         entitatForm.addHiddenField(SEGELLDETEMPSVIAWEB);
         entitatForm.getEntitat().setSegellDeTempsViaWeb(SEGELLDETEMPSVIAWEB_NOUSAR);
       } else {
         // TODO S'ha d'ocultar només si te usuarisEntitat o usuarisAplicacio associats (veure mètode delete)         
         entitatForm.addHiddenField(ENTITATID);           
       }

       // HELP
       entitatForm.addHelpToField(FILTRECERTIFICATS, I18NUtils.tradueix("manualfiltrescertificats.ajuda"));
       entitatForm.addHelpToField(MOTIUDELEGACIOID, I18NUtils.tradueix("motiudelegacio.help"));
       entitatForm.addHelpToField(FIRMATPERFORMATID, I18NUtils.tradueix("firmatperformat.help"));
       
       // #165 Pentent de que s'implementi XYZ ZZZ
       entitatForm.getEntitat().setPoliticaCustodia(Constants.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_ENTITAT);
       entitatForm.addReadOnlyField(POLITICACUSTODIA);

       
       // #172 Pentent de que s'implementi XYZ ZZZ
       entitatForm.addReadOnlyField(PLUGINRUBRICAID);
       
       
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

    Where where2;
    if (entitatForm.isNou()) {
      where2 = where;
    } else {

      where2 = Where.AND(
          where,
          PluginFields.ENTITATID.equal(entitatForm.getEntitat().getEntitatID()),
          PluginFields.TIPUS.equal(Constants.TIPUS_PLUGIN_SEGELLDETEMPS)
          );
    }

    return super.getReferenceListForPluginID(request, mav, entitatForm, where2);
  }
  

  
  
  @Override
  public List<StringKeyValue> getReferenceListForSegellDeTempsViaWeb(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
   List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
   __tmp.add(new StringKeyValue("" + SEGELLDETEMPSVIAWEB_NOUSAR , I18NUtils.tradueix("segelldetempsviaweb_" + SEGELLDETEMPSVIAWEB_NOUSAR)));
   __tmp.add(new StringKeyValue("" + SEGELLDETEMPSVIAWEB_SEMPREUSAR , I18NUtils.tradueix("segelldetempsviaweb_" + SEGELLDETEMPSVIAWEB_SEMPREUSAR)));
   __tmp.add(new StringKeyValue("" + SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_SI , I18NUtils.tradueix("segelldetempsviaweb_" + SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_SI)));
   __tmp.add(new StringKeyValue("" + SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_NO , I18NUtils.tradueix("segelldetempsviaweb_" + SEGELLDETEMPSVIAWEB_USUARIELEGEIX_PER_DEFECTE_NO)));
   return __tmp;
 }
  
  
  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    
    List<StringKeyValue> kvList = ConfiguracioUsuariEntitatController.staticGetReferenceListForPoliticaCustodia();
    
    for (StringKeyValue skv : kvList) {
      if (skv.getKey().equals(String.valueOf(Constants.POLITICA_CUSTODIA_EL_DEFINIT_EN_ENTITAT))) {
        kvList.remove(skv);
        break;
      }
    }
    
    
    return kvList;
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
  

}
