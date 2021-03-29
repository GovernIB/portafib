package es.caib.portafib.logic;

import es.caib.portafib.ejb.PerfilDeFirmaLocal;
import es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal;
import es.caib.portafib.jpa.PerfilDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.portafib.model.entity.PerfilDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ConfiguracioUsuariAplicacioLogicaTest {

    private ConfiguracioUsuariAplicacioLogicaEJB configuracioUsuariAplicacioLogica;

    @Mock
    private PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacio;

    @Mock
    private PerfilDeFirmaLocal perfilDeFirma;

    @Before
    public void setUp() {
        configuracioUsuariAplicacioLogica = Mockito.spy(new ConfiguracioUsuariAplicacioLogicaEJB());
        configuracioUsuariAplicacioLogica.perfilsPerUsuariAplicacioEjb = perfilsPerUsuariAplicacio;
        configuracioUsuariAplicacioLogica.perfilDeFirmaEjb = perfilDeFirma;
    }

    @Test
    public void testGetConfiguracioUsuariAplicacioPerPassarela() throws I18NException {
        PerfilDeFirma perfil = new PerfilDeFirmaJPA();
        perfil.setNom("perfil test");
        perfil.setConfiguracioDeFirma1ID(23L);

        UsuariAplicacioConfiguracioJPA configuracio = new UsuariAplicacioConfiguracioJPA();
        configuracio.setUsuariAplicacioConfigID(23L);
        configuracio.setUsEnFirmaPassarelaWeb(true);

        Mockito.when(
                perfilsPerUsuariAplicacio.executeQuery(
                        Mockito.any(StringField.class),
                        Mockito.any(Where.class)))
                .thenReturn(Collections.singletonList("perfil"));
        Mockito.when(
                perfilDeFirma.select(Mockito.any(Where.class)))
                .thenReturn(Collections.singletonList(perfil));
        Mockito.when(
                configuracioUsuariAplicacioLogica.findByPrimaryKey(Mockito.<Long>any()))
                .thenReturn(null);
        Mockito.when(
                configuracioUsuariAplicacioLogica.findByPrimaryKey(Mockito.eq(Long.valueOf(23L))))
                .thenReturn(configuracio);

        PassarelaFileInfoSignature fileInfoSignature = new PassarelaFileInfoSignature();
        fileInfoSignature.setSignID("signId");
        PassarelaFileInfoSignature[] fileInfoSignatures = {fileInfoSignature};
        PassarelaSignaturesSet signaturesSet = new PassarelaSignaturesSet(
                "id", new Date(), new PassarelaCommonInfoSignature(), fileInfoSignatures);

        PerfilConfiguracionsDeFirma perfilConfiguracio = configuracioUsuariAplicacioLogica
                .getConfiguracioUsuariAplicacioPerPassarela("app", signaturesSet, false);

        Assert.assertEquals("perfil test", perfilConfiguracio.perfilDeFirma.getNom());
        Assert.assertEquals(23L, perfilConfiguracio.configBySignID.get("signId").getUsuariAplicacioConfigID());
    }

}
