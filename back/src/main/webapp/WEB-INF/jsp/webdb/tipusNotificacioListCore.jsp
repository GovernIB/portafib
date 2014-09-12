  <c:if test="${empty tipusNotificacioItems}">
     <%@include file="tipusNotificacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusNotificacioItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusNotificacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusNotificacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusNotificacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusNotificacio" items="${tipusNotificacioItems}">

        <tr>
          <%@include file="tipusNotificacioListCoreMultipleSelect.jsp" %>

          <%@include file="tipusNotificacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusNotificacioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
