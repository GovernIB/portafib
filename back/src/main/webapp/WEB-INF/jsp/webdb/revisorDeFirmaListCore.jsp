  <c:if test="${empty revisorDeFirmaItems}">
     <%@include file="revisorDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty revisorDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="revisorDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="revisorDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="revisorDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="revisorDeFirma" items="${revisorDeFirmaItems}">

        <tr id="revisorDeFirma_rowid_${revisorDeFirma.revisorDeFirmaID}">
          <%@include file="revisorDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="revisorDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="revisorDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
