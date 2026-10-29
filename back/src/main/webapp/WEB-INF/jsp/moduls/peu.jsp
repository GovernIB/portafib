<%@page import="es.caib.portafib.commons.utils.Configuracio"%><%@page import="es.caib.portafib.commons.utils.Version"%><%@ page
    import="es.caib.portafib.logic.utils.LogicUtils"%><%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%
Version version = new Version();
%>
<footer id="footer">
    <div class="row mr-auto ml-3 mr-3 peuResponsive">
        <!-- Esquerra -->
        <div class="col-4 pt-2 elementPeuResponsive">

            <p title="<fmt:message key="app.nom" /> v<%=version.getVersion() + (Configuracio.isCAIB() ? "-caib" : "")%>"
                style="margin-bottom: 0px">
                <b> <fmt:message key="app.nom" /> v<%=version.getVersion()%>
                </b>
            </p>
            <small> <sec:authorize access="hasAnyRole('ROLE_ADEN','ROLE_ADMIN')"> 
				Build: <%=version.getBuildTime()%>
                    <br /> 
	     		JDK: <%=version.getJdkVersion()%>
                    <br />
                Versió de JBoss Compatible: <%=version.getJBossCompliant()%><br />
                Versió de JBoss Executant-se: ${jboss_version}<br />
                    <fmt:message key="revisio" />: 
				<%
                    if (version.getScmRevision() == null) {
                    %>
                    <fmt:message key="scmversion.msg" />
                    <%
                    } else {
                    %>
                    <%=version.getScmRevision()%>
                    <%
                    }
                    %>
                    <br />
                </sec:authorize> <span style="padding-top: 2px"> <i><fmt:message key="desenvolupatper" /></i>
            </span>
            </small>

        </div>



        <!-- Centre esquerra -->
        <div class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionsPeu elementPeuResponsive">
            <c:if test="${not empty loginInfo.entitatID}">
                <c:out value="${loginInfo.entitat.adrezaHtml}" escapeXml="false" />
            </c:if>
            
            <p>&nbsp;</p>
            
            <a styleClass="text-dark linkPeu" href="<c:url value="/public/accessibilitat"/>"><fmt:message key="label.accessibilitat" /></a>
        </div>


        <!-- Dreta -->
        <div class="col-4  logoPeu pt-2" style="text-align: right;">
            <c:if test="${not empty loginInfo.entitatID}">
                <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
                    <img src="<c:url value="${pfi:fileUrlPublic(loginInfo.entitat.logoWebPeu)}"/>" alt="${loginInfo.entitat.nom }" />
                </a>
                <br>
                <!-- Button to trigger modal -->
                <small><a href="#modalAjuda" role="button" data-toggle="modal">
                        <fmt:message key="ajuda.necessitau" />
                    </a></small>

            </c:if>
        </div>


        <c:if test="${not empty loginInfo.entitatID}">

            <!-- Modal -->
            <div id="modalAjuda" class="modal" tabindex="-1" role="dialog" />"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            
                            <div class="modal-title h5">
                                <fmt:message key="ajuda.titol" />
                            </div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>
                                <fmt:message key="ajuda.missatge" />
                            </p>
                            <ul>
                                <c:if test="${not empty loginInfo.entitat.suportTelefon }">
                                    <li><fmt:message key="ajuda.viatelefon" /> ${loginInfo.entitat.suportTelefon}</li>
                                </c:if>

                                <c:if test="${not empty loginInfo.entitat.suportWeb }">
                                    <li><fmt:message key="ajuda.viaweb" /> <a target="_blank" href="${loginInfo.entitat.suportWeb}">${loginInfo.entitat.suportWeb}
                                        </a></li>
                                </c:if>

                                <c:if test="${not empty loginInfo.entitat.suportEmail }">
                                    <li><fmt:message key="ajuda.viaemail" />
                                        <a href="mailto: ${loginInfo.entitat.suportEmail}"> ${loginInfo.entitat.suportEmail}</a></li>
                                </c:if>
                            </ul>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="tancar" />
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </c:if>
    </div>
</footer>