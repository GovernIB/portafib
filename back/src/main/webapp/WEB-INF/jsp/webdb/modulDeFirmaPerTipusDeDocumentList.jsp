<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="modulDeFirmaPerTipusDeDocument" cssClass="form-search"  modelAttribute="modulDeFirmaPerTipusDeDocumentFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="modulDeFirmaPerTipusDeDocumentListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="modulDeFirmaPerTipusDeDocumentListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="modulDeFirmaPerTipusDeDocumentListSubtitle.jsp" %>
  <%@include file="modulDeFirmaPerTipusDeDocumentListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="modulDeFirmaPerTipusDeDocumentListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="modulDeFirmaPerTipusDeDocumentListCore.jsp" %>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/modulDeFirmaPerTipusDeDocumentListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

