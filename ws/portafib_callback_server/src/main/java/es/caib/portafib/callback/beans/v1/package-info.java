

@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.portafib.ws.utils.WsTimestampAdapter.class,type=java.sql.Timestamp.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.portafib.ws.utils.WsSqlDateAdapter.class,type=java.sql.Date.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=es.caib.portafib.ws.utils.WsTimeAdapter.class,type=java.sql.Time.class),
})
package es.caib.portafib.callback.beans.v1;