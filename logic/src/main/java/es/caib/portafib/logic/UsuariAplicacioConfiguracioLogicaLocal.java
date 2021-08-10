package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

/**
 * EJB que bàsicament sobreescriu els mètodes de crear/actualitzar/esborrar per permetre la bitàcola #335
 * @author areus
 */
@Local
public interface UsuariAplicacioConfiguracioLogicaLocal extends UsuariAplicacioConfiguracioLocal {

   String JNDI_NAME = "java:app/portafib-logic/UsuariAplicacioConfiguracioLogicaEJB";

   @Override
   void delete(UsuariAplicacioConfiguracio instance);

   @Override
   UsuariAplicacioConfiguracio create(UsuariAplicacioConfiguracio instance) throws I18NException;

   @Override
   UsuariAplicacioConfiguracio update(UsuariAplicacioConfiguracio instance) throws I18NException;
}
