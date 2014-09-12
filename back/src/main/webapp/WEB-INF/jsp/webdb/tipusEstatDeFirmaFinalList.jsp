<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tipusEstatDeFirmaFinal" cssClass="form-search"  modelAttribute="tipusEstatDeFirmaFinalFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="tipusEstatDeFirmaFinalListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
    <%@include file="tipusEstatDeFirmaFinalListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tipusEstatDeFirmaFinalListSubtitle.jsp" %>
  <%@include file="tipusEstatDeFirmaFinalListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tipusEstatDeFirmaFinalListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tipusEstatDeFirmaFinalListCore.jsp" %>
  <c:if test="${not empty tipusEstatDeFirmaFinalItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tipusEstatDeFirmaFinalListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

