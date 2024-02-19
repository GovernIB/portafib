  <c:if test="${empty usuariAplicacioItems}">
     <%@include file="usuariAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariAplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariAplicacio" items="${usuariAplicacioItems}">

        <tr id="usuariAplicacio_rowid_${usuariAplicacio.usuariAplicacioID}">
          <%@include file="usuariAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="usuariAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariAplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
