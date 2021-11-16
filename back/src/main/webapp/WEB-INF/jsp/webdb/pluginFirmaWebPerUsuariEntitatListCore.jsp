  <c:if test="${empty pluginFirmaWebPerUsuariEntitatItems}">
     <%@include file="pluginFirmaWebPerUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginFirmaWebPerUsuariEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginFirmaWebPerUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pluginFirmaWebPerUsuariEntitat" items="${pluginFirmaWebPerUsuariEntitatItems}">

        <tr id="pluginFirmaWebPerUsuariEntitat_rowid_${pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsrEntID}">
          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginFirmaWebPerUsuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
