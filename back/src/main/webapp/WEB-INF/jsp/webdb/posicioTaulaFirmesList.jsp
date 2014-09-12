<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="posicioTaulaFirmes" cssClass="form-search"  modelAttribute="posicioTaulaFirmesFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="posicioTaulaFirmesListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
    <%@include file="posicioTaulaFirmesListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="posicioTaulaFirmesListSubtitle.jsp" %>
  <%@include file="posicioTaulaFirmesListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="posicioTaulaFirmesListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="posicioTaulaFirmesListCore.jsp" %>
  <c:if test="${not empty posicioTaulaFirmesItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/posicioTaulaFirmesListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

