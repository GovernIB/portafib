package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.jaxb.JAXBUtil;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatusList;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.bean.PeticioDeFirmaBean;
import es.caib.portafib.model.bean.UsuariAplicacioConfiguracioBean;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import java.io.File;
import java.net.URL;


/**
 *
 * 
 */
public class TestJAXB {
  
  public static final Logger log = Logger.getLogger(TestJAXB.class);

  public static void main(String[] args) {
    new TestJAXB().testSerialitzarPeticioDeFirma();
  }

  @Test
  public void testSerialitzarPeticioDeFirma() {

    log.info("testSerialitzarPeticioDeFirma");

    URL resource = getClass().getResource("/annex_1.txt");
    if (resource == null) {
      throw new RuntimeException("Resource no trobat");
    }

    log.info(resource.toString());

    FitxerBean fitxer = new FitxerBean();
    fitxer.setData(new DataHandler(new URLDataSource(resource)));

    PeticioDeFirmaBean peticio = new PeticioDeFirmaBean();
    peticio.setFitxerAFirmar(fitxer);

    String serialitzat = JAXBUtil.marshal(peticio);

    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
          "<peticioDeFirmaBean>\n" +
          "    <algorismeDeFirmaID>0</algorismeDeFirmaID>\n" +
          "    <avisWeb>false</avisWeb>\n" +
          "    <fitxerAFirmar>\n" +
          "        <data></data>\n" +
          "        <fitxerID>0</fitxerID>\n" +
          "        <tamany>0</tamany>\n" +
          "    </fitxerAFirmar>\n" +
          "    <fluxDeFirmesID>0</fluxDeFirmesID>\n" +
          "    <origenPeticioDeFirma>0</origenPeticioDeFirma>\n" +
          "    <peticioDeFirmaID>0</peticioDeFirmaID>\n" +
          "    <posicioTaulaFirmesID>0</posicioTaulaFirmesID>\n" +
          "    <prioritatID>0</prioritatID>\n" +
          "    <segellatDeTemps>false</segellatDeTemps>\n" +
          "    <tipusDocumentID>0</tipusDocumentID>\n" +
          "    <tipusEstatPeticioDeFirmaID>0</tipusEstatPeticioDeFirmaID>\n" +
          "    <tipusFirmaID>0</tipusFirmaID>\n" +
          "    <tipusOperacioFirma>0</tipusOperacioFirma>\n" +
          "</peticioDeFirmaBean>\n", serialitzat);

    Assert.assertNotNull(peticio.getFitxerAFirmar().getData());

    log.info(serialitzat);
  }

  @Test
  public void testSerialitzarUsuariAplicacioConfiguracio() {

    log.info("testSerialitzarUsuariAplicacioConfiguracio");

    UsuariAplicacioConfiguracioBean config = new UsuariAplicacioConfiguracioBean();

    String serialitzat = JAXBUtil.marshal(config);

    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
          "<usuariAplicacioConfiguracioBean>\n" +
          "    <modeDeFirma>false</modeDeFirma>\n" +
          "    <politicaSegellatDeTemps>0</politicaSegellatDeTemps>\n" +
          "    <politicaTaulaFirmes>0</politicaTaulaFirmes>\n" +
          "    <posicioTaulaFirmesID>0</posicioTaulaFirmesID>\n" +
          "    <tipusFirmaID>0</tipusFirmaID>\n" +
          "    <tipusOperacioFirma>0</tipusOperacioFirma>\n" +
          "    <usEnFirmaApiSimpleServidor>false</usEnFirmaApiSimpleServidor>\n" +
          "    <usEnFirmaApiSimpleWeb>false</usEnFirmaApiSimpleWeb>\n" +
          "    <usEnFirmaAsyncRest2>false</usEnFirmaAsyncRest2>\n" +
          "    <usEnFirmaPassarelaServidor>false</usEnFirmaPassarelaServidor>\n" +
          "    <usEnFirmaPassarelaWeb>false</usEnFirmaPassarelaWeb>\n" +
          "    <usEnFirmaWS1>false</usEnFirmaWS1>\n" +
          "    <usEnFirmaWeb>false</usEnFirmaWeb>\n" +
          "    <usPoliticaDeFirma>0</usPoliticaDeFirma>\n" +
          "    <usuariAplicacioConfigID>0</usuariAplicacioConfigID>\n" +
          "</usuariAplicacioConfiguracioBean>\n", serialitzat);

    log.info(serialitzat);
  }

  @Test
  public void testSerialitzarSetPassarelaSignatureStatus() throws Exception {

    log.info("testSerialitzarSetPassarelaSignatureStatus");

    PassarelaSignatureStatusList statusList = new PassarelaSignatureStatusList();

    PassarelaSignatureStatusWebInternalUse psswiu1 = new PassarelaSignatureStatusWebInternalUse();
    psswiu1.setStatus(2);
    URL resource = getClass().getResource("/annex_1.txt");
    psswiu1.setFitxerFirmat(new File(resource.toURI()));

    PassarelaSignatureStatusWebInternalUse psswiu2 = new PassarelaSignatureStatusWebInternalUse();
    psswiu2.setStatus(-1);
    psswiu2.setErrorMessage("Error -1");

    PassarelaSignatureStatusWebInternalUse psswiu3 = new PassarelaSignatureStatusWebInternalUse();
    psswiu3.setStatus(-2);
    psswiu3.setErrorMessage("Error -2");

    statusList.getPassarelaSignatureStatus().add(psswiu1);
    statusList.getPassarelaSignatureStatus().add(psswiu2);
    statusList.getPassarelaSignatureStatus().add(psswiu3);


    String serialitzat = JAXBUtil.marshal(statusList);

    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
          "<passarelaSignatureStatusList>\n" +
          "    <passarelaSignatureStatus>\n" +
          "        <status>2</status>\n" +
          "    </passarelaSignatureStatus>\n" +
          "    <passarelaSignatureStatus>\n" +
          "        <errorMessage>Error -1</errorMessage>\n" +
          "        <status>-1</status>\n" +
          "    </passarelaSignatureStatus>\n" +
          "    <passarelaSignatureStatus>\n" +
          "        <errorMessage>Error -2</errorMessage>\n" +
          "        <status>-2</status>\n" +
          "    </passarelaSignatureStatus>\n" +
          "</passarelaSignatureStatusList>\n", serialitzat);

    Assert.assertNotNull(
          ((PassarelaSignatureStatusWebInternalUse) statusList.getPassarelaSignatureStatus().get(0)).getFitxerFirmat());

    log.info(serialitzat);
  }
}
