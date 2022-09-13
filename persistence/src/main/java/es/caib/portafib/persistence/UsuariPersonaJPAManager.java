
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariPersonaJPAManager
         extends AbstractJPAManager<UsuariPersona, String>
         implements UsuariPersonaIJPAManager, IUsuariPersonaManager, UsuariPersonaFields {



    public static final TableName<UsuariPersona> _TABLENAME =  new TableName<UsuariPersona>("UsuariPersonaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UsuariPersonaJPAManager() {
    }

    protected UsuariPersonaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UsuariPersonaJPA. class;
    }



    public TableName<UsuariPersona> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public UsuariPersona[] listToArray(List<UsuariPersona> list)  {
        if(list == null) { return null; };
        return list.toArray(new UsuariPersona[list.size()]);
    };

    public UsuariPersona create( java.lang.String _usuariPersonaID_, java.lang.String _nom_, java.lang.String _llinatges_, java.lang.String _email_, java.lang.String _nif_, java.lang.String _idiomaID_, java.lang.Long _rubricaID_, boolean _usuariIntern_, java.lang.String _contrasenya_) throws I18NException {
        UsuariPersonaJPA __bean =  new UsuariPersonaJPA(_usuariPersonaID_,_nom_,_llinatges_,_email_,_nif_,_idiomaID_,_rubricaID_,_usuariIntern_,_contrasenya_);
        return create(__bean);
    }



 public void delete(java.lang.String _usuariPersonaID_) {
   delete(findByPrimaryKey(_usuariPersonaID_));
 }




    public UsuariPersona findByPrimaryKey(java.lang.String _usuariPersonaID_) {
        return __em.find(UsuariPersonaJPA.class, _usuariPersonaID_);  
    }
    @Override
    protected UsuariPersona getJPAInstance(UsuariPersona __bean) {
        return convertToJPA(__bean);
    }


    public static UsuariPersonaJPA convertToJPA(UsuariPersona __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UsuariPersonaJPA) {
        return (UsuariPersonaJPA)__bean;
      }
      
      return UsuariPersonaJPA.toJPA(__bean);
    }


}