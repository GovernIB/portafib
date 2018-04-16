  <c:if test="${empty usuariEntitatRevisorItems}">
     <%@include file="usuariEntitatRevisorListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariEntitatRevisorItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariEntitatRevisorListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariEntitatRevisorListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariEntitatRevisorListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariEntitatRevisor" items="${usuariEntitatRevisorItems}">

        <tr id="usuariEntitatRevisor_rowid_${usuariEntitatRevisor.usuariEntitatRevisorId}">
          <%@include file="usuariEntitatRevisorListCoreMultipleSelect.jsp" %>

          <%@include file="usuariEntitatRevisorListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariEntitatRevisorListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
