<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="roleUsuariEntitat" cssClass="form-search"  modelAttribute="roleUsuariEntitatFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="../webdb/roleUsuariEntitatListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
  <%@include file="../webdb/roleUsuariEntitatListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="../webdb/roleUsuariEntitatListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="../webdb/roleUsuariEntitatListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty roleUsuariEntitatItems}">
       <%@include file="../webdb/roleUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty roleUsuariEntitatItems}">


  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>
      
          <%@include file="../webdb/roleUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="../webdb/roleUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>
		  <th><fmt:message key="entitat.entitat" /></th>

          <%@include file="../webdb/roleUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="roleUsuariEntitat" items="${roleUsuariEntitatItems}">

        <tr>
          <%@include file="../webdb/roleUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="../webdb/roleUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>
          <td>
			${roleUsuariEntitat.usuariEntitat.entitat.nom}
          </td>

          <%@include file="../webdb/roleUsuariEntitatListButtons.jsp" %>

        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty roleUsuariEntitatItems}">
     <%@include file="../webdb/webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

