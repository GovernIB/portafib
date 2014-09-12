package es.caib.portafib.ws.utils;

import java.util.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author anadal
 * 
 */
public class WsTimestampAdapter extends XmlAdapter<Date, Timestamp> {

  @Override
  public Date marshal(Timestamp v) {
    return new Date(v.getTime());
  }

  @Override
  public Timestamp unmarshal(Date v) {
    return new Timestamp(v.getTime());
  }
}
