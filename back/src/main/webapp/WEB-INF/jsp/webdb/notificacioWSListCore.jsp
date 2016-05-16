  <c:if test="${empty notificacioWSItems}">
     <%@include file="notificacioWSListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty notificacioWSItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="notificacioWSListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="notificacioWSListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="notificacioWSListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="notificacioWS" items="${notificacioWSItems}">

        <tr>
          <%@include file="notificacioWSListCoreMultipleSelect.jsp" %>

          <%@include file="notificacioWSListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="notificacioWSListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  