
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="grupEntitatUsuariEntitatFormTitle.jsp" %>


<form:form modelAttribute="grupEntitatUsuariEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${grupEntitatUsuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupEntitatUsuariEntitatFormCorePre.jsp" %>
  <%@include file="grupEntitatUsuariEntitatFormCore.jsp" %>

  <%@include file="grupEntitatUsuariEntitatFormCorePost.jsp" %>

  <%@include file="grupEntitatUsuariEntitatFormButtons.jsp" %>

  <c:if test="${grupEntitatUsuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupEntitatUsuariEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


