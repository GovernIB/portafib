package es.caib.portafib.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.AbstractTableManager;
import org.fundaciobit.genapp.common.query.GroupBy;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPortaFIBJPAManager<I extends IGenAppEntity, PK extends Object>
    extends AbstractTableManager<I, PK> {

  public final Logger log = Logger.getLogger(this.getClass());

  
  public void delete(I persistentInstance) {
    getEntityManager().remove(getEntityManager().merge(getJPAInstance(persistentInstance)));
  }
  
  public void delete(PK id) {
    delete(findByPrimaryKey(id));
  }
  
  public int delete(Where where) throws I18NException {
    Query q = getEntityManager().createQuery("delete " + generateWhereQueryString(where));
    if (where != null) {
      where.setValues(q);
    }
    return q.executeUpdate();
  }

  public I create(I transientInstance) throws I18NException {
    try {
      I __tmp = getJPAInstance(transientInstance);
      getEntityManager().persist(__tmp);
      return __tmp;
    } catch (Exception e) {
      log.error("Error creant element de tipus " + getTableNameVariable(), e);
      throw new I18NException(e, "error.create",
          new I18NArgumentString(getTableNameVariable()),
          new I18NArgumentString(e.getMessage()));
    }
  }

  public I update(I instance) throws I18NException {
    try {
      return getEntityManager().merge(getJPAInstance(instance));
    } catch (Exception e) {
      log.error(e);
      throw new I18NException(e, "error.update",
          new I18NArgumentString(getTableNameVariable()),
          new I18NArgumentString(e.getMessage()));
    }
  }
  
  public I findByPrimaryKey(PK id) {
    return id == null? null : (I)getEntityManager().find(getJPAClass(), id);
  }
  
  public abstract Class<?> getJPAClass();
  
  protected abstract I getJPAInstance(I __bean);
  
  protected abstract EntityManager getEntityManager();
  
  protected abstract String getTableNameVariable();
  
  //public abstract Select<I> getSelectAll(); 
  

  public List<I> select(Where where, OrderBy ... orderBy) throws I18NException {
    return select(where, null, null, orderBy);
  }
  
  
  public class SelectAll extends Select<I> {
    
    public String getSelectString() {
      return getTableNameVariable();
    }
    
    public I getFromObject(Object rs) throws I18NException {
      return(I)rs;
    }
  }
  
  
  
  @SuppressWarnings("unchecked")
  public List<I> select(Where where, Integer firstResult, Integer maxResults,
      OrderBy ... orderBy) throws I18NException {
    
    String __query = generateQueryString(new SelectAll(), where, orderBy);
    
    Query q = getEntityManager().createQuery(__query);
    if (where != null) {
      where.setValues(q);
    }
    if (firstResult != null) { q.setFirstResult(firstResult); }
    if (maxResults != null) {
       q.setMaxResults(maxResults);
    }
    try {
      return q.getResultList();
    } catch(Exception e) {
       log.error("Error executant la sentència SQL: " + __query.toString());
       throw new I18NException(e, "error.query", 
           new I18NArgumentString( __query.toString()),
           new I18NArgumentString(e.getMessage()));
    }
  }
  
  public String generateQueryString(Select<?> select, Where where, OrderBy[] orderBy) {
     // select
     StringBuffer query = new StringBuffer("select " + select.getSelectString());
     // from
     query.append(" from " + getTableName() + " " + getTableNameVariable());
     // where
     if (where != null) {
       query.append(" where " + where.toSQL());
     }
     // group by
     if (select instanceof GroupBy) {
       String groupby = ((GroupBy)select).getGroupBy();
       if (groupby != null && groupby.trim().length() != 0) {
         query.append(" group by " + groupby);
       }
     }
     // order by
     query.append(OrderBy.processOrderBy(orderBy));

     return query.toString();
   }
  
  public String generateSelectQueryString(Select<?> select, Where where, OrderBy[] orderBy) {
    // select
    StringBuffer query = new StringBuffer("select " + select.getSelectString());
    // from i where
    query.append(generateWhereQueryString(where));
    // group by
    if (select instanceof GroupBy) {
      String groupby = ((GroupBy) select).getGroupBy();
      if (groupby != null && groupby.trim().length() != 0) {
        query.append(" group by " + groupby);
      }
    }
    // order by
    query.append(OrderBy.processOrderBy(orderBy));
    return query.toString();
  }

  public String generateWhereQueryString(Where where) {
    StringBuffer query = new StringBuffer();
    // from
    query.append(" from " + getTableName() + " " + getTableNameVariable());
    // where
    if (where != null) {
      query.append(" where " + where.toSQL());
    }
    return query.toString();
  }

}
