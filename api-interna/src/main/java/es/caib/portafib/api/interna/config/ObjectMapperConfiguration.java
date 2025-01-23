package es.caib.portafib.api.interna.config;

import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import org.fundaciobit.pluginsib.utils.rest.ISO8601DateTimeSerializer;
import org.fundaciobit.pluginsib.utils.rest.ISO8601TimestampSerializer;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;


/**
 * 
 * @author anadal
 *
 */
@Provider
@Produces("application/json")
public class ObjectMapperConfiguration extends JacksonJaxbJsonProvider {

    protected static Logger log = Logger.getLogger(ObjectMapperConfiguration.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {

        SimpleModule modul = new SimpleModule();
        modul.addSerializer(Timestamp.class, new ISO8601TimestampSerializer());
        modul.addSerializer(Date.class, new ISO8601DateTimeSerializer());
        modul.addSerializer(byte[].class, new ByteArraySerializer());
        MAPPER.registerModule(modul);

        // allow only non-null fields to be serialized
        MAPPER.setSerializationInclusion(Include.NON_NULL);
        
    }

    public ObjectMapperConfiguration() {
        
        log.info("Inicialitzant ObjectMapperConfiguration ...");
        
        super.setMapper(MAPPER);
    }

    


}

