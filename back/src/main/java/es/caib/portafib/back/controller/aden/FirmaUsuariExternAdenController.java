package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FirmaController;
import es.caib.portafib.back.form.webdb.FirmaFilterForm;
import es.caib.portafib.back.form.webdb.FirmaForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal (u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/firmausuariextern")
@SessionAttributes(types = { FirmaForm.class, FirmaFilterForm.class })
public class FirmaUsuariExternAdenController extends FirmaController {

  public static final int COLUMN_PETICIO_FIRMA = 1;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @Override
  public String getTileForm() {
    return "firmaUsuariExternFormAden";
  }

  @Override
  public String getTileList() {
    return "firmaUsuariExternListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "FirmaUsuariExternAden_FilterForm";
  }

  @Override
  public String getEntityNameCode() {
    return "firmausuariextern";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "firmausuariextern.plural";
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    // Es un usuari extern
    Where w1 = FirmaFields.USUARIEXTERNEMAIL.isNotNull();
    // Firma no finalitzada
    Where w2 = FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull();
    // D'un bloc no finalitzat
    Where w3 = new FirmaQueryPath().BLOCDEFIRMES().DATAFINALITZACIO().isNull();
    // Petició en marxa
    Where w4 = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .TIPUSESTATPETICIODEFIRMAID().equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES);
    // D'aquesta entitat
    Where w5 = new FirmaQueryPath().USUARIENTITAT().ENTITATID()
        .equal(LoginInfo.getInstance().getEntitatID());

    Where w = Where.AND(w1, w2, w3, w4, w5);

    return w;
  }

  @PostConstruct
  public void init() {
    usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);

    usuariEntitatRefList.setSelects(
        new Select<?>[] { new UsuariEntitatQueryPath().USUARIPERSONA().NIF().select });
    usuariEntitatRefList.setSeparator("");
  }

  @Override
  public FirmaFilterForm getFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    FirmaFilterForm firmaFilterForm = super.getFirmaFilterForm(pagina, mav, request);
    if (firmaFilterForm.isNou()) {
      firmaFilterForm.setAddButtonVisible(false);
      firmaFilterForm.setDeleteButtonVisible(false);

      firmaFilterForm.setDeleteSelectedButtonVisible(false);

      firmaFilterForm.setVisibleMultipleSelection(false);

      Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
          Arrays.asList(FirmaFields.ALL_FIRMA_FIELDS));

      hiddenFields.remove(USUARIEXTERNEMAIL);
      hiddenFields.remove(USUARIEXTERNNOM);
      hiddenFields.remove(USUARIEXTERNLLINATGES);
      hiddenFields.remove(DESTINATARIID);

      firmaFilterForm.setHiddenFields(hiddenFields);

      // Agrupació només per DestinatariID
      firmaFilterForm.setGroupByFields(new ArrayList<Field<?>>());
      firmaFilterForm.addGroupByField(DESTINATARIID);

      // Filtres
      firmaFilterForm.setFilterByFields(new ArrayList<Field<?>>());
      firmaFilterForm.addFilterByField(USUARIEXTERNEMAIL);
      firmaFilterForm.addFilterByField(USUARIEXTERNNOM);
      firmaFilterForm.addFilterByField(USUARIEXTERNLLINATGES);

      // Afegir nova columna de peticio
      AdditionalField<Long, String> additionalField = new AdditionalField<Long, String>();
      additionalField.setCodeName(
          PeticioDeFirmaFields._TABLE_MODEL + "." + PeticioDeFirmaFields._TABLE_MODEL);
      additionalField.setPosition(COLUMN_PETICIO_FIRMA);
      additionalField.setEscapeXml(true);
      // Els valors s'ompliran al mètode postList()
      additionalField.setValueMap(new HashMap<Long, String>());
      firmaFilterForm.addAdditionalField(additionalField);

      // Afegir boto per anar a detalls de Peticio
      firmaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-info",
          "veuredetalls", getContextWeb() + "/veurepeticio/{0}", "btn-info"));

      // Afegir boto per reenviar missatge
      firmaFilterForm.addAdditionalButtonForEachItem(
          new AdditionalButton("far fa-envelope", "firmausuariextern.reenviaremail",
              getContextWeb() + "/reenviaremail/{0}", "btn-warning"));

      firmaFilterForm.setOrderBy(USUARIEXTERNLLINATGES.javaName);

      firmaFilterForm.addLabel(DESTINATARIID, UsuariPersonaFields.NIF.codeLabel);

    }
    return firmaFilterForm;
  }

  @Override
  public FirmaForm getFirmaForm(FirmaJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {
    FirmaForm firmaForm = super.getFirmaForm(_jpa, __isView, request, mav);

    Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
        Arrays.asList(FirmaFields.ALL_FIRMA_FIELDS));

    hiddenFields.remove(USUARIEXTERNEMAIL);
    hiddenFields.remove(USUARIEXTERNNOM);
    hiddenFields.remove(USUARIEXTERNLLINATGES);

    hiddenFields.remove(DESTINATARIID);

    firmaForm.setHiddenFields(hiddenFields);

    firmaForm.addReadOnlyField(USUARIEXTERNNOM);
    firmaForm.addReadOnlyField(USUARIEXTERNLLINATGES);
    firmaForm.addReadOnlyField(USUARIEXTERNIDIOMA);
    firmaForm.addReadOnlyField(DESTINATARIID);

    firmaForm.setDeleteButtonVisible(false);

    return firmaForm;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @RequestMapping(value = "/veurepeticio/{firmaID}", method = RequestMethod.GET)
  public String veurePeticio(@PathVariable("firmaID") Long firmaID, HttpServletRequest request,
      HttpServletResponse response) throws Exception, I18NException {

    Field<Long> field = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA()
        .PETICIODEFIRMAID();
    Long peticioID = firmaEjb.executeQueryOne(field, FIRMAID.equal(firmaID));

    return "redirect:/aden/peticiofirmatotesgestionar/" + peticioID + "/edit";

  }

  @RequestMapping(value = "/reenviaremail/{firmaID}", method = RequestMethod.GET)
  public String reenviarEmail(@PathVariable("firmaID") Long firmaID,
      HttpServletRequest request, HttpServletResponse response) {

    FirmaJPA firmaJPA = null;
    try {
      
      PeticioDeFirmaQueryPath qp = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA(); 

      String titolPeticio = firmaEjb.executeQueryOne(qp.TITOL(), FIRMAID.equal(firmaID));
      Long peticioDeFirmaID =  firmaEjb.executeQueryOne(qp.PETICIODEFIRMAID(), FIRMAID.equal(firmaID));

      firmaJPA = (FirmaJPA) firmaEjb.findByPrimaryKey(firmaID);

      peticioDeFirmaLogicaEjb.sendMailToExternalUser(LoginInfo.getInstance().getEntitatID(),
          peticioDeFirmaID, titolPeticio, firmaJPA);

      HtmlUtils.saveMessageSuccess(request, I18NUtils
          .tradueix("firmausuariextern.reenviar.ok", firmaJPA.getUsuariExternEmail()));

    } catch (I18NException e) {
      String error = I18NUtils.getMessage(e);

      String email = firmaJPA == null? "[UNKNOWN]" : firmaJPA.getUsuariExternEmail();
      
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("firmausuariextern.reenviar.error", email , error));
    }

    return "redirect:/" + getContextWeb() + "/list";

  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      FirmaFilterForm filterForm, List<Firma> list) throws I18NException {

    Map<Long, String> mapFirmaIDNomPeticio = (Map<Long, String>) filterForm
        .getAdditionalField(COLUMN_PETICIO_FIRMA).getValueMap();
    mapFirmaIDNomPeticio.clear();

    List<Long> ids = new ArrayList<Long>();
    for (Firma pf : list) {
      ids.add(pf.getFirmaID());
    }

    // XYZ ZZZ TODO Optimitzar amb un SelectkeyValue<Long, String>
    SelectMultipleStringKeyValue mskv = new SelectMultipleStringKeyValue(FIRMAID.select,
        new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().TITOL().select);

    List<StringKeyValue> dades = firmaEjb.executeQuery(mskv, FIRMAID.in(ids));

    Map<String, String> map = Utils.listToMap(dades);

    for (Firma pf : list) {
      mapFirmaIDNomPeticio.put(pf.getFirmaID(), map.get(String.valueOf(pf.getFirmaID())));
    }

  }

}
