package es.caib.portafib.logic.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.validation.IValidatorResult;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;







import es.caib.portafib.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * 
 * @author anadal
 *
 * @param <T>
 */
public class SignaturesSetValidator<T extends PassarelaSignaturesSet> {

  protected final Logger log = Logger.getLogger(getClass());

  public SignaturesSetValidator() {
    super();
  }

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__,
       AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb, String entitatID) {

    
    final PassarelaSignaturesSet pss = __target__;

    // Comprovar signatureSetID #436
    if (isEmpty(pss.getSignaturesSetID())) {
      Field<?> f = getF("signaturesSetID");
      __vr.rejectValue(f, "genapp.validation.required",
          new I18NArgumentString(f.javaName));
    } else {
      if (pss.getSignaturesSetID().length() > 100) {
        Field<?> f = getF("signaturesSetID");
        __vr.rejectValue(f, "genapp.validation.sizeexceeds",
                new I18NArgumentString(f.javaName), new I18NArgumentString(String.valueOf(100)));
      }
    }

    // Valors Not Null
    if (pss.getCommonInfoSignature() == null) {
      Field<?> f = getF("commonInfoSignature");
      __vr.rejectValue(f, "genapp.validation.required",
          new I18NArgumentString(f.javaName));
    }

    if (pss.getFileInfoSignatureArray() == null || pss.getFileInfoSignatureArray().length == 0) {
      Field<?> f = getF("fileInfoSignatureArray");
      __vr.rejectValue(f, "genapp.validation.required",
          new I18NArgumentString(f.javaName));
    } else {

      // TODO Falten tots els tipus
      
      // TODO falta validar CSV !!!
  
      // No suportam Custòdia
      for (int i = 0; i < pss.getFileInfoSignatureArray().length; i++) {
        PassarelaFileInfoSignature pfis = pss.getFileInfoSignatureArray()[i];
        
        if (pfis == null) {
          Field<?> f = getF("fileInfoSignatureArray");
          __vr.rejectValue(f, "genapp.validation.required",
              new I18NArgumentString(f.javaName));
        }
        
       
        // --------------------  FileToSign
        FitxerBean fitxer = pfis.getFileToSign(); 
        if (fitxer== null) {
          Field<?> f = getF("fileInfoSignatureArray.fileToSign");
          __vr.rejectValue(f, "genapp.validation.required",
              new I18NArgumentString(f.javaName));

        } else {
          
          // Valors Not Null
          
          if (isEmpty(fitxer.getNom())) {
            Field<?> NOM = getF("fileInfoSignatureArray.fileToSign.nom");
            __vr.rejectValue(NOM, "genapp.validation.required",
              new I18NArgumentString(get(NOM)));
          }

          if (fitxer.getTamany() <= 0) {
            Field<?> TAMANY = getF("fileInfoSignatureArray.fileToSign.tamany");
            __vr.rejectValue(TAMANY, 
              "genapp.validation.required",
              new I18NArgumentString(get(TAMANY)));
          }

          if (isEmpty(fitxer.getMime())) {
            Field<?> MIME = getF("fileInfoSignatureArray.fileToSign.mime");
            __vr.rejectValue(MIME, 
              "genapp.validation.required",
              new I18NArgumentString(get(MIME)));
          }
        }
        
        
        //pfis.getSignID
        if (isEmpty(pfis.getSignID())) {
          Field<?> SIGNID = getF("fileInfoSignatureArray.signID");
          __vr.rejectValue(SIGNID, 
            "genapp.validation.required", new I18NArgumentString(get(SIGNID)));
        }
        
        //pfis.getLanguageSign()
        if (isEmpty(pfis.getLanguageSign())) {
          Field<?> FIELD = getF("fileInfoSignatureArray.languageSign");
          __vr.rejectValue(FIELD, 
            "genapp.validation.required",
            new I18NArgumentString(get(FIELD)));
        }
        
        //pfis.getName()
        if (isEmpty(pfis.getName())) {
          Field<?> FIELD = getF("fileInfoSignatureArray.fileToSign.name");
          __vr.rejectValue(FIELD, 
            "genapp.validation.required",
            new I18NArgumentString(get(FIELD)));
        }
        
        // pfis.getReason()
        if (isEmpty(pfis.getReason())) {
          Field<?> FIELD = getF("fileInfoSignatureArray.reason");
          __vr.rejectValue(FIELD, 
            "genapp.validation.required",
            new I18NArgumentString(get(FIELD)));
        }
        
        // -----------------------------------
        boolean signTypeOK = false;
        String signType = pfis.getSignType();
        {
          Field<?> SIGNTYPE = getF("fileInfoSignatureArray.signType");
          
          if (isEmpty(signType)) {
            
            __vr.rejectValue(SIGNTYPE, 
              "genapp.validation.required", new I18NArgumentString(get(SIGNTYPE)));
          } else {
            List<String> list = Arrays.asList(passarelaDeFirmaEjb.getSupportedSignatureTypes(entitatID, null, null)); 
            if (list.size() == 0) {
              // No hi ha plugins disponibles
              __vr.rejectValue(SIGNTYPE, "plugin.signatureserver.none", new I18NArgumentString(entitatID));
            } else {
              // Algun dels plugins suporta el tipus de firma 
              if (!list.contains(signType)) {
                
                __vr.rejectValue(SIGNTYPE, "error.passarela.field.fixedvalues",
                  new I18NArgumentString(get(SIGNTYPE)), new I18NArgumentString(
                      Arrays.toString(passarelaDeFirmaEjb.getSupportedSignatureTypes(entitatID, null, null))
                       ));
              } else {
                signTypeOK = true;
              }
            }
          }
        }
        
        // -------------------------
        if (signTypeOK) {

          Field<?> SIGNALGO = getF("fileInfoSignatureArray.fileToSign.signAlgorithm");
          String signAlgo = pfis.getSignAlgorithm();
          if (isEmpty(signType)) {
            __vr.rejectValue(SIGNALGO, 
              "genapp.validation.required", new I18NArgumentString(get(SIGNALGO)));
          } else {
            if (!Arrays.asList(passarelaDeFirmaEjb.getSupportedSignatureAlgorithms(signType, entitatID, null, null)).contains(signAlgo)) {
              __vr.rejectValue(SIGNALGO, "error.passarela.field.fixedvalues",
                new I18NArgumentString(get(SIGNALGO)), new I18NArgumentString(
                    Arrays.toString(passarelaDeFirmaEjb.getSupportedSignatureAlgorithms(signType, entitatID, null, null))
                     ));
            }
          }
        }
        
        
//        peticioDeFirma.tipusFirmaID=Tipus Firma
//            peticioDeFirma.algorismeDeFirmaID=Algorisme de firma
//            peticioDeFirma.modeDeFirma=Mode de firma
        
        

        // ------------------------
        if (pfis.getSignMode() != FileInfoSignature.SIGN_MODE_EXPLICIT
            && pfis.getSignMode() != FileInfoSignature.SIGN_MODE_IMPLICIT) {
          // El campo {0} solamente acepta los siguientes valores: {1}.
          Field<?> STL = getF("fileInfoSignatureArray.fileToSign.signMode");
          __vr.rejectValue(STL, "error.passarela.field.fixedvalues",
            new I18NArgumentString(get(STL)), new I18NArgumentString(
                "" + FileInfoSignature.SIGN_MODE_EXPLICIT + ", " 
                +  FileInfoSignature.SIGN_MODE_IMPLICIT ));
        }

        // ------------------------
        int tl = pfis.getSignaturesTableLocation(); // RANG -1, 0, 1
        if (tl == FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE
            || tl == FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE
            || tl == FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
          // OK
        } else {
          // El campo {0} solamente acepta los siguientes valores: {1}.
          Field<?> STL = getF("fileInfoSignatureArray.fileToSign.signaturesTableLocation");
          __vr.rejectValue(STL, "error.passarela.field.fixedvalues",
            new I18NArgumentString(get(STL)), new I18NArgumentString(
                "" + FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE + ", " 
                + FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE + ", " 
                + FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT));
        }
        
        // ------------------------
        
        if (!(passarelaDeFirmaEjb instanceof PassarelaDeFirmaEnServidorLocal)) {
           
          if (pfis.getSignNumber()  != 1) {
            // El campo {0} solamente acepta los siguientes valores: {1}.
            Field<?> SIGNNUMBER = getF("fileInfoSignatureArray.fileToSign.signNumber");
            __vr.rejectValue(SIGNNUMBER, "error.passarela.field.fixedvalues",
              new org.fundaciobit.genapp.common.i18n.I18NArgumentString(get(SIGNNUMBER)), new I18NArgumentString("1"));
          }
        }

        // ------------------------
        
        int politicaSegellTemps;
        try {
          politicaSegellTemps = passarelaDeFirmaEjb.getTimeStampPolicy(entitatID);
        } catch (I18NException e) {
          log.error(I18NLogicUtils.getMessage(e, new Locale("ca")));
          politicaSegellTemps = ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR;
        }
        if (pfis.isUseTimeStamp()) {
          if (politicaSegellTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR) {
            // Segellat de Temps en l´entitat {0} està deshabilitat.
            __vr.rejectValue(getF("fileInfoSignatureArray.useTimeStamp"),
                "error.passarela.segelltemps.noespotusar", new I18NArgumentString(entitatID));
          }
        } else {
          if (politicaSegellTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI) {
            // Segellat de Temps és obligatori dins l´entitat {0}.
            __vr.rejectValue(getF("fileInfoSignatureArray.useTimeStamp"),
                "error.passarela.segelltemps.obligatori", new I18NArgumentString(entitatID));
          }
        }
 
      }
    }

  } // Final de mètode

  public Field<?> getF(String path) {
    
    return new StringField("", path, path);
  }

  public String get(Field<?> field) {
    return field.javaName;
  }
  
  public boolean isEmpty(String str ) {
    return str == null || str.trim().isEmpty();
  }
  
}
