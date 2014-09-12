  <c:if test="${empty tipusDocumentColaboracioDelegacioItems}">
     <%@include file="tipusDocumentColaboracioDelegacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusDocumentColaboracioDelegacioItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusDocumentColaboracioDelegacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusDocumentColaboracioDelegacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusDocumentColaboracioDelegacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusDocumentColaboracioDelegacio" items="${tipusDocumentColaboracioDelegacioItems}">

        <tr>
          <%@include file="tipusDocumentColaboracioDelegacioListCoreMultipleSelect.jsp" %>

          <%@include file="tipusDocumentColaboracioDelegacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusDocumentColaboracioDelegacioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
