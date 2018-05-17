package es.caib.portafib.logic;
 
import java.util.List;


import es.caib.portafib.ejb.UsuariAplicacioConfiguracioEJB;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.ejb3.annotation.SecurityDomain;
 /**
  * 
  * @author anadal
  *
  */
 @Stateless(name="ConfiguracioUsuariAplicacioLogicaEJB")
 @SecurityDomain("seycon")
 public class ConfiguracioUsuariAplicacioLogicaEJB extends UsuariAplicacioConfiguracioEJB
   implements ConfiguracioUsuariAplicacioLogicaLocal
 {

	/*
	 * @Override
	 
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void deleteFull(TipusDocumentJPA tipusDocument) throws Exception, I18NException {
		
	};
*/
   
   @Override
   @RolesAllowed({"PFI_ADMIN","PFI_USER"})
   public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacio(
       final String usuariAplicacioID) throws I18NException {
     final UsuariAplicacioConfiguracio config;
     {
       List<UsuariAplicacioConfiguracio> configuracioList;
       configuracioList = this.select(
           UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));
       
       if (configuracioList == null || configuracioList.size() != 1) {
         // XYZ ZZZ Traduir
         // configusrapp.error.senseconfiguracio
         //return generateServerError("No s'ha definit cap configuració de firma per l'usuari aplicació "
         //   + usuariAplicacioID + "(Consulti amb l'administrador de PortaFIB)");
         throw new I18NException("configusrapp.error.senseconfiguracio", usuariAplicacioID);
       }
       
       config = configuracioList.get(0);
     }
     return config;
   }

 }
