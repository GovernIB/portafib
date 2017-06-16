package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.barcode.IBarcodePlugin;
import org.fundaciobit.plugins.signature.api.CommonInfoSignature;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.IRubricGenerator;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.utils.PluginsManager;

import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.SignBoxRectangle;

/**
 * 
 * @author anadal
 *
 */
public class SignatureUtils {

  protected static final Logger log = Logger.getLogger(SignatureUtils.class);

   /**
    * 
    * @param entitat
    * @param languageUI
    * @param username
    * @param urlFinal
    * @return
    */
   public static CommonInfoSignature getCommonInfoSignature(EntitatJPA entitat, 
       String languageUI, String username, String administrationID) {
       
       PolicyInfoSignature policyInfoSignature = null;
       if (entitat.getPolicyIdentifier() != null) {
         
         policyInfoSignature = new PolicyInfoSignature(
           entitat.getPolicyIdentifier(), entitat.getPolicyIdentifierHash(),
           entitat.getPolicyIdentifierHashAlgorithm(), entitat.getPolicyUrlDocument());
       }
       
       return new CommonInfoSignature(languageUI,
           CommonInfoSignature.cleanFiltreCertificats(entitat.getFiltreCertificats()),
           username, administrationID, policyInfoSignature); 
     }

   
   /**
    * 
    * @param signatureID
    * @param fileToSign
    * @param idname
    * @param locationSignTableID
    * @param reason
    * @param signNumber
    * @param languageSign
    * @param signTypeID
    * @param signAlgorithmID
    * @param signModeBool
    * @param firmatPerFormat
    * @param timeStampGenerator
    * @return
    * @throws I18NException
    */
   public static FileInfoSignature getFileInfoSignature(String signatureID, File fileToSign,
       String mimeType, String idname, long locationSignTableID, String reason,
       String location, String signerEmail,  int signNumber, String languageSign, long signTypeID, 
       long signAlgorithmID, boolean signModeBool, String firmatPerFormat,
       ITimeStampGenerator timeStampGenerator) throws I18NException {

     PdfVisibleSignature pdfInfoSignature = null;

     final int signMode = ((signModeBool == Constants.SIGN_MODE_IMPLICIT) ? 
            FileInfoSignature.SIGN_MODE_IMPLICIT : FileInfoSignature.SIGN_MODE_EXPLICIT); 

     String signType;

     int locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
     switch((int)signTypeID) {
       case Constants.TIPUSFIRMA_PADES:
         signType = FileInfoSignature.SIGN_TYPE_PADES;

         switch((int)locationSignTableID) { 
            case Constants.TAULADEFIRMES_SENSETAULA:
              locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
            break;
            case Constants.TAULADEFIRMES_PRIMERAPAGINA:
              locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE;
            break;
            case Constants.TAULADEFIRMES_DARRERAPAGINA:
              locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE;
            break;
            default:
               // TODO Traduir
               throw new I18NException("error.unknown", 
                   "Posicio de taula de firmes desconeguda: " + locationSignTableID);
         }

         
         if (locationSignTable != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
           
           // PDF Visible
           pdfInfoSignature = new PdfVisibleSignature();

           SignBoxRectangle signBoxRectangle = SignBoxRectangle.getPositionOfVisibleSignature(signNumber);
           
           PdfRubricRectangle prr = new PdfRubricRectangle();
           prr.setLowerLeftX(signBoxRectangle.llx);
           prr.setLowerLeftY(signBoxRectangle.lly);
           prr.setUpperRightX(signBoxRectangle.urx);
           prr.setUpperRightY(signBoxRectangle.ury);

           IRubricGenerator rubricGenerator = new PortaFIBRubricGenerator(
               languageSign, firmatPerFormat, reason, prr);

           pdfInfoSignature.setRubricGenerator(rubricGenerator);
           pdfInfoSignature.setPdfRubricRectangle(prr);
           
         }
         
         
       break;
       
       case Constants.TIPUSFIRMA_CADES:
         signType = FileInfoSignature.SIGN_TYPE_CADES;
       break;
       
       case Constants.TIPUSFIRMA_SMIME:
         signType = FileInfoSignature.SIGN_TYPE_SMIME;
       break;
         
       case Constants.TIPUSFIRMA_XADES:
         signType = FileInfoSignature.SIGN_TYPE_XADES;
       break;
       
       default:
         // TODO Traduir
         throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signTypeID);
     }

     
     String signAlgorithm;
     switch((int)signAlgorithmID) {
       case Constants.SIGN_ALGORITHM_SHA1WITHRSA:
         signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;
         break;
       case Constants.SIGN_ALGORITHM_SHA256WITHRSA:
         signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA256;
         break;
       case Constants.SIGN_ALGORITHM_SHA384WITHRSA:
         signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA384;
         break;
       case Constants.SIGN_ALGORITHM_SHA512WITHRSA:
         signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA512;
         break;

       default:
         // TODO Traduir
         throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithmID);
     }
     // Ja s'ha arreglat abans
     final SignaturesTableHeader signaturesTableHeader = null;
     // Ja s'ha arreglat abans
     final SecureVerificationCodeStampInfo csvStampInfo = null;
     
     FileInfoSignature fis = new FileInfoSignature(signatureID, fileToSign, mimeType , idname,  reason,
         location, signerEmail,  signNumber, languageSign, signType, signAlgorithm,
         signMode, locationSignTable, signaturesTableHeader, pdfInfoSignature,
         csvStampInfo,  timeStampGenerator != null, timeStampGenerator);
     
     return fis;
   };
   
   
   



   public static SignaturesSet passarelaSignaturesSetToSignaturesSet(
       AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb, 
       SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb,
       String signaturesSetID,  PassarelaSignaturesSet pss, EntitatJPA entitat) throws I18NException {
     SignaturesSet ss = new SignaturesSet();
     {
     
       FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[pss.getFileInfoSignatureArray().length];
       int count = 0;
       for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {
   
         // Preparar pàgina
         final String idname = pfis.getName();
   
         final String reason = pfis.getReason();      
         final String location = pfis.getLocation();
         final String signerEmail = pfis.getSignerEmail();
         
         final int sign_number = 1;
   
         final String langDoc = pfis.getLanguageSign();
         
         final String signID = pfis.getSignID();
         
         final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
         
   
         // Ve d'un camp d'Autofirma que indica si l'usuari vol Segellat de Temps
         boolean userRequiresTimeStamp = pfis.isUseTimeStamp();
                  
         // Aqui revisam si la voluntat de segellat de temps de l'usuari no entra en 
         // conflicte amb la configuració de segellat definida dins l'entitat
         ITimeStampGenerator timeStampGenerator;
         timeStampGenerator = PortaFIBTimeStampGenerator.getInstance(segellDeTempsPublicEjb, 
             entitat, userRequiresTimeStamp );
         
         int signTypeID = getSignTypeToPortaFIB(pfis.getSignType());
         
         final String mime;
         if (signTypeID == Constants.TIPUSFIRMA_PADES) {
           // NOTA Convertir Document a PDF i Afegir Taula de Firmes ja s'ha fet durant 
           // l'startTransacction via WS
           mime = FileInfoSignature.PDF_MIME_TYPE;
         } else {
           mime = pfis.getFileToSign().getMime();     
         }
         
         File pdfAdaptat = passarelaDeFirmaEjb.getFitxerAdaptatPath(signaturesSetID, signID);
         
         int signAlgorithm = getSignAlgorithmToPortaFIB(pfis.getSignAlgorithm());
         
         boolean signMode = getSignModeToPortaFIB(pfis.getSignMode());
               
         FileInfoSignature fis = getFileInfoSignature(signID,
             pdfAdaptat, mime, idname,
             posicioTaulaFirmesID, reason, location, signerEmail, sign_number, 
             langDoc, signTypeID, signAlgorithm,
             signMode,
             getFirmatPerFormat(entitat, langDoc), timeStampGenerator);
         
         
         fileInfoSignatureArray[count] = fis;
         count++;
         
       } 
       ss.setFileInfoSignatureArray(fileInfoSignatureArray);
       
       CommonInfoSignature commonInfoSignature;
       {
         PassarelaCommonInfoSignature cis = pss.getCommonInfoSignature();
         final String username = cis.getUsername();
         final String administrationID = cis.getAdministrationID();
         final String langUI = cis.getLanguageUI();
         commonInfoSignature = getCommonInfoSignature(entitat, 
             langUI, username, administrationID);
         if (!cis.isUsePortafibCertificateFilter()) {
           commonInfoSignature.setFiltreCertificats(cis.getFiltreCertificats());
         }
         
         PassarelaPolicyInfoSignature ppis = cis.getPolicyInfoSignature();
         if (ppis != null) {
           
           if (commonInfoSignature.getPolicyInfoSignature() != null) {
             log.warn("Ja s'ha definit una politica de Firma de l'entitat, "
                 + " però la firma via passarel·la  n'ha definida una altra !!!. "
                 + "S'utilitzarà la de la Passarel·la");
             
           }
   
           commonInfoSignature.setPolicyInfoSignature(
               new PolicyInfoSignature(ppis.getPolicyIdentifier(), ppis.getPolicyIdentifierHash(),
                   ppis.getPolicyIdentifierHashAlgorithm(), ppis.getPolicyUrlDocument()));
   
         }
       }
       ss.setCommonInfoSignature(commonInfoSignature);

       ss.setSignaturesSetID(signaturesSetID);
       
     }
     return ss;
   }


   
   public static int getSignTypeToPortaFIB(String signType) throws I18NException {
     
     if(FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
       return  Constants.TIPUSFIRMA_PADES;
     } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
       return Constants.TIPUSFIRMA_CADES;
     } else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
       return Constants.TIPUSFIRMA_SMIME;
     } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
       return Constants.TIPUSFIRMA_XADES;
     } else {
       // TODO Traduir
       throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signType);
     }
   }

   public static int getSignAlgorithmToPortaFIB(String signAlgorithm) throws I18NException {
     if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
       return Constants.SIGN_ALGORITHM_SHA1WITHRSA;
     } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
       return Constants.SIGN_ALGORITHM_SHA256WITHRSA;
     } else if ( FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
         return Constants.SIGN_ALGORITHM_SHA384WITHRSA;
     } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
         return Constants.SIGN_ALGORITHM_SHA512WITHRSA;
     } else {
       throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithm);
     }
   }
   
   
   public static boolean getSignModeToPortaFIB(int signMode) throws I18NException{
     if (FileInfoSignature.SIGN_MODE_IMPLICIT == signMode) {
       return Constants.SIGN_MODE_IMPLICIT;
     } else if (FileInfoSignature.SIGN_MODE_EXPLICIT == signMode) {
       return Constants.SIGN_MODE_EXPLICIT;
     } else {
       throw new I18NException("error.unknown", "Tipus de mode de firma no suportat " + signMode);
     } 
   }
   
   
   public static String getFirmatPerFormat(EntitatJPA entitat, String lang) {
     
     String firmatPerFormat = null;
     
     TraduccioJPA traduccio = entitat.getFirmatPerFormat();
     
     if (traduccio != null) {
       TraduccioMapJPA tm = traduccio.getTraduccio(lang);
       if (tm != null) {
         firmatPerFormat = tm.getValor();
       }
     }

     if (firmatPerFormat == null) {
       // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - Càrrec {5}} (Emissor {3})
       firmatPerFormat = I18NLogicUtils.tradueix(new Locale(lang), "firmatperformat");
     }
     
     return firmatPerFormat;

   }
   
   
   public static String getMotiuDeFirmaFormat(EntitatJPA entitat, String lang) {
     
     
     String motiuDeFirma = null;
     
     
     TraduccioJPA traduccio = entitat.getMotiuDelegacio();
     
     if (traduccio != null) {
       TraduccioMapJPA tm = traduccio.getTraduccio(lang);
       if (tm != null) {
         motiuDeFirma = tm.getValor();
       }
     }

     
     if (motiuDeFirma == null) {
       // Firma {0} ({1}) per delegació de {2} ({3}). Motiu: {4}
       motiuDeFirma = I18NLogicUtils.tradueix(new Locale(lang),"motiudelegacio");
     }
     
     return motiuDeFirma;

   }
   
   

   // -----------------------------------------------------------------
   // -----------------------------------------------------------------
   // --- UTILITATS FITXERS PADES: conversio i taula de firmes --------
   // -----------------------------------------------------------------
   // -----------------------------------------------------------------

   /**
    * 
    * @param usuariEntitat
    * @param id
    * @param autoFirmaForm
    * @return
    */
   public static void convertirDocumentAPDF(Fitxer srcInfo, File src, File dst)
       throws I18NException {

     try {

       Fitxer fitxerConvertit = PdfUtils.convertToPDF(src, srcInfo);

       if (fitxerConvertit == srcInfo) {
         // Es un PDF. Movem l'original a l'adaptat
         FileUtils.moveFile(src, dst);
       } else {
         // No és un PDF, ho substituim pel fitxer convertit

         InputStream is = fitxerConvertit.getData().getInputStream();

         FileUtils.copyInputStreamToFile(is, dst);

       }
       // OK

     } catch (Exception e) {
       log.error("Error desconegut convertint document a pdf: " + e.getMessage(), e);
       throw new I18NException("formatfitxer.conversio.error", new I18NArgumentString(
           e.getMessage()));
     }

   }

   /**
    * 
    * @param fitxerPDF
    * @param stampTaulaDeFirmes
    * @param stampCustodiaInfo
    * @throws I18NException
    */
   public static int afegirTaulaDeFirmesCodiSegurVerificacio(File fitxerPDF,
       StampTaulaDeFirmes stampTaulaDeFirmes, StampCustodiaInfo stampCustodiaInfo)
           throws I18NException {

     // La pujada de fitxers des d'autofirma ho gestiona la classe
     // PortaFIBCommonsMultipartResolver
     final Long maxSizeFitxerAdaptat = null;
     try {
       File tmpDest = File.createTempFile("Passarela_Taula_de_Firmes", ".pdf");

       final int originalNumberOfSigns = PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(fitxerPDF, tmpDest, null,
           maxSizeFitxerAdaptat, stampTaulaDeFirmes, stampCustodiaInfo);

       // Destí no pot existir !!!
       fitxerPDF.delete();

       FileUtils.moveFile(tmpDest, fitxerPDF);
       
       return originalNumberOfSigns;
     } catch (Exception e) {
       // TODO traduir
       String msg = "Error desconegut afegint taula de firmes a fitxer ("
           + fitxerPDF.getAbsolutePath() + "): " + e.getMessage();
       throw new I18NException("error.unknown", msg);
     }
   }
   
   
   /**
    * 
    * @param locale
    * @param entitatID
    * @param pfis
    * @param original
    * @param adaptat
    * @param entitatEjb
    * @param codiBarresEjb
    * @return
    * @throws I18NException
    */
   public static int processFileToSign(Locale locale, String entitatID,
       PassarelaFileInfoSignature pfis, File original, File adaptat, 
       EntitatLocal entitatEjb, CodiBarresLocal codiBarresEjb)
       throws I18NException {
     
     
     
     
     // (1) Moure FitxerBean (datasource en memòria) a Fitxer en el Sistema
     // d'arxius
     FitxerBean originalInfo = pfis.getFileToSign();
     
     try {
       FileUtils.copyInputStreamToFile(originalInfo.getData().getInputStream(), original);
     } catch (IOException e) {
       // TODO traduir
       String msg = "Error desconegut copiant fitxer des de DataSource ("
           + pfis.getSignID() + ") a " + original.getAbsolutePath() + ": " + e.getMessage();
       throw new I18NException("error.unknown", msg);
     }
     // Desreferenciam memoria
     originalInfo.setData(null);
     // Alliberar memòria DataSource
     System.gc();

     // (2) Adaptam el fitxer
     
     
     
     if (FileInfoSignature.SIGN_TYPE_PADES.equals(pfis.getSignType())) {

       // (a.2.1) Converteix a PDF si necessari. En qualsevol cas deixa el
       // fitxer a "adaptat"
       SignatureUtils.convertirDocumentAPDF(originalInfo, original, adaptat);

       StampTaulaDeFirmes stampTaulaDeFirmes = null;

       // (a.2.2) Afegir taula de firmes
       final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
       if (posicioTaulaFirmesID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
         final byte[] logoSegellJpeg;

         final String titol;
         final String descripcio;
         final String signantLabel;
         final String resumLabel;
         final String titolLabel;
         final String descLabel;

         PassarelaSignaturesTableHeader tableHeader = pfis.getSignaturesTableHeader();

         if (tableHeader == null) {

           final Long logoSegellID = entitatEjb.executeQueryOne(EntitatFields.LOGOSEGELLID,
               EntitatFields.ENTITATID.equal(entitatID));
           try {
             logoSegellJpeg = FileUtils.readFileToByteArray(FileSystemManager
                 .getFile(logoSegellID));
           } catch (IOException e) {
             // TODO Traduir
             String msg = "Error desconegut llegint logo-segell de l'entitat " + entitatID
                 + ": " + e.getMessage();                
             throw new I18NException("error.unknown", msg);
           }

           Locale localeSign = new Locale(pfis.getLanguageSign());

           titol = I18NLogicUtils.tradueix(locale, "tauladefirmes");
           descripcio = ""; // TODO Posar alguna cosa ????

           signantLabel = I18NLogicUtils.tradueix(localeSign, "signant");
           resumLabel = I18NLogicUtils.tradueix(localeSign, "resumdefirmes");
           titolLabel = I18NLogicUtils.tradueix(localeSign, "titol");
           descLabel = I18NLogicUtils.tradueix(localeSign, "descripcio");

         } else {

           logoSegellJpeg = tableHeader.getLogoJpeg();

           titol = tableHeader.getTitleFieldValue();
           descripcio = tableHeader.getDescriptionFieldValue();

           signantLabel = tableHeader.getSignatureLabel();
           resumLabel = tableHeader.getTitle();
           titolLabel = tableHeader.getTitleFieldLabel();
           descLabel = tableHeader.getDescriptionFieldLabel();
         }

         stampTaulaDeFirmes = new StampTaulaDeFirmes(pfis.getSignNumber(),
             posicioTaulaFirmesID, signantLabel, resumLabel, descLabel, descripcio,
             titolLabel, titol, logoSegellJpeg);
       }

       StampCustodiaInfo stampCodiSegurVerificacio = null;
       PassarelaSecureVerificationCodeStampInfo pcvsStamp = pfis
           .getSecureVerificationCodeStampInfo();

       if (pcvsStamp != null) {

         // TODO Message Position s'usarà per CodiBarPosition !!!!!
         if (pcvsStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {

           String codiBarresClass = codiBarresEjb.executeQueryOne(
               CodiBarresFields.CODIBARRESID,
               CodiBarresFields.NOM.equal(pcvsStamp.getBarCodeType()));

           if (codiBarresClass == null) {
             // TODO Traduir
             String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                 + pcvsStamp.getBarCodeType();                
             throw new I18NException("error.unknown", msg);
           }

           IBarcodePlugin barcodePlugin;
           barcodePlugin = (IBarcodePlugin) PluginsManager
               .instancePluginByClassName(codiBarresClass);

           stampCodiSegurVerificacio = new StampCustodiaInfo();

           stampCodiSegurVerificacio.setBarcodePlugin(barcodePlugin);
           stampCodiSegurVerificacio.setBarcodeText(pcvsStamp.getBarCodeText());
           stampCodiSegurVerificacio.setMissatgeCustodia(pcvsStamp.getMessage());
           stampCodiSegurVerificacio.setPagines(pcvsStamp.getPages());
           stampCodiSegurVerificacio.setPosicioCustodiaInfo(pcvsStamp.getMessagePosition());

         }
       }

       return SignatureUtils.afegirTaulaDeFirmesCodiSegurVerificacio(adaptat, stampTaulaDeFirmes,
           stampCodiSegurVerificacio);
       // Final IF PADES
     } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(pfis.getSignType())  
         || FileInfoSignature.SIGN_TYPE_CADES.equals(pfis.getSignType())
         || FileInfoSignature.SIGN_TYPE_SMIME.equals(pfis.getSignType())) {
       // L'original és l'adaptat, per això el movem allà
       try {
         FileUtils.moveFile(original, adaptat);
       } catch (Exception e) {
         log.error(" Error movent fitxer des de " + original.getAbsolutePath() + " a "
             + adaptat.getAbsolutePath(), e);
         throw new I18NException("error.copyfile", original.getAbsolutePath(),
             adaptat.getAbsolutePath());
       }
       return 0;

     } else {
        throw new I18NException(new Exception(), "error.desconegut",
            new I18NArgumentString("Tipus de Signatura " + pfis.getSignType() 
            + " no supportat dins la classe " + SignatureUtils.class.getName()));
     }

   }
   

   /* 
    * Si es modifica el valor d'aquesta constant llavors s'ha d'adaptar el web.xml 
    * del projecte back. 
    *  
    */
   public static final String CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR = "/common/timestampgenerator";
   
   /**
    * 
    * @param request
    * @param webContext
    * @param pluginID
    * @param signaturesSetID
    * @param signatureIndex
    * @return
    */
   public static String getAbsoluteURLToTimeStampGeneratorPerFirmaEnServidor(
       String basePath, Long pluginID) {

     String absoluteRequestPluginBasePath = basePath 
         + CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR         
         + "/" + pluginID;
     // NOTA signaturesSetID i signatureIndex s'afegeix dins dels propi plugin;

     return absoluteRequestPluginBasePath;
   }

   
}
