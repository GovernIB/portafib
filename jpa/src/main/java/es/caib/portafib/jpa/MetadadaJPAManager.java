
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


public class MetadadaJPAManager
		 extends AbstractPortaFIBJPAManager<Metadada, Long>
		 implements IMetadadaManager, MetadadaFields {




  private static final long serialVersionUID = 591487505L;

	 public static final TableName<Metadada> _TABLENAME =  new TableName<Metadada>("MetadadaJPA");



  static final ModificationManager<Metadada> __eventManager = new ModificationManager<Metadada>();


  @PersistenceContext
  protected EntityManager __em;
  public MetadadaJPAManager() {
  }
  protected MetadadaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return MetadadaJPA. class;
	}



	public ModificationManager<Metadada> getEventManager() {
	return __eventManager;
	}


	public TableName<Metadada> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Metadada[] listToArray(List<Metadada> list)  {
		if(list == null) { return null; };
		return list.toArray(new Metadada[list.size()]);
	};

	public Metadada create( java.lang.String _nom_, java.lang.String _valor_, java.lang.String _descripcio_, long _peticioDeFirmaID_, int _tipusMetadadaID_) throws I18NException {
		MetadadaJPA __bean =  new MetadadaJPA(_nom_,_valor_,_descripcio_,_peticioDeFirmaID_,_tipusMetadadaID_);
		return create(__bean);
	}



 public void delete(long _metadadaID_) {
   delete(findByPrimaryKey(_metadadaID_));
 }




	public Metadada findByPrimaryKey(long _metadadaID_) {
	  return __em.find(MetadadaJPA.class, _metadadaID_);  
	}
	@Override
	protected Metadada getJPAInstance(Metadada __bean) {
		return convertToJPA(__bean);
	}


	public static MetadadaJPA convertToJPA(Metadada __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof MetadadaJPA) {
	    return (MetadadaJPA)__bean;
	  }
	  
	  return MetadadaJPA.toJPA(__bean);
	}


}