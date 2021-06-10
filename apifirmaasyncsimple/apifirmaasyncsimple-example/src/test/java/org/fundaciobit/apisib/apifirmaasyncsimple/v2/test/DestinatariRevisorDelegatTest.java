package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Colaborador;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Delegat;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.DestinatariUsuari;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Inbox;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors.Revisor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.mail.Session;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_REJECTED;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_RUNNING;
import static org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestState.SIGNATURE_REQUEST_STATE_SIGNED;

public class DestinatariRevisorDelegatTest extends ApiFirmaAsyncTestBase {

    private static DestinatariUsuari destinatariA;
    private static DestinatariUsuari destinatariB;
    private static DestinatariUsuari destinatariC;
    private static Revisor revisorA;
    private static Revisor revisorB;
    private static Revisor revisorC;
    private static Delegat delegatA;
    private static Colaborador colaboradorA;

    private static Session session;

    private static String flowTemplate;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("destinatarisign.properties");
        properties.load(reader);
        reader.close();

        String baseUrl = properties.getProperty("usuari.baseUrl");

        destinatariA = getDestinatari("destinatari.A", properties, baseUrl);
        destinatariB = getDestinatari("destinatari.B", properties, baseUrl);
        destinatariC = getDestinatari("destinatari.C", properties, baseUrl);
        revisorA = getRevisor("revisor.A", properties, baseUrl);
        revisorB = getRevisor("revisor.B", properties, baseUrl);
        revisorC = getRevisor("revisor.C", properties, baseUrl);
        delegatA = getDelegat("delegat.A", properties, baseUrl);
        colaboradorA = getColaborador("colaborador.A", properties, baseUrl);

        flowTemplate = properties.getProperty("flowTemplate");

        session = Session.getInstance(properties);

        initApi(properties);
    }

    private static DestinatariUsuari getDestinatari(String prefix, Properties properties, String url) {
        String administrationId = properties.getProperty(prefix + ".administrationId");
        String username = properties.getProperty(prefix + ".username");
        String password = properties.getProperty(prefix + ".password");
        String pin = properties.getProperty(prefix + ".pin");
        return new DestinatariUsuari(administrationId, username, password, pin, url);
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
    public void testCreateAndDelete() {

        Inbox destInbox = new Inbox(session, "pruebas@fundaciobit.org", "x");
        int mails = destInbox.getMessageCount(500);

        int firmes = destinatariA.tasquesPendents();
        long peticio = crearPeticioDestinataris(destinatariA);
        try {
            Assert.assertEquals(firmes + 1, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));
            Assert.assertEquals(mails + 1, destInbox.getMessageCount(500));
        } finally {
            deletePeticio(peticio);
            Assert.assertEquals(firmes, destinatariA.tasquesPendents());
        }
    }

    @Test
    public void testCreateAndDelete2()  {

        Inbox destInbox = new Inbox(session, "pruebas@fundaciobit.org", "x");
        Inbox reviInbox = new Inbox(session, "revi1@fundaciobit.org", "x");

        int revisions = revisorA.tasquesPendents();
        int mailsRevisor = reviInbox.getMessageCount(500);
        int mailsDestinatari = destInbox.getMessageCount(500);

        long peticio = crearPeticioDestinariRevisor(destinatariA, revisorA);
        try {
            Assert.assertEquals(revisions + 1, revisorA.tasquesPendents());
            Assert.assertEquals(mailsRevisor + 1, reviInbox.getMessageCount(500));
            Assert.assertEquals(mailsDestinatari, destInbox.getMessageCount(500));
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
            Assert.assertEquals(revisions, revisorA.tasquesPendents());
        }
    }

    @Test
    public void testCreateWithFlow() {

        int firmes = destinatariA.tasquesPendents();

        long peticio = crearPeticioWithFlow(flowTemplate);
        try {

            Assert.assertEquals(firmes + 1, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            destinatariA.firmarDarreraPeticio();

            // després de signar, hi torna haver el mateix nombre de firmes pendents i l'estat de la petició és signed.
            Assert.assertEquals(firmes, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateAndSignA()  {

        int firmes = destinatariA.tasquesPendents();
        long peticio = crearPeticioDestinataris(destinatariA);

        try {
            // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
            Assert.assertEquals(firmes + 1, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            destinatariA.firmarDarreraPeticio();

            // després de signar, hi torna haver el mateix nombre de firmes pendents i l'estat de la petició és signed.
            Assert.assertEquals(firmes, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateAndSignB()  {

        int firmes = destinatariB.tasquesPendents();
        long peticio = crearPeticioDestinataris(destinatariB);

        try {
            // el nombre de firmes pendents s'ha incrementat en un i l'estat de la petició és running....
            Assert.assertEquals(firmes + 1, destinatariB.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            destinatariB.firmarDarreraPeticio();

            // després de signar, hi torna haver el mateix nombre de firmes pendents i l'estat de la petició és signed.
            Assert.assertEquals(firmes, destinatariB.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateAndTwoSignatures() {
        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();

        long peticio = crearPeticioDestinataris(destinatariA, destinatariB);

        try {
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
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithRevisorAcceptar() {

        Inbox destInbox = new Inbox(session, "pruebas@fundaciobit.org", "x");
        Inbox reviInbox = new Inbox(session, "revi1@fundaciobit.org", "x");

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        int mailsRevisor = reviInbox.getMessageCount(500);
        int mailsDestinatari = destInbox.getMessageCount(500);

        long peticio = crearPeticioDestinariRevisor(destinatariA, revisorA);

        try {
            Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(mailsRevisor+1, reviInbox.getMessageCount(500));

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
            Assert.assertEquals(mailsDestinatari, destInbox.getMessageCount(500));

            Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            revisorA.acceptarDarrera();

            Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
            Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());
            Assert.assertEquals(mailsDestinatari+1, destInbox.getMessageCount(500));

            Assert.assertEquals(delegacionsPendents+1, delegatA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
            Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithRevisorRebutjar() {

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisor(destinatariA, revisorA);

        try {
            Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
            Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            revisorA.rebutjarDarrera("test");

            Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
            Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_REJECTED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateDestintariRevisorDestinatariRevisor() {

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();

        long peticio = crearPeticioDestinariRevisorDestintariRevisor(destinatariB, revisorA, destinatariA, revisorA);

        try {
            Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(firmesA, destinatariA.tasquesPendents());
            Assert.assertEquals(firmesB, destinatariB.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            revisorA.acceptarDarrera();
            Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
            Assert.assertEquals(firmesB+1, destinatariB.tasquesPendents());

            destinatariB.firmarDarreraPeticio();

            Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(firmesB, destinatariB.tasquesPendents());

            revisorA.acceptarDarrera();
            Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
            Assert.assertEquals(firmesA+1, destinatariA.tasquesPendents());

            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(firmesA, destinatariA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateDestintariRevisorDestinatariRevisorParallel() {

        int revisionsPendents = revisorA.tasquesPendents();
        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();

        long peticio = crearPeticioDestinariRevisorDestintariRevisorParallel(
                destinatariB, revisorA,
                destinatariA, null);

        try {
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertEquals(revisionsPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(firmesA+1, destinatariA.tasquesPendents());
            Assert.assertEquals(firmesB, destinatariB.tasquesPendents());

            destinatariA.firmarDarreraPeticio();
            Assert.assertEquals(firmesA, destinatariA.tasquesPendents());

            revisorA.acceptarDarrera();

            Assert.assertEquals(revisionsPendents, revisorA.tasquesPendents());
            Assert.assertEquals(firmesB+1, destinatariB.tasquesPendents());

            destinatariB.firmarDarreraPeticio();

            Assert.assertEquals(firmesB, destinatariB.tasquesPendents());

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateAndSignDelegat() {

        Inbox destInbox = new Inbox(session, "pruebas@fundaciobit.org", "x");
        Inbox deleInbox = new Inbox(session, "persona@fundaciobit.org", "x");

        int firmesPendents = destinatariA.tasquesPendents();
        int delegacionsPendents = delegatA.tasquesPendents();

        int mailsDestinatari = destInbox.getMessageCount(300);
        int mailsDelegat = deleInbox.getMessageCount(300);

        long peticio = crearPeticioDestinataris(destinatariA);
        try {
            Assert.assertEquals(firmesPendents + 1, destinatariA.tasquesPendents());
            Assert.assertEquals(delegacionsPendents + 1, delegatA.tasquesPendents());

            Assert.assertEquals(++mailsDelegat, deleInbox.getMessageCount(300));
            Assert.assertEquals(++mailsDestinatari, destInbox.getMessageCount(300));

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            delegatA.firmarDarreraPeticio();

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());
            Assert.assertEquals(delegacionsPendents, delegatA.tasquesPendents());

            // mail de descartat + mail de firma parcial
            Assert.assertEquals(mailsDestinatari + 2, destInbox.getMessageCount(300));

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }

    }

    @Test
    public void testCreateAndValidateColaborador() {

        int firmesPendents = destinatariA.tasquesPendents();
        int colaboracionsPendents = colaboradorA.tasquesPendents();

        long peticio = crearPeticioDestinataris(destinatariA);
        try {

            Assert.assertEquals(firmesPendents + 1, destinatariA.tasquesPendents());
            Assert.assertEquals(colaboracionsPendents + 1, colaboradorA.tasquesPendents());
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertTrue(destinatariA.colaboradorPendentDarrera());
            colaboradorA.validarDarrera();
            Assert.assertEquals(colaboracionsPendents, colaboradorA.tasquesPendents());
            Assert.assertTrue(destinatariA.colaboradorValidatDarrera());

            destinatariA.firmarDarreraPeticio();
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateThreeOptionalSignersMinimThree() {

        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();
        int firmesC = destinatariC.tasquesPendents();

        long peticio = crearPeticioDestinatarisParallel(3, 0, destinatariA, destinatariB, destinatariC);
        try {
            Assert.assertEquals(firmesA + 1, destinatariA.tasquesPendents());
            destinatariA.firmarDarreraPeticio();
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertEquals(firmesB + 1, destinatariB.tasquesPendents());
            destinatariB.firmarDarreraPeticio();
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertEquals(firmesC + 1, destinatariC.tasquesPendents());
            destinatariC.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));

        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateThreeOptionalSignersMinimTwo() {

        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();
        int firmesC = destinatariC.tasquesPendents();

        long peticio = crearPeticioDestinatarisParallel(2, 0, destinatariA, destinatariB, destinatariC);
        try {
            Assert.assertEquals(firmesA + 1, destinatariA.tasquesPendents());
            destinatariA.firmarDarreraPeticio();
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            Assert.assertEquals(firmesB + 1, destinatariB.tasquesPendents());
            destinatariB.firmarDarreraPeticio();

            // Ja està siganda i el darrer ja no té la firma pendent
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
            Assert.assertEquals(firmesC, destinatariC.tasquesPendents());

        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateOnRequiredTwoOptionalSignersMinimTwo() {

        int firmesA = destinatariA.tasquesPendents();
        int firmesB = destinatariB.tasquesPendents();
        int firmesC = destinatariC.tasquesPendents();

        long peticio = crearPeticioDestinatarisParallel(2, 1, destinatariA, destinatariB, destinatariC);
        try {
            Assert.assertEquals(firmesB + 1, destinatariB.tasquesPendents());
            destinatariB.firmarDarreraPeticio();
            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            // el C ja no la té pendent, perquè és opcional i ja ha signat l'altre opcional
            Assert.assertEquals(firmesC, destinatariC.tasquesPendents());

            // Falta l'A per signar-la que és obligat
            Assert.assertEquals(firmesA + 1, destinatariA.tasquesPendents());
            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));

        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithTwoRequiredRevisors() {

        int revisorAPendents = revisorA.tasquesPendents();
        int revisorBPendents = revisorB.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisors(destinatariA, 2, 2, revisorA, revisorB);

        try {
            Assert.assertEquals(revisorAPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents+1, revisorB.tasquesPendents());

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorA.acceptarDarrera();

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorB.acceptarDarrera();

            Assert.assertEquals(revisorAPendents, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents, revisorB.tasquesPendents());
            Assert.assertEquals(firmesPendents + 1, destinatariA.tasquesPendents());

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_RUNNING, statusPeticio(peticio));

            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithTwoRequiredRevisorsRebutjaPrimer() {

        int revisorAPendents = revisorA.tasquesPendents();
        int revisorBPendents = revisorB.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisors(destinatariA, 2, 2, revisorA, revisorB);

        try {
            Assert.assertEquals(revisorAPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents+1, revisorB.tasquesPendents());
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorA.rebutjarDarrera("test");

            Assert.assertEquals(revisorBPendents, revisorB.tasquesPendents());
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_REJECTED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithTwoRequiredRevisorsRebutjaSegon() {

        int revisorAPendents = revisorA.tasquesPendents();
        int revisorBPendents = revisorB.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisors(destinatariA, 2, 2, revisorA, revisorB);

        try {
            Assert.assertEquals(revisorAPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents+1, revisorB.tasquesPendents());
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorA.acceptarDarrera();
            revisorB.rebutjarDarrera("test");

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_REJECTED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithTwoOptionalRevisors() {

        int revisorAPendents = revisorA.tasquesPendents();
        int revisorBPendents = revisorB.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisors(destinatariA, 1, 0, revisorA, revisorB);

        try {
            Assert.assertEquals(revisorAPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents+1, revisorB.tasquesPendents());

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorA.acceptarDarrera();

            // El segon revisor no fa falta, el destintari ja pot signar
            Assert.assertEquals(revisorBPendents, revisorB.tasquesPendents());
            Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());

            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

    @Test
    public void testCreateWithTwoOptionalOneRequiredRevisors() {

        int revisorAPendents = revisorA.tasquesPendents();
        int revisorBPendents = revisorB.tasquesPendents();
        int revisorCPendents = revisorC.tasquesPendents();
        int firmesPendents = destinatariA.tasquesPendents();

        long peticio = crearPeticioDestinariRevisors(destinatariA, 2, 1, revisorA, revisorB, revisorC);

        try {
            Assert.assertEquals(revisorAPendents+1, revisorA.tasquesPendents());
            Assert.assertEquals(revisorBPendents+1, revisorB.tasquesPendents());
            Assert.assertEquals(revisorCPendents+1, revisorC.tasquesPendents());

            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorB.acceptarDarrera();

            // El segon revisor no fa falta
            Assert.assertEquals(revisorCPendents, revisorC.tasquesPendents());

            // Però encara no podem signar perquè falta el revisor obligatori
            Assert.assertEquals(firmesPendents, destinatariA.tasquesPendents());

            revisorA.acceptarDarrera();

            Assert.assertEquals(firmesPendents+1, destinatariA.tasquesPendents());
            destinatariA.firmarDarreraPeticio();

            Assert.assertEquals(SIGNATURE_REQUEST_STATE_SIGNED, statusPeticio(peticio));
        } finally {
            deletePeticio(peticio);
        }
    }

}