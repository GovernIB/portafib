  <c:if test="${empty firmaItems}">
     <%@include file="firmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty firmaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="firmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="firmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="firmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="firma" items="${firmaItems}">

        <tr id="firma_rowid_${firma.firmaID}">
          <%@include file="firmaListCoreMultipleSelect.jsp" %>

          <%@include file="firmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="firmaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
