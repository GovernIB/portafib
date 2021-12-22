  <c:if test="${empty tipusDocumentItems}">
     <%@include file="tipusDocumentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusDocumentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusDocumentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusDocumentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusDocumentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusDocument" items="${tipusDocumentItems}">

        <tr id="tipusDocument_rowid_${tipusDocument.tipusDocumentID}">
          <%@include file="tipusDocumentListCoreMultipleSelect.jsp" %>

          <%@include file="tipusDocumentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusDocumentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
