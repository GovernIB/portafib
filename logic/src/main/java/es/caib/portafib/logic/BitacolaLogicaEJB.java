package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaEJB;
import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.jpa.validator.BitacolaBeanValidator;
import es.caib.portafib.logic.jaxb.JAXBUtil;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.sql.Timestamp;

/**
 *
 * @author areus
 */
@Stateless(name = "BitacolaLogicaEJB")
@SecurityDomain("seycon")
public class BitacolaLogicaEJB extends BitacolaEJB implements BitacolaLogicaLocal {

  @Resource
  SessionContext context;

  @Override
  public BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte, int tipusoperacio,
                                    String descripcio, Object objecte) {
    try {
      Timestamp data = new Timestamp(System.currentTimeMillis());
      String usuariid = context.getCallerPrincipal().getName();
      if (context.getCallerPrincipal().getClass().getSimpleName().equals("RunAsIdentity")) {
        usuariid = "[system]";
      }
      String objecteSerialitzat = null;
      if (objecte != null) {
        objecteSerialitzat = JAXBUtil.marshal(objecte);
      }
      if (descripcio != null && descripcio.length() > 255) {
        log.warn("Camp DESCRIPCIO de bitàcola massa llarg. Missatge original: \n" + descripcio);
        descripcio = descripcio.substring(0, 255);
      }

      // Asseguram que es validen els camps per evitar errors de base de dades.
      BitacolaBeanValidator beanValidator = new BitacolaBeanValidator(this);

      BitacolaJPA bitacola = new BitacolaJPA(entitatid, usuariid, data, tipusobjecte, objecteid, tipusoperacio, descripcio, objecteSerialitzat);
      beanValidator.throwValidationExceptionIfErrors(bitacola, true);

      return (BitacolaJPA) this.create(bitacola);

    } catch (Throwable th) {
      String bitacolaString = "[" +
              "Entitat: " + entitatid +
              "ObjecteID: " + objecteid +
              "TipusObjecte: " + tipusobjecte +
              "TipusOperacio: " + tipusoperacio +
              "Descripcio: " + descripcio +
              "]";
      log.error("Error creant bitàcola: " + bitacolaString, th);
      return null;
    }
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte, int tipusoperacio) {
    return createBitacola(entitatid, objecteid, tipusobjecte, tipusoperacio, null, null);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte, int tipusoperacio, String descripcio) {
    return createBitacola(entitatid, objecteid, tipusobjecte, tipusoperacio, descripcio, null);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte, int tipusoperacio, Object objecte) {
    return createBitacola(entitatid, objecteid, tipusobjecte, tipusoperacio, null, objecte);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte, int tipusoperacio) {
    return createBitacola(entitatid, String.valueOf(objecteid), tipusobjecte, tipusoperacio, null, null);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte, int tipusoperacio, String descripcio) {
    return createBitacola(entitatid, String.valueOf(objecteid), tipusobjecte, tipusoperacio, descripcio, null);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte, int tipusoperacio, Object objecte) {
    return createBitacola(entitatid, String.valueOf(objecteid), tipusobjecte, tipusoperacio, null, objecte);
  }

  @Override
  public BitacolaJPA createBitacola(String entitatid, long objecteid, int tipusobjecte, int tipusoperacio,
                                    String descripcio, Object objecte) {
    return createBitacola(entitatid, String.valueOf(objecteid), tipusobjecte, tipusoperacio, descripcio, objecte);
  }
}
