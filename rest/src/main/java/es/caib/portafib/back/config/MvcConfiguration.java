package es.caib.portafib.back.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * 
 * @author anadal
 *
 */
@Configuration
@ComponentScan(basePackages = { "es.caib.portafib" })
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Obliga a tots els Serveis Rest a retornar JSON. 
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new org.springframework.context.support.ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");

        messageSource.addBasenames("/WEB-INF/classes/missatges", "/WEB-INF/classes/logicmissatges",
                "/WEB-INF/classes/portafib_genapp", "/WEB-INF/classes/genapp");

        // messageSource.addBasenames("/WEB-INF/classes/messages",
        // "/WEB-INF/classes/ValidationMessages",
        // "classpath:shared/ValidationMessages");

        return messageSource;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");

        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

}
