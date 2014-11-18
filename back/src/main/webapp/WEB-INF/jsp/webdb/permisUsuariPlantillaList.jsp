<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="permisUsuariPlantilla" cssClass="form-search"  modelAttribute="permisUsuariPlantillaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="permisUsuariPlantillaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="permisUsuariPlantillaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="permisUsuariPlantillaListSubtitle.jsp" %>
  <%@include file="permisUsuariPlantillaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="permisUsuariPlantillaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="permisUsuariPlantillaListCore.jsp" %>
  <c:if test="${not empty permisUsuariPlantillaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/permisUsuariPlantillaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

