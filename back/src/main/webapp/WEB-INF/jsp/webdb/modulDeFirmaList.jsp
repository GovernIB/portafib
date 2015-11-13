<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="modulDeFirma" cssClass="form-search"  modelAttribute="modulDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="modulDeFirmaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="modulDeFirmaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="modulDeFirmaListSubtitle.jsp" %>
  <%@include file="modulDeFirmaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="modulDeFirmaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="modulDeFirmaListCore.jsp" %>
  <c:if test="${not empty modulDeFirmaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/modulDeFirmaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

