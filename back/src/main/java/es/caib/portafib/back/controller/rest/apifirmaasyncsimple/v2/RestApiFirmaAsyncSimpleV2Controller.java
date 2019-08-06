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
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
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
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleValidationInfo;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
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
import java.io.File;
import java.io.IOException;
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
 * @author anadal
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
  
  @EJB(mappedName = "portafib/FirmaLogicaEJB/local")
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

    String error = autenticate(request);
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

    String error = autenticate(request);
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
      log.info(" XYZ ZZZ ZZZ Usuari-APP = " + loginInfo.getUsuariAplicacio());

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

    String error = autenticate(request);
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
          
          FirmaAsyncSimplePerson personToSign = new FirmaAsyncSimplePerson();
          UsuariEntitatJPA ue = firma.getUsuariEntitat();
          
          if (ue.getCarrec() == null) {
            personToSign.setIntermediateServerUsername(ue.getUsuariEntitatID());
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

    String error = autenticate(request);
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
        return generateServerError("El parametre d'entrada languageUI no pot ser null o buit.");
      }

      FirmaAsyncSimpleFile fileToConvertInfo = signatureRequest.getFileToSign();

      log.info(" XYZ ZZZ ZZZ fileToConvertInfo = " + fileToConvertInfo);

      if (fileToConvertInfo == null) {
        throw new I18NException("genapp.validation.required",
            PeticioDeFirmaFields.FITXERAFIRMARID.fullName);
      }

      PeticioDeFirmaJPA peticioDeFirmaJPA = signatureRequestToPeticioDeFirmaJPAFull(
          signatureRequest, loginInfo.getUsuariAplicacio(), loginInfo.getEntitat(),
          fitxersCreats);

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

      try {
        peticioDeFirmaLogicaEjb.start(peticioDeFirmaJPA.getPeticioDeFirmaID(), true);
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
      cleanPostError(fitxerLogicaEjb, fitxersCreats);
      log.error(msg, ve);
      return generateServerError(msg);

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
      cleanPostError(fitxerLogicaEjb, fitxersCreats);
      log.error(msg, i18ne);
      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a createAndStartSignatureRequest: "
          + th.getMessage();
      log.error(msg, th);
      cleanPostError(fitxerLogicaEjb, fitxersCreats);
      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/" + ApiFirmaAsyncSimple.SIGNATUREREQUESTSTATE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getSignatureRequestState(HttpServletRequest request,
      @RequestBody FirmaAsyncSimpleSignatureRequestInfo info) {

    String error = autenticate(request);
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
        // XYZ ZZZ ZZZ
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

    String error = autenticate(request);
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

      // XYZ ZZZ ZZZ
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
            String serialNumberCert = firma.getNumeroSerieCertificat().toString();
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
          log.info("XYZ ZZZ ZZZ NUMERO DE FIRMA ES " + firma.getNumFirmaDocument() );
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

    String error = autenticate(request);
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
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest, UsuariAplicacioJPA usrapp,
      EntitatJPA entitatJPA, Set<Long> fitxersCreats) throws I18NException, I18NValidationException {

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
      FitxerJPA f = createFitxer(signatureRequest.getFileToSign(), fitxerLogicaEjb,
          fitxersCreats, PeticioDeFirmaFields.FITXERAFIRMARID);
      jpa.setFitxerAFirmarID(f == null ? null : f.getFitxerID());
      jpa.setFitxerAFirmar(null);
    }

    // Fitxer
    if (jpa.getLogoSegellID() != null) {
      // Logo de Segell de l'usuari Aplicacio
      long fitxerID = jpa.getLogoSegellID();
      byte[] data;
      try {
        data = FileSystemManager.getFileContent(fitxerID);
      } catch (Exception e) {
        String msg = "No puc llegir el Logo de l'usuari aplicacio amb ID = " + fitxerID;
        log.error(msg, e);
        throw new I18NException("genapp.comodi", msg);
      }

      Fitxer fitxerBBDD = fitxerLogicaEjb.findByPrimaryKey(fitxerID);

      FitxerJPA f = createFitxer(
          new FirmaAsyncSimpleFile(fitxerBBDD.getNom(), fitxerBBDD.getMime(), data),
          fitxerLogicaEjb, fitxersCreats, PeticioDeFirmaFields.LOGOSEGELLID);
      jpa.setLogoSegellID(f == null ? 0 : f.getFitxerID());
      jpa.setLogoSegell(null);
    }

    // Custodia
    {
      CustodiaInfoJPA custodiaInfoJPA = getPoliticaCustodiaDeConfig(usrapp, entitatJPA);
      if (custodiaInfoJPA == null) {
        jpa.setCustodiaInfo(null);
        jpa.setCustodiaInfoID(null);
      } else {
        CustodiaInfoJPA custodiaCloned = CustodiaInfoJPA.toJPA(custodiaInfoJPA);
        custodiaCloned.setCustodiaInfoID(0);
        jpa.setCustodiaInfo(custodiaCloned);
      }
    }

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
      jpa.setFluxDeFirmes(toJPA(blocks, entitatJPA.getEntitatID(), fitxersCreats));
    }

    return jpa;
  }

  protected FluxDeFirmesJPA toJPA(FirmaAsyncSimpleSignatureBlock[] blocks, String entitatID,
      Set<Long> fitxersCreats) throws I18NException {

    // Bean
    FluxDeFirmesJPA jpa = new FluxDeFirmesJPA("Flux per Petició Async " + blocks.toString());

    Set<BlocDeFirmesJPA> blocsDeFirmesJPA = new HashSet<BlocDeFirmesJPA>();
    for (int b = 0; b < blocks.length; b++) {
      FirmaAsyncSimpleSignatureBlock bloc = blocks[b];
      blocsDeFirmesJPA.add(toJPA(b, bloc, entitatID, fitxersCreats));
    }
    jpa.setBlocDeFirmess(blocsDeFirmesJPA);

    return jpa;
  }

  protected BlocDeFirmesJPA toJPA(int ordre, FirmaAsyncSimpleSignatureBlock bloc,
      String entitatID, Set<Long> fitxersCreats) throws I18NException {

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

      firmesJPA.add(toJPA(firmaBean, entitatID, fitxersCreats));

      jpa.setFirmas(firmesJPA);
    }

    return jpa;
  }

  protected FirmaJPA toJPA(FirmaAsyncSimpleSignature firmaBean, String entitatID,
      Set<Long> fitxersCreats) throws I18NException {

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

    java.lang.String destinatariID = searchUser(firmaBean.getPersonToSign(), entitatID,
        FirmaFields.DESTINATARIID);
    java.lang.Long fitxerFirmatID = null;

    FirmaJPA jpa = new FirmaJPA(firmaID, destinatariID, blocDeFirmaID, obligatori,
        fitxerFirmatID, numFirmaDocument, caixaPagina, caixaX, caixaY, caixaAmple, caixaAlt,
        numeroSerieCertificat, emissorCertificat, nomCertificat, tipusEstatDeFirmaFinalID,
        mostrarRubrica, motiu, minimDeRevisors, null,null,null,null);

    List<FirmaAsyncSimpleReviser> revisors = firmaBean.getRevisers();

    if (revisors == null || revisors.size() == 0) {
      jpa.setRevisorDeFirmas(null);
    } else {

      Set<RevisorDeFirmaJPA> revisorsJPA = new HashSet<RevisorDeFirmaJPA>();

      for (FirmaAsyncSimpleReviser rev : revisors) {
        String usuariEntitatID = searchUser(rev.getReviser(), entitatID,
            RevisorDeFirmaFields.USUARIENTITATID);
        RevisorDeFirmaJPA revisor = new RevisorDeFirmaJPA(usuariEntitatID, 0, rev.isRequired());
        revisorsJPA.add(revisor);
      }
      jpa.setRevisorDeFirmas(revisorsJPA);
    }
    return jpa;
  }

  protected String searchUser(FirmaAsyncSimplePerson persona, String entitatID, Field<?> camp)
      throws I18NException {

    int count = 0;
    int type = -1;
    if (persona.getAdministrationID() != null
        && persona.getAdministrationID().trim().length() != 0) {
      count++;
      type = 0;
    }
    if (persona.getUsername() != null && persona.getUsername().trim().length() != 0) {
      count++;
      type = 1;
    }
    if (persona.getIntermediateServerUsername() != null
        && persona.getIntermediateServerUsername().trim().length() != 0) {
      count++;
      type = 2;
    }
    if (persona.getPositionInTheCompany() != null
        && persona.getPositionInTheCompany().trim().length() != 0) {
      type = 3;
      count++;
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

    UsuariEntitat ue = null;
    switch (type) {

      case 0: // NIF
        ue = usuariEntitatLogicaEjb.findUsuariEntitatByNif(entitatID,
            persona.getAdministrationID());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix cap usuari amb NIF "
              + persona.getAdministrationID());
        }
      break;

      case 1: // Username
        ue = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatID,
            persona.getUsername());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No existeix cap usuari amb username "
              + persona.getUsername());
        }

      break;

      case 2: // UsuariEntitatID
        ue = usuariEntitatLogicaEjb.findByPrimaryKey(persona.getIntermediateServerUsername());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No existeix cap usuari entitat (IntermediateServerUsername) "
                  + persona.getIntermediateServerUsername());
        }
        if (ue.getCarrec() != null) {
          // XYZ ZZZ TRA
          throw new I18NException(
              "genapp.comodi",
              "S´ha assignat dins IntermediateServerUsername un identificador que correspon a un càrrec (PositionInTheCompany)");
        }
      break;

      case 3: // Càrrec
        ue = usuariEntitatLogicaEjb.findByPrimaryKey(persona.getPositionInTheCompany());
        if (ue == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No existeix cap càrrec (PositionInTheCompany) "
                  + persona.getIntermediateServerUsername());
        }
        // Comprovar que
        if (ue.getCarrec() == null) {
          // XYZ ZZZ TRA
          throw new I18NException(
              "genapp.comodi",
              "S´ha assignat dins càrrec (PositionInTheCompany) un identificador que correspon a un usuari entitat (IntermediateServerUsername)");
        }
      break;

    }

    if (!entitatID.equals(ue.getEntitatID())) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "usuari definit a " + camp.fullName
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

    if (jpa.getFitxerID() == 0) {
      FitxerJPA f = createFitxer(annexBean.getAnnex(), fitxerEjb, fitxersCreats,
          AnnexFields.FITXERID);
      fitxersCreats.add(f.getFitxerID());
      jpa.setFitxerID(f.getFitxerID());
      jpa.setFitxer(null);
    }

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
    log.info("XYZ ZZZ ZZZ   POLITICA CUSTODIA CONFIG ==>  " + politica);
    // Falten casos de #165

    switch (politica) {
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        log.info("XYZ ZZZ ZZZ   POLITICA USRAPP  XXXXXXXXXXX");
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
        log.info("XYZ ZZZ ZZZ   POLITICA USRAPP => Entitat  EEEEEEE");
      // continuam
      break;
    }

    // utilitzarem la politica de l'entitat
    politica = entitatJPA.getPoliticaCustodia();

    switch (politica) {
      case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
      case ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU:
        log.info("XYZ ZZZ ZZZ   POLITICA Entitat  XXXXXXXXXXX");

        return null;

      case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:

        log.info("XYZ ZZZ ZZZ   POLITICA Entitat  YYYYYYYYYYYYYY");

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

  protected void cleanPostError(FitxerLogicaLocal fitxerEjb, Set<Long> fitxersCreats) {
    if (fitxersCreats == null) {
      return;
    }

    for (Long fileID : fitxersCreats) {
      try {
        fitxerEjb.delete(fileID);
      } catch (Throwable e) {
        // TODO Enviar mail a ADMINISTRADOR
        log.error("Error esborrant fitxer després d'un error: " + e.getMessage(), e);
      }
      FileSystemManager.eliminarArxiu(fileID);
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
        descripcioTipusDocument, posicioTaulaFirmesID, dataSolicitud, dataFinal,
        dataCaducitat, tipusOperacioFirma, tipusFirmaID, algorismeDeFirmaID, modeDeFirma,
        tipusEstatPeticioDeFirmaID, motiuDeRebuig, idiomaID, prioritatID, fluxDeFirmesID,
        solicitantUsuariAplicacioID, remitentNom, remitentDescripcio, expedientCodi,
        expedientNom, expedientUrl, procedimentCodi, procedimentNom, informacioAddicional,
        informacioAddicionalAvaluable, logoSegellID, custodiaInfoID,
        solicitantUsuariEntitat1ID, solicitantUsuariEntitat2ID, solicitantUsuariEntitat3ID,
        avisWeb, segellatDeTemps, origenPeticioDeFirma, config.getUsuariAplicacioConfigID());

    // peticioDeFirmaID, titol, motiu, tipusDocumentID, posicioTaulaFirmesID, dataCaducitat,
    // tipusOperacioFirma, tipusFirmaID, algorismeDeFirmaID, modeDeFirma,
    // tipusEstatPeticioDeFirmaID, idiomaID, prioritatID, fluxDeFirmesID,
    // solicitantUsuariAplicacioID, remitentNom, avisWeb, segellatDeTemps)

    return peticio;

  }

  // TODO throw I18NException
  protected FitxerJPA createFitxer(FirmaAsyncSimpleFile fitxer, FitxerLogicaLocal fitxerEjb,
      Set<Long> fitxersCreats, Field<?> field) throws I18NException, I18NValidationException {

    if (fitxer == null) {
      return null;
    }

    File tmp;
    try {
      if (fitxer.getData() == null) {
        // TODO Translate
        throw new IOException();
      }

      tmp = File.createTempFile("ws_", ".tmp", FileSystemManager.getFilesPath());

      FileUtils.writeByteArrayToFile(tmp, fitxer.getData());

    } catch (IOException ioe) {

      log.error("Error escrivint FirmaAsyncSimpleFile a fitxer: " + ioe.getMessage(), ioe);

      throw new I18NException("fitxer.sensedades", field.fullName);
    }

    /**
     * if (fitxer.getTamany() != data.length) { // TODO Translate throw new
     * Exception("El tamany esperat del Fitxer " + field.fullName + " es " + fitxer.getTamany()
     * + " però el tamany rebut es de " + data.length); }
     */

    log.info(" TAMANY DE FITXER REBUT = " + tmp.length());

    FitxerJPA fitxerJPA = new FitxerJPA(fitxer.getNom(), null, tmp.length(), fitxer.getMime());

    // TODO Arreglar aquest
    try {
      fitxerJPA = fitxerEjb.createFull(fitxerJPA);
    } catch (I18NValidationException e) {
      // Si falla la creació per errors de validació, borram el fitxer temporal i rellançam excepció
      if (tmp.exists()) {
        if (!tmp.delete()) {
          tmp.deleteOnExit();
        }
      }
      throw e;
    }

    long fitxerID = fitxerJPA.getFitxerID();

    log.info("ID FITXER CREAT = " + fitxerID);

    // FileSystemManager.crearFitxer(tmp, fitxerID);
    //FileSystemManager.sobreescriureFitxer(tmp, fitxerID);
    try {
      LogicUtils.sobreescriureFitxerChecked(tmp, fitxerID);
    } catch (Exception e) {
      /*
      Si ha fallat el sobreescriure intentam borrar tot i llançar una runtime.
       */
      fitxerEjb.deleteFull(fitxerID);
      if (tmp.exists()) {
        if (!tmp.delete()) {
          tmp.deleteOnExit();
        }
      }
      throw new RuntimeException(e);
    }

    fitxersCreats.add(fitxerJPA.getFitxerID());

    return fitxerJPA;

  }

  /*
   * 
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.GETTRANSACTIONID, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * getTransactionID(HttpServletRequest request,
   * 
   * @RequestBody FirmaSimpleCommonInfo commonInfo) {
   * 
   * String error = autenticate(request); if (error != null) { return
   * generateServerError(error, HttpStatus.UNAUTHORIZED); }
   * 
   * // Fer neteja de transaccions Obsoletes !!!! cleanExpiredTransactions();
   * 
   * // Check de commonInfo if (commonInfo == null) { return
   * generateServerError("El parametre d'entrada de tipus FirmaSimpleCommonInfo no pot ser null."
   * ); }
   * 
   * String lang = commonInfo.getLanguageUI(); if (lang == null || lang.trim().length() == 0) {
   * 
   * return generateServerError(
   * "El camp LanguageUI del tipus FirmaSimpleCommonInfo no pot ser null o buit."); }
   * 
   * 
   * try {
   * 
   * getPerfilDeFirma(commonInfo, esFirmaEnServidor);
   * 
   * } catch (I18NException i18ne) {
   * 
   * String msg = I18NLogicUtils.getMessage(i18ne, new Locale(lang));
   * 
   * return generateServerError(msg);
   * 
   * } catch (Throwable th) {
   * 
   * // XYZ ZZZ TRA String msg = "Error desconegut intentant obtenir el transacctionID: " +
   * th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * String transactionID = internalGetTransacction();
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin();
   * 
   * ResponseEntity<String> res = new ResponseEntity<String>(transactionID, headers,
   * HttpStatus.OK);
   * 
   * currentTransactions.put(transactionID, new TransactionInfo(transactionID, commonInfo,
   * TransactionInfo.STATUS_RESERVED_ID));
   * 
   * return res;
   * 
   * }
   */

  /*
   * 
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.ADDFILETOSIGN, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * addFileToSign(HttpServletRequest request,
   * 
   * @RequestBody FirmaSimpleAddFileToSignRequest holder) {
   * 
   * String error = autenticate(request); if (error != null) { return
   * generateServerError(error, HttpStatus.UNAUTHORIZED); }
   * 
   * if (holder == null) { return
   * generateServerError("Aquest mètode requereix que el parametre no sigui NULL"); }
   * 
   * String transactionID = holder.getTransactionID(); FirmaSimpleFileInfoSignature sfis =
   * holder.getFileInfoSignature();
   * 
   * log.info(" XYZ ZZZ addFileToSign::transactionID => |" + transactionID + "|");
   * log.info(" XYZ ZZZ addFileToSign::FirmaSimpleFileInfoSignature: " + sfis);
   * 
   * cleanExpiredTransactions();
   * 
   * // TODO XYZ ZZZ CHECKS DE LOGIN
   * 
   * // CHECKS DE variable
   * 
   * log.info(" XYZ ZZZ addFileToSign::currentTransactions.size() => " +
   * currentTransactions.size());
   * 
   * TransactionInfo ti = currentTransactions.get(transactionID);
   * 
   * if (ti == null) { // TODO XYZ ZZZ Traduir return
   * generateServerError("No existeix cap transacció amb ID " + transactionID); }
   * 
   * if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) { // TODO XYZ ZZZ Traduir return
   * generateServerError("La transacció " + transactionID +
   * " es troba en un estat que no accepta més documents per firmar"); }
   * 
   * Date dataCreacio = ti.getStartTime();
   * 
   * if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) { //
   * TODO XYZ ZZZ Traduir currentTransactions.remove(transactionID); return
   * generateServerError("La transacció amb ID " + transactionID + " ha expirat"); }
   * 
   * // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
   * 
   * try {
   * 
   * LoginInfo loginInfo = LoginInfo.getInstance();
   * 
   * log.info(" XYZ ZZZ LOGININFO => " + loginInfo);
   * 
   * // Checks Globals if (loginInfo.getUsuariEntitat() != null) { throw new
   * Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ"); }
   * 
   * // Checks usuari aplicacio log.info(" XYZ ZZZ Usuari-APP = " +
   * loginInfo.getUsuariAplicacio());
   * 
   * // EntitatJPA entitatJPA = loginInfo.getEntitat();
   * 
   * String signID = sfis.getSignID(); String name = sfis.getName();
   * 
   * 
   * ti.getFirmaSimpleFileList().add(sfis);
   * 
   * // Actualitzar Data expriracio ti.setStartTime(new Date());
   * log.info(" XYZ ZZZ addFileToSign::afegida firma [" + signID + " | " + name +
   * " ] a la llista de la transacció |" + transactionID + "|");
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re = new
   * ResponseEntity<String>(headers, HttpStatus.OK); return re;
   * 
   * } catch (Throwable th) {
   * 
   * String msg = "Error desconegut afegint fitxer per Firmar a transacció [" + transactionID +
   * "]: " + th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   */
  /*
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.STARTTRANSACTION, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * startTransaction(HttpServletRequest request,
   * 
   * @RequestBody FirmaSimpleStartTransactionRequest startTransactionRequest) {
   * 
   * String error = autenticate(request); if (error != null) { return
   * generateServerError(error, HttpStatus.UNAUTHORIZED); }
   * 
   * // XYZ ZZZ Canviar per idioma per defecte String languageUI = "ca";
   * 
   * try {
   * log.info(" XYZ ZZZ eNTRA A startTransaction => FirmaWebSimpleStartTransactionRequest: " +
   * startTransactionRequest);
   * 
   * // TODO XYZ ZZZ CHECKS DE LOGIN LoginInfo loginInfo = commonChecks(esFirmaEnServidor);
   * 
   * log.info(" XYZ ZZZ LOGININFO => " + loginInfo);
   * 
   * // Validar simpleSignature
   * 
   * cleanExpiredTransactions();
   * 
   * // CHECKS DE variable final String transactionID =
   * startTransactionRequest.getTransactionID();
   * 
   * log.info(" XYZ ZZZ startTransaction::transactionID => |" + transactionID + "|");
   * log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => " +
   * currentTransactions.size());
   * 
   * TransactionInfo ti = currentTransactions.get(transactionID);
   * 
   * if (ti == null) { // TODO XYZ ZZZ Traduir return
   * generateServerError("No existeix cap transacció amb ID " + transactionID); }
   * 
   * if (ti.getStatus() != TransactionInfo.STATUS_RESERVED_ID) { // TODO XYZ ZZZ Traduir return
   * generateServerError("La transacció " + transactionID +
   * " es troba en un estat que no accepta més documents per firmar"); }
   * 
   * languageUI = ti.getCommonInfo().getLanguageUI();
   * 
   * // XYZ ZZZ TODO // Falta verificar estructura de
   * 
   * // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();
   * 
   * Date dataCreacio = ti.getStartTime();
   * 
   * if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) { //
   * TODO XYZ ZZZ Traduir currentTransactions.remove(transactionID); return
   * generateServerError("La transacció amb ID " + transactionID + " ha expirat"); }
   * 
   * // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
   * 
   * 
   * // Checks Globals
   * 
   * if (loginInfo.getUsuariEntitat() != null) { throw new
   * Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ"); }
   * 
   * // Checks usuari aplicacio UsuariAplicacioJPA usuariAplicacio =
   * loginInfo.getUsuariAplicacio();
   * 
   * String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
   * 
   * 
   * EntitatJPA entitatJPA = loginInfo.getEntitat();
   * 
   * String entitatID = entitatJPA.getEntitatID();
   * 
   * final String virtualTransactionID = internalGetTransacction();
   * 
   * // Cercam que tengui configuracio
   * 
   * FirmaSimpleSignDocumentsRequest simpleSignaturesSet;
   * 
   * FirmaSimpleFileInfoSignature[] fileInfoSignatureArray = ti.getFirmaSimpleFileList()
   * .toArray(new FirmaSimpleFileInfoSignature[ti.getFirmaSimpleFileList().size()]);
   * 
   * simpleSignaturesSet = new FirmaSimpleSignDocumentsRequest(ti.getCommonInfo(),
   * fileInfoSignatureArray);
   * 
   * final FirmaSimpleCommonInfo commonInfo = ti.getCommonInfo();
   * 
   * final PerfilDeFirma perfilDeFirma = getPerfilDeFirma(commonInfo, esFirmaEnServidor);
   * 
   * configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirma( usuariAplicacioID,
   * ti.getCommonInfo().getSignProfile(), ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB);
   * 
   * log.info(" XYZ ZZZ PERFILFIRMA FIRMA WEB = " + perfilDeFirma.getCodi());
   * 
   * Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = new HashMap<String,
   * UsuariAplicacioConfiguracioJPA>();
   * 
   * 
   * 
   * for (FirmaSimpleFileInfoSignature firmaSimpleFileInfoSignature : fileInfoSignatureArray) {
   * 
   * UsuariAplicacioConfiguracioJPA config; config =
   * configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleWeb(
   * usuariAplicacioID, perfilDeFirma, new FirmaSimpleSignDocumentRequest(commonInfo,
   * firmaSimpleFileInfoSignature));
   * 
   * configBySignID.put(firmaSimpleFileInfoSignature.getSignID(), config);
   * 
   * log.info(" XYZ ZZZ CONFIG => " + config.getNom());
   * 
   * }
   * 
   * 
   * PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanWeb(transactionID,
   * virtualTransactionID, simpleSignaturesSet, perfilDeFirma, configBySignID);
   * 
   * String urlFinal = startTransactionRequest.getReturnUrl();
   * pss.getCommonInfoSignature().setUrlFinal(urlFinal);
   * 
   * 
   * 
   * // CRIDAR A START TRANSACION final boolean fullView =
   * FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN
   * .equals(startTransactionRequest.getView());
   * 
   * 
   * 
   * String redirectUrl = passarelaDeFirmaWebEjb.startTransaction(pss, entitatID, fullView,
   * usuariAplicacio, perfilDeFirma, configBySignID);
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re = new
   * ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
   * log.info(" XYZ ZZZ SURT DE startTransaction => FINAL OK");
   * 
   * ti.setStatus(TransactionInfo.STATUS_IN_PROGRESS);
   * 
   * return re;
   * 
   * } catch (I18NValidationException i18nve) {
   * 
   * String msg = I18NLogicUtils.getMessage(i18nve, new Locale(languageUI)); log.error(msg,
   * i18nve); return generateServerError(msg);
   * 
   * } catch (I18NException i18ne) {
   * 
   * String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
   * 
   * log.error(msg, i18ne);
   * 
   * return generateServerError(msg);
   * 
   * } catch (Throwable th) {
   * 
   * // XYZ ZZZ TRA String msg = "Error desconegut iniciant el proces de Firma: " +
   * th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   */
  /*
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.TRANSACTIONSTATUS, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?>
   * getTransactionStatus(@RequestBody String transactionID, HttpServletRequest request) { try
   * {
   * 
   * log.info(" XYZ ZZZ ENTRA A getTransactionStatus => " + transactionID);
   * 
   * String error = autenticate(request); if (error != null) { return
   * generateServerError(error, HttpStatus.UNAUTHORIZED); }
   * 
   * PassarelaSignatureStatus status; status =
   * passarelaDeFirmaWebEjb.getStatusTransaction(transactionID);
   * 
   * FirmaSimpleStatus transactionStatus; transactionStatus = new
   * FirmaSimpleStatus(status.getStatus(), status.getErrorMessage(),
   * status.getErrorStackTrace());
   * 
   * final boolean addFiles = false;
   * 
   * List<PassarelaSignatureResult> results; results =
   * passarelaDeFirmaWebEjb.getSignatureResults(transactionID, addFiles);
   * 
   * log.info("\n\n XYZ ZZZ Numero d'arxius firmat trobats per la transacció " + transactionID
   * + " es de " + results.size() + "\n\n");
   * 
   * List<FirmaSimpleSignatureStatus> signResults = new
   * ArrayList<FirmaSimpleSignatureStatus>(); for (PassarelaSignatureResult psr : results) {
   * 
   * 
   * 
   * signResults.add(new FirmaSimpleSignatureStatus(psr.getSignID(), new FirmaSimpleStatus(
   * psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace())));
   * 
   * }
   * 
   * FirmaSimpleGetTransactionStatusResponse ssresponse; ssresponse = new
   * FirmaSimpleGetTransactionStatusResponse(transactionStatus, signResults);
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re = new
   * ResponseEntity<FirmaSimpleGetTransactionStatusResponse>( ssresponse, headers,
   * HttpStatus.OK); log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL OK");
   * 
   * return re;
   * 
   * } catch (Throwable th) { final String msg =
   * "Error desconegut intentant recuperar informació de l'estat de la transacció: " +
   * transactionID + ": " + th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   */

  /*
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.SIGNATURERESULT, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public ResponseEntity<?> getSignatureResult(
   * 
   * @RequestBody FirmaSimpleGetSignatureResultRequest signatureResultRequest,
   * HttpServletRequest request) {
   * 
   * log.info(" XYZ ZZZ getSignaturesResult => ENTRA");
   * 
   * String error = autenticate(request); if (error != null) { return
   * generateServerError(error, HttpStatus.UNAUTHORIZED); }
   * 
   * // Clean Transactions caducades cleanExpiredTransactions();
   * 
   * // XYZ ZZZ // Revisar que existeix currentTransaccitions
   * 
   * String signID = signatureResultRequest.getSignID(); String transactionID =
   * signatureResultRequest.getTransactionID();
   * 
   * try {
   * 
   * PassarelaSignatureResult result; result =
   * passarelaDeFirmaWebEjb.getSignatureResult(transactionID, signID);
   * 
   * if (result == null) { // XYZ ZZZ Traduir String msg =
   * "No s'ha pogut trobar informació de la firma [" + signID + "] de la transacció: " +
   * transactionID; return generateServerError(msg); }
   * 
   * PassarelaSignaturesSetWebInternalUse pss = passarelaDeFirmaWebEjb
   * .getSignaturesSetFullByTransactionID(transactionID); PassarelaFileInfoSignature infoSign =
   * null; for (PassarelaFileInfoSignature pfis : pss.getSignaturesSet()
   * .getFileInfoSignatureArray()) {
   * 
   * if (signID.equals(pfis.getSignID())) { infoSign = pfis; break; } }
   * 
   * // FirmaSimpleFile fsf = convertFitxerBeanToFirmaSimpleFile(result.getSignedFile()); final
   * boolean isSignatureInServer = false; FirmaSimpleSignatureResult fssr; fssr =
   * convertPassarelaSignatureResult2FirmaSimpleSignatureResult(result, pss
   * .getSignaturesSet().getCommonInfoSignature(), infoSign, isSignatureInServer);
   * 
   * HttpHeaders headers = addAccessControllAllowOrigin(); ResponseEntity<?> re = new
   * ResponseEntity<FirmaSimpleSignatureResult>(fssr, headers, HttpStatus.OK);
   * log.info(" XYZ ZZZ getSignaturesStatus => FINAL OK"); return re;
   * 
   * } catch (Throwable th) {
   * 
   * // TRADUIR final String msg =
   * "Error desconegut intentant recuperar resultat de la firma [" + signID +
   * "] de la transacció: " + transactionID + ": " + th.getMessage();
   * 
   * log.error(msg, th);
   * 
   * return generateServerError(msg, th); }
   * 
   * }
   * 
   * @RequestMapping(value = "/" + ApiFirmaWebSimple.CLOSETRANSACTION, method =
   * RequestMethod.POST)
   * 
   * @ResponseBody
   * 
   * @Produces(MediaType.APPLICATION_JSON)
   * 
   * @Consumes(MediaType.APPLICATION_JSON) public void closeTransaction(@RequestBody String
   * transactionID, HttpServletRequest request, HttpServletResponse response) {
   * 
   * log.info(" XYZ ZZZ closeTransaction => ENTRA");
   * 
   * String error = autenticate(request); if (error != null) { try {
   * response.sendError(HttpServletResponse.SC_UNAUTHORIZED, error); } catch (IOException e) {
   * log.error(e.getMessage(), e); } return; }
   * 
   * internalCloseTransaction(transactionID);
   * 
   * log.info(" XYZ ZZZ closeTransaction => FINAL OK");
   * 
   * }
   * 
   * protected void internalCloseTransaction(String transactionID) {
   * passarelaDeFirmaWebEjb.closeTransaction(transactionID);
   * 
   * currentTransactions.remove(transactionID);
   * 
   * try { File transactionFolder = getTransactionFolder(TIPUS_WEB, transactionID);
   * FileUtils.deleteDirectory(transactionFolder); } catch (Exception e) {
   * log.error("Error desconegut fent neteja dels fitxers " +
   * "de ApiFirmaWebSimple de la transacció " + transactionID + ":" + e.getMessage(), e); } }
   * 
   * /** Fer neteja de transaccions Obsoletes
   */
  /*
   * protected void cleanExpiredTransactions() {
   * 
   * final long now = System.currentTimeMillis(); for (TransactionInfo info : new
   * ArrayList<TransactionInfo>(currentTransactions.values())) { try { // 15 minutes if
   * (info.getStartTime().getTime() + 900000 < now) {
   * internalCloseTransaction(info.getTransactionID()); } } catch (Exception e) {
   * log.error("Error desconegut" + " netejant transaccions expirades de l'APIFirmaSimple: " +
   * e.getMessage(), e); } } }
   */

  /**
   * 
   * @author anadal
   *
   */
  /*
   * public class TransactionInfo {
   * 
   * // 15 minuts public static final long MAX_TIME = 900000L;
   * 
   * public static final int STATUS_RESERVED_ID = 0;
   * 
   * public static final int STATUS_IN_PROGRESS = 1;
   * 
   * final String transactionID;
   * 
   * final FirmaSimpleCommonInfo commonInfo;
   * 
   * @Deprecated final List<PassarelaFileInfoSignature> fileInfoSignatureList = new
   * ArrayList<PassarelaFileInfoSignature>();
   * 
   * final List<FirmaSimpleFileInfoSignature> firmaSimpleFileList = new
   * ArrayList<FirmaSimpleFileInfoSignature>();
   * 
   * Date startTime;
   * 
   * int status;
   * 
   * 
   * public TransactionInfo(String transactionID, FirmaSimpleCommonInfo commonInfo, int status)
   * { super(); this.transactionID = transactionID; this.startTime = new Date();
   * this.commonInfo = commonInfo; this.status = status; }
   * 
   * public int getStatus() { return status; }
   * 
   * public void setStatus(int status) { this.status = status; }
   * 
   * public String getTransactionID() { return transactionID; }
   * 
   * public Date getStartTime() { return startTime; }
   * 
   * public FirmaSimpleCommonInfo getCommonInfo() { return commonInfo; }
   * 
   * @Deprecated // XYZ ZZZ public List<PassarelaFileInfoSignature> getFileInfoSignatureList()
   * { return fileInfoSignatureList; }
   * 
   * public void setStartTime(Date startTime) { this.startTime = startTime; }
   * 
   * public List<FirmaSimpleFileInfoSignature> getFirmaSimpleFileList() { return
   * firmaSimpleFileList; }
   * 
   * }
   */
}
