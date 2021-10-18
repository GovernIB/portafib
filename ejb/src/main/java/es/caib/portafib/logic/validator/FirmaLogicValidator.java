package es.caib.portafib.logic.validator;

import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.validator.FirmaValidator;
import es.caib.portafib.logic.utils.FirmaUtils;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;
import es.caib.portafib.model.dao.IFirmaManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

public class FirmaLogicValidator extends FirmaValidator<FirmaJPA> {

   @Override
   public void validate(IValidatorResult<FirmaJPA> __vr, FirmaJPA __target__, boolean __isNou__, IBlocDeFirmesManager __blocDeFirmesManager, IFirmaManager __firmaManager, IUsuariEntitatManager __usuariEntitatManager) {
      super.validate(__vr, __target__, __isNou__, __blocDeFirmesManager, __firmaManager, __usuariEntitatManager);

      int totalRevisors = __target__.getRevisorDeFirmas().size();
      int minimRealRevisors = FirmaUtils.minimRevisors(__target__.getRevisorDeFirmas());

      Integer minimDeRevisors = (Integer) __vr.getFieldValue(__target__, MINIMDEREVISORS);
      if (minimDeRevisors < minimRealRevisors || minimDeRevisors > totalRevisors) {
         __vr.rejectValue(MINIMDEREVISORS, "firma.error.minimderevisors",
              new I18NArgumentString(String.valueOf(minimRealRevisors)),
              new I18NArgumentString(String.valueOf(totalRevisors))
         );
      }
   }
}
