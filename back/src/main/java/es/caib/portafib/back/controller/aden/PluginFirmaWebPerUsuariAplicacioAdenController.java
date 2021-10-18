package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.PluginFirmaWebPerUsuariAplicacioController;
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariAplicacioFilterForm;
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariAplicacioForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
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
 * Controller per gestionar el llistat i manteniment de plugins de firma web d'un Usuari Aplicació.
 * L'entrada al controller s'ha de realitzar mitjançant el mapping de "/seleccio/{usuariAplicacioID}" que fixarà
 * l'usuari aplicació dins la sessió.
 * @author areus
 */
@Controller
@RequestMapping(value = "/aden/pluginFirmaWebPerUsuariAplicacio")
@SessionAttributes(types = { PluginFirmaWebPerUsuariAplicacioForm.class, PluginFirmaWebPerUsuariAplicacioFilterForm.class })
public class PluginFirmaWebPerUsuariAplicacioAdenController extends PluginFirmaWebPerUsuariAplicacioController {

   static final String CONTEXT_WEB = "/aden/pluginFirmaWebPerUsuariAplicacio";

   /** Vista a la que botar si no està seleccioant un usuari aplicació */
   private static final String NO_USUARIAPLICACIO_VIEW = GestioUsuariAplicacioAdenController.CONTEXTWEB + "/list";

   /** Atribut de sessió que emmagatzemarà l'id d'usuari aplicació del qual gestionam els seus plugins */
   private static final String SESSION_USUARIAPLICACIOID = "pluginFirmaWebPerUsuariAplicacioListAden_usuariAplicacioID";

   @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
   private UsuariAplicacioService usuariAplicacioEjb;

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
      return "pluginFirmaWebPerUsuariAplicacioListAden";
   }

   @Override
   public String getTileForm() {
      return "pluginFirmaWebPerUsuariAplicacioFormAden";
   }

   @Override
   public String getSessionAttributeFilterForm() {
      return "PluginFirmaWebPerUsuariAplicacioAden_FilterForm";
   }

   /**
    * Fixa la condició per filtrar per UsuariAplicacioID
    * @param request petició
    * @return Where representant la codició que l'UsuariAplicacioID del Plugin ha de ser el fixat a la sessió.
    */
   @Override
   public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      String usuariAplicacioID = (String) request.getSession().getAttribute(SESSION_USUARIAPLICACIOID);
      return USUARIAPLICACIOID.equal(usuariAplicacioID);
   }

   @Override
   public PluginFirmaWebPerUsuariAplicacioFilterForm getPluginFirmaWebPerUsuariAplicacioFilterForm(
         Integer pagina, ModelAndView mav, HttpServletRequest request) throws I18NException {

      PluginFirmaWebPerUsuariAplicacioFilterForm pluginFilterForm = super.getPluginFirmaWebPerUsuariAplicacioFilterForm(pagina, mav, request);

      // En el cas de filterform les operacions que només s'han d'executar un pic, quan isNou() s'ha de fer al principi
      // perquè si pel que fos, aquesta primera vegada no s'executa, en tornar ja no entrarà dins isNou().

      if (pluginFilterForm.isNou()) {
         // Camps a amagar
         // No interesa mostrar l'ID.
         pluginFilterForm.addHiddenField(PLUGINFIRMAWEBPERUSRAPPID);
         // Estam gestionant els plugins d'un usuari, per tant no mostram l'usuari, que ja apareix al títol
         pluginFilterForm.addHiddenField(USUARIAPLICACIOID);

         // Afegir un botó per tornar a la pantalla per triar usuari d'una llista.
         pluginFilterForm.addAdditionalButton(
               new AdditionalButton("icon-arrow-left icon-white", "tornar",
                   getContextWeb() + "/tornar", "btn-primary"));

      }


      String usuariAplicacioID = (String)request.getSession().getAttribute(SESSION_USUARIAPLICACIOID);

      // Això passa quan no s'ha entrat correctametn per /seleccio/{usuariAplicacioID}
      // Guardam missatge d'error i redireccionam a la vista de la llista d'usuaris
      if (usuariAplicacioID == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         mav.setView(new RedirectView(NO_USUARIAPLICACIO_VIEW, true));
         return pluginFilterForm;
      }
      

      // Posam un títol "custom" que inclou el nom de l'usuari
      pluginFilterForm.setTitleCode("pluginfirmaweb.listtitle");
      pluginFilterForm.setTitleParam(usuariAplicacioID);
      
      
      // Mirar la politica de Plugins Web a veure si es compatible amb aquesta pantalla
      Integer politica = usuariAplicacioEjb.executeQueryOne(UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB,
          UsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
      
      PluginFirmaWebPerUsuariEntitatAdenController.mostrarAvisSegonsPolitica(request, pluginFilterForm, politica);

      // Només mostrar boto d'afegir si queden pluguins per vincular al usuari aplicació.
      pluginFilterForm.setAddButtonVisible(
            !getRemainingReferenceListForPluginFirmaWebID(request, mav, usuariAplicacioID).isEmpty()
      );

      return pluginFilterForm;
   }

   @Override
   public PluginFirmaWebPerUsuariAplicacioForm getPluginFirmaWebPerUsuariAplicacioForm(
         PluginFirmaWebPerUsuariAplicacioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
         throws I18NException {

      PluginFirmaWebPerUsuariAplicacioForm pluginForm = super.getPluginFirmaWebPerUsuariAplicacioForm(_jpa, __isView, request, mav);
      String usuariAplicacioID = (String)request.getSession().getAttribute(SESSION_USUARIAPLICACIOID);

      // Això passa quan no s'ha entrat correctametn per /seleccio/{usuariAplicacioID}
      // Guardam missatge d'error i redireccionam a la llista d'usuaris aplicació
      if (usuariAplicacioID == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         mav.setView(new RedirectView(NO_USUARIAPLICACIO_VIEW, true));
         return pluginForm;
      }

      // Posam un títol "custom" que inclou el nom de l'usuari
      pluginForm.setTitleCode("pluginfirmaweb.formtitle");
      pluginForm.setTitleParam(usuariAplicacioID);

      // I no mostrar el camp de l'usuari aplicació dins el formuari, que estarà prefixat.
      pluginForm.addHiddenField(USUARIAPLICACIOID);

      // Només si és un formulari de nova alta
      if(pluginForm.isNou()) {

         // Prefixam l'usuariAplicacio
         pluginForm.getPluginFirmaWebPerUsuariAplicacio().setUsuariAplicacioID(usuariAplicacioID);

         // La llista de plugins que es poden seleccionar s'ha de filtrar addicionalment pels plugins que no estan
         // ja afegits a l'usuari, atés que el parell <usuariAplicacio, plugin> és clau única.
         List<StringKeyValue> list = getRemainingReferenceListForPluginFirmaWebID(request, mav, usuariAplicacioID);
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

     String usuariAplicaicoID = (String) request.getSession().getAttribute(
         SESSION_USUARIAPLICACIOID);

     String redirect;
     if (usuariAplicaicoID == null) {
       redirect = NO_USUARIAPLICACIO_VIEW;
     } else {
       redirect = "/aden/usuariAplicacio/" + usuariAplicaicoID + "/edit";
     }

     return new ModelAndView(new RedirectView(redirect, true));
   }
    
   
   
   
   /**
    * Punt d'entrada a la gestió de plugins d'un usuari aplicació.
    * @param usuariAplicacioID identificador de l'usuari aplicació
    */
   @RequestMapping(value = "/seleccio/{usuariAplicacioID}", method = RequestMethod.GET)
   public ModelAndView seleccioPeticio(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable String usuariAplicacioID) {

      // Això passa quan cridam sense un usuari vàlid.
      // Guardam missatge d'error i redireccionam a la vista de llistat d'usuaris aplicació
      if (usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID) == null) {
         HtmlUtils.saveMessageError(request, I18NUtils.tradueix("pluginfirmaweb.nousuari"));
         return new ModelAndView(new RedirectView(NO_USUARIAPLICACIO_VIEW, true));
      }

      request.getSession().setAttribute(SESSION_USUARIAPLICACIOID, usuariAplicacioID);
      return new ModelAndView(new RedirectView(CONTEXT_WEB + "/list", true));
   }

   /**
    * Filtra la llista de plugins seleccionables amb les condicions ja fixades del reference list
    * {@link #getReferenceListForPluginFirmaWebID(HttpServletRequest, ModelAndView, Where)}
    * i a més, elimina els plugins que no estan ja afegits al usuari.
    * És a dir, la llista que retorna són només els pluguins que no estan encara vinculats (afegits/llevats)
    * a l'usuari.
    */
   public List<StringKeyValue> getRemainingReferenceListForPluginFirmaWebID(HttpServletRequest request, ModelAndView mav, String usuariAplicacioID) throws I18NException {

      // La llista de plugins que es poden seleccionar s'ha de filtrar addicionalment pels plugins que no estan
      // ja afegits a l'usuari, atés que el parell <usuariAplicacio, plugin> és clau única.
      SubQuery<PluginFirmaWebPerUsuariAplicacio, Long> subQuery =
            pluginFirmaWebPerUsuariAplicacioEjb.getSubQuery(PLUGINFIRMAWEBID, USUARIAPLICACIOID.equal(usuariAplicacioID));
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
