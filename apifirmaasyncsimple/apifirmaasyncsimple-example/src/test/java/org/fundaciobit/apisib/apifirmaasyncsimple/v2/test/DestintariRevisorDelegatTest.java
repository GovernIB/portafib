package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Colaborador;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Delegat;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Destinatari;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Revisor;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_REJECTED;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_RUNNING;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_SIGNED;

public class DestintariRevisorDelegatTest {

    private static ApiFirmaAsyncSimple api;
    private static String apiProfile;
    private static Destinatari destinatariA;
    private static Destinatari destinatariB;
    private static Revisor revisorA;
    private static Delegat delegatA;
    private static Colaborador colaboradorA;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("destinatarisign.properties");
        properties.load(reader);
        reader.close();

        String baseUrl = properties.getProperty("usuari.baseUrl");

        destinatariA = getDestinatari("destinatari.A", properties, baseUrl);
        destinatariB = getDestinatari("destinatari.B", properties, baseUrl);
        revisorA = getRevisor("revisor.A", properties, baseUrl);
        delegatA = getDelegat("delegat.A", properties, baseUrl);
        colaboradorA = getColaborador("colaborador.A", properties, baseUrl);

        api = new ApiFirmaAsyncSimpleJersey(
                properties.getProperty("api.endpoint"),
                properties.getProperty("api.username"),
                properties.getProperty("api.password"));
        apiProfile = properties.getProperty("api.profile");
    }

    private static Destinatari getDestinatari(String prefix, Properties properties, String url) {
        String administrationId = properties.getProperty(prefix + ".administrationId");
        String username = properties.getProperty(prefix + ".username");
        String password = properties.getProperty(prefix + ".password");
        String pin = properties.getProperty(prefix + ".pin");
        return new Destinatari(administrationId, username, password, pin, url);
    }

    private static Delegat getDelegat(String prefix, Properties properties, String url) {
        String administrationId = properties.getProperty(prefix + ".administrationId");
        String username = properties.getProperty(prefix + ".username");
        String password = properties.getProperty(prefix + ".password");
        String pin = properties.getProperty(prefix + ".pin");
        return new Delegat(administrationId, username, password, pin, url);
    }

    private static Revisor getRevisor(String prefix, Properties properties, String url) {
        String administrationId = properties.getProperty(prefix + ".administrationId");
        String username = properties.getProperty(prefix + ".username");
        String password = properties.getProperty(prefix + ".password");
        return new Revisor(administrationId, username, password, url);
    }

    private static Colaborador getColaborador(String prefix, Properties properties, String url) {
        String administrationId = properties.getProperty(prefix + ".administrationId");
        String username = properties.getProperty(prefix + ".username");
        String password = properties.getProperty(prefix + ".password");
        return new Colaborador(administrationId, username, password, url);
    }

    @Test
    public void testCreateAndSign()  {

        int firmes = destinatariA.tasquesPendents();
        long peticio = crearPeticioDestinataris(destinatariA);

        // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
        Assert.assertEquals(firmes + 1, destinatariA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destinatariA.firmarDarreraPeticio();

        // després de signar, hi torna haver el mateix nombre de firmes pendents i l'estat de la petició és signed.
        Assert.assertEquals(firmes, destinatariA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    @Test
    public void testCreateAndTwoSignatures() {
        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();

        long peticio = crearPeticioDestinataris(destinatariA, destinatariB);

        // Les firmes pendents del primer destintari s'ha incrementat, la del segon, no.
        Assert.assertEquals(firmesA + 1, destinatariA.tasquesPendents());
        Assert.assertEquals(firmesB, destinatariB.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destinatariA.firmarDarreraPeticio();

        // Les firmes pendents del primer destintari torna a ser el mateix, la del segon s'ha increementat
        Assert.assertEquals(firmesA, destinatariA.tasquesPendents());
        Assert.assertEquals(firmesB + 1, destinatariB.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destinatariB.firmarDarreraPeticio();

        // Les firmes pendents del primer destintari i segon, tornen ser les mateixes
        Assert.assertEquals(firmesA, destinatariA.tasquesPendents());
        Assert.assertEquals(firmesB, destinatariB.tasquesPendents());

        // i l'estat de la peticó és signat
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    @Test
    public void testCreateWithRevisorAcceptar() {

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisor(destinatariA, revisorA);

        Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        revisorA.acceptarDarrera();

        Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
        Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents+1, delegatA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        destinatariA.firmarDarreraPeticio();

        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    @Test
    @Ignore
    // Rebujar no funciona amb versió disponible de HtmlUnit
    // https://github.com/SeleniumHQ/htmlunit-driver/issues/14
    public void testCreateWithRevisorRebutjar() {

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisor(destinatariA, revisorA);

        Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        revisorA.rebutjarDarrera("test");

        Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_REJECTED, statusPeticio(peticio));
    }

    @Test
    public void testCreateAndSignDelegat() {

        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        long peticio = crearPeticioDestinataris(destinatariA);

        Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents+1, delegatA.tasquesPendents());

        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        delegatA.firmarDarreraPeticio();

        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
        Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());

        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }

    @Test
    public void testCreateAndValidateColaborador() {

        int firmesPendents = destinatariA.tasquesPendents();
        int colaboracionsPendents = colaboradorA.tasquesPendents();

        long peticio = crearPeticioDestinataris(destinatariA);

        Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());
        Assert.assertEquals(colaboracionsPendents+1, colaboradorA.tasquesPendents());
        Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

        Assert.assertTrue(destinatariA.colaboradorPendentDarrera());
        colaboradorA.validarDarrera();
        Assert.assertEquals(colaboracionsPendents, colaboradorA.tasquesPendents());
        Assert.assertTrue(destinatariA.colaboradorValidatDarrera());

        destinatariA.firmarDarreraPeticio();
        Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

        Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
    }


    ///////////////////////////////////////////////////////////////////
    // Mètodes d'utilitat per la API

    private long crearPeticioDestinataris(Destinatari... destinataris) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithBlockList("hola.pdf", "application/pdf", destinataris));
        } catch (AbstractApisIBException e) {
            throw new RuntimeException("Error creant petició", e);
        } catch (Exception e) {
            throw new RuntimeException("Error creant petició", e);
        }
    }

    private long crearPeticioDestinariRevisor(Destinatari destinatari, Revisor revisor) {
        try {
            return api.createAndStartSignatureRequestWithSignBlockList(
                    getRequestWithBlockList("hola.pdf", "application/pdf", destinatari, revisor));
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

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithBlockList(
            String fileName, String mime, Destinatari... destinataris) throws Exception{
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSignatureBlock(destinataris));
    }

    private FirmaAsyncSimpleSignatureRequestWithSignBlockList getRequestWithBlockList(
            String fileName, String mime, Destinatari destinatari, Revisor revisor) throws Exception{
        return new FirmaAsyncSimpleSignatureRequestWithSignBlockList(
                getRequestBase(fileName, mime), getSignatureBlock(destinatari, revisor));
    }

    private FirmaAsyncSimpleSignatureRequestBase getRequestBase(String fileName, String mime) throws Exception {
        return new FirmaAsyncSimpleSignatureRequestBase(apiProfile, "Petció test selenium",
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

    private FirmaAsyncSimpleSignatureBlock[] getSignatureBlock(Destinatari... destinataris) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

        for (int i = 0; i < destinataris.length; i++) {
            blocks[i] = new FirmaAsyncSimpleSignatureBlock(1,
                    Collections.singletonList(getSimpleSignature(destinataris[i].getAdministrationId())));
        }

        return blocks;
    }

    private FirmaAsyncSimpleSignatureBlock[] getSignatureBlock(Destinatari destinatari, Revisor revisor) {
        FirmaAsyncSimpleSignatureBlock[] blocks = new FirmaAsyncSimpleSignatureBlock[1];

            blocks[0] = new FirmaAsyncSimpleSignatureBlock(1,
                    Collections.singletonList(getSimpleSignature(destinatari.getAdministrationId(), revisor.getAdministrationId())));
        return blocks;
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(String destinatari) {
        return new FirmaAsyncSimpleSignature(
                getSimpleSigner(destinatari), true, null, 0, null);
    }

    private FirmaAsyncSimpleSignature getSimpleSignature(String destinatari, String reviser) {
        FirmaAsyncSimpleSignature signature = getSimpleSignature(destinatari);
        if (reviser != null) {
            signature.setMinimumNumberOfRevisers(1);
            signature.setRevisers(Collections.singletonList(getSimpleReviser(reviser)));
        }
        return signature;
    }

    private FirmaAsyncSimpleSigner getSimpleSigner(String destinatari) {
        FirmaAsyncSimpleSigner signer = new FirmaAsyncSimpleSigner();
        signer.setAdministrationID(destinatari);
        return signer;
    }

    private FirmaAsyncSimpleReviser getSimpleReviser(String revisor) {
        FirmaAsyncSimpleReviser reviser = new FirmaAsyncSimpleReviser();
        reviser.setAdministrationID(revisor);
        reviser.setRequired(true);
        return reviser;
    }
}
