
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="annexFormTitle.jsp" %>


<form:form modelAttribute="annexForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${annexForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="annexFormCorePre.jsp" %>
  <%@include file="annexFormCore.jsp" %>

  <%@include file="annexFormCorePost.jsp" %>

  <%@include file="annexFormButtons.jsp" %>

  <c:if test="${annexForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/annexFormModificable.jsp" %>
  </c:if>

</form:form>


