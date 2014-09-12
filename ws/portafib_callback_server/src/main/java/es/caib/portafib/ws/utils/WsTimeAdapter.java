package es.caib.portafib.ws.utils;

import java.util.Date;
import java.sql.Time;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author anadal
 * 
 */
public class WsTimeAdapter extends XmlAdapter<Date, Time> {

  @Override
  public Date marshal(Time v) {
    return new Date(v.getTime());
  }

  @Override
  public Time unmarshal(Date v) {
    return new Time(v.getTime());
  }
}
