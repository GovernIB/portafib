  <c:if test="${empty pluginItems}">
     <%@include file="pluginListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pluginListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="plugin" items="${pluginItems}">

        <tr id="plugin_rowid_${plugin.pluginID}">
          <%@include file="pluginListCoreMultipleSelect.jsp" %>

          <%@include file="pluginListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
