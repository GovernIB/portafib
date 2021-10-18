
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PerfilDeFirmaJPAManager
         extends AbstractJPAManager<PerfilDeFirma, Long>
         implements PerfilDeFirmaIJPAManager, IPerfilDeFirmaManager, PerfilDeFirmaFields {




    private static final long serialVersionUID = -497386936L;

    public static final TableName<PerfilDeFirma> _TABLENAME =  new TableName<PerfilDeFirma>("PerfilDeFirmaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PerfilDeFirmaJPAManager() {
    }

    protected PerfilDeFirmaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PerfilDeFirmaJPA. class;
    }



    public TableName<PerfilDeFirma> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public PerfilDeFirma[] listToArray(List<PerfilDeFirma> list)  {
        if(list == null) { return null; };
        return list.toArray(new PerfilDeFirma[list.size()]);
    };

    public synchronized PerfilDeFirma create( java.lang.String _nom_, java.lang.String _codi_, java.lang.String _descripcio_, java.lang.String _condicio_, long _configuracioDeFirma1ID_, java.lang.Long _configuracioDeFirma2ID_, java.lang.Long _configuracioDeFirma3ID_, java.lang.Long _configuracioDeFirma4ID_, java.lang.Long _configuracioDeFirma5ID_, java.lang.String _urlBase_) throws I18NException {
        PerfilDeFirmaJPA __bean =  new PerfilDeFirmaJPA(_nom_,_codi_,_descripcio_,_condicio_,_configuracioDeFirma1ID_,_configuracioDeFirma2ID_,_configuracioDeFirma3ID_,_configuracioDeFirma4ID_,_configuracioDeFirma5ID_,_urlBase_);
        return create(__bean);
    }



 public void delete(long _usuariAplicacioPerfilID_) {
   delete(findByPrimaryKey(_usuariAplicacioPerfilID_));
 }




    public PerfilDeFirma findByPrimaryKey(long _usuariAplicacioPerfilID_) {
        return __em.find(PerfilDeFirmaJPA.class, _usuariAplicacioPerfilID_);  
    }
    @Override
    protected PerfilDeFirma getJPAInstance(PerfilDeFirma __bean) {
        return convertToJPA(__bean);
    }


    public static PerfilDeFirmaJPA convertToJPA(PerfilDeFirma __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PerfilDeFirmaJPA) {
        return (PerfilDeFirmaJPA)__bean;
      }
      
      return PerfilDeFirmaJPA.toJPA(__bean);
    }


}