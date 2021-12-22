  <c:if test="${empty blocDeFirmesItems}">
     <%@include file="blocDeFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty blocDeFirmesItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="blocDeFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="blocDeFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="blocDeFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="blocDeFirmes" items="${blocDeFirmesItems}">

        <tr id="blocDeFirmes_rowid_${blocDeFirmes.blocDeFirmesID}">
          <%@include file="blocDeFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="blocDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="blocDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
