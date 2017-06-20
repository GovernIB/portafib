  <c:if test="${empty usuariEntitatItems}">
     <%@include file="usuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariEntitat" items="${usuariEntitatItems}">

        <tr id="usuariEntitat_rowid_${usuariEntitat.usuariEntitatID}">
          <%@include file="usuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="usuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
