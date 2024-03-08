package es.caib.portafib.api.interna.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
        info = @Info(
                title = "API REST INTERNA de PortaFIB",
                description = "Conjunt de Serveis REST de PortaFIB per ser accedits des de l'interior",
                version = "1.0.0",
                license = @License(name = "License Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "http://otae.fundaciobit.org")

        ),
        servers = { @Server(url = "/portafibapi/interna"),
                    @Server(url = "http://localhost:8080/portafibapi/interna"),
                    @Server(url = "https://dev.caib.es/portafibapi/interna"),
                    @Server(url = "https://proves.caib.es/portafibapi/interna"),
                    @Server(url = "https://se.caib.es/portafibapi/interna"),
                    @Server(url = "https://www.caib.es/portafibapi/interna")
                },
        externalDocs = @ExternalDocumentation(
                description = "Java Client (GovernIB Github)",
                url = "https://github.com/GovernIB/portafib/tree/portafib-1.0/portafib-api-interna-client")

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
