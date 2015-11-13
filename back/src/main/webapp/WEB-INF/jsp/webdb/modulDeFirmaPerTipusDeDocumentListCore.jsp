  <c:if test="${empty modulDeFirmaPerTipusDeDocumentItems}">
     <%@include file="modulDeFirmaPerTipusDeDocumentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="modulDeFirmaPerTipusDeDocumentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="modulDeFirmaPerTipusDeDocumentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="modulDeFirmaPerTipusDeDocumentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="modulDeFirmaPerTipusDeDocument" items="${modulDeFirmaPerTipusDeDocumentItems}">

        <tr>
          <%@include file="modulDeFirmaPerTipusDeDocumentListCoreMultipleSelect.jsp" %>

          <%@include file="modulDeFirmaPerTipusDeDocumentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="modulDeFirmaPerTipusDeDocumentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
