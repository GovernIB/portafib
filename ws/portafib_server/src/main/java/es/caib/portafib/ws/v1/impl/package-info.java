

@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsTimestampAdapter.class,type=java.sql.Timestamp.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsSqlDateAdapter.class,type=java.sql.Date.class),
  @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(value=org.fundaciobit.genapp.common.ws.WsTimeAdapter.class,type=java.sql.Time.class)
})
package es.caib.portafib.ws.v1.impl;