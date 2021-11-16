  <c:if test="${empty notificacioWSItems}">
     <%@include file="notificacioWSListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty notificacioWSItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
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

        <tr id="notificacioWS_rowid_${notificacioWS.notificacioID}">
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
  
