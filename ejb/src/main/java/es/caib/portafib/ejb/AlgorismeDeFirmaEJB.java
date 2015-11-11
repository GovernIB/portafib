
package es.caib.portafib.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.entity.AlgorismeDeFirma;
import es.caib.portafib.jpa.AlgorismeDeFirmaJPA;
import es.caib.portafib.jpa.AlgorismeDeFirmaJPAManager;

@Stateless(name = "AlgorismeDeFirmaEJB")
@SecurityDomain("seycon")
public class AlgorismeDeFirmaEJB extends AlgorismeDeFirmaJPAManager implements AlgorismeDeFirmaLocal {

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public void delete(AlgorismeDeFirma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public AlgorismeDeFirma create(AlgorismeDeFirma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
	public AlgorismeDeFirma update(AlgorismeDeFirma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PFI_ADMIN","PFI_USER"})
  public AlgorismeDeFirmaJPA findByPrimaryKey(Integer _ID_) {
    return (AlgorismeDeFirmaJPA)super.findByPrimaryKey(_ID_);
  }

}
