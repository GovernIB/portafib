
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PseudonimJPAManager
         extends AbstractJPAManager<Pseudonim, Long>
         implements PseudonimIJPAManager, IPseudonimManager, PseudonimFields {



    public static final TableName<Pseudonim> _TABLENAME =  new TableName<Pseudonim>("PseudonimJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PseudonimJPAManager() {
    }

    protected PseudonimJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PseudonimJPA. class;
    }



    public TableName<Pseudonim> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Pseudonim[] listToArray(List<Pseudonim> list)  {
        if(list == null) { return null; };
        return list.toArray(new Pseudonim[list.size()]);
    };

    public Pseudonim create( java.lang.String _pseudonim_, java.lang.String _nif_) throws I18NException {
        PseudonimJPA __bean =  new PseudonimJPA(_pseudonim_,_nif_);
        return create(__bean);
    }



 public void delete(long _pseudonimid_) {
   delete(findByPrimaryKey(_pseudonimid_));
 }




    public Pseudonim findByPrimaryKey(long _pseudonimid_) {
        return __em.find(PseudonimJPA.class, _pseudonimid_);  
    }
    @Override
    protected Pseudonim getJPAInstance(Pseudonim __bean) {
        return convertToJPA(__bean);
    }


    public static PseudonimJPA convertToJPA(Pseudonim __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PseudonimJPA) {
        return (PseudonimJPA)__bean;
      }
      
      return PseudonimJPA.toJPA(__bean);
    }


}