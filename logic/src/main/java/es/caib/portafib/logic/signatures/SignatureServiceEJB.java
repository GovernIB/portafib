package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.portafib.logic.ValidacioException;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.datasource.FitxerIdDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.plugins.validatesignature.api.ValidationStatus;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Locale;

@Stateless
@Local(SignatureServiceLocal.class)
@SecurityDomain("seycon")
public class SignatureServiceEJB implements SignatureServiceLocal {

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

    private SignatureExtractorFactory extractorFactory;

    @PostConstruct
    protected void init() {
        extractorFactory = SignatureExtractorFactory.getInstance();
    }

    @Override
    public List<Signature> getSignatures(Fitxer fitxer) throws I18NException {
        if (fitxer == null) {
            throw new IllegalArgumentException("fitxer no pot ser null");
        }

        SignatureExtractor extractor = extractorFactory.getExtractor(fitxer);
        IPortaFIBDataSource dataSource = new FitxerIdDataSource(fitxer.getFitxerID());
        return extractor.extract(dataSource);
    }

    @Override
    public SignatureValidation getSignaturesValidation(Fitxer fitxer, String entitat, String lang)
            throws I18NException {
        if (fitxer == null) {
            throw new IllegalArgumentException("fitxer no pot ser null");
        }

        try {
            IPortaFIBDataSource dataSource = new FitxerIdDataSource(fitxer.getFitxerID());
            String signType = SignType.fromFile(fitxer).typeName();
            ValidateSignatureResponse response =
                    validacioFirmesEjb.validateSignature(entitat, signType, dataSource, null, lang);
            if (response == null) {
                // un respose null és la manera actual de dir que no hi ha plugin de validació
                String message = I18NLogicUtils.tradueix(new Locale(lang), "peticiodefirma.error.nopluginvalidacio");
                return SignatureValidation.error(message);
            }

            int status = response.getValidationStatus().getStatus();
            switch (status) {
                case ValidationStatus.SIGNATURE_VALID:
                    return SignatureValidation.valid();

                case ValidationStatus.SIGNATURE_INVALID:
                    return SignatureValidation.invalid(response.getValidationStatus().getErrorMsg());

                case ValidationStatus.SIGNATURE_ERROR:
                    return SignatureValidation.error(response.getValidationStatus().getErrorMsg());

                default:
                    throw new IllegalStateException("Status de validació desconegut: " + status);
            }
        } catch (ValidacioException e) {
            return SignatureValidation.error(e.getMessage());
        }
    }

}
