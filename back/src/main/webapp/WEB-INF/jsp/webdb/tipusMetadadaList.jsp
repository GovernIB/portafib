<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tipusMetadada" cssClass="form-search"  modelAttribute="tipusMetadadaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="tipusMetadadaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
    <%@include file="tipusMetadadaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tipusMetadadaListSubtitle.jsp" %>
  <%@include file="tipusMetadadaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tipusMetadadaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tipusMetadadaListCore.jsp" %>
  <c:if test="${not empty tipusMetadadaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tipusMetadadaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

