  <c:if test="${empty codiBarresItems}">
     <%@include file="codiBarresListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty codiBarresItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="codiBarresListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="codiBarresListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="codiBarresListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="codiBarres" items="${codiBarresItems}">

        <tr id="codiBarres_rowid_${codiBarres.codiBarresID}">
          <%@include file="codiBarresListCoreMultipleSelect.jsp" %>

          <%@include file="codiBarresListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="codiBarresListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
