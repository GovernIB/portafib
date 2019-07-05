package es.caib.portafib.logic.validator;

import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.validator.FirmaValidator;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;
import es.caib.portafib.model.dao.IFirmaManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

public class FirmaLogicValidator extends FirmaValidator<FirmaJPA> {

   @Override
   public void validate(IValidatorResult<FirmaJPA> __vr, FirmaJPA __target__, boolean __isNou__, IBlocDeFirmesManager __blocDeFirmesManager, IFirmaManager __firmaManager, IUsuariEntitatManager __usuariEntitatManager) {
      super.validate(__vr, __target__, __isNou__, __blocDeFirmesManager, __firmaManager, __usuariEntitatManager);

      int revisors = __target__.getRevisorDeFirmas() != null ? __target__.getRevisorDeFirmas().size() : 0;

      Integer minimDeRevisors = (Integer) __vr.getFieldValue(__target__, MINIMDEREVISORS);
      if (minimDeRevisors < 0 || minimDeRevisors > revisors) {
         __vr.rejectValue(MINIMDEREVISORS, "blocdefirmes.error.minimdefirmes",
               new I18NArgumentString(String.valueOf(revisors)));
      }
   }
}
