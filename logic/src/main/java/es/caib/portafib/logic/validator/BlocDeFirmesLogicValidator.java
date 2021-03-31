package es.caib.portafib.logic.validator;

import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.validator.BlocDeFirmesValidator;
import es.caib.portafib.logic.utils.BlocUtils;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;
import es.caib.portafib.model.dao.IFluxDeFirmesManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

public class BlocDeFirmesLogicValidator extends BlocDeFirmesValidator<BlocDeFirmesJPA> {

   @Override
   public void validate(IValidatorResult<BlocDeFirmesJPA> __vr, BlocDeFirmesJPA __target__, boolean __isNou__,
                        IBlocDeFirmesManager __blocDeFirmesManager, IFluxDeFirmesManager __fluxDeFirmesManager) {

      super.validate(__vr, __target__, __isNou__, __blocDeFirmesManager, __fluxDeFirmesManager);

      int totalFirmes = __target__.getFirmas().size();
      int minimRealFirmes = BlocUtils.minimFirmes(__target__.getFirmas());
      Integer minimDeFirmes = (Integer) __vr.getFieldValue(__target__, MINIMDEFIRMES);
      if (minimDeFirmes < minimRealFirmes || minimDeFirmes >  totalFirmes) {
         __vr.rejectValue(MINIMDEFIRMES, "blocdefirmes.error.minimdefirmes",
               new I18NArgumentString(String.valueOf(minimRealFirmes)),
               new I18NArgumentString(String.valueOf(totalFirmes))
         );
      }

   }
}
