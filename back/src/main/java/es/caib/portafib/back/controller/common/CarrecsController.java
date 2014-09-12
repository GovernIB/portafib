package es.caib.portafib.back.controller.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.security.LoginInfo;

/**
 * 
 * @author dboerner
 *
 */
@Controller
@RequestMapping(value = "/common/carrecs")
@SessionAttributes(types = { UsuariEntitatFilterForm.class })
public class CarrecsController extends UsuariEntitatController {

	@Override
	public String getTileList() {
		return "carrecsList";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request)
			throws I18NException {
		LoginInfo li = LoginInfo.getInstance();
		Where w1 = CARREC.isNotNull();
		Where w2 = ENTITATID.equal(li.getEntitatID());
		Where w3 = USUARIPERSONAID.equal(li.getUsuariPersona()
				.getUsuariPersonaID());

		return Where.AND(w1, w2, w3);
	}

	@Override
	public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina,
			ModelAndView mav, HttpServletRequest request) throws I18NException {

		UsuariEntitatFilterForm usuariEntitatFilterForm = super
				.getUsuariEntitatFilterForm(pagina, mav, request);
		if (usuariEntitatFilterForm.isNou()) {
			// ocultam columnes
		  List<Field<?>> hiddenFields = usuariEntitatFilterForm.getHiddenFields();
		  hiddenFields.addAll(Arrays.asList(ALL_USUARIENTITAT_FIELDS));
		  hiddenFields.remove(CARREC);

			// ocultam botons d'accions
			usuariEntitatFilterForm.setAddButtonVisible(false);
			usuariEntitatFilterForm.setDeleteButtonVisible(false);
			usuariEntitatFilterForm.setEditButtonVisible(false);
			// titol llistat
			//usuariEntitatFilterForm.setTitleCode("carrec.llistat");
			usuariEntitatFilterForm.setSubTitleCode("carrec.llistat.subtitol");
			// eliminar botóns d'Agrupació i Filtre
			usuariEntitatFilterForm.setGroupByFields(new ArrayList<Field<?>>());
			usuariEntitatFilterForm.setFilterByFields(new ArrayList<Field<?>>());
			// ocultar seleccio multiple
			usuariEntitatFilterForm.setVisibleMultipleSelection(false);
		}
		return usuariEntitatFilterForm;
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "Carrecs_FilterForm";
	}
	
	@Override
  public boolean isActiveFormNew() {
    return false;
  }
	
	 @Override
   public boolean isActiveFormEdit() {
     return false;
   }

	@Override
  public boolean isActiveDelete() {
    return false;
  }
	
  public String getEntityNameCode() {
    return "carrec";
  }

  public String getEntityNameCodePlural() {
    return "carrec.plural";
  }

}
