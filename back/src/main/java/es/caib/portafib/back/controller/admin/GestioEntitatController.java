package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.caib.portafib.back.controller.webdb.EntitatController;
import es.caib.portafib.back.form.webdb.EntitatFilterForm;
import es.caib.portafib.back.form.webdb.EntitatForm;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.validator.EntitatLogicValidator;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class GestioEntitatController extends EntitatController {

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/EntitatLogicaEJB/local")
  protected EntitatLogicaLocal entitatLogicaEjb;

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

  

  @Override
  public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView,
        HttpServletRequest request, ModelAndView mav) throws I18NException {

       EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);

       if(entitatForm.isNou()) {
         entitatForm.addHiddenField(USUARIAPLICACIOID);         
       } else {
         // TODO S'ha d'ocultar només si te usuarisEntitat o usuarisAplicacio associats (veure mètode delete)         
         entitatForm.addHiddenField(ENTITATID);           
       }
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
		  List<Field<?>> hiddenFields = entitatFilterForm.getHiddenFields(); 
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

}
