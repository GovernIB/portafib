package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.PluginFirmaWebPerUsuariEntitatController;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Controller per gestionar el llistat i manteniment de plugins de firma web d'un Usuari Entitat.
 * L'entrada al controller s'ha de realitzar mitjançant el mapping de "/seleccio/{usuariEntitatID}" que fixarà
 * l'usuari entitat dins la sessió.
 * @author areus
 */
@Controller
@RequestMapping(value = "/aden/pluginFirmaWebPerUsuariEntitat")
@SessionAttributes(types = { PluginFirmaWebPerUsuariEntitatForm.class, PluginFirmaWebPerUsuariEntitatFilterForm.class })
public class PluginFirmaWebPerUsuariEntitatAdenController extends PluginFirmaWebPerUsuariEntitatController {

   static final String CONTEXT_WEB = "/aden/pluginFirmaWebPerUsuariEntitat";

   /** Vista a la que botar si no està seleccioant un usuari entitat */
   private static final String NO_USUARIENTITAT_VIEW = GestioUsuariEntitatAdenController.CONTEXTWEB + "/selecciousuari";

   /** Atribut de sessió que emmagatzemarà l'id d'usuari entitat del qual gestionam els seus plugins */
   private static final String SESSION_USUARIENTITATID = "pluginFirmaWebPerUsuariEntitatListAden_usuariEntitatID";

   @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
   private UsuariEntitatLocal usuariEntitatEjb;

   @Override
   public boolean isActiveFormNew() {
      return true;
   }

   @Override
   public boolean isActiveFormEdit() {
      return true;
   }

   @Override
   public boolean isActiveDelete() {
      return true;
   }

   @Override
   public String getTileList() {
      return "pluginFirmaWebPerUsuariEntitatListAden";
   }

   @Override
   public String getTileForm() {
      return "pluginFirmaWebPerUsuariEntitatFormAden";
   }

   @Override
   public String getSessionAttributeFilterForm() {
      return "PluginFirmaWebPerUsuariEntitatAden_FilterForm";
   }

   /**
    * Fixa la condició per filtrar per UsuariEntitatID
    * @param request petició
    * @return Where representant la codició que l'UsuariEntitatID del Plugin ha de ser el fixat a la sessió.
    */
   @Override
   public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      String usuariEntitatID = (String) request.getSession().getAttribute(SESSION_USUARIENTITATID);
      return USUARIENTITATID.equal(usuariEntitatID);
   }

   @Override
   public PluginFirmaWebPerUsuariEntitatFilterForm getPluginFirmaWebPerUsuariEntitatFilterForm(
         Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {

      PluginFirmaWebPerUsuariEntitatFilterForm pluginFilterForm = super.getPluginFirmaWebPerUsuariEntitatFilterForm(pagina, mav, request);

      // En el cas de filterform les operacions que només s'han d'executar un pic, quan isNou() s'ha de fer al principi
      // perquè si pel que fos, aquesta primera vegada no s'executa, en tornar ja no entrarà dins isNou().

      if (pluginFilterForm.isNou()) {
         // Camps a amagar
         // No interesa mostrar l'ID.
         pluginFilterForm.addHiddenField(PLUGINFIRMAWEBPERUSRENTID);
         // Estam gestionant els plugins d'un usuari, per tant no mostram l'usuari, que ja apareix al títol
         pluginFilterForm.addHiddenField(USUARIENTITATID);

         
         // Afegir un botó per tornar a la pantalla de selecció d'usuari
         pluginFilterForm.addAdditionalButton(
               new AdditionalButton("icon-arrow-left icon-white", "tornar",
                     getContextWeb() + "/tornar", "btn-primary"));
         
      }
      


      String usuariEntitatID = (String)request.getSession().getAttribute(SESSION_USUARIENTITATID);

      // Això passa quan no s'ha entrat correctametn per /seleccio/{usuariEntitatID}
      // Guardam missatge d'error i redireccionam a la vista de selecció d'usuari
      if (usuariEntitatID == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         mav.setView(new RedirectView(NO_USUARIENTITAT_VIEW, true));
         return pluginFilterForm;
      }

      // Posam un títol "custom" que inclou el nom de l'usuari
      pluginFilterForm.setTitleCode("pluginfirmaweb.listtitle");
      // TODO agafar una representació millor del nom
      pluginFilterForm.setTitleParam(usuariEntitatID);
      
      
      // Mirar la politica de Plugins Web a veure si es compatible amb aquesta pantalla
      Integer politica = usuariEntitatEjb.executeQueryOne(UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB,
             UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID));
      
      mostrarAvisSegonsPolitica(request, pluginFilterForm, politica);
      
      // Només mostrar boto d'afegir si queden pluguins per vincular al usuari entitat.
      pluginFilterForm.setAddButtonVisible(
            !getRemainingReferenceListForPluginFirmaWebID(request, mav, usuariEntitatID).isEmpty()
      );

      return pluginFilterForm;
   }

  public static void mostrarAvisSegonsPolitica(HttpServletRequest request,
      PortaFIBBaseFilterForm pluginFilterForm, Integer politica)
      throws I18NException {
    switch (politica) {
      
      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_PLUGINS_ENTITAT:
        // Els plugins aqui definits no tindrán efecte, ja que la politica d´aquest
        // usuari és la de utilitzar només els plugins de firma web definits dins l´entitat
        HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.error.senseefecte"));
      break;
        
      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_ENTITAT_I_ADDICIONALS:
        // Els plugins aquí definits s'afegiran o es llevaran als definits en l'entitat.
        pluginFilterForm.setSubTitleCode("pluginfirmaweb.info.definitsientitat");
      break;

      case ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB_NOMES_ADDICIONALS:
        //Només es mostraran els plugins aquí definits per l'usuari seleccionat.
        pluginFilterForm.setSubTitleCode("pluginfirmaweb.info.nomesdefinits");
      break;  
        
      default:
        throw new I18NException("genapp.comodi",
            "Politica de PLugins de Firma Web desconeguda: " + politica);
    }
  }

   @Override
   public PluginFirmaWebPerUsuariEntitatForm getPluginFirmaWebPerUsuariEntitatForm(
         PluginFirmaWebPerUsuariEntitatJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
         throws I18NException {

      PluginFirmaWebPerUsuariEntitatForm pluginForm = super.getPluginFirmaWebPerUsuariEntitatForm(_jpa, __isView, request, mav);
      String usuariEntitatID = (String)request.getSession().getAttribute(SESSION_USUARIENTITATID);

      // Això passa quan no s'ha entrat correctametn per /seleccio/{usuariEntitatID}
      // Guardam missatge d'error i redireccionam a la vista de selecció d'usuari
      if (usuariEntitatID == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         mav.setView(new RedirectView(NO_USUARIENTITAT_VIEW, true));
         return pluginForm;
      }

      // Posam un títol "custom" que inclou el nom de l'usuari
      pluginForm.setTitleCode("pluginfirmaweb.formtitle");
      // TODO agafar una representació millor del nom
      pluginForm.setTitleParam(usuariEntitatID);

      // I no mostrar el camp de l'usuari entitat dins el formuari, que estarà prefixat.
      pluginForm.addHiddenField(USUARIENTITATID);

      // Només si és un formulari de nova alta
      if(pluginForm.isNou()) {

         // Prefixam l'usuariEntitat
         pluginForm.getPluginFirmaWebPerUsuariEntitat().setUsuariEntitatID(usuariEntitatID);

         // La llista de plugins que es poden seleccionar s'ha de filtrar addicionalment pels plugins que no estan
         // ja afegits a l'usuari, atés que el parell <usuariEntitat, plugin> és clau única.
         List<StringKeyValue> list = getRemainingReferenceListForPluginFirmaWebID(request, mav, usuariEntitatID);
         pluginForm.setListOfPluginForPluginFirmaWebID(list);

         // Si no queden més plugins per vincular (afegir/llevar), anam a la llista i treim un missatge d'error.
         if (list.isEmpty()) {
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nomes"));
            mav.setView(new RedirectView(CONTEXT_WEB + "/list", true));
            return pluginForm;
         }

      } else {
         // Si no és nou, és edició, no podem canviar el plugin seleccionat, sinó que només podem canviar que
         // s'afegeix o es lleva.
         pluginForm.addReadOnlyField(PLUGINFIRMAWEBID);
      }

      return pluginForm;
   }
   
   
   
  @RequestMapping(value = "/tornar", method = RequestMethod.GET)
  public ModelAndView tornarEditUsuari(HttpServletRequest request, HttpServletResponse response) {

    String usuariEntitatID = (String) request.getSession().getAttribute(
        SESSION_USUARIENTITATID);

    String redirect;
    if (usuariEntitatID == null) {
      redirect = NO_USUARIENTITAT_VIEW;
    } else {
      redirect = "/aden/usuariEntitat/" + usuariEntitatID + "/edit";
    }

    return new ModelAndView(new RedirectView(redirect, true));
  }
   
   

   /**
    * Punt d'entrada a la gestió de plugins d'un usuari entitat.
    * @param usuariEntitatID identificador de l'usuari entitat
    */
   @RequestMapping(value = "/seleccio/{usuariEntitatID}", method = RequestMethod.GET)
   public ModelAndView seleccioPeticio(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable String usuariEntitatID) {

      // Això passa quan cridam sense un usuari vàlid.
      // Guardam missatge d'error i redireccionam a la vista de selecció d'usuari
      if (usuariEntitatEjb.findByPrimaryKey(usuariEntitatID) == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         return new ModelAndView(new RedirectView(NO_USUARIENTITAT_VIEW, true));
      }

      request.getSession().setAttribute(SESSION_USUARIENTITATID, usuariEntitatID);
      return new ModelAndView(new RedirectView(CONTEXT_WEB + "/list", true));
   }

   /**
    * Filtra la llista de plugins seleccionables amb les condicions ja fixades del reference list
    * {@link #getReferenceListForPluginFirmaWebID(HttpServletRequest, ModelAndView, Where)}
    * i a més, elimina els plugins que no estan ja afegits al usuari.
    * És a dir, la llista que retorna són només els pluguins que no estan encara vinculats (afegits/llevats)
    * a l'usuari.
    */
   public List<StringKeyValue> getRemainingReferenceListForPluginFirmaWebID(HttpServletRequest request, ModelAndView mav, String usuariEntitatID) throws I18NException {

      // La llista de plugins que es poden seleccionar s'ha de filtrar addicionalment pels plugins que no estan
      // ja afegits a l'usuari, atés que el parell <usuariEntitat, plugin> és clau única.
      SubQuery<PluginFirmaWebPerUsuariEntitat, Long> subQuery =
            pluginFirmaWebPerUsuariEntitatEjb.getSubQuery(PLUGINFIRMAWEBID, USUARIENTITATID.equal(usuariEntitatID));
      Where whereNoExisteixJa = PluginFields.PLUGINID.notIn(subQuery);
      return getReferenceListForPluginFirmaWebID(request, mav, whereNoExisteixJa);
   }

   /**
    * Filtra la llista de plugins seleccionables amb les següents condicions:
    *  - Són de tipus Modul Firma Web
    *  - Pertanyen a l'entitat
    */
   @Override
   public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
      Where wherePluginWeb = PluginFields.TIPUS.equal(ConstantsV2.TIPUS_PLUGIN_MODULDEFIRMA_WEB);
      Where whereEntitat = PluginFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());

      return super.getReferenceListForPluginFirmaWebID(request, mav,
            Where.AND(where, wherePluginWeb, whereEntitat));
   }

   /**
    * Sobreescriu per posar etiquetes d'afegir i llevar als valors 1 i -1
    */
   public List<StringKeyValue> getReferenceListForAccio(HttpServletRequest request,
                                                        ModelAndView mav, Where where)  throws I18NException {
      List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
      __tmp.add(new StringKeyValue("-1" , I18NUtils.tradueix("pluginfirmaweb.llevar")));
      __tmp.add(new StringKeyValue("1" , I18NUtils.tradueix("pluginfirmaweb.afegir")));
      return __tmp;
   }
}
