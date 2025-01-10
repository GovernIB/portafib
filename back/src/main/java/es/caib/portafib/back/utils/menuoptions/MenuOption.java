package es.caib.portafib.back.utils.menuoptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author anadal
 * 29 nov 2024 13:13:21
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MenuOption {

    /**
     * @return Codi traduïble de l'etiqueta. Si comença per "=" es un literal ja traduït
     */
    public String labelCode();

    public String relativeLink() default "/list";

    public String baseLink() default "";

    /**
     * ordre del menú. Si no s'indica es posa al final
     * @return
     */
    public int order() default Integer.MAX_VALUE;

    /**
     * Si es vol afegir un separador abans de l'opció
     * @return
     */
    public boolean addSeparatorBefore() default false;

    /**
     * @return  Si es vol afegir un separador després de l'opció
     */
    public boolean addSeparatorAfter() default false;

    /**     
     * @return El Menu en que s'ha de ficar. "" Significa cercar a partir del context web
     */
    public String group() default ""; // 

}
