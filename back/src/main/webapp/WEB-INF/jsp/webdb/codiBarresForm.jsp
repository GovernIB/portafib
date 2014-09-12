
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="codiBarresFormTitle.jsp" %>


<form:form modelAttribute="codiBarresForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${codiBarresForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="codiBarresFormCorePre.jsp" %>
  <%@include file="codiBarresFormCore.jsp" %>

  <%@include file="codiBarresFormCorePost.jsp" %>

  <%@include file="codiBarresFormButtons.jsp" %>

  <c:if test="${codiBarresForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/codiBarresFormModificable.jsp" %>
  </c:if>

</form:form>


