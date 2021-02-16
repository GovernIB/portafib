package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_RUNNING;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_SIGNED;

public class DestinatariSignTest {

    private static ApiFirmaAsyncSimple api;
    private static Destinatari[] destinataris;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("destinatarisign.properties");
        properties.load(reader);
        reader.close();

        String firmesPendentsUrl = properties.getProperty("firmesPendents.url");
        String[] destinatarisKeys = properties.getProperty("destinataris").split(",");

        destinataris = new Destinatari[destinatarisKeys.length];
        for (int i = 0; i < destinataris.length; i++) {
            String destintariPrefix = "destinatari." + destinatarisKeys[i];
            String administrationId = properties.getProperty(destintariPrefix + ".administrationId");
            String username = properties.getProperty(destintariPrefix + ".username");
            String password = properties.getProperty(destintariPrefix + ".password");
            String pin = properties.getProperty(destintariPrefix + ".pin");
            destinataris[i] = new Destinatari(administrationId, username, password, pin, firmesPendentsUrl);
        }

        api = new ApiFirmaAsyncSimpleJersey(
                properties.getProperty("api.endpoint"),
                properties.getProperty("api.username"),
                properties.getProperty("api.password"));
    }

    @Test
    public void createAndSignPeticio()  {
        Destinatari dest = destinataris[0];

        int firmes = dest.firmesPendents();
        long peticio = crearPeticioDestinataris(dest);

        // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
        Assert.assertEquals(firmes + 1, dest.firmesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        dest.firmarDarreraPeticio();

        // després de signar, hi torna haver el mateix nombre de firmes pendents i l'estat de la petició és signed.
        Assert.assertEquals(firmes, dest.firmesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    @Test
    public void createAndSign2Destinataris() {
        Destinatari destA = destinataris[0];
        Destinatari destB = destinataris[1];

        int firmesA = destA.firmesPendents();
        int firmesB = destB.firmesPendents();

        long peticio = crearPeticioDestinataris(destA, destB);

        // Les firmes pendents del primer destintari s'ha incrementat, la del segon, no.
        Assert.assertEquals(firmesA + 1, destA.firmesPendents());
        Assert.assertEquals(firmesB, destB.firmesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destA.firmarDarreraPeticio();

        // Les firmes pendents del primer destintari torna a ser el mateix, la del segon s'ha increementat
        Assert.assertEquals(firmesA, destA.firmesPendents());
        Assert.assertEquals(firmesB + 1, destB.firmesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destB.firmarDarreraPeticio();

        // Les firmes pendents del primer destintari i segon, tornen ser les mateixes
        Assert.assertEquals(firmesA, destA.firmesPendents());
        Assert.assertEquals(firmesB, destB.firmesPendents());

        // i l'estat de la peticó és signat
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    private long crearPeticioDestinataris(Destinatari... destinataris) {
        try {
            String[] adminId = new String[destinataris.length];
            for (int i = 0; i < adminId.length; i++) {
                adminId[i] = destinataris[i].getAdministrationId();
            }
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithBlockList("hola.pdf", "application/pdf", adminId));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    private long statusPeticio(long peticio) {
        try {
            FirmaAsyncSimpleSignatureRequestState state =
                    api.getSignatureRequestState(new FirmaAsyncSimpleSignatureRequestInfo(peticio, "ca"));
            return state.getState();
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithBlockList(String fileName, String mime,
                                                                                      String... destintaris)
            throws Exception{
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSignatureBlock(destintaris));
    }

    private FirmaAsyncSimpleSignatureRequestBase getRequestBase(String fileName, String mime) throws Exception {
        return new FirmaAsyncSimpleSignatureRequestBase(null, "Petció test selenium",
                "Descripció test selenium", "Això és el reason", getSimpleFile(fileName, mime),
                null, 8L, "Publicació", "ca", "ca",
                FirmaAsyncSimpleSignatureRequestWithSignBlockList.PRIORITY_NORMAL_NORMAL, "sender name",
                "sender description",null, null, null, null,
                null, "Additional information", null);
    }

    private FirmaAsyncSimpleFile getSimpleFile(String fileName, String mime) throws Exception {
        byte[] bytes = FileUtils.readFromFile(new File("./testfiles/" + fileName));
        return new FirmaAsyncSimpleFile(fileName, mime, bytes);
    }

    private FirmaAsyncSimpleSignatureBlock[] getSignatureBlock(String... destintaris) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[destintaris.length];

        for (int i = 0; i < destintaris.length; i++) {
            blocks[i] = new FirmaAsyncSimpleSignatureBlock(1,
                    Collections.singletonList(getSimpleSignature(destintaris[i])));
        }

        return blocks;
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(String destintari) {
        return new FirmaAsyncSimpleSignature(
                getSimpleSigner(destintari), true, null, 0, null);
    }

    private FirmaAsyncSimpleSigner getSimpleSigner(String destinatari) {
        FirmaAsyncSimpleSigner signer = new FirmaAsyncSimpleSigner();
        signer.setAdministrationID(destinatari);
        return signer;
    }
}
