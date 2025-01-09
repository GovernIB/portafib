package es.caib.portafib.api.interna.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

/**
 *
 * @author anadal
 *
 */
@OpenAPIDefinition(
        servers = { @Server(url = "/portafibapi/interna"),
                @Server(url = "http://localhost:8080/portafibapi/interna"),
                @Server(url = "https://dev.caib.es/portafibapi/interna"),
                @Server(url = "https://proves.caib.es/portafibapi/interna"),
                @Server(url = "https://se.caib.es/portafibapi/interna"),
                @Server(url = "https://www.caib.es/portafibapi/interna") },
        externalDocs = @ExternalDocumentation(
                description = "Java API Interna Client (Github Source Code)",
                url = "https://github.com/GovernIB/portafib/tree/portafib-3.0/")
)
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    protected Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public JAXRSConfiguration() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        log.info("Iniciant API REST INTERNA de PortaFIB");
    }

}
