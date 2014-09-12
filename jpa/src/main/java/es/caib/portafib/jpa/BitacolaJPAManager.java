
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


public class BitacolaJPAManager
		 extends AbstractPortaFIBJPAManager<Bitacola, Long >
		 implements IBitacolaManager, BitacolaFields {




  private static final long serialVersionUID = 1518198567L;

	 public static final TableName<Bitacola> _TABLENAME =  new TableName<Bitacola>("BitacolaJPA");



  static final ModificationManager<Bitacola> __eventManager = new ModificationManager<Bitacola>();


  @PersistenceContext
  protected EntityManager __em;
  public BitacolaJPAManager() {
  }
  protected BitacolaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return BitacolaJPA. class;
	}



	public ModificationManager<Bitacola> getEventManager() {
	return __eventManager;
	}


	public TableName<Bitacola> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Bitacola[] listToArray(List<Bitacola> list)  {
		if(list == null) { return null; };
		return list.toArray(new Bitacola[list.size()]);
	};

	public synchronized Bitacola create( java.sql.Timestamp _data_, java.lang.String _descripcio_, long _peticioDeFirmaID_, java.lang.String _usuariEntitatID_) throws I18NException {
		BitacolaJPA __bean =  new BitacolaJPA(_data_,_descripcio_,_peticioDeFirmaID_,_usuariEntitatID_);
		return create(__bean);
	}



 public void delete(long _bitacolaID_) {
   delete(findByPrimaryKey(_bitacolaID_));
 }




	public Bitacola findByPrimaryKey(long _bitacolaID_) {
	  return __em.find(BitacolaJPA.class, _bitacolaID_);  
	}
	@Override
	protected Bitacola getJPAInstance(Bitacola __bean) {
		return convertToJPA(__bean);
	}


	public static BitacolaJPA convertToJPA(Bitacola __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof BitacolaJPA) {
	    return (BitacolaJPA)__bean;
	  }
	  
	  return BitacolaJPA.toJPA(__bean);
	}


}