
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.PosicioTaulaFirmes;
import es.caib.portafib.jpa.PosicioTaulaFirmesJPA;
import es.caib.portafib.jpa.PosicioTaulaFirmesJPAManager;

@Stateless(name = "PosicioTaulaFirmesEJB")
@SecurityDomain("seycon")
public class PosicioTaulaFirmesEJB extends PosicioTaulaFirmesJPAManager implements PosicioTaulaFirmesLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(PosicioTaulaFirmes instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PosicioTaulaFirmes create(PosicioTaulaFirmes instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public PosicioTaulaFirmes update(PosicioTaulaFirmes instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public PosicioTaulaFirmesJPA findByPrimaryKey(Integer _ID_) {
    return (PosicioTaulaFirmesJPA)super.findByPrimaryKey(_ID_);
  }

}
