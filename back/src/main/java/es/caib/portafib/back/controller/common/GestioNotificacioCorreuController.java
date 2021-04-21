package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.webdb.RebreAvisController;
import es.caib.portafib.back.form.webdb.RebreAvisFilterForm;
import es.caib.portafib.back.form.webdb.RebreAvisForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.RebreAvisJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.RebreAvisLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.model.fields.TipusNotificacioFields;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * Created 17/06/13 10:27
 * 
 * @author mgonzalez
 * @author anadal (correus agrupats)
 */
@Controller
@RequestMapping(value = "/common/rebreAvis")
public class GestioNotificacioCorreuController extends RebreAvisController {

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = RebreAvisLogicaLocal.JNDI_NAME)
  protected RebreAvisLogicaLocal rebreAvisLogicaEjb;

  @Override
  public String getTileForm() {
    return "rebreAvisForm";
  }

  @Override
  public String getTileList() {
    return "rebreAvisList";
  }

  @Override
  public RebreAvisForm getRebreAvisForm(RebreAvisJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    RebreAvisForm rebreAvisForm = super.getRebreAvisForm(_jpa, __isView, request, mav);

    String ueID = LoginInfo.getInstance().getUsuariEntitatID();
    if (rebreAvisForm.isNou()) {

      teConfiguratNotificacions(request, ueID);
      rebreAvisForm.getRebreAvis().setUsuariEntitatID(ueID);
    } else {  
      rebreAvisForm.addReadOnlyField(TIPUSNOTIFICACIOID);
    }
    
    rebreAvisForm.addLabel(TIPUSNOTIFICACIOID, "notificaciocorreu");
    rebreAvisForm.addHiddenField(USUARIENTITATID);


    return rebreAvisForm;
  }

  @Override
  public RebreAvisFilterForm getRebreAvisFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    RebreAvisFilterForm filterForm = super.getRebreAvisFilterForm(pagina, mav, request);
    String ueID = LoginInfo.getInstance().getUsuariEntitatID();
    // S'ha creat de nou (no s'ha llegit de cache)
    if (filterForm.isNou()) {
      teConfiguratNotificacions(request, ueID);
      filterForm.addHiddenField(ID);
      filterForm.addHiddenField(USUARIENTITATID);
      
      filterForm.addLabel(TIPUSNOTIFICACIOID, "notificaciocorreu");    
    }
    return filterForm;
  }
  

  /**
   * Llistat de Avisos pel formulari: Hem de llevar els tipus d'avis que ja té
   */
  @Override
  public List<StringKeyValue> getReferenceListForTipusNotificacioID(
      HttpServletRequest request, ModelAndView mav, RebreAvisForm rebreAvisForm, Where _w)
      throws I18NException {

    Where where;
    if (rebreAvisForm.isNou()) {
    
      LoginInfo loginInfo = LoginInfo.getInstance();
      final String ueID = loginInfo.getUsuariEntitatID();
  
      SubQuery<RebreAvis, Long> subQuery = rebreAvisEjb.getSubQuery(
          RebreAvisFields.TIPUSNOTIFICACIOID, RebreAvisFields.USUARIENTITATID.equal(ueID));
  
      where = Where.AND(
          TipusNotificacioFields.TIPUSNOTIFICACIOID.notIn(subQuery),
          Where.OR(TipusNotificacioFields.ESAVIS.isNull(),
              TipusNotificacioFields.ESAVIS.equal(true)));
      
    } else {
      where = _w;
    }

    return getReferenceListForTipusNotificacioID(request, mav, where);
  }

  /**
   * Tipus d'Avisos pel llistat: Només cercar les traduccions pels tipus d'avis
   * que ja té
   */
  @Override
  public List<StringKeyValue> getReferenceListForTipusNotificacioID(
      HttpServletRequest request, ModelAndView mav, RebreAvisFilterForm rebreAvisFilterForm,
      List<RebreAvis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where _w)
      throws I18NException {

    LoginInfo loginInfo = LoginInfo.getInstance();
    final String ueID = loginInfo.getUsuariEntitatID();
    if (log.isDebugEnabled()) {
      log.info("getReferenceListForTipusNotificacioID => " + ueID);
    }

    SubQuery<RebreAvis, Long> subQuery = rebreAvisEjb.getSubQuery(
        RebreAvisFields.TIPUSNOTIFICACIOID, RebreAvisFields.USUARIENTITATID.equal(ueID));

    Where where = TipusNotificacioFields.TIPUSNOTIFICACIOID.in(subQuery);

    return getReferenceListForTipusNotificacioID(request, mav, where);
  }

  
  @Override
  public String getSessionAttributeFilterForm() {
    return "RebreAvis_FilterForm_Common";
  }
  
  @Override
  public String getEntityNameCode() {
    return "notificaciocorreu";
  }
  
  @Override
  public String getEntityNameCodePlural() {
    return "notificaciocorreu.plural";
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return USUARIENTITATID.equal(LoginInfo.getInstance().getUsuariEntitatID());
  }

  /**
   * LLançam un avis si l'usuariEntitat te marcat rebre totes les notificacions
   * que ja té
   */
  private void teConfiguratNotificacions(HttpServletRequest request, String  ueID) {
     UsuariEntitatJPA usuariEntitatJPA = usuariEntitatLogicaEjb.findByPrimaryKey(ueID);
     if(usuariEntitatJPA.isRebreTotsElsAvisos()){
        HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("notificaciocorreu.avis"));
     }
  }

  /**
   * Sobreescrivim per seguretat. Evitam que es carregui un avís que no és de l'usuari en qüestío, i per
   * tant no les podrà editar ni borrar si no són seus.
   */
  @Override
  public RebreAvisJPA findByPrimaryKey(HttpServletRequest request, Long id) throws I18NException {
    RebreAvisJPA rebreAvis = rebreAvisLogicaEjb.findByPrimaryKey(id);;
    String ueID = LoginInfo.getInstance().getUsuariEntitatID();
    return rebreAvis.getUsuariEntitatID().equals(ueID) ? rebreAvis : null;
  }

  @Override
  public RebreAvisJPA create(HttpServletRequest request, RebreAvisJPA rebreAvis) throws Exception, I18NException, I18NValidationException {
    return (RebreAvisJPA) rebreAvisLogicaEjb.create(rebreAvis);
  }

  @Override
  public RebreAvisJPA update(HttpServletRequest request, RebreAvisJPA rebreAvis) throws Exception, I18NException, I18NValidationException {
    return (RebreAvisJPA) rebreAvisLogicaEjb.update(rebreAvis);
  }

  @Override
  public void delete(HttpServletRequest request, RebreAvis rebreAvis) throws Exception, I18NException {
    rebreAvisLogicaEjb.delete(rebreAvis);
  }
}
