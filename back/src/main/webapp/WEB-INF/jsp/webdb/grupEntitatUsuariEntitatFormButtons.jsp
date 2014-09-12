<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
  <div class="navbar-form pull-right">
    <c:if test="${__theForm.saveButtonVisible}">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="genapp.save"/>">
    </c:if>
    <c:if test="${__theForm.cancelButtonVisible}">
    <input type="button" class="btn" onclick="goTo('<c:url value="${contexte}/${__theForm.grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID}/cancel"/>')" value="<fmt:message key="genapp.cancel"/>">
    </c:if>
    <c:if test="${!__theForm.nou && __theForm.deleteButtonVisible}">
    <button type="button" class="btn btn-danger" onclick="openModal('<c:url value="${contexte}/${__theForm.grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID}/delete"/>','show');"><fmt:message key="genapp.delete"/></button>
    </c:if>
    <c:set var="bracket" value="{0}"/>
    <c:forEach var="button" items="${__theForm.additionalButtons}">
    <c:if test="${!__theForm.nou || (-1 == fn:indexOf(button.link,bracket))}">
    <c:set var="pk" value="${__theForm.grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID}"/>
    <c:set var="thelink" value="${fn:replace(button.link,bracket, pk)}" />
    <c:if test="${!fn:startsWith(thelink,'javascript:')}">
     <c:url var="thelink" value="${thelink}"/>
     <c:set var="thelink" value="goTo('${thelink}')"/>
    </c:if>
    <button type="button" class="btn ${button.type}" 
       onclick="${thelink}">
       <i class="${button.icon}"></i><fmt:message key="${button.codeText}"/>
    </button>
    </c:if>
    </c:forEach>
  </div>
