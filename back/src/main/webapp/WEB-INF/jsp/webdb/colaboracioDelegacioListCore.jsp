  <c:if test="${empty colaboracioDelegacioItems}">
     <%@include file="colaboracioDelegacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty colaboracioDelegacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="colaboracioDelegacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="colaboracioDelegacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="colaboracioDelegacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="colaboracioDelegacio" items="${colaboracioDelegacioItems}">

        <tr id="colaboracioDelegacio_rowid_${colaboracioDelegacio.colaboracioDelegacioID}">
          <%@include file="colaboracioDelegacioListCoreMultipleSelect.jsp" %>

          <%@include file="colaboracioDelegacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="colaboracioDelegacioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
