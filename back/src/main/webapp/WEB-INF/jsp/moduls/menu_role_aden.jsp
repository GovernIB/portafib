<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%><%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
    <%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<sec:authorize access="hasRole('ROLE_ADEN')">
    <div>
        <h5>
            <fmt:message key="ROLE_ADEN.menu" />
        </h5>
        <%!private static final List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();

    static {

        List<MenuItem> menu1;

        menu1 = new ArrayList<MenuItem>();
        menu1.add(MenuItem.retallaDarrerPath("entitat.modificar", "/aden/entitat/current"));
        menu1.add(MenuItem.retallaDarrerPath("propietatglobal.entitat.gestio", "/aden/propietatglobal/list"));
        menu1.add(MenuItem.retallaDarrerPath("tipusdocument.aden.plural", "/aden/gestiotipusdocentitat/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("usuaripersona.alta", "/aden/usuariPersona/alta"));
        menu1.add(MenuItem.retallaDarrerPath("usuaripersona.modificar", "/aden/usuariPersona/modificar"));
        menu1.add(MenuItem.retallaDarrerPath("usuarientitat.gestio", "/aden/usuariEntitat/selecciousuari"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("carrec.gestio", "/aden/carrec/list"));
        menu1.add(MenuItem.retallaDarrerPath("colaboradordecarrec.plural", "/aden/colaboradordecarrec/list"));
        menu1.add(MenuItem.retallaDarrerPath("solicitant.gestio", "/aden/solicitant/selecciousuari"));
        menu1.add(MenuItem.retallaDarrerPath("revisor.gestio", "/aden/revisor/selecciousuari"));
        menu1.add(MenuItem.retallaDarrerPath("grups.gestio", "/aden/grup/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("moduldefirmaenservidor.gestio", "/aden/moduldefirmaenservidor/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("moduldefirma.gestio", "/aden/modulDeFirma/list"));
        menu1.add(MenuItem.retallaDarrerPath("modulDeFirmaPerTipusDeDocument.short", "/aden/modulfirmatipusdoc/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("segelldetemps.gestio", "/aden/segelldetemps/list"));
        menu1.add(MenuItem.retallaDarrerPath("plantillacustodia.gestio", "/aden/plantillacustodia/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("validaciodefirmes.gestio", "/aden/validaciofirmes/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.totes.consultar.llistar",
                "/aden/peticiofirmatotesconsultar/list"));
        menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.totes.gestionar.llistar",
                "/aden/peticiofirmatotesgestionar/list"));

        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("peticionscaducades.llistat", "/aden/peticionscaducades/list"));
        menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.netejaesborrat", "/aden/peticio/netejaesborrat/list"));

        menu1.add(MenuItem.retallaDarrerPath("peticionsdefirma.destinatari",
                "/aden/peticionsdedestinatari/selecciousuari"));
        menu1.add(MenuItem.retallaDarrerPath("firmausuariextern.plural", "/aden/firmausuariextern/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("estadistica.estadistica.plural", "/aden/estadistica/search"));
        menu1.add(MenuItem.retallaDarrerPath("bitacola.menu", "/aden/bitacola/list"));
        menu1.add(null);
        menu1.add(MenuItem.retallaDarrerPath("usuarientitat.senserols.plural", "/aden/usuarientitatsenserols/list"));

        menus.add(menu1);

    }%>
     <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

    </div>
</sec:authorize>
