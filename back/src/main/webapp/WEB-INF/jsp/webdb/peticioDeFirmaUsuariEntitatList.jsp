<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="peticioDeFirmaUsuariEntitat" cssClass="form-search"  modelAttribute="peticioDeFirmaUsuariEntitatFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="peticioDeFirmaUsuariEntitatListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
  <%@include file="peticioDeFirmaUsuariEntitatListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="peticioDeFirmaUsuariEntitatListSubtitle.jsp" %>
  <%@include file="peticioDeFirmaUsuariEntitatListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="peticioDeFirmaUsuariEntitatListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty peticioDeFirmaUsuariEntitatItems}">
          <%@include file="peticioDeFirmaUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty peticioDeFirmaUsuariEntitatItems}">


  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="peticioDeFirmaUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="peticioDeFirmaUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="peticioDeFirmaUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="peticioDeFirmaUsuariEntitat" items="${peticioDeFirmaUsuariEntitatItems}">

        <tr>
          <%@include file="peticioDeFirmaUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="peticioDeFirmaUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>



          <%@include file="peticioDeFirmaUsuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty peticioDeFirmaUsuariEntitatItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

