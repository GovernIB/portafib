  <c:if test="${empty pluginFirmaWebPerUsuariAplicacioItems}">
     <%@include file="pluginFirmaWebPerUsuariAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pluginFirmaWebPerUsuariAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginFirmaWebPerUsuariAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pluginFirmaWebPerUsuariAplicacio" items="${pluginFirmaWebPerUsuariAplicacioItems}">

        <tr id="pluginFirmaWebPerUsuariAplicacio_rowid_${pluginFirmaWebPerUsuariAplicacio.pluginfirmawebperusrappid}">
          <%@include file="pluginFirmaWebPerUsuariAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginFirmaWebPerUsuariAplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
