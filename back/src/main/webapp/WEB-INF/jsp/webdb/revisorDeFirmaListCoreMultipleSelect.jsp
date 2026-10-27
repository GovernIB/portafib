      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" aria-label="Seleccionar" value="${revisorDeFirma.revisorDeFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

