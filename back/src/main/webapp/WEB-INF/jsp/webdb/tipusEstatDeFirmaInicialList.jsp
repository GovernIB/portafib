<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tipusEstatDeFirmaInicial" cssClass="form-search"  modelAttribute="tipusEstatDeFirmaInicialFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="tipusEstatDeFirmaInicialListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="tipusEstatDeFirmaInicialListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tipusEstatDeFirmaInicialListSubtitle.jsp" %>
  <%@include file="tipusEstatDeFirmaInicialListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tipusEstatDeFirmaInicialListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tipusEstatDeFirmaInicialListCore.jsp" %>
  <c:if test="${not empty tipusEstatDeFirmaInicialItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tipusEstatDeFirmaInicialListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

