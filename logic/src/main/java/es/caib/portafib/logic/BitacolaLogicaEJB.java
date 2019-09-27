package es.caib.portafib.logic;

import es.caib.portafib.ejb.BitacolaEJB;
import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.logic.jaxb.JAXBUtil;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.sql.Timestamp;

/**
 * 
 * @author areus
 *
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
      return (BitacolaJPA) this.create(entitatid, usuariid, data, tipusobjecte, objecteid,
            tipusoperacio, descripcio, objecteSerialitzat);
    } catch (I18NException e) {
      log.error("Error creant bitàcola", e);
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
