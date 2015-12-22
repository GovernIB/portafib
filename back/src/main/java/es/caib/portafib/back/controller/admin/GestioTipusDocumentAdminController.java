package es.caib.portafib.back.controller.admin;


import java.util.ArrayList;
import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.controller.webdb.TipusDocumentController;
import es.caib.portafib.back.form.webdb.TipusDocumentFilterForm;
import es.caib.portafib.back.form.webdb.TipusDocumentForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.logic.TipusDocumentLogicaLocal;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 * 
 * @author dboerner
 * @author anadal
 */
@Controller
@RequestMapping(value = "/admin/gestiotipusdoc")
public class GestioTipusDocumentAdminController extends TipusDocumentController {

	@EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
	protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

	@EJB(mappedName = "portafib/TipusDocumentLogicaEJB/local")
	protected TipusDocumentLogicaLocal tipusDocumentLogicaEjb;

	@PostConstruct
	public void init() {

	  log.debug("Entra dins init()");
	  
	  // Configura com es mostra l'usuari aplicació
	  usuariAplicacioRefList = new UsuariAplicacioRefList(
				usuariAplicacioRefList);
		usuariAplicacioRefList
				.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
		usuariAplicacioRefList.setSeparator("");
		
		
	}
	

	/**
	 * 
	 * @param list
	 * @author anadal
	 */
	/*
	public static void tradueixTipusDocument(List<TipusDocument> list) {	  
	  for (TipusDocument tipus : list) {
      long id = tipus.getTipusDocumentID();
      if (id  < 99) {        
        // Si no te traducció usar el nom
        tipus.setNom(I18NUtils.tradueix(true, tipus.getNom()));
      }
    }
	}
	*/
	
	
	@Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      TipusDocumentFilterForm filterForm,  List<TipusDocument> list) throws I18NException {
	  //tradueixTipusDocument(list);
  }

	@Override
	public TipusDocumentFilterForm getTipusDocumentFilterForm(Integer pagina,
			ModelAndView mav, HttpServletRequest request) throws I18NException {
		TipusDocumentFilterForm tipusDocumentFilterForm;
		tipusDocumentFilterForm = super.getTipusDocumentFilterForm(pagina, mav,
				request);
		/**
		 * Cuando un FilterForm es nuevo?
		 * Cuando se crea la sesión.
		 * Un FilterForm representa la composición básica (títulos, botones columnas, etc...) de un listado.
		 * Durante la sesión el usuario puede des/ordenar o des/agrupar como el quiera pero, a pesar de estas
		 * acciones la estructura base se debe mantener.
		 * Por ello, mediante la siguiente condición inicializamos la composición del listado base
		 * solo una vez: isNou()
		 */
		if (tipusDocumentFilterForm.isNou()) {
			if (isAdmin()) {
				// Ocultam columnes
				tipusDocumentFilterForm.addHiddenField(USUARIAPLICACIOID);				
				// No volem cap agrupacio
				tipusDocumentFilterForm.setGroupByFields(new ArrayList<Field<?>>());				
			} else {
				//if (!Configuracio.isDesenvolupament()) {
				//	tipusDocumentFilterForm.addHiddenField(TIPUSDOCUMENTID);
				//}
				tipusDocumentFilterForm.addFilterByField(USUARIAPLICACIOID);
				tipusDocumentFilterForm.addGroupByField(USUARIAPLICACIOID);
			}
			
			tipusDocumentFilterForm.setVisibleExportList(true);
			
			// Sempre filtram per nom i descripcio
			tipusDocumentFilterForm.addFilterByField(TIPUSDOCUMENTID);
			// Poder filtrar per traduccions
			// tipusDocumentFilterForm.addFilterByField(NOM);
			tipusDocumentFilterForm.addFilterByField(DESCRIPCIO);
			
			tipusDocumentFilterForm.setDefaultOrderBy(new OrderBy[] {
			   new OrderBy(TIPUSDOCUMENTID) 
			});
			
			tipusDocumentFilterForm.setItemsPerPage(30);
			
		}
		return tipusDocumentFilterForm;
	}

	@Override
	public TipusDocumentForm getTipusDocumentForm(TipusDocumentJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		TipusDocumentForm tipusDocumentForm = super.getTipusDocumentForm(_jpa, __isView,
				request, mav);

		if (isAdmin()) {
			if (tipusDocumentForm.isNou()) {
			  // TODO s'ha de cercar el primer lliure entre 1 i 100
			  
				long tipusDocID = this.tipusDocumentEjb.max(TIPUSDOCUMENTID,
						TIPUSDOCUMENTID.lessThan(99L));
				if (tipusDocID == 0L) {
					tipusDocID = 1L;
				}
				tipusDocumentForm.getTipusDocument().setTipusDocumentID(
						tipusDocID + 1);
			} else {
				tipusDocumentForm.addReadOnlyField(TIPUSDOCUMENTID);
			}
			tipusDocumentForm.addHiddenField(USUARIAPLICACIOID);
		} else {
			tipusDocumentForm.addHiddenField(TIPUSDOCUMENTID);
			tipusDocumentForm.addLabel(USUARIAPLICACIOID,
					"tipusdocument.usuariAplicacioID");
		}

		return tipusDocumentForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request)
			throws I18NException {
		Where where;
		if (isAdmin()) {
		  // Tots els comuns 
			where = USUARIAPLICACIOID.isNull();
		} else {
			String entitatID = LoginInfo.getInstance().getEntitatID();
			SubQuery<UsuariAplicacio, String> subQuery;
			subQuery = usuariAplicacioEjb.getSubQuery(
					UsuariAplicacioFields.USUARIAPLICACIOID,
					UsuariAplicacioFields.ENTITATID.equal(entitatID));
			where = Where.AND(USUARIAPLICACIOID.isNotNull(),
					TipusDocumentFields.USUARIAPLICACIOID.in(subQuery));
		}
		return where;
	}

	@Override
	public void postValidate(HttpServletRequest request, TipusDocumentForm tipusDocumentForm,
			BindingResult result) throws I18NException {

		super.postValidate(request, tipusDocumentForm, result);

		if (isAdmin()) {
			Long __tdid = (Long) result.getFieldValue(get(TIPUSDOCUMENTID));

			if (__tdid < 1L) {
				// El valor {0} del camp {1} ha de ser major o igual que {2}
				result.rejectValue(
						get(TIPUSDOCUMENTID),
						"genapp.validation.minimum",
						new String[] { String.valueOf(__tdid),
								I18NUtils.tradueix(get(TIPUSDOCUMENTID)),
								String.valueOf(1L) }, null);
			}
			if (__tdid > 99L) {
				// El valor {0} del camp {1} ha de ser menor o igual que {2}
				result.rejectValue(
						get(TIPUSDOCUMENTID),
						"genapp.validation.maximum",
						new String[] { String.valueOf(__tdid),
								I18NUtils.tradueix(get(TIPUSDOCUMENTID)),
								String.valueOf(99L) }, null);
			}
		} else {
			String __uaid = (String) result.getFieldValue(get(USUARIAPLICACIOID));
			if (__uaid == null || __uaid.trim().length() == 0 ) {
				// El camp {0} és obligatori.
				result.rejectValue(get(USUARIAPLICACIOID),
						"genapp.validation.required", new String[] { I18NUtils
								.tradueix(get(USUARIAPLICACIOID)) }, null);
			}
		}
	}
	
	
	@Override
	public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
      ModelAndView mav, TipusDocumentForm tipusDocumentForm, Where where)  throws I18NException {
   
	 Where where2;
	 if (tipusDocumentForm.isNou()) {
	   where2 = UsuariAplicacioFields.ACTIU.equal(true);
	 } else {
	   where2 = Where.OR(
	       UsuariAplicacioFields.ACTIU.equal(true),
	       UsuariAplicacioFields.USUARIAPLICACIOID.equal(
	           tipusDocumentForm.getTipusDocument().getUsuariAplicacioID())
	       );
	 }
   return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, where2));
 }
	
	
	
	@Override
	public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {
		Where w = UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
		return super.getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, w));
	}
	
	@Override
	public String getTileList() {
		return "gestioTipusDocumentAdminList";
	}

	@Override
	public String getTileForm() {
		return "gestioTipusDocumentAdminForm";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "GestioTipusDocumentAdmin_FilterForm";
	}
	
	@Override
	public void delete(HttpServletRequest request, TipusDocument tipusDocument) throws Exception, I18NException {
		tipusDocumentLogicaEjb.deleteFull((TipusDocumentJPA) tipusDocument);
	}
	
	@Override
	public TipusDocumentJPA create(HttpServletRequest request, TipusDocumentJPA tipusDocument)
			throws Exception, I18NException {
		return tipusDocumentLogicaEjb.create(tipusDocument, !isAdmin());
	}

	public boolean isAdmin() {
		return true;
	}
	
	
  public String getEntityNameCodePlural() {
    return "tipusdocument.plural";
  }
  

/*
  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
      HttpServletRequest request, HttpServletResponse response,
      TipusDocumentFilterForm filterForm) throws Exception, I18NException {
    
    
    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusDocument> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSDOCUMENT_FIELDS;
    

    exportData(request, response, dataExporterID, filterForm, list, allFields);
  }
*/


} // Final de Classe

