  <c:if test="${empty grupEntitatUsuariEntitatItems}">
     <%@include file="grupEntitatUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupEntitatUsuariEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="grupEntitatUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="grupEntitatUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="grupEntitatUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="grupEntitatUsuariEntitat" items="${grupEntitatUsuariEntitatItems}">

        <tr id="grupEntitatUsuariEntitat_rowid_${grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID}">
          <%@include file="grupEntitatUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="grupEntitatUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="grupEntitatUsuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
