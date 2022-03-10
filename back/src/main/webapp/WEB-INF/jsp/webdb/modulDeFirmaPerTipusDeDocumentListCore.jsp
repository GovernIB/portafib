  <c:if test="${empty modulDeFirmaPerTipusDeDocumentItems}">
     <%@include file="modulDeFirmaPerTipusDeDocumentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
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

        <tr id="modulDeFirmaPerTipusDeDocument_rowid_${modulDeFirmaPerTipusDeDocument.ID}">
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
  
