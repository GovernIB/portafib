package org.fundaciobit.pluignsib.signatureweb.fortress;

import com.viafirma.fortress.sdk.FortressApi;
import com.viafirma.fortress.sdk.configuration.FortressApiConfiguration;
import com.viafirma.fortress.sdk.model.AccessToken;
import com.viafirma.fortress.sdk.model.signature.SignatureConfiguration;
import com.viafirma.fortress.sdk.model.signature.SignatureRequest;
import com.viafirma.fortress.sdk.model.signature.SignatureRequestResponse;
import com.viafirma.fortress.sdk.model.signature.SignatureResponse;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractMiniAppletSignaturePlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static com.viafirma.fortress.sdk.FortressApi.GRANT_TYPE_CLIENT_CREDENTIALS;

/**
 * Implementació del plugin de firma web de ViaFirma - Fortress
 *
 * @author areus
 */
public class FortressSignatureWebPlugin extends AbstractMiniAppletSignaturePlugin {

    public static final String FORTRESS_BASE_PROPERTIES = PLUGINSIB_SIGNATUREWEB_BASE_PROPERTY + "fortress.";

    private final Map<String, String> executionCodes = new ConcurrentHashMap<String, String>(20);

    // Constructors per defecte

    public FortressSignatureWebPlugin() {
        super();
    }

    public FortressSignatureWebPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    public FortressSignatureWebPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    // Propietats de configuració

    public String getUrl() throws Exception {
        return getPropertyRequired(FORTRESS_BASE_PROPERTIES + "url");
    }

    public String getClientId() throws Exception {
        return getPropertyRequired(FORTRESS_BASE_PROPERTIES + "client_id");
    }

    public String getClientSecret() throws Exception {
        return getPropertyRequired(FORTRESS_BASE_PROPERTIES + "client_secret");
    }

    public boolean isDebug() throws Exception {
        return "true".equalsIgnoreCase(getPropertyRequired(FORTRESS_BASE_PROPERTIES + "debug"));
    }

    /////////

    private volatile FortressApi api;

    public FortressApi getApi() throws Exception {
        FortressApi result = api;
        if (result == null) {
            synchronized (this) {
                if (api == null) {
                    FortressApiConfiguration conf =
                            new FortressApiConfiguration(getUrl(), getClientId(), getClientSecret());
                    conf.setDebug(isDebug());
                    api = result = new FortressApi(conf);
                }
            }
        }
        return result;
    }

    @Override
    public String getName(Locale locale) {
        return getTraduccio("pluginname", locale);
    }

    @Override
    public String filter(HttpServletRequest request, SignaturesSetWeb signaturesSet,
                         Map<String, Object> parameters) {
        // A priori no tenim manera de saber si l'usuari ja existeix o no a viafirma
        return super.filter(request, signaturesSet, parameters);
    }

    @Override
    public void closeSignaturesSet(HttpServletRequest request, String id) {
        executionCodes.remove(id);
        super.closeSignaturesSet(request, id);
    }

    @Override
    public String signDocuments(HttpServletRequest request, String absolutePluginRequestPath,
                                String relativePluginRequestPath, SignaturesSetWeb signaturesSet,
                                Map<String, Object> parameters) throws Exception {

        addSignaturesSet(signaturesSet);
        return relativePluginRequestPath + "/" + INICI_FIRMA;
    }

    @Override
    public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath,
                           String query, SignaturesSetWeb signaturesSet, int signatureIndex,
                           HttpServletRequest request, HttpServletResponse response, Locale locale) {

        commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
                signaturesSet, signatureIndex, request, response, locale, true);
    }

    @Override
    public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath,
                            String query, SignaturesSetWeb signaturesSet, int signatureIndex,
                            HttpServletRequest request, HttpServletResponse response, Locale locale) {
        commonRequestGETPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
                signaturesSet, signatureIndex, request, response, locale, false);
    }

    private void commonRequestGETPOST(String absolutePluginRequestPath,
                                      String relativePluginRequestPath, String query, SignaturesSetWeb signaturesSet,
                                      int signatureIndex, HttpServletRequest request, HttpServletResponse response,
                                      Locale locale, boolean isGet) {

        if (query.startsWith(INICI_FIRMA)) {
            iniciFirma(absolutePluginRequestPath, relativePluginRequestPath, request,
                    response, signaturesSet, locale);
        } else if (query.startsWith(EXECUCIO_FIRMA)) {
            execucioFirma(absolutePluginRequestPath, relativePluginRequestPath, request,
                    response, signaturesSet, locale);
        } else {
            if (isGet) {
                super.requestGET(absolutePluginRequestPath, relativePluginRequestPath, query,
                        signaturesSet, signatureIndex, request, response, locale);
            } else {
                super.requestPOST(absolutePluginRequestPath, relativePluginRequestPath, query,
                        signaturesSet, signatureIndex, request, response, locale);
            }
        }
    }

    private static final String INICI_FIRMA = "inicifirma";

    private void iniciFirma(String absolutePluginRequestPath,
                                 String relativePluginRequestPath, HttpServletRequest request,
                                 HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {
        try {
            if (isDebug()) {
                log.info("iniciFirma");
            }

            AccessToken accessToken = getApi().getAccessToken("", absolutePluginRequestPath,
                    GRANT_TYPE_CLIENT_CREDENTIALS);

            int pos = relativePluginRequestPath.lastIndexOf("-1");
            String baseSignaturesSet = relativePluginRequestPath.substring(0, pos - 1);
            String callbackhost = getHostAndContextPath(absolutePluginRequestPath, relativePluginRequestPath);
            String baseTimeStampURL = callbackhost + baseSignaturesSet;

            SignatureAdapter adapter = new SignatureAdapter(signaturesSet);
            List<SignatureConfiguration> configurationList = adapter.getSignatureConfigurationList(baseTimeStampURL);

            SignatureRequest signatureRequest = new SignatureRequest();
            signatureRequest.setSignatureConfigurations(configurationList);
            signatureRequest.setUserCode(signaturesSet.getCommonInfoSignature().getAdministrationID());

            SignatureRequestResponse signatureRequestResponse =
                    getApi().signatureRequest(accessToken.getAccessToken(), signatureRequest);
            executionCodes.put(signaturesSet.getSignaturesSetID(), signatureRequestResponse.getExeCode());
            signaturesSet.getStatusSignaturesSet().setStatus(StatusSignaturesSet.STATUS_IN_PROGRESS);

            String callbackUrl = absolutePluginRequestPath + "/" + EXECUCIO_FIRMA;
            String authCode = signatureRequestResponse.getAuthCode();
            if (isDebug()) {
                log.info("authcode: " + authCode);
                log.info("callbackUrl: " + callbackUrl);
            }
            String signatureAuthorizationUri = getApi().getSignatureAuthorizationUri(authCode, callbackUrl);

            sendRedirect(response, signatureAuthorizationUri);

        } catch (Exception e) {
            String msg = "Error preparant inici de firma ViaFirma-Fortress: " + e.getMessage();
            finishWithError(response, signaturesSet, msg, e);
        }
    }

    private static final String EXECUCIO_FIRMA = "execuciofirma";

    private void execucioFirma(String absolutePluginRequestPath,
                                 String relativePluginRequestPath, HttpServletRequest request,
                                 HttpServletResponse response, SignaturesSetWeb signaturesSet, Locale locale) {

        try {
            if (isDebug()) {
                log.info("execucioFirma");
            }

            checkErrors(request);

            String exeCode = executionCodes.get(signaturesSet.getSignaturesSetID());
            if (exeCode == null) {
                throw new Exception("No s'ha trobat exeCode");
            }

            AccessToken accessToken = getApi().getAccessToken("", absolutePluginRequestPath,
                    GRANT_TYPE_CLIENT_CREDENTIALS);

            List<SignatureResponse> signatureResponses =
                    getApi().executeSignature(accessToken.getAccessToken(), exeCode);

            SignatureAdapter adapter = new SignatureAdapter(signaturesSet);
            adapter.updateSignatureStatus(signatureResponses);

            sendRedirect(response, signaturesSet.getUrlFinal());

        } catch (CancelledException e) {
            cancel(request, response, signaturesSet);
        } catch (Exception e) {
            String msg = "Error executant firma ViaFirma-Fortress: " + e.getMessage();
            finishWithError(response, signaturesSet, msg, e);
        }
    }

    protected void checkErrors(HttpServletRequest request) throws Exception {
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            if (error.equals("access_denied")) {
                throw new CancelledException();
            } else {
                throw new Exception(error);
            }
        }
    }

    @Override
    public String getResourceBundleName() {
        return "fortress";
    }

    @Override
    protected String getSimpleName() {
        return "FortressPlugin";
    }

    @Override
    public int getActiveTransactions() throws Exception {
        return internalGetActiveTransactions();
    }

    @Override
    public void resetAndClean(HttpServletRequest request) throws Exception {
        internalResetAndClean(request);
    }

    @Override
    public List<String> getSupportedBarCodeTypes() {
        // Aquests Plugins No suporten estampació de CSV per si mateixos
        return null;
    }

    /**
     * @return true, indica que el plugin internament ofereix un generador de imatges de
     * la Firma Visible PDF.
     */
    @Override
    public boolean providesRubricGenerator() {
        return false;
    }


    @Override
    public boolean acceptExternalSecureVerificationCodeStamper() {
        return false;
    }

    @Override
    public boolean providesSecureVerificationCodeStamper() {
        return false;
    }


    @Override
    public String[] getSupportedSignatureTypes() {
        return new String[]{
                FileInfoSignature.SIGN_TYPE_PADES,
                FileInfoSignature.SIGN_TYPE_CADES,
                FileInfoSignature.SIGN_TYPE_XADES
        };
    }

    /**
     * @return true, indica que el plugin internament ofereix un generador de segellat de temps.
     */
    @Override
    public boolean providesTimeStampGenerator(String signType) {
        return false;
    }

    @Override
    protected boolean isSuportXadesT() {
        return true;
    }

    @Override
    public String[] getSupportedSignatureAlgorithms(String signType) {
        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)
                || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)
                || FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
            return new String[]{FileInfoSignature.SIGN_ALGORITHM_SHA1,
                    FileInfoSignature.SIGN_ALGORITHM_SHA256,
                    FileInfoSignature.SIGN_ALGORITHM_SHA384,
                    FileInfoSignature.SIGN_ALGORITHM_SHA512};
        }
        return null;
    }

    @Override
    public boolean acceptExternalTimeStampGenerator(String signType) {
        return true;
    }

    @Override
    public boolean acceptExternalRubricGenerator() {
        return false;
    }
}
