package es.caib.portafib.logic.utils;

import java.io.File;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
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

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.TraduccioJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
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
    * @param browserSupportsJava
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
   
         File pdfAdaptat = passarelaDeFirmaEjb.getFitxerAdaptatPath(signaturesSetID, signID);
         
         ITimeStampGenerator timeStampGenerator = null;
         
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
   
}
