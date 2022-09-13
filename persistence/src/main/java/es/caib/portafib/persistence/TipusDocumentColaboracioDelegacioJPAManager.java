
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TipusDocumentColaboracioDelegacioJPAManager
         extends AbstractJPAManager<TipusDocumentColaboracioDelegacio, Long>
         implements TipusDocumentColaboracioDelegacioIJPAManager, ITipusDocumentColaboracioDelegacioManager, TipusDocumentColaboracioDelegacioFields {



    public static final TableName<TipusDocumentColaboracioDelegacio> _TABLENAME =  new TableName<TipusDocumentColaboracioDelegacio>("TipusDocumentColaboracioDelegacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TipusDocumentColaboracioDelegacioJPAManager() {
    }

    protected TipusDocumentColaboracioDelegacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TipusDocumentColaboracioDelegacioJPA. class;
    }



    public TableName<TipusDocumentColaboracioDelegacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TipusDocumentColaboracioDelegacio[] listToArray(List<TipusDocumentColaboracioDelegacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new TipusDocumentColaboracioDelegacio[list.size()]);
    };

    public TipusDocumentColaboracioDelegacio create( long _colaboracioDelegacioID_, long _tipusDocumentID_) throws I18NException {
        TipusDocumentColaboracioDelegacioJPA __bean =  new TipusDocumentColaboracioDelegacioJPA(_colaboracioDelegacioID_,_tipusDocumentID_);
        return create(__bean);
    }



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




    public TipusDocumentColaboracioDelegacio findByPrimaryKey(long _id_) {
        return __em.find(TipusDocumentColaboracioDelegacioJPA.class, _id_);  
    }
    @Override
    protected TipusDocumentColaboracioDelegacio getJPAInstance(TipusDocumentColaboracioDelegacio __bean) {
        return convertToJPA(__bean);
    }


    public static TipusDocumentColaboracioDelegacioJPA convertToJPA(TipusDocumentColaboracioDelegacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TipusDocumentColaboracioDelegacioJPA) {
        return (TipusDocumentColaboracioDelegacioJPA)__bean;
      }
      
      return TipusDocumentColaboracioDelegacioJPA.toJPA(__bean);
    }


}