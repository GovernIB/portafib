package es.caib.portafib.back.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.ejb.RoleUsuariEntitatService;
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
import es.caib.portafib.back.utils.ObfuscatedNifStringField;
import es.caib.portafib.back.utils.ObfuscatedUsernameStringField;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.bean.UsuariPersonaBean;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariAplicacio;
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

    private static final String RECOLLECT_ALL_REVISORS = "#####__ALL__#####";

    protected static final Logger log = Logger.getLogger(SearchJSONController.class);

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
    protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

    @EJB(mappedName = RoleUsuariEntitatService.JNDI_NAME)
    protected RoleUsuariEntitatService roleUsuariEntitatEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

    //@EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    //protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

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

            this.usuariPersonaRefList.setSelects(new Select<?>[] { UsuariPersonaFields.LLINATGES.select,
                    new SelectConstant(", "), UsuariPersonaFields.NOM.select, new SelectConstant(" ("),
                    new ObfuscatedNifStringField(UsuariPersonaFields.NIF).select, new SelectConstant(" - "),
                    new ObfuscatedUsernameStringField(UsuariPersonaFields.USUARIPERSONAID).select,
                    new SelectConstant(")") });

            this.usuariPersonaRefList.setSeparator("");
        }
    }

    
    
    
    
    /**
     * Filtre totes els usuari-entitat que son revisors de l'entitat
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuarientitatrevisorall", method = RequestMethod.POST)
    public void usuarientitatrevisorall(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final String queryFull = RECOLLECT_ALL_REVISORS;
        usuariEntitatRevisorInternal(request, response, queryFull);
        
    }
    
    
    
    /**
     * Filtre totes els usuari-entitat que son revisors de l'entitat
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuarientitatrevisor", method = RequestMethod.POST)
    public void usuariEntitatRevisor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String queryFull = request.getParameter("query");
        usuariEntitatRevisorInternal(request, response, queryFull);
        
    }

        
        
    
   private void usuariEntitatRevisorInternal(HttpServletRequest request, HttpServletResponse response, final String queryFull) throws Exception {

        // Quins revisors volem ???? 
        //  true: revisors globals
        //  false: revisors de destinatari
        //  null: revisors globals i revisors de destinatari
        Boolean quinsRevisors = null;
        try {

            LoginInfo loginInfo = LoginInfo.getInstance();

            UsuariAplicacio usuariAplicacio = loginInfo.getUsuariAplicacio();

            //log.info("usuarientitatrevisor() -> LoginInfo.usuariAplicacio: " + usuariAplicacio);

            if (usuariAplicacio == null) {

                // Accés des del BACK
                usuariAplicacio = loginInfo.getEntitat().getUsuariAplicacio();

                if (usuariAplicacio == null) {
                    log.error(
                            "usuarientitatrevisor() :: L'entitat " + loginInfo.getEntitatID()
                                    + " no té cap usuari aplicació associat !!!! ",
                            new Exception("No es pot obtenir getTipusRevisors()"));
                } else {
                    //usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(usuariAplicacioID);
                    quinsRevisors = usuariAplicacio.getTipusRevisors();
                    //log.info("usuarientitatrevisor() -> loginInfo.getEntitat().getUsuariAplicacio() => usr:" + usuariAplicacio + " ");
                }
            } else {
                // Accés des de l'API de FLux
                //log.info("usuarientitatrevisor() -> LoginINfo.usuariAplicacio: (XYZ  " + usuariAplicacio);
                quinsRevisors = usuariAplicacio.getTipusRevisors();
            }

            //log.info("usuarientitatrevisor() -> quinsRevisors: " + quinsRevisors);

        } catch (Exception e) {
            log.error("usuarientitatrevisor():: Error intentant obtenir informació de "
                    + "l'Usuari Aplicació per coneixer quinsRevisors: " + e.getMessage(), e);
        }

        
        List<StringKeyValue> entries = new ArrayList<StringKeyValue>();

        // -----------------------------------------
        // Revisors Globals

        if (quinsRevisors == null || quinsRevisors.booleanValue() == true) {

            SubQuery<RoleUsuariEntitat, String> subqueryrevisor;
            try {
                subqueryrevisor = roleUsuariEntitatEjb.getSubQuery(RoleUsuariEntitatFields.USUARIENTITATID,
                        RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI));
            } catch (I18NException e) {
                log.error("Error cercant usuaris dins del mètode usuaripersonarevisor(1)", e);
                subqueryrevisor = null;
            }
            final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();

            Where additionalWhere = Where.AND(
                    UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                    UsuariEntitatFields.CARREC.isNull(), UsuariEntitatFields.ACTIU.equal(true),
                    UsuariEntitatFields.USUARIENTITATID.in(subqueryrevisor));

            StringField keyField = UsuariEntitatFields.USUARIENTITATID;

            entries.addAll(genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb, additionalWhere, keyField,
                    usuariEntitatRefList));
        }

        // -----------------------------------
        // Revisors del destinatari
        if (quinsRevisors == null || quinsRevisors.booleanValue() == false) {

            String usuariEntitatID = request.getParameter("param2");

            //log.info("\n\n\nusuariEntitatRevisorInternal() -> Destinatari usuariEntitatID[param2]: " + usuariEntitatID + "\n\n\n");

            if (usuariEntitatID != null && usuariEntitatID.trim().length() != 0) {

                // Cridada a EJB
                List<UsuariPersonaBean> persones;
                persones = revisorDeDestinatariEjb.getRevisorsDeDestinatariUsingUsuariEntitatID(usuariEntitatID,
                        RECOLLECT_ALL_REVISORS.equals(queryFull)?null:queryFull);
                // Contrasenya conté l'usuarientitatid
                for (UsuariPersonaBean usuariPersonaBean : persones) {
                    final String label = usuariPersonaBean.getLlinatges() + ", " + usuariPersonaBean.getNom() + " ("
                            + Utils.ofuscarDNI(usuariPersonaBean.getNif()) + " - "
                            + Utils.ofuscarUserName(usuariPersonaBean.getUsuariPersonaID()) + ")";
                    entries.add(new StringKeyValue(usuariPersonaBean.getContrasenya(), label));
                }
            }
        }

        // Convertir a JSON
        String json = stringKeyValueList2Json(entries);
        
        response.setContentType("application/json");

        PrintWriter pw = response.getWriter();

        pw.write(json);
        pw.flush();

    }

    /**
     * Filtre totes les persones de PortaFIB
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuaripersona", method = RequestMethod.POST)
    public void usuaripersona(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final Where additionalWhere = null;

        processUsuariPersonaRequest(request, response, additionalWhere);

    }

    /**
     * Filtre totes les persones de PortaFIB de tipus intern
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuaripersonaintern", method = RequestMethod.POST)
    public void usuaripersonaintern(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final Where additionalWhere = UsuariPersonaFields.USUARIINTERN.equal(true);

        processUsuariPersonaRequest(request, response, additionalWhere);

    }

    /**
     * Filtre per les persones que estan donades d'alta a l'entitat actual i a més estan actives
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuaripersonaentitat", method = RequestMethod.POST)
    public void usuaripersonaentitat(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Tots
        final Boolean isIntern = null;

        usuaripersonaentitatCommon(request, response, isIntern);

    }

    @RequestMapping(value = "/usuaripersonaentitatintern", method = RequestMethod.POST)
    public void usuaripersonaentitatintern(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Només intern     
        final Boolean isIntern = true;

        usuaripersonaentitatCommon(request, response, isIntern);

    }

    protected void usuaripersonaentitatCommon(HttpServletRequest request, HttpServletResponse response,
            Boolean isIntern) throws IOException {
        SubQuery<UsuariEntitat, String> subquery;

        try {
            Where whereBase = Where.AND(UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                    UsuariEntitatFields.CARREC.isNull(), UsuariEntitatFields.ACTIU.equal(true));

            Where byTipusUsuari;

            if (isIntern == null) {
                byTipusUsuari = whereBase;
            } else if (isIntern == true) {
                byTipusUsuari = Where.AND(whereBase,
                        new UsuariEntitatQueryPath().USUARIPERSONA().USUARIINTERN().equal(true));
            } else {
                byTipusUsuari = Where.AND(whereBase,
                        new UsuariEntitatQueryPath().USUARIPERSONA().USUARIINTERN().equal(false));
            }

            subquery = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIPERSONAID, byTipusUsuari);
        } catch (I18NException e) {
            log.error("Error cercant usuaris dins del mètode usuaripersonaentitat()", e);
            subquery = null;
        }

        Where additionalWhere = UsuariPersonaFields.USUARIPERSONAID.in(subquery);

        processUsuariPersonaRequest(request, response, additionalWhere);
    }

    protected void processUsuariPersonaRequest(HttpServletRequest request, HttpServletResponse response,
            Where additionalWhere) throws IOException {
        String queryFull = request.getParameter("query");

        final UsuariPersonaQueryPath personaQueryPath = new UsuariPersonaQueryPath();

        StringField keyField = UsuariPersonaFields.USUARIPERSONAID;

        //IRefBaseReferenceList refListBase = new RefBaseReferenceListUsuariPersona();

        String json = genericSearchJson(queryFull, personaQueryPath, usuariPersonaLogicaEjb, additionalWhere, keyField,
                usuariPersonaRefList);

        PrintWriter pw = response.getWriter();

        pw.write(json);
        pw.flush();
    }

    /**
     * Filtre usuaris-entitat intern de l'entitat actual. Exclou els usuaris-externs
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/usuarientitatintern", method = RequestMethod.POST)
    public void usuarientitatintern(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String queryFull = request.getParameter("query");

        final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();

        Where additionalWhere = Where.AND(new UsuariEntitatQueryPath().USUARIPERSONA().USUARIINTERN().equal(true),
                UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                UsuariEntitatFields.CARREC.isNull(), UsuariEntitatFields.ACTIU.equal(true));

        StringField keyField = UsuariEntitatFields.USUARIENTITATID;

        String json = genericSearchJson(queryFull, personaQueryPath, usuariEntitatLogicaEjb, additionalWhere, keyField,
                usuariEntitatRefList);

        PrintWriter pw = response.getWriter();

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
    public void usuarientitat(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String queryFull = request.getParameter("query");

        final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();

        Where additionalWhere = Where.AND(UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                UsuariEntitatFields.CARREC.isNull(), UsuariEntitatFields.ACTIU.equal(true));

        StringField keyField = UsuariEntitatFields.USUARIENTITATID;

        String json = genericSearchJson(queryFull, personaQueryPath, usuariEntitatLogicaEjb, additionalWhere, keyField,
                usuariEntitatRefList);

        PrintWriter pw = response.getWriter();

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
    public void usuarientitatcarrec(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String queryFull = request.getParameter("query");

        final List<String> aEliminar = new ArrayList<String>();

        String json = generaLlistatUsuarisCarrecsJson(queryFull, aEliminar, usuariEntitatLogicaEjb,
                usuariEntitatRefList, carrecRefList);

        PrintWriter pw = response.getWriter();

        pw.write(json);
        pw.flush();

    }

    public static String generaLlistatUsuarisCarrecsJson(String queryFull, List<String> aEliminar,
            UsuariEntitatLogicaLocal usuariEntitatLogicaEjb, RefListBase usuariEntitatRefList,
            RefListBase carrecRefList) throws IOException {
        String json;
        final boolean isDebug = log.isDebugEnabled();
        if (queryFull == null) {
            json = "[]";
        } else {

            Where whereBase = Where.AND(UsuariEntitatFields.USUARIENTITATID.notIn(aEliminar),
                    UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                    UsuariEntitatFields.ACTIU.equal(true));

            final UsuariPersonaQueryPath personaQueryPath = new UsuariEntitatQueryPath().USUARIPERSONA();

            StringField keyField = UsuariEntitatFields.USUARIENTITATID;

            Where additionalWhereUsuariEntitat = Where.AND(whereBase, UsuariEntitatFields.CARREC.isNull()

            );
            Where additionalWhereCarrec = Where.AND(whereBase, UsuariEntitatFields.CARREC.isNotNull());

            List<StringKeyValue> jsonUsuariEntitat = genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb,
                    additionalWhereUsuariEntitat, keyField, usuariEntitatRefList);

            List<StringKeyValue> jsonCarrec = genericSearch(queryFull, personaQueryPath, usuariEntitatLogicaEjb,
                    additionalWhereCarrec, keyField, carrecRefList, UsuariEntitatFields.CARREC);

            // Ajuntam les llistes

            List<StringKeyValue> total = new ArrayList<StringKeyValue>();
            total.addAll(jsonUsuariEntitat);
            total.addAll(jsonCarrec);

            json = stringKeyValueList2Json(total);

        }
        if (isDebug) {
            log.debug("  json = |" + json + "|");
        }
        return json;
    }

    public static String genericSearchJson(String queryFull, final UsuariPersonaQueryPath personaQueryPath,
            org.fundaciobit.genapp.common.query.ITableManager<?, ?> uem, Where additionalWhere, StringField keyField,
            RefListBase refListBase) throws IOException {

        return genericSearchJson(queryFull, personaQueryPath, uem, additionalWhere, keyField, refListBase, null);

    }

    public static List<StringKeyValue> genericSearch(String queryFull, final UsuariPersonaQueryPath personaQueryPath,
            org.fundaciobit.genapp.common.query.ITableManager<?, ?> uem, Where additionalWhere, StringField keyField,
            RefListBase refListBase) throws IOException {

        return genericSearch(queryFull, personaQueryPath, uem, additionalWhere, keyField, refListBase, null);

    }

    public static String genericSearchJson(String queryFull, final UsuariPersonaQueryPath personaQueryPath,
            org.fundaciobit.genapp.common.query.ITableManager<?, ?> uem, Where additionalWhere, StringField keyField,
            RefListBase refListBase, Field<String> fieldOR) throws IOException {

        List<StringKeyValue> values = genericSearch(queryFull, personaQueryPath, uem, additionalWhere, keyField,
                refListBase, fieldOR);

        String str = stringKeyValueList2Json(values);

        return str;

    }

    public static List<StringKeyValue> genericSearch(String queryFull, final UsuariPersonaQueryPath personaQueryPath,
            org.fundaciobit.genapp.common.query.ITableManager<?, ?> uem, Where additionalWhere, StringField keyField,
            RefListBase refListBase, Field<String> fieldOR) throws IOException {

        final boolean isDebug = log.isDebugEnabled();

        if (isDebug) {
            log.debug("JSON CRIDADA FULL ]" + queryFull + "[");
        }

        List<StringKeyValue> values = new ArrayList<StringKeyValue>();
        
        final OrderBy orderBy = new OrderBy(personaQueryPath.LLINATGES());

        // Bug en MOZILLA FIREFOX (Fa una segona cridada buida
        if (queryFull == null || queryFull.trim().length() == 0) {
            return values;
        }

        boolean recollectAll = RECOLLECT_ALL_REVISORS.equals(queryFull);
        
        final Where where;
        if (recollectAll) {
            where = additionalWhere;
        } else {
            List<Where> wheres = new ArrayList<Where>();
            for (String query : queryFull.split(" ")) {
    
                final String like = "%" + query + "%";
                final Where whereQuery = Where.OR(personaQueryPath.NOM().likeSubstitutionsSimpleVowels(like),
                        personaQueryPath.LLINATGES().likeSubstitutionsSimpleVowels(like), personaQueryPath.NIF().like(like),
                        personaQueryPath.USUARIPERSONAID().like(like), (fieldOR == null) ? null : fieldOR.like(like));
    
                wheres.add(whereQuery);
            }
    
            Where whereQueryFull = Where.AND(wheres.toArray(new Where[wheres.size()]));
    
            where = Where.AND(additionalWhere, whereQueryFull);
        }

        String entitatID = null;
        if (LoginInfo.getInstance() != null) {
            entitatID = LoginInfo.getInstance().getEntitatID();
        }

        final long max = recollectAll? Long.MAX_VALUE:PropietatGlobalUtil.getMaxItemsToShowInAutocomplete(entitatID);

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
        }

        if (isDebug) {
            log.debug("RESULTATS.RETURN = " + values.size());
            log.debug(" ========================================== ");
        }

        return values;

    }

    protected static String stringKeyValueList2Json(List<StringKeyValue> values) {
        //usuariEntitatLogicaEjb.s
        StringBuffer str = new StringBuffer();
        str.append('[');

        for (int i = 0; i < values.size(); i++) {
            StringKeyValue skv = values.get(i);

            if (i != 0) {
                str.append(',');
            }
            str.append("{\"Id\":\"" + escapeJSON(skv.getKey()) + "\",\"Name\":\"" + escapeJSON(skv.getValue()) + "\"}");

        }

        str.append(" ]");
        return str.toString();
    }

    public static String escapeJSON(String txt) {

        return txt.replace("\\", "\\\\").replace("\"", "\\\"");
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
        if (list == null)
            return null;

        List<StringKeyValue> listSKV = new ArrayList<StringKeyValue>(list.size());

        for (UsuariEntitatJPA favorit : list) {
            UsuariPersonaJPA up = favorit.getUsuariPersona();
            String value = usuariPersonaToString(up);

            listSKV.add(new StringKeyValue(up.getUsuariPersonaID(), value));
        }

        return listSKV;
    }

    public static List<StringKeyValue> favoritsToUsuariEntitat(List<UsuariEntitatJPA> list) {
        if (list == null)
            return null;

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
            value = usuariPersonaCarrecToString(carrec, up);
        }
        StringKeyValue skv = new StringKeyValue(favorit.getUsuariEntitatID(), value);
        return skv;
    }

    protected static String usuariPersonaToString(UsuariPersonaJPA up) {
        String tmp = up.getLlinatges() + ", " + up.getNom() + " (" + Utils.ofuscarDNI(up.getNif()) + " - "
                + Utils.ofuscarUserName(up.getUsuariPersonaID()) + ")";

        return tmp.replace('"', '\'');
    }

    protected static String usuariPersonaCarrecToString(String carrec, UsuariPersonaJPA up) {
        String tmp = "(*) " + carrec + " (" + up.getNom() + " " + up.getLlinatges() + " - "
                + Utils.ofuscarDNI(up.getNif()) + " - " + Utils.ofuscarUserName(up.getUsuariPersonaID()) + ")";

        return tmp.replace('"', '\'');
    }

}
