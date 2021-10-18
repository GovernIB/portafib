
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TipusDocumentJPAManager
         extends AbstractJPAManager<TipusDocument, Long>
         implements TipusDocumentIJPAManager, ITipusDocumentManager, TipusDocumentFields {




    private static final long serialVersionUID = -1199196410L;

    public static final TableName<TipusDocument> _TABLENAME =  new TableName<TipusDocument>("TipusDocumentJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TipusDocumentJPAManager() {
    }

    protected TipusDocumentJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TipusDocumentJPA. class;
    }



    public TableName<TipusDocument> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TipusDocument[] listToArray(List<TipusDocument> list)  {
        if(list == null) { return null; };
        return list.toArray(new TipusDocument[list.size()]);
    };

    public synchronized TipusDocument create( long _tipusDocumentID_, long _nomID_, long _tipusDocumentBaseID_, java.lang.String _descripcio_, java.lang.String _usuariAplicacioID_) throws I18NException {
        TipusDocumentJPA __bean =  new TipusDocumentJPA(_tipusDocumentID_,_nomID_,_tipusDocumentBaseID_,_descripcio_,_usuariAplicacioID_);
        return create(__bean);
    }



 public void delete(long _tipusDocumentID_) {
   delete(findByPrimaryKey(_tipusDocumentID_));
 }




    public TipusDocument findByPrimaryKey(long _tipusDocumentID_) {
        return __em.find(TipusDocumentJPA.class, _tipusDocumentID_);  
    }
    @Override
    protected TipusDocument getJPAInstance(TipusDocument __bean) {
        return convertToJPA(__bean);
    }


    public static TipusDocumentJPA convertToJPA(TipusDocument __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TipusDocumentJPA) {
        return (TipusDocumentJPA)__bean;
      }
      
      return TipusDocumentJPA.toJPA(__bean);
    }

  @Override
  public TipusDocument create(TipusDocument transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public TipusDocument update(TipusDocument transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(TipusDocument transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof TipusDocumentJPA) {
          TipusDocumentJPA _jpa = (TipusDocumentJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}