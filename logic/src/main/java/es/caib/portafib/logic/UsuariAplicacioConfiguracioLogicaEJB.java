package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioEJB;
import es.caib.portafib.model.bean.UsuariAplicacioConfiguracioBean;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * EJB que bàsicament sobreescriu els mètodes de crear/actualitzar/esborrar per permetre la bitàcola #335
 * @author areus
 */
@Stateless(name = "UsuariAplicacioConfiguracioLogicaEJB")
@SecurityDomain("seycon")
public class UsuariAplicacioConfiguracioLogicaEJB extends UsuariAplicacioConfiguracioEJB
      implements UsuariAplicacioConfiguracioLogicaLocal {

   @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
   protected BitacolaLogicaLocal bitacolaLogicaEjb;

   @Override
   @RolesAllowed({"PFI_ADMIN","PFI_USER"})
   public void delete(UsuariAplicacioConfiguracio instance) {
      super.delete(instance);
      bitacolaLogicaEjb.createBitacola(
            instance.getEntitatID(),
            instance.getUsuariAplicacioConfigID(),
            ConstantsV2.BITACOLA_TIPUS_CONFIGURACIO,
            ConstantsV2.BITACOLA_OP_ESBORRAR,
            "Esborrada configuració " + instance.getNom());
   }

   @Override
   @RolesAllowed({"PFI_ADMIN","PFI_USER"})
   public UsuariAplicacioConfiguracio create(UsuariAplicacioConfiguracio instance) throws I18NException {
      UsuariAplicacioConfiguracio configuracio = super.create(instance);
      bitacolaLogicaEjb.createBitacola(
            configuracio.getEntitatID(),
            configuracio.getUsuariAplicacioConfigID(),
            ConstantsV2.BITACOLA_TIPUS_CONFIGURACIO,
            ConstantsV2.BITACOLA_OP_CREAR,
            "Creada configuració " + configuracio.getNom(),
            UsuariAplicacioConfiguracioBean.toBean(configuracio));
      return configuracio;
   }

   @Override
   @RolesAllowed({"PFI_ADMIN","PFI_USER"})
   public UsuariAplicacioConfiguracio update(UsuariAplicacioConfiguracio instance) throws I18NException {
      UsuariAplicacioConfiguracio configuracio = super.update(instance);
      bitacolaLogicaEjb.createBitacola(
            configuracio.getEntitatID(),
            configuracio.getUsuariAplicacioConfigID(),
            ConstantsV2.BITACOLA_TIPUS_CONFIGURACIO,
            ConstantsV2.BITACOLA_OP_ACTUALITZAR,
            "Actualitzada configuració " + configuracio.getNom(),
            UsuariAplicacioConfiguracioBean.toBean(configuracio));
      return configuracio;
   }
}
