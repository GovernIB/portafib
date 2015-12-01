<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="propietatGlobal" cssClass="form-search"  modelAttribute="propietatGlobalFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="propietatGlobalListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="propietatGlobalListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="propietatGlobalListSubtitle.jsp" %>
  <%@include file="propietatGlobalListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="propietatGlobalListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="propietatGlobalListCore.jsp" %>
  <c:if test="${not empty propietatGlobalItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/propietatGlobalListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

