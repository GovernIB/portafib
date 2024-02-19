  <c:if test="${empty annexFirmatItems}">
     <%@include file="annexFirmatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty annexFirmatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="annexFirmatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="annexFirmatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="annexFirmatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="annexFirmat" items="${annexFirmatItems}">

        <tr id="annexFirmat_rowid_${annexFirmat.annexfirmatID}">
          <%@include file="annexFirmatListCoreMultipleSelect.jsp" %>

          <%@include file="annexFirmatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="annexFirmatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
