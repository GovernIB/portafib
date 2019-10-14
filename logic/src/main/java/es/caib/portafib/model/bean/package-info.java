@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters( {
      @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(
            value=org.fundaciobit.genapp.common.ws.WsTimestampAdapter.class,
            type=java.sql.Timestamp.class),
      @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(
            value= es.caib.portafib.logic.jaxb.DataHandlerAdapter.class,
            type=javax.activation.DataHandler.class)
})
package es.caib.portafib.model.bean;