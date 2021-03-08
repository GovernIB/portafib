package org.fundaciobit.pluginsib.signatureweb.fortress.test;


import com.viafirma.fortress.sdk.FortressApi;
import com.viafirma.fortress.sdk.configuration.FortressApiConfiguration;
import com.viafirma.fortress.sdk.model.AccessToken;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.viafirma.fortress.sdk.FortressApi.GRANT_TYPE_CLIENT_CREDENTIALS;

public class TestFortress {

    private static final Logger log = Logger.getLogger(TestFortress.class);

    public static final String ENDPOINT = "https://pre.firmacloud.com/fortress/";
    private static final String CLIENT_ID = "goib";
    private static final String CLIENT_SECRET = "b53dg34ffgac49f0939c9fd28bba2250";

    private static FortressApi api;

    @BeforeClass
    public static void setup() {
        FortressApiConfiguration conf =
                new FortressApiConfiguration(ENDPOINT, CLIENT_ID, CLIENT_SECRET);
        conf.setDebug(true);
        api = new FortressApi(conf);
    }

    @Test
    public void testAuth() throws Exception {

        AccessToken accessToken = api.getAccessToken("", "http://localhost:8080/portafib",
                GRANT_TYPE_CLIENT_CREDENTIALS);

        log.info(accessToken.getAccessToken());
        log.info(accessToken.getExpiresIn());
    }
}
