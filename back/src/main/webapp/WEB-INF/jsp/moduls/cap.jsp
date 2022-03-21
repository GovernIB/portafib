<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%><%@ taglib prefix="tiles"
    uri="http://tiles.apache.org/tags-tiles"%>
<header>
    <!-- Header -->
    <nav
        class="navbar navbar-expand-md navbar-dark fixed-top bg-aplicacio"
        style="padding: 0;">

 
        <!-- Logo i nom aplicació -->
        <div class="navbar-brand menuGovern">

            <c:if test="${not empty loginInfo.entitatID}">
                <div class="logoGovern">
                    <a href="<c:out value="${loginInfo.entitat.web}" />"
                        target="_blank"> <img
                        src="<c:url value="${pfi:fileUrl(loginInfo.entitat.logoWeb)}"/>"
                        title="${loginInfo.entitat.nom}"
                        alt="${loginInfo.entitat.nom}" />
                    </a>
                </div>
            </c:if>
            <div class="logoGovern">
                <img src="<c:url value="/img/app-logo-header.png"/>"
                    alt="PortaFIB" title="PortaFIB" />
            </div>
            <c:if test="${isMobile}">
                <div class="clearfix"></div>
            </c:if>
        </div>
        <!-- FI Logo i nom aplicació -->

        <!-- Botons -->
        <div class="collapse navbar-collapse" id="navbarCollapse">

            <ul class="navbar-nav mobil">
                <c:if test="${not empty loginInfo.entitatID}">
                    <c:if test="${loginInfo.usuariPersona.usuariIntern}">

                        <li style="margin-top:14px;"><a
                            href="<c:out value="${loginInfo.entitat.web}" />"
                            target="_blank">
                               <i> ${loginInfo.entitat.nom}</i> </a>
                        </li>


                        <li class="dropdown colorVerd">

                            <button
                                class="btn colorVerd dropdown-toggle"
                                type="button" id="dropdownMenu2"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                                <i class="fas fa-home fa-lg"></i>
                                <fmt:message key="estitat_entitats" />                                
                            </button>

                            <div class="dropdown-menu"
                                aria-labelledby="dropdownMenu2">
                                <c:forEach var="entry"
                                    items="${loginInfo.entitats}">
                                    <c:if
                                        test="${not(entry.key eq loginInfo.entitatID)}">
                                        <a class="dropdown-item"
                                            href="<c:url value="/canviarEntitat/${entry.key}"></c:url>">
                                            ${entry.value.nom} </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </li>

                    </c:if>
                </c:if>

                <!--  IDIOMES -->
                <c:if test="${not empty loginInfo}">
                    <li class="dropdown colorVerd">

                        <button class="btn colorVerd dropdown-toggle"
                            type="button" id="dropdownMenu2"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <i class="fas fa-language fa-lg"></i>
                          <!--   <fmt:message key="idiomes" />  -->
                        </button>
                        <div class="dropdown-menu"
                            aria-labelledby="dropdownMenu2">
                            <c:forEach var="idioma" items="${idiomes}" varStatus="status">
                            
						
							<c:set var="idiomaID" value="${idioma.idiomaID}" />
							
							<a class="dropdown-item"
								href="?lang=${idiomaID}">
								<img
								src="<c:url value="/img/${idiomaID}_petit_${lang eq idiomaID? 'on' : 'off'}.gif"/>"
								alt="${idiomaID}" width="17" height="14" border="0" />${idioma.nom}
							</a>
							
							

                            
<%--                             <c:set var="idioma" value="${idiomaItem.idiomaID}"/>
                            
                            <a class="dropdown-item"
                                href="<c:url value="/canviarIdioma/${idioma}"></c:url>">
                                <img
                                src="<c:url value="/img/${idioma}_petit_${lang eq idioma? 'on' : 'off'}.gif"/>"
                                alt="${idiomaItem.nom}" width="17"
                                height="14" border="0" />
                            </a>
 --%>                                
                                
                                
                            </c:forEach>

                        </div>
                    </li>
                </c:if>

                <%--   OPCIONS ...  --%>
                <c:if test="${not empty loginInfo.usuariPersona}">


                    <li class="dropdown colorVerd">

                        <button class="btn colorVerd dropdown-toggle"
                            type="button" id="dropdownMenu3"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                        <div class="dropdown-menu  dropdown-menu-right"
                            aria-labelledby="dropdownMenu3">



                            <c:if test="${not empty loginInfo  }">
                                <a class="dropdown-item" href="#"> <i
                                    class="icon-user icon-white"></i>
                                    <i>
                                    ${loginInfo.usuariPersona.nom}&nbsp;${loginInfo.usuariPersona.llinatges}
                                    </i>
                                </a>

                                <c:if
                                    test="${loginInfo.usuariPersona.usuariIntern}">
                                    <a class="dropdown-item"
                                        href="<c:url value="/common/configuracio/usuaripersona/${pageContext.request.userPrincipal.name}/edit"/>">
                                        <i class="fas fa-cog"></i> <fmt:message
                                            key="configuracio" />
                                    </a>
                                </c:if>



                                <c:if test="${not empty menuLogOutUrl}">
                                    <a class="dropdown-item"
                                        href="<c:url value="${menuLogOutUrl}" />"
                                        <i class="fas fa-sign-out-alt"></i>
                                        <fmt:message key="sortir" /></a>
                                </c:if>

                            </c:if>
                        </div>

                    </li>
                    </c:if>

         
            </ul>
        </div>
        <!--   </div>
          </div>  -->
        <!-- FI Botons -->
    </nav>

    <!-- FI Header -->
</header>