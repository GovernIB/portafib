package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioUsuariAplicacioUpgradeUtils extends ConfiguracioCommonUtils {

    private static final String CONFIGURACIO_FIRMA_SIMPLE_UPGRADE_REQUEST = "firmaSimpleUpgradeRequest";

    private final FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest;

    public ConfiguracioUsuariAplicacioUpgradeUtils(FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) {
        super(ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR);
        this.firmaSimpleUpgradeRequest = firmaSimpleUpgradeRequest;
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_UPGRADE_REQUEST, firmaSimpleUpgradeRequest);
        return parameters;
    }

    @Override
    protected String getLang() {
        return firmaSimpleUpgradeRequest.getLanguageUI();
    }

    @Override
    protected long getTamanyFitxer() {
        return firmaSimpleUpgradeRequest.getSignature().getData().length;
    }

    @Override
    protected String getMimeFitxer() {
        return firmaSimpleUpgradeRequest.getSignature().getMime();
    }

    @Override
    protected boolean isUpgrade() {
        return true;
    }
}
