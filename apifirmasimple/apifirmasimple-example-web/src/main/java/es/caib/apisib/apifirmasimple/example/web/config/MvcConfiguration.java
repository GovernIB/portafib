package es.caib.apisib.apifirmasimple.example.web.config;

import javax.validation.Validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/**
 * 
 * @author anadal
 *
 */
@Configuration
@ComponentScan(basePackages="es.caib.apisib.apifirmasimple.example.web")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}

	@Bean
	public MultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
	
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
	
	  ReloadableResourceBundleMessageSource messageSource = new org.springframework.context.support.ReloadableResourceBundleMessageSource();
	  messageSource.setDefaultEncoding("UTF-8");
	  messageSource.addBasenames("/WEB-INF/classes/messages", 
	          "/WEB-INF/classes/ValidationMessages", "classpath:shared/ValidationMessages");
	  

	 return messageSource;
  }


	@Bean
  public LocalValidatorFactoryBean validator() {
	    
	    ReloadableResourceBundleMessageSource messageSource = messageSource();
	    
      MyLocalValidatorFactoryBean bean = new MyLocalValidatorFactoryBean(messageSource);
      bean.setValidationMessageSource(messageSource);
      
      bean.setMessageInterpolator(new MyMessageInterpolator( 
              Validation.byDefaultProvider().configure().getDefaultMessageInterpolator()));
      
     
      return bean;
  }

  @Override
  public org.springframework.validation.Validator getValidator() {
      return validator();
  }
	
	
	
}
