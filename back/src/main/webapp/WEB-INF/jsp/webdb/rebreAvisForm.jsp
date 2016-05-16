
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="rebreAvisFormTitle.jsp" %>


<form:form modelAttribute="rebreAvisForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${rebreAvisForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="rebreAvisFormCorePre.jsp" %>
  <%@include file="rebreAvisFormCore.jsp" %>

  <%@include file="rebreAvisFormCorePost.jsp" %>

  <%@include file="rebreAvisFormButtons.jsp" %>

  <c:if test="${rebreAvisForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/rebreAvisFormModificable.jsp" %>
  </c:if>

</form:form>

