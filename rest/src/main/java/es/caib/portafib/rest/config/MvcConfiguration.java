package es.caib.portafib.rest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author anadal
 *
 */
@Configuration
@ComponentScan(basePackages = { "es.caib.portafib" })
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Obliga a tots els Serveis Rest a retornar JSON. 
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new org.springframework.context.support.ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.addBasenames("/WEB-INF/classes/missatges", "/WEB-INF/classes/logicmissatges",
                "/WEB-INF/classes/portafib_genapp", "/WEB-INF/classes/genapp");
        return messageSource;
    }

}
