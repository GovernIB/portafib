
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.CustodiaInfoJPAManager;

@Stateless(name = "CustodiaInfoEJB")
@SecurityDomain("seycon")
public class CustodiaInfoEJB extends CustodiaInfoJPAManager implements CustodiaInfoLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(CustodiaInfo instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public CustodiaInfo create(CustodiaInfo instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public CustodiaInfo update(CustodiaInfo instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public CustodiaInfoJPA findByPrimaryKey(Long _ID_) {
    return (CustodiaInfoJPA)super.findByPrimaryKey(_ID_);
  }

}
