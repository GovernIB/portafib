  <c:if test="${empty tipusMetadadaItems}">
     <%@include file="tipusMetadadaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusMetadadaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusMetadadaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusMetadadaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusMetadadaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusMetadada" items="${tipusMetadadaItems}">

        <tr>
          <%@include file="tipusMetadadaListCoreMultipleSelect.jsp" %>

          <%@include file="tipusMetadadaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusMetadadaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
