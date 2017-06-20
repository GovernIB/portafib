  <c:if test="${empty metadadaItems}">
     <%@include file="metadadaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty metadadaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="metadadaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="metadadaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="metadadaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="metadada" items="${metadadaItems}">

        <tr id="metadada_rowid_${metadada.metadadaID}">
          <%@include file="metadadaListCoreMultipleSelect.jsp" %>

          <%@include file="metadadaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="metadadaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
