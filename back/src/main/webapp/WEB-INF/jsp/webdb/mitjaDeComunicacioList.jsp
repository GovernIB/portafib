<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="mitjaDeComunicacio" cssClass="form-search"  modelAttribute="mitjaDeComunicacioFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="mitjaDeComunicacioListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
  <%@include file="mitjaDeComunicacioListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="mitjaDeComunicacioListSubtitle.jsp" %>
  <%@include file="mitjaDeComunicacioListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="mitjaDeComunicacioListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty mitjaDeComunicacioItems}">
          <%@include file="mitjaDeComunicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty mitjaDeComunicacioItems}">


  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="mitjaDeComunicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="mitjaDeComunicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="mitjaDeComunicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="mitjaDeComunicacio" items="${mitjaDeComunicacioItems}">

        <tr>
          <%@include file="mitjaDeComunicacioListCoreMultipleSelect.jsp" %>

          <%@include file="mitjaDeComunicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>



          <%@include file="mitjaDeComunicacioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty mitjaDeComunicacioItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

