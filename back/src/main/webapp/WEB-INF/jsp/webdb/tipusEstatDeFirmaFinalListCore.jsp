  <c:if test="${empty tipusEstatDeFirmaFinalItems}">
     <%@include file="tipusEstatDeFirmaFinalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusEstatDeFirmaFinalItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusEstatDeFirmaFinalListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusEstatDeFirmaFinalListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusEstatDeFirmaFinalListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusEstatDeFirmaFinal" items="${tipusEstatDeFirmaFinalItems}">

        <tr>
          <%@include file="tipusEstatDeFirmaFinalListCoreMultipleSelect.jsp" %>

          <%@include file="tipusEstatDeFirmaFinalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusEstatDeFirmaFinalListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
