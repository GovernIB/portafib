package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaEJB;
import es.caib.portafib.persistence.BitacolaJPA;
import es.caib.portafib.persistence.validator.BitacolaBeanValidator;
import es.caib.portafib.logic.bitacola.InfoBitacola;
import es.caib.portafib.logic.jaxb.JAXBUtil;


import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.security.Principal;
import java.sql.Timestamp;

/**
 *
 * @author areus
 */
@Stateless(name = "BitacolaLogicaEJB")
public class BitacolaLogicaEJB extends BitacolaEJB implements BitacolaLogicaLocal {

  @Resource
  SessionContext context;

  @Override
  public BitacolaJPA createBitacola(InfoBitacola info) {
    return doCreateBitacola(info);
  }

  /**
   * Versió de la creació de bitàcola que crea una nova transacció en cas que calgui
   */
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public BitacolaJPA createBitacolaFailsafe(InfoBitacola info) {
    return doCreateBitacola(info);
  }

  private BitacolaJPA doCreateBitacola(InfoBitacola info) {
    Principal principal = info.getPrincipal() == null ? context.getCallerPrincipal() : info.getPrincipal();
    try {
      Timestamp data = new Timestamp(System.currentTimeMillis());

      String usuariid = principal.getName();
      if (principal.getClass().getSimpleName().equals("RunAsIdentity")) {
        usuariid = "[system]";
      }

      String objecteSerialitzat = null;
      if (info.getObjecte() != null) {
        objecteSerialitzat = JAXBUtil.marshal(info.getObjecte());
      }

      String descripcio = info.getDescripcio();
      if (descripcio != null && descripcio.length() > 255) {
        log.warn("Camp DESCRIPCIO de bitàcola massa llarg. Missatge original: \n" + descripcio);
        descripcio = descripcio.substring(0, 255);
      }

      // Asseguram que es validen els camps per evitar errors de base de dades.
      BitacolaBeanValidator beanValidator = new BitacolaBeanValidator(this);

      BitacolaJPA bitacola = new BitacolaJPA(info.getEntitatid(), usuariid, data,
              info.getTipusObjecte(), info.getObjecteid(),
              info.getTipusOperacio(), descripcio, objecteSerialitzat);
      beanValidator.throwValidationExceptionIfErrors(bitacola, true);

      return (BitacolaJPA) this.create(bitacola);

    } catch (Throwable th) {
      String bitacolaString = "[" +
              "Usuari: " + principal.getName() +
              ", Entitat: " + info.getEntitatid() +
              ", ObjecteID: " + info.getObjecteid() +
              ", TipusObjecte: " + info.getTipusObjecte() +
              ", TipusOperacio: " + info.getTipusOperacio() +
              ", Descripcio: " + info.getDescripcio() +
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

  @Override
  public BitacolaJPA createBitacola(String entitatid, String objecteid, int tipusobjecte, int tipusoperacio,
                                    String descripcio, Object objecte) {
    InfoBitacola info = InfoBitacola.builder(entitatid)
            .objecteid(objecteid)
            .tipusObjecte(tipusobjecte)
            .tipusOperacio(tipusoperacio)
            .descripcio(descripcio)
            .objecte(objecte).build();
    return createBitacola(info);
  }

}
