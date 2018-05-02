package es.caib.portafib.logic;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.SecurityDomain;

import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.ejb.RoleUsuariEntitatEJB;
import es.caib.portafib.jpa.RoleUsuariEntitatJPA;
import es.caib.portafib.jpa.validator.RoleUsuariEntitatBeanValidator;
import es.caib.portafib.logic.validator.RoleUsuariEntitatLogicValidator;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author dboerner
 * @author anadal
 */
@Stateless(name = "RoleUsuariEntitatLogicaEJB")
@SecurityDomain("seycon")
public class RoleUsuariEntitatLogicaEJB extends RoleUsuariEntitatEJB
		implements RoleUsuariEntitatLogicaLocal, ConstantsV2 {

	@EJB(mappedName = "portafib/UsuariEntitatEJB/local")
	protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;
	
  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  private PeticioDeFirmaLocal peticioDeFirmaEjb;
	
  @EJB(mappedName = es.caib.portafib.ejb.RoleLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleLocal roleEjb;

  RoleUsuariEntitatLogicValidator<RoleUsuariEntitatJPA> validator = new RoleUsuariEntitatLogicValidator<RoleUsuariEntitatJPA>();

  @Override
  @RolesAllowed({PFI_ADMIN, PFI_USER})
  public RoleUsuariEntitatJPA createFull(RoleUsuariEntitatJPA instance)
    throws Exception, I18NException, I18NValidationException {
    
    RoleUsuariEntitatBeanValidator bv;
    bv = new RoleUsuariEntitatBeanValidator(validator, roleEjb , this, usuariEntitatEjb);
    
    boolean isNou = true;
    bv.throwValidationExceptionIfErrors(instance, isNou);

    return (RoleUsuariEntitatJPA)create(instance);
    
  }


  @Override
  @RolesAllowed({PFI_ADMIN})
  public void deleteFull(String usuariEntitatID, String roleID) throws Exception, I18NException {

    // Validar parametres
    Long id = executeQueryOne(ID, Where.AND(
         USUARIENTITATID.equal(usuariEntitatID),
         ROLEID.equal(roleID)));
    if (id == null) {
      return;
    }

    RoleUsuariEntitatJPA instance = new RoleUsuariEntitatJPA();
    instance.setUsuariEntitatID(usuariEntitatID);
    instance.setRoleID(roleID);
    instance.setId(id);

    RoleUsuariEntitatBeanValidator bv;
    bv = new RoleUsuariEntitatBeanValidator(validator,  roleEjb , this, usuariEntitatEjb);
    
    final boolean isNou = false;
    List<I18NFieldError> errors = bv.validate(instance, isNou);
    
    if (!errors.isEmpty()) {
      throw new I18NException(errors.get(0).getTranslation());
    }
    
    
    // Mirar si existeix i obtenir ID
    List<RoleUsuariEntitat> list = select(
          Where.AND(
             USUARIENTITATID.equal(usuariEntitatID),
             ROLEID.equal(roleID)
          )
        );
    
    if (list.isEmpty()) {
      return;
    }
    
    
    instance = (RoleUsuariEntitatJPA)list.get(0);
		

		if (ConstantsV2.ROLE_ADEN.equals(roleID)) {
		  // NO es pot borrar el role ADEN si és el darrer administrador d'entitat d'una entitat
			
			// 1. recuperar de usuarientitat la entrada con usuarientitatid == instance.getUsuariEntitatID()
			UsuariEntitat usuariEntitat = usuariEntitatEjb.findByPrimaryKey(instance.getUsuariEntitatID());
			// 2. de la entrada anterior, obtener el entitatid
			String entitatID = usuariEntitat.getEntitatID();
			// 3. Conjunto de usuarientitatid con entitatid == entitatID
			SubQuery<UsuariEntitat, String> subQ = usuariEntitatEjb
					.getSubQuery(UsuariEntitatFields.USUARIENTITATID,
							UsuariEntitatFields.ENTITATID.equal(entitatID));
			// 3.1 Columna USUARIENTITATID
			Where w1 = USUARIENTITATID.in(subQ);
			// 3.2 Columna ROLEID
			Where w2 = ROLEID.equal(ConstantsV2.ROLE_ADEN);
			// 3.3 Combinamos las condiciones anteriores
			Where w = Where.AND(w1, w2);
			
			// 4. si es mayor que 1, entonces eliminar de rolesuarientitat la entrada con usuarientitatid == instance.getUsuariEntitatID()

			if (count(w) == 1) {
				throw new I18NException("roleusuarientitat.aden.error.esunic", usuariEntitat.getUsuariPersonaID());
			}						
		}
		
		if (ConstantsV2.ROLE_SOLI.equals(roleID)) {
		  // No es pot borrar el role SOLI si l'usuari té solicituds de firma associades
		  Long count = peticioDeFirmaEjb.count(
		      es.caib.portafib.model.fields.PeticioDeFirmaFields.USUARIENTITATID.equal(instance.getUsuariEntitatID()));
		  if (count != 0) {
		    throw new I18NException("roleusuarientitat.solicitant.error.tepeticionsdefirma");
		  }
		}
		
		// DEST mentre tengui firmes pendents
	
		// TODO faltan checks para los demás roles
		
		super.delete(instance);
	}
  
  @Override
  public List<RoleUsuariEntitat> selectFullWithEntitat(Where where,
     final OrderBy[] orderBy, final Integer itemsPerPage, final int inici) throws I18NException {
    
    List<RoleUsuariEntitat> list;
    if (itemsPerPage == null) {
      list = this.select(where, orderBy);
    } else {
      list = this.select(where, inici, itemsPerPage, orderBy);
    }
    
    for(RoleUsuariEntitat rue : list) {
      RoleUsuariEntitatJPA rueJPA = (RoleUsuariEntitatJPA)rue;
      Hibernate.initialize(rueJPA.getUsuariEntitat());
      Hibernate.initialize(rueJPA.getUsuariEntitat().getEntitat());
    }

    return list;
  }
  

}
