  <c:if test="${empty perfilsPerUsuariAplicacioItems}">
     <%@include file="perfilsPerUsuariAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty perfilsPerUsuariAplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="perfilsPerUsuariAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="perfilsPerUsuariAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="perfilsPerUsuariAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="perfilsPerUsuariAplicacio" items="${perfilsPerUsuariAplicacioItems}">

        <tr id="perfilsPerUsuariAplicacio_rowid_${perfilsPerUsuariAplicacio.perfilsPerUsrAppID}">
          <%@include file="perfilsPerUsuariAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="perfilsPerUsuariAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="perfilsPerUsuariAplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
