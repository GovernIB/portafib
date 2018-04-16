
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="pluginFirmaWebPerUsuariEntitatFormTitle.jsp" %>


<form:form modelAttribute="pluginFirmaWebPerUsuariEntitatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${pluginFirmaWebPerUsuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginFirmaWebPerUsuariEntitatFormCorePre.jsp" %>
  <%@include file="pluginFirmaWebPerUsuariEntitatFormCore.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariEntitatFormCorePost.jsp" %>

  <%@include file="pluginFirmaWebPerUsuariEntitatFormButtons.jsp" %>

  <c:if test="${pluginFirmaWebPerUsuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginFirmaWebPerUsuariEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


