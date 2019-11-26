package es.caib.portafib.back.controller.rest.apifirmaasyncsimple.v2;

import es.caib.portafib.back.controller.rest.RestFirmaUtils;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.jpa.MetadadaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.PluginDeCustodiaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.datasource.ByteArrayDataSource;
import es.caib.portafib.logic.utils.datasource.FitxerIdDataSource;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.commons.lang.StringUtils;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleExternalSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleKeyValue;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimplePerson;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleValidationInfo;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created 06/06/2019 10:23
 *
 * @author anadal (u80067)
 */
@Controller
@RequestMapping(value = RestApiFirmaAsyncSimpleV2Controller.CONTEXT)
public class RestApiFirmaAsyncSimpleV2Controller extends
    RestFirmaUtils<FirmaAsyncSimpleKeyValue> {

  public static final String CONTEXT = "/common/rest/apifirmaasyncsimple/v2";

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
  private FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
  protected FitxerLogicaLocal fitxerLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = PluginDeCustodiaLogicaLocal.JNDI_NAME)
  private PluginDeCustodiaLogicaLocal pluginDeCustodiaLogicaEjb;

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;
  
  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.AVAILABLELANGUAGES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableLanguages(HttpServletRequest request,
      @RequestBody String languageUI) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {
      // LoginInfo loginInfo =
      commonChecks();

      // Check XYZ ZZZ languageUI

      List<StringKeyValue> idiomes;

      SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
          IdiomaFields.IDIOMAID.select, IdiomaFields.NOM.select);

      idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

      List<FirmaAsyncSimpleKeyValue> list = new ArrayList<FirmaAsyncSimpleKeyValue>();

      for (StringKeyValue skv : idiomes) {
        list.add(new FirmaAsyncSimpleKeyValue(skv.getKey(), skv.getValue()));
      }

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<List<FirmaAsyncSimpleKeyValue>> res;
      res = new ResponseEntity<List<FirmaAsyncSimpleKeyValue>>(list, headers, HttpStatus.OK);

      return res;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a getTypesOfDocumentsAvailable: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.AVAILABLEPROFILES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableProfiles(HttpServletRequest request,
      @RequestBody String languageUI) {

    return internalGetAvailableProfiles(request, languageUI);

  }
  
  
  

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.AVAILABLETYPESOFDOCUMENTS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableTypesOfDocuments(HttpServletRequest request,
      @RequestBody String languageUI) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // Check de commonInfo
    if (languageUI == null || languageUI.trim().length() == 0) {
      // XYZ ZZZ TRA
      return generateServerError("El parametre d'entrada languageUI no pot ser null o buit.");
    }

    try {

      LoginInfo loginInfo = commonChecks();

      // Checks usuari aplicacio
      if (log.isDebugEnabled()) {
        log.info("Usuari-APP = " + loginInfo.getUsuariAplicacio());
      }

      UsuariAplicacioJPA ua = loginInfo.getUsuariAplicacio();

      String userapp = ua.getUsuariAplicacioID();

      Where whereTD = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.equal(userapp),
          TipusDocumentFields.USUARIAPLICACIOID.isNull());

      List<TipusDocument> list = tipusDocumentEjb.select(whereTD, new OrderBy(
          TipusDocumentFields.TIPUSDOCUMENTID));

      /*
       * 
       * if (idioma == null || idioma.trim().length() != 2) { idioma = ua.getIdiomaID(); } else
       * { long count = idiomaEjb.count(Where.AND(IdiomaFields.IDIOMAID.equal(idioma),
       * IdiomaFields.SUPORTAT.equal(true))); if (count == 0) { idioma = ua.getIdiomaID(); } }
       */

      List<FirmaAsyncSimpleDocumentTypeInformation> tipus = new ArrayList<FirmaAsyncSimpleDocumentTypeInformation>();
      for (TipusDocument td : list) {

        TraduccioMapJPA tramap;
        tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(languageUI);
        if (tramap == null) {
          tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(
              Configuracio.getDefaultLanguage());
        }

        long id = td.getTipusDocumentID();
        String nom = tramap.getValor();
        long id_base = td.getTipusDocumentBaseID();
        tipus.add(new FirmaAsyncSimpleDocumentTypeInformation(id, nom, id_base));
      }

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<List<FirmaAsyncSimpleDocumentTypeInformation>> res;
      res = new ResponseEntity<List<FirmaAsyncSimpleDocumentTypeInformation>>(tipus, headers,
          HttpStatus.OK);

      return res;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a getTypesOfDocumentsAvailable: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/"
      + ApiFirmaAsyncSimple.CREATEANDSTARTSIGNATUREREQUESTWITHFLOWTEMPLATECODE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> createAndStartSignatureRequestWithFlowTemplateCode(
      HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode signatureRequest)
      throws AbstractApisIBException {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    FirmaAsyncSimpleSignatureBlock[] signatureBlocks;

    String languageUI = "ca"; // XYZ ZZZ

    try {

      LoginInfo loginInfo = commonChecks();

      if (signatureRequest == null) {
        // XYZ ZZZ TRA
        return generateServerError("La peticio de firma no pot valer null.");
      }

      // Check de commonInfo
      languageUI = signatureRequest.getLanguageUI();
      if (languageUI == null || languageUI.trim().length() == 0) {
        // XYZ ZZZ TRA
        return generateServerError("El parametre d'entrada languageUI no pot ser null o buit.");
      }

      // Per ara collirem el Codi com l'ID de les plantilles !!!!
      String codiPlantilla = signatureRequest.getFlowTemplateCode();

      long plantillaDeFluxDeFirmesID;
      try {
        plantillaDeFluxDeFirmesID = Long.parseLong(codiPlantilla);
      } catch (NumberFormatException nfe) {
        // XYZ ZZZ TRA
        return generateServerError("Durant aquesta fase de desenvolupament"
            + " requerim que els codis de plantilla de flux siguin els IDs.");
      }

      // Cercar per ID
      FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb
          .findByPrimaryKeyFullForPlantilla(plantillaDeFluxDeFirmesID);
      if (flux == null || flux.getPlantillaFluxDeFirmes() == null) {
        // XYZ ZZZ TRA
        return generateServerError("El codi de plantilla " 
           + codiPlantilla + " no existeix o no és una plantilla.");
      }
      // Check que la plantilla és de l'usuari que crida o esta compartida
      // per algun usuari-entitat o usuari-aplicacio de la pròpia entitat
      PlantillaFluxDeFirmesJPA plantilla = flux.getPlantillaFluxDeFirmes();
      if (!plantilla.getCompartir()) {
        String userapp = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();
        if (!userapp.equals(plantilla.getUsuariAplicacioID())) {
          // TODO XYZ ZZZ TRA Traduir
          String msg = "L'usuari app connectat " + userapp
              + " no té permis sobre la plantilla amd ID " + plantillaDeFluxDeFirmesID;
          log.error(msg);
          // Error desconegut: {0}
          throw new I18NException("error.unknown", msg);
        }
      }

      // Eliminar identificadors

      Set<BlocDeFirmesJPA> blocs = flux.getPlantillaFluxDeFirmes().getFluxDeFirmes()
          .getBlocDeFirmess();

      signatureBlocks = new FirmaAsyncSimpleSignatureBlock[blocs.size()];

      int count = 0;

      TreeSet<BlocDeFirmesJPA> blocsOrdered = new TreeSet<BlocDeFirmesJPA>(
          new ComparatorBlocDeFirmesJPA());
      blocsOrdered.addAll(blocs);

      for (BlocDeFirmesJPA blocDeFirmes : blocsOrdered) {

        int minimumNumberOfSignaturesRequired = blocDeFirmes.getMinimDeFirmes();

        List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();

        for (FirmaJPA firma : blocDeFirmes.getFirmas()) {
          

          List<FirmaAsyncSimpleReviser> revisers = null;
          Set<RevisorDeFirmaJPA> revisorsJPA = firma.getRevisorDeFirmas();
          if (revisorsJPA != null && revisorsJPA.size() != 0) {

            revisers = new ArrayList<FirmaAsyncSimpleReviser>();

            for (RevisorDeFirmaJPA revisorDeFirmaJPA : revisorsJPA) {
              FirmaAsyncSimplePerson person = new FirmaAsyncSimplePerson();
              person.setIntermediateServerUsername(revisorDeFirmaJPA.getUsuariEntitatID());
              revisers.add(new FirmaAsyncSimpleReviser(person, revisorDeFirmaJPA
                  .isObligatori()));
            }
          }
          
          FirmaAsyncSimpleSigner personToSign = new FirmaAsyncSimpleSigner();
          UsuariEntitatJPA ue = firma.getUsuariEntitat();
          
          if (ue.getCarrec() == null) {
            // NO es carrec
            if (ue.getUsuariPersona().isUsuariIntern()) {
              // Usuari Intern
              personToSign.setIntermediateServerUsername(ue.getUsuariEntitatID());
            } else {
              //Si usuari extern llavors omplir tot lo de usuari extern
              UsuariPersonaJPA persona = ue.getUsuariPersona();
              FirmaAsyncSimpleExternalSigner exSigner = new FirmaAsyncSimpleExternalSigner(
                  persona.getNif(),
                  firma.getUsuariExternNom() == null? persona.getNom() : firma.getUsuariExternNom(),
                  firma.getUsuariExternLlinatges() == null? persona.getLlinatges() : firma.getUsuariExternLlinatges(),
                  firma.getUsuariExternEmail() == null? persona.getEmail() : firma.getUsuariExternEmail(),
                  firma.getUsuariExternIdioma() == null? persona.getIdiomaID() : firma.getUsuariExternIdioma(),
                  firma.getUsuariExternNivellSeguretat());
              personToSign.setExternalSigner(exSigner);
            }
          } else {
            personToSign.setPositionInTheCompany(ue.getUsuariEntitatID());
          }

          boolean required = firma.isObligatori();

          String reason = firma.getMotiu();
          int minimumNumberOfRevisers = firma.getMinimDeRevisors();

          FirmaAsyncSimpleSignature sign = new FirmaAsyncSimpleSignature(personToSign,
              required, reason, minimumNumberOfRevisers, revisers);
          
          signers.add(sign);

        }

        signatureBlocks[count] = new FirmaAsyncSimpleSignatureBlock(
            minimumNumberOfSignaturesRequired, signers);
        count++;
      }

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);
      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a createAndStartSignatureRequestWithFlowTemplateCode: "
          + th.getMessage();
      log.error(msg, th);

      return generateServerError(msg, th);
    }

    FirmaAsyncSimpleSignatureRequestWithSignBlockList sr = new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
        signatureRequest, signatureBlocks);

    return createAndStartSignatureRequest(request, sr);

  }

  public class ComparatorBlocDeFirmesJPA implements Comparator<BlocDeFirmesJPA> {

    @Override
    public int compare(BlocDeFirmesJPA o1, BlocDeFirmesJPA o2) {

      return o1.getOrdre() - o2.getOrdre();
    }

  }

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.CREATEANDSTARTSIGNATUREREQUESTWITHSIGNBLOCKLIST, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> createAndStartSignatureRequest(HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }
    String languageUI = "ca"; // XYZ ZZZ
    Set<Long> fitxersCreats = new HashSet<Long>();
    try {
      LoginInfo loginInfo = commonChecks();

      if (signatureRequest == null) {
        // XYZ ZZZ TRA
        return generateServerError("La peticio de firma no pot valer null.");
      }

      // Check de commonInfo
      languageUI = signatureRequest.getLanguageUI();
      if (languageUI == null || languageUI.trim().length() == 0) {
        // XYZ ZZZ TRA
        return generateServerError("El paràmetre d'entrada languageUI no pot ser null o buit.");
      }

      FirmaAsyncSimpleFile fileToConvertInfo = signatureRequest.getFileToSign();

      if (fileToConvertInfo == null) {
        throw new I18NException("genapp.validation.required",
            PeticioDeFirmaFields.FITXERAFIRMARID.fullName);
      }

      PeticioDeFirmaJPA peticioDeFirmaJPA = signatureRequestToPeticioDeFirmaJPAFull(
          signatureRequest, loginInfo.getUsuariAplicacio(), loginInfo.getEntitat(),
          fitxersCreats, languageUI);

      // Convertir Fitxers
      /*
       * Long fitxerAFirmarID = peticioDeFirmaJPA.getFitxerAFirmarID(); {
       * 
       * File fileToConvert = FileSystemManager.getFile(fitxerAFirmarID); Fitxer
       * fitxerConvertit = PdfUtils.convertToPDF(fileToConvert,
       * JPAConversion.toJPA(fileToConvertInfo));
       * 
       * if (fitxerConvertit == fileToConvertInfo) { // Es un PDF. // No feim res } else { //
       * No és un PDF, ho substituim pel fitxer convertit
       * 
       * // Actualitzam el Fitxer a firmar InputStream is =
       * fitxerConvertit.getData().getInputStream(); FileOutputStream fos = new
       * FileOutputStream(fileToConvert); try { FileSystemManager.copy(is,fos); } finally { try
       * { is.close(); } catch(Throwable th) {} } fos.flush(); fos.close(); // Canviar BBDD
       * Fitxer f = fitxerLogicaEjb.findByPrimaryKey(fitxerAFirmarID); f.setNom(f.getNom()+
       * ".pdf"); f.setMime(Constants.PDF_MIME_TYPE); f.setTamany(fitxerConvertit.getTamany());
       * 
       * fitxerLogicaEjb.update(f); }
       * 
       * }
       */
      // Final Convertir Fitxer

      peticioDeFirmaJPA = peticioDeFirmaLogicaEjb.createFull(peticioDeFirmaJPA);

      //System.gc();

      long peticioDeFirmaID = peticioDeFirmaJPA.getPeticioDeFirmaID();
      
      UsuariAplicacioJPA ua = loginInfo.getUsuariAplicacio();

      try {
        peticioDeFirmaLogicaEjb.start(peticioDeFirmaJPA.getPeticioDeFirmaID(), true, ua.getUsuariAplicacioID());
      } catch (I18NException th) {
        deletePeticioDeFirma(peticioDeFirmaID, loginInfo.getUsuariAplicacio()
            .getUsuariAplicacioID());
        throw th;
      }

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<Long> res;
      res = new ResponseEntity<Long>(peticioDeFirmaID, headers, HttpStatus.OK);

      return res;
    } catch (org.fundaciobit.genapp.common.i18n.I18NValidationException ve) {

      String msg = I18NLogicUtils.getMessage(ve, new Locale(languageUI));
      fitxerLogicaEjb.cleanSet(fitxersCreats);
      log.error(msg, ve);
      return generateServerError(msg);

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
      fitxerLogicaEjb.cleanSet(fitxersCreats);
      log.error(msg, i18ne);
      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a createAndStartSignatureRequest: "
          + th.getMessage();
      log.error(msg, th);
      fitxerLogicaEjb.cleanSet(fitxersCreats);
      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.SIGNATUREREQUESTSTATE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getSignatureRequestState(HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestInfo info) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ ZZZ
    // Check info i info.getlanguage
    String languageUI = info.getLanguageUI();

    try {
      LoginInfo loginInfo = commonChecks();

      long peticioDeFirmaID = info.getSignatureRequestID();

      // Check propietari
      checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, loginInfo);

      Integer estat = peticioDeFirmaLogicaEjb.executeQueryOne(
          PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID,
          PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      if (estat == null) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "La peticio de Firma amb ID "
            + peticioDeFirmaID + " no existeix.");

      }

      String message = null;
      if (estat == ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT) {

        message = peticioDeFirmaLogicaEjb.executeQueryOne(PeticioDeFirmaFields.MOTIUDEREBUIG,
            PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      }

      FirmaAsyncSimpleSignatureRequestState state;
      state = new FirmaAsyncSimpleSignatureRequestState(estat, message);

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<FirmaAsyncSimpleSignatureRequestState> res;
      res = new ResponseEntity<FirmaAsyncSimpleSignatureRequestState>(state, headers,
          HttpStatus.OK);

      return res;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a " + ApiFirmaAsyncSimple.SIGNATUREREQUESTSTATE
          + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }

  /**
   * @return El darrer fitxer firmat si la petició esta en marxa i algu ha firmat, els fitxer
   *         adaptat si la petició esta en marxa i ningú ha firmat o el fitxer original si la
   *         peticio no s'ha iniciat.
   */
  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.SIGNEDFILEOFSIGNATUREREQUEST, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getSignedFileOfSignatureRequest(HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestInfo info) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ ZZZ
    // Check info i info.getlanguage
    String languageUI = info.getLanguageUI();
    try {
      LoginInfo loginInfo = commonChecks();

      long peticioDeFirmaID = info.getSignatureRequestID();

      // Check propietari
      checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, loginInfo);

      FitxerJPA fitxerJPA = peticioDeFirmaLogicaEjb
          .getLastSignedFileOfPeticioDeFirma(peticioDeFirmaID);

      FirmaAsyncSimpleFile signedFile = new FirmaAsyncSimpleFile(fitxerJPA.getNom(),
          fitxerJPA.getMime(), FileSystemManager.getFileContent(fitxerJPA.getFitxerID()));

      PeticioDeFirmaJPA peticioDeFirma = (PeticioDeFirmaJPA) peticioDeFirmaLogicaEjb
          .findByPrimaryKeyFullWithUserInfo(peticioDeFirmaID);

      int signOperation = peticioDeFirma.getTipusOperacioFirma();
      String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(peticioDeFirma
          .getTipusFirmaID());
      String signAlgorithm = SignatureUtils.convertSignAlgorithmID(peticioDeFirma
          .getAlgorismeDeFirmaID());
      Integer signMode = SignatureUtils.convertPortafibSignMode2ApiSignMode(peticioDeFirma
          .getModeDeFirma());
      int signaturesTableLocation = peticioDeFirma.getPosicioTaulaFirmesID();
      boolean timeStampIncluded = peticioDeFirma.isSegellatDeTemps();

      String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

      

      // Obtenir de la petició de firma la informació de la POlitica de Firma Utilitzada
      final Boolean policyIncluded;
      {
        UsuariAplicacioConfiguracioJPA config;
        config = configuracioUsuariAplicacioLogicaLocalEjb.findByPrimaryKey(peticioDeFirma.getConfiguracioDeFirmaID());
        if (SignatureUtils.getPolicyInfoSignature(LoginInfo.getInstance().getEntitat(), config) == null) {
          policyIncluded = false;
        } else {
          policyIncluded = true;
        }
      }

      FirmaAsyncSimpleCustodyInfo custodyInfo;
      Long custodiInfoId = peticioDeFirma.getCustodiaInfoID();
      if (custodiInfoId == null) {
        custodyInfo = null;
      } else {

        CustodiaInfoJPA custodiaInfoJPA = peticioDeFirma.getCustodiaInfo(); 

        String custodyID = custodiaInfoJPA.getCustodiaDocumentID();
        // Això és plugin.getValidationFileUrl();
        String custodyFileURL = custodiaInfoJPA.getUrlFitxerCustodiat();

        // PORTAFIB2: Nous camps de CustodyInfo #280
        final String csv = custodiaInfoJPA.getCsv();
        final String csvGenerationDefinition = custodiaInfoJPA.getCsvGenerationDefinition();
        final String csvValidationWeb = custodiaInfoJPA.getCsvValidationWeb();
        final String originalFileDirectURL = custodiaInfoJPA.getOriginalFileDirectUrl();
        final String printableFileDirectUrl = custodiaInfoJPA.getPrintableFileDirectUrl();
        final String eniFileDirectUrl = custodiaInfoJPA.getEniFileDirectUrl();

        custodyInfo = new FirmaAsyncSimpleCustodyInfo(custodyID, csv, csvValidationWeb, custodyFileURL,
            csvGenerationDefinition, originalFileDirectURL,printableFileDirectUrl,eniFileDirectUrl,
            custodiaInfoJPA.getExpedientArxiuId(), custodiaInfoJPA.getDocumentArxiuId());
      }

      FirmaAsyncSimpleValidationInfo validationInfo = null;
      List<FirmaAsyncSimpleSignerInfo> signers;
      String eniPerfilFirma = null;
      {
        
        List<FirmaJPA> firmes = estatDeFirmaLogicaEjb.getFirmesWithEstatDeFirmaFirmatOfPeticio(peticioDeFirmaID);

        if (firmes == null || firmes.size() == 0) {
          signers = null;
        } else {
          signers = new ArrayList<FirmaAsyncSimpleSignerInfo>(firmes.size());

          for (FirmaJPA firma : firmes) {
            // XYZ ZZZ ZZZ
            // FALTA API firma Simple per Signatures Asíncrones #224
            // eEMGDE17.2 - ROL DE FIRMA: Valida, Refrenda, Testimonia.
            String eniRolFirma = null;
            // eEMGDE17.5.4 – NIVEL DE FIRMA: Nick, PIN ciudadano, Firma electrónica avanzada,
            // Claves concertadas, Firma electrónica avanzada basada en certificados, CSV
            String eniSignLevel = null;
            
            EstatDeFirmaJPA estat = firma.getEstatDeFirmas().iterator().next();

            UsuariPersonaJPA up = estat.getUsuariEntitat().getUsuariPersona();
            String eniSignerName = up.getNom() + " " + up.getLlinatges();
            String eniSignerAdministrationId = up.getNif();
            Date signDate =  new Date(estat.getDataFi().getTime());
            
            BigInteger ns = firma.getNumeroSerieCertificat();
            String serialNumberCert;
            if (ns == null) {
              serialNumberCert = null;
            } else {
              serialNumberCert = ns.toString();
            }
            String issuerCert = firma.getEmissorCertificat();
            String subjectCert = firma.getNomCertificat();
            List<FirmaAsyncSimpleKeyValue> additionInformation = null;
            
            FirmaAsyncSimpleSignerInfo signerInfo = new FirmaAsyncSimpleSignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel, signDate, serialNumberCert, issuerCert, subjectCert, additionInformation);
            signers.add(signerInfo);      
          }

          // Nous camps a Firma i a Peticio de Firma #281
          // Obtenir la informació del darrer fitxer signat

          // La darrera firma està en el primer lloc
          FirmaJPA firma = firmes.get(0);
          if (log.isDebugEnabled()) {
            log.debug("NUMERO DE FIRMA ES " + firma.getNumFirmaDocument() );
          }
          eniPerfilFirma = firma.getPerfilDeFirma();

          validationInfo = new FirmaAsyncSimpleValidationInfo(
            firma.getCheckAdministrationIdOfSigner(),
            firma.getCheckDocumentModifications(),
            firma.getCheckValidationSignature());
        }
      }

      FirmaAsyncSimpleSignedFileInfo signedFileInfo = new FirmaAsyncSimpleSignedFileInfo(
          signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
          timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signers,
          custodyInfo, validationInfo);

      FirmaAsyncSimpleSignedFile response = new FirmaAsyncSimpleSignedFile(signedFile,
          signedFileInfo);

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<FirmaAsyncSimpleSignedFile> res;
      res = new ResponseEntity<FirmaAsyncSimpleSignedFile>(response, headers, HttpStatus.OK);

      return res;
    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a " + ApiFirmaAsyncSimple.SIGNATUREREQUESTSTATE
          + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }



  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.DELETESIGNATUREREQUEST, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> deletePeticioDeFirma(HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestInfo info) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ ZZZ
    // Check info i info.getlanguage
    String languageUI = info.getLanguageUI();
    try {
      LoginInfo loginInfo = commonChecks();

      long peticioDeFirmaID = info.getSignatureRequestID();

      checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, loginInfo);

      deletePeticioDeFirma(peticioDeFirmaID, loginInfo.getUsuariAplicacio()
          .getUsuariAplicacioID());

      HttpHeaders headers = addAccessControllAllowOrigin();

      // NULL enlloc de no retornar res
      ResponseEntity<?> res;// = new ResponseEntity(HttpStatus.NO_CONTENT);;
      res = new ResponseEntity<String>(null, headers, HttpStatus.OK);

      return res;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a " + ApiFirmaAsyncSimple.SIGNATUREREQUESTSTATE
          + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }

  protected void deletePeticioDeFirma(long peticioDeFirmaID, String usrApp)
      throws I18NException {

    Set<Long> fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariAplicacio(
        peticioDeFirmaID, usrApp);
    FileSystemManager.eliminarArxius(fitxers);
  }

  protected void checkIfPeticioDeFirmaIsPropertyOfUsrApp(long peticioDeFirmaID,
      LoginInfo loginInfo) throws I18NException {

    checkIfPeticioDeFirmaIsPropertyOfUsrApp(peticioDeFirmaID, loginInfo.getUsuariAplicacio()
        .getUsuariAplicacioID(), loginInfo.hasRole(ConstantsV2.ROLE_ADMIN));
  }

  /**
   * Check propietari de la peticio de firma
   * 
   * @param peticioDeFirmaID
   * @throws I18NException
   */
  protected void checkIfPeticioDeFirmaIsPropertyOfUsrApp(long peticioDeFirmaID,
      String userapp, boolean hasRoleAdmin) throws I18NException {

    if (!hasRoleAdmin) {

      String usrappPropietari = peticioDeFirmaLogicaEjb.executeQueryOne(
          PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID,
          PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticioDeFirmaID));

      if (!userapp.equals(usrappPropietari)) {
        throw new I18NException("peticiodefirma.error.nopropietari", userapp,
            String.valueOf(peticioDeFirmaID));
      }
    }

  }

  protected PeticioDeFirmaJPA signatureRequestToPeticioDeFirmaJPAFull(
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest,
      UsuariAplicacioJPA usrapp, EntitatJPA entitatJPA, 
      Set<Long> fitxersCreats, String languageUI) throws I18NException, I18NValidationException {

    // De Perfil de Firma i Configuracio de Firma
    // XYZ ZZZ ZZZ
    UsuariAplicacioConfiguracioJPA config;
    config = configuracioUsuariAplicacioLogicaLocalEjb
        .getConfiguracioFirmaPerApiFirmaAsyncSimple(usrapp.getUsuariAplicacioID(),
            signatureRequest.getProfileCode(), signatureRequest);

    // Bean
    PeticioDeFirmaJPA jpa = signatureRequestToPeticioDeFirmaJPA(signatureRequest, usrapp,
        entitatJPA, config);

    // Fitxer
    if (signatureRequest.getFileToSign() == null) {
      throw new I18NException("genapp.validation.required", new I18NArgumentCode(
          PeticioDeFirmaFields.FITXERAFIRMARID.fullName));
    } else {
      FirmaAsyncSimpleFile fileToSign = signatureRequest.getFileToSign();
      FitxerJPA fitxerAFirmar = new FitxerJPA(fileToSign.getNom(), null, fileToSign.getData().length, fileToSign.getMime());
      FitxerJPA f = fitxerLogicaEjb.createFitxerField(fitxerAFirmar,
            new ByteArrayDataSource(fileToSign.getData()),
            fitxersCreats, PeticioDeFirmaFields.FITXERAFIRMARID);
      jpa.setFitxerAFirmarID(f == null ? null : f.getFitxerID());
      jpa.setFitxerAFirmar(null);
    }

    // Fitxer
    if (jpa.getLogoSegellID() != null) {
      // Logo de Segell de l'usuari Aplicacio
      FitxerJPA logoSegell = fitxerLogicaEjb.findByPrimaryKey(jpa.getLogoSegellID());
      logoSegell.setFitxerID(0);
      FitxerJPA f = fitxerLogicaEjb.createFitxerField(logoSegell,
            new FitxerIdDataSource(jpa.getLogoSegellID()),
            fitxersCreats, PeticioDeFirmaFields.LOGOSEGELLID);

      jpa.setLogoSegellID(f == null ? 0 : f.getFitxerID());
      jpa.setLogoSegell(null);
    }

    // Custodia
    jpa.setCustodiaInfo(null);
    jpa.setCustodiaInfoID(null);


    // Metadades
    List<FirmaAsyncSimpleMetadata> metadades = signatureRequest.getMetadadaList();
    if (metadades != null && metadades.size() != 0) {
      Set<MetadadaJPA> metadadesJPA = new HashSet<MetadadaJPA>();
      for (FirmaAsyncSimpleMetadata metadada : metadades) {
        metadadesJPA.add(toJPA(metadada));
      }
      jpa.setMetadadas(metadadesJPA);
    }

    // Annexs
    List<FirmaAsyncSimpleAnnex> annexs = signatureRequest.getAnnexs();
    if (annexs != null && annexs.size() != 0) {
      Set<AnnexJPA> annexsJPA = new HashSet<AnnexJPA>();
      for (FirmaAsyncSimpleAnnex annexBean : annexs) {
        annexsJPA.add(toJPA(annexBean, fitxerLogicaEjb, fitxersCreats));
      }
      jpa.setAnnexs(annexsJPA);
    }

    // Flux de firmes
    FirmaAsyncSimpleSignatureBlock[] blocks = signatureRequest.getSignatureBlocks();
    if (blocks == null || blocks.length == 0) {
      // XYZ ZZZ TRA
      final String msg = "Els blocs de firmes de la Petició de Firmes val null o està buit";
      throw new I18NException("genapp.comodi", msg);
    } else {
      jpa.setFluxDeFirmes(toJPA(blocks, entitatJPA.getEntitatID(), fitxersCreats, 
          languageUI, signatureRequest.getTitle()));
    }

    return jpa;
  }

  /**
   * 
   * @param blocks
   * @param entitatID
   * @param fitxersCreats
   * @param languageUI
   * @param titolPeticio
   * @return
   * @throws I18NException
   */
  protected FluxDeFirmesJPA toJPA(FirmaAsyncSimpleSignatureBlock[] blocks, String entitatID,
      Set<Long> fitxersCreats, String languageUI, String titolPeticio) throws I18NException {

    // Bean
    FluxDeFirmesJPA jpa = new FluxDeFirmesJPA("Flux per Petició Async " + titolPeticio);

    Set<BlocDeFirmesJPA> blocsDeFirmesJPA = new HashSet<BlocDeFirmesJPA>();
    for (int b = 0; b < blocks.length; b++) {
      FirmaAsyncSimpleSignatureBlock bloc = blocks[b];
      blocsDeFirmesJPA.add(toJPA(b, bloc, entitatID, fitxersCreats, languageUI));
    }
    jpa.setBlocDeFirmess(blocsDeFirmesJPA);

    return jpa;
  }

  protected BlocDeFirmesJPA toJPA(int ordre, FirmaAsyncSimpleSignatureBlock bloc,
      String entitatID, Set<Long> fitxersCreats, String languageUI) throws I18NException {

    if (bloc == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "El Bloc de Firmes de la posició " + ordre
          + " val NULL.");
    }

    // Bean
    BlocDeFirmesJPA jpa = new BlocDeFirmesJPA(ordre, null, 0,
        bloc.getMinimumNumberOfSignaturesRequired());
    // Firmes
    List<FirmaAsyncSimpleSignature> firmants = bloc.getSigners();
    if (firmants == null || firmants.size() == 0) {
      // XYZ ZZZ TRA
      final String msg = "Les firmes del Bloc de Firmes " + ordre + " val null o està buit";
      throw new I18NException("genapp.comodi", msg);
    }

    Set<FirmaJPA> firmesJPA = new HashSet<FirmaJPA>();
    for (FirmaAsyncSimpleSignature firmaBean : firmants) {

      firmesJPA.add(toJPA(firmaBean, entitatID, fitxersCreats, languageUI));

      jpa.setFirmas(firmesJPA);
    }

    return jpa;
  }

  protected FirmaJPA toJPA(FirmaAsyncSimpleSignature firmaBean, String entitatID,
      Set<Long> fitxersCreats, String languageUI) throws I18NException {

    if (firmaBean == null) {
      // XYZ ZZZ TRA
      final String msg = "Hi ha una firma del Bloc de Firmes que val null.";
      throw new I18NException("genapp.comodi", msg);
    }

    long firmaID = 0;

    long blocDeFirmaID = 0;
    boolean obligatori = firmaBean.isRequired();

    java.lang.Integer numFirmaDocument = null;
    int caixaPagina = -1;
    java.lang.Integer caixaX = null;
    java.lang.Integer caixaY = null;
    java.lang.Integer caixaAmple = null;
    java.lang.Integer caixaAlt = null;
    java.math.BigInteger numeroSerieCertificat = null;
    java.lang.String emissorCertificat = null;
    java.lang.String nomCertificat = null;
    java.lang.Long tipusEstatDeFirmaFinalID = null;
    boolean mostrarRubrica = false;
    java.lang.String motiu = firmaBean.getReason();
    int minimDeRevisors = firmaBean.getMinimumNumberOfRevisers();

    java.lang.String destinatariID = searchUser(firmaBean.getSigner(), entitatID,
        FirmaFields.DESTINATARIID, languageUI);
    java.lang.Long fitxerFirmatID = null;
    
    
    // External Signer
    java.lang.String extern_nom= null;
    java.lang.String extern_llinatges= null;
    java.lang.String extern_email= null;
    java.lang.String extern_idioma= null;    
    java.lang.Integer extern_nivellseguretat= null;
    java.lang.String extern_token= null;
    
    FirmaAsyncSimpleExternalSigner es = firmaBean.getSigner().getExternalSigner(); 
    if (es != null) {
      extern_nom = es.getName();
      extern_llinatges= es.getSurnames();
      extern_email= es.getEmail();
      extern_idioma= es.getLanguage();
      extern_nivellseguretat= es.getSecurityLevel();
      
      // Genera un token únic  
      extern_token = firmaLogicaEjb.getUniqueTokenForFirma();
    }

    FirmaJPA jpa = new FirmaJPA(firmaID, destinatariID, blocDeFirmaID, obligatori,
        fitxerFirmatID, numFirmaDocument, caixaPagina, caixaX, caixaY, caixaAmple, caixaAlt,
        numeroSerieCertificat, emissorCertificat, nomCertificat, tipusEstatDeFirmaFinalID,
        mostrarRubrica, motiu, minimDeRevisors, null,null,null,null,
        extern_nom , extern_llinatges , extern_email ,  extern_idioma , 
        extern_token , extern_nivellseguretat);

    List<FirmaAsyncSimpleReviser> revisors = firmaBean.getRevisers();

    if (revisors == null || revisors.size() == 0) {
      jpa.setRevisorDeFirmas(null);
    } else {

      Set<RevisorDeFirmaJPA> revisorsJPA = new HashSet<RevisorDeFirmaJPA>();

      for (FirmaAsyncSimpleReviser rev : revisors) {
        String usuariEntitatID = searchUser(rev, entitatID,
            RevisorDeFirmaFields.USUARIENTITATID, languageUI);
        RevisorDeFirmaJPA revisor = new RevisorDeFirmaJPA(usuariEntitatID, 0, rev.isRequired());
        revisorsJPA.add(revisor);
      }
      jpa.setRevisorDeFirmas(revisorsJPA);
    }
    return jpa;
  }

  /**
   * 
   * @param person
   * @param entitatID
   * @param camp
   * @return
   * @throws I18NException
   */
  protected String searchUser(FirmaAsyncSimplePerson person, String entitatID, Field<?> camp, String languageUI)
      throws I18NException {

    int count = 0;
    int type = -1;
    if (person.getAdministrationID() != null
        && person.getAdministrationID().trim().length() != 0) {
      count++;
      type = 0;
    }
    if (person.getUsername() != null && person.getUsername().trim().length() != 0) {
      count++;
      type = 1;
    }
    if (person.getIntermediateServerUsername() != null
        && person.getIntermediateServerUsername().trim().length() != 0) {
      count++;
      type = 2;
    }
    if (person.getPositionInTheCompany() != null
        && person.getPositionInTheCompany().trim().length() != 0) {
      type = 3;
      count++;
    }
    if (person instanceof FirmaAsyncSimpleSigner) {
      FirmaAsyncSimpleSigner signer = (FirmaAsyncSimpleSigner)person;
      if (signer.getExternalSigner() != null) {
        type = 4;
        count++;
      }
    }

    if (count == 0) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
          "No s´ha definit cap camp de l´objecte FirmaAsyncSimplePerson declarat "
              + camp.fullName);
    }
    

    if (count != 1) {
      // XYZ ZZZ TRA
      throw new I18NException(
          "genapp.comodi",
          "S´ha definit múltiples camps de l´objecte FirmaAsyncSimplePerson (només se´n permet un) declarat a "
              + camp.fullName);
    }

    UsuariEntitatJPA ue = null;
    switch (type) {

      case 0: // NIF
        ue = usuariEntitatLogicaEjb.findUsuariEntitatInternByNif(entitatID,
            person.getAdministrationID());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix cap usuari amb NIF "
              + person.getAdministrationID());
        }
      break;

      case 1: // Username
        ue = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatID,
            person.getUsername());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix cap usuari amb username "
              + person.getUsername());
        }

      break;

      case 2: // UsuariEntitatID
        ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getIntermediateServerUsername());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No existeix cap usuari entitat (IntermediateServerUsername) "
                  + person.getIntermediateServerUsername());
        }
        if (ue.getCarrec() != null) {
          // XYZ ZZZ TRA
          throw new I18NException(
              "genapp.comodi",
              "S´ha assignat dins IntermediateServerUsername un identificador que correspon a un càrrec (PositionInTheCompany)");
        }
      break;

      case 3: // Càrrec
        ue = usuariEntitatLogicaEjb.findByPrimaryKey(person.getPositionInTheCompany());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No existeix cap càrrec (PositionInTheCompany) "
                  + person.getIntermediateServerUsername());
        }
        // Comprovar que
        if (ue.getCarrec() == null) {
          // XYZ ZZZ TRA
          throw new I18NException(
              "genapp.comodi",
              "S´ha assignat dins càrrec (PositionInTheCompany) un identificador que correspon a un usuari entitat (IntermediateServerUsername)");
        }
      break;
      
      case 4: // Usuari Extern
      {

        FirmaAsyncSimpleSigner signer = (FirmaAsyncSimpleSigner)person;
        
        FirmaAsyncSimpleExternalSigner extSigner = signer.getExternalSigner();
        // Cercar usuari extern amb NIF
        
        
        // Check camps 
        String nif = extSigner.getAdministrationId();
        
        if (nif == null || nif.trim().length() == 0) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "El camp NIF de l'Usuari Extern val null o està buit");
        }
        
        // XYZ ZZZ ZZZ CHECK NIF
        if (nif.length() > 9) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.validation.sizeexceeds",// XYZ ZZZ TRA
            new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"),
            new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
        }
        
        // XYZ ZZZ TRA
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
        if (!p.matcher(nif).matches()) {
          throw new I18NException("genapp.validation.malformed",
             // XYZ ZZZ TRA
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"));
        }
        
        ue = usuariEntitatLogicaEjb.findUsuariEntitatExternByNif(entitatID, nif);
        if (ue == null) {
          log.warn("No existeix cap usuari entitat extern amb NIF "
              + person.getAdministrationID() + ". El cream." );
          
          // L'hem de crear persona i usuari entitat extern
          UsuariPersonaJPA persona = new UsuariPersonaJPA();
          persona.setEmail(extSigner.getEmail());
          persona.setIdiomaID(extSigner.getLanguage());
          persona.setLlinatges(extSigner.getSurnames());
          persona.setNif(extSigner.getAdministrationId());
          persona.setNom(extSigner.getName());
          persona.setUsuariIntern(false);
          
          log.info("Cridant a crear persona externa: " + extSigner.getName() 
              + " " + extSigner.getSurnames() + "[" + extSigner.getEmail()+ "] {" + extSigner.getAdministrationId() + "}");
          

          UsuariEntitatJPA ueExtern = new UsuariEntitatJPA();
          ueExtern.setActiu(true);
          ueExtern.setEntitatID(entitatID);
          ueExtern.setUsuariPersona(persona);

          try {
            ue = usuariEntitatLogicaEjb.createUsuariEntitatExtern(ueExtern, entitatID);
          } catch (I18NValidationException ve) {
            
            throw new I18NException("genapp.comodi", 
                "Pareix se que algunes dades de l´usuari extern són incorrectes: "
                + I18NCommonUtils.getMessage(ve, new Locale(languageUI)));
          }
        } else {
          // Usuari Entitat Extern existeix
          // Revisar si tots els camps de FirmaAsyncSimpleExternalSigner són correctes
          UsuariPersonaJPA persona = ue.getUsuariPersona();
          if (StringUtils.isBlank(extSigner.getEmail())) {
            extSigner.setEmail(persona.getEmail());
          }
          if (StringUtils.isBlank(extSigner.getLanguage())) {
            extSigner.setLanguage(persona.getIdiomaID());
          }
          if (StringUtils.isBlank(extSigner.getName())) {
            extSigner.setName(persona.getNom());
          }
          if (StringUtils.isBlank(extSigner.getSurnames())) {
            extSigner.setSurnames(persona.getLlinatges());
          }
          
          switch(extSigner.getSecurityLevel()) {
            case FirmaAsyncSimpleExternalSigner.SECURITY_LEVEL_TOKEN:
              // OK
            break;
            
            case FirmaAsyncSimpleExternalSigner.SECURITY_LEVEL_CERTIFICATE:
            case FirmaAsyncSimpleExternalSigner.SECURITY_LEVEL_PASSWORD:
              // XYZ ZZZ XYZ 
              throw new I18NException("genapp.comodi", 
                  "Encara no es suporta el nivell de seguretat " + extSigner.getSecurityLevel());
            default:
              // XYZ ZZZ XYZ 
              throw new I18NException("genapp.comodi", 
                  "Nivell de seguretat desconegut" + extSigner.getSecurityLevel());
            
          }
          
        }
      } 
      break;
      
      default:
        throw new I18NException("genapp.comodi", "Tipus de firmant desconegut: " + type);

    }

    if (!entitatID.equals(ue.getEntitatID())) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "Usuari definit a " + camp.fullName
          + " no pertany a l´entitat " + entitatID);
    }

    return ue.getUsuariEntitatID();

  }

  protected AnnexJPA toJPA(FirmaAsyncSimpleAnnex annexBean, FitxerLogicaLocal fitxerEjb,
      Set<Long> fitxersCreats) throws I18NException, I18NValidationException {
    if (annexBean == null) {
      return null;
    }
    AnnexJPA jpa = new AnnexJPA(0, 0, annexBean.isAttach(), annexBean.isSign());

    FirmaAsyncSimpleFile annexFile = annexBean.getAnnex();
    FitxerJPA fitxer = new FitxerJPA(annexFile.getNom(), null, annexFile.getData().length, annexFile.getMime());
    FitxerJPA f = fitxerLogicaEjb.createFitxerField(fitxer,
          new ByteArrayDataSource(annexFile.getData()),
          fitxersCreats, AnnexFields.FITXERID);
    jpa.setFitxerID(f.getFitxerID());
    jpa.setFitxer(null);

    return jpa;

  }

  protected MetadadaJPA toJPA(FirmaAsyncSimpleMetadata metadada) {
    int type = metadada.getType();
    MetadadaJPA metaJPA;

    switch (type) {

      case FirmaAsyncSimpleMetadata.INTEGER:
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_INTEGER);
      break;
      case FirmaAsyncSimpleMetadata.DECIMAL:
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_DECIMAL);
      break;
      case FirmaAsyncSimpleMetadata.BOOLEAN:
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_BOOLEAN);
      break;
      case FirmaAsyncSimpleMetadata.BASE64:
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_BASE64);
      break;
      case FirmaAsyncSimpleMetadata.DATE: // ISO8601
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_DATE);
      break;

      default:
      case FirmaAsyncSimpleMetadata.STRING:
        metaJPA = new MetadadaJPA(metadada.getName(), metadada.getValue(),
            metadada.getDescription(), 0, ConstantsV2.TIPUSMETADADA_STRING);
      break;

    }
    return metaJPA;
  }

  protected CustodiaInfoJPA getPoliticaCustodiaDeConfig(
      final UsuariAplicacioJPA usuariAplicacio, EntitatJPA entitatJPA) throws I18NException {

    int politica = usuariAplicacio.getPoliticaCustodia();
    // Falten casos de #165

    switch (politica) {
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        return null;

      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
        // XYZ ZZZ TRA
        String msg = "No s'ha definit la implementació per la Politica de Custodia per usuari aplicació amb ID "
            + politica;
        throw new I18NException("genapp.comodi", msg);

      case ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT:
        // continuam
      break;
    }

    // utilitzarem la politica de l'entitat
    politica = entitatJPA.getPoliticaCustodia();

    switch (politica) {
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        return null;

      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:
        Long infoCust = entitatJPA.getCustodiaInfoID();

        CustodiaInfoJPA custodia = custodiaInfoLogicaEjb.findByPrimaryKey(infoCust);
        return custodia;

      default:
      case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:

      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU:
      case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
        // XYZ ZZZ TRA
        String msg = "No s'ha definit la implementació per la Politica de Custodia per usuari aplicació amb ID "
            + politica;
        throw new I18NException("genapp.comodi", msg);
    }

  }

  protected PeticioDeFirmaJPA signatureRequestToPeticioDeFirmaJPA(
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest, UsuariAplicacioJPA usrapp,
      EntitatJPA entitatJPA, UsuariAplicacioConfiguracioJPA config) throws I18NException {

    long peticioDeFirmaID = 0;
    java.lang.String titol = signatureRequest.getTitle();
    java.lang.String descripcio = signatureRequest.getDescription();
    java.lang.String motiu = signatureRequest.getReason();
    long tipusDocumentID = signatureRequest.getDocumentType();
    java.lang.String descripcioTipusDocument = signatureRequest.getDocumentTypeDescription();
    // 1 mes
    java.sql.Timestamp dataCaducitat = new Timestamp(System.currentTimeMillis() + 2629750000L);
    java.sql.Timestamp dataSolicitud = new Timestamp(System.currentTimeMillis());
    java.sql.Timestamp dataFinal = null;

    int tipusEstatPeticioDeFirmaID = ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT;
    java.lang.String idiomaID = signatureRequest.getLanguageDoc();
    int prioritatID = signatureRequest.getPriority();
    long fluxDeFirmesID = 0;
    java.lang.String solicitantUsuariAplicacioID = usrapp.getUsuariAplicacioID();
    java.lang.String remitentNom = signatureRequest.getSenderName();
    java.lang.String remitentDescripcio = signatureRequest.getSenderDescription();
    boolean avisWeb = false;

    java.lang.String expedientCodi = signatureRequest.getExpedientCode();
    java.lang.String expedientNom = signatureRequest.getExpedientCode();
    java.lang.String expedientUrl = signatureRequest.getExpedientCode();
    java.lang.String procedimentCodi = signatureRequest.getProcedureCode();
    java.lang.String procedimentNom = signatureRequest.getProcedureName();
    java.lang.String informacioAddicional = signatureRequest.getAdditionalInformation();
    java.lang.Double informacioAddicionalAvaluable = signatureRequest
        .getAdditionalInformationEvaluable();

    // XYZ ZZZ ZZZ LOGO DE l'USUARI APLICACIO
    java.lang.Long logoSegellID = usrapp.getLogoSegellID();
    java.lang.String motiuDeRebuig = null;

    java.lang.String solicitantUsuariEntitat1ID = null;
    java.lang.String solicitantUsuariEntitat2ID = null;
    java.lang.String solicitantUsuariEntitat3ID = null;

    // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
    final int tipusOperacioFirma = config.getTipusOperacioFirma();

    // TIPUS DE FIRMA
    final int tipusFirmaID = config.getTipusFirmaID();

    // Algorisme de Firma
    int algorismeDeFirmaID = getAlgorismeDeFirmaIDOfConfig(config, entitatJPA);

    // Mode de Firma
    final boolean modeDeFirma;
    if (tipusFirmaID == ConstantsV2.TIPUSFIRMA_PADES) {
      // Si és una PAdES llavors val implicit
      modeDeFirma = ConstantsV2.SIGN_MODE_IMPLICIT;
    } else {
      modeDeFirma = config.isModeDeFirma();
    }

    // TAULA DE FIRMES
    int posicioTaulaFirmesID = SignatureUtils.getSignaturesTableLocationOfConfig(solicitantUsuariAplicacioID,
        config, entitatJPA);

    // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
    // DADES DE l'ENTITAT
    final boolean segellatDeTemps = getUseTimestampOfConfig(solicitantUsuariAplicacioID,
        config, entitatJPA);

    // XYZ ZZZ ZZZ
    java.lang.Long fitxerAFirmarID = null;
    java.lang.Long firmaOriginalDetachedID = null;
    java.lang.Long fitxerAdaptatID = null;

    Long custodiaInfoID = null;
    
    

    final int origenPeticioDeFirma = ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_ASYNC_SIMPLE_V2;

    PeticioDeFirmaJPA peticio = new PeticioDeFirmaJPA(peticioDeFirmaID, titol, descripcio,
        motiu, fitxerAFirmarID, firmaOriginalDetachedID, fitxerAdaptatID, tipusDocumentID,
        descripcioTipusDocument, dataSolicitud, dataFinal, dataCaducitat, tipusOperacioFirma,
        tipusFirmaID, algorismeDeFirmaID, modeDeFirma, posicioTaulaFirmesID,
        tipusEstatPeticioDeFirmaID, motiuDeRebuig, idiomaID, prioritatID, fluxDeFirmesID,
        solicitantUsuariAplicacioID, remitentNom, remitentDescripcio, expedientCodi,
        expedientNom, expedientUrl, procedimentCodi, procedimentNom, informacioAddicional,
        informacioAddicionalAvaluable, logoSegellID, custodiaInfoID,
        solicitantUsuariEntitat1ID, solicitantUsuariEntitat2ID, solicitantUsuariEntitat3ID,
        avisWeb, segellatDeTemps, origenPeticioDeFirma, config.getUsuariAplicacioConfigID());

    return peticio;

  }

}
