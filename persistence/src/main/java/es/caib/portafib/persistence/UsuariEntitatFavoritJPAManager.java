
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariEntitatFavoritJPAManager
         extends AbstractJPAManager<UsuariEntitatFavorit, Long>
         implements UsuariEntitatFavoritIJPAManager, IUsuariEntitatFavoritManager, UsuariEntitatFavoritFields {




    private static final long serialVersionUID = 449152947L;

    public static final TableName<UsuariEntitatFavorit> _TABLENAME =  new TableName<UsuariEntitatFavorit>("UsuariEntitatFavoritJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UsuariEntitatFavoritJPAManager() {
    }

    protected UsuariEntitatFavoritJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UsuariEntitatFavoritJPA. class;
    }



    public TableName<UsuariEntitatFavorit> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public UsuariEntitatFavorit[] listToArray(List<UsuariEntitatFavorit> list)  {
        if(list == null) { return null; };
        return list.toArray(new UsuariEntitatFavorit[list.size()]);
    };

    public synchronized UsuariEntitatFavorit create( java.lang.String _origenID_, java.lang.String _favoritID_) throws I18NException {
        UsuariEntitatFavoritJPA __bean =  new UsuariEntitatFavoritJPA(_origenID_,_favoritID_);
        return create(__bean);
    }



 public void delete(long _ID_) {
   delete(findByPrimaryKey(_ID_));
 }




    public UsuariEntitatFavorit findByPrimaryKey(long _ID_) {
        return __em.find(UsuariEntitatFavoritJPA.class, _ID_);  
    }
    @Override
    protected UsuariEntitatFavorit getJPAInstance(UsuariEntitatFavorit __bean) {
        return convertToJPA(__bean);
    }


    public static UsuariEntitatFavoritJPA convertToJPA(UsuariEntitatFavorit __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UsuariEntitatFavoritJPA) {
        return (UsuariEntitatFavoritJPA)__bean;
      }
      
      return UsuariEntitatFavoritJPA.toJPA(__bean);
    }


}