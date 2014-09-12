
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="custodiaInfoFormTitle.jsp" %>


<form:form modelAttribute="custodiaInfoForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${custodiaInfoForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="custodiaInfoFormCorePre.jsp" %>
  <%@include file="custodiaInfoFormCore.jsp" %>

  <%@include file="custodiaInfoFormCorePost.jsp" %>

  <%@include file="custodiaInfoFormButtons.jsp" %>

  <c:if test="${custodiaInfoForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/custodiaInfoFormModificable.jsp" %>
  </c:if>

</form:form>


