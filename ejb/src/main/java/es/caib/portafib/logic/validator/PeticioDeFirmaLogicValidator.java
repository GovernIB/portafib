package es.caib.portafib.logic.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import com.itextpdf.text.pdf.PdfReader;

import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.persistence.validator.PeticioDeFirmaValidator;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 * @param <T>
 */
public class PeticioDeFirmaLogicValidator<T extends PeticioDeFirma> extends PeticioDeFirmaValidator<T> {

    @Override
    public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__,
            es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
            es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager,
            es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
            es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
            es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
            es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
            es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager,
            es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

        super.validate(__vr, __target__, __isNou__, __custodiaInfoManager, __fluxDeFirmesManager, __idiomaManager,
                __peticioDeFirmaManager, __tipusDocumentManager, __usuariAplicacioManager,
                __usuariAplicacioConfiguracioManager, __usuariEntitatManager);

        if (__vr.getFieldErrorCount(FITXERAFIRMARID) == 0 && __vr.getFieldErrorCount(TIPUSFIRMAID) == 0) {
            int tipus = (java.lang.Integer) __vr.getFieldValue(__target__, TIPUSFIRMAID);
            if (tipus == ConstantsV2.TIPUSFIRMA_PADES) {
                InputStream is = null;
                try {
                    long fitxerID = (Long) __vr.getFieldValue(__target__, FITXERAFIRMARID);
                    File pdf = FileSystemManager.getFile(fitxerID);
                    is = new FileInputStream(pdf);
                    new PdfReader(is);
                } catch (com.itextpdf.text.exceptions.BadPasswordException pdfAmbContrasenyaExcepcio) {
                    log.error("Fitxer està protegit amb contrasenya:" + pdfAmbContrasenyaExcepcio.getMessage(),
                            pdfAmbContrasenyaExcepcio);
                    __vr.rejectValue(FITXERAFIRMARID, "peticiodefirma.pdfambcontrasenya", new I18NArgumentString(""));

                } catch (Throwable e) {
                    log.error("Fitxer no es pot llegir o no és PDF:" + e.getMessage(), e);
                    __vr.rejectValue(FITXERAFIRMARID, "peticiodefirma.fitxernoespdf", new I18NArgumentString(""));
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Throwable e) {
                            log.error("Error tancant fitxer a validar:" + e.getMessage(), e);
                        }
                    }
                }

            }
        }

        if (__vr.getFieldErrorCount(DATACADUCITAT) == 0) {

            Date dataCaducitat = (Date) __vr.getFieldValue(__target__, DATACADUCITAT);

            Calendar avui = Calendar.getInstance();
            final int diesAfegits = 3;
            avui.add(Calendar.DATE, diesAfegits);
            avui.set(Calendar.HOUR_OF_DAY, 23);
            avui.set(Calendar.MINUTE, 59);
            avui.set(Calendar.SECOND, 59);
            if (!dataCaducitat.after(avui.getTime())) {
                __vr.rejectValue(DATACADUCITAT, "peticiodefirma.datacaducitat.superior",
                        new I18NArgumentString(String.valueOf(diesAfegits)));
            }
        }

    }

}
