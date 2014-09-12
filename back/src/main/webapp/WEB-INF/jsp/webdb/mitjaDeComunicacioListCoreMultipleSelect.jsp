      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${mitjaDeComunicacioFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${mitjaDeComunicacio.mitjaDeComunicacioID}"/>
       &nbsp;
      </td>
      </c:if>

