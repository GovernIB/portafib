package es.caib.portafib.ws.callback.api.v1.test;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.callback.api.v1.Actor;
import es.caib.portafib.ws.callback.api.v1.PortaFIBCallBackWs;
import es.caib.portafib.ws.callback.api.v1.PortaFIBEvent;
import es.caib.portafib.ws.callback.api.v1.Sign;
import es.caib.portafib.ws.callback.api.v1.SigningRequest;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBCallBackTest extends PortaFIBTestUtils {
  
 
  
  protected static PortaFIBCallBackWs callBackApi;
  
  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    callBackApi = getCallBackApi();
  }
  
  @Test
  public void testVersio() throws Exception {
    Assert.assertEquals(callBackApi.getVersionWs(), 1);
  }

  
  @Test
  public void testSendEvent() throws Exception {
    
    PortaFIBEvent event = new PortaFIBEvent();
    
    Actor actor = new Actor();
    actor.setAdministrationID("12345678Z");
    actor.setName("Usuari Test");
    actor.setID("entitytest_usertest");
    
    event.setActor(actor);
    
    
    event.setApplicationID("entitytest_userapp");
    
    event.setEntityID("entitytest");

    event.setEventDate(new Timestamp(new Date().getTime()));

    event.setEventTypeID((int)Constants.NOTIFICACIOAVIS_PETICIO_EN_PROCES);
    
    Sign sign = new Sign();
    
    sign.setID(12345L);
    sign.setIssuer("Camerfirma Certificate");
    sign.setSubject("Subject Certificate");
    sign.setSerialNumber(new BigInteger("123456432109876"));
    
    event.setSign(sign);

    SigningRequest signingRequest = new SigningRequest();
    
    signingRequest.setID(1234L);
    signingRequest.setTitle("Titol peticio");
    signingRequest.setState(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES);
    signingRequest.setAdditionalInformation("additiona info WS");
    signingRequest.setRejectionReason("Rebutjar per ...");
    signingRequest.setCustodyURL("http://vd.caib.es/holacaracola/12345");

    event.setSigningRequest(signingRequest);
   
    event.setVersion(1);
    
    callBackApi.event(event);

  }
  
  
  public static void main(String[] args) {
    try {
      PortaFIBCallBackTest test = new PortaFIBCallBackTest();
      
      test.setUpBeforeClass();
      
      System.out.println(" Versio: " + callBackApi.getVersionWs());
      
      test.testSendEvent();
      
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
  }
  

}
