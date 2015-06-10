package es.caib.portafib.ws.v1.test;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.caib.portafib.ws.api.v1.CarrecWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.UsuariEntitatBean;
import es.caib.portafib.ws.api.v1.UsuariPersonaBean;
import es.caib.portafib.ws.api.v1.WsFieldValidationError;
import es.caib.portafib.ws.api.v1.WsI18NException;
import es.caib.portafib.ws.api.v1.WsValidationException;
import es.caib.portafib.ws.api.v1.utils.I18NUtils;
import es.caib.portafib.ws.api.v1.utils.WsClientUtils;

/**
 *
 * @author anadal
 *
 */
public final class PortaFIBUsuariEntitatTest extends PortaFIBTestUtils {
  
  public static final Logger log = Logger.getLogger(PortaFIBUsuariEntitatTest.class);

  protected static PortaFIBUsuariEntitatWs usuariEntitatAPI;

  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    usuariEntitatAPI = getUsuariEntitatApi();
  }

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Persona |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @Test
  public void testUsuariPersona() throws Exception {
    
    
    String nif = getTestPersonaNif();
    try {
      String ueID = usuariEntitatAPI.getUsuariEntitatIDInMyEntitatByAdministrationID(nif);
      
      UsuariEntitatBean ue = usuariEntitatAPI.getUsuariEntitat(ueID);
      
      usuariEntitatAPI.deleteUsuariEntitat(ueID);
      usuariEntitatAPI.deleteUsuariPersona(ue.getUsuariPersonaID());
      
    } catch(WsI18NException e) {
      System.out.println(e.getFaultInfo().getTranslation().getCode());
      e.printStackTrace();
    }
    
    

    UsuariPersonaBean usuariPersonaBean = new UsuariPersonaBean();

    // test 6 errors de validacio
    try {
      usuariEntitatAPI.createUsuariPersona(usuariPersonaBean);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(6, ve.getFaultInfo().getFieldFaults().size());
    }

    // test 5 errors de validacio
    
    usuariPersonaBean.setNif(nif);
    try {
      usuariEntitatAPI.createUsuariPersona(usuariPersonaBean);
      Assert.fail();
    } catch (WsValidationException ve) {
      log.info(WsClientUtils.toString(ve));
      Assert.assertEquals(5, ve.getFaultInfo().getFieldFaults().size());
    }

    // Obtenim informacio de Plugin de Info
    UsuariPersonaBean usb = usuariEntitatAPI.getInfoFromPluginUserInfoByAdministrationID(nif);
    String usuariPersonaID = usb.getUsuariPersonaID();
    if (usb.getEmail() == null) {
      usb.setEmail("any@portafib.org");
    }

    // test idioma no soportat
    String anticIdioma = usb.getIdiomaID();
    usb.setIdiomaID("it"); // No suportat
    try {
      usuariEntitatAPI.createUsuariPersona(usb);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(1, ve.getFaultInfo().getFieldFaults().size());
      WsFieldValidationError err = ve.getFaultInfo().getFieldFaults().get(0);
      Assert.assertEquals("idiomaID", err.getField());
      Assert.assertEquals("idioma.nosuportat", err.getTranslation().getCode());
      Assert.assertEquals("it", err.getTranslation().getArgs().get(0).getValue());
    } finally {
      usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
    }
    usb.setIdiomaID(anticIdioma);

    // test username no concorda amb nif
    String anticUsername = usb.getUsuariPersonaID();
    usb.setUsuariPersonaID("holacaracola");
    try {
      usuariEntitatAPI.createUsuariPersona(usb);
      Assert.fail();
    } catch (WsValidationException ve) {
      Assert.assertEquals(1, ve.getFaultInfo().getFieldFaults().size());
      WsFieldValidationError err = ve.getFaultInfo().getFieldFaults().get(0);
      Assert.assertEquals("usuariPersonaID", err.getField());
      Assert.assertEquals("usuaripersona.iddiferentdeplugin", err.getTranslation().getCode());
      Assert.assertEquals(usb.getUsuariPersonaID(), err.getTranslation().getArgs().get(0)
          .getValue());
      Assert.assertEquals(anticUsername, err.getTranslation().getArgs().get(1).getValue());
    }
    usb.setUsuariPersonaID(anticUsername);

    // crear OK i mètodes get per username i per nif
    try {
      usuariEntitatAPI.createUsuariPersona(usb);
      UsuariPersonaBean up = usuariEntitatAPI.getUsuariPersona(usuariPersonaID);
      Assert.assertEquals(nif, up.getNif());
      String id = usuariEntitatAPI.getUsuariPersonaIDByAdministrationID(nif);
      Assert.assertEquals(usuariPersonaID, id);
    } finally {
      usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
    }

  }

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ------------------------| Usuari Entitat |-------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @Test
  public void testUsuariEntitat() throws Exception {

    String usuariPersonaID = null;
    String entitatID = usuariEntitatAPI.getEntitatID();
    String nif = getTestPersonaNif();
    try {
      log.info(" PUNT 1 ");
      
      
      
      // Crear usuari persona
      usuariPersonaID = createUsuariPersona(usuariEntitatAPI, nif);
      
      log.info(" PUNT 12 ");

      // Crear usuari entitat buit
      UsuariEntitatBean ue = new UsuariEntitatBean();

      try {
        ue = usuariEntitatAPI.createUsuariEntitat(ue);
        Assert.fail();
      } catch (WsValidationException ve) {
        Assert.assertEquals(2, ve.getFaultInfo().getFieldFaults().size());
      }
      
      log.info(" PUNT 2");

      // Crear usuari entitat amb setUsuariEntitatID incorrecte
      ue.setEntitatID(entitatID);
      ue.setUsuariPersonaID("usuariPersonaNoValid");
      String usuariEntitatID = "incorrect_usuariEntitatID";
      ue.setUsuariEntitatID(usuariEntitatID);
      try {
        ue = usuariEntitatAPI.createUsuariEntitat(ue);
        Assert.fail();
      } catch (WsValidationException ve) {
        Assert.assertEquals(1, ve.getFaultInfo().getFieldFaults().size());
        WsFieldValidationError err = ve.getFaultInfo().getFieldFaults().get(0);
        Assert.assertEquals("usuariPersonaID", err.getField());
        Assert.assertEquals("error.notfound", err.getTranslation()
            .getCode());
      }
      
      ue.setUsuariPersonaID(usuariPersonaID);
      
      log.info(" PUNT 3");

      // Crear usuari entitat (Servidor omple ID)
      ue.setUsuariEntitatID(null);
      try {
        ue = usuariEntitatAPI.createUsuariEntitat(ue);
        usuariEntitatID = ue.getUsuariEntitatID();
        Assert.assertEquals(usuariEntitatID, entitatID + "_" + usuariPersonaID);
      } finally {
        log.info(" Borrant usuari Entitat 000 = " + usuariEntitatID);
        usuariEntitatAPI.deleteUsuariEntitat(usuariEntitatID);
      }

      // Crear usuari entitat (ID definit en el client)
      usuariEntitatID = entitatID + "_" + usuariPersonaID;
      ue.setUsuariEntitatID(usuariEntitatID);
      try {
        ue = usuariEntitatAPI.createUsuariEntitat(ue);
        usuariEntitatID = ue.getUsuariEntitatID();
        Assert.assertEquals(usuariEntitatID, entitatID + "_" + usuariPersonaID);
      } finally {
        log.info(" Borrant usuari Entitat 111 = " + usuariEntitatID);
        usuariEntitatAPI.deleteUsuariEntitat(usuariEntitatID);
      }

      // Crear usuari entitat de forma ràpida
      usuariEntitatID = entitatID + "_" + usuariPersonaID;
      ue.setUsuariEntitatID(usuariEntitatID);
      try {
        ue = usuariEntitatAPI.createUsuariEntitatSimple(nif, entitatID);
        usuariEntitatID = ue.getUsuariEntitatID();
        Assert.assertEquals(true, ue.isActiu());
        Assert.assertEquals(usuariEntitatID, entitatID + "_" + usuariPersonaID);

        // Llegir usuariEntitat
        ue = usuariEntitatAPI.getUsuariEntitat(usuariEntitatID);
        Assert.assertNotNull(ue);
        Assert.assertEquals(ue.getUsuariEntitatID(), usuariEntitatID);

        // Llegir usuariEntitat a partir de nif
        String id = usuariEntitatAPI.getUsuariEntitatIDByAdministrationID(nif, entitatID);
        Assert.assertEquals(usuariEntitatID, id);

        // Desactivar
        usuariEntitatAPI.deactivateUsuariEntitat(usuariEntitatID);
        ue = usuariEntitatAPI.getUsuariEntitat(usuariEntitatID);
        Assert.assertEquals(false, ue.isActiu());

        // Activar
        usuariEntitatAPI.activateUsuariEntitat(usuariEntitatID);
        ue = usuariEntitatAPI.getUsuariEntitat(usuariEntitatID);
        Assert.assertEquals(true, ue.isActiu());

      } finally {
        log.info(" Borrant usuari Entitat 222 = " + usuariEntitatID);
        usuariEntitatAPI.deleteUsuariEntitat(usuariEntitatID);
      }

    } finally {
      if (usuariPersonaID != null) {
        usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
      }
    }

  }

  public static String createUsuariPersona(PortaFIBUsuariEntitatWs usuariEntitatAPI2,
      String nif) throws WsI18NException, WsValidationException {
    String usuariPersonaID;
    // Crear usuari persona
    UsuariPersonaBean upb = usuariEntitatAPI2.getInfoFromPluginUserInfoByAdministrationID(nif);
    usuariPersonaID = upb.getUsuariPersonaID();
    if (upb.getEmail() == null) {
      upb.setEmail("any@portafib.org");
    }
    for(int x = 0; x < 2; x++) {
      try {
        usuariEntitatAPI2.createUsuariPersona(upb);
        break;
      } catch(WsValidationException WsValEx) {
        
        List<WsFieldValidationError> list = WsValEx.getFaultInfo().getFieldFaults();
        boolean reintentar = false;
        for (WsFieldValidationError err : list) {
          System.out.println(" XXXXX  " + err.getField() + "  " + err.getError() + " " + err.getTranslation().getCode());
          if ("nif".equals(err.getField()) && "genapp.validation.unique".equals(err.getTranslation().getCode())) {
            
            try {
              usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
            } catch(Exception e) {
              System.err.println(" Error borrant persona: " + usuariPersonaID);
            }
            
            reintentar = true;
          }
          
        }
        if (reintentar && x < 1) {
          continue;
        }
        throw WsValEx;
      }
      
      
      
    }

    return usuariPersonaID;
  }

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // ---------------------------| Carrecs |-----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @Test
  public void testCarrec() throws Exception {

    String usuariPersonaID = null;
    String usuariEntitatID = null;
    String entitatID = usuariEntitatAPI.getEntitatID();
    String nif = getTestPersonaNif();
    final String carrecID = "testcarrec";
    final String carrecName = "Test Carrec";

    String fullCarrecID = null;

    try {
      // Crear usuari persona
      usuariPersonaID = createUsuariPersona(usuariEntitatAPI, nif);
      // Crear usuari Entitat
      UsuariEntitatBean ue = usuariEntitatAPI.createUsuariEntitatSimple(nif, entitatID);
      usuariEntitatID = ue.getUsuariEntitatID();

      // Test de nif null
      try {
        usuariEntitatAPI.createCarrecSimple(null, entitatID, carrecID, carrecName);
        Assert.fail();
      } catch (WsI18NException wse) {
        Assert.assertEquals("usuaripersona.noexisteix", wse.getFaultInfo().getTranslation().getCode());
      }

      // Test de nif no existent
      try {
        usuariEntitatAPI
            .createCarrecSimple("nif_No_Existent", entitatID, carrecID, carrecName);
        Assert.fail();
      } catch (WsI18NException i18ne) {
        Assert.assertEquals("usuaripersona.noexisteix", i18ne.getFaultInfo().getTranslation()
            .getCode());
      }

      // test de crear un carrec
      try {
        CarrecWs carrec = usuariEntitatAPI.createCarrecSimple(nif, entitatID, carrecID,
            carrecName);
        fullCarrecID = entitatID + "_" + carrecID;
        Assert.assertEquals(fullCarrecID, carrec.getCarrecID());

        fullCarrecID = carrec.getCarrecID();

        // Obtenir un carrec
        carrec = usuariEntitatAPI.getCarrec(fullCarrecID);
        Assert.assertNotNull(carrec);
        Assert.assertEquals(fullCarrecID, carrec.getCarrecID());
        Assert.assertEquals(true, carrec.isActiu());

        // Obtenir tots els càrrec d'una entitat
        List<CarrecWs> listCarrecs = usuariEntitatAPI.getCarrecsByEntitatID(entitatID);
        Assert.assertNotNull(listCarrecs);
        boolean contains = false;
        for (CarrecWs carrecWs : listCarrecs) {
          if (fullCarrecID.equals(carrecWs.getCarrecID())) {
            contains = true;
            break;
          }
        }
        if (!contains) {
          Assert.fail("La llista de càrrecs no conté el càrrec que s'acba de crear.");
        }

        // Desactivar
        usuariEntitatAPI.deactivateCarrec(fullCarrecID);
        carrec = usuariEntitatAPI.getCarrec(fullCarrecID);
        Assert.assertNotNull(carrec);
        Assert.assertEquals(false, carrec.isActiu());

        // Activar
        usuariEntitatAPI.activateCarrec(fullCarrecID);
        carrec = usuariEntitatAPI.getCarrec(fullCarrecID);
        Assert.assertNotNull(carrec);
        Assert.assertEquals(true, carrec.isActiu());

      } finally {
        if (fullCarrecID != null) {
          usuariEntitatAPI.deleteCarrec(fullCarrecID);
        }
      }

    } finally {
      if (usuariPersonaID != null) {
        if (usuariEntitatID != null) {
          usuariEntitatAPI.deleteUsuariEntitat(usuariEntitatID);
        }
        usuariEntitatAPI.deleteUsuariPersona(usuariPersonaID);
      }

    }
  }

  public static void main(String[] args) {
    try {

      PortaFIBUsuariEntitatWs usuariEntitatApi = getUsuariEntitatApi();

      log.info(getTestPersonaNif());

      // usuaris.createUsuariEntitatSimple("administrationID", "entitatID");

      UsuariEntitatBean usuariEntitatBean = new UsuariEntitatBean();
      usuariEntitatBean = usuariEntitatApi.createUsuariEntitat(usuariEntitatBean);

      for (Locale idioma : new Locale[] { new Locale("ca"), new Locale("es") }) {
        log.info("Traducció[" + idioma.getLanguage() + "]: "
            + I18NUtils.tradueix(idioma, "genapp.listtitle", "XXXX"));
      }

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

}
