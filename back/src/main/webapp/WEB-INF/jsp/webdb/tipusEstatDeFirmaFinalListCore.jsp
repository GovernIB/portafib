  <c:if test="${empty tipusEstatDeFirmaFinalItems}">
     <%@include file="tipusEstatDeFirmaFinalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusEstatDeFirmaFinalItems}">

  <div class="row" style="margin-left: 0px;">
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

        <tr id="tipusEstatDeFirmaFinal_rowid_${tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID}">
          <%@include file="tipusEstatDeFirmaFinalListCoreMultipleSelect.jsp" %>

          <%@include file="tipusEstatDeFirmaFinalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusEstatDeFirmaFinalListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
