  <c:if test="${empty usuariAplicacioConfiguracioItems}">
     <%@include file="usuariAplicacioConfiguracioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariAplicacioConfiguracioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariAplicacioConfiguracioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariAplicacioConfiguracioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariAplicacioConfiguracioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariAplicacioConfiguracio" items="${usuariAplicacioConfiguracioItems}">

        <tr id="usuariAplicacioConfiguracio_rowid_${usuariAplicacioConfiguracio.usuariAplicacioConfigID}">
          <%@include file="usuariAplicacioConfiguracioListCoreMultipleSelect.jsp" %>

          <%@include file="usuariAplicacioConfiguracioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariAplicacioConfiguracioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
