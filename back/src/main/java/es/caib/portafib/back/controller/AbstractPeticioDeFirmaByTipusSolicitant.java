package es.caib.portafib.back.controller;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.utils.ZipProducer;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesService ;
import es.caib.portafib.ejb.UsuariAplicacioService ;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal ;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.model.entity.PermisUsuariPlantilla;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.lang.StringEscapeUtils;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractPeticioDeFirmaByTipusSolicitant extends AbstractPeticioDeFirmaController
        implements ConstantsV2 {

  private static final int COLUMN_PETICIODEFIRMA_TITOL = -1;

  /**
   * Columna Solicitant
   */
  public static final int SOLICITANT = 10;
  
  /**
   * Columna Netejar Fitxers Adaptats
   */
  public static final int NETEJA_ADAPTAT = 11;

  
  /**
   * Columna Netejar Fitxers Originals
   */
  public static final int NETEJA_ORIGINAL = 12;

  public enum TipusSolicitant {
    SOLICITANT_WEB, SOLICITANT_APLICACIO, SOLICITANT_TOTS
  }

  @EJB(mappedName = PlantillaFluxDeFirmesService.JNDI_NAME)
  private PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;

  @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
  protected UsuariAplicacioService usuariAplicacioEjb;

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME, beanName = "FirmaLogicaEJB")
  protected FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaService permisGrupPlantillaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaService permisUsuariPlantillaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatService grupEntitatEjb;

  public abstract TipusSolicitant getTipusSolicitant();

  public abstract String getAnnexPath();

  public abstract String getCustodiaContext() throws I18NException;

  public abstract String getFluxPath();
  
  public abstract boolean isNomesConsulta();
  
  public abstract boolean addCreateButton();

  @PostConstruct
  public void init() {

    // Configura com es mostra l'usuari aplicació
    this.usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);
    usuariAplicacioRefList
        .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    usuariAplicacioRefList.setSeparator("");

    // Idiomes suportats
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);

    // Configura com es mostra l'usuari entitat
    this.usuariEntitatRefList = new UsuariEntitatRefList(usuariEntitatRefList);

    UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
    usuariEntitatRefList.setSelects(new Select<?>[] { personaQueryPath.NOM().select,
        new SelectConstant(" "), personaQueryPath.LLINATGES().select,
        new SelectConstant(" ("), personaQueryPath.USUARIPERSONAID().select,
        new SelectConstant(")") });
    usuariEntitatRefList.setSeparator("");

  }

  // --------------------------------------------------------------------
  // --------------------------------------------------------------------
  // ------------- SELECCIÓ DE FLUX DE LA PETICIÓ DE FIRMA ------------
  // --------------------------------------------------------------------
  // --------------------------------------------------------------------

  public static final String SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES = "SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES";

  public static final String SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO = "SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO";

  public static final String SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA = "SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA";

  public static final String SELECTFLUX_TILE = "seleccionaFluxDeFirmaForm";

  public static final Comparator<FluxDeFirmesJPA> FLUXCOMPARATOR = new Comparator<FluxDeFirmesJPA>() {

    @Override
    public int compare(FluxDeFirmesJPA o1, FluxDeFirmesJPA o2) {
      return o1.getNom().compareToIgnoreCase(o2.getNom()); // To change body of implemented
                                                           // methods use File | Settings |
                                                           // File Templates.
    }
  };

  /**
   * SELECCIO DE FLUX DE FIRMA
   * 
   */
  @RequestMapping(value = "/selectflux", method = RequestMethod.GET)
  public ModelAndView seleccionarFluxDeFirmaGet(HttpServletRequest request)
      throws I18NException {
    ModelAndView mav = new ModelAndView(getTileSeleccioFlux());

    log.debug("Entra dins seleccionarFluxDeFirmaGet");

    LoginInfo loginInfo = LoginInfo.getInstance();

    String entitatActualID = loginInfo.getEntitatID();

    SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm = new SeleccioFluxDeFirmesForm();

    // Favorits
    {
      // Si entram en mode UsuariAplicacio´, els usuaris que es veuran
      // seran els favorits de l'administrador d'entitat
      String usuariEntitatID = loginInfo.getUsuariEntitatID();

      List<UsuariEntitatJPA> usuarisFavorits;
      usuarisFavorits = usuariEntitatLogicaEjb.selectFavorits(usuariEntitatID, null, true);

      seleccioFluxDeFirmesForm.setUrlData("/common/json/usuarientitatcarrec");
      seleccioFluxDeFirmesForm.setUsuarisFavorits(Utils
          .sortStringKeyValueList(SearchJSONController
              .favoritsToUsuariEntitat(usuarisFavorits)));

    }

    // Plantilles de l'usuari-persona
    {
      SubQuery<PlantillaFluxDeFirmes, Long> fluxosSubQuery;
      switch (getTipusSolicitant()) {

        case SOLICITANT_WEB: {
          String usuariEntitatID = loginInfo.getUsuariEntitatID();
          // log.info("     -usuariEntitatID = " + usuariEntitatID);
          fluxosSubQuery = getFluxosDeUsuariEntitat(usuariEntitatID);

          seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(true);
        }
        break;

        default:
        case SOLICITANT_TOTS:
        case SOLICITANT_APLICACIO: {
          String usuariAplicacioID = request.getParameter("usuariAplicacioID");
          if (log.isDebugEnabled()) {
            log.debug("Request Parameter[usuariAplicacioID] = ]" + usuariAplicacioID + "[");
          }

          if (usuariAplicacioID == null) {
            HtmlUtils.saveMessageWarning(request,
                I18NUtils.tradueix("peticiodefirma.error.usuariaplicacionodefinit"));
            return new ModelAndView(new RedirectView(getContextWeb() + "/list"));
          }

          fluxosSubQuery = getFluxosDeUsuariAplicacio(usuariAplicacioID);

          seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(false);
          seleccioFluxDeFirmesForm.setUsuariAplicacioID(usuariAplicacioID);

          String origenPeticioDeFirmaStr = request.getParameter("origenPeticioDeFirma");

          int origenPeticioDeFirma = Integer.parseInt(origenPeticioDeFirmaStr);
          seleccioFluxDeFirmesForm.setOrigenPeticioDeFirma(origenPeticioDeFirma);

        }
        break;

      }

      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID.in(fluxosSubQuery);
      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);

      seleccioFluxDeFirmesForm.setListOfFluxPlantillaUsuari(fluxos);

      if (fluxos == null || fluxos.size() == 0) {
        HtmlUtils.saveMessageWarning(request,
            I18NUtils.tradueix("selectflux.avisnoplantilles"));
      }

    }

    // Plantilles dels usuaris-persona de la mateixa entitat que ofereixen
    // les seves plantilles a tothom. Si entram en mode usuari-aplicacio llavors
    // es mostraran els que tengui permis l'administrador
    {
      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID.in(getFluxosCompartitsDeUsuaris(entitatActualID));
      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);
      seleccioFluxDeFirmesForm.setListOfFluxPlantillaPersonaCompartit(fluxos);

    }

    {
      // Plantilles dels usuaris-aplicacio de la mateixa entitat que ofereixen
      // les seves plantilles a tothom
      Where w;
      w = FluxDeFirmesFields.FLUXDEFIRMESID
          .in(getFluxosCompartitsPerAplicacions(entitatActualID));

      List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

      Collections.sort(fluxos, FLUXCOMPARATOR);
      seleccioFluxDeFirmesForm.setListOfFluxPlantillaAplicacioCompartit(fluxos);

    }

    seleccioFluxDeFirmesForm
        .setTipus(SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX);

    seleccioFluxDeFirmesForm.setContexte(getContextWeb());

    mav.addObject("seleccioFluxDeFirmesForm", seleccioFluxDeFirmesForm);

    return mav;
  }

  public String getTileSeleccioFlux() {
    return "seleccionaFluxDeFirmaForm";
  }

  @RequestMapping(value = "/selectflux", method = RequestMethod.POST)
  public String seleccionarFluxDeFirmaPost(SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm,
      BindingResult result, HttpServletRequest request) {

    // Validar Nom i Tipus
    String nom = seleccioFluxDeFirmesForm.getNom();
    if (nom == null || nom.trim().length() == 0) {
      ValidationUtils.rejectIfEmptyOrWhitespace(result, "nom", "genapp.validation.required",
          new Object[] { I18NUtils.tradueix("nom") });

      return getTileSeleccioFlux(); // "redirect:" + getContextWeb() + "/selectflux";
    }
    
    
    final boolean isDebug = log.isDebugEnabled();
    String usuariAplicacioID;
    int origenPeticioDeFirma;
    if (getTipusSolicitant() == TipusSolicitant.SOLICITANT_WEB) {
      usuariAplicacioID = null;
      origenPeticioDeFirma = ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB;
    } else {

      usuariAplicacioID = seleccioFluxDeFirmesForm.getUsuariAplicacioID();
      if (isDebug) {
        log.debug("Seleccionat usuariaplicacio = ]" + usuariAplicacioID + "[");
      }

      origenPeticioDeFirma = seleccioFluxDeFirmesForm.getOrigenPeticioDeFirma();
      if (isDebug) {
        log.debug("Seleccionat origenPeticioDeFirma = ]" + origenPeticioDeFirma + "[");
      }

    }

    int tipus = seleccioFluxDeFirmesForm.getTipus();

    if (isDebug) {
      log.info("POST: Nom és " + nom);
      log.info("POST: Tipus és " + tipus);
    }

    FluxDeFirmesJPA fluxDeFirmes;
    try {
      switch (tipus) {

        case SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX:

          String usuariEntitatPrimeraFirma = seleccioFluxDeFirmesForm.getId();
          if (usuariEntitatPrimeraFirma == null
              || usuariEntitatPrimeraFirma.trim().length() == 0) {

            if (isDebug) {
              log.info(" HTTP usuarisFavorits: "
                  + Arrays.toString(request.getParameterValues("usuarisFlux")));
            }
            ValidationUtils.rejectIfEmpty(result, "id", "selectflux.elegirusuari", null, null);

            return getTileSeleccioFlux(); // "redirect:" + getContextWeb() + "/selectflux";
          }

          if (isDebug) {
            log.debug("usuariEntitatPrimeraFirma == " + usuariEntitatPrimeraFirma);
          }

          Set<BlocDeFirmesJPA> blocDeFirmes = new HashSet<BlocDeFirmesJPA>();
          int ordre = 0;
          // for (String usuari : usuarisFavorits) {
          FirmaJPA firma = new FirmaJPA();
          firma.setDestinatariID(usuariEntitatPrimeraFirma);
          firma.setObligatori(true);
          
          
          UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatPrimeraFirma);
          UsuariPersonaJPA usuariPersona = usuariEntitat.getUsuariPersona();
          if (!usuariPersona.isUsuariIntern()) {
            firma.setUsuariExternEmail(usuariPersona.getEmail());
            firma.setUsuariExternIdioma(usuariPersona.getIdiomaID());
            firma.setUsuariExternLlinatges(usuariPersona.getLlinatges());
            firma.setUsuariExternNom(usuariPersona.getNom());

            firma.setUsuariExternNivellSeguretat(ConstantsV2.USUARIEXTERN_SECURITY_LEVEL_TOKEN);
            firma.setUsuariExternToken(firmaLogicaEjb.getUniqueTokenForFirma());
          }

          
          Set<FirmaJPA> firmes = new HashSet<FirmaJPA>();
          firmes.add(firma);

          BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
          bloc.setFirmas(firmes);
          bloc.setMinimDeFirmes(1);
          bloc.setOrdre(ordre);
          blocDeFirmes.add(bloc);

          fluxDeFirmes = new FluxDeFirmesJPA();
          fluxDeFirmes.setNom(nom);
          fluxDeFirmes.setBlocDeFirmess(blocDeFirmes);

        break;

        case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_APLICACIO_COMPARTIT: {
          Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaAplicacioCompartit();
          if (isDebug) {
            log.info("TIPUS_PLANTILLA_APLICACIO_COMPARTIT " + idPlantilla);
          }
          fluxDeFirmes = clonarFlux(nom, idPlantilla);
        }
        break;
        case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI: {
          Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaUsuari();
          if (isDebug) {
            log.info("TIPUS_PLANTILLA_USUARI " + idPlantilla);
          }
          fluxDeFirmes = clonarFlux(nom, idPlantilla);
        }
        break;

        case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI_COMPARTIT: {
          Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaPersonaCompartit();
          if (isDebug) {
            log.info("TIPUS_PLANTILLA_USUARI_COMPARTIT " + idPlantilla);
          }
          fluxDeFirmes = clonarFlux(nom, idPlantilla);
        }
        break;

        default:
          // TODO traduir
          HtmlUtils.saveMessageError(request, "Tipus de flux de firmes desconegut " + tipus);
          return "redirect:" + getContextWeb() + "/selectflux";

      }

    } catch (I18NException e) {
      // TODO XYZ ZZZ TRA traduir icatch de I18NException
      String msg = "Error creant flux de firmes " + I18NUtils.getMessage(e);
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
      return "redirect:" + getContextWeb() + "/selectflux";
    } catch (Exception e) {
      // TODO XYZ ZZZ TRA traduir icatch de I18NException
      String msg = "Error creant flux de firmes " + e.getMessage();
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
      return "redirect:" + getContextWeb() + "/selectflux";
    }

    request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES,
        fluxDeFirmes);

    request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO,
        usuariAplicacioID);

    request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA,
        origenPeticioDeFirma);

    return "redirect:" + getContextWeb() + "/new";

  }

  protected FluxDeFirmesJPA clonarFlux(String nom, Long plantillaFluxID) throws Exception {

    FluxDeFirmesJPA fluxPlantilla = fluxDeFirmesLogicaEjb
        .findByPrimaryKeyFull(plantillaFluxID);
    if (fluxPlantilla == null) {
      // NOT FOUND
      String[] args = new String[] { I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmes"),
          I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmesID"), String.valueOf(plantillaFluxID) };

      throw new Exception(I18NUtils.tradueix("error.notfound", args));
    }
    fluxPlantilla.setFluxDeFirmesID(-1);
    // TODO check max lenght de NOM
    fluxPlantilla.setNom(nom);

    fluxPlantilla.setPlantillaFluxDeFirmes(null);
    fluxPlantilla.setPeticioDeFirma(null);

    log.info("CANVIANT CODI TOKEN DE getUsuariExternToken !!!!!");
    Set<BlocDeFirmesJPA> blocsOrig = fluxPlantilla.getBlocDeFirmess();
    for (BlocDeFirmesJPA blocDeFirmesOrig : blocsOrig) {
      Set<FirmaJPA> firmes = blocDeFirmesOrig.getFirmas();
      for (FirmaJPA firmaOrig : firmes) {
        if (firmaOrig.getUsuariExternNom() != null) {
          firmaOrig.setUsuariExternToken(firmaLogicaEjb.getUniqueTokenForFirma());
        }
      }
    }

    return fluxPlantilla;

  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsDeUsuaris(
      String entitatActual) throws I18NException {

    // Usuaris-Entitat de la mateixa entitat
    SubQuery<UsuariEntitat, String> usuarisDeLaMevaEntitat;
    usuarisDeLaMevaEntitat = usuariEntitatLogicaEjb.getSubQuery(
        UsuariEntitatFields.USUARIENTITATID, Where.AND(
            UsuariEntitatFields.ENTITATID.equal(entitatActual),
            UsuariEntitatFields.ACTIU.equal(true)));

    // Compartiris a Tothom
    Where whereFFPS_true;
    {

      // Fluxos disponibles dels anteriors usuaris-entitat amb
      // compartir = true
      whereFFPS_true = Where.AND(
          PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
          PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
    }
    // Compartits amb permis d'usuari directe
    Where whereFFPS_null_usuaris;
    {
      // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
      // Fluxos disponibles dels anteriors usuaris-entitat amb
      // compartir = null (Segons permisos)
      String currentusuariEntitatId = LoginInfo.getInstance().getUsuariEntitatID();
      SubQuery<PermisUsuariPlantilla, Long> permis;
      permis = permisUsuariPlantillaEjb.getSubQuery(
          PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID,
          PermisUsuariPlantillaFields.USUARIENTITATID.equal(currentusuariEntitatId));

      whereFFPS_null_usuaris = Where.AND(
          PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
          PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
    }

    // Compartits amb permis de pertença a grup

    Where whereFFPS_null_grups;
    {
      // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
      // Fluxos disponibles dels anteriors usuaris-entitat que estan definits en algun grups
      // d'usuaris de la plantilla amb compartir = null (Segons permisos)

      // (a) Cercar ID's dels grups que contenen usuaris de la meva entitat
      SubQuery<GrupEntitatUsuariEntitat, Long> grupsDelsUsuaris;
      grupsDelsUsuaris = grupEntitatUsuariEntitatEjb.getSubQuery(
          GrupEntitatUsuariEntitatFields.GRUPENTITATID,
          GrupEntitatUsuariEntitatFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat));

      // (b) Cercar Grups que estan en el subquery anterior i a més l'entitat és la meva
      SubQuery<GrupEntitat, Long> grups;
      grups = grupEntitatEjb.getSubQuery(GrupEntitatFields.GRUPENTITATID, Where.AND(
          GrupEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
          GrupEntitatFields.GRUPENTITATID.in(grupsDelsUsuaris)));

      SubQuery<PermisGrupPlantilla, Long> permis;
      permis = permisGrupPlantillaEjb.getSubQuery(
          PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID,
          PermisGrupPlantillaFields.GRUPENTITATID.in(grups));

      whereFFPS_null_grups = Where.AND(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
          PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
    }

    // Juntar-ho tot
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFPS;
    subQueryFFPS = plantillaFluxDeFirmesEjb.getSubQuery(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
        Where.OR(whereFFPS_true, whereFFPS_null_usuaris, whereFFPS_null_grups));

    return subQueryFFPS;
  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsPerAplicacions(
      String entitatActual) throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFAS;
    {
      // Usuaris-Aplicacio de la mateixa entitat
      SubQuery<UsuariAplicacio, String> uae;
      uae = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
          UsuariAplicacioFields.ENTITATID.equal(entitatActual));
      // Fluxos disponibles dels anteriors usuaris aplicacio amb
      // compartir = true

      Where whereFFAS = Where.AND(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.in(uae),
          PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
      subQueryFFAS = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFAS);
    }
    return subQueryFFAS;
  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariEntitat(String usuariEntitat)
      throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
    {
      Where whereFFU = PlantillaFluxDeFirmesFields.USUARIENTITATID.equal(usuariEntitat);
      subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
    }
    return subQueryFFU;
  }

  private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariAplicacio(
      String usuariAplicacioID) throws I18NException {
    SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
    {
      Where whereFFU = PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID);
      subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
    }
    return subQueryFFU;
  }

  // --------------------------------------------------------------------
  // --------------------------------------------------------------------
  // ------------------------------- CUSTODIA -------------------------
  // --------------------------------------------------------------------
  // --------------------------------------------------------------------

  
  @RequestMapping(value = "/veurecustodiainfo/{custodiaInfoID}", method = RequestMethod.GET)
  public String veureCustodiaInfo(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long custodiaInfoID) throws Exception, I18NException {
    
    String redirectOnModify = getContextWeb() + "/list";
    
    request.getSession().setAttribute("redirectOnCustody", redirectOnModify);

    return "redirect:" + getCustodiaContext() + "/view/" + custodiaInfoID;
    
  }
  
  
  @RequestMapping(value = "/editarcustodiainfo/{custodiaInfoID}", method = RequestMethod.GET)
  public String editarCustodiaInfo(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long custodiaInfoID) throws Exception, I18NException {
    
    String redirectOnModify = getContextWeb() + "/list";
    
    request.getSession().setAttribute("redirectOnCustody", redirectOnModify);
    

    return "redirect:" + getCustodiaContext() + "/" + custodiaInfoID + "/edit";
    
  }
  
  
  
  @RequestMapping(value = "/afegircustodiainfo/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String afegirCustodia(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {

    CustodiaInfo custodiaInfo;
    try {
      custodiaInfo = peticioDeFirmaLogicaEjb.addCustodiaInfoToPeticioDeFirma(peticioDeFirmaID,
          LoginInfo.getInstance().getEntitat());

    } catch (I18NException e) {
      String msg = I18NUtils.getMessage(e);
      HtmlUtils.saveMessageError(request, msg);
      log.error("Error intentant afegir custòdia a peticio: " + msg, e);
      return llistat(request, response);
    } catch (I18NValidationException e) {
      String msg = I18NUtils.getMessage(e);
      HtmlUtils.saveMessageError(request, msg);
      log.error("Error intentant afegir custòdia a peticio: " + msg, e);
      return llistat(request, response);
    }

    if (custodiaInfo == null) {
      // XYZ ZZZ TRA TODO traduir i passar a LOGICA
      // No s'ha definit el plugin de custodia
      HtmlUtils.saveMessageError(request, "La política de custòdia no té assignada"
          + " cap Plantilla de Custòdia. Consulti amb l´Administrador.");
      return llistat(request, response);
    }

    I18NTranslation messageTranslation  = new I18NTranslation("success.creation",
            new I18NArgumentCode("custodiaInfo.custodiaInfo"),
            new I18NArgumentCode("custodiaInfo.custodiaInfoID"),
            new I18NArgumentString(String.valueOf(custodiaInfo.getCustodiaInfoID()))
            );
    HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix(messageTranslation));
    
    String redirectOnModify = getContextWeb() + "/list";
    request.getSession().setAttribute("redirectOnCustody", redirectOnModify);

    return "redirect:" + getCustodiaContext() + "/"
        + custodiaInfo.getCustodiaInfoID() + "/edit";
  }

  // --------------------------------------------------------------------
  // --------------------------------------------------------------------
  // --------------------------- PETICIO DE FIRMES --------------------
  // --------------------------------------------------------------------
  // --------------------------------------------------------------------

  @RequestMapping(value = "/iniciar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String iniciar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws Exception, I18NException {

    try {
      this.peticioDeFirmaLogicaEjb.start(peticioDeFirmaID, true,
          LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID());
      createMessageSuccess(request, "success.iniciat", peticioDeFirmaID);
    } catch (I18NException error) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(error));
    } catch (Exception error) {
      // TODO posar-ho un poc guapo: Error desconegut
      HtmlUtils.saveMessageError(request, error.getMessage());
    }

    return llistat(request, response);
  }

  @RequestMapping(value = "/gestioannexes/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String gestioAnnexes(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws Exception, I18NException {

    request.getSession().setAttribute(
        AbstractAnnexController.PETICIO_DE_FIRMA_FOR_ANNEX_MANAGEMENT, peticioDeFirmaID);

    request.getSession().setAttribute(AbstractAnnexController.BACKPAGE_FOR_ANNEX_MANAGEMENT,
        getContextWeb() + "/list");

    String pathAnnex = getAnnexPath();
    return "redirect:" + pathAnnex;

  }

  @RequestMapping(value = "/iniciarseleccionats", method = RequestMethod.POST)
  public String iniciarSeleccionats(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws I18NException, IOException {

    // seleccionats conté les peticioIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();

    if (seleccionatsStr == null || seleccionatsStr.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      
      String username = LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID();

      for (int i = 0; i < seleccionatsStr.length; i++) {
        Long peticioDeFirmaID;

        try {
          peticioDeFirmaID = Long.parseLong(seleccionatsStr[i]);

        } catch (Throwable e) {
          log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
          continue;
        }

        try {
          this.peticioDeFirmaLogicaEjb.start(peticioDeFirmaID, false, username);
          // createMessageSuccess(request, "success.iniciat", peticioDeFirmaID);
        } catch (I18NException error) {
          HtmlUtils.saveMessageError(request, I18NUtils.getMessage(error));
        } catch (Exception error) {
          // TODO posar-ho un poc guapo: Error desconegut
          HtmlUtils.saveMessageError(request, error.getMessage());
        }

      }

    }
    return llistat(request, response);

  }

  @RequestMapping(value = "/pausar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String pausar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {

    // TODO Ha de llançar excepcio (no ha de tornar booleà)
    if (this.peticioDeFirmaLogicaEjb.pause(peticioDeFirmaID,
        LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID())) {
      createMessageSuccess(request, "success.pausat", peticioDeFirmaID);
    } else {
      // TODO Aquest no és el missatge correcte
      createMessageWarning(request, "error.notfound", peticioDeFirmaID);
    }

    // TODO falta un trycatch amb missatge d'errorr

    return llistat(request, response);
  }

  @RequestMapping(value = "/pausarseleccionats", method = RequestMethod.POST)
  public ModelAndView pausarSeleccionats(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute PeticioDeFirmaFilterForm filterForm)
      throws I18NException {

    // seleccionats conté els estatIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();
    // String role = filterForm.getRole();

    if (seleccionatsStr == null || seleccionatsStr.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    } else {
      
      String username = LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID();

      for (int i = 0; i < seleccionatsStr.length; i++) {
        try {
          Long peticioDeFirmaID = Long.parseLong(seleccionatsStr[i]);

          // TODO Ha de llanaçar un error no un booleà
          if (this.peticioDeFirmaLogicaEjb.pause(peticioDeFirmaID, username)) {
            // TODO OK
          } else {
            // TODO EEROR
          }
        } catch (Throwable e) {
          log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
        }
      }

    }

    return llistatPaginat(request, response, null);
  }

  @RequestMapping(value = "/docfirmat/{peticioDeFirmaID}", method = RequestMethod.GET)
  public void docfirmat(HttpServletResponse response, @PathVariable Long peticioDeFirmaID)
      throws I18NException {

    Fitxer f;
    f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

    final boolean attachment = false;
    FileDownloadController.fullDownload(f.getFitxerID(), f.getNom(), f.getMime(), response,
        attachment);

  }

  @RequestMapping(value = "/docfirmat/descarregar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public void docfirmatDescarregar(HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {

    Fitxer f;
    f = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

    final boolean attachment = true;
    FileDownloadController.fullDownload(f.getFitxerID(), f.getNom(), f.getMime(), response,
        attachment);

  }

  @RequestMapping(value = "/revisat/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String revisat(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {

    PeticioDeFirma pfue;
    pfue = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);

    if (pfue == null) {
      this.createMessageError(request, "error.notfound", peticioDeFirmaID);
    }

    pfue.setAvisWeb(false);
    peticioDeFirmaEjb.update(pfue);

    return llistat(request, response);
  }

  @RequestMapping(value = "/clonar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String clonarPeticio(HttpServletRequest request, HttpServletResponse response,
      @PathVariable long peticioDeFirmaID) throws I18NException {

    try {
      PeticioDeFirmaJPA peticio;
      peticio = this.peticioDeFirmaLogicaEjb.clonePeticioDeFirma(peticioDeFirmaID, LoginInfo
          .getInstance().getEntitat(), I18NUtils.tradueix("copiade"));

      if (peticio == null) {
        this.createMessageError(request, "error.notfound", peticioDeFirmaID);
        return llistat(request, response);
      }

      return "redirect:" + getContextWeb() + "/" + peticio.getPeticioDeFirmaID() + "/edit";
    } catch (Throwable e) {
      log.error(e);
      String msg;
      if (e instanceof I18NException) {
        msg = I18NUtils.getMessage((I18NException) e);
      } else {
        // error.creation=Ha ocorregut un error al crear {0}: {3}
        msg = I18NUtils.tradueix(
            "error.creation",
            I18NUtils.tradueix(PeticioDeFirmaFields._TABLE_MODEL + "."
                + PeticioDeFirmaFields._TABLE_MODEL), null, null, e.getMessage());
      }
      HtmlUtils.saveMessageError(request, msg);
      return llistat(request, response);
    }

  }

  @RequestMapping(value = "/reinicialitzar/{peticioDeFirmaID}", method = RequestMethod.GET)
  public String reinicialitzarPeticio(HttpServletRequest request,
      HttpServletResponse response, @PathVariable long peticioDeFirmaID) throws I18NException {

    try {
      PeticioDeFirmaJPA peticio;
      peticio = this.peticioDeFirmaLogicaEjb.resetPeticioDeFirma(peticioDeFirmaID, LoginInfo
          .getInstance().getEntitat());
      if (peticio == null) {
        this.createMessageError(request, "error.notfound", peticioDeFirmaID);
        return llistat(request, response);
      }
      return "redirect:" + getContextWeb() + "/" + peticioDeFirmaID + "/edit";
    } catch (Throwable e) {

      if (e instanceof I18NException) {
        String msg = I18NUtils.getMessage((I18NException) e);
        HtmlUtils.saveMessageError(request, msg);
      } else if (e instanceof I18NValidationException) {
        String msg = I18NUtils.getMessage((I18NValidationException) e);
        HtmlUtils.saveMessageError(request, msg);
        log.error("Error de Validacio: " + e.getMessage(), e);
      } else {
        log.error("Error desconegut al reinicialitzar la peticio " + peticioDeFirmaID + " :"
            + e.getMessage(), e);
        createMessageError(request, "error.modification", peticioDeFirmaID);
      }

      return llistat(request, response);
    }

  }

  @RequestMapping(value = "/resetseleccionats", method = RequestMethod.POST)
  public String resetSeleccionats(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws I18NException, IOException {

    // seleccionats conté les peticioIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();

    if (seleccionatsStr == null || seleccionatsStr.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {

      for (int i = 0; i < seleccionatsStr.length; i++) {
        Long peticioDeFirmaID;

        try {
          peticioDeFirmaID = Long.parseLong(seleccionatsStr[i]);

        } catch (Throwable e) {
          log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
          continue;
        }
        try {
          PeticioDeFirmaJPA peticio;
          peticio = this.peticioDeFirmaLogicaEjb.resetPeticioDeFirma(peticioDeFirmaID,
              LoginInfo.getInstance().getEntitat());
          if (peticio == null) {
            this.createMessageError(request, "error.notfound", peticioDeFirmaID);
          }
        } catch (Throwable e) {
          log.error(e);

          if (e instanceof I18NException) {
            String msg = I18NUtils.getMessage((I18NException) e);
            HtmlUtils.saveMessageError(request, msg);
          } else {
            createMessageError(request, "error.modification", peticioDeFirmaID);
          }
        }

      }

    }
    return llistat(request, response);

  }

  /**
   * 
   */
  @Override
  public PeticioDeFirmaJPA create(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma)
      throws I18NException, I18NValidationException {

    HttpSession sessio = request.getSession();
    {
      FluxDeFirmesJPA flux;
      flux = (FluxDeFirmesJPA) sessio
          .getAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);
      if (log.isDebugEnabled()) {
        log.debug("CREATE fluxDeFirmes=" + flux);
      }
      peticioDeFirma.setFluxDeFirmes(flux);
      peticioDeFirma.setFluxDeFirmesID(0);
    }

    PeticioDeFirmaJPA pf = peticioDeFirmaLogicaEjb.createFull(peticioDeFirma);

    sessio.removeAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);

    return pf;
  }

  @RequestMapping(value = "/marcarrevisatseleccionats", method = RequestMethod.POST)
  public ModelAndView marcarRevisatSeleccionats(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute PeticioDeFirmaFilterForm filterForm)
      throws I18NException {

    // seleccionats conté els estatIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();
    // String role = filterForm.getRole();

    if (seleccionatsStr == null || seleccionatsStr.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    } else {

      for (int i = 0; i < seleccionatsStr.length; i++) {
        try {
          Long peticioDeFirmaID = Long.parseLong(seleccionatsStr[i]);

          PeticioDeFirma pfue;
          pfue = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);

          if (pfue == null) {
            this.createMessageError(request, "error.notfound", peticioDeFirmaID);
          }

          if (pfue.isAvisWeb()) {
            pfue.setAvisWeb(false);
            peticioDeFirmaEjb.update(pfue);
          }
        } catch (Throwable e) {
          log.error("Error parsejant numero ]" + seleccionatsStr[i] + "[", e);
        }
      }

    }

    return llistatPaginat(request, response, null);
  }

  @RequestMapping(value = "/downloadseleccionats", method = RequestMethod.POST)
  public void downloadSeleccionats(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws I18NException, IOException {

    // seleccionats conté les peticioIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();
    if (seleccionatsStr == null || seleccionatsStr.length == 0) {
      HtmlUtils.saveMessageWarning(request,
              I18NUtils.tradueix("peticiodefirma.capseleccionat"));
      response.sendRedirect(request.getContextPath() + getContextWeb() + "/list");
      return;
    }

    ZipProducer zipProducer = ZipProducer.getInstance();
    try {
      for (String seleccionat : seleccionatsStr) {
        long peticioDeFirmaID;
        try {
          peticioDeFirmaID = Long.parseLong(seleccionat);
        } catch (NumberFormatException e) {
          log.error("Error parsejant numero ]" + seleccionat + "[", e);
          continue;
        }

        FitxerJPA firmat = peticioDeFirmaLogicaEjb.getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);
        File file = FileSystemManager.getFile(firmat.getFitxerID());
        zipProducer.addEntry(firmat.getNom(), file);
      }

      response.setContentType("application/zip");
      response.setHeader("Content-Disposition", "inline; filename=\"fitxersfirmats.zip\"");

      ServletOutputStream outputStream = response.getOutputStream();
      zipProducer.transferTo(outputStream);
      outputStream.flush();
      outputStream.close();

    } finally {
      zipProducer.cleanUp();
    }
  }

  /**
   * Només per AdEn
   * 
   * @param request
   * @param response
   * @param peticioDeFirmaID
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/esborrar/{peticioDeFirmaID}")
  public String esborrar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) {
    try {
      delete(request, peticioDeFirmaID);
    } catch (I18NException i18ne) {
      String missatge = I18NUtils.getMessage(i18ne);
      HtmlUtils.saveMessageError(request, missatge);
      log.error(missatge, i18ne);
    } catch(Exception e) {
      String missatge = e.getMessage();
      HtmlUtils.saveMessageError(request, missatge);
      log.error(missatge, e);
    }

    return getRedirectWhenDelete(request, null, null);
  }

  @RequestMapping(value = "/esborrarSeleccionades", method = RequestMethod.POST)
  public String esborrarSeleccionades(HttpServletRequest request, HttpServletResponse response,
          @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

      String[] seleccionats = filterForm.getSelectedItems();

      if (seleccionats == null || seleccionats.length == 0) {

          HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("peticiodefirma.capseleccionat"));

      } else {

          for (int i = 0; i < seleccionats.length; i++) {
              Long peticioDeFirmaID = stringToPK(seleccionats[i]);
              try {
                  delete(request, peticioDeFirmaID);
              } catch (I18NException i18ne) {
                  String missatge = I18NUtils.getMessage(i18ne);
                  HtmlUtils.saveMessageError(request, missatge);
                  log.error(missatge, i18ne);
              }
          }
      }

      return getRedirectWhenDelete(request, null, null);
  }
 
  
  protected void delete(HttpServletRequest request, Long peticioDeFirmaID)
      throws Exception, I18NException {
    
    PeticioDeFirma peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFull(peticioDeFirmaID);
    
    // Validar que no sigui NULL
    if (peticioDeFirma == null) { 
      createMessageError(request, "error.notfound", null);
    } else {
      delete(request, peticioDeFirma);
    }
    
  }
  
  
  @Override
  public void delete(HttpServletRequest request, PeticioDeFirma peticioDeFirma)
      throws I18NException {

    
    if (getTipusSolicitant() == TipusSolicitant.SOLICITANT_WEB) {
      Set<Long> fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariEntitat(
        peticioDeFirma.getPeticioDeFirmaID(), LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID());
      borrarFitxers(fitxers);
    } else {
      
      // motiuRebuig == motiuEsborrat
      String motiuEsborrat = request.getParameter("motiuRebuig");
      
      log.info(" Motiu Rebuig _=]" + motiuEsborrat + "[");
      

      int estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
      if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
          || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
        rebutjar(request, (PeticioDeFirmaJPA)peticioDeFirma, motiuEsborrat);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
      }
    
      Set<Long> fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingAdministradorEntitat(
        peticioDeFirma.getPeticioDeFirmaID(), LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID(),
        motiuEsborrat);
      borrarFitxers(fitxers);
    }
  
      
  }
  
  
  /**
   * Només per AdEN
   * @param request
   * @param response
   * @param filterForm
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/netejarOriginal", method = RequestMethod.POST)
  public String netejarOriginals(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats == null || seleccionats.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      for (int i = 0; i < seleccionats.length; i++) {
        try {
          Long peticioDeFirmaID = stringToPK(seleccionats[i]);

          peticioDeFirmaLogicaEjb.cleanOriginalFilesOfPeticioDeFirma(peticioDeFirmaID);

        } catch (I18NException i18ne) {
          String missatge = I18NUtils.getMessage(i18ne);
          HtmlUtils.saveMessageError(request, missatge);
          log.error(missatge, i18ne);
        }
      }
    }

    String redirect = getRedirectWhenDelete(request, null, null);
    return redirect;

  }

  /**
   * Només per AdEN
   * @param request
   * @param response
   * @param filterForm
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/netejarAdaptat", method = RequestMethod.POST)
  public String netejarAdaptats(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats == null || seleccionats.length == 0) {

      HtmlUtils.saveMessageWarning(request,
          I18NUtils.tradueix("peticiodefirma.capseleccionat"));

    } else {
      for (int i = 0; i < seleccionats.length; i++) {
        try {
          Long peticioDeFirmaID = stringToPK(seleccionats[i]);

          peticioDeFirmaLogicaEjb.cleanAdaptatFileOfPeticioDeFirma(peticioDeFirmaID);

        } catch (I18NException i18ne) {
          String missatge = I18NUtils.getMessage(i18ne);
          HtmlUtils.saveMessageError(request, missatge);
          log.error(missatge, i18ne);
        }
      }
    }

    String redirect = getRedirectWhenDelete(request, null, null);
    return redirect;

  }
  



  /**
   * Només per AdEN
   * @param request
   * @param response
   * @param peticioDeFirmaID
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/rebutjar/{peticioDeFirmaID}")
  public String rebutjar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long peticioDeFirmaID) throws I18NException {

    rebutjar(request, peticioDeFirmaID);

    return getRedirectWhenDelete(request, peticioDeFirmaID, null);
  }

  /**
   *  Només per AdEN
   * @param request
   * @param response
   * @param filterForm
   * @return
   */
  @RequestMapping(value = "/rebutjarSeleccionades", method = RequestMethod.POST)
  public String rebutjarSeleccionades(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute PeticioDeFirmaFilterForm filterForm) {

    String[] seleccionats = filterForm.getSelectedItems();

    if (seleccionats != null && seleccionats.length != 0) {
      for (int i = 0; i < seleccionats.length; i++) {
        rebutjar(request, Long.parseLong(seleccionats[i]));
      }
    }

    return getRedirectWhenDelete(request, null, null);
  }

  protected PeticioDeFirmaJPA rebutjar(HttpServletRequest request, Long peticioDeFirmaID) {
    
    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb
        .findByPrimaryKeyFull(peticioDeFirmaID);
    
    String motiuDeRebuig2 = request.getParameter("motiuRebuig");
    
    return rebutjar(request, peticioDeFirma, motiuDeRebuig2);
    
  }
  
  protected PeticioDeFirmaJPA rebutjar(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma,
      String motiuDeRebuig2) {
    try {
      
      if (peticioDeFirma == null) {
        // Error
        new PeticioDeFirmaController().createMessageError(request, "error.notfound", null);
      } else {
        /*
        La bitàcola ja recull que és un administrador i el seu login.
        String motiuDeRebuig = I18NUtils.tradueix("peticionsdefirma.destinatari.motiurebuig",
            Utils.getNom(LoginInfo.getInstance().getUsuariPersona()), motiuDeRebuig2);
         */

        peticioDeFirmaLogicaEjb.rebutjarADEN(peticioDeFirma, LoginInfo.getInstance()
            .getUsuariEntitatID(), motiuDeRebuig2);

      }

      return peticioDeFirma;

    } catch (I18NException i18ne) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));
      return null;
    }

  }

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    if (peticioDeFirmaFilterForm.isNou()) {

      // Ocultar columnes
      Set<Field<?>> hiddenFields = peticioDeFirmaFilterForm.getHiddenFields();
      hiddenFields.addAll(Arrays.asList(ALL_PETICIODEFIRMA_FIELDS));

      // Mostrar camps següents
      //hiddenFields.remove(TITOL);
      hiddenFields.remove(DATASOLICITUD);
      hiddenFields.remove(DATAFINAL);
      hiddenFields.remove(TIPUSESTATPETICIODEFIRMAID);

      // Mostrar ID si estam en mode desenvolupament
      if (Configuracio.isDesenvolupament()) {
        hiddenFields.remove(PETICIODEFIRMAID);
      }

      // Mostrar usuari aplicacio i remitent si estan en gestio de usuari aplicacio
      TipusSolicitant tipusSolicitant = getTipusSolicitant();
      switch (tipusSolicitant) {
        case SOLICITANT_WEB:
        break;

        case SOLICITANT_TOTS:
          //hiddenFields.remove(SOLICITANTUSUARIENTITAT1ID);
        case SOLICITANT_APLICACIO:
          hiddenFields.remove(PETICIODEFIRMAID); // #219
          //hiddenFields.remove(SOLICITANTUSUARIAPLICACIOID);
          hiddenFields.remove(REMITENTNOM);
          //hiddenFields.remove(ORIGENPETICIODEFIRMA);
        break;
      }

      // Agegir agrupacions
      peticioDeFirmaFilterForm.addGroupByField(TIPUSDOCUMENTID);
      peticioDeFirmaFilterForm.addGroupByField(TIPUSESTATPETICIODEFIRMAID);
      //peticioDeFirmaFilterForm.addGroupByField(DATASOLICITUD);
      //peticioDeFirmaFilterForm.addGroupByField(DATAFINAL);
      peticioDeFirmaFilterForm.addGroupByField(INFORMACIOADDICIONALAVALUABLE);
      peticioDeFirmaFilterForm.addGroupByField(EXPEDIENTCODI);
      peticioDeFirmaFilterForm.addGroupByField(PROCEDIMENTCODI);
      peticioDeFirmaFilterForm.addGroupByField(REMITENTNOM);

      switch (getTipusSolicitant()) {
        case SOLICITANT_WEB:
          //peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIENTITAT1ID);
        break;

        case SOLICITANT_TOTS:
          peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIENTITAT1ID);
        case SOLICITANT_APLICACIO:
          peticioDeFirmaFilterForm.addGroupByField(SOLICITANTUSUARIAPLICACIOID);
          peticioDeFirmaFilterForm.addGroupByField(ORIGENPETICIODEFIRMA);
        break;
      }

      // Filtres
      List<Field<?>> filtres = new ArrayList<Field<?>>(
          peticioDeFirmaFilterForm.getDefaultFilterByFields());
      filtres.remove(SOLICITANTUSUARIAPLICACIOID);

      peticioDeFirmaFilterForm.setFilterByFields(filtres);

      // Ocultar selecció multiple
      peticioDeFirmaFilterForm.setVisibleMultipleSelection(false);

      // Ocultar columna d'accions
      peticioDeFirmaFilterForm.setEditButtonVisible(false);
      peticioDeFirmaFilterForm.setDeleteButtonVisible(false);

      // Ocultar boto de crear
      peticioDeFirmaFilterForm.setAddButtonVisible(false);

      peticioDeFirmaFilterForm.setAttachedAdditionalJspCode(true);

      OrderBy[] ordre;
      switch (tipusSolicitant) {
        case SOLICITANT_WEB:
          ordre = new OrderBy[] {
              // TODO
              new OrderBy(AVISWEB, OrderType.DESC),
              new OrderBy(TIPUSESTATPETICIODEFIRMAID, OrderType.ASC),
              new OrderBy(DATAFINAL, OrderType.DESC),
              new OrderBy(DATASOLICITUD, OrderType.DESC), };
        break;

        default:
        case SOLICITANT_TOTS:
        case SOLICITANT_APLICACIO:
          ordre = new OrderBy[] { new OrderBy(TIPUSESTATPETICIODEFIRMAID, OrderType.ASC),
              new OrderBy(DATAFINAL, OrderType.DESC),
              new OrderBy(DATASOLICITUD, OrderType.DESC), };
        break;
      }
      
      
      //  NOVES COLUMNES PETICIO

      // ===================  Nom de petició de firma
      {
        AdditionalField<String,String> addfieldPF = new AdditionalField<String,String>(); 
        addfieldPF.setCodeName("peticioDeFirma.titol");
        addfieldPF.setPosition(COLUMN_PETICIODEFIRMA_TITOL);
        // Els valors s'ompliran al mètode postList()
        addfieldPF.setValueMap(new HashMap<String, String>());
        addfieldPF.setOrderBy(TITOL);
        addfieldPF.setEscapeXml(false);
        
        peticioDeFirmaFilterForm.addAdditionalField(addfieldPF);
      }

      
      
      
      if (getTipusSolicitant() != TipusSolicitant.SOLICITANT_WEB) {
        AdditionalField<Long, String> adfield0 = new AdditionalField<Long, String>();
        adfield0.setCodeName("ROLE_SOLI");
        adfield0.setPosition(SOLICITANT);
        adfield0.setEscapeXml(false);
        // Els valors s'ompliran al mètode postList()
        adfield0.setValueMap(new HashMap<Long, String>());

        peticioDeFirmaFilterForm.addAdditionalField(adfield0);
      }

      peticioDeFirmaFilterForm.setDefaultOrderBy(ordre);
      peticioDeFirmaFilterForm
          .setActionsRenderer(EstatDeFirmaFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

    }

    return peticioDeFirmaFilterForm;
  }

  @Override
  public PeticioDeFirmaForm getPeticioDeFirmaForm(PeticioDeFirmaJPA _jpa2, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    log.debug(" Entra dins crearPeticioDeFirmaForm ");
    LoginInfo loginInfo = LoginInfo.getInstance();
    Entitat entitat = loginInfo.getEntitat();

    PeticioDeFirmaForm peticioDeFirmaForm = super.getPeticioDeFirmaForm(_jpa2, __isView,
        request, mav);

    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaForm.getPeticioDeFirma();

    if (peticioDeFirmaForm.isNou()) {

      FluxDeFirmesJPA flux = (FluxDeFirmesJPA) request.getSession().getAttribute(
          SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);

      if (flux == null) {
        // NO Venim de la pàgina de selecccio de Fluxos
        mav.setView(new RedirectView(getContextWeb() + "/selectflux", true));
        return peticioDeFirmaForm;
      }
      // Venim de la pàgina de selecccio de Fluxos
      final String nomPeticio = flux.getNom();

      String usuariAplicacioID;

      switch (getTipusSolicitant()) {

        case SOLICITANT_WEB:
          // Obtenim l'usuari aplicacio per defecte a emprar en
          // aquesta entitat per peticions de usuari-entitat
          usuariAplicacioID = entitat.getUsuariAplicacioID();
          peticioDeFirma.setSolicitantUsuariEntitat1ID(loginInfo.getUsuariEntitatID());
          peticioDeFirma.setAvisWeb(false);

          peticioDeFirma.setRemitentNom(Utils.getOnlyNom(loginInfo.getUsuariPersona()));

          String mail = loginInfo.getUsuariEntitat().getEmail();
          if (mail == null) {
            mail = loginInfo.getUsuariPersona().getEmail();
          }
          peticioDeFirma.setRemitentDescripcio(mail);

          peticioDeFirma.setConfiguracioDeFirmaID(null);
          peticioDeFirma.setOrigenPeticioDeFirma(ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB);

        break;

        case SOLICITANT_TOTS:
        case SOLICITANT_APLICACIO:
          // Obtenim l'usuari aplicació elegit
          usuariAplicacioID = (String) request.getSession().getAttribute(
              SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO);
          request.getSession().removeAttribute(SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO);

          // Si estam des d'una compte d'Administrador d'Entitat provant un usuari aplicacio
          peticioDeFirma.setRemitentNom(Utils.getOnlyNom(loginInfo.getUsuariPersona()) + " ("
              + usuariAplicacioID + ")");
          UsuariAplicacioJPA ua = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);
          peticioDeFirma.setRemitentDescripcio(ua.getEmailAdmin());
          Integer origen = (Integer) request.getSession().getAttribute(
              SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA);
          peticioDeFirma.setOrigenPeticioDeFirma(origen.intValue());
          request.getSession().removeAttribute(SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA);

        break;

        default:
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              " No hi ha codi per gestionar el Form de les Peticions de Firma amb getTipusSolicitant() "
                  + getTipusSolicitant());
      }

      if (usuariAplicacioID == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.usuariaplicacionodefinit"));

        mav.setView(new RedirectView(getContextWeb() + "/selectflux", true));
        return peticioDeFirmaForm;
      }

      peticioDeFirma.setTipusFirmaID(ConstantsV2.TIPUSFIRMA_PADES);
      peticioDeFirma.setAlgorismeDeFirmaID(entitat.getAlgorismeDeFirmaID());
      peticioDeFirma.setModeDeFirma(ConstantsV2.SIGN_MODE_IMPLICIT);

      peticioDeFirma.setTitol(nomPeticio);
      peticioDeFirma.setDescripcio(nomPeticio);

      peticioDeFirma.setPrioritatID(PRIORITAT_BAIXA);

      peticioDeFirma.setTipusEstatPeticioDeFirmaID(TIPUSESTATPETICIODEFIRMA_NOINICIAT); // NO_INICIAT
      // Data caducitat = 1 mes
      Calendar cal = Calendar.getInstance();
      peticioDeFirma.setDataSolicitud(new Timestamp(cal.getTimeInMillis()));

      cal.add(Calendar.MONTH, 1);
      peticioDeFirma.setDataCaducitat(new Timestamp(cal.getTimeInMillis()));

      peticioDeFirma.setIdiomaID(loginInfo.getUsuariPersona().getIdiomaID());
      

      // LoginInfo li = LoginInfo.getInstance();

      // TODO Mirar si l'usuari-entitat té definit un altre logo
      /*
       * String urlLogoSegell = Configuracio.getAppUrl() +
       * FileDownloadController.fileUrl(li.getEntitat().getLogoSegell());
       * 
       * log.info("LOGO SEGELL: " + urlLogoSegell );
       * 
       * peticioDeFirma.setUrlLogoSegell(urlLogoSegell);
       */

      peticioDeFirma.setSolicitantUsuariAplicacioID(usuariAplicacioID);

      peticioDeFirmaForm.addHiddenField(FLUXDEFIRMESID);

      HtmlUtils.saveMessageInfo(request,
          I18NUtils.tradueix("peticiodefirma.modificacionspostcreacio"));

      // TODO Afegir boto per crear i iniciar flux de firmes

      // DESCRIPCIO TIPUS DE DOCUMENT
      peticioDeFirmaForm.setAttachedAdditionalJspCode(true);

    } else {

      peticioDeFirmaForm.addHiddenField(FLUXDEFIRMESID);

      int tipusFirma = peticioDeFirma.getTipusFirmaID();
      
     

      switch (tipusFirma) {
        
        case ConstantsV2.TIPUSFIRMA_PADES:
        break;
        case ConstantsV2.TIPUSFIRMA_XADES:
        case ConstantsV2.TIPUSFIRMA_CADES:
        break;

        default:
          // TODO traduir
          throw new I18NException("error.unknown", "Tipus de Firma no suportada " + tipusFirma);
      }

      if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {

        switch (tipusFirma) {
          case ConstantsV2.TIPUSFIRMA_PADES:
          case ConstantsV2.TIPUSFIRMA_XADES:
          case ConstantsV2.TIPUSFIRMA_CADES:
          break;

          default:
            throw new I18NException("error.unknown", "Tipus de Firma no suportada "
                + tipusFirma);
        }

      } else {
        
        switch (tipusFirma) {

          case ConstantsV2.TIPUSFIRMA_PADES:
            peticioDeFirmaForm.addHiddenField(MODEDEFIRMA);
          break;

          case ConstantsV2.TIPUSFIRMA_XADES:
          case ConstantsV2.TIPUSFIRMA_CADES:
            peticioDeFirmaForm.addHiddenField(POSICIOTAULAFIRMESID);
          break;

          default:
            throw new I18NException("error.unknown", "Tipus de Firma no suportada "
                + tipusFirma);
        }
        
      }

    }

    // #293 fer cas dels valors de taula de firmes de l'entitat
    switch (entitat.getPoliticaTaulaFirmes()) {
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET:
        // Enitat no permet taula de firmes. Marcam sense taula i feim cap readonly
        peticioDeFirma.setPosicioTaulaFirmesID(ConstantsV2.TAULADEFIRMES_SENSETAULA);
        peticioDeFirmaForm.addReadOnlyField(POSICIOTAULAFIRMESID);
      break;
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT:
        // Enitat defineix obligatòriament taula de firmes. Marcam la posició definida a
        // l'entitat i feim cap readonly
        peticioDeFirma.setPosicioTaulaFirmesID(entitat.getPosicioTaulaFirmes());
        peticioDeFirmaForm.addReadOnlyField(POSICIOTAULAFIRMESID);
      break;
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF:
        // Entitat defineix taula de firmes opicional i valor per defecte. Marcam per defecte
        // posició de l'entitat
        if (peticioDeFirmaForm.isNou()) {
          peticioDeFirma.setPosicioTaulaFirmesID(entitat.getPosicioTaulaFirmes());
        }
      break;
    }

    switch (entitat.getPoliticaSegellatDeTemps()) {
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
        peticioDeFirmaForm.addReadOnlyField(SEGELLATDETEMPS);
        peticioDeFirma.setSegellatDeTemps(false);
      break;

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
        peticioDeFirmaForm.addReadOnlyField(SEGELLATDETEMPS);
        peticioDeFirma.setSegellatDeTemps(true);
      break;

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
        if (peticioDeFirmaForm.isNou()) {
          peticioDeFirma.setSegellatDeTemps(false);
        }
      break;

      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
        if (peticioDeFirmaForm.isNou()) {
          peticioDeFirma.setSegellatDeTemps(true);
        }
      break;

    }

    // TODO quan hi hagi més tipus disponibles llavors això s'ha de llevar
    if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {

      peticioDeFirmaForm.addHiddenField(DATASOLICITUD);
      peticioDeFirmaForm.addHiddenField(DATAFINAL);

      peticioDeFirmaForm.addHiddenField(MOTIUDEREBUIG);

      // TODO Mirar si l'usuari té definit el URL Segel i assignar-li aquest
      peticioDeFirmaForm.addHiddenField(LOGOSEGELLID);

      // TODO Que feim amb idioma
      // peticioDeFirmaForm.addHiddenField(IDIOMAID);
      peticioDeFirmaForm.addHiddenField(FITXERADAPTATID);

      peticioDeFirmaForm.addHiddenField(TIPUSESTATPETICIODEFIRMAID);

      // GESTIONAR VIA JS LA DESCRIPCIO DE TIPUS DE DOCUMENT
      peticioDeFirmaForm.setAttachedAdditionalJspCode(true);

    } else {
      for (Field<?> field : PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS) {
        peticioDeFirmaForm.addReadOnlyField(field);
      }

      Utils.hiddenEmptyFields(peticioDeFirmaForm, peticioDeFirma,
          PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS);

      peticioDeFirmaForm.setTitleCode("peticiodefirma.veuredetalls");
      peticioDeFirmaForm.setDeleteButtonVisible(false);
      peticioDeFirmaForm.setSaveButtonVisible(false);

//      // OCULTAR LA DESCRIPCIO DE TIPUS DE DOCUMENT SI VAL NULL
//      String descr = peticioDeFirmaForm.getPeticioDeFirma().getDescripcioTipusDocument();
//      if (descr == null || descr.trim().length() == 0) {
//        peticioDeFirmaForm.addHiddenField(DESCRIPCIOTIPUSDOCUMENT);
//      }
    }

    switch (peticioDeFirma.getOrigenPeticioDeFirma()) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
        peticioDeFirmaForm.addHiddenField(SOLICITANTUSUARIAPLICACIOID);

        peticioDeFirmaForm.addHiddenField(REMITENTNOM);
        peticioDeFirmaForm.addHiddenField(REMITENTDESCRIPCIO);

        peticioDeFirmaForm.addHiddenField(CONFIGURACIODEFIRMAID);

      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:

        peticioDeFirmaForm.addReadOnlyField(SOLICITANTUSUARIAPLICACIOID);
        peticioDeFirmaForm.addHiddenField(CONFIGURACIODEFIRMAID);

      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
        peticioDeFirmaForm.addReadOnlyField(SOLICITANTUSUARIAPLICACIOID);

        if (peticioDeFirma.getTipusEstatPeticioDeFirmaID() != ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          peticioDeFirmaForm.addReadOnlyField(CONFIGURACIODEFIRMAID);
        }

      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "No hi ha codi per gestionar camps ocults o readonly de les Peticions de Firma amb Origen "
                + I18NUtils.tradueix("origenpeticiodefirma."
                    + peticioDeFirma.getOrigenPeticioDeFirma()));
    }

    // XYZ ZZZ #164
    peticioDeFirmaForm.addHiddenField(FIRMAORIGINALDETACHEDID);

    // XYZ ZZZ #164
    peticioDeFirmaForm.addHiddenField(TIPUSOPERACIOFIRMA);
    peticioDeFirma.setTipusOperacioFirma(ConstantsV2.TIPUS_OPERACIO_FIRMA_FIRMAR);

    // #305 i #294
    //peticioDeFirmaForm.addHiddenField(TIPUSFIRMAID);
    //peticioDeFirmaForm.addHiddenField(MODEDEFIRMA);
    
    
    
    peticioDeFirmaForm.addHiddenField(ALGORISMEDEFIRMAID);
    

    peticioDeFirmaForm.addHiddenField(SOLICITANTUSUARIENTITAT1ID);
    peticioDeFirmaForm.addHiddenField(SOLICITANTUSUARIENTITAT2ID);
    peticioDeFirmaForm.addHiddenField(SOLICITANTUSUARIENTITAT3ID);
    peticioDeFirmaForm.addHiddenField(AVISWEB);

    peticioDeFirmaForm.addHiddenField(CUSTODIAINFOID);

    // Nous camps a Firma i a Petició de Firma #281
    peticioDeFirmaForm.addHiddenField(ORIGENPETICIODEFIRMA);

    if (__isView) {
      // Ocultar Camps NULL CampsNull
      Utils.hiddenEmptyFields(peticioDeFirmaForm, peticioDeFirma,
          PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS);
    }

    return peticioDeFirmaForm;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
      ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)
      throws I18NException {

    String usuariAplicacioID = peticioDeFirmaForm.getPeticioDeFirma()
        .getSolicitantUsuariAplicacioID();
    Where whereTD;
    whereTD = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
        TipusDocumentFields.USUARIAPLICACIOID.isNull());

    boolean showIds;
    int origen = peticioDeFirmaForm.getPeticioDeFirma().getOrigenPeticioDeFirma();
    switch (origen) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
        whereTD = Where.AND(TipusDocumentFields.TIPUSDOCUMENTID.greaterThan(0L));
        showIds = false;
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
        UsuariAplicacio ua = this.usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);

        // Per usuaris aplicacio tipus Indra només mostram els tipus negatius
        if (ua.getCallbackVersio() == 0) {
          whereTD = Where.AND(TipusDocumentFields.TIPUSDOCUMENTID.lessThan(0L));
        }
        showIds = true;

      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException(
            "genapp.comodi",
            " No hi ha codi per gestionar el WHERE de TipusDocuments de les Peticions de Firma amb Origen "
                + I18NUtils.tradueix("origenpeticiodefirma." + origen));
    }

    List<StringKeyValue> result;
    result = super.getReferenceListForTipusDocumentID(request, mav, peticioDeFirmaForm,
        Where.AND(where, whereTD));

    if (showIds) {
      for (StringKeyValue stringKeyValue : result) {
        String id = stringKeyValue.getKey();
        String newvalue = stringKeyValue.getValue() + " (" + id + ")";
        stringKeyValue.setValue(newvalue);
      }
    }
    return result;

  }


  @Override
  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {

    final Where w = IdiomaFields.SUPORTAT.equal(true);

    return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, Where.AND(where, w));
  }

  @Override
  public String getTileForm() {
    return "peticioDeFirmaForm";
  }

  @Override
  public String getTileList() {
    return "peticioDeFirmaList";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return super.getSessionAttributeFilterForm() + "_" + getTipusSolicitant() + "_" + getContextWeb();
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    switch (getTipusSolicitant()) {

      case SOLICITANT_WEB:
        // Seleccionar només les peticions de firma de l'usuari-entitat (Solicitant)
        return Where.AND(
            SOLICITANTUSUARIENTITAT1ID.equal(LoginInfo.getInstance().getUsuariEntitatID()),
            ORIGENPETICIODEFIRMA.equal(ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB));

      case SOLICITANT_APLICACIO:
        // Seleccionam totes aquelles que no tenguin definit cap usuari-entitat
        // i que els usuaris-aplicació pertanyin a aquesta entitat
        return Where.AND(
            ORIGENPETICIODEFIRMA.notEqual(ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB),
            new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID()
                .equal(LoginInfo.getInstance().getEntitatID()));

      case SOLICITANT_TOTS:
        String entitatID = LoginInfo.getInstance().getEntitatID();
        SubQuery<UsuariAplicacio, String> subqueryApp = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID, UsuariAplicacioFields.ENTITATID.equal(entitatID) );

        // No cal filtrar per usuari-entitat, ja que sempre hi ha un usuari-aplicació
        // per defecte de l'Entitat que s'empra quan el solicitant és un usuari-entitat
        /*
        SubQuery<UsuariEntitat, String> subqueryPerson = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIENTITATID, UsuariEntitatFields.ENTITATID.equal(entitatID) );
        Where w = Where.OR(SOLICITANTUSUARIENTITAT1ID.in(subqueryPerson),
            SOLICITANTUSUARIAPLICACIOID.in(subqueryApp));
        return w;*/

        return SOLICITANTUSUARIAPLICACIOID.in(subqueryApp);

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            " No hi ha codi per gestionar el getAdditionalCondition de les Peticions"
            + " de Firma amb TipusSolicitant " + getTipusSolicitant());
    }
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {

    //long start = System.currentTimeMillis();

    final Integer politicaCustodia;

    List<Long> peticionsIDsAmbAvis = null;

    TipusSolicitant tipusSolicitant = getTipusSolicitant();

    LoginInfo loginInfo = LoginInfo.getInstance();
    EntitatJPA entitat = loginInfo.getEntitat();
    String entitatID = loginInfo.getEntitatID();
    
    int titleLength = PropietatGlobalUtil.getMaxPeticioTitleLength(entitatID);
    
	Map<Long, String> mapPF;
    mapPF = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PETICIODEFIRMA_TITOL).getValueMap();
    mapPF.clear();
      
	for (PeticioDeFirma pf : list) {
		long pk = pf.getPeticioDeFirmaID();
		String pfTitol = pf.getTitol();
		String pfTitolCut = "";
		if (pfTitol != null) {
			pfTitolCut = (pfTitol.length() > titleLength) ? pfTitol.substring(0, titleLength) + "..." : pfTitol;
		}

		pfTitol = StringEscapeUtils.escapeXml(pfTitol);
        pfTitolCut = StringEscapeUtils.escapeXml(pfTitolCut);

        String pfTitolView =(titleLength>0)?"<a href=\"#\" data-toggle=\"tooltip\" title=\"" + pfTitol + "\">" + pfTitolCut + "</a>":pfTitol;
		mapPF.put(pk, pfTitolView);
	}
    

    switch (tipusSolicitant) {

      case SOLICITANT_WEB: {
        // Llista amb les peticions finalitzades o rebutjades que l'usuari
        // encara no ha marcat com ja revisada
        //  XYZ ZZZ ZZZ Optimitzar cridades BBDD només amb IDs del llistat 
//        List<Long> peticioIDs = new ArrayList<Long>();
//        
//        for (PeticioDeFirma peticioDeFirma : list) {
//          peticioIDs.add(peticioDeFirma.getPeticioDeFirmaID());
//        }

        String usuariEntitat = loginInfo.getUsuariEntitatID();

        Where w = Where.AND(
            //PeticioDeFirmaFields.PETICIODEFIRMAID.in(peticioIDs),
            PeticioDeFirmaFields.SOLICITANTUSUARIENTITAT1ID.equal(usuariEntitat),
            PeticioDeFirmaFields.AVISWEB.equal(true));

        peticionsIDsAmbAvis = peticioDeFirmaEjb.executeQuery(PETICIODEFIRMAID, w);

        politicaCustodia = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUE(
            usuariEntitat, entitat);

      }
      break;

      case SOLICITANT_APLICACIO:
      case SOLICITANT_TOTS: {
        // Administrador d'Entitat - USUARI APLICACIO
        {

          final Where w1 = UsuariAplicacioFields.ACTIU.equal(true);
          final Where w2 = UsuariAplicacioFields.ENTITATID.equal(entitatID);

          List<String> _listSKV = usuariAplicacioEjb.executeQuery(
              UsuariAplicacioFields.USUARIAPLICACIOID, Where.AND(w1, w2));

          java.util.Collections.sort(_listSKV, String.CASE_INSENSITIVE_ORDER);

          mav.addObject("listOfUsuariAplicacio", _listSKV);
        }
        {
          final int[] estatsFirmaAsyncPerApp = { ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1,
              ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2 };

          List<StringKeyValue> origens = new ArrayList<StringKeyValue>();
          for (int i = 0; i < estatsFirmaAsyncPerApp.length; i++) {

            origens.add(new StringKeyValue(String.valueOf(estatsFirmaAsyncPerApp[i]),
                I18NUtils.tradueix("origenpeticiodefirma." + estatsFirmaAsyncPerApp[i])));

          }
          mav.addObject("listOfOrigenPeticioDeFirma", origens);
        }

        // Cada petició depen d'un usuari-aplicacio amb una politica de custodia diferent
        // Es cercarà la politica dins del buble per cada Usr App.
        politicaCustodia = null;

      }
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            " No hi ha codi per gestionar el PostList de les Peticions de Firma "
                + "amb getTipusSolicitant() =  " + getTipusSolicitant());
    }


    // ================================================
    // ============= COLUMNA SOLICITANT ===============
    // ================================================
    if (tipusSolicitant != TipusSolicitant.SOLICITANT_WEB) {

      Map<Long, String> mapSoli;
      mapSoli = (Map<Long, String>) filterForm.getAdditionalField(SOLICITANT).getValueMap();
      mapSoli.clear();
  
      Long key;
  
      Map<String, String> mapWeb = filterForm
          .getMapOfUsuariEntitatForSolicitantUsuariEntitat1ID();
      Map<String, String> mapApp = filterForm
          .getMapOfUsuariAplicacioForSolicitantUsuariAplicacioID();
  
      for (PeticioDeFirma peticio : list) {
        key = peticio.getPeticioDeFirmaID();
  
        // Nous camps de Peticio de Firma #281
        switch (peticio.getOrigenPeticioDeFirma()) {
          case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
            mapSoli.put(key,
                "<small><b>WEB:</b> " + mapWeb.get(peticio.getSolicitantUsuariEntitat1ID())
                    + "</small>");
          break;
  
          case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
            mapSoli.put(key,
                "<small><b>WS_V1:</b> " + mapApp.get(peticio.getSolicitantUsuariAplicacioID())
                    + "</small>");
          break;
  
          case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
            mapSoli.put(
                key,
                "<small><b>ASYNC_SIMPLE_V2:</b> "
                    + mapApp.get(peticio.getSolicitantUsuariAplicacioID()) + "</small>");
          break;
  
          default:
            // XYZ ZZZ TRA
            HtmlUtils.saveMessageError(
                request,
                "No hi ha codi per el PostList de les Peticions de Firma amb Origen "
                    + I18NUtils.tradueix("origenpeticiodefirma."
                        + peticio.getOrigenPeticioDeFirma()));
        }
  
      }
    }
    
    
    
    // ================================================
    // =============== OPCIONS BY PK  =================
    // ================================================

    filterForm.getAdditionalButtonsByPK().clear();
    filterForm.getAdditionalInfoForActionsRendererByPK().clear();

    int noIniciat = 0;
    int deleteCount = 0;
    int pausarCount = 0;
    int firmatCount = 0;
    int reiniciableCount = 0;
    int marcarRevisatCount = 0;
    boolean mostrarBotoGlobalRebutjar = false;

    Map<Long, String> mapAdaptat = new HashMap<Long, String>();
    Map<Long, String> mapOriginal = new HashMap<Long, String>();

    for (PeticioDeFirma pf : list) {

      PeticioDeFirmaJPA peticioDeFirma = (PeticioDeFirmaJPA) pf;

      final Long peticioDeFirmaID = peticioDeFirma.getPeticioDeFirmaID();
      /*
       * Utils.TIPUSESTATPETICIODEFIRMA_NOINICIAT = 0; Utils.TIPUSESTATPETICIODEFIRMA_ENPROCESS
       * = 1; Utils.TIPUSESTATPETICIODEFIRMA_PAUSAT = 2;
       * Utils.TIPUSESTATPETICIODEFIRMA_REBUTJAT = 3; Utils.TIPUSESTATPETICIODEFIRMA_FIRMAT =
       * 4;
       */
      boolean avisweb;
      if (tipusSolicitant == TipusSolicitant.SOLICITANT_WEB) {
        avisweb = peticionsIDsAmbAvis.contains(peticioDeFirmaID);

      } else {
        avisweb = false;
      }

      long estat = peticioDeFirma.getTipusEstatPeticioDeFirmaID();

      String botomenu;
      if (avisweb) {
        botomenu = "btn-warning";
      } else {

        switch ((int) estat) {
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT:
            botomenu = ""; // BLANC
          break;
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES:
            botomenu = "btn-primary"; // BLAU
          break;

          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
            botomenu = "btn-danger"; // Vermell
          break;

          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT:
            botomenu = "btn-success"; // Verd
          break;

          default:
            botomenu = "btn-inverse"; // Negre CAS DESCONEGUT
        }
      }

      filterForm.addAdditionalInfoForActionsRendererByPK(peticioDeFirmaID, botomenu);
      
      /* NETEJA DE FITXERS ORIGINALS i ADAPTATS */
      if (tipusSolicitant != TipusSolicitant.SOLICITANT_WEB && 
          ((estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT)
               || (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT))) {
          // Afegir contingut a columna        
          if (peticioDeFirma.getFitxerAFirmarID() != null) {
            mapOriginal.put(peticioDeFirmaID, "<i class=\"fas fa-fire\"></i>");
            if (peticioDeFirma.getFitxerAdaptatID() != null) {
              mapAdaptat.put(peticioDeFirmaID, "<i class=\"fas fa-share-square\"></i>");              
            }
          }
      }

      if (avisweb) {
        marcarRevisatCount++;
        /* MARCAR REVISAT */
        filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
            "far fa-check-square icon-white", "revisat", getContextWeb() + "/revisat/"
                + peticioDeFirmaID, "btn-warning"));
      }

      /* VEURE DOC */
      filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
          "far fa-file icon-white", "veuredoc",
          // getContextWeb() + "/docfirmat/" + peticioDeFirmaID,
          "javascript:var win = window.open('" + request.getContextPath() + getContextWeb()
              + "/docfirmat/" + peticioDeFirmaID + "', '_blank'); win.focus();", "btn-info"));

      /* DESCARREGAR DOC */
      filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
          "fas fa-download icon-white", "descarregardoc",
          // getContextWeb() + "/docfirmat/" + peticioDeFirmaID,
          "javascript:var win = window.open('" + request.getContextPath() + getContextWeb()
              + "/docfirmat/descarregar/" + peticioDeFirmaID + "', '_blank'); win.focus();",
          "btn-info"));

      // PETICIO DE FIRMA i FLUX DE FIRMA
      if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT && !isNomesConsulta()) {
          /* PETICIO EDITAR */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
              new AdditionalButton("fas fa-pencil-alt icon-white", "peticiodefirma.editar",
                  "javascript:goTo('" + request.getContextPath() + getContextWeb() + "/"
                      + peticioDeFirmaID + "/edit')", "btn-warning"));
          /* FLUX EDITAR */
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
              new AdditionalButton("/img/fluxicon.png", "fluxDeFirmes.editar",
                  "javascript:goTo('" + request.getContextPath() + getFluxPath() + "/"
                      + peticioDeFirma.getFluxDeFirmesID() + "/edit?redirectOnModify="
                      + getContextWeb() + "/list')", "btn-warning"));
      } else {
        /* FLUXDEFIRMES */
        filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
            "fas fa-eye icon-white", "peticiodefirma.veuredetalls", getContextWeb() + "/"
                + peticioDeFirmaID + "/edit", "btn-info"));

        filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
            "/img/fluxicon.png", "fluxDeFirmes.fluxDeFirmes", getFluxPath() + "/view/"
                + peticioDeFirma.getFluxDeFirmesID() + "?redirectOnModify=" + getContextWeb()
                + "/list&readOnly=true", "btn-info"));
      }
      
      
      // ANNEXES
      {
        String type;
        if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          type = "btn-warning";
        } else {
          type = "btn-info";
        }
      
        // Afegim Boto de gestió d'Annexes
        filterForm.addAdditionalButtonByPK(peticioDeFirmaID, 
            new AdditionalButton("icon-white far fa-file", "annex.annex.plural",
            getContextWeb() + "/gestioannexes/{0}", type));
      }
      
      

      // <%-- CUSTODIA --%>
      {

        if (estat != ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
          if (peticioDeFirma.getCustodiaInfoID() != null) {
            /* VEURE CUSTODIA */
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
                new AdditionalButton("/img/custodia.png", "custodia.view", 
                    getContextWeb() + "/veurecustodiainfo/" + peticioDeFirma.getCustodiaInfoID(),
                    "btn-info"));

          }
        }

        if (!isNomesConsulta()) {
          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
            if (peticioDeFirma.getCustodiaInfoID() == null) {
  
              Integer pc = politicaCustodia;
  
              if (pc == null) {
  
                if (peticioDeFirma.getOrigenPeticioDeFirma() == ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB) {
                  pc = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUE(
                      peticioDeFirma.getSolicitantUsuariEntitat1ID(), entitat);
                } else {
                  // XYZ ZZZ Falta Cache temporal de politiques per usuari Aplicacio!!!
                  pc = custodiaInfoLogicaEjb.getPoliticaDeCustodiaFinalPerUA(
                      peticioDeFirma.getSolicitantUsuariAplicacioID(), entitat);
                }
              }
  
              if (pc != null && pc != ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE) {
                /* CREAR CUSTODIA */
                filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
                    new AdditionalButton("/img/custodia.png", "custodia.crear",
                         getContextWeb() + "/afegircustodiainfo/" + peticioDeFirmaID,
                         "btn-warning"));
              }
            } else {
  
              /* MODIFICAR CUSTODIA */
              if (!isNomesConsulta()) {
                filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
                  new AdditionalButton("/img/custodia.png", "custodia.modificar",
                      getContextWeb() + "/editarcustodiainfo/" + peticioDeFirma.getCustodiaInfoID(),
                      "btn-warning"));
              }
  
            }
          }
        }
      }

      // <%-- FINAL CUSTODIA --%>



      if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT
          || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
        /* INICIAR */
        if (!isNomesConsulta()) {
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "fas fa-play icon-white", "iniciar", "javascript:goTo('" + request.getContextPath()
                  + getContextWeb() + "/iniciar/" + peticioDeFirmaID + "')", "btn-success"));
  
          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
            noIniciat++;
          }
        }

      }

      if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES) {
        /* PAUSAR */
        if (!isNomesConsulta()) {
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "fas fa-pause icon-white", "pausar", "javascript:goTo('" + request.getContextPath()
                  + getContextWeb() + "/pausar/" + peticioDeFirmaID + "')", "btn-warning"));
  
          pausarCount++;
        }
      }

      // REBUTJAR (només per AdEn)
      if (!isNomesConsulta() && tipusSolicitant != TipusSolicitant.SOLICITANT_WEB) {

        if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
            || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
          mostrarBotoGlobalRebutjar = true;
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
              "fas fa-times", "rebutjar", "javascript:rebutjar(" + peticioDeFirmaID + ");",
              "btn-warning"));
        }
      }
      
      
      
      /** CLONAR */
      if (peticioDeFirma.getFitxerAFirmarID() != null) {
        if (!isNomesConsulta()) {
          filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
            "fas fa-random", "clonar", "javascript:goTo('" + request.getContextPath()
                + getContextWeb() + "/clonar/" + peticioDeFirmaID + "')", "btn-secondary"));
        }

        if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT
            || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT
            || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {

          /** REINICIALITZAR */
          if (!isNomesConsulta()) {
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID,
                new AdditionalButton("fas fa-redo icon-white", "reinicialitzar",
                    "javascript:goTo('" + request.getContextPath() + getContextWeb()
                        + "/reinicialitzar/" + peticioDeFirmaID + "')", "btn-danger"));
            
            if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT
                || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT) {
              reiniciableCount++;
            }
          }
          

          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT) {
            firmatCount++;
          }

        } else {
          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
            if (!isNomesConsulta()) {
              reiniciableCount++;
            }
          }
        }
      }
      
      /* === Esborrar === */
      if (!isNomesConsulta()) {
        if (tipusSolicitant == TipusSolicitant.SOLICITANT_WEB) {
          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
              || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
           // NO Boto Esborrar
         } else {
           filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
               "fas fa-trash icon-white", "genapp.delete",
               // "javascript:goTo('" + request.getContextPath() + "/" + getContextWeb() + "/" +
               // peticioDeFirmaID + "/delete')",
               "javascript:openModal('" + request.getContextPath() + getContextWeb() + "/"
                   + peticioDeFirmaID + "/delete','show');", "btn-danger"));
           deleteCount++;
         }
         
        } else {

          if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES
               || estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT) {
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
                "fas fa-trash icon-white", "genapp.delete",
                "javascript:rebutjarEsborrar(" + peticioDeFirmaID + ");", "btn-danger"));
          } else {
            filterForm.addAdditionalButtonByPK(peticioDeFirmaID, new AdditionalButton(
                "fas fa-trash icon-white", "genapp.delete",
                "javascript:esborrarAmbMotiu(" + peticioDeFirmaID + ");", "btn-danger"));
          }
          deleteCount++;
        }
      }

    }
    // Final For de totes les peticions
    
    
    // --------------------------------------------
    // ------------- BOTONS SUPERIORS -------------
    // --------------------------------------------
    
    
   
    
    filterForm.getAdditionalButtons().clear();

    filterForm.setVisibleMultipleSelection(false);

    final int size = list.size();

    final boolean noIniciatMultiple = (size == noIniciat && size != 0);

    final boolean deleteMultiple = (size == deleteCount && size != 0);

    final boolean pausarMultiple = (size == pausarCount && size != 0);

    final boolean downloadMassiu = (size == firmatCount && size != 0);

    final boolean reiniciable = (size == reiniciableCount && size != 0);

    final boolean marcarRevisat = (marcarRevisatCount > 1);
    
    final boolean fitxersOriginals = (mapOriginal.size() != 0);
    
    final boolean fitxersAdaptats = (mapAdaptat.size() != 0);

    if (deleteMultiple || pausarMultiple || marcarRevisat || downloadMassiu
        || noIniciatMultiple || reiniciable || mostrarBotoGlobalRebutjar
        || fitxersOriginals  || fitxersAdaptats) {

      filterForm.setVisibleMultipleSelection(true);

      if (mostrarBotoGlobalRebutjar) {
        filterForm.addAdditionalButton(new AdditionalButton("fas fa-times",
            "rebutjar", "javascript:rebutjarseleccionades();", "btn-warning"));
      }

      if (deleteMultiple) {
        
        if (tipusSolicitant == TipusSolicitant.SOLICITANT_WEB) {
          filterForm.setDeleteSelectedButtonVisible(true);
        } else {
          filterForm.setDeleteSelectedButtonVisible(false);
          filterForm.addAdditionalButton(new AdditionalButton(
              "fas fa-trash icon-white", "genapp.delete",
              "javascript:esborrarAmbMotiuSeleccionades();", "btn-danger"));
        }
      } else {
        filterForm.setDeleteSelectedButtonVisible(false);
      }

      if (pausarMultiple) {
        filterForm.addAdditionalButton(new AdditionalButton("fas fa-pause icon-white", "pausar",
            "javascript:submitTo('peticioDeFirmaFilterForm'," + " '"
                + request.getContextPath() + getContextWeb() + "/pausarseleccionats');",
            "btn-warning"));
      }

      if (downloadMassiu) {
        filterForm.addAdditionalButton(new AdditionalButton("fas fa-download icon-white",
            "descarregar.firmes", "javascript:submitTo('peticioDeFirmaFilterForm'," + " '"
                + request.getContextPath() + getContextWeb() + "/downloadseleccionats');",
            "btn-success"));
      }

      if (reiniciable) {
        filterForm.addAdditionalButton(new AdditionalButton("fas fa-redo icon-white",
            "reinicialitzar", "javascript:submitTo('peticioDeFirmaFilterForm'," + " '"
                + request.getContextPath() + getContextWeb() + "/resetseleccionats');",
            "btn-danger"));
      }

      if (marcarRevisat) {
        filterForm
            .addAdditionalButton(new AdditionalButton("far fa-check-square icon-white", "revisat",
                "javascript:submitTo('peticioDeFirmaFilterForm'," + " '"
                    + request.getContextPath() + getContextWeb()
                    + "/marcarrevisatseleccionats');", "btn-warning"));
      }

      if (noIniciatMultiple) {
        filterForm.addAdditionalButton(new AdditionalButton("fas fa-play icon-white", "iniciar",
            "javascript:submitTo('peticioDeFirmaFilterForm','" + request.getContextPath()
                + getContextWeb() + "/iniciarseleccionats')", "btn-success"));
      }
      
      
      // FITXERS ADAPTATS I ORIGINALS 
      if (fitxersOriginals  || fitxersAdaptats) {
        HtmlUtils.saveMessageInfo(request,
          I18NUtils.tradueix("peticiodefirma.netejaesborrat.ajuda"));
      }


      // En el pare es fa un esborrat de tots el botons addicionals,
      // per això cada vegada les hem de tornar a afegir
      if (fitxersOriginals) {
        if (!isNomesConsulta()) {
          filterForm.addAdditionalButton(new AdditionalButton("fas fa-fire icon-white",
            "peticiodefirma.netejaesborrat.netejaroriginal",
            "javascript:submitTo('peticioDeFirmaFilterForm'," + " '" + request.getContextPath()
              + getContextWeb() + "/netejarOriginal');", "btn-danger"));
        }
        
        AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
        adfield2.setCodeName("=<i class=\"fas fa-fire\"></i>");
        adfield2.setPosition(NETEJA_ORIGINAL);
        adfield2.setEscapeXml(false);
        adfield2.setValueMap(mapOriginal);

        filterForm.addAdditionalField(adfield2);
      } else {
        filterForm.getAdditionalFields().remove(NETEJA_ORIGINAL);
      }

      if (fitxersAdaptats) {
        if (!isNomesConsulta()) {
          filterForm.addAdditionalButton(new AdditionalButton("fas fa-broom icon-white",
            "peticiodefirma.netejaesborrat.netejaradaptat",
            "javascript:submitTo('peticioDeFirmaFilterForm'," + " '" + request.getContextPath()
              + getContextWeb() + "/netejarAdaptat');", "btn-warning"));
        }
        
        AdditionalField<Long, String> adfield1 = new AdditionalField<Long, String>();
        adfield1.setCodeName("=<i class=\"fas fa-broom\"></i>");
        adfield1.setPosition(NETEJA_ADAPTAT);
        adfield1.setEscapeXml(false);
        adfield1.setValueMap(mapAdaptat);

        filterForm.addAdditionalField(adfield1);
      } else {
        filterForm.getAdditionalFields().remove(NETEJA_ADAPTAT);
      }

    }

    // Crear nou boto de Crear Petició
    if (!isNomesConsulta() && addCreateButton()) {
      String action;
      switch (tipusSolicitant) {

        case SOLICITANT_WEB:
          action = getContextWeb() + "/selectflux";
        break;

        case SOLICITANT_APLICACIO:
        case SOLICITANT_TOTS:
          action = "javascript:openSelectUserAppDialog();";
        break;

        default:
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              " No hi ha codi per gestionar el Boto de Creació de les Peticions de Firma"
                  + " amb tipusSolicitant = " + tipusSolicitant);
      }

      filterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle",
          "peticiodefirma.crear", action, "btn-secondary"));
    }

    
    // Ocultar Columna Data Final
    boolean ocultarColumna = true;   
    for (PeticioDeFirma pf : list) {
      if (pf.getDataFinal() != null) {
        ocultarColumna = false;
        break;
      }
    }
    if (ocultarColumna) {
      filterForm.addHiddenField(DATAFINAL);
    } else {
      filterForm.getHiddenFields().remove(DATAFINAL);
    }

    //long end = System.currentTimeMillis();
    //log.info("\n\n TOTAL TEMPS = " + (end - start) + " ms");

  }

  @Override
  public final String getEntityNameCodePlural() {
    return getEntityNameCode() + ".plural";
  }

  @Override
  public boolean isActiveFormNew() {
    return true;
  }

  @Override
  public boolean isActiveFormEdit() {
    return true;
  }

  @Override
  public void preValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result) throws I18NException {
/* XYZ ZZZ ZZZ
    // Si s'actualitza però no es canvia el fitxer llavors no feim res.
    CommonsMultipartFile multiPartFitxerAFirmar = peticioDeFirmaForm.getFitxerAFirmarID();

    if (multiPartFitxerAFirmar == null || multiPartFitxerAFirmar.isEmpty()) {
      return;
    }

    log.info(" MIME FITXER A FIRMAR = " + multiPartFitxerAFirmar.getContentType());

    PeticioDeFirmaJPA pf = peticioDeFirmaForm.getPeticioDeFirma();
    long fitxerID = pf.getFitxerAFirmarID();

    if (fitxerID != 0) {

      File file = FileSystemManager.getFile(fitxerID);
      File fileTmp = FileSystemManager.getTmpFile(fitxerID);

      // En aquest moment el fitxer pujat es troba en [ID] si la peticio de firma
      // és nova o en [ID].tmp si la petició existeix i s'esta actualitzant. En
      // aquest darrer cas esta en tmp ja que encara no s'ha guardat la peticio
      // i no es vol sobreescriure el fitxer original fins que es guardi la peticio.
      // Just després de guardar la petició es moura el fitxer [ID].tmp a [ID]
      // si estam en mode actualització.
      File fileToConvert = peticioDeFirmaForm.isNou() ? file : fileTmp;

      // TODO PASSAR A DEBUG
      log.info(" FILE ORIG = " + file.getAbsolutePath() + "\t" + file.exists() + "\t"
          + file.length() + "\t" + new Date(file.lastModified()));
      log.info(" FILE TEMP = " + fileTmp.getAbsolutePath() + "\t" + fileTmp.exists() + "\t"
          + fileTmp.length() + "\t" + new Date(fileTmp.lastModified()));

      Fitxer fileToConvertInfo = new FitxerBean();
      fileToConvertInfo.setMime(multiPartFitxerAFirmar.getContentType());
      fileToConvertInfo.setNom(multiPartFitxerAFirmar.getOriginalFilename());
      fileToConvertInfo.setTamany(fileToConvert.length());

      try {
        Fitxer fitxerConvertit = PdfUtils.convertToPDF(fileToConvert, fileToConvertInfo);

        if (fitxerConvertit == fileToConvertInfo) {
          // Es un PDF. No feim res.
        } else {
          // No és un PDF, ho substituim pel fitxer convertit
          Fitxer fileInfo = pf.getFitxerAFirmar();
          fileInfo.setMime(fitxerConvertit.getMime());
          fileInfo.setNom(fitxerConvertit.getNom());
          fileInfo.setTamany(fitxerConvertit.getTamany());
          // Actualitzam BBDD
          fitxerEjb.update(fileInfo);

          // Actualitzam Sistema de Fitxers
          try {
            InputStream is = fitxerConvertit.getData().getInputStream();
            FileOutputStream fos = new FileOutputStream(fileToConvert);
            FileSystemManager.copy(is, fos);
            fos.flush();
            fos.close();
          } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new I18NException("error.unknown",
                "Error substituint fitxer original per fitxer original convertiti a pdf:"
                    + e.getMessage());
          }

          // TODO PASSAR A DEBUG
          log.info(" FILE CONV = " + fileToConvert.getAbsolutePath() + "\t"
              + fileToConvert.exists() + "\t" + fileToConvert.length() + "\t"
              + new Date(fileToConvert.lastModified()));
        }
      } catch (I18NException e) {
        String error = I18NUtils.getMessage(e);
        log.error("Error convertint document a pdf: " + error, e);
        result.rejectValue(get(FITXERAFIRMARID), "formatfitxer.conversio.error", error);
      }
    }
*/
  }

  @Override
  public void postValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result) throws I18NException {

    super.postValidate(request, peticioDeFirmaForm, result);

    if (result.hasErrors()) {

      FieldError fe = result.getFieldError(PeticioDeFirmaFields.FLUXDEFIRMESID.fullName);

      if (fe != null) {

        java.lang.reflect.Field f;
        try {
          f = getField(result.getClass(), "errors", true);

          f.setAccessible(true);
          List<?> errors22 = (List<?>) f.get(result);
          errors22.remove(fe);

        } catch (Throwable e) {
          // TODO
          e.printStackTrace();
        }

      }

    }

    int origen = peticioDeFirmaForm.getPeticioDeFirma().getOrigenPeticioDeFirma();
    switch (origen) {

      case ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB:
      case ORIGEN_PETICIO_DE_FIRMA_API_PORTAFIB_WS_V1:
      // NO requereixen CONFIGURACIO DE FIRMA
      break;

      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1:
      case ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2:
        // Requereixen CONFIGURACIO DE FIRMA
        if (peticioDeFirmaForm.getPeticioDeFirma().getConfiguracioDeFirmaID() == null) {
          result.rejectValue(get(CONFIGURACIODEFIRMAID), "genapp.validation.required",
              new Object[] { I18NUtils.tradueix(CONFIGURACIODEFIRMAID.fullName) },
              "Camp Requerit");
        }
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "En PostValidate de PeticioDeFirma, tenim un getOrigenPeticioDeFirma = " + origen
                + " desconegut");
    }

  }

  /**
   * Gets an accessible {@link Field} by name, breaking scope if requested.
   * Superclasses/interfaces will be considered.
   * 
   * @param cls
   *          the {@link Class} to reflect, must not be {@code null}
   * @param fieldName
   *          the field name to obtain
   * @param forceAccess
   *          whether to break scope restrictions using the
   *          {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)} method.
   *          {@code false} will only match {@code public} fields.
   * @return the Field object
   * @throws IllegalArgumentException
   *           if the class is {@code null}, or the field name is blank or empty or is matched
   *           at multiple places in the inheritance hierarchy
   */
  public static java.lang.reflect.Field getField(final Class<?> cls, final String fieldName,
      final boolean forceAccess) {

    // check up the superclass hierarchy
    for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
      try {
        final java.lang.reflect.Field field = acls.getDeclaredField(fieldName);
        // getDeclaredField checks for non-public scopes as well
        // and it returns accurate results
        if (!java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
          if (forceAccess) {
            field.setAccessible(true);
          } else {
            continue;
          }
        }
        return field;
      } catch (final NoSuchFieldException ex) { // NOPMD
        // ignore
      }
    }

    return null;

  }

}
