  <c:if test="${empty usuariPersonaItems}">
     <%@include file="usuariPersonaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariPersonaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariPersonaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariPersonaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariPersonaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariPersona" items="${usuariPersonaItems}">

        <tr id="usuariPersona_rowid_${usuariPersona.usuariPersonaID}">
          <%@include file="usuariPersonaListCoreMultipleSelect.jsp" %>

          <%@include file="usuariPersonaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariPersonaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
