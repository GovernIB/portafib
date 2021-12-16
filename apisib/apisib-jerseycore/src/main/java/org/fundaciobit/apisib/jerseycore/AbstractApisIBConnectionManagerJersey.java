package org.fundaciobit.apisib.jerseycore;

import org.codehaus.jackson.map.ObjectMapper;
import org.fundaciobit.apisib.core.beans.ApisIBError;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.apisib.core.exceptions.ApisIBTimeOutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.apache4.ApacheHttpClient4;
import com.sun.jersey.client.apache4.config.ApacheHttpClient4Config;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author anadal
 * @author areus
 */
public abstract class AbstractApisIBConnectionManagerJersey<E extends ApisIBError> {

    protected final String endPointBase;

    protected final String username;

    protected final String password;

    private ApacheHttpClient4 client4 = null;

    private final ObjectMapper mapper;

    private final static Logger log = LoggerFactory
            .getLogger(AbstractApisIBConnectionManagerJersey.class);

    public AbstractApisIBConnectionManagerJersey(String endPointBase) {
        this(endPointBase, null, null);
    }

    public AbstractApisIBConnectionManagerJersey(String endPointBase, String username,
            String password) {
        this.endPointBase = endPointBase.endsWith("/") ? endPointBase : (endPointBase + "/");
        this.username = username;
        this.password = password;
        this.mapper = new ObjectMapper();
    }

    /**
     * Emprar ignoreServerCertificates amb valor true és insegur i ja no està
     * suportat
     * 
     * @throws Exception
     */
    @Deprecated
    public AbstractApisIBConnectionManagerJersey(String endPointBase, String username,
            String password, boolean ignoreServerCertificates) {
        this(endPointBase, username, password);
        if (ignoreServerCertificates) {
            throw new UnsupportedOperationException(
                    "La propietat ignoreServerCertificate ja no està soportada per "
                            + "motius de seguretat.");
        }
    }

    private ApacheHttpClient4 getClient() /* throws Exception */ {

        if (this.client4 == null) {
            ClientConfig config = new DefaultApacheHttpClient4Config();
            config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            final String proxyHost = System.getProperty("http.proxyHost");
            if (proxyHost != null && !"".equals(proxyHost)) {
                if (!validateNonProxyHosts(endPointBase)) {
                    config.getProperties().put(ApacheHttpClient4Config.PROPERTY_PROXY_USERNAME,
                            System.getProperty("http.proxyUser"));
                    config.getProperties().put(ApacheHttpClient4Config.PROPERTY_PROXY_PASSWORD,
                            System.getProperty("http.proxyPassword"));
                    config.getProperties().put(DefaultApacheHttpClient4Config.PROPERTY_PROXY_URI,
                            "http://" + System.getProperty("http.proxyHost") + ":"
                                    + System.getProperty("http.proxyPort"));
                }
            }
            ApacheHttpClient4 client = ApacheHttpClient4.create(config);

            if (this.username != null) {
                client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
            }

            this.client4 = client;
        }
        return this.client4;
    }

    /**
     * Busca els host de la url indicada dentro de la propiedad http.nonProxyHosts
     * de la JVM
     *
     * @param url Endpoint del ws
     * @return true si el host esta dentro de la propiedad, fals en caso contrario
     */
    private static boolean validateNonProxyHosts(final String url) {
        final String nonProxyHosts = System.getProperty("http.nonProxyHosts");
        boolean existe = false;
        URL urlURL;
        try {
            if (nonProxyHosts != null && !"".equals(nonProxyHosts)) {
                urlURL = new URL(url);
                final String[] nonProxyHostsArray = nonProxyHosts.split("\\|");
                for (int i = 0; i < nonProxyHostsArray.length; i++) {
                    final String a = nonProxyHostsArray[i].replaceAll("\\.", "\\\\.")
                            .replaceAll("\\*", ".*");
                    if (urlURL.getHost().matches(a)) {
                        existe = true;
                        break;
                    }
                }
            }
        } catch (final MalformedURLException e) {
            log.error("Error al validar los nonProxyHost " + e.getCause(), e);
            // throw e;
        }
        return existe;
    }

    public Integer getReadTimeoutMs() {
        return (Integer) getClient().getProperties().get(ClientConfig.PROPERTY_READ_TIMEOUT);
    }

    public void setReadTimeoutMs(Integer readTimeoutMs) {
        getClient().setReadTimeout(readTimeoutMs);
    }

    public Integer getConnectionTimeoutMs() {
        return (Integer) getClient().getProperties().get(ClientConfig.PROPERTY_CONNECT_TIMEOUT);
    }

    public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
        getClient().setConnectTimeout(connectionTimeoutMs);
    }

    protected String cleanString(String result) {
        if (result != null) {
            result = result.trim();
            if (result.startsWith("\"")) {
                result = result.substring(1);
            }
            if (result.endsWith("\"")) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    protected ClientResponse commonCall(Object parameter, String method)
            throws AbstractApisIBException {

        ClientResponse response;
        try {
            String endPoint = endPointBase + (endPointBase.endsWith("/") ? "" : "/") + method;

            WebResource webResource = getClient().resource(endPoint);
            if (parameter == null) {
                response = webResource.type("application/json").post(ClientResponse.class);
            } else {
                String json = mapper.writeValueAsString(parameter);
                response = webResource.type("application/json").post(ClientResponse.class, json);
            }

        } catch (Exception e) {
            throw new ApisIBClientException(e.getMessage(), e);
        }

        if (response.getStatus() == 200) {
            return response;
        } else {
            // Miram si ho podem transformar a ApiSimpleError
            E simple = null;
            try {
                simple = response.getEntity(getErrorClass());
            } catch (Exception e) {
                // Error no controlat
                e.printStackTrace();
            }

            if (simple != null) {
                String tipus = simple.getType();
                if (tipus != null && tipus.trim().length() != 0) {
                    processException(simple, tipus);
                }
            }

            // Error de Comunicació o no controlat
            String raw_msg = response.getEntity(String.class);
            throw new ApisIBClientException("Error desconegut (Codi de servidor "
                    + +response.getStatus() + "): " + raw_msg);
        }
    }

    protected void processException(E simple, String tipus) throws AbstractApisIBException {
        if (tipus.equals(ApisIBTimeOutException.class.getName())) {
            throw new ApisIBTimeOutException(simple.getMessage(), simple.getStackTrace());
        } else if (tipus.equals(ApisIBServerException.class.getName())) {
            throw new ApisIBServerException(simple.getMessage(), simple.getStackTrace());
        } else {
            throw new ApisIBClientException(simple.getMessage(), simple.getStackTrace());
        }
    }

    protected abstract Class<E> getErrorClass();

}
