
<%@page import="es.caib.portafib.commons.utils.Version"%>
<%@ page import="es.caib.portafib.logic.utils.LogicUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%
   Version version = new Version();
%>
<footer id="footer">
	<div class="row mr-auto ml-3 mr-3 peuResponsive">
		<!-- Esquerra -->
		<div class="col-4 pt-2 elementPeuResponsive">

			<b>
				<fmt:message key="app.nom" /> v<%=version.getVersion()%>
			</b>
			<br /> 

			<small> 
				Build: <%=version.getBuildTime()%> <br /> 
	     		JDK: <%=version.getJdkVersion()%> <br />
                Versió de JBoss Compatible: <%=version.getJBossCompliant() %><br />
                Versió de JBoss Executant-se: ${jboss_version}<br />
 				<fmt:message key="revisio" />: 
				<% if (version.getScmRevision() == null) { %>
				   <fmt:message key="scmversion.msg" />
				<% } else { %>
					<%=version.getScmRevision()%>
				<% } %>
				<br/> 
	
				<span style="padding-top: 2px"> 
					<i><fmt:message key="desenvolupatper" /></i>
				</span>
			</small>




			<%-- 		
			
				<b title="Build: <%=LogicUtils.getBuild()%>"><fmt:message
					key="app.nom" /> v<%=LogicUtils.getVersio()%></b><br /> <i><a
				href="http://blog.fundaciobit.org/category/admindigital/"
				target="_blank"><fmt:message key="desenvolupatper" /></a></i><br />

			<!-- Button to trigger modal -->
			<c:if test="${not empty loginInfo.entitatID}">
				<small><a href="#modalAjuda" role="button"
					data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
			</c:if>
 --%>


		</div>



		<!-- Centre esquerra -->
		<div
			class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionsPeu elementPeuResponsive">
			<c:if test="${not empty loginInfo.entitatID}">
				<c:out value="${loginInfo.entitat.adrezaHtml}" escapeXml="false" />
			</c:if>
		</div>


		<!-- Dreta -->
		<div class="col-4  logoPeu pt-2" style="text-align: right;">
			<c:if test="${not empty loginInfo.entitatID}">
				<a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
					<img
					src="<c:url value="${pfi:fileUrl(loginInfo.entitat.logoWebPeu)}"/>"
					alt="${loginInfo.entitat.nom }" />
				</a>
			</c:if>
		</div>


		<c:if test="${not empty loginInfo.entitatID}">

			<!-- Modal -->
			<div id="modalAjuda" class="modal hide fade" tabindex="-1"
				role="dialog" aria-labelledby="<fmt:message key="ajuda.titol" />"
				aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">
						<fmt:message key="ajuda.titol" />
					</h3>
				</div>
				<div class="modal-body">
					<p>
						<fmt:message key="ajuda.missatge" />
					</p>
					<ul>
						<c:if test="${not empty loginInfo.entitat.suportTelefon }">
							<li><fmt:message key="ajuda.viatelefon" />
								${loginInfo.entitat.suportTelefon}</li>
						</c:if>

						<c:if test="${not empty loginInfo.entitat.suportWeb }">
							<li><fmt:message key="ajuda.viaweb" /> <a target="_blank"
								href="${loginInfo.entitat.suportWeb}">${loginInfo.entitat.suportWeb}
							</a></li>
						</c:if>

						<c:if test="${not empty loginInfo.entitat.suportEmail }">
							<li><fmt:message key="ajuda.viaemail" /><a
								href="mailto: ${loginInfo.entitat.suportEmail}">
									${loginInfo.entitat.suportEmail}</a></li>
						</c:if>
					</ul>

				</div>
			</div>

		</c:if>
	</div>
</footer>