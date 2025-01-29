<%@page import="es.caib.portafib.back.security.LoginInfo"%><%@ page
	contentType="text/html;charset=UTF-8" language="java"%><%@ page
	pageEncoding="UTF-8"%><%@include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LoginInfo"
	className="es.caib.portafib.back.security.LoginInfo" />
<un:useConstants var="Constants"
	className="es.caib.portafib.utils.Constants" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="<c:out value="${pageContext.response.locale.language}"/>"
	lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
<c:if test="${loginInfo.needConfigUser}">
	<%
	LoginInfo.getInstance().setNeedConfigUser(false);
	%>

	<%-- 	<c:if test="${not empty loginInfo.error}">
		<c:redirect url="/common/loginerror.html" />
	</c:if>
 --%>
	<c:redirect
		url="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID}/edit" />
</c:if>

<c:if test="${not empty loginInfo.entitatID}">
	<link href="<c:url value="${pfi:fileUrl(loginInfo.entitat.favicon)}"/>"
		rel="shortcut icon" type="image/x-icon" />
</c:if>

<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

	<!--  INICI CAPÇALERA -->

	<tiles:insertAttribute name="cap">
		<tiles:putAttribute name="data" value="${data}" />
	</tiles:insertAttribute>


	<!--  PIPELLES -->
	<div
		class="row-fluid ${isMobile?'container-mobile main-mobile':'container main'}"
		style="margin-bottom: 0px; max-width: none;">

		<ul
			class="nav nav-tabs ${isMobile?'custom-submenu-mobile':'custom-submenu'}"
			style="margin-bottom: 0px; margin-left: 40px;">

			<c:if test="${loginInfo.usuariPersona.usuariIntern }">
				<li class="nav-item ${(empty pipella)?'active' : '' }"><a
					class="nav-link ${(empty pipella)?'active' : '' }"
					href="<c:url value="/canviarPipella/"/>"><fmt:message
							key="inici" /></a></li>
			</c:if>



			<c:forEach var="rolG" items="${loginInfo.roles}">
				<c:set var="rol" value="${rolG.authority}" />
				<c:if
					test="${not( (rol eq 'ROLE_ANY') || (rol eq 'ROLE_USER')  || (rol eq 'ROLE_AUTOFIRMA') )}">
					<li class="nav-item ${(rol eq 'ROLE_COLA')?' dropdown' : '' } ${(pipella eq rol)?' active' : '' }">
						<c:url var="linktab" value="/canviarPipella/${rol}" /> <c:set
							var="href" value="href=\" ${linktab}\"" /> <a
						class="nav-link ${(pipella eq rol)?'active' : '' } " ${href}><fmt:message
								key="${rol}" /> <c:if test="${not(empty avisos[rol])}">
                 &nbsp; <span id="avisos_${rol}"
									class="badge badge-pill badge-warning">${avisos[rol]}</span>
							</c:if> </a>
					</li>

					<c:if test="${rol eq 'ROLE_ADEN' }">
						<li class="nav-item ${(pipella eq 'ROLE_ADAPP')?'active' : '' }">
							<c:url var="linktab" value="/canviarPipella/ROLE_ADAPP" /> <c:set
								var="href" value="href=\" ${linktab}\"" /> <a
							class="nav-link ${(pipella eq 'ROLE_ADAPP')?'active' : '' }"
							${href}><fmt:message key="ROLE_ADAPP" /> <c:if
									test="${not(empty avisos['ROLE_ADAPP'])}">
                    &nbsp; <span class="badge badge-pill badge-warning">${avisos['ROLE_ADAPP']}</span>
								</c:if> </a>

						</li>
					</c:if>

				</c:if>
			</c:forEach>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item ${(pipella eq 'webdb')?'active' : '' }">
					<a class="nav-link ${(pipella eq 'webdb')?'active' : '' }" href="<c:url value="/canviarPipella/webdb"/>">
						WebDatabase</a>
				</li>
			</sec:authorize>

			<c:if test="${pfi:isDesenvolupament()}">
				<li
					class="nav-item ${(pipella eq 'desenvolupament')?'active' : '' }">
					<a class="nav-link ${(pipella eq 'desenvolupament')?'active' : '' }"
					href="<c:url value="/canviarPipella/desenvolupament"/>">
						Desenvolupament</a>
				</li>
			</c:if>

		</ul>


		<!-- INICI MENU + CONTINGUT -->
		<div class="well well-white" style="padding: 10px;">
			<tiles:insertAttribute name="menu_i_contingut">
				<%--  <tiles:insertTemplate template="/WEB-INF/jsp/moduls/menu_i_contingut.jsp"/>  --%>
				<tiles:putAttribute name="menu" value="${menu_tile}" />
				<tiles:putAttribute name="contingut" value="${contingut_tile}" />
			</tiles:insertAttribute>
			<!-- FINAL MENU + CONTINGUT -->
		</div>

		<!-- FINAL DIV PIPELLES -->
	</div>

	<div class="${isMobile?'container-mobile':'container-fluid'}">
		<tiles:insertAttribute name="peu">
		</tiles:insertAttribute>
	</div>

</body>
</html>
