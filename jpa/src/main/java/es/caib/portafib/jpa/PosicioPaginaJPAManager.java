
package es.caib.portafib.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.events.ModificationManager;


public class PosicioPaginaJPAManager
		 extends AbstractPortaFIBJPAManager<PosicioPagina, Long >
		 implements IPosicioPaginaManager, PosicioPaginaFields {




  private static final long serialVersionUID = 778989452L;

	 public static final TableName<PosicioPagina> _TABLENAME =  new TableName<PosicioPagina>("PosicioPaginaJPA");



  static final ModificationManager<PosicioPagina> __eventManager = new ModificationManager<PosicioPagina>();


  @PersistenceContext
  protected EntityManager __em;
  public PosicioPaginaJPAManager() {
  }
  protected PosicioPaginaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PosicioPaginaJPA. class;
	}



	public ModificationManager<PosicioPagina> getEventManager() {
	return __eventManager;
	}


	public TableName<PosicioPagina> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PosicioPagina[] listToArray(List<PosicioPagina> list)  {
		if(list == null) { return null; };
		return list.toArray(new PosicioPagina[list.size()]);
	};

	public synchronized PosicioPagina create( long _posicioPaginaID_, java.lang.String _nom_) throws I18NException {
		PosicioPaginaJPA __bean =  new PosicioPaginaJPA(_posicioPaginaID_,_nom_);
		return create(__bean);
	}



 public void delete(long _posicioPaginaID_) {
   delete(findByPrimaryKey(_posicioPaginaID_));
 }




	public PosicioPagina findByPrimaryKey(long _posicioPaginaID_) {
	  return __em.find(PosicioPaginaJPA.class, _posicioPaginaID_);  
	}
	@Override
	protected PosicioPagina getJPAInstance(PosicioPagina __bean) {
		return convertToJPA(__bean);
	}


	public static PosicioPaginaJPA convertToJPA(PosicioPagina __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PosicioPaginaJPA) {
	    return (PosicioPaginaJPA)__bean;
	  }
	  
	  return PosicioPaginaJPA.toJPA(__bean);
	}


}