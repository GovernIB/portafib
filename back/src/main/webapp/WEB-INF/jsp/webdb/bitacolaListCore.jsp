  <c:if test="${empty bitacolaItems}">
     <%@include file="bitacolaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty bitacolaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="bitacolaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="bitacolaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="bitacolaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="bitacola" items="${bitacolaItems}">

        <tr id="bitacola_rowid_${bitacola.bitacolaID}">
          <%@include file="bitacolaListCoreMultipleSelect.jsp" %>

          <%@include file="bitacolaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="bitacolaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
