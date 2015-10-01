package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.webdb.RebreAvisController;
import es.caib.portafib.back.form.webdb.RebreAvisFilterForm;
import es.caib.portafib.back.form.webdb.RebreAvisForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.RebreAvisJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.misc.EnviarCorreusAgrupatsUtils;
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
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
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

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected es.caib.portafib.logic.UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/RebreAvisEJB/local")
  protected es.caib.portafib.ejb.RebreAvisLocal rebreAvisEjb;

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
      rebreAvisForm.addLabel(TIPUSNOTIFICACIOID, "notificaciocorreu");
      rebreAvisForm.addHiddenField(USUARIENTITATID);
      rebreAvisForm.setAttachedAdditionalJspCode(true);
    } else {     
      throw new I18NException("error.unknown","No està permès editar");
    }

    return rebreAvisForm;
  }
  
  
  @Override
  public RebreAvisJPA create(HttpServletRequest request, RebreAvisJPA rebreAvis)
      throws Exception,I18NException, I18NValidationException {
    
    String agruparCorreuStr = request.getParameter("agruparcorreu");
    
    RebreAvisJPA rebreAvisJPA = super.create(request, rebreAvis);
    
    // TODO Ho ha de fer la BBDD
    if (agruparCorreuStr != null) {
      EnviarCorreusAgrupatsUtils.setAgruparCorreus(rebreAvis.getUsuariEntitatID(),
        rebreAvis.getTipusNotificacioID(), true);
    }

    return rebreAvisJPA;
  }
  
  @Override
  public void delete(HttpServletRequest request, RebreAvis rebreAvis) throws Exception,I18NException {
    super.delete(request, rebreAvis);
    
    // TODO Ho ha de fer la BBDD
    EnviarCorreusAgrupatsUtils.setAgruparCorreus(rebreAvis.getUsuariEntitatID(),
        rebreAvis.getTipusNotificacioID(), false);
    
  }
  

  private static final int AGRUPARCORREUS = 1;
  
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

      filterForm.setEditButtonVisible(false);
      
      filterForm.addLabel(TIPUSNOTIFICACIOID, "notificaciocorreu");    

      // TODO Això s'hauria de fer via BBDD
      AdditionalField<String,String> adfield4 = new AdditionalField<String,String>(); 
      adfield4.setCodeName("agruparcorreus");
      adfield4.setPosition(AGRUPARCORREUS);
      // Els valors s'ompliran al mètode postList()
      adfield4.setValueMap(new HashMap<String, String>());
      adfield4.setEscapeXml(false);
      
      filterForm.addAdditionalField(adfield4);
      
      
    }
    return filterForm;
  }
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, RebreAvisFilterForm filterForm,  List<RebreAvis> list) throws I18NException {

    super.postList(request, mav, filterForm, list);

    // TODO Això s'hauria de fer via BBDD
    Map<Long, String> map;
    map = (Map<Long, String>)filterForm.getAdditionalField(AGRUPARCORREUS).getValueMap(); 
    map.clear();

    for (RebreAvis rebreAvis : list) {
      boolean res = EnviarCorreusAgrupatsUtils.isAgruparCorreus(
          rebreAvis.getUsuariEntitatID(), rebreAvis.getTipusNotificacioID());
      String img;
      if (res) {
        img = "icn_alert_success.png";
      } else {
        img = "icn_alert_error.png";
      }

      map.put(rebreAvis.getId(), "<img height=\"18\" width=\"18\" src=\"" + request.getContextPath()
          + "/img/" + img + "\">");
    }
    
  
  }


  /**
   * Llistat de Avisos pel formulari: Hem de llevar els tipus d'avis que ja té
   */
  @Override
  public List<StringKeyValue> getReferenceListForTipusNotificacioID(
      HttpServletRequest request, ModelAndView mav, RebreAvisForm rebreAvisForm, Where _w)
      throws I18NException {

    LoginInfo loginInfo = LoginInfo.getInstance();
    final String ueID = loginInfo.getUsuariEntitatID();

    SubQuery<RebreAvis, Long> subQuery = rebreAvisEjb.getSubQuery(
        RebreAvisFields.TIPUSNOTIFICACIOID, RebreAvisFields.USUARIENTITATID.equal(ueID));

    Where where = Where.AND(
        TipusNotificacioFields.TIPUSNOTIFICACIOID.notIn(subQuery),
        Where.OR(TipusNotificacioFields.ESAVIS.isNull(),
            TipusNotificacioFields.ESAVIS.equal(true)));

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
  public boolean isActiveFormEdit() {
    return false;
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
  private void teConfiguratNotificacions(HttpServletRequest request, String  ueID) throws I18NException {

     UsuariEntitatJPA usuariEntitatJPA = usuariEntitatLogicaEjb.findByPrimaryKey(ueID);

     if(usuariEntitatJPA.isRebreTotsElsAvisos()){
        HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("notificaciocorreu.avis"));
     }

  }

}
