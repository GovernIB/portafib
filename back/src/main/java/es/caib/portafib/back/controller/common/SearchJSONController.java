package es.caib.portafib.back.controller.common;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.controller.RefListBase;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.portafib.back.form.webdb.UsuariPersonaRefList;
import es.caib.portafib.back.reflist.CarrecJSONRefList;
import es.caib.portafib.back.reflist.UsuariEntitatJSONRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  
 * @autor anadal
 * 
 */
@Controller
@RequestMapping(value = "/common/json")
public class SearchJSONController {

  protected static final Logger log = Logger.getLogger(SearchJSONController.class);

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
 
  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariEntitatLocal roleUsuariEntitatEjb;


  @Autowired
  protected UsuariEntitatJSONRefList usuariEntitatRefList;
 
  @Autowired
  protected CarrecJSONRefList carrecRefList;

  @Autowired
  protected UsuariPersonaRefList usuariPersonaRefList;

  @PostConstruct
  public void init() {

    {
      this.usuariPersonaRefList = new UsuariPersonaRefList(usuariPersonaRefList);

      this.usuariPersonaRefList.setSelects(new Select<?>[] { 
          UsuariPersonaFields.LLINATGES.select , new SelectConstant(", "), 
          UsuariPersonaFields.NOM.select, new SelectConstant(" ("), 
          UsuariPersonaFields.NIF.select, new SelectConstant(" - "),
          UsuariPersonaFields.USUARIPERSONAID.select,new SelectConstant(")") });
      
      this.usuariPersonaRefList.setSeparator("");
    }
  }
  
  
  
  /**
   * Filtre totes els usuari-entitat que son revisors de l'entitat
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/usuarientitatrevisor", method = RequestMethod.POST)
  public void usuarientitatrevisor(HttpServletRequest request, HttpServletResponse response
     ) throws Exception {
    
    
    SubQuery<RoleUsuariEntitat, String> subqueryrevisor;
    try {
    subqueryrevisor = roleUsuariEntitatEjb.getSubQuery(RoleUsuariEntitatFields.USUARIENTITATID,
        RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI) );
    } catch (I18NException e) {
      log.error("Error cercant usuaris dins del mètode usuaripersonarevisor(1)", e);
      subqueryrevisor = null;
    }
    
    String queryFull = request.getParameter("query");
    
    final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
 
    Where additionalWhere = Where.AND(
        UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        UsuariEntitatFields.CARREC.isNull(),
        UsuariEntitatFields.ACTIU.equal(true),
        UsuariEntitatFields.USUARIENTITATID.in(subqueryrevisor)
        );
    
    StringField keyField = UsuariEntitatFields.USUARIENTITATID;
    
    String json = genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb,
        additionalWhere, keyField, usuariEntitatRefList);
    
    
    PrintWriter pw= response.getWriter();
    
    pw.write(json);
    pw.flush();
    

    /* XYZ ZZZ
    SubQuery<UsuariEntitat, String> subquery;
    
    try {
      subquery = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIPERSONAID,
          Where.AND(
            UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
            UsuariEntitatFields.CARREC.isNull(),
            UsuariEntitatFields.ACTIU.equal(true),
            UsuariEntitatFields.USUARIENTITATID.in(subqueryrevisor)
            ) 
          );
    } catch (I18NException e) {
      log.error("Error cercant usuaris dins del mètode usuaripersonarevisor(2)", e);
      subquery = null;
    }
    
    Where additionalWhere = UsuariPersonaFields.USUARIPERSONAID.in(subquery);
    
    
    processUsuariPersonaRequest(request, response, additionalWhere);
    */
    
  }
  
  
 
  
  /**
   * Filtre totes les persones de PortaFIB
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/usuaripersona", method = RequestMethod.POST)
  public void usuaripersona(HttpServletRequest request, HttpServletResponse response
     ) throws Exception {

    final Where additionalWhere = null;
    
    processUsuariPersonaRequest(request, response, additionalWhere);
    
  }
  
  
  
  /**
   * Filtre per les persones que estan donades d'alta a l'entitat actual i a més estan actives
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/usuaripersonaentitat", method = RequestMethod.POST)
  public void usuaripersonaentitat(HttpServletRequest request, HttpServletResponse response
     ) throws Exception {

    SubQuery<UsuariEntitat, String> subquery;
    
    try {
      subquery = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIPERSONAID,
          Where.AND(
            UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
            UsuariEntitatFields.CARREC.isNull(),
            UsuariEntitatFields.ACTIU.equal(true)
            ) 
          );
    } catch (I18NException e) {
      log.error("Error cercant usuaris dins del mètode usuaripersonaentitat()", e);
      subquery = null;
    }
    
    Where additionalWhere = UsuariPersonaFields.USUARIPERSONAID.in(subquery);
    
    processUsuariPersonaRequest(request, response, additionalWhere);

  }



  protected void processUsuariPersonaRequest(HttpServletRequest request,
      HttpServletResponse response, Where additionalWhere) throws IOException {
    String queryFull = request.getParameter("query");
    
    final UsuariPersonaQueryPath personaQueryPath = new UsuariPersonaQueryPath();

    StringField keyField = UsuariPersonaFields.USUARIPERSONAID;
    
    //IRefBaseReferenceList refListBase = new RefBaseReferenceListUsuariPersona();
    
    String json = genericSearch(queryFull, personaQueryPath, usuariPersonaLogicaEjb, additionalWhere, keyField,
        usuariPersonaRefList);
    
    
    PrintWriter pw= response.getWriter();
    
    pw.write(json);
    pw.flush();
  }
  
  
  
  /**
   * Filtre usuaris-entitat de l'entitat actual.
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/usuarientitat", method = RequestMethod.POST)
  public void usuarientitat(HttpServletRequest request, HttpServletResponse response
     ) throws Exception {

    
    String queryFull = request.getParameter("query");
    
    final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();
 
    Where additionalWhere = Where.AND(
        UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        UsuariEntitatFields.CARREC.isNull(),
        UsuariEntitatFields.ACTIU.equal(true)
        );
    
    StringField keyField = UsuariEntitatFields.USUARIENTITATID;
    
    String json = genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb,
        additionalWhere, keyField, usuariEntitatRefList);
    
    
    PrintWriter pw= response.getWriter();
    
    pw.write(json);
    pw.flush();
    
  }
  
  
  /**
   * Filtre usuaris-entitat de l'entitat actual.
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value = "/usuarientitatcarrec", method = RequestMethod.POST)
  public void usuarientitatcarrec(HttpServletRequest request, HttpServletResponse response
     ) throws Exception {

    
    String queryFull = request.getParameter("query");

 
    final List<String> aEliminar = new ArrayList<String>();
    
    String json = generaLlistatUsuarisCarrecsJson(queryFull,
         aEliminar, usuariEntitatLogicaEjb,
        usuariEntitatRefList, carrecRefList);
    
    
    PrintWriter pw= response.getWriter();
    
    pw.write(json);
    pw.flush();
    
  }
  


  
  public static  String generaLlistatUsuarisCarrecsJson(String queryFull,
      List<String> aEliminar, UsuariEntitatLogicaLocal usuariEntitatLogicaEjb,
      RefListBase usuariEntitatRefList, RefListBase carrecRefList)
      throws IOException {
    String json;
    final boolean isDebug = log.isDebugEnabled();
    if (queryFull == null) {
      json = "[]";
    } else {
    
      Where whereBase = Where.AND(     
          UsuariEntitatFields.USUARIENTITATID.notIn(aEliminar),
          UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
          UsuariEntitatFields.ACTIU.equal(true));

      final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();

      StringField keyField = UsuariEntitatFields.USUARIENTITATID;
   
      Where additionalWhereUsuariEntitat = Where.AND(
          whereBase,
          UsuariEntitatFields.CARREC.isNull()

          );
      Where additionalWhereCarrec = Where.AND(
          whereBase,
          UsuariEntitatFields.CARREC.isNotNull()
          );

      String jsonUsuariEntitat = SearchJSONController.genericSearch(queryFull, 
          personaQueryPath, usuariEntitatLogicaEjb,
          additionalWhereUsuariEntitat, keyField, usuariEntitatRefList);

      String jsonCarrec = SearchJSONController.genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb,
          additionalWhereCarrec, keyField, carrecRefList, UsuariEntitatFields.CARREC);
      
      if (isDebug) {
        log.debug("jsonUsuariEntitat = |" + jsonUsuariEntitat + "|");
      }
      
      
      if (jsonUsuariEntitat.trim().equals("[]")) {
        jsonUsuariEntitat = "";
      } else {
        // Llevam corchetes
        jsonUsuariEntitat = jsonUsuariEntitat.substring(1, jsonUsuariEntitat.length() - 1).trim();
      }
      
      if (isDebug) {
        log.debug("jsonCarrec = |" + jsonCarrec + "|");
      }
      if (jsonCarrec.trim().equals("[]")) {
        jsonCarrec = "";
      } else {
        // Llevam corchetes
        jsonCarrec = jsonCarrec.substring(1, jsonCarrec.length() - 1).trim();
      }
  
      
      if (jsonCarrec.length() != 0 && jsonUsuariEntitat.length() != 0) {
        json = "[" + jsonUsuariEntitat + ", " + jsonCarrec + "]";
      } else if (jsonCarrec.length() == 0) {
        json = "[" + jsonUsuariEntitat + "]";
      } else {
        json = "[" + jsonCarrec + "]";
      }
    }
    if (isDebug) {
      log.debug("  json = |" + json + "|");
    }
    return json;
  }
  
  


  public static String genericSearch(String queryFull,
      final UsuariPersonaQueryPath personaQueryPath,
      org.fundaciobit.genapp.common.query.ITableManager<?,?> uem, Where additionalWhere,
      StringField keyField, RefListBase refListBase) throws IOException {
    
    return genericSearch(queryFull, personaQueryPath, uem,  additionalWhere,
         keyField,  refListBase,  null);
      
}
  
  
  
  public static String genericSearch(String queryFull,
      final UsuariPersonaQueryPath personaQueryPath,
      org.fundaciobit.genapp.common.query.ITableManager<?,?> uem, Where additionalWhere,
      StringField keyField, RefListBase refListBase, Field<String> fieldOR) throws IOException {
    
      final boolean isDebug = log.isDebugEnabled();

      if (isDebug) {
        log.debug("JSON CRIDADA FULL ]" + queryFull  + "[");
      }
      
      // Bug en MOZILLA FIREFOX (Fa una segonca cridada buida
      if (queryFull == null || queryFull.trim().length() == 0) {
        return "[]";
      }
      

      final OrderBy orderBy = new OrderBy(personaQueryPath.LLINATGES());

      List<Where> wheres = new ArrayList<Where>();
      for(String query: queryFull.split(" ")) {
      
        final String like = "%" + query + "%";
        final Where whereQuery = Where.OR( 
            personaQueryPath.NOM().like(like),
            personaQueryPath.LLINATGES().like(like),
            personaQueryPath.NIF().like(like),
            personaQueryPath.USUARIPERSONAID().like(like),
            (fieldOR == null)? null : fieldOR.like(like)
            );

        wheres.add(whereQuery);
      }
      
      Where whereQueryFull = Where.AND(wheres.toArray(new Where[wheres.size()]));
      
      final Where where = Where.AND(
          additionalWhere,
          whereQueryFull
          );
  
      String entitatID = null;
      if (LoginInfo.getInstance() != null) {
        entitatID = LoginInfo.getInstance().getEntitatID();
      }
  
      final long max = PropietatGlobalUtil.getMaxItemsToShowInAutocomplete(entitatID);
      
      List<StringKeyValue> values;
      try {
        Long count = uem.count(where);
        if (isDebug) {
          log.debug("RESULTATS.FOUND = " + count + " (max = " + max + ")");
        }
        if (count > max) {
          if (isDebug) {
            log.debug("S'han trobat " + count 
              + " usuaris però només es permet mostrar amb resultats menors que " + max);
          }
          values = new ArrayList<StringKeyValue>();
        } else { 
          values = refListBase.getReferenceList(keyField, where, orderBy);
        }
      } catch (I18NException e) {
        log.error(I18NUtils.getMessage(e), e);
        values = new ArrayList<StringKeyValue>();
      }
  
      
      if (isDebug) {
        log.debug("RESULTATS.RETURN = " + values.size());
        log.debug(" ========================================== ");
      }
      
      
  
      //usuariEntitatLogicaEjb.s
      StringBuffer str = new StringBuffer();
      str.append('[');
  
      for (int i = 0; i < values.size(); i++) {
        StringKeyValue skv = values.get(i);
  
        if (i != 0) {
          str.append(',');
        }
        str.append("{\"Id\":\"" + escapeJSON(skv.getKey()) + "\",\"Name\":\"" + escapeJSON(skv.getValue()) + "\"}");
        
        if (isDebug) {
          log.debug(" RESULTAT[" + i 
            + "] = {\"Id\":\"" + skv.getKey() + "\",\"Name\":\"" + skv.getValue() + "\"}");
        }
        
      }
  
      
      str.append(" ]");
    
     return str.toString();
    
  }




  
  
  public static String escapeJSON(String txt) {
    
    return txt.replace("\\","\\\\").replace("\"", "\\\"");
    // \"  Double quote
    //\\  Backslash caracte
  }
  
  
  /*
  public static interface IRefBaseReferenceList {
    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException;
  }

  
  public static class RefBaseReferenceListUsuariEntitat implements IRefBaseReferenceList {
    
    final RefListBase usuariEntitatRefList;

    public RefBaseReferenceListUsuariEntitat(RefListBase usuariEntitatRefList) {
      super();
      this.usuariEntitatRefList = usuariEntitatRefList;
    }

    @Override
    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where,
        OrderBy... orderBy) throws I18NException {
      return this.usuariEntitatRefList.getReferenceList(keyField, where, orderBy);
    }

  }
  
  
  public static class RefBaseReferenceListUsuariPersona implements IRefBaseReferenceList {
    
    final RefListBase usuariPersonaRefList;

    public RefBaseReferenceListUsuariPersona(RefListBase usuariPersonaRefList) {
      super();
      this.usuariPersonaRefList = usuariPersonaRefList;
    }

    @Override
    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where,
        OrderBy... orderBy) throws I18NException {
      return this.usuariPersonaRefList.getReferenceList(keyField, where, orderBy);
    }

  }
  */
  
  
  public static List<StringKeyValue> favoritsToUsuariPersona(List<UsuariEntitatJPA> list) {
    if (list == null) return null;

    List<StringKeyValue> listSKV = new ArrayList<StringKeyValue>(list.size());
    
    for (UsuariEntitatJPA favorit : list) {
      UsuariPersonaJPA up = favorit.getUsuariPersona();
      String value = usuariPersonaToString(up);
      
      listSKV.add(new StringKeyValue(up.getUsuariPersonaID(), value));
    }
    
    return listSKV;
  }
  
  
  public static List<StringKeyValue> favoritsToUsuariEntitat(List<UsuariEntitatJPA> list) {
    if (list == null) return null;

    List<StringKeyValue> listSKV = new ArrayList<StringKeyValue>(list.size());
    
    for (UsuariEntitatJPA favorit : list) {
      
      StringKeyValue skv = getStringKeyValueFromUsuariEntitat(favorit);
      listSKV.add(skv);
    }
    
    return listSKV;
  }



  public static StringKeyValue getStringKeyValueFromUsuariEntitat(UsuariEntitatJPA favorit) {
    String carrec = favorit.getCarrec();
    UsuariPersonaJPA up = favorit.getUsuariPersona();
    String value;
    if (carrec == null) {
      value = usuariPersonaToString(up);
    } else {
      value = usuariPersonaCarrecToString(carrec,up); 
    }
    StringKeyValue skv= new StringKeyValue(favorit.getUsuariEntitatID(), value);
    return skv;
  }
  
  


  protected static String usuariPersonaToString(UsuariPersonaJPA up) {
    String tmp = up.getLlinatges() + ", " + up.getNom() + " (" + up.getNif() 
        + " - " + up.getUsuariPersonaID() + ")";
    
    return tmp.replace('"', '\'');
  }
  
  protected static String usuariPersonaCarrecToString(String carrec, UsuariPersonaJPA up) {
    String tmp = "(*) " + carrec + " (" + up.getNom() + " " + up.getLlinatges() + " - " + up.getNif() 
        + " - " + up.getUsuariPersonaID() + ")";
    
    return tmp.replace('"', '\'');
  }
  


}
