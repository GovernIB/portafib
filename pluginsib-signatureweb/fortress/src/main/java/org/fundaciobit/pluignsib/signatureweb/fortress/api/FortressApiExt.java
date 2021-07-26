package org.fundaciobit.pluignsib.signatureweb.fortress.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viafirma.fortress.sdk.FortressApi;
import com.viafirma.fortress.sdk.configuration.FortressApiConfiguration;
import com.viafirma.fortress.sdk.exception.ApiException;
import com.viafirma.fortress.sdk.exception.CertificateAlreadyExistsException;
import com.viafirma.fortress.sdk.exception.CertificateNotFoundException;
import com.viafirma.fortress.sdk.exception.CertificateValidationException;
import com.viafirma.fortress.sdk.exception.DocumentNotFoundException;
import com.viafirma.fortress.sdk.exception.ExpiredCertificateException;
import com.viafirma.fortress.sdk.exception.GroupNotFoundException;
import com.viafirma.fortress.sdk.exception.InvalidAliasException;
import com.viafirma.fortress.sdk.exception.InvalidCertificatePasswordException;
import com.viafirma.fortress.sdk.exception.InvalidClientException;
import com.viafirma.fortress.sdk.exception.InvalidGrantException;
import com.viafirma.fortress.sdk.exception.InvalidKeystoreException;
import com.viafirma.fortress.sdk.exception.InvalidLicenseException;
import com.viafirma.fortress.sdk.exception.InvalidRequestException;
import com.viafirma.fortress.sdk.exception.InvalidResponseException;
import com.viafirma.fortress.sdk.exception.InvalidTokenException;
import com.viafirma.fortress.sdk.exception.InvalidUserException;
import com.viafirma.fortress.sdk.exception.LockedCertificateException;
import com.viafirma.fortress.sdk.exception.NetworkException;
import com.viafirma.fortress.sdk.exception.NotAuthorizedSignatureException;
import com.viafirma.fortress.sdk.exception.NotTrustedCertificateException;
import com.viafirma.fortress.sdk.exception.RedirectUriMismatchException;
import com.viafirma.fortress.sdk.exception.RevokedCertificateException;
import com.viafirma.fortress.sdk.exception.SecurityException;
import com.viafirma.fortress.sdk.exception.SendEmailException;
import com.viafirma.fortress.sdk.exception.ServerException;
import com.viafirma.fortress.sdk.exception.SignatureException;
import com.viafirma.fortress.sdk.exception.UserNotEnrolledException;
import com.viafirma.fortress.sdk.exception.UserNotFoundException;
import com.viafirma.fortress.sdk.model.AccessToken;
import com.viafirma.fortress.sdk.model.ApiError;
import com.viafirma.fortress.sdk.model.Certificate;
import com.viafirma.fortress.sdk.model.User;
import com.viafirma.fortress.sdk.model.UserStatus;
import com.viafirma.fortress.sdk.model.signature.Document;
import com.viafirma.fortress.sdk.model.signature.ExtendSignatureRequest;
import com.viafirma.fortress.sdk.model.signature.ExtendSignatureResponse;
import com.viafirma.fortress.sdk.model.signature.SignatureRequest;
import com.viafirma.fortress.sdk.model.signature.SignatureRequestResponse;
import com.viafirma.fortress.sdk.model.signature.SignatureRequestStatus;
import com.viafirma.fortress.sdk.model.signature.SignatureResponse;
import com.viafirma.fortress.sdk.service.FortressApiService;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Extensió del API per customitzar la llista d'algoritmes de xifratge suportats.
 * Veure. https://github.com/GovernIB/portafib/issues/554#issuecomment-885588000
 * En passar a JDK 11, es pot llevar tot això.
 */
public class FortressApiExt extends FortressApi {

    private final FortressApiService fortressApiService;
    private final Converter<ResponseBody, ApiError> errorConverter;

    private static final ObjectMapper OBJECT_MAPPER;

    private static final ConnectionSpec COMPATIBLE_SPEC;

    static {
        List<CipherSuite> cipherSuites = new ArrayList<CipherSuite>(ConnectionSpec.MODERN_TLS.cipherSuites());
        cipherSuites.add(CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        cipherSuites.add(CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256);

        COMPATIBLE_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .cipherSuites(cipherSuites.toArray(new CipherSuite[0]))
                .build();
    }

    public FortressApiExt(FortressApiConfiguration fortressApiConfiguration) {
        super(fortressApiConfiguration);

        OkHttpClient.Builder builder = (new OkHttpClient.Builder())
                .connectTimeout(fortressApiConfiguration.getConnectionTimeout(), TimeUnit.SECONDS)
                .readTimeout(fortressApiConfiguration.getReadTimeout(), TimeUnit.SECONDS)
                .connectionSpecs(Util.immutableList(COMPATIBLE_SPEC));

        if (fortressApiConfiguration.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        String baseUrl = fortressApiConfiguration.getUrl();
        Retrofit retrofit = (new retrofit2.Retrofit.Builder()).addConverterFactory(JacksonConverterFactory.create(OBJECT_MAPPER)).baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).client(builder.build()).build();
        this.fortressApiService = retrofit.create(FortressApiService.class);
        this.errorConverter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
    }

    public AccessToken getAccessToken(String code, String redirectUri, String grantType, boolean pem) throws ApiException {
        return this.executeCall(this.fortressApiService.getAccessToken(code, getFortressApiConfiguration().getClientId(), getFortressApiConfiguration().getClientSecret(), redirectUri, grantType, pem));
    }

    public List<Certificate> getClientCertificates(String accessToken, boolean pem) throws ApiException {
        return this.executeCall(this.fortressApiService.getClientCertificates("Bearer " + accessToken, getFortressApiConfiguration().getClientId(), pem));
    }

    public Certificate getClientCertificate(String accessToken, String certificateCode, boolean pem) throws ApiException {
        return this.executeCall(this.fortressApiService.getClientCertificate("Bearer " + accessToken, getFortressApiConfiguration().getClientId(), certificateCode, pem));
    }

    public UserStatus getUserStatus(String accessToken, String userCode) throws ApiException {
        return this.executeCall(this.fortressApiService.getUserStatus("Bearer " + accessToken, userCode));
    }

    public User getUser(String accessToken, String userCode) throws ApiException {
        return this.executeCall(this.fortressApiService.getUser("Bearer " + accessToken, userCode));
    }

    public List<Certificate> getCertificates(String accessToken, String userCode, boolean pem) throws ApiException {
        return this.executeCall(this.fortressApiService.getCertificates("Bearer " + accessToken, userCode, pem));
    }

    public Certificate getCertificate(String accessToken, String userCode, String certificateCode, boolean pem) throws ApiException {
        return this.executeCall(this.fortressApiService.getCertificate("Bearer " + accessToken, userCode, certificateCode, pem));
    }

    public SignatureRequestResponse signatureRequest(String accessToken, SignatureRequest signatureRequest) throws ApiException {
        return this.executeCall(this.fortressApiService.requestSignature("Bearer " + accessToken, signatureRequest));
    }

    public SignatureRequestStatus getSignatureRequestStatus(String accessToken, String authCode) throws ApiException {
        return this.executeCall(this.fortressApiService.getSignatureRequestStatus("Bearer " + accessToken, authCode));
    }

    public List<SignatureResponse> executeSignature(String accessToken, String executionCode) throws ApiException {
        return this.executeCall(this.fortressApiService.executeSignature("Bearer " + accessToken, executionCode));
    }

    public void removeAuthCache(String accessToken, String userCode) throws ApiException {
        this.executeCall(this.fortressApiService.removeAuthCache("Bearer " + accessToken, userCode));
    }

    public Document getDocument(String accessToken, String signatureCode) throws ApiException {
        return this.executeCall(this.fortressApiService.getDocument("Bearer " + accessToken, signatureCode));
    }

    public List<ExtendSignatureResponse> extendSignatureRequest(String accessToken, ExtendSignatureRequest extendSignatureRequest) throws ApiException {
        return this.executeCall(this.fortressApiService.requestExtendSignature("Bearer " + accessToken, extendSignatureRequest));
    }

    private <T> T executeCall(Call<T> call) throws ApiException {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw this.getFortressApiException(response.errorBody());
            }
        } catch (IOException var3) {
            throw new NetworkException(var3.getMessage(), var3);
        }
    }

    private ApiException getFortressApiException(ResponseBody errorBody) {
        try {
            ApiError apiError = errorConverter.convert(errorBody);
            String error = apiError.getError();
            String description = apiError.getErrorDescription();
            byte var6 = -1;
            switch(error.hashCode()) {
                case -2054838772:
                    if (error.equals("server_error")) {
                        var6 = 19;
                    }
                    break;
                case -1301301379:
                    if (error.equals("expired_certificate")) {
                        var6 = 8;
                    }
                    break;
                case -1250728338:
                    if (error.equals("certificate_not_found")) {
                        var6 = 7;
                    }
                    break;
                case -1112350814:
                    if (error.equals("user_not_found")) {
                        var6 = 5;
                    }
                    break;
                case -853518840:
                    if (error.equals("invalid_alias")) {
                        var6 = 11;
                    }
                    break;
                case -847806252:
                    if (error.equals("invalid_grant")) {
                        var6 = 2;
                    }
                    break;
                case -835880527:
                    if (error.equals("invalid_token")) {
                        var6 = 4;
                    }
                    break;
                case -693757610:
                    if (error.equals("group_not_found")) {
                        var6 = 6;
                    }
                    break;
                case -632018157:
                    if (error.equals("invalid_client")) {
                        var6 = 1;
                    }
                    break;
                case -617237321:
                    if (error.equals("network_error")) {
                        var6 = 20;
                    }
                    break;
                case -441138235:
                    if (error.equals("user_not_enroled")) {
                        var6 = 21;
                    }
                    break;
                case -371325696:
                    if (error.equals("not_authorized_signature")) {
                        var6 = 22;
                    }
                    break;
                case -368231351:
                    if (error.equals("security_error")) {
                        var6 = 17;
                    }
                    break;
                case -288922687:
                    if (error.equals("signature_error")) {
                        var6 = 16;
                    }
                    break;
                case 415668683:
                    if (error.equals("certificate_already_exists")) {
                        var6 = 13;
                    }
                    break;
                case 442796771:
                    if (error.equals("not_trusted_certificate")) {
                        var6 = 9;
                    }
                    break;
                case 527258899:
                    if (error.equals("invalid_user")) {
                        var6 = 23;
                    }
                    break;
                case 569794802:
                    if (error.equals("document_not_found")) {
                        var6 = 15;
                    }
                    break;
                case 1003187908:
                    if (error.equals("redirect_uri_mismatch")) {
                        var6 = 3;
                    }
                    break;
                case 1168729462:
                    if (error.equals("revoked_certificate")) {
                        var6 = 10;
                    }
                    break;
                case 1188443641:
                    if (error.equals("invalid_license")) {
                        var6 = 18;
                    }
                    break;
                case 1212912610:
                    if (error.equals("locked_certificate")) {
                        var6 = 25;
                    }
                    break;
                case 1296844833:
                    if (error.equals("certificate_validation")) {
                        var6 = 12;
                    }
                    break;
                case 1598717294:
                    if (error.equals("send_email_error")) {
                        var6 = 26;
                    }
                    break;
                case 1619352971:
                    if (error.equals("invalid_certificate_password")) {
                        var6 = 24;
                    }
                    break;
                case 2117379143:
                    if (error.equals("invalid_request")) {
                        var6 = 0;
                    }
                    break;
                case 2127102762:
                    if (error.equals("invalid_keystore")) {
                        var6 = 14;
                    }
            }

            switch(var6) {
                case 0:
                    return new InvalidRequestException(description);
                case 1:
                    return new InvalidClientException(description);
                case 2:
                    return new InvalidGrantException(description);
                case 3:
                    return new RedirectUriMismatchException(description);
                case 4:
                    return new InvalidTokenException(description);
                case 5:
                    return new UserNotFoundException(description);
                case 6:
                    return new GroupNotFoundException(description);
                case 7:
                    return new CertificateNotFoundException(description);
                case 8:
                    return new ExpiredCertificateException(description);
                case 9:
                    return new NotTrustedCertificateException(description);
                case 10:
                    return new RevokedCertificateException(description);
                case 11:
                    return new InvalidAliasException(description);
                case 12:
                    return new CertificateValidationException(description);
                case 13:
                    return new CertificateAlreadyExistsException(description);
                case 14:
                    return new InvalidKeystoreException(description);
                case 15:
                    return new DocumentNotFoundException(description);
                case 16:
                    return new SignatureException(description);
                case 17:
                    return new SecurityException(description);
                case 18:
                    return new InvalidLicenseException(description);
                case 19:
                    return new ServerException(description);
                case 20:
                    return new NetworkException(description);
                case 21:
                    return new UserNotEnrolledException(description);
                case 22:
                    return new NotAuthorizedSignatureException(description);
                case 23:
                    return new InvalidUserException(description);
                case 24:
                    return new InvalidCertificatePasswordException(description);
                case 25:
                    return new LockedCertificateException(description);
                case 26:
                    return new SendEmailException(description);
                default:
                    return new ApiException(description);
            }
        } catch (IOException var7) {
            return new InvalidResponseException("Invalid response", var7);
        }
    }

    static {
        OBJECT_MAPPER = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
