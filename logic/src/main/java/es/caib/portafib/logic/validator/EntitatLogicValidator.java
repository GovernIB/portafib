package es.caib.portafib.logic.validator;

import org.fundaciobit.pluginsib.core.utils.Base64;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.EntitatValidator;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * 
 * @author anadal
 *
 */
public class EntitatLogicValidator<T> extends EntitatValidator<T>{

  @Override
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
      ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
      ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
      ,es.caib.portafib.model.dao.IPluginManager __pluginManager
      ,es.caib.portafib.model.dao.ITraduccioManager __traduccioManager
      ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    
    super.validate(__vr, __target__, __isNou__,
        __custodiaInfoManager, __entitatManager, __pluginManager,
        __traduccioManager, __usuariAplicacioManager);
    
    
    Integer segellatWeb = (Integer)__vr.getFieldValue(__target__, POLITICASEGELLATDETEMPS);
    if (segellatWeb != ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR) {
      // Requerim un plugin de segellat definit
      Long pluginSegellatID = (Long)__vr.getFieldValue(__target__, PLUGINID);
      if (pluginSegellatID == null) {
        __vr.rejectValue(PLUGINID, 
            "genapp.validation.required",
            new I18NArgumentCode(get(PLUGINID)));
      }
    }
    
    String oid = (String)__vr.getFieldValue(__target__, POLICYIDENTIFIER);
        
    if (oid == null || oid.trim().length() == 0) {
      // Error si policyIdentifierHash, policyIdentifierHashAlgorithm o 
      // policyUrlDocument es diferent de buit
      
      Field<?>[] fields = {
          POLICYIDENTIFIERHASH, POLICYIDENTIFIERHASHALGORITHM,
          POLICYURLDOCUMENT
      };
      for (Field<?> field : fields) {
        String value = (String)__vr.getFieldValue(__target__, field);
        if (value != null && value.trim().length() != 0) {
          __vr.rejectValue(field, 
              "genapp.validation.mustbeempty",
              new I18NArgumentCode(get(field)));
        }
      }
      
      
    } else {
      // policyIdentifierHash i policyIdentifierHashAlgorithm han de ser no nulls
      // i a més policyIdentifierHash ha d'estar en Base64
      
      __vr.rejectIfEmptyOrWhitespace(__target__,POLICYIDENTIFIERHASH, 
          "genapp.validation.required",
          new I18NArgumentCode(get(POLICYIDENTIFIERHASH)));
      
      if (__vr.getFieldErrorCount(POLICYIDENTIFIERHASH) == 0) {
        try {
          String hashB64 = (String)__vr.getFieldValue(__target__, POLICYIDENTIFIERHASH);
          Base64.decode(hashB64);
        } catch(Exception e) {
          __vr.rejectValue(POLICYIDENTIFIERHASH, 
              "entitat.error.noestaenbase64",
              new I18NArgumentCode(get(POLICYIDENTIFIERHASH)));
        }
      }

      __vr.rejectIfEmptyOrWhitespace(__target__,POLICYIDENTIFIERHASHALGORITHM, 
          "genapp.validation.required",
          new I18NArgumentCode(get(POLICYIDENTIFIERHASHALGORITHM)));
      
      if(__vr.getFieldErrorCount(POLICYIDENTIFIERHASHALGORITHM) == 0) {
        String value = (String)__vr.getFieldValue(__target__, POLICYIDENTIFIERHASHALGORITHM);
        final String pattern = "SHA1|SHA-256|SHA-384|SHA-512"; 
        if (!value.matches(pattern)) {
          __vr.rejectValue(POLICYIDENTIFIERHASHALGORITHM, 
              "entitat.error.hashalgorithmincorrectvalue",
              new I18NArgumentCode(get(POLICYIDENTIFIERHASHALGORITHM)),
              new I18NArgumentString(pattern));
        }
      }
    }
    
  }
  
}
