<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="algorismeDeFirma" cssClass="form-search"  modelAttribute="algorismeDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="algorismeDeFirmaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="algorismeDeFirmaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="algorismeDeFirmaListSubtitle.jsp" %>
  <%@include file="algorismeDeFirmaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="algorismeDeFirmaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="algorismeDeFirmaListCore.jsp" %>
  <c:if test="${not empty algorismeDeFirmaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/algorismeDeFirmaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

