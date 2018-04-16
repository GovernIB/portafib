      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${revisorDeFirma.revisorDeFirmaID}"/>
       &nbsp;
      </td>
      </c:if>

