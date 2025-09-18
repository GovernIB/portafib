  <c:if test="${empty pseudonimItems}">
     <%@include file="pseudonimListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pseudonimItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pseudonimListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pseudonimListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pseudonimListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pseudonim" items="${pseudonimItems}">

        <tr id="pseudonim_rowid_${pseudonim.pseudonimid}">
          <%@include file="pseudonimListCoreMultipleSelect.jsp" %>

          <%@include file="pseudonimListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pseudonimListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
