package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.DestinatariExtern;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.DestinatariUsuari;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.mail.Session;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_RUNNING;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_SIGNED;

public class DestinatariExternTest extends ApiFirmaAsyncTestBase {

    private static DestinatariExtern destExtern;
    private static DestinatariUsuari destIntern;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("destinatarisign.properties");
        properties.load(reader);
        reader.close();

        String baseUrl = properties.getProperty("usuari.baseUrl");

        Session session = Session.getInstance(properties);

        destExtern = new DestinatariExtern(
                "99999999R", "pruebas-extern@fundaciobit.org",
                "Pruebas", "Eidas Extern",
                "1234", session);

        destIntern = new DestinatariUsuari("99999990S",
                "ciudadano", "ciudadano", "741258", baseUrl);

        initApi(properties);
    }

    @Test
    public void testCreateAndSign()  {
        long peticio = crearPeticioDestinataris(destExtern);
        try {
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            String url = destExtern.pollLastMessageUrl(300);
            destExtern.signarUrl(url);

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateAndSignInternExtern()  {
        int peticionsIntern = destIntern.tasquesPendents();
        long peticio = crearPeticioDestinataris(destIntern, destExtern);
        try {
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertNull(destExtern.pollLastMessageUrl(300));

            Assert.assertEquals(peticionsIntern+1, destIntern.tasquesPendents());
            destIntern.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            String url = destExtern.pollLastMessageUrl(300);
            destExtern.signarUrl(url);

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateOptionalExternInternSignExtern()  {
        long peticio = crearPeticioDestinatarisParallel(1, 0, destIntern, destExtern);
        try {
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            String url = destExtern.pollLastMessageUrl(300);
            destExtern.signarUrl(url);

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateOptionalExternInternSignIntern()  {
        int peticionsIntern = destIntern.tasquesPendents();
        long peticio = crearPeticioDestinatarisParallel(1, 0, destIntern, destExtern);
        try {
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertEquals(peticionsIntern+1, destIntern.tasquesPendents());
            destIntern.firmarDarreraPeticio();

            String url = destExtern.pollLastMessageUrl(300);
            Assert.assertTrue(destExtern.comprovarFirmaJaProcessada(url));

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            //deletePeticio(peticio);
        }
    }
}
