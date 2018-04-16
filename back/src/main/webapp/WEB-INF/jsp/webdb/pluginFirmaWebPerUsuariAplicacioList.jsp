<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="pluginFirmaWebPerUsuariAplicacio" cssClass="form-search"  modelAttribute="pluginFirmaWebPerUsuariAplicacioFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="pluginFirmaWebPerUsuariAplicacioListCommon.jsp" %>
  <div id="${formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="pluginFirmaWebPerUsuariAplicacioListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="pluginFirmaWebPerUsuariAplicacioListSubtitle.jsp" %>
  <%@include file="pluginFirmaWebPerUsuariAplicacioListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="pluginFirmaWebPerUsuariAplicacioListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="pluginFirmaWebPerUsuariAplicacioListCore.jsp" %>
  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/pluginFirmaWebPerUsuariAplicacioListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

