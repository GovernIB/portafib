  <c:if test="${empty tipusDocumentColaboracioDelegacioItems}">
     <%@include file="tipusDocumentColaboracioDelegacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusDocumentColaboracioDelegacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
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

        <tr id="tipusDocumentColaboracioDelegacio_rowid_${tipusDocumentColaboracioDelegacio.id}">
          <%@include file="tipusDocumentColaboracioDelegacioListCoreMultipleSelect.jsp" %>

          <%@include file="tipusDocumentColaboracioDelegacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusDocumentColaboracioDelegacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
