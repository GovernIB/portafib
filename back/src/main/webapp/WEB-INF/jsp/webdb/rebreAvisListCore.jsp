  <c:if test="${empty rebreAvisItems}">
     <%@include file="rebreAvisListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty rebreAvisItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="rebreAvisListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="rebreAvisListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="rebreAvisListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="rebreAvis" items="${rebreAvisItems}">

        <tr id="rebreAvis_rowid_${rebreAvis.id}">
          <%@include file="rebreAvisListCoreMultipleSelect.jsp" %>

          <%@include file="rebreAvisListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="rebreAvisListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
