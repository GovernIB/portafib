      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${revisorDeDestinatari.revisorDeDestinatariID}"/>
       &nbsp;
      </td>
      </c:if>

