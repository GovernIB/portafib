  <c:if test="${empty estadisticaItems}">
     <%@include file="estadisticaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estadisticaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estadisticaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estadisticaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estadisticaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estadistica" items="${estadisticaItems}">

        <tr id="estadistica_rowid_${estadistica.estadisticaID}">
          <%@include file="estadisticaListCoreMultipleSelect.jsp" %>

          <%@include file="estadisticaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estadisticaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
