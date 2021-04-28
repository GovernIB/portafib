package es.caib.portafib.logic;

import es.caib.portafib.jpa.PerfilDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ConfiguracioUsuariAplicacioLogicaTest {

    public static final String PERFIL_TEST = "perfil test";
    public static final long CONFIGURACIO_FIRMA_1 = 23L;
    public static final long CONFIGURACIO_FIRMA_2 = 24L;
    public static final String USUARI_APLICACIO_ID = "app";

    private ConfiguracioUsuariAplicacioLogicaEJB configuracioUsuariAplicacioLogica;

    @Before
    public void setUp() {
        configuracioUsuariAplicacioLogica = Mockito.spy(new ConfiguracioUsuariAplicacioLogicaEJB());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerPassarela() throws I18NException {
        PerfilDeFirma perfil = getPerfilSenzill();
        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma1();
        configuracio.setUsEnFirmaPassarelaWeb(true);

        Mockito.doReturn(perfil)
                .when(configuracioUsuariAplicacioLogica)
                .getPerfilDeFirmaPerPassarela(USUARI_APLICACIO_ID, false);
        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_1));

        PassarelaSignaturesSet signaturesSet = getPassarelaSignaturesSet();

        PerfilConfiguracionsDeFirma perfilConfiguracio = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerPassarela(USUARI_APLICACIO_ID, signaturesSet, false);

        Assert.assertEquals(PERFIL_TEST, perfilConfiguracio.perfilDeFirma.getNom());
        Assert.assertEquals(CONFIGURACIO_FIRMA_1, perfilConfiguracio.configBySignID.get("signId").getUsuariAplicacioConfigID());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerPassarelaServidor() throws I18NException {
        PerfilDeFirma perfil = getPerfilSenzill();
        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma1();
        configuracio.setUsEnFirmaPassarelaServidor(true);
        configuracio.setPluginFirmaServidorID(1L);

        Mockito.doReturn(perfil)
                .when(configuracioUsuariAplicacioLogica)
                .getPerfilDeFirmaPerPassarela(USUARI_APLICACIO_ID, true);
        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_1));

        PassarelaSignaturesSet signaturesSet = getPassarelaSignaturesSet();

        PerfilConfiguracionsDeFirma perfilConfiguracio = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerPassarela(USUARI_APLICACIO_ID, signaturesSet, true);

        Assert.assertEquals(PERFIL_TEST, perfilConfiguracio.perfilDeFirma.getNom());
        Assert.assertEquals(CONFIGURACIO_FIRMA_1, perfilConfiguracio.configBySignID.get("signId").getUsuariAplicacioConfigID());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerPassarelaExpressio() throws I18NException {
        PerfilDeFirma perfil = getPerfilExpressioUsFirma(ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB);
        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma2();
        configuracio.setUsEnFirmaPassarelaWeb(true);

        Mockito.doReturn(perfil)
                .when(configuracioUsuariAplicacioLogica)
                .getPerfilDeFirmaPerPassarela(USUARI_APLICACIO_ID, false);
        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_2));

        PassarelaSignaturesSet signaturesSet = getPassarelaSignaturesSet();

        PerfilConfiguracionsDeFirma perfilConfiguracio = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerPassarela(USUARI_APLICACIO_ID, signaturesSet, false);

        Assert.assertEquals(PERFIL_TEST, perfilConfiguracio.perfilDeFirma.getNom());
        Assert.assertEquals(CONFIGURACIO_FIRMA_2, perfilConfiguracio.configBySignID.get("signId").getUsuariAplicacioConfigID());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerPassarelaServidorExpressio() throws I18NException {
        PerfilDeFirma perfil = getPerfilExpressioUsFirma(ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR);
        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma2();
        configuracio.setUsEnFirmaPassarelaServidor(true);
        configuracio.setPluginFirmaServidorID(1L);

        Mockito.doReturn(perfil)
                .when(configuracioUsuariAplicacioLogica)
                .getPerfilDeFirmaPerPassarela(USUARI_APLICACIO_ID, true);
        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_2));

        PassarelaSignaturesSet signaturesSet = getPassarelaSignaturesSet();

        PerfilConfiguracionsDeFirma perfilConfiguracio = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerPassarela(USUARI_APLICACIO_ID, signaturesSet, true);

        Assert.assertEquals(PERFIL_TEST, perfilConfiguracio.perfilDeFirma.getNom());
        Assert.assertEquals(CONFIGURACIO_FIRMA_2, perfilConfiguracio.configBySignID.get("signId").getUsuariAplicacioConfigID());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerUpgrade() throws I18NException {
        PerfilDeFirma perfil = getPerfilSenzill();
        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma1();
        configuracio.setUsEnFirmaApiSimpleServidor(true);
        configuracio.setPluginFirmaServidorID(1L);
        configuracio.setUpgradeSignFormat(40);

        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_1));

        UsuariAplicacioConfiguracioJPA result = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerUpgrade(USUARI_APLICACIO_ID, perfil, getFirmaSimpleUpgradeRequest());

        Assert.assertEquals(CONFIGURACIO_FIRMA_1, result.getUsuariAplicacioConfigID());
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerUpgradeExpressio() throws I18NException {
        PerfilDeFirma perfil = getPerfilExpressioUsFirma(ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR);

        UsuariAplicacioConfiguracioJPA configuracio = getConfiguracioFirma2();
        configuracio.setUsEnFirmaApiSimpleServidor(true);
        configuracio.setPluginFirmaServidorID(1L);
        configuracio.setUpgradeSignFormat(40);

        Mockito.doReturn(configuracio)
                .when(configuracioUsuariAplicacioLogica).findByPrimaryKey(Long.valueOf(CONFIGURACIO_FIRMA_2));

        UsuariAplicacioConfiguracioJPA result = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerUpgrade(USUARI_APLICACIO_ID, perfil, getFirmaSimpleUpgradeRequest());

        Assert.assertEquals(CONFIGURACIO_FIRMA_2, result.getUsuariAplicacioConfigID());
    }

    private UsuariAplicacioConfiguracioJPA getConfiguracioFirma1() {
        UsuariAplicacioConfiguracioJPA configuracio = new UsuariAplicacioConfiguracioJPA();
        configuracio.setUsuariAplicacioConfigID(CONFIGURACIO_FIRMA_1);
        return configuracio;
    }

    private UsuariAplicacioConfiguracioJPA getConfiguracioFirma2() {
        UsuariAplicacioConfiguracioJPA configuracio = new UsuariAplicacioConfiguracioJPA();
        configuracio.setUsuariAplicacioConfigID(CONFIGURACIO_FIRMA_2);
        return configuracio;
    }

    private PerfilDeFirma getPerfilSenzill() {
        PerfilDeFirma perfil = new PerfilDeFirmaJPA();
        perfil.setNom(PERFIL_TEST);
        perfil.setConfiguracioDeFirma1ID(CONFIGURACIO_FIRMA_1);
        return perfil;
    }

    private PerfilDeFirma getPerfilExpressioUsFirma(int usFirma) {
        PerfilDeFirma perfil = new PerfilDeFirmaJPA();
        perfil.setNom("perfil test");
        perfil.setCondicio(
                "<#if usFirma == " + usFirma + " && tamanyFitxer == 100 && mimeFitxer == \"application/pdf\">\n" +
                "     2\n" +
                "<#else>\n" +
                "     1\n" +
                "</#if>");
        perfil.setConfiguracioDeFirma1ID(CONFIGURACIO_FIRMA_1);
        perfil.setConfiguracioDeFirma2ID(CONFIGURACIO_FIRMA_2);
        return perfil;
    }

    private PassarelaSignaturesSet getPassarelaSignaturesSet() {
        PassarelaFileInfoSignature fileInfoSignature = new PassarelaFileInfoSignature();
        fileInfoSignature.setSignID("signId");
        fileInfoSignature.setFileToSign(new FitxerBean("prova.pdf", null, 100, "application/pdf"));
        PassarelaFileInfoSignature[] fileInfoSignatures = {fileInfoSignature};
        return new PassarelaSignaturesSet(
                "id", new Date(), new PassarelaCommonInfoSignature(), fileInfoSignatures);
    }

    private FirmaSimpleUpgradeRequest getFirmaSimpleUpgradeRequest() {
        FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest = new FirmaSimpleUpgradeRequest();
        FirmaSimpleFile signature = new FirmaSimpleFile("prova.pdf", "application/pdf", new byte[100]);
        firmaSimpleUpgradeRequest.setSignature(signature);
        return firmaSimpleUpgradeRequest;
    }
}
