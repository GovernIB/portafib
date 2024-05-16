  <c:if test="${empty revisorDeDestinatariItems}">
     <%@include file="revisorDeDestinatariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty revisorDeDestinatariItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="revisorDeDestinatariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="revisorDeDestinatariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="revisorDeDestinatariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="revisorDeDestinatari" items="${revisorDeDestinatariItems}">

        <tr id="revisorDeDestinatari_rowid_${revisorDeDestinatari.revisorDeDestinatariID}">
          <%@include file="revisorDeDestinatariListCoreMultipleSelect.jsp" %>

          <%@include file="revisorDeDestinatariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="revisorDeDestinatariListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
