package org.fundaciobit.plugins.signatureserver.afirmaserver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.fundaciobit.plugins.signatureserver.miniappletutils.SMIMEInputStream;
import org.fundaciobit.plugins.signatureserver.miniappletutils.MIMEInputStream;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.utils.Base64;
import org.fundaciobit.plugins.utils.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerException;
import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerFacade;
import es.gob.afirma.i18n.Language;
import es.gob.afirma.integraFacade.GenerateMessageRequest;
import es.gob.afirma.integraFacade.GenerateMessageResponse;
import es.gob.afirma.integraFacade.ValidateRequest;
import es.gob.afirma.integraFacade.pojo.DetailLevelEnum;
import es.gob.afirma.integraFacade.pojo.ServerSignerResponse;
import es.gob.afirma.integraFacade.pojo.VerificationReport;
import es.gob.afirma.integraFacade.pojo.VerifyCertificateRequest;
import es.gob.afirma.integraFacade.pojo.VerifyCertificateResponse;
import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.GeneralConstants;
import es.gob.afirma.utils.DSSConstants.AlgorithmTypes;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.DSSConstants.SignTypesURIs;
import es.gob.afirma.utils.DSSConstants.SignatureForm;
import es.gob.afirma.utils.DSSConstants.XmlSignatureMode;

/**
 *
 * @author anadal
 *
 */
public class AfirmaServerSignatureServerPlugin extends AbstractSignatureServerPlugin {

  protected static final Map<String, String> hashAlgorithmMap = new HashMap<String, String>();

  static {
    hashAlgorithmMap.put("SHA1", "http://www.w3.org/2000/09/xmldsig#sha1");
    hashAlgorithmMap.put("SHA-256", "http://www.w3.org/2001/04/xmlenc#sha256");
    hashAlgorithmMap.put("SHA-384", "http://www.w3.org/2001/04/xmldsig-more#sha384");
    hashAlgorithmMap.put("SHA-512", "http://www.w3.org/2001/04/xmlenc#sha512");
  }

  public static final String AFIRMASERVER_BASE_PROPERTIES = SIGNATURESERVER_BASE_PROPERTY
      + "afirmaserver.";

  public static final String APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "applicationID";
  
  public static final String APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "applicationID_TimeStamp";


  public static final String DEFAULT_ALIAS_CERTIFICATE_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "defaultAliasCertificate";

  public static final String TRANSFORMERSTEMPLATESPATH_PROPERTY = AFIRMASERVER_BASE_PROPERTIES
      + "TransformersTemplatesPath";

  /**
   * 
   */
  public AfirmaServerSignatureServerPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AfirmaServerSignatureServerPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AfirmaServerSignatureServerPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  /**
   * 
   * @return true true indica que el plugin accepta generadors de Segell de
   *         Temps definits dins FileInfoSignature.timeStampGenerator
   */
  @Override
  public boolean acceptExternalTimeStampGenerator(String signType) {

    return false;
    /*
    if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
       || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return true; 
    } else {
      return false;
    }
    */
    /*
     * if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) { return true; }
     * else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) { // Per ara
     * MiniApplet no suporta firma de XadesT return false; } else {
     * log.warn("S'ha cridat a " + this.getClass().getName() +
     * "::acceptExternalTimeStampGenerator amb un tipus de firma no controlat:"
     * + signType); return false; }
     */
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  @Override
  public boolean providesTimeStampGenerator(String signType) {
    // TODO XYZ ZZZ AQUI HEM DE VEURE SI l'applicationID de @FIRMA permet estampador
    // de segell de temps
    
    String applicationID_TS = getProperty(APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY);
    if (applicationID_TS != null) {
      // 
      // || FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
      //
      
      // NOTA: Amb aplication amb suport de firma només he aconseguit 
      // que es generin firmes Xades amb segell de temps
      if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
          || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
          || FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)
          ) {
        return true;
      }
    }
    
    return false;
  }

  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la
   *         Firma Visible PDF definits dins
   *         FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  @Override
  public boolean acceptExternalRubricGenerator() {
    return false; // TODO XYZ ZZZ No se COM ESTAMPAR 
  }

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         imatges de la Firma Visible PDF.
   */
  @Override
  public boolean providesRubricGenerator() {
    return false;
  }

  @Override
  public boolean acceptExternalSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public boolean providesSecureVerificationCodeStamper() {
    return false;
  }

  @Override
  public String[] getSupportedSignatureTypes() {
    // TODO Falta CADes, ...
    return new String[] { FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES,
        FileInfoSignature.SIGN_TYPE_CADES,
        FileInfoSignature.SIGN_TYPE_SMIME };
  }

  @Override
  public String[] getSupportedSignatureAlgorithms(String signType) {

    if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
        || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {

      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1,
          FileInfoSignature.SIGN_ALGORITHM_SHA256, FileInfoSignature.SIGN_ALGORITHM_SHA384,
          FileInfoSignature.SIGN_ALGORITHM_SHA512 };
    }
    if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
      return new String[] { FileInfoSignature.SIGN_ALGORITHM_SHA1 };
    }
    return null;
  }

  @Override
  public List<String> getSupportedBarCodeTypes() {
    // Aquests Plugins No suporten estampació de CSV per si mateixos
    return null;
  }

  @Override
  public String getName(Locale locale) {
    return getTraduccio("pluginname", locale);
  }

  @Override
  protected String getSimpleName() {
    return "AfirmaServerSignatureServerPlugin";
  }

  @Override
  public boolean filter(SignaturesSet signaturesSet) {

    // Requerim un username
    //if (super.filter(signaturesSet)) {
    
    final  boolean suportXAdES_T = true;
    if (checkFilter(this, signaturesSet, suportXAdES_T,  this.log)) {;

      try {
        return checkConnection(getConnectionProperties(),
            getPropertyRequired(APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY));
      } catch (Exception e) {
        log.warn("No puc obtenir el applicationID ni de defaultApplicationID ni"
            + " de signaturesSet.getCommonInfoSignature().getUsername(): " + e.getMessage());
        return false;
      }
    }

    return false;
  }

  public String getAliasCertificate(SignaturesSet signaturesSet) throws Exception {
    String usr = signaturesSet.getCommonInfoSignature().getUsername();
    if (usr != null) {
      return usr;
    } else {
      return getPropertyRequired(DEFAULT_ALIAS_CERTIFICATE_PROPERTY);
    }
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------------ FIRMAR
  // --------------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  public static final Map<String, SignaturesSet> tmpCache = new HashMap<String, SignaturesSet>();

  /**
   * 
   */
  @Override
  public SignaturesSet signDocuments(SignaturesSet signaturesSet, String timestampUrlBase) {

    try {
      
      // TODO XYZ  SON LES PROPIETATS que hi ha dins resources/ afirma5ServiceInvoker.properties
      // es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerProperties que fa.
      
      

      // / TODO XYZ ZZZ Falta fer mètode syncrhonized amb objecte
      // / TODO XYZ ZZZ Anar a classe es.gob.afirma.i18n.Language i posar dins
      // / atribut Locale currentLocale el locale del
      // commonSignatureInfo.getLanguageUI()
      // / NOTA només hi ha disponible es_ES ca_ES i en_US

      Locale def = Locale.getDefault();

      System.out.println("Locale: " + def.toString());
      System.out.println("Locale Country: " + def.getCountry()); // => ca, es,
                                                                 // en
      System.out.println("Locale Lang: " + def.getLanguage());

      // Guardam dins la cache pel tema del Segellat de Temps
      // TODO XYZ ZZZ HO necessitam a això ???
      tmpCache.put(signaturesSet.getSignaturesSetID(), signaturesSet);

      final CommonInfoSignature commonInfoSignature = signaturesSet.getCommonInfoSignature();


      FileInfoSignature[] fileInfoArray = signaturesSet.getFileInfoSignatureArray();

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

      long start;

      Locale locale = new Locale(commonInfoSignature.getLanguageUI());

      for (int i = 0; i < fileInfoArray.length; i++) {

        start = System.currentTimeMillis();
        FileInfoSignature fileInfo = fileInfoArray[i];

        // XYZ ZZZ QUE FER !!!!
        /*
        final String signaturesSetID = signaturesSet.getSignaturesSetID();
        String timestampUrl;
        if (timestampUrlBase == null || fileInfo.getTimeStampGenerator() == null) {
          timestampUrl = null;
        } else {
          timestampUrl = timestampUrlBase + "/" + signaturesSetID + "/" + i;
        }
*/

        try {
          /*
           * MiniAppletSignInfo info = null; info =
           * MiniAppletUtils.convertLocalSignature(commonInfoSignature,
           * fileInfo, timestampUrl, certificate);
           */
          
          byte[] bytesToSign = FileUtils.readFromFile(fileInfo.getFileToSign());
          
          //ByteArrayOutputStream baos = new ByteArrayOutputStream();
          //FileUtils.copyFile(fileInfo.getFileToSign(), baos);

          // FIRMA PADES o Xades
          StatusSignature ss = fileInfo.getStatusSignature();

          ss.setStatus(StatusSignature.STATUS_IN_PROGRESS);

          final String algorithm = fileInfo.getSignAlgorithm();
          String algorisme;
          if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA1;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA256;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA384;
          } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(algorithm)) {
            algorisme = AlgorithmTypes.SHA512;
          } else {
            throw new Exception("Algoritme no supportat: " + algorithm);
          }

          final String tipusFirma = fileInfo.getSignType();
          String signType;
          String signMode = null;
          if (FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma)) {
            signType = SignTypesURIs.PADES;
            
            
            //SignTypesURIs.
            
          } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())) {
            signType = SignTypesURIs.XADES_V_1_3_2;
//            if (fileInfo.isUserRequiresTimeStamp()) {
//              signType = SignTypesURIs.XML_TST;
//            }
          } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())
              || FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
            
            signType = SignTypesURIs.CADES;
//            if (fileInfo.isUserRequiresTimeStamp()) {
//              signType = SignTypesURIs.CMS_TST;
//            }

          } else {
            // TODO Falta CADes,

            // "Tipus de Firma amb ID {0} no està suportat pel plugin `{1}`
            String msg = getTraduccio("tipusfirma.desconegut", locale, fileInfo.getSignType(),
                this.getName(locale));

            ss.setErrorMsg(msg);
            ss.setErrorException(null);
            ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            continue;
          }
          
          
          // MODE PER XADES I CADES
          if (!FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma)) {
            if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_EXPLICIT) {
              signMode =  XmlSignatureMode.DETACHED;
            } else if(fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
              signMode =  XmlSignatureMode.ENVELOPING;
            } else {
              String msg = getTraduccio("modefirma.desconegut", locale, fileInfo.getSignType(),
                  this.getName(locale));
  
              ss.setErrorMsg(msg);
              ss.setErrorException(null);
              ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              continue;
            }
          }
          
          

          // System.out.println(System.getProperties().toString());

          // Instanciamos un mapa con los parámetros de entrada
          Map<String, Object> inParams = new HashMap<String, Object>();

          // Indicamos los datos a firmar 
          
          
          if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
            // SMIME
            String mimeType =  fileInfo.getMimeType();
            if (mimeType == null || mimeType.trim().length() == 0) {
              mimeType = "application/octet-stream";
            }

            MIMEInputStream mis = new MIMEInputStream(new ByteArrayInputStream(bytesToSign), mimeType);
           
            bytesToSign =  FileUtils.toByteArray(mis); //IOUtils.toByteArray(mis);
            mis.close();
          }
          
          final String applicationID;
          if (fileInfo.isUserRequiresTimeStamp()) {
            applicationID = getPropertyRequired(APPLICATIONID_AMB_SEGELLLAT_DE_TEMPS_PROPERTY);
          } else {
            applicationID = getPropertyRequired(APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY);
          }
          log.info(" XYZ ZZZ applicationID = " + applicationID);
          
          // TODO XYZ ZZZ Veure nota final mètode (isXMLFormat)
          //System.out.println("XMZ  ES PADES: " + FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma));
          boolean isXML = isXMLFormat(bytesToSign);
          log.info("XYZ ZZZ ES XML: " + isXML);

          if (!FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma) && isXML) {
            inParams.put(DSSTagsRequest.BASE64XML, new String(Base64.encode(bytesToSign)));
          } else {
            inParams.put(DSSTagsRequest.BASE64DATA, new String(Base64.encode(bytesToSign)));
          }

          // Indicamos el nombre del documento a firmar
          inParams.put(DSSTagsRequest.ADDITIONAL_DOCUMENT_NAME, fileInfo.getName());

          // Indicamos el tipo del documento a firmar
          inParams.put(DSSTagsRequest.ADDITIONAL_DOCUMENT_TYPE, fileInfo.getMimeType());

          // Indicamos el alias del certificado que usar para generar la firma
          inParams.put(DSSTagsRequest.KEY_SELECTOR, getAliasCertificate(signaturesSet));

          // Indicamos el identificador de aplicación cliente definida en
          // @firma que usar para generar la firma
          inParams.put(DSSTagsRequest.CLAIMED_IDENTITY,  applicationID);

          // Indicamos el tipo de firma a generar
          inParams.put(DSSTagsRequest.SIGNATURE_TYPE, signType);

          if (signMode != null) {
            inParams.put(DSSTagsRequest.XML_SIGNATURE_MODE, signMode);
          }

          // Indicamos el algoritmo de hash que usar para generar la firma
          inParams.put(DSSTagsRequest.HASH_ALGORITHM, algorisme);

          // Indicamos el formato de la firma a generar
          PolicyInfoSignature pis = commonInfoSignature.getPolicyInfoSignature();
          if (FileInfoSignature.SIGN_TYPE_PADES.equals(tipusFirma)) {

            if (pis != null) {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.PADES_EPES);
            } else {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.PADES_BES);
            }
          } else {
            
            // TODO XYZ ZZZ Que passa amb CADES I XADES !!!!!
            if (pis != null) {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.EPES);
            }
            /*
            else {
              inParams.put(DSSTagsRequest.SIGNATURE_FORM, SignatureForm.BES);
            }
            */
            
          }

          if (pis != null) {

            inParams.put(DSSTagsRequest.SIGPOL_SIGNATURE_POLICY_IDENTIFIER,
                pis.getPolicyIdentifier());
  
            String algHash = hashAlgorithmMap.get(pis.getPolicyIdentifierHashAlgorithm());
            if (algHash == null) {
              throw new Exception("No es troba l'algorisme en que està firmat el Hash: "
                  + pis.getPolicyIdentifierHashAlgorithm());
            }
  
            inParams.put(DSSTagsRequest.HASH_ALGORITHM, algHash);
  
            // TODO XYZ XYZ FALTA URL i HASH inParams.put(DSSTagsRequest., );
          }
          

          TransformersFacade transformersFacade = getTransformersFacade();

          // transfProp.put("TransformersTemplatesPath",
          // "D:/dades/dades/CarpetesPersonals/Programacio/portafib-1.1/plugins-signatureserver/afirma/src/main/resources/integra/transformersTemplates");

          // FieldUtils.writeField(transformersFacade, "transformersProperties",
          // transfProp , true);

          // Construimos el XML que constituye la petici�n
          String xmlInput = transformersFacade.generateXml(inParams,
              GeneralConstants.DSS_AFIRMA_SIGN_REQUEST,
              GeneralConstants.DSS_AFIRMA_SIGN_METHOD, TransformersConstants.VERSION_10);

          // Almacenamos la petici�n por si queremos procesarla XYZ ZZZ
          // FileUtils.writeByteArrayToFile(new
          // File("DSSAfirmaSignClient_prueba_7_request.xml"),
          // xmlInput.getBytes());

          // CRIDADA STRING
          // Invocamos el servicio y obtenemos un XML de respuesta
          // String xmlOutput =
          // Afirma5ServiceInvokerFacade.getInstance().invokeService(xmlInput,
          // GeneralConstants.DSS_AFIRMA_SIGN_REQUEST,
          // GeneralConstants.DSS_AFIRMA_SIGN_METHOD, "appPrueba");

          // CRIDADA PROPERTIES
          Properties properties = getConnectionProperties();

          // Invocamos el servicio y obtenemos un XML de respuesta
          String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(xmlInput,
              GeneralConstants.DSS_AFIRMA_SIGN_REQUEST,
              GeneralConstants.DSS_AFIRMA_SIGN_METHOD, properties);

          // Almacenamos la respuesta por si queremos procesarla XYZ ZZZ
          /*FileUtils.writeByteArrayToFile(new
           File("DSSAfirmaSignClient_prueba_7_response.xml"),
           xmlOutput.getBytes());
           */

          // Parseamos la respuesta en un mapa
          Map<String, Object> propertiesResult = transformersFacade.parseResponse(xmlOutput,
              GeneralConstants.DSS_AFIRMA_SIGN_REQUEST,
              GeneralConstants.DSS_AFIRMA_SIGN_METHOD, TransformersConstants.VERSION_10);

          // XYZ ZZZ
          /*
           * for (String key : propertiesResult.keySet()) {
           * 
           * System.out.println(key + " => " +
           * propertiesResult.get(key).getClass()); }
           */


          if (propertiesResult != null) {
            ServerSignerResponse response = new ServerSignerResponse();
            GenerateMessageResponse.generateServerSignerResponse(propertiesResult, response);

            String result = response.getResult().getResultMajor();

            System.out.println("RESULT = " + result);

            if (result != null && result.endsWith("Success")) {

              // TODO XYZ ZZZ result.equals(ResultProcessIds.SUCESS)

              byte[] signedData = response.getSignature();
              
              if (signedData == null) {
                // BUG del generateServerSignerResponse()
                
                // SIGNATURE_DS = "dss:SignatureObject/ds:Signature";
                signedData = propertiesResult.get(DSSTagsRequest.SIGNATURE_DS).toString().getBytes();
              }

              
              //******* SELLO DE TIEMPO PER CADES ( i indirectamente a SMIME) *****

              /*
              if (fileInfo.getTimeStampGenerator() != null) {
              
                // Aplicar Segellat de Temps si és SMIME o CADES
                if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())
                    || FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
                  final boolean isLocalSignature = true;
                  Properties tsProperties = new Properties();
                  MiniAppletUtils.convertTimeStamp(fileInfo, timestampUrl, isLocalSignature, tsProperties);;

                  try {        
                    
                    if (log.isDebugEnabled()) {
                      log.debug("storeDocument:: fisig.getSignType() => " + fileInfo.getSignType());
                      log.debug("storeDocument:: tsProperties => " + tsProperties);
                    }
                    
                    if (tsProperties.size() == 0) {
                      throw new Exception("No es troba informació per realitzar el segellat de Temps"
                          + "(tsProperties no hauria de ser buit !!!!)");
                    }

                    TsaParams tsaParams = new TsaParams(tsProperties);

                    CMSTimestamper cmsTS = new CMSTimestamper(tsaParams);
                    signedData = cmsTS.addTimestamp(
                        signedData,
                      tsaParams.getTsaHashAlgorithm(),
                      new GregorianCalendar()
                    );
                  } catch (final Exception e) {
                    String msg = "Error Aplicant Segellat de Temps a una firma CADES: " + e.getMessage(); 
                    log.error(msg, e );
                    
                    // TODO Fer Batch
                    // Estat de tots els document ja que per ara només permet 1 fitxer
                    ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
                    ss.setErrorMsg(msg);
                    ss.setErrorException(e);
                   
                    continue;

                  }
                }
              }
              
              //************** FIN SELLO DE TIEMPO ****************
               
               */

              File firmat = File.createTempFile("AfirmaServerPlugin", "signedfile");
              
              if (FileInfoSignature.SIGN_TYPE_SMIME.equals(fileInfo.getSignType())) {
                // SMIME

                String mimeType =  fileInfo.getMimeType();
                if (mimeType == null || mimeType.trim().length() == 0) {
                  mimeType = "application/octet-stream";
                }

                FileInputStream fis = new FileInputStream(fileInfo.getFileToSign());

                SMIMEInputStream smis =  new SMIMEInputStream(signedData, fis , mimeType);

                FileOutputStream fos = new FileOutputStream(firmat);
                org.fundaciobit.plugins.utils.FileUtils.copy(smis, fos);

                smis.close();
                fis.close();
                fos.flush();
                fos.close();

                
              } else {
          
                FileOutputStream fos = new FileOutputStream(firmat);
                fos.write(signedData);
                fos.flush();
                fos.close();
              }
              
            
              // Buidar memòria
              signedData = null;

              ss.setSignedData(firmat);
              ss.setStatus(StatusSignature.STATUS_FINAL_OK);

            } else {
              // String msg = (String)
              // propertiesResult.get(transformersFacade.getParserParameterValue("ResultMessage"));
              String msg = response.getResult().getResultMessage();

              if (msg == null || msg.trim().length() == 0) {
                // msg = (String)
                // propertiesResult.get(transformersFacade.getParserParameterValue("ResultMinor"));
                msg = response.getResult().getResultMinor();
              }

              if (msg == null || msg.trim().length() == 0) {
                // TODO XYZ ZZZ Traduir
                throw new Exception(
                    "No es pot determinar l'estat final de la petició de firma");
              } else {
                // TODO XYZ ZZZ Traduir
                throw new Exception("Error durant la firma: " + msg);
              }
            }

          } else {

            // TODO XYZ ZZZ Traduir
            throw new Exception(
                "No es pot determinar l'estat final de la petició de firma (XML de retorn està buit)");
          }

          if (log.isDebugEnabled()) {
            log.debug("Firma amb ID " + fileInfo.getSignID() + " finalitzada en "
                + (System.currentTimeMillis() - start) + "ms ");
          }

        } catch (Throwable th) {
          // TODO Mirar certs tipus d'excepció

          String param = fileInfo.getName() + " (" + i + ")[" + th.getClass().getName() + "]:"
              + th.getMessage();
          String msg = getTraduccio("error.firmantdocument", locale, param);

          log.error(msg, th);

          StatusSignature ss = getStatusSignature(signaturesSet, i);

          ss.setStatus(StatusSignature.STATUS_FINAL_ERROR);

          ss.setErrorException(th);

          ss.setErrorMsg(msg);
        }
      }

      signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_FINAL_OK);

    } finally {
      // Ho eliminam de la cache
      tmpCache.remove(signaturesSet.getSignaturesSetID());
    }

    
    
    return signaturesSet;

  }
  
  /*
   * Al utilitzar import net.java.xades.util.XMLUtils.isXMLFormat(bytesToSign); tal
   *  i com fa la capa de FACADE llavors llança aquest error.
  
  
  java.lang.VerifyError: (class: es/gob/afirma/utils/UtilsSignature, method: validatePAdESEnhancedOptionalAttributes signature: (Les/gob/afirma/signature/pades/PDFSignatureDictionary;Lorg/bouncycastle/asn1/cms/AttributeTable;Lorg/bouncycastle/asn1/cms/AttributeTable;Z)V) Incompatible argument to function
  at net.java.xades.util.XMLUtils.isXMLFormat(XMLUtils.java:535)
  at org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin.signDocuments(AfirmaServerSignatureServerPlugin.java:376)
  at org.fundaciobit.plugins.signatureserver.afirmaserver.test.AfirmaServerTest.signFile(AfirmaServerTest.java:199)
  at org.fundaciobit.plugins.signatureserver.afirmaserver.test.AfirmaServerTest.main(AfirmaServerTest.java:105)
Error Firma 1. MSG = Error firmant document hola.pdf (0)[java.lang.VerifyError]:(class: es/gob/afirma/utils/UtilsSignature, method: validatePAdESEnhancedOptionalAttributes signature: (Les/gob/afirma/signature/pades/PDFSignatureDictionary;Lorg/bouncycastle/asn1/cms/AttributeTable;Lorg/bouncycastle/asn1/cms/AttributeTable;Z)V) Incompatible argument to function
java.lang.Exception: Error firmant document hola.pdf (0)[java.lang.VerifyError]:(class: es/gob/afirma/utils/UtilsSignature, method: validatePAdESEnhancedOptionalAttributes signature: (Les/gob/afirma/signature/pades/PDFSignatureDictionary;Lorg/bouncycastle/asn1/cms/AttributeTable;Lorg/bouncycastle/asn1/cms/AttributeTable;Z)V) Incompatible argument to function
  at org.fundaciobit.plugins.signatureserver.afirmaserver.test.AfirmaServerTest.signFile(AfirmaServerTest.java:216)
  at org.fundaciobit.plugins.signatureserver.afirmaserver.test.AfirmaServerTest.main(AfirmaServerTest.java:105)

  
  
*/
  
  
  @Override
  public byte[] generateTimeStamp(String signaturesSetID, int signatureIndex,
      byte[] inputRequest) throws Exception {
    
    
    // TODO XYZ ZZZ Acceptam EXTERNAL TIMESTAMPER ?????
    throw new Exception("NO SUPORTAT");
/*
    final boolean isDebug = log.isDebugEnabled();

    SignaturesSet signaturesSet = tmpCache.get(signaturesSetID);
    if (signaturesSet == null) {
      throw new Exception(" Dins la cache de SignaturesSet no hi ha cap element amb id "
          + signaturesSetID);
    }

    // DEL MINIAPPLET SEMPRE REBREM UNA TimeStampRequest encoded
    TimeStampRequest tsr = new TimeStampRequest(inputRequest);

    byte[] inputData = tsr.getMessageImprintDigest();

    BigInteger time = tsr.getNonce();

    FileInfoSignature fileInfo = signaturesSet.getFileInfoSignatureArray()[signatureIndex];

    ITimeStampGenerator timeStampGen = fileInfo.getTimeStampGenerator();

    if (timeStampGen == null) {
      throw new Exception("El generador de TimeStamp per la petició amb id " + signaturesSetID
          + " val null");
    }

    try {
      final Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(time.longValue());
      byte[] returnedData = timeStampGen.getTimeStamp(inputData, cal);

      if (isDebug && returnedData != null) {
        log.info("requestTimeStamp:: returnedData LEN= " + returnedData.length);
        log.info("requestTimeStamp:: returnedData DATA= " + new String(returnedData));
      }

      return returnedData;
    } catch (Exception e) {
      throw new Exception("Error greu cridant el TimeStampGenerator: " + e.getMessage(), e);
    }
*/
  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------------------ U T I L I T A T S
  // ---------------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------

  @Override
  public String getResourceBundleName() {
    return "afirmaserver";
  }
  
  public static boolean isXMLFormat(byte[] data) {

    if (!isBinaryFile(data)) {

      boolean isXML = isXMLLike(new String(data));

      System.out.println("isXML = " + isXML);

      if (isXML) {
        return true;
      }

    }
    return false;
  }

  public static boolean isBinaryFile(byte[] data) {

    int size = data.length;
    if (size > 1024)
      size = 1024;

    int ascii = 0;
    int other = 0;

    for (int i = 0; i < size; i++) {
      byte b = data[i];
      if (b < 0x09)
        return true;

      if (b == 0x09 || b == 0x0A || b == 0x0C || b == 0x0D)
        ascii++;
      else if (b >= 0x20 && b <= 0x7E)
        ascii++;
      else
        other++;
    }

    if (other == 0)
      return false;
    return 100 * other / (ascii + other) > 95;
  }

  public static boolean isXMLLike(String inXMLStr) {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    try {
      db = dbf.newDocumentBuilder();
      InputSource is = new InputSource();
      is.setCharacterStream(new StringReader(inXMLStr));

      Document doc = db.parse(is);

      String message = doc.getDocumentElement().getTextContent();
      System.out.println(message);
      return true;
    } catch (Throwable e1) {
      // handle ParserConfigurationException
      // XYZ ZZZ log.debug
      //e1.printStackTrace();

      return false;
    }

  }

  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  // ------------- METODES COMUNICACIO AFIRMA FEDERAT ---------------------------
  // ----------------------------------------------------------------------------
  // ----------------------------------------------------------------------------
  
  
  
  
  private static void addSoftwareLibrary(File file) throws Exception {
    Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{file.toURI().toURL()});
}
  
 
  

  protected TransformersFacade getTransformersFacade() throws Exception {
    
    
    
    /*
    URL url = TransformersProperties.class.getClassLoader().getResource("transformers.properties");
         if (url == null) {
           throw new URISyntaxException("Error", Language.getFormatResIntegra("TP005", new Object[] { "transformers.properties" }));
         }
        URI uri = new URI(url.toString());
   System.out.println("XYZ ZZZ URI = " + uri);
         File res = new File(uri);
    
    log.info("XYZ ZZZ res.lastModified() = " + res.lastModified());
    
    
    FieldUtils.writeDeclaredStaticField(TransformersProperties.class, "propsFileLastUpdate", res.lastModified(), true );
    */

    String newClassPath = getPropertyRequired(TRANSFORMERSTEMPLATESPATH_PROPERTY);
    
    log.error("XYZ ZZZ newClassPath=" + newClassPath);
    
    addSoftwareLibrary(new File(newClassPath));
    
    
    
    TransformersFacade transformersFacade = TransformersFacade.getInstance();

    
    
    Properties transfProp = (Properties) FieldUtils.readField(transformersFacade,
        "transformersProperties", true);
    

    // S'obte d'una propietat
    transfProp.put("TransformersTemplatesPath",
        getPropertyRequired(TRANSFORMERSTEMPLATESPATH_PROPERTY));


    return transformersFacade;
  }

  private Properties connectionPropertiesCache = null;

  /**
   * S'han d'obtenir aquestes dades de Propietats
   * 
   * @return
   */
  protected Properties getConnectionProperties() {

    if (connectionPropertiesCache == null) {

      Properties properties = new Properties();

      final String[] allProps = { "secureMode", "endPoint", "servicePath", "callTimeout",
          "authorizationMethod", "authorizationMethod.user", "authorizationMethod.password",
          "authorizationMethod.passwordType",
          "authorizationMethod.userKeystore",
          "authorizationMethod.userKeystorePassword",
          "authorizationMethod.userKeystoreType", "response.validate",
          "response.certificateAlias" };

      final boolean debug = log.isDebugEnabled();
      for (String key : allProps) {
        String value = getProperty(AFIRMASERVER_BASE_PROPERTIES + "connection." + key);
        if (value != null) {
          properties.put(key, value);
          if (debug) {
            log.debug("Read Property[" + key + "] = " + value);
          }
        }
      }

      /*
       * properties.put("secureMode", "false"); properties.put("endPoint",
       * "localhost:8080"); properties.put("servicePath", "afirmaws/services");
       * properties.put("callTimeout", "20000000"); // Valores posibles para
       * esta propiedad: none, UsernameToken y // BinarySecurityToken");
       * properties.put("authorizationMethod", "UsernameToken"); // Usuario o
       * alias certificado"); properties.put("authorizationMethod.user",
       * "userTest"); // Password del usuario o de la clave privada del
       * certificado"); properties.put("authorizationMethod.password", "12345");
       * // Solo en caso de usuario y password: clear o digest");
       * properties.put("authorizationMethod.passwordType", "digest"); // Ruta
       * al almac�n donde se encuentran los certificados firmantes de la //
       * peticion"); properties.put("authorizationMethod.userKeystore", ""); //
       * Password del almac�n anterior");
       * properties.put("authorizationMethod.userKeystorePassword", ""); // Tipo
       * del almacen anterior: JKS, PKCS12, JCEKS");
       * properties.put("authorizationMethod.userKeystoreType", ""); // Valida
       * la respuesta firmada de @Firma. Posibles valores true/false.");
       * properties.put("response.validate", "false"); // Alias de certificado
       * usado en la valiaci�n de las respuestas");
       * properties.put("response.certificateAlias", "");
       */

      connectionPropertiesCache = properties;
    }

    return connectionPropertiesCache;
  }

  // TODO XYZ ZZZ Aquets mètode servirà per validar la comunicació amb el
  // Servidor
  // TODO XYZ ZZZ S'ha de fer
  protected boolean checkConnection(Properties connectionProperties, String applicationID) {

    try {

      VerifyCertificateRequest verCerReq = new VerifyCertificateRequest();

      verCerReq.setApplicationId(applicationID);

      InputStream is = org.fundaciobit.plugins.utils.FileUtils.readResource(
          AfirmaServerSignatureServerPlugin.class, "check/usedtocheckconnection.cer");

      if (is == null) {
        log.error("No puc llegir recurs 'check/usedtocheckconnection.cer'");
        return false;
      }

      // Indicamos el certificado que validar
      verCerReq.setCertificate(FileUtils.toByteArray(is));

      // Indicamos que queremos recuperar informaci�n del certificado en
      // lo que a campos mapeados se refiere
      verCerReq.setReturnReadableCertificateInfo(false);

      // Instanciamos el objeto que se encarga de a�adir opciones de
      // verificaci�n a la petici�n
      VerificationReport verRep = new VerificationReport();

      // Indicamos que queremos incluir la cadena de certificaci�n en la
      // respuesta
      verRep.setIncludeCertificateValues(false);

      // Indicamos que queremos verificar el estado de cada uno de los
      // certificados de la cadena de certificaci�n
      verRep.setCheckCertificateStatus(false);

      // Indicamos que queremos incluir informaci�n del estado de
      // revocaci�n de la cadena de certificaci�n en la
      // respuesta
      verRep.setIncludeRevocationValues(false);

      verRep.setReportDetailLevel(DetailLevelEnum.NO_DETAILS);

      verCerReq.setReturnVerificationReport(verRep);

      String resultValidate = ValidateRequest.validateVerifyCertificateRequest(verCerReq);

      if (resultValidate != null) {
        throw new Afirma5ServiceInvokerException(Language.getFormatResIntegra("IFWS020",
            new Object[] { resultValidate }));
      }

      Map<String, Object> inputParameters = GenerateMessageRequest
          .generateVerifyCertificateRequest(verCerReq);

      if (inputParameters == null) {
        log.error(" inputParameters val null");
        return false;
      }

      TransformersFacade transformersFacade = getTransformersFacade();
      String xmlInput = transformersFacade.generateXml(inputParameters,
          "DSSAfirmaVerifyCertificate", "verify", "1_0");

      String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(xmlInput,
          "DSSAfirmaVerifyCertificate", "verify", connectionProperties); // verCerReq.getApplicationId());

      Map<String, Object> propertiesResult = transformersFacade.parseResponse(xmlOutput,
          "DSSAfirmaVerifyCertificate", "verify", "1_0");

      if (propertiesResult == null) {
      }

      VerifyCertificateResponse verCerRes = new VerifyCertificateResponse();
      GenerateMessageResponse.generateVerifyCertificateResponse(propertiesResult, verCerRes);

      if (verCerRes.getResult() != null && verCerRes.getResult().getResultMajor() != null) {
        return true;
      } else {
        return false;
      }

      // XYZ ZZZ Comprovar
      /*
       * if (verCerRes.getResult()!= null &&
       * verCerRes.getResult().getResultMajor() != null &&
       * verCerRes.getResult().getResultMajor().endsWith("Success")) { return
       * true; } else { log.warn(" XYZ ZZZ Result major = " +
       * verCerRes.getResult().getResultMajor());
       * log.warn(" XYZ ZZZ Result minor = " +
       * verCerRes.getResult().getResultMinor());
       * log.warn(" XYZ ZZZ Result minor = " +
       * verCerRes.getResult().getResultMessage()); return false; }
       */

    } catch (Throwable e) {
      log.error(Language.getFormatResIntegra("IFWS043", new Object[] { e.getMessage() }), e);
      return false;
    }
  }

}
