package es.caib.portafib.ws.v1.test;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.caib.portafib.ws.api.v1.PortaFIBUsuariAplicacioWs;
import es.caib.portafib.ws.api.v1.UsuariAplicacioBean;
import es.caib.portafib.ws.api.v1.UsuariAplicacioBeanArray;
import es.caib.portafib.ws.api.v1.UsuariAplicacioFilterWs;
import es.caib.portafib.ws.api.v1.WsFieldValidationError;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;

/**
 * 
 * @author anadal
 * 
 */
public final class PortaFIBUsuariAplicacioTest extends PortaFIBTestUtils {

  public static final Logger log = Logger.getLogger(PortaFIBUsuariAplicacioTest.class);
  
  protected static PortaFIBUsuariAplicacioWs usuariAplicacioAPI;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    usuariAplicacioAPI = getUsuariAplicacioApi();
  }

  /**
   * S'executa una vegada al final de l'execució de tots els tests d'aquesta
   * classe
   * 
   * @throws Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * S'executa abans de cada test
   * 
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * S'executa despres de per cada test
   * 
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testVersio() throws Exception {
    if (isCAIB()) {
      Assert.assertEquals("1.0.0-caib", usuariAplicacioAPI.getVersion());
    } else {
      Assert.assertEquals("1.0.0", usuariAplicacioAPI.getVersion());
    }
  }

  @Test
  public void testVersioWs() throws Exception {
    Assert.assertEquals(1, usuariAplicacioAPI.getVersionWs());
  }

  @Test
  public void addRolAdmin() throws Exception {
    String usr = getTestAppUserName();
    usuariAplicacioAPI.addRolAdminToUsuariAplicacio(usr);
  }

  @Test
  public void addRolUser() throws Exception {
    String usr = getTestAppUserName();
    usuariAplicacioAPI.addRolUserToUsuariAplicacio(usr);
  }

  @Test
  public void deleteUsuariAplicacio() throws Exception {
    // No llança cap error si l'usuari no existeix
    usuariAplicacioAPI.deleteUsuariAplicacio("usuariAplicacioID_inexistent");
  }

  @Test
  public void createUsuariAplicacio() throws Exception {
    UsuariAplicacioBean usuariAplicacioBean = new UsuariAplicacioBean();
    
    try {
      usuariAplicacioAPI.createUsuariAplicacio(usuariAplicacioBean);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(isCAIB() ? 5 : 6, ve.getFaultInfo().getFieldFaults().size());
    }

    String entitatID = usuariAplicacioAPI.getEntitatID();

    usuariAplicacioBean.setEntitatID(entitatID);
    usuariAplicacioBean.setIdiomaID("ca");
    usuariAplicacioBean.setCallbackURL("any");
    usuariAplicacioBean.setCallbackVersio(1);
    usuariAplicacioBean.setContrasenya("anypwd");
    usuariAplicacioBean.setEmailAdmin("any@mail.com");

    usuariAplicacioBean.setUsuariAplicacioID(getTestAppUserName());
    try {
      usuariAplicacioAPI.createUsuariAplicacio(usuariAplicacioBean);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(ve.getFaultInfo().getFieldFaults().size(), 1);

      WsFieldValidationError fve = ve.getFaultInfo().getFieldFaults().get(0);

      Assert.assertEquals("genapp.validation.unique", fve.getTranslation().getCode());
    }

    usuariAplicacioBean.setUsuariAplicacioID("1234567890");
    try {
      usuariAplicacioAPI.createUsuariAplicacio(usuariAplicacioBean);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(ve.getFaultInfo().getFieldFaults().size(), 1);

      WsFieldValidationError fve = ve.getFaultInfo().getFieldFaults().get(0);

      String expected = isCAIB()? "usuariaplicacio.usuarinoseycon" : "usuariaplicacio.errorprefix"; 
      
      Assert.assertEquals(expected, fve.getTranslation().getCode());

    }

    if (!isCAIB()) {
    
      String id = entitatID + "_1234567890";
      usuariAplicacioBean.setUsuariAplicacioID(id);
      
      try {
        usuariAplicacioAPI.createUsuariAplicacio(usuariAplicacioBean);
        UsuariAplicacioBean createdUser;
        createdUser = usuariAplicacioAPI.getUsuariAplicacio(usuariAplicacioBean.getUsuariAplicacioID());
        if (isCAIB()) {
          Assert.fail("En mode CAIB no es poden crear usuaris aplicació");
        }
  
        Assert.assertEquals(id, createdUser.getUsuariAplicacioID());
  
        Assert.assertEquals("", createdUser.getContrasenya());
      } catch(WsValidationException ve) {
        if (isCAIB()) {
          Assert.assertEquals(1, ve.getFaultInfo().getFieldFaults().size());
        } else {
          throw ve;
        }
        
      } finally {
        usuariAplicacioAPI.deleteUsuariAplicacio(id);
      }
    }

  }

  public static void main(String[] args) {
    try {

      PortaFIBUsuariAplicacioWs usuariAplicacioApi = getUsuariAplicacioApi();

      String userName = getTestAppUserName();

      System.out.println("L'usuari aplicació " + userName + " pertany a l'entitat amb ID="
          + usuariAplicacioApi.getEntitatID());

      UsuariAplicacioBean usuariAplicacioBean = new UsuariAplicacioBean();
      usuariAplicacioApi.createUsuariAplicacio(usuariAplicacioBean);

    } catch (Throwable th) {
      if (th instanceof WsValidationException) {
        System.err.println(" ========== ValidationFault_Exception  ==========");
        System.err.println(WsClientUtils.toString((WsValidationException) th));
      } else if (th instanceof WsI18NException) {
        System.err.println(" ========== I18NException  ==========");
        System.err.println(WsClientUtils.toString((WsI18NException) th));
      } else {
        System.err.println(" ========== Throwable " + th.getClass() + " ==========");
        th.printStackTrace(System.err);
      }

    }

  }
  
  
  @Test
  public void testList() throws Exception  {
    // Llistar usuaris aplicació
   
    UsuariAplicacioFilterWs filtre = new UsuariAplicacioFilterWs();
    
    UsuariAplicacioBeanArray listOriginalArray = usuariAplicacioAPI.listUsuariAplicacio(filtre);
    
    List<UsuariAplicacioBean> listOriginal = listOriginalArray.getItem();
    
    for (UsuariAplicacioBean ua : listOriginal) {
      String id = ua.getUsuariAplicacioID();
      System.out.println(id);
    }
    
    String username = getTestAppUserName();
    String usr2 = username.substring(1, username.length() - 1);
    System.out.println(usr2);
    filtre.setFilterByUsuariAplicacioID("%" + usr2 + "%");
    listOriginalArray = usuariAplicacioAPI.listUsuariAplicacio(filtre);
    
    listOriginal = listOriginalArray.getItem();
    
    List<String> ids = new ArrayList<String>();
    for (UsuariAplicacioBean ua : listOriginal) {
      String id = ua.getUsuariAplicacioID();
      ids.add(id);
    }
    
    if (!ids.contains(username)) {
      Assert.fail("La cerca per id ha fallat. Expected " + username + " dins " + ids.toString());
    }

  }

}
